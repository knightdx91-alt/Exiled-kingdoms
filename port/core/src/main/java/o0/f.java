package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;
import k0.a;
import n0.k1;
import n0.l1;
import n0.z0;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.SheetBonus;
import net.fdgames.GameEntities.Final.Loot;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.GameEntities.Helpers.Lootable;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.SkillActions;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: CharacterWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class f extends Window {
    public static Lootable K0 = null;
    static int L0 = 0;
    public static float M0 = 1.0f;
    public static float N0;
    public static float O0;
    public static float P0;
    public static float Q0;
    public static float R0;
    public static float S0;
    public static float T0;
    public static float U0;
    public static int V0;
    public static int W0;
    public Label A;
    public n0.t A0;
    public Label B;
    public n0.t B0;
    public Label C;
    public ImageButton C0;
    public Label D;
    public ImageButton D0;
    public Label E;
    public n0.a E0;
    public Label F;
    public n0.a F0;
    public Label G;
    public n0.a G0;
    public Label H;
    public float H0;
    public Label I;
    public int I0;
    public o0.c J;
    public int J0;
    public o0.b K;
    public Label L;
    public o0.a M;
    public Label N;
    public Label O;
    public Label P;
    public Label Q;
    public Image R;
    public o0.d S;
    public o0.i T;
    public Image U;
    public Image V;
    public Image W;
    public Image X;
    public Image Y;
    public Image Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Table f3470a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public Image f3471a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Table f3472b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public n0.t f3473b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Table f3474c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public n0.t f3475c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Table f3476d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public n0.t f3477d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public z f3478e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public o0.i f3479e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Table f3480f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public o0.i f3481f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Table f3482g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public o0.i f3483g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Table f3484h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public o0.i f3485h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Table f3486i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public o0.i f3487i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Table f3488j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public o0.i f3489j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Table f3490k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public o0.i f3491k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public o0.k f3492l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public o0.i f3493l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Table f3494m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public o0.i f3495m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Table f3496n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public o0.i f3497n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Character f3498o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public o0.i f3499o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Label f3500p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public o0.i f3501p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Label f3502q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public o0.i[] f3503q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Label f3504r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public Image f3505r0;
    public Label s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public o0.i[] f3506s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Label f3507t;
    public Image t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Label f3508u;
    public Label u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Label f3509v;
    public n0.t v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Label f3510w;
    public n0.t w0;
    public n0.t x0;
    public n0.t y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Label f3511z;
    public n0.t z0;

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class a extends z0 {
        a(int i2) {
            super(i2, true);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            f.this.M(this.f2970c);
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (GameData.v().hasVault) {
                f.this.T(1, GameData.v().z("vault"));
            }
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (GameData.v().hasVault2) {
                f.this.T(1, GameData.v().z("vault2"));
            }
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class d extends ChangeListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (GameData.v().hasVault3) {
                f.this.T(1, GameData.v().z("vault3"));
            }
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class e extends ChangeListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (GameData.v().hasVault4) {
                f.this.T(1, GameData.v().z("vault4"));
            }
        }
    }

    /* JADX INFO: renamed from: o0.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CharacterWindow.java */
    final class C0042f extends InputListener {
        C0042f() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            f.K0.b();
            f fVar = f.this;
            fVar.V(0, 0);
            if (f.K0.isEmpty().booleanValue()) {
                fVar.E();
            }
            fVar.X();
            return true;
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class g extends InputListener {
        g() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            f.K0.e();
            f fVar = f.this;
            fVar.V(0, 0);
            if (f.K0.isEmpty().booleanValue()) {
                fVar.E();
            }
            fVar.X();
            return true;
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class h extends k1 {
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class i extends p0.b {
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            GameLevel.n(false);
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class j extends k1 {
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class k extends k1 {
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class l extends z0 {
        l(int i2) {
            super(i2, false);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            int i2 = this.f2970c;
            f fVar = f.this;
            int iR = fVar.R();
            int i3 = 0;
            while (i2 > i3) {
                if (fVar.f3498o.A1(iR)) {
                    GameData.v().player.R1((int) (SkillActions.a(GameData.v().player.sheet) * f.t(fVar, iR)));
                    i3++;
                } else {
                    i2 = 0;
                }
            }
            if (i3 > 0) {
                GameAssets.o("coin");
                fVar.X();
            }
        }
    }

    /* JADX INFO: compiled from: CharacterWindow.java */
    final class m extends z0 {
        m(int i2) {
            super(i2, true);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            int i2 = this.f2970c;
            f fVar = f.this;
            int iR = fVar.R();
            int i3 = 0;
            while (i2 > i3) {
                if (fVar.f3498o.A1(iR)) {
                    f.K0.c(iR);
                    i3++;
                } else {
                    i2 = 0;
                }
            }
            if (i3 > 0) {
                GameAssets.o("item2");
                fVar.V(0, 0);
                fVar.X();
            }
        }
    }

    public f() {
        super("", Assets.g());
        this.f3470a = new Table();
        this.f3472b = new Table();
        this.f3474c = new Table();
        this.f3476d = new Table();
        this.f3478e = new z();
        new Table();
        this.f3480f = new Table();
        this.f3482g = new Table();
        this.f3484h = new Table();
        this.f3486i = new Table();
        this.f3488j = new Table();
        this.f3490k = new Table();
        this.f3492l = new o0.k();
        this.f3494m = new Table();
        this.f3496n = new Table();
        Label label = new Label("", Assets.g(), "menuLabelStrongLargeStyle");
        this.f3500p = label;
        Label label2 = new Label("", Assets.g(), "menuLabelStyle");
        this.f3502q = label2;
        Label label3 = new Label(GameString.b("ATTACK_STATS", false), Assets.g(), "menuLabelStrongMidLargeStyle");
        this.f3504r = label3;
        Label label4 = new Label(a.a.o("DAMAGE", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
        this.s = label4;
        Label label5 = new Label(a.a.o("SPEED", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
        this.f3507t = label5;
        Label label6 = new Label("", Assets.g(), "menuLabelStyle");
        this.f3508u = label6;
        Label label7 = new Label(a.a.o("CRITICAL", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
        this.f3509v = label7;
        Label label8 = new Label(a.a.m(GameString.b("DPS", false), ": ", new StringBuilder()), Assets.g(), "menuLabelSubStrongStyle");
        this.f3510w = label8;
        Label label9 = new Label("", Assets.g(), "menuLabelStyle");
        this.f3511z = label9;
        Label label10 = new Label(a.a.o("DPS", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
        this.A = label10;
        Label label11 = new Label("", Assets.g(), "menuLabelStyle");
        this.B = label11;
        Label label12 = new Label(GameString.b("RESISTANCES", false), Assets.g(), "menuLabelStrongMidLargeStyle");
        this.C = label12;
        Label label13 = new Label("+0   ", Assets.g(), "menuLabelStyle");
        this.D = label13;
        Label label14 = new Label("+0   ", Assets.g(), "menuLabelStyle");
        this.E = label14;
        Label label15 = new Label("+0  ", Assets.g(), "menuLabelStyle");
        this.F = label15;
        Label label16 = new Label("+0   ", Assets.g(), "menuLabelStyle");
        this.G = label16;
        Label label17 = new Label("+0   ", Assets.g(), "menuLabelStyle");
        this.H = label17;
        Label label18 = new Label("+0   ", Assets.g(), "menuLabelStyle");
        this.I = label18;
        this.J = new o0.c();
        this.K = new o0.b();
        Label label19 = new Label(a.a.o("ARMOR", false, new StringBuilder(), ": "), Assets.g(), "menuLabelStrongMidLargeStyle");
        this.L = label19;
        this.M = new o0.a();
        Label label20 = new Label(GameString.b("BACKPACK", false), Assets.g(), "menuLabelStrongLargeStyle");
        this.N = label20;
        Label label21 = new Label(GameString.b("EQUIPMENT", false), Assets.g(), "menuLabelStrongLargeStyle");
        this.O = label21;
        Label label22 = new Label("", Assets.g(), "menuLabelStyle");
        this.P = label22;
        Label label23 = new Label("", Assets.g(), "menuLabelStrongStyle");
        this.Q = label23;
        this.R = new Image();
        this.T = new o0.i();
        this.U = new Image();
        this.V = new Image();
        this.W = new Image();
        this.X = new Image();
        this.Y = new Image();
        this.Z = new Image();
        this.f3471a0 = new Image();
        n0.t tVar = new n0.t(a.a.m(GameString.b("QUICKSLOTS", false), " ", new StringBuilder(" ")), Assets.g(), "menuSmallButton");
        this.f3473b0 = tVar;
        this.f3475c0 = new n0.t(GameString.b("EQUIP", false), Assets.g(), "menuButton");
        this.f3477d0 = new n0.t(GameString.b("DROP", false), Assets.g(), "menuButton");
        Label label24 = new Label("   ", Assets.g(), "menuLabelStyle");
        this.f3479e0 = new o0.i();
        this.f3481f0 = new o0.i();
        this.f3483g0 = new o0.i();
        this.f3485h0 = new o0.i();
        this.f3487i0 = new o0.i();
        this.f3489j0 = new o0.i();
        this.f3491k0 = new o0.i();
        this.f3493l0 = new o0.i();
        this.f3495m0 = new o0.i();
        this.f3497n0 = new o0.i();
        this.f3499o0 = new o0.i();
        this.f3501p0 = new o0.i();
        this.f3503q0 = new o0.i[20];
        this.f3505r0 = new Image();
        this.f3506s0 = new o0.i[20];
        this.t0 = new Image();
        Label label25 = new Label("", Assets.g(), "menuLabelStyle");
        this.u0 = label25;
        this.v0 = new n0.t(GameString.b("TAKE", false), Assets.g(), "menuButton");
        this.w0 = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
        this.x0 = new n0.t(GameString.b("TAKE_ALL", false), Assets.g(), "menuButton");
        this.y0 = new n0.t(GameString.b("JOURNAL", false), Assets.g(), "menuButton");
        this.z0 = new n0.t(GameString.b("SKILLS", false), Assets.g(), "menuButton");
        this.A0 = new n0.t(GameString.b("REPUTATION", false), Assets.g(), "menuButton");
        this.B0 = new n0.t(GameString.b("STAT_DETAILS", false), Assets.g(), "menuButton");
        this.C0 = new ImageButton(GameAssets.a(GameAssets.f3358z));
        this.D0 = new ImageButton(GameAssets.a(GameAssets.f3358z));
        this.E0 = new n0.a(1, 0.8f);
        this.F0 = new n0.a(0, 0.8f);
        this.G0 = new n0.a(2, 0.8f);
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        this.f3498o = GameData.v().player;
        D();
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        N0 = fMin;
        O0 = fMin;
        if (ExiledKingdoms.f3378h) {
            N0 = 0.8f;
            O0 = 1.0f;
        }
        float f2 = N0;
        Q0 = 5.0f * f2;
        R0 = 82.0f * f2;
        float f3 = 412.0f * f2;
        P0 = f3;
        S0 = 140.0f * f2;
        T0 = f2 * 120.0f;
        U0 = f3 / 3.0f;
        label.setFontScale(O0);
        label2.setFontScale(O0);
        label24.setFontScale(O0);
        label4.setFontScale(O0);
        label5.setFontScale(O0);
        label6.setFontScale(O0);
        label7.setFontScale(O0);
        label8.setFontScale(O0);
        label9.setFontScale(O0);
        label11.setFontScale(O0);
        label10.setFontScale(O0);
        label19.setFontScale(O0);
        label20.setFontScale(O0);
        label21.setFontScale(O0);
        label23.setFontScale(O0);
        label22.setFontScale(O0);
        label25.setFontScale(O0);
        label12.setFontScale(O0);
        label13.setFontScale(O0);
        label14.setFontScale(O0);
        label15.setFontScale(O0);
        label16.setFontScale(O0);
        label17.setFontScale(O0);
        label18.setFontScale(O0);
        label3.setFontScale(O0);
        tVar.getLabel().setFontScale(O0);
        for (int i2 = 0; i2 < 20; i2++) {
            this.f3503q0[i2] = new o0.i();
        }
        for (int i3 = 0; i3 < 20; i3++) {
            this.f3506s0[i3] = new o0.i();
        }
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        if (ExiledKingdoms.f3378h) {
            setWidth(1024.0f);
            setHeight(576.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
    }

    public void D() {
        this.f3472b = new Table();
        this.f3474c = new Table();
        this.f3476d = new Table();
        this.f3480f = new Table();
        this.f3482g = new Table();
        new Table();
        this.f3484h = new Table();
        this.f3488j = new Table();
        this.f3490k = new Table();
        this.f3492l = new o0.k();
        this.f3494m = new Table();
        this.f3496n = new Table();
    }

    /* JADX INFO: Access modifiers changed from: public */
    public void L() {
        if (this.f3477d0.isDisabled() || V0 != 1) {
            return;
        }
        if (L0 == 0) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameLevelData.d(new Loot(GameData.v().player.f3092x + 32, GameData.v().player.f3093y + 32, R()));
            GameAssets.o("item2");
            this.f3498o.sheet.getClass();
            GameData.v().backpack.s(W0);
        }
        if (L0 == 1) {
            this.f3498o.sheet.getClass();
            int iL = GameData.v().backpack.l(R());
            if (iL == 1) {
                M(1);
            } else {
                new a(iL).show(getStage());
            }
        }
        if (R() == 0) {
            V(0, 0);
        }
        X();
    }

    /* JADX INFO: Access modifiers changed from: public */
    public void M(int i2) {
        int iR = R();
        int i3 = 1;
        if (K0.getType() == Lootable.LootableType.SHOP) {
            GameData.v().player.B1((P(iR) / 4) * i2);
            while (i3 <= i2) {
                GameData.v().backpack.q(iR);
                i3++;
            }
        } else {
            while (i3 <= i2) {
                if (K0.i(iR)) {
                    GameData.v().backpack.q(iR);
                }
                i3++;
            }
        }
        X();
    }

    public void N() {
        Lootable lootable;
        this.f3496n.clear();
        this.f3496n.row().center().padRight(Q0).padBottom(Q0).padTop(Q0 * 2.0f).colspan(4);
        Table table = new Table();
        table.row().center();
        table.add(this.U).height(N0 * 48.0f).width(N0 * 48.0f).right();
        table.add(this.Q).left().padLeft(Q0);
        this.f3496n.add(table).center();
        this.f3496n.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        Table table2 = this.f3496n;
        o0.i[] iVarArr = this.f3506s0;
        table2.add(iVarArr[0], iVarArr[1], iVarArr[2], iVarArr[3]);
        this.f3496n.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3496n.add(iVarArr[4], iVarArr[5], iVarArr[6], iVarArr[7]);
        this.f3496n.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3496n.add(iVarArr[8], iVarArr[9], iVarArr[10], iVarArr[11]);
        this.f3496n.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3496n.add(iVarArr[12], iVarArr[13], iVarArr[14], iVarArr[15]);
        this.f3496n.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3496n.add(iVarArr[16], iVarArr[17], iVarArr[18], iVarArr[19]);
        if (L0 == 1 && (lootable = K0) != null && lootable.getName().contains("vault")) {
            this.f3496n.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left().padTop(Q0 * 2.0f);
            ImageButton imageButton = new ImageButton(GameAssets.a(Assets.a("vault")));
            ImageButton imageButton2 = new ImageButton(GameAssets.a(Assets.a("vault")));
            ImageButton imageButton3 = new ImageButton(GameAssets.a(Assets.a("vault")));
            ImageButton imageButton4 = new ImageButton(GameAssets.a(Assets.a("vault")));
            if (GameData.v().hasVault) {
                imageButton.setStyle(GameAssets.a(Assets.a("vault_active")));
            } else {
                imageButton.setStyle(GameAssets.a(Assets.a("vault")));
            }
            if (GameData.v().hasVault2) {
                imageButton2.setStyle(GameAssets.a(Assets.a("vault_active")));
            } else {
                imageButton2.setStyle(GameAssets.a(Assets.a("vault")));
            }
            if (GameData.v().hasVault3) {
                imageButton3.setStyle(GameAssets.a(Assets.a("vault_active")));
            } else {
                imageButton3.setStyle(GameAssets.a(Assets.a("vault")));
            }
            if (GameData.v().hasVault4) {
                imageButton4.setStyle(GameAssets.a(Assets.a("vault_active")));
            } else {
                imageButton4.setStyle(GameAssets.a(Assets.a("vault")));
            }
            if (K0.getName().equals("vault")) {
                imageButton.setColor(1.0f, 0.3f, 0.3f, 1.0f);
            }
            if (K0.getName().equals("vault2")) {
                imageButton2.setColor(1.0f, 0.3f, 0.3f, 1.0f);
            }
            if (K0.getName().equals("vault3")) {
                imageButton3.setColor(1.0f, 0.3f, 0.3f, 1.0f);
            }
            if (K0.getName().equals("vault4")) {
                imageButton4.setColor(1.0f, 0.3f, 0.3f, 1.0f);
            }
            imageButton.addListener(new b());
            imageButton2.addListener(new c());
            imageButton3.addListener(new d());
            imageButton4.addListener(new e());
            this.f3496n.add(imageButton, imageButton2, imageButton3, imageButton4);
        }
        this.f3496n.row().expandX().center().pad(Q0 * 2.0f).height(N0 * 50.0f);
        this.f3496n.add(this.t0).width(N0 * 50.0f).height(N0 * 50.0f).right();
        this.f3496n.add(this.u0);
        this.f3496n.add(this.v0).colspan(2);
        this.f3496n.row().expand().bottom().colspan(4).pad(Q0 * 2.0f);
        Table table3 = this.f3496n;
        n0.t tVar = this.x0;
        table3.add(tVar).align(4).align(8).width(U0).width(N0 * 196.0f).height(N0 * 50.0f);
        if (ExiledKingdoms.f3378h) {
            tVar.e(Settings.f3419b.interact);
        }
        tVar.clearListeners();
        tVar.d(false);
        tVar.addListener(new C0042f());
        this.v0.clearListeners();
        this.v0.d(false);
        this.v0.addListener(new g());
    }

    public static int P(int i2) {
        if (M0 == 0.0f || i2 == 0) {
            return 0;
        }
        return (int) (Rules.f(i2).value * M0);
    }

    public static String Q(int i2) {
        String str = i2 < 0 ? "" : "+";
        if (i2 <= -10 || i2 >= 10) {
            return str + i2 + " ";
        }
        return str + i2 + "   ";
    }

    /* JADX INFO: Access modifiers changed from: public */
    public int R() {
        int i2 = V0;
        if (i2 == 1) {
            this.f3498o.sheet.getClass();
            return GameData.v().backpack.j(W0).itemID;
        }
        if (i2 == 2) {
            return this.f3498o.sheet.w(W0);
        }
        if (i2 == 4) {
            return K0.getItem(W0);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: public */
    public void V(int i2, int i3) {
        int i4 = V0;
        o0.i[] iVarArr = this.f3503q0;
        if (i4 == 1) {
            iVarArr[W0].f3540j = false;
        }
        o0.i iVar = this.f3499o0;
        o0.i iVar2 = this.f3497n0;
        o0.i iVar3 = this.f3495m0;
        o0.i iVar4 = this.f3493l0;
        o0.i iVar5 = this.f3491k0;
        o0.i iVar6 = this.f3489j0;
        o0.i iVar7 = this.f3487i0;
        o0.i iVar8 = this.f3485h0;
        o0.i iVar9 = this.f3483g0;
        o0.i iVar10 = this.f3481f0;
        o0.i iVar11 = this.f3479e0;
        o0.i iVar12 = this.f3501p0;
        if (i4 == 2) {
            iVar11.f3540j = false;
            iVar10.f3540j = false;
            iVar9.f3540j = false;
            iVar8.f3540j = false;
            iVar7.f3540j = false;
            iVar6.f3540j = false;
            iVar5.f3540j = false;
            iVar4.f3540j = false;
            iVar3.f3540j = false;
            iVar2.f3540j = false;
            iVar.f3540j = false;
            iVar12.f3540j = false;
        }
        o0.i[] iVarArr2 = this.f3506s0;
        if (i4 == 4) {
            iVarArr2[W0].f3540j = false;
        }
        V0 = i2;
        W0 = i3;
        if (i2 == 1) {
            iVarArr[i3].f3540j = true;
        }
        if (i2 == 2) {
            switch (i3) {
                case 0:
                    iVar11.f3540j = true;
                    break;
                case 1:
                    iVar10.f3540j = true;
                    break;
                case 2:
                    iVar9.f3540j = true;
                    break;
                case 3:
                    iVar8.f3540j = true;
                    break;
                case 4:
                    iVar7.f3540j = true;
                    break;
                case 5:
                    iVar6.f3540j = true;
                    break;
                case 6:
                    iVar3.f3540j = true;
                    break;
                case 7:
                    iVar12.f3540j = true;
                    break;
                case 8:
                    iVar2.f3540j = true;
                    break;
                case 9:
                    iVar.f3540j = true;
                    break;
                case 10:
                    iVar5.f3540j = true;
                    break;
                case 11:
                    iVar4.f3540j = true;
                    break;
            }
        }
        if (i2 == 4) {
            iVarArr2[i3].f3540j = true;
        }
        iVar12.f3541k = false;
        if (this.f3498o.sheet.N().twohanded) {
            iVar12.f3541k = true;
        }
    }

    static boolean a(f fVar) {
        if (!ExiledKingdoms.f3378h) {
            fVar.getClass();
            return false;
        }
        if (fVar.H0 + 0.25f >= GameData.v().realTime && Math.abs(Gdx.input.getX() - fVar.I0) < 14 && Math.abs(Gdx.input.getY() - fVar.J0) < 14) {
            fVar.H0 = 0.0f;
            return true;
        }
        fVar.H0 = (float) GameData.v().realTime;
        fVar.I0 = Gdx.input.getX();
        fVar.J0 = Gdx.input.getY();
        return false;
    }

    static /* synthetic */ int t(f fVar, int i2) {
        fVar.getClass();
        return P(i2);
    }

    public final void B() {
        int i2;
        int iL;
        int i3;
        Item itemF;
        if (this.f3475c0.isDisabled()) {
            return;
        }
        float fA = SkillActions.a(GameData.v().player.sheet);
        int i4 = V0;
        Item.ItemType itemType = Item.ItemType.CLOAK;
        Item.ItemType itemType2 = Item.ItemType.NECKLACE;
        Item.ItemType itemType3 = Item.ItemType.SHIELD;
        Item.ItemType itemType4 = Item.ItemType.BELT;
        Item.ItemType itemType5 = Item.ItemType.RING;
        Item.ItemType itemType6 = Item.ItemType.ARMOR_LEGS;
        Item.ItemType itemType7 = Item.ItemType.ARMOR_HEAD;
        Item.ItemType itemType8 = Item.ItemType.ARMOR_FEET;
        Item.ItemType itemType9 = Item.ItemType.ARMOR_CHEST;
        Item.ItemType itemType10 = Item.ItemType.ARMOR_ARMS;
        Item.ItemType itemType11 = Item.ItemType.WEAPON;
        if (i4 == 1 && (itemF = Rules.f(R())) != null) {
            Item.ItemType itemType12 = itemF.type;
            if (itemType12 == itemType10 || itemType12 == itemType9 || itemType12 == itemType8 || itemType12 == itemType7 || itemType12 == itemType6 || itemType12 == itemType5 || itemType12 == itemType4 || itemType12 == itemType11 || itemType12 == itemType3 || itemType12 == itemType2 || itemType12 == itemType) {
                if (itemType12 == itemType11) {
                    GameAssets.o("sword");
                    CharacterEffects characterEffects = this.f3498o.sheet.effects;
                    Boolean bool = Boolean.FALSE;
                    characterEffects.stab = bool;
                    characterEffects.stabBonus = 0;
                    characterEffects.disintegrate = bool;
                    characterEffects.disintegrateBonus = 0;
                } else {
                    GameAssets.o("item");
                }
                this.f3498o.sheet.e(W0);
                if (itemF.traitBonus != null) {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= 6) {
                            break;
                        }
                        if (itemF.traitBonus[i5] == 0) {
                            i5++;
                        } else if (!GameData.v().TIP_ITEM_ATTRIBUTE) {
                            GameData.v().TIP_ITEM_ATTRIBUTE = true;
                            new i(GameString.b("HELP_SECTION_ATTRIBUTE_ITEM", false), GameString.b("HELP_ATTRIBUTE_ITEM", false), Assets.a("trait_agility")).show(n0.z.v().a());
                        } else if (this.f3498o.sheet.inventory.p()) {
                            new j(GameString.b("MSG_ITEM_NOT_STACK", false), 0).show(n0.z.v().a());
                        }
                    }
                }
                V(0, 0);
                X();
                return;
            }
            if (itemF.g(this.f3498o.sheet)) {
                if (!this.f3498o.F0()) {
                    new k(GameData.v().n(), 0).show(n0.z.v().a());
                    return;
                }
                this.f3498o.lastItemUsed = GameData.v().u();
                if (itemF.type == Item.ItemType.POTION) {
                    GameAssets.o("potion");
                } else {
                    GameAssets.o("buff1");
                }
                itemF.OnUse.a();
                int i6 = itemF.manaCost;
                if (i6 > 0) {
                    this.f3498o.sheet.j0(i6);
                    k0.a aVarL = k0.a.l();
                    Character character = this.f3498o;
                    aVarL.getClass();
                    aVarL.b(character.B(), a.EnumC0031a.CASTING, 1.0f).owner = character;
                }
                X();
                return;
            }
        }
        int i7 = V0;
        if (i7 != 2) {
            if (i7 == 4 && !this.f3475c0.isDisabled()) {
                if (K0.getType() == Lootable.LootableType.SHOP) {
                    int iP = (int) (fA * P(R()));
                    int iH = GameData.v().player.h();
                    if (iH >= iP) {
                        if (iH >= iP * 2 && Rules.f(R()).stackable) {
                            new l(Math.min(iH / iP, 20)).show(getStage());
                        } else if (this.f3498o.A1(R())) {
                            GameData.v().player.R1(iP);
                            GameAssets.o("coin");
                        }
                    }
                    X();
                    return;
                }
                if (Rules.n(R()).booleanValue()) {
                    iL = K0.j().l(R());
                    i2 = 1;
                } else {
                    i2 = 1;
                    iL = 1;
                }
                if (iL != i2) {
                    new m(iL).show(getStage());
                } else if (this.f3498o.A1(R())) {
                    K0.removeItem(W0);
                    V(0, 0);
                    GameAssets.o("item2");
                }
            }
            X();
            return;
        }
        CharacterSheet characterSheet = this.f3498o.sheet;
        int iR = R();
        int i8 = W0;
        boolean z2 = i8 == 7 || i8 == 10;
        CharacterInventory characterInventory = characterSheet.inventory;
        characterInventory.getClass();
        if (!((iR == 0) | (GameData.v().backpack.f() == -1))) {
            Item itemF2 = Rules.f(iR);
            GameData.v().backpack.a(iR, 1);
            Item.ItemType itemType13 = itemF2.type;
            if (itemType13 != itemType11 || z2) {
                i3 = 0;
            } else {
                i3 = 0;
                characterInventory.slot_mainhand = 0;
            }
            if (itemType13 == itemType11 && z2) {
                characterInventory.slot_offhand = i3;
            }
            if (itemType13 == itemType9) {
                characterInventory.slot_body = i3;
            }
            if (itemType13 == itemType10) {
                characterInventory.slot_hands = i3;
            }
            if (itemType13 == itemType8) {
                characterInventory.slot_feet = i3;
            }
            if (itemType13 == itemType6) {
                characterInventory.slot_legs = i3;
            }
            if (itemType13 == itemType7) {
                characterInventory.slot_head = i3;
            }
            if (itemType13 == itemType3) {
                characterInventory.slot_offhand = i3;
            }
            if (itemType13 == itemType5 && !z2) {
                characterInventory.slot_ring = i3;
            }
            if (itemType13 == itemType5 && z2) {
                characterInventory.slot_ring2 = i3;
            }
            if (itemType13 == itemType) {
                characterInventory.slot_cloak = i3;
            }
            if (itemType13 == itemType4) {
                characterInventory.slot_belt = i3;
            }
            if (itemType13 == itemType2) {
                characterInventory.slot_necklace = i3;
            }
            characterInventory.s();
        }
        if (characterSheet.X()) {
            l1.f2768r = characterSheet.N().ranged;
        }
        V(0, 0);
        X();
        GameAssets.o("item2");
    }

    public final boolean C() {
        return isVisible() && this.x0.isVisible() && V0 == 0;
    }

    public final void E() {
        Lootable lootable;
        setVisible(false);
        o0.m.g().setVisible(false);
        GameLevel.n(false);
        n0.z.v().r();
        V(0, 0);
        System.gc();
        if (L0 == 1 && (lootable = K0) != null && lootable.f()) {
            new h(GameString.b("WARNING_CONTAINER", false), 0).show(n0.z.v().a());
        }
    }

    public final void F() {
        if (!this.f3475c0.isVisible() || this.f3475c0.isDisabled()) {
            return;
        }
        B();
    }

    public final void G() {
        if (!this.f3477d0.isVisible() || this.f3477d0.isDisabled()) {
            return;
        }
        L();
    }

    public final void H() {
        int i2;
        int i3 = V0;
        if (i3 == 0) {
            V(1, 0);
            return;
        }
        if ((i3 == 1 || i3 == 4) && (i2 = W0) <= 15) {
            V(i3, i2 + 4);
        } else if (i3 == 2) {
            int i4 = W0;
            if (i4 == 0) {
                V(2, 7);
            } else if (i4 == 1) {
                V(2, 3);
            } else if (i4 == 2) {
                V(2, 4);
            } else if (i4 == 5) {
                V(2, 11);
            } else if (i4 == 6) {
                V(2, 5);
            } else if (i4 == 7) {
                V(2, 10);
            } else if (i4 == 9) {
                V(2, 6);
            } else if (i4 == 10) {
                V(2, 8);
            }
        }
        X();
    }

    public final void I() {
        int i2 = V0;
        if (i2 == 0) {
            V(1, 0);
            return;
        }
        if (i2 == 1) {
            int i3 = W0;
            if (i3 % 4 != 0) {
                V(i2, i3 - 1);
            } else if (i3 == 0) {
                V(2, 9);
            } else if (i3 == 4) {
                V(2, 6);
            } else if (i3 == 8) {
                V(2, 5);
            } else if (i3 == 12) {
                V(2, 11);
            }
        } else if (i2 == 2) {
            int i4 = W0;
            if (i4 == 9) {
                V(2, 2);
            } else if (i4 != 11) {
                switch (i4) {
                    case 1:
                        V(2, 0);
                        break;
                    case 2:
                        V(2, 1);
                        break;
                    case 3:
                        V(2, 8);
                        break;
                    case 4:
                        V(2, 3);
                        break;
                    case 5:
                        V(2, 10);
                        break;
                    case 6:
                        V(2, 7);
                        break;
                    default:
                        int i5 = L0;
                        if (i5 == 1 || i5 == 2) {
                            if (i4 == 0) {
                                V(4, 3);
                            } else if (i4 == 10) {
                                V(4, 11);
                            } else if (i4 == 7) {
                                V(4, 7);
                            } else if (i4 == 8) {
                                V(4, 15);
                            }
                        }
                        break;
                }
            } else {
                V(2, 4);
            }
        } else if (i2 == 4) {
            int i6 = W0;
            if (i6 % 4 != 0) {
                V(i2, i6 - 1);
            }
        }
        X();
    }

    public final void J() {
        int i2 = V0;
        if (i2 == 0) {
            V(1, 0);
            return;
        }
        if (i2 == 1) {
            int i3 = W0 + 1;
            if (i3 % 4 != 0) {
                V(i2, i3);
            }
        } else if (i2 == 2) {
            switch (W0) {
                case 0:
                    V(2, 1);
                    break;
                case 1:
                    V(2, 2);
                    break;
                case 2:
                    V(2, 9);
                    break;
                case 3:
                    V(2, 4);
                    break;
                case 4:
                    V(2, 11);
                    break;
                case 5:
                    V(1, 8);
                    break;
                case 6:
                    V(1, 4);
                    break;
                case 7:
                    V(2, 6);
                    break;
                case 8:
                    V(2, 3);
                    break;
                case 9:
                    V(1, 0);
                    break;
                case 10:
                    V(2, 5);
                    break;
                case 11:
                    V(1, 12);
                    break;
            }
        } else if (i2 == 4) {
            int i4 = W0;
            int i5 = i4 + 1;
            if (i5 % 4 != 0) {
                V(i2, i5);
            } else if (i4 == 3) {
                V(2, 0);
            } else if (i4 == 7) {
                V(2, 7);
            } else if (i4 == 11) {
                V(2, 10);
            } else if (i4 == 15) {
                V(2, 8);
            }
        }
        X();
    }

    public final void K() {
        int i2;
        int i3 = V0;
        if (i3 == 0) {
            V(1, 0);
            return;
        }
        if ((i3 != 1 && i3 != 4) || (i2 = W0) < 4) {
            if (i3 == 2) {
                switch (W0) {
                    case 3:
                        V(2, 1);
                        break;
                    case 4:
                        V(2, 2);
                        break;
                    case 5:
                        V(2, 6);
                        break;
                    case 6:
                        V(2, 9);
                        break;
                    case 7:
                        V(2, 0);
                        break;
                    case 8:
                        V(2, 10);
                        break;
                    case 10:
                        V(2, 7);
                        break;
                    case 11:
                        V(2, 5);
                        break;
                }
            }
        } else {
            V(i3, i2 - 4);
        }
        X();
    }

    public final Character O() {
        if (isVisible()) {
            return this.f3498o;
        }
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        return GameData.v().player;
    }

    public final void S(int i2, int i3) {
        V(i2, i3);
    }

    public final void T(int i2, Lootable lootable) {
        U(i2, lootable, GameData.v().player);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0b47  */
    /* JADX WARN: Type inference failed for: r1v191 */
    /* JADX WARN: Type inference failed for: r1v279, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v283 */
    /* JADX WARN: Type inference failed for: r1v292 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void U(int i2, Lootable lootable, Character character) {
        boolean z2;
        o0.i[] iVarArr = this.f3503q0;
        n0.t tVar = this.f3473b0;
        z zVar = this.f3478e;
        Image image = this.f3471a0;
        this.f3498o = character;
        L0 = i2;
        V0 = 0;
        W0 = 0;
        M0 = 0.0f;
        this.S = new o0.d(this.f3498o);
        if (L0 == 1 && lootable != null) {
            K0 = lootable;
            lootable.j().g();
        }
        D();
        this.C0 = new ImageButton(GameAssets.a(GameAssets.f3358z));
        ImageButton imageButton = new ImageButton(GameAssets.a(GameAssets.B));
        this.D0 = imageButton;
        imageButton.setVisible(false);
        if (L0 == 0 && this.f3498o.q() == 1 && GameData.v().A()) {
            this.D0.setVisible(true);
            this.D0.setColor(Color.ORANGE);
        }
        if (GameData.v().party.j() && L0 == 0) {
            this.C0.setVisible(true);
            if (this.f3498o.q() == 1) {
                this.C0.setStyle(GameAssets.a(GameData.v().party.f().E()));
            } else {
                this.C0.setStyle(GameAssets.a(GameData.v().player.E()));
            }
            this.C0.setColor(Color.ORANGE);
        } else {
            this.C0.setVisible(false);
        }
        Label label = this.s;
        label.setText(GameString.b("DAMAGE", false));
        Label label2 = this.f3507t;
        label2.setText(GameString.b("SPEED", false));
        Label label3 = this.f3509v;
        label3.setText(GameString.b("CRITICAL", false));
        Label label4 = this.f3510w;
        label4.setText(GameString.b("DPS", false));
        Label label5 = this.A;
        label5.setText(GameString.b("EFFECT", false));
        Label label6 = this.C;
        label6.setText(GameString.b("RESISTANCES", false));
        Label label7 = this.L;
        label7.setText(GameString.b("ARMOR", false));
        Label label8 = this.N;
        label8.setText(GameString.b("BACKPACK", false));
        this.O.setText(GameString.b("EQUIPMENT", false));
        this.f3475c0 = new n0.t(GameString.b("EQUIP", false), Assets.g(), "menuButton");
        this.f3477d0 = new n0.t(GameString.b("DROP", false), Assets.g(), "menuButton");
        this.v0 = new n0.t(GameString.b("TAKE", false), Assets.g(), "menuButton");
        n0.t tVar2 = this.w0;
        tVar2.getLabel().setText(GameString.b("BACK", false));
        n0.t tVar3 = this.x0;
        tVar3.getLabel().setText(GameString.b("TAKE_ALL", false));
        n0.t tVar4 = this.y0;
        tVar4.getLabel().setText(GameString.b("JOURNAL", false));
        n0.t tVar5 = this.z0;
        tVar5.getLabel().setText(GameString.b("SKILLS", false));
        n0.t tVar6 = this.A0;
        tVar6.getLabel().setText(GameString.b("REPUTATION", false));
        n0.t tVar7 = this.B0;
        tVar7.getLabel().setText(GameString.b("STAT_DETAILS", false));
        tVar7.setDisabled(false);
        Image image2 = this.V;
        image2.setDrawable(new TextureRegionDrawable(Assets.a("fire")));
        Image image3 = this.W;
        image3.setDrawable(new TextureRegionDrawable(Assets.a("cold")));
        Image image4 = this.X;
        image4.setDrawable(new TextureRegionDrawable(Assets.a("shock")));
        Image image5 = this.Y;
        image5.setDrawable(new TextureRegionDrawable(Assets.a("death")));
        Image image6 = this.Z;
        image6.setDrawable(new TextureRegionDrawable(Assets.a("poison")));
        image.setDrawable(new TextureRegionDrawable(Assets.a("holy")));
        this.f3474c.clear();
        this.f3474c.center().top();
        this.f3474c.defaults().pad(Q0);
        this.f3474c.row().center();
        this.f3474c.add(this.f3502q).center();
        this.f3474c.row();
        this.f3474c.add(this.E0).width(N0 * 180.0f).height(N0 * 25.0f);
        this.f3474c.row();
        this.f3474c.add(this.F0).width(N0 * 180.0f).height(N0 * 25.0f);
        this.f3474c.row();
        this.f3474c.add(this.G0).width(N0 * 180.0f).height(N0 * 25.0f);
        zVar.d(this.f3498o.sheet);
        this.f3476d.clear();
        this.f3476d.left();
        this.f3476d.defaults().pad(Q0 / 2.0f);
        this.f3476d.row().center();
        this.f3476d.add(this.f3504r).center().colspan(2).padTop(0.0f);
        this.f3476d.row();
        this.f3476d.add(label);
        this.f3476d.add(this.J).center().padLeft(N0 * 20.0f);
        this.f3476d.row();
        this.f3476d.add(label2, this.f3508u);
        this.f3476d.row();
        this.f3476d.add(label3, this.K);
        this.f3476d.row();
        this.f3476d.add(label4, this.f3511z);
        this.f3476d.row();
        this.f3476d.add(label5);
        this.f3476d.add(this.B).width(N0 * 90.0f).align(8);
        this.f3480f.clear();
        this.f3480f.left();
        this.f3480f.defaults().pad(Q0 / 2.0f);
        this.f3480f.row().center();
        this.f3480f.add(label6).center().colspan(6);
        this.f3480f.row();
        this.f3480f.add(image2).width(N0 * 32.0f).height(N0 * 32.0f).center();
        this.f3480f.add(this.D).left();
        this.f3480f.add(image3).width(N0 * 32.0f).height(N0 * 32.0f).center();
        this.f3480f.add(this.E);
        this.f3480f.add(image4).width(N0 * 32.0f).height(N0 * 32.0f).center();
        this.f3480f.add(this.F);
        this.f3480f.row();
        this.f3480f.add(image5).width(N0 * 32.0f).height(N0 * 32.0f).center();
        this.f3480f.add(this.G);
        this.f3480f.add(image6).width(N0 * 32.0f).height(N0 * 32.0f).center();
        this.f3480f.add(this.H);
        this.f3480f.add(image).width(N0 * 32.0f).height(N0 * 32.0f).center();
        this.f3480f.add(this.I);
        this.f3482g.clear();
        this.f3482g.center().top();
        this.f3482g.defaults().pad(Q0);
        this.f3482g.row().center();
        this.f3482g.add(label7);
        this.f3482g.row().center();
        this.f3482g.add(this.M).height(N0 * 64.0f).width(N0 * 64.0f);
        this.f3484h.clear();
        this.f3484h.center().top();
        this.f3484h.defaults().pad(0.0f);
        this.f3484h.row();
        this.f3484h.add(this.f3482g).left().padRight(N0 * 35.0f).width(N0 * 64.0f);
        this.f3484h.add(this.f3480f).right().padRight(0.0f);
        this.f3472b.clear();
        this.f3472b.center().top();
        this.f3472b.defaults().pad(Q0).padBottom(0.0f);
        this.f3472b.columnDefaults(0).align(8);
        this.f3472b.columnDefaults(1).align(1);
        this.f3472b.row().colspan(2).padTop(Q0);
        this.f3472b.add(this.f3500p).center();
        this.f3472b.row().align(1).spaceTop(N0 * 8.0f);
        this.f3472b.add(this.R).width(S0).height(S0);
        this.f3472b.add(this.f3474c);
        Table table = this.f3486i;
        table.clear();
        table.add(zVar).width(N0 * 120.0f).padRight(N0 * 6.0f).height(N0 * 210.0f);
        table.add(this.f3476d).expandX().width(N0 * 230.0f).spaceLeft(N0 * 15.0f);
        this.f3472b.row().center().colspan(2).pad(0.0f).spaceTop(N0 * 10.0f);
        this.f3472b.add(table);
        this.f3472b.row().center().colspan(2).spaceTop(N0 * 10.0f);
        this.f3472b.add(this.f3484h);
        this.f3472b.row().space(N0 * 4.0f).spaceTop(0.0f).padTop(0.0f).spaceTop(N0 * 24.0f);
        this.f3472b.add(tVar4).height(N0 * 50.0f).width(N0 * 185.0f).bottom().center();
        this.f3472b.add(tVar5).height(N0 * 50.0f).width(N0 * 185.0f).bottom().center();
        this.f3472b.row().space(N0 * 4.0f);
        this.f3472b.add(tVar6).height(N0 * 50.0f).width(N0 * 185.0f).bottom().center();
        this.f3472b.add(tVar7).height(N0 * 50.0f).width(N0 * 185.0f).bottom().center();
        if (Settings.h() == 2) {
            tVar5.getLabel().setFontScale(O0 * 0.9f);
            tVar4.getLabel().setFontScale(O0 * 0.9f);
            tVar6.getLabel().setFontScale(O0 * 0.9f);
            tVar7.getLabel().setFontScale(O0 * 0.9f);
            label7.setFontScale(O0 * 0.8f);
        } else if (ExiledKingdoms.f3378h) {
            tVar5.getLabel().setFontScale(O0 * 0.99f);
            tVar4.getLabel().setFontScale(O0 * 0.99f);
            tVar6.getLabel().setFontScale(O0 * 0.99f);
            tVar7.getLabel().setFontScale(O0 * 0.99f);
            label7.setFontScale(O0 * 0.99f);
            tVar2.getLabel().setFontScale(O0 * 0.99f);
            this.f3475c0.getLabel().setFontScale(O0 * 0.99f);
            this.f3477d0.getLabel().setFontScale(O0 * 0.99f);
        }
        N();
        this.f3490k.clear();
        this.f3490k.setBackground(new NinePatchDrawable(GameAssets.P));
        this.f3490k.center().top();
        this.f3490k.columnDefaults(0).align(1).align(2);
        this.f3490k.row().width(R0).height(R0).pad(Q0 / 2.0f).top();
        this.f3490k.add(this.f3479e0, this.f3481f0, this.f3483g0, this.f3499o0);
        this.f3490k.row();
        Table table2 = new Table();
        table2.pad(Q0 / 2.0f);
        table2.add(this.f3501p0).height(R0).width(R0);
        table2.row().spaceTop(Q0);
        table2.add(this.f3491k0).height(R0).width(R0);
        this.f3490k.add(table2);
        this.f3490k.add(this.S).width(T0).height(T0).colspan(2).center().bottom().padRight(N0 * 15.0f);
        Table table3 = new Table();
        table3.pad(Q0 / 2.0f);
        table3.add(this.f3495m0).height(R0).width(R0);
        table3.row().spaceTop(Q0);
        table3.add(this.f3489j0).height(R0).width(R0);
        this.f3490k.add(table3);
        this.f3490k.row().width(R0).height(R0).pad(Q0 / 2.0f);
        this.f3490k.add(this.f3497n0, this.f3485h0, this.f3487i0, this.f3493l0);
        Table table4 = new Table();
        table4.row().height(R0).pad(Q0).center().expandX().fillX();
        table4.add(tVar).height(N0 * 40.0f).width(N0 * 160.0f).spaceRight(N0 * 50.0f).left().padLeft(0.0f).fill();
        Image image7 = this.f3505r0;
        table4.add(image7).width(N0 * 50.0f).height(N0 * 50.0f).right();
        table4.add(this.P).left();
        image7.setDrawable(new TextureRegionDrawable(Assets.a("goldcoins")));
        this.f3494m.clear();
        this.f3494m.row().center().colspan(4).padRight(Q0).padBottom(Q0 * 2.0f).padTop(Q0);
        this.f3494m.add(label8);
        this.f3494m.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3494m.add(iVarArr[0], iVarArr[1], iVarArr[2], iVarArr[3]);
        this.f3494m.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3494m.add(iVarArr[4], iVarArr[5], iVarArr[6], iVarArr[7]);
        this.f3494m.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3494m.add(iVarArr[8], iVarArr[9], iVarArr[10], iVarArr[11]);
        this.f3494m.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3494m.add(iVarArr[12], iVarArr[13], iVarArr[14], iVarArr[15]);
        this.f3494m.row().fill().expandX().width(R0).height(R0).padRight(Q0).padBottom(Q0).left();
        this.f3494m.add(iVarArr[16], iVarArr[17], iVarArr[18], iVarArr[19]);
        this.f3494m.row().height(R0).pad(Q0).left().colspan(4);
        this.f3494m.add(table4);
        Table table5 = new Table();
        table5.row().expand().bottom().right();
        table5.add(this.D0).width(N0 * 70.0f).pad(Q0).padRight(Q0 * 2.0f).height(N0 * 70.0f);
        table5.add(this.C0).width(N0 * 70.0f).pad(Q0).padRight(Q0 * 2.0f).height(N0 * 70.0f);
        table5.add(tVar2).width(P0 / 2.0f).pad(Q0).padRight(Q0 * 2.0f).height(N0 * 70.0f);
        this.f3494m.row().height(R0).pad(Q0).left().colspan(4);
        this.f3494m.add(table5);
        for (int i3 = 0; i3 < 20; i3++) {
            iVarArr[i3].f3540j = false;
        }
        int i4 = L0;
        if (i4 == 0) {
            z2 = 0;
            this.f3470a = this.f3472b;
        } else if (i4 == 1) {
            if (this.f3498o.sheet.skillSet.g("stealth") < 3) {
                this.f3498o.sheet.effects.stealth = Boolean.FALSE;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.sheet.skillSet.g("stealth") == 3 && FDUtils.b(1, 2) == 1) {
                GameData.v().player.sheet.effects.stealth = Boolean.FALSE;
            }
            if (lootable != null) {
                this.f3470a = this.f3496n;
                K0 = lootable;
                M0 = lootable.a();
                if (K0.getType() == Lootable.LootableType.SHOP || K0.g()) {
                    z2 = 0;
                    tVar3.setVisible(false);
                } else {
                    tVar3.setVisible(true);
                    z2 = 0;
                }
                K0.d();
                N();
                for (int i5 = z2 == true ? 1 : 0; i5 < 20; i5++) {
                    this.f3506s0[i5].f3540j = z2;
                }
            } else {
                z2 = 0;
            }
        }
        clear();
        top();
        row();
        columnDefaults(z2).width(P0);
        columnDefaults(1).width(P0);
        columnDefaults(2).width(P0);
        padTop(Q0 * 6.0f);
        add(this.f3470a).left().pad(Q0).padLeft(Q0 * 2.0f).align(2);
        add(this.f3488j).pad(Q0 / 2.0f).align(2);
        this.f3488j.row().colspan(2);
        this.f3488j.add(this.f3490k);
        this.f3488j.row().expandX().colspan(2);
        this.f3488j.add(this.f3492l).align(2).top();
        this.f3488j.row().center().expandX();
        this.f3488j.add(this.f3477d0).left().width(U0 * 1.4f);
        this.f3488j.add(this.f3475c0).right().width(U0 * 1.4f);
        add(this.f3494m).align(1).pad(Q0).padTop(Q0 * 3.0f).align(2).spaceLeft(N0 * 10.0f);
        this.f3475c0.align(1);
        this.f3475c0.getLabel().setAlignment(1);
        this.f3477d0.align(1);
        this.f3477d0.getLabel().setAlignment(1);
        row().colspan(3).expand().bottom().right().pad(Q0).padRight(Q0 * 2.0f);
        clearListeners();
        addListener(new o0.e(this, 3));
        tVar2.clearListeners();
        tVar2.d(false);
        tVar2.addListener(new o0.e(this, 4));
        this.D0.clearListeners();
        if (this.D0.isVisible()) {
            this.D0.addListener(new o0.e(this, 5));
        }
        if (this.C0.isVisible()) {
            this.C0.addListener(new o0.e(this, 6));
        }
        this.f3475c0.clearListeners();
        this.f3475c0.d(true);
        this.f3475c0.e(Settings.f3419b.invTake);
        this.f3475c0.addListener(new o0.e(this, 7));
        tVar4.clearListeners();
        int i6 = 0;
        tVar4.d(false);
        tVar4.addListener(new o0.g(i6));
        tVar5.clearListeners();
        tVar5.d(false);
        tVar5.addListener(new o0.e(this, 8));
        tVar6.clearListeners();
        tVar6.d(false);
        tVar6.addListener(new o0.g(1));
        tVar7.clearListeners();
        tVar7.d(false);
        tVar7.addListener(new o0.e(this, i6));
        tVar.clearListeners();
        tVar.d(false);
        tVar.addListener(new o0.e(this, 1));
        this.f3477d0.clearListeners();
        this.f3477d0.d(true);
        this.f3477d0.e(Settings.f3419b.invDrop);
        this.f3477d0.addListener(new o0.e(this, 2));
        setVisible(true);
        X();
        System.gc();
    }

    public final void W() {
        Lootable lootable = K0;
        if (lootable == null) {
            return;
        }
        lootable.b();
        V(0, 0);
        if (K0.isEmpty().booleanValue()) {
            E();
        }
        X();
    }

    public final void X() {
        String strSubstring;
        float fA = SkillActions.a(GameData.v().player.sheet);
        boolean zIsVisible = isVisible();
        o0.i iVar = this.f3501p0;
        if (zIsVisible) {
            CharacterSheet characterSheet = this.f3498o.sheet;
            characterSheet.d0();
            this.f3479e0.a(characterSheet.w(0));
            this.f3481f0.a(characterSheet.w(1));
            this.f3483g0.a(characterSheet.w(2));
            this.f3485h0.a(characterSheet.w(3));
            this.f3487i0.a(characterSheet.w(4));
            this.f3489j0.a(characterSheet.w(5));
            this.f3491k0.a(characterSheet.w(10));
            this.f3493l0.a(characterSheet.w(11));
            this.f3495m0.a(characterSheet.w(6));
            this.f3497n0.a(characterSheet.w(8));
            this.f3499o0.a(characterSheet.w(9));
            iVar.a(characterSheet.w(7));
            for (int i2 = 0; i2 < 20; i2++) {
                o0.i[] iVarArr = this.f3503q0;
                iVarArr[i2].a(GameData.v().backpack.j(i2).itemID);
                if (M0 != 0.0f) {
                    iVarArr[i2].b(P(GameData.v().backpack.j(i2).itemID) / 4);
                } else {
                    iVarArr[i2].b(0);
                }
                iVarArr[i2].c(GameData.v().backpack.j(i2).units);
            }
            this.P.setText("" + GameData.v().player.h());
            int i3 = V0;
            Lootable.LootableType lootableType = Lootable.LootableType.SHOP;
            if (i3 == 0 || R() == 0) {
                this.f3492l.setVisible(false);
                this.f3475c0.setVisible(false);
                this.f3477d0.setVisible(false);
            } else {
                this.f3492l.a(R());
                this.f3475c0.setDisabled(false);
                this.f3477d0.setDisabled(false);
                if (V0 == 1) {
                    if (Rules.l(R()).booleanValue()) {
                        this.f3475c0.setText(GameString.b("EQUIP", false));
                        this.f3475c0.setVisible(true);
                        if (Rules.m(R(), characterSheet).booleanValue()) {
                            this.f3475c0.setDisabled(false);
                        } else {
                            this.f3475c0.setDisabled(true);
                        }
                    } else {
                        int iR = R();
                        if ((iR != 0 ? Boolean.valueOf(Rules.f(iR).g(this.f3498o.sheet)) : Boolean.FALSE).booleanValue()) {
                            this.f3475c0.setText(GameString.b("USE", false));
                            this.f3475c0.setVisible(true);
                        } else {
                            this.f3475c0.setVisible(false);
                        }
                    }
                    if (L0 == 1) {
                        if (K0.getType() == lootableType) {
                            this.f3477d0.setText(GameString.b("SELL", false));
                            if (Rules.f(R()).value <= 0 || !((Shop) K0).m(R())) {
                                this.f3477d0.setDisabled(true);
                            }
                        } else {
                            this.f3477d0.setText(GameString.b("STORE_CONTAINER", false));
                            if (!K0.k(R()) || (Rules.f(R()).value <= 0 && !K0.g())) {
                                this.f3477d0.setDisabled(true);
                            }
                        }
                    }
                    if (L0 == 0) {
                        this.f3477d0.setText(GameString.b("DROP", false));
                        if (Rules.f(R()).value <= 0) {
                            this.f3477d0.setDisabled(true);
                        }
                    }
                    this.f3477d0.setVisible(true);
                }
                if (V0 == 4) {
                    if (K0.getType() == lootableType) {
                        this.f3475c0.setText(GameString.b("BUY", false));
                        this.f3475c0.setVisible(true);
                        this.f3477d0.setVisible(false);
                        if (P(R()) * fA > GameData.v().player.h()) {
                            this.f3475c0.setDisabled(true);
                        }
                    } else {
                        this.f3475c0.setText(GameString.b("TAKE", false));
                        this.f3475c0.setVisible(true);
                        this.f3477d0.setVisible(false);
                    }
                    if (!GameData.v().backpack.n(R())) {
                        this.f3475c0.setDisabled(true);
                    }
                }
                if (V0 == 2) {
                    this.f3475c0.setText(GameString.b("UNEQUIP", false));
                    this.f3475c0.setVisible(true);
                    this.f3477d0.setVisible(false);
                }
                this.f3492l.setVisible(true);
                this.T.setVisible(true);
            }
            this.f3475c0.setWidth(P0 / 3.0f);
            this.f3477d0.setWidth(P0 / 3.0f);
            if (L0 == 0) {
                this.R.setDrawable(new TextureRegionDrawable(this.f3498o.E()));
                this.f3500p.setText("" + this.f3498o.getName() + ", " + Rules.CharacterClass.a(this.f3498o.sheet.stats.c()));
                Label label = this.f3502q;
                StringBuilder sb = new StringBuilder();
                a.a.w("LEVEL", false, sb, " ");
                sb.append(characterSheet.stats.f());
                label.setText(sb.toString());
                String str = "" + FDUtils.q(characterSheet.stats.i()) + "/" + FDUtils.q(Rules.b(characterSheet.stats.f() + 1));
                n0.a aVar = this.E0;
                aVar.setText(str);
                int iB = Rules.b(this.f3498o.sheet.stats.f());
                aVar.a((this.f3498o.sheet.stats.i() - iB) / (Rules.b(this.f3498o.sheet.stats.f() + 1) - iB));
                this.J.a(this.f3498o.sheet, false, false);
                this.f3508u.setText("" + characterSheet.H());
                this.K.a(characterSheet);
                this.f3511z.setText(String.format("%.1f", Float.valueOf(characterSheet.q())));
                Label label2 = this.B;
                StringBuilder sb2 = new StringBuilder("");
                ArrayList<DamageEffect> arrayListA = SheetBonus.a(characterSheet, characterSheet.N().a());
                if (arrayListA.isEmpty()) {
                    strSubstring = "";
                } else {
                    strSubstring = "";
                    for (DamageEffect damageEffect : arrayListA) {
                        StringBuilder sbS = a.a.s(strSubstring);
                        sbS.append(damageEffect.toString());
                        sbS.append(",");
                        strSubstring = sbS.toString();
                    }
                    if (strSubstring.substring(strSubstring.length() - 1).equals(",")) {
                        strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                    }
                    if (strSubstring.length() > 0 && strSubstring.substring(0, 1).equals(",")) {
                        strSubstring = strSubstring.substring(1);
                    }
                }
                sb2.append(strSubstring);
                label2.setText(sb2.toString());
                this.D.setText(Q(characterSheet.F(CharacterResistances.ResistanceType.Fire)));
                this.E.setText(Q(characterSheet.F(CharacterResistances.ResistanceType.Cold)));
                this.F.setText(Q(characterSheet.F(CharacterResistances.ResistanceType.Shock)));
                this.G.setText(Q(characterSheet.F(CharacterResistances.ResistanceType.Death)));
                this.H.setText(Q(characterSheet.F(CharacterResistances.ResistanceType.Toxic)));
                this.I.setText(Q(characterSheet.F(CharacterResistances.ResistanceType.Spirit)));
                String str2 = "" + characterSheet.o() + "/" + characterSheet.z();
                n0.a aVar2 = this.F0;
                aVar2.setText(str2);
                aVar2.a(characterSheet.o() / characterSheet.z());
                String str3 = "" + characterSheet.p() + "/" + characterSheet.A();
                n0.a aVar3 = this.G0;
                aVar3.setText(str3);
                aVar3.a(characterSheet.p() / characterSheet.A());
                aVar3.setVisible(this.f3498o.h1());
                this.M.a(characterSheet.j());
                String strB = GameString.b("SKILLS", false);
                if (characterSheet.L() > 0) {
                    StringBuilder sbT = a.a.t(strB, " [CYAN](");
                    sbT.append(characterSheet.L());
                    sbT.append(")");
                    strB = sbT.toString();
                }
                this.z0.getLabel().setText(strB);
                z zVar = this.f3478e;
                zVar.d(characterSheet);
                zVar.c(n0.z.v().W);
            }
            if (L0 == 1) {
                this.Q.setText(K0.getName());
                this.U.setDrawable(new TextureRegionDrawable(K0.l()));
                for (int i4 = 0; i4 < 20; i4++) {
                    o0.i[] iVarArr2 = this.f3506s0;
                    iVarArr2[i4].a(K0.getItem(i4));
                    if (M0 != 0.0f) {
                        iVarArr2[i4].b((int) (P(K0.getItem(i4)) * fA));
                    } else {
                        iVarArr2[i4].b(0);
                    }
                    iVarArr2[i4].c(K0.j().j(i4).units);
                }
                int iH = K0.h();
                Label label3 = this.u0;
                Image image = this.t0;
                if (iH <= 0 || K0.getType() == lootableType) {
                    image.setVisible(false);
                    label3.setVisible(false);
                    this.v0.setVisible(false);
                } else {
                    image.setDrawable(new TextureRegionDrawable(Assets.a("goldcoins")));
                    label3.setText("" + K0.h());
                    image.setVisible(true);
                    label3.setVisible(true);
                    this.v0.setVisible(true);
                }
                K0.getClass();
            }
        }
        int iQ = this.f3498o.q();
        n0.t tVar = this.f3473b0;
        n0.t tVar2 = this.y0;
        n0.t tVar3 = this.A0;
        if (iQ == 1 && L0 == 0) {
            tVar3.setVisible(true);
            tVar2.setVisible(true);
            tVar.setDisabled(false);
        } else {
            tVar3.setVisible(false);
            tVar2.setVisible(false);
            tVar.setDisabled(true);
        }
        iVar.f3541k = false;
        if (this.f3498o.sheet.N().twohanded) {
            iVar.f3541k = true;
        }
    }
}
