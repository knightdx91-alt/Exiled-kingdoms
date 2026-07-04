package net.fdgames.GameEntities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import k0.a;
import m0.b;
import n0.w;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.AI.Pathfinding.Path;
import net.fdgames.GameEntities.CharacterSheet.AttributesSet;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterStats;
import net.fdgames.GameEntities.Final.MapEffectEntity;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Projectile;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.Follower;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameLog;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.MessageRouter;
import net.fdgames.GameWorld.Party;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.AreaEffects;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.ItemAttributes;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.SkillActions;
import net.fdgames.Rules.Skills;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class Character extends MapActor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Color f2991a = new Color(0.0f, 0.0f, 0.0f, 0.5f);
    public int SkillUseData_consecutive_hits;
    public float SkillUseData_consecutive_lasthit;
    public float SkillUseData_lastStabTime;
    public int SkillUseData_lastStabbed_id;
    public float SkillUseData_last_melee_kill;
    private float drawsize;
    public Gender gender;
    protected boolean killedByNPC;
    public float lastItemUsed;
    private float lastSkillEffectCheck;
    public int lastTargetHit_id;
    public float lastWeakEffect;
    private String logString;
    private String name;
    public int nextStep;
    public Path path;
    public int portraitIndex;
    protected DamageData receivedDamage;
    public CharacterSheet sheet;
    private float size;
    public Coords skillOrigin;
    public float speedModifier;
    private int spellTarget;
    public String spell_id;
    public a<Integer> spriteIndex;
    public boolean wasJustHitByMageBarrier;
    public Coords waypointDestination;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum Gender {
        Male, Female, None;
    }

    public Character() {
        this.gender = Gender.Male;
        this.speedModifier = 1.0f;
        this.size = 1.0f;
        this.drawsize = 1.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x068a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x025c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void J0() {
        Coords coords;
        String str;
        int iC;
        int i2;
        Coords coords2;
        Coords coords3;
        int i3;
        int i4;
        int i5;
        WeaponStats weaponStatsJ;
        int iG;
        int iF;
        int iC2;
        Coords coords4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        String str2 = this.spell_id;
        Locale locale = Locale.ENGLISH;
        if (str2.toLowerCase(locale).equals("heal_wounds")) {
            int i11 = this.spellTarget;
            if (i11 == 0) {
                SkillActions.c(this, this);
            } else {
                Character characterF = GameLevel.f(i11);
                if (characterF != null) {
                    SkillActions.c(this, characterF);
                }
            }
        } else if (this.spell_id.toLowerCase(locale).equals("thelumes_wisdom")) {
            int iG2 = this.sheet.skillSet.g("thelumes_wisdom");
            if (iG2 == 1) {
                SkillActions.b(this, false);
            }
            if (iG2 == 2) {
                SkillActions.b(this, true);
            }
            this.sheet.skillSet.s("thelumes_wisdom");
        } else if (this.spell_id.toLowerCase(locale).equals("detect")) {
            SkillActions.b(this, false);
        } else {
            int i12 = 8;
            if (this.spell_id.toLowerCase(locale).equals("arbenos_might")) {
                int iG3 = this.sheet.skillSet.g("arbenos_might");
                if (iG3 == 1) {
                    k1(3, 12.0f);
                    K0(12.0f);
                } else if (iG3 == 2) {
                    k1(5, 14.0f);
                    K0(14.0f);
                } else if (iG3 == 3) {
                    k1(8, 16.0f);
                    K0(16.0f);
                }
                this.sheet.skillSet.s("arbenos_might");
            } else {
                int i13 = 4;
                if (this.spell_id.toLowerCase(locale).equals("turn_undead")) {
                    int iG4 = this.sheet.skillSet.g("turn_undead");
                    if (iG4 == 1) {
                        i8 = 2;
                        i9 = 3;
                    } else {
                        i8 = 0;
                        i9 = 0;
                    }
                    if (iG4 == 2) {
                        i9 = 4;
                    } else {
                        i13 = i8;
                    }
                    if (iG4 == 3) {
                        i10 = 6;
                    } else {
                        i = i9;
                        i10 = i13;
                    }
                    GameAssets.o("spell1");
                    for (NPC npc : GameLevelData.t()) {
                        if (!npc.destroy && npc.sheet.P("undead") && b.s(npc.B(), B()) <= i10 * 32 && GameWorld.f3189c.g(npc.r(), r())) {
                            npc.z1(i);
                        }
                    }
                    k0.a.l().d(1.0f, this.f3092x, this.f3093y, i10 * 32, "flash_red");
                    this.sheet.skillSet.s("turn_undead");
                } else if (this.spell_id.toLowerCase(locale).equals("flames_of_faith")) {
                    AreaEffects.e(this.f3092x, this.f3093y, this.uniqueID, this.sheet.skillSet.g("flames_of_faith"));
                    this.sheet.skillSet.s("flames_of_faith");
                } else {
                    int i14 = 10;
                    if (this.spell_id.toLowerCase(locale).equals("battle_prayer")) {
                        int iG5 = this.sheet.skillSet.g("battle_prayer");
                        if (iG5 == 1) {
                            i6 = 2;
                            i7 = 3;
                        } else {
                            i6 = 0;
                            i7 = 0;
                        }
                        if (iG5 == 2) {
                            i7 = 5;
                            i6 = 3;
                        } else {
                            i14 = 0;
                        }
                        if (iG5 == 3) {
                            i14 = 25;
                        } else {
                            i = i6;
                            i12 = i7;
                        }
                        t1(i12, 12.0f);
                        l1(i);
                        p1(i14);
                        if (GameData.v().party.j()) {
                            GameData.v().party.f().t1(i12, 12.0f);
                            GameData.v().party.f().l1(i);
                            GameData.v().party.f().p1(i14);
                        }
                        if (GameData.v().party != null && GameData.v().party.followers != null) {
                            Iterator<Follower> it = GameData.v().party.followers.iterator();
                            while (it.hasNext()) {
                                NPC npcJ = GameLevel.j(it.next().a().tag);
                                if (npcJ != null) {
                                    npcJ.t1(i12, 12.0f);
                                    npcJ.l1(i);
                                    npcJ.p1(i14);
                                }
                            }
                            this.sheet.skillSet.s("battle_prayer");
                        }
                    } else {
                        int i15 = 20;
                        int i16 = 15;
                        if (this.spell_id.toLowerCase(locale).equals("lightning_bolt")) {
                            int iG6 = this.sheet.skillSet.g("lightning_bolt");
                            if (iG6 <= 0) {
                                weaponStatsJ = null;
                                Damage.DamageType damageType = Damage.DamageType.Shock;
                                CharacterSheet characterSheet = this.sheet;
                                iG = characterSheet.skillSet.g("lightning_bolt");
                                if (iG <= 0) {
                                    iF = 0;
                                    DamageData damageData = new DamageData(damageType, iF, true);
                                    damageData.procs.add(new DamageEffect(weaponStatsJ.procEffect, weaponStatsJ.procLevel, weaponStatsJ.procChance));
                                    this.sheet.skillSet.s("lightning_bolt");
                                    GameAssets.o("spell_major");
                                    b.P().getClass();
                                    Rectangle pVarU = b.U();
                                    b bVarP = b.P();
                                    int i17 = this.uniqueID;
                                    b.P().getClass();
                                    iC2 = bVarP.c(i17, b.j(pVarU));
                                    if (iC2 == 0) {
                                        iC2 = b.P().a(B(), r());
                                    }
                                    Projectile.ProjectileType projectileType = Projectile.ProjectileType.BOLT;
                                    if (iC2 <= 0) {
                                        Character characterF2 = GameLevel.f(iC2);
                                        if (characterF2 != null && b.P().e0(B(), characterF2.B())) {
                                            GameLevel.c(this.uniqueID, iC2, r(), weaponStatsJ, damageData, projectileType);
                                            R(characterF2.B());
                                        }
                                    } else {
                                        float f2 = this.speedX;
                                        if (f2 == 0.0f && this.speedY == 0.0f) {
                                            coords4 = b.L(B(), 100, this.facing);
                                        } else {
                                            float f3 = this.speedY * 100.0f;
                                            double dSqrt = 100.0d / Math.sqrt((f3 * f3) + (r3 * r3));
                                            coords4 = new Coords(this.f3092x + ((int) (((double) (f2 * 100.0f)) * dSqrt)), this.f3093y + ((int) (((double) f3) * dSqrt)));
                                        }
                                        GameLevelData.h(new Projectile(this.uniqueID, coords4.f3287x, coords4.f3288y, r(), weaponStatsJ, damageData, projectileType));
                                    }
                                } else {
                                    if (iG == 1) {
                                        iF = FDUtils.b(0, 5) + 10;
                                    } else if (iG == 2) {
                                        iF = FDUtils.b(0, 5) + 15 + characterSheet.stats.f();
                                    } else if (iG == 3) {
                                        iF = (characterSheet.stats.f() * 2) + FDUtils.b(0, 8) + 20;
                                    } else if (iG == 4) {
                                        iF = (characterSheet.stats.f() * 2) + FDUtils.b(0, 8) + 36;
                                    }
                                    DamageData damageData2 = new DamageData(damageType, iF, true);
                                    damageData2.procs.add(new DamageEffect(weaponStatsJ.procEffect, weaponStatsJ.procLevel, weaponStatsJ.procChance));
                                    this.sheet.skillSet.s("lightning_bolt");
                                    GameAssets.o("spell_major");
                                    b.P().getClass();
                                    Rectangle pVarU2 = b.U();
                                    b bVarP2 = b.P();
                                    int i172 = this.uniqueID;
                                    b.P().getClass();
                                    iC2 = bVarP2.c(i172, b.j(pVarU2));
                                    if (iC2 == 0) {
                                    }
                                    Projectile.ProjectileType projectileType2 = Projectile.ProjectileType.BOLT;
                                    if (iC2 <= 0) {
                                    }
                                }
                            } else {
                                if (iG6 == 1) {
                                    weaponStatsJ = Rules.j("lightning_bolt1");
                                } else if (iG6 == 2) {
                                    weaponStatsJ = Rules.j("lightning_bolt2");
                                } else if (iG6 == 3) {
                                    weaponStatsJ = Rules.j("lightning_bolt3");
                                } else if (iG6 == 4) {
                                    weaponStatsJ = Rules.j("lightning_bolt4");
                                }
                                Damage.DamageType damageType2 = Damage.DamageType.Shock;
                                CharacterSheet characterSheet2 = this.sheet;
                                iG = characterSheet2.skillSet.g("lightning_bolt");
                                if (iG <= 0) {
                                }
                            }
                        } else if (this.spell_id.toLowerCase(locale).equals("mage_armor")) {
                            int iG7 = this.sheet.skillSet.g("mage_armor");
                            if (iG7 == 1) {
                                i3 = 3;
                                i4 = 6;
                            } else {
                                i3 = 0;
                                i4 = 0;
                            }
                            if (iG7 == 2) {
                                i4 = 9;
                                i3 = 4;
                            } else {
                                i16 = 0;
                            }
                            if (iG7 == 3) {
                                i4 = 14;
                            } else {
                                i = i3;
                                i15 = i16;
                            }
                            if (iG7 == 4) {
                                i4 = 21;
                                i15 = 30;
                                i5 = 6;
                            } else {
                                i5 = i;
                            }
                            CharacterEffects characterEffects = this.sheet.effects;
                            characterEffects.mageArmor_Charges = i5;
                            characterEffects.mageArmorBonus = i4;
                            characterEffects.mageArmorElementalBonus = i15;
                            int i18 = this.uniqueID;
                            MessageRouter.a("UNMAGEARMOR", i18, i18, null, 180.0f, null);
                            this.sheet.skillSet.s("mage_armor");
                        } else if (this.spell_id.toLowerCase(locale).equals("lesser_summoning")) {
                            int iG8 = this.sheet.skillSet.g("lesser_summoning");
                            if (iG8 == 1) {
                                SkillActions.l(this, "familiar1", this.sheet.stats.f() < 4 ? 2 : 3, 120);
                            }
                            if (iG8 == 2) {
                                int iF2 = this.sheet.stats.f();
                                SkillActions.l(this, "familiar2", iF2 >= 7 ? 6 : iF2 < 6 ? 4 : 5, 120);
                            }
                            this.sheet.skillSet.s("lesser_summoning");
                        } else if (this.spell_id.toLowerCase(locale).equals("fire_mastery")) {
                            int iG9 = this.sheet.skillSet.g("fire_mastery");
                            if (iG9 == 1) {
                                SkillActions.l(this, "fire_elemental_1", 7, 180);
                            }
                            if (iG9 == 2) {
                                SkillActions.l(this, "fire_elemental_2", 10, 180);
                            }
                            if (iG9 == 3) {
                                SkillActions.l(this, "fire_elemental_3", 13, 180);
                            }
                            this.sheet.skillSet.s("fire_mastery");
                        } else if (this.spell_id.toLowerCase(locale).equals("ice_mastery")) {
                            int iG10 = this.sheet.skillSet.g("ice_mastery");
                            if (iG10 == 1) {
                                SkillActions.l(this, "ice_elemental_1", 7, 180);
                            }
                            if (iG10 == 2) {
                                SkillActions.l(this, "ice_elemental_2", 10, 180);
                            }
                            if (iG10 == 3) {
                                SkillActions.l(this, "ice_elemental_3", 13, 180);
                            }
                            this.sheet.skillSet.s("ice_mastery");
                        } else if (this.spell_id.toLowerCase(locale).equals("earth_mastery")) {
                            int iG11 = this.sheet.skillSet.g("earth_mastery");
                            if (iG11 == 1) {
                                SkillActions.l(this, "elemental_earth_lesser", 8, 180);
                            }
                            if (iG11 == 2) {
                                SkillActions.l(this, "elemental_earth", 11, 180);
                            }
                            if (iG11 == 3) {
                                SkillActions.l(this, "golem_iron_1", 15, 180);
                            }
                            this.sheet.skillSet.s("earth_mastery");
                        } else {
                            boolean zEquals = this.spell_id.toLowerCase(locale).equals("fireball");
                            Projectile.ProjectileType projectileType3 = Projectile.ProjectileType.GRENADE;
                            if (zEquals) {
                                int iG12 = this.sheet.skillSet.g("fireball");
                                String str3 = "fireball_weak_1";
                                if (iG12 > 0 && iG12 != 1) {
                                    if (iG12 == 2) {
                                        str3 = "fireball_weak_2";
                                    } else if (iG12 == 3) {
                                        str3 = "fireball_weak_3";
                                    } else if (iG12 == 4) {
                                        str3 = "fireball_weak_4";
                                    }
                                }
                                GameAssets.o("spell_major");
                                this.sheet.skillSet.s("fireball");
                                b.P().getClass();
                                Rectangle pVarU3 = b.U();
                                b bVarP3 = b.P();
                                int i19 = this.uniqueID;
                                b.P().getClass();
                                int iC3 = bVarP3.c(i19, b.j(pVarU3));
                                if (iC3 == 0) {
                                    iC3 = b.P().a(B(), r());
                                }
                                int i20 = iC3;
                                if (i20 > 0) {
                                    Character characterF3 = GameLevel.f(i20);
                                    if (characterF3 != null && b.P().e0(B(), characterF3.B())) {
                                        Projectile projectileC = GameLevel.c(this.uniqueID, i20, r(), Rules.j("fire_shot"), null, projectileType3);
                                        if (projectileC != null) {
                                            projectileC.N(str3);
                                        }
                                        R(characterF3.B());
                                    }
                                } else {
                                    float f4 = this.speedX;
                                    if (f4 == 0.0f && this.speedY == 0.0f) {
                                        coords3 = b.L(B(), 100, this.facing);
                                    } else {
                                        float f5 = this.speedY * 100.0f;
                                        double dSqrt2 = 100.0d / Math.sqrt((f5 * f5) + (r3 * r3));
                                        coords3 = new Coords(this.f3092x + ((int) (((double) (f4 * 100.0f)) * dSqrt2)), this.f3093y + ((int) (((double) f5) * dSqrt2)));
                                    }
                                    Projectile projectile = new Projectile(this.uniqueID, coords3.f3287x, coords3.f3288y, r(), Rules.j("fire_shot"), null, projectileType3);
                                    GameLevelData.h(projectile);
                                    projectile.N(str3);
                                }
                            } else if (this.spell_id.toLowerCase(locale).equals("death_cloud")) {
                                int iG13 = this.sheet.skillSet.g("death_cloud");
                                if (iG13 <= 0) {
                                    str = "dc_1";
                                    GameAssets.o("spell_major");
                                    this.sheet.skillSet.s("death_cloud");
                                    b.P().getClass();
                                    Rectangle pVarU4 = b.U();
                                    b bVarP4 = b.P();
                                    int i21 = this.uniqueID;
                                    b.P().getClass();
                                    iC = bVarP4.c(i21, b.j(pVarU4));
                                    if (iC == 0) {
                                        iC = b.P().a(B(), r());
                                    }
                                    i2 = iC;
                                    if (i2 <= 0) {
                                        Character characterF4 = GameLevel.f(i2);
                                        if (characterF4 != null && b.P().e0(B(), characterF4.B())) {
                                            Projectile projectileC2 = GameLevel.c(this.uniqueID, i2, r(), Rules.j("toxic_spit"), null, projectileType3);
                                            if (projectileC2 != null) {
                                                projectileC2.N(str);
                                            }
                                            R(characterF4.B());
                                        }
                                    } else {
                                        float f6 = this.speedX;
                                        if (f6 == 0.0f && this.speedY == 0.0f) {
                                            coords2 = b.L(B(), 100, this.facing);
                                        } else {
                                            float f7 = this.speedY * 100.0f;
                                            double dSqrt3 = 100.0d / Math.sqrt((f7 * f7) + (r3 * r3));
                                            coords2 = new Coords(this.f3092x + ((int) (((double) (f6 * 100.0f)) * dSqrt3)), this.f3093y + ((int) (((double) f7) * dSqrt3)));
                                        }
                                        Projectile projectile2 = new Projectile(this.uniqueID, coords2.f3287x, coords2.f3288y, r(), Rules.j("toxic_spit"), null, projectileType3);
                                        GameLevelData.h(projectile2);
                                        projectile2.N(str);
                                    }
                                } else {
                                    if (iG13 == 1) {
                                        str = "dc_weak_1";
                                    } else if (iG13 == 2) {
                                        str = "dc_weak_2";
                                    } else if (iG13 == 3) {
                                        str = "dc_weak_3";
                                    }
                                    GameAssets.o("spell_major");
                                    this.sheet.skillSet.s("death_cloud");
                                    b.P().getClass();
                                    Rectangle pVarU42 = b.U();
                                    b bVarP42 = b.P();
                                    int i212 = this.uniqueID;
                                    b.P().getClass();
                                    iC = bVarP42.c(i212, b.j(pVarU42));
                                    if (iC == 0) {
                                    }
                                    i2 = iC;
                                    if (i2 <= 0) {
                                    }
                                }
                            } else if (this.spell_id.toLowerCase(locale).equals("combustion")) {
                                AreaEffects.c(this.f3092x, this.f3093y, this.uniqueID, this.sheet.skillSet.g("combustion"));
                                this.sheet.skillSet.s("combustion");
                            } else if (this.spell_id.toLowerCase(locale).equals("ice_storm")) {
                                this.sheet.skillSet.s("ice_storm");
                                GameAssets.o("spell_major");
                                b.P().getClass();
                                Rectangle pVarU5 = b.U();
                                b bVarP5 = b.P();
                                int i22 = this.uniqueID;
                                b.P().getClass();
                                int iC4 = bVarP5.c(i22, b.j(pVarU5));
                                int iG14 = this.sheet.skillSet.g("ice_storm");
                                String str4 = "ice_storm_weak_1";
                                if (iG14 > 0 && iG14 != 1) {
                                    if (iG14 == 2) {
                                        str4 = "ice_storm_weak_2";
                                    } else if (iG14 == 3) {
                                        str4 = "ice_storm_weak_3";
                                    } else if (iG14 == 4) {
                                        str4 = "ice_storm_weak_4";
                                    }
                                }
                                String str5 = str4;
                                if (iC4 == 0) {
                                    iC4 = b.P().a(B(), r());
                                }
                                if (iC4 > 0) {
                                    Character characterF5 = GameLevel.f(iC4);
                                    if (characterF5 != null && b.P().e0(B(), characterF5.B())) {
                                        MapEffectEntity mapEffectEntity = new MapEffectEntity(0.0f, this.f3092x, this.f3093y, this.uniqueID, str5);
                                        GameLevelData.e(mapEffectEntity);
                                        mapEffectEntity.U(characterF5.B().f3287x, characterF5.B().f3288y);
                                        mapEffectEntity.S(4.5f);
                                        R(characterF5.B());
                                    }
                                } else {
                                    float f8 = this.speedX;
                                    if (f8 == 0.0f && this.speedY == 0.0f) {
                                        coords = b.L(B(), 100, this.facing);
                                    } else {
                                        float f9 = this.speedY * 100.0f;
                                        double dSqrt4 = 100.0d / Math.sqrt((f9 * f9) + (r1 * r1));
                                        coords = new Coords(this.f3092x + ((int) (((double) (f8 * 100.0f)) * dSqrt4)), this.f3093y + ((int) (((double) f9) * dSqrt4)));
                                    }
                                    MapEffectEntity mapEffectEntity2 = new MapEffectEntity(0.0f, this.f3092x, this.f3093y, this.uniqueID, str5);
                                    GameLevelData.e(mapEffectEntity2);
                                    mapEffectEntity2.U(coords.f3287x, coords.f3288y);
                                    mapEffectEntity2.S(4.0f);
                                }
                            } else if (this.spell_id.toLowerCase(locale).equals("disintegrate")) {
                                int iG15 = this.sheet.skillSet.g("disintegrate");
                                int iF3 = this.sheet.stats.f();
                                if (iG15 == 1) {
                                    I0(50);
                                } else if (iG15 == 2) {
                                    I0(iF3 + 75);
                                } else if (iG15 == 3) {
                                    I0((iF3 * 2) + 100);
                                }
                                this.sheet.skillSet.s("disintegrate");
                            }
                        }
                    }
                }
            }
        }
        k0.a.l().e(0.6f, this.uniqueID, 120, "flash_blue");
        this.spell_id = "";
        this.spellTarget = 0;
        q0(MapActor.ActorState.IDLE);
        t0();
    }

    private int S0() {
        return (int) (((1.0f - this.drawsize) * 140) / 2.0f);
    }

    private void x0() {
        Coords coords = this.destination;
        if (coords.f3287x == -1 || coords.f3288y == -1) {
            return;
        }
        N(0.0f);
        O(0.0f);
        int i2 = this.f3092x;
        int i3 = this.destination.f3287x;
        if (!(i2 == i3)) {
            if (i2 < i3) {
                N(this.speedModifier * 1.0f);
            } else if (i2 > i3) {
                N((-this.speedModifier) * 1.0f);
            }
        }
        int i4 = this.f3093y;
        int i5 = this.destination.f3288y;
        if (i4 == i5) {
            return;
        }
        if (i4 < i5) {
            O(this.speedModifier * 1.0f);
        } else if (i4 > i5) {
            O((-this.speedModifier) * 1.0f);
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String A() {
        return this.conversationTag;
    }

    public boolean A0(int i2, boolean z2) {
        int iA = (int) this.sheet.a();
        if (z2) {
            iA = 44;
        }
        MapSprite mapSpriteI = GameLevel.i(i2);
        return mapSpriteI != null && Math.abs(this.f3092x - mapSpriteI.f3092x) < iA && Math.abs(this.f3093y - mapSpriteI.f3093y) < iA;
    }

    public boolean A1(int i2) {
        this.sheet.getClass();
        return CharacterSheet.b(i2);
    }

    public boolean B0() {
        int i2 = this.f3092x;
        Coords coords = this.destination;
        return i2 == coords.f3287x && this.f3093y == coords.f3288y;
    }

    public final void C0() {
        this.path = null;
        Coords coords = this.destination;
        coords.f3287x = this.f3092x;
        coords.f3288y = this.f3093y;
        this.speedX = 0.0f;
        this.speedY = 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void D0() {
        if (i0()) {
            return;
        }
        if (this.sheet.effects.slowed.booleanValue() || this.sheet.effects.fatigued.booleanValue() || this.sheet.effects.resistances.c()) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.j("slowed").getKeyFrame(GameLevel.b()));
        }
        if (this.sheet.effects.stunned.booleanValue()) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.j("stunned").getKeyFrame(GameLevel.b()));
        }
        if (this.sheet.effects.holy_shielded.booleanValue() || ((this.sheet.effects.shielded.booleanValue() && this.sheet.effects.shieldBonus > 0) || this.sheet.effects.mageArmor_Charges > 0)) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.j("shield").getKeyFrame(GameLevel.b()));
        }
        if (this.sheet.effects.stab.booleanValue() || this.sheet.effects.fury.booleanValue() || this.sheet.effects.might.booleanValue()) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.j("damage").getKeyFrame(GameLevel.b()));
        } else {
            CharacterEffects characterEffects = this.sheet.effects;
            if (!characterEffects.rapid_fire && !characterEffects.disintegrate.booleanValue() && !this.sheet.effects.duel.booleanValue()) {
                CharacterEffects characterEffects2 = this.sheet.effects;
                if (characterEffects2.flurry || characterEffects2.rage.booleanValue() || this.sheet.effects.might_arbenos.booleanValue() || this.sheet.effects.might_prayer.booleanValue()) {
                }
            }
        }
        if (this.sheet.effects.bloodlust.booleanValue()) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.j("bloodlust").getKeyFrame(GameLevel.b()));
        }
        if (this.sheet.effects.evasion.booleanValue()) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.j("evasion").getKeyFrame(GameLevel.b()));
        }
        if (this.sheet.effects.resistances.d()) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.j("resist").getKeyFrame(GameLevel.b()));
        }
        if (this.sheet.effects.stealth.booleanValue()) {
            GameAssets.f3317b = 0.4f;
        } else {
            GameAssets.f3317b = 1.0f;
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return Assets.n(this.gender, this.portraitIndex);
    }

    public void E0(int i2) {
        int i3;
        if (g0() || i0() || j0()) {
            return;
        }
        this.wasJustHitByMageBarrier = false;
        boolean z2 = this.sheet.N().ranged && this.sheet.stats.c() != Rules.CharacterClass.WIZARD;
        CharacterSheet characterSheet = this.sheet;
        CharacterInventory characterInventory = characterSheet.inventory;
        int i4 = (characterInventory == null || (i3 = characterInventory.slot_mainhand) == 0) ? 0 : Rules.f(i3).attributes.arcane;
        int iG = i4 == 0 ? 0 : i4 - characterSheet.skillSet.g("arcanist");
        if (iG > 0) {
            if (this.sheet.p() < iG) {
                if (this.uniqueID == 1) {
                    GameConsole.a(GameString.b("NOT_ENOUGH_MANA", false));
                    return;
                }
                return;
            }
            this.sheet.j0(iG);
        }
        if (i2 != -1) {
            R(GameLevel.g(i2).B());
        }
        this.actionStartTime = GameLevel.b();
        int i5 = this.uniqueID;
        float fQ0 = Q0();
        int iH = this.sheet.H();
        Item[] itemArr = Rules.f3247a;
        MessageRouter.a("ATTACK", this.uniqueID, i5, null, (10.0f / iH) * fQ0, null);
        W();
        if (z2) {
            q0(MapActor.ActorState.FIRING);
        } else {
            q0(MapActor.ActorState.ATTACKING);
        }
        this.stateRelativeTime = 0.0f;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        Q();
        if (ExiledKingdoms.f3378h && !i0()) {
            int i2 = GameLevel.f3099f;
            int i3 = this.uniqueID;
            if (i2 == i3 || GameLevel.f3101h == i3) {
                spriteBatch.setColor(Color.WHITE);
                int i4 = GameLevel.f3101h;
                int i5 = this.uniqueID;
                if (i4 == i5) {
                    spriteBatch.draw(GameAssets.O, H() + S0(), I() - (8 * this.size), GameAssets.O.getRegionWidth() * this.size, GameAssets.O.getRegionHeight() * this.size);
                } else if (GameLevel.f3099f == i5) {
                    spriteBatch.draw(GameAssets.N, H() + S0(), I() - (8 * this.size), GameAssets.N.getRegionWidth() * this.size, GameAssets.N.getRegionHeight() * this.size);
                }
                spriteBatch.setColor(m0.a.g());
            }
        }
        boolean z2 = GameAssets.f3317b != 1.0f;
        if (z2) {
            spriteBatch.setColor(m0.a.g().f1680r, m0.a.g().f1679g, m0.a.g().f1678b, GameAssets.f3317b);
        }
        a.b<TextureRegion> it = GameAssets.f3315a.iterator();
        while (it.hasNext()) {
            TextureRegion next = it.next();
            if (i1()) {
                spriteBatch.setColor(f2991a);
            }
            MapActor.Facing facing = this.facing;
            if (facing == MapActor.Facing.L || facing == MapActor.Facing.LD || facing == MapActor.Facing.LU) {
                spriteBatch.draw(next, (next.getRegionWidth() * this.size) + H() + S0(), I() - (8 * this.size), (-next.getRegionWidth()) * this.size, next.getRegionHeight() * this.size);
            } else {
                spriteBatch.draw(next, H() + S0(), I() - (8 * this.size), next.getRegionWidth() * this.size, next.getRegionHeight() * this.size);
            }
        }
        if (z2 || i1()) {
            spriteBatch.setColor(m0.a.g());
        }
    }

    public final boolean F0() {
        return (d0().equals(MapActor.ActorState.PARALIZED) || i0() || GameData.v().u() <= this.lastItemUsed + 4.0f) ? false : true;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 140;
    }

    public final boolean G0(int i2, String str) {
        if (g0() || i0() || j0()) {
            return false;
        }
        if (str.toLowerCase(Locale.ENGLISH).contains("arbe")) {
            str = "Might";
        }
        String str2 = str;
        this.actionStartTime = GameLevel.b();
        W();
        q0(MapActor.ActorState.ACTING);
        this.stateRelativeTime = 0.0f;
        float f2 = i2;
        this.actionDuration = f2;
        GameAssets.o("buff2");
        k0.a aVarL = k0.a.l();
        a.EnumC0031a enumC0031a = a.EnumC0031a.f2303o;
        aVarL.getClass();
        aVarL.b(B(), enumC0031a, f2).owner = this;
        k0.a.l().e(0.6f, this.uniqueID, 64, "aura_blue");
        k0.a.l().a(new w(this.uniqueID, str2, 1.0f, Color.BLUE, 1.2f, 0.7f));
        return true;
    }

    public final Path H0(Path path) {
        if (path == null || path.e() <= 0) {
            return null;
        }
        path.i();
        for (int i2 = 0; i2 < path.e() / 3; i2++) {
            for (int i3 = 0; i3 < path.e() - 2; i3++) {
                int i4 = i3 + 2;
                if (b.P().D(path.f(i3).f2989x, path.f(i3).f2990y, path.f(i4).f2989x, path.f(i4).f2990y, this.uniqueID, 0)) {
                    path.h(i3 + 1);
                }
            }
        }
        return path;
    }

    public final void I0(int i2) {
        CharacterEffects characterEffects = this.sheet.effects;
        characterEffects.disintegrate = Boolean.TRUE;
        characterEffects.disintegrateBonus = i2;
        m(4.0f, this.uniqueID, "UNDISINTEGRATE");
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void J(int i2) {
        C0();
        R(GameLevel.i(i2).B());
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final boolean K() {
        return true;
    }

    public final void K0(float f2) {
        this.sheet.effects.flameAura = true;
        k0.a aVarL = k0.a.l();
        a.EnumC0031a enumC0031a = a.EnumC0031a.f2302n;
        aVarL.getClass();
        aVarL.b(B(), enumC0031a, f2).owner = this;
        k0.a.l().e(f2, this.uniqueID, 0, "fire");
        m(f2, this.uniqueID, "UNFLAMEAURA");
    }

    public abstract boolean L0(Character character);

    @Override // net.fdgames.GameEntities.MapActor, net.fdgames.GameEntities.MapSprite
    public void M(float f2) {
        int i2 = this.uniqueID;
        MapActor.ActorState actorState = MapActor.ActorState.PARALIZED;
        MapActor.ActorState actorState2 = MapActor.ActorState.PUSHED;
        MapActor.ActorState actorState3 = MapActor.ActorState.IDLE;
        if (i2 == 1 && d0() == actorState2 && !i0() && GameData.v().u() > this.pushmaxtime) {
            if (this.sheet.effects.stunned.booleanValue()) {
                q0(actorState);
            } else {
                q0(actorState3);
            }
            C0();
        }
        this.sheet.skillSet.t(f2);
        SkillSet skillSet = this.sheet.skillSet;
        if (((skillSet == null || skillSet.d()) ? false : true) && this.lastSkillEffectCheck + 1.0f < GameData.v().u()) {
            this.lastSkillEffectCheck = GameData.v().u();
            if (this.sheet.effects.duelbonus > 0 && Math.abs(this.SkillUseData_consecutive_lasthit - GameData.v().u()) > 2.0f) {
                CharacterEffects characterEffects = this.sheet.effects;
                characterEffects.duel = Boolean.FALSE;
                characterEffects.duelbonus = 0;
            }
            if (Math.abs(this.SkillUseData_last_melee_kill - GameData.v().u()) > 6.0f) {
                CharacterEffects characterEffects2 = this.sheet.effects;
                characterEffects2.bloodlust = Boolean.FALSE;
                characterEffects2.bloodlustArmorBonus = 0;
                characterEffects2.bloodlustBonus = 0;
            }
        }
        if (d0() != actorState2 && this.sheet.effects.stunned.booleanValue() && d0() != actorState) {
            q0(actorState);
            W();
        }
        if (g0()) {
            int iOrdinal = d0().ordinal();
            if (iOrdinal == 2) {
                float fB = GameLevel.b() - this.actionStartTime;
                int iH = this.sheet.H();
                Item[] itemArr = Rules.f3247a;
                if (fB >= 10.0f / iH) {
                    q0(actorState3);
                    if (this.uniqueID == 1 && z.x() && l0()) {
                        GameData.v().player.getClass();
                        z.f2926j0 = false;
                        z.v().Z();
                        z.v().H();
                    }
                    t0();
                    if (m1() && !j0() && !i0()) {
                        this.actionStartTime = 0.0f;
                        E0(-1);
                    }
                }
            } else if (iOrdinal != 4) {
                if (iOrdinal == 10) {
                    float fB2 = GameLevel.b() - this.actionStartTime;
                    int iH2 = this.sheet.H();
                    Item[] itemArr2 = Rules.f3247a;
                    if (fB2 >= 10.0f / iH2) {
                        q0(actorState3);
                        if (this.uniqueID == 1 && z.x() && l0()) {
                            GameData.v().player.getClass();
                            z.f2926j0 = false;
                            z.v().Z();
                            z.v().H();
                        }
                        t0();
                        if (m1()) {
                            this.actionStartTime = 0.0f;
                            E0(-1);
                        }
                    }
                } else if (iOrdinal == 6) {
                    float fB3 = GameLevel.b() - this.actionStartTime;
                    Item[] itemArr3 = Rules.f3247a;
                    if (fB3 >= 10.0f / 14) {
                        q0(actorState3);
                        t0();
                    }
                } else if (iOrdinal == 7 && GameLevel.b() - this.actionStartTime >= 0.4f) {
                    q0(actorState3);
                    this.speedX = 0.0f;
                    this.speedY = 0.0f;
                    t0();
                }
            } else if (GameLevel.b() - this.actionStartTime >= this.actionDuration) {
                q0(actorState3);
                t0();
            }
        } else {
            Path path = this.path;
            if (path == null) {
                Coords coords = this.destination;
                if (coords.f3287x != -1 && coords.f3288y != -1) {
                    x0();
                    if (B0()) {
                        Coords coords2 = this.destination;
                        coords2.f3287x = -1;
                        coords2.f3288y = -1;
                        if (d0() == actorState2 || d0() == MapActor.ActorState.SKILL_CHARGE) {
                            q0(actorState3);
                        }
                    }
                }
            } else if (path.e() == 0 || this.path.f(0).a()) {
                this.path.c();
                Coords coords3 = this.destination;
                coords3.f3287x = -1;
                coords3.f3288y = -1;
            } else {
                p0(this.path.d());
                Coords coords4 = this.destination;
                if (coords4 == null) {
                    coords4.f3287x = -1;
                    coords4.f3288y = -1;
                }
                x0();
                if (B0()) {
                    Path path2 = this.path;
                    Coords coords5 = this.destination;
                    path2.a(coords5.f3287x, coords5.f3288y);
                }
            }
        }
        super.M(f2);
    }

    public final void M0() {
        this.sheet.f();
        GameConsole.a(this.name + ":" + GameString.b("SKILL_POINT_GAINED", false));
        GameData.v().log.a(this.name + ":" + GameString.b("SKILL_POINT_GAINED", false));
        k0.a.l().a(new w(this.uniqueID, GameString.b("SKILL_POINT_GAINED", false), 1.0f, Color.BLUE, 1.0f, 0.7f));
        k0.a.l().b(B(), a.EnumC0031a.f2291c, 0.0f);
        GameAssets.o("levelup");
    }

    public final void N0() {
        this.sheet.g();
        GameConsole.a(this.name + ":" + GameString.b("TRAIT_POINT_GAINED", false));
        GameData.v().log.a(this.name + ":" + GameString.b("TRAIT_POINT_GAINED", false));
        k0.a.l().a(new w(this.uniqueID, GameString.b("TRAIT_POINT_GAINED", false), 1.0f, Color.BLUE, 1.0f, 0.7f));
        k0.a.l().b(B(), a.EnumC0031a.f2291c, 0.0f);
        GameAssets.o("levelup");
    }

    public abstract void O0(int i2);

    public abstract float P0();

    protected abstract float Q0();

    /* JADX WARN: Removed duplicated region for block: B:103:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x041c  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x049c  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x05e8  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0641  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x065e  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x067a  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0688  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x069b  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x06a0  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x06ed  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x074f  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0764  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x0780  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x07ba A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:410:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x083e  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x085f  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:575:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0150  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void R0(Damage damage, int i2, boolean z2, int i3) {
        boolean z3;
        Color color;
        String str;
        Rules.CharacterRace characterRace;
        boolean z4;
        String strO;
        boolean z5;
        float f2;
        CharacterEffects characterEffects;
        String str2;
        Item item;
        Damage.DamageType damageType;
        String str3;
        String str4;
        int iG0;
        MapSprite mapSprite;
        float f3;
        boolean z6;
        String strO2;
        float f4;
        int iOrdinal;
        Item item2;
        WeaponStats weaponStats;
        MapSprite mapSprite2;
        int iG;
        int i4;
        int i5;
        int i6;
        int i7;
        NPC npcJ;
        int i8;
        int i9;
        String strC;
        boolean zD;
        boolean z7;
        String strN;
        String strO3;
        WeaponStats weaponStats2;
        int iG2;
        float f5;
        boolean z8;
        int i10;
        Character character;
        int iG3;
        int i11;
        int i12;
        float f6;
        int i13;
        int i14;
        int i15;
        float f7;
        float f8;
        int iG4;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        boolean z9;
        boolean z10;
        String strO4;
        String str5;
        boolean z11;
        CharacterSheet characterSheet;
        int i21;
        float f9;
        float f10;
        SkillSet skillSet;
        CharacterSheet characterSheet2;
        SkillSet skillSet2;
        float f11;
        int i22;
        boolean z12;
        if (this.sheet.d()) {
            if (i0()) {
                return;
            }
            X();
            return;
        }
        CharacterSheet characterSheet3 = this.sheet;
        Damage.DamageType damageType2 = damage.type;
        characterSheet3.getClass();
        int iOrdinal2 = damageType2.ordinal();
        if (iOrdinal2 != 1 ? iOrdinal2 != 2 ? iOrdinal2 != 3 ? iOrdinal2 != 4 ? false : characterSheet3.P("ghost") : characterSheet3.P("shock_elemental") : characterSheet3.P("ice_elemental") : characterSheet3.P("fire_elemental")) {
            V0(damage.hp);
            return;
        }
        if (z2) {
            CharacterSheet characterSheet4 = this.sheet;
            if (characterSheet4.inventory != null && characterSheet4.Z() && this.sheet.inventory.j().b()) {
                int i23 = this.sheet.skillSet.g("infantry_training") == 1 ? 35 : 0;
                if (this.sheet.skillSet.g("infantry_training") == 2) {
                    i23 = 50;
                }
                if (FDUtils.b(1, 100) < i23) {
                    z12 = true;
                }
                if (z12) {
                }
            } else {
                z12 = false;
                if (z12) {
                    z3 = z2;
                } else {
                    damage.hp /= 2;
                    k0.a.l().a(new w(this.uniqueID, a.a.o("CRITICAL_NEGATED", false, new StringBuilder("[ROYAL]("), ")[]"), 0.6f, Color.WHITE, 1.0f, 0.6f));
                    z3 = false;
                }
            }
        }
        switch (damage.type.ordinal()) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.ORANGE;
                break;
            case 2:
                color = Color.WHITE;
                break;
            case 3:
                color = Color.CYAN;
                break;
            case 4:
                color = Color.BLACK;
                break;
            case 5:
                color = Color.GREEN;
                break;
            case 6:
                color = Color.YELLOW;
                break;
            default:
                color = Color.RED;
                break;
        }
        Color color2 = color;
        MapSprite mapSpriteI = i2 > 0 ? GameLevel.i(i2) : null;
        Item itemF = i3 > 0 ? Rules.f(i3) : null;
        Damage.DamageType damageType3 = damage.type;
        Damage.DamageType damageType4 = Damage.DamageType.Normal;
        float f12 = 1.25f;
        if (damageType3 != damageType4) {
            str = "";
        } else if (mapSpriteI != null && mapSpriteI.K() && (mapSpriteI instanceof Character) && L0((Character) mapSpriteI)) {
            Character characterF = GameLevel.f(i2);
            if (characterF.sheet.N().ranged) {
                f10 = 1.0f;
                f9 = 1.0f;
            } else {
                CharacterSheet characterSheet5 = characterF.sheet;
                if (characterSheet5 == null || (skillSet = characterSheet5.skillSet) == null) {
                    characterF.sheet.effects.stealth.getClass();
                    f9 = 1.25f;
                    f10 = 1.0f;
                } else {
                    int iG5 = skillSet.g("sneak_attack");
                    if (iG5 == 1) {
                        f9 = 1.5f;
                    } else if (iG5 == 2) {
                        f9 = 1.7f;
                    } else if (iG5 == 3) {
                        f9 = 2.0f;
                    }
                    f10 = 1.0f;
                }
            }
            if (f9 > f10) {
                strO4 = a.a.o("FLANKED", false, new StringBuilder("[ROYAL]("), ")[]");
                damage.hp = (int) (damage.hp * f9);
                Character characterF2 = GameLevel.f(i2);
                if (characterF2 != null && (characterSheet2 = characterF2.sheet) != null && (skillSet2 = characterSheet2.skillSet) != null) {
                    int iG6 = skillSet2.g("sneak_attack");
                    if (iG6 == 2) {
                        i22 = 100;
                        if (FDUtils.b(1, 100) < 15) {
                            f11 = 1.0f;
                            z1(1.0f);
                        } else {
                            f11 = 1.0f;
                        }
                    } else {
                        f11 = 1.0f;
                        i22 = 100;
                    }
                    if (iG6 == 3) {
                        if (FDUtils.b(1, i22) < 25) {
                            z1(f11);
                        }
                    }
                }
            }
            if (itemF == null) {
            }
        } else {
            strO4 = "";
            if (itemF == null) {
                ItemAttributes itemAttributes = itemF.attributes;
                if (itemAttributes.hasAttributes) {
                    int i24 = damage.hp;
                    CharacterSheet characterSheet6 = this.sheet;
                    if (itemAttributes.orc_slayer <= 0 || !characterSheet6.P("orc")) {
                        str5 = strO4;
                        i21 = 0;
                    } else {
                        i21 = itemAttributes.orc_slayer * 2;
                        str5 = strO4;
                    }
                    if (itemAttributes.holy > 0 && characterSheet6.P("undead")) {
                        i21 += itemAttributes.holy * 2;
                    }
                    if (itemAttributes.banishing > 0 && characterSheet6.P("outsider")) {
                        i21 += itemAttributes.banishing * 2;
                    }
                    if (itemAttributes.beast_slayer > 0 && characterSheet6.P("beast")) {
                        i21 += itemAttributes.beast_slayer * 2;
                    }
                    if (itemAttributes.vicious > 0 && characterSheet6.r() > 0.7f) {
                        i21 += itemAttributes.vicious * 2;
                    }
                    damage.hp = i24 + i21;
                } else {
                    str5 = strO4;
                }
                if (this.sheet.attributes.beast && z0() > 0) {
                    damage.hp = z0() + 1 + damage.hp;
                }
                if (this.sheet.attributes.shapechanger) {
                    if (mapSpriteI == null || !(mapSpriteI instanceof Character) || (characterSheet = ((Character) mapSpriteI).sheet) == null || !characterSheet.l().silver) {
                        if (i3 > 0 && itemF != null) {
                            ItemAttributes itemAttributes2 = itemF.attributes;
                            if (itemAttributes2.hasAttributes && itemAttributes2.silver > 0) {
                                z11 = false;
                            }
                            if (z11) {
                                damage.hp /= 4;
                            }
                        }
                        z11 = true;
                        if (z11) {
                        }
                    }
                }
                str = str5;
            }
        }
        if (this.sheet.effects.evasion.booleanValue()) {
            int iG7 = this.sheet.skillSet.g("evasion");
            if (iG7 != 0) {
                int i25 = iG7 == 1 ? 50 : 0;
                if (iG7 == 2) {
                    i25 = 70;
                }
                if (iG7 == 3) {
                    i25 = 90;
                }
                if (FDUtils.b(1, 100) <= i25) {
                    k0.a.l().a(new w(this.uniqueID, GameString.b("DODGED", false), 1.0f, Color.YELLOW, 1.5f, 0.7f));
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (z10) {
                    damage.hp = 0;
                }
            }
        }
        Rules.CharacterRace characterRace2 = Rules.CharacterRace.BOSS;
        if (z3 && mapSpriteI != null && (mapSpriteI instanceof Character)) {
            Character character2 = (Character) mapSpriteI;
            z4 = z3;
            if (this.uniqueID == 1 || this.sheet.o() > 100 || character2.sheet.skillSet.bonusSet.critDamageModifier <= 150 || this.sheet.stats.f() + 1 >= character2.sheet.stats.f() || this.sheet.stats.characterRace.equals(characterRace2) || this.sheet.stats.characterRace.equals(Rules.CharacterRace.MINIBOSS) || (iG4 = character2.sheet.skillSet.g("massive_criticals")) == 0) {
                characterRace = characterRace2;
            } else {
                if (iG4 == 1) {
                    i16 = 2;
                    i17 = 10;
                } else {
                    i16 = 2;
                    i17 = 0;
                }
                if (iG4 == i16) {
                    i18 = 3;
                    i17 = 25;
                } else {
                    i18 = 3;
                }
                characterRace = characterRace2;
                if (iG4 == i18) {
                    i19 = 1;
                    i20 = 100;
                    i17 = 50;
                } else {
                    i19 = 1;
                    i20 = 100;
                }
                z9 = FDUtils.b(i19, i20) <= i17;
                if (!z9) {
                    damage.hp = this.sheet.j() + this.sheet.o();
                    strO = a.a.o("MASSIVE_CRIT", false, new StringBuilder("[ROYAL]"), "! []");
                    f2 = 0.3f;
                    z5 = false;
                }
                characterEffects = this.sheet.effects;
                String str6 = strO;
                float f13 = f2;
                if (characterEffects.mageArmor_Charges <= 0 || !(mapSpriteI instanceof Trap)) {
                    str2 = str;
                    item = itemF;
                } else {
                    damage.hp = 0;
                    characterEffects.mageArmor_Charges = 0;
                    characterEffects.mageArmorBonus = 0;
                    characterEffects.mageArmorElementalBonus = 0;
                    str2 = str;
                    item = itemF;
                    k0.a.l().a(new w(this.uniqueID, "[ROYAL](" + Skills.c("mage_armor").d() + ")[]", 0.6f, Color.WHITE, 1.0f, 0.6f));
                    GameData.v().log.a("[YELLOW]" + GameString.b("LOG_MAGE_ARMOR_TRAP", false) + "[]");
                }
                CharacterSheet characterSheet7 = this.sheet;
                characterSheet7.getClass();
                damageType = damage.type;
                if (damageType == damageType4) {
                    int iJ = characterSheet7.j();
                    if (damage.projectile) {
                        iJ += characterSheet7.k();
                    }
                    AttributesSet attributesSet = damage.attackerAttributes;
                    if (attributesSet != null && attributesSet.a("undead")) {
                        iJ += characterSheet7.skillSet.g("nivarias_barrier");
                    }
                    if (characterSheet7.Z()) {
                        f7 = iJ;
                        f8 = 0.4f;
                    } else {
                        f7 = iJ;
                        f8 = 0.2f;
                    }
                    int iB = FDUtils.b((int) (f7 * f8), iJ);
                    str3 = ")[]";
                    str4 = "[]";
                    iG0 = (int) Math.ceil(damage.hp * 0.15f);
                    int i26 = damage.hp - iB;
                    if (i26 >= iG0) {
                        iG0 = i26;
                    }
                } else {
                    str3 = ")[]";
                    str4 = "[]";
                    iG0 = CharacterSheet.g0(damage.hp, characterSheet7.G(damageType));
                    if (damage.projectile) {
                        iG0 -= FDUtils.b(characterSheet7.k() / 3, characterSheet7.k());
                    }
                }
                characterSheet7.stats.missingHP += iG0;
                SkillActions.d(this);
                if (this.sheet.effects.mageArmor_Charges > 0) {
                    if ((mapSpriteI instanceof Character) && (character = (Character) mapSpriteI) != null && !character.i0()) {
                        if (character.uniqueID == this.uniqueID || character.sheet.N().ranged || character.wasJustHitByMageBarrier || (iG3 = this.sheet.skillSet.g("mage_barrier")) == 0) {
                            mapSprite = mapSpriteI;
                            z8 = false;
                            f3 = 1.0f;
                        } else {
                            if (iG3 == 1) {
                                i12 = 12;
                                i11 = 2;
                                f6 = 32.0f;
                            } else {
                                i11 = 2;
                                i12 = 0;
                                f6 = 0.0f;
                            }
                            if (iG3 == i11) {
                                f6 = 48.0f;
                                i13 = 3;
                                i12 = 25;
                                i14 = 10;
                            } else {
                                i13 = 3;
                                i14 = 0;
                            }
                            if (iG3 == i13) {
                                f6 = 64.0f;
                                i12 = 40;
                                i15 = 30;
                            } else {
                                i15 = i14;
                            }
                            mapSprite = mapSpriteI;
                            if (i15 <= 0 || FDUtils.b(1, 100) >= i15) {
                                f3 = 1.0f;
                            } else {
                                f3 = 1.0f;
                                character.z1(1.0f);
                            }
                            if (f6 > 0.0f) {
                                character.n0(f6, B());
                            }
                            if (i12 > 0) {
                                n(character.uniqueID, new DamageData(Damage.DamageType.Shock, i12, false));
                                character.wasJustHitByMageBarrier = true;
                                z8 = true;
                            }
                        }
                        if (!z8 || damage.type == damageType4) {
                            CharacterEffects characterEffects2 = this.sheet.effects;
                            i10 = characterEffects2.mageArmor_Charges - 1;
                            characterEffects2.mageArmor_Charges = i10;
                            if (i10 != 0) {
                                z6 = false;
                                characterEffects2.mageArmorBonus = 0;
                                characterEffects2.mageArmorElementalBonus = 0;
                            }
                        }
                        if (z5) {
                            strO2 = a.a.o("CRIT", z6, new StringBuilder("[ROYAL]"), "! []");
                            f4 = 0.3f;
                        } else {
                            f12 = f3;
                            strO2 = str6;
                            f4 = f13;
                        }
                        if (iG0 > 0) {
                            GameAssets.o("hit");
                        }
                        if (iG0 > 0 || damage.type == damageType4) {
                            iOrdinal = damage.type.ordinal();
                            if (iOrdinal == 0) {
                                if (iOrdinal == 1) {
                                    k0.a.l().b(B(), a.EnumC0031a.f2292d, 0.0f);
                                } else if (iOrdinal == 2) {
                                    k0.a.l().b(B(), a.EnumC0031a.f2293e, 0.0f);
                                } else if (iOrdinal == 3) {
                                    k0.a.l().c(B(), "shock", 0.8f);
                                } else if (iOrdinal == 4) {
                                    k0.a.l().b(B(), a.EnumC0031a.f2294f, 0.0f);
                                } else if (iOrdinal == 5) {
                                    k0.a.l().b(B(), a.EnumC0031a.f2295g, 0.0f);
                                }
                                item2 = item;
                            } else {
                                item2 = item;
                                if (item == null || (weaponStats = item2.weaponStats) == null || !weaponStats.c()) {
                                    k0.a.l().b(B(), a.EnumC0031a.f2289a, 0.0f);
                                } else {
                                    k0.a.l().b(B(), a.EnumC0031a.f2304p, 0.0f);
                                }
                            }
                            k0.a aVarL = k0.a.l();
                            int i27 = this.uniqueID;
                            StringBuilder sbS = a.a.s(strO2);
                            sbS.append(Integer.toString(iG0));
                            sbS.append(str2);
                            float f14 = f4 + 0.6f;
                            mapSprite2 = mapSprite;
                            aVarL.a(new w(i27, sbS.toString(), f14, color2, f12, 0.6f));
                        } else {
                            mapSprite2 = mapSprite;
                            item2 = item;
                        }
                        if (item2 != null && (weaponStats2 = item2.weaponStats) != null && mapSprite2 != null && weaponStats2.wand && (mapSprite2 instanceof Character)) {
                            iG2 = ((Character) mapSprite2).sheet.skillSet.g("wand_mastery");
                            if (iG2 <= 0) {
                                f5 = 0.5f;
                                n0(f5 * 32.0f, mapSprite2.B());
                            } else {
                                if (iG2 == 1) {
                                    f5 = f3;
                                } else if (iG2 == 2) {
                                    f5 = 1.4f;
                                } else if (iG2 == 3) {
                                    f5 = 1.6f;
                                } else if (iG2 == 4) {
                                    f5 = 1.8f;
                                }
                                n0(f5 * 32.0f, mapSprite2.B());
                            }
                        }
                        if (iG0 > 0 && (Settings.o() || (mapSprite2 != null && (mapSprite2 instanceof Trap)))) {
                            strC = mapSprite2 == null ? mapSprite2.C() : "";
                            GameLog gameLog = GameData.v().log;
                            String strD = D();
                            zD = this.sheet.d();
                            boolean z13 = !(this instanceof NPC) && GameData.v().party.m(((NPC) this).spawn_id);
                            gameLog.getClass();
                            if (zD) {
                                z7 = false;
                                strN = "";
                            } else if (z13) {
                                z7 = false;
                                strN = a.a.n("INCAPACITATED", false, a.a.u(". ", strD, " "));
                            } else {
                                z7 = false;
                                strN = a.a.n("DIED", false, a.a.u(". ", strD, " "));
                            }
                            String strB = GameString.b("HITS", z7);
                            if (strC.contains(GameString.b("YOU", z7))) {
                                strB = GameString.b("HIT", z7);
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append(strC);
                            sb.append(" ");
                            sb.append(strB);
                            sb.append(" ");
                            sb.append(strD);
                            sb.append(",");
                            sb.append(damage.hp);
                            sb.append("-");
                            sb.append(damage.hp - iG0);
                            sb.append("=[]");
                            String str7 = "" + iG0;
                            switch (damage.type.ordinal()) {
                                case 1:
                                    strO3 = a.a.o("FIRE", false, a.a.u("[ORANGE]", str7, " ("), str3);
                                    break;
                                case 2:
                                    strO3 = a.a.o("COLD", false, a.a.u("[WHITE]", str7, " ("), str3);
                                    break;
                                case 3:
                                    strO3 = a.a.o("SHOCK", false, a.a.u("[CYAN]", str7, " ("), str3);
                                    break;
                                case 4:
                                    strO3 = a.a.o("DEATH", false, a.a.u("[GRAY]", str7, " ("), str3);
                                    break;
                                case 5:
                                    strO3 = a.a.o("TOXIC", false, a.a.u("[GREEN]", str7, " ("), str3);
                                    break;
                                case 6:
                                    strO3 = a.a.o("SPIRIT", false, a.a.u("[YELLOW]", str7, " ("), str3);
                                    break;
                                default:
                                    strO3 = a.a.l("[RED]", str7, " []");
                                    break;
                            }
                            sb.append(strO3);
                            sb.append(strN);
                            gameLog.a(sb.toString());
                        }
                        if (i2 > 0 && mapSprite2 != null) {
                            U(i2);
                        }
                        if (GameData.v().party.j()) {
                            int i28 = this.uniqueID;
                            MapActor.ActorState actorState = MapActor.ActorState.MOVING;
                            MapActor.ActorState actorState2 = MapActor.ActorState.IDLE;
                            if (i28 != 1 || i2 == 1) {
                                i8 = 1;
                            } else {
                                if (mapSprite2 != null && (mapSprite2 instanceof Character)) {
                                    NPC npcF = GameData.v().party.f();
                                    if (npcF.detectedEnemyID == 0 && ((npcF.d0() == actorState2 || npcF.d0() == actorState) && ((i9 = npcF.attackStrategy) == 0 || i9 == 2))) {
                                        npcF.U(i2);
                                    }
                                }
                                i8 = 1;
                            }
                            if (i2 == i8 && this.uniqueID != i8) {
                                NPC npcF2 = GameData.v().party.f();
                                int i29 = this.uniqueID;
                                if (npcF2.detectedEnemyID == 0 && ((npcF2.d0() == actorState2 || npcF2.d0() == actorState) && npcF2.attackStrategy == 0)) {
                                    npcF2.U(i29);
                                }
                            }
                        }
                        if (GameData.v().party.k().booleanValue() && this.uniqueID == 1 && i2 != 1 && mapSprite2 != null && (mapSprite2 instanceof Character)) {
                            Party party = GameData.v().party;
                            int i30 = mapSprite2.uniqueID;
                            for (Follower follower : party.followers) {
                                if (follower.a().tag.contains("summon") && (npcJ = GameLevel.j(follower.a().tag)) != null) {
                                    npcJ.U(i30);
                                }
                            }
                        }
                        if (this.sheet.d()) {
                            this.sheet.effects = new CharacterEffects();
                            if (h1() && this.sheet.skillSet.g("intervention") > 0 && this.sheet.p() > 0) {
                                int iG8 = this.sheet.skillSet.g("intervention");
                                int iP = this.sheet.p();
                                this.sheet.j0(iP);
                                int i31 = iG8 == 1 ? iP * 2 : 0;
                                if (iG8 == 2) {
                                    i31 = iP * 4;
                                }
                                if (iG8 == 3) {
                                    i31 = iP * 8;
                                }
                                V0(i31);
                                k0.a.l().a(new w(this.uniqueID, Skills.c("intervention").d() + "!", 1.0f, Color.YELLOW, 1.5f, 0.7f));
                                if (!this.sheet.d()) {
                                    return;
                                }
                            }
                            if (this.uniqueID == 1 && GameData.v().backpack.l(2613) > 0) {
                                GameData.v().backpack.q(2613);
                                V0(this.sheet.stats.missingHP);
                                k0.a.l().a(new w(this.uniqueID, GameString.b("ANKH_CONSUMED", false), 1.0f, Color.YELLOW, 1.5f, 0.7f));
                                GameConsole.a("[YELLOW]" + GameString.b("ANKH_CONSUMED", false) + str4);
                                return;
                            }
                            boolean z14 = this.sheet.P("immortal") || (this.sheet.P("regeneration") && !this.receivedDamage.b());
                            C0();
                            if ((!(this instanceof NPC) || !GameData.v().party.m(((NPC) this).spawn_id)) && !z14) {
                                if (i2 == 1 || (((NPC) GameLevel.g(i2)) != null && ((NPC) GameLevel.g(i2)).k0())) {
                                    this.killedByNPC = false;
                                    Character character3 = (Character) mapSprite2;
                                    int i32 = this.uniqueID;
                                    if (character3.sheet != null && !character3.destroy && !character3.i0() && !character3.sheet.skillSet.k("stab") && character3.SkillUseData_lastStabbed_id == i32 && Math.abs(character3.SkillUseData_lastStabTime - GameData.v().u()) < 0.5d && (iG = character3.sheet.skillSet.g("assassinate")) != 0) {
                                        if (iG == 1) {
                                            i5 = 2;
                                            i4 = 30;
                                        } else {
                                            i4 = 0;
                                            i5 = 2;
                                        }
                                        if (iG == i5) {
                                            i7 = 3;
                                            i6 = 50;
                                        } else {
                                            i6 = i4;
                                            i7 = 3;
                                        }
                                        if (iG == i7) {
                                            i6 = 75;
                                        }
                                        if (FDUtils.b(1, 100) <= i6) {
                                            character3.sheet.skillSet.o();
                                            k0.a.l().a(new w(character3.uniqueID, Skills.c("assassinate").d() + "!", 1.0f, Color.YELLOW, 1.5f, 0.7f));
                                        }
                                    }
                                } else {
                                    this.killedByNPC = true;
                                }
                                if (mapSprite2 != null && (mapSprite2 instanceof Character)) {
                                    Character character4 = (Character) mapSprite2;
                                    if (!character4.sheet.N().ranged) {
                                        character4.SkillUseData_last_melee_kill = GameData.v().u();
                                        int iG9 = character4.sheet.skillSet.g("bloodlust");
                                        if (iG9 > 0) {
                                            CharacterEffects characterEffects3 = character4.sheet.effects;
                                            characterEffects3.bloodlust = Boolean.TRUE;
                                            if (iG9 == 1) {
                                                characterEffects3.bloodlustBonus = 3;
                                                characterEffects3.bloodlustArmorBonus = 2;
                                            } else if (iG9 == 2) {
                                                characterEffects3.bloodlustBonus = 4;
                                                characterEffects3.bloodlustArmorBonus = 3;
                                            } else if (iG9 == 3) {
                                                characterEffects3.bloodlustBonus = 6;
                                                characterEffects3.bloodlustArmorBonus = 4;
                                            }
                                        }
                                    }
                                }
                                float f15 = this.sheet.stats.characterRace.equals(characterRace) ? 20.0f : 10.0f;
                                if (this.sheet.P("explosive")) {
                                    int i33 = this.uniqueID;
                                    MessageRouter.a("EXPLODE", i33, i33, null, 0.7f, null);
                                    f15 = 0.72f;
                                }
                                int i34 = this.uniqueID;
                                MessageRouter.a("DESTROY", i34, i34, null, f15, null);
                            }
                            X();
                            return;
                        }
                        return;
                    }
                    mapSprite = mapSpriteI;
                    f3 = 1.0f;
                    z8 = false;
                    if (!z8) {
                        CharacterEffects characterEffects22 = this.sheet.effects;
                        i10 = characterEffects22.mageArmor_Charges - 1;
                        characterEffects22.mageArmor_Charges = i10;
                        if (i10 != 0) {
                        }
                    }
                    if (z5) {
                    }
                    if (iG0 > 0) {
                    }
                    if (iG0 > 0) {
                        iOrdinal = damage.type.ordinal();
                        if (iOrdinal == 0) {
                        }
                        k0.a aVarL2 = k0.a.l();
                        int i272 = this.uniqueID;
                        StringBuilder sbS2 = a.a.s(strO2);
                        sbS2.append(Integer.toString(iG0));
                        sbS2.append(str2);
                        float f142 = f4 + 0.6f;
                        mapSprite2 = mapSprite;
                        aVarL2.a(new w(i272, sbS2.toString(), f142, color2, f12, 0.6f));
                    }
                    if (item2 != null) {
                        iG2 = ((Character) mapSprite2).sheet.skillSet.g("wand_mastery");
                        if (iG2 <= 0) {
                        }
                    }
                    if (iG0 > 0) {
                        if (mapSprite2 == null) {
                        }
                        GameLog gameLog2 = GameData.v().log;
                        String strD2 = D();
                        zD = this.sheet.d();
                        if (this instanceof NPC) {
                            gameLog2.getClass();
                            if (zD) {
                            }
                            String strB2 = GameString.b("HITS", z7);
                            if (strC.contains(GameString.b("YOU", z7))) {
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(strC);
                            sb2.append(" ");
                            sb2.append(strB2);
                            sb2.append(" ");
                            sb2.append(strD2);
                            sb2.append(",");
                            sb2.append(damage.hp);
                            sb2.append("-");
                            sb2.append(damage.hp - iG0);
                            sb2.append("=[]");
                            String str72 = "" + iG0;
                            switch (damage.type.ordinal()) {
                            }
                            sb2.append(strO3);
                            sb2.append(strN);
                            gameLog2.a(sb2.toString());
                        }
                    }
                    if (i2 > 0) {
                        U(i2);
                    }
                    if (GameData.v().party.j()) {
                    }
                    if (GameData.v().party.k().booleanValue()) {
                        Party party2 = GameData.v().party;
                        int i302 = mapSprite2.uniqueID;
                        while (r2.hasNext()) {
                        }
                    }
                    if (this.sheet.d()) {
                    }
                } else {
                    mapSprite = mapSpriteI;
                    f3 = 1.0f;
                }
                z6 = false;
                if (z5) {
                }
                if (iG0 > 0) {
                }
                if (iG0 > 0) {
                }
                if (item2 != null) {
                }
                if (iG0 > 0) {
                }
                if (i2 > 0) {
                }
                if (GameData.v().party.j()) {
                }
                if (GameData.v().party.k().booleanValue()) {
                }
                if (this.sheet.d()) {
                }
            }
            if (!z9) {
            }
            characterEffects = this.sheet.effects;
            String str62 = strO;
            float f132 = f2;
            if (characterEffects.mageArmor_Charges <= 0) {
                str2 = str;
                item = itemF;
            }
            CharacterSheet characterSheet72 = this.sheet;
            characterSheet72.getClass();
            damageType = damage.type;
            if (damageType == damageType4) {
            }
            characterSheet72.stats.missingHP += iG0;
            SkillActions.d(this);
            if (this.sheet.effects.mageArmor_Charges > 0) {
            }
            z6 = false;
            if (z5) {
            }
            if (iG0 > 0) {
            }
            if (iG0 > 0) {
            }
            if (item2 != null) {
            }
            if (iG0 > 0) {
            }
            if (i2 > 0) {
            }
            if (GameData.v().party.j()) {
            }
            if (GameData.v().party.k().booleanValue()) {
            }
            if (this.sheet.d()) {
            }
        } else {
            characterRace = characterRace2;
            z4 = z3;
        }
        strO = "";
        z5 = z4;
        f2 = 0.0f;
        characterEffects = this.sheet.effects;
        String str622 = strO;
        float f1322 = f2;
        if (characterEffects.mageArmor_Charges <= 0) {
        }
        CharacterSheet characterSheet722 = this.sheet;
        characterSheet722.getClass();
        damageType = damage.type;
        if (damageType == damageType4) {
        }
        characterSheet722.stats.missingHP += iG0;
        SkillActions.d(this);
        if (this.sheet.effects.mageArmor_Charges > 0) {
        }
        z6 = false;
        if (z5) {
        }
        if (iG0 > 0) {
        }
        if (iG0 > 0) {
        }
        if (item2 != null) {
        }
        if (iG0 > 0) {
        }
        if (i2 > 0) {
        }
        if (GameData.v().party.j()) {
        }
        if (GameData.v().party.k().booleanValue()) {
        }
        if (this.sheet.d()) {
        }
    }

    public final void T0() {
        float f2 = this.size;
        if (f2 < 2.0f) {
            this.size = f2 + 0.1f;
        }
    }

    public final boolean U0() {
        return this.sheet.N().ranged;
    }

    public final void V0(int i2) {
        int iMin;
        if (d0() == MapActor.ActorState.DEAD || this.destroy) {
            return;
        }
        if (i2 != 1000 && (iMin = Math.min(this.sheet.stats.missingHP, i2)) > 0) {
            k0.a.l().a(new w(this.uniqueID, "+" + Integer.toString(iMin) + "hp", 0.4f, Color.GREEN, 1.0f, 1.0f));
            k0.a.l().b(B(), a.EnumC0031a.f2290b, 0.0f);
            GameAssets.o("heal");
        }
        this.sheet.R(i2);
        SkillActions.d(this);
    }

    public final void W0(int i2) {
        CharacterEffects characterEffects = this.sheet.effects;
        characterEffects.holy_shielded = Boolean.TRUE;
        characterEffects.holy_shieldBonus = i2;
        int i3 = this.uniqueID;
        MessageRouter.a("UNHOLYSHIELD", i3, i3, null, 12.0f, null);
    }

    public abstract boolean X0();

    public final void Y0() {
        if (g0() || i0() || j0()) {
            return;
        }
        this.actionStartTime = GameLevel.b();
        this.actionDuration = 0.3f;
        W();
        q0(MapActor.ActorState.ACTING);
        this.stateRelativeTime = 0.0f;
        int i2 = this.uniqueID;
        float fQ0 = Q0();
        Item[] itemArr = Rules.f3247a;
        MessageRouter.a("BASH", this.uniqueID, i2, null, (10.0f / 10) * fQ0, null);
        this.sheet.skillSet.s("bash");
    }

    public final boolean Z0(int i2, float f2) {
        if (!g0() && !i0() && !j0()) {
            if (this.sheet.c0(i2)) {
                this.sheet.j0(i2);
                this.actionStartTime = GameLevel.b();
                W();
                q0(MapActor.ActorState.ACTING);
                this.stateRelativeTime = 0.0f;
                this.actionDuration = f2;
                GameAssets.o("buff2");
                k0.a.l().e(0.6f, this.uniqueID, 64, "aura_yellow");
                return true;
            }
            if (this.uniqueID == 1) {
                GameConsole.a(GameString.b("NOT_ENOUGH_MANA", false));
            }
        }
        return false;
    }

    public final void a1() {
        Coords coords;
        if (g0() || i0() || j0()) {
            return;
        }
        this.actionStartTime = GameLevel.b();
        this.skillOrigin = new Coords(this.f3092x, this.f3093y);
        W();
        q0(MapActor.ActorState.SKILL_CHARGE);
        this.stateRelativeTime = 0.0f;
        GameAssets.o("swing");
        Coords coords2 = this.destination;
        coords2.f3287x = -1;
        coords2.f3288y = -1;
        float f2 = this.speedX;
        if (f2 == 0.0f && this.speedY == 0.0f) {
            coords = b.L(B(), 180, this.facing);
        } else {
            float f3 = this.speedY * 100.0f;
            double dSqrt = 180 / Math.sqrt((f3 * f3) + (r1 * r1));
            coords = new Coords(this.f3092x + ((int) (((double) (f2 * 100.0f)) * dSqrt)), this.f3093y + ((int) (((double) f3) * dSqrt)));
        }
        Vector2 qVar = new Vector2 ();
        Vector2 qVarA = coords.a();
        qVar.f91a = qVarA.f91a;
        qVar.f92b = qVarA.f92b;
        qVar.c(new Coords(this.f3092x, this.f3093y).a());
        qVar.a();
        this.speedX = c0() * qVar.f91a * 125.0f;
        this.speedY = c0() * qVar.f92b * 125.0f;
        int i2 = this.uniqueID;
        MessageRouter.a("CHARGE", i2, i2, null, 0.32000002f, null);
        this.sheet.skillSet.s("charge");
    }

    public final void b1() {
        this.sheet.effects.evasion = Boolean.TRUE;
        int i2 = this.uniqueID;
        MessageRouter.a("UNEVASION", i2, i2, null, 5.0f, null);
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final float c0() {
        if (d0() == MapActor.ActorState.PUSHED) {
            return 3.0f;
        }
        if (d0() == MapActor.ActorState.SKILL_CHARGE) {
            return 2.5f;
        }
        if (this.sheet.effects.slowed.booleanValue()) {
            return this.speedModifier * 0.5f;
        }
        if (this.sheet.effects.rooted.booleanValue()) {
            return 0.0f;
        }
        if (this.sheet.effects.speed.booleanValue()) {
            return this.speedModifier * 1.5f;
        }
        return Math.max(P0() * this.speedModifier, 0.5f);
    }

    public final void c1() {
        this.sheet.effects.flurry = true;
        int i2 = this.uniqueID;
        MessageRouter.a("UNFLURRY", i2, i2, null, 6.0f, null);
    }

    public void d1() {
        if (g0() || i0() || j0()) {
            return;
        }
        this.actionStartTime = GameLevel.b();
        this.actionDuration = 0.3f;
        W();
        q0(MapActor.ActorState.ACTING);
        this.stateRelativeTime = 0.0f;
        int i2 = this.uniqueID;
        float fQ0 = Q0();
        Item[] itemArr = Rules.f3247a;
        MessageRouter.a("KICK", this.uniqueID, i2, null, (10.0f / 10) * fQ0, null);
        this.sheet.skillSet.s("kick");
    }

    public final void e1() {
        this.sheet.effects.rapid_fire = true;
        int i2 = this.uniqueID;
        MessageRouter.a("UNRAPID_FIRE", i2, i2, null, 6.0f, null);
    }

    @Override // net.fdgames.GameEntities.MapActor
    protected final boolean f0() {
        CharacterSheet characterSheet = this.sheet;
        if (characterSheet.l().giant) {
            return true;
        }
        CharacterInventory characterInventory = characterSheet.inventory;
        return characterInventory != null && characterInventory.stability;
    }

    public final void f1(float f2) {
        this.sheet.effects.stealth = Boolean.TRUE;
        m(f2, this.uniqueID, "UNSTEALTH");
    }

    public void g1() {
        if (g0() || i0() || j0()) {
            return;
        }
        this.actionStartTime = GameLevel.b();
        W();
        q0(MapActor.ActorState.SKILL_WHIRLWIND);
        this.stateRelativeTime = 0.0f;
        GameAssets.o("swing");
        int i2 = this.uniqueID;
        float fQ0 = Q0();
        Item[] itemArr = Rules.f3247a;
        MessageRouter.a("WHIRLWIND", this.uniqueID, i2, null, (10.0f / 10) * fQ0, null);
        this.sheet.skillSet.s("whirlwind");
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return this.name;
    }

    public final boolean h1() {
        return this.sheet.stats.c().equals(Rules.CharacterClass.CLERIC) || this.sheet.stats.c().equals(Rules.CharacterClass.WIZARD);
    }

    protected boolean i1() {
        return false;
    }

    public final void j1(int i2, float f2) {
        CharacterEffects characterEffects = this.sheet.effects;
        characterEffects.might = Boolean.TRUE;
        characterEffects.mightBonus = i2;
        int i3 = this.uniqueID;
        MessageRouter.a("UNMIGHT", i3, i3, null, f2, null);
    }

    public final void k1(int i2, float f2) {
        CharacterEffects characterEffects = this.sheet.effects;
        characterEffects.might_arbenos = Boolean.TRUE;
        characterEffects.mightBonus_arbenos = i2;
        int i3 = this.uniqueID;
        MessageRouter.a("UNMIGHT_ARBENOS", i3, i3, null, f2, null);
    }

    public final void l1(int i2) {
        CharacterEffects characterEffects = this.sheet.effects;
        characterEffects.might_prayer = Boolean.TRUE;
        characterEffects.mightBonus_prayer = i2;
        int i3 = this.uniqueID;
        MessageRouter.a("UNMIGHT_PRAYER", i3, i3, null, 12.0f, null);
    }

    public boolean m1() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void n1(int i2, float f2) {
        float f3;
        int iG;
        float f4;
        int iG2 = this.sheet.skillSet.g("poison_master");
        if (iG2 != 2) {
            if (iG2 == 3) {
                f3 = i2 * 1.5f;
            }
            iG = this.sheet.skillSet.g("poison_master");
            if (iG != 1) {
                f2 *= 1.5f;
            } else {
                if (iG != 2) {
                    f4 = iG == 3 ? 2.0f : 1.75f;
                }
                f2 *= f4;
            }
            float f5 = f2;
            CharacterEffects characterEffects = this.sheet.effects;
            characterEffects.poison = Boolean.TRUE;
            characterEffects.poisonBonus = i2;
            k0.a.l().a(new w(this.uniqueID, GameString.b("APPLY_POISON", false), 1.0f, Color.YELLOW, 1.2f, 0.7f));
            int i3 = this.uniqueID;
            MessageRouter.a("UNPOISONWEAPON", i3, i3, null, f5, null);
        }
        f3 = i2 * 1.25f;
        i2 = (int) f3;
        iG = this.sheet.skillSet.g("poison_master");
        if (iG != 1) {
        }
        float f52 = f2;
        CharacterEffects characterEffects2 = this.sheet.effects;
        characterEffects2.poison = Boolean.TRUE;
        characterEffects2.poisonBonus = i2;
        k0.a.l().a(new w(this.uniqueID, GameString.b("APPLY_POISON", false), 1.0f, Color.YELLOW, 1.2f, 0.7f));
        int i32 = this.uniqueID;
        MessageRouter.a("UNPOISONWEAPON", i32, i32, null, f52, null);
    }

    public final void o1(int i2) {
        if (i2 != 1000) {
            k0.a.l().a(new w(this.uniqueID, "+" + Integer.toString(i2) + "mana", 0.4f, Color.CYAN, 1.0f, 1.0f));
            k0.a.l().b(B(), a.EnumC0031a.f2303o, 0.0f);
            GameAssets.o("heal");
        }
        CharacterStats characterStats = this.sheet.stats;
        int i3 = characterStats.missingMana - i2;
        characterStats.missingMana = i3;
        if (i3 < 0) {
            characterStats.missingMana = 0;
        }
    }

    public final void p1(int i2) {
        this.sheet.effects.resistances.f(i2);
        int i3 = this.uniqueID;
        MessageRouter.a("UNRESISTGLOBAL", i3, i3, null, 12.0f, null);
    }

    public final void q1(CharacterResistances.ResistanceType resistanceType, int i2, float f2) {
        this.sheet.effects.resistances.g(resistanceType, i2);
        int iOrdinal = resistanceType.ordinal();
        if (iOrdinal == 0) {
            int i3 = this.uniqueID;
            MessageRouter.a("UNRESISTFIRE", i3, i3, null, f2, null);
            return;
        }
        if (iOrdinal == 1) {
            int i4 = this.uniqueID;
            MessageRouter.a("UNRESISTCOLD", i4, i4, null, f2, null);
            return;
        }
        if (iOrdinal == 2) {
            int i5 = this.uniqueID;
            MessageRouter.a("UNRESISTSHOCK", i5, i5, null, f2, null);
            return;
        }
        if (iOrdinal == 3) {
            int i6 = this.uniqueID;
            MessageRouter.a("UNRESISTDEATH", i6, i6, null, f2, null);
        } else if (iOrdinal == 4) {
            int i7 = this.uniqueID;
            MessageRouter.a("UNRESISTTOXIC", i7, i7, null, f2, null);
        } else {
            if (iOrdinal != 5) {
                return;
            }
            int i8 = this.uniqueID;
            MessageRouter.a("UNRESISTSPIRIT", i8, i8, null, f2, null);
        }
    }

    public final void r1(String str) {
        this.name = str;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final void s0(float f2) {
        if (!this.sheet.effects.slowed.booleanValue()) {
            k0.a.l().a(new w(this.uniqueID, "Slowed", 0.6f, Color.GREEN, 1.0f, 0.7f));
        }
        this.sheet.effects.slowed = Boolean.TRUE;
        m(f2, this.uniqueID, "UNSLOW");
    }

    public final void s1(int i2) {
        this.spellTarget = i2;
    }

    public final void t1(int i2, float f2) {
        CharacterEffects characterEffects = this.sheet.effects;
        characterEffects.shielded = Boolean.TRUE;
        characterEffects.shieldBonus = i2;
        m(f2, this.uniqueID, "UNSHIELD");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:339:0x09eb  */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v5 */
    @Override // net.fdgames.GameEntities.GameObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void u(int i2, String str, String str2) {
        ArrayList<DamageEffect> arrayList;
        Iterator<DamageEffect> it;
        int i3;
        int i4;
        Damage.DamageType damageType;
        int iB;
        float f2;
        int i5;
        int iB2;
        int i6;
        int iB3;
        int iG;
        SkillSet skillSet;
        int iG2;
        int i7;
        Character characterF;
        Coords coords;
        int i8 = 1;
        boolean r6 = false;
        if (!i0()) {
            int i9 = 4;
            int i10 = 2;
            int i11 = 3;
            if (i2 == this.uniqueID) {
                boolean zEquals = str.equals("ATTACK");
                MapActor.ActorState actorState = MapActor.ActorState.DEAD;
                if (zEquals) {
                    if (d0() == actorState || this.destroy) {
                        return;
                    }
                    CharacterSheet characterSheet = this.sheet;
                    CharacterEffects characterEffects = characterSheet.effects;
                    Boolean bool = Boolean.FALSE;
                    characterEffects.stealth = bool;
                    if (characterSheet.N().ranged) {
                        if (!this.sheet.N().c() && ((k0() || this.uniqueID == 1) && FDUtils.b(1, 100) < 30 && b.P().d(B(), r(), 1) > 0)) {
                            k0.a.l().a(new w(this.uniqueID, GameString.b("MISSED", false), 0.6f, Color.GREEN, 1.0f, 0.7f));
                            if (this.uniqueID == 1) {
                                GameData.v().log.a(GameString.b("MISSED_MESSAGE", false));
                            }
                            if (this.sheet.effects.disintegrate.booleanValue()) {
                                CharacterEffects characterEffects2 = this.sheet.effects;
                                characterEffects2.disintegrate = bool;
                                characterEffects2.disintegrateBonus = 0;
                                return;
                            }
                            return;
                        }
                        if (this.uniqueID == 1) {
                            boolean zU0 = U0();
                            Projectile.ProjectileType projectileType = Projectile.ProjectileType.ARROW;
                            if (zU0) {
                                b.P().getClass();
                                Rectangle pVarU = b.U();
                                b bVarP = b.P();
                                int i12 = this.uniqueID;
                                b.P().getClass();
                                int iB4 = bVarP.b(i12, b.j(pVarU));
                                if (iB4 > 0 && (characterF = GameLevel.f(iB4)) != null && b.P().e0(B(), characterF.B())) {
                                    GameLevel.c(this.uniqueID, iB4, r(), this.sheet.N(), this.sheet.h(), projectileType);
                                }
                            } else {
                                System.out.println("Warning, trying to shoot but weapon isn't ranged");
                            }
                            if (!u1()) {
                                if (U0()) {
                                    float f3 = this.speedX;
                                    if (f3 == 0.0f && this.speedY == 0.0f) {
                                        coords = b.L(B(), 100, this.facing);
                                    } else {
                                        float f4 = this.speedY * 100.0f;
                                        double dSqrt = 100.0d / Math.sqrt((f4 * f4) + (r1 * r1));
                                        coords = new Coords(this.f3092x + ((int) (((double) (f3 * 100.0f)) * dSqrt)), this.f3093y + ((int) (((double) f4) * dSqrt)));
                                    }
                                    int i13 = this.uniqueID;
                                    int i14 = coords.f3287x;
                                    int i15 = coords.f3288y;
                                    int[] iArrR = r();
                                    WeaponStats weaponStatsN = this.sheet.N();
                                    DamageData damageDataH = this.sheet.h();
                                    AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                                    GameLevelData.h(new Projectile(i13, i14, i15, iArrR, weaponStatsN, damageDataH, projectileType));
                                } else {
                                    System.out.println("Warning, trying to shoot but weapon isn't ranged");
                                }
                            }
                        } else {
                            u1();
                        }
                        if (this.sheet.effects.disintegrate.booleanValue()) {
                            CharacterEffects characterEffects3 = this.sheet.effects;
                            characterEffects3.disintegrate = bool;
                            characterEffects3.disintegrateBonus = 0;
                            return;
                        }
                        return;
                    }
                    b.P().getClass();
                    ArrayList<Integer> arrayListY = b.Y(this);
                    if (ExiledKingdoms.f3378h && this.uniqueID == 1 && (i7 = GameLevel.f3101h) > 0 && arrayListY.contains(Integer.valueOf(i7))) {
                        iB3 = GameLevel.f3101h;
                        Character characterF2 = GameLevel.f(this.lastTargetHit_id);
                        if (characterF2 == null || characterF2.i0()) {
                            this.lastTargetHit_id = 0;
                        }
                        int i16 = this.lastTargetHit_id;
                        if (i16 <= 0 || iB3 != i16 || Math.abs(this.SkillUseData_consecutive_lasthit - GameData.v().u()) >= 2.0f) {
                            CharacterEffects characterEffects4 = this.sheet.effects;
                            characterEffects4.duel = bool;
                            characterEffects4.duelbonus = 0;
                            if (iB3 > 0) {
                                this.SkillUseData_consecutive_hits = 1;
                            }
                            this.SkillUseData_consecutive_lasthit = GameData.v().u();
                        } else {
                            this.SkillUseData_consecutive_hits++;
                            SkillActions.n(this);
                            this.SkillUseData_consecutive_lasthit = GameData.v().u();
                        }
                    } else {
                        Character characterF3 = GameLevel.f(this.lastTargetHit_id);
                        if (characterF3 == null || characterF3.i0()) {
                            this.lastTargetHit_id = 0;
                        }
                        int i17 = this.lastTargetHit_id;
                        if (i17 <= 0 || !arrayListY.contains(Integer.valueOf(i17)) || Math.abs(this.SkillUseData_consecutive_lasthit - GameData.v().u()) >= 2.0f) {
                            CharacterEffects characterEffects5 = this.sheet.effects;
                            characterEffects5.duel = bool;
                            characterEffects5.duelbonus = 0;
                            iB3 = b.P().b(this.uniqueID, arrayListY);
                            if (iB3 > 0) {
                                this.SkillUseData_consecutive_hits = 1;
                            }
                            this.SkillUseData_consecutive_lasthit = GameData.v().u();
                        } else {
                            iB3 = this.lastTargetHit_id;
                            this.SkillUseData_consecutive_hits++;
                            SkillActions.n(this);
                            this.SkillUseData_consecutive_lasthit = GameData.v().u();
                        }
                    }
                    if (this.uniqueID == 1 && !GameLevel.f3100g) {
                        GameLevel.f3101h = 0;
                    }
                    if (iB3 != 0) {
                        if (iB3 > 0) {
                            R(GameLevel.g(iB3).B());
                        }
                        this.lastTargetHit_id = iB3;
                        if (this.sheet.effects.stab.booleanValue()) {
                            k0.a.l().a(new w(this.uniqueID, GameString.b("STAB", false), 0.6f, Color.GREEN, 1.0f, 0.7f));
                            CharacterSheet characterSheet2 = this.sheet;
                            if (characterSheet2 != null && (skillSet = characterSheet2.skillSet) != null && (iG2 = skillSet.g("assassinate")) != 0) {
                                int i18 = iG2 == 1 ? 10 : 0;
                                if (iG2 == 2) {
                                    i18 = 20;
                                }
                                if (FDUtils.b(1, 100) <= (iG2 == 3 ? 35 : i18)) {
                                    this.sheet.skillSet.o();
                                    k0.a.l().a(new w(this.uniqueID, Skills.c("assassinate").d() + "!", 1.0f, Color.YELLOW, 1.5f, 0.7f));
                                }
                            }
                            this.SkillUseData_lastStabbed_id = iB3;
                            this.SkillUseData_lastStabTime = GameData.v().u();
                        }
                        if (this.sheet.effects.disintegrate.booleanValue()) {
                            k0.a.l().a(new w(this.uniqueID, Skills.c("disintegrate").d(), 0.6f, Color.GREEN, 1.0f, 0.7f));
                        }
                        n(iB3, this.sheet.h());
                        if (arrayListY.size() > 1 && this.sheet.stats.c() == Rules.CharacterClass.WARRIOR && (iG = this.sheet.skillSet.g("cleave")) > 0) {
                            k0.a.l().a(new w(this.uniqueID, "Cleave!", 0.6f, Color.GREEN, 1.0f, 0.7f));
                            float f5 = iG == 2 ? 0.6f : 0.5f;
                            if (iG == 3) {
                                f5 = 0.75f;
                            }
                            Iterator<Integer> it2 = arrayListY.iterator();
                            while (it2.hasNext()) {
                                int iIntValue = it2.next().intValue();
                                if (iG > 0 && iIntValue != iB3) {
                                    n(iIntValue, this.sheet.i(f5, true, true));
                                    iG--;
                                }
                            }
                        }
                    }
                    if (this.sheet.effects.stab.booleanValue()) {
                        this.sheet.effects.stab = Boolean.FALSE;
                    }
                    if (this.sheet.effects.disintegrate.booleanValue()) {
                        CharacterEffects characterEffects6 = this.sheet.effects;
                        characterEffects6.disintegrate = Boolean.FALSE;
                        characterEffects6.disintegrateBonus = 0;
                        return;
                    }
                    return;
                }
                if (str.equals("FATIGUE")) {
                    CharacterEffects characterEffects7 = this.sheet.effects;
                    characterEffects7.fatigued = Boolean.TRUE;
                    characterEffects7.rage = Boolean.FALSE;
                    characterEffects7.rageStrBonus = 0;
                    k0.a.l().a(new w(this.uniqueID, GameString.b("FATIGUED", false), 0.6f, Color.GREEN, 0.8f, 0.7f));
                    int i19 = this.uniqueID;
                    MessageRouter.a("UNFATIGUE", i19, i19, null, 5, null);
                    return;
                }
                int i20 = 96;
                float f6 = 3.0f;
                int i21 = 64;
                if (str.equals("KICK")) {
                    k0.a.l().a(new w(this.uniqueID, Skills.c("kick").d(), 0.6f, Color.GREEN, 1.0f, 0.7f));
                    if (this.sheet.Y()) {
                        iB2 = b.P().d(B(), r(), 1);
                    } else {
                        b.P().getClass();
                        iB2 = b.P().b(this.uniqueID, b.Y(this));
                    }
                    if (iB2 != 0) {
                        int iG3 = this.sheet.skillSet.g("kick");
                        if (iG3 == 1) {
                            f = 3.0f;
                        } else {
                            i21 = 0;
                        }
                        if (iG3 == 2) {
                            f = 4.0f;
                        } else {
                            i20 = i21;
                        }
                        if (iG3 == 3) {
                            f = 5.0f;
                            i6 = 128;
                        } else {
                            i6 = i20;
                        }
                        MapActor mapActorG = GameLevel.g(iB2);
                        R(mapActorG.B());
                        DamageData damageDataH2 = this.sheet.h();
                        Damage damage = damageDataH2.damages.get(0);
                        damageDataH2.damages.clear();
                        damageDataH2.damages.add(damage);
                        n(iB2, damageDataH2);
                        if (mapActorG.d0() != actorState) {
                            mapActorG.n0(i6, B());
                            mapActorG.s0(f);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (str.equals("BASH")) {
                    k0.a.l().a(new w(this.uniqueID, Skills.c("bash").d(), 0.6f, Color.GREEN, 1.0f, 0.7f));
                    if (this.sheet.Y()) {
                        iB = b.P().d(B(), r(), 1);
                    } else {
                        b.P().getClass();
                        iB = b.P().b(this.uniqueID, b.Y(this));
                    }
                    if (iB != 0) {
                        int iG4 = this.sheet.skillSet.g("bash");
                        if (iG4 == 1) {
                            f = 1.0f;
                        } else {
                            i21 = 0;
                        }
                        if (iG4 == 2) {
                            f2 = 2.0f;
                        } else {
                            f2 = f;
                            i20 = i21;
                        }
                        if (iG4 == 3) {
                            i5 = 128;
                        } else {
                            f6 = f2;
                            i5 = i20;
                        }
                        MapActor mapActorG2 = GameLevel.g(iB);
                        R(mapActorG2.B());
                        CharacterSheet characterSheet3 = this.sheet;
                        if (characterSheet3.inventory.shield) {
                            f6 += 1.0f;
                        }
                        float f7 = f6;
                        DamageData damageDataH3 = characterSheet3.h();
                        Damage damage2 = damageDataH3.damages.get(0);
                        damageDataH3.damages.clear();
                        damageDataH3.damages.add(damage2);
                        n(iB, damageDataH3);
                        if (mapActorG2.d0() != actorState) {
                            ((Character) mapActorG2).z1(f7);
                            mapActorG2.n0(i5, B());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (str.equals("WHIRLWIND")) {
                    k0.a.l().a(new w(this.uniqueID, Skills.c("whirlwind").d(), 0.6f, Color.GREEN, 1.0f, 0.7f));
                    Rectangle pVar = new Rectangle (this.f3092x - 44.8f, this.f3093y - 44.8f, 89.6f, 89.6f);
                    b.P().getClass();
                    ArrayList<Integer> arrayListJ = b.j(pVar);
                    int iG5 = this.sheet.skillSet.g("whirlwind");
                    if (iG5 == 1) {
                        i9 = 0;
                        f = 1.5f;
                    } else if (iG5 == 2) {
                        f = 1.7f;
                    } else if (iG5 == 3) {
                        f = 1.9f;
                        i9 = 5;
                    } else if (iG5 == 4) {
                        i9 = 7;
                        f = 2.1f;
                    } else {
                        i9 = 0;
                    }
                    for (Integer num : arrayListJ) {
                        if (num.intValue() != this.uniqueID && GameWorld.f3189c.g(GameLevel.g(num.intValue()).r(), r())) {
                            GameLevel.g(num.intValue()).n0(80.0d, B());
                            GameLevel.g(this.uniqueID).n(num.intValue(), this.sheet.i(f, false, false));
                            if (FDUtils.b(1, 100) <= i9) {
                                GameLevel.f(num.intValue()).z1(1.5f);
                            }
                        }
                    }
                    return;
                }
                if (str.equals("CHARGE")) {
                    k0.a.l().a(new w(this.uniqueID, Skills.c("charge").d(), 0.6f, Color.GREEN, 1.0f, 0.7f));
                    b bVarP2 = b.P();
                    int[] iArrR2 = r();
                    Coords coords2 = this.skillOrigin;
                    Coords coordsB = B();
                    bVarP2.getClass();
                    ArrayList<Integer> arrayListK = b.k(iArrR2, coords2, coordsB);
                    int iG6 = this.sheet.skillSet.g("charge");
                    f = iG6 == 1 ? 1.5f : 0.0f;
                    if (iG6 == 2) {
                        f = 1.8f;
                    }
                    float f8 = iG6 != 3 ? f : 2.1f;
                    if (iG6 == 4) {
                        f8 = 2.5f;
                    }
                    float f9 = f8;
                    for (Integer num2 : arrayListK) {
                        if (num2.intValue() != this.uniqueID && GameWorld.f3189c.g(GameLevel.g(num2.intValue()).r(), r())) {
                            GameLevel.g(num2.intValue()).n0(80.0d, B());
                            GameLevel.g(this.uniqueID).n(num2.intValue(), this.sheet.i(f9, false, false));
                        }
                    }
                    return;
                }
                if (str.equals("TRY_TO_DESPAWN")) {
                    L();
                    return;
                }
                if (str.equals("CAST")) {
                    if (!j0()) {
                        J0();
                        return;
                    }
                    k0.a.l().a(new w(this.uniqueID, GameString.b("INTERRUPTED", false), 0.6f, Color.GREEN, 1.0f, 0.7f));
                    this.spell_id = "";
                    this.spellTarget = 0;
                    return;
                }
            }
            if (str.equals("HIT")) {
                if (this.receivedDamage != null) {
                    if (!this.sheet.l().vampire || GameData.v().e()) {
                        int i22 = this.receivedDamage.weapon_item_id;
                        AttributesSet attributesSetL = this.sheet.l();
                        float fU = GameData.v().u();
                        if (attributesSetL.toxic_aura && fU > attributesSetL.last_toxic_aura + 8.0f && FDUtils.b(1, 100) < 60.0f) {
                            attributesSetL.last_toxic_aura = fU;
                            AreaEffects.b(this.f3092x, this.f3093y, this.uniqueID);
                            GameData.v().log.a(C() + " " + GameString.b("EFFECT_GAS", false));
                        }
                        for (Damage damage3 : this.receivedDamage.damages) {
                            if (damage3 != null && (damageType = damage3.type) != null) {
                                if (damageType.equals(Damage.DamageType.Normal)) {
                                    R0(damage3, i2, this.receivedDamage.critical, i22);
                                } else {
                                    R0(damage3, i2, false, i22);
                                }
                                i22 = 0;
                            }
                        }
                        if (!i0() && (arrayList = this.receivedDamage.procs) != null && arrayList.size() != 0) {
                            Iterator<DamageEffect> it3 = this.receivedDamage.procs.iterator();
                            while (it3.hasNext()) {
                                DamageEffect next = it3.next();
                                if (next.a()) {
                                    int iOrdinal = next.type.ordinal();
                                    if (iOrdinal == 0) {
                                        it = it3;
                                        i3 = i10;
                                        i4 = i11;
                                        z1(next.level * i3);
                                    } else if (iOrdinal != i8) {
                                        Damage.DamageType damageType2 = Damage.DamageType.Spirit;
                                        if (iOrdinal != i10) {
                                            MapActor.ActorState actorState2 = MapActor.ActorState.PARALIZED;
                                            if (iOrdinal != i11) {
                                                if (iOrdinal == i9) {
                                                    AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                                                    if (GameData.v().player.sheet.Q(1022) > 0) {
                                                        k0.a.l().a(new w(this.uniqueID, a.a.o("CAPTION_EMP", r6, new StringBuilder("[WHITE]"), "[]"), 1.8f, Color.WHITE, 1.3f, 0.33f));
                                                        CharacterSheet characterSheet4 = this.sheet;
                                                        if (characterSheet4.attributes.robot && characterSheet4.o() > 0) {
                                                            GameData.v().log.a("[YELLOW]" + this.name + " " + GameString.b("LOG_IS", r6) + " " + GameString.b("EFFECT_PARALYZED", r6) + "! (4 s.)[]");
                                                            this.sheet.effects.stunned = Boolean.TRUE;
                                                            k0.a.l().a(new w(this.uniqueID, GameString.b("EFFECT_PARALYZED", r6), 1.8f, Color.GREEN, 1.0f, 0.33f));
                                                            q0(actorState2);
                                                            int i23 = this.uniqueID;
                                                            MessageRouter.a("UNSTUN", i23, i23, null, (float) i9, null);
                                                        }
                                                        R0(new Damage(Damage.DamageType.Shock, FDUtils.b(35, 75), r6), i2, r6, r6);
                                                        GameData.v().player.sheet.b0(1022);
                                                    }
                                                }
                                                it = it3;
                                                i4 = i11;
                                                i3 = 2;
                                            } else {
                                                float f10 = next.level;
                                                if (!this.sheet.effects.rage.booleanValue() && !i0() && this.sheet.o() > 0) {
                                                    float fF0 = this.sheet.f0();
                                                    if (fF0 == 0.0f || fF0 == 0.5f) {
                                                        it = it3;
                                                        GameData.v().log.a("[YELLOW]" + this.name + " " + GameString.b("LOG_RESIST", false) + " (" + this.sheet.E() + "%)[]");
                                                    } else {
                                                        CharacterSheet characterSheet5 = this.sheet;
                                                        if (!characterSheet5.l().robot && !characterSheet5.l().shadow) {
                                                            if (fF0 == 1.0f) {
                                                                if (this.sheet.T()) {
                                                                    f10 /= 2.0f;
                                                                }
                                                                GameLog gameLog = GameData.v().log;
                                                                StringBuilder sb = new StringBuilder("[YELLOW]");
                                                                sb.append(this.name);
                                                                sb.append(" ");
                                                                it = it3;
                                                                sb.append(GameString.b("LOG_FAILED_RESIST", false));
                                                                sb.append(" (");
                                                                sb.append(this.sheet.E());
                                                                sb.append("%)[]");
                                                                gameLog.a(sb.toString());
                                                                GameData.v().log.a("[YELLOW]" + this.name + " " + GameString.b("LOG_IS", false) + " " + GameString.b("EFFECT_PARALYZED", false) + "! (" + ((int) f10) + " s.)[]");
                                                            } else {
                                                                it = it3;
                                                            }
                                                            this.sheet.effects.stunned = Boolean.TRUE;
                                                            k0.a.l().a(new w(this.uniqueID, GameString.b("EFFECT_PARALYZED", false), 1.8f, Color.GREEN, 1.0f, 0.33f));
                                                            q0(actorState2);
                                                            int i24 = this.uniqueID;
                                                            MessageRouter.a("UNSTUN", i24, i24, null, fF0 * f10, null);
                                                        }
                                                    }
                                                }
                                            }
                                            it = it3;
                                        } else {
                                            it = it3;
                                            if (this.sheet.P("undead")) {
                                                i3 = 2;
                                                R0(new Damage(damageType2, next.level * 2, false), i2, false, 0);
                                            }
                                            i4 = 3;
                                        }
                                        i3 = 2;
                                        i4 = 3;
                                    } else {
                                        it = it3;
                                        i3 = i10;
                                        i4 = 3;
                                        s0((next.level * i3) + 3);
                                    }
                                } else {
                                    it = it3;
                                    i3 = i10;
                                    i4 = i11;
                                }
                                it3 = it;
                                i10 = i3;
                                i11 = i4;
                                i8 = 1;
                                r6 = false;
                                i9 = 4;
                            }
                        }
                    } else {
                        GameData.v().log.a("[RED]" + GameString.b("LOG_INVULNERABLE", false) + "[]");
                    }
                    this.receivedDamage = null;
                    return;
                }
                return;
            }
        }
        if (str.equals("UNSLOW")) {
            this.sheet.effects.slowed = Boolean.FALSE;
            return;
        }
        if (str.equals("UNSTUN")) {
            this.sheet.effects.stunned = Boolean.FALSE;
            if (i0() || this.sheet.o() <= 0) {
                return;
            }
            q0(MapActor.ActorState.IDLE);
            Coords coords3 = this.destination;
            coords3.f3287x = -1;
            coords3.f3288y = -1;
            t0();
            return;
        }
        if (str.equals("SHIELD")) {
            CharacterEffects characterEffects8 = this.sheet.effects;
            characterEffects8.shielded = Boolean.TRUE;
            characterEffects8.shieldBonus = Integer.parseInt(str2);
            return;
        }
        if (str.equals("UNSHIELD")) {
            this.sheet.effects.shielded = Boolean.FALSE;
            return;
        }
        if (str.equals("UNHOLYSHIELD")) {
            this.sheet.effects.holy_shielded = Boolean.FALSE;
            return;
        }
        if (str.equals("UNSTEALTH")) {
            this.sheet.effects.stealth = Boolean.FALSE;
            return;
        }
        if (str.equals("UNSTAB")) {
            CharacterEffects characterEffects9 = this.sheet.effects;
            characterEffects9.stab = Boolean.FALSE;
            characterEffects9.stabBonus = 0;
            return;
        }
        if (str.equals("UNDISINTEGRATE")) {
            CharacterEffects characterEffects10 = this.sheet.effects;
            characterEffects10.disintegrate = Boolean.FALSE;
            characterEffects10.disintegrateBonus = 0;
            return;
        }
        if (str.equals("UNMIGHT")) {
            CharacterEffects characterEffects11 = this.sheet.effects;
            characterEffects11.might = Boolean.FALSE;
            characterEffects11.mightBonus = 0;
            return;
        }
        if (str.equals("UNMIGHT_ARBENOS")) {
            CharacterEffects characterEffects12 = this.sheet.effects;
            characterEffects12.might_arbenos = Boolean.FALSE;
            characterEffects12.mightBonus_arbenos = 0;
            return;
        }
        if (str.equals("UNMIGHT_PRAYER")) {
            CharacterEffects characterEffects13 = this.sheet.effects;
            characterEffects13.might_prayer = Boolean.FALSE;
            characterEffects13.mightBonus_prayer = 0;
            return;
        }
        if (str.equals("UNSPEED")) {
            this.sheet.effects.speed = Boolean.FALSE;
            return;
        }
        if (str.equals("UNFLAMEAURA")) {
            this.sheet.effects.flameAura = false;
            return;
        }
        if (str.equals("UNPOISONWEAPON")) {
            CharacterEffects characterEffects14 = this.sheet.effects;
            characterEffects14.poison = Boolean.FALSE;
            characterEffects14.poisonBonus = 0;
            k0.a.l().a(new w(this.uniqueID, GameString.b("POISON_WOREOFF", false), 1.0f, Color.YELLOW, 1.2f, 0.7f));
            return;
        }
        if (str.equals("UNRAPID_FIRE")) {
            this.sheet.effects.rapid_fire = false;
            return;
        }
        if (str.equals("UNFLURRY")) {
            this.sheet.effects.flurry = false;
            return;
        }
        if (str.equals("UNEVASION")) {
            this.sheet.effects.evasion = Boolean.FALSE;
            return;
        }
        if (str.equals("UNFATIGUE")) {
            this.sheet.effects.fatigued = Boolean.FALSE;
            return;
        }
        if (str.equals("UNMAGEARMOR")) {
            CharacterEffects characterEffects15 = this.sheet.effects;
            characterEffects15.mageArmor_Charges = 0;
            characterEffects15.mageArmorBonus = 0;
            characterEffects15.mageArmorElementalBonus = 0;
            return;
        }
        if (str.equals("UNRESISTCOLD")) {
            this.sheet.effects.resistances.g(CharacterResistances.ResistanceType.Cold, 0);
            return;
        }
        if (str.equals("UNRESISTDEATH")) {
            this.sheet.effects.resistances.g(CharacterResistances.ResistanceType.Death, 0);
            return;
        }
        if (str.equals("UNRESISTFIRE")) {
            this.sheet.effects.resistances.g(CharacterResistances.ResistanceType.Fire, 0);
            return;
        }
        if (str.equals("UNRESISTSHOCK")) {
            this.sheet.effects.resistances.g(CharacterResistances.ResistanceType.Shock, 0);
            return;
        }
        if (str.equals("UNRESISTSPIRIT")) {
            this.sheet.effects.resistances.g(CharacterResistances.ResistanceType.Spirit, 0);
            return;
        }
        if (str.equals("UNRESISTTOXIC")) {
            this.sheet.effects.resistances.g(CharacterResistances.ResistanceType.Toxic, 0);
            return;
        }
        if (str.equals("UNRESISTGLOBAL")) {
            this.sheet.effects.resistances.f(0);
            return;
        }
        if (str.equals("DESTROY")) {
            this.destroy = true;
            return;
        }
        if (str.equals("EXPLODE")) {
            if (this.sheet.stats.f() > 12) {
                AreaEffects.d(this.f3092x, this.f3093y, "explo_5", 0);
            } else {
                AreaEffects.d(this.f3092x, this.f3093y, "explo_4", 0);
            }
            this.destroy = true;
            return;
        }
        if (i0()) {
            return;
        }
        l.d("CAUTION : NON-CONSUMED MESSAGE BY NPC: " + this.uniqueID + "/" + this.name + " message: " + str + "/" + i2 + "/" + str2);
    }

    public final boolean u1() {
        if (!U0()) {
            System.out.println("Warning, trying to shoot but weapon isn't ranged");
            return false;
        }
        int iA = b.P().a(B(), r());
        MapActor mapActorG = GameLevel.g(iA);
        if (iA <= 0 || mapActorG == null) {
            return false;
        }
        R(mapActorG.B());
        GameLevel.c(this.uniqueID, iA, r(), this.sheet.N(), this.sheet.h(), Projectile.ProjectileType.ARROW);
        return true;
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
        if (damageData != null) {
            this.receivedDamage = damageData;
        }
        u(i2, str, str2);
    }

    public final void v1(float f2) {
        if (this.sheet.effects.slowed.booleanValue() || this.sheet.effects.rooted.booleanValue()) {
            CharacterEffects characterEffects = this.sheet.effects;
            Boolean bool = Boolean.FALSE;
            characterEffects.slowed = bool;
            characterEffects.rooted = bool;
        }
        this.sheet.effects.speed = Boolean.TRUE;
        k0.a aVarL = k0.a.l();
        a.EnumC0031a enumC0031a = a.EnumC0031a.f2301m;
        aVarL.getClass();
        aVarL.b(B(), enumC0031a, f2).owner = this;
        m(f2, this.uniqueID, "UNSPEED");
    }

    @Override // net.fdgames.GameEntities.MapActor
    protected final void w0(float f2) {
        if (d0() != MapActor.ActorState.ATTACKING && d0() != MapActor.ActorState.FIRING) {
            super.w0(f2);
        } else {
            this.stateRelativeTime = ((f2 * this.sheet.H()) / 5.0f) + this.stateRelativeTime;
        }
    }

    public final void w1(int i2) {
        CharacterEffects characterEffects = this.sheet.effects;
        characterEffects.stab = Boolean.TRUE;
        characterEffects.stabBonus = i2;
        int i3 = this.uniqueID;
        MessageRouter.a("UNSTAB", i3, i3, null, 4.0f, null);
    }

    public final void x1(String str) {
        y1(str, 1.0f);
    }

    public final Rectangle y0() {
        int iA = (int) (this.sheet.a() * 0.75f);
        float f2 = this.f3092x - iA;
        float f3 = this.f3093y - iA;
        float f4 = iA * 2;
        return new Rectangle (f2, f3, f4, f4);
    }

    public final boolean y1(String str, float f2) {
        if (!g0() && !i0() && !j0()) {
            if (this.sheet.p() >= this.sheet.skillSet.f(str)) {
                CharacterSheet characterSheet = this.sheet;
                characterSheet.j0(characterSheet.skillSet.f(str));
                G0(1, Skills.c(str).d());
                this.spell_id = str;
                int i2 = this.uniqueID;
                MessageRouter.a("CAST", i2, i2, null, f2, null);
                return true;
            }
            if (this.uniqueID == 1) {
                GameConsole.a(GameString.b("NOT_ENOUGH_MANA", false));
            }
        }
        return false;
    }

    public final int z0() {
        SkillSet skillSet;
        if (!this.sheet.X() || (skillSet = this.sheet.skillSet) == null || skillSet.d()) {
            return 0;
        }
        return this.sheet.skillSet.g("beast_master");
    }

    public final void z1(float f2) {
        if (this.sheet.effects.rage.booleanValue() || i0() || this.sheet.o() <= 0) {
            return;
        }
        float fF0 = this.sheet.f0();
        if (fF0 == 1.0f) {
            CharacterSheet characterSheet = this.sheet;
            if (characterSheet.inventory != null && characterSheet.Z() && characterSheet.inventory.j().b() && characterSheet.skillSet.g("infantry_training") == 1) {
                fF0 = 0.5f;
            }
        }
        if (fF0 == 0.0f || this.sheet.T()) {
            GameData.v().log.a("[YELLOW]" + this.name + " " + GameString.b("LOG_RESIST", false) + " (" + this.sheet.E() + "%)[]");
            return;
        }
        if (fF0 == 0.5f) {
            GameData.v().log.a("[YELLOW]" + this.name + " " + GameString.b("LOG_PARTIAL_RESIST", false) + " (" + this.sheet.E() + "%)[]");
        }
        if (fF0 > 0.0f) {
            GameData.v().log.a("[YELLOW]" + this.name + " " + GameString.b("LOG_FAILED_RESIST", false) + " (" + this.sheet.E() + "%)[]");
            GameData.v().log.a("[YELLOW]" + this.name + " " + GameString.b("LOG_IS", false) + " " + GameString.b("EFFECT_STUNNED", false) + "! (" + ((int) (fF0 * f2)) + " s.)[]");
        }
        this.sheet.effects.stunned = Boolean.TRUE;
        k0.a.l().a(new w(this.uniqueID, GameString.b("EFFECT_STUNNED", false), 0.6f, Color.GREEN, 1.0f, 0.7f));
        q0(MapActor.ActorState.PARALIZED);
        int i2 = this.uniqueID;
        MessageRouter.a("UNSTUN", i2, i2, null, fF0 * f2, null);
    }

    public Character(Rules.CharacterRace characterRace, Rules.CharacterClass characterClass, String str, String str2, float f2, float f3, int[] iArr, Gender gender, int i2, String str3, boolean z2) {
        this.gender = Gender.Male;
        this.speedModifier = 1.0f;
        this.size = 1.0f;
        this.drawsize = 1.0f;
        this.sheet = new CharacterSheet(characterRace, characterClass);
        this.animationSetName = new ArrayList<>();
        Iterator it = Arrays.asList(str2.split(";")).iterator();
        while (it.hasNext()) {
            this.animationSetName.add((String) it.next());
        }
        this.size = f2;
        this.drawsize = f2;
        if (z2) {
            this.drawsize = f2 * 1.5f;
        }
        this.speedModifier = f3;
        y(iArr);
        this.name = str;
        this.gender = gender;
        this.portraitIndex = i2;
        this.conversationTag = str3;
        this.spell_id = "";
    }
}
