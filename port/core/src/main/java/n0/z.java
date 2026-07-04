package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;
import k0.a;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.Loot;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.PlantSpawn;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: GameHUD.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class z {

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public static final Color f2922f0 = new Color(0.9411765f, 0.7058824f, 0.3529412f, 0.66f);

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static Color f2923g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static o f2924h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static Color f2925i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static boolean f2926j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public static float f2927k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public static float f2928l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public static float f2929m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public static float f2930n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public static float f2931o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public static float f2932p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public static int f2933q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    static final int f2934r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public static z f2935s0;
    public static float t0;
    public static float u0;
    public static float v0;
    public static boolean w0;
    public v A;
    public l1[] B;
    public o0.i[] C;
    public e1 D;
    public ImageButton E;
    public Table F;
    public Table G;
    public Table H;
    public v I;
    public v J;
    public TextButton K;
    public f1 L;
    public Label M;
    public Image N;
    public com.badlogic.gdx.math.Vector3 O;
    public u P;
    public j1 Q;
    public i1 R;
    public c1 S;
    public p T;
    public g U;
    public o0.f V;
    public o0.a0 W;
    public o0.t X;
    public o0.r Y;
    public a.f Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2936a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public n1 f2937a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Stage f2938b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public o0.s f2939b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Touchpad f2940c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public m1 f2941c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageButton f2942d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public float f2943d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public v f2944e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f2945e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v f2946f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Label f2947g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b0 f2948h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public q1 f2949i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2950j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2951k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f2952l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f2953m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f2954n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f2955o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f2956p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f2957q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f2958r;
    Boolean[] s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public n0.a f2959t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public n0.a f2960u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public n0.a f2961v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public n0.b f2962w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public n0.b f2963x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public n0.a f2964y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public v[] f2965z;

    /* JADX INFO: compiled from: GameHUD.java */
    final class a extends e {
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            GameLevel.n(false);
        }
    }

    /* JADX INFO: compiled from: GameHUD.java */
    final class b extends j {
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            if (((Boolean) obj).booleanValue()) {
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                GameData.v().V(true);
                Assets.f3309a.t(m0.b.P().f2415a);
                System.gc();
                k0.a.l().i();
                l0.b.n();
                GameData.v().player.areasVisited.b();
                GameData.v().player.U1(true);
            }
            GameLevel.n(false);
        }
    }

    /* JADX INFO: compiled from: GameHUD.java */
    final class c extends j {
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            if (((Boolean) obj).booleanValue()) {
                GameData.v().player.P1();
            }
            GameLevel.n(false);
        }
    }

    static {
        new Color(0.9411765f, 0.7058824f, 0.3529412f, 0.3f);
        f2923g0 = new Color(0.27450982f, 0.47058824f, 0.8627451f, 0.75f);
        f2925i0 = Color.CYAN;
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        f2927k0 = fMin;
        f2928l0 = 0.33f;
        f2929m0 = 0.2f;
        f2930n0 = 0.44f;
        f2931o0 = 0.14f;
        f2932p0 = 0.035f;
        f2933q0 = -1;
        f2934r0 = (int) (fMin * 15.0f);
        f2935s0 = null;
        t0 = 1.0f;
        u0 = 1.0f;
        v0 = 1.0f;
    }

    public static boolean B() {
        return f2935s0 != null;
    }

    public static void D(float f2, float f3) {
        if (GameLevel.l() || GameData.v().player.d0() == MapActor.ActorState.PUSHED || GameData.v().player.d0() == MapActor.ActorState.SKILL_CHARGE) {
            return;
        }
        float fAbs = Math.abs(f3) + Math.abs(f2);
        if (fAbs > 1.0f) {
            f2 /= fAbs;
            f3 /= fAbs;
        }
        GameData.v().player.N(f2 - f3);
        GameData.v().player.O(f3 + f2);
    }

    public static void N(String str, String str2) {
        GameLevel.n(true);
        new a(str, str2).show(f2935s0.f2938b);
    }

    public static void V(float f2) {
        u0 = f2;
        Table table = f2935s0.H;
        float x2 = table.getX();
        int i2 = f2934r0;
        table.setPosition(x2, i2 * f2);
        Touchpad touchpad = f2935s0.f2940c;
        touchpad.setPosition(touchpad.getX(), i2 * f2);
        Table table2 = f2935s0.F;
        float f3 = f2 - 1.0f;
        table2.setPosition(table2.getX(), (i2 * f3) + (Gdx.graphics.getHeight() * 0.25f * t0));
        Table table3 = f2935s0.G;
        table3.setPosition(table3.getX(), (i2 * f3) + (f2927k0 * 15.0f));
    }

    public static void X(float f2) {
        int i2;
        int i3;
        t0 = f2;
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        float f3 = (int) (f2928l0 * height * t0);
        f2935s0.f2940c.setHeight(f3);
        f2935s0.f2940c.setWidth(f3);
        Touchpad touchpad = f2935s0.f2940c;
        int i4 = f2934r0;
        touchpad.setPosition((i4 * v0) + ExiledKingdoms.f3390u, (i4 * u0) + ExiledKingdoms.f3389t);
        f2935s0.H.clear();
        int i5 = (int) (f2929m0 * height * t0);
        z zVar = f2935s0;
        float f4 = i5;
        zVar.H.add(zVar.f2942d).width(f4).height(f4).fill();
        Table table = f2935s0.H;
        table.setPosition(((width - table.getWidth()) - (i4 * 2)) - (((v0 - 1.0f) * i4) + ExiledKingdoms.f3391v), (i4 * u0) + ExiledKingdoms.f3389t);
        f2935s0.F.clear();
        if (ExiledKingdoms.f3378h) {
            f2935s0.F.setPosition(width - ((i4 + 60) * 2), i4);
            i2 = 60;
            i3 = 5;
        } else {
            float f5 = f2931o0 * height;
            float f6 = t0;
            i2 = (int) (f5 * f6);
            i3 = (int) (f2932p0 * height * f6);
            f2935s0.F.setPosition((width - ((i4 + i2) * 2)) - (((v0 - 1.0f) * i4) + ExiledKingdoms.f3391v), a.a.C(u0, 1.0f, i4, height * 0.25f * f6) + ExiledKingdoms.f3389t);
        }
        for (int i6 = 3; i6 >= 0; i6--) {
            f2935s0.F.row().space(0.0f);
            z zVar2 = f2935s0;
            float f7 = i2;
            zVar2.F.add(zVar2.B[i6 + 4]).width(f7).height(f7).padRight(0.0f);
            z zVar3 = f2935s0;
            zVar3.F.add(zVar3.B[i6]).width(f7).height(f7).padRight(i3);
        }
    }

    public static Float l() {
        return Float.valueOf(f2924h0.getWidth());
    }

    public static int m() {
        if (f2924h0.isVisible()) {
            return f2924h0.f2827b;
        }
        return 0;
    }

    public static void n() {
        o0.m.f();
        o0.l.d();
        g1.d();
        d.e();
        f.e();
        o0.n.a();
        p0.c.a();
        o0.x.a();
        c0.a();
        d0.a();
        r1.a();
        p1.a();
        if (f2935s0 != null) {
            f2935s0 = null;
        }
    }

    public static o u() {
        return f2924h0;
    }

    public static z v() {
        if (f2935s0 == null) {
            z zVar = new z();
            zVar.f2936a = 0;
            Stage stage = new Stage();
            zVar.f2938b = stage;
            zVar.f2950j = 0;
            zVar.f2952l = 0.045f;
            zVar.f2953m = 0.18f;
            zVar.f2954n = 0.07f;
            zVar.f2955o = 0.17f;
            zVar.f2956p = 260.0f;
            zVar.f2957q = 0.03f;
            zVar.f2958r = 0.2f;
            zVar.s = new Boolean[4];
            zVar.f2965z = new v[4];
            zVar.B = new l1[8];
            zVar.C = new o0.i[5];
            zVar.N = new Image();
            if (ExiledKingdoms.f3378h) {
                f2927k0 = 1.0f;
                zVar.f2952l = 0.04f;
                zVar.f2953m = 0.135f;
                f2930n0 = 0.44f;
                zVar.f2954n = 0.04f;
                zVar.f2955o = 0.12f;
                f2931o0 = 0.09f;
                f2932p0 = 0.025f;
                zVar.f2956p = 210.0f;
                zVar.f2957q = 0.02f;
                zVar.f2958r = 0.2f;
            }
            q1 q1Var = new q1();
            zVar.f2949i = q1Var;
            zVar.V = new o0.f();
            zVar.W = new o0.a0();
            zVar.X = new o0.t();
            zVar.Z = new a.f(stage);
            zVar.U = new g(stage);
            zVar.O = new com.badlogic.gdx.math.Vector3(0.0f, 0.0f, 0.0f);
            zVar.Q = new j1(null);
            c1 c1Var = new c1();
            zVar.S = c1Var;
            zVar.R = new i1(null, c1Var);
            float height = Gdx.graphics.getHeight();
            float width = Gdx.graphics.getWidth();
            Assets.k();
            stage.addActor(q1Var);
            int i2 = (int) (f2928l0 * height);
            Touchpad touchpad = new Touchpad(10.0f, (Touchpad.TouchpadStyle) Assets.g().get("touchpad", Touchpad.TouchpadStyle.class));
            zVar.f2940c = touchpad;
            int i3 = f2934r0;
            float f2 = i3;
            float f3 = i2 + i3;
            touchpad.setBounds(f2, f2, f3, f3);
            Color color = f2922f0;
            touchpad.setColor(color);
            stage.addActor(touchpad);
            Table table = new Table();
            stage.addActor(table);
            int i4 = (int) (zVar.f2956p * f2927k0);
            int i5 = (int) (zVar.f2957q * height);
            float f4 = zVar.f2953m;
            int i6 = (int) (height * f4);
            if (ExiledKingdoms.f3378h) {
                i6 = (int) (f4 * 720.0f);
            }
            table.setPosition(ExiledKingdoms.f3390u + i3, height - (i3 + i6));
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            e1 e1Var = new e1(GameAssets.c(GameData.v().player.E()), color);
            zVar.D = e1Var;
            e1Var.setStyle(GameAssets.c(GameData.v().player.E()));
            e1Var.getImage().setSize(e1Var.getWidth() * 0.9f, e1Var.getHeight() * 0.9f);
            e1Var.getImage().setPosition(e1Var.getWidth() * 0.05f, e1Var.getHeight() * 0.05f);
            float f5 = i6;
            table.add(e1Var).width(f5).height(f5);
            Table table2 = new Table();
            table2.top().left();
            table2.setHeight(table.getHeight());
            n0.a aVar = new n0.a(0, 0.8f);
            zVar.f2959t = aVar;
            float f6 = i4;
            float f7 = i5;
            table2.add(aVar).width(f6).height(f7).fill().top().padTop(f2);
            table2.row();
            n0.a aVar2 = new n0.a(2, 0.7f);
            zVar.f2960u = aVar2;
            table2.add(aVar2).width(f6 * 0.8f).height(f7 * 0.8f).fill().top().left().padTop(i3 / 5);
            table.add(table2).top();
            table.pack();
            n0.a aVar3 = new n0.a(1, 0.0f);
            zVar.f2961v = aVar3;
            table.row();
            float f8 = f7 * 0.4f;
            table.add(aVar3).width(0.95f * f5).height(f8);
            table.pack();
            Table table3 = new Table();
            stage.addActor(table3);
            float f9 = zVar.f2955o;
            int i7 = (int) (height * f9 * 0.8f);
            if (ExiledKingdoms.f3378h) {
                i7 = (int) (f9 * 720.0f * 0.8f);
            }
            table3.setPosition(ExiledKingdoms.f3390u + i3, height - ((i3 * 3) + (i6 + i7)));
            ImageButton imageButton = new ImageButton(GameAssets.a(GameAssets.f3358z));
            zVar.E = imageButton;
            imageButton.setStyle(GameAssets.c(GameData.v().player.E()));
            imageButton.setColor(color);
            imageButton.getImage().setSize(imageButton.getWidth() * 0.9f, imageButton.getHeight() * 0.9f);
            imageButton.getImage().setPosition(imageButton.getWidth() * 0.05f, 0.05f * imageButton.getHeight());
            float f10 = i7;
            table3.add(imageButton).width(f10).height(f10).bottom();
            n0.b bVar = new n0.b(0);
            zVar.f2962w = bVar;
            float f11 = i5 / 2;
            table3.add(bVar).width(f11).height(f10).fill().top().padTop(f2);
            n0.b bVar2 = new n0.b(2);
            zVar.f2963x = bVar2;
            table3.add(bVar2).width(f11).height(f10).fill().top().padTop(f2);
            table3.pack();
            table3.row();
            n0.a aVar4 = new n0.a(1, 0.0f);
            zVar.f2964y = aVar4;
            table3.add(aVar4).width(f10).height(f8);
            table3.pack();
            Table table4 = new Table();
            stage.addActor(table4);
            table4.setPosition((i3 * 4) + i6 + ExiledKingdoms.f3390u, height - (i6 - i3));
            u uVar = new u("", GameAssets.f3316a0);
            uVar.f2893a = GameData.v().u();
            zVar.P = uVar;
            uVar.setFontScale(f2927k0);
            uVar.setAlignment(8);
            table4.row().width(stage.getWidth() * 0.2f).left();
            table4.add(uVar).minHeight(40.0f).left();
            table4.pack();
            Table table5 = new Table();
            stage.addActor(table5);
            v vVar = new v(GameAssets.a(Assets.f("locked")), color, false);
            zVar.A = vVar;
            vVar.setVisible(false);
            vVar.setColor(color);
            vVar.addListener(new a0(0));
            float f12 = (int) (0.08f * height);
            table5.add(vVar).width(f12).height(f12);
            float f13 = f2 * 3.0f;
            table5.setPosition(f5 + f13, height - (i3 * 7));
            b0 b0Var = new b0();
            Label label = new Label("", GameAssets.f3318b0);
            b0Var.f2538a = label;
            if (ExiledKingdoms.f3378h) {
                b0.f2536b = 1.0f;
            }
            b0Var.left();
            label.setFontScale(b0.f2536b);
            b0Var.add(label);
            zVar.f2948h = b0Var;
            stage.addActor(b0Var);
            b0Var.setPosition(width * 0.33f, height - (3.2f * f2));
            b0Var.align(8);
            b0Var.pack();
            if (!ExiledKingdoms.f3378h) {
                b0Var.addListener(new a0(1));
            }
            b0Var.addListener(new a0(2));
            Table table6 = new Table();
            stage.addActor(table6);
            float f14 = zVar.f2955o;
            int i8 = (int) (height * f14);
            if (ExiledKingdoms.f3378h) {
                i8 = (int) (f14 * 720.0f);
            }
            float f15 = i8;
            table6.setPosition(width - (((1.5f * f2) + f15) * 4.0f), height - ((i3 / 2) + i8));
            for (int i9 = 3; i9 >= 0; i9--) {
                zVar.f2965z[i9] = new v(GameAssets.a(Assets.a("loot")), f2923g0, true);
                table6.add(zVar.f2965z[i9]).width(f15).height(f15).padRight(f2);
                zVar.f2965z[i9].setVisible(false);
            }
            v vVar2 = zVar.f2965z[0];
            vVar2.getClass();
            vVar2.addListener(new q(1, vVar2));
            table6.pack();
            Table table7 = new Table();
            zVar.f2938b.addActor(table7);
            float f16 = width * 0.1f;
            table7.padLeft(f16).padRight(f16);
            table7.setPosition(0.0f, (zVar.f2938b.getHeight() * 2.0f) / 3.0f);
            table7.setWidth(zVar.f2938b.getWidth() * 0.8f);
            zVar.f2947g = new Label("", GameAssets.f3320c0);
            if (Settings.M()) {
                zVar.f2947g = new Label("", GameAssets.Y);
            }
            zVar.f2947g.setWrap(true);
            zVar.f2947g.setFontScale(f2927k0);
            if (ExiledKingdoms.f3378h) {
                zVar.f2947g.setFontScale(0.99f);
            }
            zVar.f2947g.setAlignment(1);
            table7.row().width(zVar.f2938b.getWidth() * 0.8f);
            table7.add(zVar.f2947g).minHeight(60.0f);
            table7.pack();
            Table table8 = new Table();
            zVar.f2938b.addActor(table8);
            table8.left();
            table8.padLeft(40.0f).padRight(40.0f);
            table8.setPosition((zVar.f2938b.getWidth() / 4.0f) + ExiledKingdoms.f3390u, f2);
            Label label2 = new Label("", GameAssets.T);
            zVar.M = label2;
            label2.setWrap(true);
            label2.setFontScale(f2927k0);
            label2.setAlignment(9);
            table8.row().width(zVar.f2938b.getWidth() / 2.0f);
            table8.add(label2).expandX().minHeight(50.0f).fill();
            Table table9 = new Table();
            zVar.F = table9;
            zVar.f2938b.addActor(table9);
            int i10 = (int) (f2931o0 * height);
            if (ExiledKingdoms.f3378h) {
                i10 = 60;
            }
            float f17 = width - ((i3 + i10) * 2);
            table9.setPosition(f17, f2927k0 * 30.0f);
            if (ExiledKingdoms.f3378h) {
                table9.setPosition(f17, f2927k0 * 30.0f);
            }
            for (int i11 = 3; i11 >= 0; i11--) {
                zVar.B[i11] = new l1(i11);
                int i12 = i11 + 4;
                zVar.B[i12] = new l1(i12);
                zVar.F.row().space(0.0f);
                float f18 = i10;
                zVar.F.add(zVar.B[i12]).width(f18).height(f18).padRight(0.0f);
                zVar.F.add(zVar.B[i11]).width(f18).height(f18).padRight(f2);
                zVar.B[i12].setVisible(false);
                zVar.B[i11].setVisible(false);
            }
            zVar.F.pack();
            zVar.Y();
            Table table10 = new Table();
            zVar.H = table10;
            zVar.f2938b.addActor(table10);
            int i13 = (int) (f2927k0 * 130.0f);
            table10.setPosition(((int) width) - i13, 0.0f);
            ImageButton imageButton2 = new ImageButton(GameAssets.b());
            zVar.f2942d = imageButton2;
            imageButton2.setColor(color);
            float f19 = i13;
            table10.add(imageButton2).width(f19).height(f19).fill();
            table10.row();
            table10.add(new Image(GameAssets.f3358z)).height(30.0f);
            table10.pack();
            imageButton2.addListener(new a0(3));
            Table table11 = new Table();
            zVar.G = table11;
            zVar.f2938b.addActor(table11);
            float f20 = f2927k0;
            int i14 = (int) (70.0f * f20);
            int i15 = (int) (25.0f * f20);
            if (ExiledKingdoms.f3378h) {
                i14 = 48;
                i15 = 20;
            }
            table11.setPosition(width * 0.48f, f20 * 15.0f);
            table11.row().spaceRight(i15);
            int i16 = 0;
            while (i16 < 5) {
                zVar.C[i16] = new o0.i();
                o0.i iVar = zVar.C[i16];
                iVar.f3539i = i16;
                float f21 = i14;
                zVar.G.add(iVar).width(f21).height(f21);
                zVar.C[i16].setVisible(false);
                zVar.C[i16].f3540j = zVar.f2951k && i16 == zVar.f2950j;
                i16++;
            }
            zVar.G.pack();
            zVar.W();
            Table table12 = new Table();
            zVar.f2938b.addActor(table12);
            float f22 = zVar.f2952l;
            int i17 = (int) (width * f22);
            boolean z2 = ExiledKingdoms.f3378h;
            if (z2) {
                i17 = (int) (f22 * 1280.0f);
            }
            if (z2) {
                table12.setPosition(f2, f13);
            } else {
                table12.setPosition((f2927k0 * 377.0f) - i17, i3 * 2);
            }
            v vVar3 = new v(GameAssets.a(Assets.f("map")), color, false);
            zVar.I = vVar3;
            v vVar4 = new v(GameAssets.a(Assets.f("world")), Color.ORANGE, false);
            zVar.J = vVar4;
            float f23 = i17;
            table12.add(vVar3).width(f23).height(f23).pad(0.0f).bottom();
            float f24 = 1.4f * f23;
            table12.add(vVar4).width(f24).height(f24).pad(0.0f).bottom();
            table12.pack();
            Table table13 = new Table();
            if (!ExiledKingdoms.f3378h) {
                zVar.f2938b.addActor(table13);
            }
            table13.setPosition((f2927k0 * 461.0f) - f23, i3 * 2);
            v vVar5 = new v(GameAssets.a(Assets.f("settings")), color, false);
            zVar.f2944e = vVar5;
            v vVar6 = new v(GameAssets.a(Assets.f("save")), color, false);
            zVar.f2946f = vVar6;
            table13.add(vVar5).width(f23).height(f23).pad(0.0f).bottom();
            table13.add(vVar6).width(f23).height(f23).pad(0.0f).bottom().spaceLeft(f2927k0 * 27.0f);
            table13.pack();
            vVar3.addListener(new x(zVar, 3));
            vVar4.addListener(new x(zVar, 4));
            TextButton textButton = new TextButton(GameString.b("BACK", false), Assets.g(), "menuButton");
            zVar.K = textButton;
            textButton.getLabel().setFontScale(f2927k0);
            textButton.addListener(new x(zVar, 5));
            f1 f1Var = new f1();
            zVar.L = f1Var;
            f1Var.setColor(color);
            f1Var.setWidth(zVar.f2954n * width);
            f1Var.setHeight(zVar.f2954n * width);
            if (ExiledKingdoms.f3378h) {
                f1Var.setWidth(zVar.f2954n * 1280.0f);
                f1Var.setHeight(zVar.f2954n * 1280.0f);
            }
            if (ExiledKingdoms.f3378h) {
                f1Var.setPosition(f2, f2927k0 * 130.0f);
            } else {
                f1Var.setPosition(i3 + ExiledKingdoms.f3390u, f2930n0 * height);
            }
            zVar.f2938b.addActor(f1Var);
            zVar.N.setDrawable(new TextureRegionDrawable(Assets.f("windrose")));
            if (ExiledKingdoms.f3378h) {
                zVar.N.setPosition(width * 0.85f, height * 0.81f);
                Image image = zVar.N;
                float f25 = width * zVar.f2958r * 0.6f;
                image.setSize(f25, f25);
            } else {
                zVar.N.setPosition(width * 0.75f, f2927k0 * 15.0f);
                Image image2 = zVar.N;
                float f26 = width * zVar.f2958r;
                image2.setSize(f26, f26);
            }
            zVar.f2938b.addActor(zVar.N);
            Table table14 = new Table();
            zVar.f2938b.addActor(table14);
            if (ExiledKingdoms.f3378h) {
                float width2 = zVar.f2938b.getWidth();
                float f27 = f2927k0;
                table14.setPosition(width2 - (150.0f * f27), f27 * 15.0f);
                table14.add(textButton).width(f2927k0 * 130.0f).spaceLeft(f2927k0 * 20.0f);
            } else {
                float width3 = zVar.f2938b.getWidth();
                float f28 = f2927k0;
                table14.setPosition(width3 - (150.0f * f28), f28 * 10.0f);
                table14.add(textButton).width(f2927k0 * 140.0f).spaceLeft(f2927k0 * 20.0f);
            }
            table14.pack();
            zVar.V.setVisible(false);
            zVar.f2938b.addActor(zVar.V);
            zVar.W.setVisible(false);
            zVar.f2938b.addActor(zVar.W);
            zVar.X.setVisible(false);
            zVar.f2938b.addActor(zVar.X);
            o0.m.g().setVisible(false);
            zVar.f2938b.addActor(o0.m.g());
            o0.l.e().setVisible(false);
            zVar.f2938b.addActor(o0.l.e());
            g1.f().setVisible(false);
            zVar.f2938b.addActor(g1.f());
            n1 n1Var = new n1();
            zVar.f2937a0 = n1Var;
            zVar.f2938b.addActor(n1Var);
            o0.s sVar = new o0.s();
            zVar.f2939b0 = sVar;
            zVar.f2938b.addActor(sVar);
            m1 m1Var = new m1();
            zVar.f2941c0 = m1Var;
            zVar.f2938b.addActor(m1Var);
            d.f().setVisible(false);
            zVar.f2938b.addActor(d.f());
            f.f().setVisible(false);
            zVar.f2938b.addActor(f.f());
            o0.n.b().setVisible(false);
            zVar.f2938b.addActor(o0.n.b());
            p0.c.b().setVisible(false);
            zVar.f2938b.addActor(p0.c.b());
            o0.x.b().setVisible(false);
            zVar.f2938b.addActor(o0.x.b());
            c0.b().setVisible(false);
            zVar.f2938b.addActor(c0.b());
            zVar.f2938b.addActor(d0.b());
            if (ExiledKingdoms.f3378h) {
                zVar.f2938b.addActor(zVar.R);
                zVar.f2938b.addActor(zVar.S);
            } else {
                zVar.f2938b.addActor(zVar.Q);
            }
            zVar.f2938b.addActor(zVar.U);
            r1.b().setVisible(false);
            zVar.f2938b.addActor(r1.b());
            o oVar = new o(Assets.g(), zVar.f2938b);
            f2924h0 = oVar;
            oVar.setVisible(false);
            zVar.f2938b.addActor(f2924h0);
            zVar.Z.setVisible(false);
            zVar.f2938b.addActor(zVar.Z);
            zVar.Z.toFront();
            o0.r rVar = new o0.r();
            zVar.Y = rVar;
            zVar.f2938b.addActor(rVar);
            rVar.toFront();
            p pVar = new p();
            zVar.T = pVar;
            zVar.f2938b.addActor(pVar);
            p1.b().setVisible(false);
            zVar.f2938b.addActor(p1.b());
            h1.a().setVisible(false);
            zVar.f2938b.addActor(h1.a());
            f1Var.addListener(new x(zVar, 6));
            vVar5.addListener(new a0(4));
            vVar6.addListener(new x(zVar, 0));
            zVar.D.addListener(new x(zVar, 1));
            zVar.E.addListener(new x(zVar, 2));
            for (int i18 = 0; i18 < 5; i18++) {
                zVar.C[i18].addListener(new y(zVar, i18, 0));
            }
            int i19 = 1;
            for (int i20 = 0; i20 < 4; i20++) {
                zVar.f2965z[i20].addListener(new y(zVar, i20, i19));
            }
            f2935s0 = zVar;
        }
        return f2935s0;
    }

    public static boolean x() {
        return f2933q0 >= 0;
    }

    public static Boolean y() {
        return v().V == null ? Boolean.FALSE : Boolean.valueOf(v().V.isVisible());
    }

    public static Boolean z() {
        v();
        o oVar = f2924h0;
        return oVar == null ? Boolean.FALSE : Boolean.valueOf(oVar.isVisible());
    }

    public final boolean A() {
        return this.f2942d.isPressed();
    }

    public final boolean C() {
        return this.Q.isVisible() || this.R.isVisible();
    }

    public final void E(int i2) {
        int iQ;
        if (GameData.v().player.activables == null || GameData.v().player.activables[i2] == null) {
            return;
        }
        int i3 = GameData.v().player.activables[i2].i();
        o0.f fVar = this.V;
        if (i3 == 2) {
            fVar.T(1, GameLevelData.l(GameData.v().player.activables[i2].j()));
            GameAssets.o("item");
            GameLevel.n(true);
        }
        if (GameData.v().player.activables[i2].i() == 1) {
            GameAssets.o("item");
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            boolean z2 = GameLevelData.f3104c;
            m0.b bVarP = m0.b.P();
            int i4 = GameData.v().player.f3092x;
            int i5 = GameData.v().player.f3093y;
            bVarP.getClass();
            ArrayList arrayListH = m0.b.h(i4, i5);
            if (arrayListH.size() > 1) {
                Loot loot = new Loot(GameData.v().player.f3092x + 32, GameData.v().player.f3093y + 32, (ArrayList<Loot>) arrayListH);
                GameLevelData.d(loot);
                GameLevel.f3097d = GameLevelData.x();
                iQ = loot.q();
            } else {
                iQ = arrayListH.size() == 1 ? ((Loot) arrayListH.get(0)).q() : -1;
            }
            if (iQ != -1 && (GameLevel.i(iQ) instanceof Loot) && ((Loot) GameLevel.i(iQ)) != null) {
                fVar.T(1, (Loot) GameLevel.i(iQ));
                GameLevel.n(true);
            }
        }
        if (GameData.v().player.activables[i2].i() == 3) {
            if (GameData.v().player.activables[i2].b().d()) {
                GameData.v().player.activables[i2].b().e();
                f2924h0.t(GameData.v().player.activables[i2].b());
                GameLevel.n(true);
                return;
            }
            GameConsole.a(GameString.b("NO_TALK", false));
            GameLevel.n(false);
        }
        if (GameData.v().player.activables[i2].i() == 4) {
            if (GameData.v().player.activables[i2].f().r()) {
                GameConsole.a(GameString.b("NO_TALK", false));
                GameLevel.n(false);
            } else {
                fVar.T(1, GameData.v().player.activables[i2].f());
                GameLevel.n(true);
            }
        }
        if (GameData.v().player.activables[i2].i() == 5) {
            GameData.v().player.activables[i2].d().getClass();
            if (Player.L1(WorldFactions.i(m0.b.P().E))) {
                GameConsole.a(GameString.b("NOT_ALLOWED", false));
                GameLevel.n(false);
            } else {
                g1.f().g(GameData.v().player.activables[i2].d());
                GameLevel.n(true);
            }
        }
        if (GameData.v().player.activables[i2].i() == 10) {
            GameLevel.n(true);
            new b(GameString.b("SLEEP_CONFIRM", false)).show(this.f2938b);
        }
        if (GameData.v().player.activables[i2].i() == 7) {
            d.f().g(GameData.v().player.activables[i2].k());
            GameLevel.n(true);
        }
        if (GameData.v().player.activables[i2].i() == 11) {
            GameData.v().player.activables[i2].g().R();
            GameLevel.n(false);
        }
        if (GameData.v().player.activables[i2].i() == 6) {
            GameConsole.a(GameString.b("YOU_FOUND", false) + Rules.g(GameData.v().player.activables[i2].c().item_ID));
            PlantSpawn plantSpawnC = GameData.v().player.activables[i2].c();
            if (plantSpawnC.item_ID != 0 && GameData.v().backpack.a(plantSpawnC.item_ID, 1)) {
                plantSpawnC.item_ID = 0;
                plantSpawnC.destroy = true;
            }
            GameLevel.n(false);
        }
        if (GameData.v().player.activables[i2].i() == 8) {
            GameData.v().player.activables[i2].e().K();
            GameLevel.n(false);
        }
        if (GameData.v().player.activables[i2].i() == 9) {
            GameData.v().player.activables[i2].h().Y(GameData.v().player);
            GameLevel.n(false);
        }
    }

    public final void F(Shop shop) {
        GameLevel.n(true);
        this.V.T(1, shop);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0063, code lost:
    
        if (r0.stats.missingMana > (r0.A() * 0.7f)) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void G() {
        if (GameLevel.l()) {
            return;
        }
        if (GameData.v().player.G1() < 1) {
            GameConsole.a(GameString.b("TOO_TIRED_RECOVER", false));
            return;
        }
        if (Player.f3028f) {
            GameConsole.a(GameString.b("ENEMIES_AROUND", false));
            return;
        }
        CharacterSheet characterSheet = GameData.v().player.sheet;
        if (characterSheet.stats.missingHP <= characterSheet.z() / 2) {
            Player player = GameData.v().player;
            if (player.sheet.V()) {
                CharacterSheet characterSheet2 = player.sheet;
            }
            GameLevel.n(true);
            new c(GameString.b("NOT_VERY_INJURED", false)).show(this.f2938b);
            return;
        }
        GameData.v().player.P1();
        l0.b.i();
    }

    public final void H() {
        for (int i2 = 0; i2 <= 7; i2++) {
            this.B[i2].d();
        }
        f2933q0 = -1;
    }

    public final void I(String str) {
        for (int i2 = 0; i2 <= 7; i2++) {
            l1[] l1VarArr = this.B;
            if (l1VarArr[i2].f2771a.equals(str)) {
                l1VarArr[i2].c();
                f2933q0 = i2;
            } else {
                l1VarArr[i2].d();
            }
        }
    }

    public final void J(int i2) {
        boolean z2;
        this.f2943d0 = (float) GameData.v().realTime;
        this.f2936a = i2;
        for (int i3 = 0; i3 < 4; i3++) {
            this.f2965z[i3].setVisible(false);
        }
        this.f2951k = ExiledKingdoms.g();
        int i4 = this.f2936a;
        n0.a aVar = this.f2961v;
        n0.a aVar2 = this.f2960u;
        n0.a aVar3 = this.f2959t;
        e1 e1Var = this.D;
        Label label = this.M;
        Image image = this.N;
        n0.b bVar = this.f2963x;
        n0.b bVar2 = this.f2962w;
        n0.a aVar4 = this.f2964y;
        ImageButton imageButton = this.E;
        b0 b0Var = this.f2948h;
        u uVar = this.P;
        Label label2 = this.f2947g;
        v vVar = this.f2946f;
        v vVar2 = this.f2944e;
        f1 f1Var = this.L;
        ImageButton imageButton2 = this.f2942d;
        Touchpad touchpad = this.f2940c;
        v vVar3 = this.I;
        q1 q1Var = this.f2949i;
        if (i4 == 0) {
            image.setVisible(false);
            e1Var.setVisible(true);
            aVar3.setVisible(true);
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            aVar2.setVisible(GameData.v().player.h1());
            aVar.setVisible(true);
            b0Var.setVisible(true);
            this.J.setVisible(false);
            this.K.setVisible(false);
            q1Var.setVisible(false);
            r1.b().setVisible(false);
            uVar.setVisible(true);
            if (GameData.v().party.j()) {
                imageButton.setVisible(true);
                aVar4.setVisible(true);
                bVar2.setVisible(true);
                bVar.setVisible(GameData.v().party.f().h1());
                z2 = false;
            } else {
                z2 = false;
                imageButton.setVisible(false);
                aVar4.setVisible(false);
                bVar2.setVisible(false);
                bVar.setVisible(false);
            }
            if (z().booleanValue()) {
                touchpad.setVisible(z2);
                imageButton2.setVisible(z2);
                f1Var.setVisible(z2);
                vVar2.setVisible(z2);
                vVar.setVisible(z2);
                label2.setVisible(z2);
                uVar.setVisible(z2);
                vVar3.setVisible(z2);
                b0Var.setVisible(z2);
                this.G.setVisible(z2);
                this.F.setVisible(z2);
            } else {
                touchpad.setVisible(true);
                imageButton2.setVisible(true);
                f1Var.setVisible(true);
                vVar2.setVisible(true);
                vVar.setVisible(true);
                label2.setVisible(true);
                label.setVisible(true);
                vVar3.setVisible(true);
                this.G.setVisible(true);
                this.F.setVisible(true);
            }
        } else if (i4 == 1) {
            image.setVisible(true);
            label.setVisible(false);
            e1Var.setVisible(false);
            aVar3.setVisible(false);
            aVar2.setVisible(false);
            aVar.setVisible(false);
            vVar3.setVisible(false);
            this.J.setVisible(true);
            this.K.setVisible(true);
            q1Var.setVisible(false);
            r1.b().setVisible(false);
            touchpad.setVisible(false);
            imageButton2.setVisible(false);
            f1Var.setVisible(false);
            vVar2.setVisible(false);
            vVar.setVisible(false);
            label2.setVisible(false);
            uVar.setVisible(false);
            b0Var.setVisible(false);
            imageButton.setVisible(false);
            aVar4.setVisible(false);
            bVar2.setVisible(false);
            bVar.setVisible(false);
        } else if (i4 == 2) {
            q1Var.getClass();
            image.setVisible(false);
            q1Var.setVisible(true);
            r1.b().setVisible(true);
            label.setVisible(false);
            e1Var.setVisible(false);
            aVar3.setVisible(false);
            aVar2.setVisible(false);
            aVar.setVisible(false);
            vVar3.setVisible(false);
            this.J.setVisible(false);
            this.K.setVisible(false);
            touchpad.setVisible(false);
            imageButton2.setVisible(false);
            f1Var.setVisible(false);
            vVar2.setVisible(false);
            vVar.setVisible(false);
            label2.setVisible(false);
            uVar.setVisible(false);
            b0Var.setVisible(false);
            imageButton.setVisible(false);
            aVar4.setVisible(false);
            bVar2.setVisible(false);
            bVar.setVisible(false);
        }
        r();
    }

    public final void K(int i2, int i3) {
        this.O.t(i2, i3, 0.0f);
    }

    public final void L(String str) {
        u uVar = this.P;
        uVar.getClass();
        uVar.f2893a = GameData.v().u();
        uVar.setText(str);
        this.D.reset();
    }

    public final void M() {
        int i2 = this.f2945e0;
        if (i2 > 0) {
            this.T.b(i2);
        }
    }

    public final void O() {
        boolean z2 = ExiledKingdoms.f3378h;
        Stage stage = this.f2938b;
        if (z2) {
            this.R.p(stage, false);
        } else {
            this.Q.q(stage, false);
        }
        GameLevel.n(true);
    }

    public final void P() {
        m1 m1Var = this.f2941c0;
        m1Var.getClass();
        GameLevel.n(true);
        m1Var.setVisible(true);
        m1Var.f2788b.setDisabled(!GameData.v().d("NG"));
        m1Var.f2789c.setDisabled(!GameData.v().d("FT"));
        m1Var.f2790d.setDisabled(!GameData.v().d("NI"));
        m1Var.f2791e.setDisabled(!GameData.v().d("IM"));
        m1Var.f2792f.setDisabled(!GameData.v().d("H10"));
        m1Var.f2793g.setDisabled(!GameData.v().d("G9"));
        m1Var.f2794h.setDisabled(!GameData.v().d("J10"));
        m1Var.f2796j.setDisabled(!GameData.v().d("E10"));
        m1Var.f2797k.setDisabled(!GameData.v().d("F8"));
        m1Var.f2800n.setDisabled(!GameData.v().d("F10"));
        m1Var.f2801o.setDisabled(!GameData.v().d("D11"));
        m1Var.f2803q.setDisabled(!GameData.v().d("H6"));
        m1Var.f2798l.setDisabled(!GameData.v().d("H12"));
        m1Var.f2799m.setDisabled(!GameData.v().d("K11"));
        m1Var.f2795i.setDisabled(!GameData.v().d("J7"));
        m1Var.f2802p.setDisabled(!GameData.v().d("C13"));
        int iB = GameData.v().gameVariables.b("key_to_past");
        t tVar = m1Var.f2787a;
        if (iB == 100) {
            tVar.setDisabled(false);
            tVar.setText(GameWorld.f3192f.g("E10_tower"));
            tVar.setColor(Color.GREEN);
        } else {
            tVar.setDisabled(true);
            tVar.setText("???");
            tVar.setColor(Color.WHITE);
        }
    }

    public final void Q() {
        p1.b().toFront();
        p1.b().setVisible(true);
        p();
    }

    /* JADX WARN: Code restructure failed: missing block: B:130:0x046a, code lost:
    
        if (net.fdgames.GameEntities.Final.Player.f3027e != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0472, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().TIP_SAVE == false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0484, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().player.sheet.stats.i() <= 125) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0486, code lost:
    
        net.fdgames.GameWorld.GameData.v().TIP_SAVE = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x048e, code lost:
    
        if (net.fdgames.ek.ExiledKingdoms.f3378h == false) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0498, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().D() != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x04a2, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().F() == false) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x04a5, code lost:
    
        r0 = net.fdgames.Helpers.GameString.b("HELP_SAVE_DESKTOP", false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x04ac, code lost:
    
        r0 = net.fdgames.Helpers.GameString.b("HELP_SAVE_DESKTOP_CASUAL", false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x04bb, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().D() != false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x04c5, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().F() == false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x04c8, code lost:
    
        r0 = net.fdgames.Helpers.GameString.b("HELP_SAVE", false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x04cf, code lost:
    
        r0 = net.fdgames.Helpers.GameString.b("HELP_SAVE_CASUAL", false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x04d5, code lost:
    
        new n0.g0(net.fdgames.Helpers.GameString.b("HELP_SECTION_SAVE", false), r0, net.fdgames.assets.Assets.f("help_saving")).show(r4);
        com.badlogic.gdx.utils.GdxNativesLoader.f1832b = net.fdgames.GameWorld.GameData.v().u();
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x04f7, code lost:
    
        if (net.fdgames.GameEntities.Final.Player.f3027e != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x04ff, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().TIP_RELOAD == false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0511, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().player.sheet.stats.i() <= 950) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0513, code lost:
    
        net.fdgames.GameWorld.GameData.v().TIP_RELOAD = false;
        new n0.h0(net.fdgames.Helpers.GameString.b("HELP_SECTION_RELOAD", false), net.fdgames.Helpers.GameString.b("HELP_RELOAD", false), net.fdgames.assets.Assets.f("help_reload")).show(r4);
        com.badlogic.gdx.utils.GdxNativesLoader.f1832b = net.fdgames.GameWorld.GameData.v().u();
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0541, code lost:
    
        if (net.fdgames.GameEntities.Final.Player.f3027e != false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0549, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().TIP_SLEEP == false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0555, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().player.G1() != 0) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0557, code lost:
    
        net.fdgames.GameWorld.GameData.v().TIP_SLEEP = false;
        new n0.i0(net.fdgames.Helpers.GameString.b("HELP_SECTION_INN", false), net.fdgames.Helpers.GameString.b("HELP_INN", false), net.fdgames.assets.Assets.f("help_sleeping")).show(r4);
        com.badlogic.gdx.utils.GdxNativesLoader.f1832b = net.fdgames.GameWorld.GameData.v().u();
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0584, code lost:
    
        if (net.fdgames.GameEntities.Final.Player.f3027e != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x058c, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().msg_letter_tremadan == false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x059a, code lost:
    
        if (net.fdgames.GameWorld.GameData.v().CurrentLevel.equals("E10_tower") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x059c, code lost:
    
        net.fdgames.GameWorld.GameData.v().msg_letter_tremadan = false;
        net.fdgames.GameLevel.GameLevel.n(true);
        net.fdgames.GameWorld.GameData.v().gameVariables.e(15, "ark_lothasan");
        new n0.j0(net.fdgames.Helpers.GameString.b("MSG_LETTER_ARK_TITLE", false), net.fdgames.Helpers.GameString.b("MSG_LETTER_ARK", false)).show(r4);
        com.badlogic.gdx.utils.GdxNativesLoader.f1832b = net.fdgames.GameWorld.GameData.v().u();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void R() {
        boolean z2 = this.f2951k;
        o0.i[] iVarArr = this.C;
        if (z2 || ExiledKingdoms.f3378h) {
            if (z2 && this.f2936a == 0) {
                iVarArr[this.f2950j].f3540j = true;
            }
            f2935s0.f2940c.setVisible(w0);
            f2935s0.f2942d.setVisible(w0);
        } else {
            iVarArr[this.f2950j].f3540j = false;
            if (this.f2936a == 0) {
                f2935s0.f2940c.setVisible(true);
                f2935s0.f2942d.setVisible(true);
            }
        }
        if (!GameLevel.l()) {
            GameData.v().player.getClass();
            if (f2926j0 && !GameData.v().player.g0()) {
                GameData.v().player.E0(-1);
            }
        }
        if (l0.b.f2310k) {
            return;
        }
        boolean zBooleanValue = z().booleanValue();
        boolean z3 = GameData.v().WARNING_SNEAK;
        Stage stage = this.f2938b;
        if (z3) {
            GameData.v().WARNING_SNEAK = false;
            GameLevel.n(true);
            new k0(GameString.b("WARNING_SNEAK", false), 0).show(stage);
            GameLevel.n(false);
        }
        if (GameData.v().WARNING_WHIRLWIND) {
            GameData.v().WARNING_WHIRLWIND = false;
            GameLevel.n(true);
            new l0(GameString.b("WARNING_WHIRLWIND", false), 0).show(stage);
            GameLevel.n(false);
        }
        if (GameData.v().WARNING_CLEAVE) {
            GameData.v().WARNING_CLEAVE = false;
            GameLevel.n(true);
            new m0(GameString.b("WARNING_CLEAVE", false), 0).show(stage);
            GameLevel.n(false);
        }
        if (GameData.v().WARNING_EVASION) {
            GameData.v().WARNING_EVASION = false;
            GameLevel.n(true);
            new n0(GameString.b("WARNING_EVASION", false), 0).show(stage);
            GameLevel.n(false);
        }
        if (GameData.v().WARNING_CRUSADER) {
            GameData.v().WARNING_CRUSADER = false;
            GameLevel.n(true);
            new p0(GameString.b("WARNING_CRUSADER", false), 0).show(stage);
            GameLevel.n(false);
        }
        if (GameData.v().WARNING_TRAITS) {
            GameData.v().WARNING_TRAITS = false;
            GameLevel.n(true);
            new q0("Please assign again your character's trait points", 0).show(stage);
            GameLevel.n(false);
        }
        if (GameData.v().u() >= com.badlogic.gdx.utils.GdxNativesLoader.f1832b + 4.0f) {
            if (!GameData.v().introMessageShown && GameData.v().CurrentLevel.equals("H10") && !GameData.v().player.i0()) {
                GameData.v().introMessageShown = true;
                GameLevel.n(true);
                new o0(GameString.b("INITIAL_MESSAGE_TITLE", false), GameString.b("INITIAL_MESSAGE", false)).show(stage);
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            } else if (!GameData.v().shardsMessageShown && GameData.v().shardsCompleted && !zBooleanValue && !GameData.v().player.i0()) {
                GameData.v().shardsMessageShown = true;
                GameLevel.n(true);
                new r0(GameString.b("WARNING_SHARDS_TITLE", false), GameString.b("WARNING_SHARDS", false)).show(stage);
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            } else if (!GameData.v().tolCurseMessageShown && GameData.v().tolCurse && !zBooleanValue && !GameData.v().player.i0()) {
                GameData.v().tolCurseMessageShown = true;
                GameLevel.n(true);
                new s0(GameString.b("TOL_CURSE_LOG", false), GameString.b("TOL_CURSE_MSG", false)).show(stage);
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            } else if (!Player.f3027e && ExiledKingdoms.f3378h && GameData.v().TIP_MOVEMENT) {
                GameData.v().TIP_MOVEMENT = false;
                new t0(GameString.b("HELP_SECTION_MOVEMENT", false), GameString.b("HELP_MOVEMENT", false), Assets.f("help_moving")).show(stage);
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            } else if (!Player.f3027e && GameData.v().TIP_INTERACTION && GameData.v().player.f3092x < 2400) {
                GameData.v().TIP_INTERACTION = false;
                if (ExiledKingdoms.f3378h) {
                    new u0(GameString.b("HELP_SECTION_INTERACTION", false), GameString.b("HELP_INTERACTION_DESKTOP", false), Assets.f("help_interaction")).show(stage);
                } else {
                    new v0(GameString.b("HELP_SECTION_INTERACTION", false), GameString.b("HELP_INTERACTION", false), Assets.f("help_interaction")).show(stage);
                }
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            } else if (!Player.f3027e && GameData.v().TIP_DUNGEON && !m0.b.P().f2423h && !GameData.v().D() && !GameData.v().F()) {
                GameData.v().TIP_DUNGEON = false;
                new w0(GameString.b("HELP_SECTION_DUNGEON", false), GameString.b("HELP_DUNGEON", false), Assets.f("help_dungeon")).show(stage);
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            } else if (!Player.f3027e && GameData.v().TIP_ITEMS && GameData.v().y().killed == 5 && GameData.v().player.f3093y > 2000) {
                GameData.v().TIP_ITEMS = false;
                new x0(GameString.b("HELP_SECTION_ITEMS", false), GameString.b("HELP_ITEMS", false), Assets.f("help_items")).show(stage);
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            } else if (Player.f3027e || !GameData.v().TIP_RECOVERY || GameData.v().player.sheet.r() <= 0.5f) {
                m0.b.P().getClass();
                Coords coordsB = GameData.v().player.B();
                int size = GameLevel.f3096c.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    WorldFactions worldFactions = GameWorld.f3189c;
                    int[] iArrR = GameData.v().player.r();
                    ArrayList<MapActor> arrayList = GameLevel.f3096c;
                    if (!worldFactions.g(iArrR, arrayList.get(i2).r()) || arrayList.get(i2).i0() || m0.b.F(arrayList.get(i2).q(), coordsB) >= 256.0d) {
                        i2++;
                    } else if (GameData.v().TIP_MOVEATTACK) {
                        GameData.v().TIP_MOVEATTACK = false;
                        if (ExiledKingdoms.f3378h) {
                            new e0(GameString.b("HELP_SECTION_ATTACK_DESKTOP", false), GameString.b("HELP_ATTACK_DESKTOP", false), Assets.f("help_attack")).show(stage);
                        } else {
                            new f0(GameString.b("HELP_SECTION_MOVING", false), GameString.b("HELP_MOVING", false), Assets.f("help_moving_attacking")).show(stage);
                        }
                        com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
                    }
                }
            } else {
                GameData.v().TIP_RECOVERY = false;
                new y0(GameString.b("HELP_SECTION_HEALING", false), GameString.b("HELP_HEALING", false), Assets.f("help_healing_recovery")).show(stage);
                com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
            }
        }
        stage.act(Gdx.graphics.getDeltaTime());
        Label label = this.f2947g;
        label.setText(GameConsole.c());
        label.setColor(1.0f, 1.0f, 1.0f, GameConsole.b());
        if (this.f2936a == 0 && !z().booleanValue()) {
            Touchpad touchpad = this.f2940c;
            D(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        }
        if (!z().booleanValue() && this.f2936a == 0) {
            this.D.b();
            if (GameData.v().party.j()) {
                NPC npcF = GameData.v().party.f();
                this.f2962w.a(npcF.sheet.o() / npcF.sheet.z());
                if (npcF.h1()) {
                    this.f2963x.a(npcF.sheet.p() / npcF.sheet.A());
                }
                int iB = Rules.b(npcF.sheet.stats.f());
                this.f2964y.a((npcF.sheet.stats.i() - iB) / (Rules.b(npcF.sheet.stats.f() + 1) - iB));
            }
        }
        n0.a aVar = this.f2959t;
        aVar.a(GameData.v().player.sheet.o() / GameData.v().player.sheet.z());
        n0.a aVar2 = this.f2960u;
        aVar2.a(GameData.v().player.sheet.p() / GameData.v().player.sheet.A());
        if (Settings.l()) {
            aVar.setText("" + GameData.v().player.sheet.o() + "/" + GameData.v().player.sheet.z());
            aVar2.setText("" + GameData.v().player.sheet.p() + "/" + GameData.v().player.sheet.A());
        } else {
            aVar.setText("");
            aVar2.setText("");
        }
        int iB2 = Rules.b(GameData.v().player.sheet.stats.f());
        this.f2961v.a((GameData.v().player.sheet.stats.i() - iB2) / (Rules.b(GameData.v().player.sheet.stats.f() + 1) - iB2));
    }

    public final void S() {
        Boolean[] boolArr;
        v[] vVarArr;
        if (!z().booleanValue() && this.f2936a == 0) {
            int i2 = 0;
            while (true) {
                boolArr = this.s;
                vVarArr = this.f2965z;
                if (i2 >= 4) {
                    break;
                }
                boolArr[i2] = Boolean.valueOf(vVarArr[i2].isVisible());
                i2++;
            }
            for (int i3 = 0; i3 < 4; i3++) {
                vVarArr[i3].setVisible(false);
            }
            for (int i4 = 1; i4 <= GameData.v().player.numActivables; i4++) {
                int i5 = i4 - 1;
                if (!boolArr[i5].booleanValue()) {
                    vVarArr[i5].reset();
                }
                if (GameData.v().player.activables[i5] != null) {
                    TextureRegion textureRegionA = GameData.v().player.activables[i5].a();
                    if (textureRegionA != null) {
                        vVarArr[i5].setStyle(GameAssets.a(textureRegionA));
                        vVarArr[i5].setVisible(true);
                        vVarArr[i5].b();
                        vVarArr[i5].getImage().setSize(vVarArr[i5].getWidth() * 0.9f, vVarArr[i5].getHeight() * 0.9f);
                        vVarArr[i5].getImage().setPosition(vVarArr[i5].getWidth() * 0.05f, vVarArr[i5].getHeight() * 0.05f);
                    }
                } else {
                    System.out.println("WARNING: null button!");
                }
            }
        }
        v vVar = this.A;
        if (vVar.isVisible()) {
            vVar.b();
        }
    }

    public final void T() {
        NPC npcF;
        if (!GameData.v().party.j() || (npcF = GameData.v().party.f()) == null) {
            return;
        }
        ImageButton imageButton = this.E;
        imageButton.setStyle(GameAssets.a(npcF.E()));
        imageButton.getImage().setSize(imageButton.getWidth() * 0.9f, imageButton.getHeight() * 0.9f);
        imageButton.getImage().setPosition(imageButton.getWidth() * 0.05f, imageButton.getHeight() * 0.05f);
    }

    public final void U() {
        this.M.setText(" " + m0.b.P().f2422g + "; " + FDUtils.h());
    }

    public final void W() {
        o0.i[] iVarArr;
        int i2 = 0;
        while (true) {
            iVarArr = this.C;
            if (i2 >= 5) {
                break;
            }
            iVarArr[i2].setVisible(false);
            i2++;
        }
        if (this.f2936a == 0) {
            int i3 = 0;
            while (i3 < 5) {
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                int iI1 = GameData.v().player.I1(i3);
                int iK1 = GameData.v().player.K1(i3);
                iVarArr[i3].a(iI1);
                iVarArr[i3].c(iK1);
                iVarArr[i3].setVisible(GameData.v().player.I1(i3) > 0 && GameData.v().player.K1(i3) > 0);
                iVarArr[i3].f3540j = iI1 > 0 && this.f2951k && this.f2950j == i3;
                i3++;
            }
        }
    }

    public final void Y() {
        l1[] l1VarArr;
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        GameData.v().player.b2();
        int i2 = 0;
        while (true) {
            l1VarArr = this.B;
            if (i2 > 7) {
                break;
            }
            l1VarArr[i2].setVisible(false);
            i2++;
        }
        if (this.f2936a == 0) {
            for (int i3 = 0; i3 < 8; i3++) {
                l1VarArr[i3].f(GameData.v().player.F1(i3));
                l1VarArr[i3].setVisible(true);
                l1VarArr[i3].e();
            }
        }
        H();
        boolean z2 = ExiledKingdoms.f3371a;
        v vVar = this.A;
        if (z2 || GameData.v().player.sheet.stats.i() < 30000) {
            vVar.setVisible(false);
        } else {
            vVar.setVisible(true);
        }
    }

    public final void Z() {
        k(f2933q0);
    }

    public final Stage a() {
        return this.f2938b;
    }

    public final void a0() {
        if (GameData.v().realTime < this.f2943d0) {
            return;
        }
        this.f2943d0 = (float) (GameData.v().realTime + 0.3499999940395355d);
        b0(this.f2950j);
    }

    public final void b0(int i2) {
        if (this.C[i2].f3542l) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (!GameData.v().player.F0()) {
                return;
            }
        }
        AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
        Player player = GameData.v().player;
        int iJ1 = player.J1(i2);
        a.EnumC0031a enumC0031a = a.EnumC0031a.CASTING;
        if (iJ1 == 1) {
            int iH1 = player.H1(i2);
            Item itemF = Rules.f(GameData.v().backpack.h(iH1));
            if (itemF != null) {
                if (Rules.l(itemF.item_ID).booleanValue()) {
                    if (itemF.type == Item.ItemType.WEAPON) {
                        GameAssets.o("sword");
                        CharacterEffects characterEffects = player.sheet.effects;
                        Boolean bool = Boolean.FALSE;
                        characterEffects.stab = bool;
                        characterEffects.stabBonus = 0;
                        characterEffects.disintegrate = bool;
                        characterEffects.disintegrateBonus = 0;
                    } else {
                        GameAssets.o("item");
                    }
                    player.sheet.e(iH1);
                } else if (itemF.g(player.sheet)) {
                    player.lastItemUsed = GameData.v().u();
                    if (itemF.type == Item.ItemType.POTION) {
                        GameAssets.o("potion");
                    } else {
                        GameAssets.o("buff1");
                    }
                    itemF.OnUse.a();
                    int i3 = itemF.manaCost;
                    if (i3 > 0) {
                        player.sheet.j0(i3);
                        k0.a aVarL = k0.a.l();
                        aVarL.getClass();
                        aVarL.b(player.B(), enumC0031a, 1.0f).owner = player;
                    }
                }
            }
        }
        if (player.J1(i2) == 2) {
            Item itemF2 = Rules.f(player.sheet.inventory.i(player.H1(i2)));
            if (itemF2 != null && itemF2.g(player.sheet)) {
                GameAssets.o("buff1");
                itemF2.OnUse.a();
                int i4 = itemF2.manaCost;
                if (i4 > 0) {
                    player.sheet.j0(i4);
                    k0.a aVarL2 = k0.a.l();
                    aVarL2.getClass();
                    aVarL2.b(player.B(), enumC0031a, 1.0f).owner = player;
                }
            }
        }
        W();
    }

    public final void e() {
        if (ExiledKingdoms.f3378h) {
            this.R.setVisible(false);
        } else {
            this.Q.setVisible(false);
        }
        GameLevel.n(false);
    }

    public final void f() {
        if (GameData.v().realTime < this.f2943d0) {
            return;
        }
        this.f2943d0 = (float) (GameData.v().realTime + 0.3499999940395355d);
        int i2 = this.f2950j;
        do {
            int i3 = this.f2950j + 1;
            this.f2950j = i3;
            if (i3 > 4) {
                this.f2950j = 0;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.I1(this.f2950j) != 0) {
                break;
            }
        } while (this.f2950j != i2);
        W();
    }

    public final void g() {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        if (GameData.v().player.i0() || !GameData.v().party.j()) {
            return;
        }
        this.V.U(0, null, GameData.v().party.f());
        GameLevel.n(true);
    }

    public final void h() {
        int i2 = this.f2936a;
        if (i2 == 2) {
            J(0);
            GameLevel.n(false);
            Y();
            return;
        }
        q1 q1Var = this.f2949i;
        if (i2 == 1) {
            J(2);
            q1Var.a(GameData.v().CurrentLevel);
            return;
        }
        if (m0.b.P().f2425j) {
            J(2);
            q1Var.a(GameData.v().CurrentLevel);
        } else {
            J(1);
        }
        GameLevel.n(true);
        Y();
        W();
    }

    public final void i() {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        if (GameData.v().player.i0()) {
            return;
        }
        this.V.T(0, null);
        if (GameData.v().party != null && GameData.v().party.j() && GameData.v().party.f().i0()) {
            GameData.v().player.o("RECOVER", GameData.v().party.f().q(), null, 1.0f, null);
        }
        GameLevel.n(true);
    }

    public final void j() {
        if (GameData.v().realTime < this.f2943d0) {
            return;
        }
        this.f2943d0 = (float) (GameData.v().realTime + 0.3499999940395355d);
        int i2 = this.f2950j;
        do {
            int i3 = this.f2950j - 1;
            this.f2950j = i3;
            if (i3 < 0) {
                this.f2950j = 4;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.I1(this.f2950j) != 0) {
                break;
            }
        } while (this.f2950j != i2);
        W();
    }

    public final void k(int i2) {
        this.B[i2].b();
    }

    public final void o(float f2, float f3) {
        q1 q1Var = this.f2949i;
        float x2 = q1Var.getX();
        com.badlogic.gdx.math.Vector3 aVar = this.O;
        q1Var.setX(((f2 - aVar.f1729a) * 0.05f) + x2);
        q1Var.setY(q1Var.getY() - ((f3 - aVar.f1730b) * 0.05f));
    }

    public final void p() {
        this.f2938b.draw();
    }

    public final void q() {
        v vVar = this.A;
        vVar.setVisible(true);
        vVar.reset();
    }

    public final void r() {
        this.f2951k = ExiledKingdoms.g();
        w0 = Settings.k();
        if (ExiledKingdoms.g() && !w0) {
            f2935s0.f2940c.setVisible(false);
            f2935s0.f2942d.setVisible(false);
        } else if (this.f2936a == 0) {
            f2935s0.f2940c.setVisible(true);
            f2935s0.f2942d.setVisible(true);
        }
        X(Settings.r());
        V(Settings.p());
        v0 = Settings.q();
        X(t0);
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        ImageButton.ImageButtonStyle imageButtonStyleC = GameAssets.c(GameData.v().player.E());
        e1 e1Var = this.D;
        e1Var.setStyle(imageButtonStyleC);
        e1Var.getImage().setSize(e1Var.getWidth() * 0.9f, e1Var.getHeight() * 0.9f);
        e1Var.getImage().setPosition(e1Var.getWidth() * 0.05f, e1Var.getHeight() * 0.05f);
        T();
        v().W();
        v vVar = this.J;
        vVar.getImage().setSize(vVar.getWidth() * 0.8f, vVar.getHeight() * 0.8f);
        vVar.getImage().setPosition(vVar.getWidth() * 0.1f, vVar.getHeight() * 0.1f);
        boolean z2 = ExiledKingdoms.f3371a;
        v vVar2 = this.A;
        if (z2) {
            vVar2.setVisible(false);
        } else if (GameData.v().player.sheet.stats.i() >= 30000) {
            vVar2.setVisible(true);
        }
        com.badlogic.gdx.utils.GdxNativesLoader.f1832b = GameData.v().u();
        boolean zD = GameData.v().D();
        v vVar3 = this.f2946f;
        if ((zD || GameData.v().F() || m0.b.P().f2423h || m0.b.P().f2426k) && !GameData.v().G()) {
            vVar3.setDisabled(false);
        } else {
            vVar3.setDisabled(true);
        }
    }

    public final Character s() {
        return this.V.O();
    }

    public final b0 t() {
        return this.f2948h;
    }

    public final int w() {
        return this.f2936a;
    }
}
