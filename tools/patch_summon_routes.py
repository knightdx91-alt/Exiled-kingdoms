#!/usr/bin/env python3
"""
Summon Familiar -> three routes, chosen the first time you buy the skill.
Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).
Reversing behind every edit: deobf/SUMMON_ROUTES_SPEC.md.

No new skill entries, no new tree buttons (the mage basic page is capped at 8 slots
and already holds exactly 8): the ONE existing `lesser_summoning` skill keeps its slot,
and the route is a saved game variable. First purchase pops a 3-way choice dialog;
every later click just ranks the same skill up. The route + rank together pick the
bestiary id, so ranks 3-4 stay meaningful at high level.
"""
import re

w = "."
C0 = 'Le/a/d/e/c0;'
CHAR = 'Lnet/fdgames/GameEntities/Character;'
CS = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
SKS = 'Lnet/fdgames/GameEntities/Helpers/SkillSet;'
SKILL = 'Lnet/fdgames/Rules/Skill;'
SKA = 'Lnet/fdgames/Rules/SkillActions;'
GD = 'Lnet/fdgames/GameWorld/GameData;'
GV = 'Lnet/fdgames/GameWorld/GameVariables;'
DIALOG = 'Lcom/badlogic/gdx/scenes/scene2d/ui/Dialog;'
TABLE = 'Lcom/badlogic/gdx/scenes/scene2d/ui/Table;'
SKIN = 'Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;'
UBTN = 'Le/a/d/u;'
L1 = 'Le/a/d/l1;'
ESP = 'Le/a/d/e/eksp;'
SKILL_ID = "lesser_summoning"
VAR = "summon_path"

# route -> per-rank (bestiary id, level cap). SkillActions caps the summon at
# min(cap, casterLevel) + summoner-rank, so a high cap just means "as good as your
# own level allows". Rank 1-2 are the vanilla power band; 3-4 are the high-end tiers
# the owner asked for, so the skill stays worth points late.
ROUTES = {
    1: ("Necromancer", [("skeleton", 5), ("skeleton_warrior", 8),
                        ("skeleton_champion", 11), ("skeleton_hero", 14)]),
    # NOT elementals: Fire/Ice/Earth Mastery (bought from the mage-tower trainers)
    # already summon fire_elemental_1/2/3, ice_elemental_1/2/3 and
    # elemental_earth_lesser/elemental_earth/golem_iron_1. The arcane route escalates
    # into CONSTRUCTS instead (capstone: the acid elemental, whose sprite is golem_green,
    # so it reads as the next construct up) -- classic conjuration, no overlap with any trainer
    # skill, and the tankiest rank-4 of the three (its rank 1-2 familiars are the
    # weakest opening, so it pays off latest and hardest).
    2: ("Arcane", [("familiar1", 3), ("familiar2", 6),
                   ("golem_iron_lesser", 11), ("elemental_acid", 14)]),
    # NOT dire_wolf / spirit_wolf: those are ranks 1 and 3 of the cleric skill
    # Guardian Wolf (dire_wolf -> white_wolf -> spirit_wolf, in e/a/d/m1). A pure mage
    # can't take that skill, but the Hero class in this mod ignores class restrictions,
    # so a Hero would have been buying the same wolves twice.
    3: ("Beast", [("grey_wolf", 5), ("wolf", 8),
                  ("bear_summoned", 11), ("wild_werewolf", 14)]),
}
DEFAULT_ROUTE = 2          # unset variable behaves exactly like vanilla

# ---------------------------------------------------------------------------
# 1) SkillActions.ekSummonId(Character)String / ekSummonCap(Character)I
#
#    Both read the caster's own rank and the saved route, so the call sites in
#    Character's dispatch stay single instructions and no register has to survive
#    across the branch (the rank register there is clobbered by the level lookup).
# ---------------------------------------------------------------------------
def _lookup(kind):
    """if-chain over (route, rank); kind 'id' -> const-string, 'cap' -> const int."""
    out = ''
    for route, (_name, ladder) in ROUTES.items():
        lbl = f'ekr_{route}'
        out += f'    const/4 v2, 0x{route:x}\n\n    if-ne v1, v2, :{lbl}\n\n'
        for rank, (sid, cap) in enumerate(ladder, start=1):
            last = rank == len(ladder)
            rlbl = f'ekr_{route}_{rank}'
            if not last:
                out += f'    const/4 v2, 0x{rank:x}\n\n    if-gt v0, v2, :{rlbl}\n\n'
            if kind == 'id':
                out += f'    const-string v0, "{sid}"\n\n    return-object v0\n\n'
            else:
                out += f'    const/16 v0, 0x{cap:x}\n\n    return v0\n\n'
            if not last:
                out += f'    :{rlbl}\n'
        out += f'    :{lbl}\n'
    # fallback: vanilla familiar / vanilla cap
    sid, cap = ROUTES[DEFAULT_ROUTE][1][0]
    out += (f'    const-string v0, "{sid}"\n\n    return-object v0\n'
            if kind == 'id' else
            f'    const/16 v0, 0x{cap:x}\n\n    return v0\n')
    return out


def _preamble():
    return f'''    iget-object v0, p0, {CHAR}->sheet:{CS}

    iget-object v0, v0, {CS}->skillSet:{SKS}

    const-string v1, "{SKILL_ID}"

    invoke-virtual {{v0, v1}}, {SKS}->c(Ljava/lang/String;)I

    move-result v0

    invoke-static {{}}, {GD}->O(){GD}

    move-result-object v1

    iget-object v1, v1, {GD}->gameVariables:{GV}

    const-string v2, "{VAR}"

    invoke-virtual {{v1, v2}}, {GV}->b(Ljava/lang/String;)I

    move-result v1

'''


helpers = f'''
.method public static ekSummonId({CHAR})Ljava/lang/String;
    .locals 3

    if-eqz p0, :ekid_default

    iget-object v0, p0, {CHAR}->sheet:{CS}

    if-eqz v0, :ekid_default

{_preamble()}{_lookup('id')}
    :ekid_default
    const-string v0, "{ROUTES[DEFAULT_ROUTE][1][0][0]}"

    return-object v0
.end method

.method public static ekSummonCap({CHAR})I
    .locals 3

    if-eqz p0, :ekcap_default

    iget-object v0, p0, {CHAR}->sheet:{CS}

    if-eqz v0, :ekcap_default

{_preamble()}{_lookup('cap')}
    :ekcap_default
    const/16 v0, 0x{ROUTES[DEFAULT_ROUTE][1][0][1]:x}

    return v0
.end method
'''
p = f'{w}/smali/net/fdgames/Rules/SkillActions.smali'
s = open(p, encoding='utf-8').read()
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + helpers + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched SkillActions: +ekSummonId/+ekSummonCap (%d routes x %d ranks)"
      % (len(ROUTES), len(ROUTES[1][1])))

# ---------------------------------------------------------------------------
# 2) Character's lesser_summoning dispatch: creature + cap come from the route.
#    Rank 1 keeps its own (vanilla) cap logic; the rank-2 test is widened from
#    `== 2` to `>= 2` so ranks 3 and 4 reach the same call with a route cap.
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/Character.smali'
s = open(p, encoding='utf-8').read()

# -- FIRST: widen the rank-2 equality test to >=, so ranks 3 and 4 reach that branch
#    with the route's own cap. The test is the single `if-ne` lying strictly between
#    the two call sites; anchoring on "the last if-ne before the id swap" is WRONG --
#    that is the rank-1 test, and widening it makes rank 2 summon twice and ranks 3-4
#    summon a high-tier creature at the rank-1 level cap.
i1, i2 = s.index('"familiar1"'), s.index('"familiar2"')
between = list(re.finditer(r'    if-ne (v\d+), (v\d+), :(cond_[0-9a-f]+)\n', s[i1:i2]))
assert len(between) == 1, f"expected exactly 1 rank test between the sites, got {len(between)}"
mt = between[0]
s = (s[:i1 + mt.start()]
     + f'    if-lt {mt.group(1)}, {mt.group(2)}, :{mt.group(3)}\n'
     + s[i1 + mt.end():])

# -- rank 1 site: creature and cap both come from the route
m1 = re.search(r'    const-string (v\d+), "familiar1"\n\n'
               r'    invoke-static \{(v\d+), \1, (v\d+), (v\d+)\}, '
               + re.escape(SKA) + r'->a\(' + re.escape(CHAR)
               + r'Ljava/lang/String;II\)V\n', s)
assert m1, "familiar1 call site not found"
id1, caster1, cap1, dur1 = m1.group(1), m1.group(2), m1.group(3), m1.group(4)
s = (s[:m1.start()]
     + f'    invoke-static {{{caster1}}}, {SKA}->ekSummonId({CHAR})Ljava/lang/String;\n\n'
     + f'    move-result-object {id1}\n\n'
     + f'    invoke-static {{{caster1}}}, {SKA}->ekSummonCap({CHAR})I\n\n'
     + f'    move-result {cap1}\n\n'
     + f'    invoke-static {{{caster1}, {id1}, {cap1}, {dur1}}}, {SKA}->a({CHAR}'
       'Ljava/lang/String;II)V\n'
     + s[m1.end():])

# -- rank 2+ site: swap the id, then the cap (the register passed as arg 3)
m2 = re.search(r'    const-string (v\d+), "familiar2"\n\n'
               r'    invoke-static \{(v\d+), \2?(v\d+)?, ?(v\d+), (v\d+)\}, '
               + re.escape(SKA) + r'->a\(' + re.escape(CHAR)
               + r'Ljava/lang/String;II\)V\n', s)
if not m2:
    m2 = re.search(r'    const-string (v\d+), "familiar2"\n\n'
                   r'    invoke-static \{(v\d+), (v\d+), (v\d+), (v\d+)\}, '
                   + re.escape(SKA) + r'->a\(' + re.escape(CHAR)
                   + r'Ljava/lang/String;II\)V\n', s)
assert m2, "familiar2 call site not found"
id_reg, caster_reg, cap_reg = m2.group(1), m2.group(2), m2.group(4)
s = (s[:m2.start()]
     + f'    invoke-static {{{caster_reg}}}, {SKA}->ekSummonId({CHAR})Ljava/lang/String;\n\n'
     + f'    move-result-object {id_reg}\n\n'
     + f'    invoke-static {{{caster_reg}}}, {SKA}->ekSummonCap({CHAR})I\n\n'
     + f'    move-result {cap_reg}\n\n'
     + m2.group(0).split('\n\n', 1)[1] + '\n'
     + s[m2.end():])
open(p, 'w', encoding='utf-8').write(s)
print("patched Character: lesser_summoning -> route creature/cap, ranks 3-4 enabled")

# ---------------------------------------------------------------------------
# 3) SkillWindow: first purchase asks which route.
#    c0.e(c0) is the buy handler; at the requisites-OK label we divert into a
#    dialog when the skill is lesser_summoning, the sheet is the PLAYER's, the
#    skill is still at rank 0 and no route has been saved yet. The dialog's
#    result() saves the route and re-enters e(c0), so the real purchase (and the
#    window's own refresh) is the untouched vanilla path.
# ---------------------------------------------------------------------------
p = f'{w}/smali/e/a/d/e/c0.smali'
s = open(p, encoding='utf-8').read()
ms = s.index('.method static synthetic e(Le/a/d/e/c0;)V')
me = s.index('.end method', ms)
method = s[ms:me]

stage = re.search(r'    iget-object p0, p0, ' + re.escape(C0)
                  + r'->(\w+):Lcom/badlogic/gdx/scenes/scene2d/Stage;\n', method)
assert stage, "c0 Stage field not found in e()"
stage_fld = stage.group(1)
ret = re.search(r'    goto :(goto_[0-9a-f]+)\n', method)
assert ret, "c0.e() early-exit label not found"
ret_lbl = ret.group(1)
ok = re.search(r'\n    :(cond_[0-9a-f]+)\n(    iget-object v0, p0, ' + re.escape(C0)
               + r'->\w+:' + re.escape(CS) + r'\n)', method)
assert ok, "c0.e() requisites-OK block not found"
method = (method[:ok.start()]
          + f'\n    :{ok.group(1)}\n'
          + f'    invoke-static {{p0}}, {C0}->ekNeedsSummonRoute({C0})Z\n\n'
          + '    move-result v0\n\n'
          + '    if-eqz v0, :ekbuy_normal\n\n'
          + f'    new-instance v0, {ESP}\n\n'
          + f'    invoke-direct {{v0, p0}}, {ESP}-><init>({C0})V\n\n'
          + f'    iget-object p0, p0, {C0}->{stage_fld}:'
            'Lcom/badlogic/gdx/scenes/scene2d/Stage;\n\n'
          + f'    invoke-virtual {{v0, p0}}, {DIALOG}->show'
            f'(Lcom/badlogic/gdx/scenes/scene2d/Stage;){DIALOG}\n\n'
          + f'    goto :{ret_lbl}\n\n'
          + '    :ekbuy_normal\n'
          + ok.group(2)
          + method[ok.end():])
s = s[:ms] + method + s[me:]

skill_fld = re.search(r'iget-object v0, p0, ' + re.escape(C0) + r'->(\w+):'
                      + re.escape(SKILL) + r'\n', s).group(1)
sheet_fld = re.search(r'iget-object v0, p0, ' + re.escape(C0) + r'->(\w+):'
                      + re.escape(CS) + r'\n', s).group(1)
need = f'''
.method public static ekNeedsSummonRoute({C0})Z
    .locals 3

    const/4 v0, 0x0

    if-eqz p0, :ekn_no

    iget-object v1, p0, {C0}->{skill_fld}:{SKILL}

    if-eqz v1, :ekn_no

    iget-object v1, v1, {SKILL}->id:Ljava/lang/String;

    const-string v2, "{SKILL_ID}"

    invoke-virtual {{v1, v2}}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :ekn_no

    iget-object v1, p0, {C0}->{sheet_fld}:{CS}

    if-eqz v1, :ekn_no

    invoke-virtual {{v1}}, {CS}->W()Z

    move-result v2

    if-eqz v2, :ekn_no

    invoke-static {{}}, {GD}->O(){GD}

    move-result-object v1

    iget-object v1, v1, {GD}->gameVariables:{GV}

    const-string v2, "{VAR}"

    invoke-virtual {{v1, v2}}, {GV}->b(Ljava/lang/String;)I

    move-result v1

    if-gtz v1, :ekn_no

    const/4 v0, 0x1

    :ekn_no
    return v0
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + need + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched SkillWindow: first lesser_summoning purchase asks for a route")

# ---------------------------------------------------------------------------
# 4) The choice dialog. Extends the game's own SimpleDialog (e/a/d/l1) so it looks
#    native; l1 ships an OK button, which is cleared and replaced with one button
#    per route. Dialog.button(Button,Object) + result(Object) is stock scene2d.
# ---------------------------------------------------------------------------
PROMPT = ("[BLACK]How will you call your ally?[]  This choice is permanent.\\n\\n"
          "UNDEAD - raise skeletons that grow into champions and heroes.\\n"
          "ARCANE - familiars from the Plane of Energy, then conjured golems.\\n"
          "BEAST - wolves, bears, and at last a wild werewolf.")

buttons = ''
for route, (name, _ladder) in ROUTES.items():
    label = {1: "UNDEAD", 2: "ARCANE", 3: "BEAST"}[route]
    buttons += f'''    new-instance v1, {UBTN}

    invoke-static {{}}, Lnet/fdgames/assets/Assets;->e(){SKIN}

    move-result-object v2

    const-string v3, "[WHITE]{label}[]"

    const-string v4, "menuButton"

    invoke-direct {{v1, v3, v2, v4}}, {UBTN}-><init>(Ljava/lang/String;{SKIN}Ljava/lang/String;)V

    const/4 v2, 0x{route:x}

    invoke-static {{v2}}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {{p0, v1, v2}}, {DIALOG}->button(Lcom/badlogic/gdx/scenes/scene2d/ui/Button;Ljava/lang/Object;){DIALOG}

'''

open(f'{w}/smali/e/a/d/e/eksp.smali', 'w', encoding='utf-8').write(f'''.class public {ESP}
.super {L1}
.source "SkillWindow.java"


# instance fields
.field final synthetic a:{C0}


# direct methods
.method public constructor <init>({C0})V
    .locals 5

    const-string v0, "{PROMPT}"

    const/high16 v1, 0x3f800000

    invoke-direct {{p0, v0, v1}}, {L1}-><init>(Ljava/lang/String;F)V

    iput-object p1, p0, {ESP}->a:{C0}

    invoke-virtual {{p0}}, {DIALOG}->getButtonTable(){TABLE}

    move-result-object v0

    invoke-virtual {{v0}}, {TABLE}->clearChildren()V

{buttons}    return-void
.end method


# virtual methods
.method protected result(Ljava/lang/Object;)V
    .locals 3

    instance-of v0, p1, Ljava/lang/Integer;

    if-nez v0, :eksp_ok

    return-void

    :eksp_ok
    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {{p1}}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-static {{}}, {GD}->O(){GD}

    move-result-object v0

    iget-object v0, v0, {GD}->gameVariables:{GV}

    const-string v1, "{VAR}"

    invoke-virtual {{v0, v1, p1}}, {GV}->b(Ljava/lang/String;I)Z

    iget-object v0, p0, {ESP}->a:{C0}

    if-eqz v0, :eksp_done

    invoke-static {{v0}}, {C0}->e({C0})V

    :eksp_done
    return-void
.end method
''')
print("added route-choice dialog class e/a/d/e/eksp")

# ---------------------------------------------------------------------------
# 5) skills2.txt: Lesser Summoning gains ranks 3 and 4, and says what it now does.
# ---------------------------------------------------------------------------
p = f'{w}/assets/data/rules/skills2.txt'
raw = open(p, encoding='utf-8', newline='').read()
bom = '﻿' if raw.startswith('﻿') else ''
body = raw[len(bom):]
nl = '\r\n' if '\r\n' in body else '\n'
lines = body.split(nl)

start = next(i for i, ln in enumerate(lines) if ln.split('\t')[0] == 'Lesser Summoning')
ncols = len(lines[0].split('\t'))
end = start + 1
while end < len(lines) and lines[end].split('\t')[0] == '':
    end += 1
assert end - start == 3, f"expected 2 existing ranks, found {end - start - 1}"


def rank_row(desc, cost, cooldown, mana, desc_es):
    f = [''] * ncols
    f[5], f[6], f[7], f[8] = desc, str(cost), str(cooldown), str(mana)
    f[-1] = desc_es
    return '\t'.join(f)


lines[start + 1:end] = [
    rank_row("Summon your ally: a Level 3-5 Skeleton, a Lesser Sparkling, "
             "or a Grey Wolf, depending on the path you chose.", 1, 30, 12,
             "Invoca a tu aliado, segun el camino que elegiste."),
    rank_row("A Level 6-8 Skeleton Warrior, a Level 4-6 Sparkling, "
             "or a Wolf.", 2, 30, 20,
             "Un aliado mas poderoso, segun tu camino."),
    rank_row("A Level 10-11 Skeleton Champion, a Lesser Iron Golem, "
             "or a Summoned Bear.", 3, 30, 28,
             "Un aliado de alto nivel, segun tu camino."),
    rank_row("A Level 13-14 Skeleton Hero, an Acid Elemental, "
             "or a Wild Werewolf.", 3, 30, 36,
             "El aliado mas poderoso de tu camino."),
]

hdr_fields = lines[start].split('\t')
hdr_fields[5] = ("Call an ally to fight for you. The FIRST time you learn this skill "
                 "you choose your path -- Undead, Arcane or Beast -- and every rank "
                 "after that summons a stronger ally of that path.")
lines[start] = '\t'.join(hdr_fields)

open(p, 'w', encoding='utf-8', newline='').write(bom + nl.join(lines))
print("patched skills2.txt: Lesser Summoning now has 4 ranks")
print("DONE")
