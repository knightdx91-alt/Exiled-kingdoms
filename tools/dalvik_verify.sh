#!/usr/bin/env bash
# Verify a dex/apk with the REAL Android 4.2 (API 17) Dalvik verifier -- the same
# era as the owner's device -- by running the emulator image's native x86 dexopt
# on this host. Catches old-Dalvik VerifyErrors that D8/ART/jadx all accept.
#
# Usage: tools/dalvik_verify.sh <apk-or-dex>
# Exit 0 = verified clean; prints any VFY/DexOpt complaints.
#
# One-time setup (all cached under /tmp):
#   curl -L -o /tmp/sysimg17.zip https://dl.google.com/android/repository/sys-img/android/sysimg_x86-17_r01.zip
#   unzip /tmp/sysimg17.zip -d /tmp/sysimg17
#   git clone https://github.com/devttys0/yaffshiv /tmp/yaffshiv
#   python3 /tmp/yaffshiv/src/yaffshiv --auto -f /tmp/sysimg17/x86/system.img -d /tmp/sys17
#   ln -sfn /tmp/sys17 /system          # bionic binaries need /system paths
#   mkdir -p /dev/log && for f in main system radio events; do : > /dev/log/$f; done
#   (plain files stand in for the Android log devices; strings(1) recovers the text)
# The ashmem shim (no /dev/ashmem on normal kernels) is built by this script.
set -euo pipefail
IN="$(realpath "${1:?usage: dalvik_verify.sh <apk-or-dex>}")"
W="$(mktemp -d)"; trap 'rm -rf "$W"' EXIT

[ -e /system/bin/dexopt ] || { echo "setup missing: see header comments"; exit 2; }

# ashmem shim (raw-syscall, no libc; sysv hash for the 2012 bionic linker).
# Source lives next to this script so the oracle is reproducible from a clean
# container -- it was previously only in a build tree and got lost with it.
SHIM=/tmp/adata/libashmem_shim.so
if [ ! -e "$SHIM" ]; then
  mkdir -p "$(dirname "$SHIM")"
  gcc -m32 -shared -fPIC -nostdlib -O2 -Wl,--hash-style=sysv \
      -o "$SHIM" "$(dirname "$0")/ashmem_shim.c" \
    || { echo "cannot build ashmem shim (need gcc with -m32 / gcc-multilib)"; exit 2; }
fi

case "$IN" in
  *.dex) cp "$IN" "$W/classes.dex" ;;
  *)     unzip -p "$IN" classes.dex > "$W/classes.dex" ;;
esac
( cd "$W" && zip -q app.jar classes.dex )

export ANDROID_ROOT=/system ANDROID_DATA="$W"
export BOOTCLASSPATH=/system/framework/core.jar:/system/framework/core-junit.jar:/system/framework/bouncycastle.jar:/system/framework/ext.jar:/system/framework/framework.jar:/system/framework/telephony-common.jar:/system/framework/mms-common.jar:/system/framework/android.policy.jar:/system/framework/services.jar:/system/framework/apache-xml.jar
for f in main system radio events; do : > /dev/log/$f; done

if timeout 300 env LD_PRELOAD="$SHIM" /system/bin/dexopt --preopt \
     "$W/app.jar" "$W/classes.odex" "v=a,o=v,m=y,u=y" 2>/dev/null; then
  echo "dexopt: OK"
else
  echo "dexopt: FAILED"
fi
strings /dev/log/main | grep -vE "^dalvikvm$" | grep -iE "vfy|verif|reject|denied|DexOpt" || true
[ -s "$W/classes.odex" ] && [ "$(stat -c%s "$W/classes.odex")" -gt 1000 ]
