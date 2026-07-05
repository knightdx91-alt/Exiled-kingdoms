package net.fdgames.GameWorld;

import android.app.cYK.BQmoQ;
import android.support.v4.app.mFy.fApIihhYHIP;
import androidx.core.content.uvr.JNrsKSCxIEXndG;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.a;
import java.util.ArrayList;
import java.util.Locale;
import n0.WOA.IUcMoQOkPHcA;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Areas {
    private ArrayList<Area> areasList;
    private ArrayList<MapRegion> regionList = new ArrayList<>();

    public Areas() {
        a aVarInternal = Gdx.files.internal("data/world/regions.txt");
        String str = fApIihhYHIP.QijfKbXck;
        String[] strArrSplit = aVarInternal.readString(str).split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            MapRegion mapRegion = new MapRegion();
            String[] strArrSplit2 = strReplace2.replace("\"", "").split("\t", -1);
            mapRegion.id = strArrSplit2[0];
            switch (Settings.h()) {
                case 1:
                    mapRegion.name = strArrSplit2[1];
                    break;
                case 2:
                    mapRegion.name = strArrSplit2[2];
                    break;
                case 3:
                    mapRegion.name = strArrSplit2[3];
                    break;
                case 4:
                    mapRegion.name = strArrSplit2[4];
                    break;
                case 5:
                    mapRegion.name = strArrSplit2[5];
                    break;
                case 6:
                    mapRegion.name = strArrSplit2[6];
                    break;
                case 7:
                    mapRegion.name = strArrSplit2[7];
                    break;
                default:
                    mapRegion.name = strArrSplit2[1];
                    break;
            }
            if (mapRegion.name.trim().equals("")) {
                mapRegion.name = strArrSplit2[1];
            }
            this.regionList.add(mapRegion);
        }
        this.areasList = new ArrayList<>();
        String[] strArrSplit3 = Gdx.files.internal("data/world/areas.txt").readString(str).split("\n");
        for (int i3 = 1; i3 < strArrSplit3.length; i3++) {
            String strReplace3 = strArrSplit3[i3].replace("\n", "");
            strArrSplit3[i3] = strReplace3;
            String strReplace4 = strReplace3.replace("\r", "");
            strArrSplit3[i3] = strReplace4;
            this.areasList.add(new Area(strReplace4));
        }
    }

    public static String d(String str) {
        if (str.contains("_")) {
            str = str.split("\\_")[0];
        }
        return !GameData.v().d(str) ? "unknown" : str;
    }

    public static Coords h(String str) {
        if (!str.contains("_")) {
            return GameWorld.f3413f.b(str).b();
        }
        return GameWorld.f3413f.b(str.split("\\_")[0]).b();
    }

    public static boolean j(String str) {
        return str.startsWith("NG") || str.startsWith(JNrsKSCxIEXndG.GLPBw) || str.startsWith("NI") || str.startsWith("IM");
    }

    public final String a(String str) {
        Area areaB = b(str);
        String strB = GameString.b("UNKNOWN_AREA", false);
        if (areaB == null) {
            return strB;
        }
        if (!str.contains("_")) {
            return GameString.b("LOCATION_AREA", false).replace("##AREA##", areaB.a());
        }
        Area areaB2 = b(str.split("\\_")[0]);
        if (areaB2 == null) {
            return strB;
        }
        return GameString.b("LOCATION_AREA", false).replace("##AREA##", areaB2.a()) + ", " + GameString.b("LOCATION_DUNGEON", false).replace("##DUNGEON##", areaB.a());
    }

    public final Area b(String str) {
        for (Area area : this.areasList) {
            String str2 = area.id;
            Locale locale = Locale.ENGLISH;
            if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                return area;
            }
        }
        System.out.println("WARNING: area not found, " + str);
        return null;
    }

    public final String c(int i2, int i3) {
        for (Area area : this.areasList) {
            if (area.worldmap && area.b().f3508x == i2 && area.b().f3509y == i3) {
                return area.id;
            }
        }
        return "";
    }

    public final String e(String str) {
        Area areaB;
        Area areaB2 = b(str);
        String strB = GameString.b("UNKNOWN_AREA", false);
        return !GameData.v().d(str) ? strB : (areaB2 == null || !areaB2.worldmap) ? (!str.contains("_") || (areaB = b(str.split("\\_")[0])) == null) ? strB : areaB.a() : areaB2.a();
    }

    public final String f(String str) {
        Area areaB;
        Area areaB2 = b(str);
        String strB = GameString.b("UNKNOWN_REGION", false);
        if (areaB2 != null && (areaB2.id.equals(BQmoQ.tyFDFs) || areaB2.id.equals(IUcMoQOkPHcA.QXUfXwckCtMbFn) || areaB2.id.equals("NI") || areaB2.id.equals("IM"))) {
            strB = "";
        }
        return !GameData.v().d(str) ? strB : (areaB2 == null || !areaB2.outdoor) ? (!str.contains("_") || (areaB = b(str.split("\\_")[0])) == null) ? strB : areaB.c() : areaB2.c();
    }

    public final String g(String str) {
        for (Area area : this.areasList) {
            String str2 = area.id;
            Locale locale = Locale.ENGLISH;
            if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                return area.a();
            }
        }
        System.out.println("WARNING: area not found, " + str);
        return "";
    }

    public final String i(String str) {
        for (MapRegion mapRegion : this.regionList) {
            String str2 = mapRegion.id;
            Locale locale = Locale.ENGLISH;
            if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                return mapRegion.name;
            }
        }
        System.out.println("WARNING: region not found, " + str);
        return "";
    }
}
