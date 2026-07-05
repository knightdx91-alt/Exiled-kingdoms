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

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
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
    public static final class ItemType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ItemType f3229a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final ItemType f3230b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final ItemType f3231c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final ItemType f3232d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final ItemType f3233e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final ItemType f3234f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final ItemType f3235g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final ItemType f3236h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final ItemType f3237i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final ItemType f3238j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final ItemType f3239k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final ItemType f3240l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final ItemType f3241m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final ItemType f3242n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final ItemType f3243o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final ItemType f3244p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private static final /* synthetic */ ItemType[] f3245q;

        static {
            ItemType itemType = new ItemType("GENERAL", 0);
            f3229a = itemType;
            ItemType itemType2 = new ItemType("WEAPON", 1);
            f3230b = itemType2;
            ItemType itemType3 = new ItemType("SHIELD", 2);
            f3231c = itemType3;
            ItemType itemType4 = new ItemType("ARMOR_HEAD", 3);
            f3232d = itemType4;
            ItemType itemType5 = new ItemType("ARMOR_CHEST", 4);
            f3233e = itemType5;
            ItemType itemType6 = new ItemType("ARMOR_FEET", 5);
            f3234f = itemType6;
            ItemType itemType7 = new ItemType("ARMOR_LEGS", 6);
            f3235g = itemType7;
            ItemType itemType8 = new ItemType("ARMOR_ARMS", 7);
            f3236h = itemType8;
            ItemType itemType9 = new ItemType("POTION", 8);
            f3237i = itemType9;
            ItemType itemType10 = new ItemType("WAND", 9);
            f3238j = itemType10;
            ItemType itemType11 = new ItemType("KEY", 10);
            f3239k = itemType11;
            ItemType itemType12 = new ItemType("RING", 11);
            f3240l = itemType12;
            ItemType itemType13 = new ItemType("BELT", 12);
            f3241m = itemType13;
            ItemType itemType14 = new ItemType("CLOAK", 13);
            f3242n = itemType14;
            ItemType itemType15 = new ItemType("NECKLACE", 14);
            f3243o = itemType15;
            ItemType itemType16 = new ItemType("SCROLL", 15);
            f3244p = itemType16;
            f3245q = new ItemType[]{itemType, itemType2, itemType3, itemType4, itemType5, itemType6, itemType7, itemType8, itemType9, itemType10, itemType11, itemType12, itemType13, itemType14, itemType15, itemType16};
        }

        private ItemType() {
            throw null;
        }

        public static ItemType valueOf(String str) {
            return (ItemType) Enum.valueOf(ItemType.class, str);
        }

        public static ItemType[] values() {
            return (ItemType[]) f3245q.clone();
        }
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
        if (this.type != ItemType.f3230b || (weaponStats = this.weaponStats) == null) {
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
        return (itemType == ItemType.f3237i || itemType == ItemType.f3244p || itemType == ItemType.f3238j) && characterSheet.c0(this.manaCost) && this.classes.c(characterSheet.stats.c()).booleanValue() && this.requisites.a(characterSheet, false);
    }

    public final void h(String str) {
        this.ResistanceBonus = new CharacterResistances(str);
    }

    public final void i(String str) {
        this.description = str;
    }
}
