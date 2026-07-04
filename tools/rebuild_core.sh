#!/usr/bin/env bash
# Reproducibly rebuilds port/core from the raw decompiled tree, applying every
# de-obfuscation transform in order. Idempotent.
#
# Usage: ./tools/rebuild_core.sh /path/to/decompiled/sources
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
SRCROOT="${1:?usage: rebuild_core.sh <decompiled .../sources>}"
DST="$ROOT/port/core/src/main/java"
T="$ROOT/tools"
CACHE="$ROOT/.cache"; mkdir -p "$CACHE"

echo ">> 1. select game packages (exclude external + relocated-libGDX + gdx-pay + box2dlights)"
rm -rf "$DST"; mkdir -p "$DST"
EXCLUDE="com android a0 h0 r t b d0 e0 q"
cp -r "$SRCROOT/net" "$DST/"
for p in $(ls -d "$SRCROOT"/*/ | xargs -n1 basename); do
  case " $EXCLUDE net " in *" $p "*) continue;; esac
  cp -r "$SRCROOT/$p" "$DST/" 2>/dev/null || true
done

echo ">> 2. drop platform files (import android/gms); keep the R8 synthetic a/a.java"
grep -rlE 'import (com\.google\.android|android)\.' "$DST" --include=*.java \
  | grep -v '/a/a\.java$' | xargs -r rm -f
[ -f "$SRCROOT/a/a.java" ] && { mkdir -p "$DST/a"; cp "$SRCROOT/a/a.java" "$DST/a/a.java"; }
echo ">> 2b. overlay correct stubs (MainActivity, zzbi, Fragment) from port/stubs"
cp -r "$ROOT/port/stubs/." "$DST/"

echo ">> 3. reconstruct broken enums (+ tree-wide constant rename)"
python3 "$T/fix_enums.py" "$DST" >/dev/null

echo ">> 4. remap obfuscated libGDX classes (imports/body/header/return-type)"
python3 "$T/remap_gdx.py" "$DST" "$ROOT/deobf/deobf_map_full.json" >/dev/null

echo ">> 5. targeted jadx-artifact hand-fixes"
python3 - "$DST" <<'PY'
import sys,os
D=sys.argv[1]
def edit(rel,fn):
    p=os.path.join(D,rel)
    if os.path.exists(p):
        s=open(p).read(); s2=fn(s)
        if s2!=s: open(p,'w').write(s2)
edit('net/fdgames/GameEntities/Character.java',
     lambda s: s.replace('?? r6 = 0;','boolean r6 = false;').replace('r6 = 0;','r6 = false;'))
def bonus(s):
    out=[];seen=False
    for l in s.split('\n'):
        if 'private int critDamage = this.critDamage;' in l:
            if seen: continue
            seen=True
        out.append(l)
    return '\n'.join(out)
edit('net/fdgames/Rules/BonusSet.java',bonus)
def skills(s):
    L=s.split('\n')
    for i,l in enumerate(L):
        if 'Skill skill22 = new Skill(' in l and 'boolean z3' not in L[i-1]:
            ind=l[:len(l)-len(l.lstrip())]
            L.insert(i,ind+'boolean z3 = !strArrSplit3[4].toLowerCase(locale).contains("y") || strArrSplit3[4].toLowerCase(locale).contains("m");')
            break
    return '\n'.join(L)
edit('net/fdgames/Rules/Skills.java',skills)
PY

echo ">> 6. type-aware method-call de-obfuscation (JavaParser symbol solver)"
GDX="$CACHE/gdx-1.12.1.jar"
[ -f "$GDX" ] || curl -sSf -o "$GDX" "https://repo1.maven.org/maven2/com/badlogicgames/gdx/gdx/1.12.1/gdx-1.12.1.jar"
"$T/javaparser/run.sh" "$DST" "$GDX" || echo "   (symbol-solver pass reported issues; continuing)"

echo ">> done. core rebuilt at $DST ($(find "$DST" -name '*.java' | wc -l) files)"
