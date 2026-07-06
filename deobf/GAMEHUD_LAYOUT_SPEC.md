# GameHUD — full in-game HUD layout & wiring (recovered spec)

Reversed from the real decompiled source, per the faithfulness rule. Primary sources:
- `decompiled/sources/n0/z.java` — **GameHUD** (1832 lines). Build = `v()`; per-frame = `R()`;
  mode switch = `J(int)`; all element fields + listeners.
- `decompiled/sources/n0/x.java`, `n0/a0.java`, `n0/y.java` — the three HUD `InputListener`
  inner classes (button handlers).
- `decompiled/sources/l0/b.java` — **GameScreen** input loop `c(float)` + touch handlers
  (`touchDown`, `l()` = attack/interact, `k()` = screen→world coords, tap-target).
- `decompiled/sources/o0/f.java` — **CharacterWindow** (the portrait screen), 1881 lines.
- `net/fdgames/assets/GameAssets.java`, `Assets.java` — asset/region names.

**Design resolution:** 1280×720 (`ENGINE_SPEC.md`). `ExiledKingdoms.f3378h` = **isDesktop**
(false on mobile/web — the web port is the mobile branch). Scale `f2927k0 =
min(H/720, W/1280)`.

---

## 0. Three HUD modes (`GameHUD.f2936a`, switched by `J(int)`)
- **0 = GAMEPLAY** — the combat HUD (everything below). Default.
- **1 = LOCAL MAP** — the current level's minimap (`q1` WorldMapImage), pan by drag.
- **2 = WORLD MAP** — the region/world map (`q1.a(CurrentLevel)`), pan by drag.
- BACK button (`K`) or the Esc/back key returns to mode 0 (`x` listener case 5 / `j()`).
- In modes 1/2, ALL gameplay widgets are hidden (see `J()` visibility table).

---

## 1. Element inventory (built in `GameHUD.v()`)

Each is a scene2d actor added to the HUD `Stage`. **Position given for mobile branch**
(origin bottom-left, y-up; `i3 = f2934r0 = 15*scale` is the base margin).

| # | Field | Widget | Where | Purpose |
|---|-------|--------|-------|---------|
| A | `f2940c` Touchpad | joystick | **bottom-left**, size `0.33*H`, at `(15,15)` | **MOVEMENT**. Polled each frame in `R()`: `D(knobX,knobY)` → `player.N/O`. |
| B | `f2942d` ImageButton | **ATTACK button** | **bottom-right**, table `H` at `(W-130*scale, 0)`, size `130*scale` | **ATTACK / auto-interact.** Style `GameAssets.b()` = `attackButton.png`. Listener `a0(3)`. |
| C | `D` (`e1` PlayerButton) | portrait+frame | **top-left**, in main table at `(15, H-…)` | Player portrait. Listener `x(...,1)` → `i()` = open CharacterWindow. |
| C-bars | `f2959t`,`f2960u`,`f2961v` (`n0.a` ADTProgressBar) | HP / mana / XP | stacked right of portrait; XP is a thin bar under it | red HP `t`, blue mana `u` (only if caster `h1()`), XP `v` (`i(1)`). |
| D | `E` ImageButton | companion portrait | **below player portrait** (table3) | Follower portrait; only when `party.j()`. Listener `x(...,2)` → `g()` opens companion sheet. |
| D-bars | `f2962w`,`f2963x` (`n0.b`),`f2964y` | companion HP/mana/XP | right of companion portrait | shown only with a follower. |
| E | `b0Var` (`b0`) | **gold** label | top, at `(W*0.33, H-…)` | coin count. Desktop-only listener opens log. |
| F | `P` (`u` ExtendedLabel) | area/time text | left, under portrait row | `GameData.v().u()` clock + area name. |
| G | `M` Label | location banner | bottom-left area | `m0.b.P().f2422g + FDUtils.h()` (area name; date). |
| H | `f2947g` Label | **console msg** | center, at `y=2/3 H` | transient `GameConsole` text (fades). |
| I | `F` Table + `B[8]` (`l1` SkillButton) | **skill bar** | **bottom-right**, above attack btn, 2-col grid `(W-(15+70)*2, 30)` | 8 assignable skills (slots 0-3 & 4-7). Tap → `k(i)` casts skill i. Backgrounds `skill_bg0..5`. |
| J | `G` Table + `C[5]` (`o0.i` InventorySlotImage) | **potion quickslots** | **bottom-center** at `(W*0.48, 15)` | 5 usable item slots. Tap → `b0(i)` uses item. Shows count + cooldown. |
| K | `f2965z[4]` (`v` FlashingImageButton) | **CONTEXT/interact buttons** | **top-right** (table6) at `(W-…, H-…)` | up to 4 "activable" actions (talk / open / pick-up / enter / sleep). Icon `loot.png`. Tap → `E(i)`. Hidden when nothing nearby. |
| L | `I`,`J` (`v`) | map / world-map buttons | table12, lower-left-ish | `I`→`h()` local map, `J`→ mode 2 world map. Regions `map`, `world`. |
| M | `f2944e`,`f2946f` (`v`) | settings ⚙ / save 💾 | table13 (**mobile only**, `if(!isDesktop)`) | `settings`→`a0(4)` opens SettingsWindow (`d0`); `save`→`x(...,0)` quicksave. Save is **disabled indoors**. |
| N | `L` (`f1` RecoverButton) | **rest/recover** | **left side**, at `(15, 0.44*H)` | Listener `x(...,6)` → `G()` = rest & heal. Icon `mend.png`. |
| O | `N` Image | windrose/compass | top-right, `windrose` region | decorative facing compass. |
| P | `A` (`v`) | "skill point available" | left, `locked` region | flashes when unspent skill points exist (`sheet.L()>0`). Listener `a0(0)`. |
| Q | `K` TextButton | BACK | table14, bottom-right | `BACK`; returns map modes → gameplay (`x` case 5). |

Windows added hidden, shown on demand: `V` CharacterWindow (`o0.f`), `W` TraitsWindow
(`o0.a0`), `X` SkillWindow (`o0.t`), `Y` SkillInfoWindow, plus Rest/Vault/Teleport/Castle/
Settings/Log/WorldMap etc. (see `UI_SPEC.md`).

---

## 2. The ATTACK button — exact behaviour (`f2942d`)
This is the piece the web port is missing. Two paths drive it, both call `GameScreen.l()`:
1. **Listener** `a0(3)` (attached line 628): `touchDown` → `l0.b.l()` and sets the
   continuous-attack flag `z.f2926j0 = true`; `touchUp` → `f2926j0 = false`.
2. **Held poll**: each frame `GameScreen.c()` does `if (z.v().A() && player.l0()) l();`
   where `A()` = `f2942d.isPressed()` and `l0()` = "has a valid attack". So **holding**
   the button auto-repeats attacks.

`GameScreen.l()` (l0/b.java:520):
```
if (Settings.b() /*auto-interact ON*/ && adjacentToNothingBlocking
     && player.numActivables > 0 && cooldownOK) {
    z.v().E(0);          // interact with the first context action instead
    return;
}
player.E0(-1);           // else: attack in the facing direction
f2315p = now + 0.8f;     // interact debounce
```
So the single attack button **doubles as interact** when you're standing on/next to an
interactable and auto-interact is enabled; otherwise it swings your weapon.

`player.E0(target)` = execute a melee/ranged attack (target id, or `-1` = free swing in
facing dir). Target selection for tap: `touchDown` (l0/b.java:1509, mobile path is the
non-desktop `else`… actually desktop tap-targets; mobile uses the joystick + attack btn).
On mobile, `GameLevel.f3099f` (hovered enemy id) is set by proximity; attack resolves
against the faced/nearest hostile.

---

## 3. Context / interact actions (`E(int)` and the `f2965z` buttons)
`player.activables[]` (max `numActivables`, ≤4 shown + 5 quickslots) are the world
interactions currently in reach. `S()` refreshes the `f2965z` button icons each frame.
`E(i)` dispatches by `activables[i].i()` (the activable **type**):

| type | action |
|------|--------|
| 1 | **pick up loot** on the ground (opens loot window `V.T(1, loot)`) |
| 2 | open a **known loot pile** (`GameLevelData.l(...)`) |
| 3 | **talk to NPC** (`.b()` conversation; `NO_TALK` if unavailable) |
| 4 | talk to a **StaticNPC** (`NO_TALK` if `r()`) |
| 5 | **enter shop / castle** area (`NOT_ALLOWED` if faction-blocked) → `g1` RestWindow |
| 6 | **harvest plant** (`YOU_FOUND` + add item to backpack) |
| 7 | open a **container** (`d.f().g(...)`) |
| 8 | **transition** / use door (`.e().K()`) |
| 9 | activate a **trigger** (`.h().Y(player)`) |
| 10 | **sleep at inn** → `SLEEP_CONFIRM` dialog → rest |
| 11 | **recall / teleport** (`.g().R()`) |

---

## 4. Quickslots & skill bar
- **Skill bar** `B[8]` (`l1` SkillButton): `Y()` fills each from `player.F1(i)`; `k(i)`
  triggers it; `I(str)`/`H()` set the "active skill" highlight; cooldown via `f2933q0`.
  Cast is gated by `GameScreen.o()` (not paused & off global cooldown `f2314o`).
- **Item quickslots** `C[5]` (`o0.i` InventorySlotImage): `W()` fills from
  `player.I1(i)` (item id) + `K1(i)` (count); `b0(i)` uses the item (potion → `OnUse`,
  spends mana if `manaCost>0`, plays `potion`/`buff1` sound). `f()`/`j()` cycle the
  active quickslot; `a0()` uses the current one.

---

## 5. Per-frame update (`GameHUD.R()`, called from `GameScreen.c()`)
- Recompute HP/mana/XP bar fills from `player.sheet` (`o()/z()`, `p()/A()`, XP via
  `Rules.b()` thresholds). Optional numeric labels if `Settings.l()`.
- Companion bars updated the same way when `party.j()`.
- Fire queued first-time HELP popups (movement, interaction, dungeon, items, attack,
  healing…) gated on flags `TIP_*` + elapsed time.
- Show combat/skill WARNING toasts (`WARNING_SNEAK/WHIRLWIND/CLEAVE/EVASION/CRUSADER`).
- `stage.act(delta)`; drive movement from the touchpad if mode 0 & no window open.

---

## 6. CharacterWindow — the portrait screen (`o0/f`, opened by `i()` → `V.T(0,null)`)
A scene2d `Window`, reused for 4 roles by the first arg of `T(role, lootable)`:
`0` = character sheet, `1` = loot/container/shop. Label keys present (exact on-screen text):

- **Tabs / sections:** `EQUIPMENT`, `BACKPACK`, `QUICKSLOTS`, plus buttons `JOURNAL`,
  `SKILLS`, `REPUTATION` (open sibling windows), `STAT_DETAILS`, `BACK`.
- **Stat blocks:** `ATTACK_STATS` (`DAMAGE`, `DPS`, `CRITICAL`, `SPEED`), `ARMOR`,
  `RESISTANCES` (fire/cold/shock/poison/holy/death icons from `ui_icons`), `EFFECT`.
- **Item actions:** `EQUIP`, `UNEQUIP`, `USE`, `DROP`, `TAKE`, `TAKE_ALL`,
  `STORE_CONTAINER`, `BUY`, `SELL`; warnings `MSG_ITEM_NOT_STACK`, `WARNING_CONTAINER`.
- **Widgets:** `S` (`o0.d` CharacterPreview = paper-doll render), 12 equipment
  `InventorySlotImage`s, a backpack grid, 5 quickslot images, damage/crit/armor detail
  tables (`o0.c`,`o0.b`,`o0.a`), arrow-key/`invTake`/`invDrop` navigation (l0/b.java:1410+).

Navigation while open (mobile): tap a backpack item → equip/use; tap an equipped slot →
unequip; the window's own Back closes it (`V.E()` / `j()`).

---

## 7. Assets the web port needs (all present under `recovered/assets/assets/data/`)
- `ui/attackButton.png` — **the attack button** (imageUp; imageDown = flipped copy).
- `ui/touchpad_base.png`, `ui/touchpad_knob.png` — movement joystick (already used).
- `ui/mend.png` — recover/rest button.
- `ui/bag_holding.png` — inventory icon; `sprites/loot.png` — context-action buttons.
- `graphics/ui_icons.pack` regions: `windrose`, `map`, `world`, `save`, `settings`,
  `locked`/`locked_gray`, `skill_bg0..5`, `shield`, `sword`, `heart`, `mana`, `redcross`,
  `bed`, `campfire`, `empty_slot_bg`, resist icons `fire/cold/shock/poison/holy/death/speed`.

---

## 8. Gap vs current web build (`web/src/hud.js`, `combat.js`)
Current HUD = placeholder: portrait + 4 emoji buttons (🛡 char / 📖 journal / 🎒 inv /
☰ menu) top-right + an emoji skill bar. Combat is **tap-an-enemy → auto-attack** only.

**Missing vs the real game (in priority order):**
1. **On-screen ATTACK button** (bottom-right, hold-to-repeat, doubles as interact). ← the
   owner's "no button to fight".
2. **Context/interact buttons** (top-right `f2965z`) for talk / open / pick-up / enter.
3. **Potion quickslot row** (5 slots, bottom-center) + **skill bar** as real slot buttons
   (bottom-right) instead of the emoji strip.
4. **Recover/rest button** (left).
5. **Companion portrait + bars** when a follower is present.
6. **Map / world-map buttons** → the two map modes.
7. Move the character/inventory/journal to the **portrait tap** (already partly wired) and
   drop the placeholder emoji button cluster; faithful `CharacterWindow` layout (§6).

Layout anchors to mirror: movement bottom-left, attack bottom-right, portrait+bars
top-left, context buttons top-right, quickslots bottom-center, skills bottom-right.
