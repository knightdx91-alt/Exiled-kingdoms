package net.fdgames.Rules;

import a.a;
import androidx.drawerlayout.widget.Qm.ReTXwDyZpZSd;
import com.badlogic.gdx.graphics.g3d.loader.rr.fIsXh;
import com.badlogic.gdx.math.tuHF.LpvdRIktEBw;
import n0.WOA.jzidqMPaLNVH;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameLogic.PlayerRequirements;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import o.Oeoo.vIBRkbZbNjpf;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
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

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final ItemType f3450b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final ItemType f3451c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final ItemType f3452d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final ItemType f3453e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final ItemType f3454f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final ItemType f3455g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final ItemType f3456h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final ItemType f3457i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final ItemType f3458j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final ItemType f3459k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final ItemType f3460l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final ItemType f3461m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final ItemType f3462n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final ItemType f3463o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final ItemType f3464p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final ItemType f3465q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private static final /* synthetic */ ItemType[] f3466r;

        static {
            ItemType itemType = new ItemType("GENERAL", 0);
            f3450b = itemType;
            ItemType itemType2 = new ItemType("WEAPON", 1);
            f3451c = itemType2;
            ItemType itemType3 = new ItemType("SHIELD", 2);
            f3452d = itemType3;
            ItemType itemType4 = new ItemType("ARMOR_HEAD", 3);
            f3453e = itemType4;
            ItemType itemType5 = new ItemType("ARMOR_CHEST", 4);
            f3454f = itemType5;
            ItemType itemType6 = new ItemType("ARMOR_FEET", 5);
            f3455g = itemType6;
            ItemType itemType7 = new ItemType("ARMOR_LEGS", 6);
            f3456h = itemType7;
            ItemType itemType8 = new ItemType("ARMOR_ARMS", 7);
            f3457i = itemType8;
            ItemType itemType9 = new ItemType("POTION", 8);
            f3458j = itemType9;
            ItemType itemType10 = new ItemType(ReTXwDyZpZSd.NCNwhYiEuj, 9);
            f3459k = itemType10;
            ItemType itemType11 = new ItemType("KEY", 10);
            f3460l = itemType11;
            ItemType itemType12 = new ItemType("RING", 11);
            f3461m = itemType12;
            ItemType itemType13 = new ItemType("BELT", 12);
            f3462n = itemType13;
            ItemType itemType14 = new ItemType("CLOAK", 13);
            f3463o = itemType14;
            ItemType itemType15 = new ItemType("NECKLACE", 14);
            f3464p = itemType15;
            ItemType itemType16 = new ItemType(jzidqMPaLNVH.vpYKjPAmiYUcaa, 15);
            f3465q = itemType16;
            f3466r = new ItemType[]{itemType, itemType2, itemType3, itemType4, itemType5, itemType6, itemType7, itemType8, itemType9, itemType10, itemType11, itemType12, itemType13, itemType14, itemType15, itemType16};
        }

        private ItemType() {
            throw null;
        }

        public static ItemType valueOf(String str) {
            return (ItemType) Enum.valueOf(ItemType.class, str);
        }

        public static ItemType[] values() {
            return (ItemType[]) f3466r.clone();
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
        a.v("WEAPON_2HANDED", false, sb, ":[] ");
        String strN2 = a.n("DESC_ITEM_2HANDED", false, sb);
        StringBuilder sb2 = new StringBuilder("[BLUE]");
        a.v("WEAPON_RANGED", false, sb2, ":[] ");
        String strN3 = a.n("DESC_ITEM_RANGED", false, sb2);
        StringBuilder sb3 = new StringBuilder("[BLUE]");
        a.v("WEAPON_LIGHT", false, sb3, ":[] ");
        String strN4 = a.n(vIBRkbZbNjpf.DOUAyGSpysxG, false, sb3);
        ItemAttributes itemAttributes = this.attributes;
        if (itemAttributes.orc_slayer > 0) {
            StringBuilder sb4 = new StringBuilder("[BLUE]");
            a.v("ITEM_ORCSLAYER", false, sb4, " ");
            sb4.append(FDUtils.a(itemAttributes.orc_slayer));
            sb4.append(":[] ");
            sb4.append(GameString.b("DESC_ITEM_ORCSLAYER", false).replace("{r}", "" + (itemAttributes.orc_slayer * 2)));
            strN = sb4.toString();
        } else {
            strN = "";
        }
        if (itemAttributes.offhand > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS = a.s(strN, "[BLUE]");
            a.v("ITEM_OFFHAND", false, sbS, ":[] ");
            strN = a.n("DESC_ITEM_OFFHAND", false, sbS);
        }
        if (itemAttributes.orb > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS2 = a.s(strN, "[BLUE]");
            a.v("ITEM_OFFHAND", false, sbS2, ":[] ");
            strN = a.n("DESC_ITEM_OFFHAND_ORB", false, sbS2);
        }
        if (itemAttributes.arcane > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS3 = a.s(strN, "[BLUE]");
            a.v("ITEM_ARCANE", false, sbS3, " ");
            sbS3.append(FDUtils.a(itemAttributes.arcane));
            sbS3.append(":[] ");
            sbS3.append(GameString.b("DESC_ITEM_ARCANE", false).replace("{r}", "" + itemAttributes.arcane));
            strN = sbS3.toString();
        }
        if (itemAttributes.holy > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS4 = a.s(strN, "[BLUE]");
            a.v("ITEM_HOLY", false, sbS4, " ");
            sbS4.append(FDUtils.a(itemAttributes.holy));
            sbS4.append(":[] ");
            sbS4.append(GameString.b("DESC_ITEM_HOLY", false).replace("{r}", "" + (itemAttributes.holy * 2)));
            strN = sbS4.toString();
        }
        if (itemAttributes.banishing > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS5 = a.s(strN, "[BLUE]");
            a.v("ITEM_BANISHING", false, sbS5, " ");
            sbS5.append(FDUtils.a(itemAttributes.banishing));
            sbS5.append(":[] ");
            sbS5.append(GameString.b("DESC_ITEM_BANISHING", false).replace("{r}", "" + (itemAttributes.banishing * 2)));
            strN = sbS5.toString();
        }
        if (itemAttributes.emp > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS6 = a.s(strN, "[BLUE]");
            a.v("ITEM_EMP", false, sbS6, " ");
            sbS6.append(FDUtils.a(itemAttributes.emp));
            sbS6.append(":[] ");
            String strB = GameString.b("DESC_ITEM_EMP", false);
            StringBuilder sb5 = new StringBuilder("");
            int i2 = itemAttributes.emp;
            Item[] itemArr = Rules.f3468a;
            sb5.append(i2 == 1 ? 12 : i2 == 2 ? 16 : 20);
            sbS6.append(strB.replace("{r}", sb5.toString()));
            strN = sbS6.toString();
        }
        if (itemAttributes.beast_slayer > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS7 = a.s(strN, "[BLUE]");
            a.v("ITEM_BEASTSLAYER", false, sbS7, " ");
            sbS7.append(FDUtils.a(itemAttributes.beast_slayer));
            sbS7.append(":[] ");
            sbS7.append(GameString.b("DESC_ITEM_BEASTSLAYER", false).replace("{r}", "" + (itemAttributes.beast_slayer * 2)));
            strN = sbS7.toString();
        }
        if (itemAttributes.slow > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS8 = a.s(strN, "[BLUE]");
            a.v("ITEM_SLOW", false, sbS8, " ");
            sbS8.append(FDUtils.a(itemAttributes.slow));
            sbS8.append(":[] ");
            sbS8.append(GameString.b("DESC_ITEM_SLOW1", false));
            strN = sbS8.toString();
        }
        if (itemAttributes.paralysis == 1) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS9 = a.s(strN, "[BLUE]");
            a.v("ITEM_PARALYSIS", false, sbS9, " ");
            sbS9.append(FDUtils.a(itemAttributes.paralysis));
            sbS9.append(":[] ");
            sbS9.append(GameString.b("DESC_ITEM_PARALYSIS1", false));
            strN = sbS9.toString();
        }
        if (itemAttributes.stun == 1) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS10 = a.s(strN, "[BLUE]");
            a.v("ITEM_STUN", false, sbS10, " ");
            sbS10.append(FDUtils.a(itemAttributes.stun));
            sbS10.append(":[] ");
            sbS10.append(GameString.b("DESC_ITEM_STUN1", false));
            strN = sbS10.toString();
        }
        if (itemAttributes.stun == 2) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS11 = a.s(strN, "[BLUE]");
            a.v("ITEM_STUN", false, sbS11, " ");
            sbS11.append(FDUtils.a(itemAttributes.stun));
            sbS11.append(":[] ");
            sbS11.append(GameString.b("DESC_ITEM_STUN2", false));
            strN = sbS11.toString();
        }
        if (itemAttributes.vorpal > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS12 = a.s(strN, "[BLUE]");
            a.v("ITEM_VORPAL", false, sbS12, " ");
            sbS12.append(FDUtils.a(itemAttributes.vorpal));
            sbS12.append(":[] ");
            sbS12.append(GameString.b("DESC_ITEM_VORPAL", false));
            strN = sbS12.toString();
        }
        if (itemAttributes.silver > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS13 = a.s(strN, "[BLUE]");
            a.v("ITEM_SILVER", false, sbS13, ":[] ");
            strN = a.n("DESC_ITEM_SILVER", false, sbS13);
        }
        if (itemAttributes.stability > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS14 = a.s(strN, "[BLUE]");
            a.v(LpvdRIktEBw.pMwquzlvIIaE, false, sbS14, ":[] ");
            strN = a.n("DESC_ITEM_STABILITY", false, sbS14);
        }
        if (itemAttributes.antirad > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS15 = a.s(strN, "[BLUE]");
            a.v("ITEM_RAD", false, sbS15, ":[] ");
            strN = a.n("DESC_ITEM_ANTIRAD", false, sbS15);
        }
        if (itemAttributes.stunimmunity > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS16 = a.s(strN, "[BLUE]");
            a.v("ITEM_STUNIMMUNITY", false, sbS16, ":[] ");
            strN = a.n("DESC_ITEM_STUNIMMUNITY", false, sbS16);
        }
        if (itemAttributes.vicious > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS17 = a.s(strN, "[BLUE]");
            a.v("ITEM_VICIOUS", false, sbS17, " ");
            sbS17.append(FDUtils.a(itemAttributes.vicious));
            sbS17.append(":[] ");
            sbS17.append(GameString.b("DESC_ITEM_VICIOUS", false).replace("{r}", "" + (itemAttributes.vicious * 2)));
            strN = sbS17.toString();
        }
        if (itemAttributes.detection > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS18 = a.s(strN, "[BLUE]");
            a.v("ITEM_DETECTION", false, sbS18, " ");
            sbS18.append(FDUtils.a(itemAttributes.detection));
            sbS18.append(":[] ");
            sbS18.append(GameString.b("DESC_ITEM_DETECTION", false).replace("{r}", "" + (itemAttributes.detection * 3)));
            strN = sbS18.toString();
        }
        if (itemAttributes.wisdom > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS19 = a.s(strN, "[BLUE]");
            a.v("ITEM_WISDOM", false, sbS19, " ");
            sbS19.append(FDUtils.a(itemAttributes.wisdom));
            sbS19.append(":[] ");
            sbS19.append(GameString.b("DESC_ITEM_WISDOM", false).replace("{r}", "" + (itemAttributes.wisdom * 2)));
            strN = sbS19.toString();
        }
        if (itemAttributes.gossip > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS20 = a.s(strN, "[BLUE]");
            a.v("ITEM_GOSSIP", false, sbS20, " ");
            sbS20.append(FDUtils.a(itemAttributes.gossip));
            sbS20.append(":[] ");
            sbS20.append(GameString.b("DESC_ITEM_GOSSIP", false).replace("{r}", "" + (itemAttributes.gossip * 3)));
            strN = sbS20.toString();
        }
        if (itemAttributes.tinkering > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS21 = a.s(strN, "[BLUE]");
            a.v("ITEM_TINKERING", false, sbS21, " ");
            sbS21.append(FDUtils.a(itemAttributes.tinkering));
            sbS21.append(":[] ");
            sbS21.append(GameString.b(fIsXh.MJH, false).replace("{r}", "" + (itemAttributes.tinkering * 5)));
            strN = sbS21.toString();
        }
        if (itemAttributes.shield > 0) {
            if (strN != "") {
                strN = a.z(strN, "\r\n\r\n");
            }
            StringBuilder sbS22 = a.s(strN, "[BLUE]");
            a.v("ITEM_SHIELD", false, sbS22, ":[] ");
            strN = a.n("DESC_ITEM_SHIELD", false, sbS22);
        }
        if (this.type != ItemType.f3451c || (weaponStats = this.weaponStats) == null) {
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
            return a.z(strN, strN4);
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
        return (itemType == ItemType.f3458j || itemType == ItemType.f3465q || itemType == ItemType.f3459k) && characterSheet.c0(this.manaCost) && this.classes.c(characterSheet.stats.c()).booleanValue() && this.requisites.a(characterSheet, false);
    }

    public final void h(String str) {
        this.ResistanceBonus = new CharacterResistances(str);
    }

    public final void i(String str) {
        this.description = str;
    }
}
