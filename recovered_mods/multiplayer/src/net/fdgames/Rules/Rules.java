package net.fdgames.Rules;

import android.util.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.l;
import com.google.android.gms.common.api.Mcc.KOdB;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.quest.Quests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameLogic.PlayerRequirements;
import net.fdgames.GameLogic.ScriptedAction;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameLog;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.GameText;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;
import net.fdgames.assets.GameAssets;
import o.Oeoo.vIBRkbZbNjpf;
import z0.ow.DkgvDLHsdXPkn;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Rules {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Item[] f3468a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Spawn[] f3469b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static GameText[] f3470c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static GameText[] f3471d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static GameText[] f3472e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int[] f3473f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static ArrayList<LootItem> f3474g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static ArrayList<WeaponStats> f3475h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static ArrayList<RewardTable> f3476i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static TrapData f3477j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static MapEffectData f3478k;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class CharacterClass {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final CharacterClass f3479b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final CharacterClass f3480c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final CharacterClass f3481d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final CharacterClass f3482e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final CharacterClass f3483f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final CharacterClass f3484g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final CharacterClass f3485h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private static final /* synthetic */ CharacterClass[] f3486i;

        static {
            CharacterClass characterClass = new CharacterClass("WARRIOR", 0);
            f3479b = characterClass;
            CharacterClass characterClass2 = new CharacterClass("ROGUE", 1);
            f3480c = characterClass2;
            CharacterClass characterClass3 = new CharacterClass("CLERIC", 2);
            f3481d = characterClass3;
            CharacterClass characterClass4 = new CharacterClass("WIZARD", 3);
            f3482e = characterClass4;
            CharacterClass characterClass5 = new CharacterClass("MONSTER", 4);
            f3483f = characterClass5;
            CharacterClass characterClass6 = new CharacterClass("GENERAL", 5);
            f3484g = characterClass6;
            CharacterClass characterClass7 = new CharacterClass("NONE", 6);
            f3485h = characterClass7;
            f3486i = new CharacterClass[]{characterClass, characterClass2, characterClass3, characterClass4, characterClass5, characterClass6, characterClass7};
        }

        private CharacterClass() {
            throw null;
        }

        public static String a(CharacterClass characterClass) {
            int iOrdinal = characterClass.ordinal();
            return iOrdinal != 0 ? iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? iOrdinal != 4 ? iOrdinal != 5 ? "" : GameString.b("GENERAL_CLASS", false) : GameString.b("MONSTER", false) : GameString.b("MAGE", false) : GameString.b("CLERIC", false) : GameString.b("ROGUE", false) : GameString.b("WARRIOR", false);
        }

        public static CharacterClass valueOf(String str) {
            return (CharacterClass) Enum.valueOf(CharacterClass.class, str);
        }

        public static CharacterClass[] values() {
            return (CharacterClass[]) f3486i.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class CharacterRace {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final CharacterRace f3487b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final CharacterRace f3488c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final CharacterRace f3489d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final CharacterRace f3490e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final CharacterRace f3491f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final CharacterRace f3492g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final CharacterRace f3493h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final CharacterRace f3494i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final CharacterRace f3495j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final CharacterRace f3496k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private static final /* synthetic */ CharacterRace[] f3497l;

        static {
            CharacterRace characterRace = new CharacterRace("HUMAN", 0);
            f3487b = characterRace;
            CharacterRace characterRace2 = new CharacterRace("HALFLING", 1);
            CharacterRace characterRace3 = new CharacterRace("GOBLIN", 2);
            f3488c = characterRace3;
            CharacterRace characterRace4 = new CharacterRace("ORC", 3);
            f3489d = characterRace4;
            CharacterRace characterRace5 = new CharacterRace("MINOTAUR", 4);
            f3490e = characterRace5;
            CharacterRace characterRace6 = new CharacterRace(vIBRkbZbNjpf.CdzGypADvzGZl, 5);
            f3491f = characterRace6;
            CharacterRace characterRace7 = new CharacterRace("MONSTER_WEAK", 6);
            f3492g = characterRace7;
            CharacterRace characterRace8 = new CharacterRace("MONSTER_STRONG", 7);
            f3493h = characterRace8;
            CharacterRace characterRace9 = new CharacterRace("MINIBOSS", 8);
            f3494i = characterRace9;
            CharacterRace characterRace10 = new CharacterRace("BOSS", 9);
            f3495j = characterRace10;
            CharacterRace characterRace11 = new CharacterRace("NPC", 10);
            f3496k = characterRace11;
            f3497l = new CharacterRace[]{characterRace, characterRace2, characterRace3, characterRace4, characterRace5, characterRace6, characterRace7, characterRace8, characterRace9, characterRace10, characterRace11};
        }

        private CharacterRace() {
            throw null;
        }

        public static CharacterRace valueOf(String str) {
            return (CharacterRace) Enum.valueOf(CharacterRace.class, str);
        }

        public static CharacterRace[] values() {
            return (CharacterRace[]) f3497l.clone();
        }
    }

    static {
        new Item();
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04ec  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x052e  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0575 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0577  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x05cd  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05d5  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0430 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a() {
        int i2;
        String str;
        GameText gameText;
        CharacterRace characterRace;
        String[] strArr;
        int i3;
        Item.ItemType itemType;
        int i4;
        GameText[] gameTextArr;
        GameText gameText2;
        int i5;
        GameText[] gameTextArr2;
        GameText gameText3;
        String str2;
        List listAsList;
        String str3;
        String str4;
        String str5;
        String str6;
        boolean z2;
        Item item;
        Item.ItemType itemType2;
        String str7;
        boolean z3;
        boolean z4;
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 1/12: loading skills....");
        Skills.e();
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 2/12: loading plants....");
        ArrayList<Plant> arrayList = Plants.f3467a;
        Plants.f3467a = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/rules/plants.txt").readString().split("\n");
        char c2 = 0;
        int i6 = 0;
        while (true) {
            int length = strArrSplit.length;
            i2 = -1;
            str = DkgvDLHsdXPkn.ORDXjJXlxkPluIK;
            if (i6 >= length) {
                break;
            }
            String strReplace = strArrSplit[i6].replace("\n", "");
            strArrSplit[i6] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i6] = strReplace2;
            String[] strArrSplit2 = strReplace2.split(str, -1);
            String str8 = strArrSplit2[0];
            String str9 = strArrSplit2[1];
            String str10 = strArrSplit2[2];
            Plant plant = new Plant();
            plant.ID = str8;
            plant.item_ID = parseIntSafe(str9);
            plant.chance = parseIntSafe(str10);
            Plants.f3467a.add(plant);
            i6++;
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 3/12: loading levels....");
        int[] iArr = new int[Quests.SELECT_COMPLETED_UNCLAIMED];
        f3473f = iArr;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 300;
        iArr[3] = 1000;
        iArr[4] = 3000;
        iArr[5] = 6000;
        char c3 = 6;
        iArr[6] = 11000;
        char c4 = 7;
        iArr[7] = 18000;
        iArr[8] = 27000;
        iArr[9] = 38000;
        iArr[10] = 50000;
        iArr[11] = 64000;
        iArr[12] = 80000;
        iArr[13] = 100000;
        iArr[14] = 125000;
        iArr[15] = 160000;
        iArr[16] = 200000;
        iArr[17] = 250000;
        iArr[18] = 310000;
        iArr[19] = 380000;
        iArr[20] = 460000;
        iArr[21] = 560000;
        iArr[22] = 700000;
        iArr[23] = 1000000;
        iArr[24] = 1500000;
        iArr[25] = 2400000;
        for (int i7 = 26; i7 <= 100; i7++) {
            int[] iArr2 = f3473f;
            iArr2[i7] = iArr2[i7 - 1] + 1000000;
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 4/12: loading weapons....");
        f3475h = new ArrayList<>();
        String[] strArrSplit3 = Gdx.files.internal("data/rules/weapons.txt").readString().split("\n");
        int i8 = 1;
        while (i8 < strArrSplit3.length) {
            String strReplace3 = strArrSplit3[i8].replace("\n", "");
            strArrSplit3[i8] = strReplace3;
            String strReplace4 = strReplace3.replace("\r", "");
            strArrSplit3[i8] = strReplace4;
            WeaponStats weaponStats = new WeaponStats();
            String[] strArrSplit4 = strReplace4.replace("\"", "").split(str, -1);
            weaponStats.id = strArrSplit4[0];
            weaponStats.minDamage = parseIntSafe(strArrSplit4[2]);
            weaponStats.maxDamage = parseIntSafe(strArrSplit4[3]);
            weaponStats.critChance = parseIntSafe(strArrSplit4[4]);
            weaponStats.speed = parseIntSafe(strArrSplit4[c3]);
            weaponStats.reach = parseIntSafe(strArrSplit4[c4]);
            weaponStats.twohanded = false;
            if (strArrSplit4[8].equals("")) {
                z3 = true;
            } else {
                z3 = true;
                weaponStats.twohanded = true;
            }
            weaponStats.ranged = false;
            if (!strArrSplit4[9].equals("")) {
                weaponStats.ranged = z3;
                if (strArrSplit4[11].equals("")) {
                    weaponStats.ranged_speed = 450.0f;
                } else {
                    weaponStats.ranged_speed = parseIntSafe(strArrSplit4[11]);
                }
            }
            if (strArrSplit4[10].equals("")) {
                z4 = true;
            } else {
                z4 = true;
                weaponStats.light = true;
            }
            if (strArrSplit4[12].contains("w")) {
                weaponStats.wand = z4;
            }
            if (strArrSplit4[12].contains("s")) {
                weaponStats.staff = z4;
            }
            weaponStats.sprite = strArrSplit4[13];
            weaponStats.damageType = Damage.b(strArrSplit4[14]);
            if (strArrSplit4[15].trim().equals("")) {
                weaponStats.has_secondary_damage = false;
            } else {
                weaponStats.has_secondary_damage = true;
                weaponStats.secondary_Damage = parseIntSafe(strArrSplit4[15]);
                weaponStats.secondary_damageType = Damage.b(strArrSplit4[16]);
            }
            if (strArrSplit4[17].trim().equals("")) {
                weaponStats.hasProc = false;
            } else {
                weaponStats.hasProc = true;
                weaponStats.procEffect = WeaponStats.d(strArrSplit4[17]);
                weaponStats.procChance = parseIntSafe(strArrSplit4[18]);
                weaponStats.procLevel = parseIntSafe(strArrSplit4[19]);
            }
            f3475h.add(weaponStats);
            i8++;
            c3 = 6;
            c4 = 7;
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 5/12: loading items text....");
        String[] strArrSplit5 = Gdx.files.internal("data/rules/items_text.txt").readString("UTF-8").replaceAll("\\r+", "").split("\n");
        f3471d = new GameText[strArrSplit5.length - 1];
        for (int i9 = 1; i9 < strArrSplit5.length; i9++) {
            f3471d[i9 - 1] = new GameText(strArrSplit5[i9], true, false);
        }
        f3472e = new GameText[strArrSplit5.length - 1];
        for (int i10 = 1; i10 < strArrSplit5.length; i10++) {
            f3472e[i10 - 1] = new GameText(strArrSplit5[i10], true, true);
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 6/12: loading items....");
        String[] strArrSplit6 = Gdx.files.internal("data/rules/items.txt").readString().split("\n");
        f3468a = new Item[strArrSplit6.length - 1];
        int i11 = 0;
        while (i11 < f3468a.length) {
            int i12 = i11 + 1;
            String strReplace5 = strArrSplit6[i12].replace("\n", "");
            strArrSplit6[i12] = strReplace5;
            String strReplace6 = strReplace5.replace("\r", "");
            strArrSplit6[i12] = strReplace6;
            String[] strArrSplit7 = strReplace6.split(str, i2);
            if (parseIntSafe(strArrSplit7[c2]) != 0) {
                f3468a[i11] = new Item();
                f3468a[i11].weaponStats = new WeaponStats();
                f3468a[i11].item_ID = parseIntSafe(strArrSplit7[c2]);
                Item item2 = f3468a[i11];
                String strTrim = strArrSplit7[2].toLowerCase(Locale.ENGLISH).trim();
                boolean zEquals = strTrim.equals("general");
                Item.ItemType itemType3 = Item.ItemType.f3450b;
                Item.ItemType itemType4 = Item.ItemType.f3465q;
                Item.ItemType itemType5 = Item.ItemType.f3458j;
                if (zEquals) {
                    itemType = itemType3;
                    item2.type = itemType;
                    Item item3 = f3468a[i11];
                    String str11 = strArrSplit7[0];
                    strArr = strArrSplit6;
                    i4 = 0;
                    while (true) {
                        gameTextArr = f3471d;
                        i3 = i12;
                        if (i4 >= gameTextArr.length) {
                        }
                        i4++;
                        i12 = i3;
                    }
                    f3468a[i11].name = gameText2 != null ? gameText2.get() : "";
                    Item item4 = f3468a[i11];
                    String str12 = strArrSplit7[0];
                    i5 = 0;
                    while (true) {
                        gameTextArr2 = f3472e;
                        if (i5 >= gameTextArr2.length) {
                        }
                        i5++;
                    }
                    item4.i((gameText3 != null ? gameText3.get() : "").replace("\"", ""));
                    str2 = strArrSplit7[3];
                    if (str2 != "") {
                    }
                    if (!strArrSplit7[4].equals("")) {
                    }
                    f3468a[i11].h(strArrSplit7[5]);
                    Item item5 = f3468a[i11];
                    String str13 = strArrSplit7[6];
                    item5.traitBonus = new int[6];
                    listAsList = Arrays.asList(str13.trim().replace("\"", "").split(","));
                    if (listAsList.size() == 6) {
                    }
                    f3468a[i11].attributes = new ItemAttributes(strArrSplit7[7]);
                    if (!strArrSplit7[8].equals("")) {
                    }
                    Item item6 = f3468a[i11];
                    item6.icon = strArrSplit7[9];
                    str3 = strArrSplit7[10];
                    if (str3 != "") {
                    }
                    str4 = strArrSplit7[11];
                    if (str4 != "") {
                    }
                    Item item7 = f3468a[i11];
                    str5 = strArrSplit7[12];
                    String str14 = strArrSplit7[13];
                    str6 = strArrSplit7[14];
                    String str15 = strArrSplit7[15];
                    String str16 = strArrSplit7[16];
                    item7.getClass();
                    if (str5.equals("")) {
                    }
                    if (!str6.equals("")) {
                    }
                    Item item8 = f3468a[i11];
                    item8.sprite = strArrSplit7[17];
                    item8.OnTake = new ActionsSet(strArrSplit7[18]);
                    f3468a[i11].OnTakeconditions = new ConditionsSet(strArrSplit7[19]);
                    f3468a[i11].OnUse = new ActionsSet(strArrSplit7[20]);
                    item = f3468a[i11];
                    itemType2 = item.type;
                    if (itemType2 == itemType5) {
                    }
                } else {
                    if (strTrim.equals("weapon")) {
                        itemType3 = Item.ItemType.f3451c;
                    } else if (strTrim.equals(KOdB.OqHsSgBFMQLYAaW)) {
                        itemType3 = Item.ItemType.f3452d;
                    } else if (strTrim.equals("armor_arms")) {
                        itemType3 = Item.ItemType.f3457i;
                    } else if (strTrim.equals("armor_chest")) {
                        itemType3 = Item.ItemType.f3454f;
                    } else if (strTrim.equals("armor_feet")) {
                        itemType3 = Item.ItemType.f3455g;
                    } else if (strTrim.equals("armor_legs")) {
                        itemType3 = Item.ItemType.f3456h;
                    } else if (strTrim.equals("armor_head")) {
                        itemType3 = Item.ItemType.f3453e;
                    } else if (strTrim.equals(KOdB.fezbGZci)) {
                        itemType3 = Item.ItemType.f3460l;
                    } else {
                        if (strTrim.equals("potion")) {
                            itemType = itemType5;
                        } else if (strTrim.equals("wand")) {
                            itemType3 = Item.ItemType.f3459k;
                        } else if (strTrim.equals("ring")) {
                            itemType3 = Item.ItemType.f3461m;
                        } else if (strTrim.equals("belt")) {
                            itemType3 = Item.ItemType.f3462n;
                        } else if (strTrim.equals("cloak")) {
                            itemType3 = Item.ItemType.f3463o;
                        } else if (strTrim.equals("necklace")) {
                            itemType3 = Item.ItemType.f3464p;
                        } else if (strTrim.equals(DkgvDLHsdXPkn.ustPsQQpGhQOvRG)) {
                            itemType = itemType4;
                        }
                        item2.type = itemType;
                        Item item32 = f3468a[i11];
                        String str112 = strArrSplit7[0];
                        strArr = strArrSplit6;
                        i4 = 0;
                        while (true) {
                            gameTextArr = f3471d;
                            i3 = i12;
                            if (i4 >= gameTextArr.length) {
                                System.out.println("WARNING: item name not found, " + str112);
                                gameText2 = null;
                                break;
                            }
                            if (gameTextArr[i4].tag.equals(str112)) {
                                gameText2 = f3471d[i4];
                                break;
                            } else {
                                i4++;
                                i12 = i3;
                            }
                        }
                        f3468a[i11].name = gameText2 != null ? gameText2.get() : "";
                        Item item42 = f3468a[i11];
                        String str122 = strArrSplit7[0];
                        i5 = 0;
                        while (true) {
                            gameTextArr2 = f3472e;
                            if (i5 >= gameTextArr2.length) {
                                System.out.println("WARNING: item description not found, " + str122);
                                gameText3 = null;
                                break;
                            }
                            if (gameTextArr2[i5].tag.equals(str122)) {
                                gameText3 = f3472e[i5];
                                break;
                            }
                            i5++;
                        }
                        item42.i((gameText3 != null ? gameText3.get() : "").replace("\"", ""));
                        str2 = strArrSplit7[3];
                        if (str2 != "") {
                            f3468a[i11].weaponStats = j(str2);
                        }
                        if (!strArrSplit7[4].equals("")) {
                            f3468a[i11].armorBonus = parseIntSafe(strArrSplit7[4]);
                        }
                        f3468a[i11].h(strArrSplit7[5]);
                        Item item52 = f3468a[i11];
                        String str132 = strArrSplit7[6];
                        item52.traitBonus = new int[6];
                        listAsList = Arrays.asList(str132.trim().replace("\"", "").split(","));
                        if (listAsList.size() == 6) {
                            int i13 = 0;
                            for (int i14 = 6; i13 < i14; i14 = 6) {
                                item52.traitBonus[i13] = parseIntSafe((String) listAsList.get(i13));
                                i13++;
                            }
                        }
                        f3468a[i11].attributes = new ItemAttributes(strArrSplit7[7]);
                        if (!strArrSplit7[8].equals("")) {
                            f3468a[i11].value = parseIntSafe(strArrSplit7[8]);
                        }
                        Item item62 = f3468a[i11];
                        item62.icon = strArrSplit7[9];
                        str3 = strArrSplit7[10];
                        if (str3 != "") {
                            item62.HPBonus = parseIntSafe(str3);
                        }
                        str4 = strArrSplit7[11];
                        if (str4 != "") {
                            f3468a[i11].ManaBonus = parseIntSafe(str4);
                        }
                        Item item72 = f3468a[i11];
                        str5 = strArrSplit7[12];
                        String str142 = strArrSplit7[13];
                        str6 = strArrSplit7[14];
                        String str152 = strArrSplit7[15];
                        String str162 = strArrSplit7[16];
                        item72.getClass();
                        if (str5.equals("")) {
                            z2 = true;
                        } else {
                            item72.damageBonus = parseIntSafe(str5);
                            item72.damageBonusType = Damage.b(str142);
                            z2 = true;
                            item72.hasDamageBonus = true;
                        }
                        if (!str6.equals("")) {
                            item72.hasProc = z2;
                            item72.procEffect = WeaponStats.d(str6);
                            item72.procChance = parseIntSafe(str152);
                            item72.procLevel = parseIntSafe(str162);
                        }
                        Item item82 = f3468a[i11];
                        item82.sprite = strArrSplit7[17];
                        item82.OnTake = new ActionsSet(strArrSplit7[18]);
                        f3468a[i11].OnTakeconditions = new ConditionsSet(strArrSplit7[19]);
                        f3468a[i11].OnUse = new ActionsSet(strArrSplit7[20]);
                        item = f3468a[i11];
                        itemType2 = item.type;
                        if (itemType2 == itemType5 || itemType2 == itemType4) {
                            item.OnUse.actions.add(new ScriptedAction(ScriptedAction.ActionType.f3363g, Integer.toString(f3468a[i11].item_ID)));
                        }
                        if (!strArrSplit7[21].equals("")) {
                            f3468a[i11].manaCost = parseIntSafe(strArrSplit7[21]);
                        }
                        str7 = strArrSplit7[22];
                        if (str7 != "") {
                            f3468a[i11].requisites = new PlayerRequirements(str7);
                        }
                        f3468a[i11].classes = new ClassRestriction(strArrSplit7[23]);
                        if (strArrSplit7[24].trim().equals("1")) {
                            f3468a[i11].stackable = false;
                        } else {
                            f3468a[i11].stackable = true;
                        }
                    }
                    itemType = itemType3;
                    item2.type = itemType;
                    Item item322 = f3468a[i11];
                    String str1122 = strArrSplit7[0];
                    strArr = strArrSplit6;
                    i4 = 0;
                    while (true) {
                        gameTextArr = f3471d;
                        i3 = i12;
                        if (i4 >= gameTextArr.length) {
                        }
                        i4++;
                        i12 = i3;
                    }
                    f3468a[i11].name = gameText2 != null ? gameText2.get() : "";
                    Item item422 = f3468a[i11];
                    String str1222 = strArrSplit7[0];
                    i5 = 0;
                    while (true) {
                        gameTextArr2 = f3472e;
                        if (i5 >= gameTextArr2.length) {
                        }
                        i5++;
                    }
                    item422.i((gameText3 != null ? gameText3.get() : "").replace("\"", ""));
                    str2 = strArrSplit7[3];
                    if (str2 != "") {
                    }
                    if (!strArrSplit7[4].equals("")) {
                    }
                    f3468a[i11].h(strArrSplit7[5]);
                    Item item522 = f3468a[i11];
                    String str1322 = strArrSplit7[6];
                    item522.traitBonus = new int[6];
                    listAsList = Arrays.asList(str1322.trim().replace("\"", "").split(","));
                    if (listAsList.size() == 6) {
                    }
                    f3468a[i11].attributes = new ItemAttributes(strArrSplit7[7]);
                    if (!strArrSplit7[8].equals("")) {
                    }
                    Item item622 = f3468a[i11];
                    item622.icon = strArrSplit7[9];
                    str3 = strArrSplit7[10];
                    if (str3 != "") {
                    }
                    str4 = strArrSplit7[11];
                    if (str4 != "") {
                    }
                    Item item722 = f3468a[i11];
                    str5 = strArrSplit7[12];
                    String str1422 = strArrSplit7[13];
                    str6 = strArrSplit7[14];
                    String str1522 = strArrSplit7[15];
                    String str1622 = strArrSplit7[16];
                    item722.getClass();
                    if (str5.equals("")) {
                    }
                    if (!str6.equals("")) {
                    }
                    Item item822 = f3468a[i11];
                    item822.sprite = strArrSplit7[17];
                    item822.OnTake = new ActionsSet(strArrSplit7[18]);
                    f3468a[i11].OnTakeconditions = new ConditionsSet(strArrSplit7[19]);
                    f3468a[i11].OnUse = new ActionsSet(strArrSplit7[20]);
                    item = f3468a[i11];
                    itemType2 = item.type;
                    if (itemType2 == itemType5) {
                        item.OnUse.actions.add(new ScriptedAction(ScriptedAction.ActionType.f3363g, Integer.toString(f3468a[i11].item_ID)));
                        if (!strArrSplit7[21].equals("")) {
                        }
                        str7 = strArrSplit7[22];
                        if (str7 != "") {
                        }
                        f3468a[i11].classes = new ClassRestriction(strArrSplit7[23]);
                        if (strArrSplit7[24].trim().equals("1")) {
                        }
                    }
                }
            } else {
                strArr = strArrSplit6;
                i3 = i12;
            }
            strArrSplit6 = strArr;
            i11 = i3;
            c2 = 0;
            i2 = -1;
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 7/12: loading bestiary text....");
        String[] strArrSplit8 = Gdx.files.internal("data/rules/bestiary_names.txt").readString("UTF-8").replaceAll("\\r+", "").split("\n");
        f3470c = new GameText[strArrSplit8.length - 1];
        for (int i15 = 1; i15 < strArrSplit8.length; i15++) {
            f3470c[i15 - 1] = new GameText(strArrSplit8[i15]);
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 8/12: loading bestiary....");
        String[] strArrSplit9 = Gdx.files.internal("data/rules/bestiary.txt").readString().split("\n");
        f3469b = new Spawn[strArrSplit9.length - 1];
        int i16 = 0;
        while (i16 < f3469b.length) {
            int i17 = i16 + 1;
            String strReplace7 = strArrSplit9[i17].replace("\n", "");
            strArrSplit9[i17] = strReplace7;
            String strReplace8 = strReplace7.replace("\r", "");
            strArrSplit9[i17] = strReplace8;
            String[] strArrSplit10 = strReplace8.split(str, -1);
            f3469b[i16] = new Spawn();
            f3469b[i16].weaponStats = new WeaponStats();
            f3469b[i16].spawn_ID = strArrSplit10[0].toLowerCase(Locale.ENGLISH).trim();
            String str17 = f3469b[i16].spawn_ID;
            int i18 = 0;
            while (true) {
                GameText[] gameTextArr3 = f3470c;
                if (i18 >= gameTextArr3.length) {
                    System.out.println("WARNING: bestiary name not found, " + str17);
                    gameText = null;
                    break;
                }
                if (gameTextArr3[i18].tag.equals(str17)) {
                    gameText = f3470c[i18];
                    break;
                }
                i18++;
            }
            if (gameText != null) {
                f3469b[i16].b(gameText.get());
            }
            Spawn spawn = f3469b[i16];
            String str18 = strArrSplit10[1];
            Locale locale = Locale.ENGLISH;
            String strTrim2 = str18.toLowerCase(locale).trim();
            if (strTrim2.equals("human")) {
                characterRace = CharacterRace.f3487b;
            } else if (strTrim2.equals("goblin")) {
                characterRace = CharacterRace.f3488c;
            } else if (strTrim2.equals("orc")) {
                characterRace = CharacterRace.f3489d;
            } else if (strTrim2.equals("minotaur")) {
                characterRace = CharacterRace.f3490e;
            } else if (strTrim2.equals("weak")) {
                characterRace = CharacterRace.f3492g;
            } else if (strTrim2.equals(KOdB.sYaGJklJGT)) {
                characterRace = CharacterRace.f3493h;
            } else if (strTrim2.equals("miniboss")) {
                characterRace = CharacterRace.f3494i;
            } else if (strTrim2.equals("npc")) {
                characterRace = CharacterRace.f3496k;
            } else if (strTrim2.equals("boss")) {
                characterRace = CharacterRace.f3495j;
            } else {
                strTrim2.equals("");
                characterRace = CharacterRace.f3491f;
            }
            spawn.race = characterRace;
            f3469b[i16].characterclass = c(strArrSplit10[2].toLowerCase(locale).trim());
            f3469b[i16].minlevel = parseIntSafe(strArrSplit10[3]);
            f3469b[i16].maxlevel = parseIntSafe(strArrSplit10[4]);
            f3469b[i16].weaponStats = j(strArrSplit10[5]);
            f3469b[i16].baseArmor = parseIntSafe(strArrSplit10[6]);
            f3469b[i16].resistances = new CharacterResistances(strArrSplit10[7].replace("\"", ""));
            f3469b[i16].speedModifier = Float.parseFloat(strArrSplit10[8]);
            f3469b[i16].attributes = strArrSplit10[9].trim();
            f3469b[i16].lootTable = strArrSplit10[10].toLowerCase(locale).trim().replace("\"", "");
            f3469b[i16].spriteName = strArrSplit10[11].toLowerCase(locale).trim().replace("\"", "");
            Spawn spawn2 = f3469b[i16];
            spawn2.extraSize = spawn2.spriteName.contains("_large");
            f3469b[i16].size = Float.valueOf(Float.parseFloat(strArrSplit10[12]));
            f3469b[i16].skills = strArrSplit10[13].toLowerCase(locale).trim();
            f3469b[i16].AI_type = strArrSplit10[14].toLowerCase(locale).trim();
            Spawn spawn3 = f3469b[i16];
            spawn3.faction = strArrSplit10[15];
            spawn3.gender = strArrSplit10[16].trim().toLowerCase(locale).equals("f") ? Character.Gender.f3208c : Character.Gender.f3207b;
            f3469b[i16].portrait = parseIntSafe(strArrSplit10[17]);
            Spawn spawn4 = f3469b[i16];
            spawn4.onDieConditions = strArrSplit10[18];
            spawn4.onDieActions = strArrSplit10[19];
            spawn4.onAggroSound = FDUtils.d(strArrSplit10[20]);
            f3469b[i16].onDieSound = FDUtils.d(strArrSplit10[21]);
            GameAssets.n(f3469b[i16].onAggroSound);
            GameAssets.n(f3469b[i16].onDieSound);
            i16 = i17;
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 9/12: loading loot....");
        f3474g = new ArrayList<>();
        String[] strArrSplit11 = Gdx.files.internal("data/rules/loot.txt").readString("UTF-16").split("\n");
        for (int i19 = 1; i19 < strArrSplit11.length; i19++) {
            String strReplace9 = strArrSplit11[i19].replace("\n", "");
            strArrSplit11[i19] = strReplace9;
            String strReplace10 = strReplace9.replace("\r", "");
            strArrSplit11[i19] = strReplace10;
            String[] strArrSplit12 = strReplace10.split(str, -1);
            String str19 = strArrSplit12[0];
            Locale locale2 = Locale.ENGLISH;
            String strTrim3 = str19.toLowerCase(locale2).trim();
            int intSafe = parseIntSafe(strArrSplit12[1].toLowerCase(locale2).trim());
            int intSafe2 = parseIntSafe(strArrSplit12[3].toLowerCase(locale2).trim());
            String strTrim4 = strArrSplit12[4].toLowerCase(locale2).trim();
            LootItem lootItem = new LootItem();
            lootItem.table = strTrim3;
            lootItem.item_ID = intSafe;
            lootItem.chance = intSafe2;
            lootItem.conditions = new ConditionsSet(strTrim4);
            f3474g.add(lootItem);
        }
        if (f3474g.size() < 100) {
            System.out.println("WARNING: ********** Loot Tables not correctly loaded ***********");
        }
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 10/12: loading rewards....");
        ArrayList<RewardTable> arrayList2 = new ArrayList<>();
        f3476i = arrayList2;
        arrayList2.add(new RewardTable("gold"));
        f3476i.add(new RewardTable("potions"));
        f3476i.add(new RewardTable("mercian_armor"));
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 11/12: loading traps....");
        f3477j = new TrapData();
        l.e("ExiledKingdoms.Rules.Load() -        >Subtask 12/12: loading map effects....");
        f3478k = new MapEffectData();
        l.e("ExiledKingdoms.Rules.Load() - Ruleset loaded");
    }

    public static int b(int i2) {
        if (i2 <= 100) {
            return f3473f[i2];
        }
        return 0;
    }

    public static CharacterClass c(String str) {
        if (str.equals("warrior")) {
            return CharacterClass.f3479b;
        }
        boolean zEquals = str.equals("wizard");
        CharacterClass characterClass = CharacterClass.f3482e;
        if (zEquals || str.equals("mage")) {
            return characterClass;
        }
        boolean zEquals2 = str.equals("priest");
        CharacterClass characterClass2 = CharacterClass.f3481d;
        return (zEquals2 || str.equals("cleric")) ? characterClass2 : str.equals("rogue") ? CharacterClass.f3480c : CharacterClass.f3483f;
    }

    public static int cdxUpgradeBonusElemental(int i2) {
        if (i2 < 0 || i2 > 10) {
            return 0;
        }
        return i2 / 3;
    }

    public static int cdxUpgradeBonusMax(int i2) {
        if (i2 < 0 || i2 > 10) {
            return 0;
        }
        return (i2 * 7) / 10;
    }

    public static int cdxUpgradeBonusMin(int i2) {
        if (i2 < 0 || i2 > 10) {
            return 0;
        }
        return (i2 * 4) / 10;
    }

    public static int cdxUpgradeGoldCost(int i2, int i3) {
        Item itemF = f(i2);
        if (itemF == null) {
            return 0;
        }
        int i4 = itemF.value;
        if (i4 < 750) {
            i4 = 750;
        }
        int i5 = (i3 * i3 * 3) + 15;
        if (i3 >= 6) {
            i5 += i3 * 2;
        }
        if (i3 >= 9) {
            i5 += 10;
        }
        int iCdxUpgradeSubtype = cdxUpgradeSubtype(i2);
        if (iCdxUpgradeSubtype == 0 || iCdxUpgradeSubtype == 3) {
            i5 += i3;
        } else if (iCdxUpgradeSubtype == 1 || iCdxUpgradeSubtype == 7) {
            i5 += i3 / 2;
        } else if (iCdxUpgradeSubtype == 4 || iCdxUpgradeSubtype == 5) {
            i5++;
        }
        return i4 * i5;
    }

    public static int cdxUpgradeLevel(int i2) {
        if (i2 <= 0) {
            return 0;
        }
        int iB = GameData.v().gameVariables.b("item_upg_" + i2);
        if (iB < 0) {
            iB = 0;
        }
        if (iB > 10) {
            return 10;
        }
        return iB;
    }

    public static String cdxUpgradePreview(int i2) {
        if (i2 <= 0 || !l(i2).booleanValue()) {
            return "";
        }
        int iCdxUpgradeLevel = cdxUpgradeLevel(i2);
        StringBuilder sb = new StringBuilder("Upgrade: +");
        sb.append(iCdxUpgradeLevel);
        sb.append("/10");
        if (iCdxUpgradeLevel >= 10) {
            sb.append("\nNivel maximo atingido");
            return sb.toString();
        }
        int i3 = iCdxUpgradeLevel + 1;
        int iCdxUpgradeGoldCost = cdxUpgradeGoldCost(i2, i3);
        int iCdxUpgradePrimaryResourceId = cdxUpgradePrimaryResourceId(i2, i3);
        int iCdxUpgradePrimaryResourceQty = cdxUpgradePrimaryResourceQty(i2, i3);
        int iCdxUpgradeSecondaryResourceId = cdxUpgradeSecondaryResourceId(i2, i3);
        int iCdxUpgradeSecondaryResourceQty = cdxUpgradeSecondaryResourceQty(i2, i3);
        Item itemF = f(iCdxUpgradePrimaryResourceId);
        Item itemF2 = f(iCdxUpgradeSecondaryResourceId);
        String str = itemF != null ? itemF.name : "Material 1";
        String str2 = itemF2 != null ? itemF2.name : "Material 2";
        sb.append("\nProximo: ");
        sb.append(iCdxUpgradeGoldCost);
        sb.append(" ouro\n");
        sb.append(iCdxUpgradePrimaryResourceQty);
        sb.append("x ");
        sb.append(str);
        sb.append("\n");
        sb.append(iCdxUpgradeSecondaryResourceQty);
        sb.append("x ");
        sb.append(str2);
        return sb.toString();
    }

    public static int cdxUpgradePrimaryResourceId(int i2, int i3) {
        int iCdxUpgradeSubtype = cdxUpgradeSubtype(i2);
        if (iCdxUpgradeSubtype == 0 || iCdxUpgradeSubtype == 1) {
            return i3 <= 3 ? GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS : i3 <= 6 ? 2012 : 2004;
        }
        if (iCdxUpgradeSubtype == 2) {
            return i3 <= 3 ? GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE : i3 <= 6 ? 2013 : 2004;
        }
        if (iCdxUpgradeSubtype == 3) {
            return i3 <= 4 ? GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE : i3 <= 8 ? 2013 : 2004;
        }
        if (iCdxUpgradeSubtype == 4) {
            if (i3 <= 3) {
                return 2003;
            }
            if (i3 <= 6) {
                return 2014;
            }
            return i3 <= 8 ? 2015 : 2004;
        }
        if (iCdxUpgradeSubtype == 5) {
            if (i3 <= 3) {
                return 2003;
            }
            if (i3 <= 6) {
                return 2014;
            }
            return i3 <= 8 ? 2004 : 2015;
        }
        if (iCdxUpgradeSubtype != 6) {
            return i3 <= 4 ? GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS : i3 <= 7 ? 2015 : 2004;
        }
        if (i3 <= 4) {
            return 2003;
        }
        return i3 <= 7 ? 2015 : 2004;
    }

    public static int cdxUpgradePrimaryResourceQty(int i2, int i3) {
        return i3 <= 3 ? (i3 * 2) + 2 : i3 <= 6 ? (i3 * 3) - 1 : i3 <= 8 ? (i3 * 4) - 7 : (i3 * 5) - 16;
    }

    public static int cdxUpgradeSecondaryResourceId(int i2, int i3) {
        int iCdxUpgradeSubtype = cdxUpgradeSubtype(i2);
        if (iCdxUpgradeSubtype == 0 || iCdxUpgradeSubtype == 1) {
            return i3 <= 4 ? GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE : i3 <= 8 ? 2013 : 2015;
        }
        if (iCdxUpgradeSubtype == 2 || iCdxUpgradeSubtype == 3) {
            return i3 <= 4 ? GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS : i3 <= 8 ? 2012 : 2015;
        }
        if (iCdxUpgradeSubtype == 4) {
            if (i3 <= 4) {
                return 2004;
            }
            return i3 <= 8 ? 2015 : 2014;
        }
        if (iCdxUpgradeSubtype == 5) {
            return i3 <= 4 ? GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE : i3 <= 8 ? 2013 : 2015;
        }
        if (iCdxUpgradeSubtype == 6) {
            return i3 <= 4 ? GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS : i3 <= 8 ? 2012 : 2015;
        }
        if (i3 <= 4) {
            return 2003;
        }
        return i3 <= 8 ? 2014 : 2015;
    }

    public static int cdxUpgradeSecondaryResourceQty(int i2, int i3) {
        return i3 <= 3 ? i3 + 1 : i3 <= 8 ? (i3 * 2) - 2 : (i3 * 3) - 10;
    }

    public static int cdxUpgradeSubtype(int i2) {
        Item itemF;
        if (i2 <= 0 || (itemF = f(i2)) == null) {
            return 0;
        }
        Item.ItemType itemType = itemF.type;
        if (itemType == Item.ItemType.f3451c) {
            WeaponStats weaponStats = itemF.weaponStats;
            if (weaponStats == null) {
                return 2;
            }
            if (weaponStats.staff || weaponStats.wand) {
                return 4;
            }
            if (weaponStats.ranged) {
                return 5;
            }
            return weaponStats.twohanded ? 3 : 2;
        }
        if (itemType == Item.ItemType.f3452d) {
            return 0;
        }
        if (itemType == Item.ItemType.f3453e || itemType == Item.ItemType.f3454f || itemType == Item.ItemType.f3455g || itemType == Item.ItemType.f3456h || itemType == Item.ItemType.f3457i) {
            return 1;
        }
        if (itemType == Item.ItemType.f3461m || itemType == Item.ItemType.f3462n) {
            return 6;
        }
        return (itemType == Item.ItemType.f3463o || itemType == Item.ItemType.f3464p) ? 7 : 0;
    }

    public static int d(String str) {
        int i2 = 0;
        ArrayList<LootItem> arrayList = f3474g;
        if (arrayList != null) {
            i2 = 0;
            for (LootItem lootItem : arrayList) {
                if (lootItem.item_ID < 0 && lootItem.table.equals(str) && lootItem.conditions.a().booleanValue()) {
                    if ((GameData.v().o() <= lootItem.chance ? Boolean.TRUE : Boolean.FALSE).booleanValue()) {
                        int iAbs = Math.abs(lootItem.item_ID);
                        int iB = 0;
                        for (int i3 = 1; i3 <= iAbs; i3++) {
                            iB += FDUtils.b(1, 6);
                        }
                        i2 += iAbs <= 5 ? iB * 8 : iAbs <= 15 ? iB * 6 : iAbs <= 50 ? iB * 5 : iB * 4;
                    }
                }
            }
        }
        return i2;
    }

    public static ArrayList<Integer> e(String str) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int iO = GameData.v().o();
        int i2 = (iO <= 70 || iO > 95) ? 1 : 2;
        if (iO > 95) {
            i2 = 3;
        }
        ArrayList<LootItem> arrayList2 = f3474g;
        if (arrayList2 != null) {
            Log.e("EK_LOOT_G", Integer.toString(arrayList2.size()));
            int i3 = 0;
            for (LootItem lootItem : arrayList2) {
                if (lootItem.item_ID > 0 && lootItem.table.equals(str) && lootItem.conditions.a().booleanValue()) {
                    if ((GameData.v().o() <= lootItem.chance ? Boolean.TRUE : Boolean.FALSE).booleanValue()) {
                        arrayList.add(Integer.valueOf(lootItem.item_ID));
                        if (lootItem.chance < 100) {
                            i3++;
                        }
                        if (i3 >= i2) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        Log.e("EK_LOOT", "LOOT_E: table=" + str + " items=" + arrayList.size());
        return arrayList;
    }

    public static Item f(int i2) {
        if (f3468a == null) {
            a();
        }
        int i3 = 0;
        while (true) {
            Item[] itemArr = f3468a;
            if (i3 >= itemArr.length) {
                return null;
            }
            Item item = itemArr[i3];
            if (item.item_ID == i2) {
                return item;
            }
            i3++;
        }
    }

    public static String g(int i2) {
        Item itemF;
        int iCdxUpgradeLevel;
        if (i2 == 0 || (itemF = f(i2)) == null) {
            return pgtXvpMCFu.SKOTyKSy;
        }
        String str = itemF.name;
        int i3 = itemF.item_ID;
        if (!l(i3).booleanValue() || (iCdxUpgradeLevel = cdxUpgradeLevel(i3)) <= 0) {
            return str;
        }
        return str + " +" + iCdxUpgradeLevel;
    }

    public static String h(int i2, String str) {
        for (RewardTable rewardTable : f3476i) {
            if (rewardTable.id.equals(str)) {
                return rewardTable.a(i2);
            }
        }
        return "";
    }

    public static Spawn i(String str) {
        Spawn[] spawnArr = f3469b;
        if (spawnArr == null) {
            a();
            spawnArr = f3469b;
        }
        int length = spawnArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (f3469b[i2].spawn_ID.equals(str.toLowerCase(Locale.ENGLISH).trim())) {
                return f3469b[i2];
            }
        }
        System.out.println("WARNING: spawn " + str + " not found");
        return null;
    }

    public static WeaponStats j(String str) {
        int size = f3475h.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (f3475h.get(i2).id.equals(str)) {
                return f3475h.get(i2);
            }
        }
        return null;
    }

    public static void k(int i2, String str) {
        for (RewardTable rewardTable : f3476i) {
            if (rewardTable.id.equals(str)) {
                rewardTable.b(i2);
            }
        }
        int i3 = i2 * 70;
        GameData.v().player.O0(i3);
        GameLog gameLog = GameData.v().log;
        gameLog.getClass();
        gameLog.a(("[YELLOW]" + GameString.b("XP_GAINED", false) + ":+" + i3 + " XP") + "[]");
    }

    public static Boolean l(int i2) {
        Item itemF;
        Item.ItemType itemType;
        return (i2 == 0 || (itemF = f(i2)) == null || !((itemType = itemF.type) == Item.ItemType.f3457i || itemType == Item.ItemType.f3454f || itemType == Item.ItemType.f3455g || itemType == Item.ItemType.f3453e || itemType == Item.ItemType.f3456h || itemType == Item.ItemType.f3461m || itemType == Item.ItemType.f3462n || itemType == Item.ItemType.f3451c || itemType == Item.ItemType.f3452d || itemType == Item.ItemType.f3464p || itemType == Item.ItemType.f3463o)) ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Boolean m(int i2, CharacterSheet characterSheet) {
        WeaponStats weaponStats;
        Damage.DamageType damageType;
        WeaponStats weaponStats2;
        if (!l(i2).booleanValue()) {
            return Boolean.FALSE;
        }
        Item itemF = f(i2);
        if (itemF != null) {
            if (itemF.classes.c(characterSheet.stats.c()).booleanValue()) {
                return Boolean.TRUE;
            }
            Item itemF2 = f(i2);
            if (itemF2 != null && characterSheet.stats.c() == CharacterClass.f3482e) {
                Item.ItemType itemType = itemF2.type;
                Item.ItemType itemType2 = Item.ItemType.f3451c;
                if (itemType == itemType2 && !itemF2.weaponStats.ranged) {
                    return (characterSheet.skillSet.g("vampiric_blade") <= 0 || itemF2.type != itemType2 || (weaponStats2 = itemF2.weaponStats) == null || weaponStats2.ranged || !weaponStats2.has_secondary_damage || weaponStats2.secondary_damageType != Damage.DamageType.f3265f) ? (characterSheet.skillSet.g("arcane_blade") <= 0 || itemF2.type != itemType2 || (weaponStats = itemF2.weaponStats) == null || weaponStats.twohanded || weaponStats.ranged || !weaponStats.has_secondary_damage || !((damageType = weaponStats.secondary_damageType) == Damage.DamageType.f3263d || damageType == Damage.DamageType.f3262c || damageType == Damage.DamageType.f3264e)) ? Boolean.FALSE : Boolean.TRUE : Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public static Boolean n(int i2) {
        if (f3468a == null) {
            a();
        }
        int i3 = 0;
        while (true) {
            Item[] itemArr = f3468a;
            if (i3 >= itemArr.length) {
                return Boolean.FALSE;
            }
            Item item = itemArr[i3];
            if (item.item_ID == i2) {
                return Boolean.valueOf(item.stackable);
            }
            i3++;
        }
    }

    public static int parseIntSafe(String str) {
        if (str == null) {
            return 0;
        }
        try {
            String strTrim = str.trim();
            if (strTrim.length() == 0) {
                return 0;
            }
            return Integer.parseInt(strTrim);
        } catch (Exception e2) {
            return 0;
        }
    }
}
