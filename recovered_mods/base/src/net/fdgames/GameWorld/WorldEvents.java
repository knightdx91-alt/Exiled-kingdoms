package net.fdgames.GameWorld;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Locale;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class WorldEvents {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<WorldEvent> f3198a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ArrayList<EventLocation> f3199b = new ArrayList<>();

    public WorldEvents() {
        int i2;
        f3199b = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/world/event_locations.txt").readString().split("\n");
        int i3 = 0;
        while (true) {
            if (i3 >= strArrSplit.length) {
                break;
            }
            String strReplace = strArrSplit[i3].replace("\n", "");
            strArrSplit[i3] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i3] = strReplace2;
            EventLocation eventLocation = new EventLocation();
            String[] strArrSplit2 = strReplace2.replace("\"", "").split("\t", -1);
            eventLocation.id = strArrSplit2[0].toLowerCase(Locale.ENGLISH);
            eventLocation.knowledge = strArrSplit2[1];
            if (Settings.h() == 2 && !strArrSplit2[2].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[2];
            }
            if (Settings.h() == 3 && !strArrSplit2[3].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[3];
            }
            if (Settings.h() == 4 && !strArrSplit2[4].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[4];
            }
            if (Settings.h() == 5 && !strArrSplit2[5].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[5];
            }
            if (Settings.h() == 6 && !strArrSplit2[6].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[6];
            }
            if (Settings.h() == 7 && !strArrSplit2[7].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[7];
            }
            if (Settings.h() == 8 && !strArrSplit2[8].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[8];
            }
            if (Settings.h() == 9 && !strArrSplit2[9].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[9];
            }
            if (Settings.h() == 10 && !strArrSplit2[10].trim().equals("")) {
                eventLocation.knowledge = strArrSplit2[10];
            }
            f3199b.add(eventLocation);
            i3++;
        }
        f3198a = new ArrayList<>();
        String[] strArrSplit3 = Gdx.files.internal("data/world/events.txt").readString().split("\n");
        for (i2 = 1; i2 < strArrSplit3.length; i2++) {
            String strReplace3 = strArrSplit3[i2].replace("\n", "");
            strArrSplit3[i2] = strReplace3;
            String strReplace4 = strReplace3.replace("\r", "");
            strArrSplit3[i2] = strReplace4;
            f3198a.add(new WorldEvent(strReplace4));
        }
    }

    public static WorldEvent a(String str) {
        for (WorldEvent worldEvent : f3198a) {
            if (worldEvent.id.equals(str)) {
                return worldEvent;
            }
        }
        return null;
    }

    public static String b(int i2, String str) {
        if (i2 == 0) {
            return GameString.b("UNKNOWN_LOCATION", false);
        }
        String strSubstring = str.substring(0, str.length() - 2);
        if (strSubstring.equals("h11_cave")) {
            strSubstring = "h11_cave_1";
        }
        if (i2 == 1) {
            String strB = GameString.b("LOCATION_REGION", false);
            Areas areas = GameWorld.f3192f;
            Area areaB = areas.b(strSubstring);
            if (strSubstring.contains("_")) {
                areaB = areas.b(strSubstring.split("\\_")[0]);
            }
            return strB.replace("##REGION##", areaB.c());
        }
        if (i2 == 2) {
            return GameWorld.f3192f.a(strSubstring);
        }
        for (EventLocation eventLocation : f3199b) {
            if (eventLocation.id.equals(str)) {
                String str2 = eventLocation.knowledge;
                if (str2.equals("")) {
                    return GameWorld.f3192f.a(strSubstring);
                }
                return GameWorld.f3192f.a(strSubstring) + ", " + str2;
            }
        }
        return GameString.b("UNKNOWN_LOCATION", false);
    }

    public static WorldEvent c() {
        return d(FDUtils.n());
    }

    private static WorldEvent d(FDUtils.Rarity rarity) {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        int iF = GameData.v().player.sheet.stats.f();
        ArrayList arrayList = new ArrayList();
        for (WorldEvent worldEvent : f3198a) {
            if (worldEvent.rarity.equals(rarity) && worldEvent.minlevel <= iF && worldEvent.maxlevel >= iF) {
                arrayList.add(worldEvent);
            }
        }
        if (arrayList.size() > 0) {
            return (WorldEvent) arrayList.get(FDUtils.b(0, arrayList.size() - 1));
        }
        if (rarity.equals(FDUtils.Rarity.ULTRA_RARE)) {
            return d(FDUtils.Rarity.RARE);
        }
        if (rarity.equals(FDUtils.Rarity.RARE)) {
            return d(FDUtils.Rarity.UNCOMMON);
        }
        if (rarity.equals(FDUtils.Rarity.UNCOMMON)) {
            return d(FDUtils.Rarity.COMMON);
        }
        return null;
    }
}
