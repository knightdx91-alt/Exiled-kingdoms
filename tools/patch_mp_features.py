#!/usr/bin/env python3
"""MP mod gameplay features 6-11 on our 4.2.2 base (spec: deobf/MP_FEATURES_SPEC.md).

Run from the build workdir AFTER patch_multiplayer.py (which compiles the Java glue, including
EkFeat). No cheats: the mod's ModMenuDialog toggles are not ported.
  6  Raise Difficulty button (options window)
  7  equipment upgrades (+0..+10 per item id, gold + gems; stats, UPGRADE button, preview, badge)
  8  vault button in the inventory, bag-of-holding tabs 1-5
  9  Recover/rest publish the restored HP to other players at once
  10 forward shot without target -- already vanilla in 4.2.2 (Character.v0), nothing to do
  11 SAF backup export/import pickers on Android 4.4+ (old path kept below that)
"""
import os, re

DST = 'smali'
EK = 'Lnet/fdgames/ek/android/lan/EkFeat;'
assert os.path.exists(f'{DST}/net/fdgames/ek/android/lan/EkFeat.smali'), "EkFeat glue missing: run patch_multiplayer.py first"

ROWS = {('strings.txt', 'RAISE_DIFFICULTY'): 'RAISE_DIFFICULTY\tRaise Difficulty\tSubir Dificultad\tУвеличить сложность\tAumentar dificuldade\tSchwierigkeit erhöhen\tZwiększ trudność\tZvyšte obtížnost\tZorluğu artırın\tAumenta difficoltà\t', ('texts.txt', 'RAISE_DIFFICULTY_CONFIRM'): 'RAISE_DIFFICULTY_CONFIRM\tAre you sure you want to raise the difficulty of this game?\t¿Estás seguro de que quieres subir la dificultad de esta partida?\tВы уверены, что хотите увеличить сложность этой игры?\tTem certeza de que deseja aumentar a dificuldade deste jogo?\tSind Sie sicher, dass Sie die Schwierigkeit dieses Spiels erhöhen möchten?\tCzy na pewno chcesz zwiększyć poziom trudności tej gry?\tOpravdu chcete zvýšit obtížnost této hry?\tBu oyunun zorluğunu artırmak istediğinizden emin misiniz?\tSei sicuro di voler aumentare la difficoltà di questo gioco?\t', ('texts.txt', 'DIF_RAISED'): "DIF_RAISED\tDifficulty has been raised. However some changes may not take effect until you reset the area by sleeping at the Inn.\tSe ha aumentado la dificultad. Sin embargo, es posible que algunos cambios no surtan efecto hasta que restablezca el área durmiendo en la posada.\tСложность увеличена. Однако некоторые изменения могут не вступить в силу до тех пор, пока вы не сбросите настройки области, спав в гостинице.\tA dificuldade foi aumentada. No entanto, algumas alterações podem não ter efeito até que você reinicie a área dormindo na pousada.\tDie Schwierigkeit wurde erhöht. Einige Änderungen werden jedoch möglicherweise erst wirksam, wenn Sie den Bereich zurücksetzen, indem Sie im Inn schlafen.\tPoziom trudności został podniesiony. Jednak niektóre zmiany mogą nie odnieść efektu, dopóki nie zresetujesz obszaru, śpiąc w gospodzie.\tObtížnost byla zvýšena. Některé změny se však nemusí projevit, dokud nevynulujete oblast spaním v hostinci.\tZorluk artırıldı. Ancak, misafirhanede uyuyarak alanı sıfırlayana kadar bazı değişiklikler etkili olmayabilir.\tLa difficoltà è stata aumentata. Tuttavia, alcune modifiche potrebbero non avere effetto finché non ripristini l'area dormendo nella locanda.\t"}


def method_span(t, header_tail):
    ms = [m for m in re.finditer(r'^\.method [^\n]*$', t, re.M) if m.group(0).endswith(' ' + header_tail)]
    assert len(ms) == 1, f"method {header_tail}: {len(ms)} matches"
    st = ms[0].start()
    return st, t.index('.end method', st) + len('.end method')


def edit_method(cls, header_tail, fn, what):
    path = f'{DST}/{cls}.smali'
    t = open(path, encoding='utf-8').read()
    a, b = method_span(t, header_tail)
    body = fn(t[a:b])
    assert body != t[a:b], f"{what}: no change"
    open(path, 'w', encoding='utf-8').write(t[:a] + body + t[b:])
    print(f"F: {what}")


def sub1(pattern, repl, text, what, flags=0):
    new, k = re.subn(pattern, repl, text, flags=flags)
    assert k == 1, f"{what}: anchor matched {k}x"
    return new


def add_methods(cls, text, what):
    path = f'{DST}/{cls}.smali'
    t = open(path, encoding='utf-8').read()
    tail = t.rindex('.end method') + len('.end method')
    open(path, 'w', encoding='utf-8').write(t[:tail] + '\n\n' + text.strip('\n') + '\n' + t[tail:])
    print(f"F: {what}")


def add_field(cls, decl):
    path = f'{DST}/{cls}.smali'
    t = open(path, encoding='utf-8').read()
    i = t.index('\n.field ')
    open(path, 'w', encoding='utf-8').write(t[:i] + '\n' + decl + t[i:])


def wrap_method(cls, header_tail, new_name, wrapper, what):
    path = f'{DST}/{cls}.smali'
    t = open(path, encoding='utf-8').read()
    a, b = method_span(t, header_tail)
    hdr_end = t.index('\n', a)
    hdr = t[a:hdr_end]
    name = header_tail[:header_tail.index('(')]
    sig = header_tail[len(name):]
    mods = ['private' if x in ('public', 'protected') else x for x in hdr.split()[1:-1]]
    t = t[:a] + '.method ' + ' '.join(mods + [new_name + sig]) + t[hdr_end:]
    b = t.index('.end method', a) + len('.end method')
    t = t[:b] + '\n\n' + wrapper.strip('\n') + '\n' + t[b:]
    open(path, 'w', encoding='utf-8').write(t)
    print(f"F: {what}")


# ======================= strings (the mod's rows, all 11 columns) ==========================
for (fname, key), row in sorted(ROWS.items()):
    p = f'assets/data/ui/strings/{fname}'
    raw = open(p, 'rb').read()
    txt = raw.decode('utf-8-sig')
    if re.search(r'(^|\n)' + re.escape(key) + r'\t', txt):
        continue
    nl = '\r\n' if '\r\n' in txt else '\n'
    if not txt.endswith('\n'):
        txt += nl
    txt += row + nl
    open(p, 'wb').write((b'\xef\xbb\xbf' if raw.startswith(b'\xef\xbb\xbf') else b'') + txt.encode('utf-8'))
    print(f"F: + {fname}:{key}")

# ======================= 6. Raise Difficulty =================================================
GD = 'Lnet/fdgames/GameWorld/GameData;'
add_methods('net/fdgames/GameWorld/GameData', f'''
.method public ekRaise()V
    .locals 2

    iget v0, p0, {GD}->difficulty:I

    const/4 v1, 0x4

    if-ne v0, v1, :r1

    const/4 v0, 0x3

    goto :done

    :r1
    const/4 v1, 0x3

    if-ne v0, v1, :r2

    const/4 v0, 0x0

    goto :done

    :r2
    if-nez v0, :r3

    const/4 v0, 0x1

    goto :done

    :r3
    const/4 v1, 0x1

    if-ne v0, v1, :keep

    const/4 v0, 0x2

    goto :done

    :keep
    return-void

    :done
    iput v0, p0, {GD}->difficulty:I

    return-void
.end method

.method public ekCanRaise()Z
    .locals 2

    iget v0, p0, {GD}->difficulty:I

    const/4 v1, 0x2

    if-eq v0, v1, :no

    const/4 v0, 0x1

    return v0

    :no
    const/4 v0, 0x0

    return v0
.end method''', "GameData.ekRaise() (their raiseDifficulty: 4->3->0->1->2) + ekCanRaise()")

# the three listener/dialog classes: clones of our Lower Difficulty ones, keys and call swapped
B0 = 'e/a/d/b0'
for src, dst in (('b0$a', 'b0$ekR'), ('b0$a$a', 'b0$ekR$a'), ('b0$a$a$a', 'b0$ekR$a$a')):
    t = open(f'{DST}/e/a/d/{src}.smali', encoding='utf-8').read()
    t = t.replace('Le/a/d/b0$a$a$a;', 'Le/a/d/b0$ekR$a$a;').replace('Le/a/d/b0$a$a;', 'Le/a/d/b0$ekR$a;').replace('Le/a/d/b0$a;', 'Le/a/d/b0$ekR;')
    t = t.replace('"RED_DIFFICULTY_CONFIRM"', '"RAISE_DIFFICULTY_CONFIRM"').replace('"DIF_LOWERED"', '"DIF_RAISED"')
    t = t.replace(f'invoke-virtual {{p1}}, {GD}->b()V', f'invoke-virtual {{p1}}, {GD}->ekRaise()V')
    t = re.sub(r'\n\.source [^\n]*', '', t)
    open(f'{DST}/e/a/d/{dst}.smali', 'w', encoding='utf-8').write(t)
t = open(f'{DST}/e/a/d/b0$ekR$a.smali', encoding='utf-8').read()
assert 'ekRaise()V' in t and 'DIF_RAISED' in t
assert 'RAISE_DIFFICULTY_CONFIRM' in open(f'{DST}/e/a/d/b0$ekR.smali', encoding='utf-8').read()
print("F: cloned b0$a* -> b0$ekR* (Raise Difficulty confirm + result dialogs)")

TB = 'Lcom/badlogic/gdx/scenes/scene2d/ui/TextButton;'
add_field(B0, f'.field private ekRaise:{TB}\n')
edit_method(B0, '<init>()V', lambda m: sub1(
    r'(    iput-object v0, p0, Le/a/d/b0;->g:' + re.escape(TB) + r'\n)',
    r'\1' + f'''
    new-instance v0, Le/a/d/u;

    const-string v2, "RAISE_DIFFICULTY"

    invoke-static {{v2}}, Lnet/fdgames/Helpers/GameString;->a(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {{}}, Lnet/fdgames/assets/Assets;->e()Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;

    move-result-object v3

    invoke-direct {{v0, v2, v3, v4}}, Le/a/d/u;-><init>(Ljava/lang/String;Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;Ljava/lang/String;)V

    iput-object v0, p0, Le/a/d/b0;->ekRaise:{TB}
''', m, 'create raise button'), "b0.<init>: create the RAISE_DIFFICULTY button")
edit_method(B0, '<init>()V', lambda m: sub1(
    r'(    iget-object v0, p0, Le/a/d/b0;->g:' + re.escape(TB) + r'\n\n    invoke-virtual \{p0, v0\}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->add\(Lcom/badlogic/gdx/scenes/scene2d/Actor;\)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;\n\n    move-result-object v0\n\n    invoke-virtual \{v0, v2\}, Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;->space\(F\)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;\n)',
    r'\1' + f'''
    invoke-virtual {{p0}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->row()Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    iget-object v0, p0, Le/a/d/b0;->ekRaise:{TB}

    invoke-virtual {{p0, v0}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->add(Lcom/badlogic/gdx/scenes/scene2d/Actor;)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    move-result-object v0

    invoke-virtual {{v0, v2}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;->space(F)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;

    iget-object v0, p0, Le/a/d/b0;->ekRaise:{TB}

    new-instance v1, Le/a/d/b0$ekR;

    invoke-direct {{v1, p0}}, Le/a/d/b0$ekR;-><init>(Le/a/d/b0;)V

    invoke-virtual {{v0, v1}}, Lcom/badlogic/gdx/scenes/scene2d/Actor;->addListener(Lcom/badlogic/gdx/scenes/scene2d/EventListener;)Z
''', m, 'layout raise row'), "b0.<init>: Raise row under Lower + listener")
add_methods(B0, f'''
.method private ekRefreshRaise()V
    .locals 3

    iget-object v0, p0, Le/a/d/b0;->ekRaise:{TB}

    if-eqz v0, :done

    const-string v1, "RAISE_DIFFICULTY"

    invoke-static {{v1}}, Lnet/fdgames/Helpers/GameString;->a(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {{v0, v1}}, {TB}->setText(Ljava/lang/String;)V

    invoke-virtual {{v0}}, {TB}->getLabel()Lcom/badlogic/gdx/scenes/scene2d/ui/Label;

    move-result-object v1

    const v2, 0x3f666666    # 0.9f

    invoke-virtual {{v1, v2}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Label;->setFontScale(F)V

    invoke-static {{}}, {GD}->O()Lnet/fdgames/GameWorld/GameData;

    move-result-object v1

    invoke-virtual {{v1}}, {GD}->ekCanRaise()Z

    move-result v1

    xor-int/lit8 v1, v1, 0x1

    invoke-virtual {{v0, v1}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Button;->setDisabled(Z)V

    :done
    return-void
.end method''', "b0.ekRefreshRaise()")
edit_method(B0, 'a()V', lambda m: sub1(r'(\n    :goto_75\n)', r'\1    invoke-direct {p0}, Le/a/d/b0;->ekRefreshRaise()V\n\n', m, 'refresh'),
            "b0.a(): refresh Raise (disabled at Ironman)")

# ======================= 7. equipment upgrades ================================================
CS = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
CI = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;'
add_methods('net/fdgames/GameEntities/CharacterSheet/CharacterSheet', f'''
.method public ekUpgMain()I
    .locals 1

    iget-object v0, p0, {CS}->hardcoded_weapon:Lnet/fdgames/Rules/WeaponStats;

    if-nez v0, :none

    iget-object v0, p0, {CS}->inventory:{CI}

    iget v0, v0, {CI}->slot_mainhand:I

    invoke-static {{v0}}, {EK}->upgLevel(I)I

    move-result v0

    return v0

    :none
    const/4 v0, 0x0

    return v0
.end method''', "CharacterSheet.ekUpgMain(): main-hand upgrade level (0 with a hardcoded weapon)")
# damage bonus (their m()): + L
edit_method('net/fdgames/GameEntities/CharacterSheet/CharacterSheet', 'm()I', lambda m: sub1(
    r'(    invoke-static \{p0, v0\}, Lnet/fdgames/GameEntities/CharacterSheet/SheetBonus;->c\(' + re.escape(CS) + r'Lnet/fdgames/Rules/WeaponStats\$weaponType;\)I\n\n    move-result v0\n)(\n    return v0\n)',
    r'\1' + f'\n    invoke-virtual {{p0}}, {CS}->ekUpgMain()I\n\n    move-result p0\n\n    add-int/2addr v0, p0\n' + r'\2', m, 'dmg bonus'),
    "CharacterSheet.m(): damage bonus + upgrade level")
# min/max (their B(Z)): +4L/10 / +7L/10
wrap_method('net/fdgames/GameEntities/CharacterSheet/CharacterSheet', 'c(Z)I', 'ekC', f'''
.method public c(Z)I
    .locals 2

    invoke-direct {{p0, p1}}, {CS}->ekC(Z)I

    move-result v0

    invoke-virtual {{p0}}, {CS}->ekUpgMain()I

    move-result v1

    invoke-static {{v1, p1}}, {EK}->upgMinMax(IZ)I

    move-result v1

    add-int/2addr v0, v1

    return v0
.end method''', "CharacterSheet.c(Z): min/max damage + upgrade")
# secondary (elemental) damage of the main hand: + L/3
edit_method('net/fdgames/GameEntities/CharacterSheet/CharacterSheet', 'a(FIZZ)Lnet/fdgames/GameEntities/Helpers/DamageData;', lambda m: sub1(
    r'(    invoke-virtual \{p4\}, ' + re.escape(CI) + r'->j\(\)Lnet/fdgames/Rules/WeaponStats;\n\n    move-result-object p4\n\n    iget p4, p4, Lnet/fdgames/Rules/WeaponStats;->secondary_Damage:I\n)',
    r'\1' + f'\n    iget-object v3, p0, {CS}->inventory:{CI}\n\n    iget v3, v3, {CI}->slot_mainhand:I\n\n    invoke-static {{p4, v3}}, {EK}->upgElemental(II)I\n\n    move-result p4\n', m, 'elemental'),
    "CharacterSheet damage roll: elemental + L/3")
# armor/HP/mana from the sum over all equipped slots (theirs: end of the inventory recompute)
slots = ['slot_body', 'slot_head', 'slot_hands', 'slot_legs', 'slot_feet', 'slot_mainhand', 'slot_offhand',
         'slot_ring', 'slot_ring2', 'slot_belt', 'slot_cloak', 'slot_necklace']
body = []
for s in slots:
    if s == 'slot_offhand':
        body.append(f'    iget-boolean v1, p0, {CI}->twohander:Z\n\n    if-nez v1, :no_off\n')
    body.append(f'    iget v1, p0, {CI}->{s}:I\n\n    invoke-static {{v1}}, {EK}->upgLevel(I)I\n\n    move-result v1\n\n    add-int/2addr v0, v1\n')
    if s == 'slot_offhand':
        body.append('    :no_off\n')
add_methods('net/fdgames/GameEntities/CharacterSheet/CharacterInventory', '.method public ekUpgTotal()I\n    .locals 2\n\n    const/4 v0, 0x0\n\n' + '\n'.join(body) + '\n    return v0\n.end method',
            "CharacterInventory.ekUpgTotal(): sum of equipped upgrade levels")
edit_method('net/fdgames/GameEntities/CharacterSheet/CharacterInventory', 'u()V', lambda m: sub1(
    r'(\n    :cond_\w+\n)(    invoke-static \{\}, Lnet/fdgames/GameLevel/GameLevelData;->s\(\)Lnet/fdgames/GameLevel/GameLevelData;\n\n    move-result-object v0\n\n    invoke-virtual \{v0\}, Lnet/fdgames/GameLevel/GameLevelData;->e\(\)V\n\n    return-void\n)',
    r'\1' + f'''    invoke-virtual {{p0}}, {CI}->ekUpgTotal()I

    move-result v0

    if-lez v0, :ekf_noupg

    iget v1, p0, {CI}->DefenseBonus:I

    add-int/2addr v1, v0

    iput v1, p0, {CI}->DefenseBonus:I

    mul-int/lit8 v0, v0, 0x2

    iget v1, p0, {CI}->HPBonus:I

    add-int/2addr v1, v0

    iput v1, p0, {CI}->HPBonus:I

    iget v1, p0, {CI}->ManaBonus:I

    add-int/2addr v1, v0

    iput v1, p0, {CI}->ManaBonus:I

    :ekf_noupg
''' + r'\2', m, 'recompute tail'),
    "CharacterInventory.u(): armor +S, HP +2S, mana +2S")

# CharacterWindow: UPGRADE button on an equipped item
H = 'Le/a/d/e/h;'
U = 'Le/a/d/u;'
add_methods('e/a/d/e/h', f'''
.method private ekUpgBtn()V
    .locals 2

    iget-object v0, p0, {H}->e0:{U}

    invoke-direct {{p0}}, {H}->v()I

    move-result v1

    invoke-static {{v0, v1}}, {EK}->upgButton({TB}I)V

    return-void
.end method

.method private ekUpgrade()V
    .locals 2

    iget-object v0, p0, {H}->e0:{U}

    invoke-virtual {{v0}}, Lcom/badlogic/gdx/scenes/scene2d/ui/Button;->isDisabled()Z

    move-result v0

    if-nez v0, :done

    invoke-direct {{p0}}, {H}->v()I

    move-result v0

    iget-object v1, p0, {H}->p:Lnet/fdgames/GameEntities/Character;

    invoke-static {{v0, v1}}, {EK}->tryUpgrade(ILnet/fdgames/GameEntities/Character;)Z

    move-result v0

    if-eqz v0, :done

    invoke-virtual {{p0}}, {H}->l()V

    :done
    return-void
.end method

.method private ekVaultBtn(Lcom/badlogic/gdx/scenes/scene2d/ui/Table;)V
    .locals 2

    sget v0, {H}->N0:I

    if-nez v0, :done

    iget-object v0, p0, {H}->p:Lnet/fdgames/GameEntities/Character;

    invoke-virtual {{v0}}, Lnet/fdgames/GameEntities/GameObject;->m()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :done

    sget v0, {H}->P0:F

    sget v1, {H}->S0:F

    invoke-static {{p0, p1, v0, v1}}, {EK}->addVaultButton({H}Lcom/badlogic/gdx/scenes/scene2d/ui/Table;FF)V

    :done
    return-void
.end method

.method private ekBagTabs()V
    .locals 4

    iget-object v0, p0, {H}->o:Lcom/badlogic/gdx/scenes/scene2d/ui/Table;

    sget-object v1, {H}->M0:Lnet/fdgames/GameEntities/Helpers/Lootable;

    sget v2, {H}->P0:F

    sget v3, {H}->S0:F

    invoke-static {{p0, v0, v1, v2, v3}}, {EK}->addBagTabs({H}Lcom/badlogic/gdx/scenes/scene2d/ui/Table;Lnet/fdgames/GameEntities/Helpers/Lootable;FF)V

    return-void
.end method''', "CharacterWindow helpers: ekUpgBtn/ekUpgrade/ekVaultBtn/ekBagTabs")
edit_method('e/a/d/e/h', 'l()V', lambda m: sub1(
    r'(    const-string v5, "UNEQUIP"\n(?:.*\n){1,14}?    iget-object v4, p0, Le/a/d/e/h;->e0:Le/a/d/u;\n\n    invoke-virtual \{v4, v3\}, Lcom/badlogic/gdx/scenes/scene2d/Actor;->setVisible\(Z\)V\n)',
    r'\1\n    invoke-direct {p0}, Le/a/d/e/h;->ekUpgBtn()V\n', m, 'upgrade button'),
    "CharacterWindow.l(): UPGRADE button for the selected equipped item")
edit_method('e/a/d/e/h', 't()V', lambda m: sub1(
    r'^(\.method private t\(\)V\n    \.locals \d+\n)',
    r'\1' + '\n    sget v0, Le/a/d/e/h;->X0:I\n\n    const/4 v1, 0x2\n\n    if-ne v0, v1, :ekf_notupg\n\n'
    '    invoke-direct {p0}, Le/a/d/e/h;->ekUpgrade()V\n\n    return-void\n\n    :ekf_notupg\n', m, 't prepend', re.M),
    "CharacterWindow.t(): second button on an equipped item = upgrade")
edit_method('e/a/d/e/t', 'a(I)V', lambda m: sub1(
    r'(    invoke-virtual \{v2, v1\}, Ljava/lang/StringBuilder;->append\(Ljava/lang/String;\)Ljava/lang/StringBuilder;\n)(\n    invoke-virtual \{v2\}, Ljava/lang/StringBuilder;->toString\(\)Ljava/lang/String;\n\n    move-result-object v1\n\n    invoke-virtual \{v0, v1\}, Lcom/badlogic/gdx/scenes/scene2d/ui/Label;->setText)',
    r'\1\n    invoke-static {v2, p1}, ' + EK + r'->upgPreview(Ljava/lang/StringBuilder;I)V\n' + r'\2', m, 'preview'),
    "ItemPreviewTable.a(I): upgrade level + next cost")
BATCH = 'Lcom/badlogic/gdx/graphics/g2d/Batch;'
wrap_method('e/a/d/e/r', f'draw({BATCH}F)V', 'ekDraw', f'''
.method public draw({BATCH}F)V
    .locals 4

    invoke-direct {{p0, p1, p2}}, Le/a/d/e/r;->ekDraw({BATCH}F)V

    iget-object v0, p0, Le/a/d/e/r;->f:Lcom/badlogic/gdx/graphics/g2d/BitmapFont;

    iget v1, p0, Le/a/d/e/r;->g:I

    sget v2, Le/a/d/e/r;->s:F

    invoke-static {{p0, p1, v0, v1, v2}}, {EK}->drawBadge(Lcom/badlogic/gdx/scenes/scene2d/Actor;{BATCH}Lcom/badlogic/gdx/graphics/g2d/BitmapFont;IF)V

    return-void
.end method''', "InventorySlotImage.draw: +L badge")

# ======================= 8. storage shortcuts ================================================
edit_method('e/a/d/e/h', 'a(ILnet/fdgames/GameEntities/Helpers/Lootable;Lnet/fdgames/GameEntities/Character;)V', lambda m: sub1(
    r'(    invoke-virtual \{v4\}, Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;->right\(\)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;\n)(\n    iget-object v4, v0, Le/a/d/e/h;->F0:Lcom/badlogic/gdx/scenes/scene2d/ui/ImageButton;\n)',
    r'\1\n    invoke-direct {v0, v3}, Le/a/d/e/h;->ekVaultBtn(Lcom/badlogic/gdx/scenes/scene2d/ui/Table;)V\n' + r'\2', m, 'vault button'),
    "CharacterWindow.a(..): vault button in the bottom-right row")
edit_method('e/a/d/e/h', 'u()V', lambda m: sub1(
    r'(\n    :cond_2ce\n)(    iget-object v0, p0, Le/a/d/e/h;->o:Lcom/badlogic/gdx/scenes/scene2d/ui/Table;\n)',
    r'\1    invoke-direct {p0}, Le/a/d/e/h;->ekBagTabs()V\n\n' + r'\2', m, 'bag tabs'),
    "CharacterWindow.u(): bag-of-holding tabs 1-5")

# ======================= 9. recover ===========================================================
for sig in ('I0()V', 'b(Z)V'):
    edit_method('net/fdgames/GameEntities/Final/Player', sig, lambda m: m.replace(
        '\n    return-void\n', f'\n    invoke-static {{}}, {EK}->afterRecover()V\n\n    return-void\n'),
        f"Player.{sig}: publish restored HP")

# ======================= 11. SAF backup ======================================================
for cls, fn in (('e/a/d/e1/a$a$a', 'safExport'), ('e/a/d/e1/k$a', 'safExport'),
                ('e/a/d/e1/a$c$a', 'safImport'), ('e/a/d/e1/l$a', 'safImport')):
    edit_method(cls, 'result(Ljava/lang/Object;)V', lambda m, fn=fn: sub1(
        r'(    const/4 v0, 0x1\n\n    if-ne p1, v0, :cond_\w+\n)',
        r'\1\n    invoke-static {}, ' + EK + '->' + fn + '()Z\n\n    move-result p1\n\n    if-eqz p1, :ekf_legacy\n\n    return-void\n\n    :ekf_legacy\n', m, cls),
        f"BackupWindow {cls}: {fn} picker on Android 4.4+")
MA = 'Lnet/fdgames/ek/android/MainActivity;'
wrap_method('net/fdgames/ek/android/MainActivity', 'onActivityResult(IILandroid/content/Intent;)V', 'ekOnActivityResult', f'''
.method protected onActivityResult(IILandroid/content/Intent;)V
    .locals 1

    invoke-static {{p0, p1, p2, p3}}, {EK}->onResult({MA}IILandroid/content/Intent;)Z

    move-result v0

    if-nez v0, :done

    invoke-direct {{p0, p1, p2, p3}}, {MA}->ekOnActivityResult(IILandroid/content/Intent;)V

    :done
    return-void
.end method''', "MainActivity.onActivityResult: SAF results first")
print("DONE")
