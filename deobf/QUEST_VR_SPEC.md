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
| `MainActivity.onCreate` (smali, 2 lines) | `initialize(game, cfg)` → `initialize(Vr.wrap(this, game, cfg), cfg)` (`tools/vr/patch_mainactivity.py`) |
| `AndroidApplicationConfiguration` | `useGL30` (ES 3 context for OpenXR), RGBA8, depth 16 |
| `Gdx.gl` | wrapped by `ekvr.GlTap` (pass-through; transparent clears while the UI layer renders) |
| `GameLevelRenderer` (`e.a.a.a`) instance, at runtime | its map renderer's batch (`BatchTiledMapRenderer.c`) → `ekvr.Rec`; its RayHandler (`d`, box2dlights `c.f`) → `Scene3D.NoLight` (keeps the ambient colour, draws no lightmap); camera zoom 2.6 so more of the map is drawn |
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

## 5. Controls (`ekvr.VrCore`, shared by the Quest and the desktop simulator)

| Input | Action |
|---|---|
| Right trigger (laser) | a tap on the game: the UI panel where it is opaque, else the ground under the laser → the screen point the 2D game would show it at (walk, attack, talk, open, loot) |
| Left stick | walk: holds a touch on the ground 2.5 cells ahead in the stick's direction (EK's hold-to-move) |
| A | the HUD attack button |
| X | the HUD potion slot |
| B / Y / Menu | the phone's Back key (close window, game menu) |
| Right stick ←/→ | turn the world 30° |
| Right stick ↑/↓ | tabletop zoom |
| Right stick click | switch tabletop ↔ walk-inside |
| Left grip | move the board (tabletop) |
| Left stick click | recenter |

Modes: **TABLE** — 4.5 cm per cell, board 0.6 m ahead at sitting-table height, the game's
screen standing 1.15 m away behind it, over passthrough. **WALK** — 1 cell = 1 m, the hero
2.6 m ahead on the floor, the game's screen 1.5 m ahead.

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
`adb logcat -s ekxr ekvr`.

## 7. Deviations / next (logged in DEOBFUSCATION_STATUS.md §3 as V1–V5)

- **V1** 3D presentation itself (§3): billboards for everything upright.
- **V2** Buildings and walls are billboards → next: boxes textured by projecting their
  iso art onto the faces (correct from the iso direction, solid from the others).
- **V3** No 3D point lights (torches, fires) yet; ambient only.
- **V4** Trees between the camera and the hero are not faded yet (walk-inside).
- **V5** UI is the game's own 2D screen on a panel (not re-laid out for VR).
