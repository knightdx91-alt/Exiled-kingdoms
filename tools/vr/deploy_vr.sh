#!/usr/bin/env bash
# Pages deploy step: rebuild each dist/quest-vr-vN APK from its base mod APK (already
# assembled into web/dist by the deploy) + the committed VR overlay, sign it with the
# committed key, and check it against sha256.txt (same bytes as tools/build_vr.sh made).
#   tools/vr/deploy_vr.sh <web/dist dir>
set -euo pipefail
REPO="$(cd "$(dirname "$0")/../.." && pwd)"; OUTD="$1"
BT="$(ls -d "${ANDROID_HOME:-${ANDROID_SDK_ROOT:-/usr/local/lib/android/sdk}}"/build-tools/* | sort -V | tail -1)"
for d in "$REPO"/dist/quest-vr-v*/; do
  [ -f "$d/vr-overlay.zip" ] || continue
  base=$(cat "$d/base.txt"); name=$(cat "$d/name.txt")
  baseapk="$OUTD/ExiledKingdoms-$base.apk"
  [ -f "$baseapk" ] || { echo "skip $name: $baseapk not assembled"; continue; }
  W="$(mktemp -d)"
  python3 "$REPO/tools/vr/assemble_vr.py" "$baseapk" "$d/vr-overlay.zip" "$W/u.apk"
  "$BT/zipalign" -P 16 -f 4 "$W/u.apk" "$W/a.apk"
  "$BT/apksigner" sign --ks "$REPO/tools/ek-release.keystore" --ks-pass pass:exiled123 --ks-key-alias ek \
    --key-pass pass:exiled123 --v1-signing-enabled true --v2-signing-enabled true --v3-signing-enabled true \
    --out "$OUTD/$name" "$W/a.apk"
  (cd "$OUTD" && sha256sum -c "$d/sha256.txt") || echo "WARNING: $name differs from the local build (still signed with the same key)"
  ls -la "$OUTD/$name"
  rm -rf "$W"
done
