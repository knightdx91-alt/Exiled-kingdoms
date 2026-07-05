// Extract the base game's real class descriptions (English) into a compact JSON the
// character-creation UI can show. Source: data/ui/strings/texts.txt (tab-separated;
// col 0 = key, col 1 = English). Converts the game's <p> paragraph breaks to newlines
// and strips its [BLUE]...[] colour markup.
import { readFileSync, writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const src = resolve(here, '../../recovered/assets/assets/data/ui/strings/texts.txt');
const out = resolve(here, '../assets/ui/class-info.json');

const keys = {
  CLASS_DESC_WARRIOR: 'WARRIOR', CLASS_DESC_ROGUE: 'ROGUE',
  CLASS_DESC_CLERIC: 'CLERIC', CLASS_DESC_MAGE: 'WIZARD',
  DL_DESC_IRONMAN: 'IRONMAN_DESC',
};
const clean = (s) => s.replace(/\[BLUE\]/g, '').replace(/\[\]/g, '').replace(/<p>/g, '\n').trim();

const res = {};
for (const line of readFileSync(src, 'utf8').split('\n')) {
  const f = line.replace(/\r$/, '').split('\t');
  if (keys[f[0]] && f[1]) res[keys[f[0]]] = clean(f[1]);
}
writeFileSync(out, JSON.stringify(res));
console.log('class-info:', Object.keys(res).join(', '));
