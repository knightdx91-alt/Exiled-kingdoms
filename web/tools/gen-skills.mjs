// Unified skill list for the web port, grouped by class, split into base + advanced
// (specialist) sets — exactly the three groups SkillWindow (o0/t) renders: the class's
// own skills, GENERAL skills, and the SPECIALIST_SKILLS ("Advanced Skills"). Each skill
// carries name/type/class/description, an icon region (skills.pack, resolved by name),
// its tier-1 cost/mana, and the per-rank level blurbs (Current/Next level text).
//
// Base rows live in skills.txt/skills2/skills3; advanced in skills_advanced*.txt. Each
// skill's main row holds name/type/class/description; the following indented rows are
// its ranks (blurb in col5, cost col6, mana col8).
import { readFileSync, writeFileSync, readdirSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
const rulesDirs = [
  resolve(here, '../../recovered/assets/assets/data/rules'),
  resolve(here, '../../recovered_mods/base/assets/assets/data/rules'),
];
const dir = rulesDirs.find(d => { try { readdirSync(d); return true; } catch { return false; } });
const packDirs = [
  resolve(here, '../../recovered/assets/assets/data/graphics'),
  resolve(here, '../../recovered_mods/base/assets/assets/data/graphics'),
];
const packDir = packDirs.find(d => { try { readdirSync(d); return true; } catch { return false; } });
const out = resolve(here, '../assets/data/skills.json');

const CLASS = { W: 'WARRIOR', R: 'ROGUE', C: 'CLERIC', M: 'WIZARD', G: 'GENERAL', X: 'NONE' };

// --- resolve a skill name to a skills.pack icon region: normalise both sides to
//     alphanumerics and match. Falls back to null (renders as "unknown"). ---
const regions = readFileSync(resolve(packDir, 'skills.pack'), 'utf8')
  .split('\n').map(l => l.replace(/\r$/, ''))
  .filter(l => /^\S/.test(l) && !l.includes(':') && !l.endsWith('.png'));
const norm = s => s.toLowerCase().replace(/[^a-z0-9]/g, '');
const regionByNorm = new Map(regions.map(r => [norm(r), r]));
const iconFor = name => regionByNorm.get(norm(name)) || null;

// parse one skills file: returns [{name,type,class,desc,icon,cost,mana,levels:[{desc,cost,mana}]}]
function parseFile(file) {
  const lines = readFileSync(resolve(dir, file), 'utf8').split('\n');
  const skills = []; let cur = null;
  for (let i = 1; i < lines.length; i++) {
    const f = lines[i].replace(/\r$/, '').split('\t');
    const name = (f[0] || '').trim();
    if (name) {                                       // main row: a new skill
      cur = { name, type: (f[1] || '').trim(), class: (f[3] || '').trim(),
              desc: (f[5] || '').replace(/\[[A-Z]*\]/g, '').trim(),
              icon: iconFor(name), cost: null, mana: 0, levels: [] };
      skills.push(cur);
    } else if (cur && (f[5] || '').trim()) {          // a rank row
      const lvl = { desc: (f[5] || '').replace(/\[[A-Z]*\]/g, '').trim(),
                    cost: +((f[6] || '').trim()) || 1, mana: +((f[8] || '').trim()) || 0 };
      cur.levels.push(lvl);
      if (cur.cost == null) { cur.cost = lvl.cost; cur.mana = lvl.mana; }
    }
  }
  for (const s of skills) if (s.cost == null) s.cost = 1;
  return skills;
}

const base = {}, advanced = {}; const seen = new Set();
const files = readdirSync(dir).filter(f => /^skills.*\.txt$/.test(f));
for (const file of files) {
  const isAdv = /advanced/i.test(file);
  const bucket = isAdv ? advanced : base;
  for (const s of parseFile(file)) {
    const cls = CLASS[s.class];
    if (!cls || seen.has(s.name)) continue;
    seen.add(s.name);
    (bucket[cls] = bucket[cls] || []).push(s);
  }
}

writeFileSync(out, JSON.stringify({ base, advanced }));
const sz = o => Object.entries(o).map(([k, v]) => `${k}:${v.length}`).join(' ');
console.log('base  ', sz(base));
console.log('adv   ', sz(advanced));
