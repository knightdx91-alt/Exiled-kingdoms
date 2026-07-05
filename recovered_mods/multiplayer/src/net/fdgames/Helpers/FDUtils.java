package net.fdgames.Helpers;

import a.a;
import androidx.core.content.uvr.JNrsKSCxIEXndG;
import androidx.drawerlayout.widget.Qm.ReTXwDyZpZSd;
import com.google.android.datatransport.fJ.lxYOBQSyWPCj;
import com.pairip.licensecheck.wYh.JDxjJEGD;
import h0.LN.EXicjtag;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;
import net.fdgames.ek.Settings;
import o.Oeoo.vIBRkbZbNjpf;
import t.Lzz.yorS;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class FDUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Random f3433a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static byte[] f3434b = new byte[1000];

    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        ULTRA_RARE
    }

    public static String a(int i2) {
        if (i2 < 1 || i2 > 3999) {
            return "Invalid Roman Number Value";
        }
        String strZ = "";
        while (i2 >= 1000) {
            strZ = a.z(strZ, "M");
            i2 -= 1000;
        }
        while (i2 >= 900) {
            strZ = a.z(strZ, "CM");
            i2 -= 900;
        }
        while (i2 >= 500) {
            strZ = a.z(strZ, "D");
            i2 -= 500;
        }
        while (i2 >= 400) {
            strZ = a.z(strZ, "CD");
            i2 -= 400;
        }
        while (i2 >= 100) {
            strZ = a.z(strZ, "C");
            i2 -= 100;
        }
        while (i2 >= 90) {
            strZ = a.z(strZ, "XC");
            i2 -= 90;
        }
        while (i2 >= 50) {
            strZ = a.z(strZ, "L");
            i2 -= 50;
        }
        while (i2 >= 40) {
            strZ = a.z(strZ, "XL");
            i2 -= 40;
        }
        while (i2 >= 10) {
            strZ = a.z(strZ, JNrsKSCxIEXndG.PxqMzgKMmQNGVYN);
            i2 -= 10;
        }
        while (i2 >= 9) {
            strZ = a.z(strZ, "IX");
            i2 -= 9;
        }
        while (i2 >= 5) {
            strZ = a.z(strZ, "V");
            i2 -= 5;
        }
        while (i2 >= 4) {
            strZ = a.z(strZ, "IV");
            i2 -= 4;
        }
        while (i2 >= 1) {
            strZ = a.z(strZ, "I");
            i2--;
        }
        return strZ;
    }

    public static int b(int i2, int i3) {
        return i2 + ((int) (Math.random() * ((double) ((i3 - i2) + 1))));
    }

    public static int c(int i2, int i3) {
        int i4 = (i2 - i3) * 3;
        if (i4 > 15) {
            i4 = 15;
        }
        if (i4 < -30) {
            return -30;
        }
        return i4;
    }

    public static String d(String str) {
        return str.replaceAll("\\s+", "").replace("\"", "");
    }

    public static String e(String str) {
        return str.replace(" [", "  [");
    }

    public static String f(String str) {
        String strR = r(e(str));
        if (!strR.contains("\"")) {
            return strR;
        }
        String strTrim = strR.trim();
        if (strTrim.startsWith("\"") && strTrim.endsWith("\"")) {
            strTrim = strTrim.substring(1, strTrim.length() - 1);
        }
        return strTrim.replaceAll("\"\"", "'").replaceAll("\"", "'");
    }

    public static String g(float f2) {
        int i2 = (int) f2;
        int i3 = i2 / 1080;
        int i4 = (i2 - (i3 * 1080)) / 45;
        StringBuilder sb = new StringBuilder("%d ");
        a.v("DAYS", false, sb, lxYOBQSyWPCj.OOGEQVUZSD);
        return String.format(a.n("HOURS", false, sb), Integer.valueOf(i3), Integer.valueOf(i4));
    }

    public static String h() {
        StringBuilder sb = new StringBuilder();
        sb.append(GameString.b("DAY", false) + " " + (((int) GameLevel.b()) / 1080));
        sb.append(", ");
        sb.append(k());
        return sb.toString();
    }

    public static int i(int i2) {
        return f3434b[i2];
    }

    public static int j() {
        return (((int) GameLevel.b()) % 1080) / 45;
    }

    public static String k() {
        if (j() > 0 && j() < 12) {
            return j() + " AM";
        }
        if (j() == 0) {
            return "12 AM";
        }
        if (j() == 12) {
            return JDxjJEGD.oFfGKUjDsxJmXia;
        }
        return (j() - 12) + yorS.UfJzPzuhItO;
    }

    public static String l() {
        switch (Settings.h()) {
            case 3:
                return "RU/";
            case 4:
                return "PT/";
            case 5:
                return "DE/";
            case 6:
                return "PL/";
            case 7:
                return "CZ/";
            case 8:
                return pgtXvpMCFu.RKzpQ;
            case 9:
                return "IT/";
            case 10:
                return "FR/";
            default:
                return "";
        }
    }

    public static double m() {
        double dRandom = Math.random();
        double dRandom2 = Math.random();
        if (dRandom < 0.5d) {
            dRandom2 = 1.0d - dRandom2;
        }
        return (dRandom2 * 1.0d) + 0.0d;
    }

    public static Rarity n() {
        int iB = b(1, 100);
        return iB > 99 ? Rarity.ULTRA_RARE : iB > 94 ? Rarity.RARE : iB > 66 ? Rarity.UNCOMMON : Rarity.COMMON;
    }

    public static String o(String str) {
        return (String) Arrays.asList(str.trim().split(";")).get(b(0, r2.size() - 1));
    }

    public static void p() {
        Random random = new Random(250L);
        f3433a = random;
        random.setSeed(250L);
        for (int i2 = 0; i2 < 1000; i2++) {
            f3434b[i2] = (byte) (f3433a.nextInt(100) + 1);
        }
    }

    public static String q(int i2) {
        double d2 = i2;
        if (d2 < 10000.0d) {
            return "" + ((int) d2);
        }
        if (d2 >= 1000000.0d) {
            return new DecimalFormat("##.##").format(d2 / 1000000.0d) + "m";
        }
        String str = EXicjtag.lJKnKZsexissR;
        if (d2 >= 100000.0d) {
            return new DecimalFormat("##").format(d2 / 1000.0d) + str;
        }
        return new DecimalFormat("##.#").format(d2 / 1000.0d) + str;
    }

    public static String r(String str) {
        if (!str.contains("{")) {
            return str;
        }
        return str.replace("{LEVELx100}", "" + (GameData.v().player.sheet.stats.f() * 100)).replace("{LEVELx50}", "" + (GameData.v().player.sheet.stats.f() * 50)).replace("{LEVELx30}", "" + (GameData.v().player.sheet.stats.f() * 30)).replace("{LEVELx25}", "" + (GameData.v().player.sheet.stats.f() * 25)).replace("{LEVELx20}", "" + (GameData.v().player.sheet.stats.f() * 20)).replace("{LEVELx10}", "" + (GameData.v().player.sheet.stats.f() * 10)).replace("{LEVELx2}", "" + (GameData.v().player.sheet.stats.f() * 2)).replace("{RESET_COST}", "" + GameData.v().x()).replace(vIBRkbZbNjpf.tRDv, "" + GameData.v().w());
    }

    public static ArrayList s(String str) {
        List<String> listAsList = Arrays.asList(d(str).split(";"));
        ArrayList arrayList = new ArrayList();
        for (String str2 : listAsList) {
            if (str2.contains("#")) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    public static Rarity t(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        return lowerCase.contains(ReTXwDyZpZSd.tAW) ? Rarity.ULTRA_RARE : lowerCase.contains("ra") ? Rarity.RARE : lowerCase.contains("un") ? Rarity.UNCOMMON : Rarity.COMMON;
    }
}
