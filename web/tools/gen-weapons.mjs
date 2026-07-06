// Weapon combat stats for the web port: weapon_id -> { min,max,crit,critMul,speed,
// reach,ranged,light,type }. Source: data/rules/weapons.txt (tab-separated).
// Columns (see deobf/COMBAT_SPEC.md): 0 id,1 name,2 min,3 max,4 crit_chance,5 crit(200=2x),
// 6 speed,7 reach,8 2h,9 ranged,10 light,14 damage_type.
import { readFileSync, writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const src = resolve(here, '../../recovered/assets/assets/data/rules/weapons.txt');
const out = resolve(here, '../assets/data/weapons.json');

// EK short damage-type codes -> our type names (Damage.b()).
const TYPE = { f: 'Fire', c: 'Cold', s: 'Shock', d: 'Death', t: 'Toxic', sp: 'Spirit', p: 'Spirit' };

const lines = readFileSync(src, 'utf8').replace(/^﻿/, '').split('\n');
const idx = {};
for (let i = 1; i < lines.length; i++) {
  const f = lines[i].replace(/\r$/, '').split('\t');
  const id = (f[0] || '').trim();
  if (!id) continue;
  const num = (n, d = 0) => { const v = parseInt((f[n] || '').trim(), 10); return Number.isFinite(v) ? v : d; };
  idx[id] = {
    name: (f[1] || '').trim(),
    min: num(2, 1), max: num(3, 2),
    crit: num(4, 0), critMul: num(5, 200),
    speed: num(6, 10), reach: num(7, 1),
    twoHanded: (f[8] || '').trim() === '1',
    ranged: (f[9] || '').trim() === '1',
    type: TYPE[(f[14] || '').trim()] || 'Normal',
  };
}
writeFileSync(out, JSON.stringify(idx));
console.log(`weapons: ${Object.keys(idx).length} entries -> ${out}`);
