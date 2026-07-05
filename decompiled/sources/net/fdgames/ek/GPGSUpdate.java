package net.fdgames.ek;

import l0.b;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.ek.android.MainActivity;
import r0.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GPGSUpdate {
    private static boolean A;
    private static boolean B;
    private static boolean C;
    private static boolean D;
    private static boolean E;
    private static boolean F;
    private static boolean G;
    private static boolean H;
    private static boolean I;
    private static boolean J;
    private static boolean K;
    private static boolean L;
    private static boolean M;
    private static boolean N;
    private static boolean O;
    private static boolean P;
    public static int Q;
    public static int R;
    public static int S;
    public static int T;
    public static int U;
    public static int V;
    public static int W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f3394b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f3395c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f3396d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f3397e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f3398f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f3399g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static boolean f3400h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static boolean f3401i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static boolean f3402j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static boolean f3403k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static boolean f3404l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static boolean f3405m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static boolean f3406n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static boolean f3407o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static boolean f3408p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static boolean f3409q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static boolean f3410r;
    private static boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static boolean f3411t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static boolean f3412u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static boolean f3413v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static boolean f3414w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static boolean f3415x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static boolean f3416y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static boolean f3417z;

    public static void a() {
        if (ExiledKingdoms.f3378h || ExiledKingdoms.f() == null || !((MainActivity) ExiledKingdoms.f()).l()) {
            return;
        }
        int i2 = Settings.e("don1") > 0 ? 1 : 0;
        if (Settings.e("don2") > 0) {
            i2++;
        }
        if (Settings.e("don3") > 0) {
            i2++;
        }
        if (i2 > 0 && !f3416y) {
            ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQNQ");
            f3416y = true;
        }
        if (i2 > 1 && !f3417z) {
            ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQNg");
            f3417z = true;
        }
        if (i2 <= 2 || A) {
            return;
        }
        ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQNw");
        A = true;
    }

    public static void b() {
        f3393a = false;
        f3394b = false;
        f3395c = false;
        f3396d = false;
        f3397e = false;
        f3398f = false;
        f3399g = false;
        f3400h = false;
        f3401i = false;
        f3402j = false;
        f3403k = false;
        f3404l = false;
        f3405m = false;
        f3406n = false;
        f3407o = false;
        f3408p = false;
        f3409q = false;
        f3410r = false;
        s = false;
        f3411t = false;
        f3412u = false;
        f3413v = false;
        f3414w = false;
        f3415x = false;
        f3416y = false;
        f3417z = false;
        A = false;
        B = false;
        C = false;
        D = false;
        E = false;
        F = false;
        G = false;
        H = false;
        I = false;
        J = false;
        K = false;
        L = false;
        M = false;
        N = false;
        O = false;
        P = false;
    }

    public static void c(boolean z2) {
        if (ExiledKingdoms.f3378h) {
            GameData.v().getClass();
            if (!GameData.I()) {
                return;
            }
        }
        if (!GameLevel.l() && !ExiledKingdoms.f3378h) {
            b.s = true;
            return;
        }
        b.s = false;
        Q = 0;
        R = 0;
        S = 0;
        T = 0;
        U = 0;
        V = 0;
        try {
            if (ExiledKingdoms.f() == null || !((MainActivity) ExiledKingdoms.f()).l()) {
                if (!z2) {
                    return;
                }
            }
            int iF = f();
            Q = iF;
            int iG = iF + g();
            Q = iG;
            int iJ = iG + j();
            Q = iJ;
            int iH = iJ + h();
            Q = iH;
            int i2 = iH + i();
            Q = i2;
            int iD = i2 + d();
            Q = iD;
            Q = iD + e();
            S = GameData.v().gameVariables.f() / 4;
            R = GameData.v().player.sheet.stats.i() / 300;
            U = GameData.v().i() * 20;
            V = GameData.v().h() * 5;
            T = GameData.v().T() * 5;
            if (Settings.f().equals("99999")) {
                return;
            }
            if (GameData.v().G()) {
                int i3 = (int) ((Q + R + S + U + V + T) * 1.2f);
                if (((MainActivity) ExiledKingdoms.f()).l() && i3 > Settings.e("GL_ironman_score")) {
                    Settings.A(i3, "GL_ironman_score");
                    ((MainActivity) ExiledKingdoms.f()).r(i3);
                }
                W = i3;
                return;
            }
            int i4 = Q + R + S + U + V + T;
            if (GameData.v().E()) {
                i4 = (int) (i4 * 1.2f);
            }
            if (GameData.v().D()) {
                i4 = (int) (i4 * 0.5f);
            }
            if (GameData.v().F()) {
                i4 = (int) (i4 * 0.2f);
            }
            if (((MainActivity) ExiledKingdoms.f()).l() && i4 > Settings.e("GL_score")) {
                Settings.A(i4, "GL_score");
                ((MainActivity) ExiledKingdoms.f()).s(i4);
            }
            W = i4;
        } catch (Exception e2) {
            a.a("ERROR 6.1;" + e2.getMessage());
        }
    }

    private static int d() {
        int i2;
        int i3 = 0;
        if (ExiledKingdoms.f() != null && ((MainActivity) ExiledKingdoms.f()).l()) {
            if (GameData.v().gameVariables.b("EXP_NG") == 1) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQHw");
                i2 = 1;
            } else {
                i2 = 0;
            }
            for (char c2 = 'B'; c2 <= 'K'; c2 = (char) (c2 + 1)) {
                for (int i4 = 3; i4 <= 13; i4++) {
                    String str = Character.toString(c2) + i4;
                    if (GameData.v().gameVariables.b("EXP_" + str) == 1) {
                        i3++;
                    }
                }
            }
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQAw");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQFA");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQFQ");
            if (i3 >= 4) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQAw");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQFA");
                i2++;
            }
            if (i3 >= 12) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQFA");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQFQ");
                i2++;
            }
            if (i3 >= 25) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQFQ");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQTw");
                i2++;
            }
            if (i3 >= 42) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQTw");
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
        }
        return i3 * 10;
    }

    private static int e() {
        float f2 = (float) (GameData.v().realTime / 3600.0d);
        int i2 = 0;
        if (f2 < 25.0f) {
            return 0;
        }
        if (ExiledKingdoms.f() != null && ((MainActivity) ExiledKingdoms.f()).l()) {
            if (f2 >= 25.0f) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQQw");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQRA");
                i2 = 1;
            }
            if (f2 >= 50.0f) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQRA");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQRQ");
                i2++;
            }
            if (f2 >= 100.0f) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQRQ");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQRg");
                i2++;
            }
            if (f2 >= 250.0f) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQRg");
                i2++;
            }
        }
        return i2 * 10;
    }

    private static int f() {
        int i2 = 0;
        if (ExiledKingdoms.f() != null && ((MainActivity) ExiledKingdoms.f()).l()) {
            int i3 = GameData.v().y().killed;
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQKw");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQBQ");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQBg");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQBw");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQCA");
            if (i3 >= 20) {
                if (ExiledKingdoms.f3378h) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQKw");
                }
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQBQ");
                i2 = 1;
            }
            if (i3 >= 100) {
                if (ExiledKingdoms.f3378h) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQBQ");
                }
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQBg");
                i2++;
            }
            if (i3 >= 500) {
                if (ExiledKingdoms.f3378h) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQBg");
                }
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQBw");
                i2++;
            }
            if (i3 >= 2500) {
                if (ExiledKingdoms.f3378h) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQBw");
                }
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQCA");
                i2++;
            }
            if (i3 >= 10000) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQCA");
                i2++;
            }
        }
        return i2 * 10;
    }

    private static int g() {
        int i2 = 0;
        if (ExiledKingdoms.f() != null && ((MainActivity) ExiledKingdoms.f()).l()) {
            int iF = GameData.v().player.sheet.stats.f();
            if (iF >= 2) {
                if (!I) {
                    I = true;
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQAA");
                    ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQAQ");
                }
                i2 = 1;
            }
            if (iF >= 6) {
                i2++;
                if (!J) {
                    J = true;
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQAQ");
                    ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQAg");
                }
            }
            if (iF >= 12) {
                i2++;
                if (!K) {
                    K = true;
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQAg");
                    ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQEA");
                }
            }
            if (iF >= 18) {
                i2++;
                if (!L) {
                    L = true;
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQEA");
                    ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQLQ");
                }
            }
            if (iF >= 25) {
                i2++;
                if (!M) {
                    M = true;
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQLQ");
                }
            }
        }
        return i2 * 10;
    }

    private static int h() {
        int i2;
        if (ExiledKingdoms.f() != null && ((MainActivity) ExiledKingdoms.f()).l()) {
            if (f3413v || GameData.v().gameVariables.b("guild_warriors") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQJA");
                f3413v = true;
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (f3414w || GameData.v().gameVariables.b("guild_seventh") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQJQ");
                i2++;
                f3414w = true;
            }
            if (f3415x || GameData.v().gameVariables.b("guild_three") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQIw");
                i2++;
                f3415x = true;
            }
            if (O || GameData.v().gameVariables.b("guild_wizards") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQJw");
                i2++;
                O = true;
            }
            if (f3406n || GameData.v().H("undermother")) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQPQ");
                i2++;
                f3406n = true;
            }
            if (GameData.v().gameVariables.b("kingslayer") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQFg");
                i2++;
            }
            if (D || GameData.v().gameVariables.b("vending") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQSQ");
                i2++;
                D = true;
            }
            if (C || GameData.v().gameVariables.b("ark_lothasan") == 150) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQSA");
                i2++;
                C = true;
            }
            if (E || GameData.v().gameVariables.b("lost_kingdom") == 100) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQSg");
                i2++;
                E = true;
            }
            if (GameData.v().gameVariables.b("arena") >= 100) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQKg");
                i2++;
            }
            if (G || GameData.v().gameVariables.b("masters_laboratory") == 100) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQTA");
                i2++;
                G = true;
            }
            if (H || GameData.v().gameVariables.b("sewer_horrors") == 100) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQTg");
                int i3 = i2 + 1;
                H = true;
                if (GameData.v().E()) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQJg");
                    i2 += 2;
                } else {
                    i2 = i3;
                }
            }
            if (B || GameData.v().gameVariables.b("ark_lothasan") == 100 || GameData.v().gameVariables.b("ark_lothasan") == 150) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQRw");
                int i4 = i2 + 1;
                B = true;
                if (GameData.v().E()) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQLA");
                    i2 += 2;
                } else {
                    i2 = i4;
                }
                if (!GameData.v().F() && !GameData.v().D() && GameData.v().u() / 1080.0f < 180.0f) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQIg");
                    i2++;
                }
            }
            if (f3408p || GameData.v().gameVariables.b("dragonslayer") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQIA");
                i2++;
                f3408p = true;
            }
            if (f3411t || GameData.v().gameVariables.b("snake_gang") == 110) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQQQ");
                i2++;
                f3411t = true;
            }
            if (f3412u || GameData.v().gameVariables.b("um_unweakened") == 1 || (GameData.v().H("zuzsare") && GameData.v().H("undermother"))) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQQg");
                i2++;
                f3412u = true;
            }
            if (f3404l || GameData.v().gameVariables.b("play_result") == 3) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQOA");
                i2++;
                f3404l = true;
            }
            if (f3396d || GameData.v().party.companions.size() > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQFw");
                int i5 = i2 + 1;
                f3396d = true;
                if (f3394b || GameData.v().party.m("adaon")) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQCQ");
                    i5 = i2 + 2;
                    f3394b = true;
                }
                if (f3393a || GameData.v().party.m("grissenda")) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQCg");
                    i5++;
                    f3393a = true;
                }
                i2 = i5;
                if (f3395c || GameData.v().party.m("hirge")) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQNA");
                    i2++;
                    f3395c = true;
                }
            }
            if (s || GameData.v().gameVariables.b("mangled_god") == 110) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQKQ");
                i2++;
                s = true;
            }
            if (f3407o || GameData.v().gameVariables.b("shards_fate") > 90) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQPg");
                i2++;
                f3407o = true;
            }
            if (f3397e || GameData.v().gameVariables.b("lannegar_mine_cleared") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQHA");
                i2++;
                f3397e = true;
            }
            if (F || GameData.v().gameVariables.b("peaceful_final") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQSw");
                i2++;
                F = true;
            }
            if (f3403k || GameData.v().gameVariables.b("poisoned_river") == 100) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQOQ");
                i2++;
                f3403k = true;
            }
            if (f3400h || GameData.v().gameVariables.b("cursed_abbey") >= 90) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQPA");
                i2++;
                f3400h = true;
            }
            if (f3399g || GameData.v().gameVariables.b("web_terror") == 100) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQMw");
                i2++;
                f3399g = true;
            }
            if (f3410r || GameData.v().gameVariables.b("key_to_past") >= 50 || GameData.v().gameVariables.b("NG_house") > 0 || GameData.v().gameVariables.b("NI_house") > 0) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQKA");
                i2++;
                f3410r = true;
            }
            if (f3398f || GameData.v().gameVariables.b("fort_assault") == 100) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQIQ");
                i2++;
                f3398f = true;
            }
            if (f3401i || GameData.v().gameVariables.b("seed_of_trust") == 110) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQHQ");
                i2++;
                f3401i = true;
            }
            if (P || GameData.v().gameVariables.b("rumors") >= 3) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQLw");
                i2++;
                P = true;
            }
            if (f3409q) {
                i2++;
            } else {
                int i6 = GameData.v().gameVariables.b("ng_enraged_mino") == 1 ? 1 : 0;
                if (GameData.v().H("enraged_troll")) {
                    i6++;
                }
                if (GameData.v().H("enraged_rat")) {
                    i6++;
                }
                if (i6 >= 3) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQEw");
                    i2++;
                    f3409q = true;
                }
            }
            if (f3402j) {
                i2++;
            } else {
                int i7 = GameData.v().gameVariables.b("forgotten_lore") == 100 ? 1 : 0;
                if (GameData.v().gameVariables.b("goblin_hunt2") == 100) {
                    i7++;
                }
                if (GameData.v().gameVariables.b("prisoner_orcs") == 100) {
                    i7++;
                }
                if (GameData.v().gameVariables.b("find_constanze") == 100) {
                    i7++;
                }
                if (GameData.v().gameVariables.b("message_in_bottle") == 100) {
                    i7++;
                }
                if (i7 >= 4) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQHg");
                    i2++;
                    f3402j = true;
                }
            }
            if (f3405m) {
                i2++;
            } else {
                int i8 = GameData.v().gameVariables.b("stopping_prejudice") > 90 ? 1 : 0;
                if (GameData.v().gameVariables.b("apprentice_none") > 90) {
                    i8++;
                }
                if (GameData.v().gameVariables.b("important_research") > 90) {
                    i8++;
                }
                if (GameData.v().gameVariables.b("easy_job") > 90) {
                    i8++;
                }
                if (i8 >= 4) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQOw");
                    i2++;
                    f3405m = true;
                }
            }
            if (N) {
                i = i2 + 2;
            } else {
                i = GameData.v().gameVariables.b("know_arbenos") == 1 ? 1 : 0;
                if (GameData.v().gameVariables.b("know_nivaria") == 1) {
                    i++;
                }
                if (GameData.v().gameVariables.b("know_temple") == 1) {
                    i++;
                }
                if (GameData.v().gameVariables.b("know_thelume") == 1) {
                    i++;
                }
                if (GameData.v().gameVariables.b("know_tol") == 1) {
                    i++;
                }
                if (GameData.v().gameVariables.b("know_threeclerics") == 1) {
                    i++;
                }
                if (GameData.v().gameVariables.b("know_stormeevil") == 1) {
                    i++;
                }
                if (GameData.v().gameVariables.b("know_intervention") == 1) {
                    i++;
                }
                if (GameData.v().gameVariables.b("know_proph") == 1) {
                    i++;
                }
                if (i >= 4) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQGw");
                    ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQLg");
                    i2++;
                }
                if (i >= 9) {
                    ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQLg");
                    N = true;
                    i = i2 + 1;
                } else {
                    i = i2;
                }
            }
        }
        return i * 10;
    }

    private static int i() {
        int i2 = 0;
        if (ExiledKingdoms.f() != null && ((MainActivity) ExiledKingdoms.f()).l()) {
            int iH = GameData.v().h();
            ((MainActivity) ExiledKingdoms.f()).q(iH, "CgkIg8PLzvILEAIQGA");
            ((MainActivity) ExiledKingdoms.f()).q(iH, "CgkIg8PLzvILEAIQGQ");
            ((MainActivity) ExiledKingdoms.f()).q(iH, "CgkIg8PLzvILEAIQGg");
            if (iH >= 5) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQGA");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQGQ");
                i2 = 1;
            }
            if (iH >= 15) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQGQ");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQGg");
                i2++;
            }
            if (iH >= 40) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQGg");
                i2++;
            }
        }
        return i2 * 10;
    }

    private static int j() {
        int i2 = 0;
        if (ExiledKingdoms.f() != null && ((MainActivity) ExiledKingdoms.f()).l()) {
            int i3 = GameData.v().i();
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQDA");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQDQ");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQDg");
            ((MainActivity) ExiledKingdoms.f()).q(i3, "CgkIg8PLzvILEAIQDw");
            if (i3 >= 1) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQCw");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQDA");
                i2 = 1;
            }
            if (i3 >= 6) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQDA");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQDQ");
                i2++;
            }
            if (i3 >= 18) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQDQ");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQDg");
                i2++;
            }
            if (i3 >= 36) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQDg");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQDw");
                i2++;
            }
            if (i3 >= 72) {
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQDw");
                ((MainActivity) ExiledKingdoms.f()).p("CgkIg8PLzvILEAIQTQ");
                i2++;
            }
            if (i3 >= 85) {
                ((MainActivity) ExiledKingdoms.f()).q(85, "CgkIg8PLzvILEAIQTQ");
                ((MainActivity) ExiledKingdoms.f()).t("CgkIg8PLzvILEAIQTQ");
                i2++;
            }
        }
        return i2 * 10;
    }
}
