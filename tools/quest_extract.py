#!/usr/bin/env python3
"""Extract the tutorial area from the owner's hero-v78 mod APK into the Quest 3D build.

Spec: deobf/QUEST_3D_TUTORIAL_SPEC.md. Everything comes from the v78 APK (reassembled
from dist/hero-v78/*.part*, sha256-checked), not from the stock 4.2.2 data:

  quest/data/map.json        collision grid, entries, spawns, containers, triggers
  quest/data/floor.png       floor layers baked into one iso image (affine-mapped in 3D)
  quest/data/cards.json      upright tiles (trees, rocks, objects) as billboard cards
  quest/data/tiles/*.png     the tileset images those cards use
  quest/data/game.json       bestiary/weapons/loot/items/classes for this area
  quest/data/conv/*.txt      the two tutorial conversations
  quest/data/sprites|portraits|icons|sfx|music|fonts

Usage: python3 tools/quest_extract.py [path/to/hero-v78.apk]
"""
import hashlib, json, os, shutil, subprocess, sys, tempfile, zipfile
from PIL import Image

REPO = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(REPO, 'quest', 'data')
MAP = 'I10_tutorial'
D = 'assets/data/'

SPRITES = ['male_knight', 'female_knight', 'adaon', 'adt_goblin_warrior', 'wolf']
SOUNDS = ['goblin-7', 'goblin-11', 'goblin-13', 'beast1', 'hit', 'hit_1', 'swing', 'sword']
FONTS = ['immortal30']
STARTER = {'WARRIOR': 'iron_longsword', 'ROGUE': 'iron_dagger', 'CLERIC': 'iron_mace',
           'WIZARD': 'wand_1', 'HERO': 'iron_longsword'}   # web/src/player.js weaponId()
CONVS = ['adaon_tutorial', 'adaon_tutorial_camp']
FLOOR_LAYERS = ('water', 'ground', 'ground2')
CARD_LAYERS = ('scenery', 'objects', 'roofs')
TOP = 128            # bake margin above row 0 for tiles taller than their diamond


def apk_path():
    if len(sys.argv) > 1:
        return sys.argv[1]
    parts = sorted(p for p in os.listdir(os.path.join(REPO, 'dist/hero-v78')) if '.part' in p)
    tmp = os.path.join(tempfile.gettempdir(), 'ek-hero-v78.apk')
    with open(tmp, 'wb') as f:
        for p in parts:
            f.write(open(os.path.join(REPO, 'dist/hero-v78', p), 'rb').read())
    want = open(os.path.join(REPO, 'dist/hero-v78/sha256.txt')).read().split()[0]
    got = hashlib.sha256(open(tmp, 'rb').read()).hexdigest()
    if got != want:
        sys.exit(f'hero-v78 reassembly sha256 mismatch: {got} != {want}')
    return tmp


def tsv(z, name, enc='utf-8'):
    """A rules .txt as a list of dicts keyed by its header row."""
    txt = z.read(D + name).decode(enc).lstrip('﻿')
    rows = [l.rstrip('\r').split('\t') for l in txt.split('\n') if l.strip()]
    head = [h.strip() for h in rows[0]]
    return [dict(zip(head, r)) for r in rows[1:]]


def unq(s):
    s = (s or '').strip()
    return s[1:-1] if len(s) >= 2 and s[0] == '"' and s[-1] == '"' else s


def num(s, d=0):
    try:
        return int(float(s))
    except (TypeError, ValueError):
        return d


def weapon(rows, wid):
    w = next((r for r in rows if r['Weapon_ID'] == wid), None)
    if not w:
        return None
    return {'id': wid, 'name': w['name'], 'min': num(w['min_damage'], 1), 'max': num(w['max_damage'], 3),
            'crit': num(w['crit_chance']), 'critMul': num(w['crit'], 200), 'speed': num(w['Speed'], 10),
            'reach': num(w['reach'], 1), 'ranged': w.get('ranged', '').strip() == '1',
            'type': (w.get('damage_type') or '').strip() or 'Normal'}


def atlas(z, pack):
    """libgdx .pack -> {region: (page, x, y, w, h)}."""
    lines = z.read(D + pack).decode('utf-8').split('\n')
    regs, page, cur = {}, None, None
    for raw in lines:
        l = raw.rstrip('\r')
        if not l.strip():
            page = None
            continue
        if page is None:
            page = l.strip()
            continue
        if not l.startswith(' ') and ':' not in l:
            cur = l.strip()
            regs[cur] = [page, 0, 0, 0, 0]
        elif cur and l.strip().startswith('xy:'):
            x, y = l.split(':')[1].split(',')
            regs[cur][1:3] = [int(x), int(y)]
        elif cur and l.strip().startswith('size:'):
            w, h = l.split(':')[1].split(',')
            regs[cur][3:5] = [int(w), int(h)]
    return regs


def main():
    apk = apk_path()
    z = zipfile.ZipFile(apk)
    if os.path.isdir(OUT):
        shutil.rmtree(OUT)
    for d in ('tiles', 'sprites', 'portraits', 'icons', 'sfx', 'music', 'fonts', 'conv'):
        os.makedirs(os.path.join(OUT, d))

    # ---- map: reuse web/tools/tmx2json.mjs (same parser the web build is verified on)
    work = tempfile.mkdtemp()
    tmxdir = os.path.join(work, 'tmx')
    os.makedirs(tmxdir)
    for n in z.namelist():
        if n.startswith(D + 'tmx/') and (n.endswith('.tsx') or n.endswith('.png') or n.endswith(MAP + '.tmx')):
            open(os.path.join(tmxdir, os.path.basename(n)), 'wb').write(z.read(n))
    subprocess.run(['node', os.path.join(REPO, 'web/tools/tmx2json.mjs'), os.path.join(tmxdir, MAP + '.tmx')],
                   cwd=work, check=True)
    m = json.load(open(os.path.join(work, 'assets/tmx', MAP + '.json')))
    W, H, TW, TH = m['width'], m['height'], m['tilew'], m['tileh']
    L = {l['name']: l for l in m['layers']}

    # collision: GameMap.v() as in web/src/move.js buildWalkable
    blocked = set(m['blockedGids'])
    walk = []
    for i in range(W * H):
        ok = L['ground']['data'][i] != 0 and L['nonwalk']['data'][i] == 0 \
             and L['objects']['data'][i] not in blocked
        walk.append('1' if ok else '0')

    # ---- tiles: floor bake + upright cards
    sheets, split = {}, {}
    def sheet(ts):
        if ts['image'] not in sheets:
            sheets[ts['image']] = Image.open(os.path.join(work, 'assets/tmx', ts['image'])).convert('RGBA')
        return sheets[ts['image']]

    def shadow_split(ts):
        """Iso art paints each tree's shadow on the ground beside it (dark, translucent).
        Stood upright it would hang in the air, so it is baked into the floor instead and
        cut out of the card: (card sheet, shadow sheet)."""
        if ts['image'] not in split:
            src = sheet(ts)
            card, shade = src.copy(), Image.new('RGBA', src.size, (0, 0, 0, 0))
            cp, sp, op = card.load(), shade.load(), src.load()
            for y in range(src.size[1]):
                for x in range(src.size[0]):
                    r, g, b, a = op[x, y]
                    if 0 < a < 200 and r + g + b < 120:
                        sp[x, y] = (r, g, b, a)
                        cp[x, y] = (0, 0, 0, 0)
            split[ts['image']] = (card, shade)
        return split[ts['image']]

    def ts_for(gid):
        found = m['tilesets'][0]
        for ts in m['tilesets']:
            if ts['firstgid'] <= gid:
                found = ts
            else:
                break
        return found

    def tile_img(ts, local):
        cx, cy = (local % ts['columns']) * ts['tilew'], (local // ts['columns']) * ts['tileh']
        return sheet(ts).crop((cx, cy, cx + ts['tilew'], cy + ts['tileh']))

    flat_cache = {}
    def is_flat(ts, local):
        """Art that stays inside the bottom diamond (decals, paving) lies on the floor."""
        k = (ts['image'], local)
        if k not in flat_cache:
            bb = tile_img(ts, local).getchannel('A').point(lambda a: 255 if a > 40 else 0).getbbox()
            flat_cache[k] = bb is None or (ts['tileh'] - bb[1]) <= 40 and (bb[2] - bb[0]) <= TW + 4
        return flat_cache[k]

    offX = (H - 1) * (TW // 2)
    fw, fh = (W + H) * (TW // 2), (W + H) * (TH // 2) + TOP
    floor = Image.new('RGBA', (fw, fh), (0, 0, 0, 255))
    cards = {}
    for lname in FLOOR_LAYERS + CARD_LAYERS:
        layer = L.get(lname)
        if not layer or not layer['visible']:
            continue
        for r in range(H):
            for c in range(W):
                gid = layer['data'][r * W + c]
                if not gid:
                    continue
                ts = ts_for(gid)
                local = gid - ts['firstgid']
                if local < 0 or local >= ts['columns'] * (ts['imageh'] // ts['tileh']):
                    continue
                if lname in FLOOR_LAYERS or is_flat(ts, local):
                    px = (c - r) * (TW // 2) + offX + TW // 2          # bottom-centre (map.js)
                    py = (c + r) * (TH // 2) + TH + TOP
                    img = tile_img(ts, local)
                    floor.alpha_composite(img, (px - ts['tilew'] // 2, py - ts['tileh']))
                else:
                    shade = shadow_split(ts)[1]
                    cx, cy = (local % ts['columns']) * ts['tilew'], (local // ts['columns']) * ts['tileh']
                    px = (c - r) * (TW // 2) + offX + TW // 2
                    py = (c + r) * (TH // 2) + TH + TOP
                    floor.alpha_composite(shade.crop((cx, cy, cx + ts['tilew'], cy + ts['tileh'])),
                                          (px - ts['tilew'] // 2, py - ts['tileh']))
                    e = cards.setdefault(ts['image'], {'image': ts['image'], 'tilew': ts['tilew'],
                                                       'tileh': ts['tileh'], 'columns': ts['columns'],
                                                       'imagew': ts['imagew'], 'imageh': ts['imageh'],
                                                       'cards': []})
                    e['cards'].append([c, r, local])
    floor.convert('RGB').save(os.path.join(OUT, 'floor.png'), optimize=True)
    # 6144x3200: import VRAM-compressed (ETC2/ASTC on the Quest), with mipmaps.
    open(os.path.join(OUT, 'floor.png.import'), 'w').write(
        '[remap]\n\nimporter="texture"\ntype="CompressedTexture2D"\n\n'
        '[params]\n\ncompress/mode=2\nmipmaps/generate=true\n')
    for img in cards:
        split[img][0].save(os.path.join(OUT, 'tiles', img))
    json.dump({'floor': {'w': fw, 'h': fh, 'top': TOP, 'offX': offX, 'tw': TW, 'th': TH},
               'sheets': list(cards.values())}, open(os.path.join(OUT, 'cards.json'), 'w'))

    json.dump({'name': MAP, 'w': W, 'h': H, 'outdoor': m['outdoor'], 'music': m['music'],
               'walk': ''.join(walk), 'entries': m['entries'], 'npcs': m['npcs'],
               'containers': m['containers'], 'triggers': m['triggers']},
              open(os.path.join(OUT, 'map.json'), 'w'))

    # ---- game data for exactly the spawns / loot / items this area uses (v78 rules)
    best = tsv(z, 'rules/bestiary.txt')
    weps = tsv(z, 'rules/weapons.txt')
    loot = tsv(z, 'rules/loot.txt', 'utf-16')
    items = tsv(z, 'rules/items.txt')
    monsters = {}
    for n in m['npcs']:
        sid = n['spawn']
        b = next(r for r in best if r['spawn_ID'] == sid)
        res = [num(x) for x in (b['resist'] or '0,0,0,0,0,0').split(',')] + [0] * 6
        monsters[sid] = {
            'race': b['race'], 'class': b['class'], 'minlevel': num(b['minlevel'], 1),
            'maxlevel': num(b['maxlevel'], 1), 'weapon': weapon(weps, b['weapon_id']),
            'armor': num(b['defense']), 'resist': dict(zip(['Fire', 'Cold', 'Shock', 'Death', 'Toxic', 'Spirit'], res)),
            'loot': b['loot'], 'sprite': b['sprite'], 'faction': b['faction'], 'gender': b['gender'],
            'portrait': b['portrait'], 'aggroSounds': [s for s in unq(b.get('onAggroSound')).split(';') if s]}
    tables = {}
    for row in loot:
        if row.get('table') in {mo['loot'] for mo in monsters.values()}:
            tables.setdefault(row['table'], []).append({'item': num(row['item_id']), 'chance': num(row['chance'])})
    want = {e['item'] for t in tables.values() for e in t if e['item'] > 0}
    for c in m['containers']:
        want |= {num(x) for x in (c['items'] or '').split(',') if x.strip()}
    RES = ['Fire', 'Cold', 'Shock', 'Death', 'Toxic', 'Spirit']
    itemdb = {}
    for it in items:
        if num(it['item_ID'], -1) in want:
            res = [num(x) for x in (it['resistances'] or '').split(',')] if it['resistances'].strip() else []
            itemdb[it['item_ID']] = {'name': it['name'], 'type': it['type'], 'icon': it['icon'],
                                     'armor': num(it['armor']), 'hp': num(it['hp']),
                                     'resist': {RES[i]: v for i, v in enumerate(res) if v},
                                     'onUse': it.get('OnUse', '').strip(), 'value': num(it['value'])}
    creation = json.load(open(os.path.join(REPO, 'web/assets/data/creation.json')))
    json.dump({'source': 'hero-v78 ' + hashlib.sha256(open(apk, 'rb').read()).hexdigest()[:12],
               'monsters': monsters, 'loot': tables, 'items': itemdb,
               'starterWeapons': {k: weapon(weps, v) for k, v in STARTER.items()},
               'classes': creation['classes'], 'xpTable': creation['xpTable'],
               'maxLevel': creation['maxLevel'], 'startGold': creation['startGold']},
              open(os.path.join(OUT, 'game.json'), 'w'), indent=1)

    # ---- art, sound, text
    for s in SPRITES:
        open(os.path.join(OUT, 'sprites', s + '.png'), 'wb').write(z.read(D + f'sprites/{s}.png'))
    for g, p in (('male', '1'), ('female', '1'), ('male', '21')):
        open(os.path.join(OUT, 'portraits', f'{g}_{p}.png'), 'wb').write(z.read(D + f'graphics/portraits/{g}/{p}.png'))
    act = atlas(z, 'graphics/activables.pack')
    itm = atlas(z, 'graphics/items.pack')
    pages = {}
    def crop(regs, name, packdir='graphics/'):
        page, x, y, w, h = regs[name]
        if page not in pages:
            import io
            pages[page] = Image.open(io.BytesIO(z.read(D + packdir + page))).convert('RGBA')
        pages[page].crop((x, y, x + w, y + h)).save(os.path.join(OUT, 'icons', name + '.png'))
    for n in {c['icon'] for c in m['containers']} | {'loot'}:
        crop(act, n)
    for it in itemdb.values():
        if it['icon'] in itm:
            crop(itm, it['icon'])
    for s in SOUNDS:
        open(os.path.join(OUT, 'sfx', s + '.mp3'), 'wb').write(z.read(D + f'sounds/{s}.mp3'))
    open(os.path.join(OUT, 'music', m['music'] + '.mp3'), 'wb').write(z.read(D + f"music/{m['music']}.mp3"))
    for f in FONTS:
        fnt = z.read(D + f'ui/fonts/{f}.fnt')
        open(os.path.join(OUT, 'fonts', f + '.fnt'), 'wb').write(fnt)
        for line in fnt.decode('utf-8', 'replace').split('\n'):
            if line.startswith('page '):
                pf = line.split('file="')[1].split('"')[0]
                open(os.path.join(OUT, 'fonts', pf), 'wb').write(z.read(D + f'ui/fonts/{pf}'))
    for c in CONVS:
        open(os.path.join(OUT, 'conv', c + '.txt'), 'wb').write(z.read(D + f'conversations/{c}.txt'))
    # The wake-up narration is the web build's owner-approved scene (TUTORIAL_SKIP_SPEC.md),
    # not an EK file: it plays after the camp's Sleep#/PlayerRobbed# (APPROX Q7).
    shutil.copy(os.path.join(REPO, 'web/assets/conversations/intro_wakeup.txt'), os.path.join(OUT, 'conv'))
    os.makedirs(os.path.join(OUT, 'ui'))
    for f in ('logo', 'paperbg', 'icon_128'):
        open(os.path.join(OUT, 'ui', f + '.png'), 'wb').write(z.read(D + f'ui/{f}.png'))
    shutil.rmtree(work)
    print(f'quest/data: {W}x{H} map, floor {fw}x{fh}, '
          f'{sum(len(s["cards"]) for s in cards.values())} cards in {len(cards)} sheets, '
          f'{len(monsters)} monster types, {len(itemdb)} items')


if __name__ == '__main__':
    main()
