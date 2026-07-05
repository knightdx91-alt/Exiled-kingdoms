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

**Location:** `web/`. **Status:** foundation built and verified in headless Chrome.

Done and proven (`cd web && node verify.mjs` → `VERIFY: PASS`):
- Phaser 3 project (`web/src/main.js`), Phaser vendored in `web/vendor/`.
- **Player-owned 4-way orientation** (portrait / landscape / reverse-portrait /
  reverse-landscape). Engine-driven, not browser auto-rotate: canvas stays upright
  (so touch input is correct) and all content sits in one `world` container we
  rotate in-engine. Input hit-tests correctly through the rotation — verified.
- **Offline PWA**: `web/sw.js` service worker + `web/manifest.webmanifest`.
  Precaches the shell; on `CACHE_ALL` caches the **entire** game uncompressed from
  `asset-manifest.json` (regenerate with `node tools/gen-manifest.mjs`). Bookmark
  / "Add to Home Screen" → plays fully offline. Verified: network cut → reload →
  game still boots.
- **Browser-local saves**: `web/src/saves.js` (IndexedDB) with export/import.
  Verified round-trip.

Next steps (in order):
1. **Render a real map**: convert a `.tmx` (Tiled CLI: `tiled --export-map in.tmx out.json`),
   load its tileset PNGs, draw it in `MapScene`. Prove real game content on screen.
2. **Wire texture atlases** so a real character sprite renders.
3. **Responsive HUD** that reflows between tall/wide (the one design task
   orientation demands).
4. **Port game systems** (movement, dialogue reader, combat) using Track A's
   recovered source as the spec. Data (`.txt` conversations/quests/rules) loads
   directly.
5. Copy `recovered/assets/data` → `web/assets/`, regenerate the asset manifest so
   the full game precaches offline.

Verify anytime: `cd web && node verify.mjs` (drives real Chrome, screenshots to
`web/shots/`, asserts orientation + input + offline + saves).

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
web/                Track B — Phaser 3 web rebuild (orientation, PWA offline, saves)
recovered/src/      Track A — decompiled game source (readable spec)
port/core/          Track A — buildable subset (154 green) + port/stubs
deobf/              de-obfuscation maps + analysis docs
tools/              full toolchain (extract, rebuild, triage, remappers, verify)
MOD_ANALYSIS.md     what the Multiplayer mod changed
REVERSE_ENGINEERING.md  original recovery writeup
CONTINUE_HERE.md    this file
```

## Continuing on another account
Repo: `knightdx91-alt/Exiled-Kingdoms`, branch `main`. Clone it, put the base APK
somewhere, run the three `tools/*.sh` scripts to rebuild Track A, and
`cd web && npm install && node verify.mjs` for Track B. Recommended focus: Track B
step 1 (render a real `.tmx` map).
