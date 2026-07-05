package net.fdgames.GameWorld;

import a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import n0.e;
import n0.z;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DynamicQuest {
    public int DQ_id;
    public String castleID;
    public int difficulty_level;
    public int duration;
    public Float expirationDate;
    public Float generationDate;
    public int knowledge;
    public String location_ID;
    public int minlevel;
    public String quest_ID;
    public String rewardTable;
    public String variation_ID;

    public DynamicQuest() {
    }

    public static void a(String str, String str2) {
        DynamicQuest dynamicQuest = new DynamicQuest(str, str2);
        int i2 = 1;
        while (true) {
            if ((!b(dynamicQuest) && !c(dynamicQuest)) || i2 >= 150) {
                break;
            }
            dynamicQuest = new DynamicQuest(str, str2);
            i2++;
        }
        if (b(dynamicQuest) || c(dynamicQuest)) {
            dynamicQuest = null;
        }
        if (str.equals("") || dynamicQuest == null) {
            return;
        }
        GameData.v().lastDynamicQuest++;
        dynamicQuest.DQ_id = GameData.v().lastDynamicQuest;
        Iterator<DynamicQuest> it = GameData.v().dynamicQuests.iterator();
        while (it.hasNext()) {
            DynamicQuest next = it.next();
            if (GameData.v().gameVariables.b("DQ_" + next.DQ_id) == -5 && ((dynamicQuest.quest_ID.equals(next.quest_ID) && dynamicQuest.variation_ID.equals(next.variation_ID)) || dynamicQuest.location_ID.equals(next.location_ID))) {
                it.remove();
            }
        }
        GameData.v().dynamicQuests.add(dynamicQuest);
        GameData.v().gameVariables.e(-5, "DQ_" + dynamicQuest.DQ_id);
        boolean z2 = false;
        for (CastleData castleData : GameData.v().castleData) {
            if (castleData.castle_ID.equals(str2)) {
                castleData.lastQuestCreation = GameData.v().u();
                z2 = true;
            }
        }
        if (!z2) {
            CastleData castleData2 = new CastleData();
            castleData2.castle_ID = str2;
            castleData2.lastQuestCreation = GameData.v().u();
            GameData.v().castleData.add(castleData2);
        }
        int i3 = GameData.v().lastDynamicQuest;
    }

    public static boolean b(DynamicQuest dynamicQuest) {
        String str = dynamicQuest.quest_ID;
        String str2 = dynamicQuest.variation_ID;
        for (DynamicQuest dynamicQuest2 : GameData.v().dynamicQuests) {
            if (GameData.v().gameVariables.b("DQ_" + dynamicQuest2.DQ_id) > 0 && dynamicQuest2.quest_ID.equals(str) && dynamicQuest2.variation_ID.equals(str2)) {
                return true;
            }
        }
        return j(dynamicQuest.location_ID) > 0;
    }

    private static boolean c(DynamicQuest dynamicQuest) {
        Quests quests = GameWorld.f3187a;
        String str = dynamicQuest.quest_ID;
        String str2 = dynamicQuest.variation_ID;
        quests.getClass();
        int i2 = Quests.d(str, str2).difficulty;
        int iF = GameData.v().player.sheet.stats.f();
        if (iF < 4 && i2 > 0) {
            return true;
        }
        if (iF >= 7 || i2 <= 1) {
            return (iF < 10 && i2 > 2) || iF < dynamicQuest.minlevel;
        }
        return true;
    }

    public static DynamicQuest e(int i2) {
        for (DynamicQuest dynamicQuest : GameData.v().dynamicQuests) {
            if (dynamicQuest.DQ_id == i2) {
                return dynamicQuest;
            }
        }
        return null;
    }

    public static ArrayList<DynamicQuest> h(String str) {
        ArrayList<DynamicQuest> arrayList = new ArrayList<>();
        for (DynamicQuest dynamicQuest : GameData.v().dynamicQuests) {
            if (GameData.v().gameVariables.b("DQ_" + dynamicQuest.DQ_id) == -5 && dynamicQuest.castleID.equals(str)) {
                arrayList.add(dynamicQuest);
            }
        }
        return arrayList;
    }

    public static int j(String str) {
        if (str != null && !str.equals("")) {
            for (DynamicQuest dynamicQuest : GameData.v().dynamicQuests) {
                if (GameData.v().gameVariables.b("DQ_" + dynamicQuest.DQ_id) > 0) {
                    if (GameData.v().gameVariables.b("DQ_" + dynamicQuest.DQ_id) < 50) {
                        String str2 = dynamicQuest.location_ID;
                        Locale locale = Locale.ENGLISH;
                        if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                            return dynamicQuest.DQ_id;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return 0;
    }

    public final void d() {
        GameData.v().gameVariables.e(100, "DQ_" + this.DQ_id);
        Rules.k(this.difficulty_level, this.rewardTable);
        i(true);
        String strB = GameString.b("QUEST_COMPLETED", false);
        StringBuilder sb = new StringBuilder();
        a.w("RECEIVE_REWARD", false, sb, ": ");
        sb.append(Rules.h(this.difficulty_level, this.rewardTable));
        new e(strB, sb.toString()) { // from class: net.fdgames.GameWorld.DynamicQuest.1
        }.show(z.v().a());
    }

    public final String f() {
        Quests quests = GameWorld.f3187a;
        String str = this.quest_ID;
        String str2 = this.variation_ID;
        quests.getClass();
        for (Quest quest : Quests.f3196a) {
            if (quest.i(str)) {
                return quest.b(str2);
            }
        }
        return "";
    }

    public final String g(int i2) {
        String strC;
        Quests quests = GameWorld.f3187a;
        String str = this.quest_ID;
        quests.getClass();
        Iterator<Quest> it = Quests.f3196a.iterator();
        while (true) {
            if (!it.hasNext()) {
                strC = "";
                break;
            }
            Quest next = it.next();
            if (next.i(str)) {
                strC = next.c(i2);
                break;
            }
        }
        String strReplace = strC.replace("#QUESTGIVER#", "[BLUE]" + GameWorld.f3188b.a(this.castleID).c() + "[]");
        StringBuilder sb = new StringBuilder("[BLUE]");
        Quests quests2 = GameWorld.f3187a;
        String str2 = this.quest_ID;
        quests2.getClass();
        sb.append(Quests.a(str2).d(this.variation_ID).a());
        sb.append("[]");
        return a.l("[BLACK]", FDUtils.e(strReplace.replace("#TARGET#", sb.toString()).replace("#LOCATION#", "[BLUE]" + Quests.b(this.knowledge, this.location_ID) + "[]").replace("#REWARD#", "[BLUE]" + Rules.h(this.difficulty_level, this.rewardTable) + "[]")), "[]");
    }

    public final void i(boolean z2) {
        int i2;
        Quests quests = GameWorld.f3187a;
        String str = this.quest_ID;
        String str2 = this.variation_ID;
        quests.getClass();
        QuestVariation questVariationD = Quests.d(str, str2);
        if (z2) {
            i2 = questVariationD.factionGain;
            if (!questVariationD.rivalFaction.trim().equals("") && questVariationD.rivalFaction.contains("#")) {
                Iterator it = FDUtils.s(questVariationD.rivalFaction).iterator();
                while (it.hasNext()) {
                    String[] strArrSplit = ((String) it.next()).split("#");
                    GameWorld.f3189c.a(Integer.parseInt(strArrSplit[1]) * (-1), 999, strArrSplit[0]);
                }
            }
        } else {
            i2 = questVariationD.factionLoss * (-1);
        }
        if (GameWorld.f3188b.a(this.castleID).faction_id2.equals("")) {
            GameWorld.f3189c.a(i2, questVariationD.maxFactionGain, GameWorld.f3188b.a(this.castleID).faction_id);
            return;
        }
        int i3 = i2 / 2;
        GameWorld.f3189c.a(i3, questVariationD.maxFactionGain, GameWorld.f3188b.a(this.castleID).faction_id);
        GameWorld.f3189c.a(i3, questVariationD.maxFactionGain, GameWorld.f3188b.a(this.castleID).faction_id2);
    }

    public DynamicQuest(String str, String str2) {
        GameWorld.f3187a.getClass();
        if (Quests.a(str) != null) {
            GameWorld.f3187a.getClass();
            if (Quests.a(str).f()) {
                this.quest_ID = str;
                this.castleID = str2;
                this.generationDate = Float.valueOf(GameData.v().u());
                this.expirationDate = Float.valueOf(0.0f);
                GameWorld.f3187a.getClass();
                ArrayList<QuestVariation> arrayListE = Quests.a(str).e();
                String str3 = arrayListE.get(FDUtils.b(0, arrayListE.size() - 1)).id;
                this.variation_ID = str3;
                Quests quests = GameWorld.f3187a;
                String str4 = this.quest_ID;
                quests.getClass();
                this.duration = (FDUtils.b(5, 10) * Quests.d(str4, str3).duration) / 10;
                Quests quests2 = GameWorld.f3187a;
                String str5 = this.quest_ID;
                String str6 = this.variation_ID;
                quests2.getClass();
                this.rewardTable = Quests.d(str5, str6).reward_table;
                Quests quests3 = GameWorld.f3187a;
                String str7 = this.quest_ID;
                String str8 = this.variation_ID;
                quests3.getClass();
                QuestVariation questVariationD = Quests.d(str7, str8);
                GameData.v().player.sheet.stats.f();
                questVariationD.getClass();
                int iF = GameData.v().player.sheet.stats.f() + questVariationD.difficulty;
                int i2 = questVariationD.maxlevel;
                iF = iF > i2 ? i2 : iF;
                int i3 = questVariationD.minlevel;
                this.difficulty_level = iF < i3 ? i3 : iF;
                Quests quests4 = GameWorld.f3187a;
                String str9 = this.quest_ID;
                String str10 = this.variation_ID;
                quests4.getClass();
                this.minlevel = Quests.d(str9, str10).minlevel;
                if (this.difficulty_level < 1) {
                    this.difficulty_level = 1;
                }
                if (this.difficulty_level > 20) {
                    this.difficulty_level = 20;
                }
                Quests quests5 = GameWorld.f3187a;
                String str11 = this.quest_ID;
                String str12 = this.variation_ID;
                quests5.getClass();
                this.location_ID = FDUtils.o(Quests.d(str11, str12).locations);
                this.knowledge = FDUtils.b(0, 1);
                return;
            }
        }
        System.out.println("WARNING: quest " + str + " has no variations and could not be dynamically generated");
    }
}
