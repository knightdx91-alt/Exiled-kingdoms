package net.fdgames.GameWorld;

import android.app.cYK.lRWXeT;
import android.support.v4.app.mFy.fApIihhYHIP;
import androidx.coordinatorlayout.widget.OvMp.SkCylVE;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.WHW.gNaSiQJmMEn;
import java.util.ArrayList;
import net.fdgames.GameEntities.Character;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameText;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class WorldRandomNames {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<GameText> f3423a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<GameText> f3424b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ArrayList<GameText> f3425c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ArrayList<GameText> f3426d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static ArrayList<GameText> f3427e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static ArrayList<GameText> f3428f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static ArrayList<GameText> f3429g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static ArrayList<GameText> f3430h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static ArrayList<GameText> f3431i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static ArrayList<GameText> f3432j;

    public WorldRandomNames() {
        f3423a = new ArrayList<>();
        f3424b = new ArrayList<>();
        f3425c = new ArrayList<>();
        f3426d = new ArrayList<>();
        f3427e = new ArrayList<>();
        f3428f = new ArrayList<>();
        f3429g = new ArrayList<>();
        f3430h = new ArrayList<>();
        f3431i = new ArrayList<>();
        f3432j = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/world/random_names.txt").readString().split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            GameText gameText = new GameText(strReplace2);
            if (gameText.tag.equals("male_first")) {
                f3423a.add(gameText);
            } else if (gameText.tag.equals("female_first")) {
                f3424b.add(gameText);
            } else if (gameText.tag.equals(pgtXvpMCFu.fKdnAvknduFlOBK)) {
                f3425c.add(gameText);
            } else if (gameText.tag.equals("alias_male")) {
                f3426d.add(gameText);
            } else if (gameText.tag.equals(fApIihhYHIP.PwDDCdJ)) {
                f3427e.add(gameText);
            } else if (gameText.tag.equals("humanoid_first")) {
                f3428f.add(gameText);
            } else if (gameText.tag.equals("humanoid_second")) {
                f3429g.add(gameText);
            } else if (gameText.tag.equals("dragon")) {
                f3430h.add(gameText);
            } else if (gameText.tag.equals("lich")) {
                f3431i.add(gameText);
            } else if (gameText.tag.equals("beast")) {
                f3432j.add(gameText);
            }
        }
    }

    private static String a(Character.Gender gender) {
        e();
        if (gender == Character.Gender.f3207b) {
            return f3423a.get(FDUtils.b(0, f3423a.size() - 1)).get();
        }
        return f3424b.get(FDUtils.b(0, f3424b.size() - 1)).get();
    }

    public static String b(String str) {
        e();
        if (str.equals("dragon")) {
            return f3430h.get(FDUtils.b(0, f3430h.size() - 1)).get();
        }
        if (str.equals("lich")) {
            return f3431i.get(FDUtils.b(0, f3431i.size() - 1)).get();
        }
        if (str.equals("beast")) {
            return f3432j.get(FDUtils.b(0, f3432j.size() - 1)).get();
        }
        boolean zEquals = str.equals(SkCylVE.ijUTpsgS);
        String str2 = gNaSiQJmMEn.tXDPvH;
        if (zEquals) {
            return f3428f.get(FDUtils.b(0, f3428f.size() - 1)).get() + str2 + f3429g.get(FDUtils.b(0, f3429g.size() - 1)).get();
        }
        return f3428f.get(FDUtils.b(0, f3428f.size() - 1)).get() + str2 + f3429g.get(FDUtils.b(0, f3429g.size() - 1)).get();
    }

    public static String c(Character.Gender gender) {
        String str;
        e();
        int iB = FDUtils.b(1, 10);
        if (iB <= 6) {
            return a(gender) + " " + f3425c.get(FDUtils.b(0, f3425c.size() - 1)).get();
        }
        if (iB > 8) {
            return a(gender);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a(gender));
        sb.append(" ");
        if (gender == Character.Gender.f3207b) {
            str = f3426d.get(FDUtils.b(0, f3426d.size() - 1)).get();
        } else {
            str = f3427e.get(FDUtils.b(0, f3427e.size() - 1)).get();
        }
        sb.append(str);
        return sb.toString();
    }

    public static String d(Character.Gender gender) {
        e();
        return a(gender) + lRWXeT.ijPrBYDef + f3425c.get(FDUtils.b(0, f3425c.size() - 1)).get();
    }

    private static void e() {
        if (f3423a == null) {
            new WorldRandomNames();
        }
    }
}
