# arm64-v8a native libraries (for 64-bit-only devices)

The base EK APK ships only `lib/armeabi-v7a/` (32-bit) natives, so it fails to install
on 64-bit-only devices (modern Snapdragon flagships, e.g. Galaxy Z Fold 8) with
`INSTALL_FAILED_NO_MATCHING_ABIS` ("App not installed"). The build adds these arm64-v8a
libraries so the APK is universal (armeabi-v7a for the owner's Android 4.2.2 phone,
arm64-v8a for modern 64-bit devices).

Source: official libGDX **1.9.12** Android natives from Maven Central
  com.badlogicgames.gdx:gdx-platform:1.9.12:natives-arm64-v8a       -> libgdx.so
  com.badlogicgames.gdx:gdx-box2d-platform:1.9.12:natives-arm64-v8a -> libgdx-box2d.so

Version verified by exact JNI symbol-set match against the game's own 32-bit libs:
libgdx.so 58/58 symbols, libgdx-box2d.so 266/266 symbols (0 missing, 0 extra) — 1.9.12
was the only version matching both. LOAD-segment alignment is 0x10000 (64 KB), so they
are safe on 16 KB-page devices as well as 4 KB.
