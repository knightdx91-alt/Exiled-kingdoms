#!/usr/bin/env python3
"""
Fix the character-details / inventory-grid crash (owner's EK_crash.txt, 2026-08-05):

    java.lang.NullPointerException
      at ...SpriteBatch.draw
      at ...TextureRegionDrawable.draw
      at ...InventorySlotImage.draw   (e/a/d/e/r)
      ... Stage.draw -> GameHUD.j

InventorySlotImage.a(I) (set the slot to an item id) scans Rules.a[] for the item, reads
its `icon`, and `Assets.b(icon)` -> TextureRegion. If the id is NOT in Rules.a[] (or the
icon asset is missing) the region register stays **null**, yet the code still builds
`new TextureRegionDrawable(null)` and stores it as `e`; draw() then draws `e` and NPEs
inside SpriteBatch.

This bites any save that still holds an item whose row was removed -- most notably the
cheat items (Tome of Renown / Phase Stone / Anchor Stone, ids 9990-9992) dropped from
items.txt in v13. draw() already skips a null `e`, so the fix is simply: when the region
comes back null, leave `e` null instead of wrapping it.

Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).
"""
w = "."
p = f'{w}/smali/e/a/d/e/r.smali'
s = open(p, encoding='utf-8').read()

# a(I) reaches :goto_2d with v1 = the resolved TextureRegion (or null when the item id
# was not found). `e` has already been set null just above, so a jump straight to the
# method's return label (:goto_66) leaves the slot icon-less instead of crashing.
anchor = ('    :goto_2d\n'
          '    invoke-direct {v2, v1}, Lcom/badlogic/gdx/scenes/scene2d/utils/'
          'TextureRegionDrawable;-><init>(Lcom/badlogic/gdx/graphics/g2d/TextureRegion;)V\n')
assert s.count(anchor) == 1, "InventorySlotImage.a(I) :goto_2d icon-build not found"
patched = ('    :goto_2d\n'
           '    if-eqz v1, :goto_66\n\n'
           '    invoke-direct {v2, v1}, Lcom/badlogic/gdx/scenes/scene2d/utils/'
           'TextureRegionDrawable;-><init>(Lcom/badlogic/gdx/graphics/g2d/TextureRegion;)V\n')
s = s.replace(anchor, patched, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched InventorySlotImage.a(I): null item icon -> empty slot, not an NPE")
print("DONE")
