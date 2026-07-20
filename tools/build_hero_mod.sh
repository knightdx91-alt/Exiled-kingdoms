#!/usr/bin/env bash
# Build the Hero-class mod (Warrior slot -> "Hero": learns every skill, uses every
# weapon/armor, caster mana). See deobf/HERO_CLASS_MOD_SPEC.md.
# Usage: tools/build_hero_mod.sh <base.apk> [out.apk]
# Requires: java (JDK 17+), curl, zip/unzip, keytool. Fetches tools from Maven.
set -euo pipefail
BASE="${1:?usage: build_hero_mod.sh <base.apk> [out.apk]}"
OUT="${2:-ExiledKingdoms-hero-signed.apk}"
WORK="$(mktemp -d)"; LIB="$WORK/lib"; mkdir -p "$LIB"
fetch(){ curl -sSL -o "$2" "$1"; }
MC=https://repo1.maven.org/maven2
fetch $MC/org/smali/baksmali/2.5.2/baksmali-2.5.2.jar "$LIB/baksmali.jar"
fetch $MC/org/smali/smali/2.5.2/smali-2.5.2.jar       "$LIB/smali.jar"
fetch $MC/org/smali/dexlib2/2.5.2/dexlib2-2.5.2.jar   "$LIB/dexlib2.jar"
fetch $MC/org/smali/util/2.5.2/util-2.5.2.jar         "$LIB/util.jar"
fetch $MC/com/google/guava/guava/32.1.3-jre/guava-32.1.3-jre.jar "$LIB/guava.jar"
fetch $MC/com/beust/jcommander/1.72/jcommander-1.72.jar "$LIB/jcommander.jar"
fetch $MC/org/antlr/antlr-runtime/3.5.2/antlr-runtime-3.5.2.jar "$LIB/antlr.jar"
fetch https://dl.google.com/android/maven2/com/android/tools/build/apksig/8.7.3/apksig-8.7.3.jar "$LIB/apksig.jar"
CP="$LIB/baksmali.jar:$LIB/smali.jar:$LIB/dexlib2.jar:$LIB/util.jar:$LIB/guava.jar:$LIB/jcommander.jar:$LIB/antlr.jar"

unzip -o -q "$BASE" classes.dex -d "$WORK/dexin"
java -cp "$CP" org.jf.baksmali.Main d "$WORK/dexin/classes.dex" -o "$WORK/smali"
unzip -o -q "$BASE" assets/data/ui/strings/strings.txt assets/data/ui/strings/texts.txt -d "$WORK/assets"

python3 - "$WORK" <<'PY'
import sys; w=sys.argv[1]
CC='Lnet/fdgames/Rules/Rules$CharacterClass;'
# 1) ClassRestriction.c(): Hero slot (enum field a = WARRIOR) unlocks everything
p=f'{w}/smali/net/fdgames/Rules/ClassRestriction.smali'; s=open(p,encoding='utf-8').read()
m='.method public final c(Lnet/fdgames/Rules/Rules$CharacterClass;)Ljava/lang/Boolean;\n    .registers 3\n'
assert s.count(m)==1
s=s.replace(m,m+f'\n    sget-object v0, {CC}->a:{CC}\n\n    if-ne p1, v0, :ekhero_not\n\n    sget-object p1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;\n\n    return-object p1\n\n    :ekhero_not\n',1)
open(p,'w',encoding='utf-8').write(s)
# 2) CharacterStats.h(): give Hero slot caster mana (level*2+12)
p=f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterStats.smali'; s=open(p,encoding='utf-8').read()
anc='    :cond_31\n    return v1\n.end method'; assert s.count(anc)>=1
rep=(f'    :cond_31\n    sget-object v4, {CC}->a:{CC}\n\n    if-ne v3, v4, :ekhero_nomana\n\n'
     '    invoke-virtual {p0}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->f()I\n\n'
     '    move-result v1\n\n    goto :goto_25\n\n    :ekhero_nomana\n    return v1\n.end method')
open(p,'w',encoding='utf-8').write(s.replace(anc,rep,1))
# 3) labels: WARRIOR -> Hero, CLASS_DESC_WARRIOR -> Hero blurb (English col, keep CRLF/BOM)
def edit(path,key,val):
    raw=open(path,'rb').read().decode('utf-8'); nl='\r\n' if '\r\n' in raw else '\n'
    L=raw.split(nl)
    for i,l in enumerate(L):
        f=l.split('\t')
        if f and f[0].lstrip('﻿')==key: f[1]=val; L[i]='\t'.join(f); break
    else: raise SystemExit(f'{key} missing')
    open(path,'wb').write(nl.join(L).encode('utf-8'))
edit(f'{w}/assets/assets/data/ui/strings/strings.txt','WARRIOR','Hero')
edit(f'{w}/assets/assets/data/ui/strings/texts.txt','CLASS_DESC_WARRIOR',
 "The Hero is a legendary adventurer bound by no single calling. A Hero can "
 "[BLUE]learn every skill[] from any discipline and [BLUE]use every weapon and armor[] "
 "ever forged, wielding both martial might and arcane power.<p><p>"
 "[BLUE]Base health at level 1[]: 45hp<p>Health bonus/level: +6hp<p>"
 "[BLUE]Mana[]: like a spellcaster (level x2 + 12).")
print("patched")
PY

java -cp "$CP" org.jf.smali.Main a "$WORK/smali" -o "$WORK/classes.dex"
cp "$BASE" "$WORK/out.apk"; zip -q -d "$WORK/out.apk" 'META-INF/*' >/dev/null 2>&1 || true
mkdir -p "$WORK/stage/assets/data/ui/strings"
cp "$WORK/classes.dex" "$WORK/stage/classes.dex"
cp "$WORK/assets/assets/data/ui/strings/"*.txt "$WORK/stage/assets/data/ui/strings/"
( cd "$WORK/stage" && zip -q "$WORK/out.apk" classes.dex assets/data/ui/strings/strings.txt assets/data/ui/strings/texts.txt )

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
echo "== done: $OUT =="; jarsigner -verify "$OUT" | grep -i verified || true
