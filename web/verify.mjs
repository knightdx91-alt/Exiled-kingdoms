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
const ok = errors.length === 0 && results.every(r => r.inputHitsToken);
await browser.close(); server.close();
console.log(ok ? 'VERIFY: PASS — all 4 orientations render and accept input' : 'VERIFY: FAIL');
process.exit(ok ? 0 : 1);
