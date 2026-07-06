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
  const t = (n) => (f[n] || '').trim();
  const resist = t(7).replace(/"/g, '').split(',').map(x => parseInt(x, 10) || 0);
  idx[id] = {
    race: t(1), class: t(2),
    minlevel: parseInt(t(3), 10) || 1, maxlevel: parseInt(t(4), 10) || (parseInt(t(3), 10) || 1),
    weapon: t(5), armor: parseInt(t(6), 10) || 0,
    // resist csv is Fire,Cold,Shock,Death,Toxic,Spirit (bestiary.txt order)
    resist: { Fire: resist[0] || 0, Cold: resist[1] || 0, Shock: resist[2] || 0,
              Death: resist[3] || 0, Toxic: resist[4] || 0, Spirit: resist[5] || 0 },
    loot: t(10), size: parseFloat(t(12)) || 1, skillset: t(13).replace(/"/g, ''),
    ai: t(14),
    sprite: t(11),
    faction: t(15),
    gender: t(16),
    portrait: t(17),
  };
}
writeFileSync(out, JSON.stringify(idx));
console.log(`bestiary: ${Object.keys(idx).length} entries -> ${out}`);
