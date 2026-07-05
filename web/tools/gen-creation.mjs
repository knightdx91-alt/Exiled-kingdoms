// Character-creation constants, recovered by deobfuscating CharacterStats.java /
// CharacterTraits.java / CharacterSheet.java — see deobf/CHARACTER_STATS_SPEC.md.
// These are hardcoded in the game's logic (not in a data file), so we emit them here
// as the single source of truth for the web creation/level-up screens.
import { writeFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const out = resolve(dirname(fileURLToPath(import.meta.url)), '../assets/data/creation.json');

// cumulative trait points to reach attribute rank v = v(v+1)/2
const traitLadder = Array.from({ length: 13 }, (_, v) => (v * (v + 1)) / 2);

const data = {
  attributes: [                                        // index order matters (0..5)
    { id: 'STR', name: 'Strength' },
    { id: 'END', name: 'Endurance' },
    { id: 'AGI', name: 'Agility' },
    { id: 'INT', name: 'Intellect' },
    { id: 'AWA', name: 'Awareness' },
    { id: 'PER', name: 'Personality' },
  ],
  attrMax: 12,
  attrStart: 0,
  traitLadder,                                          // traitLadder[v] = cumulative cost
  // pools at a given level: trait = 2L+2 (+1/lvl>15), skill = 2L-1
  traitPoints: (level) => 2 * level + 2 + Math.max(0, level - 15),
  skillPoints: (level) => 2 * level - 1,
  // combat stats per class (human race)
  classes: {
    WARRIOR: { hp: 45, hpPerLevel: 6, mana: 0, manaPerLevel: 0, dmgMul: 0.5 },
    ROGUE:   { hp: 30, hpPerLevel: 4, mana: 0, manaPerLevel: 0, dmgMul: 0.75 },
    CLERIC:  { hp: 35, hpPerLevel: 5, mana: 12, manaPerLevel: 2, dmgMul: 0.3 },
    WIZARD:  { hp: 20, hpPerLevel: 3, mana: 12, manaPerLevel: 2, dmgMul: 0.0 },
  },
  startLevel: 1,
};

// functions can't serialize — bake the level-1 pools that creation needs
const json = {
  ...data,
  traitPoints: data.traitPoints(1),      // 4
  skillPoints: data.skillPoints(1),      // 1
};
writeFileSync(out, JSON.stringify(json));
console.log(`creation constants -> ${out}  (traitPts ${json.traitPoints}, skillPts ${json.skillPoints})`);
