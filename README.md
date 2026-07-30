# Exiled Kingdoms — recovery, web rebuild, and 4.2.2 mod

Working notes for the owner's Exiled Kingdoms project. **Read `CONTINUE_HERE.md` for the
full handoff**; this file is the quick orientation plus the findings worth not
re-deriving next session.

Three lines of work:

| Track | What | Where |
|---|---|---|
| **A** | Source recovery / de-obfuscation of the APK | `recovered/`, `port/`, `deobf/`, `tools/` |
| **B** | Phaser 3 browser rebuild (the product) | `web/` — verify with `cd web && node verify.mjs` |
| **C** | Mod of the owner's Android **4.2.2** APK (cheats, Hero class, Janod) | `tools/patch_*.py`, `tools/build_mod_4_2_2.sh` |

## Current mod build (Track C)

Latest: **hero-v10**. Download (assembled automatically by the Pages deploy):

```
https://knightdx91-alt.github.io/Exiled-kingdoms/dist/ExiledKingdoms-hero-v10.apk
```

Previous build (fallback):
`https://knightdx91-alt.github.io/Exiled-kingdoms/dist/ExiledKingdoms-hero-v9.apk`

Build it yourself:
```
EK_LIB=/tmp/eklib tools/build_mod_4_2_2.sh <base.apk> out.apk
```
Jars needed in `EK_LIB`: baksmali/smali 2.5.2, `apksig8.jar` (apksig 8.3.1).
`EK_SKIP_HERO=1` builds without the Hero class.

**APK distribution:** the repo's Git LFS budget is spent and GitHub rejects any file
over 100 MB, so built APKs are committed to `dist/<name>/` as 25 MB split parts, and
`.github/workflows/deploy.yml` reassembles them into `web/dist/` on deploy — that's
where the single-file download link above comes from. Base APK sha256
`5fc7c866…` (owner's Drive).

Working: install on 4.2.2, max-reputation + cheat items, export save, Janod companion,
Hero class + per-class skill pager. Still broken: **no-clip** (see below — it was the
wrong tool for the job anyway).

## Findings worth keeping

### Locked doors are conversations, not locks
A "locked door" is a **TMX `conversation` object** (`icon=door`), not a physical lock, and
the way through is a `Travel#map,entry` action inside the conversation. So no-clip can
never open one — the far side is a different map reached by a scripted action, not
adjacent geometry.

Of 38 door conversations: 22 have no condition on their first row; **16 are gated by a
game variable**, split 9 "locked line first" / 7 "open line first". So neither
"force all conditions true" nor "force all false" opens everything. The rule that does
work: while a cheat flag is on, **ignore conditions and take the branch that leads to a
`Travel#`** (falling back to "prefer the row whose Go To isn't 0" opens 15 of the 16).
Design + evidence: `deobf/` (to be written when built).

### Summoning — and how to get an actual army
`Summon#<spawn_id>,<level>,<seconds>` is a **native base-game dialogue action**
(`ScriptedAction$ActionType`, ordinal 0x52) — byte-identical in base and Sorrow Mod. It
needs **zero code changes**.

`SkillActions.a(caster, spawn, levelCap, secs)` branches on the caster:
* caster is the **player** (`uniqueID == 1`) → despawns the previous summon first →
  **strictly one at a time**;
* caster is **any NPC** → no despawn → **summons stack**.

That is why Sorrow Mod's hired necromancer can field a 5-strong undead army from
dialogue, while its *skills* that call the summon six times over (bats, lorettes) only
ever produce one creature — a modder bug.

So the owner's two options are independent: **Summon Familiar → skeletons** stays a
2-string edit (personal, one at a time), and a **skeleton army** is pure conversation
data on an NPC. Every creature needed (skeleton and its 8 variants, zombie, death
knight, lich) is already in the base bestiary. Full detail:
`deobf/SUMMON_SKELETON_SPEC.md`.

### The mods (`MOD_ANALYSIS.md`)
Both **Sorrow Mod 7.5** and **ENB** run stock game code (1.3.1182 vs the owner's
1.3.1207) — **no modder code at all**, so everything they do ports as data.
Sorrow Mod = expansion (+52 quests, +170 maps, +933 bestiary rows, 13 new skills,
2 new companions, hireable necromancers). ENB = overhaul (417 of 420 base bestiary rows
retuned, ~300 base conversations rewritten, heavy art/UI replacement).

### Elisse, the woman in New Garand's Farmlands — no missing quest
Map **H8** ("New Garand's Farmlands"). She is a **lore/signpost NPC and gives no quest**,
so there is nothing to finish. She sets `elisse_talked` (used nowhere else) and pushes
`want_letter_back` to 22, pointing you at the Magistrate in New Garand. Her "strange
screams… something dark is coming" line foreshadows the **`dark_matters`** quest, which
is complete and given by **Brother Gabriel** in New Garand (resolved at the H8 tomb
altar). The farmlands' other NPC, Horton, is the ambush ending `dont_shoot_messenger`.

## Project rules
See `CLAUDE.md` — **work happens directly on `main`**, no branches, no PRs. Reverse the
source before building a feature (`tools/trace_calls.py`, `deobf/CLASS_MAP.tsv`), and
write the recovered spec to `deobf/` before coding.
