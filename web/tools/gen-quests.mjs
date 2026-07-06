// Quest journal data for the web port: id -> { name, type, states: { value: desc } }.
// Source: data/quests/list.txt + data/quests/<id>.txt (tab-separated:
// progress, description, description_ES, actions). Progress 0 carries the Name (+ an
// optional type letter in col 4); other rows are journal states keyed by progress value.
// See deobf/QUEST_SPEC.md.
import { readFileSync, writeFileSync, existsSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const qdir = resolve(here, '../../recovered/assets/assets/data/quests');
const out = resolve(here, '../assets/data/quests.json');

const TYPE = { B: 'bounty', I: 'investigation', M: 'main', C: 'companion' };
const list = readFileSync(resolve(qdir, 'list.txt'), 'utf8').split('\n')
  .map(s => s.replace(/\r/g, '').trim()).filter(Boolean);

const quests = {};
let skipped = 0;
for (const id of list) {
  const file = resolve(qdir, `${id}.txt`);
  if (!existsSync(file)) { skipped++; continue; }
  const lines = readFileSync(file, 'utf8').replace(/^﻿/, '').split('\n');
  const q = { name: id, type: '', states: {} };
  for (let i = 1; i < lines.length; i++) {
    const f = lines[i].replace(/\r/g, '').split('\t');
    const prog = (f[0] || '').trim();
    if (prog === '') continue;
    const desc = (f[1] || '').trim().replace(/^"|"$/g, '');
    if (prog === '0') {
      if (desc) q.name = desc;
      const ty = (f[4] || '').trim();
      if (ty && TYPE[ty]) q.type = TYPE[ty];
    } else {
      q.states[prog] = desc;
    }
  }
  quests[id] = q;
}
writeFileSync(out, JSON.stringify(quests));
console.log(`quests: ${Object.keys(quests).length} written (${skipped} missing files) -> ${out}`);
