#!/usr/bin/env python3
"""Route the game's ApplicationListener through ekvr.android.Vr (deobf/QUEST_VR_SPEC.md §4).

In MainActivity.onCreate (smali), right before
    invoke-virtual {p0, vG, vC}, ...AndroidApplication;->initialize(Lcom/badlogic/gdx/a;...)V
insert
    invoke-static {p0, vG, vC}, Lekvr/android/Vr;->wrap(MainActivity, a, AndroidApplicationConfiguration)a
    move-result-object vG
so libGDX starts our VrListener (which owns the real game) with an ES3 context. Idempotent.
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
hook = (f'\n{ind}invoke-static {{p0, {g}, {c}}}, Lekvr/android/Vr;->wrap(Lnet/fdgames/ek/android/MainActivity;'
        f'Lcom/badlogic/gdx/a;Lcom/badlogic/gdx/backends/android/AndroidApplicationConfiguration;)Lcom/badlogic/gdx/a;\n'
        f'\n{ind}move-result-object {g}\n')
s = s[:m.start()] + hook + s[m.start():]
open(f, 'w').write(s)
print(f'MainActivity.onCreate: initialize({g}, {c}) now gets Vr.wrap(this, {g}, {c})')
