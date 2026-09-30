#!/usr/bin/env python3
"""English text for the MP mod's conversations (deobf/MP_ENGLISH_SPEC.md).

usage: mp_english.py <base.apk> <workdir>
The MP mod wrote its dialogue in Russian into the English `text` column; the game font has no Cyrillic glyphs, so
those lines show as bare punctuation. For every top-level conversation the MP merge put in <workdir>:
  1. a row whose English text is Russian and which exists in the official file (same index, type and Spanish
     text) gets the official English line back;
  2. any other Russian line found in tools/mp_translations.tsv gets that translation;
  3. the mod's collar lines "…on he" / "Take off he…" get "him" / "his".
Prints how many Russian lines are left.
"""
import os
import re
import sys
import zipfile

BASE, WORK = sys.argv[1], sys.argv[2]
HERE = os.path.dirname(os.path.abspath(__file__))
CYR = re.compile('[Ѐ-ӿ]')
GRAMMAR = [(re.compile(r'collar of submission on he\)'), 'collar of submission on him)'),
           (re.compile(r'By tricking he into'), 'By tricking him into'),
           (re.compile(r'Take off he collar'), 'Take off his collar')]

TR = {}
for line in open(os.path.join(HERE, 'mp_translations.tsv'), encoding='utf-8'):
    if line.startswith('#') or '\t' not in line:
        continue
    ru, en = line.rstrip('\r\n').split('\t', 1)
    TR[ru.strip()] = en

z = zipfile.ZipFile(BASE)
base_names = set(z.namelist())
merged = open(os.path.join(WORK, 'mp_merged.txt')).read().split()
restored = translated = grammar = left = 0
left_files = set()

for n in merged:
    if not (n.startswith('assets/data/conversations/') and n.endswith('.txt') and n.count('/') == 3):
        continue
    p = os.path.join(WORK, n)
    raw = open(p, 'rb').read()
    bom = raw.startswith(b'\xef\xbb\xbf')
    text = raw[3 if bom else 0:].decode('utf-8', errors='surrogateescape')
    nl = '\r\n' if '\r\n' in text else '\n'
    lines = text.split(nl)
    head = lines[0].split('\t')
    if 'text' not in head:
        continue
    ti = head.index('text')
    es = head.index('text_ES') if 'text_ES' in head else -1
    official = {}
    if n in base_names:
        for r in z.read(n).decode('utf-8-sig', errors='replace').replace('\r\n', '\n').split('\n')[1:]:
            r = r.split('\t')
            if len(r) > max(ti, es):
                official.setdefault((r[0], r[1], r[es].strip() if es >= 0 else ''), r[ti])
    changed = False
    for i, line in enumerate(lines[1:], 1):
        r = line.split('\t')
        if len(r) <= ti:
            continue
        cell = r[ti]
        if CYR.search(cell):
            key = (r[0], r[1], r[es].strip() if es >= 0 and len(r) > es else '')
            if key in official and not CYR.search(official[key]):
                r[ti] = official[key]
                restored += 1
            elif cell.strip() in TR:
                r[ti] = TR[cell.strip()]
                translated += 1
            else:
                left += 1
                left_files.add(os.path.basename(n))
        for rx, new in GRAMMAR:
            fixed = rx.sub(new, r[ti])
            if fixed != r[ti]:
                r[ti] = fixed
                grammar += 1
        if r[ti] != cell:
            lines[i] = '\t'.join(r)
            changed = True
    if changed:
        s = nl.join(lines).encode('utf-8', errors='surrogateescape')
        open(p, 'wb').write((b'\xef\xbb\xbf' if bom else b'') + s)

print('mp english: %d official lines restored, %d translated, %d grammar fixes; %d Russian lines left in %d files'
      % (restored, translated, grammar, left, len(left_files)))
