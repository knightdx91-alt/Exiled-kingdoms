# Exiled Kingdoms — project handoff / continue here

Everything done so far, how to reproduce it, and what's planned. Start here.

The goal: **a browser-playable version of Exiled Kingdoms** (Chrome on Android),
offline-capable, with player-chosen orientation and browser-local saves — rebuilt
from the recovered game (the original project files were lost in a drive crash).

There are **two tracks** in this repo. Track B is the recommended path to a
shippable web game; Track A is the source-recovery that feeds it.

---

## What's NOT in git (and how to get it)

Kept out of the repo on purpose (size / third-party copyright). All re-derivable:

| Item | Where | How to restore |
|---|---|---|
| Base game APK (`Exiled Kingdoms.apk`, 130 MB) | owner's Google Drive | download, then `tools/extract.sh <apk>` |
| Recovered assets (~150 MB) + native libs | — | `tools/extract.sh <apk>` regenerates `recovered/assets` + `recovered/native` |
| `node_modules/` (web) | — | `cd web && npm install` |
| `.cache/` (gdx/JavaParser jars) | — | auto-fetched by the scripts |
| 3 mod APKs (Multiplayer, Sorrow, ENB) | owner's Google Drive | third-party — see `MOD_ANALYSIS.md` |

---

## Track B — Web rebuild (Phaser 3)   ← recommended, this is the product

**Location:** `web/`. **Status:** a playable vertical slice — start a character, walk
a seamless world, meet NPCs, and hold conversations. All verified in headless Chrome
(`cd web && node verify.mjs` → `VERIFY: PASS`, screenshots in `web/shots/`).

### DONE (proven by verify.mjs)
- **Foundation**: Phaser 3 (`web/src/main.js`, Phaser vendored in `web/vendor/`);
  player-owned **4-way orientation** (engine-rotated `world` container, input
  hit-tests through the rotation); **offline PWA** (`sw.js` + `manifest.webmanifest`,
  precaches the whole game from `asset-manifest.json`); **IndexedDB saves**
  (`src/saves.js`, export/import + an `auto` save of the created character).
- **Isometric maps**: `tools/tmx2json.mjs` converts every `.tmx` (iso tiles, external
  `.tsx`, base64+gzip/zlib layers, **object layer → transitions / entries / npcs /
  containers / triggers**) to JSON; `src/map.js` renders three depth planes with
  recovered ambient/day-night (`deobf/ENGINE_SPEC.md`, `CAMERA.md`). All **151 maps**
  converted.
- **Seamless streaming overworld** (`src/world.js`): the 53 `[outdoor][worldmap]`
  tiles live in one global cell space and stream in a small window around the hero —
  **no load screen between outdoor tiles**. Viewport **tile culling** via a sprite
  pool bounds draw cost to the camera (~600 tiles, not ~90k); residency is lean for
  mobile (1 chunk mid-map, up to 4 at a corner) with tileset-texture dedup. Towns/
  buildings/caves stay discrete, entered by walking onto a transition cell (an arch).
- **Movement**: `src/move.js` — collision grid (`GameMap.v()`), 8-way A*, iso
  cell↔pixel (+ global variants). Two control schemes via a toggle: **tap-to-move**
  and a **free-floating joystick** (`src/joystick.js`, reskinned to the game's real
  `touchpad_base/knob`). Facing/anim from `src/sprite.js` (9×11×140 sheets).
- **Game start** (`src/char-create.js`): title → character creation using EK's own
  assets (logo, intro backdrop, portrait arrows, real class/difficulty text) →
  begins in `I10_tutorial` (Adaon's road) with the gender-correct sprite.
- **Entities** (`src/entity.js`): NPCs/monsters from each map's spawns render as
  animated, depth-sorted actors (interior + streamed world), sprites resolved via a
  **bestiary index** (`tools/gen-bestiary.mjs` → `assets/data/bestiary.json`);
  composite paper-doll sprites fall back to their base layer.
- **Dialogue reader** (`src/dialogue.js`): parses EK conversation trees, shows NPC
  lines (portrait + Continue) and player choices, evaluates **conditions** and runs
  **actions** against a shared world state (global variables + party/followers) — the
  same store quests/triggers use. Tap an NPC to walk up and talk. Verified end-to-end
  in the tutorial (Adaon greets you, the tree branches, `NPCFollow#` adds him).
- **Deobfuscated stat model** (`deobf/CHARACTER_STATS_SPEC.md`): real numbers behind
  creation/level-up — 6 attributes (0–12, triangular cost), trait pool `2L+2`, skill
  pool `2L−1`, tier-1 skill cost 1 (+mana), per-class HP/mana/damage. Baked into
  `assets/data/creation.json` + `skills.json` (`tools/gen-creation.mjs`,
  `gen-skills.mjs`).

### TODO (roughly in order)
1. **Creation: traits + abilities pages** — attribute allocation (4 pts, triangular,
   0–12) and starting ability pick (1 pt, class skill list w/ mana), from
   `creation.json` / `skills.json`. *(Data ready; UI not built.)*
2. **Player model + HUD** — HP/mana/XP/level/gold/inventory from the deobfuscated
   formulas; responsive HUD that reflows tall↔wide. Persist `gameState` in saves.
3. **Combat + followers** — real-time-with-pause from the recovered AI/`Rules`
   (skills, weapons `weapons.txt`, bestiary, loot); companions physically follow/
   fight (followers are tracked in state but don't yet move). Enemies from
   `spawntables`. This is the big one — the tutorial goblin fight lives here.
4. **Quests + world state** — quest tracking + faction/event flags on `gameState`.
5. **Hero class + trainers** (design in flight) — a class that learns all skills via
   RuneScape-style trainer NPCs (dialogue actions set unlock flags), with
   trained-discipline gates for class-restricted equipment (`items.txt` Classes col).
6. **Render polish** — recovered roof-fade (A3) + fog-of-war (A5), dynamic lights (A1).

### UI fidelity note
Per the owner: every UI surface (creation screens, dialogue box, menus) must
eventually match the real game **exactly**. Current styling is an agreed placeholder;
match to a screenshot when building each screen.

Verify anytime: `cd web && node verify.mjs` (drives real Chrome, screenshots to
`web/shots/`, asserts start/creation, movement (tap + joystick), seamless world +
transitions, NPCs + dialogue, day/night, zoom, 4 orientations, offline, saves).

---

## Track A — Source recovery (de-obfuscation of the APK)

**Location:** `recovered/` (raw decompiled source), `port/` (buildable subset),
`deobf/` (maps + analysis), `tools/` (the toolchain). **Status:** 154 of 499
classes compile against stock libGDX; the whole game logic/data/entity model is
recovered and readable.

Reproduce the whole thing from the APK:
```
tools/extract.sh <ExiledKingdoms.apk>     # decompile + extract assets
tools/rebuild_core.sh <decompiled/sources> # apply all de-obfuscation transforms
tools/verify_core.sh                       # compile the known-good set -> GREEN: 154/499
```

The pipeline (`tools/rebuild_core.sh`) in order: select game packages → stub the
platform (`port/stubs/`) → reconstruct broken enums → remap obfuscated libGDX
classes → jadx-artifact hand-fixes → widen `private` → JavaParser symbol solver
(type-aware method-call de-obfuscation).

Key tools:
- `tools/triage.py` — **run this first each session.** Dependency-aware: ranks
  failing classes by unblock impact + groups by error type. `python3 tools/triage.py port/core/src/main/java <errs.txt>`.
- `tools/remap_gdx.py` — obfuscated libGDX class/import/header remapper.
- `tools/javaparser/Remap.java` + `deobf/method_lookup.tsv` — symbol-solver
  method-call de-obfuscation; logs unmapped calls to extend the map.
- `tools/match_more.py`, `tools/fix_enums.py`, `tools/widen_private.py`.

Docs in `deobf/`: `METHOD_RECOVERY.md`, `R8_OPTIMIZATION.md`,
`REMAINING_BLOCKERS.md`, `ROUTE1_SYMBOL_SOLVER.md`, `TRIAGE.md` (via `tools/`).

**Honest state:** the remaining ~345 classes are the render/asset/UI tier, where
R8 optimization merged classes and hints mislead (e.g. `l` claimed
`GdxNativesLoader` but was a logger; `r/d` claimed `AssetManager` but is a custom
wrapper). It's slow, correctness-critical, per-class reconciliation — and it's the
layer you'd rewrite for web anyway. **This is why Track B (rebuild) is
recommended over finishing Track A.** Track A's value is as a complete, readable
*specification* of the game's behavior.

---

## The mods
Three third-party mods were analyzed/collected — see `MOD_ANALYSIS.md` (Multiplayer
mod fully diffed; Sorrow Mod + ENB collected, not yet diffed). The base game is the
owner's; the mods' original additions belong to their creators (winlatorbrasil,
Sorrow Mod, ENB authors) — permission needed to ship any of it.

---

## Repo map
```
web/                Track B — Phaser 3 web rebuild (the product)
  web/src/          main.js (scene), world.js (seamless streamer), map.js, move.js,
                    sprite.js, entity.js (NPCs), dialogue.js, char-create.js,
                    joystick.js, saves.js
  web/tools/        tmx2json, gen-manifest, gen-bestiary, gen-skills, gen-creation,
                    gen-world-grid
  web/assets/       tmx/ (151 maps), sprites/, portraits/, conversations/, ui/,
                    data/ (bestiary, skills, creation, world-grid)
  web/verify.mjs    end-to-end headless-Chrome check (keep green)
recovered/src/      Track A — decompiled game source (readable spec)
port/core/          Track A — buildable subset (154 green) + port/stubs
deobf/              de-obfuscation specs (ENGINE, UI, CAMERA, CHARACTER_STATS, …)
tools/              Track A toolchain (extract, rebuild, triage, remappers, verify,
                    trace_calls — obfuscated call-graph tracer)
MOD_ANALYSIS.md     what the Multiplayer mod changed
REVERSE_ENGINEERING.md  original recovery writeup
CONTINUE_HERE.md    this file
```

## Continuing on another account
Repo: `knightdx91-alt/Exiled-Kingdoms`, branch `main` (main-only, no branches/PRs —
see CLAUDE.md). Clone it, put the base APK somewhere, run the `tools/*.sh` scripts to
rebuild Track A, and `cd web && npm install && node verify.mjs` for Track B.
Recommended focus: Track B TODO #1 (creation traits + abilities pages — data is
already in `web/assets/data/creation.json` + `skills.json`).
