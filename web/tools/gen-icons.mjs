// Extract EK's item + object icon atlases into per-icon PNGs the web build uses, so the
// inventory / quickslots / containers show the REAL game art (not text or a stand-in).
//
// Mirrors how the skill icons were produced: slices each libGDX atlas (data/graphics/*.pack)
// with tools/extract-skin.mjs. Requires the recovered assets — run `tools/extract.sh <APK>`
// first so recovered/assets exists.
//
//   items.pack      -> web/assets/ui/items    (potions, weapons, armor — items.json `icon`)
//   activables.pack -> web/assets/ui/objects  (crates/chests/barrels/sarcophagi — container `icon`)
//   plants.pack     -> web/assets/ui/objects  (harvestables: nests, gold trees, …)
//   mapitems.pack   -> web/assets/ui/objects  (ground loot markers)
//
// Usage:  cd web && node tools/gen-icons.mjs
import { execFileSync } from 'node:child_process';
import { existsSync, rmSync } from 'node:fs';
import { resolve, dirname } from 'node:path';
import { fileURLToPath } from 'node:url';

const here = dirname(fileURLToPath(import.meta.url));
const GRAPHICS = [
  resolve(here, '../../recovered/assets/assets/data/graphics'),
  resolve(here, '../../recovered_mods/base/assets/assets/data/graphics'),
].find(existsSync);

if (!GRAPHICS) {
  console.error('No recovered graphics dir found.\n' +
    'Run  tools/extract.sh <path/to/Exiled Kingdoms.apk>  first, then re-run this.');
  process.exit(1);
}

const slicer = resolve(here, 'extract-skin.mjs');
// objects dir is shared by several packs, so clear its manifest once up front (the slicer
// then MERGES each pack into it).
rmSync(resolve(here, '../assets/ui/objects/manifest.json'), { force: true });

const jobs = [
  { pack: 'items.pack',      png: 'items.png',      out: 'assets/ui/items',   fresh: true },
  { pack: 'activables.pack', png: 'activables.png', out: 'assets/ui/objects' },
  { pack: 'plants.pack',     png: 'plants.png',     out: 'assets/ui/objects' },
  { pack: 'mapitems.pack',   png: 'mapitems.png',   out: 'assets/ui/objects' },
];

for (const j of jobs) {
  const atlas = resolve(GRAPHICS, j.pack);
  if (!existsSync(atlas)) { console.warn('skip (missing):', j.pack); continue; }
  if (j.fresh) rmSync(resolve(here, '..', j.out, 'manifest.json'), { force: true });
  console.log(`\n>> ${j.pack} -> ${j.out}`);
  execFileSync('node', [slicer, atlas, j.png, j.out], { stdio: 'inherit', cwd: resolve(here, '..') });
}
console.log('\nDone. Re-run  node tools/gen-manifest.mjs  to add the new icons to the offline cache.');
