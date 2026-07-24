import sys
w="."
# --- no-clip in GameMap e/a/c/b -> c(II)Z ---
p=f'{w}/smali/e/a/c/b.smali'; s=open(p,encoding='utf-8').read()
m='.method public c(II)Z\n    .registers 6\n'
assert s.count(m)==1, "walkability method signature not found"
s=s.replace(m, m+'''
    invoke-static {}, Lnet/fdgames/GameWorld/GameData;->O()Lnet/fdgames/GameWorld/GameData;

    move-result-object v0

    iget-object v0, v0, Lnet/fdgames/GameWorld/GameData;->gameVariables:Lnet/fdgames/GameWorld/GameVariables;

    const-string v1, "noclip"

    invoke-virtual {v0, v1}, Lnet/fdgames/GameWorld/GameVariables;->b(Ljava/lang/String;)I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :ekcheat_noclip_off

    return v1

    :ekcheat_noclip_off
''',1)
open(p,'w',encoding='utf-8').write(s)

# --- grant items in Player.y0()V ---
p=f'{w}/smali/net/fdgames/GameEntities/Final/Player.smali'; s=open(p,encoding='utf-8').read()
a=s.index('.method public y0()V'); b=s.index('.end method',a); body=s[a:b]
assert body.rstrip().endswith('return-void')
g=''
# Add-item-by-id in this 2023 base is CharacterSheet.a(I)Z (backpack.a(id) -> Rules.f(id));
# it is exactly what the original y0() uses for the starting weapon (sheet.a(0x1f5)).
# NOTE: CharacterInventory.a(I)Z is NOT add-by-id here -- it treats the arg as a backpack
# SLOT INDEX (Items.e(p1) -> array[p1]), so passing an item id (9990+) throws
# ArrayIndexOutOfBoundsException during the new-game inventory reset (the load-screen crash).
for h in ('0x2706','0x2707','0x2708'):
    g+=('    iget-object v0, p0, Lnet/fdgames/GameEntities/Character;->sheet:Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;\n\n'
        f'    const v1, {h}\n\n'
        '    invoke-virtual {v0, v1}, Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;->a(I)Z\n\n')
nb=body[:body.rindex('return-void')]+g+'    return-void\n'
open(p,'w',encoding='utf-8').write(s[:a]+nb+s[b:])

# --- items.txt: append 3 rows ---
p=f'{w}/assets/assets/data/rules/items.txt'; raw=open(p,encoding='utf-8').read()
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
 row(item_ID='9990',name='Tome of Renown',type='general',armor='0',value='-1',icon='book4',hp='0',mana='0',
     OnUse=';'.join(f'SetVariable#REP_{f},100' for f in reps)),
 row(item_ID='9991',name='Phase Stone',type='general',armor='0',value='-1',icon='ring1',hp='0',mana='0',OnUse='SetVariable#noclip,1'),
 row(item_ID='9992',name='Anchor Stone',type='general',armor='0',value='-1',icon='ring1',hp='0',mana='0',OnUse='SetVariable#noclip,0'),
]
open(p,'w',encoding='utf-8').write(raw.rstrip('\n')+'\n'+'\n'.join(rows)+'\n')
print("patched: noclip(e/a/c/b.c) + grant(Player.y0) + items.txt")
