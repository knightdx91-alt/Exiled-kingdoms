#!/usr/bin/env bash
# Build the Exiled Kingdoms VR (Quest 3S) APK: the tutorial area from hero-v78, in 3D.
# Spec: deobf/QUEST_3D_TUTORIAL_SPEC.md. Project: quest/ (Godot 4.6.2 + OpenXR vendors 5.1).
#
#   1. extract the tutorial area from dist/hero-v78 (tools/quest_extract.py)
#   2. import the Godot project, install the Android build template
#   3. play the whole tutorial on desktop (autotest) -- the build fails if it fails
#   4. export a release APK signed with the committed tools/ek-release.keystore
#   5. check the signing certificate, then split it into 25 MB parts in dist/quest-vN/
#
# Requires: godot 4.6.2 on PATH (or $GODOT) with its export templates installed,
# an Android SDK ($ANDROID_SDK_ROOT: platform-tools, build-tools 35, platforms;android-35),
# JDK 17+, python3 + Pillow, node, xvfb-run (for the autotest; EK_SKIP_AUTOTEST=1 skips).
#
# Usage: tools/build_quest.sh [version]      (default 1 -> dist/quest-v1/)
set -euo pipefail
REPO="$(cd "$(dirname "$0")/.." && pwd)"
Q="$REPO/quest"
V="${1:-1}"
GODOT="${GODOT:-godot}"
SDK="${ANDROID_SDK_ROOT:-${ANDROID_HOME:-}}"
TPL="$HOME/.local/share/godot/export_templates/4.6.2.stable"
KS="$REPO/tools/ek-release.keystore"
WANT_CERT="538b4322"            # first bytes of the owner's release cert SHA-256 (CLAUDE.md)
NAME="ExiledKingdomsVR-v$V.apk"

[ -f "$KS" ] || { echo "tools/ek-release.keystore missing -- refusing to sign with a new key"; exit 1; }
[ -n "$SDK" ] && [ -d "$SDK/build-tools" ] || { echo "set ANDROID_SDK_ROOT to an Android SDK"; exit 1; }
[ -f "$TPL/android_source.zip" ] || { echo "Godot 4.6.2 export templates missing in $TPL"; exit 1; }
BT="$(ls -d "$SDK"/build-tools/* | sort -V | tail -1)"

echo "== 1. extract the tutorial area from hero-v78 =="
python3 "$REPO/tools/quest_extract.py"

echo "== 2. editor settings, import, Android build template =="
CFG="$HOME/.config/godot"; mkdir -p "$CFG"
for f in editor_settings-4.6.tres editor_settings-4.tres; do
  grep -q android_sdk_path "$CFG/$f" 2>/dev/null || cat > "$CFG/$f" <<CFGEOF
[gd_resource type="EditorSettings" format=3]

[resource]
export/android/android_sdk_path = "$SDK"
export/android/java_sdk_path = "${JAVA_HOME:-$(dirname "$(dirname "$(readlink -f "$(which java)")")")}"
CFGEOF
done
if [ "$(cat "$Q/android/.build_version" 2>/dev/null)" != "4.6.2.stable" ]; then
  rm -rf "$Q/android"; mkdir -p "$Q/android/build"
  unzip -qo "$TPL/android_source.zip" -d "$Q/android/build"
  echo "4.6.2.stable" > "$Q/android/.build_version"; touch "$Q/android/build/.gdignore"
  chmod +x "$Q/android/build/gradlew"
fi
"$GODOT" --headless --path "$Q" --import >/dev/null 2>&1 || true

if [ "${EK_SKIP_AUTOTEST:-0}" != 1 ]; then
  echo "== 3. desktop playthrough of the whole tutorial (autotest) =="
  LOG="$(mktemp)"
  timeout 600 xvfb-run -a -s "-screen 0 1280x800x24" "$GODOT" --path "$Q" --rendering-driver opengl3 \
      --resolution 1280x800 -- --autotest > "$LOG" 2>&1 || true
  grep -E "AUTOTEST|SCRIPT ERROR" "$LOG" | head -20
  grep -q "AUTOTEST PASS" "$LOG" && ! grep -q "SCRIPT ERROR" "$LOG" || { echo "autotest failed (log: $LOG)"; exit 1; }
fi

echo "== 4. export release APK (signed with tools/ek-release.keystore) =="
sed -i "s/^version\/code=.*/version\/code=$V/; s/^version\/name=.*/version\/name=\"1.0.$V\"/" "$Q/export_presets.cfg"
mkdir -p "$Q/build"
GODOT_ANDROID_KEYSTORE_RELEASE_PATH="$KS" GODOT_ANDROID_KEYSTORE_RELEASE_USER=ek \
GODOT_ANDROID_KEYSTORE_RELEASE_PASSWORD=exiled123 \
  "$GODOT" --headless --path "$Q" --export-release Quest "$Q/build/$NAME" > "$Q/build/export.log" 2>&1 || true
[ -s "$Q/build/$NAME" ] || { tail -40 "$Q/build/export.log"; echo "export failed"; exit 1; }

echo "== 5. verify + split into dist/quest-v$V =="
"$BT/apksigner" verify --print-certs "$Q/build/$NAME" | tee "$Q/build/certs.txt"
grep -qi "SHA-256 digest: $WANT_CERT" "$Q/build/certs.txt" || { echo "not signed with the owner's key"; exit 1; }
"$BT/aapt2" dump badging "$Q/build/$NAME" | grep -E "^package|uses-feature.*oculus|native-code" || true
OUT="$REPO/dist/quest-v$V"; rm -rf "$OUT"; mkdir -p "$OUT"
( cd "$OUT" && split -b 25M -d -a 2 "$Q/build/$NAME" "$NAME.part" && sha256sum "$Q/build/$NAME" | sed "s|  .*|  $NAME|" > sha256.txt )
ls -la "$OUT"
echo "done: $OUT (Pages: https://knightdx91-alt.github.io/Exiled-kingdoms/dist/$NAME)"
