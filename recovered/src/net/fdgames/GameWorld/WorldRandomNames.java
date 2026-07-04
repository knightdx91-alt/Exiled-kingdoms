package net.fdgames.GameWorld;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import net.fdgames.GameEntities.Character;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameText;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class WorldRandomNames {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<GameText> f3202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<GameText> f3203b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ArrayList<GameText> f3204c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ArrayList<GameText> f3205d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static ArrayList<GameText> f3206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static ArrayList<GameText> f3207f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static ArrayList<GameText> f3208g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static ArrayList<GameText> f3209h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static ArrayList<GameText> f3210i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static ArrayList<GameText> f3211j;

    public WorldRandomNames() {
        f3202a = new ArrayList<>();
        f3203b = new ArrayList<>();
        f3204c = new ArrayList<>();
        f3205d = new ArrayList<>();
        f3206e = new ArrayList<>();
        f3207f = new ArrayList<>();
        f3208g = new ArrayList<>();
        f3209h = new ArrayList<>();
        f3210i = new ArrayList<>();
        f3211j = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/world/random_names.txt").readString().split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            GameText gameText = new GameText(strReplace2);
            if (gameText.tag.equals("male_first")) {
                f3202a.add(gameText);
            } else if (gameText.tag.equals("female_first")) {
                f3203b.add(gameText);
            } else if (gameText.tag.equals("human_second")) {
                f3204c.add(gameText);
            } else if (gameText.tag.equals("alias_male")) {
                f3205d.add(gameText);
            } else if (gameText.tag.equals("alias_female")) {
                f3206e.add(gameText);
            } else if (gameText.tag.equals("humanoid_first")) {
                f3207f.add(gameText);
            } else if (gameText.tag.equals("humanoid_second")) {
                f3208g.add(gameText);
            } else if (gameText.tag.equals("dragon")) {
                f3209h.add(gameText);
            } else if (gameText.tag.equals("lich")) {
                f3210i.add(gameText);
            } else if (gameText.tag.equals("beast")) {
                f3211j.add(gameText);
            }
        }
    }

    private static String a(Character.Gender gender) {
        if (gender == Character.Gender.f2992a) {
            return f3202a.get(FDUtils.b(0, f3202a.size() - 1)).get();
        }
        return f3203b.get(FDUtils.b(0, f3203b.size() - 1)).get();
    }

    public static String b(String str) {
        if (str.equals("dragon")) {
            return f3209h.get(FDUtils.b(0, f3209h.size() - 1)).get();
        }
        if (str.equals("lich")) {
            return f3210i.get(FDUtils.b(0, f3210i.size() - 1)).get();
        }
        if (str.equals("beast")) {
            return f3211j.get(FDUtils.b(0, f3211j.size() - 1)).get();
        }
        if (str.equals("giant")) {
            return f3207f.get(FDUtils.b(0, f3207f.size() - 1)).get() + " " + f3208g.get(FDUtils.b(0, f3208g.size() - 1)).get();
        }
        return f3207f.get(FDUtils.b(0, f3207f.size() - 1)).get() + " " + f3208g.get(FDUtils.b(0, f3208g.size() - 1)).get();
    }

    public static String c(Character.Gender gender) {
        String str;
        int iB = FDUtils.b(1, 10);
        if (iB <= 6) {
            return a(gender) + " " + f3204c.get(FDUtils.b(0, f3204c.size() - 1)).get();
        }
        if (iB > 8) {
            return a(gender);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a(gender));
        sb.append(" ");
        if (gender == Character.Gender.f2992a) {
            str = f3205d.get(FDUtils.b(0, f3205d.size() - 1)).get();
        } else {
            str = f3206e.get(FDUtils.b(0, f3206e.size() - 1)).get();
        }
        sb.append(str);
        return sb.toString();
    }

    public static String d(Character.Gender gender) {
        return a(gender) + " " + f3204c.get(FDUtils.b(0, f3204c.size() - 1)).get();
    }
}
