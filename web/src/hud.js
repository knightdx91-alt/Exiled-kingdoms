// In-game HUD — portrait, health/mana/XP bars, level, gold, and action buttons, with a
// Character panel (attributes + derived stats). Mirrors GameHUD's information (see
// deobf/HUD_SPEC.md); styling is a placeholder EK theme until the exact GameHUD art is
// matched. A DOM overlay in screen space, so it reflows tall↔wide with the viewport.

import { SKILL_FX, knownActives } from './skills.js';
import { npcPortrait } from './entity.js';

const ATTR = [['STR', 'Strength'], ['END', 'Endurance'], ['AGI', 'Agility'],
              ['INT', 'Intellect'], ['AWA', 'Awareness'], ['PER', 'Personality']];

export class HUD {
  constructor(root) {
    this.model = null;
    this.el = document.createElement('div');
    this.el.id = 'hud';
    this.el.style.display = 'none';
    // Layout mirrors the real GameHUD (deobf/GAMEHUD_LAYOUT_SPEC.md): portrait + bars
    // top-left, companion below it, gold top-left, ATTACK button bottom-right, potion
    // quickslots bottom-centre, skill bar bottom-right (above attack), recover left.
    this.el.innerHTML = `
      <div class="hud-top">
        <img class="hud-portrait" alt="" title="Character / Inventory">
        <div class="hud-bars">
          <div class="hud-name-row"><span class="hud-name"></span><span class="hud-lvl"></span></div>
          <div class="hud-bar hud-hp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
          <div class="hud-bar hud-mp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
          <div class="hud-bar hud-xp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
        </div>
        <div class="hud-gold"><span class="hud-coin">◉</span><span class="hud-gold-n"></span></div>
      </div>
      <div class="hud-comp" style="display:none">
        <img class="hud-comp-portrait" alt="">
        <div class="hud-bars">
          <div class="hud-comp-name"></div>
          <div class="hud-bar hud-hp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
          <div class="hud-bar hud-mp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
        </div>
      </div>
      <div class="hud-btns">
        <button class="hud-btn" data-act="journal" title="Journal">📖</button>
        <button class="hud-btn" data-act="menu" title="Menu">☰</button>
      </div>
      <img class="hud-recover" src="assets/ui/hud/mend.png" alt="" title="Rest &amp; recover">
      <div class="hud-quickslots"></div>
      <div class="hud-skillbar"></div>
      <button class="hud-attack" title="Attack / interact">
        <img src="assets/ui/hud/attackButton.png" alt="Attack">
      </button>
      <div class="hud-panel" style="display:none"></div>`;
    root.appendChild(this.el);
    this.skillbar = this.el.querySelector('.hud-skillbar');
    this.quickslots = this.el.querySelector('.hud-quickslots');

    this.$ = (s) => this.el.querySelector(s);
    this.panel = this.$('.hud-panel');
    this.el.querySelectorAll('.hud-btn').forEach(b =>
      b.addEventListener('click', () => this.onButton(b.dataset.act)));
    // Tapping the portrait opens the character/inventory screen (owner's request).
    const port = this.$('.hud-portrait');
    if (port) { port.style.cursor = 'pointer'; port.addEventListener('click', () => this.togglePanel('inv')); }

    // ATTACK button — press & hold to keep attacking (the held flag is polled by the
    // scene each frame; the weapon cooldown paces the swings). Doubles as interact when
    // the attackInteracts setting is on and nothing is in reach (see scene.heroAction).
    const atk = this.$('.hud-attack');
    const down = (ev) => { ev.preventDefault(); this._attackHeld = true; if (this.onAttackDown) this.onAttackDown(); };
    const up = () => { this._attackHeld = false; if (this.onAttackUp) this.onAttackUp(); };
    atk.addEventListener('pointerdown', down);
    atk.addEventListener('pointerup', up);
    atk.addEventListener('pointerleave', up);
    atk.addEventListener('pointercancel', up);
    // Recover / rest button.
    this.$('.hud-recover').addEventListener('click', () => { if (this.onRecover) this.onRecover(); });

    this._cache = {};
  }

  // Whether the attack button is currently held (polled by the scene's update loop).
  attackHeld() { return !!this._attackHeld; }

  setModel(model) {
    this.model = model;
    const g = model.gender.toLowerCase();
    this.$('.hud-portrait').src = model.portrait
      ? `assets/portraits/${g}/${model.portrait}` : '';
    this.$('.hud-name').textContent = model.name;
    this.$('.hud-mp').style.display = model.isCaster() ? '' : 'none';
    this.update(true);
  }

  show() { this.el.style.display = ''; }
  hide() { this.el.style.display = 'none'; }

  // Refresh live values; only touches the DOM when something changed.
  update(force) {
    const m = this.model;
    if (!m) return;
    // Bottom-HUD widgets have their own change-signature guards; run them every frame so
    // a follower joining or a quickslot item appearing shows up without a stat change.
    this.renderSkillBar();
    this.renderQuickslots();
    this.renderCompanion();
    const hp = Math.ceil(m.hp), maxhp = m.maxHP();
    const mp = Math.ceil(m.mana), maxmp = m.maxMana();
    const key = `${hp}/${maxhp}|${mp}/${maxmp}|${m.level()}|${Math.round(m.xpProgress() * 100)}|${m.gold}|${this.showNumbers() ? 1 : 0}`;
    if (!force && key === this._cache.key) return;
    this._cache.key = key;

    const num = this.showNumbers();
    this.setBar('.hud-hp', hp / maxhp, num ? `${hp} / ${maxhp}` : '');
    if (maxmp) this.setBar('.hud-mp', mp / maxmp, num ? `${mp} / ${maxmp}` : '');
    this.setBar('.hud-xp', m.xpProgress(), m.xpToNext() ? `XP ${m.xpToNext().toLocaleString()} to next` : 'MAX');
    this.$('.hud-lvl').textContent = `Lv ${m.level()}`;
    this.$('.hud-gold-n').textContent = m.gold.toLocaleString();
    // re-render only the currently-open panel (not always Character)
    if (this.panel.style.display !== 'none') {
      if (this._panel === 'char') this.renderCharacter();
      else if (this._panel === 'journal') this.renderJournal();
    }
  }

  // Whether numeric values are shown on the bars (Settings.showNumbersBars).
  showNumbers() { return !!(this.settings && this.settings.showNumbersBars); }

  // Potion / usable-item quickslot row (GameHUD.C[5] — InventorySlotImage). Shows up to
  // 5 usable backpack items (those with an OnUse). Tap → use it.
  renderQuickslots() {
    if (!this.quickslots || !this.model) return;
    const items = this.items || {};
    const usable = [];
    const seen = new Set();
    for (const id of this.model.backpack) {
      if (seen.has(id)) continue;
      const it = items[id];
      if (it && it.onUse && !it.slot) { usable.push(id); seen.add(id); }
      if (usable.length >= 5) break;
    }
    const sig = usable.join(',');
    if (sig === this._qsSig) return;
    this._qsSig = sig;
    this.quickslots.innerHTML = usable.map(id => {
      const it = items[id];
      const n = this.model.backpack.filter(x => x === id).length;
      return `<button class="qslot" data-id="${id}" title="${(it && it.name) || id}">` +
             `<span class="qs-name">${((it && it.name) || `#${id}`).split(' ')[0]}</span>` +
             (n > 1 ? `<span class="qs-n">${n}</span>` : '') + `</button>`;
    }).join('');
    this.quickslots.querySelectorAll('.qslot').forEach(b =>
      b.onclick = () => { if (this.onUseItem) { this.onUseItem(+b.dataset.id); this.update(true); } });
  }

  // Companion portrait + HP/mana bars (GameHUD.E + f2962w/f2963x), shown when a follower
  // is in the party. Follower combat state comes from the scene's Combat.
  renderCompanion() {
    const comp = this.$('.hud-comp');
    const f = this.combat && this.combat.follower && this.combat.follower();
    if (!f || !f.cbt) { comp.style.display = 'none'; return; }
    comp.style.display = '';
    const c = f.cbt;
    const cp = this.$('.hud-comp-portrait');
    const src = npcPortrait(f.rec) || '';
    if (cp.getAttribute('src') !== src) cp.setAttribute('src', src);
    this.$('.hud-comp-name').textContent = (f.npc.name || 'Companion').replace(/_/g, ' ');
    const hpBar = comp.querySelector('.hud-hp');
    hpBar.querySelector('.hud-fill').style.width = `${Math.max(0, Math.min(1, c.hp / c.maxHp)) * 100}%`;
    hpBar.querySelector('.hud-bt').textContent = this.showNumbers() ? `${Math.ceil(c.hp)} / ${c.maxHp}` : '';
    comp.querySelector('.hud-mp').style.display = 'none';   // followers: HP only for now
  }

  // Wire the combat system + a cast callback for the skill bar.
  setCombat(combat, castFn) { this.combat = combat; this.castFn = castFn; this._skillSig = null; }

  // Skill/quickslot bar: the hero's learned active skills; tap to cast (greyed when on
  // cooldown or out of mana). Rebuilt only when the castable set changes.
  renderSkillBar() {
    if (!this.skillbar || !this.model || !this.combat) return;
    const ids = knownActives(this.model);
    const sig = ids.map(id => `${id}:${this.combat.canCast(id) ? 1 : 0}:${Math.round(this.combat.cooldownFrac(id) * 8)}`).join('|');
    if (sig === this._skillSig) return;
    this._skillSig = sig;
    if (!ids.length) { this.skillbar.innerHTML = ''; return; }
    this.skillbar.innerHTML = ids.map((id, i) => {
      const fx = SKILL_FX[id], can = this.combat.canCast(id), cf = this.combat.cooldownFrac(id);
      return `<button class="skbtn ${can ? '' : 'off'}" data-skill="${id}" title="${fx.name}" ` +
             `style="background-image:url(assets/ui/hud/skill_bg${i % 6}.png)">` +
             `<span class="sk-name">${fx.name.split(' ')[0]}</span>` +
             (cf > 0 ? `<span class="sk-cd" style="height:${Math.round(cf * 100)}%"></span>` : '') +
             `</button>`;
    }).join('');
    this.skillbar.querySelectorAll('.skbtn').forEach(b =>
      b.onclick = () => { if (this.castFn) this.castFn(b.dataset.skill); });
  }

  setBar(sel, frac, text) {
    const bar = this.$(sel);
    bar.querySelector('.hud-fill').style.width = `${Math.max(0, Math.min(1, frac)) * 100}%`;
    bar.querySelector('.hud-bt').textContent = text;
  }

  onButton(act) {
    if (act === 'char') this.togglePanel('char');
    else if (act === 'journal') this.togglePanel('journal');
    else if (act === 'inv') this.togglePanel('inv');
    else if (act === 'menu') this.togglePanel('menu');
  }

  // Quest data + a live variable-store accessor, wired by the scene.
  setQuests(quests, varsFn) { this.quests = quests || {}; this.varsOf = varsFn || (() => ({})); }

  togglePanel(which) {
    if (this.panel.style.display !== 'none' && this._panel === which) {
      this.panel.style.display = 'none'; return;
    }
    this._panel = which;
    this.panel.style.display = '';
    if (which === 'char') this.renderCharacter();
    else if (which === 'journal') this.renderJournal();
    else if (which === 'inv') this.renderInventory();
    else this.panel.innerHTML =
      `<div class="hud-panel-h">Menu</div><p class="hud-dim">Progress auto-saves. Full save/settings menu TODO.</p>${this.closeBtn()}`;
    this.wireClose();
  }

  renderCharacter() {
    const m = this.model;
    const cls = m.charClass[0] + m.charClass.slice(1).toLowerCase();
    const rows = ATTR.map(([id, name]) =>
      `<div class="hud-stat"><span>${name}</span><b>${m.attributes[id] || 0}</b></div>`).join('');
    const skills = m.skills.length ? m.skills.map(s => s.name || s).join(', ') : '—';
    // trainer-taught advanced skills, resolved to display names via the catalog
    const cat = this.trainers || {};
    const trained = [...(m.trained || [])].map(id => (cat[id] && cat[id].name) || id);
    const DISC = { W: 'Warrior', R: 'Rogue', C: 'Cleric', M: 'Mage' };
    const disc = [...m.usableClasses()].map(l => DISC[l] || l);
    this.panel.innerHTML = `
      <div class="hud-panel-h">${m.name} — ${cls} · Lv ${m.level()}</div>
      <div class="hud-stats">
        <div class="hud-stat"><span>Health</span><b>${Math.ceil(m.hp)}/${m.maxHP()}</b></div>
        ${m.isCaster() ? `<div class="hud-stat"><span>Mana</span><b>${Math.ceil(m.mana)}/${m.maxMana()}</b></div>` : ''}
        <div class="hud-stat"><span>XP</span><b>${m.xp.toLocaleString()}</b></div>
        <div class="hud-stat"><span>Gold</span><b>${m.gold}</b></div>
        ${m.skillPoints ? `<div class="hud-stat"><span>Skill Pts</span><b>${m.skillPoints}</b></div>` : ''}
      </div>
      <div class="hud-panel-sub">Attributes</div>
      <div class="hud-stats">${rows}</div>
      <div class="hud-panel-sub">Skills</div>
      <p class="hud-skills">${skills}</p>
      ${trained.length ? `<div class="hud-panel-sub">Trained (Advanced)</div><p class="hud-skills">${trained.join(', ')}</p>` : ''}
      ${m.learnsAll() ? `<div class="hud-panel-sub">Disciplines</div><p class="hud-skills">${disc.length ? disc.join(', ') : '— (train to unlock equipment)'}</p>` : ''}
      ${this.closeBtn()}`;
    this.wireClose();
  }

  // Journal: every quest whose variable > 0, showing the description for its current
  // progress value; completed at >= 100 (deobf/QUEST_SPEC.md).
  renderJournal() {
    const vars = this.varsOf ? this.varsOf() : {};
    const quests = this.quests || {};
    const strip = (s) => (s || '').replace(/\[[A-Z]*\]/g, '').replace(/<p>/g, ' ').trim();
    const active = [], done = [];
    for (const [id, q] of Object.entries(quests)) {
      const v = vars[id] | 0;
      if (v <= 0) continue;
      // the state to show: exact match, else the highest state key <= v
      const keys = Object.keys(q.states).map(Number).sort((a, b) => a - b);
      let show = null;
      for (const k of keys) if (k <= v) show = k;
      if (show == null && keys.length) show = keys[0];
      const desc = strip(q.states[show]);
      const item = `<div class="jq"><div class="jq-name">${q.name}${v >= 100 ? ' <span class="jq-done">✓ Completed</span>' : ''}</div>` +
                   `<div class="jq-desc">${desc || ''}</div></div>`;
      (v >= 100 ? done : active).push(item);
    }
    const body = (active.length || done.length)
      ? `${active.join('')}${done.length ? `<div class="hud-panel-sub">Completed</div>${done.join('')}` : ''}`
      : `<p class="hud-dim">No quests yet. Talk to people and explore.</p>`;
    this.panel.innerHTML = `<div class="hud-panel-h">Journal</div>${body}${this.closeBtn()}`;
    this.wireClose();
  }

  // Character / inventory screen (deobf/INVENTORY_SPEC.md): equipment paper-doll (12
  // slots), attack/armor/resistance stat blocks, backpack grid + quick slots. Tap a
  // backpack item to equip (or use a potion); tap an equipped slot to unequip.
  renderInventory() {
    const m = this.model;
    const items = this.items || {};
    const nameOf = (id) => (items[id] && items[id].name) || (id ? `#${id}` : '—');
    // equipment paper-doll — the 12 slots in a readable order
    const SLOTS = [['head', 'Head'], ['necklace', 'Necklace'], ['cloak', 'Cloak'],
      ['body', 'Chest'], ['hands', 'Hands'], ['legs', 'Legs'], ['feet', 'Feet'],
      ['mainhand', 'Main Hand'], ['offhand', 'Off Hand'], ['belt', 'Belt'],
      ['ring', 'Ring'], ['ring2', 'Ring 2']];
    const doll = SLOTS.map(([slot, label]) => {
      const id = m.equipment[slot];
      const filled = id ? 'filled' : '';
      return `<button class="eq-slot ${filled}" data-slot="${slot}" ${id ? '' : 'disabled'} title="${id ? 'Unequip' : ''}">` +
             `<span class="eq-label">${label}</span><span class="eq-item">${nameOf(id)}</span></button>`;
    }).join('');
    // attack block from the equipped/derived weapon
    const w = this.weapons ? this.weapons[m.weaponId()] : null;
    const dmgLo = (w ? w.min : 1) + m.dmgBonus(), dmgHi = (w ? w.max : 3) + m.dmgBonus();
    const atk = w ? `${dmgLo}–${dmgHi} ${w.type}${w.crit ? ` · crit ${w.crit}%` : ''}` : '—';
    // resistances (only non-zero shown)
    const res = m.resist();
    const resStr = Object.entries(res).filter(([, v]) => v).map(([k, v]) => `${k} ${v > 0 ? '+' : ''}${v}`).join(', ') || 'none';
    // backpack grid — tap to equip/use
    const bp = m.backpack.length ? m.backpack.map((id, i) => {
      const it = items[id]; const eq = it && it.slot;
      const use = it && it.onUse;
      return `<button class="bp-item" data-idx="${i}" data-id="${id}" title="${eq ? 'Equip' : use ? 'Use' : ''}">` +
             `<span class="bp-name">${nameOf(id)}</span>${eq ? '<span class="bp-tag">equip</span>' : use ? '<span class="bp-tag use">use</span>' : ''}</button>`;
    }).join('') : `<p class="hud-dim">Backpack is empty. Defeat foes to find loot.</p>`;

    this.panel.innerHTML = `
      <div class="hud-panel-h">${m.name} — Inventory</div>
      <div class="inv-stats">
        <div class="hud-stat"><span>Attack</span><b>${atk}</b></div>
        <div class="hud-stat"><span>Armor</span><b>${m.armor()}</b></div>
        <div class="hud-stat"><span>HP</span><b>${Math.ceil(m.hp)}/${m.maxHP()}</b></div>
        <div class="hud-stat"><span>Gold</span><b>${m.gold}</b></div>
      </div>
      <div class="hud-stat inv-res"><span>Resistances</span><b>${resStr}</b></div>
      <div class="hud-panel-sub">Equipment</div>
      <div class="eq-doll">${doll}</div>
      <div class="hud-panel-sub">Backpack (${m.backpack.length})</div>
      <div class="bp-grid">${bp}</div>
      ${this.closeBtn()}`;
    // wire equip / unequip / use
    this.panel.querySelectorAll('.eq-slot').forEach(b => b.onclick = () => {
      if (m.unequip(b.dataset.slot)) { this.model.hp = Math.min(this.model.hp, this.model.maxHP()); this.update(true); this.renderInventory(); }
    });
    this.panel.querySelectorAll('.bp-item').forEach(b => b.onclick = () => {
      const id = +b.dataset.id, it = items[id];
      if (it && it.slot) { if (m.equip(id)) { this.update(true); this.renderInventory(); } }
      else if (it && it.onUse && this.onUseItem) { this.onUseItem(id); this.update(true); this.renderInventory(); }
    });
    this.wireClose();
  }

  closeBtn() { return `<button class="hud-close">Close</button>`; }
  wireClose() {
    const b = this.panel.querySelector('.hud-close');
    if (b) b.onclick = () => { this.panel.style.display = 'none'; };
  }
}
