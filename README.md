# Exiled Kingdoms — recovery, web rebuild, and the Android mod

Working notes for the owner's Exiled Kingdoms project. **Read `CONTINUE_HERE.md` for the
full handoff**; this file is the quick orientation plus the findings worth not
re-deriving next session.

Three lines of work:

| Track | What | Where |
|---|---|---|
| **A** | Source recovery / de-obfuscation of the APK | `recovered/`, `port/`, `deobf/`, `tools/` |
| **B** | Phaser 3 browser rebuild (the product) | `web/` — verify with `cd web && node verify.mjs` |
| **C** | Mod of the owner's APK (Hero class, companions, summoning) — built for the **4.2.2 tablet** *and* for a **Galaxy Z Fold 8** | `tools/patch_*.py`, `tools/build_mod_4_2_2.sh`, `tools/build_modern_compat.sh` |

## Current mod builds (Track C)

Latest: **hero-v19**, in two device flavours with the same feature set and the same signing key.

| Device | APK | Built by |
|---|---|---|
| **Galaxy Z Fold 8** (Android 16, 64-bit-only, foldable) | `ExiledKingdoms-hero-v19-fold.apk` | the tablet APK, then `tools/build_modern_compat.sh` |
| Owner's Android **4.2.2** tablet | `ExiledKingdoms-hero-v19.apk` | `tools/build_mod_4_2_2.sh` (universal since v19: both ABIs) |

Downloads (assembled automatically by the Pages deploy):

```
https://knightdx91-alt.github.io/Exiled-kingdoms/dist/ExiledKingdoms-hero-v19-fold.apk   <- Galaxy Z Fold 8
https://knightdx91-alt.github.io/Exiled-kingdoms/dist/ExiledKingdoms-hero-v19.apk        <- 4.2.2 tablet
```

What's in it (each reversed first; specs in `deobf/`):
* **Hero class** + per-class skill pager (`HERO_CLASS_MOD_SPEC.md`)
* **Janod** as a full mage companion — recruit, dismiss, gear, companion-grade stats
* **Companions come home** to their own spawn point when dismissed
* **Mage AI** — wizards actually cast (the engine had no WIZARD branch at all), and as of
  v18 they use the full offensive/summon kit, not 4 spells
* **Two companions** at once; dismissing one promotes the other
* **No companion XP tax** (vanilla quietly took 20% of everything)
* **Summon Familiar → 3 routes** (Undead / Arcane / Beast), chosen on first purchase,
  4 ranks each; summons **stack**, last 10 minutes, and **gain XP and level** while alive
* v15–v17: five owner-reported fixes, two character-Details crashes, orphaned cheat items
  purged, Hero mana bar

### The Fold 8 build — what makes it different
Four walls stopped the tablet APK dead on a modern phone; all four are measured, not
assumed (`deobf/MODERN_DEVICE_COMPAT.md`):
1. **ABI** — the APK shipped **`armeabi-v7a` natives only** and the Fold's SoC is
   64-bit-only → `INSTALL_FAILED_NO_MATCHING_ABIS`. Fixed in **v19** by adding
   **libGDX 1.9.12 `arm64-v8a`** natives, verified as a drop-in (identical 58 + 266 JNI
   symbols; 64 KB-aligned, so 16 KB-page devices are fine). `build_modern_compat.sh`
   re-checks both properties on every build and fails on a mismatch.
2. **Foldable** — `MainActivity` didn't handle `screenLayout`/`smallestScreenSize`, so
   **every fold or unfold destroyed the activity** and libGDX restarted the game at the
   title screen → `configChanges` widened to `0x40003ffc` (a 4-byte in-place manifest edit).
3. **External storage** — `/sdcard` needs a runtime grant on Android 11+ that this 2023
   build never asks for, so save export, save import *and* the crash log all failed into a
   silent catch → `tools/patch_modern_device.py` asks for it once at startup and falls back
   to the app-private external dir when refused, so they always land somewhere real.
4. **Signature** — signed **v1+v2+v3** (verified API 16–36) with the same committed
   keystore as v19, so the Fold build installs straight over v19 with no uninstall.

**Installing it on the phone** — three Samsung-specific gotchas that are not the APK's
fault: turn off **Auto Blocker** (Settings → Security and privacy → Auto Blocker), which
blocks sideloading outright on One UI 6.1+; **uninstall any Play-Store copy of Exiled
Kingdoms first** (same package name, different signing key → "App not installed"; export
its save first); and after the first launch, allow **Files/media** permission if you want
`EK.bak` under `/sdcard/Download`.

Build them yourself:
```
EK_LIB=/tmp/eklib tools/build_mod_4_2_2.sh <base.apk> ExiledKingdoms-hero-v19.apk
EK_LIB=/tmp/eklib tools/build_modern_compat.sh ExiledKingdoms-hero-v19.apk \
                                               ExiledKingdoms-hero-v19-fold.apk
```
Jars needed in `EK_LIB`: baksmali/smali 2.5.2, `apksig8.jar` (apksig 8.3.1) —
`build_modern_compat.sh` fetches everything it needs itself.
`EK_SKIP_HERO=1` builds without the Hero class.
`EK_CHEATS=1` re-adds the cheat items (Tome of Renown, Phase/Anchor Stone) and no-clip,
which are out of the default build as of v13.
`EK_KEEP_CONFIGCHANGES=1` reverts the Fold build to restart-on-fold behaviour.

**APK distribution:** the repo's Git LFS budget is spent and GitHub rejects any file
over 100 MB, so built APKs are committed to `dist/<name>/` as 25 MB split parts, and
`.github/workflows/deploy.yml` reassembles them into `web/dist/` on deploy — that's
where the single-file download links above come from. Base APK sha256
`5fc7c866…` (owner's Drive).

Working (all statically verified, none device-confirmed since v6): install on 4.2.2, Janod
as a full mage companion, Hero class + per-class skill pager, companions coming home when
dismissed, wizard AI, stacking summons, a second companion, the 3-route Summon Familiar,
no companion XP tax, summons that level. Cheat items + no-clip were **removed** at the
owner's request in v13 (`EK_CHEATS=1` restores them). Export save is shipped but was never
confirmed on device.

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
