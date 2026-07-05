// Generate web/assets/world-grid.json — the seamless-overworld chunk grid.
//
// The base game's outdoor world is a set of uniform 96x96 isometric maps, each
// tagged [outdoor][worldmap] in data/world/areas.txt with integer grid coords
// posX (column) / posY (row). Adjacent columns/rows are edge-neighbours
// (verified: e=col+1, w=col-1, n=row+1, s=row-1 matches every map's exit_*),
// so they tile seamlessly in one global cell space (gc = c + col*W, gr = r + row*H).
//
// Towns (NG/FT/NI/IM) are also [worldmap] but NOT [outdoor]: their posX/posY are
// just parchment-map icon positions, not adjacency — they're entered through an
// arch (a transition) and stay discrete. We emit ONLY the outdoor tiles here.
//
// Usage: node web/tools/gen-world-grid.mjs [path/to/areas.txt]
import { readFileSync, writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const areasPath = process.argv[2] ||
  resolve(here, '../../recovered/assets/assets/data/world/areas.txt');
const outPath = resolve(here, '../assets/world-grid.json');

const grid = {};      // name -> { col, row }
for (const line of readFileSync(areasPath, 'utf8').split('\n')) {
  if (!line.trim()) continue;
  const f = line.split('\t');
  const [id, posX, posY, , flags] = f;
  if (!flags) continue;
  const fl = flags.toLowerCase();
  if (!fl.includes('[worldmap]') || !fl.includes('[outdoor]')) continue;
  grid[id] = { col: parseInt(posX, 10), row: parseInt(posY, 10) };
}

const out = { chunkW: 96, chunkH: 96, tilew: 64, tileh: 32, grid };
writeFileSync(outPath, JSON.stringify(out));
console.log(`world-grid: ${Object.keys(grid).length} outdoor chunks -> ${outPath}`);
