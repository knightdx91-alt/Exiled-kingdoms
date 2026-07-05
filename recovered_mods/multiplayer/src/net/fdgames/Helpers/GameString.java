package net.fdgames.Helpers;

import a.a;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.l;
import com.google.android.datatransport.backend.cct.CSLH.qJDUJ;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class GameString {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<GameText> f3441a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<GameText> f3442b;

    public static String a(String str) {
        return b(str, false);
    }

    public static String b(String str, boolean z2) {
        int i2 = 0;
        if (z2) {
            int size = f3442b.size();
            while (i2 < size) {
                if (f3442b.get(i2).tag.equals(str.toLowerCase(Locale.ENGLISH))) {
                    return f3442b.get(i2).get();
                }
                i2++;
            }
        } else {
            int size2 = f3441a.size();
            while (i2 < size2) {
                if (f3441a.get(i2).tag.equals(str.toLowerCase(Locale.ENGLISH))) {
                    return f3441a.get(i2).get();
                }
                i2++;
            }
        }
        System.out.println("WARNING - String not found: " + str.toLowerCase(Locale.ENGLISH));
        return "";
    }

    public static void c() {
        f3441a = new ArrayList<>();
        f3442b = new ArrayList<>();
        d("strings");
        e("names", true);
        e("names2", true);
        d("texts");
    }

    private static void d(String str) {
        try {
            e(str, false);
        } catch (Exception e2) {
            StringBuilder sbT = a.t("GameString.loadStringFile  ERROR-:  ", str, "  -  ");
            sbT.append(e2.getMessage());
            l.e(sbT.toString());
            PrintStream printStream = System.out;
            StringBuilder sbT2 = a.t("GameString.loadStringFile  ERROR-: ", str, "  -  ");
            sbT2.append(e2.getLocalizedMessage());
            printStream.println(sbT2.toString());
        }
    }

    private static void e(String str, boolean z2) {
        String[] strArrSplit = Gdx.files.internal("data/ui/strings/" + str + ".txt").readString("UTF-8").split("\n");
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            String strReplace3 = strReplace2.replace(qJDUJ.gTsOBUIrmk, "\n\r");
            strArrSplit[i2] = strReplace3;
            String strE = FDUtils.e(strReplace3);
            strArrSplit[i2] = strE;
            GameText gameText = new GameText(strE);
            if (z2) {
                f3442b.add(gameText);
            } else {
                f3441a.add(gameText);
            }
        }
    }
}
