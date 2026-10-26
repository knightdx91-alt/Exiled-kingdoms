#!/usr/bin/env python3
"""Gate: the patched MainActivity must pass game objects to game methods with their real type.

    check_regtypes.py <baksmali -r ARGS,DEST output of MainActivity.smali>

v1 of the VR APK reused onCreate's game register for the Vr.wrap result, so the register
later handed to e/a/f/a.<init>(ExiledKingdoms, ...) held a plain ApplicationListener and
ART rejected the whole class (VerifyError, black screen on the headset). Nothing in the
build caught it: smali, D8 and the desktop test bed don't run the device verifier.

This reads baksmali's register-type analysis (the "#vN=(Reference,Lx;)" comments before each
instruction) and, for every invoke, checks each argument declared as one of the game's own
classes (Lnet/fdgames/...) against the type the analyzer inferred: it must be null or a
game class too (exact subtype checks need the whole class hierarchy; a game interface such
as IPlatformResolver legitimately takes MainActivity). A libGDX/Android type in a game slot
-- v1's bug -- fails. Exit 1 on any mismatch.
"""
import re
import sys

PROTO = re.compile(r'invoke-(\w+)(?:/range)? \{([^}]*)\}, (L[^;]+;)->[^(]+\(([^)]*)\)')
REG = re.compile(r'([vp]\d+)=\((\w+)(?:,(L[^;]+;|\[[^)]*))?\)')
TYPE = re.compile(r'\[*(?:L[^;]+;|[ZBSCIFJD])')


def regs(spec):
    spec = spec.strip()
    if not spec:
        return []
    if ' .. ' in spec:                                   # invoke/range {v3 .. v6}
        a, b = spec.split(' .. ')
        return [f'{a[0]}{i}' for i in range(int(a[1:]), int(b[1:]) + 1)]
    return [r.strip() for r in spec.split(',')]


bad = 0
types = {}
for n, line in enumerate(open(sys.argv[1]), 1):
    s = line.strip()
    if s.startswith('#') and '=(' in s:
        types = {r: (k, t) for r, k, t in REG.findall(s)}
        continue
    m = PROTO.search(s)
    if not m:
        continue
    kind, rs, owner, params = m.group(1), regs(m.group(2)), m.group(3), TYPE.findall(m.group(4))
    i = 0 if kind == 'static' else 1                     # skip the receiver
    for p in params:
        if i >= len(rs):
            break
        r = rs[i]
        if p.startswith('Lnet/fdgames/'):
            k, t = types.get(r, ('?', None))
            # ours: ekvr.android.VrEK extends ExiledKingdoms (v10)
            if not (k == 'Null' or (k == 'Reference' and t and (t.startswith('Lnet/fdgames/') or t == 'Lekvr/android/VrEK;'))):
                print(f'line {n}: {r} is {k} {t or ""} but {owner} wants {p}\n  {s}')
                bad += 1
        i += 2 if p in ('J', 'D') else 1
if bad:
    sys.exit(f'{bad} argument(s) of the wrong type: the device verifier would reject the class')
print('MainActivity: every game-typed argument holds its declared type')
