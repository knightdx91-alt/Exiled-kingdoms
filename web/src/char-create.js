// Title + character creation — styled with the base game's own assets (logo, splash
// backdrop, portrait-cycling arrows, EK's gold-on-stone look) and its real class
// descriptions (data/ui/strings/texts.txt via tools/gen-class-info.mjs).
//
// Mirrors Rules/PlayerCreation.java: name, gender, portrait, class, difficulty.
// On "Begin" it resolves with a PlayerCreation object the engine consumes.

// img = base name of the ui_icons region (assets/ui/creation/<img>_on/off.png)
export const CLASSES = [
  { id: 'WARRIOR', name: 'Warrior', img: 'class_warrior' },
  { id: 'ROGUE',   name: 'Rogue',   img: 'class_rogue' },
  { id: 'CLERIC',  name: 'Cleric',  img: 'class_cleric' },
  { id: 'WIZARD',  name: 'Wizard',  img: 'class_mage' },
];

// The five EK difficulties, each with its ui_icons artwork medallion. `img` is the
// region base (assets/ui/creation/<img>.png, disabled variant <img>_disabled.png).
export const DIFFICULTIES = [
  { id: 4, name: 'Story',    img: 'story',   blurb: 'A gentle telling — combat is trivial so you can enjoy the story.' },
  { id: 0, name: 'Casual',   img: 'easy',    blurb: 'A relaxed adventure. Enemies hit softer and death is forgiving.' },
  { id: 1, name: 'Normal',   img: 'normal',  blurb: 'The intended balance of challenge and story.' },
  { id: 2, name: 'Hard',     img: 'hard',    blurb: 'For veterans. Enemies are tougher and more numerous.' },
  { id: 3, name: 'Iron Man', img: 'ironman', blurb: '' },   // real description injected from class-info.json
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
    const [portraits, info, creation, skills, traits] = await Promise.all([
      fetch('assets/portraits/index.json').then(r => r.json()).catch(() => ({ male: [], female: [] })),
      fetch('assets/ui/class-info.json').then(r => r.json()).catch(() => ({})),
      fetch('assets/data/creation.json').then(r => r.json()),
      fetch('assets/data/skills.json').then(r => r.json()).catch(() => ({})),
      fetch('assets/data/traits.json').then(r => r.json()).catch(() => ({})),
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

      const gWrap = el('div', 'cc-genders');
      for (const g of [['MALE', 'male', 'Male'], ['FEMALE', 'female', 'Female']]) {
        const t = el('button', 'cc-img-tile cc-gender'); t.dataset.g = g[0];
        const im = el('img', 'cc-tile-img'); im.dataset.base = g[1]; t.appendChild(im);
        t.appendChild(el('span', 'cc-tile-label', g[2]));
        t.onclick = () => { state.gender = g[0]; state.pIndex = 0; renderGender(); renderPortrait(); };
        gWrap.appendChild(t);
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
      const cRow = el('div', 'cc-tiles');
      for (const c of CLASSES) {
        const t = el('button', 'cc-img-tile'); t.dataset.c = c.id;
        const im = el('img', 'cc-tile-img'); im.dataset.base = c.img; t.appendChild(im);
        t.appendChild(el('span', 'cc-tile-label', c.name));
        t.onclick = () => { state.charClass = c.id; renderClass(); };
        cRow.appendChild(t);
      }
      right.appendChild(cRow);

      const desc = el('div', 'cc-desc');
      right.appendChild(desc);

      right.appendChild(el('div', 'cc-heading', 'Difficulty'));
      const dRow = el('div', 'cc-tiles cc-diffs');
      for (const d of DIFFICULTIES) {
        const t = el('button', 'cc-img-tile'); t.dataset.d = String(d.id);
        const im = el('img', 'cc-tile-img cc-diff-img'); im.dataset.base = d.img; t.appendChild(im);
        t.appendChild(el('span', 'cc-tile-label', d.name));
        t.onclick = () => { state.difficulty = d.id; renderDiff(); };
        dRow.appendChild(t);
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
      // gender/difficulty images swap <base>.png / <base>_disabled.png; class uses _on/_off.
      function renderGender() {
        gWrap.querySelectorAll('.cc-gender').forEach(t => {
          const on = t.dataset.g === state.gender; t.classList.toggle('on', on);
          const im = t.querySelector('.cc-tile-img');
          im.src = `assets/ui/creation/${im.dataset.base}${on ? '' : '_disabled'}.png`;
        });
      }
      function renderClass() {
        cRow.querySelectorAll('.cc-img-tile').forEach(t => {
          const on = t.dataset.c === state.charClass; t.classList.toggle('on', on);
          const im = t.querySelector('.cc-tile-img');
          im.src = `assets/ui/creation/${im.dataset.base}_${on ? 'on' : 'off'}.png`;
        });
        desc.textContent = info[state.charClass] || '';
      }
      function renderDiff() {
        dRow.querySelectorAll('.cc-img-tile').forEach(t => {
          const on = t.dataset.d === String(state.difficulty); t.classList.toggle('on', on);
          const im = t.querySelector('.cc-tile-img');
          im.src = `assets/ui/creation/${im.dataset.base}${on ? '' : '_disabled'}.png`;
        });
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

    // ---------- attributes (traits) — reproduction of TraitsWindow (o0/a0) ----------
    // A parchment window: "Available points: N" header + six trait cards
    // (TraitDescriptionTable o0/y), each with the attribute icon, red name, current
    // value, a rank-based description (DESC_<ATTR>_<rank>), an info (help) button, and a
    // gold + button (arrow_up) that spends a point at the triangular cost. Reset + Next;
    // Next warns (SPEND_ALL_TRAITS) if points remain. Recovered in
    // deobf/CREATION_FLOW_SPEC.md + CHARACTER_STATS_SPEC.md.
    const TRAIT_REGION = { STR: 'trait_strenght', END: 'trait_endurance', AGI: 'trait_agility',
      INT: 'trait_intellect', AWA: 'trait_awareness', PER: 'trait_personality' };
    function attributesPage() {
      overlay.innerHTML = '';
      const ladder = creation.traitLadder, max = creation.attrMax, pool = creation.traitPoints;
      const spent = () => creation.attributes.reduce((s, a) => s + ladder[state.attrs[a.id]], 0);
      const descOf = (id, r) => {
        const d = traits.desc && traits.desc[id] || [];
        for (let i = Math.min(r, d.length - 1); i >= 0; i--) if (d[i] != null) return d[i];
        return '';
      };

      const win = el('div', 'tw-window');
      const title = el('div', 'tw-title', `${(traits.header || 'Available points')}: `);
      const ptsVal = el('span', 'tw-pts'); title.appendChild(ptsVal);
      win.appendChild(title);
      const grid = el('div', 'tw-grid'); win.appendChild(grid);

      const cards = creation.attributes.map((a) => {
        const card = el('div', 'tw-card');
        const head = el('div', 'tw-head');
        const icon = el('img', 'tw-icon'); icon.src = `assets/ui/traits/${TRAIT_REGION[a.id]}.png`;
        const name = el('span', 'tw-name', (traits.names && traits.names[a.id]) || a.name);
        const val = el('span', 'tw-val');
        head.append(icon, name, val);
        const desc = el('div', 'tw-desc');
        const foot = el('div', 'tw-foot');
        const info = el('button', 'tw-info'); info.title = 'Info';
        const plus = el('button', 'tw-plus');
        info.onclick = () => { if (traits.stats && traits.stats[a.id]) alert(traits.stats[a.id]); };
        plus.onclick = () => {
          const v = state.attrs[a.id], cost = ladder[v + 1] - ladder[v];
          if (v < max && pool - spent() >= cost) { state.attrs[a.id]++; refresh(); }
        };
        foot.append(info, plus);
        card.append(head, desc, foot); grid.appendChild(card);
        return { a, val, desc, plus };
      });

      const actions = el('div', 'tw-actions');
      const reset = el('button', 'cc-btn', traits.reset || 'Reset');
      reset.onclick = () => { for (const a of creation.attributes) state.attrs[a.id] = 0; refresh(); };
      const next = el('button', 'cc-btn cc-btn-primary', traits.next || 'Next');
      next.onclick = () => {
        if (pool - spent() > 0) { alert(traits.spendAll || 'Spend all your points first.'); return; }
        abilityPage();
      };
      actions.append(reset, next);
      win.appendChild(actions);
      overlay.appendChild(win);

      function refresh() {
        const rem = pool - spent();
        ptsVal.textContent = rem;
        for (const c of cards) {
          const v = state.attrs[c.a.id];
          c.val.textContent = v > 0 ? v : '';
          c.desc.textContent = descOf(c.a.id, v);
          c.plus.disabled = v >= max || rem < (ladder[v + 1] - ladder[v]);
        }
      }
      refresh();
    }

    // ---------- starting ability — reproduction of SkillWindow (o0/t) ----------
    // The parchment skill window: header "Available Skill Points: N   (You already used
    // M Points)"; a left detail pane (icon, name+level, Active/Passive, description,
    // Current level / Next level blurbs, Details) and a right skill grid grouped exactly
    // as the game does it — "[Class] Skills" (slots 0–7), "General Skills" (8–11) and
    // "Advanced Skills" (SPECIALIST, 12–19, locked at creation). A skill tile is
    // skill_bg + a smallsquare frame + the skill icon (or "unknown" when empty/locked);
    // the selected tile tints orange. Train spends the tier cost; Start game begins.
    // Traced from o0/t.java, o0/q.java (SkillImage), Skills.b(class). Level-1 pool = 1.
    const ROMAN = ['', 'I', 'II', 'III', 'IV', 'V', 'VI'];
    function abilityPage() {
      overlay.innerHTML = '';
      const pool = creation.skillPoints;                 // level-1 skill points (1)
      const trained = state.trained || (state.trained = {});   // name -> trained level
      const used = () => Object.values(trained).reduce((s, l) => s + l, 0);  // tier-1 cost 1/level

      const baseList = (skills.base && skills.base[state.charClass] || []).slice(0, 8);
      const genList  = (skills.base && skills.base.GENERAL || []).slice(0, 4);
      const advList  = (skills.advanced && skills.advanced[state.charClass] || []);   // locked at creation

      const win = el('div', 'sw-window');
      const top = el('div', 'sw-top');
      const avail = el('div', 'sw-avail');
      avail.append(el('span', null, 'Available Skill Points: '), el('span', 'sw-availn'));
      const usedLbl = el('div', 'sw-used');
      top.append(avail, usedLbl); win.appendChild(top);

      const main = el('div', 'sw-main');
      const detail = el('div', 'sw-detail');
      const dHead = el('div', 'sw-detail-head');
      const dIcon = el('img', 'sw-detail-icon');
      const dNames = el('div', 'sw-detail-names');
      const dName = el('div', 'sw-detail-name');
      const dType = el('div', 'sw-detail-type');
      dNames.append(dName, dType); dHead.append(dIcon, dNames);
      const dDesc = el('div', 'sw-detail-desc');
      const dCur = el('div', 'sw-lvl'); const dNext = el('div', 'sw-lvl');
      const details = el('button', 'cc-btn sw-details', 'Details');
      detail.append(dHead, dDesc, dCur, dNext, details);

      const grid = el('div', 'sw-grid');
      let selected = null;                                // { s, locked }
      const tile = (s, locked) => {
        const b = el('button', 'sw-tile');
        const bg = el('img', 'sw-tile-bg'); bg.src = 'assets/ui/skillbg/skill_bg0.png';
        const ic = el('img', 'sw-tile-icon');
        ic.src = (!locked && s && s.icon) ? `assets/ui/skills/${s.icon}.png` : 'assets/ui/skills/unknown.png';
        b.append(bg, ic);
        if (locked || !s) { b.classList.add('locked'); }
        else b.onclick = () => { selected = { s }; select(); };
        b.dataset.name = s ? s.name : '';
        return b;
      };
      const group = (label, items, count, locked) => {
        grid.appendChild(el('div', 'sw-group-title', label));
        const row = el('div', 'sw-row');
        for (let i = 0; i < count; i++) row.appendChild(tile(items[i] || null, locked || !items[i]));
        grid.appendChild(row);
      };
      const CLASS_LABEL = { WARRIOR: 'Warrior', ROGUE: 'Rogue', CLERIC: 'Cleric', WIZARD: 'Mage' };
      group(` ${CLASS_LABEL[state.charClass] || ''} Skills`, baseList, 8, false);
      group(' General Skills', genList, 4, false);
      group(' Advanced Skills', advList, 8, true);       // specialist — all locked at creation

      main.append(detail, grid); win.appendChild(main);

      const actions = el('div', 'sw-actions');
      const reset = el('button', 'cc-btn', 'Reset');
      const train = el('button', 'cc-btn sw-train');
      const start = el('button', 'cc-btn cc-btn-primary', 'Start game');
      actions.append(reset, train, start); win.appendChild(actions);
      overlay.appendChild(win);

      reset.onclick = () => { for (const k in trained) delete trained[k]; selected = null; select(); };
      train.onclick = () => {
        if (!selected) return;
        const s = selected.s, lvl = trained[s.name] || 0, cost = s.cost || 1;
        if (lvl >= s.levels.length) return;
        if (pool - used() < cost) return;                // not enough points
        trained[s.name] = lvl + 1; select();
      };
      start.onclick = () => { overlay.remove(); resolve({
        name: state.name, gender: state.gender, charClass: state.charClass,
        portrait: state.portrait, difficulty: state.difficulty,
        attributes: { ...state.attrs },
        trainedSkills: { ...trained },
        startingSkill: Object.keys(trained)[0] || null,
      }); };

      function select() {
        const rem = pool - used();
        avail.querySelector('.sw-availn').textContent = rem;
        usedLbl.textContent = `(You already used ${used()} ${used() === 1 ? 'Point' : 'Points'})`;
        grid.querySelectorAll('.sw-tile').forEach(t =>
          t.classList.toggle('on', !!selected && t.dataset.name === selected.s.name));
        if (!selected) {
          detail.style.visibility = 'hidden';
          train.textContent = 'Train'; train.disabled = true;
          return;
        }
        detail.style.visibility = 'visible';
        const s = selected.s, lvl = trained[s.name] || 0;
        dIcon.src = s.icon ? `assets/ui/skills/${s.icon}.png` : 'assets/ui/skills/unknown.png';
        dName.textContent = `${s.name}${lvl ? ' ' + ROMAN[lvl] : ''}`;
        dType.textContent = s.type === 'A' ? 'Active Skill' : 'Passive Skill';
        dType.className = 'sw-detail-type ' + (s.type === 'A' ? 'active' : 'passive');
        dDesc.textContent = s.desc;
        const cur = lvl > 0 ? (s.levels[lvl - 1] || {}).desc : "You haven't trained this skill yet";
        dCur.innerHTML = '';
        dCur.append(el('span', 'sw-lvl-h', 'Current level:'), el('span', 'sw-lvl-t', ' ' + (cur || '')));
        const nx = s.levels[lvl];
        dNext.innerHTML = '';
        if (nx) dNext.append(el('span', 'sw-lvl-h', 'Next level:'), el('span', 'sw-lvl-t', ' ' + nx.desc));
        dNext.style.display = nx ? '' : 'none';
        const cost = s.cost || 1, canTrain = nx && rem >= cost;
        train.textContent = `Train (${cost} SP)`;
        train.disabled = !canTrain;
      }

      select();
    }

    title();
  });
}
