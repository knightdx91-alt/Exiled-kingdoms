// Isometric map renderer for the converted EK .tmx JSON (see tools/tmx2json.mjs).
//
// EK maps are isometric with a base tile of (tilew x tileh) = 64x32. A cell (c,r)
// projects to screen as:
//     x = (c - r) * tilew/2 ,  y = (c + r) * tileh/2
// Tileset images can be TALLER than the base tile (walls, objects that stand up),
// so every tile is anchored bottom-center onto its cell's base diamond. Layers are
// drawn in file order; within a layer, row-major (r,c) gives correct iso overlap.

// Load the map JSON, then load each tileset PNG as a spritesheet sized to that
// tileset's tile dimensions (frame index == tile's local id). Resolves to the map.
export async function loadMap(scene, name) {
  const map = await (await fetch(`assets/tmx/${name}.json`)).json();
  // Key textures by IMAGE (+ tile dims), not by map name, so adjacent maps that reuse
  // the same tileset PNG share ONE GPU texture instead of each uploading its own copy
  // — a big memory win on mobile where many chunks are resident at once.
  let queued = false;
  map.tilesets.forEach((ts) => {
    ts.key = `ts_${ts.image}_${ts.tilew}x${ts.tileh}`.replace(/[^a-z0-9_]/gi, '_');
    if (scene.textures.exists(ts.key)) return;       // already uploaded by another chunk
    scene.load.spritesheet(ts.key, `assets/tmx/${ts.image}`,
      { frameWidth: ts.tilew, frameHeight: ts.tileh });
    queued = true;
  });
  if (queued)
    await new Promise((resolve) => { scene.load.once('complete', resolve); scene.load.start(); });
  return map;
}

// Which depth plane a layer belongs to. EK's layer stack is roughly:
//   nonwalk(hidden), water, ground, ground2 -> floor (always under actors)
//   scenery, objects                        -> mid   (depth-sorted WITH actors)
//   roofs                                   -> roof  (always over actors)
export function planeOf(name) {
  const n = name.toLowerCase();
  if (n.includes('roof')) return 'roof';
  if (n.includes('object') || n.includes('scener')) return 'mid';
  return 'floor';
}

// Ambient light COLOR for a map, recovered from GameLevelRenderer (k0/a.java ~698):
//   maxlight>0 (dungeons): bluish, rgb = (0.08f+0.1, 0.08f+0.1, 0.05f+0.4), f=maxlight/10
//   outdoor (no maxlight): day/night by clock hour (0-23) — FDUtils.j()
//   otherwise: near-bright (0.93)
// Returned as {r,g,b} in 0..1 (used as a per-tile multiply tint).
// APPROX (A1, see deobf/DEOBFUSCATION_STATUS.md): the base game applies these as the
// ambient of a box2dlights RayHandler and adds dynamic PointLight sources (torches,
// fires, player light) on top. We only do the flat ambient tint — no light sources.
export function ambientColor(map, hour) {
  if (map.maxlight > 0) {
    const f = map.maxlight / 10;
    return { r: 0.08 * f + 0.1, g: 0.08 * f + 0.1, b: 0.05 * f + 0.4 };
  }
  if (map.outdoor) {
    if (hour >= 23 || hour <= 5) return { r: 0.30, g: 0.30, b: 0.70 };  // night
    if (hour === 6 || hour === 22) return { r: 0.40, g: 0.40, b: 0.60 }; // deep dusk/dawn
    if (hour === 7 || hour === 21) return { r: 0.50, g: 0.50, b: 0.60 };
    if (hour === 8 || hour === 20) return { r: 0.65, g: 0.65, b: 0.65 };
    return { r: 0.93, g: 0.93, b: 0.93 };                               // day (9-19)
  }
  return { r: 0.93, g: 0.93, b: 0.93 };
}

const tintOf = (c) => (Math.round(c.r * 255) << 16) |
                      (Math.round(c.g * 255) << 8) | Math.round(c.b * 255);

// Re-tint every already-rendered map tile to a new ambient color (for live day/night
// or entering a differently-lit area) without rebuilding the scene.
export function applyAmbient(planes, color) {
  const t = tintOf(color);
  for (const p of Object.values(planes))
    for (const child of p.list) if (child.setTint) child.setTint(t);
}

// Draw the map into three plane containers { floor, mid, roof }, tinted by `ambient`
// {r,g,b}. Actors live in `mid` and are depth-sorted against scenery/objects by their
// feet Y (see sortMid). Returns { tiles, width, height, images }.
//
// `opts` lets a caller place the map into a SHARED global cell space (the seamless
// overworld — see world.js): pass { gc0, gr0 } to offset every cell by that global
// chunk origin, and offX:0 so projection is the raw global iso (no per-map centering).
// Default (interior maps): gc0=gr0=0 and offX centers the diamond at x>=0.
export function renderMap(scene, planes, map, ambient, opts = {}) {
  const { width: W, height: H, tilew: TW, tileh: TH } = map;
  const gc0 = opts.gc0 || 0, gr0 = opts.gr0 || 0;
  const offX = opts.offX != null ? opts.offX : (H - 1) * (TW / 2);
  const tint = tintOf(ambient);
  const images = [];

  const tsFor = (gid) => {                     // last tileset whose firstgid <= gid
    let found = map.tilesets[0];
    for (const ts of map.tilesets) { if (ts.firstgid <= gid) found = ts; else break; }
    return found;
  };

  let count = 0;
  for (const layer of map.layers) {
    if (!layer.visible) continue;              // e.g. the invisible "nonwalk" mask
    const planeName = planeOf(layer.name);
    const plane = planes[planeName];
    for (let r = 0; r < H; r++) {
      for (let c = 0; c < W; c++) {
        const gid = layer.data[r * W + c];
        if (!gid) continue;
        const ts = tsFor(gid);
        const local = gid - ts.firstgid;
        if (local < 0 || local >= scene.textures.get(ts.key).frameTotal - 1) continue;
        const gc = gc0 + c, gr = gr0 + r;
        const px = (gc - gr) * (TW / 2) + offX + TW / 2; // bottom-center of the cell
        const py = (gc + gr) * (TH / 2) + TH;
        const img = scene.add.image(px, py, ts.key, local).setOrigin(0.5, 1).setTint(tint);
        // Tag for the render-FX layer (roof-fade A3, fog-of-war A5): local cell, which
        // plane it's on, and the base ambient tint so fog can dim/restore it.
        img._cell = { c, r }; img._plane = planeName; img._baseTint = tint;
        plane.add(img);
        images.push(img);
        count++;
      }
    }
  }
  const pxW = (W + H) * (TW / 2);
  const pxH = (W + H) * (TH / 2) + TH;
  return { tiles: count, width: pxW, height: pxH, images };
}

// Build lightweight tile RECORDS (no sprites) for a chunk placed at global cell
// origin (gc0,gr0). Used by the seamless overworld, which renders only the records
// near the camera via a sprite pool (see main.js refreshVisible) — so world size is
// decoupled from draw cost. Each record: { plane, px, py, key, frame }.
export function buildTiles(scene, map, gc0, gr0) {
  const { width: W, height: H, tilew: TW, tileh: TH } = map;
  const tsFor = (gid) => {
    let found = map.tilesets[0];
    for (const ts of map.tilesets) { if (ts.firstgid <= gid) found = ts; else break; }
    return found;
  };
  const out = [];
  for (const layer of map.layers) {
    if (!layer.visible) continue;
    const plane = planeOf(layer.name);
    for (let r = 0; r < H; r++)
      for (let c = 0; c < W; c++) {
        const gid = layer.data[r * W + c];
        if (!gid) continue;
        const ts = tsFor(gid);
        const frame = gid - ts.firstgid;
        if (frame < 0 || frame >= scene.textures.get(ts.key).frameTotal - 1) continue;
        const gc = gc0 + c, gr = gr0 + r;
        out.push({ plane,
          px: (gc - gr) * (TW / 2) + TW / 2,
          py: (gc + gr) * (TH / 2) + TH,
          key: ts.key, frame });
      }
  }
  return out;
}

// Depth-sort the mid plane (scenery/objects + actors) by feet Y so things lower on
// screen (larger y) draw in front. Call after any actor moves.
export function sortMid(midPlane) {
  midPlane.sort('y');
}
