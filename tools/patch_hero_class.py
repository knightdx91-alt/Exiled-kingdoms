#!/usr/bin/env python3
"""
Hero class mod: turn the WARRIOR slot into an unrestricted "Hero", with a
per-class paged skill screen. See deobf/HERO_CLASS_MOD_SPEC.md.

Reversed against the owner's 2023/4.2.2 base (obfuscation differs from the 2025 base):
  Rules$CharacterClass fields: b=WARRIOR(Hero) c=ROGUE d=CLERIC e=WIZARD
                               f=MONSTER g=GENERAL h=NONE
  Restriction choke point:     ClassRestriction->a(CharacterClass)Ljava/lang/Boolean;
                               (callers: Item [equipment], Skill x2 [basic+advanced/
                               trainers], Rules) -- one method gates all of them.
  Max mana:                    CharacterStats->g()I  (grants only WIZARD/CLERIC)
  Skill screen:                e/a/d/e/c0 (.source "SkillWindow.java"), builder c()V

Design note -- why a suppress flag rather than a plain bypass:
  Making a(WARRIOR) always true means Skills.a(WARRIOR) returns ALL skills, so there is
  no longer any way to ask for "just the warrior list" -- which the per-class pager needs.
  The bypass is therefore gated on a static flag that the pager turns ON only while it
  builds one page's list. Everywhere else (equipping, learning, trainers) the bypass is
  live, so the Hero is unrestricted.
"""
w = "."
CC = 'Lnet/fdgames/Rules/Rules$CharacterClass;'
CR = 'Lnet/fdgames/Rules/ClassRestriction;'
SW = 'Le/a/d/e/c0;'
TB = 'Lcom/badlogic/gdx/scenes/scene2d/ui/TextButton;'
SHEET = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'

# ---------------------------------------------------------------------------
# 1) ClassRestriction: add the suppress flag + the Hero bypass
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/Rules/ClassRestriction.smali'
s = open(p, encoding='utf-8').read()

fld = '.field private classes:Ljava/util/HashSet;'
assert s.count(fld) == 1, "ClassRestriction.classes field not found"
s = s.replace(fld, '.field public static ekSuppress:Z\n\n' + fld, 1)

sig = ('.method public a(Lnet/fdgames/Rules/Rules$CharacterClass;)Ljava/lang/Boolean;\n'
       '    .locals 1\n')
assert s.count(sig) == 1, "ClassRestriction.a(CharacterClass) not found"
bypass = (sig +
          f'\n    sget-boolean v0, {CR}->ekSuppress:Z\n\n'
          '    if-nez v0, :ekhero_normal\n\n'
          f'    sget-object v0, {CC}->b:{CC}\n\n'
          '    if-ne p1, v0, :ekhero_normal\n\n'
          '    sget-object p1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;\n\n'
          '    return-object p1\n\n'
          '    :ekhero_normal\n')
s = s.replace(sig, bypass, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched ClassRestriction: Hero bypass (equipment + all skills + trainers)")

# ---------------------------------------------------------------------------
# 2) CharacterStats.g()I : Hero gets caster mana (so spells are actually usable)
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterStats.smali'
s = open(p, encoding='utf-8').read()
ms = s.index('.method public g()I'); me = s.index('.end method', ms)
method = s[ms:me]

# tail of g(): ":cond_N  return v1" -- the non-caster exit. Give WARRIOR the caster branch.
import re
m = re.search(r'    :(cond_[0-9a-f]+)\n    return v1\n', method)
assert m, "CharacterStats.g() non-caster exit not found"
lbl = m.group(1)
# The caster branches converge on a join label whose NAME is disassembler-dependent
# (apktool :goto_2 vs baksmali :goto_1a), so capture it from the CLERIC branch instead
# of hardcoding it.
gm = re.search(r'    move-result v1\n\n    goto :(goto_[0-9a-f]+)\n', method)
assert gm, "CharacterStats.g() caster join label not found"
join = gm.group(1)
new_tail = (f'    :{lbl}\n'
            f'    sget-object v4, {CC}->b:{CC}\n\n'
            '    if-ne v3, v4, :ekhero_nomana\n\n'
            '    invoke-virtual {p0}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->e()I\n\n'
            '    move-result v1\n\n'
            f'    goto :{join}\n\n'
            '    :ekhero_nomana\n'
            '    return v1\n')
method = method[:m.start()] + new_tail + method[m.end():]
s = s[:ms] + method + s[me:]
open(p, 'w', encoding='utf-8').write(s)
print("patched CharacterStats.g(): Hero gets caster mana")

# ---------------------------------------------------------------------------
# 3) SkillWindow (e/a/d/e/c0): per-class pager
# ---------------------------------------------------------------------------
p = f'{w}/smali/e/a/d/e/c0.smali'
s = open(p, encoding='utf-8').read()

# 3a) fields
anchor = '.field private k:I'
assert s.count(anchor) == 1, "c0.k field not found"
s = s.replace(anchor, f'.field public ekPage:I\n\n.field private ekPageBtn:{TB}\n\n' + anchor, 1)

# 3b) constructor: build the pager button next to the existing TextButton
ctor_anchor = f'    iput-object v0, p0, {SW}->d:{TB}\n'
assert s.count(ctor_anchor) == 1, "c0 TextButton d assignment not found"
mkbtn = (ctor_anchor +
         f'\n    new-instance v0, {TB}\n\n'
         '    const-string v2, "HERO"\n\n'
         '    invoke-static {}, Lnet/fdgames/assets/Assets;->e()Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;\n\n'
         '    move-result-object v3\n\n'
         '    const-string v5, "menuSmallButton"\n\n'
         f'    invoke-direct {{v0, v2, v3, v5}}, {TB}-><init>(Ljava/lang/String;Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;Ljava/lang/String;)V\n\n'
         f'    iput-object v0, p0, {SW}->ekPageBtn:{TB}\n\n'
         '    new-instance v2, Le/a/d/e/c0$ekp;\n\n'
         f'    invoke-direct {{v2, p0}}, Le/a/d/e/c0$ekp;-><init>({SW})V\n\n'
         '    invoke-virtual {v0, v2}, Lcom/badlogic/gdx/scenes/scene2d/Actor;->addListener(Lcom/badlogic/gdx/scenes/scene2d/EventListener;)Z\n')
s = s.replace(ctor_anchor, mkbtn, 1)

# 3c) c(): source the class-skill list from the pager instead of sheet.n()
src = (f'    iget-object v0, p0, {SW}->j:{SHEET}\n\n'
       f'    invoke-virtual {{v0}}, {SHEET}->n()Lnet/fdgames/Rules/Rules$CharacterClass;\n\n'
       '    move-result-object v0\n\n'
       '    invoke-static {v0}, Lnet/fdgames/Rules/Skills;->a(Lnet/fdgames/Rules/Rules$CharacterClass;)Ljava/util/ArrayList;\n\n'
       '    move-result-object v1\n')
assert s.count(src) == 1, "c() class-skill source not unique (expected exactly 1)"
s = s.replace(src,
              f'    iget-object v0, p0, {SW}->j:{SHEET}\n\n'
              f'    iget v1, p0, {SW}->ekPage:I\n\n'
              f'    invoke-static {{v0, v1}}, {SW}->ekClassSkills({SHEET}I)Ljava/util/ArrayList;\n\n'
              '    move-result-object v1\n', 1)

# 3d) c(): add the pager button row (Hero only), right after the table pad().
#     v2 (2026-07-27): the v1 inline block created a branch inside c() whose two
#     paths reached the join with DIFFERENT reference types in v0/v1
#     (Table/TextButton vs CharacterClass). ART/D8 accept that (dead conflict
#     regs), but the device's Dalvik 4.2.2 verifier rejected the class:
#     "java.lang.VerifyError: e/a/d/e/c0" at MainMenuScreen.<init> (owner's
#     EK_crash.txt). Fix: c() gets a single straight-line invoke -- all
#     branching lives in a new method with its own frame, and even there both
#     paths leave identical register types at every join.
pad = ('    invoke-virtual {v0, v1}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->pad(F)'
       'Lcom/badlogic/gdx/scenes/scene2d/ui/Table;\n')
i = s.index('.method private c()V')
j = s.index(pad, i) + len(pad)
s = s[:j] + f'\n    invoke-virtual {{p0}}, {SW}->ekMaybeAddPagerRow()V\n' + s[j:]

# 3e) helper methods
helpers = f'''
.method public ekMaybeAddPagerRow()V
    .locals 2

    iget-object v0, p0, {SW}->j:{SHEET}

    invoke-virtual {{v0}}, {SHEET}->n()Lnet/fdgames/Rules/Rules$CharacterClass;

    move-result-object v0

    sget-object v1, {CC}->b:{CC}

    if-ne v0, v1, :ekp_nobutton

    iget v0, p0, {SW}->ekPage:I

    invoke-static {{v0}}, {SW}->ekPageName(I)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, {SW}->ekPageBtn:{TB}

    invoke-virtual {{v1, v0}}, {TB}->setText(Ljava/lang/String;)V

    iget-object v0, p0, {SW}->l:Lcom/badlogic/gdx/scenes/scene2d/ui/Table;

    iget-object v1, p0, {SW}->ekPageBtn:{TB}

    invoke-virtual {{v0, v1}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->add(Lcom/badlogic/gdx/scenes/scene2d/Actor;)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    iget-object v0, p0, {SW}->l:Lcom/badlogic/gdx/scenes/scene2d/ui/Table;

    invoke-virtual {{v0}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->row()Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    sget-object v0, {CC}->b:{CC}

    sget-object v1, {CC}->b:{CC}

    :ekp_nobutton
    return-void
.end method

.method public static ekPageName(I)Ljava/lang/String;
    .locals 1

    const/4 v0, 0x1

    if-ne p0, v0, :ekn2

    const-string p0, "ROGUE"

    return-object p0

    :ekn2
    const/4 v0, 0x2

    if-ne p0, v0, :ekn3

    const-string p0, "CLERIC"

    return-object p0

    :ekn3
    const/4 v0, 0x3

    if-ne p0, v0, :ekn0

    const-string p0, "MAGE"

    return-object p0

    :ekn0
    const-string p0, "HERO"

    return-object p0
.end method

.method public static ekClassSkills({SHEET}I)Ljava/util/ArrayList;
    .locals 3

    invoke-virtual {{p0}}, {SHEET}->n()Lnet/fdgames/Rules/Rules$CharacterClass;

    move-result-object v0

    sget-object v1, {CC}->b:{CC}

    if-ne v0, v1, :ekcs_plain

    const/4 v2, 0x1

    if-ne p1, v2, :ekcs_p2

    sget-object v0, {CC}->c:{CC}

    goto :ekcs_have

    :ekcs_p2
    const/4 v2, 0x2

    if-ne p1, v2, :ekcs_p3

    sget-object v0, {CC}->d:{CC}

    goto :ekcs_have

    :ekcs_p3
    const/4 v2, 0x3

    if-ne p1, v2, :ekcs_have

    sget-object v0, {CC}->e:{CC}

    :ekcs_have
    const/4 v2, 0x1

    sput-boolean v2, {CR}->ekSuppress:Z

    invoke-static {{v0}}, Lnet/fdgames/Rules/Skills;->a(Lnet/fdgames/Rules/Rules$CharacterClass;)Ljava/util/ArrayList;

    move-result-object v0

    const/4 v2, 0x0

    sput-boolean v2, {CR}->ekSuppress:Z

    return-object v0

    :ekcs_plain
    invoke-static {{v0}}, Lnet/fdgames/Rules/Skills;->a(Lnet/fdgames/Rules/Rules$CharacterClass;)Ljava/util/ArrayList;

    move-result-object v0

    return-object v0
.end method

.method public ekNextPage()V
    .locals 2

    iget v0, p0, {SW}->ekPage:I

    add-int/lit8 v0, v0, 0x1

    const/4 v1, 0x4

    rem-int/2addr v0, v1

    iput v0, p0, {SW}->ekPage:I

    invoke-direct {{p0}}, {SW}->c()V

    return-void
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + helpers + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched SkillWindow: per-class pager (HERO/ROGUE/CLERIC/MAGE)")

# 3f) the pager button's listener (mirrors the existing c0$d InputListener)
open(f'{w}/smali/e/a/d/e/c0$ekp.smali', 'w', encoding='utf-8').write(f'''.class Le/a/d/e/c0$ekp;
.super Lcom/badlogic/gdx/scenes/scene2d/InputListener;
.source "SkillWindow.java"


# instance fields
.field final synthetic a:{SW}


# direct methods
.method constructor <init>({SW})V
    .locals 0

    iput-object p1, p0, Le/a/d/e/c0$ekp;->a:{SW}

    invoke-direct {{p0}}, Lcom/badlogic/gdx/scenes/scene2d/InputListener;-><init>()V

    return-void
.end method


# virtual methods
.method public touchDown(Lcom/badlogic/gdx/scenes/scene2d/InputEvent;FFII)Z
    .locals 0

    iget-object p1, p0, Le/a/d/e/c0$ekp;->a:{SW}

    invoke-virtual {{p1}}, {SW}->ekNextPage()V

    const/4 p1, 0x1

    return p1
.end method
''')
print("added listener class e/a/d/e/c0$ekp")

# ---------------------------------------------------------------------------
# 4) strings.txt: WARRIOR -> Hero (English column only; CRLF/BOM preserved)
# ---------------------------------------------------------------------------
p = f'{w}/assets/data/ui/strings/strings.txt'
raw = open(p, encoding='utf-8', newline='').read()
bom = '﻿' if raw.startswith('﻿') else ''
body = raw[len(bom):]
nl = '\r\n' if '\r\n' in body else '\n'
lines = body.split(nl)
hit = 0
for i, ln in enumerate(lines):
    f = ln.split('\t')
    if f and f[0] == 'WARRIOR':
        f[1] = 'Hero'
        lines[i] = '\t'.join(f)
        hit += 1
assert hit == 1, f"expected 1 WARRIOR row, found {hit}"
open(p, 'w', encoding='utf-8', newline='').write(bom + nl.join(lines))
print("patched strings.txt: WARRIOR -> Hero")
print("DONE")
