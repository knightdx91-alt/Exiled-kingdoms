# Floating joystick (owner request, v53)

"Is there any way to make the joystick free floating?" The joystick should appear wherever the thumb
lands, instead of only responding at its fixed spot.

## Reversed (4.2.2)
- GameHUD (`e/a/d/y`, decompiled `n0/z`): the joystick is a stock libGDX `Touchpad` in field `c`, made in
  the HUD init (`new Touchpad(10f, skin "touchpad")`, bounds `(t0, t0, size+t0, size+t0)`) and added to the
  HUD stage (`y.a()`). `X(float)` (size option) and `V(float)` (height option) move/resize it; the HUD's
  mode switch shows it only in normal play (mode 0, not in dialogs/cutscenes). Every frame in mode 0 the
  HUD reads `getKnobPercentX/Y()` and moves the player (`D(x,y)`).
- GameScreen (`e/a/b/b`) constructor builds the input chain: `InputMultiplexer` (`com.badlogic.gdx.i`)
  = [desktop-only handler], **HUD stage**, **GameScreen itself** (world taps: walk, attack, talk, loot).
  A touch that hits no HUD actor falls through to the world.
- Options window (`e/a/d/b0`) already carries our extra rows (Raise Difficulty, MULTIPLAYER via
  `EkFeat.mpOptionsButton()`).

## Design
New `EkStick`, an InputProcessor inserted **before** the HUD stage in the multiplexer. It never consumes
events (always returns false). It only moves the Touchpad so the stage's own hit test lands on it:
1. `touchDown` with floating on, the Touchpad visible (normal play), no other finger already steering,
   the touch in the bottom-left area (stage x < 45% of the width, y < 65% of the height), and the stage's
   hit at that point being empty or the Touchpad itself (so HUD buttons keep working):
   remember the Touchpad's home position, then centre it on the touch. The stage then delivers the same
   touchDown to the Touchpad: the knob starts at the centre (no movement) and follows the drag as usual.
2. `touchUp` of that finger: the Touchpad returns home (visible at its normal spot while idle).
3. **Taps still reach the world.** If the finger barely moved (< 3% of screen height) and was down under
   0.35 s, and the touch wasn't on the joystick's home spot, the tap is replayed to GameScreen
   (`touchDown` + `touchUp`), so tapping an enemy or chest in that corner still works.
4. Option: an Options button **"Joystick: Floating" / "Joystick: Fixed"** (next to MULTIPLAYER), saved in
   the app's preferences (`ek_joystick_floating`), default Floating. Fixed = stock behaviour.

## Deliberate deviation
Stock EK has only the fixed joystick; floating is an added option, logged here and in
DEOBFUSCATION_STATUS.md §3 terms (APPROX: zone sizes and tap thresholds are our choice).

## Verified offline (game's own libGDX Stage/Touchpad/InputMultiplexer from the built APK)
1. Thumb down in empty bottom-left space: joystick centred under it; drag right → knob X 0.80; release →
   back home, knob 0, nothing sent to the world.
2. Quick tap there → the world gets exactly one touchDown+touchUp at the tap point.
3. Tap on a HUD button inside the area → the button gets it, joystick doesn't move.
4. Tap outside the area → world as before.
5. Steering with one finger while tapping elsewhere with another → both work.
6. Fixed mode → stock behaviour.
