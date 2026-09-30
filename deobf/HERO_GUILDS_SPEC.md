# Hero joins every guild — spec (v72)

Owner (tester suggestion): "you should be able to join any guild as a hero, or at least learn their advanced
skills". Reversed from the shipped data (`assets/data/conversations/*`) and `net.fdgames.GameLogic.Condition`.

## Reversed: how guild membership works
- Membership = a game variable per guild: `guild_warriors`, `guild_seventh`, `guild_wizards`, `guild_three`
  (the engine also knows `guild_golden`, `guild_loreseekers`; no data ever sets them).
- `PlayerHasGuild#` (Condition case 35) = any of the six variables > 0. It is the **one-oath rule**: every join
  conversation's refusal line "I am afraid you are already loyal to another guild…" carries it.
- `PlayerIsClass#x` / `PlayerIsntClass#x` compare `Rules.c(x)` with the player's class. `Rules.c("warrior")` is the
  Warrior slot = **our Hero** (`HERO_CLASS_MOD_SPEC.md`).
- Class gates on joining: the Wizard's Guild refuses `PlayerIsntClass#wizard` (Ilemma, node 51); the Church of the
  Three only lets `PlayerIsClass#cleric` in (bishop Q51; archbishop A102; hall bishops A32). Warrior's Guild and
  Seventh House have no class gate.
- Guild trainers only train members (greeting `VariableEqual#guild_x,1` → node 60 "train me" → `TrainSkill#…`):

| Guild | Join NPC (file) | Trainer skills |
|---|---|---|
| Warrior's Guild | Daukar (`NG_warriors_daukar`) | Duel, Heavyhand |
| Seventh House | Kardagis (`NG_sewers_kardagis`) | Assassinate, Poison Master |
| Wizard's Guild | Ilemma (`IM_ilemma`) | Disintegrate, Earth Mastery, Arcanist |
| Church of the Three | bishop (`NG_temple_bishop`), archbishop/hall bishops (`NI_hall_*`) | Turn Undead, Retribution; Battle Prayer, Flames of Faith |

- `TrainSkill#` itself already lets the Hero learn any advanced skill (ClassRestriction bypass), so membership is
  the only thing missing.

## Change (data only; every other class is untouched)
`tools/hero_guilds.py`, run by `build_mod_4_2_2.sh` after the MP content merge, on the root file and all 8
language copies (`CZ DE FR IT PL PT RU TR`) of the 7 files above (each copy carries its own conditions column):
1. Every `PlayerHasGuild#` refusal row → `"PlayerHasGuild#;PlayerIsntClass#warrior"`: the Hero may take every oath.
2. Ilemma's `PlayerIsntClass#wizard` refusal → `"PlayerIsntClass#wizard;PlayerIsntClass#warrior"`.
3. Every `PlayerIsClass#cleric` row in the Church files → followed by an identical row with
   `PlayerIsClass#warrior` (Q rows: the Hero gets the cleric's line; A rows: the Hero gets the cleric's option —
   the two conditions never both hold, so nobody sees a duplicate).
Membership quests, oath text and rewards are the game's own. The Church's line "you won't be able to join other
guilds" still reads the same; for the Hero it is not enforced (APPROX, `DEOBFUSCATION_STATUS.md` §3).
