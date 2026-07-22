#!/usr/bin/env bash
# Make an Exiled Kingdoms APK runnable on 32-bit / old Android (tested target:
# Android 4.2.2 / API 17, Dalvik). See deobf/ANDROID_4_2_2_COMPAT.md.
#
# The stock 2025 EK build ships ONLY arm64-v8a native code and a dex in format
# 038 (Android 8.0+). Neither can run on a 32-bit Dalvik device, so it installs
# but crashes instantly. This post-processor fixes all three walls:
#   1. injects lib/armeabi-v7a/{libgdx,libgdx-box2d}.so  (stock libGDX 1.9.12 —
#      byte-for-byte JNI-symbol match to EK's arm64 libs)
#   2. rewrites classes.dex to dex format 035 (smali --api 15)
#   3. adds WRITE/READ_EXTERNAL_STORAGE so .bak save export works on API 17
# then re-signs (v1+v2; API 17 uses v1).
#
# Usage: tools/build_arm32_compat.sh <in.apk> <out.apk>
# Requires: java (JDK 17+), curl, zip/unzip, keytool, python3.
set -euo pipefail
IN="${1:?usage: build_arm32_compat.sh <in.apk> <out.apk>}"
OUT="${2:?usage: build_arm32_compat.sh <in.apk> <out.apk>}"
HERE="$(cd "$(dirname "$0")" && pwd)"
GDX=1.9.12
WORK="$(mktemp -d)"; LIB="$WORK/lib"; mkdir -p "$LIB"
echo ">> workdir: $WORK"

fetch(){ curl -sSL -o "$2" "$1"; }
MC=https://repo1.maven.org/maven2
fetch $MC/org/smali/baksmali/2.5.2/baksmali-2.5.2.jar   "$LIB/baksmali.jar"
fetch $MC/org/smali/smali/2.5.2/smali-2.5.2.jar         "$LIB/smali.jar"
fetch $MC/org/smali/dexlib2/2.5.2/dexlib2-2.5.2.jar     "$LIB/dexlib2.jar"
fetch $MC/org/smali/util/2.5.2/util-2.5.2.jar           "$LIB/util.jar"
fetch $MC/com/google/guava/guava/32.1.3-jre/guava-32.1.3-jre.jar "$LIB/guava.jar"
fetch $MC/com/beust/jcommander/1.72/jcommander-1.72.jar "$LIB/jcommander.jar"
fetch $MC/org/antlr/antlr-runtime/3.5.2/antlr-runtime-3.5.2.jar  "$LIB/antlr.jar"
fetch https://dl.google.com/android/maven2/com/android/tools/build/apksig/8.7.3/apksig-8.7.3.jar "$LIB/apksig.jar"
CP="$LIB/baksmali.jar:$LIB/smali.jar:$LIB/dexlib2.jar:$LIB/util.jar:$LIB/guava.jar:$LIB/jcommander.jar:$LIB/antlr.jar"

# --- 32-bit native libs (stock libGDX 1.9.12, JNI-identical to EK's arm64) ---
GDXB=$MC/com/badlogicgames/gdx
fetch $GDXB/gdx-platform/$GDX/gdx-platform-$GDX-natives-armeabi-v7a.jar       "$WORK/gdx.jar"
fetch $GDXB/gdx-box2d-platform/$GDX/gdx-box2d-platform-$GDX-natives-armeabi-v7a.jar "$WORK/box.jar"
mkdir -p "$WORK/stage/lib/armeabi-v7a"
unzip -o -q "$WORK/gdx.jar" libgdx.so       -d "$WORK/stage/lib/armeabi-v7a"
unzip -o -q "$WORK/box.jar" libgdx-box2d.so -d "$WORK/stage/lib/armeabi-v7a"
ls -l "$WORK/stage/lib/armeabi-v7a"

# --- classes.dex -> format 035 (only if it is newer) ---
unzip -o -q "$IN" classes.dex -d "$WORK/dexin"
VER=$(head -c 7 "$WORK/dexin/classes.dex" | tail -c 3)
echo ">> input dex format: $VER"
if [ "$VER" != "035" ]; then
  echo ">> downgrading dex $VER -> 035"
  java -cp "$CP" org.jf.baksmali.Main d "$WORK/dexin/classes.dex" -o "$WORK/smali"
  java -cp "$CP" org.jf.smali.Main a --api 15 "$WORK/smali" -o "$WORK/stage/classes.dex"
  NEWVER=$(head -c 7 "$WORK/stage/classes.dex" | tail -c 3); echo ">> output dex format: $NEWVER"
else
  cp "$WORK/dexin/classes.dex" "$WORK/stage/classes.dex"
fi

# --- AndroidManifest.xml: lower minSdk (26->16) + add storage permissions ---
# The 2025 base declares minSdkVersion 26 (Android 8.0), so a strict installer
# refuses it on 4.2.2. Sorrows/ENB install because they declare 16 — match that.
unzip -o -q "$IN" AndroidManifest.xml -d "$WORK/mf"
python3 "$HERE/axml_add_perms.py" "$WORK/mf/AndroidManifest.xml" "$WORK/stage/AndroidManifest.xml" --min-sdk 16

# --- repackage: keep original zip, swap/add our entries ---
cp "$IN" "$WORK/out.apk"
zip -q -d "$WORK/out.apk" 'META-INF/*' >/dev/null 2>&1 || true
( cd "$WORK/stage" && zip -q -r "$WORK/out.apk" classes.dex AndroidManifest.xml lib/armeabi-v7a )

# --- sign (v1 for API 17, v2 for modern) ---
keytool -genkeypair -keystore "$WORK/ek.keystore" -alias ek -keyalg RSA -keysize 2048 \
  -validity 10000 -storepass exiled123 -keypass exiled123 -dname "CN=EK Mod, O=Local, C=NA" >/dev/null 2>&1
cat > "$WORK/Sign.java" <<'JAVA'
import com.android.apksig.ApkSigner; import java.io.File; import java.security.*;
import java.security.cert.X509Certificate; import java.util.Collections;
public class Sign{public static void main(String[] a)throws Exception{
 KeyStore k=KeyStore.getInstance("PKCS12");
 try(java.io.FileInputStream f=new java.io.FileInputStream(a[0])){k.load(f,a[1].toCharArray());}
 PrivateKey pk=(PrivateKey)k.getKey(a[2],a[1].toCharArray());
 X509Certificate c=(X509Certificate)k.getCertificate(a[2]);
 ApkSigner.SignerConfig sc=new ApkSigner.SignerConfig.Builder("ek",pk,Collections.singletonList(c)).build();
 new ApkSigner.Builder(Collections.singletonList(sc)).setInputApk(new File(a[3]))
   .setOutputApk(new File(a[4])).setV1SigningEnabled(true).setV2SigningEnabled(true).build().sign();
 System.out.println("SIGNED "+a[4]);}}
JAVA
javac -cp "$LIB/apksig.jar" -d "$WORK" "$WORK/Sign.java"
java -cp "$WORK:$LIB/apksig.jar" Sign "$WORK/ek.keystore" exiled123 ek "$WORK/out.apk" "$OUT"
echo "== done: $OUT =="
jarsigner -verify "$OUT" | grep -i 'verified' || true
