// Skill / spell effects (combat layer 2) — deobf/SKILLS_EXEC_SPEC.md.
// Numbers are the reversed in-game skill texts (skills.json level descriptions),
// cross-checked against Rules/SkillActions.java. `perLevel[rank-1]` holds the params for
// a trained rank. Skills not in this table are "known but inert" (a later pass).

export const SKILL_FX = {
  // ---- heals ----
  heal_wounds: { kind: 'heal', name: 'Heal Wounds', cls: 'CLERIC', cd: 8,
    perLevel: [{ mana: 6, heal: 30 }, { mana: 12, heal: 75 }, { mana: 18, heal: 125 }, { mana: 24, heal: 180 }] },

  // ---- caster damage spells (AoE / bolt) ----
  fireball: { kind: 'damage', name: 'Fireball', cls: 'WIZARD', cd: 3, type: 'Fire', radius: 2,
    perLevel: [{ mana: 4, dmg: [9, 18] }, { mana: 8, dmg: [15, 30] }, { mana: 16, dmg: [28, 56] }, { mana: 32, dmg: [42, 84] }] },
  lightning_bolt: { kind: 'damage', name: 'Lightning Bolt', cls: 'WIZARD', cd: 3, type: 'Shock', radius: 1,
    perLevel: [{ mana: 5, dmg: [10, 15], stun: 20 }, { mana: 9, dmg: [15, 20], stun: 25 }, { mana: 18, dmg: [20, 28], stun: 30 }, { mana: 24, dmg: [36, 44], stun: 40 }] },
  ice_storm: { kind: 'damage', name: 'Ice Storm', cls: 'WIZARD', cd: 3, type: 'Cold', radius: 2,
    perLevel: [{ mana: 4, dmg: [8, 16] }, { mana: 7, dmg: [12, 24] }, { mana: 12, dmg: [18, 36] }, { mana: 24, dmg: [32, 64] }] },
  sacred_fire: { kind: 'damage', name: 'Sacred Fire', cls: 'CLERIC', cd: 4, type: 'Spirit', radius: 2,
    perLevel: [{ mana: 3, dmg: [12, 12], undead: 8 }, { mana: 6, dmg: [24, 24], undead: 12 }, { mana: 9, dmg: [48, 48], undead: 20 }] },

  // ---- warrior / rogue melee actives (hit enemies in reach for weaponDmg·mult) ----
  whirlwind: { kind: 'melee', name: 'Whirlwind', cls: 'WARRIOR', cd: 6, area: true,
    perLevel: [{ mana: 0, mult: 1.5 }, { mana: 0, mult: 1.75, stun: 3 }, { mana: 0, mult: 2.0, stun: 5 }, { mana: 0, mult: 2.2, stun: 7 }] },
  charge: { kind: 'melee', name: 'Charge', cls: 'WARRIOR', cd: 6,
    perLevel: [{ mana: 0, mult: 1.6 }, { mana: 0, mult: 1.9 }, { mana: 0, mult: 2.25 }, { mana: 0, mult: 2.6 }] },
  bash: { kind: 'melee', name: 'Bash', cls: 'WARRIOR', cd: 5,
    perLevel: [{ mana: 0, mult: 0.5, stunSec: 1 }, { mana: 0, mult: 0.6, stunSec: 2 }, { mana: 0, mult: 0.75, stunSec: 3 }] },
  kick: { kind: 'melee', name: 'Kick', cls: 'ROGUE', cd: 4,
    perLevel: [{ mana: 0, mult: 0.5, slow: 3 }, { mana: 0, mult: 0.6, slow: 4 }, { mana: 0, mult: 0.75, slow: 5 }] },

  // ---- buffs (timed self modifiers) ----
  resilience: { kind: 'buff', name: 'Resilience', cls: 'WARRIOR', cd: 12,
    perLevel: [{ mana: 0, armor: 5, dur: 6 }, { mana: 0, armor: 9, dur: 8 }, { mana: 0, armor: 14, dur: 10 }] },
  holy_shield: { kind: 'buff', name: 'Holy Shield', cls: 'CLERIC', cd: 12,
    perLevel: [{ mana: 5, armor: 4, resist: { Death: 25 }, dur: 12 }, { mana: 5, armor: 6, resist: { Death: 40 }, dur: 12 }, { mana: 6, armor: 8, resist: { Death: 60 }, dur: 12 }, { mana: 6, armor: 10, resist: { Death: 80 }, dur: 12 }] },
  mage_armor: { kind: 'buff', name: 'Mage Armor', cls: 'WIZARD', cd: 10,
    perLevel: [{ mana: 3, armor: 6, dur: 180 }, { mana: 5, armor: 9, resist: { Fire: 15, Cold: 15, Shock: 15 }, dur: 180 }, { mana: 9, armor: 14, resist: { Fire: 20, Cold: 20, Shock: 20 }, dur: 180 }, { mana: 16, armor: 21, resist: { Fire: 30, Cold: 30, Shock: 30 }, dur: 180 }] },
  arbenos_might: { kind: 'buff', name: "Arbenos' Might", cls: 'CLERIC', cd: 12,
    perLevel: [{ mana: 2, dmgAdd: 3, dur: 12 }, { mana: 4, dmgAdd: 5, dur: 14 }, { mana: 7, dmgAdd: 8, dur: 16 }] },
  evasion: { kind: 'buff', name: 'Evasion', cls: 'ROGUE', cd: 12,
    perLevel: [{ mana: 0, dodge: 50, dur: 5 }, { mana: 0, dodge: 70, dur: 5 }, { mana: 0, dodge: 90, dur: 5 }] },
  // stab: a one-shot bonus on the next melee hit = round(level·mul)+add (SkillActions.k)
  stab: { kind: 'buff', name: 'Stab', cls: 'ROGUE', cd: 5,
    perLevel: [{ mana: 0, nextHit: { add: 6, mul: 1 } }, { mana: 0, nextHit: { add: 10, mul: 1 } }, { mana: 0, nextHit: { add: 10, mul: 1.4 } }, { mana: 0, nextHit: { add: 10, mul: 1.8 } }] },

  // ---- passives (folded into combat, never "cast") ----
  fury: { kind: 'passive', name: 'Fury', cls: 'WARRIOR',
    perLevel: [{ thr: 0.30, mul: 0.30 }, { thr: 0.35, mul: 0.50 }, { thr: 0.35, mul: 0.65 }] },
  mana_surge: { kind: 'passive', name: 'Mana Surge', cls: 'WIZARD',
    perLevel: [{ pool: 3 }, { pool: 6 }, { pool: 9 }] },
};

export const isActive = (id) => { const s = SKILL_FX[id]; return s && s.kind !== 'passive'; };
export const isPassive = (id) => { const s = SKILL_FX[id]; return s && s.kind === 'passive'; };

// Params for a skill at a rank (1-based), clamped to its defined ranks.
export function skillParams(id, rank) {
  const s = SKILL_FX[id]; if (!s) return null;
  const i = Math.max(0, Math.min((rank || 1) - 1, s.perLevel.length - 1));
  return s.perLevel[i];
}
export const skillManaCost = (id, rank) => { const p = skillParams(id, rank); return p ? (p.mana || 0) : 0; };
export const skillCooldown = (id) => (SKILL_FX[id] && SKILL_FX[id].cd) || 6;

// The active skills a model currently knows (learned at rank >= 1).
export function knownActives(model) {
  return Object.keys(SKILL_FX).filter(id => isActive(id) && model.skillRank(id) > 0);
}
export function knownPassives(model) {
  return Object.keys(SKILL_FX).filter(id => isPassive(id) && model.skillRank(id) > 0);
}
