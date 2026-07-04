package net.fdgames.GameEntities.CharacterSheet;

import java.util.Iterator;
import java.util.Locale;
import n0.k1;
import n0.l1;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.SheetEffect;
import net.fdgames.GameEntities.Final.Loot;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.Follower;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.WeaponStats;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class CharacterSheet {
    final float MITIGATION_CAP;
    public AttributesSet attributes;
    public CharacterEffects effects;
    private int hardcoded_defense;
    private CharacterResistances hardcoded_resistances;
    private WeaponStats hardcoded_weapon;
    public CharacterInventory inventory;
    public SheetEffects sheetEffects;
    public SkillSet skillSet;
    public CharacterStats stats;
    private CharacterTraits traits;

    final class a extends k1 {
    }

    public CharacterSheet() {
        this.hardcoded_weapon = null;
        this.hardcoded_defense = -999;
        this.hardcoded_resistances = null;
        this.attributes = null;
        this.traits = null;
        this.MITIGATION_CAP = 0.15f;
    }

    public static boolean b(int i2) {
        boolean zA = GameData.v().backpack.a(i2, 1);
        if (!zA) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameLevelData.d(new Loot(GameData.v().player.f3092x + 32, GameData.v().player.f3093y + 32, i2));
            GameData.v().log.a("[RED]WARNING:[] inventory full, item was dropped to the ground!");
            if (i2 != 0 && Rules.f(i2).value < 0) {
                new a(GameString.b("WARNING_DROPPED", false), 0).show(z.v().a());
            }
        }
        return zA;
    }

    public static int g0(int i2, int i3) {
        if (i3 == 0) {
            return i2;
        }
        if (i3 > 50) {
            i3 = i3 <= 80 ? ((i3 - 50) / 2) + 50 : i3 <= 110 ? ((i3 - 80) / 3) + 65 : ((i3 - 110) / 4) + 75;
        }
        if (i3 >= 100) {
            return 0;
        }
        return ((100 - i3) * i2) / 100;
    }

    public final int A() {
        int iF;
        int iF2 = 0;
        if (!V()) {
            return 0;
        }
        int iD = SheetBonus.d(J(), 3, this.inventory, this.effects);
        int iD2 = SheetBonus.d(J(), 5, this.inventory, this.effects);
        if (this.stats.c().equals(Rules.CharacterClass.WIZARD)) {
            iF = this.stats.f() * ((iD2 / 2) + iD + 2);
        } else {
            iF = 0;
        }
        if (this.stats.c().equals(Rules.CharacterClass.CLERIC)) {
            iF = this.stats.f() * ((iD / 2) + iD2 + 2);
        }
        int iG = this.skillSet.g("mana_surge");
        if (iG == 1) {
            iF2 = this.stats.f() + 3;
        } else if (iG == 2) {
            iF2 = (this.stats.f() * 2) + 6;
        } else if (iG == 3) {
            iF2 = (this.stats.f() * 3) + 9;
        }
        return iF2 + this.inventory.k() + this.stats.h() + iF;
    }

    public final int B(boolean z2) {
        if (z2) {
            WeaponStats weaponStats = this.hardcoded_weapon;
            if (weaponStats != null) {
                return m() + weaponStats.maxDamage + 1;
            }
            return m() + this.inventory.j().maxDamage + 1;
        }
        WeaponStats weaponStats2 = this.hardcoded_weapon;
        if (weaponStats2 != null) {
            return m() + weaponStats2.minDamage;
        }
        return m() + this.inventory.j().minDamage;
    }

    public final int C() {
        return this.stats.missingMana;
    }

    public final String D() {
        CharacterInventory characterInventory = this.inventory;
        int i2 = characterInventory.slot_offhand;
        String str = (i2 <= 0 || Rules.f(i2).sprite.equals("")) ? "" : Rules.f(characterInventory.slot_offhand).sprite;
        CharacterInventory characterInventory2 = this.inventory;
        return (characterInventory2 == null || characterInventory2.j() == null || this.inventory.j().twohanded || str.equals("")) ? "" : "offhand_".concat(str);
    }

    public final int E() {
        int iG = G(Damage.DamageType.Spirit);
        if (iG == 0) {
            return 0;
        }
        return iG <= 50 ? iG : iG <= 80 ? ((iG - 50) / 2) + 50 : iG <= 110 ? ((iG - 80) / 3) + 65 : ((iG - 110) / 4) + 75;
    }

    public final int F(CharacterResistances.ResistanceType resistanceType) {
        CharacterResistances characterResistances = this.hardcoded_resistances;
        if (characterResistances != null) {
            return this.effects.resistances.a(resistanceType) + characterResistances.a(resistanceType);
        }
        int iA = this.effects.resistances.a(resistanceType) + this.inventory.m(resistanceType);
        int i2 = this.effects.mageArmorElementalBonus;
        if (resistanceType == CharacterResistances.ResistanceType.Death) {
            iA = (this.skillSet.g("death_ward") * 12) + (SheetBonus.d(J(), 5, this.inventory, this.effects) * 5) + iA;
        }
        if (resistanceType == CharacterResistances.ResistanceType.Spirit) {
            iA = (this.skillSet.g("spiritual_ward") * 10) + (SheetBonus.d(J(), 5, this.inventory, this.effects) * 2) + iA;
        }
        if (resistanceType == CharacterResistances.ResistanceType.Toxic) {
            iA = (this.skillSet.g("poison_master") * 10) + (SheetBonus.d(J(), 1, this.inventory, this.effects) * 5) + iA + (this.skillSet.g("toxic_ward") * 12);
        }
        if (resistanceType == CharacterResistances.ResistanceType.Fire) {
            iA = (this.skillSet.g("fire_ward") * 12) + iA + i2;
        }
        if (resistanceType == CharacterResistances.ResistanceType.Cold) {
            iA = (this.skillSet.g("ice_ward") * 12) + iA + i2;
        }
        return resistanceType == CharacterResistances.ResistanceType.Shock ? (this.skillSet.g("shock_ward") * 12) + iA + i2 : iA;
    }

    public final int G(Damage.DamageType damageType) {
        switch (damageType.ordinal()) {
            case 1:
                return F(CharacterResistances.ResistanceType.Fire);
            case 2:
                return F(CharacterResistances.ResistanceType.Cold);
            case 3:
                return F(CharacterResistances.ResistanceType.Shock);
            case 4:
                return F(CharacterResistances.ResistanceType.Death);
            case 5:
                return F(CharacterResistances.ResistanceType.Toxic);
            case 6:
                return F(CharacterResistances.ResistanceType.Spirit);
            default:
                return 0;
        }
    }

    public final int H() {
        WeaponStats weaponStats = this.hardcoded_weapon;
        if (weaponStats != null) {
            return (int) (SheetBonus.b(this, weaponStats.a()) * weaponStats.speed);
        }
        return (int) (SheetBonus.b(this, this.inventory.j().a()) * this.inventory.j().speed);
    }

    public final int I(int i2) {
        if (this.traits == null) {
            this.stats.getClass();
            this.traits = new CharacterTraits(0);
        }
        return SheetBonus.d(this.traits, i2, this.inventory, this.effects);
    }

    public final CharacterTraits J() {
        if (this.traits == null) {
            this.traits = new CharacterTraits();
        }
        return this.traits;
    }

    public final int K(int i2) {
        if (this.traits == null) {
            this.traits = new CharacterTraits();
        }
        return this.traits.d(i2);
    }

    public final int L() {
        int iF;
        int i2;
        if (X()) {
            iF = (this.stats.f() * 2) - (this.skillSet.h() + 1);
            i2 = this.skillSet.bonusPoints;
        } else {
            iF = this.stats.f() - this.skillSet.h();
            i2 = this.skillSet.bonusPoints;
        }
        return iF + i2;
    }

    public final int M() {
        return this.traits.c(this.stats.f(), X());
    }

    public final WeaponStats N() {
        WeaponStats weaponStats = this.hardcoded_weapon;
        return weaponStats != null ? weaponStats : this.inventory.j();
    }

    public final int O(int i2) {
        double d2 = i2;
        return (int) (Math.ceil((((double) SheetBonus.e(this)) / 100.0d) * d2) + d2);
    }

    public final boolean P(String str) {
        AttributesSet attributesSet = this.attributes;
        if (attributesSet == null) {
            return false;
        }
        return attributesSet.a(str);
    }

    public final int Q(int i2) {
        CharacterInventory characterInventory = this.inventory;
        characterInventory.getClass();
        int iL = GameData.v().backpack.l(i2);
        if (characterInventory.slot_head == i2) {
            iL++;
        }
        if (characterInventory.slot_hands == i2) {
            iL++;
        }
        if (characterInventory.slot_legs == i2) {
            iL++;
        }
        if (characterInventory.slot_feet == i2) {
            iL++;
        }
        if (characterInventory.slot_mainhand == i2) {
            iL++;
        }
        if (characterInventory.slot_offhand == i2) {
            iL++;
        }
        if (characterInventory.slot_ring == i2) {
            iL++;
        }
        if (characterInventory.slot_ring2 == i2) {
            iL++;
        }
        if (characterInventory.slot_belt == i2) {
            iL++;
        }
        if (characterInventory.slot_cloak == i2) {
            iL++;
        }
        if (characterInventory.slot_necklace == i2) {
            iL++;
        }
        return characterInventory.slot_body == i2 ? iL + 1 : iL;
    }

    public final void R(int i2) {
        CharacterStats characterStats = this.stats;
        int i3 = characterStats.missingHP - i2;
        characterStats.missingHP = i3;
        if (i3 < 0) {
            characterStats.missingHP = 0;
        }
    }

    public final void S() {
        R(this.stats.missingHP);
        this.stats.missingMana = 0;
    }

    public final boolean T() {
        if (l().robot || l().shadow) {
            return true;
        }
        CharacterInventory characterInventory = this.inventory;
        if (characterInventory == null) {
            return false;
        }
        if (characterInventory.stunimmunity) {
            return true;
        }
        return Z() && this.inventory.j().b() && this.skillSet.g("infantry_training") >= 2;
    }

    public final void U(int i2) {
        this.traits.h(i2);
    }

    public final boolean V() {
        return this.stats.c().equals(Rules.CharacterClass.WIZARD) || this.stats.c().equals(Rules.CharacterClass.CLERIC);
    }

    public final int W(int i2) {
        if (this.traits == null) {
            this.stats.getClass();
            this.traits = new CharacterTraits(0);
        }
        CharacterTraits characterTraits = this.traits;
        int iD = SheetBonus.d(characterTraits, i2, this.inventory, this.effects);
        if (iD > characterTraits.d(i2)) {
            return 1;
        }
        return iD < characterTraits.d(i2) ? -1 : 0;
    }

    public final boolean X() {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        return equals(GameData.v().player.sheet);
    }

    public final boolean Y() {
        WeaponStats weaponStats = this.hardcoded_weapon;
        return weaponStats != null ? weaponStats.ranged : this.inventory.j().ranged;
    }

    public final boolean Z() {
        CharacterInventory characterInventory = this.inventory;
        if (characterInventory != null && characterInventory.shield) {
            return true;
        }
        AttributesSet attributesSet = this.attributes;
        return attributesSet != null && attributesSet.shield;
    }

    public final float a() {
        return (N().reach * 32) + 14.0f;
    }

    public final void a0(String str) {
        if (this.skillSet == null) {
            this.skillSet = new SkillSet();
        }
        if (this.skillSet.i()) {
            this.skillSet.m(str);
        }
    }

    public final void b0(int i2) {
        CharacterInventory characterInventory = this.inventory;
        characterInventory.getClass();
        if (GameData.v().backpack.l(i2) > 0) {
            GameData.v().backpack.q(i2);
            return;
        }
        if (characterInventory.slot_head == i2) {
            characterInventory.slot_head = 0;
            return;
        }
        if (characterInventory.slot_hands == i2) {
            characterInventory.slot_hands = 0;
            return;
        }
        if (characterInventory.slot_legs == i2) {
            characterInventory.slot_legs = 0;
            return;
        }
        if (characterInventory.slot_feet == i2) {
            characterInventory.slot_feet = 0;
            return;
        }
        if (characterInventory.slot_mainhand == i2) {
            characterInventory.slot_mainhand = 0;
            return;
        }
        if (characterInventory.slot_offhand == i2) {
            characterInventory.slot_offhand = 0;
            return;
        }
        if (characterInventory.slot_ring == i2) {
            characterInventory.slot_ring = 0;
            return;
        }
        if (characterInventory.slot_ring2 == i2) {
            characterInventory.slot_ring2 = 0;
            return;
        }
        if (characterInventory.slot_belt == i2) {
            characterInventory.slot_belt = 0;
            return;
        }
        if (characterInventory.slot_body == i2) {
            characterInventory.slot_body = 0;
        } else if (characterInventory.slot_cloak == i2) {
            characterInventory.slot_cloak = 0;
        } else if (characterInventory.slot_necklace == i2) {
            characterInventory.slot_necklace = 0;
        }
    }

    public final void c(float f2, int i2, String str) {
        SheetEffect.SheetEffectType sheetEffectTypeA = SheetEffect.a(str);
        if (this.sheetEffects == null) {
            this.sheetEffects = new SheetEffects();
        }
        this.sheetEffects.a(sheetEffectTypeA, GameData.v().u() + f2, i2);
    }

    public final boolean c0(int i2) {
        if (p() >= i2) {
            return true;
        }
        int iG = this.skillSet.g("magical_training");
        if (iG == 0) {
            return false;
        }
        int i3 = iG == 2 ? 6 : 10;
        if (iG == 3) {
            i3 = 4;
        }
        return o() > i2 * i3;
    }

    public final boolean d() {
        return this.stats.missingHP >= z();
    }

    public final void d0() {
        this.inventory.s();
        if (this.traits == null) {
            this.stats.getClass();
            this.traits = new CharacterTraits(0);
        }
        this.traits.i();
        this.stats.k();
        this.skillSet.n();
    }

    public final void e(int i2) {
        if (Rules.m(GameData.v().backpack.j(i2).itemID, this).booleanValue()) {
            this.inventory.c(i2);
            if (X()) {
                l1.f2768r = N().ranged;
            }
        }
    }

    public final void e0() {
        this.traits.j();
    }

    public final void f() {
        SkillSet skillSet = this.skillSet;
        if (skillSet == null) {
            return;
        }
        skillSet.bonusPoints++;
    }

    public final float f0() {
        int iG0 = g0(100, F(CharacterResistances.ResistanceType.Spirit));
        int iB = FDUtils.b(1, 100);
        if (iB <= iG0) {
            return 1.0f;
        }
        return iB / 2 > iG0 ? 0.0f : 0.5f;
    }

    public final void g() {
        this.traits.b();
    }

    public final DamageData h() {
        return i(1.0f, true, true);
    }

    public final void h0(WeaponStats weaponStats, int i2, int i3, CharacterResistances characterResistances, String str) {
        if (weaponStats != null) {
            this.hardcoded_weapon = weaponStats;
            this.hardcoded_defense = i2;
            if (characterResistances != null && characterResistances.b()) {
                this.hardcoded_resistances = characterResistances;
            }
            AttributesSet attributesSet = new AttributesSet();
            String lowerCase = str.toLowerCase(Locale.ENGLISH);
            if (lowerCase.contains("[undead]")) {
                attributesSet.undead = true;
            }
            if (lowerCase.contains("[goblin]")) {
                attributesSet.goblin = true;
            }
            if (lowerCase.contains("[orc]")) {
                attributesSet.orc = true;
            }
            if (lowerCase.contains("[beast]")) {
                attributesSet.beast = true;
            }
            if (lowerCase.contains("[detector]")) {
                attributesSet.detector = true;
            }
            if (lowerCase.contains("[outsider]")) {
                attributesSet.outsider = true;
            }
            if (lowerCase.contains("[aberration]")) {
                attributesSet.aberration = true;
            }
            if (lowerCase.contains("[nocturne]")) {
                attributesSet.nocturne = true;
            }
            if (lowerCase.contains("[regeneration]")) {
                attributesSet.regeneration = true;
            }
            if (lowerCase.contains("[fire_elemental]")) {
                attributesSet.fire_elemental = true;
            }
            if (lowerCase.contains("[ice_elemental]")) {
                attributesSet.ice_elemental = true;
            }
            if (lowerCase.contains("[shock_elemental]")) {
                attributesSet.shock_elemental = true;
            }
            if (lowerCase.contains("[ghost]")) {
                attributesSet.ghost = true;
            }
            if (lowerCase.contains("[toxic_aura]")) {
                attributesSet.toxic_aura = true;
            }
            if (lowerCase.contains("[shield]")) {
                attributesSet.shield = true;
            }
            if (lowerCase.contains("[giant]")) {
                attributesSet.giant = true;
            }
            if (lowerCase.contains("[shapechanger]")) {
                attributesSet.shapechanger = true;
            }
            if (lowerCase.contains("[immortal]")) {
                attributesSet.immortal = true;
            }
            if (lowerCase.contains("[silver]")) {
                attributesSet.silver = true;
            }
            if (lowerCase.contains("[explosive]")) {
                attributesSet.explosive = true;
            }
            if (lowerCase.contains("[robot]")) {
                attributesSet.robot = true;
            }
            if (lowerCase.contains("[growth]")) {
                attributesSet.growth = true;
            }
            if (lowerCase.contains("[shadow]")) {
                attributesSet.shadow = true;
                attributesSet.undead = true;
                attributesSet.outsider = true;
            }
            if (lowerCase.contains("[vampire]")) {
                attributesSet.undead = true;
                attributesSet.vampire = true;
            }
            this.attributes = attributesSet;
        }
        i0(i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final DamageData i(float f2, boolean z2, boolean z3) {
        float f3;
        boolean z4 = z2 && FDUtils.b(1, 100) <= n();
        WeaponStats weaponStats = this.hardcoded_weapon;
        DamageData damageData = new DamageData(weaponStats != null ? weaponStats.damageType : this.inventory.j().damageType, FDUtils.b(B(z4), y(z4)), Y());
        damageData.critical = z4;
        damageData.damages.get(0).hp = (int) (damageData.damages.get(0).hp * f2);
        float f4 = 1.0f;
        if (this.effects.fury.booleanValue()) {
            damageData.damages.get(0).hp = (int) (Math.max(this.effects.furyMultiplier, 1.0f) * damageData.damages.get(0).hp);
        }
        Damage.DamageType damageType = Damage.DamageType.Normal;
        if (z3 && this.effects.disintegrate.booleanValue()) {
            if (damageData.damages.get(0).type == damageType) {
                damageData.damages.get(0).hp += this.effects.disintegrateBonus;
            } else {
                damageData.a(damageType, this.effects.disintegrateBonus, false);
            }
        }
        boolean z5 = this.inventory.j().has_secondary_damage;
        Damage.DamageType damageType2 = Damage.DamageType.Fire;
        if (z5) {
            Damage.DamageType damageType3 = this.inventory.j().secondary_damageType;
            int i2 = this.inventory.j().secondary_Damage;
            if (this.stats.c() != Rules.CharacterClass.WIZARD || N().ranged) {
                f3 = i2;
            } else {
                f3 = i2;
                if (damageType3 == Damage.DamageType.Death) {
                    int iG = this.skillSet.g("vampiric_blade");
                    if (iG != 0 && iG != 1) {
                        if (iG != 2) {
                            if (iG == 3) {
                            }
                        }
                        f3 *= 1.5f;
                    }
                    f3 *= f4;
                } else if (damageType3 == Damage.DamageType.Cold || damageType3 == damageType2 || damageType3 == Damage.DamageType.Shock) {
                    int iG2 = this.skillSet.g("arcane_blade");
                    if (iG2 == 0 || iG2 == 1) {
                        f3 *= f4;
                    } else if (iG2 == 2) {
                        f3 *= 1.5f;
                    } else if (iG2 == 3) {
                        f3 *= 2.0f;
                    } else if (iG2 == 4) {
                        f4 = 2.5f;
                        f3 *= f4;
                    }
                }
            }
            damageData.a(this.inventory.j().secondary_damageType, (int) f3, false);
        }
        if (this.inventory.q() && Rules.f(this.inventory.slot_offhand).damageBonusType == damageType) {
            damageData.damages.get(0).hp = this.inventory.l() + damageData.damages.get(0).hp;
        }
        if (this.inventory.q() && Rules.f(this.inventory.slot_offhand).damageBonusType != damageType) {
            damageData.a(Rules.f(this.inventory.slot_offhand).damageBonusType, this.inventory.l(), false);
        }
        WeaponStats weaponStats2 = this.hardcoded_weapon;
        if (weaponStats2 != null && weaponStats2.has_secondary_damage) {
            damageData.a(weaponStats2.secondary_damageType, weaponStats2.secondary_Damage, false);
        }
        if (this.effects.flameAura) {
            damageData.a(damageType2, 4, false);
        }
        if (this.effects.poison.booleanValue()) {
            damageData.a(Damage.DamageType.Toxic, this.effects.poisonBonus, false);
        }
        damageData.procs = SheetBonus.a(this, N().a());
        damageData.weapon_item_id = this.inventory.slot_mainhand;
        return damageData;
    }

    public final void i0(int i2) {
        this.stats.l(i2);
    }

    public final int j() {
        int i2 = this.hardcoded_defense;
        if (i2 != -999) {
            return this.stats.b() + i2 + this.effects.shieldBonus;
        }
        int iD = (SheetBonus.d(J(), 4, this.inventory, this.effects) / 3) + SheetBonus.d(J(), 2, this.inventory, this.effects);
        int iG = (!Z() || N().twohanded) ? 0 : this.skillSet.g("shield_expert");
        if (this.effects.fatigued.booleanValue()) {
            iG -= 5;
        }
        int iD2 = this.inventory.d() + iG + iD + this.skillSet.bonusSet.armor;
        CharacterEffects characterEffects = this.effects;
        int i3 = characterEffects.shielded.booleanValue() ? characterEffects.shieldBonus : 0;
        if (characterEffects.holy_shielded.booleanValue()) {
            i3 += characterEffects.holy_shieldBonus;
        }
        if (characterEffects.bloodlust.booleanValue()) {
            i3 += characterEffects.bloodlustArmorBonus;
        }
        if (characterEffects.rage.booleanValue()) {
            i3 += characterEffects.rageArmorBonus;
        }
        if (characterEffects.mageArmor_Charges > 0) {
            i3 += characterEffects.mageArmorBonus;
        }
        return iD2 + i3;
    }

    public final void j0(int i2) {
        if (p() >= i2) {
            this.stats.missingMana += i2;
            return;
        }
        int iG = this.skillSet.g("magical_training");
        if (iG > 0) {
            int i3 = iG == 2 ? 6 : 10;
            if (iG == 3) {
                i3 = 4;
            }
            int i4 = i2 * i3;
            if (o() > i4) {
                this.stats.missingHP += i4;
            }
        }
    }

    public final int k() {
        if (this.inventory.slot_offhand > 0 && !N().twohanded) {
            int iG = this.skillSet.g("shield_expert");
            if (iG >= 3) {
                return 4;
            }
            if (iG == 2) {
                return 3;
            }
            if (iG == 1) {
                return 2;
            }
        }
        return 0;
    }

    public final void k0() {
        if (this.traits == null) {
            this.traits = new CharacterTraits();
        }
        this.traits.j();
        this.skillSet.a(this);
    }

    public final AttributesSet l() {
        if (this.attributes == null) {
            this.attributes = new AttributesSet();
        }
        return this.attributes;
    }

    public final int m() {
        return SheetBonus.c(this, N().a());
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int n() {
        int i2;
        if (this.hardcoded_weapon != null && this.stats.f() < 3) {
            return 0;
        }
        WeaponStats weaponStats = this.hardcoded_weapon;
        if (weaponStats != null) {
            return weaponStats.critChance;
        }
        WeaponStats weaponStatsJ = this.inventory.j();
        boolean z2 = weaponStatsJ.ranged;
        if (z2) {
            float f2 = this.skillSet.bonusSet.critChanceModifierRanged;
            if (f2 <= 1.0f) {
                if (!z2) {
                    float f3 = this.skillSet.bonusSet.critChanceModifierMelee;
                    if (f3 > 1.0f) {
                        float f4 = weaponStatsJ.critChance * f3;
                        i2 = (int) f4;
                        if (f4 <= i2) {
                            return i2;
                        }
                    }
                }
                return weaponStatsJ.critChance;
            }
            float f5 = weaponStatsJ.critChance * f2;
            i2 = (int) f5;
            if (f5 <= i2) {
                return i2;
            }
        }
        return i2 + 1;
    }

    public final int o() {
        return Math.max(z() - this.stats.missingHP, 0);
    }

    public final int p() {
        return Math.max(A() - this.stats.missingMana, 0);
    }

    public final float q() {
        float fH = H() / 10.0f;
        float fY = ((y(false) + B(false)) / 2.0f) + v();
        float fY2 = ((y(true) + B(true)) / 2.0f) + v();
        if (this.effects.fury.booleanValue()) {
            fY *= Math.max(this.effects.furyMultiplier, 1.0f);
        }
        float fN = (n() * fY2) / 100.0f;
        DamageData damageDataI = i(1.0f, true, false);
        if (damageDataI.damages.size() > 1) {
            for (int i2 = 1; i2 < damageDataI.damages.size(); i2++) {
                fY += damageDataI.damages.get(i2).hp;
            }
        }
        return (((1.0f - (n() / 100.0f)) * fY) + fN) * fH;
    }

    public final float r() {
        return this.stats.missingHP / z();
    }

    public final int s() {
        int i2 = 0;
        int i3 = this.stats.c() == Rules.CharacterClass.ROGUE ? 15 : 0;
        int iD = SheetBonus.d(J(), 4, this.inventory, this.effects);
        int iD2 = SheetBonus.d(J(), 3, this.inventory, this.effects);
        Iterator<Follower> it = GameData.v().party.followers.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next().spawn_id.contains("familiar")) {
                i2 = 20;
                break;
            }
        }
        return this.inventory.e() + (((iD2 / 2) + iD) * 5) + i3 + i2 + this.skillSet.bonusSet.detect;
    }

    public final int t() {
        int iD = SheetBonus.d(J(), 2, this.inventory, this.effects);
        int iD2 = SheetBonus.d(J(), 3, this.inventory, this.effects);
        int i2 = this.stats.c() == Rules.CharacterClass.ROGUE ? 20 : 0;
        return this.inventory.f() + (((iD2 / 2) + iD) * 5) + (this.skillSet.g("trap_master") > 0 ? this.skillSet.g("trap_master") * 10 : 0) + i2;
    }

    public final int u() {
        return this.inventory.g() + (((SheetBonus.d(J(), 4, this.inventory, this.effects) / 2) + SheetBonus.d(J(), 5, this.inventory, this.effects)) * 5) + this.skillSet.bonusSet.gossip + (this.stats.c().equals(Rules.CharacterClass.ROGUE) ? 10 : 0);
    }

    public final int v() {
        CharacterInventory characterInventory = this.inventory;
        if (characterInventory != null && characterInventory.q() && Rules.f(this.inventory.slot_offhand).damageBonusType == Damage.DamageType.Normal) {
            return this.inventory.l();
        }
        return 0;
    }

    public final int w(int i2) {
        switch (i2) {
            case 0:
                return this.inventory.slot_head;
            case 1:
                return this.inventory.slot_body;
            case 2:
                return this.inventory.slot_hands;
            case 3:
                return this.inventory.slot_legs;
            case 4:
                return this.inventory.slot_feet;
            case 5:
                return this.inventory.slot_ring;
            case 6:
                return this.inventory.slot_mainhand;
            case 7:
                return this.inventory.slot_offhand;
            case 8:
                return this.inventory.slot_cloak;
            case 9:
                return this.inventory.slot_necklace;
            case 10:
                return this.inventory.slot_ring2;
            case 11:
                return this.inventory.slot_belt;
            default:
                return 0;
        }
    }

    public final String x() {
        CharacterInventory characterInventory = this.inventory;
        if (characterInventory != null && characterInventory.j() != null) {
            CharacterInventory characterInventory2 = this.inventory;
            int i2 = characterInventory2.slot_mainhand;
            String str = (i2 <= 0 || Rules.f(i2).sprite.equals("")) ? "" : Rules.f(characterInventory2.slot_mainhand).sprite;
            if (!str.equals("")) {
                return "mainhand_".concat(str);
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001c A[PHI: r3 r5
      0x001c: PHI (r3v5 int) = (r3v4 int), (r3v10 int) binds: [B:11:0x0035, B:6:0x001a] A[DONT_GENERATE, DONT_INLINE]
      0x001c: PHI (r5v14 float) = (r5v11 float), (r5v16 float) binds: [B:11:0x0035, B:6:0x001a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int y(boolean z2) {
        int i2;
        int iM;
        float fM;
        int i3;
        if (!z2) {
            WeaponStats weaponStats = this.hardcoded_weapon;
            if (weaponStats != null) {
                i2 = weaponStats.maxDamage;
                iM = m();
            } else {
                i2 = this.inventory.j().maxDamage;
                iM = m();
            }
            return iM + i2;
        }
        WeaponStats weaponStats2 = this.hardcoded_weapon;
        float f2 = 150.0f;
        if (weaponStats2 != null) {
            fM = m() + weaponStats2.maxDamage;
            i3 = this.skillSet.bonusSet.critDamageModifier;
            if (i3 > 150) {
                f2 = i3;
            }
        } else {
            fM = m() + this.inventory.j().maxDamage;
            i3 = this.skillSet.bonusSet.critDamageModifier;
            if (i3 > 150) {
            }
        }
        return (int) ((f2 / 100.0f) * fM);
    }

    public final int z() {
        int i2;
        int iF;
        int iD = SheetBonus.d(J(), 1, this.inventory, this.effects);
        int iD2 = SheetBonus.d(J(), 0, this.inventory, this.effects);
        int i3 = iD2 / 2;
        int iF2 = this.stats.f() * (iD + i3);
        int iG = this.skillSet.g("body_development");
        if (iG == 1) {
            iF2 = this.stats.f() * ((iD / 2) + iD + i3);
        } else {
            if (iG == 2) {
                i2 = (iD / 2) + iD + iD2;
                iF = this.stats.f();
            } else if (iG == 3) {
                i2 = (iD * 2) + iD2;
                iF = this.stats.f();
            }
            iF2 = iF * i2;
        }
        return (iD * 5) + this.inventory.h() + this.stats.g() + this.skillSet.bonusSet.HP + iF2 + (P("giant") ? this.stats.f() * 5 : 0);
    }

    public CharacterSheet(Rules.CharacterRace characterRace, Rules.CharacterClass characterClass) {
        this.hardcoded_weapon = null;
        this.hardcoded_defense = -999;
        this.hardcoded_resistances = null;
        this.attributes = null;
        this.traits = null;
        this.MITIGATION_CAP = 0.15f;
        this.stats = new CharacterStats(characterRace, characterClass);
        this.inventory = new CharacterInventory();
        this.skillSet = new SkillSet();
        this.effects = new CharacterEffects();
        this.traits = new CharacterTraits(0);
    }
}
