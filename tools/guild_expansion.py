#!/usr/bin/env python3
"""Guild expansion: ranks, contracts, main quest lines, Guild Master, members' stock (deobf/GUILD_EXPANSION_SPEC.md).

usage: guild_expansion.py <base.apk> <workdir>
Data only (the perks are EkGuild.java). Files not yet in <workdir> are taken from the base APK; every edited
path is appended to <workdir>/mp_merged.txt so the zip step picks it up. Run after hero_guilds.py.
"""
import os
import re
import sys
import zipfile

BASE, WORK = sys.argv[1], sys.argv[2]
LANGS = ['', 'CZ/', 'DE/', 'FR/', 'IT/', 'PL/', 'PT/', 'RU/', 'TR/']
z = zipfile.ZipFile(BASE)
NAMES = set(z.namelist())
LISTED_PATH = os.path.join(WORK, 'mp_merged.txt')
LISTED = set(open(LISTED_PATH).read().split()) if os.path.exists(LISTED_PATH) else set()
ADDED = []
STATS = dict(files=0, rows=0, oaths=0, quests=0, shops=0)

ORDER = ['warriors', 'seventh', 'wizards', 'three', 'loreseekers', 'golden']   # ekg_gm numbering 1..6
# guild_loreseekers / guild_golden are EK's own (Condition.PlayerHasGuild checks them), never given a join path in 4.2.2
MEMBER = {g: 'guild_' + g for g in ORDER}
R_NEED = [2, 4, 7, 10, 14]                                # contracts before chapter k (1..5)
L_NEED = [8, 12, 15, 18, 21]                              # level before chapter k
GOLD = [1000, 2000, 3500, 5000, 10000]
XP = [500, 2000, 4500, 8000, 12500]

GUILDS = {
    'warriors': dict(
        name="Warriors' Guild", leader_name='Sergeant Daukar', leader='NG_warriors_daukar', menu=60, menu_goto=63, lname='Sergeant',
        titles=['Recruit', 'Soldier', 'Veteran', 'Champion', 'Warmaster', 'Guild Master'],
        stock_npc=('NG_warriors', 'NG_warriors_daukar'),
        stock='115,116,117,118,119,534,537,539,185,191,4021',
        perks=['+3 HP', '+3 HP, +1 armor', '+3 HP', '+3 HP, +1 armor, +1 Strength', '+3 HP', '+3 HP, +1 armor, +1 Endurance (Guild Master)'],
        contracts=[(1001, 3, 'Minotaur Horns'), (1011, 2, 'Troll Hides'), (1015, 3, 'Chitin Carapaces')],
        contract_intro="The King pays us to keep the roads clear of beasts, and the King wants proof. Bring me trophies and the guild pays you in coin: [BLUE]3 Minotaur Horns[], [BLUE]2 Troll Hides[] or [BLUE]3 Chitin Carapaces[]. As many times as you like.",
        quest='The Iron Oath',
        rewards=[3024, 3032, 3505, 3012, 7005],
        gm_text="The seat of the Guild Master is yours by right of steel. The guild is yours to lead.",
        chapters=[
            dict(visit=('pre', 'FT_warriors_toel'), kill='kakrak',
                 brief="Every recruit carries the Sergeant's dispatch to our Freetown guildhouse once. Take this to [BLUE]Toel[] in Freetown; he has a job that will prove your arm.",
                 visit_text="[BLUE](Toel reads the Sergeant's dispatch and grunts)[] So Daukar sends me a fresh one. Good. A batrax warlord called [BLUE]Kakrak[] squats in the [BLUE]Imperial Fortress[] on the Steel Coast and bleeds our caravans. Kill it, then tell Daukar the road is open.",
                 j0="Sergeant Daukar gave me her dispatch for Toel, at the Warriors' Guildhouse in Freetown.",
                 j3="Toel wants the batrax warlord Kakrak dead. He lairs in the Imperial Fortress on the Steel Coast. Then I must report to Daukar.",
                 remind="Toel is waiting in Freetown, and after him, Kakrak. Go.",
                 report="Toel's runner already told me: Kakrak is dead and the caravans roll again. You are a Soldier of this guild now. Wear it well."),
            dict(kill='king_gurguth',
                 brief="The trolls of the [BLUE]Trollfens[] have a king again. [BLUE]Gurguth[] holds a cave there and his brood raids the farms north of the fens. A Veteran is someone who has faced a troll king and walked back. Go and be one.",
                 j0="Daukar wants King Gurguth, lord of the trolls, dead. His cave is in the Trollfens.",
                 remind="Gurguth still breathes. The Trollfens, soldier.",
                 report="Troll king's gone and the fens are quiet. You've earned your scars: Veteran."),
            dict(kill='D13_giant',
                 brief="Hill giants have come down from [BLUE]Sanctuary Peak[] under a new chief. The Ilmaran lords begged the guild for a Champion. Kill the giant chief and I will call you one.",
                 j0="The giants of Sanctuary Peak follow a new chief. Daukar wants him dead.",
                 remind="The giant chief at Sanctuary Peak. The Ilmarans are still waiting.",
                 report="The Ilmaran lords have sent their thanks, and gold. From today you are a Champion of the Warriors' Guild."),
            dict(visit=('post', 'NI_warriors_morg'), kill='mino_underking',
                 brief="The minotaur raids all lead back to one horned tyrant: the [BLUE]Underking[] of the [BLUE]Sunken Citadel[], in the Ashen Wastes. Break him. Then report to [BLUE]Morg[] at our Nivarian hall; the guild's warmasters sit there.",
                 visit_text="[BLUE](Morg looks you over for a long moment)[] The Underking dead, by a single blade. Daukar chose well. Go back and tell her the warmasters agree.",
                 j0="The Minotaur Underking rules the Sunken Citadel in the Ashen Wastes. I must kill him and report to Morg in Nivarian.",
                 j3="Morg and the warmasters agree. Daukar is waiting for me in New Garand.",
                 remind="The Underking first, then Morg in Nivarian.",
                 report="The warmasters have spoken. You are a Warmaster of the guild, and there is only one step left above you."),
            dict(kill='H4_dragon_elder',
                 brief="Our founders swore that whoever slew an elder dragon would lead the guild. None has managed it in a hundred years. [BLUE]Basrudaxul[] sleeps in the [BLUE]Bappasalar Cave[] of the Southern Dragon Mountains. Do it, and the seat is yours. But hear me: a Guild Master serves one guild alone. Every other oath you have sworn will be released.",
                 j0="To become Guild Master I must slay Basrudaxul, the elder red dragon of Bappasalar Cave. Leading the guild will release me from every other guild.",
                 remind="Basrudaxul still sleeps in Bappasalar Cave.",
                 report="Basrudaxul is dead. By the founders' oath I stand aside: Guild Master, the Warriors' Guild is yours to lead."),
        ]),
    'seventh': dict(
        name='Seventh House', leader_name='Sister Kardagis', leader='NG_sewers_kardagis', menu=60, lname='Sister',
        titles=['Associate', 'Operative', 'Shadow', 'Master Thief', 'Hand of the House', 'Master of the House'],
        stock_npc=('NG_sewers', 'NG_sewers_torja'),
        stock='146,147,406,407,409,410,611,730,5006',
        perks=['+2 HP, +2 devices, +1 detection'] * 3 + ['+2 HP, +2 devices, +1 detection, +1 Agility'] * 2
              + ['+2 HP, +2 devices, +1 detection, +1 Awareness (Master of the House)'],
        contracts=[(1008, 4, 'Poison Sacs'), (2012, 2, 'Emeralds'), (1021, 1, 'Gold Ingot')],
        contract_intro="The House always has buyers. Bring me [BLUE]4 Poison Sacs[] for our alchemists, [BLUE]2 Emeralds[] or a [BLUE]Gold Ingot[] for our fences, and I'll pay you better than any merchant. No questions asked.",
        quest='The Long Game',
        rewards=[3039, 3022, 3504, 7001, 7004],
        gm_text="Every coin in the sewers flows to you now. Try not to spend it all on one job.",
        chapters=[
            dict(visit=('pre', 'FT_seventh_arkados'), kill='surtag',
                 brief="Freetown's chapter needs a new face. Go and see [BLUE]Arkados[] in the Freetown sewers and do what he asks. He'll tell me if you're worth a second job.",
                 visit_text="Kardagis sent you? Fine. A crew in the [BLUE]Jabal Grotto[] is cutting into our trade, and their muscle is a brute called [BLUE]Surtag[]. Remove him and the House will remember your name.",
                 j0="Kardagis sent me to Arkados, in the Seventh House hideout under Freetown.",
                 j3="Arkados wants Surtag, the rival crew's bodyguard, removed. He hides in the Jabal Grotto, South Jabal Hills. Then back to Kardagis.",
                 remind="Arkados first. Then Surtag.",
                 report="Surtag is feeding the crabs and Arkados is happy. Welcome, Operative."),
            dict(kill='xidar',
                 brief="Without their muscle, the Jabal crew's boss [BLUE]Xidar[] is exposed. End him and the grotto's trade is ours.",
                 j0="Xidar, boss of the Jabal Grotto crew, must go. The grotto's trade will then belong to the House.",
                 remind="Xidar still runs the Jabal Grotto.",
                 report="The grotto answers to the House now. You move like a Shadow; from now on, that's your name."),
            dict(kill='H6_director',
                 brief="The big job. The [BLUE]Golden Cove Bank[] director keeps the ledgers that hang half our people. Get in, get him, and let the ledgers burn. Master Thieves are made on jobs like this.",
                 j0="The director of the Golden Cove Bank must be dealt with, so the House's ledgers are gone for good.",
                 remind="The Golden Cove Bank. The director. Don't make me say it twice.",
                 report="The ledgers are ash and every member owes you a drink. Master Thief: you earned it."),
            dict(visit=('post', 'NG_sewers_torja'), kill='I9_castle_executioner',
                 brief="Storme's [BLUE]executioner[] hanged three of ours last winter. The House does not forget. Visit [BLUE]Castle Storme[], then tell [BLUE]Torja[] here in the sewers it is done; she keeps the tally of our debts.",
                 visit_text="[BLUE](Torja strikes three names from a greasy ledger)[] Paid in full. Tell Kardagis the House's debt is settled.",
                 j0="The executioner of Castle Storme hanged three members of the House. Kardagis wants the debt paid, then reported to Torja.",
                 j3="Torja has settled the tally. Kardagis is waiting.",
                 remind="Castle Storme's executioner, then Torja.",
                 report="The debt is paid. You are the Hand of the House; only the seat remains."),
            dict(items=(1021, 3), gold=15000,
                 brief="The Master of the House isn't chosen for the blade but for the purse. Bring the House [BLUE]3 Gold Ingots[] and [BLUE]15000 gold[] as tribute and the seat is yours. And mind: the Master serves only the House. Every other oath is released the day you sit.",
                 j0="To become Master of the House I must bring 3 Gold Ingots and 15000 gold as tribute. Leading the House will release me from every other guild.",
                 remind="3 Gold Ingots and 15000 gold. The seat waits.",
                 report="The tribute is counted and the House bows. Master of the House, the sewers are yours."),
        ]),
    'wizards': dict(
        name="Wizard's Guild", leader_name='High Mage Ilemma', leader='IM_ilemma', menu=60, lname='High Mage',
        titles=['Apprentice', 'Adept', 'Magus', 'Master Magus', 'Archmagus', 'Grand Magus'],
        stock_npc=('IM', 'IM_ilemma'),
        stock='381,382,387,388,392,325,341,3036,3038,460,4018',
        perks=['+4 mana'] * 3 + ['+4 mana, +1 Intellect'] * 2 + ['+4 mana, +1 Intellect, +1 Awareness (Grand Magus)'],
        contracts=[(1005, 3, 'Fire Salts'), (1006, 3, 'Living Ice'), (1007, 3, 'Sparkling Powder')],
        contract_intro="Our laboratories consume reagents faster than the enclaves can gather them. The Guild buys [BLUE]3 Fire Salts[], [BLUE]3 Living Ice[] or [BLUE]3 Sparkling Powder[] from its members, at a fair price.",
        quest='Echoes of the Council',
        rewards=[3021, 3013, 4500, 3033, 4503],
        gm_text="The Council's chair is yours, Grand Magus. May your research be long and your enemies brief.",
        chapters=[
            dict(visit=('pre', 'D9_tower_aglaron'), items=(1007, 3),
                 brief="An Adept must know our enclaves. Travel to the Iron Valley enclave and present yourself to [BLUE]Aglaron[]; he tests every apprentice. Bring back [BLUE]3 Sparkling Powder[] for his wards, and his seal of approval.",
                 visit_text="[BLUE](Aglaron traces a sigil in the air; it glows faintly)[] Your attunement is adequate. Tell Ilemma I approve, and bring her the powder for my wards.",
                 j0="High Mage Ilemma sent me to Aglaron at the Iron Valley enclave. I also need 3 Sparkling Powder.",
                 j3="Aglaron approved me. I must bring 3 Sparkling Powder to Ilemma in Icemist.",
                 remind="Aglaron, then the powder.",
                 report="Aglaron's seal, and the powder. Welcome among the Adepts."),
            dict(kill='D9_crypt_necro',
                 brief="A renegade who once studied with us practises necromancy in the [BLUE]Mercian Royal Crypt[], in the Iron Valley. The Guild cleans its own messes. End it.",
                 j0="A renegade guild necromancer hides in the Mercian Royal Crypt, Iron Valley. Ilemma wants the matter closed.",
                 remind="The Royal Crypt. Our renegade still works there.",
                 report="The renegade is dead and the Guild's name is clean. You are a Magus now."),
            dict(visit=('post', 'E11_tower_arabelle'), kill='F9_mausoleum_lich',
                 brief="The lich of the [BLUE]Mausoleum[] in the Deadwood was a Council member, centuries ago. Destroy it, then report to [BLUE]Arabelle[] at the Solliga enclave. She keeps the Council's records.",
                 visit_text="[BLUE](Arabelle closes a heavy tome)[] So the old councillor is finally at rest. I have struck the name from our records. Tell Ilemma the Council approves.",
                 j0="The lich of the Deadwood Mausoleum was once a Council wizard. Ilemma wants it destroyed and reported to Arabelle, at the Solliga enclave.",
                 j3="Arabelle recorded the lich's end. Ilemma is waiting in Icemist.",
                 remind="The Mausoleum lich first, then Arabelle.",
                 report="The Council's records are corrected. Rise, Master Magus."),
            dict(kill='IM_boss_lich1',
                 brief="Something foul grows in the [BLUE]Sewer of Horrors[] beneath our own city. A lich, fed by the Guild's discarded experiments. An Archmagus would not tolerate it.",
                 j0="A lich lurks in the Sewer of Horrors beneath Icemist, fed by the Guild's discarded experiments.",
                 remind="Beneath Icemist, the Sewer of Horrors. The lich.",
                 report="Icemist sleeps easier. You are an Archmagus of the Guild."),
            dict(kill='IM_lord_flame',
                 brief="The last trial. The [BLUE]Flame Lord[] haunts the [BLUE]Icemist Underlevels[]; every Grand Magus before me once sealed him away. You will end him. Know this: the Grand Magus serves the Guild alone, and every other oath will be released.",
                 j0="To become Grand Magus I must destroy the Flame Lord in the Icemist Underlevels. Leading the Guild will release me from every other guild.",
                 remind="The Flame Lord waits below Icemist.",
                 report="The Flame Lord is gone, and the Council has voted. Grand Magus, the Guild is yours."),
        ]),
    'three': dict(
        name='Church of the Three', leader_name='Archbishop Dilla', leader='NI_hall_archbishop', menu=2, lname='Your Grace',
        member_greet='[BLUE](The Archbishop looks up from her prayer and smiles)[] Welcome home, child of the Three.',
        titles=['Acolyte', 'Deacon', 'Priest', 'Templar', 'Exemplar', 'Hierophant'],
        stock_npc=('NI_hall', 'NI_hall_archbishop'),
        stock='131,145,161,221,193,187,617,618,5020,5010',
        perks=['+2 HP, +2 mana'] * 3 + ['+2 HP, +2 mana, +1 Personality'] * 2
              + ['+2 HP, +2 mana, +1 Personality, +1 Endurance (Hierophant)'],
        contracts=[(1009, 10, 'Zombie Flesh'), (1010, 6, 'Skulls'), (1013, 1, 'Demonic Skull')],
        contract_intro="The dead do not rest by themselves. Bring the remains of the risen for purification: [BLUE]10 Zombie Flesh[], [BLUE]6 Skulls[] or a [BLUE]Demonic Skull[]. The Church rewards the faithful.",
        quest='The Long Vigil',
        rewards=[3015, 3026, 463, 3028, 3030],
        gm_text="The Three have chosen you, Hierophant. Lead the faithful well.",
        chapters=[
            dict(items=(1010, 6),
                 brief="Every Deacon begins by tending the dead. Our ossuary lost its relics to grave-robbers; bring [BLUE]6 Skulls[] recovered from the risen, so they may be blessed and laid to rest.",
                 j0="The Archbishop needs 6 Skulls from the risen dead for the ossuary of the Nivarian hall.",
                 remind="Six skulls, for the ossuary.",
                 report="They shall rest now. The Three see your devotion, Deacon."),
            dict(kill='lich_G7',
                 brief="A lich has stirred in the [BLUE]Irazur Tomb[], in Great Inori. Its servants spill into the villages. Carry the light of the Three there.",
                 j0="A lich has awakened in the Irazur Tomb, Great Inori. The Archbishop wants it destroyed.",
                 remind="The Irazur Tomb. The lich still stirs.",
                 report="The tomb is silent. You have been ordained a Priest of the Three."),
            dict(kill='lich_H7',
                 brief="Another lich, in the [BLUE]Forgotten Temple[] of Eastern Inori. It defiles an old shrine of Thelume. A Templar is the Church's sword; be ours.",
                 j0="A lich defiles the Forgotten Temple in Eastern Inori, once a shrine of Thelume.",
                 remind="The Forgotten Temple awaits its cleansing.",
                 report="Thelume's shrine is clean again. Rise, Templar."),
            dict(kill='D11_greater_demon',
                 brief="Beneath the Fögas Forest lies the [BLUE]Hellish Cave[], and in it a greater demon that the abbey's fall set loose. An Exemplar faces what others dare not.",
                 j0="A greater demon dwells in the Hellish Cave, beneath the Fögas Forest.",
                 remind="The Hellish Cave in Fögas Forest.",
                 report="The demon is banished. The faithful will speak your name, Exemplar."),
            dict(kill='C13_lord',
                 brief="The last vigil. In the [BLUE]Forbidden Pit[] of Mount Orogg waits the [BLUE]Void Lord[], darkness the Three themselves once bound. Only a Hierophant may face it, and a Hierophant serves the Church alone: every other oath will be released.",
                 j0="To become Hierophant I must destroy the Void Lord in the Forbidden Pit of Mount Orogg. Leading the Church will release me from every other guild.",
                 remind="The Void Lord, in the Forbidden Pit.",
                 report="The Void is sealed. Kneel, and rise Hierophant of the Church of the Three."),
        ]),
    'loreseekers': dict(
        name='Loreseekers', leader_name='Master Librarian Rurazar', leader='NG_library_librarian', menu=5, lname='Master',
        member_greet='[BLUE](The Master Librarian looks up from his reading, and for once does not frown)[] Ah. A fellow seeker.',
        join=dict(node=5, rep='REP_loreseekers', rep_min=10, bond=1500, ask='I wish to join the Loreseekers.',
                  explain="The Loreseekers serve the King by recovering what the Empire knew and the Exile lost. We take few: those our order already counts as friends [BLUE](Friendly reputation with the Loreseekers)[], or patrons whose gold keeps these halls lit [BLUE](a bond of 1500 gold)[]. A member swears the [BLUE]Loyalty Oath[] like any guild's. It binds for life, and you won't be able to join any other guild.",
                  welcome="Then welcome, Initiate. Knowledge is a debt we owe the dead; now you owe it too. Come to me when you want work."),
        titles=['Initiate', 'Scribe', 'Archivist', 'Loreseeker', 'Loremaster', 'Master Librarian'],
        stock_npc=('NG_loreseekers', 'NG_library_librarian'),
        stock='6001,6002,6003,6004,6010,6011,5013,5020,5021,5025',
        perks=['+1 HP, +2 mana, +1 detection'] * 3 + ['+1 HP, +2 mana, +1 detection, +1 Intellect'] * 2
              + ['+1 HP, +2 mana, +1 detection, +1 Intellect, +1 Awareness (Master Librarian)'],
        contracts=[(1022, 2, "Muud'ari Energy Cells"), (1018, 1, 'Vorator Egg'), (1025, 1, 'Demonic Wolf Skull')],
        contract_intro="Our scholars study what the rest of the Kingdoms kill and throw away. The order pays for [BLUE]2 Muud'ari Energy Cells[] from the old ruins, a [BLUE]Vorator Egg[] or a [BLUE]Demonic Wolf Skull[]. Bring them intact.",
        quest='The Last Codex',
        rewards=[3014, 3023, 3027, 4502, 3018],
        gm_text="The Great Library is yours, Master Librarian. Try to leave it better catalogued than I did.",
        chapters=[]),
    'golden': dict(
        name='Golden Hand', leader_name='the Guardian of the Grey Library', leader='FT_library_guardian', menu=2, lname='Guardian',
        member_greet='[BLUE](The Guardian inclines his head)[] Partner. The Hand opens for you.',
        greet_before='VariableEqual#FT_access_library,1',
        join=dict(node=2, rep='REP_goldenhand', rep_min=3, bond=2500, ask='I want to become a partner of the Golden Hand.',
                  explain="The Golden Hand is the greatest trading guild in the Kingdoms, and a partnership in it is worth more than a title. We admit those our records show have served our interests [BLUE](reputation 3 or more with the Golden Hand)[], or who buy in with a partner's bond [BLUE](2500 gold)[]. Partners swear the [BLUE]Loyalty Oath[] like any guild. It binds for life, and you won't be able to join any other guild.",
                  welcome="Signed, sealed and entered in the ledger. Welcome, Clerk. The left hall is open to you now, and so is our business.",
                  extra='SetVariable#FT_access_library,1'),
        titles=['Clerk', 'Factor', 'Broker', 'Consul', 'Magnate', 'Master of the Hand'],
        stock_npc=('FT_library', 'FT_library_guardian'),
        stock='2001,2002,2003,2004,3002,3009,3010,3011,5001,5003,5016',
        perks=['+2 HP, +1 armor'] * 3 + ['+2 HP, +1 armor, +1 Personality'] * 2
              + ['+2 HP, +1 armor, +1 Personality, +1 Agility (Master of the Hand)'],
        contracts=[(2015, 1, 'Pearl'), (2013, 1, 'Ruby'), (2014, 1, 'Sapphire')],
        contract_intro="The Hand's buyers in Freetown pay above any merchant for fine stones. Bring a [BLUE]Pearl[], a [BLUE]Ruby[] or a [BLUE]Sapphire[] and the Hand buys it at a partner's premium.",
        quest='The Gilded Oath',
        rewards=[3029, 3019, 3020, 7010, 7002],
        gm_text="Every ledger of the Golden Hand closes at your desk now, Master of the Hand.",
        chapters=[]),
}


# ------------------------------------------------------------------------------------------------ v75 stories
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from guild_story import Story                      # noqa: E402
from guild_story_data import STORIES               # noqa: E402
STORY = {}
for _g, _d in STORIES.items():
    _d.setdefault('quest', GUILDS[_g]['quest'])
    STORY[_g] = Story(_g, _d, None)


def reward_actions(g, r, master=False):
    G = GUILDS[g]
    n = ORDER.index(g) + 1
    act = ['SetVariable#ekg_rank_%s,%d' % (g, r), 'SetVariable#ekg_gq_%s,%d' % (g, 60 if r == 5 else 10 + 10 * r),
           'GainItem#%d' % G['rewards'][r - 1], 'GainGold#%d' % GOLD[r - 1], 'GainXP#%d' % XP[r - 1]]
    if master:
        act.append('SetVariable#ekg_gm,%d' % n)
        act += ['SetVariable#%s,0' % MEMBER[o] for o in ORDER if o != g]
    return act


# ------------------------------------------------------------------------------------------------ helpers
def src_bytes(n):
    p = os.path.join(WORK, n)
    if os.path.exists(p):
        return open(p, 'rb').read()
    if n in NAMES:
        return z.read(n)
    return None


def write(n, data):
    p = os.path.join(WORK, n)
    os.makedirs(os.path.dirname(p), exist_ok=True)
    open(p, 'wb').write(data)
    STATS['files'] += 1
    if n not in LISTED:
        LISTED.add(n)
        ADDED.append(n)


def load_tsv(n):
    raw = src_bytes(n)
    if raw is None or raw[:2] in (b'\xff\xfe', b'\xfe\xff'):
        return None
    bom = raw.startswith(b'\xef\xbb\xbf')
    text = raw[3 if bom else 0:].decode('utf-8', errors='surrogateescape')
    nl = '\r\n' if '\r\n' in text else '\n'
    return text.split(nl), nl, bom


def save_tsv(n, lines, nl, bom):
    write(n, (b'\xef\xbb\xbf' if bom else b'') + nl.join(lines).encode('utf-8', errors='surrogateescape'))


def cell(s):
    s = str(s)
    return '"%s"' % s if ';' in s else s


def row(head, **kw):
    kw.setdefault('text_ES', kw.get('text', ''))
    return '\t'.join(cell(kw.get(h.strip(), '')) for h in head)


def R(index, typ, text, goto, cond='', act=''):
    return {'index': index, 'type': typ, 'text': text, 'Go To': goto, 'conditions': cond, 'actions': act}


def conv_files(base):
    for lang in LANGS:
        n = 'assets/data/conversations/%s%s.txt' % (lang, base)
        got = load_tsv(n)
        if got and 'conditions' in got[0][0].split('\t'):
            yield n, got


def insert_rows(lines, head, rows, where):
    """where: ('top_q', idx) before the first Q row of idx; ('after_a', idx) after the last A row of idx;
    ('first_a', idx) before its first A row (EK shows only the first 4 matching answers); 'end'."""
    out = [row(head, **r) for r in rows]
    ii = head.index('index')
    ti = head.index('type')
    pos = len(lines)
    while pos > 1 and lines[pos - 1].strip() == '':
        pos -= 1
    if where != 'end':
        kind, idx = where
        hits = [i for i, l in enumerate(lines[1:], 1)
                if len(l.split('\t')) > ti and l.split('\t')[ii].strip() == str(idx)
                and l.split('\t')[ti].strip() == ('Q' if kind == 'top_q' else 'A')]
        assert hits, 'anchor %s %s not found' % (kind, idx)
        pos = hits[0] if kind in ('top_q', 'first_a') else hits[-1] + 1
    lines[pos:pos] = out
    STATS['rows'] += len(out)


def insert_greet(lines, head, greet, member_var, before=None):
    """Just before the leader's own member greeting (else before the default node-1 line): keeps the MP
    mod's follower/companion lines, which sit above it, working."""
    ii, ti, ci = head.index('index'), head.index('type'), head.index('conditions')
    q1 = [i for i, l in enumerate(lines[1:], 1) if len(l.split('\t')) > ci and l.split('\t')[ii].strip() == '1'
          and l.split('\t')[ti].strip() == 'Q']
    assert q1, 'no node 1'
    mem = [i for i in q1 if member_var in lines[i].split('\t')[ci]]
    dflt = [i for i in q1 if lines[i].split('\t')[ci].strip() == '']
    first = [i for i in q1 if before and before in lines[i].split('\t')[ci]]
    pos = (first or mem or dflt or q1)[0]
    lines[pos:pos] = [row(head, **greet)]
    STATS['rows'] += 1


# ------------------------------------------------------------------------------------------------ leader rows
def leader_rows(g, G):
    n = ORDER.index(g) + 1
    mq, rk, rep, gq, mem = 'ekg_mq_' + g, 'ekg_rank_' + g, 'ekg_rep_' + g, 'ekg_gq_' + g, 'ekg_mem_' + g
    T = G['titles']
    rows = []
    for r in range(6):
        rows.append(R(700, 'Q', "What guild business do you bring, %s?" % T[r], 701, 'VariableEqual#%s,%d' % (rk, r)))
    rows += [R(701, 'A', 'Are there any contracts?', 710),
             R(701, 'A', 'About my advancement in the guild...', 720),
             R(701, 'A', "I'd like to see the members' stock.", 740, 'VariableGreater#%s,0' % rk),
             R(701, 'A', 'Nothing for now.', 0)]
    rows.append(R(710, 'Q', G['contract_intro'], 711))
    for item, cnt, label in G['contracts']:
        val = ITEM_VALUE.get(item, 200) * cnt
        rows.append(R(711, 'A', "I have %d %s for you. [BLUE](+%d gold)[]" % (cnt, label, int(val * 1.3)), 712,
                      'PlayerHasItems#%d,%d' % (item, cnt),
                      'LoseItems#%d,%d;GainGold#%d;GainXP#%d;IncVariable#%s,1' % (item, cnt, int(val * 1.3), max(50, val // 5), rep)))
    rows.append(R(711, 'A', 'Back.', 700))
    rows.append(R(712, 'Q', 'Good work. Your pay, as agreed, and the guild will remember it.', 711))
    if g == 'seventh':
        rows.append(R(740, 'Q', "[BLUE]Torja[] keeps the House's stock, over by the beds. Tell her I sent you.", 701))
    else:
        rows.append(R(740, 'Q', 'Take a look. Members pay less, of course.', 0, '', 'OpenShop#'))
    # --- advancement (first matching Q row wins)
    if g in STORY:
        rows += STORY[g].hub(R, G, rk, rep, T)
        for r in range(5):
            rows.append(R(720, 'Q', "You are a %s of the %s." % (T[r], G['name']), 701, 'VariableEqual#%s,%d' % (rk, r)))
        return leader_tail(g, G, rows)
    rows.append(R(720, 'Q', G['gm_text'], 701, 'VariableEqual#%s,5' % rk))
    for k, ch in enumerate(G['chapters'], 1):
        ready = ready_cond(g, k, ch)
        act = []
        if 'items' in ch:
            act.append('LoseItems#%d,%d' % ch['items'])
        if 'gold' in ch:
            act.append('LoseGold#%d' % ch['gold'])
        act += ['SetVariable#%s,%d' % (rk, k), 'SetVariable#%s,%d' % (mq, 10 * k + 6 if k < 5 else 100),
                'SetVariable#%s,%d' % (gq, 10 + 10 * k), 'GainItem#%d' % G['rewards'][k - 1],
                'GainGold#%d' % GOLD[k - 1], 'GainXP#%d' % XP[k - 1]]
        if k == 5:
            act.append('SetVariable#ekg_gm,%d' % n)
            act += ['SetVariable#%s,0' % MEMBER[o] for o in ORDER if o != g]
            rows.append(R(720, 'Q', "You have done all I asked... but you already lead another guild. A Guild Master serves one guild alone; the seat cannot be yours.",
                          701, ready + ';VariableGreater#ekg_gm,0'))
            ready += ';VariableLower#ekg_gm,1'
        rows.append(R(720, 'Q', ch['report'] + ' [BLUE](You are now: %s)[]' % T[k], 701, ready, ';'.join(act)))
    for k, ch in enumerate(G['chapters'], 1):
        rows.append(R(720, 'Q', ch['remind'], 701, 'VariableGreater#%s,%d;VariableLower#%s,%d' % (mq, 10 * k - 1, mq, 10 * k + 6)))
    for k, ch in enumerate(G['chapters'], 1):
        cond = 'VariableEqual#%s,%d;VariableLower#%s,%d;VariableGreater#%s,%d;PlayerIsLevel#%d' % (
            rk, k - 1, mq, 10 * k, rep, R_NEED[k - 1] - 1, L_NEED[k - 1])
        if k == 5:
            rows.append(R(720, 'Q', "You have climbed as high as any member can while you lead another guild. Only one seat may be yours.",
                          701, 'VariableEqual#%s,4;VariableGreater#ekg_gm,0' % rk))
            cond += ';VariableLower#ekg_gm,1'
        rows.append(R(720, 'Q', ch['brief'], 730 + k, cond))
        rows.append(R(730 + k, 'A', "I'll do it.", 0, '', 'SetVariable#%s,%d' % (mq, 10 * k)))
        rows.append(R(730 + k, 'A', 'Not yet.', 700))
    for r in range(5):
        rows.append(R(720, 'Q', "You are a %s of the %s. When you have fulfilled at least [BLUE]%d guild contracts[] and reached [BLUE]level %d[], come to me: I will have work worthy of a %s."
                      % (T[r], G['name'], R_NEED[r], L_NEED[r], T[r + 1]), 701, 'VariableEqual#%s,%d' % (rk, r)))
    return leader_tail(g, G, rows)


def leader_tail(g, G, rows):
    T = G['titles']
    mem, gq = 'ekg_mem_' + g, 'ekg_gq_' + g
    # first talk as a member: journal + perks start
    greet = R(1, 'Q', "Welcome, %s. As a sworn member of the %s you may now take on guild business: contracts, promotions and the members' stock. [BLUE](New: Guild business)[]"
              % (T[0], G['name']), G['menu'], 'VariableEqual#%s,1;VariableLower#%s,1' % (MEMBER[g], mem),
              'SetVariable#%s,1;SetVariable#%s,10' % (mem, gq))
    menu = R(G['menu'], 'A', '[BLUE](Guild business)[]', 700, 'VariableEqual#%s,1' % MEMBER[g])
    return greet, menu, rows


def join_rows(g, G):
    """New guilds (EK's own guild_loreseekers / guild_golden): the oath, gated like EK's four."""
    J, m = G['join'], MEMBER[g]
    oath = 'SetVariable#%s,1;IncVariable#%s,5' % (m, J['rep']) + (';' + J['extra'] if J.get('extra') else '')
    tag = ' [BLUE](Take the Oath and become a member of the %s)[] [RED](NOTE: This decision has no way back!)[]' % G['name']
    return [R(790, 'Q', 'You already lead another guild. A Guild Master serves one guild alone; I cannot take your oath.', 0,
              'VariableGreater#ekg_gm,0'),
            R(790, 'Q', 'I am afraid you are already loyal to another guild. A Loyalty Oath can only be taken once, in a lifetime.', 0,
              'PlayerHasGuild#;PlayerIsntClass#warrior'),
            R(790, 'Q', J['explain'], 791),
            R(791, 'A', 'Very well.' + tag, 792, 'VariableGreater#%s,%d;VariableLower#ekg_gm,1' % (J['rep'], J['rep_min'] - 1), oath),
            R(791, 'A', '[BLUE](Pay the %d gold bond)[]' % J['bond'] + tag, 792,
              'VariableLower#%s,%d;PlayerHasGold#%d;VariableLower#ekg_gm,1' % (J['rep'], J['rep_min'], J['bond']),
              'LoseGold#%d;' % J['bond'] + oath),
            R(791, 'A', "I'll have to think about it.", 0),
            R(792, 'Q', J['welcome'], 0)]


def ready_cond(g, k, ch):
    mq = 'ekg_mq_' + g
    visit = ch.get('visit')
    state = 10 * k + 3 if visit else 10 * k
    cond = ['VariableEqual#%s,%d' % (mq, state)]
    if visit and visit[0] == 'post':
        return cond[0]
    if 'kill' in ch:
        cond.append('NPCIsDead#%s' % ch['kill'])
    if 'items' in ch:
        cond.append('PlayerHasItems#%d,%d' % ch['items'])
    if 'gold' in ch:
        cond.append('PlayerHasGold#%d' % ch['gold'])
    return ';'.join(cond)


# ------------------------------------------------------------------------------------------------ item values
ITEM_VALUE = {}
for line in z.read('assets/data/rules/items.txt').decode('utf-8-sig', errors='replace').replace('\r\n', '\n').split('\n')[1:]:
    r = line.split('\t')
    if len(r) > 8 and r[0].strip().isdigit():
        try:
            ITEM_VALUE[int(r[0])] = int(r[8])
        except ValueError:
            pass
for G in GUILDS.values():
    for it in G['rewards'] + [c[0] for c in G['contracts']] + [int(x) for x in G['stock'].split(',')]:
        assert it in ITEM_VALUE, 'unknown item %d' % it

# ------------------------------------------------------------------------------------------------ conversations
for g, G in GUILDS.items():
    greet, menu, rows = leader_rows(g, G)
    for n, (lines, nl, bom) in conv_files(G['leader']):
        head = [h.strip() for h in lines[0].lstrip('﻿').split('\t')]
        ii = head.index('index')
        assert not any(l.split('\t')[ii].strip().isdigit() and 698 <= int(l.split('\t')[ii]) < 800
                       for l in lines[1:] if l.strip()), n + ': node range 700-759 already used'
        insert_greet(lines, head, greet, MEMBER[g], G.get('greet_before'))
        if G.get('join'):
            insert_rows(lines, head, join_rows(g, G), 'end')
            insert_rows(lines, head, [R(G['join']['node'], 'A', G['join']['ask'], 790, 'VariableLower#%s,1' % MEMBER[g])],
                        ('first_a', G['join']['node']))
        if g in STORY:                      # v74 saves that already hold a rank continue the story from it
            for mr in STORY[g].migrate_rows(R, G, 'ekg_rank_' + g, G['menu'] if G.get('menu_goto') else 699, G['titles']):
                insert_greet(lines, head, mr, MEMBER[g])
        if G.get('menu_goto'):              # reuse the leader's own (dead-end) answer instead of adding one
            gi, ci = head.index('Go To'), head.index('conditions')
            hit = [i for i, l in enumerate(lines[1:], 1) if l.split('\t')[ii].strip() == str(G['menu'])
                   and l.split('\t')[head.index('type')].strip() == 'A' and l.split('\t')[gi].strip() == str(G['menu_goto'])]
            assert len(hit) == 1, n + ': contracts answer not found'
            r = lines[hit[0]].split('\t')
            r[gi] = '700'
            r[ci] = 'VariableEqual#%s,1' % MEMBER[g]
            lines[hit[0]] = '\t'.join(r)
        else:                               # two-step: members first choose guild business or the usual menu
            gi, ci, ti = head.index('Go To'), head.index('conditions'), head.index('type')
            own = [i for i, l in enumerate(lines[1:], 1) if len(l.split('\t')) > ci and l.split('\t')[ii].strip() == '1'
                   and l.split('\t')[ti].strip() == 'Q' and l.split('\t')[ci].strip() in (
                       'VariableEqual#%s,1' % MEMBER[g], '"VariableEqual#%s,1"' % MEMBER[g])]
            if own:
                r = lines[own[0]].split('\t')
                r[gi] = '699'
                lines[own[0]] = '\t'.join(r)
            wl = [i for i, l in enumerate(lines[1:], 1) if len(l.split('\t')) > ci and 'ekg_mem_%s,1' % g in l.split('\t')[ci]]
            if not own:                     # no member greeting of its own: add one right after our welcome row
                lines[wl[0] + 1:wl[0] + 1] = [row(head, **R(1, 'Q', G['member_greet'], 699, 'VariableEqual#%s,1' % MEMBER[g]))]
                STATS['rows'] += 1
            r = lines[wl[0]].split('\t')
            r[gi] = '699'
            lines[wl[0]] = '\t'.join(r)
            insert_rows(lines, head, [R(699, 'A', '[BLUE](Guild business)[]', 700),
                                      R(699, 'A', 'Something else...', 698),
                                      R(698, 'Q', 'Of course. What is it?', G['menu'])], 'end')
        insert_rows(lines, head, rows, 'end')
        save_tsv(n, lines, nl, bom)
    # remote visits
    for k, ch in enumerate([] if g in STORY else G['chapters'], 1):
        if 'visit' not in ch:
            continue
        mode, npc = ch['visit']
        mq = 'ekg_mq_' + g
        cond = 'VariableEqual#%s,%d' % (mq, 10 * k)
        if mode == 'post':
            cond += ';NPCIsDead#%s' % ch['kill']
        vr = R(1, 'Q', ch['visit_text'], 0, cond, 'SetVariable#%s,%d' % (mq, 10 * k + 3))
        done = 0
        for n, (lines, nl, bom) in conv_files(npc):
            head = [h.strip() for h in lines[0].lstrip('﻿').split('\t')]
            insert_rows(lines, head, [vr], ('top_q', 1))
            save_tsv(n, lines, nl, bom)
            done += 1
        assert done, 'visit npc %s missing' % npc
    # Seventh House stock at Torja
    if g == 'seventh':
        for n, (lines, nl, bom) in conv_files('NG_sewers_torja'):
            head = [h.strip() for h in lines[0].lstrip('﻿').split('\t')]
            insert_rows(lines, head, [R(2, 'A', "Kardagis sent me for the House's stock.", 750,
                                        'VariableEqual#guild_seventh,1;VariableGreater#ekg_rank_seventh,0')], ('after_a', 2))
            insert_rows(lines, head, [R(750, 'Q', 'Members only, and members pay less. Take a look.', 0, '', 'OpenShop#')], 'end')
            save_tsv(n, lines, nl, bom)

# ------------------------------------------------------------------------------------------------ story scenes
USED = {}


def used_nodes(base):
    if base not in USED:
        u = set()
        got = load_tsv('assets/data/conversations/%s.txt' % base)
        if got:
            head = [h.strip() for h in got[0][0].lstrip('\ufeff').split('\t')]
            ii = head.index('index')
            for l in got[0][1:]:
                v = l.split('\t')[ii].strip().split(',')[0] if l.strip() else ''
                if v.isdigit():
                    u.add(int(v))
        USED[base] = u
    return USED[base]


def alloc(base):
    u = used_nodes(base)
    n = 800
    while n in u:
        n += 1
    u.add(n)
    return n


NEW_CONV_HEAD = ['index', 'type', 'text', 'text_ES', 'Go To', 'conditions', 'actions']
for g, S in STORY.items():
    S.compile(R, alloc, GUILDS[g], lambda r, master=False, g=g: reward_actions(g, r, master))
    for base, idle in S.newfiles.items():
        rows = [x for kind, x in S.rows.get(base, [])]
        entry = [x for kind, x in S.rows.get(base, []) if kind == 'entry']
        body = [x for kind, x in S.rows.get(base, []) if kind == 'body']
        lines = ['\t'.join(NEW_CONV_HEAD)] + [row(NEW_CONV_HEAD, **x) for x in entry + idle + body]
        write('assets/data/conversations/%s.txt' % base, '\r\n'.join(lines).encode('utf-8'))
        STATS['rows'] += len(lines) - 1
    for base, rws in S.rows.items():
        if base in S.newfiles:
            continue
        done = 0
        for n, (lines, nl, bom) in conv_files(base):
            head = [h.strip() for h in lines[0].lstrip('\ufeff').split('\t')]
            insert_rows(lines, head, [x for kind, x in rws if kind == 'entry'], ('top_q', 1))
            insert_rows(lines, head, [x for kind, x in rws if kind == 'body'], 'end')
            save_tsv(n, lines, nl, bom)
            done += 1
        assert done, 'story npc %s has no conversation' % base

# new characters / ambushes on the maps
for g, S in STORY.items():
    for mp, objs in S.objects().items():
        n = 'assets/data/tmx/%s.tmx' % mp
        s = src_bytes(n).decode('utf-8')
        gid = re.search(r'<object\b[^>]*type="(?:spawn|staticNPC)"[^>]*gid="(\d+)"', s).group(1)
        ids = [int(x) for x in re.findall(r'<object id="(\d+)"', s)]
        nxt = max(ids) + 1 if ids else None
        m = re.search(r'<object\b[^>]*type="(?:spawn|staticNPC)"', s)
        end = s.index('</objectgroup>', m.start())
        ind = re.search(r'\n(\s*)<object\b[^>]*type="(?:spawn|staticNPC)"', s).group(1)
        xml = ''
        for typ, oid, (x, y), props in objs:
            idattr = ''
            if nxt is not None:
                idattr = ' id="%d"' % nxt
                nxt += 1
            xml += '%s<object%s name="%s" type="%s" gid="%s" x="%d" y="%d">\n%s <properties>\n' % (ind, idattr, oid, typ, gid, x, y, ind)
            for k2, v2 in sorted(props):
                xml += '%s  <property name="%s" value="%s"/>\n' % (ind, k2, v2.replace('&', '&amp;').replace('"', '&quot;'))
            xml += '%s </properties>\n%s</object>\n' % (ind, ind)
            STATS['npcs'] = STATS.get('npcs', 0) + 1
        pos = s.rindex('\n', 0, end) + 1
        s = s[:pos] + xml + s[pos:]
        write(n, s.encode('utf-8'))

# quest items
ITEM_ROWS = [it for S in STORY.values() for it in S.d.get('items', [])]
if ITEM_ROWS:
    for fn in ('items.txt', 'items_text.txt'):
        n = 'assets/data/rules/' + fn
        raw = src_bytes(n)
        bom = raw.startswith(b'\xef\xbb\xbf')
        text = raw[3 if bom else 0:].decode('utf-8', errors='surrogateescape')
        nl = '\r\n' if '\r\n' in text else '\n'
        lines = text.rstrip('\r\n').split(nl)
        head = lines[0].split('\t')
        have = {l.split('\t')[0].strip() for l in lines[1:]}
        for iid, name, desc, icon in ITEM_ROWS:
            assert str(iid) not in have, 'item id %d already used' % iid
            if fn == 'items.txt':
                r = [''] * len(head)
                r[0], r[1], r[2], r[4], r[8], r[9], r[10], r[11] = str(iid), name, 'general', '0', '-1', icon, '0', '0'
            else:
                r = [str(iid)] + [name if i % 2 == 1 else desc for i in range(1, len(head))]
            lines.append('\t'.join(r))
            ITEM_VALUE[iid] = -1
        write(n, (b'\xef\xbb\xbf' if bom else b'') + (nl.join(lines) + nl).encode('utf-8', errors='surrogateescape'))

# one Guild Master seat: no oath (any guild, incl. the Church's "renounce" row) once ekg_gm is set
OATH = re.compile(r'SetVariable#guild_(warriors|seventh|wizards|three|loreseekers|golden),1')
conv_names = sorted({n for n in NAMES if n.startswith('assets/data/conversations/') and n.endswith('.txt')}
                    | {n for n in LISTED if n.startswith('assets/data/conversations/') and n.endswith('.txt')})
for n in conv_names:
    got = load_tsv(n)
    if not got:
        continue
    lines, nl, bom = got
    head = [h.strip() for h in lines[0].lstrip('﻿').split('\t')]
    if 'conditions' not in head or 'actions' not in head:
        continue
    ci, ai = head.index('conditions'), head.index('actions')
    ch = 0
    for i in range(1, len(lines)):
        r = lines[i].split('\t')
        if len(r) > ai and OATH.search(r[ai]) and 'ekg_gm' not in r[ci]:
            c = r[ci].strip().strip('"')
            r[ci] = cell((c + ';' if c else '') + 'VariableLower#ekg_gm,1')
            lines[i] = '\t'.join(r)
            ch += 1
    if ch:
        save_tsv(n, lines, nl, bom)
        STATS['oaths'] += ch


# ------------------------------------------------------------------------------------------------ quests
def quest(qid, name, states):
    lines = ['progress\tdescription\tdescription_ES\tactions', '0\t%s\t%s\t' % (name, name)]
    lines += ['%d\t%s\t%s\t' % (p, d, d) for p, d in sorted(states.items())]
    write('assets/data/quests/%s.txt' % qid, b'\xef\xbb\xbf' + '\r\n'.join(lines).encode('utf-8'))
    STATS['quests'] += 1
    return qid


new_q = []
for g, G in GUILDS.items():
    T = G['titles']
    st = {}
    for r in range(5):
        nxt = 'Next: %s — at least %d contracts, level %d, and chapter %d of "%s".' % (T[r + 1], R_NEED[r], L_NEED[r], r + 1, G['quest'])
        st[10 + 10 * r] = 'Rank: %s of the %s. Perks: %s. %s' % (T[r], G['name'], G['perks'][r], nxt)
    st[60] = 'I am the %s of the %s. Perks: %s. I serve this guild alone.' % (T[5], G['name'], G['perks'][5])
    for r in range(5):
        st[90 + r] = 'Former %s of the %s. I left when I took another guild\'s seat; my rank\'s perks remain: %s.' % (T[r], G['name'], G['perks'][r])
    new_q.append(quest('ekg_gq_' + g, '%s: Standing' % G['name'], st))
    if g in STORY:
        new_q.append(quest('ekg_st_' + g, '%s: %s' % (G['name'], G['quest']), STORY[g].journal(G, T)))
        continue
    st = {}
    for k, ch in enumerate(G['chapters'], 1):
        st[10 * k] = ch['j0']
        if 'j3' in ch:
            st[10 * k + 3] = ch['j3']
        if k < 5:
            st[10 * k + 6] = 'Chapter %d complete: I was promoted to %s. The next chapter opens with more contracts and experience.' % (k, T[k])
    st[100] = 'I became %s of the %s.' % (T[5], G['name'])
    new_q.append(quest('ekg_mq_' + g, '%s: %s' % (G['name'], G['quest']), st))

got = src_bytes('assets/data/quests/list.txt')
lst = got.decode('utf-8-sig', errors='replace')
nl = '\r\n' if '\r\n' in lst else '\n'
have = [l.strip() for l in lst.split(nl) if l.strip()]
have += [q for q in new_q if q not in have]
write('assets/data/quests/list.txt', nl.join(have).encode('utf-8') + nl.encode())

# ------------------------------------------------------------------------------------------------ members' stock
for g, G in GUILDS.items():
    tmx, conv = G['stock_npc']
    n = 'assets/data/tmx/%s.tmx' % tmx
    s = src_bytes(n).decode('utf-8')
    hits = [m for m in re.finditer(r'<object\b[^>]*[^/]>(.*?)</object>', s, re.S) if 'value="%s"' % conv in m.group(1)]
    assert len(hits) >= 1, 'stock npc %s not in %s' % (conv, tmx)
    for m in reversed(hits):
        body = m.group(1)
        assert 'shop_items' not in body, '%s already sells' % conv
        indent = re.search(r'\n(\s*)<property ', body).group(1)
        add = '%s<property name="shop_items" value="%s"/>\n%s<property name="shop_modifier" value="0.85"/>\n' % (indent, G['stock'], indent)
        pos = m.start(1) + body.index('</properties>')
        pos = s.rindex('\n', 0, pos) + 1
        s = s[:pos] + add + s[pos:]
        STATS['shops'] += 1
    write(n, s.encode('utf-8'))

if ADDED:
    with open(LISTED_PATH, 'a') as fh:
        fh.write(''.join(a + '\n' for a in ADDED))
STATS.setdefault('npcs', 0)
print('guild expansion: %(files)d files written, %(rows)d rows added, %(oaths)d oath rows gated, %(quests)d journal quests, %(shops)d guild shops, %(npcs)d story characters' % STATS)
if STATS['shops'] < len(GUILDS) or STATS['quests'] != 2 * len(GUILDS) or STATS['oaths'] < 5 or STATS.get('npcs', 0) < len(STORY) * 5:
    sys.exit('guild expansion: incomplete (%s)' % STATS)
