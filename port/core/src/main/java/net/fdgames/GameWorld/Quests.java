package net.fdgames.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.l;
import java.util.ArrayList;
import java.util.Locale;
import net.fdgames.Helpers.GameString;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Quests {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ArrayList<Quest> f3196a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ArrayList<QuestLocation> f3197b = new ArrayList<>();

    public Quests() {
        f3197b = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/quests/variations/quest_locations.txt").readString().split("\n");
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            QuestLocation questLocation = new QuestLocation();
            String[] strArrSplit2 = strReplace2.replace("\"", "").split("\t", -1);
            questLocation.id = strArrSplit2[0].toLowerCase(Locale.ENGLISH);
            questLocation.desc = strArrSplit2[1];
            questLocation.desc_ES = strArrSplit2[2];
            f3197b.add(questLocation);
        }
        f3196a = new ArrayList<>();
        String[] strArrSplit3 = Gdx.files.internal("data/quests/list.txt").readString().split("\n");
        for (int i3 = 0; i3 < strArrSplit3.length; i3++) {
            String strReplace3 = strArrSplit3[i3].replace("\n", "");
            strArrSplit3[i3] = strReplace3;
            String strReplace4 = strReplace3.replace("\r", "");
            strArrSplit3[i3] = strReplace4;
            String[] strArrSplit4 = strReplace4.split("\t", -1);
            try {
                f3196a.add(new Quest(strArrSplit4[0]));
            } catch (Exception unused) {
                l.d("ExiledKingdoms.GameWorld.load() -        >     >  ERROR! quest:" + strArrSplit4[0]);
            }
        }
        l.d("ExiledKingdoms.GameWorld.load() -        >     >" + f3196a.size() + " quests added");
    }

    public static Quest a(String str) {
        for (Quest quest : f3196a) {
            if (quest.i(str)) {
                return quest;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String b(int i2, String str) {
        String strB;
        if (i2 == 0) {
            return GameString.b("UNKNOWN_LOCATION", false);
        }
        String strSubstring = str.substring(0, str.length() - 2);
        if (i2 == 1) {
            String strB2 = GameString.b("LOCATION_REGION", false);
            Areas areas = GameWorld.f3192f;
            Area areaB = areas.b(strSubstring);
            if (strSubstring.contains("_")) {
                areaB = areas.b(strSubstring.split("\\_")[0]);
            }
            return strB2.replace("##REGION##", areaB.c());
        }
        if (i2 == 2) {
            return GameWorld.f3192f.a(strSubstring);
        }
        for (QuestLocation questLocation : f3197b) {
            if (questLocation.id.equals(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append(GameWorld.f3192f.a(strSubstring));
                sb.append(", ");
                if (Settings.h() == 2) {
                    strB = i2 == 3 ? questLocation.desc_ES : GameString.b("UNKNOWN_LOCATION", false);
                } else if (i2 == 3) {
                    strB = questLocation.desc;
                }
                sb.append(strB);
                return sb.toString();
            }
        }
        System.out.println("WARNING: unknown quest location " + str + " " + i2);
        return GameString.b("UNKNOWN_LOCATION", false);
    }

    public static String c(String str) {
        for (Quest quest : f3196a) {
            if (quest.i(str)) {
                return quest.a();
            }
        }
        return "";
    }

    public static QuestVariation d(String str, String str2) {
        for (Quest quest : f3196a) {
            if (quest.i(str)) {
                for (QuestVariation questVariation : quest.e()) {
                    if (questVariation.id.equals(str2)) {
                        return questVariation;
                    }
                }
            }
        }
        return null;
    }
}
