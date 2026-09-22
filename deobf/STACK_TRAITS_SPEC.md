# v21 — owner requests: Hero trait mana, "Summon" name, Arcane ranks 3-4, stacking trait items

Reversed against the owner's 4.2.2 base (sha256 `5fc7c866…`) before coding. Build:
`EK_LIB=/tmp/eklib tools/build_mod_4_2_2.sh <base.apk> ExiledKingdoms-hero-v21.apk`, then
`tools/build_modern_compat.sh` for the Fold.

## 1. Hero mana ignores INT / PER — `CharacterSheet.C()I` (`tools/patch_hero_class.py` §2d)
Max mana = `trait term + Mana Surge term + CharacterStats.g() (level*2+12 for casters/Hero) + item mana`.
The trait term (`v3`) in vanilla:

| class | trait term |
|---|---|
| WIZARD (`e`) | `level * (INT + 2 + PER/2)` |
| CLERIC (`d`) | `level * (PER + 2 + INT/2)` |
| anything else, incl. the Hero (`b`) | **0** |

`INT = SheetBonus.a(traits, 3, …)`, `PER = SheetBonus.a(traits, 5, …)` (base + items + effects),
`level = z()` (= `CharacterStats.e()`). So a Hero's INT/PER never moved the pool and Mana
Surge was the only growth. **Fix:** Hero gets the better of the two formulas,
`level * (max(INT,PER) + 2 + min(INT,PER)/2)` — a pure-INT Hero matches a Mage, a pure-PER
Hero matches a Cleric. Inserted at the join after the cleric branch (where `v0`=INT,
`v2`=PER are still intact for a non-cleric); `v6` is reset to 2 because the Mana Surge
ladder compares against it.

## 2. "Lesser Summoning" → "Summon" — `Skill.<init>` (`tools/patch_summon_routes.py` §6)
`Skills` loader passes column 0 (English name) as BOTH the id source and the display name;
the constructor builds `id = lower(name).replace(' ','_')`. Renaming the row would change the
id from `lesser_summoning` (saves, route patch, `Character` rank dispatch, wizard AI all key
on it). So the constructor swaps only `Skill->name` (read solely by the getter `b()`) when it
equals "Lesser Summoning". Spanish/other localized names are untouched.

## 3. Arcane route ranks 3-4 — `tools/patch_summon_routes.py` `ROUTES[2]`

> **Superseded in v22:** owner rejected the beasts; ranks 3-4 are now `elemental_acid` →
> `elemental_acid_epic` ("Animated Waste"). See `SUMMON_ROUTES_SPEC.md` §7. v21 notes kept below.

v10-v20: `golem_iron_lesser` (sprite `golem`) → `elemental_acid` (sprite `golem_green`), which
read as Earth/Iron. Owner: last two must not be Earth/Iron, Fire or Ice. Now:

| rank | creature | row facts |
|---|---|---|
| 3 | `wyvern` (cap 11) | strong, lvl 10-12, `wyvern_claw`, sprite `wyvern` |
| 4 | `manticore` (cap 14) | strong, lvl 14, `manticore` weapon, sprite `manticore`, [beast] |

Both are existing base rows with names in `bestiary_names.txt`; no other skill, route or
base-game script summons either. Route dialog text and skills2.txt rank descriptions updated.

## 4. Trait items stack — `CharacterInventory` (`tools/patch_stack_traits.py`)
`u()V` rebuilds `traits:[I` (6 traits) on each equip change, taking the **max** of each
trait over 12 slots (body seeds it; head, hands, legs, feet, mainhand, offhand if not
two-handed, ring, ring2, belt, cloak, necklace compare `if-le v3, v2` then reload).
Patch: each compare becomes `if-lez v3` + `add-int/2addr v2, v3` → **sum of the positive
bonuses**. Negatives are still ignored, same as vanilla. `r()Z` (the "more than one item
boosts the same trait" test behind the `MSG_ITEM_NOT_STACK` popup in `e/a/d/e/h`) now
returns false. `texts.txt HELP_ATTRIBUTE_ITEM` reworded. Applies to every character sheet
(player and companions).

## Verification (off-device)
Whole dex: D8 `--min-api 15` (tablet) and `--min-api 24` (Fold) clean, zero diagnostics.
baksmali diff vs a byte-for-byte rebuild of v20 shows only the intended methods changed.
**Update install:** v21 and v21-fold are signed with the same committed key as v20
(cert `53:8B:43:22:68:A6:81:6A…`), and their `AndroidManifest.xml` is byte-identical to
v20's (same package + versionCode), so each installs straight over v20 as an update.
Not playtested on device.
