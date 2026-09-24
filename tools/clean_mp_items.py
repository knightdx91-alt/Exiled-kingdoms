#!/usr/bin/env python3
"""Tidy the Multiplayer mod's joke content (spec: deobf/MP_ITEMS_REVIEW.md). Run by merge_mp_content.py on the
merged tree; data only, no code.

Owner: "He added a lot of stupid items, I got a dildo as a drop." Decisions (2026-09-24):
  * crude items: renamed to plain fantasy items (new name + description in every language column, stock
    icon/sprite where the art was crude) so quests that hand them out and saves that hold them keep working;
  * crude + modern/joke + developer-junk items: no longer drop. Their rows in loot tables and their entries in
    map chests are replaced with a plain stock item of the same kind (tables and chests keep their size);
    shop lists just lose them;
  * crude NPC display names renamed.
"""
import glob, os, re

# ---- what changes -------------------------------------------------------------------------------------
# id -> (name, description or None = keep, icon or None, sprite or None)
RENAME = {
    '10106': ('Oak Cudgel', 'A short, heavy cudgel of polished oak. It can also be used in a fight.', None, None),
    '10996': ('Divine Oak Cudgel', None, None, None),
    '10616': ('Axe of the Bull Lord', 'The great axe of the minotaur warlord, still notched from his last battle.', None, None),
    '10648': ('Iron Girdle', 'A heavy iron girdle. Uncomfortable, but it turns a blow.', None, None),
    '10361': ('Heartseeker', 'Its arrows always seem to find the heart.', None, None),
    '10892': ('Stout Dirk', 'Short but sturdy. Instills fear in enemies.', None, 'dagger'),
    '10995': ('Perfect Stout Dirk', None, None, 'dagger'),
    '10808': ("Michaela's silk ribbon", 'A silk ribbon embroidered with little vorators. Michaela will want it back.', None, None),
    '10994': ('Magic Sketchbook', 'Sansa clearly worked hard on these enchanted sketches.', None, None),
    '10990': ("Sansa's Breastplate", 'A finely made breastplate. I wonder what Sansa bought it for?', 'armor_plate_chest_yellow', 'plate'),
    '11058': ("Sansa's Breastplate", 'A finely made breastplate. I wonder what Sansa bought it for?', 'armor_plate_chest_yellow', 'plate'),
    '10108': ("Hireling's Contract", 'A sellsword will fight at your side for an hour.', None, None),
    '10402': ('Collar of Obedience', 'Dark magic lies in this collar: whoever is strong, nimble or clever enough to put it on'
              ' someone can make them obey.', None, None),
    '10400': ('Troll Tusk Necklace', 'Huge and smelly, just like its former owner. You can wear it around your neck.',
              'medallion1', None),
    '11019': ('Wooden figurine', 'Someone carved it very carefully.', 'totem2', None),
    '11035': ('Black rag', 'A worn black cloth with the image of a muscular man.', 'grey_pelt', None),
    '10110': ('Padded Breeches', "Someone's padded breeches. A good wash and they're wearable.", None, None),
    '11011': (None, 'Why are we being treated so unfairly?! - she exclaimed. Magister thought about it and replied:'
              ' Because the world was never fair.', None, None),
    # modern / joke
    '10114': ('Firewater', 'Home-made strong moonshine! (-50 HP, +3 melee attack for 40 seconds)', None, None),
    '10115': ('Distilled Spirit', 'Adds +100 to melee attack for 3 seconds, -30 HP.', None, None),
    '10107': ('Iron Weight', 'In the right hands it can be a formidable weapon. Maybe.', None, None),
    '10111': ('Hearty Gruel', 'What fighters eat, or what pigs are fed. +5 melee attack for 20 seconds.', None, None),
    '10109': ('Training Breeches', 'Loose breeches for training.', None, None),
    '10340': ('Weavesteel Vest', 'No heavier than a coat, yet it hardens like steel when struck.', None, None),
    '10213': ('Rapid Bow', 'Arrange a shower of arrows for your enemies!', None, None),
    '10615': ('Prayer Beads', 'A string of worn prayer beads.', None, None),
    '10864': ('Tavern Commendation', 'The tavern regulars are proud of you.', None, None),
    '10985': ('Sickening Blade', None, None, None),
    '10443': ('Eye of Tol, Reforged', None, None, None),
    '10797': ('Bearskin Belt', 'Your faithful talisman.', None, None),
    '10835': ('Orcish Iron Breeches', 'Orcs know very well which part needs the most protection.', None, None),
    '10419': ('Ape Meat', None, None, None),
    '10428': ('Withered Claw', 'A reminder that everything ends.', None, None),
    '11013': (None, "He sat down next to the grave, and a ghost appeared before him and said angrily: Don't do that"
              " here!", None, None),
    '11049': (None, "This little pest won't be able to sting anyone anymore!", None, None),
}
# name families (every Bewitched/Blessed variant): English substring -> replacement
FAMILY = [('Divine Dildo', 'Divine Oak Cudgel'), ('Dildo', 'Oak Cudgel'), ('Perfect Impressive Dignity', 'Perfect Stout Dirk'),
          ('Impressive Dignity', 'Stout Dirk'), ('Satisfier', 'Heartseeker'), ('Gangster Bit', "Brigand's Cudgel")]
CRUDE_SPRITE = re.compile(r'^dyldo')           # in-hand sprites of the crude daggers -> stock dagger
VALUE = {'10864': '100'}                         # the tavern certificate was worth 1,400,000 gold

NO_DROP = set('''10106 10888 10889 10890 10891 10996 10616 10648 10361 10964 10965 10966 10892 10995 10896 10897 10898
10899 10808 10809 10994 10990 11058 10108 10402 10400 11019 11035 11011 10110 11018
10114 10115 10107 10123 10901 10902 10903 10904 10905 10111 10109 10340 10213 10615 10864 10985 10443 10797 10835
10419 10428 10624 10626 10763'''.split())
# replacement drop by item type (stock items): keeps loot tables and chests the same size
STOCK = {'WEAPON': '758', 'POTION': '5000', 'armor_legs': '101', 'armor_chest': '100'}
STOCK_OTHER = '1002'

NPC_NAMES = {'Pimp': 'Racketeer', 'Harlot': 'Bandit Scout', 'Dominatrix Philippa': 'Warlady Philippa',
             "Bull's Dick": 'Bull Lord', 'Green bastard': 'Green Brute', 'Just sex': 'Brawler',
             'Anal lord': 'Abyssal Lord', "It's kind of fucked up": 'Twisted Horror', 'Dream EK-wanker': 'Dream Wanderer',
             'Femipet': 'Tamed Demon', 'Black guy': 'Stranger', 'Black Bro': 'Brother in Arms', 'Dead Bastard': 'Dead Brute',
             'Great Good Dignity': 'Great Demon', 'Best tank in game': 'Wild Brawler',
             'You know who it is': 'Mysterious Stranger', 'Illusory bro': 'Illusory Brother',
             'Machine Gunner': 'Arbalester', 'Energo Machine Gunner': 'Energy Arbalester',
             'Nerd to grave': 'Restless Scholar'}
# in-hand art of the crude daggers (NPCs and items): same 1260x1540 sheet as the stock dagger, so it's swapped
CRUDE_SHEETS = re.compile(r'^assets/data/sprites/composite/(male|female)_mainhand_dyldo[a-z]*(_small)?\.png$')
TEXT = [('conversations/F6_sleeper.txt', 'Dildo', 'Oak Cudgel')]


# ---- file helpers (keep encoding, BOM and line endings) -------------------------------------------------
def load(path):
    raw = open(path, 'rb').read()
    if raw[:2] in (b'\xff\xfe', b'\xfe\xff'):
        return raw.decode('utf-16'), 'utf-16', False
    bom = raw.startswith(b'\xef\xbb\xbf')
    return raw.decode('utf-8-sig', errors='surrogateescape'), 'utf-8', bom


def save(path, text, enc, bom):
    if enc == 'utf-16':
        data = text.encode('utf-16')
    else:
        data = (b'\xef\xbb\xbf' if bom else b'') + text.encode('utf-8', errors='surrogateescape')
    open(path, 'wb').write(data)


def table(text):
    nl = '\r\n' if '\r\n' in text else '\n'
    return [l.split('\t') for l in text.split(nl)], nl


def untable(rows, nl):
    return nl.join('\t'.join(r) for r in rows)


def run(work, merged, base_apk=None):
    d = os.path.join(work, 'assets/data')
    stats = {}
    if base_apk:
        import zipfile
        z = zipfile.ZipFile(base_apk)
        n = 0
        for m in merged:
            g = CRUDE_SHEETS.match(m)
            if g:
                open(os.path.join(work, m), 'wb').write(
                    z.read('assets/data/sprites/composite/%s_mainhand_dagger.png' % g.group(1)))
                n += 1
        stats['weapon sheets'] = n
    # items.txt: names, icons, sprites, values; learn types and the family members
    p = os.path.join(d, 'rules/items.txt')
    t, enc, bom = load(p)
    rows, nl = table(t)
    h = rows[0]
    ci, cs, cv = h.index('icon'), h.index('sprite'), h.index('value')
    types, names, n = {}, {}, 0
    for r in rows[1:]:
        if len(r) <= cs:
            continue
        iid = r[0]
        types[iid] = r[2]
        new = r[1]
        if iid in RENAME and RENAME[iid][0]:
            new = RENAME[iid][0]
        else:
            for old, rep in FAMILY:
                if old in new:
                    new = new.replace(old, rep)
                    NO_DROP.add(iid)
                    break
        if iid in RENAME:
            _, _, icon, sprite = RENAME[iid]
            if icon:
                r[ci] = icon
            if sprite:
                r[cs] = sprite
        if CRUDE_SPRITE.match(r[cs] or ''):
            r[cs] = 'dagger'
        if iid in VALUE:
            r[cv] = VALUE[iid]
        if new != r[1]:
            names[iid] = new
            r[1] = new
            n += 1
    save(p, untable(rows, nl), enc, bom)
    stats['renamed'] = n
    # items_text.txt: every language's name/description
    p = os.path.join(d, 'rules/items_text.txt')
    t, enc, bom = load(p)
    rows, nl = table(t)
    h = rows[0]
    name_cols = [i for i, c in enumerate(h) if i > 0 and c.lower().startswith('name')]
    desc_cols = [i for i, c in enumerate(h) if c.lower().startswith('desc')]
    for r in rows[1:]:
        iid = r[0] if r else ''
        if iid in names:
            for i in name_cols:
                if i < len(r):
                    r[i] = names[iid]
        if iid in RENAME and RENAME[iid][1]:
            for i in desc_cols:
                if i < len(r):
                    r[i] = RENAME[iid][1]
    save(p, untable(rows, nl), enc, bom)
    # loot tables: replace the dropped items with a stock item of the same kind
    p = os.path.join(d, 'rules/loot.txt')
    t, enc, bom = load(p)
    rows, nl = table(t)
    n = 0
    for r in rows[1:]:
        if len(r) > 2 and r[1] in NO_DROP:
            rep = STOCK.get(types.get(r[1], ''), STOCK_OTHER)
            r[1] = rep
            r[2] = ''
            n += 1
    save(p, untable(rows, nl), enc, bom)
    stats['loot rows'] = n
    # map chests (items) and shops (shop_items)
    nc = ns = 0
    for p in glob.glob(os.path.join(d, 'tmx/*.tmx')):
        t, enc, bom = load(p)

        def fix(m):
            nonlocal nc, ns
            key, vals = m.group(1), m.group(2).split(',')
            if not any(v.strip() in NO_DROP for v in vals):
                return m.group(0)
            if key == 'shop_items':
                keep = [v for v in vals if v.strip() not in NO_DROP] or ['758']
                ns += 1
            else:
                keep = [STOCK.get(types.get(v.strip(), ''), STOCK_OTHER) if v.strip() in NO_DROP else v for v in vals]
                nc += 1
            return '<property name="%s" value="%s"' % (key, ','.join(keep))
        t2 = re.sub(r'<property name="(items|shop_items)" value="([^"]*)"', fix, t)
        if t2 != t:
            save(p, t2, enc, bom)
    stats['chests'] = nc
    stats['shops'] = ns
    # NPC display names (every language column)
    p = os.path.join(d, 'rules/bestiary_names.txt')
    t, enc, bom = load(p)
    rows, nl = table(t)
    n = 0
    for r in rows[1:]:
        if len(r) > 1 and r[1] in NPC_NAMES:
            new = NPC_NAMES[r[1]]
            for i in range(1, len(r)):
                if r[i].strip():
                    r[i] = new
            n += 1
    save(p, untable(rows, nl), enc, bom)
    stats['npc names'] = n
    for rel, old, new in TEXT:
        p = os.path.join(d, rel)
        if os.path.exists(p):
            t, enc, bom = load(p)
            if old in t:
                save(p, t.replace(old, new), enc, bom)
    return stats
