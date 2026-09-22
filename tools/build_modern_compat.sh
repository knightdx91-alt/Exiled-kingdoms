#!/usr/bin/env bash
# Make a finished EK mod APK (hero-v19 and friends) run on a MODERN device --
# target: Galaxy Z Fold 8 (Android 16, 64-bit-only SoC, foldable).
# See deobf/MODERN_DEVICE_COMPAT.md for the reversing/evidence behind every step.
#
# This is the mirror image of tools/build_arm32_compat.sh: that one takes a modern
# build down to Android 4.2.2, this one takes the 4.2.2 build up to Android 16.
# It is a POST-PROCESSOR: it consumes an already-built mod APK, so the output is
# always exactly "<that build> + this layer" and no access to the clean base APK
# is needed.
#
# The four walls it removes (all measured off dist/hero-v18, the last build before
# any of this; wall 1 has since been fixed in the main pipeline as hero-v19, and this
# script re-verifies it rather than trusting it):
#   1. lib/ is armeabi-v7a ONLY -> a 64-bit-only phone rejects the install with
#      INSTALL_FAILED_NO_MATCHING_ABIS. Inject arm64-v8a libGDX 1.9.12 natives
#      (verified drop-in: identical JNI symbol set, 64 KB-aligned so 16 KB-page
#      devices are fine). Both properties are CHECKED here and fail the build.
#   2. v1-only SHA1 signature -> sign v1+v2+v3, with the committed stable keystore
#      when present so this installs in place over the main build.
#   3. MainActivity configChanges=0x4a0 -> the activity is destroyed on every
#      fold/unfold and libGDX restarts the game. Widen it (in-place 4-byte edit).
#   4. external storage: /sdcard needs a runtime grant on Android 11+ that this
#      2023 build never asks for -> tools/patch_modern_device.py.
#
# Zip-swap only (never `apktool b`): resources.arsc and every untouched entry
# stay byte-identical -- same rule as the rest of the pipeline.
#
# Usage: tools/build_modern_compat.sh <in.apk> <out.apk>
# Env:   EK_LIB=<dir>                 cache dir for the jars (default: a temp dir)
#        EK_KEEP_CONFIGCHANGES=1      leave configChanges alone (restart-on-fold,
#                                     the stock behaviour -- fallback if the game's
#                                     own layout misbehaves after a fold)
#        EK_SKIP_STORAGE=1            skip the storage/permission dex patch
# Requires: java (JDK 17+), curl, zip/unzip, keytool, python3. nm (binutils) is
# optional; when present the arm64 libs are symbol-checked against the 32-bit ones.
set -euo pipefail
IN="$(realpath "${1:?usage: build_modern_compat.sh <in.apk> <out.apk>}")"
OUT="$(realpath -m "${2:?usage: build_modern_compat.sh <in.apk> <out.apk>}")"
REPO="$(cd "$(dirname "$0")/.." && pwd)"
WORK="$(mktemp -d)"; trap 'rm -rf "$WORK"' EXIT
LIB="${EK_LIB:-$WORK/lib}"; mkdir -p "$LIB"
GDX=1.9.12
MC=https://repo1.maven.org/maven2

fetch(){ [ -s "$2" ] || curl -sSfL -o "$2" "$1"; }

echo "== 0. tools =="
fetch $MC/org/smali/baksmali/2.5.2/baksmali-2.5.2.jar          "$LIB/baksmali.jar"
fetch $MC/org/smali/smali/2.5.2/smali-2.5.2.jar                "$LIB/smali.jar"
fetch $MC/org/smali/dexlib2/2.5.2/dexlib2-2.5.2.jar            "$LIB/dexlib2.jar"
fetch $MC/org/smali/util/2.5.2/util-2.5.2.jar                  "$LIB/util.jar"
fetch $MC/com/google/guava/guava/32.1.3-jre/guava-32.1.3-jre.jar "$LIB/guava.jar"
fetch $MC/com/beust/jcommander/1.72/jcommander-1.72.jar        "$LIB/jcommander.jar"
fetch $MC/org/antlr/antlr-runtime/3.5.2/antlr-runtime-3.5.2.jar "$LIB/antlr.jar"
fetch https://dl.google.com/dl/android/maven2/com/android/tools/build/apksig/8.3.1/apksig-8.3.1.jar "$LIB/apksig.jar"
CP="$LIB/baksmali.jar:$LIB/smali.jar:$LIB/dexlib2.jar:$LIB/util.jar:$LIB/guava.jar:$LIB/jcommander.jar:$LIB/antlr.jar"

echo "== 1. wall 1: arm64-v8a native libs (libGDX $GDX) =="
fetch $MC/com/badlogicgames/gdx/gdx-platform/$GDX/gdx-platform-$GDX-natives-arm64-v8a.jar             "$LIB/gdx-a64.jar"
fetch $MC/com/badlogicgames/gdx/gdx-box2d-platform/$GDX/gdx-box2d-platform-$GDX-natives-arm64-v8a.jar "$LIB/box-a64.jar"
mkdir -p "$WORK/stage/lib/arm64-v8a" "$WORK/in32"
unzip -o -q "$LIB/gdx-a64.jar" libgdx.so       -d "$WORK/stage/lib/arm64-v8a"
unzip -o -q "$LIB/box-a64.jar" libgdx-box2d.so -d "$WORK/stage/lib/arm64-v8a"
unzip -o -q "$IN" 'lib/armeabi-v7a/*' -d "$WORK/in32" 2>/dev/null || true
if command -v nm >/dev/null && [ -f "$WORK/in32/lib/armeabi-v7a/libgdx.so" ]; then
  for n in libgdx libgdx-box2d; do
    nm -D --defined-only "$WORK/in32/lib/armeabi-v7a/$n.so"   | awk '{print $3}' | grep '^Java_' | sort > "$WORK/s32_$n"
    nm -D --defined-only "$WORK/stage/lib/arm64-v8a/$n.so"    | awk '{print $3}' | grep '^Java_' | sort > "$WORK/s64_$n"
    if ! diff -q "$WORK/s32_$n" "$WORK/s64_$n" >/dev/null; then
      echo "!! $n.so JNI symbol mismatch between the APK's 32-bit lib and libGDX $GDX arm64:"
      diff "$WORK/s32_$n" "$WORK/s64_$n" | head
      echo "!! refusing to ship a native lib that does not match the game's Java side."
      exit 1
    fi
    echo "   $n.so: $(wc -l < "$WORK/s64_$n") JNI symbols, identical to the APK's 32-bit lib"
  done
else
  echo "   (nm unavailable or no 32-bit libs in input -- skipping the symbol check)"
fi
# 16 KB page sizes (Android 15+ hardware): every LOAD segment must be aligned to
# >= 16384. libGDX's arm64 libs use 64 KB, so this is a check, not a fix.
python3 - "$WORK/stage/lib/arm64-v8a"/*.so <<'PYALIGN'
import struct, sys
for path in sys.argv[1:]:
    d = open(path, 'rb').read()
    phoff, phentsize, phnum = struct.unpack_from('<Q', d, 0x20)[0], struct.unpack_from('<H', d, 0x36)[0], struct.unpack_from('<H', d, 0x38)[0]
    aligns = []
    for i in range(phnum):
        o = phoff + i * phentsize
        if struct.unpack_from('<I', d, o)[0] == 1:          # PT_LOAD
            aligns.append(struct.unpack_from('<Q', d, o + 48)[0])
    bad = [a for a in aligns if a % 16384]
    if bad:
        sys.exit('!! %s: LOAD p_align %s is not a multiple of 16 KB -- would fail dlopen on a 16 KB-page device' % (path, bad))
    print('   %s: LOAD p_align %s (>= 16 KB page safe)' % (path.rsplit('/', 1)[-1], ['0x%x' % a for a in aligns]))
PYALIGN

echo "== 2. wall 4: storage/permission dex patch =="
unzip -o -q "$IN" classes.dex -d "$WORK/dexin"
if [ -z "${EK_SKIP_STORAGE:-}" ]; then
  java -cp "$CP" org.jf.baksmali.Main d -l "$WORK/dexin/classes.dex" -o "$WORK/smali"
  ( cd "$WORK" && python3 "$REPO/tools/patch_modern_device.py" )
  java -cp "$CP" org.jf.smali.Main a --api 15 "$WORK/smali" -o "$WORK/stage/classes.dex"
  head -c 8 "$WORK/stage/classes.dex" | grep -q "dex" || { echo "bad dex"; exit 1; }
  echo "   dex format: $(head -c 7 "$WORK/stage/classes.dex" | tail -c 3) (ART reads 035 fine)"
else
  echo "   skipped (EK_SKIP_STORAGE=1)"
fi

echo "== 3. wall 3: foldable configChanges =="
unzip -o -q "$IN" AndroidManifest.xml -d "$WORK/mf"
if [ -z "${EK_KEEP_CONFIGCHANGES:-}" ]; then
  python3 "$REPO/tools/axml_set_config.py" "$WORK/mf/AndroidManifest.xml" \
    "$WORK/stage/AndroidManifest.xml" \
    --activity net.fdgames.ek.android.MainActivity --config-changes 0x40003ffc
else
  echo "   skipped (EK_KEEP_CONFIGCHANGES=1)"
fi

echo "== 4. zip-swap into a copy of the input APK =="
cp "$IN" "$WORK/out.apk"
( cd "$WORK" && zip -q -d out.apk 'META-INF/*' >/dev/null 2>&1 || true )
( cd "$WORK/stage" && zip -q -r "$WORK/out.apk" . )

echo "== 5. wall 2: sign v1+v2+v3 =="
# Reuse the committed stable keystore when present -- the SAME key build_mod_4_2_2.sh
# signs with, so this APK installs straight over that build with no uninstall. Its cert
# is SHA1withRSA (required by 4.2.2); that is the certificate's own self-signature and
# is unrelated to the v2/v3 block, which is SHA-256 either way.
[ -f "$REPO/tools/ek-release.keystore" ] || { echo "tools/ek-release.keystore missing -- refusing to sign with a new key"; exit 1; }
cp "$REPO/tools/ek-release.keystore" "$WORK/ek.keystore"
echo "   using the committed stable keystore (in-place updates across versions)"
cat > "$WORK/Sign.java" <<'JAVA'
import com.android.apksig.ApkSigner; import com.android.apksig.ApkVerifier;
import java.io.File; import java.security.*; import java.security.cert.X509Certificate;
import java.util.Collections;
public class Sign{public static void main(String[] a)throws Exception{
 KeyStore k=KeyStore.getInstance("PKCS12");
 try(java.io.FileInputStream f=new java.io.FileInputStream(a[0])){k.load(f,a[1].toCharArray());}
 PrivateKey pk=(PrivateKey)k.getKey(a[2],a[1].toCharArray());
 X509Certificate c=(X509Certificate)k.getCertificate(a[2]);
 ApkSigner.SignerConfig sc=new ApkSigner.SignerConfig.Builder("CERT",pk,Collections.singletonList(c)).build();
 // minSdk 16 keeps SHA1 v1 digests (harmless -- Android 7+ verifies through v2/v3,
 // and it keeps this APK parseable by the old installer too).
 new ApkSigner.Builder(Collections.singletonList(sc)).setInputApk(new File(a[3]))
   .setOutputApk(new File(a[4])).setMinSdkVersion(16)
   .setV1SigningEnabled(true).setV2SigningEnabled(true).setV3SigningEnabled(true)
   .build().sign();
 ApkVerifier.Result r=new ApkVerifier.Builder(new File(a[4])).setMinCheckedPlatformVersion(24).build().verify();
 System.out.println("verified="+r.isVerified()+" v1="+r.isVerifiedUsingV1Scheme()
   +" v2="+r.isVerifiedUsingV2Scheme()+" v3="+r.isVerifiedUsingV3Scheme());
 for(ApkVerifier.IssueWithParams e:r.getErrors()) System.out.println("ERROR "+e);
 if(!r.isVerified()) System.exit(1);
}}
JAVA
javac -cp "$LIB/apksig.jar" -d "$WORK" "$WORK/Sign.java"
java -cp "$WORK:$LIB/apksig.jar" Sign "$WORK/ek.keystore" exiled123 ek "$WORK/out.apk" "$OUT"

echo "== 6. report =="
unzip -l "$OUT" | grep -E "lib/|classes.dex" || true
python3 "$REPO/tools/axml_set_config.py" <(unzip -p "$OUT" AndroidManifest.xml) --print 2>/dev/null \
  || { unzip -o -q "$OUT" AndroidManifest.xml -d "$WORK/chk" && python3 "$REPO/tools/axml_set_config.py" "$WORK/chk/AndroidManifest.xml" --print; }
"$REPO/tools/check_update_compat.sh" "$OUT" "$LIB/apksig.jar"
echo "== done: $OUT ($(du -h "$OUT" | cut -f1)) =="
