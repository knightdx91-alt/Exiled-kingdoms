// Exiled Kingdoms — web rebuild (Phaser 3).
//
// Orientation defaults to AUTO — it follows the device like the original game's
// sensor rotation, but across all four orientations (portrait, reverse-portrait,
// landscape, reverse-landscape), not just landscape. In auto mode nothing is
// force-rotated: the browser reflows the viewport as the phone turns and Phaser's
// RESIZE mode refills the canvas upright, so pointer input stays correct.
// The orient bar also offers manual LOCKS (0/90/180/270): those keep the canvas
// axis-aligned and rotate a single `world` container in-engine instead, so Phaser
// hit-tests interactive children through the rotation and taps still land right.

import { loadMap, renderMap, sortMid, ambientColor, applyAmbient } from './map.js';
import { loadHero, makeHero, setFacing } from './sprite.js';
import { buildWalkable, cellToPx, pxToCell, findPath, facingFor,
         findPathWorld } from './move.js';
import { Overworld } from './world.js';
import { startFlow } from './char-create.js';
import { Joystick } from './joystick.js';
import { Saves } from './saves.js';
import { Dialogue } from './dialogue.js';
import { spriteName, loadNpcSheet, makeNpc, bestiaryOf, npcPortrait } from './entity.js';
import { PlayerModel } from './player.js';
import { HUD } from './hud.js';
import { Combat } from './combat.js';
import { RenderFX } from './render_fx.js';

const HERO_SPEED = 140;                             // px/sec along the path (map-space)

const tintOf = (c) => (Math.round(c.r * 255) << 16) |
                      (Math.round(c.g * 255) << 8) | Math.round(c.b * 255);
const rint = (a, b) => a + Math.floor(Math.random() * (b - a + 1));

const ORIENTS = [0, 90, 180, 270];
const TUTORIAL_MAP = 'I10_tutorial';                // Adaon's road — the old (now-skipped) tutorial
const DEV_START_MAP = 'H10';                        // Lannegar Valley — test/quick-start entry
// New games skip the playable tutorial and open with the "wake up robbed" beat in
// Lannegar Valley (H10), at the same east-edge entry the old tutorial's Travel# used.
// The exact post-tutorial state is set on start — see deobf/TUTORIAL_SKIP_SPEC.md.
const INTRO_MAP = 'H10';
const INTRO_ENTRY = '0001';

// Resolve a spawn point on `map` for a requested entry id: the named entry, else map
// centre. When `preferDefault` is set (scripted Travel#, which may name an entry our
// TMX export doesn't carry), the conventional '0001' arrival marker is tried before
// centre — e.g. the tutorial's Travel#H10,1,14 → H10's 0001 (east edge, the
// geographically correct arrival). Normal arch transitions pass preferDefault=false so
// they never bounce off a map's 0001 portal. APPROX — see DEOBFUSCATION_STATUS.md §3.
function entryOf(map, entryId, preferDefault = false) {
  const es = map && map.entries;
  return (entryId != null && es && es[entryId])
    || (preferDefault && es && es['0001'])
    || { c: Math.floor(map.width / 2), r: Math.floor(map.height / 2) };
}
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
    this.autoOrient = true;                         // follow the device by default
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
    // Perf HUD: rendered/total counts the entity culler below fills in each refresh.
    this._entityStats = { visible: 0, total: 0 };
    this._perfNextAt = 0;

    // Movement control scheme: 'tap' (tap-to-move A*) or 'joystick' (free-floating).
    this.control = 'tap';
    this.heroKey = 'hero';
    this.charSpriteFile = 'assets/sprites/male_knight.png';
    const root = document.getElementById('overlay-root');
    this.joystick = new Joystick(root);
    // Gameplay settings (mirror SettingsData): attackInteracts folds interact into the
    // attack button; showNumbersBars prints values on the HP/mana bars.
    this.settings = { attackInteracts: true, showNumbersBars: false, showPerf: true };
    try {
      const s = JSON.parse(localStorage.getItem('ek_settings') || '{}');
      Object.assign(this.settings, s);
    } catch (e) { /* private mode */ }
    this._wireSettings();

    // Entities + dialogue + shared world state (variables/party) for NPCs and quests.
    this.entities = [];                              // { sprite, npc, cell, group }
    this.containers = [];                            // { sprite, cell, data, group, opened }
    this.bestiary = {};
    this.gameState = { vars: {}, followers: new Set() };
    this.dialogue = new Dialogue(this, root);
    this.gameHud = new HUD(root);
    this.combat = new Combat(this);
    this.renderFx = new RenderFX(this);
    this._firedTriggers = new Set();

    this.boot();

    // Restore the saved orientation choice; default to device-following 'auto'.
    let savedOrient = null;
    try { savedOrient = localStorage.getItem('ek_orient'); } catch (e) { /* private mode */ }
    this.setOrient(savedOrient || 'auto');
    // When the device physically rotates, re-fit in auto mode. Phaser's own
    // 'resize' also fires as the canvas changes shape; this is a belt-and-braces
    // signal for mobile browsers that report orientation before the resize.
    const onDeviceRotate = () => { if (this.autoOrient) { this.scale.refresh(); this.relayout(); } };
    window.addEventListener('orientationchange', onDeviceRotate);
    if (window.matchMedia) {
      try { window.matchMedia('(orientation: portrait)').addEventListener('change', onDeviceRotate); }
      catch (e) { /* older Safari: no addEventListener on MQL */ }
    }

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
        if (!Object.keys(this.bestiary).length) {
          try { this.bestiary = await (await fetch('assets/data/bestiary.json')).json(); } catch {}
        }
        if (!this.weapons) { try { this.weapons = await (await fetch('assets/data/weapons.json')).json(); } catch {} }
        if (!this.loot) { try { this.loot = await (await fetch('assets/data/loot.json')).json(); } catch {} }
        if (!this.quests) { try { this.quests = await (await fetch('assets/data/quests.json')).json(); this.gameHud.setQuests(this.quests, () => this.gameState.vars); } catch {} }
        if (!this.trainers) { try { this.trainers = await (await fetch('assets/data/trainers.json')).json(); this.gameHud.trainers = this.trainers; } catch {} }
        if (!this.items) { try { this.items = await (await fetch('assets/data/items.json')).json(); this.gameHud.items = this.items; } catch {} }
        if (!this._spriteSet) {
          try { this._spriteSet = new Set(await (await fetch('assets/sprites/index.json')).json()); } catch { this._spriteSet = new Set(); }
        }
        if (!this._creation) {
          try { this._creation = await (await fetch('assets/data/creation.json')).json(); } catch {}
        }
        await this.loadIconSets();
        const pc = Object.assign(
          { name: 'Tester', gender: 'MALE', charClass: 'WARRIOR', portrait: null, difficulty: 1,
            attributes: { STR: 0, END: 0, AGI: 0, INT: 0, AWA: 0, PER: 0 }, startingSkill: null },
          opts.pc || {});
        await this.startNewGame(pc, opts.map || DEV_START_MAP, { intro: opts.intro === true });
        return { name: this._mapName, mode: this.mode };
      },
      player: () => this.player ? { ...this.player } : null,
      control: () => this.control,
      setControl: (m) => this.setControl(m),
      // test helper: drive the free-floating joystick with a screen vector (-1..1).
      stick: (x, y) => { this.joystick.active = (x || y) ? true : false; this.joystick.vec = { x, y }; },
      titleShown: () => !!document.querySelector('.cc-overlay'),
      // entities + dialogue introspection for tests
      entities: () => this.entities.map(e => ({ name: e.npc.name, conv: e.npc.conversation, cell: { ...e.cell } })),
      talkNearest: () => { const e = this.entities.find(x => x.npc.conversation); if (!e) return null; this.talkTo(e); return e.npc.name; },
      dlg: () => this.dialogue.active ? {
        name: this.dialogue.$name.textContent, text: this.dialogue.$text.textContent,
        choices: [...this.dialogue.$choices.children].map(b => b.textContent),
      } : null,
      dlgChoose: (i) => { const bs = [...this.dialogue.$choices.children]; if (bs[i]) bs[i].click(); return this.dialogue.active; },
      followers: () => [...this.gameState.followers],
      setVar: (k, v) => { this.gameState.vars[k] = v; },
      getVar: (k) => this.gameState.vars[k],
      addFollower: (id) => this.gameState.followers.add(id),
      saveAuto: (area) => this.saveAuto(area),
      runAction: (s) => this.dialogue.runActions(s),
      inventoryDebug: () => { const m = this.playerModel; return m ? {
        backpack: [...m.backpack], equipment: { ...m.equipment },
        armor: m.armor(), weaponId: m.weaponId(), maxHP: m.maxHP(),
        resist: m.resist(), shield: m.hasShield() } : null; },
      equip: (id) => this.playerModel && this.playerModel.equip(+id),
      unequip: (slot) => this.playerModel && this.playerModel.unequip(slot),
      useItem: (id) => this.useItem(+id),
      setSkillRank: (id, r) => this.playerModel && this.playerModel.setSkillRank(id, r),
      castSkill: (id) => this.castSkill(id),
      refillMana: () => { const m = this.playerModel; if (m) m.mana = m.maxMana(); },
      skillDebug: () => { const m = this.playerModel, c = this.combat; return m ? {
        mana: Math.ceil(m.mana), maxMana: m.maxMana(),
        cooldowns: Object.keys(c.cooldowns), buffs: c.buffs.length,
        armor: (c._heroCbt() || {}).armor } : null; },
      playerModelDebug: () => { const m = this.playerModel; return m ? {
        learnsAll: m.learnsAll(), trained: [...m.trained], disciplines: [...m.disciplines],
        skillPoints: m.skillPoints,
        canW: m.canUseItemClass('W'), canR: m.canUseItemClass('R'),
        canC: m.canUseItemClass('C'), canM: m.canUseItemClass('M'),
        canBlank: m.canUseItemClass('') } : null; },
      // combat introspection / drivers for tests
      combatants: () => this.entities.filter(e => e.cbt).map(e => ({
        name: e.npc.name, side: e.cbt.side, hp: e.cbt.hp, maxHp: e.cbt.maxHp,
        dead: !!e.cbt.dead, cell: { ...e.cell } })),
      targetEnemy: () => { const f = this.entities.find(e => e.cbt && e.cbt.side === 'enemy' && !e.cbt.dead); if (f) this.combat.setTarget(f); return f ? f.npc.name : null; },
      targetByName: (name, cell) => { const f = this.entities.find(e => e.cbt && e.cbt.side === 'enemy' && !e.cbt.dead && e.npc.name === name && (!cell || (e.cell.c === cell.c && e.cell.r === cell.r))); if (f) this.combat.setTarget(f); return !!f; },
      hp: () => this.playerModel ? Math.ceil(this.playerModel.hp) : null,
      teleport: (c, r) => { const g = this.nearestWalkable(c, r); this.heroCell = g; const p = this.toPx(g.c, g.r); if (this.hero) this.hero.setPosition(p.x, p.y); this.path = null; return g; },
      fx: () => { const f = this.renderFx; if (!f) return null;
        const imgs = f.images || [];
        return { enabled: f.enabled, fog: f.useFog, tiles: imgs.length,
          hidden: imgs.filter(i => i.visible === false).length,
          dimmed: imgs.filter(i => i.visible !== false && i._baseTint != null && i.tintTopLeft === f.dimTint).length,
          faded: (f.fadeImgs || []).filter(i => i.alpha < 1).length,
          explored: f.explored ? f.explored.reduce((a, b) => a + b, 0) : 0,
          lights: (f.glows || []).length, playerGlow: !!f.playerGlow }; },
      paused: () => this.combat ? this.combat.paused : false,
      togglePause: () => this.setPaused(this.combat.togglePause()),
      // damage a named enemy directly (test helper)
      hurtEnemy: (name, n) => { const e = this.entities.find(x => x.cbt && x.npc.name === name); if (e) { e.cbt.hp = Math.max(0, e.cbt.hp - n); if (e.cbt.hp <= 0) { e.cbt.dead = true; this.combat._onEnemyDeath(e); } } return e ? e.cbt.hp : null; },
      // test helper: open a conversation at a specific node (e.g. the tutorial camp's
      // sleep/rob/travel node) without having to walk into its trigger zone.
      startConv: (id, node, speaker) => this.dialogue.start(id, node, speaker),
      fading: () => !!(this._fadeEl && parseFloat(this._fadeEl.style.opacity) > 0.5),
      // player model + HUD introspection for tests
      stats: () => { const m = this.playerModel; return m ? {
        class: m.charClass, level: m.level(), hp: Math.ceil(m.hp), maxhp: m.maxHP(),
        mana: Math.ceil(m.mana), maxmana: m.maxMana(), xp: m.xp, gold: m.gold,
        caster: m.isCaster(), attrs: { ...m.attributes } } : null; },
      hudShown: () => this.gameHud && this.gameHud.el.style.display !== 'none',
      gainXP: (n) => { this.playerModel && this.playerModel.gainXP(n); return this.playerModel && this.playerModel.level(); },
      hurt: (n) => { this.playerModel && this.playerModel.damage(n); return this.playerModel && Math.ceil(this.playerModel.hp); },
      openChar: () => { this.gameHud.openCharWindow(); return !!this.gameHud.cw.innerHTML; },
      containers: () => this.containers.map(k => ({ cell: { ...k.cell }, items: k.data.items, opened: k.opened })),
      openNearestContainer: () => { const k = this.containerNear(this.heroCell.c, this.heroCell.r, 999); if (k) this.openContainer(k); return k ? k.data.items : null; },
      // perf introspection: FPS + what's actually being drawn, for diagnosing slowdowns
      perf: () => ({
        fps: Math.round(this.game.loop.actualFps || 0),
        frameMs: this.game.loop.delta || 0,
        tiles: this._mapTiles || 0,
        entities: { ...(this._entityStats || { visible: 0, total: 0 }) },
        chunks: this.overworld ? [...this.overworld.loaded.keys()] : [],
        cache: window.__EK && window.__EK.cache ? { ...window.__EK.cache } : null,
      }),
      setShowPerf: (on) => { this.settings.showPerf = !!on; },
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
      this.handleTap(p.x, p.y);
    });
    // Space bar toggles the real-time-with-pause freeze.
    if (this.input.keyboard) this.input.keyboard.on('keydown-SPACE', () => {
      if (this.combat) this.setPaused(this.combat.togglePause());
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

  // Cell -> pixel in the current mode's space (world = global cells, interior = local).
  toPx(c, r) {
    return this.mode === 'world' ? this.overworld.cellToPx(c, r) : cellToPx(c, r, this._map);
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

  // A tap either talks to a nearby NPC or walks. If the tapped NPC isn't adjacent, the
  // hero paths to it and talks on arrival (_pendingTalk). Ignored while a dialogue is open.
  handleTap(sx, sy) {
    if (this._dialogue || this._loading || !this.hero || !this._map) return;
    const local = this.mapLayer.getWorldTransformMatrix().applyInverse(sx, sy);
    const cell = this.mode === 'world'
      ? this.overworld.pxToCell(local.x, local.y) : pxToCell(local.x, local.y, this._map);
    // Tapping a live enemy targets it — combat walks the hero into reach and attacks.
    const foe = this.enemyNear(cell.c, cell.r, 1.8);
    if (foe && this.combat) { this._pendingTalk = null; this.combat.setTarget(foe); return; }
    const e = this.entityNear(cell.c, cell.r, 1.6);
    if (e) {
      if (Math.hypot(e.cell.c - this.heroCell.c, e.cell.r - this.heroCell.r) <= 1.6) { this.talkTo(e); return; }
      const goal = this.nearestWalkable(e.cell.c, e.cell.r);
      const path = this.planPath(this.heroCell, goal);
      if (path && path.length > 1) { this.path = path; this.pathIdx = 1; this._pendingTalk = e; }
      return;
    }
    // Tapping a crate/chest: open it if adjacent, else walk up and open on arrival.
    const k = this.containerNear(cell.c, cell.r, 1.6);
    if (k) {
      this._pendingTalk = null;
      if (Math.hypot(k.cell.c - this.heroCell.c, k.cell.r - this.heroCell.r) <= 1.6) { this.openContainer(k); return; }
      const goal = this.nearestWalkable(k.cell.c, k.cell.r);
      const path = this.planPath(this.heroCell, goal);
      if (path && path.length > 1) { this.path = path; this.pathIdx = 1; this._pendingContainer = k; }
      return;
    }
    this._pendingTalk = null;
    if (this.combat) this.combat.clearTarget();       // tapping the ground disengages
    this.moveTo(sx, sy);
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
    if (this.playerModel) this.gameHud.update();         // cheap; only writes DOM on change
    // Track two-finger pinch distance frame-to-frame and scale the zoom by its ratio.
    const ps = [this.input.pointer1, this.input.pointer2].filter(p => p && p.isDown);
    if (ps.length === 2) {
      const d = Phaser.Math.Distance.Between(ps[0].x, ps[0].y, ps[1].x, ps[1].y);
      if (this._pinchPrev) this.setZoom(this.zoomFactor * (d / this._pinchPrev));
      this._pinchPrev = d;
    } else {
      this._pinchPrev = null;
    }
    if (this._dialogue) return;                      // gameplay pauses during dialogue
    if (this.control === 'joystick') this.stepJoystick(dtMs / 1000);
    else this.stepHero(dtMs / 1000);
    // Attack button held → keep striking (weapon cooldown paces it inside heroSwing).
    if (this.gameHud && this.gameHud.attackHeld() && !this._loading) this.combat.heroSwing();
    if (this.combat) this.combat.tick(dtMs);         // real-time-with-pause combat
    if (this.renderFx) this.renderFx.update(dtMs);   // roof-fade / fog / light flicker
    this.updatePerfHud(_t);
    this.refreshContext(_t);
  }

  // Refresh the top-right context/interact buttons from whatever is in reach of the hero
  // (GameHUD.S(); see deobf/CONTEXT_ACTIONS_SPEC.md). Scans nearby talk-NPCs, containers,
  // dropped loot bags and door/arch transitions, sends the nearest ≤4 to the HUD, and
  // hides the bar when nothing's around. Throttled — the button set changes slowly and
  // setContext() skips the rebuild when the list is unchanged anyway.
  refreshContext(t = 0) {
    if (!this.gameHud) return;
    if (t && t < (this._ctxNextAt || 0)) return;
    this._ctxNextAt = t + 160;
    if (this._dialogue || this._loading || !this.hero || !this.heroCell || !this.mode) {
      this.gameHud.setContext([], null);
      return;
    }
    const hc = this.heroCell;
    const REACH = 4;                                  // cells; icon appears as you approach
    const out = [];
    // Talk-NPCs and followers with a conversation (never hostile enemies). Followers are
    // allies in `entities`; they show a talk icon too so you can speak to your companions.
    for (const e of this.entities) {
      if (!e.npc || !e.npc.conversation || (e.cbt && e.cbt.side === 'enemy')) continue;
      const d = Math.hypot(e.cell.c - hc.c, e.cell.r - hc.r);
      if (d > REACH) continue;
      const label = (e.npc.name || 'NPC').replace(/_/g, ' ').replace(/\b\w/g, ch => ch.toUpperCase());
      out.push({ d, kind: 'talk', key: `talk:${e.npc.name}:${e.cell.c},${e.cell.r}`,
        icon: npcPortrait(e.rec), label, _e: e });
    }
    for (const k of this.containers) {
      if (k.opened) continue;
      const d = Math.hypot(k.cell.c - hc.c, k.cell.r - hc.r);
      if (d > REACH) continue;
      const isLoot = !!k.isLoot;
      const icon = (!isLoot && k.data.icon && this._objIcons && this._objIcons.has(k.data.icon))
        ? `assets/ui/objects/${k.data.icon}.png` : 'assets/sprites/loot.png';
      out.push({ d, kind: isLoot ? 'pickup' : 'open',
        key: `cont:${k.cell.c},${k.cell.r}`, icon, label: isLoot ? 'Loot' : (k.data.name || 'Open'), _k: k });
    }
    for (const tr of this.currentTransitions()) {
      if (!tr.area) continue;
      const d = Math.hypot(tr.c - hc.c, tr.r - hc.r);
      if (d > REACH) continue;
      out.push({ d, kind: 'enter', key: `enter:${tr.area}:${tr.c},${tr.r}`,
        icon: 'assets/ui/objects/door.png', label: 'Enter', _t: tr });
    }
    out.sort((a, b) => a.d - b.d);
    this._ctxList = out;
    this.gameHud.setContext(out, (key) => this.doContext(key));
  }

  // Perform a context button's action (GameHUD.E(i)). Walks the hero up to the target if
  // he isn't already adjacent, then talks / opens / picks up / travels — reusing the same
  // pending-arrival hooks as tap-to-interact so behaviour is identical either way.
  doContext(key) {
    const a = (this._ctxList || []).find(x => x.key === key);
    if (!a || this._dialogue || this._loading) return;
    const hc = this.heroCell;
    if (a.kind === 'talk' && a._e) {
      const e = a._e;
      if (Math.hypot(e.cell.c - hc.c, e.cell.r - hc.r) <= 1.6) { if (this.combat) this.combat.clearTarget(); this.talkTo(e); return; }
      const path = this.planPath(hc, this.nearestWalkable(e.cell.c, e.cell.r));
      if (path && path.length > 1) { this.path = path; this.pathIdx = 1; this._pendingTalk = e; }
    } else if ((a.kind === 'open' || a.kind === 'pickup') && a._k) {
      const k = a._k;
      if (Math.hypot(k.cell.c - hc.c, k.cell.r - hc.r) <= 1.6) { this.openContainer(k); return; }
      const path = this.planPath(hc, this.nearestWalkable(k.cell.c, k.cell.r));
      if (path && path.length > 1) { this.path = path; this.pathIdx = 1; this._pendingContainer = k; }
    } else if (a.kind === 'enter' && a._t) {
      const tr = a._t;
      if (Math.abs(tr.c - hc.c) <= 1 && Math.abs(tr.r - hc.r) <= 1) { this.path = null; this.goArea(tr.area, tr.entry); return; }
      const path = this.planPath(hc, this.nearestWalkable(tr.c, tr.r));
      if (path && path.length > 1) { this.path = path; this.pathIdx = 1; }
    }
  }

  // Drop a loot bag at a global/interior cell (an enemy's death spot). It becomes a
  // tappable, context-button interactable container carrying the rolled gold + item ids
  // (deobf/CONTEXT_ACTIONS_SPEC.md). Reuses the container render/open path; loaded into
  // the current entity group so it streams out with its chunk in world mode.
  dropLootBag(cell, itemIds, gold) {
    if ((!itemIds || !itemIds.length) && !gold) return;
    const p = this.toPx(cell.c, cell.r);
    let s;
    if (this.textures.exists('container_icon')) {
      s = this.add.image(p.x, p.y, 'container_icon').setOrigin(0.5, 0.9);
    } else {
      s = this.add.rectangle(p.x, p.y - 8, 18, 14, 0xcaa04a).setStrokeStyle(2, 0x8a6d33).setOrigin(0.5, 1);
    }
    this.planes.mid.add(s);
    const group = (this.mode === 'world' && this._curChunk)
      ? this.overworld.nameAt(this._curChunk.col, this._curChunk.row) : 'interior';
    this.containers.push({ sprite: s, cell: { c: cell.c, r: cell.r }, group, opened: false,
      isLoot: true, gold: gold || 0, data: { items: (itemIds || []).join(','), name: 'Loot', icon: 'loot' } });
    sortMid(this.planes.mid);
  }

  // On-screen FPS + diagnostics ("why is it dropping"): frame rate, tiles/NPCs actually
  // being drawn this frame vs how many exist in the resident chunks, how many chunks are
  // streamed in, and whether the game is fully cached for offline (vs still downloading/
  // hitting the network). Toggled from Settings ("Show performance stats"); throttled to
  // ~3/sec so the readout itself is not a perf cost.
  updatePerfHud(t) {
    if (!this.settings.showPerf) { if (this._perfEl) this._perfEl.style.display = 'none'; return; }
    if (t < this._perfNextAt) return;
    this._perfNextAt = t + 300;
    let el = this._perfEl;
    if (!el) {
      el = document.createElement('div');
      el.id = 'ek-perf';
      document.getElementById('overlay-root').appendChild(el);
      this._perfEl = el;
    }
    el.style.display = 'flex';
    const fps = Math.round(this.game.loop.actualFps || 0);
    const frameMs = (this.game.loop.delta || 0).toFixed(0);
    const es = this._entityStats || { visible: 0, total: 0 };
    const chunks = this.overworld ? this.overworld.loaded.size : (this.mode === 'interior' ? 1 : 0);
    const cache = window.__EK && window.__EK.cache;
    const cacheTxt = !cache ? '—' : cache.complete ? 'offline'
      : cache.total ? `${Math.floor(100 * cache.done / cache.total)}%` : '…';
    // Stacked stat lines so the readout forms a narrow strip down the RIGHT edge (the
    // top-left of the owner's screen is cracked). Each line: label over value.
    const rows = [
      ['FPS', String(fps)],
      ['ms', frameMs],
      ['tiles', String(this._mapTiles || 0)],
      ['npc', `${es.visible}/${es.total}`],
      ['chunk', String(chunks)],
      ['cache', cacheTxt],
    ];
    el.innerHTML = rows.map(([k, v]) =>
      `<div class="ek-perf-row"><span class="ek-perf-k">${k}</span><span class="ek-perf-v">${v}</span></div>`).join('');
    el.classList.toggle('ek-perf-warn', fps > 0 && fps < 30);
  }

  // Swap control scheme. Tap-to-move uses Phaser pointer input; the joystick uses its
  // own pointer layer (which, when enabled, sits over the canvas so taps don't also
  // fire a move). Cancels any in-progress A* path when switching to the joystick.
  setControl(mode) {
    this.control = (mode === 'joystick') ? 'joystick' : 'tap';
    this.joystick.enable(this.control === 'joystick');
    if (this.control === 'joystick') { this.path = null; }
    try { localStorage.setItem('ek_control', this.control); } catch (e) { /* private mode */ }
    document.querySelectorAll('#control-bar button').forEach(b =>
      b.setAttribute('aria-pressed', String(b.dataset.control === this.control)));
    return this.control;
  }

  // Reflect the combat pause state with a small banner over the game.
  setPaused(on) {
    let el = document.getElementById('ek-paused');
    if (!el) {
      el = document.createElement('div');
      el.id = 'ek-paused';
      el.textContent = '❚❚ PAUSED';
      el.style.cssText = 'position:fixed;top:8px;left:50%;transform:translateX(-50%);' +
        'z-index:50;color:#ffd54a;font:600 14px system-ui;background:rgba(0,0,0,.55);' +
        'padding:4px 12px;border-radius:12px;pointer-events:none';
      document.getElementById('overlay-root').appendChild(el);
    }
    el.style.display = on ? 'block' : 'none';
    return on;
  }

  // Wire the Settings panel: the gear opens/closes it; inside are the orientation
  // and movement controls. Kept out of the way until the player wants them.
  _wireSettings() {
    const panel = document.getElementById('settings-panel');
    const gear = document.getElementById('settings-btn');
    if (gear && panel) {
      gear.onclick = () => { panel.hidden = !panel.hidden; };
      const close = document.getElementById('settings-close');
      if (close) close.onclick = () => { panel.hidden = true; };
    }
    const cbar = document.getElementById('control-bar');
    if (cbar) cbar.addEventListener('click', (e) => {
      const b = e.target.closest('button');
      if (b) this.setControl(b.dataset.control);
    });
    // Restore the saved movement scheme (default tap) and reflect it on the buttons.
    let savedControl = null;
    try { savedControl = localStorage.getItem('ek_control'); } catch (e) { /* private mode */ }
    this.setControl(savedControl || 'tap');

    // Combat toggles: attackInteracts + showNumbersBars, persisted to ek_settings.
    const ai = document.getElementById('set-attackinteracts');
    const sn = document.getElementById('set-shownumbers');
    const persist = () => {
      try { localStorage.setItem('ek_settings', JSON.stringify(this.settings)); } catch (e) { /* private */ }
      if (this.gameHud) { this.gameHud.settings = this.settings; this.gameHud.update(true); }
    };
    if (ai) { ai.checked = !!this.settings.attackInteracts;
      ai.onchange = () => { this.settings.attackInteracts = ai.checked; persist(); }; }
    if (sn) { sn.checked = !!this.settings.showNumbersBars;
      sn.onchange = () => { this.settings.showNumbersBars = sn.checked; persist(); }; }
    const sp = document.getElementById('set-showperf');
    if (sp) { sp.checked = this.settings.showPerf !== false;
      sp.onchange = () => { this.settings.showPerf = sp.checked; persist(); }; }
  }

  // The Settings gear stays reachable; nothing else to hide now that the movement
  // toggle moved into the panel. (Kept as a no-op-safe hook for the boot flow.)
  setChromeHidden() { /* controls now live in the Settings panel */ }

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
    this.checkTriggers();
  }

  // ---- Entities (NPCs / monsters placed from the map's `spawn` objects) ---------

  // Spawn an entity group. `toPx(c,r)` maps a cell (local for interiors, global for
  // world chunks) to pixels. Sheets shared across NPCs load once.
  async spawnEntities(npcs, group, toPx, off = { c: 0, r: 0 }) {
    if (!npcs || !npcs.length) return;
    this._entityGroups = this._entityGroups || new Set();
    this._entityGroups.add(group);
    // Load every distinct sprite sheet this batch needs IN PARALLEL. A town like
    // Lannegar (G10, 45 NPCs) used to await loadNpcSheet one NPC at a time here, so
    // every new sheet was a serial Phaser load-cycle round trip before the next NPC's
    // load even started -- a multi-second stutter walking near it. Batch instead.
    const names = new Set(npcs.map(npc => spriteName(this.bestiary, npc, this._spriteSet)));
    const keyOf = new Map();
    await Promise.all([...names].map(async (s) => keyOf.set(s, await loadNpcSheet(this, s))));
    if (this._entityGroups && !this._entityGroups.has(group)) return; // group cleared mid-load
    for (const npc of npcs) {
      const key = keyOf.get(spriteName(this.bestiary, npc, this._spriteSet));
      if (!key) continue;
      const p = toPx(npc.c, npc.r);
      const s = makeNpc(this, this.planes.mid, key, p.x, p.y);
      // Store the cell in the SAME space the scene uses for the hero (global cells in
      // world mode, local in interiors) so reach tests, combat AI and context buttons all
      // agree. `toPx` already globalizes the pixel; `off` globalizes the cell to match.
      const e = { sprite: s, npc, cell: { c: off.c + npc.c, r: off.r + npc.r }, group, key,
        rec: bestiaryOf(this.bestiary, npc) };
      this.entities.push(e);
      if (this.combat) this.combat.attach(e);        // enemies/allies get combat state
    }
    sortMid(this.planes.mid);
  }

  despawnGroup(group) {
    this.entities = this.entities.filter(e => {
      if (e.group !== group) return true;
      e.sprite.destroy(); return false;
    });
    this.containers = this.containers.filter(k => {
      if (k.group !== group) return true;
      if (k.sprite) k.sprite.destroy(); return false;
    });
    if (this._entityGroups) this._entityGroups.delete(group);
  }

  clearEntities() {
    for (const e of this.entities) e.sprite.destroy();
    this.entities = [];
    for (const k of this.containers) if (k.sprite) k.sprite.destroy();
    this.containers = [];
    this._entityGroups = new Set();
  }

  // ---- Containers (crates/chests/barrels placed from a map's `containers` objects) ---
  // EK draws these as interactable world objects (GameHUD context action type 7). The
  // recovered TMX object gives a cell, an `icon` (atlas region we don't have) and a csv
  // `items` list. We render each as a loot marker (sprites/loot.png) at its cell so it's
  // visible + tappable; opening it transfers its items into the backpack (deobf/
  // GAMEHUD_LAYOUT_SPEC.md §3, INVENTORY_SPEC.md). APPROX: loot icon stands in for the
  // real crate/chest art (unavailable without the APK atlas) — logged in §3.
  async spawnContainers(list, group, toPx, off = { c: 0, r: 0 }) {
    if (!list || !list.length) return;
    // Resolve each container's real object icon (assets/ui/objects/<icon>.png) when it's
    // been extracted; otherwise use the loot marker. Queue every needed texture, load in
    // one batch, then place the sprites.
    const iconKey = (data) => (data.icon && this._objIcons && this._objIcons.has(data.icon))
      ? `obj_${data.icon}` : 'container_icon';
    const toLoad = new Map();
    if (!this.textures.exists('container_icon')) toLoad.set('container_icon', 'assets/sprites/loot.png');
    for (const data of list) {
      const k = iconKey(data);
      if (k !== 'container_icon' && !this.textures.exists(k)) toLoad.set(k, `assets/ui/objects/${data.icon}.png`);
    }
    if (toLoad.size) {
      try {
        for (const [k, url] of toLoad) this.load.image(k, url);
        await new Promise((res) => { this.load.once('complete', res); this.load.start(); });
      } catch { /* fall back to a drawn marker below */ }
    }
    for (const data of list) {
      const p = toPx(data.c, data.r);
      const key = iconKey(data);
      let s;
      if (this.textures.exists(key)) {
        s = this.add.image(p.x, p.y, key).setOrigin(0.5, 0.9);
      } else if (this.textures.exists('container_icon')) {
        s = this.add.image(p.x, p.y, 'container_icon').setOrigin(0.5, 0.9);
      } else {
        s = this.add.rectangle(p.x, p.y - 10, 20, 16, 0x8a5a22).setStrokeStyle(2, 0xd8b56a).setOrigin(0.5, 1);
      }
      this.planes.mid.add(s);
      this.containers.push({ sprite: s, cell: { c: off.c + data.c, r: off.r + data.r }, data, group, opened: false });
    }
    sortMid(this.planes.mid);
  }

  // The nearest un-opened container to cell (c,r), within `rad`.
  containerNear(c, r, rad = 1.6) {
    let best = null, bd = rad + 0.001;
    for (const k of this.containers) {
      if (k.opened) continue;
      const d = Math.hypot(k.cell.c - c, k.cell.r - r);
      if (d <= bd) { bd = d; best = k; }
    }
    return best;
  }

  // Open a container: parse its csv item ids into the backpack (id === -2 → gold), show a
  // floater per item, then mark it looted (fade the marker out). Empty ones say "Empty".
  openContainer(k) {
    if (!k || k.opened) return;
    k.opened = true;
    const m = this.playerModel;
    const ids = (k.data.items || '').split(/[,;]/).map(s => s.trim()).filter(Boolean);
    let got = 0;
    // Loot bags carry an exact gold amount (from the kill); crates roll a small purse.
    if (k.isLoot && k.gold) {
      if (m) m.addGold(k.gold);
      if (this.combat) this.combat._floater(k.sprite, `+${k.gold}g`, false, false, '#ffd54a');
      got++;
    }
    for (const raw of ids) {
      const id = +raw;
      if (!id) continue;
      if (id === -2) { if (m) m.addGold(rint(3, 12)); got++; continue; }
      if (m) m.addItem(id);
      const nm = (this.items && this.items[id] && this.items[id].name) || `item ${id}`;
      if (this.combat) this.combat._floater(k.sprite, nm, false, false, '#9ad');
      got++;
    }
    if (!got && this.combat) this.combat._floater(k.sprite, 'Empty', false, false, '#aaa');
    if (this.gameHud) this.gameHud.update(true);
    const s = k.sprite;
    if (s && s.scene) s.scene.tweens.add({ targets: s, alpha: 0, duration: 500,
      onComplete: () => { if (s) s.destroy(); } });
  }

  // Keep world-chunk entity groups in sync with the currently-loaded chunks.
  syncWorldEntities() {
    if (this.mode !== 'world' || !this.overworld) return;
    const loaded = this.overworld.loaded;
    for (const g of [...(this._entityGroups || [])])
      if (g !== 'interior' && !loaded.has(g)) this.despawnGroup(g);
    for (const [name, ch] of loaded) {
      if (this._entityGroups && this._entityGroups.has(name)) continue;
      // Register the group here, not (only) inside spawnEntities: a chunk with containers
      // but zero NPCs makes spawnEntities early-return before it records the group, so it
      // would never be marked resident and its containers would be re-spawned on every
      // stream cycle -- an unbounded sprite leak. Marking it here makes the skip guard
      // above cover container-only chunks too.
      this._entityGroups = this._entityGroups || new Set();
      this._entityGroups.add(name);
      const gc0 = ch.col * this.overworld.CW, gr0 = ch.row * this.overworld.CH;
      const toPx = (c, r) => this.overworld.cellToPx(gc0 + c, gr0 + r);
      this.spawnEntities(ch.map.npcs, name, toPx, { c: gc0, r: gr0 });
      this.spawnContainers(ch.map.containers, name, toPx, { c: gc0, r: gr0 });
    }
  }

  // The nearest live enemy combatant to cell (c,r), within `rad`.
  enemyNear(c, r, rad = 1.8) {
    let best = null, bestD = rad + 0.001;
    for (const e of this.entities) {
      if (!e.cbt || e.cbt.dead || e.cbt.side !== 'enemy') continue;
      const d = Math.hypot(e.cell.c - c, e.cell.r - r);
      if (d <= bestD) { bestD = d; best = e; }
    }
    return best;
  }

  // The interactable NPC (has a conversation) nearest to cell (c,r), within `rad`.
  entityNear(c, r, rad = 1) {
    let best = null, bestD = rad + 0.001;
    for (const e of this.entities) {
      if (!e.npc.conversation) continue;
      const d = Math.hypot(e.cell.c - c, e.cell.r - r);
      if (d <= bestD) { bestD = d; best = e; }
    }
    return best;
  }

  // Start an NPC's conversation, showing its bestiary portrait + a readable name.
  talkTo(entity) {
    if (!entity || !entity.npc.conversation) return;
    const label = (entity.npc.name || 'NPC').replace(/_/g, ' ')
      .replace(/\b\w/g, ch => ch.toUpperCase());
    this.path = null;
    this.dialogue.start(entity.npc.conversation, '1',
      { label, portrait: npcPortrait(entity.rec) });
  }

  // Rectangular map triggers (StartConversation etc.) fire once when the hero enters
  // and their conditions pass. Interior triggers only for now (map.triggers).
  checkTriggers() {
    const trigs = (this.mode === 'interior' && this._map && this._map.triggers) || [];
    for (let i = 0; i < trigs.length; i++) {
      const t = trigs[i];
      const key = `${this._mapName}#${i}`;
      if (this._firedTriggers.has(key)) continue;
      const { c, r } = this.heroCell;
      if (c < t.c || c >= t.c + t.w || r < t.r || r >= t.r + t.h) continue;
      if (t.conditions && !this.dialogue.passes(t.conditions)) continue;
      this._firedTriggers.add(key);
      if (t.actions) this.dialogue.runActions(t.actions);
    }
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
        if (this._pendingTalk) {                     // walked up to an NPC -> talk
          const e = this._pendingTalk; this._pendingTalk = null;
          if (Math.hypot(e.cell.c - this.heroCell.c, e.cell.r - this.heroCell.r) <= 1.6) this.talkTo(e);
        }
        if (this._pendingContainer) {                // walked up to a crate/chest -> open
          const k = this._pendingContainer; this._pendingContainer = null;
          if (!k.opened && Math.hypot(k.cell.c - this.heroCell.c, k.cell.r - this.heroCell.r) <= 1.6) this.openContainer(k);
        }
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
    try { this.bestiary = await (await fetch('assets/data/bestiary.json')).json(); } catch {}
    try { this.weapons = await (await fetch('assets/data/weapons.json')).json(); } catch {}
    try { this.loot = await (await fetch('assets/data/loot.json')).json(); } catch {}
    try { this.quests = await (await fetch('assets/data/quests.json')).json(); } catch {}
    try { this.trainers = await (await fetch('assets/data/trainers.json')).json(); } catch {}
    try { this.items = await (await fetch('assets/data/items.json')).json(); } catch {}
    try { this.factions = await (await fetch('assets/data/factions.json')).json(); } catch {}
    try { this.skillCat = await (await fetch('assets/data/skills.json')).json(); } catch {}
    this.gameHud.setQuests(this.quests, () => this.gameState.vars);
    this.gameHud.trainers = this.trainers;
    this.gameHud.items = this.items;
    this.gameHud.weapons = this.weapons;
    this.gameHud.factions = this.factions;
    this.gameHud.skillCat = this.skillCat;
    try { this._spriteSet = new Set(await (await fetch('assets/sprites/index.json')).json()); } catch { this._spriteSet = new Set(); }
    try { this._creation = await (await fetch('assets/data/creation.json')).json(); } catch {}
    await this.loadIconSets();
    if (this._quickStarted) return;                  // a test already started the game
    this.setChromeHidden(true);                       // hide the tap toggle behind the title UI
    const pc = await startFlow(document.getElementById('overlay-root'));
    this.setChromeHidden(false);
    await this.startNewGame(pc);
  }

  // Load the sets of item/object icon names that were extracted from the game atlases
  // (tools/gen-icons.mjs → assets/ui/items|objects). Absent until the atlas is extracted,
  // in which case the sets stay empty and the UI falls back to text / the loot marker.
  async loadIconSets() {
    if (this._itemIcons) return;                     // once
    try { this._itemIcons = new Set(Object.keys(await (await fetch('assets/ui/items/manifest.json')).json())); }
    catch { this._itemIcons = new Set(); }
    try { this._objIcons = new Set(Object.keys(await (await fetch('assets/ui/objects/manifest.json')).json())); }
    catch { this._objIcons = new Set(); }
    this.gameHud.itemIcons = this._itemIcons;
    // Preload the loot-bag marker so enemy drops (dropLootBag) can place instantly,
    // and the real EK projectile atlas (assets/sprites/projectiles, extracted from the
    // APK) so spell casts show the genuine fire/ice/lightning/magic art (Combat._castFx).
    let needLoad = false;
    if (!this.textures.exists('container_icon')) {
      this.load.image('container_icon', 'assets/sprites/loot.png'); needLoad = true;
    }
    if (!this.textures.exists('projectiles')) {
      this.load.atlas('projectiles', 'assets/sprites/projectiles/projectiles.png',
        'assets/sprites/projectiles/projectiles.json'); needLoad = true;
    }
    // Warm the particle textures the spell effects use (assets/particle/*.p, ported by
    // particles.js) so the first cast doesn't stutter. Keys match its `ptex_<file>` scheme.
    for (const [k, f] of [['ptex_default_png', 'default.png'], ['ptex_onepixelwhite_png', 'onepixelwhite.png']]) {
      if (!this.textures.exists(k)) { this.load.image(k, `assets/particle/${f}`); needLoad = true; }
    }
    if (needLoad) {
      try { await new Promise((res) => { this.load.once('complete', res); this.load.start(); }); }
      catch { /* fall back to drawn markers / placeholder FX */ }
    }
  }

  // Apply a created character and begin play. `pc` is the PlayerCreation
  // (name/gender/charClass/portrait/difficulty). Persisted so the run survives reload.
  // By default a new game opens with the "wake up robbed" intro in Lannegar Valley
  // (the playable tutorial is skipped); `opts.intro` false (tests/dev) skips the beat.
  async startNewGame(pc, startMap = INTRO_MAP, opts = {}) {
    const intro = opts.intro !== false && startMap === INTRO_MAP;
    this.player = pc;
    this.playerModel = new PlayerModel(pc, this._creation || {});
    this.playerModel.setItemDb(this.items || {});
    this.combat.reset();
    this.gameHud.onUseItem = (id) => this.useItem(id);
    this.gameHud.setCombat(this.combat, (id) => this.castSkill(id));
    this.gameHud.items = this.items;
    this.gameHud.settings = this.settings;
    // ATTACK button (GameHUD.f2942d): press → one action now, then held-repeat via update().
    this.gameHud.onAttackDown = () => this.heroAction();
    this.gameHud.onRecover = () => this.heroRecover();
    this.heroKey = `hero_${pc.gender.toLowerCase()}`;
    this.charSpriteFile = pc.gender === 'FEMALE'
      ? 'assets/sprites/female_knight.png' : 'assets/sprites/male_knight.png';
    this.gameHud.charSpriteSrc = this.charSpriteFile;   // paper-doll centre in CharacterWindow
    const entry = startMap === TUTORIAL_MAP ? '0001' : (intro ? INTRO_ENTRY : null);
    await this.goArea(startMap, entry);
    this.setChromeHidden(false);                      // game is playing -> show tap toggle
    this.gameHud.setModel(this.playerModel);
    this.gameHud.show();
    if (intro) this.playIntro(pc);                    // wake-up-robbed opening
    try { await this.saveAuto(startMap); } catch {}
  }

  // The "wake up robbed" opening that replaces the playable tutorial. Sets the EXACT
  // state the old tutorial produced (deobf/TUTORIAL_SKIP_SPEC.md) — in code, so it holds
  // even if the player dismisses the monologue — then plays the narration. The traced
  // set is the whole tutorial payload: want_letter_back=10, PlayerRobbed (gold 18),
  // Adaon not in party.
  playIntro(pc) {
    this.gameState.vars.want_letter_back = 10;        // quest "A mysterious letter" @ robbed
    this.gameState.followers.delete('adaon_tutorial'); // he robbed you and left
    this.dialogue._robPlayer();                        // PlayerRobbed# → gold 18
    if (this.gameHud) this.gameHud.update(true);
    const label = (pc.name || 'You');
    const portrait = this.playerModel && this.playerModel.portrait != null
      ? `assets/portraits/${pc.gender === 'FEMALE' ? 'female' : 'male'}/${this.playerModel.portrait}.png` : null;
    // Start on the next tick so the scene/HUD have settled before the box opens.
    setTimeout(() => this.dialogue.start('intro_wakeup', '1', { label, portrait }), 350);
  }

  // Cast a learned skill/spell (deobf/SKILLS_EXEC_SPEC.md).
  castSkill(id) {
    const r = this.combat ? this.combat.castSkill(id) : { ok: false };
    if (this.gameHud) this.gameHud.update(true);
    return r;
  }

  // Use a consumable from the backpack: run its OnUse effect (GainHP/GainMana/…), then
  // remove one from the backpack (deobf/INVENTORY_SPEC.md).
  useItem(id) {
    const m = this.playerModel, it = m && m.itemOf(id);
    if (!it || !it.onUse) return false;
    for (const part of it.onUse.split(';').filter(Boolean)) {
      const [verb, rest] = part.split('#');
      const n = +(rest || '').split(',')[0] || 0;
      const v = (verb || '').trim().toLowerCase();
      if (v === 'gainhp') m.heal(n);
      else if (v === 'gainmana') m.mana = Math.min(m.maxMana(), m.mana + n);
      else this.dialogue.runActions(part);        // fall back to the shared action runner
    }
    m.removeItem(id);
    if (this.gameHud) this.gameHud.update(true);
    return true;
  }

  // The attack button's action (GameScreen.l()). When attackInteracts is on and nothing
  // is in strike range, interact with an adjacent NPC/door instead; otherwise swing.
  heroAction() {
    if (this._dialogue || this._loading || !this.combat || !this.heroCell) return;
    if (this.settings.attackInteracts && !this.combat.enemyInReach()) {
      if (this.interactNearest()) return;
    }
    this.combat.heroSwing();
  }

  // Interact with whatever is adjacent (mirrors GameHUD.E(0) dispatch, condensed to the
  // web build's world objects): an adjacent conversation NPC → talk; else an adjacent
  // area transition (door/arch) → travel. Returns true if something was interacted with.
  interactNearest() {
    const hc = this.heroCell;
    const e = this.entityNear(hc.c, hc.r, 1.6);
    if (e) { this.combat.clearTarget(); this.talkTo(e); return true; }
    const k = this.containerNear(hc.c, hc.r, 1.6);
    if (k) { this.combat.clearTarget(); this.openContainer(k); return true; }
    const t = this.currentTransitions().find(t =>
      t.area && Math.abs(t.c - hc.c) <= 1 && Math.abs(t.r - hc.r) <= 1);
    if (t) { this.path = null; this.goArea(t.area, t.entry); return true; }
    return false;
  }

  // Rest & recover (GameHUD.G() / RecoverButton): only out of combat — heal HP + mana to
  // full. If enemies are near, refuse (ENEMIES_AROUND).
  heroRecover() {
    if (this._dialogue || this._loading || !this.playerModel) return;
    if (this.enemyNear(this.heroCell.c, this.heroCell.r, 8)) {
      this.combat._floater(this.hero, 'Enemies around', false, false, '#ff9a9a');
      return;
    }
    const m = this.playerModel;
    m.hp = m.maxHP();
    if (m.isCaster()) m.mana = m.maxMana();
    if (this.gameHud) this.gameHud.update(true);
    this.combat._floater(this.hero, 'Rested', false, false, '#7fd07f');
  }

  // Serialize the shared world state (all game variables incl. quest progress, and the
  // party/followers) so quests and flags survive a reload.
  worldStateJSON() {
    return { vars: { ...this.gameState.vars }, followers: [...this.gameState.followers] };
  }
  restoreWorldState(w) {
    if (!w) return;
    this.gameState.vars = w.vars || {};
    this.gameState.followers = new Set(w.followers || []);
  }
  async saveAuto(area) {
    await Saves.put('auto',
      { player: this.playerModel.toJSON(), area: area || this._mapName, world: this.worldStateJSON() },
      { name: this.playerModel.name, charClass: this.playerModel.charClass });
  }

  // Dispatch to the right loader: outdoor [worldmap] tiles stream seamlessly (world
  // mode); everything else (towns, buildings, caves) is a discrete interior reached
  // through an arch/door.
  goArea(name, entryId, preferDefault = false) {
    if (this.overworld && this.overworld.has(name)) return this.enterWorld(name, entryId, preferDefault);
    return this.enterInterior(name, entryId, preferDefault);
  }

  // Fade the screen to/from black. `down` true = curtain in (screen goes black);
  // false = curtain out. Returns a promise that resolves when the CSS transition
  // finishes, so callers can swap the area while the screen is dark (the tutorial's
  // Sleep#/StopRender# → wake-up beat). A full-viewport fixed overlay is used so it
  // covers the canvas regardless of engine/DOM rotation.
  fadeBlack(down, ms = 650) {
    if (!this._fadeEl) {
      const el = document.createElement('div');
      el.id = 'ek-fade';
      el.style.cssText = 'position:fixed;inset:0;background:#000;opacity:0;z-index:9999;' +
                         'pointer-events:none;transition:opacity ' + ms + 'ms ease';
      document.body.appendChild(el);
      this._fadeEl = el;
    }
    const el = this._fadeEl;
    el.style.transition = 'opacity ' + ms + 'ms ease';
    return new Promise(resolve => {
      // force reflow so the transition always runs from the current opacity
      void el.offsetWidth;
      el.style.pointerEvents = down ? 'auto' : 'none';
      el.style.opacity = down ? '1' : '0';
      let done = false;
      const finish = () => { if (done) return; done = true; el.removeEventListener('transitionend', finish); resolve(); };
      el.addEventListener('transitionend', finish);
      setTimeout(finish, ms + 120);                  // guarantee resolution
    });
  }

  // Tear down whatever is currently rendered, keeping the persistent hero. Pooled
  // overworld sprites are returned to the pool (not destroyed) so they can be reused;
  // everything else (interior tiles) is destroyed.
  clearScene() {
    if (this.overworld) this.overworld.clear();
    this.clearEntities();
    this._firedTriggers = new Set();
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
  async enterWorld(name, entryId, preferDefault = false) {
    try {
      this._loading = true;
      this.mode = 'world';
      this._streaming = false;
      this.path = null; this.pathIdx = 0;
      this.clearScene();
      this.renderFx.dispose();                         // no fog/roof-fade in the open world

      const g = this.overworld.grid[name];
      this._curChunk = { col: g.col, row: g.row };
      await this.overworld.ensureChunk(name);         // bootstrap the start chunk

      const ch = this.overworld.loaded.get(name);
      this._map = ch.map; this._mapName = name;
      const e = entryOf(ch.map, entryId, preferDefault);
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
      this.syncWorldEntities();                       // NPCs for the loaded chunks
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
      .then(({ changed }) => { this._streaming = false; if (changed) { this.refreshVisible(); this.syncWorldEntities(); } })
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

    // Cull NPC/container sprites to the same viewport window as the tiles above.
    // Unlike tiles (pooled to the viewport already), every NPC in every resident chunk
    // used to stay a fully-animated, always-rendered Phaser sprite for as long as its
    // chunk was streamed in -- fine out in the wilds, but Lannegar (G10) alone spawns
    // 45 of them, and up to 4 chunks can be resident at once near a corner. That's 100+
    // live sprites drawing + animating every frame regardless of whether the camera can
    // even see them. Hide (and pause the animation of) whatever's outside the window;
    // this is the actual fix for "FPS drops so much I can't move" near town.
    // Hiding a sprite stops it drawing, but a culled NPC still stays a child of the
    // depth-sorted `mid` plane -- so sortMid() (run every frame while walking) pays for
    // every NPC in every resident chunk, not just the handful on screen. Near a town
    // corner that's 200+ sprites sorted 60x/sec: the real "drags to a halt". Pull culled
    // sprites OUT of the plane on the visibility transition and re-add them when they
    // return, so the per-frame sort cost is bounded to the viewport regardless of town
    // size. (mid tiles are already pooled to the viewport, so mid stays viewport-sized.)
    let visEntities = 0;
    for (const e of this.entities) {
      const s = e.sprite;
      if (!s || !s.scene) continue;
      const vis = s.x >= minX && s.x <= maxX && s.y >= minY && s.y <= maxY;
      if (vis !== s.visible) {
        s.setVisible(vis);
        if (s.anims) { if (vis) s.anims.resume(); else s.anims.pause(); }
        if (vis) this.planes.mid.add(s); else this.planes.mid.remove(s);
      }
      if (vis) visEntities++;
    }
    this._entityStats = { visible: visEntities, total: this.entities.length };
    for (const k of this.containers) {
      const s = k.sprite;
      if (!s || !s.scene) continue;
      const vis = s.x >= minX && s.x <= maxX && s.y >= minY && s.y <= maxY;
      if (vis !== s.visible) {
        s.setVisible(vis);
        if (vis) this.planes.mid.add(s); else this.planes.mid.remove(s);
      }
    }

    sortMid(this.planes.mid);
    this._lastRefreshXY = { x: cx, y: cy };
  }

  // Load + render a discrete INTERIOR (town/building/cave), placing the hero at entry
  // `entryId` (or the map centre). The hero persists across maps; only tiles clear.
  async enterInterior(name, entryId, preferDefault = false) {
    try {
      this._loading = true;
      this.mode = 'interior';
      this._curChunk = null;
      this.path = null; this.pathIdx = 0;
      this.clearScene();

      const map = await loadMap(this, name);
      this._map = map; this._mapName = name;
      const ambient = ambientColor(map, this.currentHour());
      const { tiles, width, height, images } = renderMap(this, this.planes, map, ambient);
      this._mapTiles = tiles; this._ambient = ambient;
      this.mapBounds = { width, height };
      this.grid.setVisible(false);
      this.walk = buildWalkable(map);
      this.renderFx.onEnter(map, images);            // roof-fade / fog / lights (interiors)

      // Where to drop the hero: the named entry, else map centre — snapped walkable.
      const e = entryOf(map, entryId, preferDefault);
      this.heroCell = this.nearestWalkable(e.c, e.r);
      this._transLock = true;                        // don't bounce off the return portal
      const hp = cellToPx(this.heroCell.c, this.heroCell.r, map);

      await this.placeHeroAt(hp.x, hp.y);
      sortMid(this.planes.mid);
      applyAmbient(this.planes, ambient);
      this.renderFx.update(0);                        // paint initial fog/roof-fade now
      this.fitMap();
      this._loading = false;
      this.spawnEntities(map.npcs, 'interior', (c, r) => cellToPx(c, r, map))
        .then(() => { this._entityStats = { visible: this.entities.length, total: this.entities.length }; });
      this.spawnContainers(map.containers, 'interior', (c, r) => cellToPx(c, r, map));
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

  // Orientation control.
  //   'auto'        — follow the device (no forced rotation; the browser reflows
  //                   the viewport and Phaser RESIZE refills upright).
  //   0/90/180/270  — LOCK. We turn the WHOLE PHONE to that orientation via the
  //                   Screen Orientation API, so the browser gives a real viewport
  //                   of that shape and EVERYTHING (game, HUD, Settings, creation)
  //                   renders upright and fills the screen with correct input.
  //                   Where that API isn't available/permitted we fall back to
  //                   rotating the game in-engine + the DOM overlay via CSS.
  setOrient(mode) {
    this.orientChoice = mode;                        // what the player picked
    if (mode === 'auto') {
      this.autoOrient = true;
      this._applyEngineRotation(0);                  // upright; device decides shape
      this._unlockOrientation();
    } else {
      this.autoOrient = false;
      // Optimistic engine/CSS fallback so something happens instantly even if the
      // real device lock is unsupported. If the lock succeeds we undo it (the phone
      // is now physically that orientation, so no in-engine rotation is needed).
      this._applyEngineRotation(+mode);
      this._lockOrientation(mode, () => this._applyEngineRotation(0));
    }
    try { localStorage.setItem('ek_orient', mode); } catch (e) { /* private mode */ }
    document.querySelectorAll('#orient-bar button').forEach(b =>
      b.setAttribute('aria-pressed', String(b.dataset.orient === mode)));
  }

  // In-engine rotation of the game world + the DOM overlay layer (#overlay-root).
  // deg 0 = upright. Used for 'auto' and as the fallback when the device can't be
  // physically rotated.
  _applyEngineRotation(deg) {
    this.orient = deg;
    document.body.classList.remove('ek-rot-90', 'ek-rot-180', 'ek-rot-270');
    if (deg) document.body.classList.add('ek-rot-' + deg);
    this.relayout();
  }

  // Turn the physical device to `deg`. On success, onLocked() runs (we drop the
  // engine rotation). Android Chrome tabs need fullscreen for the lock; an installed
  // PWA (standalone) doesn't — so request fullscreen only when not standalone.
  _lockOrientation(deg, onLocked) {
    const type = { '0': 'portrait-primary', '90': 'landscape-primary',
                   '180': 'portrait-secondary', '270': 'landscape-secondary' }[String(deg)];
    const so = (typeof screen !== 'undefined') && screen.orientation;
    if (!so || !so.lock || !type) return;            // unsupported -> keep engine fallback
    const lock = () => so.lock(type).then(() => { this._deviceLocked = true; onLocked(); })
                                    .catch(() => { /* not permitted -> engine fallback stays */ });
    const standalone = (window.matchMedia && window.matchMedia('(display-mode: standalone)').matches)
                       || window.navigator.standalone;
    const el = document.documentElement;
    if (standalone || document.fullscreenElement || !el.requestFullscreen) {
      lock();
    } else {
      el.requestFullscreen().then(lock).catch(lock);
    }
  }

  _unlockOrientation() {
    const so = (typeof screen !== 'undefined') && screen.orientation;
    if (so && so.unlock) { try { so.unlock(); } catch (e) { /* ignore */ } }
    this._deviceLocked = false;
  }

  relayout() {
    const SW = this.scale.width, SH = this.scale.height;
    const rot = (this.orient % 180) !== 0;      // 90/270 swap logical dimensions
    this.LW = rot ? SH : SW;
    this.LH = rot ? SW : SH;

    // Expose the current LOGICAL orientation as a body class so DOM overlays (e.g. the
    // CharacterWindow grid) can lay out per portrait/landscape. This is correct in both
    // orientation modes — device-rotation (real viewport) and in-engine rotation (LW/LH
    // derived from `orient`) — unlike a raw CSS viewport media query.
    const landscape = this.LW >= this.LH;
    document.body.classList.toggle('ek-landscape', landscape);
    document.body.classList.toggle('ek-portrait', !landscape);

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

    const label = this.autoOrient
      ? (this.LW >= this.LH ? 'Auto · landscape' : 'Auto · portrait')
      : { 0: 'Portrait', 90: 'Landscape',
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
  if (b) window.__EK && window.__EK.setOrient(b.dataset.orient);   // 'auto' | '0' | '90' | '180' | '270'
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
  // If a NEW service worker takes control (an update landed), reload once so the
  // freshest app code runs immediately — no more "stuck on an old copy". Only when
  // there was already a controller (a real update, not the first-ever install).
  if (navigator.serviceWorker.controller) {
    let reloaded = false;
    navigator.serviceWorker.addEventListener('controllerchange', () => {
      if (reloaded) return; reloaded = true; location.reload();
    });
  }
  navigator.serviceWorker.register('./sw.js').then(async (reg) => {
    if (reg && reg.update) { try { reg.update(); } catch (e) { /* ignore */ } }  // check for a new SW each load
    await navigator.serviceWorker.ready;
    // ask the browser to keep our storage (avoid eviction of the big cache + saves)
    if (navigator.storage && navigator.storage.persist) navigator.storage.persist();
    const post = () => navigator.serviceWorker.controller
      && navigator.serviceWorker.controller.postMessage({ type: 'CACHE_ALL' });
    navigator.serviceWorker.controller ? post()
      : navigator.serviceWorker.addEventListener('controllerchange', post, { once: true });
  }).catch(() => {});
}
