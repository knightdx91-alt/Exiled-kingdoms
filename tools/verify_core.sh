#!/usr/bin/env bash
# Compiles the currently-known-good subset of recovered core sources against
# stock libGDX. This is the regression guard for the Option-A grind: as classes
# are de-obfuscated and made to compile, add them to port/core/compiling.txt and
# this must stay green.
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
CACHE="${ROOT}/.cache"; mkdir -p "$CACHE"
GDX_VER=1.12.1
GDX="$CACHE/gdx-${GDX_VER}.jar"
[ -f "$GDX" ] || curl -sSf -o "$GDX" \
  "https://repo1.maven.org/maven2/com/badlogicgames/gdx/gdx/${GDX_VER}/gdx-${GDX_VER}.jar"

cd "$ROOT/port"
OUT="$(mktemp -d)"
mapfile -t SRCS < core/compiling.txt
echo ">> compiling ${#SRCS[@]} known-good classes against libGDX ${GDX_VER}"
if javac -Xmaxerrs 9999 -d "$OUT" -cp "$GDX" "${SRCS[@]}"; then
  echo ">> GREEN: ${#SRCS[@]}/$(find core/src/main/java/net -name '*.java' | wc -l) recovered classes compile."
else
  echo ">> RED: compiling.txt is not clean."; exit 1
fi
rm -rf "$OUT"
