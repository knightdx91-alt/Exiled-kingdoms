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
1. ~~**Creation: traits + abilities pages**~~ ✅ DONE (`src/char-create.js`). After
   name/gender/class/difficulty, an **Attributes** page allocates 4 points across the
   6 attributes (0–12, triangular cost from `creation.json` `traitLadder`, pool
   enforced) and a **Starting Ability** page picks 1 skill from the class list +
   GENERAL (`skills.json`, Active/Passive + mana tags, optional). The created PC now
   carries `attributes` + `startingSkill`; both persist into the auto-save. Styled to
   the EK gold-on-stone look.
2. ~~**Player model + HUD**~~ ✅ DONE (`src/player.js`, `src/hud.js`; spec
   `deobf/HUD_SPEC.md`). `PlayerModel` derives level/maxHP/maxMana/damage from the
   created character via the recovered formulas + XP table (max level 25) + start gold
   18; the HUD shows portrait, HP/mana(caster)/XP bars + level + gold, action buttons,
   and a Character panel (attributes + derived stats); persisted in the save.
   *(Styling is placeholder EK-theme; exact GameHUD art is a later fidelity pass.
   Inventory/quickslots come with combat.)*
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

---

## Session log — 2026-07-05 (mobile UI + tutorial exit) — READ THIS FIRST

Everything below is **live on `main`** and deployed to GitHub Pages
(`https://knightdx91-alt.github.io/Exiled-kingdoms/`). All tested with
`cd web && node verify.mjs` (PASS) unless noted.

### ⚠️ Versioning / cache — HOW TO KNOW A DEPLOY LANDED
- **A visible build tag `#ek-build` shows in the bottom-left corner** (currently
  **v8**). It is the owner's ground truth that a fresh copy loaded.
- **On EVERY web change bump BOTH:** `web/index.html` `#ek-build` text AND
  `web/sw.js` `const VERSION` (keep them in sync, e.g. v8 ↔ ek-v8). Tell the owner
  the new number when you push.
- The service worker (`web/sw.js`) is now **network-first for app code**
  (html/js/css/webmanifest via `SHELL_RE`) so new deploys show up online; the big
  game assets stay cache-first for offline. `main.js` adds a `controllerchange`
  **auto-reload** (only when a controller already existed) + `registration.update()`
  so future updates self-apply. The owner was repeatedly stuck on an old
  cache-first SW — if it happens again, tell them to load in a **private tab** (no
  SW) or clear site data; it self-heals from v7+.
- **Deploy flakiness:** GitHub Pages sometimes fails the deploy at "syncing_files"
  with `Deployment failed, try again later` — this is GitHub-side, not the code.
  Re-run the workflow (or push again). Verify live with
  `curl -s .../index.html | grep ek-build`.

### Mobile UI — orientation, Settings, HUD (all in `web/`)
- **Orientation is device-lock first.** The 4 orientation buttons call the
  **Screen Orientation API** (`screen.orientation.lock('landscape-primary'|…)`),
  requesting fullscreen first on non-standalone browsers (Android Chrome tabs need
  it; installed PWAs don't). On success the whole page (game + HUD + Settings +
  creation) is really that orientation and fills the screen with correct input.
  Where the API is unavailable it **falls back** to engine rotation (`this.orient`
  rotates the Phaser `world`) + a CSS class `ek-rot-90/180/270` that rotates the
  DOM overlay layer. See `setOrient()` / `_lockOrientation()` in `src/main.js`.
  The **Auto** button was removed (it wrapped the row and hid reverse-portrait);
  device-follow is still the default until a lock is chosen. 4 buttons:
  portrait/landscape/reverse-portrait/reverse-landscape.
- **`#overlay-root`** (new, in `index.html`) wraps ALL DOM game overlays (HUD,
  dialogue, joystick, character-creation). It's `position:fixed; inset:0;
  pointer-events:none` over the canvas; interactive children (`#dlg`, `.cc-overlay`,
  `#stick-layer`) re-enable pointer-events. The engine-rotation fallback CSS-rotates
  this wrapper as a unit so the **HUD rotates with the game** (the canvas stays
  axis-aligned so Phaser input works). HUD/dialogue/joystick/creation are attached
  to `#overlay-root` (was `#game-root`).
- **Settings panel** (gear `#settings-btn` → `#settings-panel`): holds the
  Orientation buttons (`#orient-bar`) and the Movement scheme (`#control-bar`:
  Tap / Joystick). Replaces the old always-visible orient bar + control toggle.
  `_wireSettings()` in `main.js`; movement choice persists in `localStorage.ek_control`.
- **HUD action buttons** (character/inventory/menu, `.hud-btns`) moved to the
  **right** side.

### ▶ Tutorial exit / "can't walk to Lannager" — DESIGN DECISION (owner-confirmed)
**Symptom:** new game starts in `I10_tutorial` (Adaon's road); the player couldn't
get anywhere and got stuck. **Findings (verified):**
- `I10_tutorial` is loaded as a **standalone interior** (NOT in `world-grid.json`,
  which has chunks E9…I10 etc.; the tutorial is a disconnected copy of I10).
- Its `transitions: []` and `edgeExits: {}` are **empty** — and `edgeExits` isn't
  even handled in code. So the map has **no walk-out exit**. Collision data is
  fine (flood-fill from the start entry `0001`=c85,r41 reaches the whole map incl.
  the west edge; ~8817 cells).
- The **only intended exit** is the camp: two `triggers` in the map start Adaon's
  conversations; walking into the west camp zone (`c0-13,r29-45`, gated by
  `VariableLower#want_letter_back,10`) starts `adaon_tutorial_camp`, whose node 50
  ("Good night, friend") runs:
  `NPCStopFollowing#adaon_tutorial; StopRender#; Travel#H10,1,14; Sleep#;
   SetVariable#want_letter_back,10; PlayerRobbed#`.
- The dialogue engine (`src/dialogue.js`) implements `Travel#map,entry`
  (`goArea`) and a few verbs, but **does NOT implement `Sleep#`, `StopRender#`,
  `PlayerRobbed#`** (they're silently ignored). `Travel#H10,1,14` → `goArea('H10','1')`
  but H10 has no entry `'1'` (only `2/3/999/0001`), so it falls back to map-centre.

**DECISION (owner):** keep the tutorial **instanced / one-time** exactly like the
real game — you camp, **sleep, screen fades to black, you're robbed, and wake up in
the world (H10) near Lannager**, and never return (the `want_letter_back=10` set is
the gate). A tried west-edge-transition hack was **reverted** (it let you wander back
and skipped the sleep beat — wrong).

**TODO to finish this (next):**
1. Implement the sleep/wake actions in `dialogue.js` `runActions`:
   - `StopRender#` / `Sleep#` → a **fade-to-black overlay + short delay** (screen
     goes black like the real game), then continue.
   - `PlayerRobbed#` → take the player's gold/starting items (story beat).
   - Make the action ORDER safe: `Travel#` currently `this.end()`s and changes area
     mid-list, so later actions (`SetVariable#want_letter_back`, `PlayerRobbed#`)
     may not run — reorder so the fade + variable-set + robbery happen, THEN travel,
     or make `runActions` finish the whole list before the area swap.
   - Fix the H10 entry: use `Travel#H10,0001` (existing east-edge entry `c86,r41`,
     geographically correct — you leave I10 west → enter H10 east → walk west to
     Lannager) instead of the non-existent `'1'`.
2. Make the camp reliably reachable/triggering (it is reachable; verify the trigger
   fires when walking the road, and that the conversation can't be closed early
   leaving the player trapped with no exit).
3. Then confirm the seamless world path H10 → … → Lannager is walkable.

### ▶ In-game character / inventory screen — TODO (owner wants it, has a reference)
- Owner: **remove the bottom HUD buttons**; clicking the **portrait** should open a
  full character/inventory screen like the real game (they sent a screenshot: Petra
  Devini, Cleric — Traits/attributes, Attack Stats, Armor, Resistances, equipment
  paper-doll, Backpack grid, Quick Slots, Journal/Skills/Reputation/Details,
  Unequip/Back, gold).
- **Faithfulness:** trace the real screen first (`InventoryScreen`/`CharacterSheet`
  cluster in the decompiled sources; `python3 tools/trace_calls.py`; `deobf/`).
  Write the spec to `deobf/` before building.
- **What's buildable now vs blocked:** attributes/derived-stats/resistances/skills/
  gold exist (`PlayerModel`, `deobf/CHARACTER_STATS_SPEC.md`) — the stats side of the
  screen can be built. The **equipment paper-doll + backpack + quick-slots need the
  item/inventory system**, which doesn't exist yet (comes with Combat, TODO #3).
  So: wire portrait → open the panel, build the stats half faithfully, stub the
  item half until the inventory system lands. The current `src/hud.js`
  `renderCharacter()` panel is the seed.

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
