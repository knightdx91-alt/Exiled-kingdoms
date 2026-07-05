package net.fdgames.Helpers;

import a.a;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.l;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GameString {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<GameText> f3220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<GameText> f3221b;

    public static String a(String str) {
        return b(str, false);
    }

    public static String b(String str, boolean z2) {
        int i2 = 0;
        if (z2) {
            int size = f3221b.size();
            while (i2 < size) {
                if (f3221b.get(i2).tag.equals(str.toLowerCase(Locale.ENGLISH))) {
                    return f3221b.get(i2).get();
                }
                i2++;
            }
        } else {
            int size2 = f3220a.size();
            while (i2 < size2) {
                if (f3220a.get(i2).tag.equals(str.toLowerCase(Locale.ENGLISH))) {
                    return f3220a.get(i2).get();
                }
                i2++;
            }
        }
        System.out.println("WARNING - String not found: " + str.toLowerCase(Locale.ENGLISH));
        return "";
    }

    public static void c() {
        f3220a = new ArrayList<>();
        f3221b = new ArrayList<>();
        d("strings");
        e("names", true);
        e("names2", true);
        d("texts");
    }

    private static void d(String str) {
        try {
            e(str, false);
        } catch (Exception e2) {
            StringBuilder sbU = a.u("GameString.loadStringFile  ERROR-:  ", str, "  -  ");
            sbU.append(e2.getMessage());
            l.d(sbU.toString());
            PrintStream printStream = System.out;
            StringBuilder sbU2 = a.u("GameString.loadStringFile  ERROR-: ", str, "  -  ");
            sbU2.append(e2.getLocalizedMessage());
            printStream.println(sbU2.toString());
        }
    }

    private static void e(String str, boolean z2) {
        String[] strArrSplit = Gdx.files.internal("data/ui/strings/" + str + ".txt").readString("UTF-8").split("\n");
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            String strReplace3 = strReplace2.replace("<p>", "\n\r");
            strArrSplit[i2] = strReplace3;
            String strE = FDUtils.e(strReplace3);
            strArrSplit[i2] = strE;
            GameText gameText = new GameText(strE);
            if (z2) {
                f3221b.add(gameText);
            } else {
                f3220a.add(gameText);
            }
        }
    }
}
