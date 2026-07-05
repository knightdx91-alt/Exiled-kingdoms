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

// Draw the map into `container`. Returns { tiles, width, height } where width/height
// are the map's pixel bounds (for fit-to-screen scaling by the caller).
export function renderMap(scene, container, map) {
  const { width: W, height: H, tilew: TW, tileh: TH } = map;
  const offX = (H - 1) * (TW / 2);            // shift so the leftmost cell sits at x=0

  const tsFor = (gid) => {                     // last tileset whose firstgid <= gid
    let found = map.tilesets[0];
    for (const ts of map.tilesets) { if (ts.firstgid <= gid) found = ts; else break; }
    return found;
  };

  let count = 0;
  for (const layer of map.layers) {
    if (!layer.visible) continue;              // e.g. the invisible "nonwalk" mask
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
        container.add(img);
        count++;
      }
    }
  }
  const pxW = (W + H) * (TW / 2);
  const pxH = (W + H) * (TH / 2) + TH;
  return { tiles: count, width: pxW, height: pxH };
}
