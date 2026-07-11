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

// --- parse the libGDX atlas (CRLF-safe). A non-indented no-colon line is either a PAGE
//     image (ends .png → starts a new page) or a REGION name; indented `key: v…` lines
//     are that region's properties. Supports multi-page atlases (items.pack has several
//     pages) and both the old (xy/size) and new (bounds) region formats + rotate. ---
const regions = {};
let cur = null, curPage = null;
for (const raw of atlas.split('\n')) {
  const line = raw.replace(/\r$/, '');
  if (!line.trim()) { continue; }
  if (/^\S/.test(line) && !line.includes(':')) {
    if (/\.png$/i.test(line)) { curPage = line; cur = null; continue; }   // page image
    cur = line; regions[cur] = { page: curPage, rotate: false, split: null };
    continue;
  }
  const m = line.match(/^\s+([a-zA-Z]+):\s*(.+)$/);
  if (!m || !cur) continue;
  const k = m[1], v = m[2].trim();
  if (k === 'rotate') regions[cur].rotate = (v === 'true' || v === '90');
  else regions[cur][k] = v.split(',').map(s => +s.trim());
}
// Normalise xy/size — the newer format packs both into `bounds: x,y,w,h`.
const list = Object.entries(regions).map(([name, v]) => {
  let x, y, w, h;
  if (v.bounds) { [x, y, w, h] = v.bounds; }
  else if (v.xy && v.size) { [x, y] = v.xy; [w, h] = v.size; }
  else return null;
  return { name, page: v.page, x, y, w, h, rotate: !!v.rotate, split: v.split || null };
}).filter(r => r && (!FILTER || FILTER.test(r.name)));
console.log(`atlas: ${list.length} regions across ${new Set(list.map(r => r.page)).size} page(s)` +
            ` (${list.filter(r => r.split).length} 9-patch)`);

// --- serve web/ and crop each region from its page image via canvas ---
const ROOT = path.resolve('.');
const server = http.createServer((req, res) => {
  const f = path.join(ROOT, decodeURIComponent(req.url.split('?')[0]));
  if (!fs.existsSync(f)) { res.writeHead(404); return res.end(); }
  res.writeHead(200); fs.createReadStream(f).pipe(res);
});
await new Promise(r => server.listen(0, r));
const port = server.address().port;

const browser = await chromium.launch({ executablePath: '/opt/pw-browsers/chromium' });
const page = await browser.newPage();
const pngs = {};
const byPage = {};
for (const r of list) (byPage[r.page || PNG] = byPage[r.page || PNG] || []).push(r);
const tmp = [];
for (const [pageName, rs] of Object.entries(byPage)) {
  const localPng = `${UI}/__page_${path.basename(pageName)}`;
  fs.copyFileSync(path.join(path.dirname(ATLAS), pageName), localPng);
  tmp.push(localPng);
  const src = `http://localhost:${port}/${localPng}`;
  await page.goto(src);
  const out = await page.evaluate(async ({ src, rs }) => {
    const img = new Image(); img.src = src; await img.decode();
    const res = {};
    for (const r of rs) {
      // packed size in the atlas; when rotated the source is stored turned 90°.
      const pw = r.w, ph = r.h;
      const ow = r.rotate ? ph : pw, oh = r.rotate ? pw : ph;   // upright (original) size
      const c = document.createElement('canvas'); c.width = ow; c.height = oh;
      const ctx = c.getContext('2d');
      if (r.rotate) {
        ctx.translate(ow / 2, oh / 2); ctx.rotate(-Math.PI / 2);
        ctx.drawImage(img, r.x, r.y, pw, ph, -pw / 2, -ph / 2, pw, ph);
      } else {
        ctx.drawImage(img, r.x, r.y, pw, ph, 0, 0, ow, oh);
      }
      res[r.name] = c.toDataURL('image/png').split(',')[1];
    }
    return res;
  }, { src, rs });
  Object.assign(pngs, out);
}
await browser.close(); server.close();

// Merge into any existing manifest so several packs can target one dir (e.g. objects).
const manifest = fs.existsSync(`${UI}/manifest.json`)
  ? JSON.parse(fs.readFileSync(`${UI}/manifest.json`, 'utf8')) : {};
for (const r of list) {
  if (!pngs[r.name]) continue;
  fs.writeFileSync(`${UI}/${r.name}.png`, Buffer.from(pngs[r.name], 'base64'));
  const ow = r.rotate ? r.h : r.w, oh = r.rotate ? r.w : r.h;
  manifest[r.name] = { w: ow, h: oh, split: r.split };
}
fs.writeFileSync(`${UI}/manifest.json`, JSON.stringify(manifest, null, 0));
for (const t of tmp) fs.rmSync(t, { force: true });   // page sheets no longer needed
fs.rmSync(`${UI}/${PNG}`, { force: true });
console.log(`wrote ${Object.keys(manifest).length} region PNGs + ${UI}/manifest.json`);
