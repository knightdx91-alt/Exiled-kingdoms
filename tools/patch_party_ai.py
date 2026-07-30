#!/usr/bin/env python3
"""
v8 features. Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).
Full reversing behind every edit: deobf/PARTY_AI_SPEC.md.

1) Wizard AI          - AISkillUsage has branches for warrior/rogue/cleric ONLY, so a
                        mage companion can never cast. Adds ekWizardAI(NPC).
2) NPC caster mana    - CharacterStats.g(): race NPC gets the caster formula, otherwise a
                        companion mage has a 0 base mana pool and nothing to spend.
3) Mage skills on the - skills2/skills_advanced2: the mage actives are NPC=N, so they are
   companion sheet     invisible on an NPC sheet (the owner's "only the top row").
4) Summon army        - SkillActions: drop the single Party.b() call that despawns the
                        player's previous summon; stretch the duration.
5) Second companion   - NPC.z0(): a companion recruited while the companion slot is taken
                        falls through to the (unlimited, fully persisted) follower path.
"""
import re

w = "."
NPCC = 'Lnet/fdgames/GameEntities/Final/NPC;'
CHAR = 'Lnet/fdgames/GameEntities/Character;'
CS = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
CST = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;'
SKS = 'Lnet/fdgames/GameEntities/Helpers/SkillSet;'
AIS = 'Lnet/fdgames/GameEntities/AI/AISkillUsage;'
PARTY = 'Lnet/fdgames/GameWorld/Party;'
CRACE = 'Lnet/fdgames/Rules/Rules$CharacterRace;'
FDU = 'Lnet/fdgames/Helpers/FDUtils;'

# ---------------------------------------------------------------------------
# 1) AISkillUsage.ekWizardAI(NPC) + hooks
#
#    The existing per-class dispatch is a chain of ordinal compares handling 0/1/2;
#    ordinal 3 (WIZARD) explicitly falls through to the return. Rather than surgically
#    widening that chain (register-hostile), the wizard behaviour is a separate static
#    that re-checks the class itself and is called from the top of a() and b() -- the two
#    combat entry points CompanionAI uses. It mirrors the cleric heal_wounds branch
#    exactly: know the skill (SkillSet.e), afford it (CharacterSheet.r >= SkillSet.b),
#    roll for it, then Character.c(id) + Character.o(targetID).
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/AI/AISkillUsage.smali'
s = open(p, encoding='utf-8').read()

# (skill id, percent chance). Ordered: best damage first, cheap filler last.
WIZARD_SPELLS = [("fireball", 40), ("ice_storm", 35), ("lightning_bolt", 45)]

blocks = ''
for n, (sk, chance) in enumerate(WIZARD_SPELLS):
    blocks += f'''    iget-object v1, p0, {CHAR}->sheet:{CS}

    iget-object v1, v1, {CS}->skillSet:{SKS}

    const-string v2, "{sk}"

    invoke-virtual {{v1, v2}}, {SKS}->e(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :ekw_next{n}

    iget-object v1, p0, {CHAR}->sheet:{CS}

    invoke-virtual {{v1}}, {CS}->r()I

    move-result v1

    iget-object v3, p0, {CHAR}->sheet:{CS}

    iget-object v3, v3, {CS}->skillSet:{SKS}

    invoke-virtual {{v3, v2}}, {SKS}->b(Ljava/lang/String;)I

    move-result v3

    if-lt v1, v3, :ekw_next{n}

    const/4 v1, 0x1

    const/16 v3, 0x64

    invoke-static {{v1, v3}}, {FDU}->a(II)I

    move-result v1

    const/16 v3, 0x{chance:x}

    if-gt v1, v3, :ekw_next{n}

    invoke-virtual {{p0, v2}}, {CHAR}->c(Ljava/lang/String;)Z

    iget v1, p0, {NPCC}->detectedEnemyID:I

    invoke-virtual {{p0, v1}}, {CHAR}->o(I)V

    return-void

    :ekw_next{n}
'''

wizard = f'''
.method public static ekWizardAI({NPCC})V
    .locals 4

    if-eqz p0, :ekw_done

    iget-object v0, p0, {CHAR}->sheet:{CS}

    if-eqz v0, :ekw_done

    invoke-virtual {{v0}}, {CS}->n()Lnet/fdgames/Rules/Rules$CharacterClass;

    move-result-object v0

    if-eqz v0, :ekw_done

    invoke-virtual {{v0}}, Ljava/lang/Enum;->ordinal()I

    move-result v0

    const/4 v1, 0x3

    if-ne v0, v1, :ekw_done

    iget v0, p0, {NPCC}->detectedEnemyID:I

    if-eqz v0, :ekw_done

{blocks}    :ekw_done
    return-void
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + wizard + s[tail:]

hooks = 0
for sig in (f'.method public static a({NPCC})V', f'.method public static b({NPCC})V'):
    m = re.search(re.escape(sig) + r'\n    \.locals \d+\n\n', s)
    assert m, f"AISkillUsage {sig} not found"
    s = (s[:m.end()]
         + f'    invoke-static {{p0}}, {AIS}->ekWizardAI({NPCC})V\n\n'
         + s[m.end():])
    hooks += 1
open(p, 'w', encoding='utf-8').write(s)
print("patched AISkillUsage: +ekWizardAI (%d spells), hooked into %d entry points"
      % (len(WIZARD_SPELLS), hooks))

# ---------------------------------------------------------------------------
# 2) CharacterStats.g()I -- race NPC gets the caster mana formula.
#
#    Vanilla: v0 = isMonster ? 2 : 0 ; v2 = (race == HUMAN) ? 12 : 0 ;
#             wizard/cleric -> level * v0 + v2, everyone else 0.
#    Race NPC is neither, so a companion mage's base pool is 0. Insert at :goto_1b
#    (after v0/v2 are decided, before v3/v4 are loaded with the class -- both are free
#    scratch there) and give NPC the human+monster numbers: level * 2 + 12.
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterStats.smali'
s = open(p, encoding='utf-8').read()
ms = s.index('.method public g()I')
me = s.index('.end method', ms)
method = s[ms:me]
anchor = re.search(r'    :(goto_[0-9a-f]+)\n(    iget-object v3, p0, '
                   + re.escape(CST) + r'->characterClass:)', method)
assert anchor, "CharacterStats.g() race/class join point not found"
inject = (f'    :{anchor.group(1)}\n'
          f'    iget-object v3, p0, {CST}->characterRace:{CRACE}\n\n'
          f'    sget-object v4, {CRACE}->l:{CRACE}\n\n'
          '    if-ne v3, v4, :eknpc_mana\n\n'
          '    const/4 v0, 0x2\n\n'
          '    const/16 v2, 0xc\n\n'
          '    :eknpc_mana\n'
          + anchor.group(2))
method = method[:anchor.start()] + inject + method[anchor.end():]
s = s[:ms] + method + s[me:]
open(p, 'w', encoding='utf-8').write(s)
print("patched CharacterStats.g(): race NPC gets caster mana (level*2 + 12)")

# ---------------------------------------------------------------------------
# 4) SkillActions.a(Character,String,II)V -- summons stack, and last longer.
#
#    The one-at-a-time rule is the single `Party.b()V` invoke on the player branch
#    (NPC casters never reach it, which is why THEIR summons already stack). Removing
#    that invoke is the whole feature. The duration argument (p3) is multiplied at the
#    top of the method so the timer still bounds how many can pile up.
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/Rules/SkillActions.smali'
s = open(p, encoding='utf-8').read()
sig = f'.method public static a({CHAR}Ljava/lang/String;II)V'
ms = s.index(sig)
me = s.index('.end method', ms)
method = s[ms:me]

drop = re.search(r'\n    invoke-virtual \{[vp]\d+\}, ' + re.escape(PARTY) + r'->b\(\)V\n', method)
assert drop, "SkillActions summon: Party.b() despawn call not found"
method = method[:drop.start()] + '\n' + method[drop.end():]

hdr = re.match(r'(.*?\n    \.locals \d+\n\n)', method, re.S)
assert hdr, "SkillActions summon: method header not found"
SUMMON_DURATION_MULT = 5
method = (method[:hdr.end()]
          + f'    mul-int/lit8 p3, p3, 0x{SUMMON_DURATION_MULT:x}\n\n'
          + method[hdr.end():])
s = s[:ms] + method + s[me:]
open(p, 'w', encoding='utf-8').write(s)
print("patched SkillActions: player summons stack, duration x%d" % SUMMON_DURATION_MULT)

# ---------------------------------------------------------------------------
# 5) NPC.z0() -- a companion recruited while the slot is taken becomes a follower.
#    Also moves the Janod gear hook here, so a follower-Janod is equipped too.
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/Final/NPC.smali'
s = open(p, encoding='utf-8').read()
ms = s.index('.method public z0()V')
me = s.index('.end method', ms)
method = s[ms:me]

gate = re.search(r'    iget-boolean (v\d+), p0, ' + re.escape(NPCC)
                 + r'->companionSpawn:Z\n\n    if-eqz \1, :(cond_[0-9a-f]+)\n', method)
assert gate, "NPC.z0() companionSpawn fork not found"
reg, cond = gate.group(1), gate.group(2)
method = (method[:gate.start()]
          + f'    invoke-static {{p0}}, {NPCC}->ekTakesCompanionSlot({NPCC})Z\n\n'
          + f'    move-result {reg}\n\n'
          + f'    if-eqz {reg}, :{cond}\n'
          + method[gate.end():])

hdr = re.match(r'(.*?\n    \.locals \d+\n\n)', method, re.S)
assert hdr, "NPC.z0() header not found"
method = (method[:hdr.end()]
          + f'    invoke-static {{p0}}, {NPCC}->ekJanodGear({NPCC})V\n\n'
          + method[hdr.end():])
s = s[:ms] + method + s[me:]

slot_helper = f'''
.method public static ekTakesCompanionSlot({NPCC})Z
    .locals 2

    const/4 v0, 0x0

    if-eqz p0, :ekts_no

    iget-boolean v1, p0, {NPCC}->companionSpawn:Z

    if-eqz v1, :ekts_no

    invoke-static {{}}, Lnet/fdgames/GameWorld/GameData;->O()Lnet/fdgames/GameWorld/GameData;

    move-result-object v1

    iget-object v1, v1, Lnet/fdgames/GameWorld/GameData;->party:{PARTY}

    if-eqz v1, :ekts_yes

    invoke-virtual {{v1}}, {PARTY}->h()Z

    move-result v1

    if-nez v1, :ekts_no

    :ekts_yes
    const/4 v0, 0x1

    :ekts_no
    return v0
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + slot_helper + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched NPC.z0(): 2nd companion -> follower path (+ekTakesCompanionSlot)")

# ---------------------------------------------------------------------------
# 3) skills2.txt / skills_advanced2.txt -- let the mage kit show on an NPC sheet.
#    Column 2 ("NPC") N -> Y for the skills a companion mage should display and use.
# ---------------------------------------------------------------------------
NPC_VISIBLE = {
    'skills2.txt': ("Lightning Bolt", "Fireball", "Ice Storm", "Mage Armor"),
    'skills_advanced2.txt': ("Mage Barrier", "Arcanist"),
}
for fname, names in NPC_VISIBLE.items():
    p = f'{w}/assets/data/rules/{fname}'
    raw = open(p, encoding='utf-8', newline='').read()
    bom = '﻿' if raw.startswith('﻿') else ''
    body = raw[len(bom):]
    nl = '\r\n' if '\r\n' in body else '\n'
    lines = body.split(nl)
    hits = 0
    for i, ln in enumerate(lines):
        f = ln.split('\t')
        if len(f) > 2 and f[0].strip() in names:
            assert f[2] in ('N', 'Y'), f"{fname}: unexpected NPC column {f[2]!r}"
            f[2] = 'Y'
            lines[i] = '\t'.join(f)
            hits += 1
    assert hits == len(names), f"{fname}: matched {hits}/{len(names)} skills"
    open(p, 'w', encoding='utf-8', newline='').write(bom + nl.join(lines))
    print(f"patched {fname}: {hits} mage skills now visible on NPC sheets")

# ---------------------------------------------------------------------------
# 6) Data: Janod knows offensive spells; recruit gates become "party is full".
# ---------------------------------------------------------------------------
p = f'{w}/assets/data/rules/bestiary.txt'
raw = open(p, encoding='utf-8', newline='').read()
nl = '\r\n' if '\r\n' in raw else '\n'
lines = raw.split(nl)
hdr = lines[0].lstrip('﻿').split('\t')
ix = {c: i for i, c in enumerate(hdr)}
done = False
for i, ln in enumerate(lines):
    f = ln.split('\t')
    if f[0] == 'janod':
        # patch_companion_janod.py has already set this to the passive starter kit;
        # the wizard AI can only fire skills the NPC actually knows.
        f[ix['Skillset']] = '"wand_mastery#3;mana_surge#2;fireball#2;ice_storm#2"'
        lines[i] = '\t'.join(f)
        done = True
        break
assert done, "janod row not found in bestiary.txt"
open(p, 'w', encoding='utf-8', newline='').write(nl.join(lines))
print("patched bestiary.txt: janod Skillset += fireball, ice_storm")

p = f'{w}/smali/net/fdgames/GameLogic/ScriptedAction.smali'
s = open(p, encoding='utf-8').read()
anchor = ('    invoke-virtual {v2, v3}, Lnet/fdgames/GameEntities/CharacterSheet/'
          'CharacterSheet;->e(Ljava/lang/String;)V\n\n    :ekjanod_noupgrade\n')
assert s.count(anchor) == 1, "UpgradeCompanion#janod grant block not found"
extra = ''
for sk in ("lightning_bolt",):
    extra += ("    iget-object v2, v1, Lnet/fdgames/GameEntities/Character;->sheet:"
              "Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;\n\n"
              f'    const-string v3, "{sk}"\n\n'
              "    invoke-virtual {v2, v3}, Lnet/fdgames/GameEntities/CharacterSheet/"
              "CharacterSheet;->e(Ljava/lang/String;)V\n\n")
s = s.replace(anchor, anchor.replace('    :ekjanod_noupgrade\n', '')
              + extra + '    :ekjanod_noupgrade\n', 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched ScriptedAction: UpgradeCompanion#janod += lightning_bolt")

# Recruit gates. Vanilla refuses a 2nd companion on a row conditioned HasCompanion#;
# that becomes "party is full" = companion AND follower. Janod's own mod-added hooks
# use HasNoCompanion# -> HasNoFollower# for the same reason.
GATES = {
    'hirge.txt': ('HasCompanion#', '"HasCompanion#;HasFollower#"'),
    'adaon.txt': ('HasCompanion#', '"HasCompanion#;HasFollower#"'),
    'mercenary_grisenda.txt': ('HasCompanion#', '"HasCompanion#;HasFollower#"'),
}
for fname, (old, new) in GATES.items():
    p = f'{w}/assets/data/conversations/{fname}'
    raw = open(p, encoding='utf-8', newline='').read()
    n = 0
    out = []
    for ln in raw.split('\n'):
        f = ln.split('\t')
        if len(f) > 5 and f[5] == old:
            f[5] = new
            ln = '\t'.join(f)
            n += 1
        out.append(ln)
    assert n == 1, f"{fname}: expected 1 HasCompanion# row, found {n}"
    open(p, 'w', encoding='utf-8', newline='').write('\n'.join(out))
    print(f"patched {fname}: recruit refusal now means PARTY FULL")

p = f'{w}/assets/data/conversations/kingsbridge_wizard.txt'
raw = open(p, encoding='utf-8', newline='').read()
assert 'HasNoCompanion#' in raw, "janod recruit hooks not found"
n = raw.count('HasNoCompanion#')
raw = raw.replace('HasNoCompanion#', 'HasNoFollower#')
open(p, 'w', encoding='utf-8', newline='').write(raw)
print(f"patched kingsbridge_wizard.txt: {n} recruit gates -> HasNoFollower#")
print("DONE")
