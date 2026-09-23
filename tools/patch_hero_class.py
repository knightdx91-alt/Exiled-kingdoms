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
SKILL_ = 'Lnet/fdgames/Rules/Skill;'

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
# The caster join is `mul v1,v1,v0 ; add v1,v2` where v0 = k()?2:0 (0 for a non-monster
# player) and v2 = race==HUMAN?12:0. Leaving v0=0 makes the Hero pool a flat 12; force
# v0=2 so it scales as level*2 + 12, the same pool the mod gives race-NPC casters.
new_tail = (f'    :{lbl}\n'
            f'    sget-object v4, {CC}->b:{CC}\n\n'
            '    if-ne v3, v4, :ekhero_nomana\n\n'
            '    invoke-virtual {p0}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->e()I\n\n'
            '    move-result v1\n\n'
            '    const/4 v0, 0x2\n\n'
            f'    goto :{join}\n\n'
            '    :ekhero_nomana\n'
            '    return v1\n')
method = method[:m.start()] + new_tail + method[m.end():]
s = s[:ms] + method + s[me:]
open(p, 'w', encoding='utf-8').write(s)
print("patched CharacterStats.g(): Hero gets caster mana")

# ---------------------------------------------------------------------------
# 2b) CharacterSheet.V()Z : the mana-pool gate. C() (max mana) returns 0 unless V()
#     is true, and V() is true only for WIZARD(e)/CLERIC(d). Without this the Hero's
#     whole pool is 0 and the g() grant above is dead code. Add WARRIOR(b) to V().
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterSheet.smali'
s = open(p, encoding='utf-8').read()
vsig = '.method public V()Z\n    .locals 2\n'
assert s.count(vsig) == 1, "CharacterSheet.V() not found"
vpre = (vsig +
        '\n    invoke-virtual {p0}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
        '->ekIsHero()Z\n\n'
        '    move-result v0\n\n'
        '    if-eqz v0, :ekhero_notwarrior\n\n'
        '    const/4 v0, 0x1\n\n'
        '    return v0\n\n'
        '    :ekhero_notwarrior\n')
s = s.replace(vsig, vpre, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched CharacterSheet.V(): Hero (WARRIOR) has a mana pool")

# ---------------------------------------------------------------------------
# 2d) CharacterSheet.C()I : trait mana. Vanilla adds level * (INT + 2 + PER/2) for
#     WIZARD(e) and level * (PER + 2 + INT/2) for CLERIC(d); every other class gets 0,
#     so the Hero's INT/PER did nothing and Mana Surge was the only way to grow the
#     pool (owner report, v21). The Hero now gets the better of the two formulas:
#     level * (max(INT,PER) + 2 + min(INT,PER)/2). Inserted at the join after the
#     cleric branch, where (for a non-cleric) v0 = INT (trait 3) and v2 = PER (trait 5)
#     are still intact and v3 is the trait-mana term. v6 must be 2 again afterwards
#     (the mana_surge ladder compares against it); v5 is dead past this point.
# ---------------------------------------------------------------------------
s = open(p, encoding='utf-8').read()
canchor = ('    iget-object v0, p0, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
           '->skillSet:Lnet/fdgames/GameEntities/Helpers/SkillSet;\n\n'
           '    const-string v2, "mana_surge"\n')
assert s.count(canchor) == 1, "CharacterSheet.C() mana_surge anchor not found"
cstart = s.index('.method public C()I\n')
assert cstart < s.index(canchor) < s.index('.end method', cstart), "anchor not inside C()"
chero = ('    invoke-virtual {p0}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
         '->ekIsHero()Z\n\n'
         '    move-result v5\n\n'
         '    if-eqz v5, :ekhero_trait_mana_done\n\n'
         '    if-ge v0, v2, :ekhero_trait_mana_sorted\n\n'
         '    move v5, v0\n\n'
         '    move v0, v2\n\n'
         '    move v2, v5\n\n'
         '    :ekhero_trait_mana_sorted\n'
         '    div-int/lit8 v2, v2, 0x2\n\n'
         '    add-int/lit8 v0, v0, 0x2\n\n'
         '    add-int/2addr v0, v2\n\n'
         '    invoke-virtual {p0}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->z()I\n\n'
         '    move-result v2\n\n'
         '    mul-int v3, v2, v0\n\n'
         '    :ekhero_trait_mana_done\n'
         '    const/4 v5, 0x0\n\n'
         '    const/4 v6, 0x2\n\n')
s = s.replace(canchor, chero + canchor, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched CharacterSheet.C(): Hero mana scales with INT and PER")

# ---------------------------------------------------------------------------
# 2c) Character.s0()Z : the "show the mana bar" gate (WIZARD/CLERIC only). Both the
#     HUD (e/a/d/y) and the character screen (e/a/d/e/h) toggle the mana bar/number on
#     s0(); without this the Hero has mana but no bar. Add WARRIOR(b), like V().
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/Character.smali'
s = open(p, encoding='utf-8').read()
s0sig = '.method public s0()Z\n    .locals 2\n'
assert s.count(s0sig) == 1, "Character.s0() not found"
s0pre = (s0sig +
         '\n    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->sheet:'
         f'{SHEET}\n\n'
         f'    invoke-virtual {{v0}}, {SHEET}->ekIsHero()Z\n\n'
         '    move-result v0\n\n'
         '    if-eqz v0, :ekhero_nos0\n\n'
         '    const/4 v0, 0x1\n\n'
         '    return v0\n\n'
         '    :ekhero_nos0\n')
s = s.replace(s0sig, s0pre, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched Character.s0(): Hero shows the mana bar (HUD + character screen)")

# ---------------------------------------------------------------------------
# 5) SkillWindow Details (c0.b): guard against a null selected skill.
#     b() passes c0->s (the tapped skill) to SkillInfoWindow.a(sheet,skill), which
#     dereferences it. s is null until a skill row is tapped (and the pager rebuild
#     never selects a default), so clicking Details first -> NPE. No-op when null.
# ---------------------------------------------------------------------------
p = f'{w}/smali/e/a/d/e/c0.smali'
s = open(p, encoding='utf-8').read()
bsig = '.method static synthetic b(Le/a/d/e/c0;)V\n    .locals 2\n'
assert s.count(bsig) == 1, "c0.b() Details handler not found"
guard = (bsig +
         f'\n    iget-object v0, p0, {SW}->s:{SKILL_}\n\n'
         '    if-nez v0, :ekdetails_ok\n\n'
         '    return-void\n\n'
         '    :ekdetails_ok\n')
s = s.replace(bsig, guard, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched SkillWindow.b(): Details no-ops when no skill is selected (was NPE)")

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
#     v3 (2026-07-27): THE crash was here, and the owner's logcat named it exactly:
#       VFY: CharacterSheet is not instance of Rules$CharacterClass
#       VFY: rejecting call to Rules$CharacterClass.a(Rules$CharacterClass)String
#       VFY: rejected Le/a/d/e/c0;.c()V  ->  VerifyError e/a/d/e/c0
#     The original block leaves v0 = sheet.n() (a CharacterClass) and c() REUSES
#     v0 much later (if-ne v0 vs CharacterClass->g, and CharacterClass.a(v0) for
#     the section headers). The v2 replacement left v0 holding the CharacterSheet.
#     Fix: replacement is straight-line and restores the original register types:
#     v0 = the page's effective CharacterClass, v1 = its skill list. Bonus: the
#     section headers now name the paged class (ROGUE/CLERIC/MAGE) on Hero pages.
src = (f'    iget-object v0, p0, {SW}->j:{SHEET}\n\n'
       f'    invoke-virtual {{v0}}, {SHEET}->n()Lnet/fdgames/Rules/Rules$CharacterClass;\n\n'
       '    move-result-object v0\n\n'
       '    invoke-static {v0}, Lnet/fdgames/Rules/Skills;->a(Lnet/fdgames/Rules/Rules$CharacterClass;)Ljava/util/ArrayList;\n\n'
       '    move-result-object v1\n')
assert s.count(src) == 1, "c() class-skill source not unique (expected exactly 1)"
s = s.replace(src,
              f'    iget-object v0, p0, {SW}->j:{SHEET}\n\n'
              f'    iget v1, p0, {SW}->ekPage:I\n\n'
              f'    invoke-static {{v0, v1}}, {SW}->ekPageClass({SHEET}I){CC}\n\n'
              '    move-result-object v0\n\n'
              f'    invoke-static {{v0}}, {SW}->ekSkillsSuppressed({CC})Ljava/util/ArrayList;\n\n'
              '    move-result-object v1\n', 1)

# 3d) c(): add the pager button row (Hero only), right after the table pad().
#     v4 (2026-09-23, owner: "the button that switches skill pages is small"): text scaled by the
#     window's c0.u and the cell sized 160x40 x c0.t, like the window's own Details button.
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
    .locals 4

    iget-object v0, p0, {SW}->j:{SHEET}

    invoke-virtual {{v0}}, {SHEET}->ekIsHero()Z

    move-result v0

    if-eqz v0, :ekp_nobutton

    iget v0, p0, {SW}->ekPage:I

    invoke-static {{v0}}, {SW}->ekPageName(I)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, {SW}->ekPageBtn:{TB}

    invoke-virtual {{v1, v0}}, {TB}->setText(Ljava/lang/String;)V

    invoke-virtual {{v1}}, {TB}->getLabel()Lcom/badlogic/gdx/scenes/scene2d/ui/Label;

    move-result-object v2

    sget v3, {SW}->u:F

    invoke-virtual {{v2, v3}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Label;->setFontScale(F)V

    iget-object v0, p0, {SW}->l:Lcom/badlogic/gdx/scenes/scene2d/ui/Table;

    invoke-virtual {{v0, v1}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->add(Lcom/badlogic/gdx/scenes/scene2d/Actor;)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    move-result-object v0

    sget v2, {SW}->t:F

    const/high16 v3, 0x43200000    # 160.0f

    mul-float v3, v3, v2

    invoke-virtual {{v0, v3}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;->width(F)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    const/high16 v3, 0x42200000    # 40.0f

    mul-float v3, v3, v2

    invoke-virtual {{v0, v3}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;->height(F)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    iget-object v0, p0, {SW}->l:Lcom/badlogic/gdx/scenes/scene2d/ui/Table;

    invoke-virtual {{v0}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->row()Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    const/4 v0, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

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

.method public static ekPageClass({SHEET}I){CC}
    .locals 3

    invoke-virtual {{p0}}, {SHEET}->n()Lnet/fdgames/Rules/Rules$CharacterClass;

    move-result-object v0

    invoke-virtual {{p0}}, {SHEET}->ekIsHero()Z

    move-result v2

    if-eqz v2, :ekpc_ret

    const/4 v2, 0x1

    if-ne p1, v2, :ekpc_p2

    sget-object v0, {CC}->c:{CC}

    goto :ekpc_ret

    :ekpc_p2
    const/4 v2, 0x2

    if-ne p1, v2, :ekpc_p3

    sget-object v0, {CC}->d:{CC}

    goto :ekpc_ret

    :ekpc_p3
    const/4 v2, 0x3

    if-ne p1, v2, :ekpc_ret

    sget-object v0, {CC}->e:{CC}

    :ekpc_ret
    return-object v0
.end method

.method public static ekSkillsSuppressed({CC})Ljava/util/ArrayList;
    .locals 1

    const/4 v0, 0x1

    sput-boolean v0, {CR}->ekSuppress:Z

    invoke-static {{p0}}, Lnet/fdgames/Rules/Skills;->a(Lnet/fdgames/Rules/Rules$CharacterClass;)Ljava/util/ArrayList;

    move-result-object p0

    const/4 v0, 0x0

    sput-boolean v0, {CR}->ekSuppress:Z

    return-object p0
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
# 6) Hero perks are the PLAYER's only (v23). The Hero reuses the WARRIOR enum, so every
#    warrior-class NPC -- above all the companion Grissenda -- was silently a Hero too:
#    unrestricted gear/skills, a mana pool + bar, the class pager. Owner wants her a
#    straight warrior again. Every Hero hook now asks CharacterSheet.ekIsHero()
#    (= class WARRIOR && this is the player's sheet) instead of "class == WARRIOR".
#    ekIsPlayer() mirrors the game's own W() test (GameData.player.sheet == this), but
#    null-safe (no player yet -> treated as the player, e.g. at creation/load) and with
#    no branch joins at all (Dalvik 4.2.2 verifier).
#    ClassRestriction.a(class) has no sheet, so the three sheet-aware call sites
#    (Item [equip], Skill.a(sheet) [learn/trainers], Rules.a(I,sheet)) now go through
#    ClassRestriction.ekAllowed(restriction, sheet), which runs a non-Hero warrior with
#    ekSuppress on (= vanilla warrior rules). The class-list builder is already
#    suppressed by the pager, and ekPageClass() keeps an NPC warrior on page 0.
# ---------------------------------------------------------------------------
GDATA = 'Lnet/fdgames/GameWorld/GameData;'
PLAYER = 'Lnet/fdgames/GameEntities/Final/Player;'
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterSheet.smali'
s = open(p, encoding='utf-8').read()
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + f"""

.method public ekIsPlayer()Z
    .locals 2

    invoke-static {{}}, {GDATA}->O(){GDATA}

    move-result-object v0

    if-nez v0, :ekip_gd

    const/4 v1, 0x1

    return v1

    :ekip_gd
    iget-object v0, v0, {GDATA}->player:{PLAYER}

    if-nez v0, :ekip_pl

    const/4 v1, 0x1

    return v1

    :ekip_pl
    iget-object v0, v0, Lnet/fdgames/GameEntities/Character;->sheet:{SHEET}

    if-eq v0, p0, :ekip_yes

    const/4 v1, 0x0

    return v1

    :ekip_yes
    const/4 v1, 0x1

    return v1
.end method

.method public ekIsHero()Z
    .locals 2

    invoke-virtual {{p0}}, {SHEET}->n(){CC}

    move-result-object v0

    sget-object v1, {CC}->b:{CC}

    if-eq v0, v1, :ekih_warrior

    const/4 v0, 0x0

    return v0

    :ekih_warrior
    invoke-virtual {{p0}}, {SHEET}->ekIsPlayer()Z

    move-result v0

    return v0
.end method
""" + s[tail:]
open(p, 'w', encoding='utf-8').write(s)

p = f'{w}/smali/net/fdgames/Rules/ClassRestriction.smali'
s = open(p, encoding='utf-8').read()
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + f"""

.method public static ekAllowed({CR}{SHEET})Ljava/lang/Boolean;
    .locals 2

    invoke-virtual {{p1}}, {SHEET}->n(){CC}

    move-result-object v0

    invoke-virtual {{p1}}, {SHEET}->ekIsHero()Z

    move-result v1

    if-nez v1, :ekal_hero

    const/4 v1, 0x1

    sput-boolean v1, {CR}->ekSuppress:Z

    invoke-virtual {{p0, v0}}, {CR}->a({CC})Ljava/lang/Boolean;

    move-result-object v0

    const/4 v1, 0x0

    sput-boolean v1, {CR}->ekSuppress:Z

    return-object v0

    :ekal_hero
    invoke-virtual {{p0, v0}}, {CR}->a({CC})Ljava/lang/Boolean;

    move-result-object v0

    return-object v0
.end method
""" + s[tail:]
open(p, 'w', encoding='utf-8').write(s)

CRA = f'{CR}->a({CC})Ljava/lang/Boolean;'
def _site(path, old, new, what):
    t = open(path, encoding='utf-8').read()
    assert t.count(old) == 1, f"{what}: call site not found"
    open(path, 'w', encoding='utf-8').write(t.replace(old, new, 1))

_site(f'{w}/smali/net/fdgames/Rules/Item.smali',
      f'    invoke-virtual {{v0, v1}}, {CRA}\n',
      f'    invoke-static {{v0, p1}}, {CR}->ekAllowed({CR}{SHEET})Ljava/lang/Boolean;\n', 'Item.a(sheet)')
_site(f'{w}/smali/net/fdgames/Rules/Rules.smali',
      f'    invoke-virtual {{v0, v2}}, {CRA}\n',
      f'    invoke-static {{v0, p1}}, {CR}->ekAllowed({CR}{SHEET})Ljava/lang/Boolean;\n', 'Rules.a(I,sheet)')
_site(f'{w}/smali/net/fdgames/Rules/Skill.smali',
      f'    invoke-virtual {{p1}}, {SHEET}->n(){CC}\n\n    move-result-object p1\n\n'
      f'    invoke-virtual {{v0, p1}}, {CRA}\n',
      f'    invoke-static {{v0, p1}}, {CR}->ekAllowed({CR}{SHEET})Ljava/lang/Boolean;\n', 'Skill.a(sheet)')
print("patched Hero perks -> player only (ekIsHero): V, C, s0, pager, equip/learn/trainer gates")

# ---------------------------------------------------------------------------
# 7) One-time cleanup of the skills a warrior NPC (Grissenda) picked up while the Hero
#    leak was open (v24, owner request). SkillSet.ekPurgeSheet(sheet): for a WARRIOR
#    sheet that is NOT the player's, remove every learned skill whose class restriction
#    rejects a (vanilla) warrior -- evaluated through ClassRestriction.ekAllowed, i.e.
#    with ekSuppress on. Points refund themselves: an NPC's free points are
#    CharacterSheet.J() = level - SkillSet.d() (+bonus), and d() sums the costs of the
#    skills still in the list. SkillSet.f() then rebuilds the passive bonus set.
#    Her scripted grants (shield_expert W, infantry_training W, precission_strikes /
#    heavyhand W,R,C, body_development / massive_criticals unrestricted) all pass.
#    Hooks, so an existing save heals without re-recruiting:
#      * Party.a(NPC)            -- recruit / re-registration
#      * SkillWindow c0.a(sheet) -- opening anyone's skill screen
#      * trigger scan e/a/c/b.e  -- SkillSet.ekPurgeParty(), every 256th call, walks
#                                   GameData.party.companions (the saved NPC objects)
# ---------------------------------------------------------------------------
SKS = 'Lnet/fdgames/GameEntities/Helpers/SkillSet;'
INV = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;'
CSK = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSkill;'
GDATA = 'Lnet/fdgames/GameWorld/GameData;'
PARTY = 'Lnet/fdgames/GameWorld/Party;'
NPCC = 'Lnet/fdgames/GameEntities/Final/NPC;'
p = f'{w}/smali/net/fdgames/GameEntities/Helpers/SkillSet.smali'
s = open(p, encoding='utf-8').read()
s = s.replace('.field public bonusPoints:I', '.field public static ekPurgeTick:I\n\n.field public bonusPoints:I', 1)
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + f"""

.method public static ekPurgeSheet({SHEET})V
    .locals 5

    if-nez p0, :ekps_a

    return-void

    :ekps_a
    invoke-virtual {{p0}}, {SHEET}->n(){CC}

    move-result-object v0

    sget-object v1, {CC}->b:{CC}

    if-eq v0, v1, :ekps_b

    return-void

    :ekps_b
    invoke-virtual {{p0}}, {SHEET}->ekIsPlayer()Z

    move-result v0

    if-eqz v0, :ekps_c

    return-void

    :ekps_c
    iget-object v0, p0, {SHEET}->inventory:{INV}

    if-eqz v0, :ekps_noinv

    invoke-static {{v0, p0}}, {INV}->ekStripOffClass({INV}{SHEET})V

    :ekps_noinv
    iget-object v0, p0, {SHEET}->skillSet:{SKS}

    if-nez v0, :ekps_d

    return-void

    :ekps_d
    iget-object v1, v0, {SKS}->characterSkills:Ljava/util/ArrayList;

    if-nez v1, :ekps_e

    return-void

    :ekps_e
    invoke-virtual {{v1}}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    const/4 v4, 0x0

    :ekps_loop
    invoke-interface {{v1}}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :ekps_done

    invoke-interface {{v1}}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, {CSK}

    iget-object v2, v2, {CSK}->skillID:Ljava/lang/String;

    invoke-static {{v2}}, Lnet/fdgames/Rules/Skills;->a(Ljava/lang/String;)Lnet/fdgames/Rules/Skill;

    move-result-object v2

    if-eqz v2, :ekps_loop

    iget-object v2, v2, Lnet/fdgames/Rules/Skill;->skillClass:{CR}

    if-eqz v2, :ekps_loop

    invoke-static {{v2, p0}}, {CR}->ekAllowed({CR}{SHEET})Ljava/lang/Boolean;

    move-result-object v2

    invoke-virtual {{v2}}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v2

    if-nez v2, :ekps_loop

    invoke-interface {{v1}}, Ljava/util/Iterator;->remove()V

    const/4 v4, 0x1

    goto :ekps_loop

    :ekps_done
    if-eqz v4, :ekps_ret

    invoke-virtual {{v0}}, {SKS}->f()V

    :ekps_ret
    return-void
.end method

.method public static ekPurgeParty()V
    .locals 4

    sget v0, {SKS}->ekPurgeTick:I

    add-int/lit8 v0, v0, 0x1

    sput v0, {SKS}->ekPurgeTick:I

    and-int/lit16 v0, v0, 0xff

    if-eqz v0, :ekpp_go

    return-void

    :ekpp_go
    invoke-static {{}}, {GDATA}->O(){GDATA}

    move-result-object v0

    if-nez v0, :ekpp_a

    return-void

    :ekpp_a
    iget-object v0, v0, {GDATA}->party:{PARTY}

    if-nez v0, :ekpp_b

    return-void

    :ekpp_b
    iget-object v0, v0, {PARTY}->companions:Ljava/util/ArrayList;

    if-nez v0, :ekpp_c

    return-void

    :ekpp_c
    const/4 v1, 0x0

    :ekpp_loop
    invoke-virtual {{v0}}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :ekpp_done

    invoke-virtual {{v0, v1}}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, {NPCC}

    if-eqz v2, :ekpp_next

    iget-object v3, v2, Lnet/fdgames/GameEntities/Character;->sheet:{SHEET}

    invoke-static {{v3}}, {SKS}->ekPurgeSheet({SHEET})V

    :ekpp_next
    add-int/lit8 v1, v1, 0x1

    goto :ekpp_loop

    :ekpp_done
    return-void
.end method
""" + s[tail:]
open(p, 'w', encoding='utf-8').write(s)

# 7b) Off-class GEAR (v25, owner request): CharacterInventory.ekStripOffClass(inv, sheet)
#     walks the 12 equipment slots; an item the (vanilla) warrior may not use is moved to
#     the PLAYER's backpack (Items.a(I)Z, the game's own add; false when full) and only
#     then unequipped, so nothing is ever lost -- a full backpack just leaves it on her
#     until a later pass. Then u() recomputes bonuses, exactly like the game's unequip
#     (CharacterInventory.a(IZ)Z zeroes the slot then calls u()).
SLOTS = ['slot_mainhand', 'slot_offhand', 'slot_head', 'slot_body', 'slot_hands',
         'slot_legs', 'slot_feet', 'slot_ring', 'slot_ring2', 'slot_belt',
         'slot_cloak', 'slot_necklace']
strip = ''
for sl in SLOTS:
    strip += f"""    iget v0, p0, {INV}->{sl}:I

    invoke-static {{v0, p1}}, {INV}->ekOffClass(I{SHEET})Z

    move-result v1

    if-eqz v1, :ekst_{sl}

    const/4 v1, 0x0

    iput v1, p0, {INV}->{sl}:I

    const/4 v2, 0x1

    :ekst_{sl}
"""
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterInventory.smali'
t = open(p, encoding='utf-8').read()
tail = t.rindex('.end method') + len('.end method')
t = t[:tail] + f"""

.method public static ekOffClass(I{SHEET})Z
    .locals 2

    if-nez p0, :ekoc_a

    const/4 v1, 0x0

    return v1

    :ekoc_a
    invoke-static {{p0}}, Lnet/fdgames/Rules/Rules;->c(I)Lnet/fdgames/Rules/Item;

    move-result-object v0

    if-nez v0, :ekoc_b

    const/4 v1, 0x0

    return v1

    :ekoc_b
    iget-object v0, v0, Lnet/fdgames/Rules/Item;->classes:{CR}

    if-nez v0, :ekoc_c

    const/4 v1, 0x0

    return v1

    :ekoc_c
    invoke-static {{v0, p1}}, {CR}->ekAllowed({CR}{SHEET})Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {{v0}}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :ekoc_d

    const/4 v1, 0x0

    return v1

    :ekoc_d
    invoke-static {{}}, {GDATA}->O(){GDATA}

    move-result-object v0

    if-nez v0, :ekoc_e

    const/4 v1, 0x0

    return v1

    :ekoc_e
    iget-object v0, v0, {GDATA}->backpack:Lnet/fdgames/GameEntities/Helpers/Items;

    if-nez v0, :ekoc_f

    const/4 v1, 0x0

    return v1

    :ekoc_f
    invoke-virtual {{v0, p0}}, Lnet/fdgames/GameEntities/Helpers/Items;->a(I)Z

    move-result v1

    return v1
.end method

.method public static ekStripOffClass({INV}{SHEET})V
    .locals 3

    const/4 v2, 0x0

{strip}
    if-eqz v2, :ekst_done

    invoke-virtual {{p0}}, {INV}->u()V

    :ekst_done
    return-void
.end method
""" + t[tail:]
open(p, 'w', encoding='utf-8').write(t)
print("patched CharacterInventory: +ekStripOffClass/+ekOffClass (off-class gear -> player backpack)")

def _hook(path, old, new, what):
    t = open(path, encoding='utf-8').read()
    assert t.count(old) == 1, f"{what}: anchor not found ({t.count(old)})"
    open(path, 'w', encoding='utf-8').write(t.replace(old, new, 1))

jg = f'    invoke-static {{p1}}, {NPCC}->ekJanodGear({NPCC})V\n'
_hook(f'{w}/smali/net/fdgames/GameWorld/Party.smali', jg,
      jg + f'\n    iget-object v0, p1, Lnet/fdgames/GameEntities/Character;->sheet:{SHEET}\n\n'
      f'    invoke-static {{v0}}, {SKS}->ekPurgeSheet({SHEET})V\n', 'Party.a(NPC)')
sig = f'.method public a({SHEET}Lcom/badlogic/gdx/scenes/scene2d/Stage;)V\n    .locals 1\n'
_hook(f'{w}/smali/e/a/d/e/c0.smali', sig,
      sig + f'\n    invoke-static {{p1}}, {SKS}->ekPurgeSheet({SHEET})V\n', 'SkillWindow.a(sheet,stage)')
hc = f'    invoke-static {{}}, {NPCC}->ekHomecomingTick()V\n'
_hook(f'{w}/smali/e/a/c/b.smali', hc,
      hc + f'\n    invoke-static {{}}, {SKS}->ekPurgeParty()V\n', 'trigger scan')
print("patched SkillSet: +ekPurgeSheet/+ekPurgeParty (warrior NPCs lose off-class skills, points refunded)")

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

# ---------------------------------------------------------------------------
# 7) One scrollable skill page for the Hero (deobf/HERO_CLASS_MOD_SPEC.md "One scrollable skill page")
# ---------------------------------------------------------------------------
p = f'{w}/smali/e/a/d/e/c0.smali'
s = open(p, encoding='utf-8').read()
TBL = 'Lcom/badlogic/gdx/scenes/scene2d/ui/Table;'
CELL = 'Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;'
ACT = 'Lcom/badlogic/gdx/scenes/scene2d/Actor;'
SK = 'Lnet/fdgames/Rules/Skill;'
Z = 'Le/a/d/e/z;'
LBL = 'Lcom/badlogic/gdx/scenes/scene2d/ui/Label;'

# 7a) 44 skill buttons (20 vanilla + 3 x 8 for ROGUE/CLERIC/MAGE)
import re as _re
new, k = _re.subn(r'(    const/16 (v\d+), 0x14\n\n    new-array \2, \2, \[Le/a/d/e/z;\n)', lambda g: g.group(1).replace('0x14', '0x2c'), s)
assert k == 1, f"n array size: {k}"
s = new

# 7b) the skill list scrolls (vertical)
old = f'    iget-object v3, p0, {SW}->l:{TBL}\n\n    invoke-virtual {{v2, v3}}, {TBL}->add({ACT}){CELL}\n'
assert s.count(old) == 1, f"l add: {s.count(old)}"
s = s.replace(old, f'    iget-object v3, p0, {SW}->l:{TBL}\n\n    invoke-static {{v3}}, {SW}->ekScroll({TBL}){ACT}\n\n    move-result-object v3\n\n'
              f'    invoke-virtual {{v2, v3}}, {TBL}->add({ACT}){CELL}\n\n    move-result-object v3\n\n'
              f'    invoke-virtual {{v3}}, {CELL}->expand(){CELL}\n\n    move-result-object v3\n\n    invoke-virtual {{v3}}, {CELL}->fill(){CELL}\n', 1)

# 7b2) the skill description panel (SkillDetailTable m) scrolls too: long descriptions (summons: rank
#      tables + route text) ran past the window's bottom / behind the Back button (beta tester report)
YT = 'Le/a/d/e/y;'
old = f'    iget-object v3, p0, {SW}->m:{YT}\n\n    invoke-virtual {{v2, v3}}, {TBL}->add({ACT}){CELL}\n\n    move-result-object v3\n'
assert s.count(old) == 1, f"m add: {s.count(old)}"
s = s.replace(old, f'    iget-object v3, p0, {SW}->m:{YT}\n\n    invoke-static {{v3}}, {SW}->ekScroll({TBL}){ACT}\n\n    move-result-object v3\n\n'
              f'    invoke-virtual {{v2, v3}}, {TBL}->add({ACT}){CELL}\n\n    move-result-object v3\n\n'
              f'    invoke-virtual {{v3}}, {CELL}->expandY(){CELL}\n\n    move-result-object v3\n\n    invoke-virtual {{v3}}, {CELL}->fillY(){CELL}\n\n    move-result-object v3\n', 1)
# picking another skill starts its description at the top
old = f'.method private a({SK})V\n    .registers 10\n'
if s.count(old) != 1:
    old = f'.method private a({SK})V\n    .locals 8\n'
assert s.count(old) == 1, "a(Skill) header"
s = s.replace(old, old + f'\n    iget-object v0, p0, {SW}->m:{YT}\n\n    invoke-static {{v0}}, {SW}->ekScrollTop({ACT})V\n', 1)

# 7c) the other classes' sections, right after the Hero's own (before General)
old = (f'    sget-object v0, {CC}->g:{CC}\n\n'
       f'    invoke-static {{v0}}, Lnet/fdgames/Rules/Skills;->a({CC})Ljava/util/ArrayList;\n')
i = s.index('.method private c()V')
j = s.index('.end method', i)
assert s[i:j].count(old) == 1, f"general section anchor: {s[i:j].count(old)}"
s = s[:i] + s[i:j].replace(old, f'    invoke-virtual {{p0}}, {SW}->ekAddOtherClasses()V\n\n' + old, 1) + s[j:]

# 7d) no pager button any more (page stays 0 = the Hero's own)
old = '    invoke-virtual {p0}, Le/a/d/e/c0;->ekMaybeAddPagerRow()V\n'
assert s.count(old) == 1
s = s.replace(old, '', 1)

# 7e) highlight over every button (was a hard-coded 20)
i = s.index('.method public draw(Lcom/badlogic/gdx/graphics/g2d/Batch;F)V')
j = s.index('.end method', i) + len('.end method')
s = s[:i] + f""".method public draw(Lcom/badlogic/gdx/graphics/g2d/Batch;F)V
    .locals 4

    invoke-super {{p0, p1, p2}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Window;->draw(Lcom/badlogic/gdx/graphics/g2d/Batch;F)V

    iget-object v0, p0, {SW}->n:[{Z}

    array-length v1, v0

    const/4 p2, 0x0

    :ekd_loop
    if-ge p2, v1, :ekd_end

    aget-object v2, v0, p2

    if-eqz v2, :ekd_next

    iget v3, p0, {SW}->k:I

    if-ne p2, v3, :ekd_off

    const/4 v3, 0x1

    iput-boolean v3, v2, {Z}->d:Z

    goto :ekd_next

    :ekd_off
    const/4 v3, 0x0

    iput-boolean v3, v2, {Z}->d:Z

    :ekd_next
    add-int/lit8 p2, p2, 0x1

    goto :ekd_loop

    :ekd_end
    return-void
.end method""" + s[j:]

# 7f) helpers
s = s.rstrip('\n') + f"""

.method public static ekScroll({TBL}){ACT}
    .locals 3

    new-instance v0, Lcom/badlogic/gdx/scenes/scene2d/ui/ScrollPane;

    invoke-direct {{v0, p0}}, Lcom/badlogic/gdx/scenes/scene2d/ui/ScrollPane;-><init>({ACT})V

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {{v0, v1, v2}}, Lcom/badlogic/gdx/scenes/scene2d/ui/ScrollPane;->setScrollingDisabled(ZZ)V

    invoke-virtual {{v0, v2, v2}}, Lcom/badlogic/gdx/scenes/scene2d/ui/ScrollPane;->setOverscroll(ZZ)V

    return-object v0
.end method

.method public static ekScrollTop({ACT})V
    .locals 2

    if-eqz p0, :ekst_done

    invoke-virtual {{p0}}, {ACT}->getParent()Lcom/badlogic/gdx/scenes/scene2d/Group;

    move-result-object v0

    instance-of v1, v0, Lcom/badlogic/gdx/scenes/scene2d/ui/ScrollPane;

    if-eqz v1, :ekst_done

    check-cast v0, Lcom/badlogic/gdx/scenes/scene2d/ui/ScrollPane;

    const/4 v1, 0x0

    invoke-virtual {{v0, v1}}, Lcom/badlogic/gdx/scenes/scene2d/ui/ScrollPane;->setScrollY(F)V

    :ekst_done
    return-void
.end method

.method public ekAddOtherClasses()V
    .locals 2

    iget-object v0, p0, {SW}->j:{SHEET}

    invoke-virtual {{v0}}, {SHEET}->ekIsHero()Z

    move-result v0

    if-eqz v0, :eko_done

    const/4 v1, 0x1

    :eko_loop
    const/4 v0, 0x4

    if-ge v1, v0, :eko_done

    invoke-virtual {{p0, v1}}, {SW}->ekAddClassSection(I)V

    add-int/lit8 v1, v1, 0x1

    goto :eko_loop

    :eko_done
    return-void
.end method

.method public ekAddClassSection(I)V
    .locals 12

    iget-object v0, p0, {SW}->j:{SHEET}

    invoke-static {{v0, p1}}, {SW}->ekPageClass({SHEET}I){CC}

    move-result-object v0

    invoke-static {{v0}}, {SW}->ekSkillsSuppressed({CC})Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {{v1}}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    add-int/lit8 v2, p1, -0x1

    mul-int/lit8 v2, v2, 0x8

    add-int/lit8 v2, v2, 0x14

    const/4 v3, 0x0

    :eks_loop
    invoke-interface {{v1}}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :eks_fill

    invoke-interface {{v1}}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, {SK}

    const/4 v5, 0x7

    if-gt v3, v5, :eks_loop

    iget-object v5, p0, {SW}->j:{SHEET}

    invoke-virtual {{v5}}, {SHEET}->W()Z

    move-result v5

    if-nez v5, :eks_ok

    iget-boolean v5, v4, {SK}->NPCSkill:Z

    if-eqz v5, :eks_loop

    :eks_ok
    iget-object v5, p0, {SW}->j:{SHEET}

    iget-object v5, v5, {SHEET}->skillSet:Lnet/fdgames/GameEntities/Helpers/SkillSet;

    iget-object v6, v4, {SK}->id:Ljava/lang/String;

    invoke-virtual {{v5, v6}}, Lnet/fdgames/GameEntities/Helpers/SkillSet;->c(Ljava/lang/String;)I

    move-result v5

    add-int v6, v2, v3

    new-instance v8, {Z}

    invoke-direct {{v8, v4, v5}}, {Z}-><init>({SK}I)V

    iget-object v9, p0, {SW}->n:[{Z}

    aput-object v8, v9, v6

    invoke-virtual {{v8}}, {ACT}->clearListeners()V

    iget-object v10, v4, {SK}->id:Ljava/lang/String;

    new-instance v11, Le/a/d/e/e0;

    invoke-direct {{v11, p0, v6, v10}}, Le/a/d/e/e0;-><init>({SW}ILjava/lang/String;)V

    invoke-virtual {{v8, v11}}, {ACT}->addListener(Lcom/badlogic/gdx/scenes/scene2d/EventListener;)Z

    add-int/lit8 v3, v3, 0x1

    goto :eks_loop

    :eks_fill
    const/4 v5, 0x7

    if-gt v3, v5, :eks_layout

    add-int v6, v2, v3

    new-instance v8, {Z}

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct {{v8, v4, v5}}, {Z}-><init>({SK}I)V

    iget-object v9, p0, {SW}->n:[{Z}

    aput-object v8, v9, v6

    invoke-virtual {{v8}}, {ACT}->clearListeners()V

    add-int/lit8 v3, v3, 0x1

    goto :eks_fill

    :eks_layout
    new-instance v4, {LBL}

    new-instance v5, Ljava/lang/StringBuilder;

    const-string v6, " "

    invoke-direct {{v5, v6}}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-static {{v0}}, {CC}->a({CC})Ljava/lang/String;

    move-result-object v7

    invoke-virtual {{v5, v7}}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {{v5, v6}}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "SKILLS"

    invoke-static {{v7}}, Lnet/fdgames/Helpers/GameString;->a(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {{v5, v7}}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {{v5}}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {{}}, Lnet/fdgames/assets/Assets;->e()Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;

    move-result-object v6

    const-string v7, "menuLabelStrongStyle"

    invoke-direct {{v4, v5, v6, v7}}, {LBL}-><init>(Ljava/lang/CharSequence;Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;Ljava/lang/String;)V

    sget v5, {SW}->u:F

    invoke-virtual {{v4, v5}}, {LBL}->setFontScale(F)V

    iget-object v5, p0, {SW}->l:{TBL}

    invoke-virtual {{v5}}, {TBL}->row(){CELL}

    move-result-object v6

    const/4 v7, 0x4

    invoke-virtual {{v6, v7}}, {CELL}->colspan(I){CELL}

    invoke-virtual {{v5, v4}}, {TBL}->add({ACT}){CELL}

    move-result-object v6

    invoke-virtual {{v6}}, {CELL}->center(){CELL}

    move-result-object v6

    invoke-virtual {{v6}}, {CELL}->expandX(){CELL}

    const/4 v3, 0x0

    :ekr_rows
    const/4 v4, 0x2

    if-ge v3, v4, :ekr_done

    invoke-virtual {{v5}}, {TBL}->row(){CELL}

    move-result-object v6

    sget v7, {SW}->t:F

    const/high16 v8, 0x40c00000    # 6.0f

    mul-float v7, v7, v8

    invoke-virtual {{v6, v7}}, {CELL}->pad(F){CELL}

    move-result-object v6

    invoke-virtual {{v6, v7}}, {CELL}->spaceBottom(F){CELL}

    const/4 v4, 0x0

    :ekr_cols
    const/4 v6, 0x4

    if-ge v4, v6, :ekr_next

    mul-int/lit8 v6, v3, 0x4

    add-int/2addr v6, v2

    add-int/2addr v6, v4

    iget-object v7, p0, {SW}->n:[{Z}

    aget-object v7, v7, v6

    invoke-virtual {{v5, v7}}, {TBL}->add({ACT}){CELL}

    move-result-object v7

    iget v8, p0, {SW}->o:F

    invoke-virtual {{v7, v8}}, {CELL}->width(F){CELL}

    move-result-object v7

    invoke-virtual {{v7, v8}}, {CELL}->height(F){CELL}

    add-int/lit8 v4, v4, 0x1

    goto :ekr_cols

    :ekr_next
    add-int/lit8 v3, v3, 0x1

    goto :ekr_rows

    :ekr_done
    return-void
.end method
"""
open(p, 'w', encoding='utf-8').write(s)
print("patched SkillWindow: one scrollable page, all classes (Hero)")

print("DONE")
