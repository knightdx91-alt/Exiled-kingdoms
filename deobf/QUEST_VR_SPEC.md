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
- Map objects between the viewer and the hero fade to 30 % (V4).
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
**Pointer hand** (auto / right / left), **Camera distance**, **Camera height**, **Recenter
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
- **V2** Buildings and walls are billboards → next: boxes textured by projecting their
  iso art onto the faces (correct from the iso direction, solid from the others).
- **V3** No 3D point lights (torches, fires) yet; ambient only.
- **V4** ~~Trees between the camera and the hero are not faded~~ DONE: map objects standing
  between the viewer and the hero (within 1.6 cells of the line) draw at 30 % alpha.
- **V5** UI is the game's own 2D screen on a panel (not re-laid out for VR).
- **V6** First person shows EK's 2D sprites as upright cut-outs at 1 cell = 1 m; walls and
  buildings are flat until V2. Laser taps on a thing tap its base cell (what a 2D tap on the
  thing's cell does), not the exact sprite pixel.
