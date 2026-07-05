// Title + character creation — the start of the game.
//
// Mirrors the base game's PlayerCreation (Rules/PlayerCreation.java): the player
// picks name, gender, portrait, class and difficulty, then begins in the tutorial.
// Built as a DOM overlay (forms/scroll grids are far cleaner in DOM than in canvas);
// on "Begin" it resolves with a PlayerCreation object the engine consumes.

export const CLASSES = [
  { id: 'WARRIOR', name: 'Warrior', blurb: 'Tough melee fighter. High HP and armor; masters weapons and shields.' },
  { id: 'ROGUE',   name: 'Rogue',   blurb: 'Agile and deadly. Stealth, critical strikes, traps and ranged attacks.' },
  { id: 'CLERIC',  name: 'Cleric',  blurb: 'Holy warrior. Heals, blesses, and smites the undead with divine power.' },
  { id: 'WIZARD',  name: 'Wizard',  blurb: 'Master of the arcane. Devastating spells but fragile in a brawl.' },
];

export const DIFFICULTIES = [
  { id: 0, key: 'CASUAL',  name: 'Casual',  blurb: 'A relaxed adventure. Enemies hit softer; death is forgiving.' },
  { id: 1, key: 'NORMAL',  name: 'Normal',  blurb: 'The intended balance of challenge and story.' },
  { id: 2, key: 'HARD',    name: 'Hard',    blurb: 'For veterans. Enemies are tougher and smarter.' },
  { id: 3, key: 'IRONMAN', name: 'Iron Man', blurb: 'Hard, plus a single save that is deleted on death. No second chances.' },
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
    const portraits = await fetch('assets/portraits/index.json').then(r => r.json()).catch(() => ({ male: [], female: [] }));

    const overlay = el('div', 'cc-overlay');
    root.appendChild(overlay);

    // ---- state ----
    const state = { name: '', gender: 'MALE', charClass: 'WARRIOR', portrait: portraits.male[0] || null, difficulty: 1 };

    // ---- title screen ----
    function title() {
      overlay.innerHTML = '';
      const box = el('div', 'cc-panel cc-title');
      box.appendChild(el('h1', 'cc-logo', 'Exiled Kingdoms'));
      box.appendChild(el('p', 'cc-sub', 'A web port — begin your journey'));
      const start = el('button', 'cc-primary', 'Start New Game');
      start.onclick = create;
      box.appendChild(start);
      overlay.appendChild(box);
    }

    // ---- creation screen ----
    function create() {
      overlay.innerHTML = '';
      const panel = el('div', 'cc-panel');
      panel.appendChild(el('h2', 'cc-h', 'Create your character'));

      // name
      const nameRow = el('label', 'cc-field');
      nameRow.appendChild(el('span', 'cc-label', 'Name'));
      const nameIn = el('input', 'cc-input');
      nameIn.type = 'text'; nameIn.maxLength = 18; nameIn.placeholder = 'Enter a name';
      nameIn.value = state.name;
      nameIn.oninput = () => { state.name = nameIn.value.trim(); validate(); };
      nameRow.appendChild(nameIn);
      panel.appendChild(nameRow);

      // gender toggle
      const gRow = el('div', 'cc-field');
      gRow.appendChild(el('span', 'cc-label', 'Gender'));
      const gWrap = el('div', 'cc-seg');
      for (const g of [['MALE', 'Male'], ['FEMALE', 'Female']]) {
        const b = el('button', 'cc-seg-btn', g[1]);
        b.dataset.g = g[0];
        b.onclick = () => { state.gender = g[0]; state.portrait = portraits[g[0].toLowerCase()][0] || null; renderGender(); renderPortraits(); };
        gWrap.appendChild(b);
      }
      gRow.appendChild(gWrap);
      panel.appendChild(gRow);

      // class picker
      panel.appendChild(el('span', 'cc-label', 'Class'));
      const cWrap = el('div', 'cc-cards');
      for (const c of CLASSES) {
        const card = el('button', 'cc-card');
        card.dataset.c = c.id;
        card.appendChild(el('div', 'cc-card-name', c.name));
        card.appendChild(el('div', 'cc-card-blurb', c.blurb));
        card.onclick = () => { state.charClass = c.id; renderClass(); };
        cWrap.appendChild(card);
      }
      panel.appendChild(cWrap);

      // difficulty picker
      panel.appendChild(el('span', 'cc-label', 'Difficulty'));
      const dWrap = el('div', 'cc-cards');
      for (const d of DIFFICULTIES) {
        const card = el('button', 'cc-card');
        card.dataset.d = String(d.id);
        card.appendChild(el('div', 'cc-card-name', d.name));
        card.appendChild(el('div', 'cc-card-blurb', d.blurb));
        card.onclick = () => { state.difficulty = d.id; renderDiff(); };
        dWrap.appendChild(card);
      }
      panel.appendChild(dWrap);

      // portrait grid
      panel.appendChild(el('span', 'cc-label', 'Portrait'));
      const pGrid = el('div', 'cc-portraits');
      panel.appendChild(pGrid);

      // actions
      const actions = el('div', 'cc-actions');
      const back = el('button', 'cc-secondary', 'Back');
      back.onclick = title;
      const begin = el('button', 'cc-primary', 'Begin');
      begin.onclick = () => {
        if (!state.name) { nameIn.focus(); return; }
        overlay.remove();
        resolve({ ...state });
      };
      actions.append(back, begin);
      panel.appendChild(actions);

      overlay.appendChild(panel);

      // renderers
      function renderGender() { gWrap.querySelectorAll('.cc-seg-btn').forEach(b => b.classList.toggle('on', b.dataset.g === state.gender)); }
      function renderClass() { cWrap.querySelectorAll('.cc-card').forEach(b => b.classList.toggle('on', b.dataset.c === state.charClass)); }
      function renderDiff() { dWrap.querySelectorAll('.cc-card').forEach(b => b.classList.toggle('on', b.dataset.d === String(state.difficulty))); }
      function renderPortraits() {
        pGrid.innerHTML = '';
        const list = portraits[state.gender.toLowerCase()] || [];
        for (const f of list) {
          const img = el('img', 'cc-portrait');
          img.src = `assets/portraits/${state.gender.toLowerCase()}/${f}`;
          img.loading = 'lazy';
          img.onclick = () => { state.portrait = f; pGrid.querySelectorAll('.cc-portrait').forEach(i => i.classList.remove('on')); img.classList.add('on'); };
          if (f === state.portrait) img.classList.add('on');
          pGrid.appendChild(img);
        }
      }
      function validate() { begin.disabled = !state.name; }

      renderGender(); renderClass(); renderDiff(); renderPortraits(); validate();
    }

    title();
  });
}
