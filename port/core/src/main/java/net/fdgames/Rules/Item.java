package net.fdgames.Rules;

import a.a;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameLogic.PlayerRequirements;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Item {
    public int DevicesBonus;
    public int HPBonus;
    public int ManaBonus;
    public ActionsSet OnTake;
    public ConditionsSet OnTakeconditions;
    public ActionsSet OnUse;
    private CharacterResistances ResistanceBonus;
    public int armorBonus;
    public ItemAttributes attributes;
    public ClassRestriction classes;
    public int damageBonus;
    public Damage.DamageType damageBonusType;
    private String description;
    public boolean hasDamageBonus;
    public boolean hasProc;
    public String icon;
    public int item_ID;
    public int manaCost;
    public String name;
    public int procChance;
    public DamageEffect.EffectType procEffect;
    public int procLevel;
    public PlayerRequirements requisites;
    public String sprite;
    public boolean stackable;
    public int[] traitBonus;
    public ItemType type;
    public int value;
    public WeaponStats weaponStats;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum ItemType {
        GENERAL, WEAPON, SHIELD, ARMOR_HEAD, ARMOR_CHEST, ARMOR_FEET, ARMOR_LEGS, ARMOR_ARMS, POTION, WAND, KEY, RING, BELT, CLOAK, NECKLACE, SCROLL;
    }

    public final ItemAttributes a() {
        if (this.attributes == null) {
            this.attributes = new ItemAttributes("");
        }
        return this.attributes;
    }

    public final String b() {
        String strN;
        WeaponStats weaponStats;
        StringBuilder sb = new StringBuilder("[BLUE]");
        a.w("WEAPON_2HANDED", false, sb, ":[] ");
        String strN2 = a.n("DESC_ITEM_2HANDED", false, sb);
        StringBuilder sb2 = new StringBuilder("[BLUE]");
        a.w("WEAPON_RANGED", false, sb2, ":[] ");
        String strN3 = a.n("DESC_ITEM_RANGED", false, sb2);
        StringBuilder sb3 = new StringBuilder("[BLUE]");
        a.w("WEAPON_LIGHT", false, sb3, ":[] ");
        String strN4 = a.n("DESC_ITEM_LIGHT", false, sb3);
        ItemAttributes itemAttributes = this.attributes;
        if (itemAttributes.orc_slayer > 0) {
            StringBuilder sb4 = new StringBuilder("[BLUE]");
            a.w("ITEM_ORCSLAYER", false, sb4, " ");
            sb4.append(FDUtils.a(itemAttributes.orc_slayer));
            sb4.append(":[] ");
            sb4.append(GameString.b("DESC_ITEM_ORCSLAYER", false).replace("{r}", "" + (itemAttributes.orc_slayer * 2)));
            strN = sb4.toString();
        } else {
            strN = "";
        }
        if (itemAttributes.offhand > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT = a.t(strN, "[BLUE]");
            a.w("ITEM_OFFHAND", false, sbT, ":[] ");
            strN = a.n("DESC_ITEM_OFFHAND", false, sbT);
        }
        if (itemAttributes.orb > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT2 = a.t(strN, "[BLUE]");
            a.w("ITEM_OFFHAND", false, sbT2, ":[] ");
            strN = a.n("DESC_ITEM_OFFHAND_ORB", false, sbT2);
        }
        if (itemAttributes.arcane > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT3 = a.t(strN, "[BLUE]");
            a.w("ITEM_ARCANE", false, sbT3, " ");
            sbT3.append(FDUtils.a(itemAttributes.arcane));
            sbT3.append(":[] ");
            sbT3.append(GameString.b("DESC_ITEM_ARCANE", false).replace("{r}", "" + itemAttributes.arcane));
            strN = sbT3.toString();
        }
        if (itemAttributes.holy > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT4 = a.t(strN, "[BLUE]");
            a.w("ITEM_HOLY", false, sbT4, " ");
            sbT4.append(FDUtils.a(itemAttributes.holy));
            sbT4.append(":[] ");
            sbT4.append(GameString.b("DESC_ITEM_HOLY", false).replace("{r}", "" + (itemAttributes.holy * 2)));
            strN = sbT4.toString();
        }
        if (itemAttributes.banishing > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT5 = a.t(strN, "[BLUE]");
            a.w("ITEM_BANISHING", false, sbT5, " ");
            sbT5.append(FDUtils.a(itemAttributes.banishing));
            sbT5.append(":[] ");
            sbT5.append(GameString.b("DESC_ITEM_BANISHING", false).replace("{r}", "" + (itemAttributes.banishing * 2)));
            strN = sbT5.toString();
        }
        if (itemAttributes.emp > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT6 = a.t(strN, "[BLUE]");
            a.w("ITEM_EMP", false, sbT6, " ");
            sbT6.append(FDUtils.a(itemAttributes.emp));
            sbT6.append(":[] ");
            String strB = GameString.b("DESC_ITEM_EMP", false);
            StringBuilder sb5 = new StringBuilder("");
            int i2 = itemAttributes.emp;
            Item[] itemArr = Rules.f3247a;
            sb5.append(i2 == 1 ? 12 : i2 == 2 ? 16 : 20);
            sbT6.append(strB.replace("{r}", sb5.toString()));
            strN = sbT6.toString();
        }
        if (itemAttributes.beast_slayer > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT7 = a.t(strN, "[BLUE]");
            a.w("ITEM_BEASTSLAYER", false, sbT7, " ");
            sbT7.append(FDUtils.a(itemAttributes.beast_slayer));
            sbT7.append(":[] ");
            sbT7.append(GameString.b("DESC_ITEM_BEASTSLAYER", false).replace("{r}", "" + (itemAttributes.beast_slayer * 2)));
            strN = sbT7.toString();
        }
        if (itemAttributes.slow > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT8 = a.t(strN, "[BLUE]");
            a.w("ITEM_SLOW", false, sbT8, " ");
            sbT8.append(FDUtils.a(itemAttributes.slow));
            sbT8.append(":[] ");
            sbT8.append(GameString.b("DESC_ITEM_SLOW1", false));
            strN = sbT8.toString();
        }
        if (itemAttributes.paralysis == 1) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT9 = a.t(strN, "[BLUE]");
            a.w("ITEM_PARALYSIS", false, sbT9, " ");
            sbT9.append(FDUtils.a(itemAttributes.paralysis));
            sbT9.append(":[] ");
            sbT9.append(GameString.b("DESC_ITEM_PARALYSIS1", false));
            strN = sbT9.toString();
        }
        if (itemAttributes.stun == 1) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT10 = a.t(strN, "[BLUE]");
            a.w("ITEM_STUN", false, sbT10, " ");
            sbT10.append(FDUtils.a(itemAttributes.stun));
            sbT10.append(":[] ");
            sbT10.append(GameString.b("DESC_ITEM_STUN1", false));
            strN = sbT10.toString();
        }
        if (itemAttributes.stun == 2) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT11 = a.t(strN, "[BLUE]");
            a.w("ITEM_STUN", false, sbT11, " ");
            sbT11.append(FDUtils.a(itemAttributes.stun));
            sbT11.append(":[] ");
            sbT11.append(GameString.b("DESC_ITEM_STUN2", false));
            strN = sbT11.toString();
        }
        if (itemAttributes.vorpal > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT12 = a.t(strN, "[BLUE]");
            a.w("ITEM_VORPAL", false, sbT12, " ");
            sbT12.append(FDUtils.a(itemAttributes.vorpal));
            sbT12.append(":[] ");
            sbT12.append(GameString.b("DESC_ITEM_VORPAL", false));
            strN = sbT12.toString();
        }
        if (itemAttributes.silver > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT13 = a.t(strN, "[BLUE]");
            a.w("ITEM_SILVER", false, sbT13, ":[] ");
            strN = a.n("DESC_ITEM_SILVER", false, sbT13);
        }
        if (itemAttributes.stability > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT14 = a.t(strN, "[BLUE]");
            a.w("ITEM_STABILITY", false, sbT14, ":[] ");
            strN = a.n("DESC_ITEM_STABILITY", false, sbT14);
        }
        if (itemAttributes.antirad > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT15 = a.t(strN, "[BLUE]");
            a.w("ITEM_RAD", false, sbT15, ":[] ");
            strN = a.n("DESC_ITEM_ANTIRAD", false, sbT15);
        }
        if (itemAttributes.stunimmunity > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT16 = a.t(strN, "[BLUE]");
            a.w("ITEM_STUNIMMUNITY", false, sbT16, ":[] ");
            strN = a.n("DESC_ITEM_STUNIMMUNITY", false, sbT16);
        }
        if (itemAttributes.vicious > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT17 = a.t(strN, "[BLUE]");
            a.w("ITEM_VICIOUS", false, sbT17, " ");
            sbT17.append(FDUtils.a(itemAttributes.vicious));
            sbT17.append(":[] ");
            sbT17.append(GameString.b("DESC_ITEM_VICIOUS", false).replace("{r}", "" + (itemAttributes.vicious * 2)));
            strN = sbT17.toString();
        }
        if (itemAttributes.detection > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT18 = a.t(strN, "[BLUE]");
            a.w("ITEM_DETECTION", false, sbT18, " ");
            sbT18.append(FDUtils.a(itemAttributes.detection));
            sbT18.append(":[] ");
            sbT18.append(GameString.b("DESC_ITEM_DETECTION", false).replace("{r}", "" + (itemAttributes.detection * 3)));
            strN = sbT18.toString();
        }
        if (itemAttributes.wisdom > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT19 = a.t(strN, "[BLUE]");
            a.w("ITEM_WISDOM", false, sbT19, " ");
            sbT19.append(FDUtils.a(itemAttributes.wisdom));
            sbT19.append(":[] ");
            sbT19.append(GameString.b("DESC_ITEM_WISDOM", false).replace("{r}", "" + (itemAttributes.wisdom * 2)));
            strN = sbT19.toString();
        }
        if (itemAttributes.gossip > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT20 = a.t(strN, "[BLUE]");
            a.w("ITEM_GOSSIP", false, sbT20, " ");
            sbT20.append(FDUtils.a(itemAttributes.gossip));
            sbT20.append(":[] ");
            sbT20.append(GameString.b("DESC_ITEM_GOSSIP", false).replace("{r}", "" + (itemAttributes.gossip * 3)));
            strN = sbT20.toString();
        }
        if (itemAttributes.tinkering > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT21 = a.t(strN, "[BLUE]");
            a.w("ITEM_TINKERING", false, sbT21, " ");
            sbT21.append(FDUtils.a(itemAttributes.tinkering));
            sbT21.append(":[] ");
            sbT21.append(GameString.b("DESC_ITEM_TINKERING", false).replace("{r}", "" + (itemAttributes.tinkering * 5)));
            strN = sbT21.toString();
        }
        if (itemAttributes.shield > 0) {
            if (strN != "") {
                strN = a.k(strN, "\r\n\r\n");
            }
            StringBuilder sbT22 = a.t(strN, "[BLUE]");
            a.w("ITEM_SHIELD", false, sbT22, ":[] ");
            strN = a.n("DESC_ITEM_SHIELD", false, sbT22);
        }
        if (this.type != ItemType.WEAPON || (weaponStats = this.weaponStats) == null) {
            strN4 = "";
        } else {
            if (weaponStats.twohanded && !weaponStats.ranged) {
                strN4 = strN2;
            } else if (weaponStats.ranged && !weaponStats.c()) {
                strN4 = strN3;
            } else if (!this.weaponStats.light) {
                strN4 = "";
            }
            WeaponStats weaponStats2 = this.weaponStats;
            if (weaponStats2.wand) {
                strN4 = strN3;
            }
            if (weaponStats2.staff) {
                strN4 = strN3 + "\r\n\r\n" + strN2;
            }
        }
        if (strN.equals("") || strN4.equals("")) {
            return a.k(strN, strN4);
        }
        return strN + "\r\n\r\n" + strN4;
    }

    public final String c() {
        return this.description;
    }

    public final int d(CharacterResistances.ResistanceType resistanceType) {
        return this.ResistanceBonus.a(resistanceType);
    }

    public final CharacterResistances e() {
        return this.ResistanceBonus;
    }

    public final int f(int i2) {
        if (this.traitBonus == null) {
            this.traitBonus = new int[6];
        }
        return this.traitBonus[i2];
    }

    public final boolean g(CharacterSheet characterSheet) {
        ItemType itemType = this.type;
        return (itemType == ItemType.POTION || itemType == ItemType.SCROLL || itemType == ItemType.WAND) && characterSheet.c0(this.manaCost) && this.classes.c(characterSheet.stats.c()).booleanValue() && this.requisites.a(characterSheet, false);
    }

    public final void h(String str) {
        this.ResistanceBonus = new CharacterResistances(str);
    }

    public final void i(String str) {
        this.description = str;
    }
}
