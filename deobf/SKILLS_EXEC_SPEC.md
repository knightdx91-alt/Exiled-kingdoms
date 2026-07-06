# Skill / spell execution — spec (combat layer 2)

Makes learned skills *do* things. Reversed from `Rules/SkillActions.java` (per-skill
methods) + `Character.java` spell casting + the `skills.json` level texts, which are the
in-game skill descriptions and thus the authoritative numbers. Cross-checked against
`SkillActions`: `heal_wounds` = 30/75/125/180 (`c()`), `resilience` = +5/6s, +9/8s, +14/10s
(`j()`→`t1(armor,secs)`), `stab` = next-hit +{lvl+6, lvl+10, lvl·1.4+10, lvl·1.8+10}
(`k()`→`w1()`). Spell damage: casters build a `Damage` from level-scaled ranges (some via
weapon-stats entries e.g. `lightning_bolt1..4`, most inline INT-scaled).

## Execution model (`Character` cast path + `skillSet.s()` cooldown)
An **active** skill: check learned (`skillSet.g(id) > 0`) → check off-cooldown & enough
mana → spend `mana_cost` (`missingMana += cost`) → apply effect → start cooldown
(`skillSet.s(id)`). Level = trained rank (1..4). **Passive** skills apply continuously in
the combat math.

## Effect archetypes (what the web engine implements)
- **heal** — restore HP to self, then allies (`heal_wounds`: 30/75/125/180).
- **damage** — spawn a `Damage(type)` at a target/area; ours: hit enemies within a small
  radius of the target cell, mitigated by the normal `g0`/armor path. Optional `stun`.
  (`fireball` Fire AoE, `lightning_bolt` Shock +stun, `ice_storm` Cold AoE, `sacred_fire`
  Spirit +undead bonus.)
- **melee** — hit enemies within weapon reach for `weaponDmg · mult` (+ optional stun/
  push). (`whirlwind` all-around, `bash`, `charge`, `kick`.)
- **buff** — timed self modifiers folded into combat: `+armor`, `+resist{type}`, `+dmgAdd`,
  `dodge%`, or a one-shot `nextHit` bonus. (`resilience`, `holy_shield`, `mage_armor`,
  `arbenos_might`, `evasion`, `stab`.)
- **passive** — always-on combat modifiers: `fury` (<30–35% HP → +30/50/65% damage),
  `mana_surge` (+mana pool). Others (masteries, sneak_attack, crusader…) are stubs.

## Data (grounded values → `assets/data/skill-fx.json`, `tools/gen-skillfx.mjs`)
Per implemented id: `{ kind, name, class, cooldown, perLevel:[{ mana, …params }] }` where
params are the reversed numbers (damage `[lo,hi]`, `type`, `stun`, `heal`, `armor`,
`resist`, `dmgAdd`, `dur`, `dodge`, `mult`). Mana/cost pulled from `skills.json`; effect
kind + numeric params authored from the reversed texts (documented above). Skills not in
the table are "known but inert" (their passive/active effect is a later pass).

## Web implementation
- **`src/skills.js`** — the `skill-fx` table loader + `SkillEngine` helpers: `actives(model)`
  (learned active skills), `canCast(model, id)` (learned + mana + off-cooldown),
  `manaCost/cooldown/params`.
- **`src/combat.js`** — `castSkill(id)`: validates, spends mana, applies the effect
  (heal/damage/melee/buff) using the existing targeting + `mitigate`, sets a per-skill
  cooldown, floats text. Active **buffs** live on the hero and tick down; `_heroCbt` folds
  buff `+armor/+resist/+dmgAdd` and passive `fury`/`mana_surge` into the hero's combat
  stats; hero **dodge** is rolled when defending; **stab** `nextHit` adds to the next attack.
- **`src/hud.js`** — a skill/quickslot bar of the hero's learned actives; tap to cast
  (greyed when on cooldown or out of mana). Quick slots persist in the save.

## Deviations (DEOBFUSCATION_STATUS.md §3)
- A representative, high-value set is implemented (heals, the four caster spells, the main
  warrior/rogue actives, the core buffs, `fury`/`mana_surge`). Summons, stealth, traps,
  most masteries, procs, and exact projectile travel/animation are **not** modelled yet —
  tagged APPROX; skills outside the table are inert. Cooldowns use the reversed
  `skills.txt` value where present, else a per-kind default.
