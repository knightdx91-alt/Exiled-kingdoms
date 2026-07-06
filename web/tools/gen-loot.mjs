// Loot tables for the web port: table -> [{ item, name, chance }]. Source:
// data/rules/loot.txt (UTF-16, tab-separated). Columns: 0 table,1 item_id,2 name,
// 3 chance(%),4 conditions. item_id == -2 => Gold (see deobf/COMBAT_SPEC.md).
import { readFileSync, writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const src = resolve(here, '../../recovered/assets/assets/data/rules/loot.txt');
const out = resolve(here, '../assets/data/loot.json');

// loot.txt is UTF-16; decode via a Buffer then strip a BOM.
const raw = readFileSync(src);
const text = raw.toString('utf16le').replace(/^﻿/, '');
const lines = text.split(/\r?\n/);
const tables = {};
for (let i = 1; i < lines.length; i++) {
  const f = lines[i].split('\t');
  const table = (f[0] || '').trim();
  const item = (f[1] || '').trim();
  if (!table || !item) continue;
  const chance = parseInt((f[3] || '').trim(), 10) || 0;
  (tables[table] ||= []).push({
    item: parseInt(item, 10),
    name: (f[2] || '').trim(),
    chance,
  });
}
writeFileSync(out, JSON.stringify(tables));
console.log(`loot: ${Object.keys(tables).length} tables -> ${out}`);
