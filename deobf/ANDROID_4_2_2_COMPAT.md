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
| `minSdkVersion`            | **26** (Android 8.0)           | ≤ 17                        |
| Engine                     | libGDX **1.9.12**              | —                           |

For reference, the 4.2.2-native community mods (`EK_ENB_eng`, `EK_SM_*` a.k.a.
"Sorrows") declare `minSdkVersion 16`, ship `armeabi-v7a`, and use dex 035 —
which is exactly why they install and run on the tablet untouched.

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
3. **Lower `minSdkVersion` 26 → 16** and **add storage permissions**
   (`WRITE_EXTERNAL_STORAGE`, `READ_EXTERNAL_STORAGE`, so `.bak` save export
   works; install-time perms on API 17, no runtime prompt). Both are done by
   `tools/axml_add_perms.py --min-sdk 16`, a surgical binary-`AndroidManifest.xml`
   editor: minSdk is a fixed-size in-place integer edit; permissions append value
   strings to the string pool and clone an existing `<uses-permission>` chunk
   (validated with androguard; `resources.arsc` and every other entry stay
   byte-identical). `targetSdkVersion` is left at 35 — an old OS ignores it.
   Lowering minSdk is what lets a strict installer accept the APK on 4.2.2 at all.
4. **Re-sign** v1+v2. Because minSdk is now 16, apksig auto-selects **SHA1** v1
   digests — required by API 17, which cannot verify the SHA-256 v1 signature a
   minSdk-26 APK would otherwise get. (v2 is ignored on 4.2.2.)

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
- `AndroidManifest.xml`: `minSdkVersion=16`, `targetSdkVersion=35`, both storage
  permissions listed (androguard parse OK).
- v1 signature present with **SHA1** digests (API-17-compatible). Modern
  `jarsigner -verify` prints "treated as unsigned … weak algorithm" — that is the
  local JDK's SHA1 policy, **not** an APK defect; 4.2.2 requires SHA1 here.

## Residual risk (needs on-device confirmation — can't be checked off-device)
All install-time blockers are fixed (ABI, dex, minSdk, SHA1 sig), so the APK now
*installs* on 4.2.2 the same way Sorrows/ENB do. The remaining unknown is
**runtime**: unlike Sorrows/ENB (built for Android 4.1+), this is a **minSdk-26
codebase** — the developers targeted Android 8.0, so the Java may call an API
newer than 17 during startup and crash even though it installs. Lowering minSdk
removes the *gate*, not that possibility. What can't be proven without the tablet:
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
