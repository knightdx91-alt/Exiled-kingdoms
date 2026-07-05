// Map entities (NPCs / monsters) placed from a map's `spawn` objects.
//
// A spawn references a bestiary entry (by object name, else its `spawn` property) that
// gives the sprite sheet + faction. EK NPC sheets use the same 9x11 x 140px layout as
// the hero (AnimationSet.java), so we reuse sprite.js to animate them. Rendering,
// depth-sorting and interaction are driven by the scene (see main.js).

import { loadHero, makeHero } from './sprite.js';

// Resolve a spawn to its bestiary record (sprite/faction/portrait/gender/class).
export function bestiaryOf(bestiary, npc) {
  return bestiary[npc.name] || bestiary[npc.spawn] || bestiary[npc.tag] || {};
}

// Resolve a spawn to a loadable sprite sheet path. Composite (paper-doll) sprites like
// "male_tunic_red;composite/male_mainhand_greatsword" reduce to their base body layer
// (first segment). Anything not present in the sprite index falls back to the hero
// sheet, so we never request a missing file.
export function spriteName(bestiary, npc, spriteSet) {
  let s = bestiaryOf(bestiary, npc).sprite || 'male_knight';
  s = s.replace(/"/g, '').split(';')[0].trim();       // base layer of a composite
  if (spriteSet && spriteSet.size && !spriteSet.has(s)) return 'male_knight';
  return s;
}

// Ensure the NPC's sheet is loaded, keyed by sprite so shared sheets load once.
export async function loadNpcSheet(scene, sprite) {
  const key = `npc_${sprite}`;
  if (!scene.textures.exists(key)) {
    try { await loadHero(scene, key, `assets/sprites/${sprite}.png`); }
    catch { return null; }
  }
  return key;
}

// Create an idle NPC sprite in `container` at pixel (x,y). Returns the sprite (or null
// if its sheet is too small for the animation grid — big/odd monster sheets).
export function makeNpc(scene, container, key, x, y) {
  const tex = scene.textures.get(key);
  if (!tex || tex.frameTotal - 1 < 45) {              // needs >=45 frames (rows U..D * 9)
    const s = scene.add.image(x, y, key, 0).setOrigin(0.5, 0.85);
    container.add(s); return s;
  }
  return makeHero(scene, container, key, x, y);
}

// The player-facing portrait URL for an NPC, if the bestiary gives one.
export function npcPortrait(rec) {
  if (!rec || !rec.portrait || rec.portrait === '0') return null;
  const g = rec.gender === 'F' ? 'female' : 'male';
  return `assets/portraits/${g}/${rec.portrait}.png`;
}
