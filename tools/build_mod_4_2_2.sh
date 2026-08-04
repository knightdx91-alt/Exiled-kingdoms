#!/usr/bin/env bash
# Build the 4.2.2 mod APK: cheats (no-clip + reputation items) + Janod mage companion.
#
# ZIP-SWAP pipeline -- deliberately does NOT use `apktool b`.
# An apktool full rebuild re-encodes AndroidManifest.xml/resources.arsc and rewrites the
# whole zip; on the owner's Android 4.2.2 device the resulting APK failed to install with
# INSTALL_PARSE_FAILED_NO_CERTIFICATES even though the signature verified fine on desktop.
# This pipeline instead copies the original APK and replaces ONLY the entries we changed,
# so the original zip layout, resources.arsc and every untouched entry stay byte-identical.
# The binary AndroidManifest.xml is edited surgically by tools/axml_add_perms.py.
# Signing uses Google's apksig with minSdkVersion=16 (v1+v2) and a SHA1withRSA cert --
# Android <=4.2.2 cannot parse SHA-256/384 certificate signatures.
#
# Usage: tools/build_mod_4_2_2.sh <base.apk> <out.apk>
set -euo pipefail
BASE="$(realpath "${1:?usage: build_mod_4_2_2.sh <base.apk> <out.apk>}")"
OUT="$(realpath -m "${2:?usage: build_mod_4_2_2.sh <base.apk> <out.apk>}")"
REPO="$(cd "$(dirname "$0")/.." && pwd)"
WORK="$(mktemp -d)"; trap 'rm -rf "$WORK"' EXIT
LIB="${EK_LIB:-/tmp}"

echo "== 1. disassemble classes.dex =="
unzip -o -q "$BASE" classes.dex -d "$WORK/dexin"
java -jar "$LIB/baksmali-2.5.2.jar" d -l "$WORK/dexin/classes.dex" -o "$WORK/smali"

echo "== 2. extract the asset files we edit =="
unzip -o -q "$BASE" \
  assets/data/rules/items.txt \
  assets/data/rules/items_text.txt \
  assets/data/rules/bestiary.txt \
  assets/data/rules/skills.txt \
  assets/data/rules/skills2.txt \
  assets/data/rules/skills3.txt \
  assets/data/rules/skills_advanced.txt \
  assets/data/rules/skills_advanced2.txt \
  assets/data/rules/skills_advanced3.txt \
  assets/data/conversations/kingsbridge_wizard.txt \
  assets/data/conversations/hirge.txt \
  assets/data/conversations/adaon.txt \
  assets/data/conversations/mercenary_grisenda.txt \
  assets/data/tmx/G9.tmx \
  assets/data/ui/strings/strings.txt \
  -d "$WORK"

echo "== 3. apply patches =="
( cd "$WORK" && python3 "$REPO/tools/patch_crashlog.py" )
# Cheat items (Tome of Renown / Phase Stone / Anchor Stone) + no-clip are OPT-IN as of
# v13 -- the owner asked for them out of the normal build. EK_CHEATS=1 puts them back.
if [ -n "${EK_CHEATS:-}" ]; then
  ( cd "$WORK" && python3 "$REPO/tools/patch_cheats_v2.py" )
fi
( cd "$WORK" && python3 "$REPO/tools/patch_companion_janod.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_party_ai.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_summon_routes.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_party_fixes.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_xp_share.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_export_fix.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_inventory_icon_fix.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_purge_orphan_items.py" )
( cd "$WORK" && python3 "$REPO/tools/patch_gpgs_deviceid_fix.py" )
# Hero class is opt-out while its pre-menu crash is being hunted:
# EK_SKIP_HERO=1 builds the safe cheats+janod+export APK.
if [ -z "${EK_SKIP_HERO:-}" ]; then
  ( cd "$WORK" && python3 "$REPO/tools/patch_hero_class.py" )
fi

echo "== 4. reassemble dex (api 15 -> dex 035, Dalvik) =="
java -jar "$LIB/smali-2.5.2.jar" a --api 15 "$WORK/smali" -o "$WORK/classes.dex"
head -c 8 "$WORK/classes.dex" | grep -q "dex" || { echo "bad dex"; exit 1; }

echo "== 5. patch binary AndroidManifest.xml (storage perms, no resources.arsc touch) =="
unzip -o -q "$BASE" AndroidManifest.xml -d "$WORK/mf"
python3 "$REPO/tools/axml_add_perms.py" \
  "$WORK/mf/AndroidManifest.xml" "$WORK/AndroidManifest.xml" \
  android.permission.WRITE_EXTERNAL_STORAGE android.permission.READ_EXTERNAL_STORAGE \
  android.permission.READ_LOGS

echo "== 6. zip-swap into a copy of the base APK =="
cp "$BASE" "$WORK/out.apk"
( cd "$WORK" && zip -q -d out.apk 'META-INF/*' >/dev/null 2>&1 || true )
( cd "$WORK" && zip -q out.apk \
    classes.dex \
    AndroidManifest.xml \
    assets/data/rules/items.txt \
    assets/data/rules/items_text.txt \
    assets/data/rules/bestiary.txt \
    assets/data/rules/skills.txt \
    assets/data/rules/skills2.txt \
    assets/data/rules/skills3.txt \
    assets/data/rules/skills_advanced.txt \
    assets/data/rules/skills_advanced2.txt \
    assets/data/rules/skills_advanced3.txt \
    assets/data/conversations/kingsbridge_wizard.txt \
    assets/data/conversations/hirge.txt \
    assets/data/conversations/adaon.txt \
    assets/data/conversations/mercenary_grisenda.txt \
    assets/data/tmx/G9.tmx \
    assets/data/ui/strings/strings.txt )

echo "== 7. sign (SHA1withRSA cert + apksig v1/v2, minSdk 16) =="
cat > "$WORK/relax.security" <<'EOF'
jdk.jar.disabledAlgorithms=
EOF
# NOTE: -sigalg on genkeypair is REQUIRED. keytool defaults to SHA384withRSA, which
# Android <=4.2.2 cannot parse -> INSTALL_PARSE_FAILED_NO_CERTIFICATES.
keytool -genkeypair -keystore "$WORK/ek.keystore" -alias ek -keyalg RSA -keysize 2048 \
  -sigalg SHA1withRSA -validity 10000 -storepass exiled123 -keypass exiled123 \
  -dname "CN=EK Mod, O=EK, C=US" -J-Djava.security.properties="$WORK/relax.security" \
  >/dev/null 2>&1
cat > "$WORK/Sign.java" <<'JAVA'
import com.android.apksig.ApkSigner; import java.io.File; import java.security.*;
import java.security.cert.X509Certificate; import java.util.Collections;
public class Sign{public static void main(String[] a)throws Exception{
 KeyStore k=KeyStore.getInstance("PKCS12");
 try(java.io.FileInputStream f=new java.io.FileInputStream(a[0])){k.load(f,a[1].toCharArray());}
 PrivateKey pk=(PrivateKey)k.getKey(a[2],a[1].toCharArray());
 X509Certificate c=(X509Certificate)k.getCertificate(a[2]);
 ApkSigner.SignerConfig sc=new ApkSigner.SignerConfig.Builder("CERT",pk,Collections.singletonList(c)).build();
 new ApkSigner.Builder(Collections.singletonList(sc)).setInputApk(new File(a[3]))
   .setOutputApk(new File(a[4])).setMinSdkVersion(16)
   .setV1SigningEnabled(true).setV2SigningEnabled(true).build().sign();
 System.out.println("signed");}}
JAVA
javac -cp "$LIB/apksig8.jar" -d "$WORK" "$WORK/Sign.java" 2>/dev/null
java -cp "$WORK:$LIB/apksig8.jar" Sign "$WORK/ek.keystore" exiled123 ek "$WORK/out.apk" "$OUT"

echo "== 8. verify =="
keytool -printcert -jarfile "$OUT" 2>/dev/null | grep -i "signature algorithm" | head -1
jarsigner -J-Djava.security.properties="$WORK/relax.security" -verify "$OUT" 2>/dev/null \
  | grep -i "^jar verified" || { echo "SIGNATURE VERIFY FAILED"; exit 1; }
echo "built: $OUT"
