// libgdx ParticleEffect (.p) -> Phaser 3.80 emitter porter.
//
// EK's spell/impact visuals are libgdx particle-editor exports under
// assets/particle/*.p (extracted from the APK). Phaser can't read that format,
// so this module parses a .p at runtime and builds an equivalent Phaser emitter
// config, then plays it as a one-shot burst for a spell impact. Faithful to the
// data (emission/life/scale/velocity/angle/tint/alpha/blend/spawn-shape); the
// per-life scaling *curves* are approximated by their endpoints (Phaser emitter
// ops interpolate start->end), which is visually indistinguishable for these
// short bursts. See deobf/SKILLS_EXEC_SPEC.md.

const TEX_DIR = 'assets/particle/';
const _cache = new Map();   // effect name -> parsed+built descriptor (or null)

// --- .p parser: split into `- Section -` blocks of `key: value` lines --------
export function parseParticle(text) {
  const out = {};
  let cur = '_head';
  out[cur] = {};
  let lastImg = null;
  for (const raw of text.split(/\r?\n/)) {
    const line = raw.replace(/\s+$/, '');
    if (line === '') continue;
    const h0 = /^-\s*(.+?)\s*-$/.test(line);
    const img = line.match(/([^\\/]+\.png)\s*$/i);     // a bare image path line
    if (img && !h0) { lastImg = img[1]; continue; }    // (Windows paths have a colon; catch first)
    const h = line.match(/^-\s*(.+?)\s*-$/);
    if (h) { cur = h[1]; out[cur] = {}; continue; }
    const kv = line.match(/^([A-Za-z0-9_]+):\s*(.*)$/);
    if (kv) out[cur][kv[1]] = kv[2];
    else out[cur]._val = line;              // emitter name
  }
  out._imgFile = lastImg;
  return out;
}

const n = (s, k, d = 0) => (s && s[k] != null ? parseFloat(s[k]) : d);

// A libgdx ScaledNumericValue: low/high ranges + a scaling curve. We keep the
// range midpoints and the first/last curve points (start/end of life).
function scaled(s) {
  if (!s) return null;
  const low = (n(s, 'lowMin') + n(s, 'lowMax')) / 2;
  const high = (n(s, 'highMin') + n(s, 'highMax')) / 2;
  let c0 = s.scaling0 != null ? parseFloat(s.scaling0) : 1;
  let cN = c0, i = 0;
  while (s['scaling' + i] != null) { cN = parseFloat(s['scaling' + i]); i++; }
  return { low, high, c0, cN,
    lowMin: n(s, 'lowMin'), lowMax: n(s, 'lowMax'),
    highMin: n(s, 'highMin'), highMax: n(s, 'highMax'),
    active: s.active !== 'false' };
}
// value at start / end of a particle's life: low + (high-low)*curvePoint
const vStart = (sc) => sc.low + (sc.high - sc.low) * sc.c0;
const vEnd = (sc) => sc.low + (sc.high - sc.low) * sc.cN;

function tintColors(s) {
  if (!s) return null;
  const cnt = n(s, 'colorsCount', 0);
  const cols = [];
  for (let i = 0; i < cnt; i += 3) {
    const r = Math.round(n(s, 'colors' + i) * 255);
    const g = Math.round(n(s, 'colors' + (i + 1)) * 255);
    const b = Math.round(n(s, 'colors' + (i + 2)) * 255);
    cols.push((r << 16) | (g << 8) | b);
  }
  return cols.length ? cols : null;
}

// Build a Phaser emitter config + burst metadata from a parsed .p.
function buildDescriptor(p) {
  const imgFile = p._imgFile || (p['Image Path'] && p['Image Path']._val) || 'default.png';
  const texKey = 'ptex_' + imgFile.replace(/[^a-z0-9]/gi, '_');
  const additive = p.Options && p.Options.additive === 'true';

  const emission = scaled(p.Emission);
  const life = scaled(p.Life);
  const scale = scaled(p.Scale);
  const vel = scaled(p.Velocity);
  const ang = scaled(p.Angle);
  const alpha = scaled(p.Transparency);
  const grav = scaled(p.Gravity);
  const spawnW = scaled(p['Spawn Width']);
  const spawnH = scaled(p['Spawn Height']);
  const cols = tintColors(p.Tint);

  const lifeMin = Math.max(80, life ? life.highMin || life.high : 400);
  const lifeMax = Math.max(lifeMin, life ? life.highMax || life.high : 700);
  // one-shot burst count: emission rate over a short window, clamped to the cap
  const rate = emission ? vStart(emission) : 60;
  const cap = n(p.Count, 'max', 200) || 200;
  const quantity = Math.max(6, Math.min(cap, Math.round(rate * 0.28)));

  const config = { lifespan: { min: lifeMin, max: lifeMax }, emitting: false };
  if (scale) {
    const sw = 0;   // texture width filled in at play time (needs the loaded image)
    config._scalePx = { start: vStart(scale), end: vEnd(scale), _tw: sw };
  }
  if (vel && vel.active) {
    const sp = vStart(vel);
    config.speed = { min: sp * 0.5, max: Math.max(sp, sp * 0.5 + 1) };
  }
  if (ang && ang.active) {
    // libgdx angle: 0=right, CCW positive. Phaser: 0=right, CW positive -> negate.
    config.angle = { min: -(ang.highMax || 90), max: -(ang.highMin || 90) };
  }
  if (alpha) config.alpha = { start: Math.min(1, vStart(alpha) || 1), end: Math.min(1, vEnd(alpha)) };
  if (grav && grav.active) config.gravityY = -(vStart(grav));   // libgdx +Y is up
  if (cols) { config.color = cols; config.colorEase = 'linear'; }
  if (additive) config.blendMode = 'ADD';
  const ew = spawnW ? (spawnW.highMax || spawnW.high) : 0;
  const eh = spawnH ? (spawnH.highMax || spawnH.high) : 0;

  return { texKey, imgFile, config, quantity, lifeMax, emitZone: { w: ew, h: eh } };
}

// Fetch + parse + build (cached), then ensure the texture is loaded.
async function loadEffect(scene, name) {
  if (_cache.has(name)) return _cache.get(name);
  let desc = null;
  try {
    const text = await (await fetch(`${TEX_DIR}${name}.p`)).text();
    desc = buildDescriptor(parseParticle(text));
  } catch { desc = null; }
  _cache.set(name, desc);
  if (!desc) return null;
  if (!scene.textures.exists(desc.texKey)) {
    try {
      scene.load.image(desc.texKey, `${TEX_DIR}${desc.imgFile}`);
      await new Promise((res) => { scene.load.once('complete', res); scene.load.start(); });
    } catch { /* texture missing -> caller falls back */ }
  }
  return desc;
}

// Play `name` as a one-shot burst at world px (x, y). Self-destroys once the
// last particle has died. No-op (resolves false) if the effect can't be loaded.
export async function playParticleEffect(scene, name, x, y, tint) {
  if (!scene || !scene.add) return false;
  const desc = await loadEffect(scene, name);
  if (!desc || !scene.textures.exists(desc.texKey)) return false;
  const cfg = Object.assign({}, desc.config);
  // Convert libgdx pixel size -> Phaser scale factor using the real texture width.
  if (cfg._scalePx) {
    const tw = scene.textures.get(desc.texKey).getSourceImage().width || 32;
    cfg.scale = { start: cfg._scalePx.start / tw, end: cfg._scalePx.end / tw };
    delete cfg._scalePx;
  }
  if (desc.emitZone.w || desc.emitZone.h) {
    cfg.emitZone = { type: 'random',
      source: new Phaser.Geom.Ellipse(0, 0, desc.emitZone.w || 4, desc.emitZone.h || 4) };
  }
  if (tint != null && !cfg.color) cfg.tint = tint;   // caller override when the .p is white
  const emitter = scene.add.particles(x, y, desc.texKey, cfg);
  if (scene.world) scene.world.add(emitter);
  emitter.explode(desc.quantity);
  scene.time.delayedCall(desc.lifeMax + 120, () => { if (emitter && emitter.scene) emitter.destroy(); });
  return true;
}

// Map an EK damage type / cast kind to its real particle effect file.
export const EFFECT_FOR_TYPE = {
  Fire: 'fire', Cold: 'ice', Shock: 'spell', Toxic: 'toxic',
  Death: 'death', Spirit: 'healing', Physical: 'simple',
};
