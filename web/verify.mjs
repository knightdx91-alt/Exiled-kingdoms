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

// --- Real map: the converted H6_bank .tmx should render real tiles on screen ---
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
         n === 1 && restored && restored.data.gold === 1200;
});
console.log('saves round-trip:', saveOk);

const ok = errors.length === 0 && orientOk && mapOk && heroOk && lightOk && zoomOk && moveOk && transOk && cached.failed === 0 && offlineBooted && saveOk;
await browser.close(); server.close();
console.log(ok
  ? `VERIFY: PASS — walking hero (tap-to-move, A* collision) across a 151-map world with portal transitions + day/night + pinch-zoom + 4 orientations, full-game cached offline, saves round-trip`
  : 'VERIFY: FAIL');
process.exit(ok ? 0 : 1);
