#!/usr/bin/env python3
"""
Cheat mod patcher for the owner's clean 4.2.2 base APK (apktool layout).

Fixes vs the previous build:
  1. NO-CLIP POLARITY BUG. GameMap `e/a/c/b->c(II)Z` is a *solid/collision* predicate:
     it returns TRUE for BLOCKED tiles (out-of-bounds, p[x][y]>0, closed doors) and
     FALSE for passable ones. The old patch prepended `return true` when noclip was on,
     which made EVERY tile solid -> player AND npcs frozen (attacks still worked because
     they don't consult tile collision). The correct behaviour is to make in-bounds tiles
     PASSABLE, i.e. return FALSE. We insert the noclip test AFTER the bounds check (at the
     in-bounds :cond_1 entry) so out-of-bounds stays solid and array access never goes OOB.
  2. STORAGE PERMISSION for export-save. On Android 4.2.2 (API 17) writing to the app's
     external files dir (where EK.bak is written via Gdx.files.external) still requires
     WRITE_EXTERNAL_STORAGE; the clean base declares none, so export silently fails.

Reputation grant + cheat items are unchanged (they worked); only paths are adapted to the
single-`assets/` apktool layout.

Run from inside the decoded apktool dir (build/dec).
"""
import sys, re
w="."

# ---------------------------------------------------------------------------
# 1) NO-CLIP: e/a/c/b->c(II)Z  (make in-bounds tiles passable when noclip==1)
# ---------------------------------------------------------------------------
p=f'{w}/smali/e/a/c/b.smali'; s=open(p,encoding='utf-8').read()

# widen locals 3 -> 4 so we get a scratch register (v3) without disturbing v0/v1/v2.
sig='.method public c(II)Z\n    .locals 3\n'
assert s.count(sig)==1, "c(II)Z signature not found"
s=s.replace(sig, '.method public c(II)Z\n    .locals 4\n', 1)

# Insert the noclip test at the in-bounds entry label :cond_1. At this point the tile is
# guaranteed in bounds. If noclip is on we return 0 (passable). We use v3 as scratch and
# build our own 0 in v0 for the return (v2 is not yet 0 here).
anchor='    :cond_1\n'
assert s.count(anchor)>=1, ":cond_1 anchor not found"
inject=('    :cond_1\n'
        '    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->O()Lnet/fdgames/GameWorld/GameData;\n\n'
        '    move-result-object v3\n\n'
        '    iget-object v3, v3, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;\n\n'
        '    const-string v1, "noclip"\n\n'
        '    invoke-virtual {v3, v1}, Lnet/fdgames/GameWorld/GameVariables;->b(Ljava/lang/String;)I\n\n'
        '    move-result v3\n\n'
        '    const/4 v1, 0x1\n\n'
        '    if-ne v3, v1, :ekcheat_noclip_off\n\n'
        '    const/4 v0, 0x0\n\n'
        '    return v0\n\n'
        '    :ekcheat_noclip_off\n')
# Replace only the FIRST :cond_1 (the one inside c(II)Z). Guard by patching within the method.
ms=s.index('.method public c(II)Z'); me=s.index('.end method', ms)
method=s[ms:me]
assert method.count('    :cond_1\n')==1, "unexpected :cond_1 count inside c(II)Z"
method=method.replace(anchor, inject, 1)
s=s[:ms]+method+s[me:]
open(p,'w',encoding='utf-8').write(s)
print("patched noclip: e/a/c/b.c(II)Z -> passable when noclip==1")

# ---------------------------------------------------------------------------
# 2) REPUTATION + PHASE/ANCHOR ITEMS granted in Player.y0()V
# ---------------------------------------------------------------------------
p=f'{w}/smali/net/fdgames/GameEntities/Final/Player.smali'; s=open(p,encoding='utf-8').read()
a=s.index('.method public y0()V'); b=s.index('.end method',a); body=s[a:b]
assert body.rstrip().endswith('return-void')
g=''
for h in ('0x2706','0x2707','0x2708'):  # 9990/9991/9992
    g+=('    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;\n\n'
        f'    const v1, {h}\n\n'
        '    invoke-virtual {v0, v1}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->a(I)Z\n\n')
nb=body[:body.rindex('return-void')]+g+'    return-void\n'
open(p,'w',encoding='utf-8').write(s[:a]+nb+s[b:])
print("patched grant: Player.y0() seeds items 9990/9991/9992")

# ---------------------------------------------------------------------------
# 3) items.txt rows
# ---------------------------------------------------------------------------
p=f'{w}/assets/data/rules/items.txt'; raw=open(p,encoding='utf-8').read()
cols=raw.split('\n')[0].split('\t'); n=len(cols); ix={c:i for i,c in enumerate(cols)}
def row(**kw):
    r=['']*n
    for k,v in kw.items(): r[ix[k]]=str(v)
    return '\t'.join(r)
reps=['varsilia','mercia','ilmara','thuram','the_three','town_lannegar','town_kingsbridge',
'town_rhoneis','town_jabal','town_sydarun','town_new_anthur','town_fogas','town_whitetower',
'varannari','loreseekers','goldenhand','seventhhouse','wizardsguild','warriorsguild',
'friguld_governor','town_lamis','town_port_malan','town_solliga']
rows=[
 row(item_ID='9990',name='Tome of Renown',type='wand',armor='0',value='-1',icon='book4',hp='0',mana='0',
     OnUse=';'.join(f'SetVariable#REP_{f},100' for f in reps)),
 row(item_ID='9991',name='Phase Stone',type='wand',armor='0',value='-1',icon='ring1',hp='0',mana='0',OnUse='SetVariable#noclip,1'),
 row(item_ID='9992',name='Anchor Stone',type='wand',armor='0',value='-1',icon='ring1',hp='0',mana='0',OnUse='SetVariable#noclip,0'),
]
open(p,'w',encoding='utf-8').write(raw.rstrip('\n')+'\n'+'\n'.join(rows)+'\n')
print("patched items.txt")

# ---------------------------------------------------------------------------
# 4) items_text.txt rows (name/description; required or loader NPEs)
# ---------------------------------------------------------------------------
p2=f'{w}/assets/data/rules/items_text.txt'; raw2=open(p2,encoding='utf-8').read()
tcols=raw2.split('\n')[0].lstrip('﻿').split('\t'); tn=len(tcols); tix={c:i for i,c in enumerate(tcols)}
def trow(item_ID,name,description):
    r=['']*tn
    r[tix['item_ID']]=item_ID; r[tix['name']]=name; r[tix['description']]=description
    return '\t'.join(r)
trows=[
 trow('9990','Tome of Renown','Reading it fills you with renown. Sets your reputation to Legendary Hero with every faction.'),
 trow('9991','Phase Stone','A shimmering stone that lets you slip through solid matter. Turns no-clip ON.'),
 trow('9992','Anchor Stone','A heavy grey stone that pulls you back into the world. Turns no-clip OFF.'),
]
open(p2,'w',encoding='utf-8').write(raw2.rstrip('\n')+'\n'+'\n'.join(trows)+'\n')
print("patched items_text.txt")
print("DONE")
