// Unified skill list for the web port, grouped by class. Skills span several files
// (skills.txt, skills2/3, skills_advanced*). Each skill's main row holds
// name/type/class/description; the following rows are its ranks (each with a cost /
// mana). We keep the tier-1 cost + mana so creation can offer a buyable first ability.
//
// Columns: 0 name, 1 type(P/A), 3 class(W/R/C/M/G/X), 5 desc, 6 cost, 8 mana.
import { readFileSync, writeFileSync, readdirSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const dir = resolve(here, '../../recovered/assets/assets/data/rules');
const out = resolve(here, '../assets/data/skills.json');

const CLASS = { W: 'WARRIOR', R: 'ROGUE', C: 'CLERIC', M: 'WIZARD', G: 'GENERAL', X: 'NONE' };
const byClass = {}; const seen = new Set();

for (const file of readdirSync(dir).filter(f => /^skills.*\.txt$/.test(f))) {
  const lines = readFileSync(resolve(dir, file), 'utf8').split('\n');
  let cur = null;
  for (let i = 1; i < lines.length; i++) {
    const f = lines[i].replace(/\r$/, '').split('\t');
    const name = (f[0] || '').trim();
    if (name) {                                       // main row: a new skill
      cur = { name, type: (f[1] || '').trim(), class: (f[3] || '').trim(),
              desc: (f[5] || '').replace(/\[[A-Z]*\]/g, '').trim(), cost: null, mana: 0 };
      const cls = CLASS[cur.class];
      if (cls && !seen.has(name)) {                   // real class skills only
        seen.add(name); (byClass[cls] = byClass[cls] || []).push(cur);
      }
    } else if (cur && cur.cost == null && (f[6] || '').trim()) {  // first rank -> tier-1 cost
      cur.cost = +(f[6].trim()) || 1;
      cur.mana = +((f[8] || '').trim()) || 0;
    }
  }
}
for (const c of Object.values(byClass)) for (const s of c) if (s.cost == null) s.cost = 1;
writeFileSync(out, JSON.stringify(byClass));
console.log('skills:', Object.entries(byClass).map(([k, v]) => `${k}:${v.length}`).join(' '));
