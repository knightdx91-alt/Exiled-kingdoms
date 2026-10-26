# Exiled Kingdoms VR (Meta Quest 3S): the real game in 3D

Owner request (2026-10-25): "It should be 3D … a standalone app you can install on the Meta
Quest 3S, with network support for the multiplayer too … start fresh using the APK"
(disregarding the web build, its menus included). Two ways to see the world: **walk inside**
it at life size, and a **tabletop** board in mixed reality.

## 1. Approach: run the APK's own game, replace only the picture

The Quest build is **hero-v78 itself** (`dist/hero-v78`) plus a VR layer. Nothing about
the game is re-implemented:

- every menu, the character wizard, the tutorial and its exit to H10, the world, quests,
  saves, combat, the Hero class and guild content are v78's own code;
- multiplayer is v78's own `net.fdgames.ek.android.lan` engine and relay client, so a Quest
  and the phone mod play together exactly as two phones do (same protocol, same relay);
- the package, key and versionCode are the mod's (CLAUDE.md): it installs as an update
  over the 2D mod and keeps its saves.

Why not re-implement in another engine (the first Godot attempt, removed): the MP protocol
ships the host's world as EK's own save JSON (SHARED_WORLD_SPEC.md §7); only EK's code can
read and write that faithfully, and every system (quests, scripts, tutorial exit) would have
to be re-derived and would drift. The earlier Godot tutorial also lacked the game's own tips.

## 2. What the VR layer touches (all of it)

| Where | Change |
|---|---|
| `MainActivity.onCreate` (smali) | v10: `new ExiledKingdoms(this)` → `new ekvr.android.VrEK(this)` (a subclass: VR around `super`'s create/render/resize), and `initialize(game, cfg)` → `initialize(Vr.wrap(this, game, cfg), cfg)` where wrap only sets the config and returns the same object (`tools/vr/patch_mainactivity.py`). v1–v9 passed a separate wrapper object instead; EK casts `Gdx.app.getApplicationListener()` to `Game` and its screens on to `ExiledKingdoms` (Save and Exit, loading a save → `e.a.d.b0$e`, `e.a.b.e`), so the wrapper crashed with ClassCastException on the headset (owner's exit report, 2026-10-25). The desktop test bed always used the game object itself, which is why it never showed. |
| `AndroidApplicationConfiguration` | `useGL30` (ES 3 context for OpenXR), RGBA8, depth 16 |
| `Gdx.gl` | wrapped by `ekvr.GlTap` (pass-through; transparent clears while the UI layer renders) |
| `GameLevelRenderer` (`e.a.a.a`) instance, at runtime | its map renderer's batch (`BatchTiledMapRenderer.c`) → `ekvr.Rec`; its RayHandler (`d`, box2dlights `c.f`) → `Scene3D.NoLight` (keeps the ambient colour, draws no lightmap); the map renderer's view bounds (`d`) widened by the draw distance as drawing begins (§3) |
| `Controllers` | our ControllerManager (`ekvr.Pad`, a virtual gamepad) — AndroidControllers needs a View we don't have on desktop |
| `AndroidGraphics.width/height` | held at a virtual 1280×720 screen (the game lays its UI out for it) |
| manifest (`tools/vr/axml.py`) | Quest VR + OpenXR IMMERSIVE_HMD launcher categories, OpenXR permissions, `android.hardware.vr.headtracking` (required), passthrough (optional), `com.oculus.supportedDevices` |
| new | `classes2.dex` (vr/common + vr/android), `lib/arm64-v8a/libekxr.so` (OpenXR bridge), `libopenxr_loader.so` (Khronos 1.1.65) |

Obfuscated names used (v78 / 1207 naming; read from the dex2jar'd classes, CFR decompile):
Graphics `com.badlogic.gdx.f`, Input `g`, Files `d`, Audio `c`, ApplicationListener `a`
(`a()` create, `a(w,h)` resize, `b()` render), InputProcessor `j`, FileHandle `q.a`,
FileType `d$a` (Classpath, Internal, External, Absolute, Local), Batch keeps its real name,
TiledMap `s.j.b` (`a()` layers), TileLayer `s.j.f` (`a(x,y)` cell), Cell `f$a` (`a()` tile),
Tile `s.j.e` (`b()` region), MapLayer `s.d` (`a()` name), ObjectMap `utils.z` (`b(K,V)` put),
GameLevelRenderer `e.a.a.a` (static `w`, `l` map renderer, `d` lights, `c` World, `g` camera),
map renderer `e.a.c.a`, GameMap `e.a.c.b`, Player `GameLevel.h()` (`x`,`y` world px).

## 3. The 3D picture (`ekvr.Scene3D`, `ekvr.Rec`)

Every quad the game draws in map space (tiles, actors, particles, effects, health bars) is
recorded with its 4 iso-screen corners, UVs, colour and blend mode, then placed in 3D:

- **Ground**: EK's projection is affine. World px (x,y) → iso screen (x+y−64, (y−x)/2)
  (`e.a.c.b.a(Coords)`); inverse: column = (sx/32 − sy/16)/2, row = (sx/32 + sy/16)/2.
  World units: 1 = one cell; X = column, Z = −row, Y up. Floor tiles (any layer but
  objects/scenery/roofs, matched by texture + UV) are laid on Y = 0 in the game's draw
  order (painter's order, depth always, writing depth).
- **Upright**: everything else stands at its base point (bottom-centre + 16 px, the cell
  centre) as a Y-billboard facing the viewer. Art px → units: √2/64 horizontally,
  (√2/64)/cos 30° vertically (EK's 2:1 view looks down 30°). Small quads drawn just above a
  character (health bars, labels) ride on that character.
- Map objects between the viewer and the hero fade to 30 % (V4).
- **Structures as solid blocks** (V2, v11). Source: EK maps are Tiled TMX (`assets/data/tmx`,
  isometric 64×32): layers `nonwalk` (invisible; blocked cells), water, ground, ground2,
  scenery, objects, roofs. A building is one image (e.g. house_village_blue_2.png 384×384)
  cut into 64×128 strips placed on the objects layer cell by cell, so as billboards it fell
  apart into slabs (owner's screenshots, Lannegar). The loader keeps each tileset's
  `imagesource` (TiledMapTileSet properties); tiles whose image is a structure — house*,
  townhall*, manor*, tower*, farmhouse, windmill, wizardtower, *wall*/brickwalls*, palisade,
  gate*, ruins, tomb*, icemist_walls/large, medieval_building_tiles, adt_tiled_dungeon*, cave*
  — are not billboarded. Instead, per frame:
  1. those quads are drawn, in the game's order, into an offscreen **structure image** in iso
     screen space (exactly the 2D picture of the buildings);
  2. the `nonwalk` cells are grouped into connected regions; each region touched by a structure
     quad becomes columns (one per cell, 1×1 cell, shared faces skipped) of height = the
     tallest structure art anchored in it;
  3. the columns are textured by projecting the structure image with EK's own projection
     (world (X, Y, Z) → iso (32(X−Z), 16(−Z−X) + Y/SY)), alpha-tested. From the 2D view
     direction this reproduces the art exactly; the walls/roofs are solid surfaces with real
     depth; cells where the art is transparent show nothing (roof outlines, gaps), and the
     backs of buildings repeat the front art (V2 limitation).
  Columns between the viewer and the hero (V4 test) draw at 35 %.
  v16 (owner: "the buildings are still fucked … all of the buildings"):
  - a structure tile anchored off its footprint (roofs, overhangs) belongs to the nearest
    region within 2 cells — v11–v15 gave it a lone 1-cell column, which showed its art as a
    floating fragment;
  - **shape**: any geometry covering the art's silhouette reproduces the art exactly from the
    2D view direction, and the closer it is to the real shape the better it looks from
    elsewhere. Each region is a **heightfield roof**: cells on its edge at wall height
    Hw = clamp(0.45 H, 1.5, 3.0), rising linearly with the distance from the edge (BFS) to the
    full height H at its innermost cells; corners average the cells around them, so the roof
    is smooth; sides are drawn only on the region's edge. Regions under 2 cells thick (walls,
    fences) are H throughout. (A gable over the bounding box was tried first: regions connect
    across a village, so the gable spanned several houses.)
  - **height H per region from the art itself**: the topmost opaque row of its tiles (read
    from the texture, like the feet) must sit at the peak, so H = (art top − iso y of the
    backmost innermost cell) × SY, clamped 0.5–8. v16's first cut used the tile quad's top:
    every tile is the same size, so every building got the same 2.86 m (measured in Lannegar
    now: 3.7, 4.1, 5.3, 6.1);
  - **faces seen from outside only**, decided per fragment: each vertex carries its face's
    outward normal (triangles wind counter-clockwise from outside), and the fragment is
    dropped when the eye is behind that plane. v16's first cut used GL culling with
    glFrontFace(CW), which kept the *inside* faces (from above, on the tabletop, houses showed
    no roofs; at street level, far walls' insides with concave corners — the owner's "all of
    the buildings"). The winding test is not used because a projection's handedness / depth
    convention can differ between the Quest and the test bed (det(mvp) said CCW there while
    CW was being shown);
  - blocks within 1.8 units of the viewer are not drawn (the camera inside a building saw its
    walls/roof across the view).
  v17 (owner: "the buildings you can see through … props and stalls are crooked"):
  - **no holes**: a face the 2D art doesn't cover (side and back walls, roof edges past the
    silhouette) sampled transparent texels and was discarded — measured as large holes on
    every building. The structure image now gets mipmaps each frame and such a texel takes the
    colour of the nearest art from coarser levels (bias 2, 4, 6, 8, 10: pull fill); only where
    no art lies within ~1000 px is it dropped;
  - thin regions (under 2 cells thick: walls, fences) are capped at 3 units (they can take
    the art top of the building behind them: one stood 6 units tall beside the viewer);
  - **fading** a block between the viewer and the hero (V4) only where it really cuts the
    line from the eye to the hero's chest (1 unit up) within 0.9 units sideways — v16 faded
    every block near that line on the ground, so from the tabletop or over a low wall whole
    houses turned see-through.
- **Multi-tile map objects** (v17; owner: "props and stalls are crooked"). EK draws a weapon
  rack, a market stall or a fence as several tiles of one tileset on neighbouring cells
  (measured: a rack = three 64×128 tiles 32 px apart). Each upright tile was a billboard
  turned toward the viewer about its own cell, so the pieces came apart at different angles.
  Tiles of the same texture on 8-neighbouring cells whose rectangles overlap/touch side by
  side, where either picture runs into the shared edge (opaque texels in its 2 outer columns;
  read once per tile frame), are one object (union-find, ≤ 160 px wide): all its pieces share
  one anchor (the mean of their cells) and baseline (the lowest piece's), so the picture stays
  whole and turns as one. Trees and barrels have transparent margins and stay single.
- **v18** (owner's v17 headset screenshots: "the wall around the town is completely gone,
  things are merged together, the altar … should be a separate prop, the npcs names are
  showing up in the ground, trees odd near the cave mouth, the striped stalls still wrong"):
  - *Footprints* (`Structures.build`, H10 measured): the low town wall's tiles
    (`wall_stone_low`, image `wall_test.png`) block by their own `blocked` property — the
    nonwalk layer leaves them out — so they had no region and their art was dropped; now a
    structure tile with `blocked`/`obstacle` is footprint. Conversely the blocked forest edge
    joined the wall into one region raised as blank walls in front of the trees: only blocked
    cells within reach of structure art count (walls / dungeon: 1 cell; buildings: 4 — a
    building's tiles are stored along its front edges and roof, often a few cells off its
    footprint in the layers).
  - *One region per structure*: regions don't cross kinds (wall / dungeon-ruin / building,
    from the tileset image name), and buildings split by the majority building tileset within
    3 cells (a house beside the town hall took its 6.9 m height). A tile joins only a region
    whose own art is of its tileset (`regionTex`).
  - *Props from structure sets*: a cluster of dungeon / ruin art under 24 cells (the shrine:
    14 tiles of `adt_tiled_dungeon` — low blocks, braziers, a lectern) is drawn as props, not
    extruded. Not applied to buildings/walls (their sparse tile runs read as lone pieces).
  - *Names*: text (BitmapFont) is drawn through the same vertex-array path as map tiles, so
    glyphs were taken for tiles and stood on the ground. A tile is now that path **and** a map
    tileset texture; a name is a run of glyphs attached to its character by the run's centre.
  - *Prop groups*: object tiles (64 px) only, ≤ 4; joined when their pictures touch (opaque in
    a common column of the overlap, or edge to edge). v17 grouped 128-px cliff/tree tiles,
    whose back pieces floated.
  - *Ground*: earth under the whole map and 40 cells around it (the edge cliffs by the mine
    stood over sky).
  - *Camera in a building*: the whole building within 2 cells of the eye is hidden at street
    level (the third-person camera ends up inside houses the hero walks along); a building
    fades only within 3 units in front of the hero.
- **v19** (owner's v18 headset screenshots: names huge, altar hidden until close and wrong,
  ghost stall/rack painted on a house):
  - *Names*: a glyph run (≥ 2 glyphs) riding on a character is drawn at 40% about the run's
    centre and baseline (EK's font is sized for the 2D screen: ~1 m tall letters).
  - *Shrine*: a cell can hold a dungeon-set piece and a building tile on different layers;
    the cell's first tile decided its kind, so shrine pieces next to the town hall counted
    as building art and were extruded / dropped. Dungeon-set art now has its own grid and
    clusters (< 24 cells), and each dungeon-set quad is a prop if its cell or a neighbour is
    in such a cluster (`isStructureAt(key, tex, col, row)`).
  - *Unseen faces*: the 2D picture looks along −X−Z; a building face turned away from it
    (outward normal with x + z < −0.2·√2) has nothing of its own in the art and sampled what
    stands in front of it in the picture (windows, a stall, the rack: "ghosts"). Such faces
    take the building's blurred colour (mip level 5). Thin regions (walls, fences) keep the
    art on both sides (normal length 2 marks them).
- **v20** (owner: "the stalls are still messed up"). Measured in Lannegar (town_objects row 1
  = stall pieces, 64×128): a stall is two tiles on diagonal neighbour cells, 32 px apart on
  screen, whose art meets *exactly at the tile edge* (the end tile's art stops at column 31,
  the middle tile's starts at column 32), so "opaque in a common column" never held and the
  pieces turned separately. Joins now allow a 2 px gap (both masks dilated). The extents
  used for that test were also overwritten by the group's after a merge (per-tile `tMinX` /
  `tMaxX` now separate from the group's). A grouped object keeps its 2D picture plane
  (facing +X+Z, or −X−Z from behind) and turns at most 30° toward the viewer — turned fully,
  a stall swung into the house wall it stands against. Building faces the art doesn't cover
  fill from mip level 6+ (levels 2/4 smeared windows and timber into arcs on walls).
- **Draw distance** (v12; owner: "can you make it draw more, so you can see farther out?").
  EK culls the map to its renderer's view bounds (`BatchTiledMapRenderer.d`, a Rectangle set by
  setView from the camera), but it also keeps the camera zoom within 1–1.5 itself every frame
  (GameLevelRenderer: > 1.5 → 1.5, then eased to 1) — so the zoom the VR layer set since v1
  (2.6) never took effect. v12 widens the view bounds instead, when map drawing begins (the
  recorder's begin(), after setView, before the culling): × Near 2.6, **Medium 4 (default)**,
  Far 6, Max 9 of the 2D view. Measured in Lannegar: ~4.8k / 7.9k / 10.7k quads (Max reaches
  the map edge). The camera itself is left to EK.
  The recorder holds up to 131072 quads (was 32768). The 3D geometry is built once per frame
  and drawn for both eyes (billboards face the first eye; was rebuilt per eye). Fog in the sky
  colour fades the ground and everything on it from 8×zoom to 13×zoom cells from the hero (a
  circle inside the drawn iso rectangle, whose half-extents are ~14×zoom cells), hiding its
  square edge. Cost: more quads per frame; the setting lets the owner trade distance for speed.
  **Characters at the draw distance** (v16; owner: "the enemies / npcs are not being drawn …
  like the terrain"). Widening the bounds widens the tiles only: EK's map renderer (`e.a.c.a`)
  draws a map sprite only through its private 96×96 bucket grid `o`, filled by
  `a(ArrayList<MapSprite>)` (called every frame with `GameLevel.d`, rebuilt every `H` s) with
  just the sprites within **288 world px (9 cells; 448 when `ExiledKingdoms.h`)** of the hero on
  both axes, at `o[clamp((x+8)/32)][clamp((y−24)/32)]` (0..95); the objects layer draws a cell's
  bucket when it reaches that cell (`a(int,int)`, which also sets `visibleToPlayer = true`).
  So each frame, at the recorder's begin() (after that call, before drawing), the VR layer
  removes the sprites it added last frame and adds every sprite of `GameLevel.d` outside EK's
  box but within 14×zoom cells to its bucket by EK's own formula. EK's near list (`n`, used for
  health bars) is untouched; fog of war still applies (cells are drawn only when
  `GameMap.m(x,y)`). Far sprites then get `visibleToPlayer = true` as EK's own do — that flag
  only gates overlays (name labels, off-screen markers), which the VR layer shows for what is
  drawn anyway.
- **Following the hero** (v15). A character is drawn on the cell (x/32, −y/32) of its world-px
  position. v1–v14 used (x/32 − 1, −(y/32 − 1)), one cell off; v6 hid that by searching each
  frame for the sprite nearest the expected spot and following it — which could latch onto a
  companion, monster or loot near the hero and stay there, so the view stopped following the
  hero (owner, v13/v14). Now the views, first-person hiding, facing and fog use the hero's own
  position directly (measured: within 0.0–0.25 cells of its drawn feet while walking).
- **Feet on the ground** (v11): characters/items (non-tile sprites) stand on their lowest
  opaque pixel row (read once per sprite frame from the texture), not quad bottom + 16 px (a
  tile's cell-centre convention), which sank them by up to ~0.4 m (owner's screenshots).
  v14: per **character**, not per layer — EK draws a character as stacked layers (body, head,
  armour, weapon) on one spot; v11-v13 lifted each layer by its own lowest row (the head's is
  its neck) and pulled them apart (owner: head and weapon detached). Sprite quads sharing a
  bottom edge (2 px) and centre (8 px) are one character; all use the group's lowest row.
  v16: map props too (crates, barrels, trees, rocks: tile quads that are neither floor nor
  structure) — they keep their cell for position but their picture's lowest opaque row stands
  on the ground (was quad bottom + 16 px, sinking up to ~0.4 m; owner: crates not sitting right).
- **Facing** (v6): EK draws a character with one of 8 screen directions
  (`MapActor.facing`, enum U, RU, R, RD, D, LD, L, LU = ordinals 0..7, clockwise; chosen from
  its velocity in `MapActor.u0`: x+y vs y−x in world px). Ordinal k = world heading
  45° − 45°·k (heading 0 = −Z, growing to the left; U = up the screen = world (−X, −Z)). The 2D
  camera looks up the screen (heading 45°). In 3D each actor is seen from heading φv (viewer →
  actor), so while the map is drawn (from the recording batch's begin() until the frame ends)
  its facing is shown as k + round((φv − 45°)/45°) mod 8, then restored: game logic never sees
  the change. Owner (v5): "it's model isn't facing the way I'm moving".
- Alpha-tested cut-outs; translucent quads (fading roofs, ghosts) and additive particles last.
- **Light**: the RayHandler's ambient colour (day/night by hour, dungeon darkness) tints the
  scene. Point lights are not drawn yet (V3).
- The game's own frame (menus, HUD, windows, conversations, map tips) renders into
  `ekvr.UiLayer` with a transparent background — the map's hole.

## 4. Quest runtime (`vr/android`, `vr/android/jni/ekxr.c`)

- libGDX's GL thread drives everything: per frame `Xr.beginFrame()` (events, xrWaitFrame,
  eye views, controllers, acquire images) → the real game renders once (UI layer + recorded
  map) → each eye drawn into its swapchain image (RGBA8, EK's colours are already sRGB) →
  `Xr.endFrame()`. No OpenXR runtime (a phone) → the normal 2D game.
- Space: LOCAL_FLOOR (else STAGE, else LOCAL). Passthrough (XR_FB_passthrough) under the
  tabletop when available.
- Touch controllers (oculus/touch_controller + meta/touch_controller_plus bindings).

### 4.1 Why did it close? (`ekvr.android.ExitLog`, v8)

On the headset the owner can't read the game's log (a terminal app's `logcat` only sees its own
process). So the VR build records it itself: an uncaught Java exception's stack trace goes to
`files/ek_crash.txt` as it happens; on the next start Android's record of the last exit
(`ApplicationExitInfo`, API 30+: crash, native crash, low-memory kill, ANR, the game exiting
itself, with description and trace) is combined with it, shown once on a red VR panel, saved
as `Download/EK_VR_last_exit.txt`, and logged (tag ekvr). Normal exits (user closed it, app
updated) are not reported.

Note: EK's own Settings → **Controller Setup** waits for a raw gamepad button (`Controller.b(i)`
for any i); the Touch controllers only drive EK commands through `ekvr.Pad`, so that screen
waits forever. Button mapping in VR is the Menu-button screen (§5.4).

## 5. Controls (`ekvr.VrCore`, shared by the Quest and the desktop simulator)

Owner feedback on v2 (first run on the headset, 2026-10-25): "the controls are atrocious …
some of the movement stuff doesn't work right … no way to click on boxes and other things …
is there any way to make it 3D like you're walking around in it?"

### 5.1 What the game itself offers (source: `GameScreen` l0/b `c(float)`, `ExiledKingdoms`)

EK has complete gamepad support, unused by v1/v2 (they faked touches instead):

- `ExiledKingdoms.n` (v78 name; 1217 sources `f3384n`) = the active libGDX `Controller`,
  picked in `ExiledKingdoms.initialize()` from `Controllers` (first one, or one named xbox).
  `ExiledKingdoms.g()` "a controller is connected" switches the HUD to controller mode
  (`z.r()`: the on-screen touch joystick and its attack button hide; the selected quick slot
  is highlighted).
- Commands come from `Settings.a` (v78; `ControllerConfig`, overridable by
  `data/config/controls.ini`), each `{type: NEGATIVE_AXIS|POSITIVE_AXIS|BUTTON|KEY|POV, id}`.
  Defaults: left/right = axis 0 −/+, up/down = axis 1 −/+, **action** = button 96 (A),
  **cancel** = 97 (B), skill1..8 = 99, 100, 101, 102, 103…, invTake 104, invDrop 105,
  nextItem/prevItem = axis 6 +/−, useItem/recovery = axis 7 +/−. Axes count past |0.2|.
- In the world (`!GameLevel.l()`): (right−left, up−down) → `z.D(f2, f3)` →
  `player.N(f2 − f3)`, `player.O(f2 + f3)` = the hero's velocity in world px (x, y): **analog
  walking**, no pathfinding. So a world direction (dx, dy) needs f2 = (dx+dy)/2, f3 = (dy−dx)/2.
- **action** → `GameScreen.l()`: if "attack interacts" (settings) and no enemy is near and
  something activable is in reach (`player.numActivables > 0`) → `z.E(0)` = use it (open the
  chest/door, talk, loot, pick up); else attack the nearest enemy (`player.E0(-1)`). In a
  conversation **action** = continue; up/down choose a line.
- **cancel** → close the window / back. skill1..8 → `z.k(i)`; nextItem/prevItem cycle the
  selected quick slot, useItem uses it (`z.a0()`), recovery = `z.G()`.

- **Buttons are also keys** (v9): a real Android gamepad sends each button as a controller
  button *and* as a key event with the same code (KEYCODE_BUTTON_A = 96, B = 97, X = 99 …,
  which is why EK's defaults are 96…). EK depends on the key half: with a controller
  connected, `GameScreen` clears the attacking flag (`z.j0`) every frame unless
  `Gdx.input.isKeyPressed(Settings.a.action.id)`. `ekvr.Pad` therefore keeps 256 button slots
  (v3–v8 kept 32, so EK's 96+ codes were dropped: Attack, Cancel and skills never reached the
  game) and VrCore mirrors every pad button as Android key down/up events.

### 5.1b Game settings the VR build sets (v5)

Two of EK's own options (`SettingsData`, held in `Settings.c` in v78) are set by the VR layer
on every frame until the first map is shown (so they win over the settings file loaded at
start-up), then left alone, so the game's own Options screen can still change them:

- `showControls = false` — EK's "show on-screen controls with a controller" (default **true**
  on Android, false on desktop): with it on, `z.r()` keeps the touch joystick and its attack
  button on screen even with a controller. The Touch controllers replace them.
- `attackInteracts = true` — with it off (the default), **action** only ever attacks
  (`GameScreen.l()`); on, it uses what's in reach when no enemy is near. Without it the
  trigger couldn't open chests or talk.

### 5.2 Mapping (v4: remappable)

Owner feedback on v3 (2026-10-25): movement good; "the camera control is not smooth at all
… you click it once in a direction and it changes" (30° snap turn); "the camera needs to stay
behind the player"; "the attack needs to be able to be mapped to the large trigger … give it
an option to map things, like an emulator does, where you click a button and it maps it".

The Touch controllers drive a virtual `Controller` (`ekvr.Pad`) installed before the game
starts, so `ExiledKingdoms.n` is it and the game runs its own controller code. Game commands
are set through `Settings.a`, so a custom controls.ini still works.

Sticks (v6): the **move stick** (left by default; setting) walks with EK's analog movement,
**relative to the view** — stick up = away from you along the view's forward (set by
recenter and by turning), not wherever your head points (setting: or the head). EK drops an
axis under |0.2| (`ExiledKingdoms` command reader), which bent some directions by up to
~14°; both components are scaled up together so neither falls under it (`z.D` divides by
|f2|+|f3| when > 1, so the direction is exact). The other stick ←/→ = turn (smooth by default, or snap; setting); right stick ↑/↓ = tabletop zoom;
**Menu (left ☰)** = the VR controls screen (§5.4). Everything else is a binding
(`ekvr.Binds`, saved in the game's local storage as `ekvr.properties`):

| Function | Default button | Does |
|---|---|---|
| Attack / act | **Right trigger** | EK action: attack the nearest enemy, else use what's in reach (chest, door, NPC, loot); continue a conversation |
| Point & click | A | a tap where the laser points: UI, a thing (its cell), or the ground (walk there) |
| Close / cancel | B | EK cancel |
| Game menu | Y | the phone's Back key |
| Next quick item | X | EK nextItem |
| Use quick item | Left trigger | EK useItem |
| Skill 1 / Skill 2 | Right grip / Left grip | EK skill1 / skill2 |
| Previous quick item, Recovery, Skill 3, Skill 4 | (none) | EK prevItem, recovery, skill3, skill4 |
| Change view | Right stick click | third person → first person → tabletop |
| Recenter | Left stick click | |

**Both controllers point** (v6): the laser comes from the pointer hand — by default whichever
trigger you pulled last (setting: auto / right / left). Whatever is bound, **the pointer hand's
trigger always clicks the game's UI** when the laser is on the
UI panel (opaque pixel), so menus and windows always work. Only **Attack / act** repeats while its button is held; every other command fires once per
press (v7: a grip squeezed just by holding the controller kept firing its skill). A button has
at most one function; binding it elsewhere moves it. Laser clicks: a press is a tap at the press point;
it becomes a drag only after moving 16 px.

### 5.2b Gestures (v13)

Owner: "add the sword swing and potion gestures". Gestures are extra inputs of the binding
system (`Binds.Btn`), so they show on the VR controls screen and can be bound to any function
— doing the gesture while a row waits for a button binds it:

| Gesture | Detected when | Default |
|---|---|---|
| SWING R / SWING L | that controller moves faster than the swing speed (sensitivity: Low 3.0 / **Medium 2.2** / High 1.6 m/s) for 2 frames; then 0.6 s cooldown | R: **Attack / act**, L: Attack / act |
| HAND TO MOUTH R / L | that controller is within 0.2 m of the mouth (0.09 m below and 0.07 m in front of the eyes) for 0.25 s; re-arms once it is 0.35 m away | **Use quick item** (EK's selected quick slot: potions) — EK's Recovery is resting (`z.G()`), not a potion |

A gesture "holds" its input for 0.3 s (so a swing is one EK action: `GameScreen.l()` has its
own 0.8 s cooldown). Positions are stage space: smooth locomotion and turning don't move the
controllers, only your arms do. Settings: **Gestures** on/off, **Swing sensitivity**.

### 5.3 Views

- **WALK = third person (default)** — 1 cell = 1 m; the hero stands 2.4 m ahead (camera distance setting:
  1.6 / 2.4 / 3.2 m) on the map floor, 2.0 m below your eyes (camera height: 1.5 / 2.0 / 2.5 m), whatever your real height (seated works). The world
  follows the hero exactly (the hero never drifts in view). **Camera follows** (setting, on by
  default): while the hero walks, the view swings gently (≤ 40°/s, slower the closer it gets; not while you turn with the right stick)
  until the walking direction points away from you — the camera stays behind the hero. v6: it
  only corrects while the hero walks roughly away from you (within 50°), so walking left /
  right goes straight instead of curving (owner, v5: left and right felt wrong). Turning orbits the hero.
- **FIRST** — you are the hero: eyes 1.55 m above the map floor, the world moves with the
  hero exactly, the hero's sprite and bars hidden, things within 1.6 cells see-through.
- **TABLE** — 4.5 cm per cell, board 0.6 m ahead, the game's screen behind it, passthrough.
- The game's UI panel re-centres on your gaze when you look more than 50° away from it
  (walking views).

### 5.4 VR controls screen (`ekvr.VrMenu`)

Menu button opens/closes it: a panel 1.25 m ahead at eye level, drawn with a built-in 5×7 pixel font (no
dependency on the game's fonts). While it is open the game gets no controller input. Rows:
each function with its button(s); then **Turning** (smooth / snap), **Turn speed** (60 / 90 /
120 / 180 °/s; snap: 30°), **Camera follows hero** (on / off), **Start view** (third person /
first person / tabletop), v6: **Move with** (left / right stick), **Move relative to** (view / head),
**Pointer hand** (auto / right / left), **Camera distance**, **Camera height**, v12: **Draw distance**, **Recenter
now** (recalibrate: forward = where you look, the hero back in front of you), **Reset to defaults**, **Close**. Point the laser at a row and pull
either trigger: an option row cycles its value; a function row shows "PRESS A BUTTON" —
the next button newly pressed (v7: not "after every button is released" — holding a Touch
controller squeezes the grip, so that never happened on the headset) (any of A B X Y, both triggers, both
grips, both stick clicks) is bound to it; Menu cancels. Saved immediately.

## 6. Desktop test bed (`vr/desktop`, `tools/vr/run_desktop.sh`)

The same game code (dex2jar) in a GLFW window through a ~300-line libGDX backend written
against the obfuscated interfaces (`DeskBackend`, `DeskInput`, `DeskGL20` = libGDX's
Lwjgl3GL20). Modes: `2d` (the game as on a phone), `3d` (third-person camera), `table`,
`vr` (`SimXr`: VrCore with a simulated seated head and right controller). Scripted input
(`Script.java`; `vr/desktop/scripts/*.txt`) with fixed 60 fps timing and screenshots.

Verified on desktop (2026-10-25): main menu → Create your hero (Hero class) → traits →
skills → intro → **I10_tutorial** in 3D (map tips, HUD, journal) → tap-to-walk on the 3D
ground → laser + stick control in tabletop and walk-inside → **the camp's own exit action
list → Lannegar Valley (H10), Day 1, 8 AM**, robbed, in 3D.

Not verifiable here: anything on the headset (OpenXR init, controller bindings, passthrough,
frame rate, the GLES3 context under libGDX 1.9.12). First thing to check on the device:
`adb logcat -s ekxr ekvr AndroidRuntime:E`.

Headset history: v1 died at launch with `VerifyError` (MainActivity.onCreate: the Vr.wrap result
was stored in the game's own register, which onCreate then passes on as an ExiledKingdoms). v2
puts the wrapper in a free register; `tools/vr/check_regtypes.py` (run by build_vr.sh on
baksmali's register-type analysis) fails the build on that class of error. Android 4.2's
Dalvik verifier (`tools/dalvik_verify.sh`) does not catch it, so it is not used here.

## 7. Deviations / next (logged in DEOBFUSCATION_STATUS.md §3 as V1–V5)

- **V1** 3D presentation itself (§3): billboards for everything upright.
- **V2** ~~Buildings and walls are billboards~~ v11: solid columns on their `nonwalk`
  footprint, textured by projecting their iso art (§3); backs repeat the front art.
- **V3** No 3D point lights (torches, fires) yet; ambient only.
- **V4** ~~Trees between the camera and the hero are not faded~~ DONE: map objects standing
  between the viewer and the hero (within 1.6 cells of the line) draw at 30 % alpha.
- **V5** UI is the game's own 2D screen on a panel (not re-laid out for VR).
- **V8** (v14) EK's game menu (`e.a.d.b0`, singleton `b0.i`) sizes its five buttons from the
  screen height (height/1.15 window, buttons height/7.4) but the difficulty row and the mod's
  MULTIPLAYER / JOYSTICK buttons are fixed-size; at the VR layer's 1280×720 the contents
  (pref height) exceed the window and the top button was cut off (owner's screenshot). Each
  frame VrCore grows that window to its preferred height (≤ screen) and re-centres it.
- **V6** First person shows EK's 2D sprites as upright cut-outs at 1 cell = 1 m; walls and
  buildings are flat until V2. Laser taps on a thing tap its base cell (what a 2D tap on the
  thing's cell does), not the exact sprite pixel.
