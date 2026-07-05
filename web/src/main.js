// Exiled Kingdoms — web rebuild (Phaser 3) — orientation prototype.
//
// Orientation is PLAYER-CHOSEN and engine-driven, not from the browser's
// auto-rotate. The canvas always fills the physical screen upright (so Phaser's
// pointer input stays correct); all game content lives in a single `world`
// container that we rotate 0/90/180/270 in-engine. Because interactive objects
// are children of that container, Phaser hit-tests them through the rotation for
// free — taps land correctly in every orientation.

import { loadMap, renderMap } from './map.js';
import { loadHero, makeHero } from './sprite.js';

const ORIENTS = [0, 90, 180, 270];
const START_MAP = 'H10';                            // Lannegar Valley (starting town)
// Camera recovered from the base game's GameLevelRenderer (deobfuscated k0/a.java):
// the phone camera is an OrthographicCamera with a 533x300 world-unit viewport and
// gameplay zoom 1.0 — i.e. 533 world-px of width fill the screen. We reproduce that
// art scale by mapping EK_VIEWPORT_W world-units across the longer logical axis
// (the base game is landscape; 533 is its long side), independent of orientation.
const EK_VIEWPORT_W = 533;
// Pinch zoom: 1.0 = the base game's view (fully zoomed out — the hard max, can't go
// past it). You can pinch IN up to ZOOM_MAX_IN. Larger factor = more zoomed in.
const ZOOM_MIN = 1.0;
const ZOOM_MAX_IN = 1.75;

class MapScene extends Phaser.Scene {
  constructor() { super('map'); }

  create() {
    this.orient = 0;
    this.zoomFactor = 1;                            // 1 = fully zoomed out (base game view)
    this.world = this.add.container(0, 0);          // rotated root for all content

    // Real map content lives in its own sub-container so we can fit-scale it to the
    // viewport independently of the orientation transform on `world`.
    this.mapLayer = this.add.container(0, 0);
    this.mapBounds = null;

    this.grid = this.add.graphics();                // tile-grid placeholder / fallback
    this.title = this.add.text(0, 0, '', {
      fontFamily: 'system-ui, sans-serif', fontSize: '20px', color: '#e9ecf1'
    }).setOrigin(0.5, 0);
    this.hud = this.add.text(0, 0, '', {
      fontFamily: 'monospace', fontSize: '13px', color: '#8b93a1'
    });

    // draggable "hero" token — used to prove input maps correctly under rotation
    this.token = this.add.circle(0, 0, 26, 0x4c6ef5).setStrokeStyle(3, 0xdfe4ec);
    this.tokenLabel = this.add.text(0, 0, '⚔', {
      fontSize: '24px', color: '#fff'
    }).setOrigin(0.5);
    // The real animated hero (loaded with the map) is now the visible protagonist;
    // this token stays as a faint, still-interactive input marker (drag/hit-test).
    this.token.setAlpha(0.22);
    this.tokenLabel.setAlpha(0);
    this.token.setInteractive({ draggable: true, useHandCursor: true });
    this.input.setDraggable(this.token);
    this.input.on('drag', (_p, obj, dx, dy) => {
      if (obj !== this.token) return;
      this._tokenMoved = true;
      this.token.setPosition(dx, dy);
      this.tokenLabel.setPosition(dx, dy);
      this.flash(0x51cf66);
      this.refreshHud();
    });
    this.token.on('pointerdown', () => this.flash(0x51cf66));

    this.world.add([this.grid, this.mapLayer, this.title, this.token, this.tokenLabel, this.hud]);

    // Load + render the first real game map. Async and self-contained: if it fails
    // (e.g. assets missing) the prototype still boots with the placeholder grid.
    this.loadStartMap();

    // expose a tiny test hook so the automated browser check can verify input.
    // Merge (don't overwrite) so the SW/cache fields set at module load survive.
    window.__EK = Object.assign(window.__EK || {}, {
      setOrient: (o) => this.setOrient(o),
      orient: () => this.orient,
      logical: () => ({ w: this.LW, h: this.LH }),
      tokenScreen: () => {
        const m = this.token.getWorldTransformMatrix();
        return { x: m.tx, y: m.ty };
      },
      hitTest: (sx, sy) => {
        const hits = this.input.hitTestPointer(
          Object.assign(this.input.activePointer, { x: sx, y: sy }));
        return hits.includes(this.token);
      },
      map: () => ({ name: START_MAP, tiles: this._mapTiles || 0 }),
      zoom: () => this.zoomFactor,
      setZoom: (f) => this.setZoom(f),
      hero: () => this.hero ? {
        playing: this.hero.anims.isPlaying,
        anim: this.hero.anims.getName(),
        frame: this.hero.anims.currentFrame && this.hero.anims.currentFrame.index,
      } : null,
    });

    this.scale.on('resize', () => this.relayout());

    // Pinch-to-zoom (two fingers) + mouse wheel (desktop). Zoom is clamped so the
    // base game view is the furthest-out (ZOOM_MIN); you can only pinch in.
    this.input.addPointer(1);                        // allow a 2nd concurrent pointer
    this._pinchPrev = null;
    this.input.on('wheel', (_p, _o, _dx, dy) =>
      this.setZoom(this.zoomFactor * (dy > 0 ? 0.92 : 1.08)));

    this.relayout();
  }

  update() {
    // Track two-finger pinch distance frame-to-frame and scale the zoom by its ratio.
    const ps = [this.input.pointer1, this.input.pointer2].filter(p => p && p.isDown);
    if (ps.length === 2) {
      const d = Phaser.Math.Distance.Between(ps[0].x, ps[0].y, ps[1].x, ps[1].y);
      if (this._pinchPrev) this.setZoom(this.zoomFactor * (d / this._pinchPrev));
      this._pinchPrev = d;
    } else {
      this._pinchPrev = null;
    }
  }

  setZoom(f) {
    this.zoomFactor = Phaser.Math.Clamp(f, ZOOM_MIN, ZOOM_MAX_IN);
    this.fitMap();
    return this.zoomFactor;
  }

  async loadStartMap() {
    try {
      const map = await loadMap(this, START_MAP);
      const { tiles, width, height } = renderMap(this, this.mapLayer, map);
      this._mapTiles = tiles;
      this.mapBounds = { width, height };
      this.grid.setVisible(false);                 // real content replaces the grid

      // Real animated hero, placed on the map (in map-space so it scales with it).
      await loadHero(this, 'hero', 'assets/sprites/male_knight.png');
      this.hero = makeHero(this, this.mapLayer, 'hero', width / 2, height / 2);

      this.fitMap();
    } catch (e) {
      console.warn('map load failed, keeping placeholder grid:', e);
    }
  }

  // Camera: reproduce the base game's phone view — 533 world-units of the long
  // screen axis, gameplay zoom 1.0 (recovered from GameLevelRenderer) — following
  // the hero at the center of the viewport. `world` handles the orientation.
  fitMap() {
    if (!this.mapBounds) return;
    const z = (Math.max(this.LW, this.LH) / EK_VIEWPORT_W) * this.zoomFactor;
    this.mapLayer.setScale(z);
    const hx = this.hero ? this.hero.x : this.mapBounds.width / 2;
    const hy = this.hero ? this.hero.y : this.mapBounds.height / 2;
    this.mapLayer.setPosition(this.LW / 2 - hx * z, this.LH / 2 - hy * z);
  }

  flash(color) {
    this.token.setFillStyle(color);
    this.time.delayedCall(160, () => this.token.setFillStyle(0x4c6ef5));
  }

  // Screen (physical) size -> logical content size for the chosen orientation.
  setOrient(deg) {
    this.orient = deg;
    this.relayout();
    document.querySelectorAll('#orient-bar button').forEach(b =>
      b.setAttribute('aria-pressed', String(+b.dataset.orient === deg)));
  }

  relayout() {
    const SW = this.scale.width, SH = this.scale.height;
    const rot = (this.orient % 180) !== 0;      // 90/270 swap logical dimensions
    this.LW = rot ? SH : SW;
    this.LH = rot ? SW : SH;

    // place + rotate the world container so logical [0..LW]x[0..LH] fills the screen
    const T = {
      0:   { r: 0,               x: 0,  y: 0  },
      90:  { r: Math.PI / 2,     x: SW, y: 0  },
      180: { r: Math.PI,         x: SW, y: SH },
      270: { r: -Math.PI / 2,    x: 0,  y: SH },
    }[this.orient];
    this.world.setRotation(T.r);
    this.world.setPosition(T.x, T.y);

    // (re)draw content to the current logical rectangle — this is the responsive
    // "design" layer the real game would own; here it's a simple centered layout.
    const cell = 48;
    this.grid.clear().lineStyle(1, 0x232a33, 1);
    for (let x = 0; x <= this.LW; x += cell) this.grid.lineBetween(x, 0, x, this.LH);
    for (let y = 0; y <= this.LH; y += cell) this.grid.lineBetween(0, y, this.LW, y);
    this.grid.fillStyle(0x11151b, 1).fillRect(0, 0, this.LW, 0); // (keeps bounds sane)

    const label = { 0: 'Portrait', 90: 'Landscape',
                    180: 'Reverse portrait', 270: 'Reverse landscape' }[this.orient];
    this.title.setText(`Exiled Kingdoms — ${label}`).setPosition(this.LW / 2, 24);

    if (!this._tokenMoved) {
      this.token.setPosition(this.LW / 2, this.LH / 2);
      this.tokenLabel.setPosition(this.LW / 2, this.LH / 2);
    }
    this.fitMap();
    this.refreshHud();
  }

  refreshHud() {
    this.hud.setText(
      `orient ${this.orient}°   logical ${Math.round(this.LW)}x${Math.round(this.LH)}` +
      `   token ${Math.round(this.token.x)},${Math.round(this.token.y)}`
    ).setPosition(12, this.LH - 24);
  }
}

new Phaser.Game({
  type: Phaser.AUTO,
  parent: 'game-root',
  backgroundColor: '#0b0d10',
  scale: { mode: Phaser.Scale.RESIZE, width: '100%', height: '100%' },
  scene: [MapScene],
});

// Wire the player-owned orientation buttons.
document.getElementById('orient-bar').addEventListener('click', (e) => {
  const b = e.target.closest('button');
  if (b) window.__EK && window.__EK.setOrient(+b.dataset.orient);
});

// PWA: register the service worker, then ask it to cache the FULL game for
// offline play. Progress is exposed on window.__EK.cache for a UI/loading bar.
if ('serviceWorker' in navigator) {
  window.__EK = window.__EK || {};
  window.__EK.cache = { done: 0, total: 0, failed: 0, complete: false };
  navigator.serviceWorker.addEventListener('message', (e) => {
    const d = e.data || {};
    if (d.type === 'CACHE_PROGRESS') Object.assign(window.__EK.cache, d);
    if (d.type === 'CACHE_DONE') Object.assign(window.__EK.cache, d, { complete: true });
  });
  navigator.serviceWorker.register('./sw.js').then(async () => {
    await navigator.serviceWorker.ready;
    // ask the browser to keep our storage (avoid eviction of the big cache + saves)
    if (navigator.storage && navigator.storage.persist) navigator.storage.persist();
    const post = () => navigator.serviceWorker.controller
      && navigator.serviceWorker.controller.postMessage({ type: 'CACHE_ALL' });
    navigator.serviceWorker.controller ? post()
      : navigator.serviceWorker.addEventListener('controllerchange', post, { once: true });
  }).catch(() => {});
}
