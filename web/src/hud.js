// In-game HUD — portrait, health/mana/XP bars, level, gold, and action buttons, with a
// Character panel (attributes + derived stats). Mirrors GameHUD's information (see
// deobf/HUD_SPEC.md); styling is a placeholder EK theme until the exact GameHUD art is
// matched. A DOM overlay in screen space, so it reflows tall↔wide with the viewport.

const ATTR = [['STR', 'Strength'], ['END', 'Endurance'], ['AGI', 'Agility'],
              ['INT', 'Intellect'], ['AWA', 'Awareness'], ['PER', 'Personality']];

export class HUD {
  constructor(root) {
    this.model = null;
    this.el = document.createElement('div');
    this.el.id = 'hud';
    this.el.style.display = 'none';
    this.el.innerHTML = `
      <div class="hud-top">
        <img class="hud-portrait" alt="">
        <div class="hud-bars">
          <div class="hud-name-row"><span class="hud-name"></span><span class="hud-lvl"></span></div>
          <div class="hud-bar hud-hp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
          <div class="hud-bar hud-mp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
          <div class="hud-bar hud-xp"><div class="hud-fill"></div><span class="hud-bt"></span></div>
        </div>
        <div class="hud-gold"><span class="hud-coin">◉</span><span class="hud-gold-n"></span></div>
      </div>
      <div class="hud-btns">
        <button class="hud-btn" data-act="char" title="Character">🛡</button>
        <button class="hud-btn" data-act="journal" title="Journal">📖</button>
        <button class="hud-btn" data-act="inv" title="Inventory">🎒</button>
        <button class="hud-btn" data-act="menu" title="Menu">☰</button>
      </div>
      <div class="hud-panel" style="display:none"></div>`;
    root.appendChild(this.el);

    this.$ = (s) => this.el.querySelector(s);
    this.panel = this.$('.hud-panel');
    this.el.querySelectorAll('.hud-btn').forEach(b =>
      b.addEventListener('click', () => this.onButton(b.dataset.act)));
    this._cache = {};
  }

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
    const hp = Math.ceil(m.hp), maxhp = m.maxHP();
    const mp = Math.ceil(m.mana), maxmp = m.maxMana();
    const key = `${hp}/${maxhp}|${mp}/${maxmp}|${m.level()}|${Math.round(m.xpProgress() * 100)}|${m.gold}`;
    if (!force && key === this._cache.key) return;
    this._cache.key = key;

    this.setBar('.hud-hp', hp / maxhp, `${hp} / ${maxhp}`);
    if (maxmp) this.setBar('.hud-mp', mp / maxmp, `${mp} / ${maxmp}`);
    this.setBar('.hud-xp', m.xpProgress(), m.xpToNext() ? `XP ${m.xpToNext().toLocaleString()} to next` : 'MAX');
    this.$('.hud-lvl').textContent = `Lv ${m.level()}`;
    this.$('.hud-gold-n').textContent = m.gold.toLocaleString();
    if (this.panel.style.display !== 'none') this.renderCharacter();
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
    else if (which === 'inv') this.panel.innerHTML =
      `<div class="hud-panel-h">Inventory</div><p class="hud-dim">Items &amp; equipment come with the combat/inventory system.</p>${this.closeBtn()}`;
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
    this.panel.innerHTML = `
      <div class="hud-panel-h">${m.name} — ${cls} · Lv ${m.level()}</div>
      <div class="hud-stats">
        <div class="hud-stat"><span>Health</span><b>${Math.ceil(m.hp)}/${m.maxHP()}</b></div>
        ${m.isCaster() ? `<div class="hud-stat"><span>Mana</span><b>${Math.ceil(m.mana)}/${m.maxMana()}</b></div>` : ''}
        <div class="hud-stat"><span>XP</span><b>${m.xp.toLocaleString()}</b></div>
        <div class="hud-stat"><span>Gold</span><b>${m.gold}</b></div>
      </div>
      <div class="hud-panel-sub">Attributes</div>
      <div class="hud-stats">${rows}</div>
      <div class="hud-panel-sub">Skills</div>
      <p class="hud-skills">${skills}</p>
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

  closeBtn() { return `<button class="hud-close">Close</button>`; }
  wireClose() {
    const b = this.panel.querySelector('.hud-close');
    if (b) b.onclick = () => { this.panel.style.display = 'none'; };
  }
}
