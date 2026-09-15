# Modern-device (Galaxy Z Fold 8) compatibility — why v18 can't run there, and the fix

Companion to `ANDROID_4_2_2_COMPAT.md`, which solves the *opposite* problem. That
document makes a modern EK build run on a 2013 tablet; this one makes the owner's
**4.2.2-targeted mod** run on a **2026 foldable** (Galaxy Z Fold 8, Android 16,
64-bit-only SoC, two displays with very different aspect ratios).

Everything in §1 was read off the then-shipped `hero-v18` APK, not assumed (v18 is no
longer in `dist/` — v19 is v18 plus the arm64 natives and the stable key, so every row
below still holds for it except the `lib/` one). **Wall 1 (the
ABI) was fixed in the main pipeline as `hero-v19`** while this was being written — the
build script here re-verifies it and covers the other three walls; the shipped foldable
artifact is `hero-v19-fold`, which is `hero-v19` plus walls 2-4.

---

## 1. What `hero-v18` actually is (measured)

| Property | Value in `ExiledKingdoms-hero-v18.apk` | How it was read |
|---|---|---|
| package / version | `net.fdgames.ek.android` · `1.3.1207` | binary manifest dump |
| `minSdkVersion` | **16** | binary manifest dump |
| `targetSdkVersion` | **29** | binary manifest dump |
| `lib/` | **`armeabi-v7a` only** (`libgdx.so`, `libgdx-box2d.so`) | `unzip -l` |
| native libs' origin | **stock libGDX 1.9.12**, byte-identical (md5 match vs Maven `gdx-platform:1.9.12:natives-armeabi-v7a`) | `md5sum` |
| `classes.dex` | format **035** (smali `--api 15`) | dex magic |
| signature | **v1 (JAR) only**, SHA1 digests, SHA1withRSA cert (v19 added v2) | `META-INF/CERT.*`, build script |
| `MainActivity` | `screenOrientation=6` (sensorLandscape), `configChanges=0x4a0` | binary manifest dump |
| `<application>` | `requestLegacyExternalStorage=true`, `allowBackup=true` | binary manifest dump |

`configChanges=0x4a0` decodes to `keyboardHidden|orientation|screenSize` — **no
`screenLayout`, no `smallestScreenSize`, no `density`**. That matters on a foldable.

The base APK the mod pipeline consumes is the owner's 2023 **1.3.1207** build (32-bit,
minSdk 16), *not* the 2025 build described in `ANDROID_4_2_2_COMPAT.md` (arm64-only,
dex 038, minSdk 26). Both documents are correct; they describe different bases.

---

## 2. The four walls on a Z Fold 8

### Wall 1 — no 64-bit native code (hard install failure)
The Z Fold 8's SoC is **64-bit only**; like every Snapdragon 8 Gen 3 / Elite-class
Samsung since the Fold 6, it has no AArch32 execution state, so `ro.product.cpu.abilist`
contains **no 32-bit ABI at all**. An APK that carries `lib/` entries but none for a
supported ABI is rejected by the package manager with
**`INSTALL_FAILED_NO_MATCHING_ABIS`**. (If it *did* install, `System.loadLibrary("gdx")`
would throw `UnsatisfiedLinkError` — libGDX cannot start without its natives.)

**Fix — inject `lib/arm64-v8a/{libgdx,libgdx-box2d}.so` from stock libGDX 1.9.12.**
This is a *verified* drop-in, not a guess:
- the APK's 32-bit libs are **byte-identical** to `gdx-platform:1.9.12:natives-armeabi-v7a`
  and `gdx-box2d-platform:1.9.12:natives-armeabi-v7a` (md5), so the engine is exactly 1.9.12;
- the 1.9.12 **arm64-v8a** libs export the **same JNI symbol set** — 58 `Java_…` in
  `libgdx.so`, 266 in `libgdx-box2d.so`, **0 missing / 0 extra** (`nm -D` diff);
- do **not** substitute a newer libGDX: 1.9.14+ dropped 3 `Matrix4` JNI symbols EK calls
  (same finding as `ANDROID_4_2_2_COMPAT.md` §1).

**16 KB page sizes** (Android 15+ requirement on new hardware) are *already* satisfied:
both arm64 `.so` files have `LOAD` segments with `p_align = 0x10000` (64 KB), which is a
multiple of 16 KB, so `dlopen` succeeds on a 16 KB-page kernel. No re-linking needed.

The 32-bit libs stay in the APK — harmless (~390 KB), and it keeps one artifact usable
if the device turns out to have 32-bit support after all.

### Wall 2 — v1-only signature
`hero-v18` is signed with the JAR (v1) scheme only, with SHA1 digests, because Android
4.2.2 cannot verify anything stronger. Modern Android expects APK Signature Scheme v2/v3;
a v1-only APK is at best fragile and at worst refused on Android 14+.

**Fix — sign v1 + v2 + v3**, with `setMinSdkVersion(16)` so the v1 digests stay SHA1 and
the APK is still parseable by the old installer; Android 7+ verifies through v2/v3 anyway.
`ApkVerifier` reports verified at API 16, 24, 29, 34 and 36.

Since v19 the pipeline signs with a **committed stable keystore**
(`tools/ek-release.keystore`), and `build_modern_compat.sh` reuses that same key — so the
Fold APK installs straight **over** `hero-v19` with no uninstall, and future versions
update in place. Its certificate is SHA1withRSA (4.2.2 cannot parse anything stronger);
that is the certificate's own self-signature and is unrelated to the v2/v3 signature
block, which is SHA-256 regardless.

### Wall 3 — folding restarts the game
Unfolding/folding changes `screenLayout`, `smallestScreenSize` and `screenSize` at once.
`MainActivity` only declares `screenSize|orientation|keyboardHidden`, so the system
**destroys and recreates the activity** on every fold — libGDX re-runs the
`ApplicationListener` from scratch, dumping the player back at the title screen and
losing anything unsaved. The same happens moving between the cover display (~2.5:1) and
the inner display (~1.2:1).

**Fix — widen `MainActivity`'s `configChanges` to `0x40003FFC`**
(`mcc`/`mnc` excluded; adds `locale|touchscreen|keyboard|navigation|screenLayout|uiMode|
smallestScreenSize|density|layoutDirection|fontScale` to the three already handled).
The activity then survives the fold and libGDX just gets a `resize()` on the GL surface.
This is a **4-byte in-place edit of the binary manifest** (`tools/axml_set_config.py`) —
`resources.arsc` and every other entry stay byte-identical, per the pipeline rule in
`CONTINUE_HERE.md`.

Nothing else in the manifest needs to change:
- `targetSdkVersion` stays **29**. It is ≥ 24, so it clears the minimum-installable-target
  block introduced in Android 14/15; and staying ≤ 29 preserves `requestLegacyExternalStorage`
  (raising it would switch the game to scoped storage and break save export outright).
- `resizeableActivity` is absent, which for target 29 **defaults to true** — so the game
  already fills both displays with no letterbox and no max-aspect-ratio clamp.

### Wall 4 — external storage (save export/import, crash log)
`patch_export_fix.py` repointed libGDX's external root from the app-specific directory to
**`/sdcard/`**, which is right on 4.2.2 (install-time permissions, always writable) and
wrong on Android 11+: legacy storage now requires a **runtime** grant the game never asks
for, so export/import and the crash log write into a directory the app cannot touch — and
the export's `catch` is silent, so the owner would just see "nothing happens" again.

This also blocks the thing the owner most likely wants: carrying the tablet save
(`/sdcard/Download/EK.bak`) across to the Fold.

**Fix — `tools/patch_modern_device.py`, three edits:**
1. New helper `net/fdgames/ek/android/EkStorage`:
   - `ekInit(Activity)` — records `getExternalFilesDir(null)` (always writable, no
     permission) and, on API ≥ 23, requests `READ/WRITE_EXTERNAL_STORAGE` once if not
     already held.
   - `ekRoot(String fallback)` — returns the external root, **probe-tested once and
     cached**: it creates and deletes `/sdcard/Download/.ek_write_test`; if that succeeds
     the root is `/sdcard/` (identical to v18 behaviour — 4.2.2 and a permission-granted
     Fold both land here), otherwise it falls back to the app-specific directory, then to
     libGDX's own `externalFilesPath`, then to `/sdcard/`.
   - `ekDir()` — the same root as a `File`, for the crash logger.
2. `AndroidFiles.getExternalStoragePath()` returns `EkStorage.ekRoot(externalFilesPath)`.
   Export *and* import go through this one root, so they stay symmetric, and the engine's
   existing `Download/EK.bak` probes keep resolving.
3. `EkCrashLog.uncaughtException` writes its first attempt into `EkStorage.ekDir()`
   instead of `Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)`
   (same register, same `File` type, existing `/sdcard/` fallback chain untouched), so a
   crash on the Fold is still recoverable even with storage permission denied.

Net effect for the owner: with the permission granted, `EK.bak` is at
`/sdcard/Download/EK.bak` exactly as on the tablet (so the tablet's exported save imports
straight away); with it denied, everything still works, just inside
`Android/data/net.fdgames.ek.android/files/`.

---

## 2a. Which artifact gets which fix
`hero-v19` (the main pipeline) carries wall 1 only, so it stays installable on the 4.2.2
tablet *and* on 64-bit devices. `hero-v19-fold` adds walls 2-4. The storage patch stays out
of the tablet build on purpose: it calls API-23 methods behind an `SDK_INT` guard, and this
project has already lost two builds to Dalvik verification surprises — there is no reason
to put the known-good tablet build at risk for a fix it does not need.

## 3. Deliberately NOT changed
- **`targetSdkVersion`** — see Wall 3; raising it buys nothing and costs legacy storage.
- **dex format** — stays 035. ART reads 035 fine; keeping it means the Fold APK carries
  the *same* verified dex as v18 plus the modern patches, instead of a re-targeted one.
- **Play Games / billing** — GMS sign-in will fail under a different signing key. It is
  already optional and failure-tolerant in game (the 4.2.2 device has no GMS at all), and
  v16's `MainActivity.f()` null-device-id fix is still in the dex. If a GMS-related crash
  shows up on the Fold, the crash log (Wall 4) will name it.
- **Display cutout** — with target 29 the inner-display camera hole is letterboxed away
  from the game surface. Changing that needs a theme attribute in `resources.arsc`, which
  this pipeline deliberately never rewrites.

## 4. Build
```bash
tools/build_modern_compat.sh ExiledKingdoms-hero-v19.apk \
                             ExiledKingdoms-hero-v19-fold.apk
```
Post-processor, same philosophy as `build_arm32_compat.sh`: it consumes a finished mod
APK, so the Fold build is always exactly "v18 + this layer" and needs no access to the
clean base APK.

## 5. Verified off-device (`hero-v19-fold`, 2026-09-15, sha256 `5608c024…`)

Everything here is static verification. **Nothing in this build has been run on a device.**

```
lib/arm64-v8a/libgdx.so        58 JNI symbols, nm -D diff vs the APK's 32-bit lib = 0
lib/arm64-v8a/libgdx-box2d.so  266 JNI symbols, nm -D diff vs the APK's 32-bit lib = 0
both: LOAD p_align 0x10000 (>= 16 KB page safe)   [re-checked, already present from v19]
classes.dex                    format 035; baksmali round-trip shows EkStorage
                               (ekInit/ekRoot/ekDir + 4 private helpers),
                               AndroidFiles.getExternalStoragePath -> EkStorage.ekRoot,
                               EkCrashLog -> EkStorage.ekDir,
                               MainActivity.onCreate -> EkStorage.ekInit(this);
                               a full D8 pass (--min-api 24) over the whole dex is clean
AndroidManifest.xml            MainActivity configChanges 0x4a0 -> 0x40003ffc, file size
                               unchanged (6472 B); minSdk 16 / targetSdk 29 / permissions
                               all untouched
signature                      ApkVerifier: verified for API 16, 24, 29, 34 and 36
                               (v1 + v2 + v3); cert fingerprint 53:8B:43:22... -- the same
                               signer as hero-v19, so it installs over it in place
zip                            vs hero-v19: 0 entries added, 0 removed, 3 changed
                               (classes.dex, AndroidManifest.xml, signature),
                               6089 entries byte-identical including resources.arsc
```

The symbol-parity and 16 KB-alignment checks are not one-off measurements — they run
**inside `build_modern_compat.sh`** and fail the build if a future libGDX substitution
ever breaks them.

Route coverage for the storage change was read from the dex, not assumed: the only callers
of `getExternalStoragePath()` are `Serializer` (export *and* import), the two backup
windows (`e/a/d/e1/a`, `e/a/d/e1/n`) and libGDX's own `FileHandle.file()` — so one root
change keeps export, import and the displayed path in sync. The export target is
`<root>Download/EK.bak` with a `getParentFile().mkdirs()`, and the import side probes
`Download/EK.bak` among others, so a tablet-exported `EK.bak` dropped into
`/sdcard/Download` imports with no further change.

## 6. Residual risk — needs the device
- **Unprovable off-device:** that Android 16 accepts a target-29 APK from this vendor's
  installer (it should; 29 ≥ the 24 floor). If install still fails, the installer's error
  string names which wall it hit.
- **Not APK problems, but they block the install anyway** (tell the owner up front):
  One UI 6.1+ ships **Auto Blocker** on by default, which refuses sideloaded APKs
  (Settings → Security and privacy → Auto Blocker); and any **Play-Store copy of Exiled
  Kingdoms** must be uninstalled first, because it has the same package name
  (`net.fdgames.ek.android`) and a different signing key → "App not installed". Its saves
  go with it, so export those before uninstalling.
- **Fold resize:** the game now survives the fold instead of restarting, but EK 1.3's own
  UI layout on a 1.2:1 inner display is untested — if the HUD looks wrong after unfolding,
  the fallback is to rebuild with `EK_KEEP_CONFIGCHANGES=1` (restart-on-fold, old
  behaviour) rather than to guess at viewport code.
- **Storage:** if Android 16 refuses to grant `WRITE_EXTERNAL_STORAGE` to a legacy-target
  app at all, export silently lands in `Android/data/…/files/` instead — by design, and
  the in-game console line from `patch_export_fix.py` reports the real path/exception.
