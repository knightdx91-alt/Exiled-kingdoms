# Exiled Kingdoms — Web (Phaser 3)

Browser rebuild target. Uses the recovered assets + Tiled maps + game data, with
logic ported from the recovered source (see ../deobf, ../recovered).

## Orientation model (player-owned, not browser-driven)

The canvas always fills the physical screen upright, so Phaser pointer input is
always correct. All game content lives in one `world` container that we rotate
0/90/180/270 in-engine, driven by the on-screen orientation control — so the
player picks portrait / landscape / reverse-portrait / reverse-landscape
regardless of how the device is held or what the browser's auto-rotate does.
Interactive objects are children of the rotated container, so taps hit-test
correctly through the rotation.

## Run / verify

    npm install
    node verify.mjs        # launches headless Chrome (Android viewport),
                           # exercises all 4 orientations, screenshots to shots/,
                           # and asserts input works in each. Prints VERIFY: PASS.

Serve `index.html` over any static server to play in a real browser.

## Status

Prototype: proves Phaser 3 renders content and the player-owned 4-way orientation
+ input works in Chrome. Next: convert `.tmx` maps (Tiled CLI -> JSON), load real
tilesets/atlases, and port the recovered game systems.
