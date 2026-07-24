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
# 1) NPC.<init>(Spawn): add "janod" to the companion whitelist -> companionSpawn=true
#    Original tail of the 3-way test:
#        invoke-virtual {v1, v3}, ...equals   (v3 == "hirge")
#        move-result v1
#        if-eqz v1, :cond_0
#        goto/16 :goto_2
#        :cond_0
#    We keep that exact fallthrough and add a 4th test in front of :cond_0.
#    v7 is already used as a Locale scratch in this region; v1 is the working reg.
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
        "    :ekjanod_iscompanion\n"
        f"    goto/16 :{goto_lbl}\n")
s = s[:m.start()] + repl + s[m.end():]
open(p, 'w', encoding='utf-8').write(s)
print("patched NPC.<init>: janod -> companionSpawn=true")

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
]

first_idx1 = next(i for i, ln in enumerate(lines) if ln.split('\t')[0] == '1')
lines[first_idx1:first_idx1] = hooks

# --- appended sub-trees ---
tail = [
    # 60: companion menu while following
    row('60', 'A', 'Nothing, let us move on.'),
    row('60', 'A', 'I have some battle orders for you.', '62'),
    row('60', 'A', 'Please step aside for a moment.', '0', '', 'NPCMoveRandom#'),
    row('60', 'A', 'I no longer need your help. Return to Whitetower, with my thanks.',
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
                   'my books are.', '0', '', 'NPCStopFollowing#'),
]
while lines and lines[-1].strip() == '':
    lines.pop()
lines.extend(tail)

open(p, 'w', encoding='utf-8', newline='').write(bom + nl.join(lines) + nl)
print("patched kingsbridge_wizard.txt: +%d rows (recruit/dismiss/orders)"
      % (len(hooks) + len(tail)))
print("DONE")
