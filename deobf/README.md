# De-obfuscation map (step 1)

Deterministic `obfuscated → real` name mappings recovered from the APK, built by
mining jadx `compiled from: X.java` hints across all 3,210 decompiled classes.
This is the deterministic alternative to statistical tools like DeGuard: because
the obfuscated layer is known open-source code (libGDX et al.) plus the game's
own relocated classes, names are *restored*, not *guessed*.

Regenerate with: `python3 ../tools/build_map.py && python3 ../tools/classify.py && python3 ../tools/finalize.py`

## Files

| File | What |
|---|---|
| `deobf_map_full.json` | All 567 `obf_fqn → real_name` mappings (every hinted class). |
| `game_obf_imports.json` | The 50 obfuscated symbols the `net.fdgames` code imports directly, with resolved real names + use counts. The minimal remap surface to compile. |
| `recoverable_game_classes.json` | 81 game classes (UI/render layer) hiding in obfuscated packages, real name → obfuscated fragment(s). |

## What the map revealed

Breakdown of the 567 hinted obfuscated classes:

| Category | Count | Notes |
|---|---|---|
| `other` | 292 | Unclassified internals (mostly libGDX/support internals; low priority). |
| `GAME-CODE` | 124 | The game's own relocated classes (81 distinct). **Recover these.** |
| `libgdx` | 83 | libGDX API under preserved `com.badlogic.gdx.*` packages. |
| `androidx` | 40 | Android support lib — dropped on web. |
| `gdx-pay` | 10 | IAP library — stub on web. |
| `libgdx-math/reflect/core (relocated)` | 14 | `Rectangle`, `Vector2`, reflection, `AssetManager`, etc. |
| `box2dlights` | 4 | `RayHandler`, `PointLight` — dynamic lighting. |

### Confidence
- **High (deterministic):** `com.badlogic.gdx.*` entries — package preserved, so
  `com.badlogic.gdx.e` + hint `Game.java` = `com.badlogic.gdx.Game`, exactly.
- **High names / unknown package:** relocated classes in single-letter packages
  (`n0.z` → `GameHUD`). The class *name* is certain; the original package must be
  reconstructed when folding them into `core`.
- **Unresolved:** 1 game import (`a.a`, 24 uses) has no hint — identify by usage.
