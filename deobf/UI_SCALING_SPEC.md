# UI scaling on high-resolution screens (owner report 2026-09-23: "check boxes … SUPER small")

## Reversed
* Every stage is `new Stage()` (screen-pixel viewport); windows scale themselves by hand with
  `E = Gdx.graphics.getHeight() / 720` (e.g. Settings `e/a/d/k1`, in-game options `e/a/d/j1`:
  window 760E x 720E, slider knob min size set to E*…, fonts per resolution).
* Every checkbox uses one style, `GameAssets.r0` (`CheckBoxStyle`): `checkboxOff/On` =
  `TextureRegionDrawable`s of `data/ui/roundcheck_off/on.png`, **20x20 px**, min size never set.
  Users: `k1` (h,i,j,k,l), `j1` (j…n), journal filters `e/a/d/e/u` (IN_PROGRESS, COMPLETED, RUMORS,
  SHOW_TOWN_HALL).
* libGDX `CheckBox.<init>` builds its image as `new Image(checkboxOff, Scaling.none)` (`h0.i`), and
  `Image` draws at `drawable.getMinWidth/Height` under `Scaling.none` — so the `cell.size(20E, 20E)`
  that `k1`/`j1` apply to the image cell is ignored and the journal filters never size it at all:
  **every checkbox is drawn at 20x20 screen pixels.** Right on the 720-px-tall screens the game was
  laid out for; ~1/2.5–1/3 of the intended size on the Z Fold 8 (vanilla bug, not from our patches).
* Sliders are fine: `k1`/`j1` scale the knob's min size by E.

## Fix
`EkUi.scaleCheckboxes(GameAssets.r0)` right after the style is built: both drawables are wrapped in
a `TextureRegionDrawable` whose min width/height = source size x `Gdx.graphics.getHeight()/720`
(never below 1x), computed on every call so folding/unfolding keeps it right. That equals the
`20E` the windows already ask for, and gives the journal filters the same size.

## Audit of everything else (2026-09-23, owner: "any other things that wouldn't scale right?")
Scanned every UI class (`e/a/d/**`) for widgets added to a table without a size, and for classes
that never scale. Results:
* **Button text stretched on non-16:9 screens (fixed, B52).** `Assets.a()` scales the skin font
  `menu-button-font` by `(width/1280, height/720)` — separate X and Y. Equal on a 16:9 phone; on the
  Fold's inner screen (~1.2:1) text is ~1.5x taller than wide, on the 21:9 outer screen wider than
  tall. Every window lays out with one uniform scale `S = min(h/720, w/1280)` (`CharacterWindow.P0`,
  `ConversationWindow.s`, `WorldMapImage.j`, `GameHUD.m0`, …; Settings/Options use `h/720`). Fix:
  the font gets `min(w/1280, h/720)` on both axes — proportions kept, never larger than before on
  either axis, so nothing that fit before overflows.
* Separator images in Conversation/Journal/Skill/Traits windows: 9-patch lines that stretch to the
  row — fine. `FadingLabelSmall`, `ExtendedLabel`, `FlashingImageButton`: sized by callers — fine.
  `KeyConfigTable`/`ControllerSettingButton`: desktop keyboard/controller screens only.
* Text: windows call `setFontScale` with their scale (294 sites); fonts are fixed .fnt sizes by
  design — fine.
* Ours: vault button / bag tabs (sized by the window's scale), MULTIPLAYER + Raise Difficulty rows
  (sized like the game's Lower Difficulty row), lobby (Android views, text in sp) — fine.
**Known leftover (vanilla):** the fonts/layouts are computed when a screen is built, so folding or
unfolding while a window is open keeps the old size until it is reopened.

## Save slots: name and class/level on top of each other (owner report, v39, B58)
`SlotDescriptionTable` (`e/a/d/e1/w`, used by `ChooseGameWindow` in 380xS-wide, 85xS-tall cells):
a filled slot puts portrait (72xS) + an inner table [name label `g` / description label `h`]; both
labels `setWrap(true)` but their cells get **no width** (the empty/incompatible branches do set one:
190xS / 300xS). A wrapped libGDX label with no width can't compute its height, so long names wrap onto
extra lines, the name looks oversized, and the two lines overlap. Fix (`EkUi.fixSlot`, called before
`pack()`): filled slots only — both lines one line, cell width 285xS (380 - 72 - margins), left-aligned;
a line too wide shrinks to fit (not below 70%), anything longer ends in "...".

## Character sheet (owner report, v40)
* **Hero skill-page button (ours, `patch_hero_class.py` §3d)**: a plain `menuSmallButton` TextButton
  added with no cell size and no font scale → tiny on the Fold. Now like the SkillWindow's own Details
  button (`c0.d`: label `setFontScale(c0.u)`, cell 100x40 x `c0.t`): text x `c0.u`, cell 160x40 x `c0.t`.
* **Details window (`StatsDetailWindow` e/a/d/e/h0)**: each row = name label in a 240xS cell (no wrap)
  + value label in a 480xS cell (wrap). A long stat name is wider than 240xS and runs into the value
  text. B59: the name label wraps inside its own column (both row builders:
  `a(String,String,Z)Table` and `a(CharacterSheet)`).

* **v42 correction**: wrapping the name (v40) didn't work — the table sizes the row before the label
  knows its wrapped height, so a two-line name ran into the next row ("character stats" section).
  Now the name stays one line, shrinks to fit 240xS (not below 60%), "..." beyond that.
* **v43, the real overlap (owner: "it had a sentence, not just a word")**: the value column — a wrapped
  sentence in a 480xS cell — asks for its height while its width is still 0, so the row is shorter than
  the wrapped text and the extra lines run over the rows below. B60: the value label gets its column
  width (`setWidth(480xS)`) before it is added, so the row is as tall as the sentence.
* **v44**: owner's screenshot (Details → Character Stats) still showed one-line rows with wrapped lines on
  the next row. Each row (label cell 480xS) now gets an explicit height = the label's wrapped pref
  height measured at 480xS, set right after the cell's width (B61, `EkUi.fitCell`).

## Cut-off text in the summon skill screens (beta tester report, v45)
* **Summon route chooser** (ours, `patch_summon_routes.py` → `e/a/d/e/eksp`, a `SimpleDialog l1`): l1 is a
  fixed 700x240·c box with a 380·c wrapped text column and 90·c buttons — built for one-liners. The
  5-sentence route prompt wraps far past 240·c and runs under UNDEAD/ARCANE/BEAST. B62 `EkUi.growDialog`:
  text column 620·c, buttons 170·c, `pack()` to the content's height, centred again.
* **Skill description panel** (`SkillDetailTable` y in `SkillWindow`): no scrolling; long descriptions
  (summon rank tables) ran past the window bottom / behind Back. Now in a vertical ScrollPane like the
  skill list (`patch_hero_class.py` §7b2), reset to the top whenever another skill is selected.

## v47 — Details overlap and the route chooser, redone from measurements (owner screenshots)
Both v44 and v45 fixes failed on the owner's Fold, so this round was checked in an offline layout
simulation that runs the game's own libGDX classes (dex2jar of the built APK) with the real
`tahoma25white.fnt` metrics — no guessing from screenshots.

**Details (h0).** Measured against the screenshot, the drawn wrap is not width-driven: "5% chance of
finding secrets." and "0% chance of disabling traps." have identical glyph widths (331 units), yet one
drew wrapped and the other didn't, while every row kept a one-line height. Whatever makes the device's
draw-time wrap differ from the measured one, relying on libGDX wrapping can't be made reliable here.
Fix: B61 now calls `EkUi.prewrap(cell)` right after the value cell gets its 480·h width. It measures
words with the label's own font at its own font scale, inserts explicit `\n` breaks at 96% of the cell
width, and turns wrapping off. Row height and drawn lines now come from the same text. B60 (width
preset) is dropped. The sim confirms row height = label height for every row at 2000x1510 and 1300x1800.

**Route chooser (eksp < l1).** l1 overrides `getPrefWidth()` = 430c and `getPrefHeight()` =
max(315c, lines·58c), and `Dialog.show()` packs to those. That undid v45's resize and left the widened
text and buttons clipped on both sides. eksp now overrides both, via `EkUi.dialogPrefWidth` =
min(660c, 94% of the screen width) and `dialogPrefHeight` = content + buttons + pads (capped at 94% of
the screen height). `growDialog` fits the text column (width − 90c, explicit lines) and the three
buttons (min(170c, share of the width)) inside that width. Sim: everything sits inside the box at
2000x1510, 1300x1800 and 2520x1080.
