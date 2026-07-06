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
// HERO — owner's design-in-flight class (not in base EK), authored here rather than
// pulled from EK's strings (deobf/TRAINERS_SPEC.md).
res.HERO = 'A versatile adventurer bound to no single discipline. The Hero learns ' +
  'skills from every school by seeking out trainers across the land, and may wield any ' +
  'class-restricted gear once trained in that discipline. Balanced health, modest mana, ' +
  'and solid melee — a jack of all trades who grows into the master you make.';
writeFileSync(out, JSON.stringify(res));
console.log('class-info:', Object.keys(res).join(', '));
