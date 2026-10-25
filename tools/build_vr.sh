#!/usr/bin/env bash
# Build Exiled Kingdoms VR for Meta Quest: the owner's hero-v78 APK + a VR layer.
# Spec: deobf/QUEST_VR_SPEC.md. The game itself (menus, tutorial, world, quests, saves,
# multiplayer) is v78's own code, untouched; the VR layer only wraps its render callback.
#
#   1. reassemble dist/hero-v78 (sha256-checked)
#   2. compile vr/common + vr/android against android.jar + the game's classes (dex2jar)
#      -> D8 -> classes2.dex
#   3. MainActivity.onCreate: initialize(game) -> initialize(Vr.wrap(game))  (smali, 2 lines),
#      then a register-type gate (tools/vr/check_regtypes.py) for what the device verifier rejects
#   4. libekxr.so (OpenXR bridge, NDK) + the Khronos OpenXR loader
#   5. manifest: Quest VR category, OpenXR permissions, head tracking, passthrough (tools/vr/axml.py)
#   6. zip-swap, zipalign, sign with the committed tools/ek-release.keystore, update gate
#   7. dist/quest-vr-vN/: the VR overlay only (~6 MB); .github/workflows/deploy.yml rebuilds the
#      same signed APK from dist/hero-v78 + the overlay (tools/vr/deploy_vr.sh), sha256-checked
#
# Same package (net.fdgames.ek.android), key and versionCode as the mod (CLAUDE.md): it
# installs as an update over the 2D mod and keeps its saves.
#
# Requires: java 17+, python3, curl, zip, ANDROID_SDK_ROOT with build-tools;35.0.1,
# platforms;android-35 and ndk;27.2.12479018.  Usage: tools/build_vr.sh [N]
set -euo pipefail
REPO="$(cd "$(dirname "$0")/.." && pwd)"
V="${1:-1}"
SDK="${ANDROID_SDK_ROOT:?set ANDROID_SDK_ROOT}"
BT="$SDK/build-tools/35.0.1"; AJ="$SDK/platforms/android-35/android.jar"
NDK="$(ls -d "$SDK"/ndk/* | sort -V | tail -1)"
CC="$NDK/toolchains/llvm/prebuilt/linux-x86_64/bin/aarch64-linux-android29-clang"
LIB="${EK_LIB:-$HOME/.cache/ekvr-lib}"; mkdir -p "$LIB/d2j"
W="$(mktemp -d)"; trap 'rm -rf "$W"' EXIT
KS="$REPO/tools/ek-release.keystore"
[ -f "$KS" ] || { echo "tools/ek-release.keystore missing -- refusing to sign with a new key"; exit 1; }
NAME="ExiledKingdoms-VR-v$V.apk"
M=https://repo1.maven.org/maven2
get(){ for i in 1 2 3 4 5; do [ -s "$2" ] && return 0; curl -sSfL -o "$2" "$1" || { rm -f "$2"; sleep $((i*4)); }; done; [ -s "$2" ]; }

echo "== 0. tools =="
for a in dex-tools dex-translator dex-reader dex-reader-api dex-ir d2j-base-cmd d2j-smali d2j-jasmin dex-writer d2j-external; do
  get $M/de/femtopedia/dex2jar/$a/2.4.24/$a-2.4.24.jar "$LIB/d2j/$a.jar"; done
for a in asm asm-tree asm-util asm-commons asm-analysis; do get $M/org/ow2/asm/$a/9.7.1/$a-9.7.1.jar "$LIB/d2j/$a.jar"; done
get $M/org/smali/baksmali/2.5.2/baksmali-2.5.2.jar "$LIB/baksmali.jar"
get $M/org/smali/smali/2.5.2/smali-2.5.2.jar "$LIB/smali.jar"
get $M/org/smali/dexlib2/2.5.2/dexlib2-2.5.2.jar "$LIB/dexlib2.jar"
get $M/org/smali/util/2.5.2/util-2.5.2.jar "$LIB/smutil.jar"
get $M/com/google/guava/guava/32.1.3-jre/guava-32.1.3-jre.jar "$LIB/guava.jar"
get $M/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar "$LIB/failureaccess.jar"
get $M/com/beust/jcommander/1.72/jcommander-1.72.jar "$LIB/jcommander.jar"
get $M/org/antlr/antlr-runtime/3.5.2/antlr-runtime-3.5.2.jar "$LIB/antlr.jar"
get https://dl.google.com/dl/android/maven2/com/android/tools/build/apksig/8.3.1/apksig-8.3.1.jar "$LIB/apksig.jar"
OXR=1.1.65
get $M/org/khronos/openxr/openxr_loader_for_android/$OXR/openxr_loader_for_android-$OXR.aar "$LIB/openxr_loader-$OXR.aar"
SM="$LIB/baksmali.jar:$LIB/smali.jar:$LIB/dexlib2.jar:$LIB/smutil.jar:$LIB/guava.jar:$LIB/failureaccess.jar:$LIB/jcommander.jar:$LIB/antlr.jar"

echo "== 1. hero-v78 =="
cat "$REPO"/dist/hero-v78/*.part* > "$W/v78.apk"
want=$(awk '{print $1}' "$REPO/dist/hero-v78/sha256.txt"); got=$(sha256sum "$W/v78.apk" | awk '{print $1}')
[ "$want" = "$got" ] || { echo "hero-v78 reassembly sha256 mismatch"; exit 1; }
(cd "$W" && unzip -q -o v78.apk classes.dex AndroidManifest.xml)

echo "== 2. VR layer -> classes2.dex =="
java -cp "$LIB/d2j/*" com.googlecode.dex2jar.tools.Dex2jarCmd -f -o "$W/game.jar" "$W/classes.dex" >/dev/null 2>&1
mkdir -p "$W/cls" "$W/dex2"
javac -nowarn --release 8 -cp "$AJ:$W/game.jar" -d "$W/cls" $(find "$REPO/vr/common/src" "$REPO/vr/android/src" -name '*.java')
"$BT/d8" --release --min-api 24 --lib "$AJ" --classpath "$W/game.jar" --output "$W/dex2" $(find "$W/cls" -name '*.class')
mv "$W/dex2/classes.dex" "$W/classes2.dex"

echo "== 3. MainActivity -> Vr.wrap =="
java -cp "$SM" org.jf.baksmali.Main d -o "$W/smali" "$W/classes.dex"
python3 "$REPO/tools/vr/patch_mainactivity.py" "$W/smali"
java -cp "$SM" org.jf.smali.Main a -o "$W/classes.dex" "$W/smali"   # dex 035, like v78's own
# gate: register types the device verifier checks (v1 shipped a VerifyError -- check_regtypes.py)
java -cp "$SM" org.jf.baksmali.Main d -a 17 -b "" -r ARGS,DEST \
  --classes 'Lnet/fdgames/ek/android/MainActivity;' -o "$W/regs" "$W/classes.dex"
python3 "$REPO/tools/vr/check_regtypes.py" "$W/regs/net/fdgames/ek/android/MainActivity.smali"

echo "== 4. OpenXR bridge =="
mkdir -p "$W/oxr" "$W/lib/arm64-v8a"
(cd "$W/oxr" && unzip -q -o "$LIB/openxr_loader-$OXR.aar")
"$CC" -shared -fPIC -O2 -Wall -Wno-unused-parameter -I"$W/oxr/prefab/modules/headers/include" \
  -o "$W/lib/arm64-v8a/libekxr.so" "$REPO/vr/android/jni/ekxr.c" \
  -L"$W/oxr/jni/arm64-v8a" -lopenxr_loader -lEGL -lGLESv3 -llog -lm
cp "$W/oxr/jni/arm64-v8a/libopenxr_loader.so" "$W/lib/arm64-v8a/"

echo "== 5. manifest =="
python3 "$REPO/tools/vr/axml.py" "$W/AndroidManifest.xml" "$W/AndroidManifest.vr.xml"

echo "== 6. overlay, assemble, align, sign =="
mkdir -p "$W/ov/lib/arm64-v8a"
cp "$W/AndroidManifest.vr.xml" "$W/ov/AndroidManifest.xml"
cp "$W/classes.dex" "$W/classes2.dex" "$W/ov/"
cp "$W/lib/arm64-v8a/"*.so "$W/ov/lib/arm64-v8a/"
(cd "$W/ov" && rm -f ../vr-overlay.zip && zip -q -X -r ../vr-overlay.zip AndroidManifest.xml classes.dex classes2.dex lib)
python3 "$REPO/tools/vr/assemble_vr.py" "$W/v78.apk" "$W/vr-overlay.zip" "$W/unsigned.apk"
"$BT/zipalign" -P 16 -f 4 "$W/unsigned.apk" "$W/aligned.apk"
mkdir -p "$REPO/build"
"$BT/apksigner" sign --ks "$KS" --ks-pass pass:exiled123 --ks-key-alias ek --key-pass pass:exiled123 \
  --v1-signing-enabled true --v2-signing-enabled true --v3-signing-enabled true \
  --out "$REPO/build/$NAME" "$W/aligned.apk"
"$REPO/tools/check_update_compat.sh" "$REPO/build/$NAME" "$LIB/apksig.jar"
"$BT/aapt2" dump badging "$REPO/build/$NAME" | grep -E "^package|native-code|uses-feature: name='android.hardware.vr" || true

echo "== 7. dist (overlay only; the Pages deploy rebuilds the APK from hero-v78) =="
OUT="$REPO/dist/quest-vr-v$V"; rm -rf "$OUT"; mkdir -p "$OUT"
cp "$W/vr-overlay.zip" "$OUT/"
echo "hero-v78" > "$OUT/base.txt"
echo "$NAME" > "$OUT/name.txt"
sha256sum "$REPO/build/$NAME" | sed "s|  .*|  $NAME|" > "$OUT/sha256.txt"
ls -la "$OUT"
echo "done: build/$NAME  (Pages: https://knightdx91-alt.github.io/Exiled-kingdoms/dist/$NAME)"
