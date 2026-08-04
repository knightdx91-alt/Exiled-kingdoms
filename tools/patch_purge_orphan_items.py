#!/usr/bin/env python3
"""
Completely remove orphaned items from the player's backpack -- items whose row no longer
exists in items.txt, i.e. the cheat items (Tome of Renown / Phase Stone / Anchor Stone,
ids 9990-9992) dropped in v13. v15's InventorySlotImage guard stopped the *draw* NPE, but
a save that still holds such an item keeps hitting other null-item paths; the owner asked
to have them gone entirely.

Mechanism:
  * The backpack is GameData.backpack (a Helpers/Items with a fixed 20-slot itemList[I +
    stackUnits[I). Rules.c(id) returns the Item for an id, or null if the row is gone.
    (Rules.f(id) is NOT a validity test -- it returns the `stackable` flag, false for
    both unknown and non-stackable items -- so the purge keys on Rules.c(id) == null.)
  * Add Items.ekPurge(): walk the 20 slots and zero any whose id resolves to null.
  * Call it from CharacterSheet.l() (the accessor that returns the backpack), so the bag
    is cleaned every time the game reads it -- inventory screen, character screen, HUD --
    before anything can dereference the dead item. A quick-slot pointing at a now-removed
    item then just resolves to "not in bag" and renders as the guarded empty slot.

Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).
"""
w = "."
ITEMS = 'Lnet/fdgames/GameEntities/Helpers/Items;'
RULES = 'Lnet/fdgames/Rules/Rules;'
ITEM = 'Lnet/fdgames/Rules/Item;'
SHEET = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;'
GD = 'Lnet/fdgames/GameWorld/GameData;'

# 1) Items.ekPurge(): zero every slot whose id no longer resolves to an Item.
p = f'{w}/smali/net/fdgames/GameEntities/Helpers/Items.smali'
s = open(p, encoding='utf-8').read()
assert 'ekPurge' not in s, "Items already patched"
purge = f'''
.method public ekPurge()V
    .locals 3

    invoke-direct {{p0}}, {ITEMS}->i()V

    const/4 v0, 0x0

    :ekpg_loop
    const/16 v1, 0x14

    if-ge v0, v1, :ekpg_done

    invoke-virtual {{p0}}, {ITEMS}->d()[I

    move-result-object v1

    aget v1, v1, v0

    if-eqz v1, :ekpg_next

    invoke-static {{v1}}, {RULES}->c(I){ITEM}

    move-result-object v1

    if-nez v1, :ekpg_next

    invoke-virtual {{p0}}, {ITEMS}->d()[I

    move-result-object v1

    const/4 v2, 0x0

    aput v2, v1, v0

    iget-object v1, p0, {ITEMS}->stackUnits:[I

    const/4 v2, 0x0

    aput v2, v1, v0

    :ekpg_next
    add-int/lit8 v0, v0, 0x1

    goto :ekpg_loop

    :ekpg_done
    return-void
.end method
'''
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '\n' + purge + s[tail:]
open(p, 'w', encoding='utf-8').write(s)
print("patched Items: +ekPurge() (drops slots whose item row is gone)")

# 2) CharacterSheet.l(): purge the backpack before handing it out.
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterSheet.smali'
s = open(p, encoding='utf-8').read()
anchor = (f'    iget-object v0, v0, {GD}->backpack:{ITEMS}\n\n'
          '    return-object v0\n')
# l() is the only method whose body is exactly "get GameData, read backpack, return it".
assert s.count(anchor) == 1, "CharacterSheet.l() backpack return not uniquely found"
s = s.replace(anchor,
              f'    iget-object v0, v0, {GD}->backpack:{ITEMS}\n\n'
              '    if-eqz v0, :ekl_ret\n\n'
              f'    invoke-virtual {{v0}}, {ITEMS}->ekPurge()V\n\n'
              '    :ekl_ret\n'
              '    return-object v0\n', 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched CharacterSheet.l(): backpack is purged of orphaned items on access")
print("DONE")
