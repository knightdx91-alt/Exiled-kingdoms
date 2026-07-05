// Converts an Exiled Kingdoms .tmx map into a compact JSON the web renderer can
// draw, and copies the tileset PNGs it needs into web/assets/tmx/.
//
// EK maps are ISOMETRIC (tilewidth 64, tileheight 32). Layer tile data is
// base64 + gzip. Tilesets are either inline (<tileset ...><image .../></tileset>)
// or external (<tileset source="foo.tsx"/>). External .tsx files hold the same
// <image> element. Tile GIDs are global; each tileset owns [firstgid, firstgid+count).
//
// Usage: node tools/tmx2json.mjs <path/to/map.tmx> [outName]
//   -> web/assets/tmx/<outName>.json  + the referenced *.png
import fs from 'node:fs';
import path from 'node:path';
import zlib from 'node:zlib';

const TMX = process.argv[2];
if (!TMX) { console.error('usage: tmx2json.mjs <map.tmx> [outName]'); process.exit(1); }
const outName = process.argv[3] || path.basename(TMX).replace(/\.tmx$/i, '');
const SRC_DIR = path.dirname(TMX);
const OUT_DIR = path.resolve('assets/tmx');
fs.mkdirSync(OUT_DIR, { recursive: true });

const attr = (tag, name) => {
  const m = tag.match(new RegExp(`${name}="([^"]*)"`));
  return m ? m[1] : undefined;
};

// Resolve a tileset element to { image, imagew, imageh, tilew, tileh } — following
// an external .tsx if needed. Returns image as a basename (copied flat into assets).
// Local tile ids that block movement — a tile with a "blocked" or "obstacle" property.
// (GameMap.v(): objects-layer tiles with those props are non-walkable; see ENGINE_SPEC.)
function blockedIds(tsXml) {
  const ids = [];
  const re = /<tile id="(\d+)">([\s\S]*?)<\/tile>/g;
  let m;
  while ((m = re.exec(tsXml)))
    if (/name="(blocked|obstacle)"/.test(m[2])) ids.push(+m[1]);
  return ids;
}

function resolveTileset(tag, blockXml) {
  const source = attr(tag, 'source');
  let name, tilew, tileh, imgTag, tsXml;
  if (source) {                                    // external .tsx
    const tsxPath = path.join(SRC_DIR, source);
    tsXml = fs.readFileSync(tsxPath, 'utf8');
    const tsTag = tsXml.match(/<tileset[^>]*>/)[0];
    name = attr(tsTag, 'name');
    tilew = +attr(tsTag, 'tilewidth'); tileh = +attr(tsTag, 'tileheight');
    imgTag = tsXml.match(/<image[^>]*>/)[0];
  } else {                                          // inline tileset
    name = attr(tag, 'name');
    tilew = +attr(tag, 'tilewidth'); tileh = +attr(tag, 'tileheight');
    tsXml = blockXml;                               // the full <tileset>…</tileset> block
    imgTag = blockXml.match(/<image[^>]*>/)[0];
  }
  const imgSrc = attr(imgTag, 'source');
  const imagew = +attr(imgTag, 'width'), imageh = +attr(imgTag, 'height');
  // copy the PNG (from the .tsx's dir for externals, else the map dir)
  const pngFrom = path.join(source ? path.dirname(path.join(SRC_DIR, source)) : SRC_DIR, imgSrc);
  const pngName = path.basename(imgSrc);
  fs.copyFileSync(pngFrom, path.join(OUT_DIR, pngName));
  return { name, image: pngName, imagew, imageh, tilew, tileh,
           columns: Math.floor(imagew / tilew), blocked: blockedIds(tsXml) };
}

const xml = fs.readFileSync(TMX, 'utf8');
const mapTag = xml.match(/<map[^>]*>/)[0];
const map = {
  width: +attr(mapTag, 'width'), height: +attr(mapTag, 'height'),
  tilew: +attr(mapTag, 'tilewidth'), tileh: +attr(mapTag, 'tileheight'),
  orientation: attr(mapTag, 'orientation'),
};

// Map-level <properties> (only the top block, before any layer). maxlight is the
// ambient light level (0-100); the base renderer multiplies the map by it. Absent
// => fully lit. music is the area track.
const mapProps = xml.slice(0, xml.search(/<(layer|tileset)\b/));
const prop = (name) => {
  const m = mapProps.match(new RegExp(`<property name="${name}" value="([^"]*)"`));
  return m ? m[1] : undefined;
};
// maxlight absent => 0 in the base game (field f2424i), which routes lighting to the
// day/night path for `outdoor` maps. Don't default it to full-bright.
map.maxlight = prop('maxlight') !== undefined ? +prop('maxlight') : 0;
map.outdoor = /<property name="outdoor"/.test(mapProps);   // enables day/night cycle
map.music = prop('music');

// Tilesets (in document order so firstgid lookup is simple).
const tilesets = [];
const tsRe = /<tileset\b[^>]*?(?:\/>|>)/g;
let m;
while ((m = tsRe.exec(xml))) {
  const tag = m[0];
  const firstgid = +attr(tag, 'firstgid');
  // for an inline (non-self-closing) tileset, pass the whole <tileset>…</tileset> block
  const end = tag.endsWith('/>') ? m.index + tag.length
                                 : xml.indexOf('</tileset>', m.index) + 10;
  const block = xml.slice(m.index, end);
  const ts = resolveTileset(tag, block);
  tilesets.push({ firstgid, ...ts });
}
tilesets.sort((a, b) => a.firstgid - b.firstgid);

// Global gids that block movement (tileset.blocked local ids + firstgid).
map.blockedGids = [];
for (const ts of tilesets)
  for (const id of ts.blocked) map.blockedGids.push(ts.firstgid + id);
for (const ts of tilesets) delete ts.blocked;   // keep tileset entries lean

// Layers: decode base64 + gzip -> Int array of global tile IDs (0 = empty).
const GID_MASK = 0x1FFFFFFF;                          // strip Tiled flip flags
const layers = [];
const lyRe = /<layer\b([^>]*)>[\s\S]*?<data([^>]*)>([\s\S]*?)<\/data>/g;
while ((m = lyRe.exec(xml))) {
  const lyTag = m[1];
  const comp = attr(`<data${m[2]}>`, 'compression');   // gzip | zlib | (none)
  const raw = Buffer.from(m[3].trim(), 'base64');
  const buf = comp === 'gzip' ? zlib.gunzipSync(raw)
            : comp === 'zlib' ? zlib.inflateSync(raw)
            : raw;
  const n = buf.length / 4;
  const data = new Array(n);
  for (let i = 0; i < n; i++) data[i] = buf.readUInt32LE(i * 4) & GID_MASK;
  layers.push({
    name: attr(`<layer ${lyTag}>`, 'name'),
    visible: attr(`<layer ${lyTag}>`, 'visible') !== '0',
    data,
  });
}

// Object layer: transitions (portals) + entry points. EK stores object x/y in a
// 32px world grid, so cell = round(coord/32). A `transition` links to area_id at
// entry_id; an `entry` (entry_id) is an arrival point. Edge exits come from map props.
const transitions = [], entries = {};
const objRe = /<object\b([^>]*?)(?:\/>|>([\s\S]*?)<\/object>)/g;
while ((m = objRe.exec(xml))) {
  const head = m[1], body = m[2] || '';
  const type = attr(`<o ${head}>`, 'type');
  if (type !== 'transition' && type !== 'entry') continue;
  const c = Math.round(+attr(`<o ${head}>`, 'x') / 32);
  const r = Math.round(+attr(`<o ${head}>`, 'y') / 32);
  const p = (n) => { const mm = body.match(new RegExp(`name="${n}" value="([^"]*)"`)); return mm ? mm[1] : undefined; };
  if (type === 'transition') transitions.push({ c, r, area: p('area_id'), entry: p('entry_id') });
  else entries[p('entry_id')] = { c, r };
}
map.transitions = transitions;
map.entries = entries;
map.edgeExits = { n: prop('exit_n'), s: prop('exit_s'), e: prop('exit_e'), w: prop('exit_w') };

const out = { name: outName, ...map, tilesets, layers };
const outPath = path.join(OUT_DIR, `${outName}.json`);
fs.writeFileSync(outPath, JSON.stringify(out));
const nonEmpty = layers.map(l => `${l.name}:${l.data.filter(Boolean).length}`).join(' ');
console.log(`wrote ${outPath}`);
console.log(`  ${map.width}x${map.height} iso ${map.tilew}x${map.tileh}, ${tilesets.length} tilesets, ${layers.length} layers`);
console.log(`  tiles per layer: ${nonEmpty}`);
console.log(`  PNGs: ${tilesets.map(t => t.image).join(', ')}`);
