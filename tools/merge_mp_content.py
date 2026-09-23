#!/usr/bin/env python3
"""Merge the Multiplayer mod's content pack into a build tree (spec: deobf/MP_CONTENT_SPEC.md).

Usage: merge_mp_content.py <base.apk> <mp.apk> <workdir>

Writes into <workdir>/assets/... every file under assets/data/ of the MP APK that is NEW or
DIFFERENT (by CRC) from the base APK -- the mod's own content: Sorrow-Mod sprites/maps/quests/
conversations, the arena, redesigned UI art, music and sounds, rule tables. The official game
content is byte-identical between our base (1207) and 2025 vanilla (1217), and every enum and
script command matches, so this is data for the same engine.

Excluded (not content):
  * anything outside assets/data/ (PDALIFE repack files, random-named blobs, crash logs)
  * the modder's backups: `_backup_original/` dirs, `*_ORIGINAL_BACKUP*`, `*.bak*`
  * the modder's pre-redesign art kept alongside the new one: `old_*` / `old-*` files in data/ui
  * tool/system files: desktop.ini, *.bat, *.bmfc
Prints the list of merged paths to <workdir>/mp_merged.txt (the build zips exactly these).
"""
import os, re, sys, zipfile

base_apk, mp_apk, work = sys.argv[1:4]


def excluded(name):
    if not name.startswith('assets/data/') or name.endswith('/'):
        return True
    b = os.path.basename(name)
    if '/_backup_original/' in name or '_ORIGINAL_BACKUP' in b or re.search(r'\.bak', b):
        return True
    if name.startswith('assets/data/ui/') and (b.startswith('old_') or b.startswith('old-')):
        return True
    if b.lower() in ('desktop.ini',) or b.endswith('.bat') or b.endswith('.bmfc'):
        return True
    return False


base = {i.filename: i.CRC for i in zipfile.ZipFile(base_apk).infolist()}
mp = zipfile.ZipFile(mp_apk)
merged, skipped_junk, new, changed = [], 0, 0, 0
for info in mp.infolist():
    n = info.filename
    if not n.startswith('assets/'):
        continue
    if excluded(n):
        skipped_junk += 1
        continue
    if n in base and base[n] == info.CRC:
        continue
    out = os.path.join(work, n)
    os.makedirs(os.path.dirname(out), exist_ok=True)
    with mp.open(info) as src, open(out, 'wb') as dst:
        dst.write(src.read())
    merged.append(n)
    if n in base:
        changed += 1
    else:
        new += 1
open(os.path.join(work, 'mp_merged.txt'), 'w').write('\n'.join(merged) + '\n')
print(f"merged MP content: {new} new + {changed} changed files ({skipped_junk} non-content excluded)")
