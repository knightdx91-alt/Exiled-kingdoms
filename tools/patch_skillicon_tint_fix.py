#!/usr/bin/env python3
"""
Fix skill-icon backgrounds rendering with the wrong colour (owner saw all skill icons as
bright green on a Galaxy Z Fold 8; correct is grey for untrained, then yellow/green/blue/
red/purple by trained rank -- skill_bg0..5 in ui_icons.pack).

Root cause: SkillImage.draw (e/a/d/e/z) draws its background (`c`, the skill_bg<rank>
texture) and glyph (`b`) with whatever colour the SpriteBatch was left on by the
previously-drawn actor -- it only ever calls setColor for the ORANGE "highlight" path,
never resetting to white otherwise. skill_bg0 is a neutral grey, so it takes on the
inherited tint; if a green UI element (e.g. the green Donate button) drew just before, the
grey backgrounds come out green. Draw order/tint differs by GPU/driver, so it looked fine
on the owner's old device and green on the new one.

Fix: when the icon is NOT in its highlight state, set the batch colour to WHITE at the top
of draw() so the background/glyph render at their true texture colour. The highlight path
(sets ORANGE, restores the saved colour) is left untouched. The game does not tint locked/
unaffordable skills via colour (they use a different glyph + skill_bg0), so forcing white
removes nothing intended.

Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).
"""
w = "."
p = f'{w}/smali/e/a/d/e/z.smali'
s = open(p, encoding='utf-8').read()

# draw() begins by reading the highlight flag `d`. Insert, before that, a white reset
# taken only when `d` is false.
anchor = ('.method public draw(Lcom/badlogic/gdx/graphics/g2d/Batch;F)V\n'
          '    .locals 12\n\n'
          '    iget-boolean p2, p0, Le/a/d/e/z;->d:Z\n')
assert s.count(anchor) == 1, "SkillImage.draw() header not found"
inject = ('.method public draw(Lcom/badlogic/gdx/graphics/g2d/Batch;F)V\n'
          '    .locals 12\n\n'
          '    iget-boolean v0, p0, Le/a/d/e/z;->d:Z\n\n'
          '    if-nez v0, :ekwhite_done\n\n'
          '    sget-object v0, Lcom/badlogic/gdx/graphics/Color;->WHITE:Lcom/badlogic/gdx/graphics/Color;\n\n'
          '    invoke-interface {p1, v0}, Lcom/badlogic/gdx/graphics/g2d/Batch;->setColor(Lcom/badlogic/gdx/graphics/Color;)V\n\n'
          '    :ekwhite_done\n'
          '    iget-boolean p2, p0, Le/a/d/e/z;->d:Z\n')
s = s.replace(anchor, inject, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched SkillImage.draw(): skill-icon background drawn at true colour (was inheriting a stale tint)")
print("DONE")
