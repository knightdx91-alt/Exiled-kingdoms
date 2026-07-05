# Engine / render core — recovered spec

Reverse-engineered from the obfuscated EK classes (member logic read from the full
decompile). This is the behavioural spec the web port should match. Obfuscated paths in
`( )`; see `CLASS_MAP.tsv`. Fidelity gaps are cross-referenced to `DEOBFUSCATION_STATUS.md`
(A1–A5).

## GameLevelRenderer (`k0/a`)
The per-frame map draw loop. Owns the camera and lighting; delegates tile drawing to
`ADTIsometricTiledMapRenderer` and light drawing to box2dlights `RayHandler`.

- **Camera** — `OrthographicCamera(f2270q, f2271r)`; phone viewport **533×300** world-units
  (tablets 780–880 by screen width). Gameplay **zoom 1.0**; 1.8 = a transition, `mapW/12`
  = automap overview, 0.33-step ≤1.5 = effects. *(PORTED — see `CAMERA.md`.)*
- **Fade transitions** — `k()` returns a multiplier used as `setColor(1/k…)`: level-load
  fade-in (`f2284k`, 1s) and death/fade-to-black (`f2285l`, 2s). Not the ambient. *(not ported)*
- **Ambient light** — a `RayHandler` ambient color set per frame (`f2275b.a(r,g,b,a)`):
  - automap: `(.93,.93,.93,.93)`
  - `maxlight>0` (dungeon): `f=maxlight/10`; `(.08f+.1, .08f+.1, .05f+.4, .04f+.5)` — bluish
  - `outdoor` (no maxlight): **day/night by clock hour** `FDUtils.j()` — night(23-5)`(.3,.3,.7,.8)`,
    dusk/dawn gradient (6-8,20-22), day(9-19)`(.93,.93,.93,.93)`
  - then dynamic `MapLight`s in `GameMap.D` drawn additively. *(ambient PORTED as flat tint = A1; day/night PORTED; dynamic lights NOT ported = A1.)*
- **Draw order per frame:** clear → tile layers (ADTIso) → light overlay (RayHandler +
  MapLights) → floating sprites `f2279f` → **particles** `f2280g` (night-gated `nocturne`) →
  HUD/floating text/health bars. *(particles NOT ported = A4.)*

## ADTIsometricTiledMapRenderer (`m0/a`)
Custom isometric tile renderer (extends libGDX `BatchTiledMapRenderer`).

- **Iso projection** — cell (c,r) → `x=(c-r)·tw/2`, `y=(c+r)·th/2` (tw/th = 64/32). *(PORTED.)*
- **Per-layer draw bands** — culling window is extended per layer type so tall art isn't
  clipped: roofs/scenery/objects/bridge each add different `i4/i5/i6` row/col padding;
  tablet (`f3378h`) adds +3/+3/+4. *(web fits whole map, band culling not needed yet.)*
- **Roof/object fade (A3)** — an `objects`/`roofs` tile fades to **alpha 0.42** when the
  player is in a 4-tile band behind it: `d0(x,y) && player.x∈[x-4,x] && player.y∈[y,y+4]`.
  Secret doors reveal at `alpha = min(frac(GameLevel.b()), 0.35)`. *(NOT ported.)*
- **Fog of war (A5)** — uses `GameMap.f2419d` explored byte-grid:
  - unexplored tiles: LOS-culled (`c0`) — not drawn
  - explored but not currently visible floor: drawn at **1/3 brightness** (`color/3, a=1`)
  - current view: full. Explored set is marked within ~12 (Manhattan) of the player. *(NOT ported.)*
- **Doors** — pulls door textures from `GameLevelData` (`Door`, `SecretDoor`) per tile.

## GameMap (`m0/b`)
A loaded level: geometry, properties, visibility, lights.

- **Load** — `TmxMapLoader` on `data/tmx/<name>.tmx`; reads `width/height`, properties.
- **Properties** — `maxlight`→`f2424i` (0 if absent), `outdoor`→`f2423h` (enables day/night),
  `music`→`f2415a`, `nominimap`→`f2425j`. *(maxlight/outdoor PORTED.)*
- **Explored grid** `f2419d` (`byte[width][height]`) — fog-of-war state; allocated per map,
  updated near the player each frame.
- **Visibility API** — `b0(x,y)` is-visible, `c0(px,py,x,y)` LOS/explored test (skips if
  explored, near edges, or fails `d0`), `d0(x,y)` blocks-sight. Drives A5. *(NOT ported.)*
- **Lights** `D` — list of `MapLight` (placed torches/fires). *(NOT ported = A1.)*

## MapStaticEffects (`m0/c`)
Animated ground FX (lava/fire) on a 96×96 `MapEffect` grid.
- `a(x,y,type)`: lava_edge→small_flames (50% roll); lava→flames(<5)/medium_flames(<25)/
  small_flames by `FDUtils.b(1,100)`. Places `MapEffect` at `(x·32, y·32)`.
- `d(dt)` animates by game time. *(NOT ported.)*

## NPCConversation (`m0/d`) · NPCSpawnData (`m0/e`)
Data records. `NPCConversation`: {id, string, texture, position} — a placed talk marker.
`NPCSpawnData`: 8 fields {sprite, count, id, level, + 4 script strings} — a spawn entry.

## Screens (`l0/*`)
UI is designed for **1280×720** and scaled by `min(H/720, W/1280)` — key for porting UI.
- **GameScreen (`l0/b`)** — the gameplay screen: owns the `GameLevel`, drives level
  **transitions** `d(Transition)` (area entry/exit, save area-visited, spawn resolution,
  Freetown lost-gold rule), and **delegates input** to a `GameInput` processor
  (`Gdx.input.setInputProcessor`) — tap-to-move lives there. *(not ported; movement TBD.)*
- **IntroScreen (`l0/c`)** — studio/intro splash with `Stage`, image/label transitions.
- **LoadingScreen (`l0/d`)** — random `splash1-3.png` + `logo.png`, progress bar (`f2344j`).
- **MainMenuScreen (`l0/e`)** — main menu; hosts SkillInfo/ClassesHelp/WaitMenu windows.
