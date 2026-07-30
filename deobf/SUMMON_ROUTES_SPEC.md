# Summon Familiar → three routes (v9) — reversed spec

Owner's ask: *"when you click and buy the skill the first time, you make the choice which
you do, then you just keep clicking the skill to upgrade it… you're not having to make
new buttons. And yes I want the higher end options, so it's viable when you hit higher
levels."*

Reversed against the base APK before coding, per the project rule. Supersedes the
"add three skills" sketch in `SUMMON_SKELETON_SPEC.md` §"open design decisions".

## 1. Why one skill, not three

The skill page is not free real estate. `SkillWindow` (`e/a/d/e/c0`) fills a 20-slot
array from two builders with hard index caps:

* basic class list — `const/4 v6, 0x7` + `if-gt v4, v6` → **8 slots**;
* advanced list — starts at `0x8`, `const/16 v12, 0xb` + `if-gt v4, v12` → **4 slots**.

`rules/skills2.txt` (the mage basic table) already holds exactly 8 skills, so there is no
free basic slot, and `skills_advanced2.txt` already holds 8 entries against 4 advanced
slots. Adding new skills therefore means widening those caps and re-flowing the grid.
Keeping the single existing `lesser_summoning` entry avoids all of it — which is also
exactly what the owner asked for.

## 2. Where the choice hooks in

`c0.e(Le/a/d/e/c0;)V` is the buy handler (the `+` on a skill):

```java
if (!skill.meetsRequisites(sheet)) { new d0(this, GameString("SKILL_REQUISITES_ERROR")).show(stage); }
else { sheet.skillSet.d(skill.id);   // <- the rank increment
       GameAssets.play("load"); …; rebuild(); }
```

At the requisites-OK label the patch inserts `c0.ekNeedsSummonRoute(c0)`, true only when
**all** of: the selected skill id is `lesser_summoning`; the sheet is the player's
(`CharacterSheet.W()`); its current rank is 0; and the saved route variable is 0. When
true it shows the choice dialog and returns without spending the point.

The dialog's `result(Object)` writes the route with `GameVariables.b(String,int)` — the
same setter `SetVariable#` uses, so it lands in the save like any quest variable — then
calls `c0.e(window)` again. The second pass fails `ekNeedsSummonRoute` (route now set) and
falls into the **untouched vanilla purchase path**, including the window's own refresh.
Every later click is plain vanilla too. No new buttons in the tree; one modal, once.

### The dialog
`e/a/d/e/eksp` extends the game's own `e/a/d/l1` (`SimpleDialog`), so it inherits the
game's skin, layout and font scaling. `l1` ships a single OK button; the subclass calls
`getButtonTable().clearChildren()` and adds one `e/a/d/u` button per route via stock
`Dialog.button(Button, Object)`, carrying an `Integer` route id. Overriding
`result(Object)` is stock scene2d — scene2d hides the dialog after the callback.

## 3. How a route becomes a creature

`Character`'s `lesser_summoning` dispatch passed literal bestiary ids:

```java
rank == 1 → SkillActions.summon(this, "familiar1", level<5?2:3, 120);
rank == 2 → SkillActions.summon(this, "familiar2", …,          120);
```

Two new statics on `SkillActions` replace those literals. Both take the caster and read
the rank and route themselves (`SkillSet.c(id)` and `GameVariables.b("summon_path")`),
because the rank register at the rank-2 site is clobbered by the level lookup before the
call — passing it would have meant register surgery.

| rank | 1 UNDEAD | 2 ARCANE (default) | 3 BEAST |
|---|---|---|---|
| 1 | `skeleton` (cap 5) | `familiar1` (cap 3) | `grey_wolf` (cap 5) |
| 2 | `skeleton_warrior` (cap 8) | `familiar2` (cap 6) | `wolf` (cap 8) |
| 3 | `skeleton_champion` (cap 11) | `golem_iron_lesser` (cap 11) | `bear_summoned` (cap 11) |
| 4 | `skeleton_hero` (cap 14) | `elemental_acid` (cap 14) | `wild_werewolf` (cap 14) |

**Why the arcane route is constructs, not elementals (v10).** Owner: *"arcane doesn't
really make sense with making it fire elementals because you can do the fire elementals
when you buy the skill from one of the trainers in the mages towers."* Correct, and
confirmed in `Character`'s dispatch — the trainer-bought masteries already summon
`fire_elemental_1/2/3` (Fire Mastery), `ice_elemental_1/2/3` (Ice Mastery) and
`elemental_earth_lesser`/`elemental_earth`/`golem_iron_1` (Earth Mastery). A v9 arcane
route ending in fire elementals was selling the trainer's own product. Constructs are
classic conjuration, overlap nothing (the `golem_iron_lesser`/`golem_iron` rows are
distinct from Earth Mastery's `golem_iron_1`). v11 caps the route with `elemental_acid`
at the owner's request — its sprite is `golem_green`, so it still reads as the next
construct up, and it brings [detector] and toxic damage that nothing else on the routes has.

**Why the beast route drops dire/spirit wolf (v11).** `e/a/d/m1` shows the cleric skill
**Guardian Wolf** summoning `dire_wolf` → `white_wolf` → `spirit_wolf`. A pure mage cannot
take that skill, but the Hero class in this mod bypasses class restrictions, so a Hero
would have been buying the same wolves twice. Beast ranks 2 and 4 are now `wolf` and
`wild_werewolf`, which nothing else summons.

**Not a duplicate, for the record:** no skill or base-game conversation summons any
`imp_*`. The eleven `Summon#imp_fire` calls live in Sorrow Mod (`demonessa.txt`) and one
in ENB (`magistr_lorain.txt`) — mods, not this base. The base game's entire scripted
summon set is `wolf`, `dire_wolf`, `golem_iron`, `fire_elemental_2`, `ice_elemental_2`,
`elemental_earth_lesser`.

`SkillActions` levels a summon to `min(cap, casterLevel) + summonerRank`, so a cap is a
ceiling, not a grant. An unset variable resolves to the ARCANE ladder, i.e. an existing
save that never picks a route behaves exactly like vanilla.

`skills2.txt` gains ranks 3 and 4 for the skill (cost 3, cooldown 30, mana 28/36) and the
descriptions name all three ladders.

## 4. The rank test — a trap worth recording

Ranks 3-4 needed the rank-2 branch, so its `if-ne rank, 2` becomes `if-lt rank, 2`.
The first attempt anchored on "the last `if-ne` before the id swap" and matched the
**rank-1** test instead. That build assembled and verified clean, and was wrong in two
silent ways: at rank 2 *both* branches fire (two summons at once — which now stack), and
at ranks 3-4 only the rank-1 branch runs, summoning a Skeleton Hero at the rank-1 level
cap of 3. Caught by reading the reassembled dex, not by any tool. The patch now anchors
the test as *the single `if-ne` lying strictly between the two call sites* and asserts
there is exactly one.

**Lesson:** `dexopt: OK` proves the class loads, never that the control flow is what you
meant. Diff the disassembled branch structure for any edit that moves a comparison.

## 5. Deliberate deviations (APPROX)
* The route is global (`summon_path`), so any caster using `lesser_summoning` follows the
  player's chosen path. No NPC has the skill by default, so this is currently player-only
  in practice.
* The choice is permanent — there is no respec. Rewriting `summon_path` from a console or
  a future trainer conversation would re-open it, since it is an ordinary game variable.
* Rank 3-4 creatures are `race=strong` bestiary rows; with v8's stacking summons and the
  ×5 duration, a rank-4 route is a genuine retinue rather than a single pet.
