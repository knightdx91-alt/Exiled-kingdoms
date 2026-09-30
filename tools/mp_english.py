#!/usr/bin/env python3
"""English text for the MP mod's content (deobf/MP_ENGLISH_SPEC.md).

usage: mp_english.py <base.apk> <workdir>
The MP mod wrote its dialogue and quest journal in Russian into the English column; the game font has no Cyrillic
glyphs, so those lines show as bare punctuation. For every top-level conversation (`text`) and quest (`description`)
file the MP merge put in <workdir>:
  1. a Russian line that exists in the official file (same keys and Spanish text) gets the official English back;
  2. any other Russian line is looked up in tools/mp_en.tsv by id = sha1(trimmed Russian cell)[:10];
  3. the mod's collar lines "...on he" / "Take off he..." get "him" / "his".
Then, in every top-level data .txt of the merge, a mostly-Latin cell with a few Cyrillic look-alike letters
("Leather Сloak") gets the Latin letters. Prints how many Russian lines are left.
"""
import hashlib
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
LOOKALIKE = str.maketrans('АВЕКМНОРСТХаеорсухіІЅѕјЈ', 'ABEKMHOPCTXaeopcyxiISsjJ')
# folder -> (English column, key columns besides the Spanish text)
KINDS = {'conversations': ('text', 2), 'quests': ('description', 1)}


def rid(s):
    return hashlib.sha1(s.strip().encode('utf-8')).hexdigest()[:10]


EN = {}
p = os.path.join(HERE, 'mp_en.tsv')
for line in open(p, encoding='utf-8'):
    if line.startswith('#') or '\t' not in line:
        continue
    k, en = line.rstrip('\r\n').split('\t', 1)
    EN[k.strip()] = en


def lookalike_only(cell):
    c = len(CYR.findall(cell))
    return 0 < c <= 3 and len(re.findall('[A-Za-z]', cell)) > 3 * c


def load(path):
    raw = open(path, 'rb').read()
    if raw[:2] in (b'\xff\xfe', b'\xfe\xff'):
        return None
    bom = raw.startswith(b'\xef\xbb\xbf')
    text = raw[3 if bom else 0:].decode('utf-8', errors='surrogateescape')
    nl = '\r\n' if '\r\n' in text else '\n'
    return text.split(nl), nl, bom


def save(path, lines, nl, bom):
    s = nl.join(lines).encode('utf-8', errors='surrogateescape')
    open(path, 'wb').write((b'\xef\xbb\xbf' if bom else b'') + s)


z = zipfile.ZipFile(BASE)
base_names = set(z.namelist())
merged = open(os.path.join(WORK, 'mp_merged.txt')).read().split()
st = dict(restored=0, translated=0, grammar=0, lookalike=0, left=0)
left_files = set()

for n in merged:
    if not (n.startswith('assets/data/') and n.endswith('.txt') and n.count('/') == 3):
        continue
    path = os.path.join(WORK, n)
    got = load(path)
    if not got:
        continue
    lines, nl, bom = got
    head = lines[0].split('\t')
    kind = KINDS.get(n.split('/')[2])
    ti = head.index(kind[0]) if kind and kind[0] in head else -1
    es = head.index(kind[0] + '_ES') if ti >= 0 and kind[0] + '_ES' in head else -1
    nkey = kind[1] if kind else 0

    def key(r):
        return tuple(r[:nkey]) + ((r[es].strip() if 0 <= es < len(r) else ''),)

    official = {}
    if ti >= 0 and n in base_names:
        for line in z.read(n).decode('utf-8-sig', errors='replace').replace('\r\n', '\n').split('\n')[1:]:
            r = line.split('\t')
            if len(r) > max(ti, es):
                official.setdefault(key(r), r[ti])
    changed = False
    for i in range(1, len(lines)):
        r = lines[i].split('\t')
        old = list(r)
        if 0 <= ti < len(r) and CYR.search(r[ti]) and not lookalike_only(r[ti]):
            k = key(r)
            if k in official and not CYR.search(official[k]):
                r[ti] = official[k]
                st['restored'] += 1
            elif rid(r[ti]) in EN:
                r[ti] = EN[rid(r[ti])]
                st['translated'] += 1
            else:
                st['left'] += 1
                left_files.add(os.path.basename(n))
        if 0 <= ti < len(r):
            for rx, new in GRAMMAR:
                fixed = rx.sub(new, r[ti])
                if fixed != r[ti]:
                    r[ti] = fixed
                    st['grammar'] += 1
        for j, cell in enumerate(r):
            if lookalike_only(cell):
                r[j] = cell.translate(LOOKALIKE)
                st['lookalike'] += 1
        if r != old:
            lines[i] = '\t'.join(r)
            changed = True
    if changed:
        save(path, lines, nl, bom)

print('mp english: %(restored)d official lines restored, %(translated)d translated, %(grammar)d grammar fixes, '
      '%(lookalike)d look-alike letters; %(left)d Russian lines left' % st, 'in %d files' % len(left_files))
