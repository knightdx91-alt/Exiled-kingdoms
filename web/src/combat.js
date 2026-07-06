// Real-time-with-pause combat — reversed from net.fdgames (deobf/COMBAT_SPEC.md).
//
// Enemies (PatrollerAI) aggro the hero within AGGRO tiles, chase via the scene's A*,
// and attack when within weapon reach. Followers (CompanionAI) fight the hero's target
// or the nearest enemy, otherwise trail the hero. Damage uses the recovered rolls and
// the exact g0/armor mitigation. Death drops loot (gold/items) and awards XP. A pause
// toggle freezes ticks — EK's "real-time with pause".

import { setFacing } from './sprite.js';
import { facingFor } from './move.js';

const AGGRO_TILES = 6;      // enemy detection radius
const LEASH_TILES = 12;     // enemy gives up beyond this
const FOLLOW_MAX = 4;       // a follower stays within this many tiles of the hero
const REPLAN_MS = 400;      // how often a chaser re-plans its path
const MOVE_SPEED = 120;     // px/sec for combatants (hero is 140 in main.js)

// Hostile factions (everything else — player/neutral/npc/ally — is friendly to the hero).
const HOSTILE = new Set(['enemy', 'beasts', 'bandits', 'monster', 'undead']);
const FRIENDLY_FACTIONS = new Set(['player', 'ally', 'companion']);

// Percentage mitigation with diminishing returns above 50 (CharacterSheet.g0).
export function g0(hp, r) {
  if (r === 0) return hp;
  if (r > 50) r = r <= 80 ? 50 + ((r - 50) / 2 | 0)
                 : r <= 110 ? 65 + ((r - 80) / 3 | 0)
                 : 75 + ((r - 110) / 4 | 0);
  if (r >= 100) return 0;
  return ((100 - r) * hp / 100) | 0;
}

// EK's inclusive random int (FDUtils.b(a,b)).
const rint = (a, b) => a + Math.floor(Math.random() * (Math.max(a, b) - a + 1));

// Apply a raw hit to a defender's armor/resistances → final HP loss (COMBAT_SPEC.md).
export function mitigate(rawHp, type, armor, resist, opts = {}) {
  if (type === 'Normal' || !type) {
    let J = armor + (opts.projectile ? (opts.block || 0) : 0);
    const f = opts.shield ? 0.4 : 0.2;
    const absorbed = rint(Math.floor(J * f), J);
    return Math.max(rawHp - absorbed, Math.ceil(rawHp * 0.15));
  }
  let taken = g0(rawHp, (resist && resist[type]) || 0);
  if (opts.projectile && opts.block) taken -= rint(Math.floor(opts.block / 3), opts.block);
  return Math.max(0, taken);
}

// Roll an attack's damage from a weapon + the attacker's flat bonus (CharacterSheet.i()).
export function rollDamage(weapon, dmgBonus) {
  const w = weapon || { min: 1, max: 3, crit: 5, critMul: 200, type: 'Normal' };
  const crit = rint(1, 100) <= (w.crit || 0);
  let hp;
  if (crit) {
    const mul = Math.max(150, w.critMul || 200);
    hp = rint(dmgBonus + w.max + 1, Math.round((mul / 100) * (dmgBonus + w.max)));
  } else {
    hp = rint(dmgBonus + w.min, dmgBonus + w.max);
  }
  return { hp: Math.max(1, hp), crit, type: w.type || 'Normal', projectile: !!w.ranged };
}

// Monster max-HP per race tier (APPROX — exact CharacterStats.g() enum mapping is a
// later fidelity pass; tuned so a lv-1 goblin ≈ 10 HP, bosses are beefy).
const RACE_HP = {
  weak:     { base: 6,  per: 2 },
  '':       { base: 10, per: 4 },
  strong:   { base: 18, per: 6 },
  miniboss: { base: 40, per: 10 },
  boss:     { base: 80, per: 16 },
  human:    { base: 20, per: 4 },
  npc:      { base: 20, per: 4 },
};
const CLASS_DMGMUL = { warrior: 0.5, rogue: 0.75, cleric: 0.3, wizard: 0.0 };
// XP for killing a monster of a given level (APPROX; EK scales reward by level).
const killXP = (lvl) => 8 + lvl * 6;

export class Combat {
  constructor(scene) {
    this.s = scene;
    this.paused = false;
    this.floaters = [];          // active floating damage/status texts
    this._target = null;         // the hero's current attack target (entity)
  }

  // Build combat state for a spawned entity from its bestiary record. Returns the cbt
  // object (also stored on the entity as `e.cbt`), or null for non-combatants.
  attach(e) {
    const rec = e.rec || {};
    const faction = (rec.faction || '').toLowerCase();
    const hostile = HOSTILE.has(faction);
    const friendly = FRIENDLY_FACTIONS.has(faction) || this._isFollower(e);
    if (!hostile && !friendly) { e.cbt = null; return null; }   // pure NPC (talk only)
    const level = rint(rec.minlevel || 1, rec.maxlevel || rec.minlevel || 1);
    const hpT = RACE_HP[rec.race] || RACE_HP[''];
    const maxHp = Math.max(1, hpT.base + hpT.per * level);
    const weapon = this.s.weapons ? this.s.weapons[rec.weapon] : null;
    const dmgMul = CLASS_DMGMUL[rec.class] ?? 0.4;
    e.cbt = {
      side: hostile ? 'enemy' : 'ally',
      hp: maxHp, maxHp, level,
      armor: rec.armor || 0, resist: rec.resist || {},
      weapon, dmgBonus: Math.max(0, Math.round(dmgMul * level)),
      reach: (weapon && weapon.reach) || 1,
      attackCd: 0, target: null, path: null, pathIdx: 0, replan: 0,
      loot: rec.loot || '', xp: killXP(level), dead: false,
    };
    return e.cbt;
  }

  _isFollower(e) {
    const f = this.s.gameState && this.s.gameState.followers;
    if (!f) return false;
    return f.has(e.npc.name) || f.has(e.npc.tag) || f.has(e.npc.spawn);
  }

  // The hero as a combatant view over the PlayerModel.
  _heroCbt() {
    const m = this.s.playerModel;
    if (!m) return null;
    const w = this.s.weapons ? this.s.weapons[m.weaponId()] : null;
    return { hp: m.hp, maxHp: m.maxHP(), armor: m.armor(), resist: m.resist(),
             weapon: w, dmgBonus: m.dmgBonus(), reach: (w && w.reach) || 1,
             shield: m.hasShield() };
  }

  // Player taps an enemy → make it the hero's attack target.
  setTarget(e) {
    if (e && e.cbt && e.cbt.side === 'enemy' && !e.cbt.dead) this._target = e;
  }
  clearTarget() { this._target = null; }
  get target() { return this._target; }

  togglePause() { this.paused = !this.paused; return this.paused; }

  // ---- main tick --------------------------------------------------------------
  tick(dtMs) {
    const dt = dtMs / 1000;
    this._tickFloaters(dt);
    if (this.paused || this.s._dialogue || this.s._loading || !this.s.hero) return;
    const hc = this.s.heroCell;
    if (!hc) return;

    // hero auto-attacks its chosen target
    this._heroTurn(dt, hc);

    for (const e of this.s.entities) {
      const c = e.cbt;
      if (!c || c.dead) continue;
      c.attackCd = Math.max(0, c.attackCd - dtMs);
      c.replan = Math.max(0, c.replan - dtMs);
      if (c.side === 'enemy') this._enemyTurn(e, dt, hc);
      else this._allyTurn(e, dt, hc);
    }
  }

  _dist(a, b) { return Math.max(Math.abs(a.c - b.c), Math.abs(a.r - b.r)); }

  _heroTurn(dt, hc) {
    const t = this._target;
    if (!t || !t.cbt || t.cbt.dead) { this._target = null; return; }
    const hero = this._heroCbt();
    if (!hero) return;
    const d = this._dist(hc, t.cell);
    if (d <= hero.reach) {
      this.s.path = null;                       // stop walking; we're in range
      if (!this._heroCd || this._heroCd <= 0) {
        this._resolveAttack(hero, t.cbt, this.s.hero, t, false);
        this._heroCd = (hero.weapon && hero.weapon.speed || 10) * 45;
        this._face(this.s.hero, this.s.heroKey, hc, t.cell);
        if (t.cbt.dead) this._onEnemyDeath(t);
      }
    } else if (d <= 14) {
      // walk toward the target using the scene's own pathing
      if (!this.s.path) {
        const goal = this.s.nearestWalkable(t.cell.c, t.cell.r);
        const p = this.s.planPath(hc, goal);
        if (p && p.length > 1) { this.s.path = p; this.s.pathIdx = 1; }
      }
    }
    this._heroCd = Math.max(0, (this._heroCd || 0) - dt * 1000);
  }

  _enemyTurn(e, dt, hc) {
    const c = e.cbt;
    const d = this._dist(e.cell, hc);
    if (!c.target && d <= AGGRO_TILES) c.target = 'hero';
    if (c.target && d > LEASH_TILES) { c.target = null; c.path = null; return; }
    if (!c.target) return;
    if (d <= c.reach) {
      c.path = null;
      if (c.attackCd <= 0) {
        const hero = this._heroCbt();
        this._resolveAttack(c, hero, e.sprite, { sprite: this.s.hero, cell: hc }, true);
        c.attackCd = (c.weapon && c.weapon.speed || 12) * 60;
        this._face(e.sprite, e.key, e.cell, hc);
        this._syncHeroHp(hero);
      }
    } else {
      this._stepToward(e, hc, dt);
    }
  }

  _allyTurn(e, dt, hc) {
    const c = e.cbt;
    // pick a target: the hero's target, else nearest enemy within aggro
    let tgt = (this._target && this._target.cbt && !this._target.cbt.dead) ? this._target : null;
    if (!tgt) {
      let best = null, bd = AGGRO_TILES + 0.5;
      for (const o of this.s.entities) {
        if (!o.cbt || o.cbt.dead || o.cbt.side !== 'enemy') continue;
        const dd = this._dist(e.cell, o.cell);
        if (dd < bd) { bd = dd; best = o; }
      }
      tgt = best;
    }
    if (tgt) {
      const d = this._dist(e.cell, tgt.cell);
      if (d <= c.reach) {
        if (c.attackCd <= 0) {
          this._resolveAttack(c, tgt.cbt, e.sprite, tgt, false);
          c.attackCd = (c.weapon && c.weapon.speed || 12) * 60;
          this._face(e.sprite, e.key, e.cell, tgt.cell);
          if (tgt.cbt.dead) this._onEnemyDeath(tgt);
        }
      } else {
        this._stepToward(e, tgt.cell, dt);
      }
    } else if (this._dist(e.cell, hc) > FOLLOW_MAX) {
      this._stepToward(e, hc, dt);            // trail the hero
    }
  }

  // Move an entity one step toward a target cell, re-planning periodically.
  _stepToward(e, targetCell, dt) {
    const c = e.cbt;
    if (!c.path || c.replan <= 0 || c.pathIdx >= c.path.length) {
      const goal = this.s.nearestWalkable(targetCell.c, targetCell.r);
      const p = this.s.planPath(e.cell, goal);
      c.path = (p && p.length > 1) ? p : null;
      c.pathIdx = 1; c.replan = REPLAN_MS;
    }
    if (!c.path) return;
    const next = c.path[c.pathIdx];
    if (!next) { c.path = null; return; }
    const tp = this.s.toPx(next.c, next.r);
    const s = e.sprite;
    const dx = tp.x - s.x, dy = tp.y - s.y;
    const dist = Math.hypot(dx, dy) || 1;
    const step = MOVE_SPEED * dt;
    this._face(s, e.key, e.cell, next);
    if (dist <= step) {
      s.setPosition(tp.x, tp.y);
      e.cell = { c: next.c, r: next.r };
      c.pathIdx++;
    } else {
      s.setPosition(s.x + (dx / dist) * step, s.y + (dy / dist) * step);
    }
  }

  _face(sprite, key, from, to) {
    if (!sprite || !sprite.anims) return;      // plain image (big monster) — skip
    const f = facingFor(to.c - from.c, to.r - from.r);
    try { setFacing(sprite, key, f.name, f.flip, false); } catch { /* non-animated */ }
  }

  // Resolve one attack from `atk` (cbt) against `def` (cbt), spawning a floating number
  // over the defender's sprite. `defEnt` is the defender entity (or hero descriptor).
  _resolveAttack(atk, def, atkSprite, defEnt, defIsHero) {
    if (!atk || !def) return;
    const roll = rollDamage(atk.weapon, atk.dmgBonus);
    const taken = mitigate(roll.hp, roll.type, def.armor || 0, def.resist || {},
                           { projectile: roll.projectile, shield: !!def.shield });
    def.hp = Math.max(0, def.hp - taken);
    if (def.hp <= 0) def.dead = true;
    const sprite = defEnt.sprite || defEnt;
    this._floater(sprite, taken, roll.crit, defIsHero);
    // brief lunge for feedback
    if (atkSprite && atkSprite.scene) {
      const ox = atkSprite.x, oy = atkSprite.y;
      atkSprite.scene.tweens.add({ targets: atkSprite, x: ox + (defEnt.sprite ? 3 : 0),
        duration: 60, yoyo: true, onComplete: () => atkSprite.setPosition(ox, oy) });
    }
  }

  // Keep the PlayerModel HP in sync after the hero is hit, handle hero death.
  _syncHeroHp(hero) {
    const m = this.s.playerModel;
    if (!m || !hero) return;
    m.hp = hero.hp;
    if (this.s.gameHud) this.s.gameHud.update(true);
    if (m.hp <= 0) this._onHeroDeath();
  }

  _onEnemyDeath(e) {
    const c = e.cbt;
    if (!c || c._looted) return;
    c._looted = true;
    // XP
    if (this.s.playerModel) {
      this.s.playerModel.gainXP(c.xp);
      if (this.s.gameHud) this.s.gameHud.update(true);
    }
    // loot roll
    this._rollLoot(c.loot, e);
    if (this._target === e) this._target = null;
    // fade the corpse out, then despawn
    const s = e.sprite;
    if (s && s.scene) s.scene.tweens.add({ targets: s, alpha: 0, duration: 700,
      onComplete: () => { s.destroy(); this.s.entities = this.s.entities.filter(x => x !== e); } });
    else this.s.entities = this.s.entities.filter(x => x !== e);
  }

  _rollLoot(table, e) {
    const rows = (this.s.loot && this.s.loot[table]) || [];
    for (const row of rows) {
      if (rint(1, 100) > (row.chance || 0)) continue;
      if (row.item === -2) {                    // gold
        const g = rint(1, 5) + (e.cbt.level || 1) * 2;
        if (this.s.playerModel) this.s.playerModel.addGold(g);
        this._floater(e.sprite, `+${g}g`, false, false, '#ffd54a');
      } else {                                  // a real item → into the backpack
        if (this.s.playerModel) this.s.playerModel.addItem(row.item);
        const nm = (this.s.items && this.s.items[row.item] && this.s.items[row.item].name) || row.name || `item ${row.item}`;
        this._floater(e.sprite, nm, false, false, '#9ad');
      }
    }
    if (this.s.gameHud) this.s.gameHud.update(true);
  }

  _onHeroDeath() {
    // Non-punishing prototype death: restore to full, disengage enemies, flash a notice.
    const m = this.s.playerModel;
    m.hp = m.maxHP();
    for (const e of this.s.entities) if (e.cbt && e.cbt.side === 'enemy') e.cbt.target = null;
    this._target = null;
    if (this.s.gameHud) this.s.gameHud.update(true);
    if (this.s.hero) this._floater(this.s.hero, 'You fall!', true, true, '#ff5252');
  }

  // ---- floating combat text ---------------------------------------------------
  _floater(sprite, text, crit, isHero, color) {
    if (!sprite || !sprite.scene) return;
    const col = color || (isHero ? '#ff6b6b' : crit ? '#ffd54a' : '#ffffff');
    const label = typeof text === 'number' ? String(text) : text;
    const t = sprite.scene.add.text(sprite.x, sprite.y - 40, label, {
      fontFamily: 'system-ui, sans-serif', fontSize: crit ? '20px' : '15px',
      color: col, stroke: '#000', strokeThickness: 3,
    }).setOrigin(0.5);
    this.s.world.add(t);
    this.floaters.push({ t, life: 0.9, vy: 34 });
  }

  _tickFloaters(dt) {
    for (const f of this.floaters) {
      f.life -= dt;
      f.t.y -= f.vy * dt;
      f.t.setAlpha(Math.max(0, f.life / 0.9));
    }
    this.floaters = this.floaters.filter(f => {
      if (f.life > 0) return true;
      f.t.destroy(); return false;
    });
  }
}
