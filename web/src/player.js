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
  }

  cls() { return this.C.classes[this.charClass] || this.C.classes.WARRIOR; }

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
             xp: this.xp, gold: this.gold, hp: this.hp, mana: this.mana };
  }
  static fromJSON(o, C) { return new PlayerModel(o, C); }
}
