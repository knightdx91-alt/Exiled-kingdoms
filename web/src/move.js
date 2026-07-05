// Movement: walkable grid, isometric cell<->pixel, and A* pathfinding.
// Collision follows the base game's GameMap.v(): a cell is blocked if the nonwalk
// layer has a tile there, the ground layer is empty (void), or the objects tile
// carries a "blocked"/"obstacle" property (map.blockedGids). See ENGINE_SPEC.md.

export function buildWalkable(map) {
  const { width: W, height: H } = map;
  const blocked = new Set(map.blockedGids || []);
  const layer = (n) => map.layers.find((l) => l.name === n);
  const ground = layer('ground'), nonwalk = layer('nonwalk'), objects = layer('objects');
  const walk = new Uint8Array(W * H);
  for (let r = 0; r < H; r++)
    for (let c = 0; c < W; c++) {
      const i = r * W + c;
      let ok = true;
      if (ground && ground.data[i] === 0) ok = false;          // no ground = void
      if (nonwalk && nonwalk.data[i] !== 0) ok = false;         // nonwalk mask
      if (objects && blocked.has(objects.data[i])) ok = false;  // blocked/obstacle object
      walk[i] = ok ? 1 : 0;
    }
  return walk;
}

// Cell (c,r) -> map-space pixel (bottom-center of the tile diamond), matching renderMap.
export function cellToPx(c, r, map) {
  const { width: W, height: H, tilew: TW, tileh: TH } = map;
  const offX = (H - 1) * (TW / 2);
  return { x: (c - r) * (TW / 2) + offX + TW / 2, y: (c + r) * (TH / 2) + TH };
}

// Map-space pixel -> cell (inverse of cellToPx).
export function pxToCell(px, py, map) {
  const { width: W, height: H, tilew: TW, tileh: TH } = map;
  const offX = (H - 1) * (TW / 2);
  const X = px - offX - TW / 2, Y = py - TH;
  return { c: Math.round(X / TW + Y / TH), r: Math.round(Y / TH - X / TW) };
}

const key = (c, r, W) => r * W + c;

// 8-connected A* over the walkable grid. No corner-cutting through blocked diagonals.
export function findPath(walk, W, H, start, goal) {
  const inb = (c, r) => c >= 0 && r >= 0 && c < W && r < H;
  const ok = (c, r) => inb(c, r) && walk[key(c, r, W)];
  if (!ok(goal.c, goal.r)) return null;
  const open = [{ c: start.c, r: start.r, g: 0, f: 0, p: null }];
  const seen = new Map();
  const h = (c, r) => Math.hypot(c - goal.c, r - goal.r);
  const N = [[1,0],[-1,0],[0,1],[0,-1],[1,1],[1,-1],[-1,1],[-1,-1]];
  let guard = 0;
  while (open.length && guard++ < 20000) {
    open.sort((a, b) => a.f - b.f);
    const cur = open.shift();
    if (cur.c === goal.c && cur.r === goal.r) {
      const path = []; for (let n = cur; n; n = n.p) path.unshift({ c: n.c, r: n.r });
      return path;
    }
    const k = key(cur.c, cur.r, W);
    if (seen.has(k) && seen.get(k) <= cur.g) continue;
    seen.set(k, cur.g);
    for (const [dc, dr] of N) {
      const nc = cur.c + dc, nr = cur.r + dr;
      if (!ok(nc, nr)) continue;
      if (dc && dr && (!ok(cur.c + dc, cur.r) || !ok(cur.c, cur.r + dr))) continue; // no corner cut
      const g = cur.g + (dc && dr ? 1.414 : 1);
      open.push({ c: nc, r: nr, g, f: g + h(nc, nr), p: cur });
    }
  }
  return null;
}

// Screen-space movement delta -> EK facing {row-name, flip}. Sheet rows U/RU/R/RD/D;
// left-facing directions are the mirrored right ones (flipX).
export function facingFor(dx, dy) {
  const a = Math.atan2(dy, dx) * 180 / Math.PI;   // -180..180, 0 = +x (screen right)
  if (a >= -22.5 && a < 22.5)  return { name: 'R',  flip: false };
  if (a >= 22.5 && a < 67.5)   return { name: 'RD', flip: false };
  if (a >= 67.5 && a < 112.5)  return { name: 'D',  flip: false };
  if (a >= 112.5 && a < 157.5) return { name: 'RD', flip: true };
  if (a >= 157.5 || a < -157.5) return { name: 'R', flip: true };
  if (a >= -157.5 && a < -112.5) return { name: 'RU', flip: true };
  if (a >= -112.5 && a < -67.5)  return { name: 'U',  flip: false };
  return { name: 'RU', flip: false };             // -67.5..-22.5
}
