#!/usr/bin/env python3
"""Route the game's ApplicationListener through ekvr.android.Vr (deobf/QUEST_VR_SPEC.md §4).

In MainActivity.onCreate (smali), right before
    invoke-virtual {p0, vG, vC}, ...AndroidApplication;->initialize(Lcom/badlogic/gdx/a;...)V
insert
    invoke-static {p0, vG, vC}, Lekvr/android/Vr;->wrap(MainActivity, a, AndroidApplicationConfiguration)a
    move-result-object vT
and pass vT to initialize, where vT is a local register onCreate never uses. (vG must keep
its type: right after initialize, onCreate hands vG on as an ExiledKingdoms — reusing vG for
the wrapper made ART reject MainActivity with a VerifyError on the headset, v1 of the VR APK.)
So libGDX starts our VrListener (which owns the real game) with an ES3 context. Idempotent.
Usage: patch_mainactivity.py <smali dir>
"""
import re
import sys

f = sys.argv[1] + '/net/fdgames/ek/android/MainActivity.smali'
s = open(f).read()
if 'Lekvr/android/Vr;->wrap' in s:
    print('MainActivity already routed through ekvr.android.Vr')
    sys.exit(0)
pat = re.compile(r'(\n(\s*)invoke-virtual \{p0, (\w+), (\w+)\}, '
                 r'Lcom/badlogic/gdx/backends/android/AndroidApplication;->initialize\(Lcom/badlogic/gdx/a;'
                 r'Lcom/badlogic/gdx/backends/android/AndroidApplicationConfiguration;\)V)')
m = pat.search(s)
if not m:
    sys.exit('initialize(game, config) call not found in MainActivity.onCreate')
ind, g, c = m.group(2), m.group(3), m.group(4)
# the onCreate method body, its local register count, and a local it never touches
ms = s.rfind('.method', 0, m.start())
me = s.index('.end method', m.start())
body = s[ms:me]
regs = int(re.search(r'\.registers (\d+)', body).group(1))
params = 2                                    # this + Bundle
used = set(int(x) for x in re.findall(r'\bv(\d+)\b', body))
free = [r for r in range(regs - params) if r not in used]
if not free:
    sys.exit('no free local register in MainActivity.onCreate')
t = f'v{free[0]}'
hook = (f'\n{ind}invoke-static {{p0, {g}, {c}}}, Lekvr/android/Vr;->wrap(Lnet/fdgames/ek/android/MainActivity;'
        f'Lcom/badlogic/gdx/a;Lcom/badlogic/gdx/backends/android/AndroidApplicationConfiguration;)Lcom/badlogic/gdx/a;\n'
        f'\n{ind}move-result-object {t}\n')
call = m.group(1).replace(f'{{p0, {g}, {c}}}', f'{{p0, {t}, {c}}}')
s = s[:m.start()] + hook + call + s[m.end():]
open(f, 'w').write(s)
print(f'MainActivity.onCreate: initialize({t}, {c}) with {t} = Vr.wrap(this, {g}, {c}); {g} keeps the game')
