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
Also `new ExiledKingdoms(this)` -> `new ekvr.android.VrEK(this)`: the game object is our
subclass (v10; a separate wrapper object made EK's own casts of the listener fail). Idempotent.
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
# the game object: ExiledKingdoms -> ekvr.android.VrEK (a subclass; QUEST_VR_SPEC.md §2). EK
# casts Gdx.app.getApplicationListener() to Game and ExiledKingdoms, so the listener must BE it.
body = s[ms:s.index('.end method', ms)]
ctor = re.compile(r'new-instance (\w+), Lnet/fdgames/ek/ExiledKingdoms;(\s+)invoke-direct \{\1, p0\}, '
                  r'Lnet/fdgames/ek/ExiledKingdoms;-><init>\(Lnet/fdgames/ek/IPlatformResolver;\)V')
nb, k = ctor.subn(lambda mm: f'new-instance {mm.group(1)}, Lekvr/android/VrEK;{mm.group(2)}invoke-direct {{{mm.group(1)}, p0}}, '
                  'Lekvr/android/VrEK;-><init>(Lnet/fdgames/ek/IPlatformResolver;)V', body)
if k != 1:
    sys.exit(f'new ExiledKingdoms(this) found {k} times in onCreate (expected 1)')
s = s[:ms] + nb + s[ms + len(body):]
open(f, 'w').write(s)
print(f'MainActivity.onCreate: game = new VrEK(this); initialize({t}, {c}) with {t} = Vr.wrap(this, {g}, {c}); {g} keeps the game')
