#!/bin/bash
# Offline check of a built APK's game data, using the game's OWN loaders on the desktop JVM:
# GameString.a() + Rules.a() + GameWorld.a() (= ExiledKingdoms.initialize "loading game data", whose
# failure leaves the quests null and crashes at startup) and every data/conversations/*.txt through
# net.fdgames.GameLogic.Conversation. Prints "INIT OK" and "conversations ok=N failed=0" when clean;
# "ERROR 4.1 ;null - X" lines are unknown condition names.
#   usage: tools/init_harness/run.sh <apk>
# Needs dex2jar (fetched from Maven Central into $D2J on first use). Android classes the code touches
# are stubbed on demand; bytecode verification is off (dex2jar emits no stack map frames).
set -e
APK="$(realpath "$1")"; HERE="$(cd "$(dirname "$0")" && pwd)"
D2J="${D2J:-/tmp/d2j}"; W="$(mktemp -d)"; trap 'rm -rf "$W"' EXIT
M=https://repo1.maven.org/maven2
get(){ for i in 1 2 3 4; do c=$(curl -sS -o "$2" -w "%{http_code}" "$1") && [ "$c" = 200 ] && return 0; sleep $((i*3)); done; echo "download failed: $1" >&2; exit 1; }
mkdir -p "$D2J"
for a in dex-tools dex-translator dex-reader dex-reader-api dex-ir d2j-base-cmd d2j-smali d2j-jasmin dex-writer d2j-external; do
  [ -s "$D2J/$a.jar" ] || get $M/de/femtopedia/dex2jar/$a/2.4.24/$a-2.4.24.jar "$D2J/$a.jar"; done
for a in asm asm-tree asm-util asm-commons asm-analysis; do
  [ -s "$D2J/$a.jar" ] || get $M/org/ow2/asm/$a/9.7.1/$a-9.7.1.jar "$D2J/$a.jar"; done
cd "$W"
unzip -q -o "$APK" classes.dex 'assets/*' -x '*.png' '*.ogg' '*.mp3' '*.jpg' '*.atlas' 2>/dev/null || true
java -cp "$D2J/*" com.googlecode.dex2jar.tools.Dex2jarCmd -f -o game.jar classes.dex >/dev/null 2>&1
mkdir -p cls astub astubcls
javac -cp game.jar -d cls "$HERE/Harness.java" 2>&1 | grep -v JAVA_TOOL || true
JV="java -XX:+UnlockDiagnosticVMOptions -XX:-BytecodeVerificationLocal -XX:-BytecodeVerificationRemote"
for i in $(seq 1 40); do
  out=$($JV -cp cls:astubcls:game.jar Harness "$W/assets" 2>&1 | grep -v JAVA_TOOL)
  miss=$(echo "$out" | grep -o "NoClassDefFoundError: [a-zA-Z0-9/\$_]*" | head -1 | awk '{print $2}')
  if [ -z "$miss" ]; then echo "$out" | grep "ERROR\|FAIL\|conversations ok\|INIT" | sort | uniq -c; break; fi
  cls=${miss//\//.}; pkg=${cls%.*}; name=${cls##*.}; outer=${name%%\$*}; inner=${name#*\$}
  f=astub/${pkg//.//}/$outer.java; mkdir -p "$(dirname "$f")"
  if [ "$outer" != "$name" ]; then ext=""; [[ $inner == *Exception ]] && ext="extends Exception"
    if [ -f "$f" ]; then sed -i "\$s/}\$/public static class $inner $ext {} }/" "$f"; else echo "package $pkg; public class $outer { public static class $inner $ext {} }" > "$f"; fi
  else ext=""; [[ $name == *Exception ]] && ext="extends Exception"; echo "package $pkg; public class $name $ext {}" > "$f"; fi
  rm -rf astubcls; mkdir astubcls; javac -d astubcls $(find astub -name "*.java") 2>&1 | grep -v JAVA_TOOL || true
done
