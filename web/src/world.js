// Seamless overworld streaming (RuneScape-style) for the [outdoor][worldmap] tiles.
//
// The base game splits the outdoor world into uniform 96x96 isometric maps laid out
// on an integer grid (col,row) — see tools/gen-world-grid.mjs. Because the iso
// projection is linear, placing every chunk in one GLOBAL cell space
// (gc = local_c + col*96, gr = local_r + row*96) tiles them edge-to-edge with no
// seams. We keep a 3x3 window of chunks loaded around the hero and stream new ones
// in / evict far ones out as he crosses a chunk boundary — so walking between
// outdoor tiles has NO load screen.
//
// Towns, buildings, caves and dungeons are NOT part of this grid: they're entered
// through a transition cell (an arch/door) and handled by the scene's discrete
// interior loader. This module only ever holds outdoor chunks.

import { loadMap, buildTiles, sortMid, ambientColor } from './map.js';
import { buildWalkable, gCellToPx, gPxToCell } from './move.js';

export class Overworld {
  constructor(scene, gridDef) {
    this.scene = scene;
    this.CW = gridDef.chunkW; this.CH = gridDef.chunkH;
    this.TW = gridDef.tilew;  this.TH = gridDef.tileh;
    this.grid = gridDef.grid;                         // name -> {col,row}
    this.coord = new Map();                           // "col,row" -> name
    for (const [name, g] of Object.entries(this.grid)) this.coord.set(`${g.col},${g.row}`, name);
    this.loaded = new Map();                          // name -> { map, walk, tiles, col, row }
  }

  // Prefetch radius (global cells). > viewport reach (~16 cells) so neighbours load
  // before they're visible, < a chunk (96) so mid-chunk only 1 chunk stays resident.

  has(name) { return !!this.grid[name]; }
  nameAt(col, row) { return this.coord.get(`${col},${row}`) || null; }

  // Global cell -> owning chunk (col,row) + local cell (lc,lr).
  chunkOfGlobal(gc, gr) {
    const col = Math.floor(gc / this.CW), row = Math.floor(gr / this.CH);
    return { col, row, lc: gc - col * this.CW, lr: gr - row * this.CH };
  }
  // A chunk's global cell origin.
  originOf(col, row) { return { gc0: col * this.CW, gr0: row * this.CH }; }

  cellToPx(gc, gr) { return gCellToPx(gc, gr, this.TW, this.TH); }
  pxToCell(px, py) { return gPxToCell(px, py, this.TW, this.TH); }

  // Is the GLOBAL cell walkable? Unloaded/void chunks are not walkable (you can never
  // path into a chunk that isn't streamed in yet).
  walkable(gc, gr) {
    const { col, row, lc, lr } = this.chunkOfGlobal(gc, gr);
    const ch = this.loaded.get(this.nameAt(col, row));
    if (!ch) return false;
    return !!ch.walk[lr * this.CW + lc];
  }

  // Global-cell bounding box over all currently-loaded chunks (search bound for A*).
  bound() {
    let minC = Infinity, minR = Infinity, maxC = -Infinity, maxR = -Infinity;
    for (const ch of this.loaded.values()) {
      minC = Math.min(minC, ch.col * this.CW);
      minR = Math.min(minR, ch.row * this.CH);
      maxC = Math.max(maxC, (ch.col + 1) * this.CW - 1);
      maxR = Math.max(maxR, (ch.row + 1) * this.CH - 1);
    }
    return { minC, minR, maxC, maxR };
  }

  // The chunks a hero at GLOBAL cell (gc,gr) needs resident: every chunk whose cells
  // fall within PREFETCH cells of him. Mid-chunk that's just his own chunk; near an
  // edge it adds that neighbour; near a corner the 2x2 block — so we hold ~1-2 chunks
  // instead of a fixed 3x3, which matters on a mobile browser. PREFETCH is comfortably
  // larger than the viewport reach, so a neighbour streams in well before it's visible.
  neededSet(gc, gr) {
    const P = Overworld.PREFETCH;
    const c0 = Math.floor((gc - P) / this.CW), c1 = Math.floor((gc + P) / this.CW);
    const r0 = Math.floor((gr - P) / this.CH), r1 = Math.floor((gr + P) / this.CH);
    const want = new Set();
    for (let r = r0; r <= r1; r++)
      for (let c = c0; c <= c1; c++) { const n = this.nameAt(c, r); if (n) want.add(n); }
    return want;
  }

  // Ensure exactly the chunks the hero (global cell gc,gr) needs are resident; evict
  // the rest. Returns { want, changed }. Sprites are pooled by the scene, so eviction
  // just drops the tile records + lets shared textures stay cached.
  async ensureWindow(gc, gr, hour) {
    const want = this.neededSet(gc, gr);
    let changed = false;
    for (const [name] of [...this.loaded])
      if (!want.has(name)) { this.loaded.delete(name); changed = true; }
    for (const name of want) {
      if (this.loaded.has(name)) continue;
      await this._loadChunk(name);
      changed = true;
    }
    return { want, changed };
  }

  // Ensure a single named chunk is resident (used to bootstrap entry before we know
  // the hero's global cell).
  async ensureChunk(name) { if (!this.loaded.has(name)) await this._loadChunk(name); }

  async _loadChunk(name) {
    const g = this.grid[name];
    const map = await loadMap(this.scene, name);
    const { gc0, gr0 } = this.originOf(g.col, g.row);
    const tiles = buildTiles(this.scene, map, gc0, gr0);
    const walk = buildWalkable(map);
    this.loaded.set(name, { map, walk, tiles, col: g.col, row: g.row });
  }

  clear() { this.loaded.clear(); }


  // The transition list for the chunk the hero currently stands in, with cells
  // translated into GLOBAL space so the scene can test them against the global hero
  // cell. Only outdoor->interior arches live here (outdoor tiles join seamlessly).
  transitionsInGlobal(col, row) {
    const ch = this.loaded.get(this.nameAt(col, row));
    if (!ch || !ch.map.transitions) return [];
    const { gc0, gr0 } = this.originOf(col, row);
    return ch.map.transitions.map(t => ({ c: t.c + gc0, r: t.r + gr0, area: t.area, entry: t.entry }));
  }
}

Overworld.PREFETCH = 40;
