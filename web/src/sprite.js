// Renders a real EK character sprite from its sheet.
//
// EK character sheets are 9 cols x 11 rows of 140x140 frames (see the base game's
// AnimationSet.java). Rows 1-5 hold facings U, RU, R, RD, D; the left-facing
// directions are drawn mirrored at runtime. Column 1 of a row is the idle frame;
// columns 2-9 are that facing's 8-frame walk cycle (looped at ~0.085s in-game).
export const FRAME = 140;
export const COLS = 9;

// facing name -> 0-indexed sheet row
export const FACING_ROW = { U: 0, RU: 1, R: 2, RD: 3, D: 4 };

const idx = (row, col) => row * COLS + col;

export function loadHero(scene, key, file) {
  scene.load.spritesheet(key, file, { frameWidth: FRAME, frameHeight: FRAME });
  return new Promise((resolve) => { scene.load.once('complete', resolve); scene.load.start(); });
}

// Build idle + walk animations for every facing, and return an animated sprite
// placed at (x, y) inside `container`, walking `down` by default.
export function makeHero(scene, container, key, x, y) {
  for (const [name, row] of Object.entries(FACING_ROW)) {
    if (!scene.anims.exists(`${key}_walk_${name}`)) {
      scene.anims.create({
        key: `${key}_walk_${name}`,
        frames: [1, 2, 3, 4, 5, 6, 7, 8].map(c => ({ key, frame: idx(row, c) })),
        frameRate: 12, repeat: -1,
      });
      scene.anims.create({
        key: `${key}_idle_${name}`,
        frames: [{ key, frame: idx(row, 0) }],
        frameRate: 1,
      });
    }
  }
  const hero = scene.add.sprite(x, y, key, idx(FACING_ROW.D, 0)).setOrigin(0.5, 0.85);
  hero.facing = 'D';
  hero.play(`${key}_idle_D`);
  container.add(hero);
  return hero;
}

// Face `name` (U/RU/R/RD/D), mirrored if `flip`; play walk or idle. Left-facing
// directions reuse the right-side rows flipped, per the base game's sheet layout.
export function setFacing(hero, key, name, flip, moving) {
  hero.facing = name;
  hero.setFlipX(flip);
  const want = `${key}_${moving ? 'walk' : 'idle'}_${name}`;
  if (hero.anims.getName() !== want) hero.play(want, true);
}
