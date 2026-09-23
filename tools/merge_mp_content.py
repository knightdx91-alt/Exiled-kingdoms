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

Format fix-ups (1.3.1218 data -> our 1.3.1207 parser), found by tools/init_harness (offline run of the
game's own GameString/Rules/GameWorld loaders on the merged assets):
  * bestiary.txt `portrait` column: the mod writes "m_118"/"m_80" (gendered name); 1207 does
    Integer.parseInt -> "Error in ExiledKingdoms.initialize", quests never load, crash at startup
    (owner's EK_crash.txt, v29-v31). Rewritten to the number (the row's `gender` picks the folder).
  * conversations/*.txt: 19 of the mod's edited files fail our Conversation parser (the base's 539
    all parse). Damage from the modder's editing, repaired generically: repeated UTF-8 BOMs -> one;
    literal "`t" (PowerShell tab escape) -> TAB; blank lines dropped (split("\t") -> 1 field ->
    AIOOBE); short rows padded to the header's 7 columns; non-numeric node ids ("8b", "8c") renumbered
    above the file's max (Go To references follow); condition names 1207 lacks: VariableSmaller ->
    VariableLower, VariableEquals -> VariableEqual, HasItem -> PlayerHasItem (unknown names make the
    game log an error and phone home); in the actions column, condition names are dropped (can't run)
    and a bare "REP_x,n" becomes IncVariable/DecVariable#REP_x,n (the base's reputation idiom).
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


def fix_bestiary(path):
    raw = open(path, 'rb').read()
    bom = raw.startswith(b'\xef\xbb\xbf')
    text = raw.decode('utf-8-sig')
    lines = text.split('\n')
    head = lines[0].rstrip('\r').split('\t')
    pi = head.index('portrait')
    fixed = 0
    for k in range(1, len(lines)):
        f = lines[k].split('\t')
        if len(f) > pi and re.fullmatch(r'[mfMF]_\d+', f[pi].strip()):
            f[pi] = f[pi].strip()[2:]
            lines[k] = '\t'.join(f)
            fixed += 1
    out = '\n'.join(lines).encode('utf-8')
    open(path, 'wb').write((b'\xef\xbb\xbf' if bom else b'') + out)
    return fixed


COND_RENAMES = [(re.compile(r'(?<![A-Za-z])VariableSmaller#'), 'VariableLower#'),
                (re.compile(r'(?<![A-Za-z])VariableEquals#'), 'VariableEqual#'),
                (re.compile(r'(?<![A-Za-z])HasItem#'), 'PlayerHasItem#')]
# 1207 condition names (net/fdgames/GameLogic/Condition): when one of these sits in the ACTIONS column
# the engine can't run it (logs an error + error report, then does nothing) -> dropped, same effect.
CONDITION_NAMES = set(['areais', 'areaisnt', 'hascompanion', 'hasfollower', 'hasnocompanion', 'hasnofollower', 'hasparty', 'isday', 'isinparty', 'isitemactive', 'isitemhidden', 'isiteminactive', 'isnight', 'isregistered', 'npcinarea', 'npcisdead', 'npcisfollower', 'npcisinparty', 'npcisnotdead', 'npcisnotinparty', 'npcisntdead', 'npcisntinparty', 'npcisntwaiting', 'npciswaiting', 'npcnotinarea', 'playerhasgold', 'playerhasguild', 'playerhasitem', 'playerhasitems', 'playerhasntgold', 'playerhasntitem', 'playerisclass', 'playerisfemale', 'playerislevel', 'playerismale', 'playerisntclass', 'playerisntlevel', 'playeriswounded', 'variableequal', 'variablegreater', 'variablelower'])


def fix_actions(cell):
    q = cell.startswith('"') and cell.endswith('"') and len(cell) > 1
    body = cell[1:-1] if q else cell
    out = []
    for tok in body.split(';'):
        t = tok.strip()
        name = t.split('#')[0].strip().lower()
        m = re.fullmatch(r'(REP_\w+),(-?\d+)', t)
        if m:  # bare "REP_x,n": the mod meant a reputation change (base uses Inc/DecVariable#REP_x,n)
            n = int(m.group(2))
            out.append(('IncVariable#%s,%d' if n >= 0 else 'DecVariable#%s,%d') % (m.group(1), abs(n)))
        elif t and '#' in t and name in CONDITION_NAMES:
            continue
        else:
            out.append(tok)
    new = ';'.join(out)
    if new == body:
        return cell  # untouched: keep the author's quoting exactly
    return ('"' + new + '"') if q and len(out) > 1 else new


def fix_conversation(path, structural):
    raw = open(path, 'rb').read()
    s = raw
    had_bom = s.startswith(b'\xef\xbb\xbf')
    while s.startswith(b'\xef\xbb\xbf'):
        s = s[3:]
    s = s.replace(b'`t', b'\t')
    if structural:
        text = s.decode('utf-8', errors='surrogateescape')
        lines = [l.rstrip('\r') for l in text.split('\n')]
        lines = [l for l in lines if l.strip()]
        if lines:
            ncol = len(lines[0].split('\t'))
            rows = [l.split('\t') for l in lines[1:]]
            ids = {}
            top = max([int(r[0].split(',')[0]) for r in rows if re.fullmatch(r'\d+', r[0].split(',')[0])] + [0])
            for r in rows:
                i = r[0].split(',')[0]
                if not re.fullmatch(r'\d+', i) and i not in ids and re.fullmatch(r'\w+', i):
                    top += 1
                    ids[i] = str(top)
            out = [lines[0]]
            for r in rows:
                if len(r) < ncol:
                    r = r + [''] * (ncol - len(r))
                head = r[0].split(',')
                if head[0] in ids:
                    head[0] = ids[head[0]]
                    r[0] = ','.join(head)
                if len(r) > 4 and r[4].strip() in ids:
                    r[4] = ids[r[4].strip()]
                for k in (5, 6):
                    if len(r) > k:
                        for rx, new in COND_RENAMES:
                            r[k] = rx.sub(new, r[k])
                if len(r) > 6 and r[6]:
                    r[6] = fix_actions(r[6])
                out.append('\t'.join(r))
            s = ('\r\n'.join(out) + '\r\n').encode('utf-8', errors='surrogateescape')
    s = (b'\xef\xbb\xbf' if had_bom else b'') + s
    if s != raw:
        open(path, 'wb').write(s)
        return True
    return False


nconv = 0
for n in merged:
    if n.startswith('assets/data/conversations/') and n.endswith('.txt'):
        top_level = n.count('/') == 3
        nconv += fix_conversation(os.path.join(work, n), top_level)
print(f"fix-up conversations: {nconv} files repaired")

bp = os.path.join(work, 'assets/data/rules/bestiary.txt')
if 'assets/data/rules/bestiary.txt' in merged:
    print(f"fix-up bestiary.txt: {fix_bestiary(bp)} gendered portrait ids -> numbers")

open(os.path.join(work, 'mp_merged.txt'), 'w').write('\n'.join(merged) + '\n')
print(f"merged MP content: {new} new + {changed} changed files ({skipped_junk} non-content excluded)")
