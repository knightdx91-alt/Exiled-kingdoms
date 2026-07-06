# Combat + followers — recovered spec

Real-time-with-pause combat, reversed from the decompiled `net.fdgames` classes. This
pins the numbers the web build (`web/src/combat.js`) implements; deeper per-skill
interactions are tagged APPROX (see §Deviations).

## Source of truth
- `GameEntities/CharacterSheet/CharacterSheet.java` — outgoing damage (`i()`, `B()`,
  `y()`, `n()`), mitigation helpers (`g0`, `G`, `j`=armor, `k`=block).
- `GameEntities/Character.java` `R0(Damage,…)` — damage receipt (armor vs resistance).
- `GameEntities/Helpers/Damage.java` — 7 damage types.
- `GameEntities/AI/*` — `AI`, `PatrollerAI`, `CivilianAI`, `CompanionAI`, `IdleAI`.
- Data: `rules/weapons.txt`, `rules/bestiary.txt`, `rules/loot.txt`, `rules/skills*.txt`.

## Damage types (`Damage.DamageType`, ordinals)
`0 Normal · 1 Fire · 2 Cold · 3 Shock · 4 Death · 5 Toxic · 6 Spirit`.
Short codes in weapons.txt: `f`=Fire `c`=Cold `s`=Shock `d`=Death `t`=Toxic `sp`/`p`=Spirit,
anything else = Normal.

## Outgoing attack (`CharacterSheet.i()` / `B()` / `y()`)
- `dmgBonus = m()` — the attacker's flat damage bonus (attribute/class-derived; the web
  `PlayerModel.damage()` already computes the player's, `deobf/CHARACTER_STATS_SPEC.md`).
- **Normal hit:** `hp = randInt(m + weaponMin, m + weaponMax)`.
- **Crit** (rolls when `randInt(1,100) <= critChance`): `hp = randInt(m + weaponMax + 1,
  round(critMul/100 · (m + weaponMax)))`, `critMul = max(150, skill critDamageModifier)`
  → i.e. a crit lands between `max+1` and `1.5×max` (before bonuses).
- `critChance = n()` = `weapon.critChance` (%), modified by melee/ranged skill mods.
- Damage type = `weapon.damageType`.

### weapons.txt columns (0-indexed)
`0 Weapon_ID · 1 name · 2 min_damage · 3 max_damage · 4 crit_chance · 5 crit(200=2×) ·
6 Speed · 7 reach · 8 2-handed · 9 ranged · 10 light · 11 range_speed · 12 ranged_type ·
13 sprite · 14 damage_type · 15 secondary_damage · 16 secondary_type · …`
Attack cooldown ∝ `Speed` (`CharacterSheet.H() = speed · bonus`); lower = faster.
`reach` = melee tiles; `ranged=1` → projectile (uses `range_speed`).

## Damage receipt (`Character.R0` → `CharacterSheet.g0`)
- **Normal damage** (type 0): armor `J = sheet.j()` (+ `k()` block vs projectiles).
  `absorbed = randInt(J·f, J)` where `f = 0.4` if `Z()` (shield/heavy armor) else `0.2`.
  `taken = max(rawHp − absorbed, ceil(rawHp · 0.15))` — **≥15% of raw always gets through**.
- **Elemental damage** (types 1–6): `taken = g0(rawHp, resist%)`, then projectiles subtract
  `randInt(block/3, block)`.
- **`g0(hp, r)`** — percentage mitigation with diminishing returns above 50:
  ```
  if r == 0: return hp
  if r > 50:  r = r<=80 ? 50+(r-50)/2 : r<=110 ? 65+(r-80)/3 : 75+(r-110)/4
  if r >= 100: return 0
  return (100 - r) * hp / 100
  ```
- Elemental resist per type from `G(type)` → the character's resistance set;
  Normal returns 0 here (Normal is handled by the armor branch, not `g0`).

## bestiary.txt columns (0-indexed)
`0 spawn_ID · 1 race · 2 class · 3 minlevel · 4 maxlevel · 5 weapon_id · 6 defense(armor) ·
7 resist("Fire,Cold,Shock,Death,Toxic,Spirit" csv) · 8 movement · 9 attributes ·
10 loot(table) · 11 sprite · 12 Size · 13 Skillset · 14 AI · 15 faction · 16 gender ·
17 portrait · 18 onDieConditions · 19 onDieActions · 20 onAggroSound · 21 onDieSound`
Monster level is rolled in `[minlevel, maxlevel]`; HP/damage derive from race/class/level
via `CharacterStats` (already reversed in `CHARACTER_STATS_SPEC.md`).

## loot.txt (UTF-16) columns
`0 table · 1 item_id · 2 name · 3 chance(%) · 4 conditions`. `item_id == -2` → Gold;
positive → item id. On death, roll each row of the monster's loot table by `chance`.

## AI (`AI` base + subclasses)
- `PatrollerAI` — enemies: idle/patrol until an enemy is detected within sight, then
  chase (`D1(target)`) and attack (`E0(target)`) when within weapon reach.
- `CivilianAI` — flees / non-combatant.
- `CompanionAI` — followers: stay near the player, adopt the player's target
  (`detectedEnemyID`), move to reach and attack; `attackStrategy` picks aggressive/defensive.
- `IdleAI` — stationary.
Detection/attack use tile distance `b.s(a,b)` (Chebyshev-ish grid distance).

## Web implementation plan (`web/src/combat.js`)
Real-time-with-pause tick driven off the scene update loop:
- Each combatant has `hp/maxHp`, `armor`, `resist{}`, `weapon{min,max,crit,critMul,
  speed,reach,ranged,type}`, `attackCd`.
- **Attack** when target in reach and `attackCd<=0`: roll damage per above, apply `g0`/armor
  mitigation, subtract HP, spawn a floating damage number, reset `attackCd = speed·k`.
- **Enemies** (PatrollerAI) aggro within `AGGRO_TILES`, path to the hero (reuse `move.js`
  A*), attack in reach. **Followers** (CompanionAI) target the hero's target / nearest
  enemy, otherwise trail the hero.
- **Death** → play death, drop loot (gold to `PlayerModel.gold`, items listed), award XP
  (`PlayerModel.gainXP`), remove the actor.
- **Pause** (space / a HUD button) freezes ticks so the player can assess — EK's
  "real-time with pause".
Enemy spawns come from each map's `npcs`/`spawns` (already parsed) resolved through the
bestiary; the tutorial goblins are the first fight.

## Deviations (logged in DEOBFUSCATION_STATUS.md §3)
- Per-skill combat procs, secondary-damage weapons, status effects (fury, mage armor,
  bleeds) and the full skill tree are **not** modelled yet — only the base attack/mitigation
  loop. Tagged APPROX; skills land incrementally with the trainer system.
- `m()` uses the reversed player damage bonus; monster `m()` approximated from class/level.
- Attack-speed time base (`Speed` units) tuned to feel right; exact frame timing APPROX.
