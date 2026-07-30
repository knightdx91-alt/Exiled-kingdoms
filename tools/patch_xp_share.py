#!/usr/bin/env python3
"""
Stop companions taxing the player's XP.
Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).

VANILLA (Player.k(I)V, == Player.O0(int) in the readable decompile):

    if (party.hasCompanion()) {
        int mine      = (int)(xp * 0.8f);          // <-- 20% TAX on every kill
        int companion = (int)(mine * 0.7f);        //     == 56% of the original xp
        mine      = sheet.h(mine);
        companion = companionSheet.h(companion);
        … companion gains XP, floating "+N xp", LEVEL_UP visual …
    } else {
        mine = sheet.h(xp);                        //     no companion == full XP
    }
    … player gains `mine` …

So travelling with Grissenda/Hirge/Adaon/Janod costs the player a flat fifth of ALL
experience -- kills, quest rewards (ScriptedAction GainXP routes here too), everything.
The companion's share is generated separately; it is not taken from the player's pot,
which makes the 0.8f purely a penalty for having company.

FIX: two float constants, no structural change (so nothing for the verifier to dislike).
  0.8f -> 1.0f   the player always receives the full amount, companion or not.
  0.7f -> 0.56f  the companion still receives exactly what it did in vanilla
                 (0.8 x 0.7 = 0.56 of the original xp), so its levelling is untouched.
"""
import re

w = "."
p = f'{w}/smali/net/fdgames/GameEntities/Final/Player.smali'
s = open(p, encoding='utf-8').read()

TAX = '0x3f4ccccd'      # 0.8f -- the player's share when a companion is present
ONE = '0x3f800000'      # 1.0f
SEVEN = '0x3f333333'    # 0.7f -- companion's share, relative to the taxed amount
FIFTYSIX = '0x3f0f5c29'  # 0.56f -- same share, now relative to the full amount

assert s.count(TAX) == 1, f"expected exactly 1 companion XP tax constant, found {s.count(TAX)}"
i = s.index(TAX)

# Sanity-check that this really is the XP block: the companion's 0.7f multiply must
# follow within a few instructions, and the player's sheet.h(I)I call after that.
window = s[i:i + 1200]
assert SEVEN in window, "companion 0.7f share not found next to the tax constant"
assert 'CharacterSheet;->h(I)I' in window, "XP modifier call not found in the block"

s = s[:i] + ONE + s[i + len(TAX):]

j = s.index(SEVEN, i)
assert j - i < 400, "companion share constant is further away than expected"
s = s[:j] + FIFTYSIX + s[j + len(SEVEN):]

open(p, 'w', encoding='utf-8').write(s)
print("patched Player XP: player keeps 100% with a companion (was 80%); "
      "companion share unchanged at 56%")
print("DONE")
