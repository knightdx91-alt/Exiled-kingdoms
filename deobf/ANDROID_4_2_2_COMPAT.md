# Android 4.2.2 (API 17 / Dalvik) compatibility — why the mods crashed, and the fix

## Symptom
The cheat and Hero-class mod APKs install on an Android 4.2.2 tablet but crash
**immediately** on launch. Changing `targetSdkVersion` did not help.

## Root cause — three independent walls, none fixable via `targetSdkVersion`
The base APK we mod is a **2025 build** (`Exiled Kingdoms.apk`, ~124 MB,
built 2025-07-26). Inspecting it:

| Property (base APK)        | Value                          | 4.2.2 (API 17) needs        |
|----------------------------|--------------------------------|-----------------------------|
| Native libs (`lib/`)       | **`arm64-v8a` only**           | `armeabi-v7a` (32-bit ARM)  |
| `classes.dex` format       | **038** (`dex\n038\0`)         | **035** (Dalvik max)        |
| Engine                     | libGDX **1.9.12**              | —                           |

1. **arm64-only native code.** Android 4.2.2 is 32-bit; the arm64 runtime did
   not exist until Android 5.0. `System.loadLibrary("gdx")` finds no loadable
   ABI → `UnsatisfiedLinkError` → instant crash. *This is the primary crash.*
2. **dex format 038** was introduced in Android 8.0. Dalvik on 4.2.2 loads only
   dex 035 and rejects 038 outright — the app can't even load its bytecode.
3. `targetSdkVersion` only affects *newer* OS behavior; an old OS ignores it, so
   editing it changes neither (1) nor (2).

The mod build scripts round-trip `classes.dex` through `smali --api 15`, which
already emits dex **035** — so the modded dex was fine. But they copied the
base APK's `lib/` untouched, leaving it arm64-only → still crash (wall 1).

## Fix — `tools/build_arm32_compat.sh` (post-processor, resources.arsc-free)
1. **Inject 32-bit native libs.** Add `lib/armeabi-v7a/{libgdx,libgdx-box2d}.so`
   from **stock libGDX 1.9.12** (Maven `gdx-platform` /
   `gdx-box2d-platform` `natives-armeabi-v7a`). These are a verified drop-in:
   the arm64 libs EK ships export **exactly** the same JNI symbol set — 58 core
   + 266 box2d, **0 missing / 0 extra**. Later libGDX (1.9.14+) dropped 3
   `Matrix4` JNI symbols EK still calls, so **1.9.12 is the correct version**;
   don't substitute a newer one.
   `arm64-v8a` is kept so the same APK still runs on modern devices.
2. **Downgrade dex 038 → 035** via `baksmali d` + `smali a --api 15` (skipped if
   already 035, so it's a no-op on mod outputs). Safe here because the dex uses
   no 038-only opcodes (no `invoke-custom` / `MethodHandle`).
3. **Add storage permissions** (`WRITE_EXTERNAL_STORAGE`, `READ_EXTERNAL_STORAGE`)
   so the game can export `.bak` save files. On API 17 these are install-time
   permissions — granted at install, no runtime prompt. Done by
   `tools/axml_add_perms.py`, a surgical binary-`AndroidManifest.xml` editor that
   appends the value strings to the string pool and clones an existing
   `<uses-permission>` chunk (validated with androguard; `resources.arsc` and all
   other entries are left byte-identical).
4. **Re-sign** v1+v2 (API 17 uses the v1 JAR signature; v2 is ignored there).

## Build
```bash
# base game, 4.2.2-compatible (for isolating compat issues from mod bugs)
tools/build_arm32_compat.sh "Exiled Kingdoms.apk" ExiledKingdoms-base-4.2.2.apk

# mods: build the mod first (emits dex 035 + patched assets), then layer compat
tools/build_cheat_mod.sh "Exiled Kingdoms.apk" cheats-raw.apk
tools/build_arm32_compat.sh cheats-raw.apk ExiledKingdoms-cheats-4.2.2.apk

tools/build_hero_mod.sh  "Exiled Kingdoms.apk" hero-raw.apk
tools/build_arm32_compat.sh hero-raw.apk  ExiledKingdoms-hero-4.2.2.apk
```

## Verified on the built APKs
- `lib/armeabi-v7a/` **and** `lib/arm64-v8a/` present; the 32-bit `libgdx.so` is a
  real ARM EABI5 ELF exporting all 58 `Java_com_badlogic…` symbols.
- `classes.dex` magic = `035`.
- `AndroidManifest.xml` lists both storage permissions (androguard parse OK).
- `jarsigner -verify` → *jar verified*.

## Residual risk (needs on-device confirmation — can't be checked off-device)
The three hard blockers above are fixed. What can't be proven without the tablet:
- Startup Java may touch a framework API newer than API 17 (the APK references
  `JobScheduler` and Google Play Games sign-in). These are normally
  version-guarded (`JobIntentService`; libGDX's own floor is API 14, per its
  embedded "requires Android API Level 14" string), so they *should* be inert on
  4.2.2 — but if a crash persists, grab `adb logcat` at launch: an
  `UnsatisfiedLinkError` means a native/ABI issue remains; a
  `NoClassDefFoundError` / `VerifyError` on an `android.*` class means a
  too-new framework call, which we'd then guard or stub.
- Install the **base-4.2.2** APK first: if it boots, the compat layer is sound
  and any remaining crash is in the mod's own patch, not the 4.2.2 fix.
