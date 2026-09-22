#!/usr/bin/env python3
"""
Trait bonuses from equipment STACK (v21, owner request): three items giving +1/+2 STR
now add up, instead of only the highest one applying.

Reversed from the 4.2.2 base (see deobf/STACK_TRAITS_SPEC.md):
  CharacterInventory.u()V rebuilds `traits:[I` (6 entries, TRAIT_STR..TRAIT_PER) on every
  equip change. Per trait index v1 it walks the 12 equipment slots (body, head, hands,
  legs, feet, mainhand, offhand unless two-handed, ring, ring2, belt, cloak, necklace)
  and keeps a running MAX in v2:
      v3 = Rules.c(slot).a(v1)          ; the item's bonus to that trait
      if-le v3, v2, :skip               ; not bigger -> ignore
      v2 = Rules.c(slot).a(v1)          ; reload it as the new max
  The body slot seeds v2 (its bonus if > 0, else 0). The result is stored with
  `aput v2, traits, v1`, and SheetBonus.a() adds traits[i] to the base trait.

  Patch: in each of the 11 compare blocks, `if-le v3, v2` -> `if-lez v3` and the
  3-instruction reload -> `add-int/2addr v2, v3`. So v2 becomes the SUM of the positive
  bonuses. Negative values are still ignored, exactly like vanilla's max (which never
  went below the 0 seed). Register types are unchanged (v2/v3 stay int throughout).

  CharacterInventory.r()Z is the "more than one item boosts the same trait" check that
  the inventory screen (e/a/d/e/h) uses to pop MSG_ITEM_NOT_STACK ("Only the highest one
  will apply!"). With stacking that warning is false, so r() now returns false.

  texts.txt HELP_ATTRIBUTE_ITEM (the one-time tip) is reworded to match.
"""
import re

w = "."
INV = 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;'
p = f'{w}/smali/net/fdgames/GameEntities/CharacterSheet/CharacterInventory.smali'
s = open(p, encoding='utf-8').read()

# --- 1) u()V: max -> sum --------------------------------------------------------
ustart = s.index('.method public u()V\n')
uend = s.index('.end method', ustart)
body = s[ustart:uend]
loop = body.index(f'    iput-object v1, p0, {INV}->traits:[I\n')   # the traits rebuild
head, tail = body[:loop], body[loop:]

blk = re.compile(
    r'(    move-result v3\n\n)'
    r'    if-le v3, v2, (:\w+)\n\n'
    r'    iget v2, p0, ' + re.escape(INV) + r'->(slot_\w+):I\n\n'
    r'    invoke-static \{v2\}, Lnet/fdgames/Rules/Rules;->c\(I\)Lnet/fdgames/Rules/Item;\n\n'
    r'    move-result-object v2\n\n'
    r'    invoke-virtual \{v2, v1\}, Lnet/fdgames/Rules/Item;->a\(I\)I\n\n'
    r'    move-result v2\n\n'
    r'    \2\n')
slots = []


def _sub(m):
    slots.append(m.group(3))
    return (m.group(1) +
            f'    if-lez v3, {m.group(2)}\n\n'
            '    add-int/2addr v2, v3\n\n'
            f'    {m.group(2)}\n')


tail, n = blk.subn(_sub, tail)
assert n == 11, f"expected 11 max-compare blocks in u(), found {n}: {slots}"
assert set(slots) == {'slot_head', 'slot_hands', 'slot_legs', 'slot_feet',
                      'slot_mainhand', 'slot_offhand', 'slot_ring', 'slot_ring2',
                      'slot_belt', 'slot_cloak', 'slot_necklace'}, slots
assert 'if-le v3, v2' not in tail
s = s[:ustart] + head + tail + s[uend:]
print(f"patched CharacterInventory.u(): trait bonuses sum across {n + 1} slots")

# --- 2) r()Z: never warn "only the highest one will apply" ------------------------
rsig = '.method public r()Z\n    .locals 5\n'
assert s.count(rsig) == 1, "CharacterInventory.r() not found"
s = s.replace(rsig, rsig + '\n    const/4 v0, 0x0\n\n    return v0\n', 1)
print("patched CharacterInventory.r(): no 'does not stack' warning")
open(p, 'w', encoding='utf-8').write(s)

# --- 3) texts.txt: reword the one-time tip (English column; BOM/CRLF preserved) ----
p = f'{w}/assets/data/ui/strings/texts.txt'
raw = open(p, 'rb').read()
old = (b'Remember that, unlike other bonuses, the effect will not stack. If you find '
       b'another item raising the same attribute, only the best one will apply.')
new = (b'Bonuses to the same trait from different items stack, so several small '
       b'bonuses add up.')
assert raw.count(old) == 1, "HELP_ATTRIBUTE_ITEM English text not found"
open(p, 'wb').write(raw.replace(old, new, 1))
print("patched texts.txt: HELP_ATTRIBUTE_ITEM says trait bonuses stack")
print("DONE")
