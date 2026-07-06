// PlayerModel — the character's live stats, derived from the created PlayerCreation
// and the recovered formulas (deobf/CHARACTER_STATS_SPEC.md, HUD_SPEC.md; data in
// assets/data/creation.json). Health/mana/level/gold are what the HUD reads.

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

  maxHP() { const c = this.cls(); return c.hp + c.hpPerLevel * this.level(); }
  maxMana() { const c = this.cls(); return c.mana ? c.mana + c.manaPerLevel * this.level() : 0; }
  isCaster() { return this.cls().mana > 0; }

  // --- Combat-derived stats (deobf/COMBAT_SPEC.md, CHARACTER_STATS_SPEC.md) -------
  // Melee damage bonus m(): dmgMul applied to level + STR. APPROX — the exact e()
  // effective-level curve + SheetBonus equipment terms aren't modelled (no items yet).
  dmgBonus() {
    const mul = this.cls().dmgMul != null ? this.cls().dmgMul : 0.5;
    return Math.max(0, Math.round(mul * (this.level() + (this.attributes.STR || 0))));
  }
  // Armor b(): max(STR − k, 0), k = 1/2/1/3 for Warrior/Rogue/Cleric/Wizard.
  armor() {
    const k = { WARRIOR: 1, ROGUE: 2, CLERIC: 1, WIZARD: 3 }[this.charClass] ?? 2;
    return Math.max(0, (this.attributes.STR || 0) - k);
  }
  // No equipment/resistance system yet → player base resistances are 0.
  resist() { return { Fire: 0, Cold: 0, Shock: 0, Death: 0, Toxic: 0, Spirit: 0 }; }
  // Default starting weapon id per class (resolved against weapons.json).
  weaponId() {
    return { WARRIOR: 'iron_longsword', ROGUE: 'iron_dagger',
             CLERIC: 'iron_mace', WIZARD: 'wand_1' }[this.charClass] || 'iron_dagger';
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
             disciplines: [...this.disciplines] };
  }
  static fromJSON(o, C) { return new PlayerModel(o, C); }
}
