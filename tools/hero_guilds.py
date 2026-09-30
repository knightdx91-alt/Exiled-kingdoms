#!/usr/bin/env python3
"""Hero joins every guild (deobf/HERO_GUILDS_SPEC.md).

usage: hero_guilds.py <base.apk> <workdir>
Edits the guild-join conversations (root + every language copy) so the Hero (Warrior slot) passes the one-oath
rule and the Wizard's Guild / Church class gates. Files not already in <workdir> are taken from the base APK;
every edited path is appended to <workdir>/mp_merged.txt so step 6a zips it in.
"""
import os
import sys
import zipfile

BASE, WORK = sys.argv[1], sys.argv[2]
FILES = ['IM_ilemma', 'NG_sewers_kardagis', 'NG_temple_bishop', 'NG_warriors_daukar',
         'NI_hall_archbishop', 'NI_hall_bishop', 'NI_hall_bishop2']
CHURCH = {'NG_temple_bishop', 'NI_hall_archbishop', 'NI_hall_bishop', 'NI_hall_bishop2'}
LANGS = ['', 'CZ/', 'DE/', 'FR/', 'IT/', 'PL/', 'PT/', 'RU/', 'TR/']
HERO_OUT = 'PlayerIsntClass#warrior'

z = zipfile.ZipFile(BASE)
names = set(z.namelist())
listed_path = os.path.join(WORK, 'mp_merged.txt')
listed = set(open(listed_path).read().split()) if os.path.exists(listed_path) else set()
added, edits = [], 0

for f in FILES:
    for lang in LANGS:
        n = 'assets/data/conversations/%s%s.txt' % (lang, f)
        p = os.path.join(WORK, n)
        if not os.path.exists(p):
            if n not in names:
                continue
            os.makedirs(os.path.dirname(p), exist_ok=True)
            open(p, 'wb').write(z.read(n))
        raw = open(p, 'rb').read()
        bom = raw.startswith(b'\xef\xbb\xbf')
        text = raw[3 if bom else 0:].decode('utf-8', errors='surrogateescape')
        nl = '\r\n' if '\r\n' in text else '\n'
        lines = text.split(nl)
        head = lines[0].split('\t')
        if 'conditions' not in head:
            continue                 # text-only translation (UTF-16 "translation" sheet): logic comes from the root file
        ci = head.index('conditions')
        out, n_file = [], 0
        for line in lines:
            r = line.split('\t')
            cond = r[ci].strip() if len(r) > ci else ''
            if cond == 'PlayerHasGuild#':
                r[ci] = '"PlayerHasGuild#;%s"' % HERO_OUT
                n_file += 1
            elif cond == 'PlayerIsntClass#wizard' and f == 'IM_ilemma':
                r[ci] = '"PlayerIsntClass#wizard;%s"' % HERO_OUT
                n_file += 1
            out.append('\t'.join(r))
            if cond == 'PlayerIsClass#cleric' and f in CHURCH:
                h = list(r)
                h[ci] = 'PlayerIsClass#warrior'
                out.append('\t'.join(h))
                n_file += 1
        if n_file:
            s = nl.join(out).encode('utf-8', errors='surrogateescape')
            open(p, 'wb').write((b'\xef\xbb\xbf' if bom else b'') + s)
            edits += n_file
            if n not in listed:
                added.append(n)
                listed.add(n)

if added:
    with open(listed_path, 'a') as fh:
        fh.write(''.join(a + '\n' for a in added))
print('hero guilds: %d rows edited, %d files added to the zip list' % (edits, len(added)))
if edits < 7 * 2:
    sys.exit('hero guilds: too few edits (%d) -- conversation layout changed?' % edits)
