// Compact NPC/creature index for the web port: spawn_ID -> { sprite, portrait,
// faction, gender, class }. Source: data/rules/bestiary.txt (tab-separated). The map
// object layers reference these by name (e.g. "goblin", "adaon"); the engine uses the
// sprite to render the entity and faction to decide friend/foe.
import { readFileSync, writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const src = resolve(here, '../../recovered/assets/assets/data/rules/bestiary.txt');
const out = resolve(here, '../assets/data/bestiary.json');

const lines = readFileSync(src, 'utf8').split('\n');
const idx = {};
for (let i = 1; i < lines.length; i++) {
  const f = lines[i].replace(/\r$/, '').split('\t');
  const id = (f[0] || '').trim();
  if (!id) continue;
  idx[id] = {
    class: (f[2] || '').trim(),
    sprite: (f[11] || '').trim(),
    faction: (f[15] || '').trim(),
    gender: (f[16] || '').trim(),
    portrait: (f[17] || '').trim(),
  };
}
writeFileSync(out, JSON.stringify(idx));
console.log(`bestiary: ${Object.keys(idx).length} entries -> ${out}`);
