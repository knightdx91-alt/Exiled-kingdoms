# Exiled Kingdoms — libGDX port scaffold

Standard libGDX multi-module Gradle project for bringing the recovered game to
desktop (validation) and the browser (goal).

```
port/
  core/     recovered + remapped game code (the ApplicationListener lives here)
  lwjgl3/   desktop launcher — the fast-feedback target
  html/     GWT/HTML5 launcher — the browser target
```

## Status: scaffold only (step 1 done)

The module layout, dependencies (libGDX 1.12.1, box2dlights, gdx-pay), and
launchers are in place. **It does not compile yet** — that's step 2. Two inputs
feed step 2, both produced in step 1 and living in `../deobf/`:

1. **`deobf/deobf_map_full.json`** — 567 `obfuscated → real` class-name mappings
   mined from jadx `compiled from:` hints. Of these, the game directly imports
   50 symbols and 49 resolve (see `deobf/game_obf_imports.json`).
2. **`deobf/recoverable_game_classes.json`** — 81 additional *game* classes
   (the whole UI/render layer: windows, dialogs, `GameHUD`, `GameScreen`, the
   isometric tiled-map renderers) that were relocated into obfuscated packages
   but whose real names survived. These must be extracted and folded into `core`.

## Step 2 checklist

- [ ] Apply `deobf_map_full.json` to rewrite obfuscated imports/refs in the
      recovered sources (start with the 50-symbol game surface).
- [ ] Recover the 81 relocated UI classes into `core/src/main/java` under a
      reconstructed package (e.g. `net.fdgames.ui`), fixing cross-refs.
- [ ] Add a desktop no-op `IPlatformResolver` (IAP/GPGS/analytics stubs).
- [ ] `./gradlew :lwjgl3:run` — iterate until the recovered code compiles & runs.
- [ ] Place `assets/data` (via `tools/extract.sh`) under `html/webapp/assets`.
- [ ] Resolve GWT constraints (reflection in `Serializer`, threads, `java.io`).
- [ ] `./gradlew :html:dist` → serve the static site.

Run `gradle wrapper` in this dir to generate the wrapper before first build.
