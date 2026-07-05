package net.fdgames.ek;

import android.app.ActivityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.e;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.l;
import com.google.android.datatransport.backend.cct.CSLH.VEZT;
import com.google.android.datatransport.backend.cct.CSLH.qJDUJ;
import com.google.android.gms.games.snapshot.hbR.cxRMW;
import d0.d;
import e1.a;
import java.util.Locale;
import m0.p;
import n0.WOA.jzidqMPaLNVH;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.android.MainActivity;
import p0.c;
import p0.f;
import p0.g;
import p0.h;
import p0.i;
import p0.j;
import z0.p1;
import z0.z;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class ExiledKingdoms extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3599a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f3600b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f3601c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3602d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3603e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f3604f = "en";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f3605g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f3606h = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f3607i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f3608j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static boolean f3609k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static Controller f3612n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static MainActivity f3613o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static boolean f3615q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f3616r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static boolean f3617s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static int f3618t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static int f3619u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static int f3620v;
    public h purchaseManagerConfig;
    public i purchaseObserver = new i() { // from class: net.fdgames.ek.ExiledKingdoms.1
        @Override // p0.i
        public final void a() {
            Gdx.app.log("handleInstall: ", "successfully..");
        }

        @Override // p0.i
        public final void b(j jVar) {
            ExiledKingdoms exiledKingdoms = ExiledKingdoms.this;
            String strA = jVar.a();
            exiledKingdoms.getClass();
            ExiledKingdoms.d(strA);
            Settings.G(jVar.b());
            Settings.F(jVar.c());
            if (ExiledKingdoms.f3615q) {
                if (jVar.b() == null || !jVar.b().contains("GPA.")) {
                    Settings.P();
                    ExiledKingdoms.f3599a = false;
                }
            }
        }

        @Override // p0.i
        public final void c(c cVar) {
            cVar.getMessage().equals("There has been a Problem with your Internet connection. Please try again later");
        }

        @Override // p0.i
        public final void d(c cVar) {
            System.out.println("  <<<<<<<<<<     handleRestoreError:" + cVar.getMessage());
        }

        @Override // p0.i
        public final void e(c cVar) {
            ExiledKingdoms.f3603e = true;
            System.out.println("PurchaseObserver: handleInstallError! " + cVar.getLocalizedMessage());
        }

        @Override // p0.i
        public final void f(j[] jVarArr) {
            for (int i2 = 0; i2 < jVarArr.length; i2++) {
                jVarArr[i2].getClass();
                ExiledKingdoms exiledKingdoms = ExiledKingdoms.this;
                String strA = jVarArr[i2].a();
                exiledKingdoms.getClass();
                if (ExiledKingdoms.d(strA)) {
                    Settings.G(jVarArr[i2].b());
                    Settings.F(jVarArr[i2].c());
                    return;
                }
            }
        }
    };

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static GameData.GameStatus f3610l = GameData.GameStatus.f3406c;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static boolean f3611m = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static a f3614p = null;

    /* JADX INFO: renamed from: net.fdgames.ek.ExiledKingdoms$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3621a;

        static {
            int[] iArr = new int[ControllerCommand.commandType.values().length];
            f3621a = iArr;
            try {
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3621a[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3621a[1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3621a[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public ExiledKingdoms(MainActivity mainActivity) {
        f3613o = mainActivity;
        f3600b = mainActivity.j();
        ActivityManager activityManager = (ActivityManager) mainActivity.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        f3601c = ((int) memoryInfo.totalMem) / 1048576;
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
                                    str = VEZT.ywK;
                                }
                            }
                        }
                    }
                }
            }
        }
        f3604f = str;
        f3615q = true;
        f3616r = false;
        f3617s = !true;
        f3606h = false;
        if (f3606h) {
            f3607i = Serializer.w().getProperty("fullscreen").contains("1");
        }
        h hVar = new h();
        this.purchaseManagerConfig = hVar;
        p0.e eVar = new p0.e();
        f fVar = f.f3694c;
        eVar.e(fVar);
        eVar.d("internal_test_2");
        hVar.a(eVar);
        h hVar2 = this.purchaseManagerConfig;
        p0.e eVar2 = new p0.e();
        f fVar2 = f.f3693b;
        eVar2.e(fVar2);
        eVar2.d("ek_donation_con_0");
        hVar2.a(eVar2);
        h hVar3 = this.purchaseManagerConfig;
        p0.e eVar3 = new p0.e();
        eVar3.e(fVar2);
        eVar3.d("ek_donation_con_1");
        hVar3.a(eVar3);
        h hVar4 = this.purchaseManagerConfig;
        p0.e eVar4 = new p0.e();
        eVar4.e(fVar2);
        eVar4.d("ek_donation_con_2");
        hVar4.a(eVar4);
        h hVar5 = this.purchaseManagerConfig;
        p0.e eVar5 = new p0.e();
        eVar5.e(fVar2);
        eVar5.d("ek_donation_con_3");
        hVar5.a(eVar5);
        h hVar6 = this.purchaseManagerConfig;
        p0.e eVar6 = new p0.e();
        eVar6.e(fVar);
        eVar6.d("full_ek_license");
        hVar6.a(eVar6);
    }

    protected static boolean d(String str) {
        boolean z2;
        if ("full_ek_license".equals(str)) {
            Settings.w();
            z2 = true;
        } else {
            z2 = false;
        }
        if ("ek_donation_con_1".equals(str)) {
            Settings.s(cxRMW.jnc);
            f3608j = true;
        }
        if ("ek_donation_con_2".equals(str)) {
            Settings.s("don2");
            f3608j = true;
        }
        if (qJDUJ.dAie.equals(str)) {
            Settings.s("don3");
            f3608j = true;
        }
        return z2;
    }

    public static float e(ControllerCommand controllerCommand) {
        if (controllerCommand.type == ControllerCommand.commandType.f3596e && Gdx.input.isKeyPressed(controllerCommand.id)) {
            return 1.0f;
        }
        Controller controller = f3612n;
        if (controller == null) {
            return 0.0f;
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3595d && controller.b(controllerCommand.id)) {
            return 1.0f;
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3593b && f3612n.c(controllerCommand.id) < -0.2f) {
            return f3612n.c(controllerCommand.id) * (-1.0f);
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3594c && f3612n.c(controllerCommand.id) > 0.2f) {
            return f3612n.c(controllerCommand.id);
        }
        return 0.0f;
    }

    public static IPlatformResolver f() {
        return f3613o;
    }

    public static boolean g() {
        return Controllers.a().f2517c > 0 && f3612n != null;
    }

    public static void i() {
        f3611m = Serializer.E();
        if (f3616r) {
            return;
        }
        f3613o.getClass();
    }

    public static boolean j(ControllerCommand controllerCommand) {
        if (z.A().booleanValue()) {
            return false;
        }
        if (controllerCommand.type == ControllerCommand.commandType.f3596e && Gdx.input.isKeyJustPressed(controllerCommand.id)) {
            return true;
        }
        if (f3612n == null) {
            return false;
        }
        try {
            int iOrdinal = controllerCommand.type.ordinal();
            return iOrdinal != 0 ? iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal == 4 && controllerCommand.type == ControllerCommand.commandType.f3597f && f3612n.a(controllerCommand.povIndex) == PovDirection.f2383c && controllerCommand.id == 1 : f3612n.b(controllerCommand.id) : f3612n.c(controllerCommand.id) > 0.2f : f3612n.c(controllerCommand.id) < -0.2f;
        } catch (Exception e2) {
            l.e("ExiledKingdoms.initialize() EXCEPTION:  " + e2.getMessage());
        }
        return false;
    }

    @Override // com.badlogic.gdx.e, com.badlogic.gdx.a
    public final void dispose() {
        GameAssets.f();
        Assets.f3530a.dispose();
        w0.a.h();
        z.o();
        p1.a();
    }

    public final void h() {
        l.e("ExiledKingdoms.initialize()");
        a aVar = f3614p;
        if (aVar != null) {
            i iVar = this.purchaseObserver;
            h hVar = this.purchaseManagerConfig;
            System.out.println("installManager installing manager       ***********************************************************************************************************************************");
            ((q0.f) aVar.mgr).s(iVar, hVar);
        }
        l.e("ExiledKingdoms.initialize() - Loading settings....");
        try {
            Settings.u();
        } catch (Exception e2) {
            l.e("ExiledKingdoms.initialize() EXCEPTION -:  " + e2.getMessage());
        }
        if (f3616r) {
            f3605g = false;
            f3613o.getClass();
            p pVar = new p();
            pVar.getX();
            Gdx.graphics.getHeight();
            f3618t = (int) ((720.0f / Gdx.graphics.getHeight()) * pVar.getY());
            f3619u = (int) ((1280.0f / Gdx.graphics.getWidth()) * pVar.getWidth());
            f3620v = (int) ((1280.0f / Gdx.graphics.getWidth()) * pVar.getHeight());
        } else {
            Settings.d();
            f3605g = false;
        }
        l.e("ExiledKingdoms.initialize() - Loading game data....");
        try {
            l.e("ExiledKingdoms.initialize() -   >Task 1/3: loading strings....");
            GameString.c();
            l.e("ExiledKingdoms.initialize() -   >Task 2/3: loading rules....");
            Rules.a();
            l.e("ExiledKingdoms.initialize() -   >Task 3/3: loading gameworld....");
            GameWorld.a();
        } catch (Exception e3) {
            l.e("ExiledKingdoms.initialize() EXCEPTION -:  " + e3.getMessage());
            System.out.println("Error in ExiledKingdoms.initialize: " + e3.getLocalizedMessage());
        }
        l.e("ExiledKingdoms.initialize() - Loading assets....");
        try {
            Assets.f3530a.r(new d());
            Assets.v();
            GameAssets.d();
        } catch (Exception e4) {
            l.e("ExiledKingdoms.initialize() EXCEPTION -:  " + e4.getMessage());
        }
        try {
            if (Controllers.a().f2517c > 0) {
                f3612n = Controllers.a().g();
            }
            if (Controllers.a().f2517c > 1) {
                a.b<Controller> it = Controllers.a().iterator();
                while (it.hasNext()) {
                    Controller next = it.next();
                    if (next != null && next.getName().toLowerCase().contains("xbox")) {
                        f3612n = next;
                    }
                }
            }
        } catch (Exception e5) {
            l.e(jzidqMPaLNVH.eXtLYRnMMY + e5.getMessage());
        }
        com.badlogic.gdx.files.a aVar2 = Serializer.f3446b;
        if (Gdx.files.local("monsterdump.txt").exists()) {
            Serializer.f3447c = true;
            Serializer.f3446b = Gdx.files.local("monsterdump.txt");
            Serializer.v("Initializing game, version: 1.3.1218");
        }
        l.e("ExiledKingdoms.initialize() - all tasks launched, returning to LoadScreen...");
    }

    @Override // com.badlogic.gdx.e, com.badlogic.gdx.a
    public final void pause() {
    }

    @Override // com.badlogic.gdx.e, com.badlogic.gdx.a
    public final void resume() {
        g gVar;
        super.resume();
        if (!f3599a && f3615q && (gVar = f3614p.mgr) != null && ((q0.f) gVar).t()) {
            f3614p.c();
        }
        Assets.k();
    }
}
