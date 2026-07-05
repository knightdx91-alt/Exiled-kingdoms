// Extract EK's libGDX Scene2D skin into web-usable assets so the UI can look EXACTLY
// like the base game. Parses uiskin.atlas (regions + 9-patch splits), crops each region
// out of uiskin.png via a headless-Chrome canvas, and writes:
//   web/assets/ui/skin/<region>.png         one PNG per region
//   web/assets/ui/skin.json                  { region: { w, h, split:[L,R,T,B]|null } }
// 9-patch `split` maps directly to CSS border-image-slice, so window/button frames
// stretch exactly like the game's.
//
// Usage:
//   node tools/extract-skin.mjs                                  # default: uiskin -> assets/ui/skin
//   node tools/extract-skin.mjs <atlas> <pngName> <outDir> [regexFilter]
//     e.g. ...  ../recovered_mods/.../ui_icons.pack ui_icons.png assets/ui/creation '^(class_|male|female|easy|normal|hard|ironman|story|locked_gray)'
import { chromium } from 'playwright';
import http from 'node:http'; import fs from 'node:fs'; import path from 'node:path';

const [, , atlasArg, pngArg, outArg, filterArg] = process.argv;
const UI = outArg || 'assets/ui/skin';
const ATLAS = atlasArg || `${UI}/uiskin.atlas`;
const PNG = pngArg || 'uiskin.png';
const FILTER = filterArg ? new RegExp(filterArg) : null;
fs.mkdirSync(UI, { recursive: true });
// the PNG must be reachable on the http origin below; copy it into the out dir
if (!fs.existsSync(`${UI}/${PNG}`))
  fs.copyFileSync(path.join(path.dirname(ATLAS), PNG), `${UI}/${PNG}`);
const atlas = fs.readFileSync(ATLAS, 'utf8');

// --- parse the libGDX atlas (CRLF-safe): a non-indented no-colon line is a region name;
//     indented `key: v0, v1, …` lines are its properties; header lines are ignored. ---
const regions = {};
let cur = null;
for (const raw of atlas.split('\n')) {
  const line = raw.replace(/\r$/, '');
  if (!line) continue;
  if (/^\S/.test(line) && !line.includes(':')) {
    cur = line === 'uiskin.png' ? null : line;
    if (cur) regions[cur] = {};
    continue;
  }
  const m = line.match(/^\s+([a-z]+):\s*(.+)$/);
  if (m && cur) regions[cur][m[1]] = m[2].split(',').map(s => +s.trim());
}
const list = Object.entries(regions)
  .filter(([name, v]) => v.xy && v.size && (!FILTER || FILTER.test(name)))
  .map(([name, v]) => ({
    name, x: v.xy[0], y: v.xy[1], w: v.size[0], h: v.size[1],
    split: v.split || null,                          // [left, right, top, bottom]
  }));
console.log(`atlas: ${list.length} regions (${list.filter(r => r.split).length} 9-patch)`);

// --- serve web/ and crop each region from uiskin.png via canvas ---
const ROOT = path.resolve('.');
const server = http.createServer((req, res) => {
  const f = path.join(ROOT, decodeURIComponent(req.url.split('?')[0]));
  if (!fs.existsSync(f)) { res.writeHead(404); return res.end(); }
  res.writeHead(200); fs.createReadStream(f).pipe(res);
});
await new Promise(r => server.listen(0, r));
const port = server.address().port;

const srcUrl = `http://localhost:${port}/${UI}/${PNG}`;
const browser = await chromium.launch({ executablePath: '/opt/pw-browsers/chromium' });
const page = await browser.newPage();
await page.goto(srcUrl);
const pngs = await page.evaluate(async ({ src, list }) => {
  const img = new Image(); img.src = src;
  await img.decode();
  const out = {};
  for (const r of list) {
    const c = document.createElement('canvas'); c.width = r.w; c.height = r.h;
    const ctx = c.getContext('2d');
    ctx.drawImage(img, r.x, r.y, r.w, r.h, 0, 0, r.w, r.h);
    out[r.name] = c.toDataURL('image/png').split(',')[1];
  }
  return out;
}, { src: srcUrl, list });
await browser.close(); server.close();

const manifest = {};
for (const r of list) {
  fs.writeFileSync(`${UI}/${r.name}.png`, Buffer.from(pngs[r.name], 'base64'));
  manifest[r.name] = { w: r.w, h: r.h, split: r.split };
}
fs.writeFileSync(`${UI}/manifest.json`, JSON.stringify(manifest, null, 0));
fs.rmSync(`${UI}/${PNG}`, { force: true });          // sheet no longer needed once cropped
console.log(`wrote ${list.length} region PNGs + ${UI}/manifest.json`);
