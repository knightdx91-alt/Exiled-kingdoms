#!/usr/bin/env bash
# Desktop test bed for the Quest build (deobf/QUEST_VR_SPEC.md §6): runs hero-v78's own game
# code (dex2jar) in a window through vr/desktop's minimal libGDX backend.
#   tools/vr/run_desktop.sh [2d|3d|table|vr] [script.txt] [shot dir]
# EK_VR_SIZE="1600 900": the game's screen size (the Quest uses 1600x900; scripts are in 1280x720 px, scaled).
# Script commands: vr/desktop/src/ekvr/desktop/Script.java. Needs java 17+, xvfb-run for
# headless use (EK_XVFB=1), curl. Everything downloaded/extracted is cached in $EK_VR_CACHE.
set -euo pipefail
REPO="$(cd "$(dirname "$0")/../.." && pwd)"
MODE="${1:-3d}"; SCRIPT="${2:-}"; SHOTS="${3:-/tmp/ekvr-shots}"
C="${EK_VR_CACHE:-$HOME/.cache/ekvr-desktop}"; mkdir -p "$C/lib" "$C/d2j" "$C/natives" "$SHOTS"
M=https://repo1.maven.org/maven2
get(){ for i in 1 2 3 4 5; do [ -s "$2" ] && return 0; curl -sSfL -o "$2" "$1" || { rm -f "$2"; sleep $((i*4)); }; done; [ -s "$2" ]; }
for a in dex-tools dex-translator dex-reader dex-reader-api dex-ir d2j-base-cmd d2j-smali d2j-jasmin dex-writer d2j-external; do
  get $M/de/femtopedia/dex2jar/$a/2.4.24/$a-2.4.24.jar "$C/d2j/$a.jar"; done
for a in asm asm-tree asm-util asm-commons asm-analysis; do get $M/org/ow2/asm/$a/9.7.1/$a-9.7.1.jar "$C/d2j/$a.jar"; done
for a in lwjgl lwjgl-glfw lwjgl-opengl; do
  get $M/org/lwjgl/$a/3.3.4/$a-3.3.4.jar "$C/lib/$a.jar"
  get $M/org/lwjgl/$a/3.3.4/$a-3.3.4-natives-linux.jar "$C/lib/$a-natives.jar"; done
get $M/com/badlogicgames/gdx/gdx-platform/1.9.12/gdx-platform-1.9.12-natives-desktop.jar "$C/gdx-natives.jar"
get $M/com/badlogicgames/gdx/gdx-box2d-platform/1.9.12/gdx-box2d-platform-1.9.12-natives-desktop.jar "$C/lib/gdx-box2d-natives.jar"
[ -s "$C/natives/libgdx.so" ] || { (cd "$C/natives" && unzip -q -o "$C/gdx-natives.jar" libgdx64.so && cp libgdx64.so libgdx.so); }
if [ ! -s "$C/game.jar" ] || [ ! -d "$C/assets" ]; then
  cat "$REPO"/dist/hero-v78/*.part* > "$C/v78.apk"
  rm -rf "$C/x"; mkdir -p "$C/x"; (cd "$C/x" && unzip -q "$C/v78.apk")
  java -cp "$C/d2j/*" com.googlecode.dex2jar.tools.Dex2jarCmd -f -o "$C/game.jar" "$C/x/classes.dex" >/dev/null 2>&1
  rm -rf "$C/assets"; mv "$C/x/assets" "$C/assets"; rm -rf "$C/x" "$C/v78.apk"
fi
rm -rf "$C/build"; mkdir -p "$C/build"
javac -nowarn -cp "$C/game.jar:$C/lib/*" -d "$C/build" $(find "$REPO/vr/desktop/src" "$REPO/vr/common/src" -name '*.java')
[ "${EK_VR_KEEP:-0}" = 1 ] || rm -rf "$C/storage"; mkdir -p "$C/storage"   # EK_VR_KEEP=1: keep saves from the last run
RUN=(java -XX:+UnlockDiagnosticVMOptions -XX:-BytecodeVerificationLocal -XX:-BytecodeVerificationRemote
     -Djava.library.path="$C/natives" -Dekvr.mode="$MODE" -Dekvr.storage="$C/storage" -Dekvr.shotDir="$SHOTS"
     ${SCRIPT:+-Dekvr.script="$SCRIPT"} -cp "$C/build:$C/game.jar:$C/lib/*" ekvr.desktop.DeskBackend "$C/assets" ${EK_VR_SIZE:-1280 720})
if [ "${EK_XVFB:-0}" = 1 ]; then xvfb-run -a -s "-screen 0 1280x720x24" "${RUN[@]}"; else "${RUN[@]}"; fi
