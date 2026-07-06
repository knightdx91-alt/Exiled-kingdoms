// Advanced ("specialist") skills catalog for the trainer system: id -> { name,
// disciplines[], cost, mana, req, desc }. These are the skills trainer NPCs teach via
// the dialogue action TrainSkill#<id> (id = the normalised skill name, matching the
// skills.pack icon region). Source: data/rules/skills_advanced*.txt (tab-separated:
// name, type, NPC, class(csv W,C,R,M), melee, desc, cost, cooldown, mana, skill_req).
// See deobf/TRAINERS_SPEC.md.
import { readFileSync, writeFileSync, readdirSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const dir = resolve(here, '../../recovered/assets/assets/data/rules');
const out = resolve(here, '../assets/data/trainers.json');

const DISC = { W: 'WARRIOR', R: 'ROGUE', C: 'CLERIC', M: 'WIZARD', G: 'GENERAL' };
const id = (name) => name.toLowerCase().replace(/[^a-z0-9]+/g, '_').replace(/^_|_$/g, '');

const catalog = {};
for (const file of readdirSync(dir).filter(f => /^skills_advanced.*\.txt$/.test(f))) {
  const lines = readFileSync(resolve(dir, file), 'utf8').replace(/^﻿/, '').split('\n');
  let cur = null;
  for (let i = 1; i < lines.length; i++) {
    const f = lines[i].replace(/\r$/, '').split('\t');
    const name = (f[0] || '').trim();
    if (name) {                                       // main row: a new skill
      const disc = (f[3] || '').split(',').map(s => DISC[s.trim()]).filter(Boolean);
      cur = { name, disciplines: disc,
              type: (f[1] || '').trim(),
              cost: +((f[6] || '').trim()) || 1, mana: +((f[8] || '').trim()) || 0,
              req: (f[9] || '').trim(),
              desc: (f[5] || '').replace(/\[[A-Z]*\]/g, '').trim() };
      const key = id(name);
      if (!catalog[key]) catalog[key] = cur;
    }
  }
}
writeFileSync(out, JSON.stringify(catalog));
console.log(`trainers: ${Object.keys(catalog).length} advanced skills -> ${out}`);
