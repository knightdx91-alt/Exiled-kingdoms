// PlayerModel — the character's live stats, derived from the created PlayerCreation
// and the recovered formulas (deobf/CHARACTER_STATS_SPEC.md, HUD_SPEC.md; data in
// assets/data/creation.json). Health/mana/level/gold are what the HUD reads.

import { skillParams } from './skills.js';

// Normalise a skill reference (object from creation, or a bare id/name) to its canonical
// id. The skill system keys everything by the lowercase-underscore id (== SKILL_FX keys
// == the skill's `icon`), so a display name like "Fireball" or "Ice Storm" MUST be
// normalised the same way — otherwise the created starting ability is stored under
// "Fireball" and never matches skillRank('fireball'), so it never shows on the skill bar.
const normId = (str) => str.toLowerCase().replace(/[^a-z0-9]+/g, '_').replace(/^_|_$/g, '');
export function skillIdOf(s) {
  if (!s) return null;
  if (typeof s === 'string') return normId(s);
  return s.icon || s.id || (s.name ? normId(s.name) : null);
}

export class PlayerModel {
  constructor(pc, C) {
    this.C = C;                                   // creation.json constants
    this.name = pc.name;
    this.gender = pc.gender;
    this.charClass = pc.charClass;                // WARRIOR/ROGUE/CLERIC/WIZARD
    this.portrait = pc.portrait;
    this.difficulty = pc.difficulty;
    this.attributes = Object.assign({ STR: 0, END: 0, AGI: 0, INT: 0, AWA: 0, PER: 0 },
                                    pc.attributes || {});
    this.skills = pc.startingSkill ? [pc.startingSkill] : (pc.skills || []);
    this.xp = pc.xp || 0;
    this.gold = pc.gold != null ? pc.gold : (C.startGold || 0);
    this.hp = pc.hp != null ? pc.hp : this.maxHP();
    this.mana = pc.mana != null ? pc.mana : this.maxMana();
    // Trainer-taught advanced skills (deobf/TRAINERS_SPEC.md) + spendable skill points.
    this.trained = new Set(pc.trained || []);
    this.skillPoints = pc.skillPoints || 0;
    // Unlocked equipment disciplines: base classes have their own; HERO earns them by
    // training that discipline's skills. Re-derived on load from `trained`.
    this.disciplines = new Set(pc.disciplines || []);
    // Inventory (deobf/INVENTORY_SPEC.md): 12 equipment slots (id or 0), a backpack of
    // item ids, and quick slots. `itemDb` (items.json) is set by the scene after load.
    this.equipment = Object.assign(
      { mainhand: 0, offhand: 0, head: 0, body: 0, hands: 0, legs: 0, feet: 0,
        ring: 0, ring2: 0, belt: 0, cloak: 0, necklace: 0 }, pc.equipment || {});
    this.backpack = (pc.backpack || []).slice();
    this.quickslots = (pc.quickslots || [0, 0, 0, 0]).slice();
    this.itemDb = null;
    // Per-skill trained rank (deobf/SKILLS_EXEC_SPEC.md). Seed the created starting
    // ability at rank 1; advanced (trainer) skills are known via `trained`.
    this.skillRanks = Object.assign({}, pc.skillRanks || {});
    for (const s of this.skills) { const id = skillIdOf(s); if (id && !this.skillRanks[id]) this.skillRanks[id] = 1; }
  }

  // --- Skills (ranks + passive folding) --------------------------------------------
  skillRank(id) { return (this.skillRanks && this.skillRanks[id]) || (this.trained && this.trained.has(id) ? 1 : 0); }
  knowsSkill(id) { return this.skillRank(id) > 0; }
  setSkillRank(id, r) { this.skillRanks[id] = Math.max(0, r | 0); return this; }

  // --- Inventory (deobf/INVENTORY_SPEC.md) -----------------------------------------
  setItemDb(db) { this.itemDb = db || {}; return this; }
  itemOf(id) { return (id && this.itemDb) ? this.itemDb[id] : null; }
  addItem(id) { if (id) this.backpack.push(+id); return this; }
  removeItem(id) { const i = this.backpack.indexOf(+id); if (i >= 0) this.backpack.splice(i, 1); return i >= 0; }

  // Equip a backpack item into its slot (rings fill ring then ring2). Returns worn item
  // to the backpack. Class-gated. Returns true on success.
  equip(id) {
    const it = this.itemOf(id);
    if (!it || !it.slot) return false;
    if (!this.canUseItemClass(it.classes)) return false;
    const bi = this.backpack.indexOf(+id);
    if (bi < 0) return false;
    let slot = it.slot;
    if (slot === 'ring') slot = this.equipment.ring ? (this.equipment.ring2 ? 'ring' : 'ring2') : 'ring';
    this.backpack.splice(bi, 1);
    const prev = this.equipment[slot];
    this.equipment[slot] = +id;
    if (prev) this.backpack.push(prev);
    return true;
  }
  unequip(slot) {
    const id = this.equipment[slot];
    if (!id) return false;
    this.equipment[slot] = 0;
    this.backpack.push(id);
    return true;
  }
  worn() {
    if (!this.equipment || !this.itemDb) return [];   // pre-init / no catalog yet
    return Object.entries(this.equipment)
      .filter(([, id]) => id).map(([slot, id]) => ({ slot, id, item: this.itemOf(id) }))
      .filter(w => w.item);
  }
  hasShield() { const o = this.itemOf(this.equipment.offhand); return !!(o && o.type === 'SHIELD'); }
  wornArmor() { return this.worn().reduce((a, w) => a + (w.item.armor || 0), 0); }
  wornHp() { return this.worn().reduce((a, w) => a + (w.item.hp || 0), 0); }
  wornMana() { return this.worn().reduce((a, w) => a + (w.item.mana || 0), 0); }
  wornResist() {
    const r = { Fire: 0, Cold: 0, Shock: 0, Death: 0, Toxic: 0, Spirit: 0 };
    for (const w of this.worn()) for (const k in (w.item.resist || {})) r[k] = (r[k] || 0) + w.item.resist[k];
    return r;
  }

  cls() { return this.C.classes[this.charClass] || this.C.classes.WARRIOR; }
  learnsAll() { return !!this.cls().learnsAll; }

  // The single equipment-class letter this character's base class owns (items.txt
  // Classes col: W/R/C/M). HERO owns none by default — it earns letters by training.
  baseDiscipline() {
    return { WARRIOR: 'W', ROGUE: 'R', CLERIC: 'C', WIZARD: 'M' }[this.charClass] || null;
  }
  // All equipment-class letters this character can currently use.
  usableClasses() {
    const s = new Set(this.disciplines);
    const b = this.baseDiscipline();
    if (b) s.add(b);
    return s;
  }
  // Can this character equip an item whose items.txt Classes column is `col` (csv of
  // W/R/C/M, blank = usable by anyone)? Trained-discipline gating (owner's design).
  canUseItemClass(col) {
    const c = (col || '').trim();
    if (!c) return true;
    const mine = this.usableClasses();
    return c.split(',').map(x => x.trim()).some(x => mine.has(x));
  }

  // Learn an advanced skill from the trainers catalog. Non-HERO classes may only train
  // skills that include their own discipline; HERO can train any and unlocks that
  // skill's disciplines (→ their class-restricted equipment). Returns true if learned.
  learnSkill(id, catalog) {
    if (!id || this.trained.has(id)) return false;
    const rec = catalog && catalog[id];
    const disc = rec ? rec.disciplines : [];
    if (!this.learnsAll() && rec && disc.length) {
      const mineC = { WARRIOR: 'WARRIOR', ROGUE: 'ROGUE', CLERIC: 'CLERIC', WIZARD: 'WIZARD' }[this.charClass];
      if (mineC && !disc.includes(mineC)) return false;   // out of this class's discipline
    }
    this.trained.add(id);
    const LETTER = { WARRIOR: 'W', ROGUE: 'R', CLERIC: 'C', WIZARD: 'M' };
    for (const d of disc) if (LETTER[d]) this.disciplines.add(LETTER[d]);
    return true;
  }

  // level from cumulative XP table (Rules.f3252f)
  level() {
    const t = this.C.xpTable || [0, 0];
    let lv = 1;
    for (let i = 2; i < t.length; i++) if (this.xp >= t[i]) lv = i; else break;
    return Math.min(lv, this.C.maxLevel || t.length - 1);
  }

  maxHP() { const c = this.cls(); return c.hp + c.hpPerLevel * this.level() + this.wornHp(); }
  maxMana() {
    const c = this.cls(); let b = c.mana ? c.mana + c.manaPerLevel * this.level() : 0;
    if (!b) return 0;
    b += this.wornMana();
    const ms = this.skillRank('mana_surge');           // Mana Surge passive: +pool
    if (ms) { const p = skillParams('mana_surge', ms); if (p) b += p.pool; }
    return b;
  }
  isCaster() { return this.cls().mana > 0; }

  // --- Combat-derived stats (deobf/COMBAT_SPEC.md, CHARACTER_STATS_SPEC.md) -------
  // Melee damage bonus m(): dmgMul applied to level + STR. APPROX — the exact e()
  // effective-level curve + SheetBonus equipment terms aren't modelled (no items yet).
  dmgBonus() {
    const mul = this.cls().dmgMul != null ? this.cls().dmgMul : 0.5;
    return Math.max(0, Math.round(mul * (this.level() + (this.attributes.STR || 0))));
  }
  // Armor b(): max(STR − k, 0), k = 1/2/1/3 for Warrior/Rogue/Cleric/Wizard, plus worn.
  armor() {
    const k = { WARRIOR: 1, ROGUE: 2, CLERIC: 1, WIZARD: 3 }[this.charClass] ?? 2;
    return Math.max(0, (this.attributes.STR || 0) - k) + this.wornArmor();
  }
  // Resistances come from worn gear (base 0).
  resist() { return this.wornResist(); }
  // Equipped mainhand weapon id (into weapons.json), else the class default starter.
  weaponId() {
    const mh = this.itemOf(this.equipment && this.equipment.mainhand);
    if (mh && mh.weapon) return mh.weapon;
    return { WARRIOR: 'iron_longsword', ROGUE: 'iron_dagger',
             CLERIC: 'iron_mace', WIZARD: 'wand_1', HERO: 'iron_longsword' }[this.charClass] || 'iron_dagger';
  }

  // XP progress within the current level, 0..1 (1.0 at max level)
  xpProgress() {
    const t = this.C.xpTable, lv = this.level();
    if (lv >= (this.C.maxLevel || t.length - 1)) return 1;
    const cur = t[lv], next = t[lv + 1];
    return next > cur ? Math.max(0, Math.min(1, (this.xp - cur) / (next - cur))) : 0;
  }
  xpToNext() {
    const t = this.C.xpTable, lv = this.level();
    if (lv >= (this.C.maxLevel || t.length - 1)) return 0;
    return t[lv + 1] - this.xp;
  }

  // mutations (clamped)
  gainXP(n) { this.xp = Math.max(0, this.xp + n); this.hp = Math.min(this.hp, this.maxHP()); return this; }
  heal(n) { this.hp = Math.max(0, Math.min(this.maxHP(), this.hp + n)); return this; }
  damage(n) { this.hp = Math.max(0, this.hp - n); return this; }
  spendMana(n) { if (this.mana < n) return false; this.mana -= n; return true; }
  addGold(n) { this.gold = Math.max(0, this.gold + n); return this; }
  spendGold(n) { if (this.gold < n) return false; this.gold -= n; return true; }

  toJSON() {
    return { name: this.name, gender: this.gender, charClass: this.charClass,
             portrait: this.portrait, difficulty: this.difficulty,
             attributes: this.attributes, skills: this.skills,
             xp: this.xp, gold: this.gold, hp: this.hp, mana: this.mana,
             trained: [...this.trained], skillPoints: this.skillPoints,
             disciplines: [...this.disciplines],
             equipment: this.equipment, backpack: this.backpack, quickslots: this.quickslots,
             skillRanks: this.skillRanks };
  }
  static fromJSON(o, C) { return new PlayerModel(o, C); }
}
