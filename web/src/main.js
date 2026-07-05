// Exiled Kingdoms — web rebuild (Phaser 3) — orientation prototype.
//
// Orientation is PLAYER-CHOSEN and engine-driven, not from the browser's
// auto-rotate. The canvas always fills the physical screen upright (so Phaser's
// pointer input stays correct); all game content lives in a single `world`
// container that we rotate 0/90/180/270 in-engine. Because interactive objects
// are children of that container, Phaser hit-tests them through the rotation for
// free — taps land correctly in every orientation.

import { loadMap, renderMap, sortMid, ambientColor, applyAmbient } from './map.js';
import { loadHero, makeHero, setFacing } from './sprite.js';
import { buildWalkable, cellToPx, pxToCell, findPath, facingFor,
         findPathWorld } from './move.js';
import { Overworld } from './world.js';
import { startFlow } from './char-create.js';
import { Joystick } from './joystick.js';
import { Saves } from './saves.js';

const HERO_SPEED = 140;                             // px/sec along the path (map-space)

const tintOf = (c) => (Math.round(c.r * 255) << 16) |
                      (Math.round(c.g * 255) << 8) | Math.round(c.b * 255);

const ORIENTS = [0, 90, 180, 270];
const TUTORIAL_MAP = 'I10_tutorial';                // Adaon's road — where a new game begins
const DEV_START_MAP = 'H10';                        // Lannegar Valley — test/quick-start entry
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

// Seamless-overworld tile culling: only tiles within the camera view (+ these px
// margins) are drawn from a sprite pool, so draw cost is bounded by the viewport, not
// by how many chunks are streamed in. TALL covers tiles taller than the base diamond
// (walls/trees, anchored at their feet) poking down from above the top edge.
const TILE_MARGIN = 96;
const TILE_TALL = 224;
const REFRESH_STEP = 64;              // re-cull after the hero moves this many world px

class MapScene extends Phaser.Scene {
  constructor() { super('map'); }

  create() {
    this.orient = 0;
    this.zoomFactor = 1;                            // 1 = fully zoomed out (base game view)
    this.world = this.add.container(0, 0);          // rotated root for all content

    // Real map content lives in its own sub-container so we can fit-scale it to the
    // viewport independently of the orientation transform on `world`. Inside it, three
    // depth planes: floor (under actors), mid (scenery/objects depth-sorted WITH the
    // hero), roof (over actors).
    this.mapLayer = this.add.container(0, 0);
    this.planes = { floor: this.add.container(0, 0),
                    mid: this.add.container(0, 0),
                    roof: this.add.container(0, 0) };
    this.mapLayer.add([this.planes.floor, this.planes.mid, this.planes.roof]);
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

    // Load the seamless overworld grid, then enter the start area. Async and
    // self-contained: if it fails the prototype still boots with the placeholder grid.
    this.mode = null;                               // 'world' (streamed) | 'interior'
    // Sprite pool for the culled overworld renderer (world mode only).
    this._pool = { floor: [], mid: [], roof: [] };
    this._active = { floor: [], mid: [], roof: [] };
    this._lastRefreshXY = null;

    // Movement control scheme: 'tap' (tap-to-move A*) or 'joystick' (free-floating).
    this.control = 'tap';
    this.heroKey = 'hero';
    this.charSpriteFile = 'assets/sprites/male_knight.png';
    const root = document.getElementById('game-root');
    this.joystick = new Joystick(root);
    this._wireControlToggle();

    this.boot();

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
      map: () => ({
        name: this._mapName, tiles: this._mapTiles || 0, mode: this.mode,
        chunks: this.overworld ? [...this.overworld.loaded.keys()] : [],
        midCount: this.planes.mid.list.length,
        heroIndex: this.hero ? this.planes.mid.list.indexOf(this.hero) : -1,
        transitions: this.currentTransitions(),
      }),
      // test helper: path the hero onto the first transition (an arch to an interior)
      // and let it fire.
      gotoTransition: () => {
        const t = this.currentTransitions()[0];
        if (!t) return null;
        const goal = this.nearestWalkable(t.c, t.r);
        const p = this.planPath(this.heroCell, goal);
        if (p && p.length > 1) { this.path = p; this.pathIdx = 1; }
        return { area: t.area };
      },
      light: () => ({
        maxlight: this._map && this._map.maxlight, outdoor: this._map && this._map.outdoor,
        hour: this.currentHour(), ambient: this._ambient,
      }),
      setHour: (h) => this.setHour(h),
      zoom: () => this.zoomFactor,
      setZoom: (f) => this.setZoom(f),
      heroCell: () => this.heroCell && { ...this.heroCell },
      moving: () => !!this.path,
      // test helper: path the hero to a cell offset from its current one
      walkBy: (dc, dr) => {
        const goal = this.nearestWalkable(this.heroCell.c + dc, this.heroCell.r + dr);
        const p = this.planPath(this.heroCell, goal);
        if (p && p.length > 1) { this.path = p; this.pathIdx = 1; }
        return goal;
      },
      hero: () => this.hero ? {
        playing: this.hero.anims.isPlaying,
        anim: this.hero.anims.getName(),
        frame: this.hero.anims.currentFrame && this.hero.anims.currentFrame.index,
      } : null,
      // test helper: skip the title/creation UI and start a game immediately.
      quickStart: async (opts = {}) => {
        this._quickStarted = true;
        document.querySelectorAll('.cc-overlay').forEach(o => o.remove());
        if (!this.overworld) {
          try { const g = await (await fetch('assets/world-grid.json')).json(); this.overworld = new Overworld(this, g); } catch {}
        }
        const pc = Object.assign(
          { name: 'Tester', gender: 'MALE', charClass: 'WARRIOR', portrait: null, difficulty: 1 },
          opts.pc || {});
        await this.startNewGame(pc, opts.map || DEV_START_MAP);
        return { name: this._mapName, mode: this.mode };
      },
      player: () => this.player ? { ...this.player } : null,
      control: () => this.control,
      setControl: (m) => this.setControl(m),
      // test helper: drive the free-floating joystick with a screen vector (-1..1).
      stick: (x, y) => { this.joystick.active = (x || y) ? true : false; this.joystick.vec = { x, y }; },
      titleShown: () => !!document.querySelector('.cc-overlay'),
    });

    this.scale.on('resize', () => this.relayout());

    // Pinch-to-zoom (two fingers) + mouse wheel (desktop). Zoom is clamped so the
    // base game view is the furthest-out (ZOOM_MIN); you can only pinch in.
    this.input.addPointer(1);                        // allow a 2nd concurrent pointer
    this._pinchPrev = null;
    this.input.on('wheel', (_p, _o, _dx, dy) =>
      this.setZoom(this.zoomFactor * (dy > 0 ? 0.92 : 1.08)));

    // Tap-to-move: a tap (not a drag/pinch) on the map paths the hero to that cell.
    this._downXY = null;
    this.input.on('pointerdown', (p) => { this._downXY = { x: p.x, y: p.y }; });
    this.input.on('pointerup', (p) => {
      const d = this._downXY; this._downXY = null;
      if (!d || !this.hero || !this._map) return;
      if (Math.hypot(p.x - d.x, p.y - d.y) > 12) return;         // was a drag, ignore
      if ([this.input.pointer1, this.input.pointer2].filter(q => q && q.isDown).length) return;
      this.moveTo(p.x, p.y);
    });

    this.relayout();
  }

  // Is cell (c,r) walkable? World mode: global cell via the streamer. Interior: local grid.
  isWalkable(c, r) {
    if (this.mode === 'world') return this.overworld.walkable(c, r);
    const W = this._map.width, H = this._map.height;
    return c >= 0 && r >= 0 && c < W && r < H && !!this.walk[r * W + c];
  }

  nearestWalkable(c0, r0) {
    const span = this.mode === 'world'
      ? Math.max(this.overworld.CW, this.overworld.CH)
      : Math.max(this._map.width, this._map.height);
    for (let rad = 0; rad < span; rad++)
      for (let dc = -rad; dc <= rad; dc++)
        for (let dr = -rad; dr <= rad; dr++) {
          const c = c0 + dc, r = r0 + dr;
          if (this.isWalkable(c, r)) return { c, r };
        }
    return { c: c0, r: r0 };
  }

  // Plan a path from start->goal in the current mode's cell space.
  planPath(start, goal) {
    if (this.mode === 'world')
      return findPathWorld((c, r) => this.overworld.walkable(c, r),
                           this.overworld.bound(), start, goal);
    return findPath(this.walk, this._map.width, this._map.height, start, goal);
  }

  // Transitions (arches/doors) reachable from where the hero stands, in the current
  // mode's cell space. Outdoor tiles join seamlessly, so world-mode transitions are
  // only the arches into towns/caves for the chunk the hero is in.
  currentTransitions() {
    if (this.mode === 'world' && this.overworld && this._curChunk)
      return this.overworld.transitionsInGlobal(this._curChunk.col, this._curChunk.row);
    return (this._map && this._map.transitions) || [];
  }

  // Path the hero from its cell to the map cell under screen point (sx,sy).
  moveTo(sx, sy) {
    const local = this.mapLayer.getWorldTransformMatrix().applyInverse(sx, sy);
    const goal = this.mode === 'world'
      ? this.overworld.pxToCell(local.x, local.y)
      : pxToCell(local.x, local.y, this._map);
    if (this.mode !== 'world') {
      const W = this._map.width, H = this._map.height;
      if (goal.c < 0 || goal.r < 0 || goal.c >= W || goal.r >= H) return;
    }
    const path = this.planPath(this.heroCell, goal);
    if (path && path.length > 1) { this.path = path; this.pathIdx = 1; }
  }

  update(_t, dtMs) {
    // Track two-finger pinch distance frame-to-frame and scale the zoom by its ratio.
    const ps = [this.input.pointer1, this.input.pointer2].filter(p => p && p.isDown);
    if (ps.length === 2) {
      const d = Phaser.Math.Distance.Between(ps[0].x, ps[0].y, ps[1].x, ps[1].y);
      if (this._pinchPrev) this.setZoom(this.zoomFactor * (d / this._pinchPrev));
      this._pinchPrev = d;
    } else {
      this._pinchPrev = null;
    }
    if (this.control === 'joystick') this.stepJoystick(dtMs / 1000);
    else this.stepHero(dtMs / 1000);
  }

  // Swap control scheme. Tap-to-move uses Phaser pointer input; the joystick uses its
  // own pointer layer (which, when enabled, sits over the canvas so taps don't also
  // fire a move). Cancels any in-progress A* path when switching to the joystick.
  setControl(mode) {
    this.control = (mode === 'joystick') ? 'joystick' : 'tap';
    this.joystick.enable(this.control === 'joystick');
    if (this.control === 'joystick') { this.path = null; }
    const btn = document.getElementById('control-toggle');
    if (btn) btn.textContent = this.control === 'joystick' ? '🕹 Joystick' : '👆 Tap';
    return this.control;
  }

  _wireControlToggle() {
    const btn = document.getElementById('control-toggle');
    if (!btn) return;
    btn.onclick = () => this.setControl(this.control === 'tap' ? 'joystick' : 'tap');
  }

  // Free-floating-joystick movement: convert the stick's screen-space vector into a
  // map-space direction (through the orientation transform), move the hero at
  // HERO_SPEED scaled by stick magnitude, and slide along blocked axes. Facing/anim,
  // camera, transitions and streaming behave exactly as in tap-to-move.
  stepJoystick(dt) {
    const h = this.hero;
    if (!h || this._loading || !this.mode) return;
    const v = this.joystick.vec;
    const mag = Math.hypot(v.x, v.y);
    if (mag < 0.001) {                                // idle
      if (this._jMoving) { setFacing(h, this.heroKey, h.facing, h.flipX, false); this._jMoving = false; }
      return;
    }
    // screen direction -> map direction (strip translation, keep rotation/scale)
    const m = this.mapLayer.getWorldTransformMatrix();
    const a = m.applyInverse(0, 0), b = m.applyInverse(v.x, v.y);
    let dx = b.x - a.x, dy = b.y - a.y;
    const l = Math.hypot(dx, dy) || 1; dx /= l; dy /= l;
    const step = HERO_SPEED * dt * Math.min(1, mag);

    const cellOf = (px, py) => this.mode === 'world'
      ? this.overworld.pxToCell(px, py) : pxToCell(px, py, this._map);
    const free = (px, py) => { const c = cellOf(px, py); return this.isWalkable(c.c, c.r); };

    let nx = h.x + dx * step, ny = h.y + dy * step;
    if (!free(nx, ny)) {                              // slide along whichever axis is clear
      if (free(h.x + dx * step, h.y)) ny = h.y;
      else if (free(h.x, h.y + dy * step)) nx = h.x;
      else { if (this._jMoving) { setFacing(h, this.heroKey, h.facing, h.flipX, false); this._jMoving = false; } return; }
    }
    h.setPosition(nx, ny);
    const face = facingFor(dx, dy);
    setFacing(h, this.heroKey, face.name, face.flip, true);
    this._jMoving = true;

    const cell = cellOf(h.x, h.y);
    this.heroCell = cell;
    if (this.mode === 'world') this.onWorldStep();
    this.checkTransition();
    if (this.mode === 'world' && this._lastRefreshXY &&
        Math.hypot(h.x - this._lastRefreshXY.x, h.y - this._lastRefreshXY.y) > REFRESH_STEP) {
      this.streamVisible();
    } else {
      sortMid(this.planes.mid);
    }
    this.fitMap();
  }

  // Fire the transition whose cell the hero is standing on (portal to another area).
  // A lock set on arrival prevents the return portal (next to where you spawn) from
  // firing immediately — you must step off it first.
  checkTransition() {
    if (this._loading) return;
    const t = this.currentTransitions().find(t =>
      Math.abs(t.c - this.heroCell.c) <= 1 && Math.abs(t.r - this.heroCell.r) <= 1);
    if (this._transLock) { if (!t) this._transLock = false; return; }
    if (t && t.area) { this.path = null; this.goArea(t.area, t.entry); }
  }

  // Advance the hero along its path; update facing, camera follow, and depth order.
  stepHero(dt) {
    const h = this.hero;
    if (!h || !this.path || this._loading) return;
    const next = this.path[this.pathIdx];
    const target = this.mode === 'world'
      ? this.overworld.cellToPx(next.c, next.r)
      : cellToPx(next.c, next.r, this._map);
    const dx = target.x - h.x, dy = target.y - h.y;
    const dist = Math.hypot(dx, dy);
    const step = HERO_SPEED * dt;

    const face = facingFor(dx, dy);
    setFacing(h, this.heroKey, face.name, face.flip, true);

    if (dist <= step) {                              // reached this node
      h.setPosition(target.x, target.y);
      this.heroCell = { c: next.c, r: next.r };
      this.pathIdx++;
      if (this.mode === 'world') this.onWorldStep();  // stream chunks as he crosses
      this.checkTransition();                        // stepped onto an arch/door?
      if (this.path && this.pathIdx >= this.path.length) {  // arrived
        this.path = null;
        const f = facingFor(dx, dy);
        setFacing(h, this.heroKey, f.name, f.flip, false);  // idle in last direction
      }
    } else {
      h.setPosition(h.x + (dx / dist) * step, h.y + (dy / dist) * step);
    }
    // In world mode, re-cull the visible tiles once the hero has drifted far enough
    // (also re-sorts mid). Otherwise just keep the mid depth order correct.
    if (this.mode === 'world' && this._lastRefreshXY &&
        Math.hypot(h.x - this._lastRefreshXY.x, h.y - this._lastRefreshXY.y) > REFRESH_STEP) {
      this.streamVisible();                          // re-cull + stream chunks near him
    } else {
      sortMid(this.planes.mid);
    }
    this.fitMap();                                   // camera follows the hero
  }

  setZoom(f) {
    this.zoomFactor = Phaser.Math.Clamp(f, ZOOM_MIN, ZOOM_MAX_IN);
    this.fitMap();
    this.refreshVisible();                            // zoom changes the visible tile set
    return this.zoomFactor;
  }

  // Hour of day (0-23) driving outdoor day/night — the device clock, like the base
  // game (FDUtils.j()). `_hourOverride` lets tests pin a specific hour.
  currentHour() {
    return this._hourOverride != null ? this._hourOverride : new Date().getHours();
  }

  setHour(h) {
    this._hourOverride = ((h % 24) + 24) % 24;
    if (this._map) {
      this._ambient = ambientColor(this._map, this._hourOverride);
      applyAmbient(this.planes, this._ambient);
    }
    return this._hourOverride;
  }

  // Boot: fetch the seamless-overworld grid, then run the title -> character-creation
  // flow. The chosen character begins in the tutorial (unless a test quick-starts).
  async boot() {
    try {
      const grid = await (await fetch('assets/world-grid.json')).json();
      this.overworld = new Overworld(this, grid);
    } catch (err) {
      console.warn('world-grid load failed; interiors only:', err);
      this.overworld = null;
    }
    if (this._quickStarted) return;                  // a test already started the game
    const pc = await startFlow(document.getElementById('game-root'));
    await this.startNewGame(pc);
  }

  // Apply a created character and drop into the tutorial. `pc` is the PlayerCreation
  // (name/gender/charClass/portrait/difficulty). Persisted so the run survives reload.
  async startNewGame(pc, startMap = TUTORIAL_MAP) {
    this.player = pc;
    this.heroKey = `hero_${pc.gender.toLowerCase()}`;
    this.charSpriteFile = pc.gender === 'FEMALE'
      ? 'assets/sprites/female_knight.png' : 'assets/sprites/male_knight.png';
    await this.goArea(startMap, startMap === TUTORIAL_MAP ? '0001' : null);
    try {
      await Saves.put('auto', { player: pc, area: startMap }, { name: pc.name, charClass: pc.charClass });
    } catch {}
  }

  // Dispatch to the right loader: outdoor [worldmap] tiles stream seamlessly (world
  // mode); everything else (towns, buildings, caves) is a discrete interior reached
  // through an arch/door.
  goArea(name, entryId) {
    if (this.overworld && this.overworld.has(name)) return this.enterWorld(name, entryId);
    return this.enterInterior(name, entryId);
  }

  // Tear down whatever is currently rendered, keeping the persistent hero. Pooled
  // overworld sprites are returned to the pool (not destroyed) so they can be reused;
  // everything else (interior tiles) is destroyed.
  clearScene() {
    if (this.overworld) this.overworld.clear();
    for (const key of ['floor', 'mid', 'roof']) {
      for (const im of this._active[key]) { this.planes[key].remove(im); im.setVisible(false); this._pool[key].push(im); }
      this._active[key].length = 0;
      this.planes[key].list.slice().forEach(ch => { if (ch !== this.hero) ch.destroy(); });
    }
  }

  async placeHeroAt(px, py) {
    if (!this.hero) {
      await loadHero(this, this.heroKey, this.charSpriteFile);
      this.hero = makeHero(this, this.planes.mid, this.heroKey, px, py);
    } else {
      this.planes.mid.add(this.hero);              // re-parent into the fresh mid plane
      this.hero.setPosition(px, py);
    }
  }

  // Enter the SEAMLESS overworld at chunk `name`, dropping the hero at entry `entryId`
  // (local cell of that chunk) translated into global space, then stream the 3x3
  // window around him. No load screen occurs again until he steps through an arch.
  async enterWorld(name, entryId) {
    try {
      this._loading = true;
      this.mode = 'world';
      this._streaming = false;
      this.path = null; this.pathIdx = 0;
      this.clearScene();

      const g = this.overworld.grid[name];
      this._curChunk = { col: g.col, row: g.row };
      await this.overworld.ensureChunk(name);         // bootstrap the start chunk

      const ch = this.overworld.loaded.get(name);
      this._map = ch.map; this._mapName = name;
      const e = (entryId != null && ch.map.entries && ch.map.entries[entryId])
        || { c: Math.floor(ch.map.width / 2), r: Math.floor(ch.map.height / 2) };
      const { gc0, gr0 } = this.overworld.originOf(g.col, g.row);
      this.heroCell = this.nearestWalkable(e.c + gc0, e.r + gr0);
      // now that we know his global cell, stream in whatever neighbours he's near
      await this.overworld.ensureWindow(this.heroCell.c, this.heroCell.r, this.currentHour());
      this._transLock = true;
      this.mapBounds = { width: 1, height: 1 };
      this.grid.setVisible(false);

      const hp = this.overworld.cellToPx(this.heroCell.c, this.heroCell.r);
      await this.placeHeroAt(hp.x, hp.y);
      this._ambient = ambientColor(ch.map, this.currentHour());
      this.fitMap();
      this.refreshVisible();                          // draw the tiles around the hero
      this._loading = false;
    } catch (err) {
      console.warn('world enter failed:', name, err);
      this._loading = false;
    }
  }

  // The hero moved a cell in world mode: if he crossed into a new chunk, make it the
  // current chunk (for ambient/transitions/HUD). Streaming itself is driven by
  // streamVisible() on the movement throttle, keyed to his global cell.
  onWorldStep() {
    const { col, row } = this.overworld.chunkOfGlobal(this.heroCell.c, this.heroCell.r);
    if (this._curChunk && col === this._curChunk.col && row === this._curChunk.row) return;
    const name = this.overworld.nameAt(col, row);
    if (!name) return;                               // stepped toward an off-grid edge
    this._curChunk = { col, row };
    const ch0 = this.overworld.loaded.get(name);
    if (ch0) { this._map = ch0.map; this._mapName = name; }
  }

  // Re-cull the visible tiles now, and stream chunks in/out around the hero's current
  // global cell (loading is async; redraw once the resident set actually changes).
  streamVisible() {
    this.refreshVisible();
    if (this._streaming) return;
    this._streaming = true;
    this.overworld.ensureWindow(this.heroCell.c, this.heroCell.r, this.currentHour())
      .then(({ changed }) => { this._streaming = false; if (changed) this.refreshVisible(); })
      .catch(() => { this._streaming = false; });
  }

  // Cull-render the overworld: draw only pooled tile sprites whose feet fall inside the
  // camera view (+margins) around the hero. Bounds draw cost to the viewport regardless
  // of how many chunks are streamed in. Also depth-sorts the visible mid tiles + hero.
  refreshVisible() {
    if (this.mode !== 'world' || !this.overworld || !this.hero) return;
    const z = (Math.max(this.LW, this.LH) / EK_VIEWPORT_W) * this.zoomFactor;
    const halfW = (this.LW / 2) / z + TILE_MARGIN;
    const halfH = (this.LH / 2) / z + TILE_MARGIN;
    const cx = this.hero.x, cy = this.hero.y;
    const minX = cx - halfW, maxX = cx + halfW;
    const minY = cy - halfH - TILE_TALL, maxY = cy + halfH;

    for (const key of ['floor', 'mid', 'roof']) {      // release last frame's sprites
      for (const im of this._active[key]) { this.planes[key].remove(im); this._pool[key].push(im); }
      this._active[key].length = 0;
    }
    const tint = tintOf(this._ambient || { r: 0.93, g: 0.93, b: 0.93 });
    let n = 0;
    for (const ch of this.overworld.loaded.values())
      for (const t of ch.tiles) {
        if (t.px < minX || t.px > maxX || t.py < minY || t.py > maxY) continue;
        let im = this._pool[t.plane].pop();
        if (!im) im = this.add.image(0, 0, t.key, t.frame).setOrigin(0.5, 1);
        im.setTexture(t.key, t.frame).setPosition(t.px, t.py).setTint(tint).setVisible(true);
        this.planes[t.plane].add(im);
        this._active[t.plane].push(im);
        n++;
      }
    this._mapTiles = n;
    sortMid(this.planes.mid);
    this._lastRefreshXY = { x: cx, y: cy };
  }

  // Load + render a discrete INTERIOR (town/building/cave), placing the hero at entry
  // `entryId` (or the map centre). The hero persists across maps; only tiles clear.
  async enterInterior(name, entryId) {
    try {
      this._loading = true;
      this.mode = 'interior';
      this._curChunk = null;
      this.path = null; this.pathIdx = 0;
      this.clearScene();

      const map = await loadMap(this, name);
      this._map = map; this._mapName = name;
      const ambient = ambientColor(map, this.currentHour());
      const { tiles, width, height } = renderMap(this, this.planes, map, ambient);
      this._mapTiles = tiles; this._ambient = ambient;
      this.mapBounds = { width, height };
      this.grid.setVisible(false);
      this.walk = buildWalkable(map);

      // Where to drop the hero: the named entry, else map centre — snapped walkable.
      const e = (entryId != null && map.entries && map.entries[entryId])
        || { c: Math.floor(map.width / 2), r: Math.floor(map.height / 2) };
      this.heroCell = this.nearestWalkable(e.c, e.r);
      this._transLock = true;                        // don't bounce off the return portal
      const hp = cellToPx(this.heroCell.c, this.heroCell.r, map);

      await this.placeHeroAt(hp.x, hp.y);
      sortMid(this.planes.mid);
      applyAmbient(this.planes, ambient);
      this.fitMap();
      this._loading = false;
    } catch (err) {
      console.warn('area load failed:', name, err);
      this._loading = false;
    }
  }

  // Camera: reproduce the base game's phone view — 533 world-units of the long
  // screen axis, gameplay zoom 1.0 (recovered from GameLevelRenderer) — following
  // the hero at the center of the viewport. `world` handles the orientation.
  fitMap() {
    if (!this.mapBounds) return;
    // APPROX (A2, see deobf/DEOBFUSCATION_STATUS.md): base game eases the camera toward
    // the player with a dead-zone (snaps if >320px); we hard-center every frame.
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
    this.refreshVisible();                            // viewport changed -> re-cull tiles
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
