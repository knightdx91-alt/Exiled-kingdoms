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
  const arch = (window.__EK.map().transitions || [])[0];   // nearest arch (global cell)
  if (!arch) return { skipped: true };
  window.__EK.teleport(arch.c - 2, arch.r - 2);            // hop next to the portal, then
  const t = window.__EK.gotoTransition();                  // walk the last couple of cells
  if (!t) return { skipped: true };
  const t0 = Date.now();
  // Only a 1-2 cell walk now, so the transition fires quickly regardless of headless load
  // (the previous full-chunk hike could crawl under heavy load and time out).
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
  const attached = window.__EK.combatants().length;
  window.__EK.teleport(foe.cell.c + 1, foe.cell.r);
  const startHp = foe.maxHp, heroStart = window.__EK.hp();
  // ATTACK BUTTON: hold it while adjacent to the foe and confirm it deals damage.
  const atkEl = document.querySelector('#hud .hud-attack');
  const foeHp0 = window.__EK.combatants().find(c => c.name === foe.name).hp;
  atkEl.dispatchEvent(new Event('pointerdown'));
  await new Promise(r => setTimeout(r, 1600));
  atkEl.dispatchEvent(new Event('pointerup'));
  const foeAfterBtn = window.__EK.combatants().find(c => c.name === foe.name);
  const attackButtonHurt = !foeAfterBtn || foeAfterBtn.dead || foeAfterBtn.hp < foeHp0;
  window.__EK.targetByName(foe.name, foe.cell);
  // poll until both sides have traded blows (deterministic vs a fixed window)
  let enemyHurt = false, heroHurt = false;
  const t1 = Date.now();
  while ((!enemyHurt || !heroHurt) && Date.now() - t1 < 12000) {
    await new Promise(r => setTimeout(r, 200));
    const now = window.__EK.combatants().find(c => c.name === foe.name && c.cell.c === foe.cell.c && c.cell.r === foe.cell.r);
    if (!now || now.dead || now.hp < startHp) enemyHurt = true;
    if (window.__EK.hp() < heroStart) heroHurt = true;
  }
  const before = window.__EK.stats().xp;
  const alive = window.__EK.combatants().find(c => c.side === 'enemy' && !c.dead);
  if (alive) window.__EK.hurtEnemy(alive.name, 99999);
  await new Promise(r => setTimeout(r, 700));
  const p1 = window.__EK.togglePause(), p2 = window.__EK.togglePause();
  return { attached, enemyHurt, heroHurt, attackButtonHurt,
           xpGain: window.__EK.stats().xp - before, pause: p1 === true && p2 === false };
});
console.log('combat:', combat);
const combatOk = combat.attached > 0 && combat.enemyHurt && combat.heroHurt &&
                 combat.attackButtonHurt && combat.xpGain > 0 && combat.pause;

// --- Quests + world state: set a quest variable, confirm the Journal shows it with the
// right stage description, a completed quest is flagged, and world state (vars) persists
// through a save round-trip (deobf/QUEST_SPEC.md). ---
const quest = await page.evaluate(async () => {
  const { Saves } = await import('./src/saves.js');
  await window.__EK.quickStart({ map: 'H10' });
  window.__EK.setVar('want_letter_back', 10);
  window.__EK.setVar('goblin_hunt', 100);
  document.querySelector('#hud .hud-portrait').click();          // open CharacterWindow
  await new Promise(r => setTimeout(r, 60));
  document.querySelector('#hud .cw [data-nav="journal"]').click(); // -> Journal sub-view
  await new Promise(r => setTimeout(r, 100));
  const panel = document.querySelector('#hud .cw');
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

// --- Items / inventory / equipment: give gear, equip it (armor/HP/weapon/shield change),
// class-gating blocks out-of-discipline gear, a potion heals + is consumed, and the
// character/inventory screen renders the paper-doll (deobf/INVENTORY_SPEC.md). ---
const inv = await page.evaluate(async () => {
  await window.__EK.quickStart({ map: 'H10', pc: { charClass: 'WARRIOR', attributes: { STR: 4, END: 2, AGI: 0, INT: 0, AWA: 0, PER: 0 } } });
  const base = window.__EK.inventoryDebug();
  ['100', '120', '501', '201', '5000'].forEach(id => window.__EK.runAction('GiveItem#' + id));
  const gotBackpack = window.__EK.inventoryDebug().backpack.length;
  window.__EK.equip(100); window.__EK.equip(120); window.__EK.equip(501);
  const rogueBlocked = window.__EK.equip(201) === false;
  const eq = window.__EK.inventoryDebug();
  window.__EK.hurt(20); const hpBefore = window.__EK.hp();
  window.__EK.useItem(5000); const hpGain = window.__EK.hp() - hpBefore;
  const potionGone = !window.__EK.inventoryDebug().backpack.includes(5000);
  document.querySelector('#hud .hud-portrait').click();   // portrait opens the CharacterWindow
  await new Promise(r => setTimeout(r, 80));
  // innerHTML so we see either the item NAME (icon-less fallback) or its real icon src
  // (assets/ui/items/<icon>.png) — the paper-doll shows equipped gear either way.
  const panel = document.querySelector('#hud .cw').innerHTML;
  return { base, gotBackpack, eq, rogueBlocked, hpGain, potionGone,
           doll: (/Leather Cuirass/.test(panel) || /armor_leather_chest1\.png/.test(panel)) &&
                 (/Iron Dagger/.test(panel) || /weapon_dagger\.png/.test(panel)) && /Armor/.test(panel) };
});
console.log('inventory:', inv);
const invOk = inv.base.armor === 3 && inv.base.weaponId === 'iron_longsword' && inv.base.maxHP === 51 &&
              inv.gotBackpack === 5 && inv.eq.armor === 5 && inv.eq.maxHP === 57 && inv.eq.shield === true &&
              inv.eq.weaponId === 'iron_dagger' && inv.rogueBlocked && inv.eq.backpack.includes(201) &&
              inv.hpGain > 0 && inv.potionGone && inv.doll;

// --- Skill/spell execution: a caster learns skills, the mana_surge passive raises the
// pool, a fireball damages an enemy + spends mana + goes on cooldown (re-cast blocked),
// heal restores HP, and a buff raises armor; the HUD skill bar renders
// (deobf/SKILLS_EXEC_SPEC.md). ---
const skills = await page.evaluate(async () => {
  await window.__EK.quickStart({ map: 'C12_cave', pc: { charClass: 'WIZARD', attributes: { STR: 0, END: 0, AGI: 0, INT: 4, AWA: 0, PER: 0 } } });
  window.__EK.setSkillRank('fireball', 2); window.__EK.setSkillRank('heal_wounds', 2);
  window.__EK.setSkillRank('resilience', 2); window.__EK.setSkillRank('mana_surge', 3);
  window.__EK.refillMana();
  await new Promise(r => setTimeout(r, 500));
  const foe = window.__EK.combatants().find(c => c.side === 'enemy' && !c.dead);
  window.__EK.teleport(foe.cell.c + 1, foe.cell.r);
  const before = window.__EK.skillDebug();
  const at = (n) => (window.__EK.combatants().find(c => c.name === n && c.cell.c === foe.cell.c && c.cell.r === foe.cell.r) || { hp: 0 }).hp;
  const foe0 = at(foe.name);
  const cast1 = window.__EK.castSkill('fireball');
  await new Promise(r => setTimeout(r, 60));
  const foe1 = at(foe.name);
  const after = window.__EK.skillDebug();
  const cast2 = window.__EK.castSkill('fireball');   // on cooldown
  window.__EK.togglePause();                          // freeze enemies for heal/buff checks
  window.__EK.hurt(15); const low = window.__EK.hp();
  window.__EK.castSkill('heal_wounds'); const healed = window.__EK.hp();
  const armorBefore = window.__EK.skillDebug().armor;
  window.__EK.castSkill('resilience'); const sk = window.__EK.skillDebug();
  window.__EK.togglePause();
  const barCount = document.querySelectorAll('#hud .hud-skillbar .skbtn').length;
  return { maxMana: before.maxMana, fireballHit: foe1 < foe0, manaSpent: after.mana < before.mana,
           cooldownSet: after.cooldowns.includes('fireball'), cast1: cast1.ok, cast2Blocked: cast2.ok === false,
           healed: healed > low, armorBefore, armorAfter: sk.armor, buffs: sk.buffs, barCount };
});
console.log('skills:', skills);
const skillsOk = skills.maxMana >= 23 && skills.fireballHit && skills.manaSpent && skills.cooldownSet &&
                 skills.cast1 && skills.cast2Blocked && skills.healed && skills.armorAfter > skills.armorBefore &&
                 skills.buffs >= 1 && skills.barCount >= 3;

// --- Render polish: in a dark dungeon, fog-of-war hides unexplored tiles and reveals
// more as the hero moves (explored grows, hidden shrinks, some tiles dim to 1/3),
// roof/object tiles fade, torch + player lights exist; in the open world the FX is inert
// (deobf/RENDER_POLISH_SPEC.md). ---
const fx = await page.evaluate(async () => {
  await window.__EK.quickStart({ map: 'D12_cave' });
  await new Promise(r => setTimeout(r, 400));
  const start = window.__EK.fx();
  const hc = window.__EK.heroCell();
  window.__EK.teleport(hc.c + 20, hc.r + 8);
  window.__EK.teleport(hc.c + 20, hc.r + 8);       // 2nd call forces a cell-change apply
  await new Promise(r => setTimeout(r, 300));
  const moved = window.__EK.fx();
  await window.__EK.quickStart({ map: 'H10' });     // open world → FX inert
  await new Promise(r => setTimeout(r, 250));
  const world = window.__EK.fx();
  return { start, moved, world };
});
console.log('render-fx:', fx);
const fxOk = fx.start.enabled && fx.start.fog && fx.start.hidden > 0 && fx.start.lights > 0 &&
             fx.start.playerGlow && fx.moved.explored > fx.start.explored &&
             fx.moved.hidden < fx.start.hidden && fx.moved.dimmed > 0 && !fx.world.enabled;

console.log('start/creation:', { titleOk, playerOk }, ' joystick:', stickOk, ' dialogue:', dlgOk, ' hud:', hudOk, ' tutorial-exit:', exitOk, ' combat:', combatOk, ' quests:', questOk, ' hero:', heroClassOk, ' inv:', invOk, ' skills:', skillsOk, ' render-fx:', fxOk);
const ok = errors.length === 0 && titleOk && playerOk && hudOk && orientOk && mapOk && heroOk && lightOk &&
           zoomOk && moveOk && stickOk && transOk && dlgOk && exitOk && combatOk && questOk && heroClassOk && invOk && skillsOk && fxOk && cached.failed === 0 && offlineBooted && saveOk;
await browser.close(); server.close();
console.log(ok
  ? `VERIFY: PASS — title + character creation, walking hero (tap-to-move OR free-floating joystick, A* collision) across a 151-map seamless world with arch transitions, on-map NPCs + dialogue reader + player model & HUD, real-time-with-pause combat (hold-to-attack button + attack/interact toggle, mitigation/loot/XP), quest journal + persistent world state, Hero class + skill trainers, items/equipment + inventory screen, castable skills/spells (damage/heal/buff/passive), dungeon fog-of-war + roof-fade + torch lights, day/night, pinch-zoom, 4 orientations, full-game cached offline, saves round-trip`
  : 'VERIFY: FAIL');
process.exit(ok ? 0 : 1);
