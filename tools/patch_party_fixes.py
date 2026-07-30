#!/usr/bin/env python3
"""
v13 party fixes. Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).
Reversing: deobf/COMPANION_SPEC.md §10, deobf/PARTY_AI_SPEC.md §3.

1) Summons gain XP and level up, on the SAME terms as a companion (56% of the
   reward), paid ADDITIVELY -- the player's own XP is untouched (v12 rule).
2) Dismissing the active companion promotes the other one, so a second companion
   left in the party becomes a real companion again (character sheet, orders, XP
   catch-up) instead of a mute follower.
"""
import re

w = "."
NPCC = 'Lnet/fdgames/GameEntities/Final/NPC;'
CHAR = 'Lnet/fdgames/GameEntities/Character;'
CS = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
GO = 'Lnet/fdgames/GameEntities/GameObject;'
MO = 'Lnet/fdgames/GameEntities/MapObject;'
GLD = 'Lnet/fdgames/GameLevel/GameLevelData;'
GD = 'Lnet/fdgames/GameWorld/GameData;'
PARTY = 'Lnet/fdgames/GameWorld/Party;'
ITER = 'Ljava/util/Iterator;'
ALIST = 'Ljava/util/ArrayList;'

# The companion's cut of a reward. v12 set Player's own multiplier to 1.0f and the
# companion's to 0.56f (== vanilla 0.8 x 0.7 of the full amount); summons match it.
SHARE = '0x3f0f5c29'      # 0.56f
SHARE_TXT = '0x3f333333'  # 0.7f -- floating-text fade/scale args, same as the companion's

# ---------------------------------------------------------------------------
# 1) NPC.ekSummonXP(I) -- every live player summon earns the companion's share.
#
#    XP plumbing (base names): CharacterSheet.h(I)I applies the sheet's XP bonuses,
#    CharacterSheet.b(I)V adds it (-> CharacterStats.c(I)V, which adds to XP and
#    recaches, so the level follows automatically). NOTE CharacterStats.a(I)V is
#    missingHP in this build, NOT xp -- the readable decompile's letters differ.
#
#    Levels persist for free: GameLevelData.K() stores follower.lastLevel on unload
#    and I() restores it with sheet.i0(lastLevel) on the next map, so a summon that
#    survives keeps what it earned. When it dies or its timer expires the follower
#    record goes with it, so the next summon starts fresh -- which is the point.
#
#    NPC.O0(I) (the NPC's own XP entry) is deliberately NOT used: it dereferences
#    party.getCompanion() unguarded to decide whether to draw the floating "+N xp",
#    which NPEs when you have no active companion. The visuals are rebuilt here instead,
#    copying the companion's own idiom out of Player.k(I)V:
#      * "+N xp" in yellow over the summon on every gain;
#      * on a level-up, "LEVEL UP" in blue + the levelup sound + a battle-log line
#        "<Name> reaches level N" (GameLog.a(String)).
#    The floating-text constructor takes 6 args, so it needs invoke-direct/range over
#    seven CONTIGUOUS registers (v4..v10 here) -- the non-range form caps at five.
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/GameEntities/Final/NPC.smali'
s = open(p, encoding='utf-8').read()

FLOAT = 'Le/a/d/x;'                       # floating combat text
FLOATMGR = 'Le/a/a/a;'                    # its manager (static h())
COLOR = 'Lcom/badlogic/gdx/graphics/Color;'
GLOG = 'Lnet/fdgames/GameWorld/GameLog;'
GSTR = 'Lnet/fdgames/Helpers/GameString;'
ASSETS = 'Lnet/fdgames/assets/GameAssets;'
STR = 'Ljava/lang/String;'

summon_xp = f'''
.method public static ekSummonXP(I)V
    .locals 12

    if-lez p0, :eksx_done

    invoke-static {{}}, {GLD}->w(){ALIST}

    move-result-object v0

    if-eqz v0, :eksx_done

    invoke-virtual {{v0}}, {ALIST}->iterator(){ITER}

    move-result-object v0

    :eksx_loop
    invoke-interface {{v0}}, {ITER}->hasNext()Z

    move-result v1

    if-eqz v1, :eksx_done

    invoke-interface {{v0}}, {ITER}->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, {NPCC}

    if-eqz v1, :eksx_loop

    iget-boolean v2, v1, {NPCC}->summoned:Z

    if-eqz v2, :eksx_loop

    iget-boolean v2, v1, {GO}->destroy:Z

    if-nez v2, :eksx_loop

    const-string v2, "player_summon"

    iget-object v3, v1, {MO}->tag:Ljava/lang/String;

    invoke-virtual {{v2, v3}}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :eksx_loop

    iget-object v2, v1, {CHAR}->sheet:{CS}

    if-eqz v2, :eksx_loop

    invoke-virtual {{v2}}, {CS}->z()I

    move-result v11

    int-to-float v3, p0

    const v4, {SHARE}

    mul-float v3, v3, v4

    float-to-int v3, v3

    if-lez v3, :eksx_loop

    invoke-virtual {{v2, v3}}, {CS}->h(I)I

    move-result v3

    if-lez v3, :eksx_loop

    invoke-virtual {{v2, v3}}, {CS}->b(I)V

    const-string v6, "+"

    invoke-static {{v3}}, Ljava/lang/Integer;->toString(I){STR}

    move-result-object v7

    invoke-virtual {{v6, v7}}, {STR}->concat({STR}){STR}

    move-result-object v6

    const-string v7, "xp"

    invoke-virtual {{v6, v7}}, {STR}->concat({STR}){STR}

    move-result-object v6

    invoke-static {{}}, {FLOATMGR}->h(){FLOATMGR}

    move-result-object v3

    if-eqz v3, :eksx_loop

    new-instance v4, {FLOAT}

    invoke-virtual {{v1}}, {GO}->m()I

    move-result v5

    const v7, 0x3fa66666

    sget-object v8, {COLOR}->YELLOW:{COLOR}

    const v9, {SHARE_TXT}

    const v10, {SHARE_TXT}

    invoke-direct/range {{v4 .. v10}}, {FLOAT}-><init>(I{STR}F{COLOR}FF)V

    invoke-virtual {{v3, v4}}, {FLOATMGR}->a({FLOAT})V

    invoke-virtual {{v2}}, {CS}->z()I

    move-result v5

    if-le v5, v11, :eksx_loop

    move v11, v5

    invoke-static {{}}, {FLOATMGR}->h(){FLOATMGR}

    move-result-object v3

    if-eqz v3, :eksx_log

    new-instance v4, {FLOAT}

    invoke-virtual {{v1}}, {GO}->m()I

    move-result v5

    const-string v6, "LEVEL_UP"

    invoke-static {{v6}}, {GSTR}->a({STR}){STR}

    move-result-object v6

    const/high16 v7, 0x3f800000

    sget-object v8, {COLOR}->BLUE:{COLOR}

    const/high16 v9, 0x3f800000

    const v10, {SHARE_TXT}

    invoke-direct/range {{v4 .. v10}}, {FLOAT}-><init>(I{STR}F{COLOR}FF)V

    invoke-virtual {{v3, v4}}, {FLOATMGR}->a({FLOAT})V

    const-string v3, "levelup"

    invoke-static {{v3}}, {ASSETS}->i({STR})V

    :eksx_log
    invoke-static {{}}, {GD}->O(){GD}

    move-result-object v3

    if-eqz v3, :eksx_loop

    iget-object v3, v3, {GD}->log:{GLOG}

    if-eqz v3, :eksx_loop

    invoke-virtual {{v1}}, {CHAR}->getName(){STR}

    move-result-object v4

    if-eqz v4, :eksx_loop

    const-string v5, " reaches level "

    invoke-virtual {{v4, v5}}, {STR}->concat({STR}){STR}

    move-result-object v4

    invoke-static {{v11}}, Ljava/lang/Integer;->toString(I){STR}

    move-result-object v5

    invoke-virtual {{v4, v5}}, {STR}->concat({STR}){STR}

    move-result-object v4

    invoke-virtual {{v3, v4}}, {GLOG}->a({STR})V

    goto :eksx_loop

    :eksx_done
    return-void
.end method
'''

# ---------------------------------------------------------------------------
# 2) NPC.ekPromoteFollower() -- fill an empty companion slot from the party.
#
#    With two companions (v8), the second travels as a FOLLOWER. Dismissing the
#    first clears activeCompanion and returns, leaving the second one following but
#    not selectable -- no character sheet, no battle orders, no XP catch-up.
#
#    This finds a still-following companion-grade NPC (companionSpawn is private, so
#    the helper has to live in NPC) and hands it the empty slot:
#      * Party.a(String,String,String) with its identity removes its FOLLOWER record
#        -- otherwise GameLevelData.I() would rebuild a duplicate on the next map.
#        Safe to reuse: activeCompanion is "" at this point, so that call cannot take
#        the early-return branch and recurse back into the hook.
#      * Party.a(NPC) then registers it exactly like a fresh recruit (activeCompanion,
#        companions list, Party.r() XP catch-up).
# ---------------------------------------------------------------------------
promote = f'''
.method public static ekPromoteFollower()V
    .locals 6

    invoke-static {{}}, {GD}->O(){GD}

    move-result-object v0

    if-eqz v0, :ekpf_done

    iget-object v0, v0, {GD}->party:{PARTY}

    if-eqz v0, :ekpf_done

    invoke-virtual {{v0}}, {PARTY}->h()Z

    move-result v1

    if-nez v1, :ekpf_done

    invoke-static {{}}, {GLD}->w(){ALIST}

    move-result-object v1

    if-eqz v1, :ekpf_done

    invoke-virtual {{v1}}, {ALIST}->iterator(){ITER}

    move-result-object v1

    :ekpf_loop
    invoke-interface {{v1}}, {ITER}->hasNext()Z

    move-result v2

    if-eqz v2, :ekpf_done

    invoke-interface {{v1}}, {ITER}->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, {NPCC}

    if-eqz v2, :ekpf_loop

    iget-boolean v3, v2, {NPCC}->companionSpawn:Z

    if-eqz v3, :ekpf_loop

    iget-boolean v3, v2, {GO}->destroy:Z

    if-nez v3, :ekpf_loop

    invoke-virtual {{v2}}, {NPCC}->P()Z

    move-result v3

    if-eqz v3, :ekpf_loop

    iget-object v3, v2, {NPCC}->spawn_id:Ljava/lang/String;

    if-eqz v3, :ekpf_loop

    iget-object v4, v2, {MO}->tag:Ljava/lang/String;

    invoke-virtual {{v2}}, {CHAR}->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {{v0, v3, v4, v5}}, {PARTY}->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {{v0, v2}}, {PARTY}->a({NPCC})V

    return-void

    :ekpf_done
    return-void
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + summon_xp + promote + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched NPC: +ekSummonXP +ekPromoteFollower")

# ---------------------------------------------------------------------------
# 3) Call sites.
# ---------------------------------------------------------------------------
# 3a) Player XP funnel -> summons. Player.k(I)V is where every reward lands
#     (kills, quest GainXP, traps); the share is computed from the FULL reward and
#     paid separately, so nothing is taken off the player (COMPANION_SPEC §9).
p = f'{w}/smali/net/fdgames/GameEntities/Final/Player.smali'
s = open(p, encoding='utf-8').read()
m = re.search(r'(\.method public k\(I\)V\n    \.locals (\d+)\n\n)', s)
assert m, "Player.k(I)V (the XP funnel) not found"
# This method is register-hungry (.locals ~20), so p1 lands above v15 and the plain
# invoke-static form -- which can only address v0-v15 -- fails to assemble
# ("Invalid register: v20"). invoke-static/range takes the high register directly.
s = (s[:m.end()]
     + f'    invoke-static/range {{p1 .. p1}}, {NPCC}->ekSummonXP(I)V\n\n'
     + s[m.end():])
open(p, 'w', encoding='utf-8').write(s)
print("patched Player.k(I)V: live summons earn the companion's XP share")

# 3b) Dismissal -> promote. The branch that clears activeCompanion and returns.
p = f'{w}/smali/net/fdgames/GameWorld/Party.smali'
s = open(p, encoding='utf-8').read()
sig = '.method public a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V'
ms = s.index(sig)
me = s.index('.end method', ms)
method = s[ms:me]
clear = re.search(r'    const-string (p\d+|v\d+), ""\n\n'
                  r'    iput-object \1, p0, ' + re.escape(PARTY)
                  + r'->activeCompanion:Ljava/lang/String;\n\n'
                    r'    return-void\n', method)
assert clear, "Party dismissal: activeCompanion clear-and-return not found"
method = (method[:clear.end() - len('    return-void\n')]
          + f'    invoke-static {{}}, {NPCC}->ekPromoteFollower()V\n\n'
          + '    return-void\n'
          + method[clear.end():])
s = s[:ms] + method + s[me:]
open(p, 'w', encoding='utf-8').write(s)
print("patched Party dismissal: the remaining companion takes the empty slot")
print("DONE")
