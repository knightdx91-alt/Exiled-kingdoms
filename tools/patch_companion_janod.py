#!/usr/bin/env python3
"""
Add Janod as a full mage companion (Adaon/Hirge/Grissenda-grade).
Run from inside the apktool-decoded tree (build/dec). See deobf/COMPANION_SPEC.md.

Two smali edits are unavoidable: companion identity is hardcoded by spawn_id in
NPC.<init> (the `companionSpawn` whitelist) and in the UpgradeCompanion action.
Everything else is data.
"""
import sys, re
w = "."

# ---------------------------------------------------------------------------
# 1) NPC.<init>(Spawn): make "janod" a companion WITHOUT the companion init path.
#
#    v1 of this patch jumped janod to the companion path (:goto_10d). That was the
#    on-device FREEZE: the companion path skips the normal stat init
#    (CharacterSheet.a(weaponStats, baseArmor, level, resists, attrs) at :cond_ab)
#    and instead relies on each vanilla companion's HARDCODED gear/level block --
#    janod matches none of them, so his sheet was never initialized at all (no
#    level, no weapon, no HP) and every AI/update tick choked once he spawned.
#
#    v2: janod stays on the NORMAL init path (full bestiary-driven stats, exactly
#    like his vanilla miniboss spawn) and we only set companionSpawn=true first.
#    That flag is all Party.a(NPC)/z0() need at recruit time.
#
#    Anchor (labels captured, never hardcoded):
#        invoke-virtual {v1, v3}, ...equals   (v3 == "hirge")
#        move-result v1
#        if-eqz v1, :cond_ab      <- normal init
#        goto/16 :goto_10d        <- companion init path
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/Final/NPC.smali'
s = open(p, encoding='utf-8').read()

# Label names differ per disassembler (apktool :cond_0 vs baksmali :cond_2f), so match
# the instruction pattern and CAPTURE whatever labels this build uses.
pat = re.compile(
    r'    invoke-virtual \{v1, v3\}, Ljava/lang/String;->equals\(Ljava/lang/Object;\)Z\n\n'
    r'    move-result v1\n\n'
    r'    if-eqz v1, :(cond_[0-9a-f]+)\n\n'
    r'    goto/16 :(goto_[0-9a-f]+)\n')
m = pat.search(s)
assert m, "NPC companion-whitelist anchor not found"
cond_lbl, goto_lbl = m.group(1), m.group(2)

# v7 is a Locale scratch in this region on every incoming path; both v1 and v7 are
# overwritten immediately at :cond_ab (v1=maxlevel, v7=sheet), so the merge is
# verifier-clean (int/int and Locale|String -> overwritten before any read).
repl = ("    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z\n\n"
        "    move-result v1\n\n"
        "    if-nez v1, :ekjanod_iscompanion\n\n"
        "    iget-object v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->spawn_id:Ljava/lang/String;\n\n"
        "    sget-object v7, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;\n\n"
        "    invoke-virtual {v1, v7}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;\n\n"
        "    move-result-object v1\n\n"
        '    const-string v7, "janod"\n\n'
        "    invoke-virtual {v1, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z\n\n"
        "    move-result v1\n\n"
        f"    if-eqz v1, :{cond_lbl}\n\n"
        "    const/4 v1, 0x1\n\n"
        "    iput-boolean v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->companionSpawn:Z\n\n"
        f"    goto/16 :{cond_lbl}\n\n"
        "    :ekjanod_iscompanion\n"
        f"    goto/16 :{goto_lbl}\n")
s = s[:m.start()] + repl + s[m.end():]
print("patched NPC.<init>: janod -> normal stat init + companionSpawn=true")

# ---------------------------------------------------------------------------
# 1b) NPC.W() (sprite build): keep janod on the bestiary-sprite path.
#     The companionSpawn branch of W() builds a composite paper-doll with a
#     HARDCODED head per companion ("_head3"/"_head2"/"_head_leather"). Janod
#     matches none -> head lookup fails -> the failure flag clears the whole
#     spriteIndex -> he can never resolve a sprite (the on-screen grind).
#     Fix: swap the method's companionSpawn read for a helper that returns
#     false for janod, so he always renders his bestiary sprite
#     (male_tunic_blue) -- which is also the faithful look.
#     The method is located by its unique "_head_leather" string, never by name.
# ---------------------------------------------------------------------------
NPCC = 'Lnet/fdgames/GameEntities/Final/NPC;'
i_head = s.index('"_head_leather"')
m_start = s.rindex('.method ', 0, i_head)
m_end = s.index('.end method', i_head)
method = s[m_start:m_end]
gate = re.search(
    rf'    iget-boolean (v\d+), p0, {re.escape(NPCC)}->companionSpawn:Z\n', method)
assert gate, "W() companionSpawn gate not found"
reg = gate.group(1)
method = (method[:gate.start()]
          + f'    invoke-static {{p0}}, {NPCC}->ekCompanionSprite({NPCC})Z\n\n'
          + f'    move-result {reg}\n'
          + method[gate.end():])
s = s[:m_start] + method + s[m_end:]

helper = f'''
.method public static ekCompanionSprite({NPCC})Z
    .locals 2

    iget-boolean v0, p0, {NPCC}->companionSpawn:Z

    if-nez v0, :ekcs_yes

    const/4 v0, 0x0

    return v0

    :ekcs_yes
    iget-object v0, p0, {NPCC}->spawn_id:Ljava/lang/String;

    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    invoke-virtual {{v0, v1}}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "janod"

    invoke-virtual {{v0, v1}}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :ekcs_notjanod

    const/4 v0, 0x0

    return v0

    :ekcs_notjanod
    const/4 v0, 0x1

    return v0
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + helper + s[tail:]
print("patched NPC.W(): janod keeps bestiary sprite (+helper ekCompanionSprite)")

# ---------------------------------------------------------------------------
# 1c) Starting equipment for Janod (v6, owner feedback: "no starting equipment").
#
#     WHY he has none. The three vanilla companions get their gear from a
#     HARDCODED block at the end of NPC.<init>'s companion path -- real item ids
#     written straight into CharacterInventory.slot_* (grissenda: ring 2502 /
#     body 100 / offhand 120 / mainhand 522). Janod deliberately takes the
#     NORMAL init path instead (that is the v2 freeze fix), and the normal path
#     never touches the inventory: it calls CharacterSheet.h0(weaponStats,
#     defense, level, resists, attrs), which stashes `hardcoded_weapon` and
#     `hardcoded_defense` from his bestiary row. Those two fields SHORT-CIRCUIT
#     the gear system -- CharacterSheet.j() returns `stats + hardcoded_defense`
#     whenever hardcoded_defense != -999, and damage reads hardcoded_weapon
#     before the equipped mainhand -- so simply handing him items would have
#     changed nothing anyway.
#
#     Fix: a static helper that, the first time janod is registered as a
#     companion, equips a level-6/7 sorcerer kit and then drops the two
#     hardcoded overrides so the gear actually drives his damage and armour,
#     exactly like Grissenda/Hirge/Adaon. Elm Wand carries the SAME weaponstats
#     (wand_3) his bestiary row already used, so his damage is unchanged.
#
#     Hooked at the top of Party.a(NPC) -- the single registration point every
#     companion goes through (NPC.C1() -> party.addCompanion). That makes it
#     self-healing: a Janod already stored in an existing save (party.companions
#     is serialised, and MonsterSpawn.Q() re-uses the stored object forever) is
#     repaired the next time he is recruited or the level reloads. Guarded by
#     `slot_mainhand == 0`, so it runs exactly once per character.
# ---------------------------------------------------------------------------
CS = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
CI = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;'

# item_ID -> slot. Value ~2900 gold, in line with Hirge's kit; every piece is a
# real items.txt row (Classes=M) so the companion sheet shows proper equipment.
JANOD_GEAR = [
    ('slot_mainhand', 391),   # Elm Wand          - weaponstats wand_3 (4-8, ranged)
    ('slot_body',     332),   # High Mage Robes   - armor 3, +15 mana
    ('slot_head',     312),   # Conjurer Hood     - armor 1, +5 mana
    ('slot_feet',     311),   # Conjurer Boots    - armor 1, +6 mana
    ('slot_ring',    3034),   # Ring of the Scholar - +4 mana, 10% death resist
]

gear_ops = ''
for slot, item in JANOD_GEAR:
    gear_ops += (f'    const/16 v2, 0x{item:x}\n\n'
                 f'    iput v2, v1, {CI}->{slot}:I\n\n')

gear_helper = f'''
.method public static ekJanodGear({NPCC})V
    .locals 3

    if-eqz p0, :ekjg_done

    iget-object v0, p0, {NPCC}->spawn_id:Ljava/lang/String;

    if-eqz v0, :ekjg_done

    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    invoke-virtual {{v0, v1}}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "janod"

    invoke-virtual {{v0, v1}}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :ekjg_done

    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->sheet:{CS}

    if-eqz v0, :ekjg_done

    iget-object v1, v0, {CS}->inventory:{CI}

    if-eqz v1, :ekjg_done

    iget v2, v1, {CI}->slot_mainhand:I

    if-nez v2, :ekjg_done

{gear_ops}    invoke-virtual {{v1}}, {CI}->u()V

    invoke-virtual {{v0}}, {CS}->ekDropHardcoded()V

    :ekjg_done
    return-void
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + gear_helper + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched NPC: +ekJanodGear (%d items)" % len(JANOD_GEAR))

# CharacterSheet.ekDropHardcoded(): the two override fields are PRIVATE, so the
# method that clears them has to live in CharacterSheet itself.
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterSheet.smali'
s = open(p, encoding='utf-8').read()
for fld in ('hardcoded_weapon:Lnet/fdgames/Rules/WeaponStats;',
            'hardcoded_defense:I'):
    assert f'.field private {fld}' in s, f"CharacterSheet.{fld} not private as expected"
drop_helper = f'''
.method public ekDropHardcoded()V
    .locals 2

    const/4 v0, 0x0

    iput-object v0, p0, {CS}->hardcoded_weapon:Lnet/fdgames/Rules/WeaponStats;

    iput-object v0, p0, {CS}->hardcoded_resistances:Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;

    const/16 v1, -0x3e7

    iput v1, p0, {CS}->hardcoded_defense:I

    return-void
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + drop_helper + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched CharacterSheet: +ekDropHardcoded")

# Call site: top of Party.a(NPC), just after the null check, before the NPC is
# recorded as the active companion.
p = f'{w}/smali/net/fdgames/GameWorld/Party.smali'
s = open(p, encoding='utf-8').read()
anchor = (f'    iget-object v0, p1, {NPCC}->spawn_id:Ljava/lang/String;\n\n'
          '    iput-object v0, p0, Lnet/fdgames/GameWorld/Party;'
          '->activeCompanion:Ljava/lang/String;\n')
assert s.count(anchor) == 1, "Party.addCompanion anchor not unique/found"
s = s.replace(anchor,
              f'    invoke-static {{p1}}, {NPCC}->ekJanodGear({NPCC})V\n\n' + anchor, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched Party.addCompanion: -> ekJanodGear")

# ---------------------------------------------------------------------------
# 2) ScriptedAction.a() UpgradeCompanion: add a janod branch.
#    MUST go before the adaon block (which clobbers v0, the action-data string).
#    We insert immediately before the FIRST "hirge" test. Scratch v2/v3 only;
#    v0 (data) and v1 (companion NPC) are preserved.
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameLogic/ScriptedAction.smali'
s = open(p, encoding='utf-8').read()

hirge_anchor = """    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v2

    const-string v3, "hirge"
"""
assert s.count(hirge_anchor) == 1, "UpgradeCompanion hirge anchor not unique/found"

# Five PASSIVE mage skills (see spec: vanilla upgrades grant passives only).
JANOD_SKILLS = ("mage_barrier", "arcanist", "mana_surge", "wand_mastery", "staff_mastery")

grant = ""
for sk in JANOD_SKILLS:
    grant += ("    iget-object v2, v1, Lnet/fdgames/GameEntities/Character;->sheet:"
              "Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;\n\n"
              f'    const-string v3, "{sk}"\n\n'
              "    invoke-virtual {v2, v3}, Lnet/fdgames/GameEntities/CharacterSheet/"
              "CharacterSheet;->e(Ljava/lang/String;)V\n\n")

janod_block = """    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v2

    const-string v3, "janod"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :ekjanod_noupgrade

""" + grant + """    :ekjanod_noupgrade
"""

s = s.replace(hirge_anchor, janod_block + hirge_anchor, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched ScriptedAction: UpgradeCompanion#janod -> %s" % ", ".join(JANOD_SKILLS))

# ---------------------------------------------------------------------------
# 3) bestiary.txt: janod race miniboss -> npc, add a starting Skillset
# ---------------------------------------------------------------------------
p = f'{w}/assets/data/rules/bestiary.txt'
raw = open(p, encoding='utf-8', newline='').read()
nl = '\r\n' if '\r\n' in raw else '\n'
lines = raw.split(nl)
hdr = lines[0].lstrip('﻿').split('\t')
ix = {c: i for i, c in enumerate(hdr)}
found = False
for i, ln in enumerate(lines):
    if ln.split('\t')[0] == 'janod':
        f = ln.split('\t')
        f[ix['race']] = 'npc'                              # match the other 3 companions
        f[ix['Skillset']] = '"wand_mastery#3;mana_surge#2"'
        # v3 (owner feedback): his miniboss row leaked into companion stats --
        # level 14 start, 8-13 FIRE wand, defense 10, resist 40x6. Real
        # companion rows (adaon/hirge/grissenda) are low-level, weaponless-ish,
        # defense 0, no resists; armor comes from gear and XP comes from the
        # recruit-time catch-up (Party.r(): companion XP -> player XP / 2).
        # Hirge-style start; deliberate deviation: the hostile "mock Janod"
        # fight in A Mad Wizard gets much easier (logged in COMPANION_SPEC).
        f[ix['minlevel']] = '6'
        f[ix['maxlevel']] = '7'
        f[ix['weapon_id']] = 'wand_3'      # Conjurer Wand: 4-8 plain magic bolt
        f[ix['defense']] = '0'
        f[ix['resist']] = ''
        lines[i] = '\t'.join(f)
        found = True
        break
assert found, "janod row not found in bestiary.txt"
open(p, 'w', encoding='utf-8', newline='').write(nl.join(lines))
print("patched bestiary.txt: janod race=npc + Skillset")

# ---------------------------------------------------------------------------
# 4) kingsbridge_wizard.txt: APPEND recruit / dismiss / battle-orders rows.
#    8 cols: index, type, text, text_ES, Go To, conditions, actions, requisites
#    Existing rows are never modified. Indices 60+ are unused (max was 53).
#    Entry hooks are added as new index-1 rows, which the engine evaluates in
#    order and picks the first whose conditions pass -- so they must come BEFORE
#    the existing unconditional index-1 greeting. We therefore insert the two
#    index-1 hook rows directly above the first existing index-1 row.
# ---------------------------------------------------------------------------
p = f'{w}/assets/data/conversations/kingsbridge_wizard.txt'
raw = open(p, encoding='utf-8', newline='').read()
bom = '﻿' if raw.startswith('﻿') else ''
body = raw[len(bom):]
nl = '\r\n' if '\r\n' in body else '\n'
lines = body.split(nl)
ncols = len(lines[0].split('\t'))
assert ncols == 8, f"unexpected column count {ncols}"


def row(index, typ, text, goto='0', cond='', act=''):
    r = [''] * ncols
    r[0], r[1], r[2] = index, typ, text
    r[3] = text          # text_ES: fall back to English (engine shows blank otherwise)
    r[4], r[5], r[6] = goto, cond, act
    return '\t'.join(r)


# --- entry hooks (must precede the existing index-1 rows) ---
hooks = [
    # already travelling with you -> companion menu
    row('1', 'Q', 'Yes, my friend? The weave is quiet today.', '60', 'NPCisFollower#'),
    # research finished and no companion yet -> offer to join
    row('1', 'Q', '[BLUE](Janod closes his tome and studies you for a long moment)[] '
                  'My research here is done, friend. And yet I find I am reluctant to '
                  'return to Whitetower alone. Would you have a sorcerer at your side?',
        '61', '"VariableGreater#mad_wizard,99;HasNoCompanion#"'),
    # ALSO offer after the Cloak of the Wolf quest (fair_deal>99) -- the owner
    # finished that quest and expected the recruit option. Guards: mad_wizard<10
    # (not while he considers you a criminal / hostile) and Adaon not in party
    # (so this hook can't shadow the scripted "damn thief!" recognition scene,
    # which is also the only way the mad_wizard quest starts).
    row('1', 'Q', '[BLUE](Janod sets down the enchanted cloak-clasp he was toying '
                  'with)[] That cloak of yours has held up well, I see. My work in '
                  'Kingsbridge is nearly done... perhaps a road with you would teach '
                  'me more than these books. Would you have a sorcerer at your side?',
        '61', '"VariableGreater#fair_deal,99;VariableLower#mad_wizard,10;'
              'NPCIsNotInParty#adaon,adaon;HasNoCompanion#"'),
]

first_idx1 = next(i for i, ln in enumerate(lines) if ln.split('\t')[0] == '1')
lines[first_idx1:first_idx1] = hooks

# --- appended sub-trees ---
tail = [
    # 60: companion menu while following
    row('60', 'A', 'Nothing, let us move on.'),
    row('60', 'A', 'I have some battle orders for you.', '62'),
    row('60', 'A', 'Please step aside for a moment.', '0', '', 'NPCMoveRandom#'),
    row('60', 'A', 'I no longer need your help. You can return to Kingsbridge, with my thanks.',
        '68', '"AreaIsnt#I9_castle;AreaIsnt#I9_temple"'),

    # 61: the join offer
    row('61', 'A', 'I would be glad to have you. Welcome.', '66', 'HasNoCompanion#',
        'NPCFollow#'),
    row('61', 'A', 'Not now, Janod. Perhaps later.', '67'),

    # 62-65: battle orders (mirrors companion_orders.txt / mercenary_grisenda 91-96)
    row('62', 'Q', 'Of course. What would you have me do?', '63'),
    row('63', 'A', 'Wait here for a moment.', '65', '', 'NPCWait#'),
    row('63', 'A', 'Follow me again.', '65', '', 'NPCDontWait#'),
    row('63', 'A', 'I want to tell you when to engage.', '64'),
    row('63', 'A', 'Nevermind.'),
    row('64', 'Q', 'Very well. And what about that?', '65'),
    row('65', 'A', 'Never attack anyone, even to defend yourself.', '0', '',
        'NPCAttack#never'),
    row('65', 'A', 'Attack only those who strike you first.', '0', '', 'NPCAttack#defend'),
    row('65', 'A', 'Attack only enemies that come close to you.', '0', '',
        'NPCAttack#close'),
    row('65', 'A', 'Attack every enemy in sight.', '0', '', 'NPCAttack#all'),

    # 66-68: flavour / dismissal
    row('66', 'Q', '[BLUE](Janod slides the tome into his satchel and smiles)[] '
                   'Then let us see what the world has been hiding. Lead on.'),
    row('67', 'Q', 'The offer stands, friend. You will find me here, among my books.'),
    row('68', 'Q', 'A fair road to you. Should you need a Weaver again, you know where '
                   'my books are.', '0', '',
        # NPCStopFollowing# de-registers him (Party.b -> activeCompanion = "")
        # and NPCDespawn#janod removes the actor from the map he was dismissed
        # on. Getting him HOME is a separate step -- see the G9.tmx triggers
        # below; the tag "janod" is the TMX object tag, which is what both
        # NPCDespawn and NPCSpawn match on.
        '"NPCStopFollowing#;NPCDespawn#janod"'),
]
while lines and lines[-1].strip() == '':
    lines.pop()
lines.extend(tail)

open(p, 'w', encoding='utf-8', newline='').write(bom + nl.join(lines) + nl)
print("patched kingsbridge_wizard.txt: +%d rows (recruit/dismiss/orders)"
      % (len(hooks) + len(tail)))

# ---------------------------------------------------------------------------
# 5) G9.tmx (Kingsbridge): send a dismissed companion back to their own spot.
#
#     REVERSED MECHANISM (v6 -- do not guess this again). Dismissal is TWO
#     separate engine steps, and only the first one is in the conversation:
#
#       NPCStopFollowing# -> NPC.V1(): restores the bestiary AI/faction and
#           calls Party.b(spawn_id, tag, name), which clears activeCompanion.
#           The NPC object STAYS in party.companions forever (that is what keeps
#           a re-recruited companion's level and XP).
#       NPCDespawn#<tag>  -> destroys the actor on the CURRENT map only.
#
#     Nothing in either step moves anyone. Coming home is done by the map:
#     MonsterSpawn.Q() checks `party.m(spawn_id)` (== "this spawn_id is a known
#     companion") FIRST and, when true, does not build a new NPC at all -- it
#     takes the stored companion object, drops it on the spawn point, re-adds it
#     to the level and calls V1(). (It even contains a "Grisenda"->"Grissenda"
#     name fixup, which is how you can tell that branch exists for exactly this.)
#     MonsterSpawn.u("SPAWN") additionally refuses to fire while the NPC is the
#     ACTIVE companion (`party.p(spawn_id, tag)`), so this can never duplicate
#     or steal a companion who is still with you.
#
#     So a dismissed companion returns iff their TMX spawn object runs again.
#     Two things stop that:
#       (a) Janod's Kingsbridge spawn is gated `VariableLower#mad_wizard,100`,
#           i.e. it is switched OFF for good the moment his quest is settled
#           (100 = paid, 110 = orb returned) -- which is precisely the state the
#           mod recruits him in. He could never come back, and for the same
#           reason the mad_wizard recruit offer was barely reachable at all.
#       (b) Level state is cached per map (data/saves/<slot>/levels/<map>.sav)
#           and only rebuilt from the TMX once it is ~1080 game-seconds stale
#           (GameLevelData.E). Until Kingsbridge resets, no spawn object runs --
#           which is why Grissenda also "isn't going to Kingsbridge" even though
#           her own spawn condition (warrior_honor > 100) is satisfied.
#
#     Fix, entirely in data and with a vanilla precedent (H10_mine.tmx uses the
#     identical shape to force Teram to appear): a map-wide trigger that calls
#     NPCSpawn#<tag>, which invokes MonsterSpawn.Q() directly -- bypassing both
#     the stale-cache wait and, for Janod, the dead spawn condition.
#     NPCNotinArea#<tag> makes it a no-op whenever they are already standing in
#     Kingsbridge, and HasNoCompanion# makes it a no-op while they travel with
#     you, so the trigger can only ever fire when they are genuinely missing.
# ---------------------------------------------------------------------------
p = f'{w}/assets/data/tmx/G9.tmx'
raw = open(p, encoding='utf-8', newline='').read()
assert '<property name="spawn" value="janod"/>' in raw, "janod spawn missing from G9.tmx"
assert 'value="mercenary_grisenda"' in raw, "grissenda spawn missing from G9.tmx"

# The Kingsbridge object layer is 96x96 tiles; every object in it sits inside
# 0..3072 on both axes, so this rect is "anywhere in Kingsbridge".
MAP_RECT = 'x="0" y="0" width="3072" height="3072"'
homecoming = f'''  <object name="ek_janod_homecoming" type="trigger" {MAP_RECT}>
   <properties>
    <property name="actions" value="NPCSpawn#janod"/>
    <property name="conditions" value="VariableGreater#mad_wizard,99;VariableLower#mad_wizard,200;NPCNotinArea#janod;HasNoCompanion#"/>
   </properties>
  </object>
  <object name="ek_janod_homecoming2" type="trigger" {MAP_RECT}>
   <properties>
    <property name="actions" value="NPCSpawn#janod"/>
    <property name="conditions" value="VariableGreater#fair_deal,99;VariableLower#mad_wizard,10;NPCNotinArea#janod;HasNoCompanion#"/>
   </properties>
  </object>
  <object name="ek_grissenda_homecoming" type="trigger" {MAP_RECT}>
   <properties>
    <property name="actions" value="NPCSpawn#mercenary_grisenda"/>
    <property name="conditions" value="VariableGreater#warrior_honor,100;NPCNotinArea#mercenary_grisenda;HasNoCompanion#"/>
   </properties>
  </object>
'''
close = ' </objectgroup>\n'
assert raw.count(close) == 1, "G9.tmx objectgroup close not unique"
raw = raw.replace(close, homecoming + close, 1)
open(p, 'w', encoding='utf-8', newline='').write(raw)
print("patched G9.tmx: +3 homecoming triggers (janod x2, grissenda)")
print("DONE")
