// Render polish for interiors — roof-fade (A3), fog-of-war (A5), dynamic lights (A1).
// Numbers reversed from the base renderer; see deobf/RENDER_POLISH_SPEC.md. World mode
// (the seamless outdoor overworld) and fully-bright interiors are left untouched.

import { cellToPx } from './move.js';

const FADE_ALPHA = 0.42;      // roof/object tile alpha when the hero is behind it (A3)
const EXPLORE_MANHATTAN = 12; // cells marked explored around the hero (A5)
const SIGHT_CHEBY = 7;        // "currently visible" radius (A5, LOS approximated by range)

// One shared soft radial-glow texture (additive light sprite for A1).
function ensureGlowTexture(scene) {
  if (scene.textures.exists('ek_glow')) return;
  const S = 128, cv = scene.textures.createCanvas('ek_glow', S, S);
  const ctx = cv.getContext();
  const g = ctx.createRadialGradient(S / 2, S / 2, 0, S / 2, S / 2, S / 2);
  g.addColorStop(0, 'rgba(255,255,255,1)');
  g.addColorStop(0.45, 'rgba(255,255,255,0.55)');
  g.addColorStop(1, 'rgba(255,255,255,0)');
  ctx.fillStyle = g; ctx.fillRect(0, 0, S, S);
  cv.refresh();
}

const parseColor = (s) => {
  if (!s) return 0xffd9a0;                    // warm torch default
  if (s[0] === '#') return parseInt(s.slice(1), 16);
  const p = s.split(',').map(Number);
  if (p.length === 3) return (p[0] << 16) | (p[1] << 8) | p[2];
  return 0xffd9a0;
};

export class RenderFX {
  constructor(scene) {
    this.s = scene;
    this.enabled = false;
    this.images = [];
    this.lightLayer = null;
    this.glows = [];
    this.playerGlow = null;
    this._lastCell = null;
  }

  // Enter an interior: build the explored grid + light glows from the map.
  onEnter(map, images) {
    this.dispose();
    if (this.s.mode !== 'interior' || !map) return;
    this.map = map; this.images = images || [];
    this.W = map.width; this.H = map.height;
    this.dungeon = (map.maxlight || 0) > 0;       // dark map → fog + player torch
    this.useFog = this.dungeon;
    this.explored = this.useFog ? new Uint8Array(this.W * this.H) : null;
    // pre-compute the 1/3-brightness fog tint from the shared ambient tint
    const base = (images[0] && images[0]._baseTint) || 0xffffff;
    this.dimTint = (((base >> 16 & 255) / 3 | 0) << 16) |
                   (((base >> 8 & 255) / 3 | 0) << 8) | ((base & 255) / 3 | 0);
    // tiles that can fade (A3): roof + mid scenery/objects
    this.fadeImgs = this.images.filter(i => i._plane === 'roof' || i._plane === 'mid');
    this._buildLights(map);
    this.enabled = this.useFog || this.fadeImgs.length > 0 || this.glows.length > 0;
    this._lastCell = null;
  }

  _buildLights(map) {
    const lights = map.lights || [];
    const wantPlayer = this.dungeon;
    if (!lights.length && !wantPlayer) return;
    ensureGlowTexture(this.s);
    this.lightLayer = this.s.add.container(0, 0);
    this.s.mapLayer.add(this.lightLayer);
    try { this.s.mapLayer.moveBelow(this.lightLayer, this.s.planes.roof); } catch { /* order best-effort */ }
    for (const L of lights) {
      const p = cellToPx(L.c, L.r, map);
      const radius = (L.radius || 4) * map.tilew;    // tiles → px, ~4-tile torch
      const img = this.s.add.image(p.x, p.y - map.tileh / 2, 'ek_glow')
        .setBlendMode(Phaser.BlendModes.ADD).setTint(parseColor(L.color));
      img.setDisplaySize(radius, radius);
      img._r0 = radius;
      this.lightLayer.add(img);
      this.glows.push(img);
    }
    if (wantPlayer) {
      const pg = this.s.add.image(0, 0, 'ek_glow')
        .setBlendMode(Phaser.BlendModes.ADD).setTint(0xffe6b0);
      pg.setDisplaySize(map.tilew * 6, map.tilew * 6);
      pg._r0 = map.tilew * 6;
      this.lightLayer.add(pg);
      this.playerGlow = pg;
    }
  }

  // Per-frame: flicker lights; on hero cell change, recompute fog + roof-fade.
  update(dt) {
    if (!this.enabled) return;
    // subtle torch flicker (A1) — cheap, every frame
    if (this.glows.length || this.playerGlow) {
      const t = this.s.time.now * 0.006;
      let i = 0;
      for (const g of this.glows) { const f = 1 + Math.sin(t + i) * 0.06; g.setDisplaySize(g._r0 * f, g._r0 * f); i += 1.7; }
      if (this.playerGlow && this.s.hero) {
        this.playerGlow.setPosition(this.s.hero.x, this.s.hero.y - this.map.tileh);
        const f = 1 + Math.sin(t) * 0.05;
        this.playerGlow.setDisplaySize(this.playerGlow._r0 * f, this.playerGlow._r0 * f);
      }
    }
    const hc = this.s.heroCell;
    if (!hc) return;
    if (this._lastCell && this._lastCell.c === hc.c && this._lastCell.r === hc.r) return;
    this._lastCell = { c: hc.c, r: hc.r };
    this._applyFogAndFade(hc);
  }

  _applyFogAndFade(hc) {
    // mark explored around the hero (A5)
    if (this.useFog) {
      const R = EXPLORE_MANHATTAN;
      for (let dr = -R; dr <= R; dr++)
        for (let dc = -R; dc <= R; dc++) {
          if (Math.abs(dc) + Math.abs(dr) > R) continue;
          const c = hc.c + dc, r = hc.r + dr;
          if (c >= 0 && r >= 0 && c < this.W && r < this.H) this.explored[r * this.W + c] = 1;
        }
    }
    for (const img of this.images) {
      const cell = img._cell; if (!cell) continue;
      const cheby = Math.max(Math.abs(cell.c - hc.c), Math.abs(cell.r - hc.r));
      // --- A5 fog ---
      if (this.useFog) {
        const seen = this.explored[cell.r * this.W + cell.c];
        if (!seen) { img.setVisible(false); continue; }
        img.setVisible(true);
        img.setTint(cheby <= SIGHT_CHEBY ? img._baseTint : this.dimTint);  // full vs 1/3
      }
      // --- A3 roof/object fade: hero within the 4-tile band behind the tile ---
      if (img._plane === 'roof' || img._plane === 'mid') {
        const behind = hc.c >= cell.c - 4 && hc.c <= cell.c &&
                       hc.r >= cell.r && hc.r <= cell.r + 4;
        img.setAlpha(behind ? FADE_ALPHA : 1);
      }
    }
    // hide entities standing on unexplored cells (fog also hides monsters)
    if (this.useFog && this.s.entities) {
      for (const e of this.s.entities) {
        const seen = e.cell && this.explored[e.cell.r * this.W + e.cell.c];
        if (e.sprite) e.sprite.setVisible(!!seen);
      }
    }
  }

  dispose() {
    if (this.lightLayer) { this.lightLayer.destroy(true); this.lightLayer = null; }
    this.glows = []; this.playerGlow = null;
    this.images = []; this.explored = null; this.enabled = false; this._lastCell = null;
  }
}
