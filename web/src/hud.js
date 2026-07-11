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
      <img class="hud-recover" src="assets/ui/hud/mend.png" alt="" title="Rest &amp; recover">
      <div class="hud-ctx"></div>
      <div class="hud-quickslots"></div>
      <div class="hud-skillbar"></div>
      <button class="hud-attack" title="Attack / interact">
        <img src="assets/ui/hud/attackButton.png" alt="Attack">
      </button>
      <div class="cw" style="display:none"></div>`;
    root.appendChild(this.el);
    this.skillbar = this.el.querySelector('.hud-skillbar');
    this.quickslots = this.el.querySelector('.hud-quickslots');
    this.ctxBar = this.el.querySelector('.hud-ctx');
    this._ctxSig = null;                 // last-rendered signature, to skip rebuilds

    this.$ = (s) => this.el.querySelector(s);
    // The full-screen CharacterWindow (o0/f). The ONLY entry is tapping the portrait —
    // exactly as in the real game (no HUD Journal/Menu/Inventory buttons exist there).
    this.cw = this.$('.cw');
    const port = this.$('.hud-portrait');
    if (port) { port.style.cursor = 'pointer'; port.addEventListener('click', () => this.openCharWindow()); }

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

  // Context / interact buttons (top-right), mirroring GameHUD's f2965z button set (see
  // deobf/CONTEXT_ACTIONS_SPEC.md). `list` is up to 4 nearby interactables, each
  // { key, kind ('talk'|'open'|'pickup'|'enter'), icon (img url|null), label }. Rebuilt
  // only when the set changes (signature compare) since the scene calls this every few
  // frames. Hidden entirely when nothing is in reach. `onPick(key)` fires on tap.
  setContext(list, onPick) {
    list = (list || []).slice(0, 4);
    const sig = list.map(a => `${a.kind}:${a.key}:${a.icon || ''}`).join('|');
    if (sig === this._ctxSig) return;                // nothing changed this frame
    this._ctxSig = sig;
    this.ctxBar.innerHTML = '';
    this.ctxBar.style.display = list.length ? 'flex' : 'none';
    // Icon-only buttons (the owner wants the interactable's icon, not its name). Talk uses
    // the NPC portrait, open/pickup the object/loot art, enter a door icon; a glyph is only
    // a last resort when no art resolves. The label lives in `title` (tooltip) only.
    const glyph = { talk: '🗨', open: '📦', pickup: '💰', enter: '🚪' };
    for (const a of list) {
      const b = document.createElement('button');
      b.className = `ctx-btn ctx-${a.kind}`;
      b.title = a.label || a.kind;
      b.innerHTML = a.icon
        ? `<img class="ctx-ic" src="${a.icon}" alt="" draggable="false" ` +
          `onerror="this.replaceWith(Object.assign(document.createElement('span'),{className:'ctx-gl',textContent:'${glyph[a.kind] || '❔'}'}))">`
        : `<span class="ctx-gl">${glyph[a.kind] || '❔'}</span>`;
      b.addEventListener('click', () => { if (onPick) onPick(a.key); });
      this.ctxBar.appendChild(b);
    }
  }

  // The real item icon (assets/ui/items/<icon>.png, extracted by tools/gen-icons.mjs)
  // when it exists, else a text fallback. `itemIcons` is the set of extracted icon names
  // wired by the scene; empty until the atlas is extracted (then everything upgrades to art).
  itemIconHtml(it, cls, fallbackText) {
    const ic = it && it.icon;
    if (ic && this.itemIcons && this.itemIcons.has(ic))
      return `<img class="${cls}" src="assets/ui/items/${ic}.png" alt="" draggable="false">`;
    return `<span class="${cls}-txt">${fallbackText || ''}</span>`;
  }

  setModel(model) {
    this.model = model;
    this.closeCW();                 // a fresh character never inherits an open window
    this._cwView = 'main';
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
    // keep the open CharacterWindow live (HP/mana/gold change while it's up)
    if (this.cw.style.display !== 'none') this.renderCW();
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
      const name = (it && it.name) || `#${id}`;
      return `<button class="qslot" data-id="${id}" title="${name}">` +
             this.itemIconHtml(it, 'qs-ic', name.split(' ')[0]) +
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
      // The real skill icon (assets/ui/skills/<id>.png) over the game's skill-button frame
      // (skill_bg). Same art the creation SkillWindow uses — never a text stand-in.
      return `<button class="skbtn ${can ? '' : 'off'}" data-skill="${id}" title="${fx.name}" ` +
             `style="background-image:url(assets/ui/hud/skill_bg${i % 6}.png)">` +
             `<img class="sk-icon" src="assets/ui/skills/${id}.png" alt="${fx.name}" draggable="false">` +
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

  // Quest data + a live variable-store accessor, wired by the scene.
  setQuests(quests, varsFn) { this.quests = quests || {}; this.varsOf = varsFn || (() => ({})); }

  // ===== CharacterWindow (o0/f) — full-screen, opened by the portrait (GameHUD.i) =====
  // The 12 equipment slots in CharacterInventory order, laid out around the paper-doll
  // exactly as f3490k builds them (deobf/GAMEHUD_LAYOUT_SPEC.md §9).
  // grid = [ row1(4) ][ mid: 2-left / sprite / 2-right ][ row3(4) ]
  static get CW_SLOTS() {
    return {
      row1: [['mainhand', 'Main Hand'], ['offhand', 'Off Hand'], ['head', 'Head'], ['belt', 'Belt']],
      midL: [['ring', 'Ring'], ['cloak', 'Cloak']],
      midR: [['feet', 'Feet'], ['legs', 'Legs']],
      row3: [['ring2', 'Ring 2'], ['body', 'Chest'], ['hands', 'Hands'], ['necklace', 'Necklace']],
    };
  }

  openCharWindow() {
    if (this.cw.style.display !== 'none') { this.closeCW(); return; }
    this._cwView = 'main';
    this._sel = null;               // { area:'equip'|'backpack', key } selected slot
    this.cw.style.display = '';
    this.renderCW();
  }
  closeCW() { this.cw.style.display = 'none'; this._sel = null; }

  renderCW() {
    if (!this.model) return;
    if (this._cwView === 'journal') return this.renderCWJournal();
    if (this._cwView === 'skills') return this.renderCWSkills();
    if (this._cwView === 'reputation') return this.renderCWReputation();
    if (this._cwView === 'details') return this.renderCWDetails();
    this.renderCWMain();
  }

  // The main character sheet — the exact screen in the screenshot.
  renderCWMain() {
    const m = this.model, items = this.items || {};
    const nameOf = (id) => (items[id] && items[id].name) || (id ? `#${id}` : '');
    const cls = m.charClass[0] + m.charClass.slice(1).toLowerCase();
    const g = m.gender.toLowerCase();
    const portrait = m.portrait ? `assets/portraits/${g}/${m.portrait}` : '';

    // --- column 1: identity + bars + traits + attack stats + armor + resistances ---
    const bar = (cls2, frac, txt) => `<div class="cw-bar cw-${cls2}"><div class="cw-fill" style="width:${Math.max(0, Math.min(1, frac)) * 100}%"></div><span>${txt}</span></div>`;
    const hp = Math.ceil(m.hp), mp = Math.ceil(m.mana);
    const bars = bar('xp', m.xpProgress(), m.xpToNext() ? `XP ${m.xpToNext().toLocaleString()}` : 'XP MAX')
      + bar('hp', hp / m.maxHP(), `${hp} / ${m.maxHP()}`)
      + (m.isCaster() ? bar('mp', mp / m.maxMana(), `${mp} / ${m.maxMana()}`) : '');
    const traits = ATTR.map(([id]) => `<div class="cw-trait"><span>${id}</span><b>${m.attributes[id] || 0}</b></div>`).join('');
    // attack stats (Damage / Speed / Critical / DPS / Effect)
    const w = this.weapons ? this.weapons[m.weaponId()] : null;
    const db = m.dmgBonus();
    const dLo = (w ? w.min : 1) + db, dHi = (w ? w.max : 3) + db;
    const spd = w && w.speed ? w.speed : 10;
    const critPct = w && w.crit ? w.crit : 0;
    const critMul = w && w.critMul ? w.critMul : 200;
    const dps = (((dLo + dHi) / 2) * (60 / (spd * 4.5))).toFixed(1);
    const effect = w && w.type && w.type !== 'Normal' ? w.type : (w && w.effect) || '—';
    const attackStats = [
      ['Damage', `${dLo}-${dHi}`], ['Speed', spd],
      ['Critical', `${critPct}% (x${(critMul / 100).toFixed(1)})`], ['DPS', dps], ['Effect', effect],
    ].map(([k, v]) => `<div class="cw-stat"><span>${k}:</span><b>${v}</b></div>`).join('');
    // resistances (6 elements, real ui_icons)
    const res = m.resist();
    const RES = [['fire', 'Fire'], ['cold', 'Cold'], ['shock', 'Shock'], ['death', 'Death'], ['poison', 'Poison'], ['holy', 'Holy']];
    const resHtml = RES.map(([ic, key]) => {
      const v = res[key] || res[ic] || res[key[0].toUpperCase() + key.slice(1)] || 0;
      return `<div class="cw-res"><img src="assets/ui/hud/${ic}.png" alt="${key}"><span>+${v}</span></div>`;
    }).join('');

    // --- column 2: equipment paper-doll (12 slots) + preview + Equip/Drop ---
    const CW = HUD.CW_SLOTS;
    const eqSlot = ([slot, label]) => {
      const id = m.equipment[slot];
      const on = this._sel && this._sel.area === 'equip' && this._sel.key === slot;
      const body = id ? this.itemIconHtml(items[id], 'cw-slot-ic', nameOf(id)) : `<span class="cw-slot-n">${nameOf(id)}</span>`;
      return `<button class="cw-slot ${id ? 'filled' : ''} ${on ? 'sel' : ''}" data-area="equip" data-key="${slot}" title="${id ? nameOf(id) : label}">` +
             `<span class="cw-slot-t">${label}</span>${body}</button>`;
    };
    const doll = `
      <div class="cw-doll-row">${CW.row1.map(eqSlot).join('')}</div>
      <div class="cw-doll-mid">
        <div class="cw-doll-col">${CW.midL.map(eqSlot).join('')}</div>
        <div class="cw-doll-sprite" style="background-image:url(${this.charSpriteSrc || ''})"></div>
        <div class="cw-doll-col">${CW.midR.map(eqSlot).join('')}</div>
      </div>
      <div class="cw-doll-row">${CW.row3.map(eqSlot).join('')}</div>`;
    // selected-item preview + contextual button labels
    const sel = this._selectedItem();
    const preview = sel
      ? `<div class="cw-preview"><b>${sel.name || ''}</b>${sel.desc ? `<span>${sel.desc}</span>` : ''}</div>`
      : `<div class="cw-preview cw-dim">Select an item</div>`;
    const eqLabel = this._equipLabel(sel), dropLabel = this._dropLabel(sel);
    const canEq = !!(sel && (sel.slotItem || sel.useItem));
    const canDrop = !!(sel && this._sel && this._sel.area === 'backpack');

    // --- column 3: backpack (20) + quick slots + gold + Back ---
    const bpSlots = [];
    for (let i = 0; i < 20; i++) {
      const id = m.backpack[i] || 0;
      const on = this._sel && this._sel.area === 'backpack' && this._sel.key === i;
      const body = id ? this.itemIconHtml(items[id], 'cw-slot-ic', nameOf(id)) : `<span class="cw-slot-n">${nameOf(id)}</span>`;
      bpSlots.push(`<button class="cw-slot ${id ? 'filled' : ''} ${on ? 'sel' : ''}" data-area="backpack" data-key="${i}" title="${id ? nameOf(id) : ''}">` +
        body + `</button>`);
    }

    this.cw.innerHTML = `
      <div class="cw-grid">
        <div class="cw-col cw-col1">
          <div class="cw-head">
            <img class="cw-portrait" src="${portrait}" alt="">
            <div class="cw-idbars">
              <div class="cw-name">${m.name}, ${cls}</div>
              <div class="cw-level">Level ${m.level()}</div>
              ${bars}
            </div>
          </div>
          <div class="cw-mid2">
            <div class="cw-traits"><div class="cw-h">Traits</div>${traits}</div>
            <div class="cw-attack"><div class="cw-h">Attack Stats</div>${attackStats}</div>
          </div>
          <div class="cw-armorres">
            <div class="cw-armor"><div class="cw-h">Armor</div><div class="cw-armorval"><img src="assets/ui/hud/shield.png" alt=""><b>${m.armor()}</b></div></div>
            <div class="cw-resist"><div class="cw-h">Resistances</div><div class="cw-resrow">${resHtml}</div></div>
          </div>
          <div class="cw-navrow">
            <button class="cw-btn" data-nav="journal">Journal</button>
            <button class="cw-btn" data-nav="skills">Skills</button>
          </div>
          <div class="cw-navrow">
            <button class="cw-btn" data-nav="reputation">Reputation</button>
            <button class="cw-btn" data-nav="details">Details</button>
          </div>
        </div>

        <div class="cw-col cw-col2">
          <div class="cw-h cw-c">Equipment</div>
          <div class="cw-doll">${doll}</div>
          ${preview}
          <div class="cw-itembtns">
            <button class="cw-btn cw-drop" data-act="drop" ${canDrop ? '' : 'disabled'}>${dropLabel}</button>
            <button class="cw-btn cw-equip" data-act="equip" ${canEq ? '' : 'disabled'}>${eqLabel}</button>
          </div>
        </div>

        <div class="cw-col cw-col3">
          <div class="cw-h cw-c">Backpack</div>
          <div class="cw-bp">${bpSlots.join('')}</div>
          <div class="cw-qsrow">
            <button class="cw-btn cw-qsbtn" data-nav="quickslots">Quick slots</button>
            <span class="cw-gold"><span class="cw-coin">◉</span>${m.gold.toLocaleString()}</span>
          </div>
          <div class="cw-backrow"><button class="cw-btn cw-back" data-act="back">Back</button></div>
        </div>
      </div>`;
    this._wireCW();
  }

  _wireCW() {
    // slot selection (equipment / backpack)
    this.cw.querySelectorAll('.cw-slot').forEach(b => b.onclick = () => {
      const area = b.dataset.area, key = area === 'backpack' ? +b.dataset.key : b.dataset.key;
      this._sel = (this._sel && this._sel.area === area && String(this._sel.key) === String(key)) ? null : { area, key };
      this.renderCWMain();
    });
    // nav buttons -> sub-windows (Journal / Skills / Reputation / Details / Quick slots)
    this.cw.querySelectorAll('[data-nav]').forEach(b => b.onclick = () => {
      const nav = b.dataset.nav;
      if (nav === 'quickslots') return;            // QuickSlotWindow (assign) — not yet
      this._cwView = nav; this.renderCW();
    });
    // item action buttons + Back
    this.cw.querySelectorAll('[data-act]').forEach(b => b.onclick = () => {
      const act = b.dataset.act;
      if (act === 'back') return this.closeCW();
      if (act === 'equip') return this._doEquip();
      if (act === 'drop') return this._doDrop();
    });
  }

  // The currently-selected item (from equipment or backpack), enriched for the preview
  // and to decide the contextual button labels.
  _selectedItem() {
    if (!this._sel) return null;
    const m = this.model, items = this.items || {};
    const id = this._sel.area === 'equip' ? m.equipment[this._sel.key] : (m.backpack[this._sel.key] || 0);
    if (!id) return null;
    const it = items[id] || {};
    return { id, name: it.name || `#${id}`, desc: it.desc || '',
             worn: this._sel.area === 'equip', slotItem: !!it.slot, useItem: !!it.onUse, it };
  }
  // EQUIP button morphs: UNEQUIP (worn) / USE (consumable) / EQUIP (equippable). (BUY/TAKE
  // are shop/container modes, not the character sheet.)
  _equipLabel(sel) { if (!sel) return 'Equip'; if (sel.worn) return 'Unequip'; if (sel.slotItem) return 'Equip'; if (sel.useItem) return 'Use'; return 'Equip'; }
  _dropLabel(sel) { return 'Drop'; }

  _doEquip() {
    const sel = this._selectedItem(); if (!sel) return;
    const m = this.model;
    if (sel.worn) { if (m.unequip(this._sel.key)) { m.hp = Math.min(m.hp, m.maxHP()); } }
    else if (sel.slotItem) { m.equip(sel.id); }
    else if (sel.useItem && this.onUseItem) { this.onUseItem(sel.id); }
    this._sel = null; this.update(true); this.renderCWMain();
  }
  _doDrop() {
    const sel = this._selectedItem(); if (!sel || !this._sel || this._sel.area !== 'backpack') return;
    if (this.model.removeItem(sel.id)) { this._sel = null; this.update(true); this.renderCWMain(); }
  }

  _cwSubHead(title) {
    return `<div class="cw-sub"><div class="cw-subhead">${title}</div>`;
  }
  _wireCWBack() {
    const b = this.cw.querySelector('.cw-back');
    if (b) b.onclick = () => { this._cwView = 'main'; this.renderCW(); };
  }

  // JOURNAL (o0.g(0) -> JournalWindow): quests whose variable > 0 (deobf/QUEST_SPEC.md).
  renderCWJournal() {
    const vars = this.varsOf ? this.varsOf() : {}, quests = this.quests || {};
    const strip = (s) => (s || '').replace(/\[[A-Z]*\]/g, '').replace(/<p>/g, ' ').trim();
    const active = [], done = [];
    for (const [id, q] of Object.entries(quests)) {
      const v = vars[id] | 0; if (v <= 0) continue;
      const keys = Object.keys(q.states).map(Number).sort((a, b) => a - b);
      let show = null; for (const k of keys) if (k <= v) show = k;
      if (show == null && keys.length) show = keys[0];
      const item = `<div class="jq"><div class="jq-name">${q.name}${v >= 100 ? ' <span class="jq-done">✓</span>' : ''}</div><div class="jq-desc">${strip(q.states[show]) || ''}</div></div>`;
      (v >= 100 ? done : active).push(item);
    }
    const body = (active.length || done.length)
      ? `${active.join('')}${done.length ? `<div class="cw-h">Completed</div>${done.join('')}` : ''}`
      : `<p class="cw-dim">No quests yet.</p>`;
    this.cw.innerHTML = this._cwSubHead('Journal') + body + `<div class="cw-backrow"><button class="cw-btn cw-back">Back</button></div></div>`;
    this._wireCWBack();
  }

  // Real English strings from the game's strings table (data/ui/strings/texts.txt).
  static get STR() {
    return {
      SKILLS: 'Skills', SKILLS_LIST: 'Skills', SPECIALIST_SKILLS: 'Advanced Skills',
      POINTS: 'Points', FACTION: 'Faction', REPUTATION: 'Reputation',
      HELP_STATS: 'Character Stats', STATS_TITLE: 'Game Stats',
      HELP_ARMOR: 'You subtract {ARMOR-VALUE} from all physical attacks.',
      HELP_ARMOR_ARROWS: 'You subtract an extra {ARMOR-VALUE} from all projectiles.',
      HELP_PERCEPTION: '{per}% chance of finding secrets.',
      HELP_DEVICES: '{dev}% chance of disabling traps.',
      HELP_GOSSIP: '{gos}% chance of learning rumours.',
      DAMAGE_BONUS: 'Damage bonus', PERCEPTION: 'Perception', DEVICES: 'Disarm Devices',
      TIT_GOSSIP: 'Gossip', XP_BONUS: 'XP Bonus',
      UNKNOWN: 'Neutral', FRIENDLY: 'Friendly', TRUSTED: 'Trusted', HERO: 'Hero',
      GREAT_HERO: 'Great Hero', LEGENDARY_HERO: 'Legendary Hero', RASCAL: 'Rascal',
      BANDIT: 'Bandit', CRIMINAL: 'Criminal', ENEMY: 'Enemy', 'ARCH-ENEMY': 'Arch-Enemy',
    };
  }
  // Reputation tier for a rep value (WorldFaction.d).
  _repTier(v) {
    const S = HUD.STR;
    return v <= -80 ? S['ARCH-ENEMY'] : v <= -60 ? S.ENEMY : v <= -40 ? S.CRIMINAL
      : v <= -20 ? S.BANDIT : v <= -5 ? S.RASCAL : v <= 9 ? S.UNKNOWN : v <= 24 ? S.FRIENDLY
      : v <= 39 ? S.TRUSTED : v <= 59 ? S.HERO : v <= 79 ? S.GREAT_HERO : S.LEGENDARY_HERO;
  }

  // SKILLS (o0.e(8) -> SkillWindow o0/t): the class + GENERAL skill lists with ranks, plus
  // advanced (trainer-taught) skills. `SKILLS_LIST <Class>` / `<Class> SKILLS` / Advanced.
  renderCWSkills() {
    const m = this.model, S = HUD.STR;
    const base = (this.skillCat && this.skillCat.base) || {};
    const cls = (k) => k ? k[0] + k.slice(1).toLowerCase() : '';
    // class key in skills.json (MAGE is stored as WIZARD; HERO has no own list)
    const clsKey = m.charClass === 'MAGE' ? 'WIZARD' : m.charClass;
    const rankOf = (sk) => {
      const id = sk.id || (sk.name || '').toLowerCase().replace(/\s+/g, '_');
      const r = m.skillRank(id);
      if (r) return r;
      return (m.skills || []).some(s => (s.name || s) === sk.name) ? 1 : 0;
    };
    const iconId = (sk) => sk.icon || (sk.name || '').toLowerCase().replace(/[^a-z0-9]+/g, '_').replace(/^_|_$/g, '');
    const skillRow = (sk) => {
      const r = rankOf(sk), maxL = (sk.levels && sk.levels.length) || 1;
      const tag = sk.type === 'P' ? ' (Passive)' : sk.type === 'A' ? ' (Active)' : '';
      const ic = iconId(sk);
      return `<div class="cw-skill ${r ? 'on' : ''}">` +
        `<img class="cw-skl-ic" src="assets/ui/skills/${ic}.png" alt="" onerror="this.style.visibility='hidden'">` +
        `<div class="cw-skl-body"><div class="cw-skl-h"><b>${sk.name}</b>` +
        `<span class="cw-skl-lv">${r ? `Lv ${r}/${maxL}` : '—'}</span></div>` +
        `<div class="cw-skl-d">${sk.desc || ''}${tag}</div></div></div>`;
    };
    const section = (title, arr) => arr && arr.length
      ? `<div class="cw-h">${title}</div>${arr.map(skillRow).join('')}` : '';
    const classList = base[clsKey] || [];
    const generalList = base.GENERAL || [];
    // advanced / trained (trainer catalogue), only those the hero has learned
    const trained = [...(m.trained || [])].map(id => {
      const t = (this.trainers || {})[id];
      return { name: (t && t.name) || id, desc: (t && t.desc) || '', type: (t && t.type) || '' };
    });
    const body =
      section(`${cls(m.charClass)} ${S.SKILLS}`, classList) +
      section(`General ${S.SKILLS}`, generalList) +
      (trained.length ? section(S.SPECIALIST_SKILLS, trained) : '');
    this.cw.innerHTML = this._cwSubHead(S.SKILLS_LIST)
      + (m.skillPoints ? `<div class="cw-points">Available skill points: <b>${m.skillPoints}</b></div>` : '')
      + (body || `<p class="cw-dim">No skills.</p>`)
      + `<div class="cw-backrow"><button class="cw-btn cw-back">Back</button></div></div>`;
    this._wireCWBack();
  }

  // REPUTATION (o0.g(1) -> ReputationWindow o0/n): 3-col FACTION | (name+desc) | REPUTATION.
  // Rep values are the `REP_<id>` variables; untouched factions read 0 -> Neutral.
  renderCWReputation() {
    const S = HUD.STR, vars = this.varsOf ? this.varsOf() : {}, facs = this.factions || [];
    const rows = facs.map(f => {
      let v = vars[`REP_${f.id}`]; if (v === undefined || v === -255) v = 0;
      const color = v > 10 ? '#2f7a2f' : v < -10 ? '#a12a2a' : '#4a3410';
      const rep = `${this._repTier(v)} (${v >= 0 ? '+' : ''}${v})`;
      return `<div class="cw-rep">
        <div class="cw-rep-f"><b>${f.name}</b><span>${f.desc}</span></div>
        <div class="cw-rep-v" style="color:${color}">${rep}</div></div>`;
    }).join('');
    this.cw.innerHTML = this._cwSubHead(S.REPUTATION)
      + `<div class="cw-rephead"><span>${S.FACTION}</span><span>${S.REPUTATION}</span></div>`
      + (rows || `<p class="cw-dim">No factions.</p>`)
      + `<div class="cw-backrow"><button class="cw-btn cw-back">Back</button></div></div>`;
    this._wireCWBack();
  }

  // DETAILS / STAT_DETAILS (o0.e(0) -> StatsDetailWindow o0/x): label | description rows
  // with the real HELP_ text. Perception/Devices/Gossip use the recovered class formulas
  // (rogue base + attribute·5 — APPROX; full SheetBonus math is TODO).
  renderCWDetails() {
    const m = this.model, S = HUD.STR;
    const rogue = m.charClass === 'ROGUE';
    const per = (rogue ? 15 : 0) + (m.attributes.AWA || 0) * 5;
    const dev = (rogue ? 20 : 0) + (m.attributes.AGI || 0) * 5;
    const gos = (rogue ? 10 : 0) + (m.attributes.PER || 0) * 5;
    const row = (label, desc) => `<div class="cw-detrow"><div class="cw-detl">${label}</div><div class="cw-detd">${desc}</div></div>`;
    const armor = m.armor();
    const rows = [
      row('Armor:', S.HELP_ARMOR.replace('{ARMOR-VALUE}', armor)),
      row('', S.HELP_ARMOR_ARROWS.replace('{ARMOR-VALUE}', Math.floor(armor / 3))),
      row('Damage:', `+${m.dmgBonus()} with hand weapons`),
      row(`${S.DAMAGE_BONUS}:`, `${m.dmgBonus()}`),
      row(`${S.PERCEPTION}:`, S.HELP_PERCEPTION.replace('{per}', per)),
      row(`${S.DEVICES}:`, S.HELP_DEVICES.replace('{dev}', dev)),
      row(`${S.TIT_GOSSIP}:`, S.HELP_GOSSIP.replace('{gos}', gos)),
      row('XP:', m.xp.toLocaleString()),
    ];
    // Game Stats section — quests completed from the var store (a quest id var > 99).
    const vars = this.varsOf ? this.varsOf() : {}, quests = this.quests || {};
    let done = 0; for (const id of Object.keys(quests)) if ((vars[id] | 0) >= 100) done++;
    const stats = [
      row('Level:', m.level()), row('Quests completed:', done),
      ...(m.isCaster() ? [row('Max Mana:', m.maxMana())] : []),
      row('Max Health:', m.maxHP()), row('Gold:', m.gold.toLocaleString()),
    ];
    this.cw.innerHTML = this._cwSubHead(S.HELP_STATS)
      + rows.join('')
      + `<div class="cw-h" style="margin-top:10px">${S.STATS_TITLE}</div>` + stats.join('')
      + `<div class="cw-backrow"><button class="cw-btn cw-back">Back</button></div></div>`;
    this._wireCWBack();
  }
}
