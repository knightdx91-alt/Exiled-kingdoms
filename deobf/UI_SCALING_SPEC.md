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
