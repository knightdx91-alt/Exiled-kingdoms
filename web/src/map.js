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
  map.tilesets.forEach((ts, i) => {
    ts.key = `ts_${name}_${i}`;
    scene.load.spritesheet(ts.key, `assets/tmx/${ts.image}`,
      { frameWidth: ts.tilew, frameHeight: ts.tileh });
  });
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

// Draw the map into three plane containers { floor, mid, roof }. Tiles are tinted by
// the map's ambient light (maxlight/100) so dark maps read as dark. Actors live in
// `mid` and are depth-sorted against scenery/objects by their feet Y (see sortMid).
// Returns { tiles, width, height, light }.
export function renderMap(scene, planes, map) {
  const { width: W, height: H, tilew: TW, tileh: TH } = map;
  const offX = (H - 1) * (TW / 2);            // shift so the leftmost cell sits at x=0

  // Ambient tint from maxlight (0-100). Floored so unlit dungeons stay legible until
  // dynamic light sources (torches/day-night) are ported.
  const light = Math.max(0.25, (map.maxlight ?? 100) / 100);
  const v = Math.round(light * 255);
  const tint = (v << 16) | (v << 8) | v;

  const tsFor = (gid) => {                     // last tileset whose firstgid <= gid
    let found = map.tilesets[0];
    for (const ts of map.tilesets) { if (ts.firstgid <= gid) found = ts; else break; }
    return found;
  };

  let count = 0;
  for (const layer of map.layers) {
    if (!layer.visible) continue;              // e.g. the invisible "nonwalk" mask
    const plane = planes[planeOf(layer.name)];
    for (let r = 0; r < H; r++) {
      for (let c = 0; c < W; c++) {
        const gid = layer.data[r * W + c];
        if (!gid) continue;
        const ts = tsFor(gid);
        const local = gid - ts.firstgid;
        if (local < 0 || local >= scene.textures.get(ts.key).frameTotal - 1) continue;
        const px = (c - r) * (TW / 2) + offX + TW / 2;   // bottom-center of the cell
        const py = (c + r) * (TH / 2) + TH;
        const img = scene.add.image(px, py, ts.key, local).setOrigin(0.5, 1);
        if (tint !== 0xffffff) img.setTint(tint);
        plane.add(img);
        count++;
      }
    }
  }
  const pxW = (W + H) * (TW / 2);
  const pxH = (W + H) * (TH / 2) + TH;
  return { tiles: count, width: pxW, height: pxH, light };
}

// Depth-sort the mid plane (scenery/objects + actors) by feet Y so things lower on
// screen (larger y) draw in front. Call after any actor moves.
export function sortMid(midPlane) {
  midPlane.sort('y');
}
