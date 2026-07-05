# Camera / GameLevelRenderer recovery

Recovered the base game's map camera to match the web port's view to the original.
The render tier is R8-obfuscated and lives in single-letter packages (not in the
`net.fdgames` set), so it required a **full** decompile of `classes.dex` (all 3,241
classes, every `a`…`z`/`a0`… package), then grepping the libGDX public API
(`OrthographicCamera`, `.zoom`) which R8 leaves intact because it's a library symbol.

## Where it lives
`k0/a.java` — the **GameLevelRenderer** (identity confirmed by its own log string
`"GameLevelRenderer.Initialize() > viewport: WxH"`). Camera field is `f2278e`
(`com.badlogic.gdx.graphics.OrthographicCamera`).

## Recovered camera parameters
- **Viewport** = `new OrthographicCamera(f2270q, f2271r)` where the dims are chosen by
  device in `m()`:
  - Tablet/large (`ExiledKingdoms.f3378h` true), by `Gdx.graphics.getWidth()`:
    `>1680 → 880x495`, `1600–1680 → 840x473`, `≤1280 or ≥1600 → 780x439`,
    else `820x462`.
  - **Phone (flag false) → `533x300`** (world-units; the tile grid is 64x32 world-units).
    The viewport is 16:9 and stretched to the window.
- **Gameplay zoom = `1.0`** — reset in the gameplay states (`n0/x.java`, `n0/r1.java`:
  `…f2278e.zoom = 1.0f`).
- Non-gameplay zoom values (do **not** use for the play view):
  - `1.8f` — a transition/idle mode (`i()`).
  - `mapWidth / 12` — whole-map **automap/overview** (`z.v().w() == 1`; `b.P().f2417b`
    is the current map's width in tiles).
  - smooth `±0.33`-step transitions clamped to `≤1.5f` — camera effects.

## Lighting (recovered alongside the camera)
The renderer multiplies the whole map batch by a grey light value before drawing:
`this.f2283j.c().setColor(f13, f13, f13, 1.0f)` (k0/a.java ~694), where `f13 = 1/k()`
and `k()` folds in the map's ambient level plus dynamic light sources / `light`-type
map objects (k0/a.java 473-485, 724-726). The map's **ambient** term is `maxlight`
(0-100), read in `m0/b.java:1498` into field `f2424i`.

The web port currently reproduces the **ambient** term only: tiles are tinted by
`maxlight/100` (floored at 0.25 for legibility) in `web/src/map.js` — so dungeons
(maxlight 20-35) render dark and outdoor maps (100) full-bright. Dynamic light
sources (torches, day/night, the `light` map objects) are a later step.

## What the web port uses (camera)
The phone view is **533 world-units of width at zoom 1.0** (≈2× the art on a 1080px
phone). Reproduced in `web/src/main.js` (`EK_VIEWPORT_W = 533`, `fitMap()`): map the
533 world-units across the **longer** logical axis (the base game is landscape; 533 is
its long side), independent of the port's player-chosen orientation, camera centered
on the hero.
