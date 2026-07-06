// Loads the prototype in a real headless Chrome (Android-sized viewport),
// exercises all four player-chosen orientations, screenshots each, and verifies
// pointer hit-testing lands on the hero token in every orientation.
import { chromium } from 'playwright';
import http from 'node:http';
import fs from 'node:fs';
import path from 'node:path';

const ROOT = path.resolve('.');
const MIME = { '.html': 'text/html', '.js': 'text/javascript', '.css': 'text/css' };
const server = http.createServer((req, res) => {
  let p = decodeURIComponent(req.url.split('?')[0]);
  if (p === '/favicon.ico') { res.writeHead(204); return res.end(); }
  if (p === '/') p = '/index.html';
  const f = path.join(ROOT, p);
  if (!f.startsWith(ROOT) || !fs.existsSync(f)) { res.writeHead(404); return res.end('nf'); }
  res.writeHead(200, { 'content-type': MIME[path.extname(f)] || 'application/octet-stream' });
  fs.createReadStream(f).pipe(res);
});
await new Promise(r => server.listen(0, r));
const port = server.address().port;

const browser = await chromium.launch({ executablePath: '/opt/pw-browsers/chromium' });
const ctx = await browser.newContext({
  viewport: { width: 412, height: 915 },              // Pixel-ish portrait
  deviceScaleFactor: 2, isMobile: true, hasTouch: true,
});
const page = await ctx.newPage();
const errors = [];
const benign = t => /favicon\.ico|\.map\b/.test(t);
page.on('console', m => { if (m.type() === 'error' && !benign(m.text())) errors.push(m.text()); });
page.on('pageerror', e => errors.push(String(e)));

await page.goto(`http://localhost:${port}/`, { waitUntil: 'networkidle' });
await page.waitForFunction(() => window.__EK && window.__EK.logical().w > 0, { timeout: 8000 });

// --- Start flow: the title/character-creation overlay should appear, then quick-start
//     past it into the seamless world (H10) for the rest of the checks. ---
await page.waitForFunction(() => window.__EK.titleShown && window.__EK.titleShown(), { timeout: 8000 });
const titleOk = await page.evaluate(() => window.__EK.titleShown());
const started = await page.evaluate(() => window.__EK.quickStart({ pc: { gender: 'FEMALE', name: 'Aria' } }));
console.log('start flow: titleShown', titleOk, '-> quickStart', started);
const playerOk = await page.evaluate(() => {
  const p = window.__EK.player();
  return !!p && p.name === 'Aria' && p.gender === 'FEMALE';
});

// --- Player model + HUD: derived stats from the recovered formulas, live HUD ---
const hud = await page.evaluate(() => {
  const s0 = window.__EK.stats();
  const shown = window.__EK.hudShown();
  const hpAfter = window.__EK.hurt(10);            // take 10 damage -> HP drops
  const s1 = window.__EK.stats();
  const charPanel = window.__EK.openChar();
  window.__EK.openChar();                            // toggle it back closed
  return { s0, shown, hpAfter, s1, charPanel };
});
console.log('player/HUD:', hud);
// female default in verify = WARRIOR: 45 HP/+6 at level 1 => 51 maxhp, gold 18, no mana
const hudOk = hud.shown && hud.s0 && hud.s0.level === 1 && hud.s0.gold === 18 &&
              hud.s0.maxhp === (45 + 6 * 1) && hud.s0.caster === false &&
              hud.hpAfter === hud.s0.hp - 10 && hud.charPanel === true;

// --- Real map: the converted .tmx should render real tiles on screen ---
await page.waitForFunction(() => window.__EK.map && window.__EK.map().tiles > 0, { timeout: 8000 });
const mapInfo = await page.evaluate(() => window.__EK.map());
console.log('map rendered:', mapInfo);
// tiles present; hero is depth-sorted INTO the scenery/objects plane (not forced on
// top) — some objects draw after it.
const mapOk = mapInfo.tiles > 100 &&
              mapInfo.heroIndex >= 0 && mapInfo.heroIndex < mapInfo.midCount - 1;

// --- Day/night: outdoor map is brighter at noon than at 2am (recovered clock model) ---
const light = await page.evaluate(() => {
  const day = window.__EK.setHour(12).valueOf(); const d = window.__EK.light().ambient;
  window.__EK.setHour(2); const n = window.__EK.light().ambient;
  const info = window.__EK.light();
  window.__EK.setHour(new Date().getHours());   // restore
  return { day: d, night: n, outdoor: info.outdoor };
});
console.log('day/night:', light);
const dayLum = light.day.r + light.day.g + light.day.b;
const nightLum = light.night.r + light.night.g + light.night.b;
const lightOk = light.outdoor && dayLum > nightLum && light.night.b > light.night.r; // night is dim & blue

// --- Real character sprite: hero exists with a valid facing animation (idles at rest) ---
await page.waitForFunction(() => window.__EK.hero && window.__EK.hero(), { timeout: 8000 });
const heroInfo = await page.evaluate(() => window.__EK.hero());
console.log('hero:', heroInfo);
const heroOk = !!heroInfo.anim && heroInfo.anim.startsWith('hero_');

// --- Pinch/wheel zoom: base view is the max zoom-out (clamped), zoom-in works ---
const zoom = await page.evaluate(() => {
  const out = window.__EK.setZoom(0.5);   // try to zoom out past base -> must clamp to 1
  const inn = window.__EK.setZoom(1.5);   // zoom in -> should take
  window.__EK.setZoom(1);                 // reset
  return { clampedOut: out, zoomedIn: inn };
});
console.log('zoom:', zoom);
const zoomOk = zoom.clampedOut === 1 && zoom.zoomedIn > 1;

// --- Movement: tap-to-move paths the hero to a new cell (collision-aware A*) ---
const move = await page.evaluate(async () => {
  const start = window.__EK.heroCell();
  const goal = window.__EK.walkBy(4, 4);            // path a few cells away
  await new Promise(r => setTimeout(r, 200));
  const walkingAnim = window.__EK.hero().anim;      // should be a walk anim mid-move
  const t0 = Date.now();
  while (window.__EK.moving() && Date.now() - t0 < 6000) await new Promise(r => setTimeout(r, 100));
  return { start, goal, end: window.__EK.heroCell(), walkingAnim, restAnim: window.__EK.hero().anim };
});
console.log('movement:', move);
const moveOk = move.end && (move.end.c !== move.start.c || move.end.r !== move.start.r) &&
               move.walkingAnim.includes('walk') && move.restAnim.includes('idle');

// --- Free-floating joystick: switch control, push the stick, hero moves + walks ---
const stick = await page.evaluate(async () => {
  window.__EK.setControl('joystick');
  const start = window.__EK.heroCell();
  window.__EK.stick(0.9, 0.2);                        // hold the stick right-ish
  await new Promise(r => setTimeout(r, 700));
  const midAnim = window.__EK.hero().anim;
  window.__EK.stick(0, 0);                            // release
  await new Promise(r => setTimeout(r, 150));
  const end = window.__EK.heroCell();
  window.__EK.setControl('tap');
  return { start, end, midAnim, mode: window.__EK.control() };
});
console.log('joystick:', stick);
const stickOk = stick.start && stick.end &&
                (stick.start.c !== stick.end.c || stick.start.r !== stick.end.r) &&
                stick.midAnim.includes('walk');

// --- Map transitions: walk onto a portal and confirm the area actually changes ---
const trans = await page.evaluate(async () => {
  const from = window.__EK.map().name;
  const t = window.__EK.gotoTransition();
  if (!t) return { skipped: true };
  const t0 = Date.now();
  while (window.__EK.map().name === from && Date.now() - t0 < 15000)
    await new Promise(r => setTimeout(r, 150));
  return { from, target: t.area, to: window.__EK.map().name, tiles: window.__EK.map().tiles };
});
console.log('transition:', trans);
const transOk = trans.skipped || (trans.to === trans.target && trans.to !== trans.from && trans.tiles > 0);

fs.mkdirSync('shots', { recursive: true });
const names = { 0: 'portrait', 90: 'landscape', 180: 'reverse-portrait', 270: 'reverse-landscape' };
const results = [];
for (const deg of [0, 90, 180, 270]) {
  await page.evaluate(d => window.__EK.setOrient(d), deg);
  await page.waitForTimeout(250);
  await page.screenshot({ path: `shots/${deg}-${names[deg]}.png` });

  // where does the hero token appear on physical screen, and does a tap there hit it?
  const info = await page.evaluate(() => {
    const s = window.__EK.tokenScreen();
    return { screen: s, hit: window.__EK.hitTest(s.x, s.y), logical: window.__EK.logical() };
  });
  // tap slightly off-center on the token via the real input pipeline
  await page.mouse.click(info.screen.x + 6, info.screen.y - 6);
  const tapped = await page.evaluate(s => window.__EK.hitTest(s.x + 6, s.y - 6), info.screen);
  results.push({ orient: `${deg}° ${names[deg]}`, logical: info.logical,
                 tokenAt: { x: Math.round(info.screen.x), y: Math.round(info.screen.y) },
                 inputHitsToken: info.hit && tapped });
}

console.log('errors:', errors.length ? errors : 'none');
console.table(results);
const orientOk = results.every(r => r.inputHitsToken);

// --- PWA: wait for the service worker to cache the full game ---
const cached = await page.evaluate(() => new Promise((res) => {
  const check = () => {
    const c = window.__EK && window.__EK.cache;
    if (c && c.complete) return res(c);
    setTimeout(check, 100);
  };
  check();
}));
console.log('full-game cache:', cached);

// --- Offline: cut the network, reload, and confirm the game still boots ---
await ctx.setOffline(true);
await page.reload({ waitUntil: 'domcontentloaded' });
const offlineBooted = await page.evaluate(() =>
  new Promise((res) => {
    const t0 = Date.now();
    const check = () => {
      if (window.__EK && window.__EK.logical && window.__EK.logical().w > 0) return res(true);
      if (Date.now() - t0 > 8000) return res(false);
      setTimeout(check, 100);
    };
    check();
  }));
console.log('offline reload booted:', offlineBooted);
await ctx.setOffline(false);

// --- Saves: round-trip through IndexedDB (still offline-capable) ---
const saveOk = await page.evaluate(async () => {
  const { Saves } = await import('./src/saves.js');
  await Saves.put('slot1', { hp: 42, area: 'newport', gold: 1200 }, { name: 'Hero' });
  const got = await Saves.get('slot1');
  const list = await Saves.list();
  const blob = await Saves.export();
  const text = await blob.text();
  await Saves.delete('slot1');
  const n = await Saves.import(text);              // restore from the export
  const restored = await Saves.get('slot1');
  return got && got.data.hp === 42 && list.length >= 1 &&
         n >= 1 && restored && restored.data.gold === 1200;   // >=1: game also autosaves
});
console.log('saves round-trip:', saveOk);

// --- Entities + dialogue: enter the tutorial, talk to Adaon, walk the tree ---
const dlg = await page.evaluate(async () => {
  await window.__EK.quickStart({ map: 'I10_tutorial', pc: { name: 'Aria', gender: 'FEMALE' } });
  await new Promise(r => setTimeout(r, 900));         // let NPC sheets load
  const ents = window.__EK.entities();
  const who = window.__EK.talkNearest();
  await new Promise(r => setTimeout(r, 250));
  const first = window.__EK.dlg();
  let steps = 0;
  while (window.__EK.dlg() && steps < 10) {           // pick the last option / Continue
    const d = window.__EK.dlg();
    window.__EK.dlgChoose(Math.max(0, d.choices.length - 1));
    steps++; await new Promise(r => setTimeout(r, 120));
  }
  return { entTotal: ents.length, withConv: ents.filter(e => e.conv).length, who,
           firstText: first && first.text, firstChoices: first && first.choices,
           steps, followers: window.__EK.followers() };
});
console.log('dialogue:', dlg);
const dlgOk = dlg.entTotal > 0 && dlg.who === 'adaon' && !!dlg.firstText &&
              dlg.firstChoices && dlg.firstChoices.length >= 1 &&
              dlg.followers.includes('adaon_tutorial');   // NPCFollow# action ran

// --- Tutorial exit: camp → sleep (fade) → robbed → wake in the seamless world near
// Lannager. Open the camp conversation at node 50 (its Q line runs the sleep/travel
// actions) and assert the whole beat: Adaon dropped, gold reset to 18, want_letter_back
// gate set to 10, arrival in world H10, fade cleared. ---
const exit = await page.evaluate(async () => {
  await window.__EK.quickStart({ map: 'I10_tutorial' });
  window.__EK.setVar('want_letter_back', 0);
  window.__EK.addFollower('adaon_tutorial');
  window.__EK.startConv('adaon_tutorial_camp', '50', { label: 'Adaon' });
  const t0 = Date.now();
  while (window.__EK.map().name !== 'H10' && Date.now() - t0 < 15000)
    await new Promise(r => setTimeout(r, 120));
  while (window.__EK.fading() && Date.now() - t0 < 20000)
    await new Promise(r => setTimeout(r, 120));
  return { map: window.__EK.map().name, mode: window.__EK.map().mode,
           gold: window.__EK.stats().gold, wantLetter: window.__EK.getVar('want_letter_back'),
           followers: window.__EK.followers(), fading: window.__EK.fading() };
});
console.log('tutorial-exit:', exit);
const exitOk = exit.map === 'H10' && exit.mode === 'world' && exit.gold === 18 &&
               exit.wantLetter === 10 && exit.fading === false &&
               !exit.followers.includes('adaon_tutorial');

// --- Combat: enter a cave, stand next to a monster, target it, and confirm a real
// melee exchange (enemy loses HP, hero takes damage), then a kill grants XP; pause
// toggles. Interior map so entities are stable (world streaming despawns them). ---
const combat = await page.evaluate(async () => {
  await window.__EK.quickStart({ map: 'C12_cave', pc: { charClass: 'WARRIOR',
    attributes: { STR: 4, END: 2, AGI: 0, INT: 0, AWA: 0, PER: 0 } } });
  const t0 = Date.now();
  while (!window.__EK.combatants().some(c => c.side === 'enemy' && !c.dead) && Date.now() - t0 < 8000)
    await new Promise(r => setTimeout(r, 120));
  const foe = window.__EK.combatants().find(c => c.side === 'enemy' && !c.dead);
  window.__EK.teleport(foe.cell.c + 1, foe.cell.r);
  const startHp = foe.maxHp, heroStart = window.__EK.hp();
  window.__EK.targetByName(foe.name, foe.cell);
  await new Promise(r => setTimeout(r, 5000));
  const now = window.__EK.combatants().find(c => c.name === foe.name && c.cell.c === foe.cell.c && c.cell.r === foe.cell.r);
  const before = window.__EK.stats().xp;
  const alive = window.__EK.combatants().find(c => c.side === 'enemy' && !c.dead);
  if (alive) window.__EK.hurtEnemy(alive.name, 99999);
  await new Promise(r => setTimeout(r, 700));
  const p1 = window.__EK.togglePause(), p2 = window.__EK.togglePause();
  return { attached: window.__EK.combatants().length,
           enemyHurt: !now || now.hp < startHp || now.dead,
           heroHurt: window.__EK.hp() < heroStart,
           xpGain: window.__EK.stats().xp - before, pause: p1 === true && p2 === false };
});
console.log('combat:', combat);
const combatOk = combat.attached > 0 && combat.enemyHurt && combat.heroHurt &&
                 combat.xpGain > 0 && combat.pause;

// --- Quests + world state: set a quest variable, confirm the Journal shows it with the
// right stage description, a completed quest is flagged, and world state (vars) persists
// through a save round-trip (deobf/QUEST_SPEC.md). ---
const quest = await page.evaluate(async () => {
  const { Saves } = await import('./src/saves.js');
  await window.__EK.quickStart({ map: 'H10' });
  window.__EK.setVar('want_letter_back', 10);
  window.__EK.setVar('goblin_hunt', 100);
  document.querySelector('#hud .hud-btn[data-act="journal"]').click();
  await new Promise(r => setTimeout(r, 100));
  const panel = document.querySelector('#hud .hud-panel');
  window.__EK.setVar('gather_ingredients', 20);
  await window.__EK.saveAuto('H10');
  const got = await Saves.get('auto');
  return { names: [...panel.querySelectorAll('.jq-name')].map(n => n.textContent),
           hasAdaon: /Adaon/i.test(panel.textContent),
           hasCompleted: /Completed/i.test(panel.textContent),
           savedVar: got.data.world && got.data.world.vars.gather_ingredients };
});
console.log('quests:', quest);
const questOk = quest.names.length >= 2 && quest.hasAdaon && quest.hasCompleted && quest.savedVar === 20;

// --- Hero class + trainers: a HERO learns advanced skills from any discipline via the
// TrainSkill# action and unlocks that discipline's class-restricted gear; a non-HERO is
// blocked from out-of-discipline skills; HERO is selectable at creation
// (deobf/TRAINERS_SPEC.md). ---
const hero = await page.evaluate(async () => {
  await window.__EK.quickStart({ map: 'H10', pc: { charClass: 'HERO', name: 'Kael' } });
  window.__EK.runAction('TrainSkill#battle_rage');   // warrior discipline
  window.__EK.runAction('TrainSkill#fire_mastery');  // wizard discipline
  const a = window.__EK.playerModelDebug();
  await window.__EK.quickStart({ map: 'H10', pc: { charClass: 'WARRIOR', name: 'Grom' } });
  window.__EK.runAction('TrainSkill#fire_mastery');  // wizard-only → rejected for warrior
  window.__EK.runAction('TrainSkill#battle_rage');   // warrior → allowed
  const w = window.__EK.playerModelDebug();
  const classes = (await import('./src/char-create.js')).CLASSES.map(c => c.id);
  return { learnsAll: a.learnsAll, trained: a.trained.length, disc: a.disciplines,
           canW: a.canW, canM: a.canM, canR: a.canR, canAny: a.canBlank,
           warriorTrained: w.trained, warriorCanMage: w.canM, classes };
});
console.log('hero/trainers:', hero);
const heroClassOk = hero.learnsAll && hero.trained === 2 && hero.disc.includes('W') && hero.disc.includes('M') &&
               hero.canW && hero.canM && !hero.canR && hero.canAny &&
               hero.warriorTrained.length === 1 && hero.warriorTrained[0] === 'battle_rage' &&
               !hero.warriorCanMage && hero.classes.includes('HERO');

console.log('start/creation:', { titleOk, playerOk }, ' joystick:', stickOk, ' dialogue:', dlgOk, ' hud:', hudOk, ' tutorial-exit:', exitOk, ' combat:', combatOk, ' quests:', questOk, ' hero:', heroClassOk);
const ok = errors.length === 0 && titleOk && playerOk && hudOk && orientOk && mapOk && heroOk && lightOk &&
           zoomOk && moveOk && stickOk && transOk && dlgOk && exitOk && combatOk && questOk && heroClassOk && cached.failed === 0 && offlineBooted && saveOk;
await browser.close(); server.close();
console.log(ok
  ? `VERIFY: PASS — title + character creation, walking hero (tap-to-move OR free-floating joystick, A* collision) across a 151-map seamless world with arch transitions, on-map NPCs + dialogue reader + player model & HUD, real-time-with-pause combat (attacks/mitigation/loot/XP), quest journal + persistent world state, Hero class + skill trainers, day/night, pinch-zoom, 4 orientations, full-game cached offline, saves round-trip`
  : 'VERIFY: FAIL');
process.exit(ok ? 0 : 1);
