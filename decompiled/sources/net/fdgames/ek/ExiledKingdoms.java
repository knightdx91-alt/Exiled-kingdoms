package net.fdgames.ek;

import a0.p;
import android.app.ActivityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.e;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.l;
import d0.c;
import d0.f;
import d0.h;
import d0.i;
import d0.j;
import java.util.Locale;
import n0.p1;
import n0.z;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.android.MainActivity;
import r.d;
import s0.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ExiledKingdoms extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3371a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f3372b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f3373c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3374d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3375e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f3376f = "en";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f3377g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f3378h = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f3379i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f3380j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static boolean f3381k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static Controller f3384n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static MainActivity f3385o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static boolean f3387q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f3388r;
    public static boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static int f3389t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static int f3390u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static int f3391v;
    public h purchaseManagerConfig;
    public i purchaseObserver = new i() { // from class: net.fdgames.ek.ExiledKingdoms.1
        @Override // d0.i
        public final void a() {
            Gdx.app.log("handleInstall: ", "successfully..");
        }

        @Override // d0.i
        public final void b(c cVar) {
            cVar.getMessage().equals("There has been a Problem with your Internet connection. Please try again later");
        }

        @Override // d0.i
        public final void c(j jVar) {
            ExiledKingdoms exiledKingdoms = ExiledKingdoms.this;
            String strA = jVar.a();
            exiledKingdoms.getClass();
            ExiledKingdoms.d(strA);
            Settings.E(jVar.b());
            Settings.D(jVar.c());
            if (ExiledKingdoms.f3387q) {
                if (jVar.b() == null || !jVar.b().contains("GPA.")) {
                    Settings.N();
                    ExiledKingdoms.f3371a = false;
                }
            }
        }

        @Override // d0.i
        public final void d(j[] jVarArr) {
            for (int i2 = 0; i2 < jVarArr.length; i2++) {
                jVarArr[i2].getClass();
                ExiledKingdoms exiledKingdoms = ExiledKingdoms.this;
                String strA = jVarArr[i2].a();
                exiledKingdoms.getClass();
                if (ExiledKingdoms.d(strA)) {
                    Settings.E(jVarArr[i2].b());
                    Settings.D(jVarArr[i2].c());
                    return;
                }
            }
        }

        @Override // d0.i
        public final void e(c cVar) {
            System.out.println("  <<<<<<<<<<     handleRestoreError:" + cVar.getMessage());
        }

        @Override // d0.i
        public final void f(c cVar) {
            ExiledKingdoms.f3375e = true;
            System.out.println("PurchaseObserver: handleInstallError! " + cVar.getLocalizedMessage());
        }
    };

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static GameData.GameStatus f3382l = GameData.GameStatus.f3185b;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static boolean f3383m = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static a f3386p = null;

    /* JADX INFO: renamed from: net.fdgames.ek.ExiledKingdoms$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3392a;

        static {
            int[] iArr = new int[ControllerCommand.commandType.values().length];
            f3392a = iArr;
            try {
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3392a[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3392a[1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3392a[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public ExiledKingdoms(MainActivity mainActivity) {
        f3385o = mainActivity;
        f3372b = mainActivity.j();
        ActivityManager activityManager = (ActivityManager) mainActivity.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        f3373c = ((int) memoryInfo.totalMem) / 1048576;
        String str = "es";
        if (!Locale.getDefault().getLanguage().equals("es") && !Locale.getDefault().getLanguage().contains("es_")) {
            str = "pt";
            if (!Locale.getDefault().getLanguage().equals("pt") && !Locale.getDefault().getLanguage().contains("pt_")) {
                str = "de";
                if (!Locale.getDefault().getLanguage().equals("de") && !Locale.getDefault().getLanguage().contains("ru_")) {
                    str = "pl";
                    if (!Locale.getDefault().getLanguage().equals("pl") && !Locale.getDefault().getLanguage().contains("pl_")) {
                        str = "cz";
                        if (!Locale.getDefault().getLanguage().equals("cs") && !Locale.getDefault().getLanguage().contains("cs_") && !Locale.getDefault().getLanguage().equals("sk") && !Locale.getDefault().getLanguage().contains("sk_")) {
                            str = "it";
                            if (!Locale.getDefault().getLanguage().equals("it") && !Locale.getDefault().getLanguage().contains("it_")) {
                                str = "ru";
                                if (!Locale.getDefault().getLanguage().equals("ru") && !Locale.getDefault().getLanguage().contains("ru_") && !Locale.getDefault().getLanguage().equals("uk")) {
                                    str = "en";
                                }
                            }
                        }
                    }
                }
            }
        }
        f3376f = str;
        f3387q = true;
        f3388r = false;
        s = !true;
        f3378h = false;
        if (f3378h) {
            f3379i = Serializer.w().getProperty("fullscreen").contains("1");
        }
        h hVar = new h();
        this.purchaseManagerConfig = hVar;
        d0.e eVar = new d0.e();
        f fVar = f.f2156b;
        eVar.e(fVar);
        eVar.d("internal_test_2");
        hVar.a(eVar);
        h hVar2 = this.purchaseManagerConfig;
        d0.e eVar2 = new d0.e();
        f fVar2 = f.f2155a;
        eVar2.e(fVar2);
        eVar2.d("ek_donation_con_0");
        hVar2.a(eVar2);
        h hVar3 = this.purchaseManagerConfig;
        d0.e eVar3 = new d0.e();
        eVar3.e(fVar2);
        eVar3.d("ek_donation_con_1");
        hVar3.a(eVar3);
        h hVar4 = this.purchaseManagerConfig;
        d0.e eVar4 = new d0.e();
        eVar4.e(fVar2);
        eVar4.d("ek_donation_con_2");
        hVar4.a(eVar4);
        h hVar5 = this.purchaseManagerConfig;
        d0.e eVar5 = new d0.e();
        eVar5.e(fVar2);
        eVar5.d("ek_donation_con_3");
        hVar5.a(eVar5);
        h hVar6 = this.purchaseManagerConfig;
        d0.e eVar6 = new d0.e();
        eVar6.e(fVar);
        eVar6.d("full_ek_license");
        hVar6.a(eVar6);
    }

    protected static boolean d(String str) {
        boolean z2;
        if ("full_ek_license".equals(str)) {
            Settings.u();
            z2 = true;
        } else {
            z2 = false;
        }
        if ("ek_donation_con_1".equals(str)) {
            Settings.s("don1");
            f3380j = true;
        }
        if ("ek_donation_con_2".equals(str)) {
            Settings.s("don2");
            f3380j = true;
        }
        if ("ek_donation_con_3".equals(str)) {
            Settings.s("don3");
            f3380j = true;
        }
        return z2;
    }

    public static float e(ControllerCommand controllerCommand) {
        if (controllerCommand.type == ControllerCommand.commandType.f3368d && Gdx.input.isKeyPressed(controllerCommand.id)) {
            return 1.0f;
        }
        Controller controller = f3384n;
        if (controller == null) {
            return 0.0f;
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3367c && controller.b(controllerCommand.id)) {
            return 1.0f;
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3365a && f3384n.c(controllerCommand.id) < -0.2f) {
            return f3384n.c(controllerCommand.id) * (-1.0f);
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3366b && f3384n.c(controllerCommand.id) > 0.2f) {
            return f3384n.c(controllerCommand.id);
        }
        return 0.0f;
    }

    public static IPlatformResolver f() {
        return f3385o;
    }

    public static boolean g() {
        return Controllers.a().f1750b > 0 && f3384n != null;
    }

    public static void i() {
        f3383m = Serializer.E();
        if (f3388r) {
            return;
        }
        f3385o.getClass();
    }

    public static boolean j(ControllerCommand controllerCommand) {
        if (z.z().booleanValue()) {
            return false;
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3368d && Gdx.input.isKeyJustPressed(controllerCommand.id)) {
            return true;
        }
        if (f3384n == null) {
            return false;
        }
        try {
            int iOrdinal = controllerCommand.type.ordinal();
            return iOrdinal != 0 ? iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal == 4 && controllerCommand.type == ControllerCommand.commandType.f3369e && f3384n.a(controllerCommand.povIndex) == PovDirection.f1616b && controllerCommand.id == 1 : f3384n.b(controllerCommand.id) : f3384n.c(controllerCommand.id) > 0.2f : f3384n.c(controllerCommand.id) < -0.2f;
        } catch (Exception e2) {
            l.d("ExiledKingdoms.initialize() EXCEPTION:  " + e2.getMessage());
        }
        return false;
    }

    @Override // com.badlogic.gdx.e, com.badlogic.gdx.a
    public final void dispose() {
        GameAssets.f();
        Assets.f3309a.dispose();
        k0.a.h();
        z.n();
        p1.a();
    }

    public final void h() {
        l.d("ExiledKingdoms.initialize()");
        a aVar = f3386p;
        if (aVar != null) {
            i iVar = this.purchaseObserver;
            h hVar = this.purchaseManagerConfig;
            System.out.println("installManager installing manager       ***********************************************************************************************************************************");
            ((e0.f) aVar.mgr).s(iVar, hVar);
        }
        l.d("ExiledKingdoms.initialize() - Loading settings....");
        try {
            Settings.t();
        } catch (Exception e2) {
            l.d("ExiledKingdoms.initialize() EXCEPTION -:  " + e2.getMessage());
        }
        if (f3388r) {
            f3377g = false;
            f3385o.getClass();
            p pVar = new p();
            pVar.getX();
            Gdx.graphics.getHeight();
            f3389t = (int) ((720.0f / Gdx.graphics.getHeight()) * pVar.getY());
            f3390u = (int) ((1280.0f / Gdx.graphics.getWidth()) * pVar.getWidth());
            f3391v = (int) ((1280.0f / Gdx.graphics.getWidth()) * pVar.getHeight());
        } else {
            f3377g = Settings.d();
        }
        l.d("ExiledKingdoms.initialize() - Loading game data....");
        try {
            l.d("ExiledKingdoms.initialize() -   >Task 1/3: loading strings....");
            GameString.c();
            l.d("ExiledKingdoms.initialize() -   >Task 2/3: loading rules....");
            Rules.a();
            l.d("ExiledKingdoms.initialize() -   >Task 3/3: loading gameworld....");
            GameWorld.a();
        } catch (Exception e3) {
            l.d("ExiledKingdoms.initialize() EXCEPTION -:  " + e3.getMessage());
            System.out.println("Error in ExiledKingdoms.initialize: " + e3.getLocalizedMessage());
        }
        l.d("ExiledKingdoms.initialize() - Loading assets....");
        try {
            Assets.f3309a.r(new d());
            Assets.v();
            GameAssets.d();
        } catch (Exception e4) {
            l.d("ExiledKingdoms.initialize() EXCEPTION -:  " + e4.getMessage());
        }
        try {
            if (Controllers.a().f1750b > 0) {
                f3384n = Controllers.a().g();
            }
            if (Controllers.a().f1750b > 1) {
                a.b<Controller> it = Controllers.a().iterator();
                while (it.hasNext()) {
                    Controller next = it.next();
                    if (next != null && next.getName().toLowerCase().contains("xbox")) {
                        f3384n = next;
                    }
                }
            }
        } catch (Exception e5) {
            l.d("ExiledKingdoms.initialize() EXCEPTION - Controllers:  " + e5.getMessage());
        }
        com.badlogic.gdx.files.a aVar2 = Serializer.f3225b;
        if (Gdx.files.local("monsterdump.txt").exists()) {
            Serializer.f3226c = true;
            Serializer.f3225b = Gdx.files.local("monsterdump.txt");
            Serializer.v("Initializing game, version: 1.3.1217");
        }
        l.d("ExiledKingdoms.initialize() - all tasks launched, returning to LoadScreen...");
    }

    @Override // com.badlogic.gdx.e, com.badlogic.gdx.a
    public final void pause() {
    }

    @Override // com.badlogic.gdx.e, com.badlogic.gdx.a
    public final void resume() {
        super.resume();
        Assets.k();
    }
}
