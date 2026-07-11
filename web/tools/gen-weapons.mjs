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
// proc_type string -> our effect key (WeaponStats.d(): stun/slow/paralysis/emp).
const procEffect = (s) => {
  s = (s || '').toLowerCase().trim();
  if (!s) return null;
  if (s.includes('paralysis') || s.includes('paralyze')) return 'paralyze';
  if (s === 'stun') return 'stun';
  if (s === 'slow') return 'slow';
  if (s === 'emp') return 'emp';
  return null;
};

const lines = readFileSync(src, 'utf8').replace(/^﻿/, '').split('\n');
const idx = {};
for (let i = 1; i < lines.length; i++) {
  const f = lines[i].replace(/\r$/, '').split('\t');
  const id = (f[0] || '').trim();
  if (!id) continue;
  const num = (n, d = 0) => { const v = parseInt((f[n] || '').trim(), 10); return Number.isFinite(v) ? v : d; };
  const w = {
    name: (f[1] || '').trim(),
    min: num(2, 1), max: num(3, 2),
    crit: num(4, 0), critMul: num(5, 200),
    speed: num(6, 10), reach: num(7, 1),
    twoHanded: (f[8] || '').trim() === '1',
    ranged: (f[9] || '').trim() === '1',
    light: (f[10] || '').trim() === '1',
    type: TYPE[(f[14] || '').trim()] || 'Normal',
  };
  // ranged projectile art (col 12 ranged_type is 'w' wand/'s' staff/'b' bolt/'a' arrow;
  // col 13 sprite is the projectiles-atlas region) — used to fly the shot (A16).
  const rtype = (f[12] || '').trim();
  const sprite = (f[13] || '').trim();
  if (rtype) w.rangedType = rtype;
  if (sprite) w.sprite = sprite;
  // secondary elemental damage (cols 15/16) folded into every hit (A8).
  const sec = num(15, 0), secType = TYPE[(f[16] || '').trim()];
  if (sec > 0 && secType) w.secondary = { dmg: sec, type: secType };
  // on-hit proc (cols 17-19): effect + % chance + magnitude level (A8).
  const pe = procEffect(f[17]);
  if (pe) w.proc = { effect: pe, chance: num(18, 0), level: num(19, 1) };
  idx[id] = w;
}
writeFileSync(out, JSON.stringify(idx));
const withSec = Object.values(idx).filter(w => w.secondary).length;
const withProc = Object.values(idx).filter(w => w.proc).length;
const withSprite = Object.values(idx).filter(w => w.sprite).length;
console.log(`weapons: ${Object.keys(idx).length} entries -> ${out}`);
console.log(`  secondary-damage: ${withSec}, procs: ${withProc}, ranged-sprite: ${withSprite}`);
