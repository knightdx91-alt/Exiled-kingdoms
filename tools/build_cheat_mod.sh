#!/usr/bin/env bash
# Build the base-game cheat mod (no-clip + max reputation) — see deobf/CHEAT_MOD_SPEC.md.
# Usage: tools/build_cheat_mod.sh <base.apk> [out.apk]
# Requires: java (JDK 17+), curl, zip/unzip, keytool. Fetches smali/baksmali/apksig from Maven.
set -euo pipefail

BASE="${1:?usage: build_cheat_mod.sh <base.apk> [out.apk]}"
OUT="${2:-ExiledKingdoms-cheats-signed.apk}"
WORK="$(mktemp -d)"; LIB="$WORK/lib"; mkdir -p "$LIB"
echo "workdir: $WORK"

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

# 1. disassemble
unzip -o -q "$BASE" classes.dex -d "$WORK/dexin"
java -cp "$CP" org.jf.baksmali.Main d "$WORK/dexin/classes.dex" -o "$WORK/smali"

# 2. patch smali + append item rows
unzip -o -q "$BASE" assets/data/rules/items.txt -d "$WORK/assets"
python3 - "$WORK" <<'PY'
import sys
w=sys.argv[1]
# --- no-clip in GameMap.C(II)Z ---
p=f'{w}/smali/m0/b.smali'; s=open(p,encoding='utf-8').read()
m='.method public final C(II)Z\n    .registers 6\n'
assert s.count(m)==1
s=s.replace(m, m+'''
    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;

    move-result-object v0

    iget-object v0, v0, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    const-string v1, "noclip"

    invoke-virtual {v0, v1}, Lnet/fdgames/GameWorld/GameVariables;->b(Ljava/lang/String;)I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :ekcheat_noclip_off

    return v1

    :ekcheat_noclip_off
''',1)
open(p,'w',encoding='utf-8').write(s)
# --- grant items in Player.C1()V ---
p=f'{w}/smali/net/fdgames/GameEntities/Final/Player.smali'; s=open(p,encoding='utf-8').read()
a=s.index('.method public final C1()V'); b=s.index('.end method',a); body=s[a:b]
assert body.rstrip().endswith('return-void')
g=''
for h in ('0x2706','0x2707','0x2708'):
    g+=('    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;\n\n'
        '    iget-object v0, v0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->inventory:Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;\n\n'
        f'    const v1, {h}\n\n'
        '    invoke-virtual {v0, v1}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->c(I)Z\n\n')
nb=body[:body.rindex('return-void')]+g+'    return-void\n'
open(p,'w',encoding='utf-8').write(s[:a]+nb+s[b:])
# --- items.txt: append 3 rows ---
p=f'{w}/assets/assets/data/rules/items.txt'; raw=open(p,encoding='utf-8').read()
cols=raw.split('\n')[0].split('\t'); n=len(cols); ix={c:i for i,c in enumerate(cols)}
def row(**kw):
    r=['']*n
    for k,v in kw.items(): r[ix[k]]=str(v)
    return '\t'.join(r)
reps=['varsilia','mercia','ilmara','thuram','the_three','town_lannegar','town_kingsbridge',
'town_rhoneis','town_jabal','town_sydarun','town_new_anthur','town_fogas','town_whitetower',
'varannari','loreseekers','goldenhand','seventhhouse','wizardsguild','warriorsguild',
'friguld_governor','town_lamis','town_port_malan','town_solliga']
rows=[
 row(item_ID='9990',name='Tome of Renown',type='general',armor='0',value='-1',icon='book4',hp='0',mana='0',
     OnUse=';'.join(f'SetVariable#REP_{f},100' for f in reps)),
 row(item_ID='9991',name='Phase Stone',type='general',armor='0',value='-1',icon='ring1',hp='0',mana='0',OnUse='SetVariable#noclip,1'),
 row(item_ID='9992',name='Anchor Stone',type='general',armor='0',value='-1',icon='ring1',hp='0',mana='0',OnUse='SetVariable#noclip,0'),
]
open(p,'w',encoding='utf-8').write(raw.rstrip('\n')+'\n'+'\n'.join(rows)+'\n')
print("patched smali + items.txt")
PY

# 3. reassemble dex
java -cp "$CP" org.jf.smali.Main a "$WORK/smali" -o "$WORK/classes.dex"

# 4. repackage (swap 2 entries; leaves resources.arsc etc. intact)
cp "$BASE" "$WORK/out.apk"
zip -q -d "$WORK/out.apk" 'META-INF/*' >/dev/null 2>&1 || true
mkdir -p "$WORK/stage/assets/data/rules"
cp "$WORK/classes.dex" "$WORK/stage/classes.dex"
cp "$WORK/assets/assets/data/rules/items.txt" "$WORK/stage/assets/data/rules/items.txt"
( cd "$WORK/stage" && zip -q "$WORK/out.apk" classes.dex assets/data/rules/items.txt )

# 5. sign v1+v2
keytool -genkeypair -keystore "$WORK/ek.keystore" -alias ek -keyalg RSA -keysize 2048 \
  -validity 10000 -storepass exiled123 -keypass exiled123 \
  -dname "CN=EK Mod, O=Local, C=NA" >/dev/null 2>&1
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
jarsigner -verify "$OUT" | grep -i verified || true
