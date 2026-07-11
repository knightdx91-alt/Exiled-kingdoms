// Real-time-with-pause combat — reversed from net.fdgames (deobf/COMBAT_SPEC.md).
//
// Enemies (PatrollerAI) aggro the hero within AGGRO tiles, chase via the scene's A*,
// and attack when within weapon reach. Followers (CompanionAI) fight the hero's target
// or the nearest enemy, otherwise trail the hero. Damage uses the recovered rolls and
// the exact g0/armor mitigation. Death drops loot (gold/items) and awards XP. A pause
// toggle freezes ticks — EK's "real-time with pause".

import { setFacing, playAttack } from './sprite.js';
import { facingFor } from './move.js';
import { SKILL_FX, skillParams, skillCooldown } from './skills.js';
import { playParticleEffect, EFFECT_FOR_TYPE } from './particles.js';

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
    this.cooldowns = {};         // skill id -> ms remaining (deobf/SKILLS_EXEC_SPEC.md)
    this.buffs = [];             // active hero buffs { armor, resist{}, dmgAdd, dodge, until }
    this.nextHit = 0;            // one-shot bonus on the hero's next melee hit (stab)
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

  // The hero as a combatant view over the PlayerModel, with active buffs + passives
  // folded in (deobf/SKILLS_EXEC_SPEC.md).
  _heroCbt() {
    const m = this.s.playerModel;
    if (!m) return null;
    const w = this.s.weapons ? this.s.weapons[m.weaponId()] : null;
    let armor = m.armor(), dmgBonus = m.dmgBonus(), dodge = 0;
    const resist = m.resist();
    for (const b of this.buffs) {
      armor += b.armor || 0; dmgBonus += b.dmgAdd || 0; dodge = Math.max(dodge, b.dodge || 0);
      for (const k in (b.resist || {})) resist[k] = (resist[k] || 0) + b.resist[k];
    }
    // Fury passive: below the HP threshold, deal +mul damage.
    const fr = m.skillRank('fury');
    if (fr) { const p = skillParams('fury', fr); if (p && m.hp <= p.thr * m.maxHP()) dmgBonus = Math.round(dmgBonus * (1 + p.mul)); }
    return { hp: m.hp, maxHp: m.maxHP(), armor, resist,
             weapon: w, dmgBonus, reach: (w && w.reach) || 1,
             shield: m.hasShield(), dodge, bonusHit: this.nextHit };
  }

  // Player taps an enemy → make it the hero's attack target.
  setTarget(e) {
    if (e && e.cbt && e.cbt.side === 'enemy' && !e.cbt.dead) this._target = e;
  }
  clearTarget() { this._target = null; }
  get target() { return this._target; }

  // The hero's melee/attack reach in tiles (weapon reach, else 1).
  heroReach() { const h = this._heroCbt(); return (h && h.reach) || 1; }

  // Is there any live enemy within the hero's strike reach right now? Used to decide
  // whether the attack button should attack or (when attackInteracts is on) interact —
  // mirrors GameScreen.l()'s `bVarP.a(...) <= 0` adjacency test.
  enemyInReach() {
    const hc = this.s.heroCell; if (!hc) return false;
    return !!this._nearestEnemy(hc, this.heroReach() + 0.5);
  }

  // The ATTACK button (GameHUD.f2942d → GameScreen.l() → player.E0(-1)). If an enemy is
  // within reach, strike it now (respecting the hero's weapon cooldown); otherwise
  // acquire the nearest enemy within ~6 tiles as the target so the hero walks in and the
  // tick loop keeps attacking. Returns true if it engaged anything, false if no enemy.
  heroSwing() {
    const hc = this.s.heroCell; if (!hc) return false;
    if (this.paused || this.s._dialogue || this.s._loading) return false;
    const target = this._nearestEnemy(hc, this.heroReach() + 0.5);
    if (target) {
      this._target = target;
      if (!this._heroCd || this._heroCd <= 0) {
        const hero = this._heroCbt();
        this._resolveAttack(hero, target.cbt, this.s.hero, target, false, this.s.heroKey);
        this._heroCd = (hero.weapon && hero.weapon.speed || 10) * 45;
        this._face(this.s.hero, this.s.heroKey, hc, target.cell);
        if (target.cbt.dead) this._onEnemyDeath(target);
        if (this.s.gameHud) this.s.gameHud.update(true);
      }
      return true;
    }
    const near = this._nearestEnemy(hc, 6);
    if (near) { this._target = near; return true; }
    return false;
  }

  // The follower entity (ally combatant that is a party follower), or null. Powers the
  // companion portrait + bars in the HUD.
  follower() {
    for (const e of this.s.entities)
      if (e.cbt && e.cbt.side === 'ally' && this._isFollower(e)) return e;
    return null;
  }

  togglePause() { this.paused = !this.paused; return this.paused; }

  // Clear transient combat state (buffs/cooldowns/target) — on a new game/character.
  reset() { this.cooldowns = {}; this.buffs = []; this.nextHit = 0; this._target = null; this._heroCd = 0; }

  // ---- skill / spell casting (deobf/SKILLS_EXEC_SPEC.md) -----------------------
  // Whether the hero can cast `id` right now (learned, off cooldown, enough mana).
  canCast(id) {
    const m = this.s.playerModel, fx = SKILL_FX[id];
    if (!m || !fx || fx.kind === 'passive' || m.skillRank(id) <= 0) return false;
    if (this.cooldowns[id] > 0) return false;
    return m.mana >= skillParams(id, m.skillRank(id)).mana;
  }
  cooldownFrac(id) { const fx = SKILL_FX[id]; return fx ? Math.max(0, (this.cooldowns[id] || 0) / (fx.cd * 1000)) : 0; }

  castSkill(id) {
    const m = this.s.playerModel, fx = SKILL_FX[id];
    if (!m || !fx || fx.kind === 'passive' || m.skillRank(id) <= 0) return { ok: false, reason: 'unknown' };
    if (this.cooldowns[id] > 0) return { ok: false, reason: 'cooldown' };
    const p = skillParams(id, m.skillRank(id));
    if (m.mana < (p.mana || 0)) return { ok: false, reason: 'mana' };
    if (m.mana) m.mana -= (p.mana || 0);
    this.cooldowns[id] = skillCooldown(id) * 1000;
    const hc = this.s.heroCell;
    if (fx.kind === 'heal') this._castHeal(p);
    else if (fx.kind === 'damage') this._castDamage(fx, p, hc);
    else if (fx.kind === 'melee') this._castMelee(fx, p, hc);
    else if (fx.kind === 'buff') this._castBuff(m, fx, p);
    if (this.s.gameHud) this.s.gameHud.update(true);
    return { ok: true };
  }

  _nearestEnemy(hc, range) {
    let best = null, bd = (range || 8) + 0.5;
    for (const e of this.s.entities) {
      if (!e.cbt || e.cbt.dead || e.cbt.side !== 'enemy') continue;
      const d = this._dist(e.cell, hc); if (d < bd) { bd = d; best = e; }
    }
    return best;
  }
  _isUndead(e) { return /undead|skeleton|zombie|ghost|wraith|lich|ghoul|vampire/i.test(`${e.rec && e.rec.race || ''} ${e.npc && e.npc.spawn || ''}`); }
  _typeColor(t) { return { Fire: '#ff7043', Cold: '#8fd0ff', Shock: '#ffe066', Death: '#b39ddb', Toxic: '#9ccc65', Spirit: '#fff59d' }[t] || '#ffffff'; }

  // EK damage-type -> real projectile-atlas region (extracted from the APK,
  // assets/sprites/projectiles; sprite ids from weapons.txt's `sprite` column).
  static get PROJECTILE_REGION() {
    return { Fire: 'fire1', Cold: 'ice1', Shock: 'lightning', Toxic: 'g_magic',
      Death: 'black_magic', Spirit: 'w_magic', Physical: 'force' };
  }

  // Cast FX with the genuine EK art: a bolt of the type's real projectile sprite flies
  // from the caster to the target cell, then on impact plays that type's real libgdx
  // particle effect (assets/particle/*.p, ported by particles.js) — fire cloud, ice
  // shards, healing motes, etc. `region` is the projectile atlas frame; `effect` the
  // particle-file name; `hex` tints the ring fallback. Cosmetic; self-cleans.
  _castFx(cell, hex, tiles, region, effect) {
    const s = this.s;
    if (!s || !s.add || !s.toPx || !s.world) return;
    const p = s.toPx(cell.c, cell.r);
    const col = (typeof hex === 'string' && hex[0] === '#') ? parseInt(hex.slice(1), 16) : (hex || 0xffffff);
    const cy = p.y - 12;
    const hero = s.hero;
    const haveArt = region && s.textures && s.textures.exists('projectiles');

    const burst = () => {
      // Real particle cloud (fire/ice/heal/...). Async load+play; falls back to the
      // sprite/ring flash below if the effect file is missing.
      let played = false;
      if (effect) { try { playParticleEffect(s, effect, p.x, cy, col); played = true; } catch { /* fall back */ } }
      if (haveArt) {
        const spr = s.add.image(p.x, cy, 'projectiles', region).setScale(0.5).setAlpha(0.95);
        s.world.add(spr);
        s.tweens.add({ targets: spr, scale: Math.max(1.1, (tiles || 1) * 0.9), alpha: 0,
          duration: 320, ease: 'Quad.Out', onComplete: () => spr.destroy() });
      }
      if (!played || !haveArt) {
        const rpx = Math.max(22, (tiles || 1) * 34);
        const ring = s.add.circle(p.x, cy, rpx, col, haveArt ? 0.16 : 0.28)
          .setStrokeStyle(3, col, 0.9).setScale(0.2);
        s.world.add(ring);
        s.tweens.add({ targets: ring, scale: 1.1, alpha: 0, duration: 360, ease: 'Cubic.Out',
          onComplete: () => ring.destroy() });
        if (!haveArt && !played) {
          const core = s.add.circle(p.x, cy, rpx * 0.5, col, 0.6);
          s.world.add(core);
          s.tweens.add({ targets: core, scale: 1.6, alpha: 0, duration: 240, ease: 'Quad.Out',
            onComplete: () => core.destroy() });
        }
      }
    };

    // Fly the projectile in from the caster when we have both the art and a hero anchor;
    // otherwise just burst in place (heals/buffs, or missing atlas).
    if (haveArt && hero && (hero.x !== p.x || hero.y !== cy)) {
      const bolt = s.add.image(hero.x, hero.y - 24, 'projectiles', region).setScale(0.7);
      bolt.setRotation(Math.atan2(cy - (hero.y - 24), p.x - hero.x));
      s.world.add(bolt);
      s.tweens.add({ targets: bolt, x: p.x, y: cy, duration: 180, ease: 'Quad.In',
        onComplete: () => { bolt.destroy(); burst(); } });
    } else {
      burst();
    }
  }

  _castHeal(p) {
    const m = this.s.playerModel, before = m.hp;
    m.heal(p.heal);
    if (this.s.heroCell) this._castFx(this.s.heroCell, '#7fd07f', 1, null, 'healing');
    this._floater(this.s.hero, `+${Math.round(m.hp - before)}`, false, false, '#7fd07f');
  }

  _castDamage(fx, p, hc) {
    const tgt = (this._target && this._target.cbt && !this._target.cbt.dead) ? this._target : this._nearestEnemy(hc, 8);
    if (!tgt) { this._floater(this.s.hero, 'No target', false, false, '#aaa'); return; }
    const center = tgt.cell, radius = fx.radius || 1;
    this._castFx(center, this._typeColor(fx.type), radius,
      Combat.PROJECTILE_REGION[fx.type], EFFECT_FOR_TYPE[fx.type]);
    for (const e of this.s.entities.slice()) {
      if (!e.cbt || e.cbt.dead || e.cbt.side !== 'enemy') continue;
      if (this._dist(e.cell, center) > radius) continue;
      let raw = rint(p.dmg[0], p.dmg[1]);
      if (p.undead && this._isUndead(e)) raw += p.undead;
      const taken = mitigate(raw, fx.type, e.cbt.armor || 0, e.cbt.resist || {}, {});
      e.cbt.hp = Math.max(0, e.cbt.hp - taken);
      this._floater(e.sprite, taken, false, false, this._typeColor(fx.type));
      if (p.stun && rint(1, 100) <= p.stun) e.cbt.stun = 1500;
      if (e.cbt.hp <= 0) { e.cbt.dead = true; this._onEnemyDeath(e); }
    }
  }

  _castMelee(fx, p, hc) {
    const hero = this._heroCbt(); if (!hero) return;
    const reach = (hero.reach || 1) + 1;
    let targets;
    if (fx.area) targets = this.s.entities.filter(e => e.cbt && !e.cbt.dead && e.cbt.side === 'enemy' && this._dist(e.cell, hc) <= reach);
    else {
      const t = (this._target && this._target.cbt && !this._target.cbt.dead && this._dist(this._target.cell, hc) <= reach)
        ? this._target : this._nearestEnemy(hc, reach);
      targets = t ? [t] : [];
    }
    this._castFx(hc, '#ffd08a', fx.area ? reach : 1);
    if (!targets.length) { this._floater(this.s.hero, 'No target', false, false, '#aaa'); return; }
    for (const e of targets.slice()) {
      const roll = rollDamage(hero.weapon, hero.dmgBonus);
      const raw = Math.max(1, Math.round(roll.hp * (p.mult || 1)));
      const taken = mitigate(raw, roll.type, e.cbt.armor || 0, e.cbt.resist || {}, {});
      e.cbt.hp = Math.max(0, e.cbt.hp - taken);
      this._floater(e.sprite, taken, roll.crit, false);
      if (p.stun && rint(1, 100) <= p.stun) e.cbt.stun = 1500;
      if (p.stunSec) e.cbt.stun = p.stunSec * 1000;
      if (e.cbt.hp <= 0) { e.cbt.dead = true; this._onEnemyDeath(e); }
    }
  }

  _castBuff(m, fx, p) {
    if (p.nextHit) this.nextHit = Math.round(m.level() * (p.nextHit.mul || 1)) + (p.nextHit.add || 0);
    if (p.armor || p.resist || p.dmgAdd || p.dodge) {
      this.buffs.push({ armor: p.armor || 0, resist: p.resist || null, dmgAdd: p.dmgAdd || 0,
        dodge: p.dodge || 0, until: this.s.time.now + (p.dur || 6) * 1000 });
    }
    if (this.s.heroCell) this._castFx(this.s.heroCell, '#ffe9a8', 1, null, 'casting');
    this._floater(this.s.hero, fx.name, false, false, '#ffe9a8');
  }

  // ---- main tick --------------------------------------------------------------
  tick(dtMs) {
    const dt = dtMs / 1000;
    this._tickFloaters(dt);
    if (this.paused || this.s._dialogue || this.s._loading || !this.s.hero) return;
    const hc = this.s.heroCell;
    if (!hc) return;

    // skill cooldowns + timed buffs
    for (const id in this.cooldowns) { this.cooldowns[id] -= dtMs; if (this.cooldowns[id] <= 0) delete this.cooldowns[id]; }
    if (this.buffs.length) { const now = this.s.time.now; this.buffs = this.buffs.filter(b => b.until > now); }

    // hero auto-attacks its chosen target
    this._heroTurn(dt, hc);

    for (const e of this.s.entities) {
      const c = e.cbt;
      if (!c || c.dead) continue;
      c.attackCd = Math.max(0, c.attackCd - dtMs);
      c.replan = Math.max(0, c.replan - dtMs);
      if (c.stun > 0) { c.stun -= dtMs; continue; }   // stunned enemies skip their turn
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
        this._resolveAttack(hero, t.cbt, this.s.hero, t, false, this.s.heroKey);
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
        this._resolveAttack(c, hero, e.sprite, { sprite: this.s.hero, cell: hc }, true, e.key);
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
          this._resolveAttack(c, tgt.cbt, e.sprite, tgt, false, e.key);
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
    // Slow proc (weapon proc 'slow') halves move speed until it expires.
    let slowMul = 1;
    if (c.slow) { if (this.s.time.now >= c.slow.until) c.slow = null; else slowMul = c.slow.mul; }
    const step = MOVE_SPEED * dt * slowMul;
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

  // Apply a weapon's on-hit proc to the defender (WeaponStats: stun/slow/paralyze/emp).
  // Durations reversed from Character.z1/s0 + the proc log strings. APPROX: exact resist
  // scaling (sheet.f0) and EMP's robot-only 35-75 hit aren't modelled — we roll the flat
  // proc chance and apply the effect. deobf/COMBAT_SPEC.md.
  _applyProc(defCbt, proc, sprite) {
    if (!proc || !proc.effect) return;
    if (rint(1, 100) > (proc.chance || 0)) return;
    const lvl = proc.level || 1;
    if (proc.effect === 'stun' || proc.effect === 'paralyze' || proc.effect === 'emp') {
      defCbt.stun = Math.max(defCbt.stun || 0, lvl * 1000);   // Character.z1: level seconds
      this._floater(sprite, proc.effect === 'paralyze' ? 'Paralyzed!' : 'Stun!', false, false, '#ffe066');
    } else if (proc.effect === 'slow') {
      defCbt.slow = { until: this.s.time.now + ((lvl * 2) + 3) * 1000, mul: 0.5 };  // s0: (level*2)+3 s
      this._floater(sprite, 'Slow!', false, false, '#8fd0ff');
    }
  }

  // Fly a projectile of atlas `region` from (fx,fy) to (tx,ty), invoking onHit() on
  // arrival. Falls back to calling onHit() immediately if the atlas is missing (A16).
  _projectileTo(fx, fy, tx, ty, region, onHit) {
    const s = this.s;
    if (!s || !s.add || !s.world || !region || !s.textures || !s.textures.exists('projectiles')) { onHit(); return; }
    const bolt = s.add.image(fx, fy, 'projectiles', region).setScale(0.7);
    bolt.setRotation(Math.atan2(ty - fy, tx - fx));
    s.world.add(bolt);
    const dur = Math.min(320, Math.max(90, Math.hypot(tx - fx, ty - fy) * 1.4));
    s.tweens.add({ targets: bolt, x: tx, y: ty, duration: dur, ease: 'Linear',
      onComplete: () => { bolt.destroy(); onHit(); } });
  }

  // Resolve one attack from `atk` (cbt) against `def` (cbt), spawning a floating number
  // over the defender's sprite. `defEnt` is the defender entity (or hero descriptor).
  // Ranged weapons fly a projectile and land the damage on arrival (A16); melee lands now.
  _resolveAttack(atk, def, atkSprite, defEnt, defIsHero, atkKey) {
    if (!atk || !def) return;
    const sprite = defEnt.sprite || defEnt;
    const w = atk.weapon;
    const ranged = !!(w && w.ranged);
    // Face the defender and play the sheet's swing animation (rows 6-10). If the sheet
    // has no attack rows (small monster), playAttack returns false → brief lunge instead.
    let swung = false;
    if (atkSprite && atkKey && sprite) {
      const f = facingFor(sprite.x - atkSprite.x, sprite.y - atkSprite.y);
      swung = playAttack(atkSprite, atkKey, f.name, f.flip);
    }
    // Evasion buff: the hero may dodge an incoming blow entirely.
    if (defIsHero && def.dodge && rint(1, 100) <= def.dodge) {
      this._floater(sprite, 'Dodge!', false, false, '#8fd0ff');
      return;
    }
    const roll = rollDamage(w, atk.dmgBonus);
    if (atk.bonusHit) { roll.hp += atk.bonusHit; this.nextHit = 0; }   // stab one-shot bonus

    const land = () => {
      if (def.dead) return;
      const taken = mitigate(roll.hp, roll.type, def.armor || 0, def.resist || {},
                             { projectile: roll.projectile, shield: !!def.shield });
      def.hp = Math.max(0, def.hp - taken);
      this._floater(sprite, taken, roll.crit, defIsHero);
      // Secondary elemental damage (weapons.txt cols 15/16) — its own mitigation + floater.
      if (w && w.secondary) {
        const sec = mitigate(w.secondary.dmg, w.secondary.type, def.armor || 0, def.resist || {}, {});
        if (sec > 0) { def.hp = Math.max(0, def.hp - sec); this._floater(sprite, sec, false, defIsHero, this._typeColor(w.secondary.type)); }
      }
      // On-hit proc (stun/slow/paralyze) unless the blow already killed.
      if (def.hp > 0 && w && w.proc) this._applyProc(def, w.proc, sprite);
      if (def.hp <= 0) def.dead = true;
      if (defIsHero) this._syncHeroHp(def);
      else if (def.dead && defEnt && defEnt.cbt) this._onEnemyDeath(defEnt);
    };

    if (ranged) {
      const reg = (w.sprite && this.s.textures && this.s.textures.exists('projectiles')) ? w.sprite : 'arrow1';
      const fx = atkSprite ? atkSprite.x : sprite.x, fy = atkSprite ? atkSprite.y - 24 : sprite.y;
      this._projectileTo(fx, fy, sprite.x, sprite.y - 12, reg, land);
    } else {
      land();
      // brief lunge for feedback when there's no attack animation on the sheet
      if (!swung && atkSprite && atkSprite.scene) {
        const ox = atkSprite.x, oy = atkSprite.y;
        atkSprite.scene.tweens.add({ targets: atkSprite, x: ox + (defEnt.sprite ? 3 : 0),
          duration: 60, yoyo: true, onComplete: () => atkSprite.setPosition(ox, oy) });
      }
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

  // Roll the enemy's loot table into a ground loot bag (GameHUD activable type 1) at its
  // death cell, rather than auto-collecting — so there's a real interactable to pick up
  // with the context button / a tap (deobf/CONTEXT_ACTIONS_SPEC.md). Nothing rolled → no bag.
  _rollLoot(table, e) {
    const rows = (this.s.loot && this.s.loot[table]) || [];
    let gold = 0; const items = [];
    for (const row of rows) {
      if (rint(1, 100) > (row.chance || 0)) continue;
      if (row.item === -2) gold += rint(1, 5) + (e.cbt.level || 1) * 2;
      else items.push(row.item);
    }
    if (!gold && !items.length) return;
    if (this.s.dropLootBag) this.s.dropLootBag(e.cell, items, gold);
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
