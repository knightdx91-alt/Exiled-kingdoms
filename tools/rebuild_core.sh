#!/usr/bin/env bash
# Reproducibly rebuilds port/core game sources from the raw decompiled tree by
# applying every de-obfuscation transform in order. Idempotent; avoids the
# "orphaned reference" problem of re-running the import-driven remapper on an
# already-rewritten tree.
#
# Usage: ./tools/rebuild_core.sh /path/to/decompiled/sources/net
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
SRC="${1:?usage: rebuild_core.sh <decompiled .../sources/net>}"
DST="$ROOT/port/core/src/main/java/net"
T="$ROOT/tools"

echo ">> 1. reset core from raw decompiled source"
rm -rf "$DST"; mkdir -p "$(dirname "$DST")"; cp -r "$SRC" "$DST"

echo ">> 2. reconstruct broken enums (+ tree-wide constant rename)"
python3 "$T/fix_enums.py" "$DST" >/dev/null

echo ">> 3. remap obfuscated libGDX classes (imports/body/header/return-type)"
python3 "$T/remap_gdx.py" "$DST" "$ROOT/deobf/deobf_map_full.json" >/dev/null

echo ">> 4. targeted jadx-artifact hand-fixes"
python3 - "$DST" <<'PY'
import sys,re,os
D=sys.argv[1]
def edit(rel,fn):
    p=os.path.join(D,rel)
    if not os.path.exists(p): return
    s=open(p).read(); s2=fn(s)
    if s2!=s: open(p,'w').write(s2)
# Character: jadx '?? r6 = 0' -> boolean (GameString.b(String,boolean) proves type)
edit('fdgames/GameEntities/Character.java',
     lambda s: s.replace('?? r6 = 0;','boolean r6 = false;').replace('r6 = 0;','r6 = false;'))
# BonusSet: drop jadx-duplicated field
def bonus(s):
    lines=s.split('\n'); out=[]; seen=False
    for l in lines:
        if 'private int critDamage = this.critDamage;' in l:
            if seen: continue
            seen=True
        out.append(l)
    return '\n'.join(out)
edit('fdgames/Rules/BonusSet.java',bonus)
# Skills: re-declare z3 dropped from the second parallel branch
def skills(s):
    L=s.split('\n')
    for i,l in enumerate(L):
        if 'Skill skill22 = new Skill(' in l and 'boolean z3' not in L[i-1]:
            ind=l[:len(l)-len(l.lstrip())]
            L.insert(i,ind+'boolean z3 = !strArrSplit3[4].toLowerCase(locale).contains("y") || strArrSplit3[4].toLowerCase(locale).contains("m");')
            break
    return '\n'.join(L)
edit('fdgames/Rules/Skills.java',skills)
print("   hand-fixes applied")
PY
echo ">> done. core rebuilt at $DST"
