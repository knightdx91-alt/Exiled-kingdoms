// Item catalog for the web port: id -> { name, type, slot, weapon, armor, hp, mana,
// resist{}, value, icon, sprite, classes, stack, onUse }. Source: data/rules/items.txt.
// Columns + type→slot mapping reversed in deobf/INVENTORY_SPEC.md.
import { readFileSync, writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const src = resolve(here, '../../recovered/assets/assets/data/rules/items.txt');
const out = resolve(here, '../assets/data/items.json');

// item `type` string → equipment slot (null = not equippable). CharacterInventory.c().
const SLOT = {
  WEAPON: 'mainhand', SHIELD: 'offhand',
  armor_head: 'head', armor_chest: 'body', armor_arms: 'hands',
  armor_legs: 'legs', armor_feet: 'feet',
  ring: 'ring', belt: 'belt', cloak: 'cloak', necklace: 'necklace',
};
const RES = ['Fire', 'Cold', 'Shock', 'Death', 'Toxic', 'Spirit'];

const lines = readFileSync(src, 'utf8').replace(/^﻿/, '').split('\n');
const items = {};
for (let i = 1; i < lines.length; i++) {
  const f = lines[i].replace(/\r$/, '').split('\t');
  const id = parseInt((f[0] || '').trim(), 10);
  if (!Number.isFinite(id)) continue;
  const type = (f[2] || '').trim();
  const num = (n) => { const v = parseInt((f[n] || '').trim(), 10); return Number.isFinite(v) ? v : 0; };
  const resCsv = (f[5] || '').replace(/"/g, '').split(',').map(x => parseInt(x, 10) || 0);
  const resist = {};
  RES.forEach((k, j) => { if (resCsv[j]) resist[k] = resCsv[j]; });
  items[id] = {
    name: (f[1] || '').trim(), type, slot: SLOT[type] || null,
    weapon: (f[3] || '').trim() || null,           // WEAPON: weapon id into weapons.json
    armor: num(4), resist,
    value: num(8), icon: (f[9] || '').trim(),
    hp: num(10), mana: num(11),
    sprite: (f[17] || '').trim() || null,
    onUse: (f[20] || '').trim() || null,
    classes: (f[23] || '').trim(),
    stack: (f[24] || '').trim() === '1',
  };
}
writeFileSync(out, JSON.stringify(items));
const equip = Object.values(items).filter(i => i.slot).length;
console.log(`items: ${Object.keys(items).length} (${equip} equippable) -> ${out}`);
