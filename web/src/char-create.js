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
    const [portraits, info] = await Promise.all([
      fetch('assets/portraits/index.json').then(r => r.json()).catch(() => ({ male: [], female: [] })),
      fetch('assets/ui/class-info.json').then(r => r.json()).catch(() => ({})),
    ]);
    if (info.IRONMAN_DESC) DIFFICULTIES[3].blurb = info.IRONMAN_DESC.split('\n')[0];

    const overlay = el('div', 'cc-overlay');
    root.appendChild(overlay);

    const listFor = (g) => (portraits[g.toLowerCase()] || []).filter(f => f !== '0.png');
    const state = {
      name: '', gender: 'MALE', charClass: 'WARRIOR', difficulty: 1,
      pIndex: 0,
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
      const begin = el('button', 'cc-btn cc-btn-primary', 'Begin');
      begin.onclick = () => { if (!state.name) { nameIn.focus(); return; } overlay.remove();
        resolve({ name: state.name, gender: state.gender, charClass: state.charClass, portrait: state.portrait, difficulty: state.difficulty }); };
      actions.append(back, begin);
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
      function validate() { begin.disabled = !state.name; }

      renderPortrait(); renderGender(); renderClass(); renderDiff(); validate();
    }

    title();
  });
}
