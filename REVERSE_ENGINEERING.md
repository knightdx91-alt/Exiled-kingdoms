# Exiled Kingdoms — Reverse Engineering & Web Port

Recovery of the lost source for **Exiled Kingdoms** from a shipped Android APK
(`Exiled Kingdoms.apk`, 130 MB, `classes.dex` build 2025-07-26), with the goal
of running the game in a web browser.

## TL;DR

- **Engine:** libGDX (Java) — the *best possible* engine for a web port, because
  libGDX ships an official HTML5 backend (GWT / TeaVM).
- **Code recovery:** the game's own code under `net.fdgames.*` was **not
  obfuscated** — real package, class, and method names survived. 185 source
  files (~34k lines) were decompiled cleanly and live in `recovered/src`.
- **Assets:** 100% standard, reusable formats (Tiled maps, PNG atlases, MP3,
  bitmap fonts, plain-text data). ~150 MB. Re-extractable via `tools/extract.sh`.
- **Bottom line:** a real browser port is achievable, but it is a porting
  *project*, not a one-command conversion. The blocker is the obfuscated
  third-party library layer, detailed below.

## How this was recovered

Everything is reproducible: `./tools/extract.sh path/to/ExiledKingdoms.apk`

1. `unzip` the APK → assets, native `.so` libs, `AndroidManifest.xml`, `classes.dex`.
2. Resolve **jadx 1.5.5** (`jadx-core` + `jadx-dex-input`) from Maven Central.
3. Decompile `classes.dex` (3,241 total classes) via the jadx API.
4. Keep the game code (`net.fdgames.*`); discard obfuscated library classes
   (real libGDX etc. is open source — no need to keep decompiled copies).

## Engine confirmation

- `lib/arm64-v8a/libgdx.so`, `libgdx-box2d.so` — libGDX native backend + Box2D.
- Bundled `com/badlogic/gdx/**.gwt.xml` GWT module descriptors — the HTML5
  backend machinery was already on the build classpath.
- `net.fdgames.ek.ExiledKingdoms extends com.badlogic.gdx.Game` (decompiled as
  `extends e`, jadx tags it `compiled from: Game.java`) — the main
  `ApplicationListener`.

## Recovered architecture (`recovered/src/net/fdgames`)

| Package | Files | What it is |
|---|---|---|
| `GameEntities` | 55 | Characters, Player, NPC, items, actors (`Character.java` is 143 KB) |
| `GameWorld` | 39 | World state: Areas, Quests, Castles, Factions, Party, Rumors, spawn tables |
| `Rules` | 22 | Combat/RPG rules engine |
| `assets` | 15 | Asset loading (`GameAssets`, `Assets`) |
| `ek` | 14 | Platform layer: `ExiledKingdoms` (main), `Settings`, controllers, IAP/GPGS |
| `TiledMap` | 14 | Tiled `.tmx` map loading |
| `Helpers` | 12 | JSON, serialization, strings |
| `GameLogic` | 11 | `ScriptedAction` (74 KB), `Condition` — the quest/event scripting VM |
| `GameLevel` | 3 | Level data |

### Assets (`recovered/assets/data`, ~150 MB)
`conversations/` (3,765 dialogue files) · `quests/` (799) · `tmx/` maps (343) ·
`sprites/` (321) · `graphics/` (209) · `ui/` (152) · `sounds/` (66) ·
`rules/` (56) · `world/` (39) · `music/` (29) · `particle/` (26).
Formats: `.tmx`/`.tsx` (Tiled), `.png`, `.pack`/`.atlas` (libGDX atlases),
`.mp3`, `.fnt`, `.txt`. All directly consumable by a libGDX HTML build.

## The one real obstacle: the obfuscated library layer

ProGuard obfuscated the **bundled third-party libraries** (libGDX itself, the
IAP/`d0.*` library, Google Play Games, controllers) down to single-letter
names (`com.badlogic.gdx.e`, `d0.i`, packages `b/`, `q/`, `e0/` …). The game
code calls into those obfuscated names. So while your *logic* is readable, it
is written against renamed API symbols rather than the real libGDX API.

To recompile against the real (GWT-compatible) libGDX, those calls must be
re-mapped `obfuscated → real`. This is tractable because libGDX is open
source and jadx preserved original `compiled from: X.java` hints, letting us
rebuild the name mapping largely mechanically — but it is the bulk of the work.

## Why not "just run the APK in the browser"?

Tools like CheerpJ run JVM bytecode in WASM, but libGDX's core (matrix math,
Box2D, asset pipeline) is **native ARM `.so`** reached via JNI. WASM can't run
arm64 native code, so that shortcut is blocked. The supported path is
libGDX's HTML5 backend, which re-implements those native pieces in pure
Java — which is exactly why a *recompile* (not an emulate) is required.

## Roadmap to the browser

1. **De-obfuscate the libGDX API surface** — map the renamed `com.badlogic.gdx.*`
   symbols the game actually uses back to real libGDX (rewrite imports/refs in
   `recovered/src`). *(largest task)*
2. **Stand up a standard libGDX Gradle project** with `core` + `html` (GWT or
   TeaVM) modules; drop recovered `core` sources + `assets/data` in.
3. **Stub/replace platform services** — IAP, Google Play Games, controllers,
   Android file I/O — with web-safe no-ops or web equivalents (`IPlatformResolver`
   is already the abstraction seam the game uses for this).
4. **Compile `core` for desktop first** (fastest feedback loop) to shake out
   de-obfuscation gaps, then build the HTML target.
5. **Fix GWT/TeaVM incompatibilities** (reflection, threads, `java.io` usage).
6. **Serve** the generated JS + assets as a static site.

Desktop-first (step 4) is the pragmatic milestone: getting the recovered code
to compile and run at all proves the recovery before tackling web-specific
constraints.

## What's in this repo

```
recovered/src/net/fdgames/   decompiled game source (185 files) — committed
recovered/assets/            extracted assets (~150 MB) — gitignored, re-extract
recovered/native/            .so libs — gitignored, not needed for web
tools/extract.sh             reproduces the whole recovery from the APK
```
