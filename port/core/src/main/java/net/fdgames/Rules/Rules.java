package net.fdgames.Rules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.l;
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
import net.fdgames.assets.GameAssets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Rules {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Item[] f3247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Spawn[] f3248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static GameText[] f3249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static GameText[] f3250d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static GameText[] f3251e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int[] f3252f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static ArrayList<LootItem> f3253g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static ArrayList<WeaponStats> f3254h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static ArrayList<RewardTable> f3255i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static TrapData f3256j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static MapEffectData f3257k;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum CharacterClass {
        WARRIOR, ROGUE, CLERIC, WIZARD, MONSTER, GENERAL, NONE;

public static String a(CharacterClass characterClass) {
            int iOrdinal = characterClass.ordinal();
            return iOrdinal != 0 ? iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? iOrdinal != 4 ? iOrdinal != 5 ? "" : GameString.b("GENERAL_CLASS", false) : GameString.b("MONSTER", false) : GameString.b("MAGE", false) : GameString.b("CLERIC", false) : GameString.b("ROGUE", false) : GameString.b("WARRIOR", false);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum CharacterRace {
        HUMAN, HALFLING, GOBLIN, ORC, MINOTAUR, MONSTER, MONSTER_WEAK, MONSTER_STRONG, MINIBOSS, BOSS, NPC;
    }

    static {
        new Item();
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x04da  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0563 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0585  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0595  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05c3  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03ea A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0423 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a() {
        int i2;
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
        String str;
        List listAsList;
        String str2;
        String str3;
        String str4;
        String str5;
        boolean z2;
        Item item;
        Item.ItemType itemType2;
        String str6;
        boolean z3;
        boolean z4;
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 1/12: loading skills....");
        Skills.e();
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 2/12: loading plants....");
        ArrayList<Plant> arrayList = Plants.f3246a;
        Plants.f3246a = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/rules/plants.txt").readString().split("\n");
        char c2 = 0;
        int i6 = 0;
        while (true) {
            i2 = -1;
            if (i6 >= strArrSplit.length) {
                break;
            }
            String strReplace = strArrSplit[i6].replace("\n", "");
            strArrSplit[i6] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i6] = strReplace2;
            String[] strArrSplit2 = strReplace2.split("\t", -1);
            String str7 = strArrSplit2[0];
            String str8 = strArrSplit2[1];
            String str9 = strArrSplit2[2];
            Plant plant = new Plant();
            plant.ID = str7;
            plant.item_ID = Integer.parseInt(str8);
            plant.chance = Integer.parseInt(str9);
            Plants.f3246a.add(plant);
            i6++;
        }
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 3/12: loading levels....");
        int[] iArr = new int[Quests.SELECT_COMPLETED_UNCLAIMED];
        f3252f = iArr;
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
            int[] iArr2 = f3252f;
            iArr2[i7] = iArr2[i7 - 1] + 1000000;
        }
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 4/12: loading weapons....");
        f3254h = new ArrayList<>();
        String[] strArrSplit3 = Gdx.files.internal("data/rules/weapons.txt").readString().split("\n");
        int i8 = 1;
        while (i8 < strArrSplit3.length) {
            String strReplace3 = strArrSplit3[i8].replace("\n", "");
            strArrSplit3[i8] = strReplace3;
            String strReplace4 = strReplace3.replace("\r", "");
            strArrSplit3[i8] = strReplace4;
            WeaponStats weaponStats = new WeaponStats();
            String[] strArrSplit4 = strReplace4.replace("\"", "").split("\t", -1);
            weaponStats.id = strArrSplit4[0];
            weaponStats.minDamage = Integer.parseInt(strArrSplit4[2]);
            weaponStats.maxDamage = Integer.parseInt(strArrSplit4[3]);
            weaponStats.critChance = Integer.parseInt(strArrSplit4[4]);
            weaponStats.speed = Integer.parseInt(strArrSplit4[c3]);
            weaponStats.reach = Integer.parseInt(strArrSplit4[c4]);
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
                    weaponStats.ranged_speed = Integer.parseInt(strArrSplit4[11]);
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
                weaponStats.secondary_Damage = Integer.parseInt(strArrSplit4[15]);
                weaponStats.secondary_damageType = Damage.b(strArrSplit4[16]);
            }
            if (strArrSplit4[17].trim().equals("")) {
                weaponStats.hasProc = false;
            } else {
                weaponStats.hasProc = true;
                weaponStats.procEffect = WeaponStats.d(strArrSplit4[17]);
                weaponStats.procChance = Integer.parseInt(strArrSplit4[18]);
                weaponStats.procLevel = Integer.parseInt(strArrSplit4[19]);
            }
            f3254h.add(weaponStats);
            i8++;
            c3 = 6;
            c4 = 7;
        }
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 5/12: loading items text....");
        String[] strArrSplit5 = Gdx.files.internal("data/rules/items_text.txt").readString("UTF-8").replaceAll("\\r+", "").split("\n");
        f3250d = new GameText[strArrSplit5.length - 1];
        for (int i9 = 1; i9 < strArrSplit5.length; i9++) {
            f3250d[i9 - 1] = new GameText(strArrSplit5[i9], true, false);
        }
        f3251e = new GameText[strArrSplit5.length - 1];
        for (int i10 = 1; i10 < strArrSplit5.length; i10++) {
            f3251e[i10 - 1] = new GameText(strArrSplit5[i10], true, true);
        }
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 6/12: loading items....");
        String[] strArrSplit6 = Gdx.files.internal("data/rules/items.txt").readString().split("\n");
        f3247a = new Item[strArrSplit6.length - 1];
        int i11 = 0;
        while (i11 < f3247a.length) {
            int i12 = i11 + 1;
            String strReplace5 = strArrSplit6[i12].replace("\n", "");
            strArrSplit6[i12] = strReplace5;
            String strReplace6 = strReplace5.replace("\r", "");
            strArrSplit6[i12] = strReplace6;
            String[] strArrSplit7 = strReplace6.split("\t", i2);
            if (Integer.parseInt(strArrSplit7[c2]) != 0) {
                f3247a[i11] = new Item();
                f3247a[i11].weaponStats = new WeaponStats();
                f3247a[i11].item_ID = Integer.parseInt(strArrSplit7[c2]);
                Item item2 = f3247a[i11];
                String strTrim = strArrSplit7[2].toLowerCase(Locale.ENGLISH).trim();
                boolean zEquals = strTrim.equals("general");
                Item.ItemType itemType3 = Item.ItemType.GENERAL;
                Item.ItemType itemType4 = Item.ItemType.SCROLL;
                Item.ItemType itemType5 = Item.ItemType.POTION;
                if (zEquals) {
                    itemType = itemType3;
                    item2.type = itemType;
                    Item item3 = f3247a[i11];
                    String str10 = strArrSplit7[0];
                    strArr = strArrSplit6;
                    i4 = 0;
                    while (true) {
                        gameTextArr = f3250d;
                        i3 = i12;
                        if (i4 >= gameTextArr.length) {
                        }
                        i4++;
                        i12 = i3;
                    }
                    item3.name = gameText2.get();
                    Item item4 = f3247a[i11];
                    String str11 = strArrSplit7[0];
                    i5 = 0;
                    while (true) {
                        gameTextArr2 = f3251e;
                        if (i5 >= gameTextArr2.length) {
                        }
                        i5++;
                    }
                    item4.i(gameText3.get().replace("\"", ""));
                    str = strArrSplit7[3];
                    if (str != "") {
                    }
                    if (!strArrSplit7[4].equals("")) {
                    }
                    f3247a[i11].h(strArrSplit7[5]);
                    Item item5 = f3247a[i11];
                    String str12 = strArrSplit7[6];
                    item5.traitBonus = new int[6];
                    listAsList = Arrays.asList(str12.trim().replace("\"", "").split(","));
                    if (listAsList.size() == 6) {
                    }
                    f3247a[i11].attributes = new ItemAttributes(strArrSplit7[7]);
                    if (!strArrSplit7[8].equals("")) {
                    }
                    Item item6 = f3247a[i11];
                    item6.icon = strArrSplit7[9];
                    str2 = strArrSplit7[10];
                    if (str2 != "") {
                    }
                    str3 = strArrSplit7[11];
                    if (str3 != "") {
                    }
                    Item item7 = f3247a[i11];
                    str4 = strArrSplit7[12];
                    String str13 = strArrSplit7[13];
                    str5 = strArrSplit7[14];
                    String str14 = strArrSplit7[15];
                    String str15 = strArrSplit7[16];
                    item7.getClass();
                    if (str4.equals("")) {
                    }
                    if (!str5.equals("")) {
                    }
                    Item item8 = f3247a[i11];
                    item8.sprite = strArrSplit7[17];
                    item8.OnTake = new ActionsSet(strArrSplit7[18]);
                    f3247a[i11].OnTakeconditions = new ConditionsSet(strArrSplit7[19]);
                    f3247a[i11].OnUse = new ActionsSet(strArrSplit7[20]);
                    item = f3247a[i11];
                    itemType2 = item.type;
                    if (itemType2 == itemType5) {
                    }
                } else {
                    if (strTrim.equals("weapon")) {
                        itemType3 = Item.ItemType.WEAPON;
                    } else if (strTrim.equals("shield")) {
                        itemType3 = Item.ItemType.SHIELD;
                    } else if (strTrim.equals("armor_arms")) {
                        itemType3 = Item.ItemType.ARMOR_ARMS;
                    } else if (strTrim.equals("armor_chest")) {
                        itemType3 = Item.ItemType.ARMOR_CHEST;
                    } else if (strTrim.equals("armor_feet")) {
                        itemType3 = Item.ItemType.ARMOR_FEET;
                    } else if (strTrim.equals("armor_legs")) {
                        itemType3 = Item.ItemType.ARMOR_LEGS;
                    } else if (strTrim.equals("armor_head")) {
                        itemType3 = Item.ItemType.ARMOR_HEAD;
                    } else if (strTrim.equals("key")) {
                        itemType3 = Item.ItemType.KEY;
                    } else {
                        if (strTrim.equals("potion")) {
                            itemType = itemType5;
                        } else if (strTrim.equals("wand")) {
                            itemType3 = Item.ItemType.WAND;
                        } else if (strTrim.equals("ring")) {
                            itemType3 = Item.ItemType.RING;
                        } else if (strTrim.equals("belt")) {
                            itemType3 = Item.ItemType.BELT;
                        } else if (strTrim.equals("cloak")) {
                            itemType3 = Item.ItemType.CLOAK;
                        } else if (strTrim.equals("necklace")) {
                            itemType3 = Item.ItemType.NECKLACE;
                        } else if (strTrim.equals("scroll")) {
                            itemType = itemType4;
                        }
                        item2.type = itemType;
                        Item item32 = f3247a[i11];
                        String str102 = strArrSplit7[0];
                        strArr = strArrSplit6;
                        i4 = 0;
                        while (true) {
                            gameTextArr = f3250d;
                            i3 = i12;
                            if (i4 >= gameTextArr.length) {
                                System.out.println("WARNING: item name not found, " + str102);
                                gameText2 = null;
                                break;
                            }
                            if (gameTextArr[i4].tag.equals(str102)) {
                                gameText2 = f3250d[i4];
                                break;
                            } else {
                                i4++;
                                i12 = i3;
                            }
                        }
                        item32.name = gameText2.get();
                        Item item42 = f3247a[i11];
                        String str112 = strArrSplit7[0];
                        i5 = 0;
                        while (true) {
                            gameTextArr2 = f3251e;
                            if (i5 >= gameTextArr2.length) {
                                System.out.println("WARNING: item description not found, " + str112);
                                gameText3 = null;
                                break;
                            }
                            if (gameTextArr2[i5].tag.equals(str112)) {
                                gameText3 = f3251e[i5];
                                break;
                            }
                            i5++;
                        }
                        item42.i(gameText3.get().replace("\"", ""));
                        str = strArrSplit7[3];
                        if (str != "") {
                            f3247a[i11].weaponStats = j(str);
                        }
                        if (!strArrSplit7[4].equals("")) {
                            f3247a[i11].armorBonus = Integer.parseInt(strArrSplit7[4]);
                        }
                        f3247a[i11].h(strArrSplit7[5]);
                        Item item52 = f3247a[i11];
                        String str122 = strArrSplit7[6];
                        item52.traitBonus = new int[6];
                        listAsList = Arrays.asList(str122.trim().replace("\"", "").split(","));
                        if (listAsList.size() == 6) {
                            int i13 = 0;
                            for (int i14 = 6; i13 < i14; i14 = 6) {
                                item52.traitBonus[i13] = Integer.parseInt((String) listAsList.get(i13));
                                i13++;
                            }
                        }
                        f3247a[i11].attributes = new ItemAttributes(strArrSplit7[7]);
                        if (!strArrSplit7[8].equals("")) {
                            f3247a[i11].value = Integer.parseInt(strArrSplit7[8]);
                        }
                        Item item62 = f3247a[i11];
                        item62.icon = strArrSplit7[9];
                        str2 = strArrSplit7[10];
                        if (str2 != "") {
                            item62.HPBonus = Integer.parseInt(str2);
                        }
                        str3 = strArrSplit7[11];
                        if (str3 != "") {
                            f3247a[i11].ManaBonus = Integer.parseInt(str3);
                        }
                        Item item72 = f3247a[i11];
                        str4 = strArrSplit7[12];
                        String str132 = strArrSplit7[13];
                        str5 = strArrSplit7[14];
                        String str142 = strArrSplit7[15];
                        String str152 = strArrSplit7[16];
                        item72.getClass();
                        if (str4.equals("")) {
                            z2 = true;
                        } else {
                            item72.damageBonus = Integer.parseInt(str4);
                            item72.damageBonusType = Damage.b(str132);
                            z2 = true;
                            item72.hasDamageBonus = true;
                        }
                        if (!str5.equals("")) {
                            item72.hasProc = z2;
                            item72.procEffect = WeaponStats.d(str5);
                            item72.procChance = Integer.parseInt(str142);
                            item72.procLevel = Integer.parseInt(str152);
                        }
                        Item item82 = f3247a[i11];
                        item82.sprite = strArrSplit7[17];
                        item82.OnTake = new ActionsSet(strArrSplit7[18]);
                        f3247a[i11].OnTakeconditions = new ConditionsSet(strArrSplit7[19]);
                        f3247a[i11].OnUse = new ActionsSet(strArrSplit7[20]);
                        item = f3247a[i11];
                        itemType2 = item.type;
                        if (itemType2 == itemType5 || itemType2 == itemType4) {
                            item.OnUse.actions.add(new ScriptedAction(ScriptedAction.ActionType.LoseItem, Integer.toString(f3247a[i11].item_ID)));
                        }
                        if (!strArrSplit7[21].equals("")) {
                            f3247a[i11].manaCost = Integer.parseInt(strArrSplit7[21]);
                        }
                        str6 = strArrSplit7[22];
                        if (str6 != "") {
                            f3247a[i11].requisites = new PlayerRequirements(str6);
                        }
                        f3247a[i11].classes = new ClassRestriction(strArrSplit7[23]);
                        if (strArrSplit7[24].trim().equals("1")) {
                            f3247a[i11].stackable = false;
                        } else {
                            f3247a[i11].stackable = true;
                        }
                    }
                    itemType = itemType3;
                    item2.type = itemType;
                    Item item322 = f3247a[i11];
                    String str1022 = strArrSplit7[0];
                    strArr = strArrSplit6;
                    i4 = 0;
                    while (true) {
                        gameTextArr = f3250d;
                        i3 = i12;
                        if (i4 >= gameTextArr.length) {
                        }
                        i4++;
                        i12 = i3;
                    }
                    item322.name = gameText2.get();
                    Item item422 = f3247a[i11];
                    String str1122 = strArrSplit7[0];
                    i5 = 0;
                    while (true) {
                        gameTextArr2 = f3251e;
                        if (i5 >= gameTextArr2.length) {
                        }
                        i5++;
                    }
                    item422.i(gameText3.get().replace("\"", ""));
                    str = strArrSplit7[3];
                    if (str != "") {
                    }
                    if (!strArrSplit7[4].equals("")) {
                    }
                    f3247a[i11].h(strArrSplit7[5]);
                    Item item522 = f3247a[i11];
                    String str1222 = strArrSplit7[6];
                    item522.traitBonus = new int[6];
                    listAsList = Arrays.asList(str1222.trim().replace("\"", "").split(","));
                    if (listAsList.size() == 6) {
                    }
                    f3247a[i11].attributes = new ItemAttributes(strArrSplit7[7]);
                    if (!strArrSplit7[8].equals("")) {
                    }
                    Item item622 = f3247a[i11];
                    item622.icon = strArrSplit7[9];
                    str2 = strArrSplit7[10];
                    if (str2 != "") {
                    }
                    str3 = strArrSplit7[11];
                    if (str3 != "") {
                    }
                    Item item722 = f3247a[i11];
                    str4 = strArrSplit7[12];
                    String str1322 = strArrSplit7[13];
                    str5 = strArrSplit7[14];
                    String str1422 = strArrSplit7[15];
                    String str1522 = strArrSplit7[16];
                    item722.getClass();
                    if (str4.equals("")) {
                    }
                    if (!str5.equals("")) {
                    }
                    Item item822 = f3247a[i11];
                    item822.sprite = strArrSplit7[17];
                    item822.OnTake = new ActionsSet(strArrSplit7[18]);
                    f3247a[i11].OnTakeconditions = new ConditionsSet(strArrSplit7[19]);
                    f3247a[i11].OnUse = new ActionsSet(strArrSplit7[20]);
                    item = f3247a[i11];
                    itemType2 = item.type;
                    if (itemType2 == itemType5) {
                        item.OnUse.actions.add(new ScriptedAction(ScriptedAction.ActionType.LoseItem, Integer.toString(f3247a[i11].item_ID)));
                        if (!strArrSplit7[21].equals("")) {
                        }
                        str6 = strArrSplit7[22];
                        if (str6 != "") {
                        }
                        f3247a[i11].classes = new ClassRestriction(strArrSplit7[23]);
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
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 7/12: loading bestiary text....");
        String[] strArrSplit8 = Gdx.files.internal("data/rules/bestiary_names.txt").readString("UTF-8").replaceAll("\\r+", "").split("\n");
        f3249c = new GameText[strArrSplit8.length - 1];
        for (int i15 = 1; i15 < strArrSplit8.length; i15++) {
            f3249c[i15 - 1] = new GameText(strArrSplit8[i15]);
        }
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 8/12: loading bestiary....");
        String[] strArrSplit9 = Gdx.files.internal("data/rules/bestiary.txt").readString().split("\n");
        f3248b = new Spawn[strArrSplit9.length - 1];
        int i16 = 0;
        while (i16 < f3248b.length) {
            int i17 = i16 + 1;
            String strReplace7 = strArrSplit9[i17].replace("\n", "");
            strArrSplit9[i17] = strReplace7;
            String strReplace8 = strReplace7.replace("\r", "");
            strArrSplit9[i17] = strReplace8;
            String[] strArrSplit10 = strReplace8.split("\t", -1);
            f3248b[i16] = new Spawn();
            f3248b[i16].weaponStats = new WeaponStats();
            f3248b[i16].spawn_ID = strArrSplit10[0].toLowerCase(Locale.ENGLISH).trim();
            String str16 = f3248b[i16].spawn_ID;
            int i18 = 0;
            while (true) {
                GameText[] gameTextArr3 = f3249c;
                if (i18 >= gameTextArr3.length) {
                    System.out.println("WARNING: bestiary name not found, " + str16);
                    gameText = null;
                    break;
                }
                if (gameTextArr3[i18].tag.equals(str16)) {
                    gameText = f3249c[i18];
                    break;
                }
                i18++;
            }
            if (gameText != null) {
                f3248b[i16].b(gameText.get());
            }
            Spawn spawn = f3248b[i16];
            String str17 = strArrSplit10[1];
            Locale locale = Locale.ENGLISH;
            String strTrim2 = str17.toLowerCase(locale).trim();
            if (strTrim2.equals("human")) {
                characterRace = CharacterRace.HUMAN;
            } else if (strTrim2.equals("goblin")) {
                characterRace = CharacterRace.GOBLIN;
            } else if (strTrim2.equals("orc")) {
                characterRace = CharacterRace.ORC;
            } else if (strTrim2.equals("minotaur")) {
                characterRace = CharacterRace.MINOTAUR;
            } else if (strTrim2.equals("weak")) {
                characterRace = CharacterRace.MONSTER_WEAK;
            } else if (strTrim2.equals("strong")) {
                characterRace = CharacterRace.MONSTER_STRONG;
            } else if (strTrim2.equals("miniboss")) {
                characterRace = CharacterRace.MINIBOSS;
            } else if (strTrim2.equals("npc")) {
                characterRace = CharacterRace.NPC;
            } else if (strTrim2.equals("boss")) {
                characterRace = CharacterRace.BOSS;
            } else {
                strTrim2.equals("");
                characterRace = CharacterRace.MONSTER;
            }
            spawn.race = characterRace;
            f3248b[i16].characterclass = c(strArrSplit10[2].toLowerCase(locale).trim());
            f3248b[i16].minlevel = Integer.parseInt(strArrSplit10[3]);
            f3248b[i16].maxlevel = Integer.parseInt(strArrSplit10[4]);
            f3248b[i16].weaponStats = j(strArrSplit10[5]);
            f3248b[i16].baseArmor = Integer.parseInt(strArrSplit10[6]);
            f3248b[i16].resistances = new CharacterResistances(strArrSplit10[7].replace("\"", ""));
            f3248b[i16].speedModifier = Float.parseFloat(strArrSplit10[8]);
            f3248b[i16].attributes = strArrSplit10[9].trim();
            f3248b[i16].lootTable = strArrSplit10[10].toLowerCase(locale).trim().replace("\"", "");
            f3248b[i16].spriteName = strArrSplit10[11].toLowerCase(locale).trim().replace("\"", "");
            Spawn spawn2 = f3248b[i16];
            spawn2.extraSize = spawn2.spriteName.contains("_large");
            f3248b[i16].size = Float.valueOf(Float.parseFloat(strArrSplit10[12]));
            f3248b[i16].skills = strArrSplit10[13].toLowerCase(locale).trim();
            f3248b[i16].AI_type = strArrSplit10[14].toLowerCase(locale).trim();
            Spawn spawn3 = f3248b[i16];
            spawn3.faction = strArrSplit10[15];
            spawn3.gender = strArrSplit10[16].trim().toLowerCase(locale).equals("f") ? Character.Gender.Female : Character.Gender.Male;
            f3248b[i16].portrait = Integer.parseInt(strArrSplit10[17]);
            Spawn spawn4 = f3248b[i16];
            spawn4.onDieConditions = strArrSplit10[18];
            spawn4.onDieActions = strArrSplit10[19];
            spawn4.onAggroSound = FDUtils.d(strArrSplit10[20]);
            f3248b[i16].onDieSound = FDUtils.d(strArrSplit10[21]);
            GameAssets.n(f3248b[i16].onAggroSound);
            GameAssets.n(f3248b[i16].onDieSound);
            i16 = i17;
        }
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 9/12: loading loot....");
        f3253g = new ArrayList<>();
        String[] strArrSplit11 = Gdx.files.internal("data/rules/loot.txt").readString("UTF-16").split("\n");
        for (int i19 = 1; i19 < strArrSplit11.length; i19++) {
            String strReplace9 = strArrSplit11[i19].replace("\n", "");
            strArrSplit11[i19] = strReplace9;
            String strReplace10 = strReplace9.replace("\r", "");
            strArrSplit11[i19] = strReplace10;
            String[] strArrSplit12 = strReplace10.split("\t", -1);
            String str18 = strArrSplit12[0];
            Locale locale2 = Locale.ENGLISH;
            String strTrim3 = str18.toLowerCase(locale2).trim();
            int i20 = Integer.parseInt(strArrSplit12[1].toLowerCase(locale2).trim());
            int i21 = Integer.parseInt(strArrSplit12[3].toLowerCase(locale2).trim());
            String strTrim4 = strArrSplit12[4].toLowerCase(locale2).trim();
            LootItem lootItem = new LootItem();
            lootItem.table = strTrim3;
            lootItem.item_ID = i20;
            lootItem.chance = i21;
            lootItem.conditions = new ConditionsSet(strTrim4);
            f3253g.add(lootItem);
        }
        if (f3253g.size() < 100) {
            System.out.println("WARNING: ********** Loot Tables not correctly loaded ***********");
        }
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 10/12: loading rewards....");
        ArrayList<RewardTable> arrayList2 = new ArrayList<>();
        f3255i = arrayList2;
        arrayList2.add(new RewardTable("gold"));
        f3255i.add(new RewardTable("potions"));
        f3255i.add(new RewardTable("mercian_armor"));
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 11/12: loading traps....");
        f3256j = new TrapData();
        l.d("ExiledKingdoms.Rules.Load() -        >Subtask 12/12: loading map effects....");
        f3257k = new MapEffectData();
        l.d("ExiledKingdoms.Rules.Load() - Ruleset loaded");
    }

    public static int b(int i2) {
        if (i2 <= 100) {
            return f3252f[i2];
        }
        return 0;
    }

    public static CharacterClass c(String str) {
        if (str.equals("warrior")) {
            return CharacterClass.WARRIOR;
        }
        boolean zEquals = str.equals("wizard");
        CharacterClass characterClass = CharacterClass.WIZARD;
        if (zEquals || str.equals("mage")) {
            return characterClass;
        }
        boolean zEquals2 = str.equals("priest");
        CharacterClass characterClass2 = CharacterClass.CLERIC;
        return (zEquals2 || str.equals("cleric")) ? characterClass2 : str.equals("rogue") ? CharacterClass.ROGUE : CharacterClass.MONSTER;
    }

    public static int d(String str) {
        int i2 = 0;
        for (LootItem lootItem : f3253g) {
            if (lootItem.item_ID < 0 && lootItem.table.equals(str) && lootItem.conditions.a().booleanValue()) {
                if ((GameData.v().o() <= lootItem.chance ? Boolean.TRUE : Boolean.FALSE).booleanValue()) {
                    int iAbs = Math.abs(lootItem.item_ID);
                    int iB = 0;
                    for (int i3 = 1; i3 <= iAbs; i3++) {
                        iB += FDUtils.b(1, 6);
                    }
                    i2 += iB;
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
        int i3 = 0;
        for (LootItem lootItem : f3253g) {
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
        return arrayList;
    }

    public static Item f(int i2) {
        int i3 = 0;
        while (true) {
            Item[] itemArr = f3247a;
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
        return i2 != 0 ? f(i2).name : "";
    }

    public static String h(int i2, String str) {
        for (RewardTable rewardTable : f3255i) {
            if (rewardTable.id.equals(str)) {
                return rewardTable.a(i2);
            }
        }
        return "";
    }

    public static Spawn i(String str) {
        int length = f3248b.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (f3248b[i2].spawn_ID.equals(str.toLowerCase(Locale.ENGLISH).trim())) {
                return f3248b[i2];
            }
        }
        System.out.println("WARNING: spawn " + str + " not found");
        return null;
    }

    public static WeaponStats j(String str) {
        int size = f3254h.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (f3254h.get(i2).id.equals(str)) {
                return f3254h.get(i2);
            }
        }
        return null;
    }

    public static void k(int i2, String str) {
        for (RewardTable rewardTable : f3255i) {
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
        Item.ItemType itemType;
        return (i2 == 0 || !((itemType = f(i2).type) == Item.ItemType.ARMOR_ARMS || itemType == Item.ItemType.ARMOR_CHEST || itemType == Item.ItemType.ARMOR_FEET || itemType == Item.ItemType.ARMOR_HEAD || itemType == Item.ItemType.ARMOR_LEGS || itemType == Item.ItemType.RING || itemType == Item.ItemType.BELT || itemType == Item.ItemType.WEAPON || itemType == Item.ItemType.SHIELD || itemType == Item.ItemType.NECKLACE || itemType == Item.ItemType.CLOAK)) ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Boolean m(int i2, CharacterSheet characterSheet) {
        WeaponStats weaponStats;
        Damage.DamageType damageType;
        WeaponStats weaponStats2;
        if (!l(i2).booleanValue()) {
            return Boolean.FALSE;
        }
        if (f(i2).classes.c(characterSheet.stats.c()).booleanValue()) {
            return Boolean.TRUE;
        }
        Item itemF = f(i2);
        if (characterSheet.stats.c() == CharacterClass.WIZARD) {
            Item.ItemType itemType = itemF.type;
            Item.ItemType itemType2 = Item.ItemType.WEAPON;
            if (itemType == itemType2 && !itemF.weaponStats.ranged) {
                return (characterSheet.skillSet.g("vampiric_blade") <= 0 || itemF.type != itemType2 || (weaponStats2 = itemF.weaponStats) == null || weaponStats2.ranged || !weaponStats2.has_secondary_damage || weaponStats2.secondary_damageType != Damage.DamageType.Death) ? (characterSheet.skillSet.g("arcane_blade") <= 0 || itemF.type != itemType2 || (weaponStats = itemF.weaponStats) == null || weaponStats.twohanded || weaponStats.ranged || !weaponStats.has_secondary_damage || !((damageType = weaponStats.secondary_damageType) == Damage.DamageType.Cold || damageType == Damage.DamageType.Fire || damageType == Damage.DamageType.Shock)) ? Boolean.FALSE : Boolean.TRUE : Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static Boolean n(int i2) {
        int i3 = 0;
        while (true) {
            Item[] itemArr = f3247a;
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
}
