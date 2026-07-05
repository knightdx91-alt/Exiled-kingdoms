// Title + character creation — styled with the base game's own assets (logo, splash
// backdrop, portrait-cycling arrows, EK's gold-on-stone look) and its real class
// descriptions (data/ui/strings/texts.txt via tools/gen-class-info.mjs).
//
// Mirrors Rules/PlayerCreation.java: name, gender, portrait, class, difficulty.
// On "Begin" it resolves with a PlayerCreation object the engine consumes.

export const CLASSES = [
  { id: 'WARRIOR', name: 'Warrior' },
  { id: 'ROGUE',   name: 'Rogue' },
  { id: 'CLERIC',  name: 'Cleric' },
  { id: 'WIZARD',  name: 'Wizard' },
];

export const DIFFICULTIES = [
  { id: 0, name: 'Casual',   blurb: 'A relaxed adventure. Enemies hit softer and death is forgiving.' },
  { id: 1, name: 'Normal',   blurb: 'The intended balance of challenge and story.' },
  { id: 2, name: 'Hard',     blurb: 'For veterans. Enemies are tougher and more numerous.' },
  { id: 3, name: 'Iron Man', blurb: '' },   // real description injected from class-info.json
];

const el = (tag, cls, txt) => {
  const e = document.createElement(tag);
  if (cls) e.className = cls;
  if (txt != null) e.textContent = txt;
  return e;
};

// Show the title screen, then character creation. Resolves with
// { name, gender ('MALE'|'FEMALE'), charClass, portrait, difficulty }.
export function startFlow(root) {
  return new Promise(async (resolve) => {
    const [portraits, info, creation, skills] = await Promise.all([
      fetch('assets/portraits/index.json').then(r => r.json()).catch(() => ({ male: [], female: [] })),
      fetch('assets/ui/class-info.json').then(r => r.json()).catch(() => ({})),
      fetch('assets/data/creation.json').then(r => r.json()),
      fetch('assets/data/skills.json').then(r => r.json()).catch(() => ({})),
    ]);
    if (info.IRONMAN_DESC) DIFFICULTIES[3].blurb = info.IRONMAN_DESC.split('\n')[0];

    const overlay = el('div', 'cc-overlay');
    root.appendChild(overlay);

    const listFor = (g) => (portraits[g.toLowerCase()] || []).filter(f => f !== '0.png');
    const state = {
      name: '', gender: 'MALE', charClass: 'WARRIOR', difficulty: 1,
      pIndex: 0,
      attrs: { STR: 0, END: 0, AGI: 0, INT: 0, AWA: 0, PER: 0 },   // all start at 0
      startingSkill: null,
      get portrait() { const l = listFor(this.gender); return l[this.pIndex] || null; },
    };

    // ---------- title ----------
    function title() {
      overlay.innerHTML = '';
      const box = el('div', 'cc-title');
      const logo = el('img', 'cc-logo-img'); logo.src = 'assets/ui/logo.png'; logo.alt = 'Exiled Kingdoms';
      box.appendChild(logo);
      const start = el('button', 'cc-btn cc-btn-primary', 'Start New Game');
      start.onclick = create;
      box.appendChild(start);
      const note = el('p', 'cc-note', 'A web port — recovered from the base game');
      box.appendChild(note);
      overlay.appendChild(box);
    }

    // ---------- creation ----------
    function create() {
      overlay.innerHTML = '';
      const panel = el('div', 'cc-panel');

      const logo = el('img', 'cc-logo-sm'); logo.src = 'assets/ui/logo.png'; logo.alt = 'Exiled Kingdoms';
      panel.appendChild(logo);

      const grid = el('div', 'cc-grid');

      // -- left: portrait + gender + name --
      const left = el('div', 'cc-col');

      const pWrap = el('div', 'cc-portrait-wrap');
      const aL = el('button', 'cc-arrow'); aL.style.backgroundImage = 'url(assets/ui/arrow_left.png)';
      const frame = el('div', 'cc-frame');
      const pImg = el('img', 'cc-portrait'); frame.appendChild(pImg);
      const aR = el('button', 'cc-arrow cc-arrow-r'); aR.style.backgroundImage = 'url(assets/ui/arrow_left.png)';
      aL.onclick = () => cyclePortrait(-1);
      aR.onclick = () => cyclePortrait(1);
      pWrap.append(aL, frame, aR);
      left.appendChild(pWrap);

      const gWrap = el('div', 'cc-seg');
      for (const g of [['MALE', 'Male'], ['FEMALE', 'Female']]) {
        const b = el('button', 'cc-seg-btn', g[1]); b.dataset.g = g[0];
        b.onclick = () => { state.gender = g[0]; state.pIndex = 0; renderGender(); renderPortrait(); };
        gWrap.appendChild(b);
      }
      left.appendChild(gWrap);

      const nameIn = el('input', 'cc-input');
      nameIn.type = 'text'; nameIn.maxLength = 18; nameIn.placeholder = 'Name your hero';
      nameIn.value = state.name;
      nameIn.oninput = () => { state.name = nameIn.value.trim(); validate(); };
      left.appendChild(nameIn);

      grid.appendChild(left);

      // -- right: class + description + difficulty --
      const right = el('div', 'cc-col');

      right.appendChild(el('div', 'cc-heading', 'Class'));
      const cRow = el('div', 'cc-choices');
      for (const c of CLASSES) {
        const b = el('button', 'cc-choice', c.name); b.dataset.c = c.id;
        b.onclick = () => { state.charClass = c.id; renderClass(); };
        cRow.appendChild(b);
      }
      right.appendChild(cRow);

      const desc = el('div', 'cc-desc');
      right.appendChild(desc);

      right.appendChild(el('div', 'cc-heading', 'Difficulty'));
      const dRow = el('div', 'cc-choices');
      for (const d of DIFFICULTIES) {
        const b = el('button', 'cc-choice', d.name); b.dataset.d = String(d.id);
        b.onclick = () => { state.difficulty = d.id; renderDiff(); };
        dRow.appendChild(b);
      }
      right.appendChild(dRow);
      const dDesc = el('div', 'cc-subdesc');
      right.appendChild(dDesc);

      grid.appendChild(right);
      panel.appendChild(grid);

      // -- actions --
      const actions = el('div', 'cc-actions');
      const back = el('button', 'cc-btn cc-btn-ghost', 'Back'); back.onclick = title;
      const next = el('button', 'cc-btn cc-btn-primary', 'Next →');
      next.onclick = () => { if (!state.name) { nameIn.focus(); return; } attributesPage(); };
      actions.append(back, next);
      panel.appendChild(actions);

      overlay.appendChild(panel);

      // renderers
      function cyclePortrait(d) { const l = listFor(state.gender); if (!l.length) return;
        state.pIndex = (state.pIndex + d + l.length) % l.length; renderPortrait(); }
      function renderPortrait() { const f = state.portrait;
        pImg.src = f ? `assets/portraits/${state.gender.toLowerCase()}/${f}` : ''; }
      function renderGender() { gWrap.querySelectorAll('.cc-seg-btn').forEach(b => b.classList.toggle('on', b.dataset.g === state.gender)); }
      function renderClass() {
        cRow.querySelectorAll('.cc-choice').forEach(b => b.classList.toggle('on', b.dataset.c === state.charClass));
        desc.textContent = info[state.charClass] || '';
      }
      function renderDiff() {
        dRow.querySelectorAll('.cc-choice').forEach(b => b.classList.toggle('on', b.dataset.d === String(state.difficulty)));
        dDesc.textContent = (DIFFICULTIES.find(d => d.id === state.difficulty) || {}).blurb || '';
      }
      function validate() { next.disabled = !state.name; }

      renderPortrait(); renderGender(); renderClass(); renderDiff(); validate();
    }

    // A shared page scaffold: logo + title + body + Back/primary actions.
    function pageShell(titleText) {
      overlay.innerHTML = '';
      const panel = el('div', 'cc-panel');
      const logo = el('img', 'cc-logo-sm'); logo.src = 'assets/ui/logo.png'; panel.appendChild(logo);
      panel.appendChild(el('div', 'cc-heading cc-step-title', titleText));
      const body = el('div', 'cc-step-body'); panel.appendChild(body);
      const actions = el('div', 'cc-actions'); panel.appendChild(actions);
      overlay.appendChild(panel);
      return { panel, body, actions };
    }

    // ---------- attributes (traits) ----------
    // 6 attributes, 0..attrMax, raised with a shared pool of `traitPoints`. Reaching
    // rank v costs cumulative traitLadder[v]; the marginal cost of the next rank is v+1
    // (triangular). Recovered in deobf/CHARACTER_STATS_SPEC.md.
    function attributesPage() {
      const { body, actions } = pageShell('Attributes');
      const ladder = creation.traitLadder, max = creation.attrMax, pool = creation.traitPoints;
      const spent = () => creation.attributes.reduce((s, a) => s + ladder[state.attrs[a.id]], 0);
      const pip = el('div', 'cc-pool'); body.appendChild(pip);
      const rows = el('div', 'cc-attrs'); body.appendChild(rows);

      const rowEls = creation.attributes.map((a) => {
        const row = el('div', 'cc-attr-row');
        const dec = el('button', 'cc-step-btn', '−');
        const val = el('span', 'cc-attr-val');
        const inc = el('button', 'cc-step-btn', '+');
        dec.onclick = () => { if (state.attrs[a.id] > 0) { state.attrs[a.id]--; refresh(); } };
        inc.onclick = () => {
          const v = state.attrs[a.id], cost = ladder[v + 1] - ladder[v];
          if (v < max && pool - spent() >= cost) { state.attrs[a.id]++; refresh(); }
        };
        row.append(el('span', 'cc-attr-name', a.name), dec, val, inc);
        rows.appendChild(row);
        return { a, val, dec, inc };
      });

      function refresh() {
        const rem = pool - spent();
        pip.textContent = `Points to spend: ${rem}`;
        for (const r of rowEls) {
          const v = state.attrs[r.a.id];
          r.val.textContent = v;
          r.dec.disabled = v <= 0;
          r.inc.disabled = v >= max || rem < (ladder[v + 1] - ladder[v]);
        }
      }

      const back = el('button', 'cc-btn cc-btn-ghost', 'Back'); back.onclick = create;
      const next = el('button', 'cc-btn cc-btn-primary', 'Next →'); next.onclick = abilityPage;
      actions.append(back, next);
      refresh();
    }

    // ---------- starting ability ----------
    // One starting skill (1 skill point) from the class's tier-1 abilities (class list +
    // GENERAL), each tier-1 costing 1. Actives show a mana cost. Optional (may bank it).
    function abilityPage() {
      const { body, actions } = pageShell('Starting Ability');
      const list = (skills[state.charClass] || []).concat(skills.GENERAL || [])
        .filter(s => (s.cost || 1) <= creation.skillPoints);
      body.appendChild(el('div', 'cc-pool', `Choose a starting ability (1 skill point) — or skip and save it.`));
      const grid = el('div', 'cc-skills'); body.appendChild(grid);
      const desc = el('div', 'cc-subdesc'); body.appendChild(desc);

      for (const s of list) {
        const b = el('button', 'cc-skill');
        b.append(el('span', 'cc-skill-name', s.name),
                 el('span', 'cc-skill-tag', s.type === 'A' ? (s.mana ? `Active · ${s.mana} mana` : 'Active') : 'Passive'));
        b.onclick = () => {
          state.startingSkill = state.startingSkill === s.name ? null : s.name;
          grid.querySelectorAll('.cc-skill').forEach(x => x.classList.toggle('on', x === b && state.startingSkill));
          desc.textContent = state.startingSkill ? s.desc : '';
        };
        grid.appendChild(b);
      }
      if (!list.length) desc.textContent = 'No selectable abilities for this class.';

      const back = el('button', 'cc-btn cc-btn-ghost', 'Back'); back.onclick = attributesPage;
      const begin = el('button', 'cc-btn cc-btn-primary', 'Begin');
      begin.onclick = () => { overlay.remove(); resolve({
        name: state.name, gender: state.gender, charClass: state.charClass,
        portrait: state.portrait, difficulty: state.difficulty,
        attributes: { ...state.attrs }, startingSkill: state.startingSkill,
      }); };
      actions.append(back, begin);
    }

    title();
  });
}
