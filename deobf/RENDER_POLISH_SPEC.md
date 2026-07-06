# Render polish — roof-fade (A3) + fog-of-war (A5) + dynamic lights (A1)

TODO #6. Numbers reversed from `GameLevelRenderer` (`k0/a`), `ADTIsometricTiledMapRenderer`
(`m0/a`, `m0/b`) and `GameMap` — see `deobf/ENGINE_SPEC.md` §k0/a, §m0/a, §GameMap and
`DEOBFUSCATION_STATUS.md` A1/A3/A5. **Interior-scoped**: fog, roof-fade and torch lighting
are dungeon/interior features (the seamless outdoor world stays full-bright day/night).

## A3 — Roof / object fade (`m0/a` ~205–360)
An `objects`/`roofs` tile fades to **alpha 0.42** when the hero stands in the 4-tile band
"behind" it: with the tile at cell `(x,y)`,
```
fade if  hero.c ∈ [x-4, x]  AND  hero.r ∈ [y, y+4]
```
so tall scenery/roofs the hero walks behind become see-through. Secret doors reveal at
`alpha = min(frac(GameLevel.b()), 0.35)` (not modelled — no secret-door reveal yet).

## A5 — Fog of war (`GameMap.f2419d` byte grid, `m0/a` ~300–345, `m0/b` b0/c0/d0)
Per map an **explored** grid (`byte[w][h]`). Each frame, cells are:
- **unexplored** → not drawn (LOS-culled, `c0`).
- **explored but not currently visible** → floor drawn at **1/3 brightness** (`color/3, a=1`).
- **currently visible** → full ambient.
The explored set is marked within **~12 (Manhattan)** of the hero. True visibility uses LOS
(`d0` blocks-sight); we approximate "currently visible" with a sight radius (APPROX — no
per-cell raycast). Applies in interiors; outdoor world is always fully explored.

## A1 — Dynamic lights (`k0/a` light pass, `GameMap.D` MapLights, box2dlights)
Ambient (already ported as a flat tint) is drawn, then **MapLights** (placed torches/fires,
`GameMap.D`) are added **additively** with soft radial falloff, plus the player's own light
radius in dark maps. Extracted now: `type="light"` objects in the TMX → `map.lights`
(`tmx2json.mjs`), cell `(c,r)` + optional `color`/`radius`. We render each as an
ADD-blended radial glow between the mid and roof planes; a **player torch** glow follows the
hero when `maxlight > 0` (dungeon). box2dlights ray occlusion is not reproduced (APPROX —
soft sprite glow instead of ray-cast shadows).

## Web implementation (`src/render_fx.js`, hooked from `main.js`/`map.js`)
- `renderMap` tags each tile image with `_cell {c,r}` and `_baseTint` (the ambient tint) and
  a `_plane` so the FX layer can update alpha/visibility/tint per hero cell.
- **`RenderFX`** (interiors only): owns the explored grid + a pooled set of additive glow
  sprites. `onEnter(map, tiles)` builds the explored grid and the light glows from
  `map.lights` (+ a player glow if `maxlight>0`). `update(heroCell)` (throttled to cell
  changes) applies A3 alpha, A5 visibility/dimming, and moves the player glow.
- Disabled in world mode and on fully-bright maps (`maxlight===0 && outdoor`), so the
  overworld is unaffected.

## Deviations (DEOBFUSCATION_STATUS.md §3 — updates A1/A3/A5)
- Current-visibility uses a radius, not true LOS raycasting; secret-door reveal not modelled.
- Lights are additive glow sprites (no ray-cast shadow occlusion); torch flicker is a subtle
  sine, colors default warm when the TMX omits them.
