#!/usr/bin/env bash
# Update-install gate: every mod APK MUST install over the previous one in place
# (no uninstall, saves kept). Android allows that only when the new APK has
#   1. the same signing certificate  (the committed tools/ek-release.keystore),
#   2. the same package name,
#   3. a versionCode >= the installed one.
# Both build scripts run this on their final APK and fail the build if any check fails.
# Usage: tools/check_update_compat.sh <apk> <apksig.jar>
set -euo pipefail
APK="${1:?apk}"; APKSIG="${2:?apksig.jar}"
REPO="$(cd "$(dirname "$0")/.." && pwd)"
PIN_CERT=538B432268A6816ADC17F186D252E9B3E3B72846A18825E58D6CB6CF48E5832F
PIN_PKG=net.fdgames.ek.android
MIN_VC=1207      # the owner's installed builds (v19-v21) are all 1207; never go below
T="$(mktemp -d)"; trap 'rm -rf "$T"' EXIT
javac -cp "$APKSIG" -d "$T" "$REPO/tools/updatecheck/UpdateCheck.java"
read -r CERT PKG VC OK < <(java -cp "$T:$APKSIG" UpdateCheck "$APK" 2>/dev/null | tail -1)
fail=0
[ "$OK" = true ]        || { echo "UPDATE GATE: signature does not verify"; fail=1; }
[ "$CERT" = "$PIN_CERT" ] || { echo "UPDATE GATE: signer cert $CERT != pinned $PIN_CERT (would force a reinstall)"; fail=1; }
[ "$PKG" = "$PIN_PKG" ] || { echo "UPDATE GATE: package $PKG != $PIN_PKG"; fail=1; }
[ "$VC" -ge "$MIN_VC" ] || { echo "UPDATE GATE: versionCode $VC < $MIN_VC (downgrade is refused)"; fail=1; }
[ $fail = 0 ] || { echo "UPDATE GATE FAILED: $APK would not install as an update"; exit 1; }
echo "update gate OK: same key (${CERT:0:16}...), $PKG, versionCode $VC -> installs over the previous build"
