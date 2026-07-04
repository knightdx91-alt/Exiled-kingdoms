package l0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;
import n0.c1;
import n0.i1;
import n0.j1;
import n0.k1;
import n0.o1;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.PlayerCreation;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.GPGSUpdate;
import net.fdgames.ek.Settings;
import net.fdgames.ek.android.MainActivity;
import o0.a0;
import o0.t;
import q0.v;
import q0.x;
import q0.z;

/* JADX INFO: compiled from: MainMenuScreen.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class e implements com.badlogic.gdx.n {
    private static t A;
    public static o0.r B;
    public static p0.a C;
    private static o1 D;
    private static float E = Gdx.graphics.getHeight() / 720.0f;
    private static float F;
    private static final float G;
    private static final float H;
    private static final float I;
    private static int J;
    private static float K;
    private static int L;
    private static int M;
    private static float N;
    private static float O;
    private static float P;
    private static float Q;
    private static float R;
    private static int S;
    private static int T;
    private static float U;
    private static int V;
    private static int W;
    private static TextureRegion X;
    private static TextureRegionDrawable Y;
    public static boolean Z;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static Stage f2353o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static com.badlogic.gdx.e f2354p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    static TextButton f2355q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    static TextButton f2356r;
    static TextButton s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    static TextButton f2357t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    static TextButton f2358u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    static TextButton f2359v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    static TextButton f2360w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    static TextButton f2361x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    static Table f2362y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static a0 f2363z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private v f2364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private j1 f2365b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i1 f2366c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c1 f2367d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private q0.c f2368e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private q0.b f2369f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private q0.i f2370g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a.f f2371h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private a.d f2372i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private q0.a f2373j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private n0.g f2374k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private q0.t f2375l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private x f2376m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f2377n;

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            e.this.f2375l.e(e.f2354p);
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            boolean z2 = ExiledKingdoms.f3378h;
            e eVar = e.this;
            if (z2) {
                eVar.f2366c.p(e.f2353o, true);
                eVar.f2366c.o();
                eVar.f2366c.n(eVar.f2368e);
            } else {
                eVar.f2365b.q(e.f2353o, true);
                eVar.f2365b.p();
                eVar.f2365b.o(eVar.f2368e);
            }
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            e.this.f2372i.a();
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class d extends ChangeListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            e.this.f2372i.a();
        }
    }

    /* JADX INFO: renamed from: l0.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class C0032e extends ChangeListener {
        C0032e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            e.this.f2370g.a();
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class f extends ChangeListener {
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            e.d();
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class g extends k1 {
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class h extends k1 {
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class i extends k1 {
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class j extends k1 {
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class k extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            Gdx.f1569net.openURI("http://www.exiledkingdoms.com/wiki");
            return true;
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class l extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            Gdx.f1569net.openURI("http://www.exiledkingdoms.com/forum/");
            return true;
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class m extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            Gdx.f1569net.openURI("https://www.exiledkingdoms.com/forum/index.php?news/");
            return true;
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class n extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            Gdx.f1569net.openURI("https://www.facebook.com/exiledkingdoms/");
            return true;
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class o extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            Gdx.f1569net.openURI("http://www.exiledkingdoms.com/forum/");
            return true;
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class p extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            Gdx.f1569net.openURI("http://www.exiledkingdoms.com/wiki");
            return true;
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class q extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            Gdx.f1569net.openURI("https://www.exiledkingdoms.com/forum/index.php?news/");
            return true;
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class r extends ChangeListener {
        r() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            e eVar = e.this;
            eVar.f2369f.g(1, eVar.f2371h, e.f2353o);
        }
    }

    /* JADX INFO: compiled from: MainMenuScreen.java */
    final class s extends ChangeListener {
        s() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            e eVar = e.this;
            eVar.f2369f.g(0, eVar.f2371h, e.f2353o);
        }
    }

    static {
        float height = Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        F = height;
        float f2 = E;
        G = f2 * 25.0f;
        H = 90.0f * f2;
        I = f2 * 320.0f;
        J = -1;
        L = 200;
        M = 200;
        N = 0.7f;
        O = 0.7f;
        P = 0.7f;
        Q = 200;
        R = 200;
        S = 830;
        T = (int) (830 * height);
        U = 25.0f;
        V = (int) ((720.0f * height) + 1000.0f);
        W = (int) ((height * 560.0f) + 1000.0f);
        Z = true;
    }

    public e(com.badlogic.gdx.e eVar) {
        TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/ui/logo.png")));
        new ArrayList();
        this.f2377n = 0.0f;
        f2354p = eVar;
        Stage stage = new Stage();
        f2353o = stage;
        Gdx.input.setInputProcessor(stage);
        com.badlogic.gdx.utils.l.d("MainMenuScreen() - initializing Main Menu");
        Table table = new Table();
        f2362y = table;
        table.setFillParent(true);
        f2353o.addActor(f2362y);
        Image image = new Image(textureRegion);
        if (ExiledKingdoms.f3378h) {
            E = 1.0f;
            S = 900;
            T = (int) (900 * F);
            L = 250;
            M = 220;
            U = 36.0f;
            V = 1650;
            W = 1680;
            f2362y.add(image).colspan(2).height(128.0f * E).width(360.0f * E).spaceBottom(90.0f);
            f2362y.row().center();
            n0.t tVar = new n0.t(GameString.b("START_NEW_GAME", false), Assets.g(), "mainMenuButton");
            f2356r = tVar;
            tVar.getLabel().setFontScale(1.0f);
            f2362y.add(f2356r).width(240.0f).height(49.5f).space(13.75f).colspan(2);
            f2362y.row();
            n0.t tVar2 = new n0.t(GameString.b("CONTINUE_GAME", false), Assets.g(), "mainMenuButton");
            f2355q = tVar2;
            tVar2.getLabel().setFontScale(1.0f);
            f2362y.add(f2355q).width(240.0f).height(49.5f).space(13.75f).colspan(2);
            f2362y.row();
            n0.t tVar3 = new n0.t(GameString.b("SETTINGS", false), Assets.g(), "mainMenuButton");
            s = tVar3;
            tVar3.getLabel().setFontScale(1.0f);
            f2362y.add(s).width(240.0f).height(49.5f).space(13.75f).colspan(2);
            f2362y.row();
            n0.t tVar4 = new n0.t(GameString.b("CREDITS", false), Assets.g(), "mainMenuButton");
            f2359v = tVar4;
            tVar4.getLabel().setFontScale(1.0f);
            f2362y.add(f2359v).width(240.0f).height(49.5f).space(13.75f).colspan(2);
            f2362y.row();
            n0.t tVar5 = new n0.t(GameString.b("EXIT", false), Assets.g(), "mainMenuButton");
            f2360w = tVar5;
            tVar5.getLabel().setFontScale(1.0f);
            f2362y.add(f2360w).width(240.0f).height(49.5f).space(13.75f).colspan(2);
            f2362y.row().colspan(2).spaceTop(50.0f);
            Table table2 = new Table();
            table2.row().center();
            Image image2 = new Image(Assets.a("forum48"));
            Image image3 = new Image(Assets.a("wiki48"));
            Image image4 = new Image(Assets.a("news48"));
            table2.add(image2).height(E * 48.0f).width(E * 48.0f).spaceRight(E * 40.0f);
            table2.add(image3).height(E * 48.0f).width(E * 48.0f).spaceRight(E * 40.0f);
            table2.add(image4).height(E * 48.0f).width(E * 48.0f);
            f2362y.row().align(2).colspan(2).spaceTop(E * 100.0f).padTop(E * 50.0f);
            f2362y.add(table2).top().space(E * 5.0f);
            image3.addListener(new k());
            image2.addListener(new l());
            image4.addListener(new m());
            f2357t = new TextButton("", Assets.g(), "mainMenuButton");
            f2358u = new TextButton("", Assets.g(), "mainMenuButton");
            f2361x = new TextButton("", Assets.g(), "menuButton");
            f2357t.setVisible(false);
            f2358u.setVisible(false);
            f2361x.setVisible(false);
        } else {
            f2362y.add(image).colspan(2).height(E * 145.0f).width(E * 410.0f).spaceBottom(E * 60.0f);
            if (!ExiledKingdoms.s) {
                q0.p pVar = new q0.p(f2354p);
                pVar.setPosition((E * 16.0f) + ExiledKingdoms.f3390u, Gdx.graphics.getHeight() - ((pVar.getHeight() + 16.0f) * E));
                f2353o.addActor(pVar);
            }
            f2362y.row().center();
            TextButton textButton = new TextButton(GameString.b("START_NEW_GAME", false), Assets.g(), "mainMenuButton");
            f2356r = textButton;
            textButton.getLabel().setFontScale(E);
            Cell cellAdd = f2362y.add(f2356r);
            float f2 = I;
            Cell cellWidth = cellAdd.width(f2);
            float f3 = H;
            Cell cellHeight = cellWidth.height(f3);
            float f4 = G;
            cellHeight.space(f4);
            TextButton textButton2 = new TextButton(GameString.b("CONTINUE_GAME", false), Assets.g(), "mainMenuButton");
            f2355q = textButton2;
            textButton2.getLabel().setFontScale(E);
            f2362y.add(f2355q).width(f2).height(f3).space(f4);
            f2362y.row();
            TextButton textButton3 = new TextButton(GameString.b("LIBRARY", false), Assets.g(), "mainMenuButton");
            f2357t = textButton3;
            textButton3.getLabel().setFontScale(E);
            f2362y.add(f2357t).width(f2).height(f3).space(f4);
            TextButton textButton4 = new TextButton(GameString.b("DONATE", false), Assets.g(), "mainMenuButton");
            f2358u = textButton4;
            textButton4.getLabel().setFontScale(E);
            f2362y.add(f2358u).width(f2).height(f3).space(f4);
            f2362y.row();
            TextButton textButton5 = new TextButton(GameString.b("SETTINGS", false), Assets.g(), "mainMenuButton");
            s = textButton5;
            textButton5.getLabel().setFontScale(E);
            f2362y.add(s).width(f2).height(f3).space(f4);
            TextButton textButton6 = new TextButton(GameString.b("CREDITS", false), Assets.g(), "mainMenuButton");
            f2359v = textButton6;
            textButton6.getLabel().setFontScale(E);
            f2362y.add(f2359v).width(f2).height(f3).space(f4);
            f2362y.row().colspan(2);
            Table table3 = new Table();
            table3.row().center();
            Image image5 = new Image(Assets.a("facebook"));
            Image image6 = new Image(Assets.a("forum"));
            new Image(Assets.a("support"));
            table3.add(image5).spaceRight(E * 40.0f).height(E * 64.0f).width(E * 64.0f);
            table3.add(image6).height(E * 64.0f).width(E * 64.0f).spaceRight(E * 40.0f);
            Image image7 = new Image(Assets.a("wiki"));
            table3.add(image7).height(E * 64.0f).width(E * 64.0f).spaceRight(E * 40.0f);
            Image image8 = new Image(Assets.a("news"));
            table3.add(image8).height(E * 64.0f).width(E * 64.0f);
            f2362y.row().align(2).colspan(2).spaceTop(E * 20.0f);
            f2362y.add(table3).top().space(E * 10.0f);
            image5.addListener(new n());
            image6.addListener(new o());
            image7.addListener(new p());
            image8.addListener(new q());
            f2360w = new TextButton(GameString.b("EXIT", false), Assets.g(), "menuButton");
            Table table4 = new Table();
            table4.right();
            table4.add(f2360w).height(E * 60.0f).width(E * 150.0f);
            f2353o.addActor(table4);
            float width = f2353o.getWidth();
            float width2 = table4.getWidth();
            float f5 = E;
            float f6 = f5 * 8.0f;
            table4.setPosition(width - ((width2 + f6) + ExiledKingdoms.f3391v), ((f5 * 60.0f) / 2.0f) + f6 + ExiledKingdoms.f3389t);
            TextButton textButton7 = new TextButton(GameString.b("DONATE", false), Assets.g(), "menuButton");
            f2361x = textButton7;
            textButton7.setColor(Color.GREEN);
            Table table5 = new Table();
            table5.right();
            table5.add(f2361x).height(E * 65.0f).width(E * 138.0f);
            f2353o.addActor(table5);
            table5.setPosition(f2353o.getWidth() - (((E * 8.0f) + table5.getWidth()) + ExiledKingdoms.f3391v), f2353o.getHeight() - ((f2361x.getHeight() + 5.0f) * E));
        }
        Label label = new Label(" 1.3.1217", GameAssets.Y);
        label.setColor(Color.CYAN);
        label.setFontScale(E);
        Table table6 = new Table();
        f2353o.addActor(table6);
        table6.left();
        label.setAlignment(10);
        table6.row().width(100.0f);
        table6.setPosition(8.0f, E * 20.0f);
        table6.add(label);
        if (ExiledKingdoms.f3378h) {
            float f7 = E;
            table6.setPosition(0.0f * f7, f7 * 20.0f);
        }
        if (Serializer.E()) {
            f2355q.setDisabled(false);
        } else {
            f2355q.setDisabled(true);
        }
        f2359v.setDisabled(false);
        f2357t.setDisabled(false);
        f2358u.setDisabled(false);
        f2361x.setVisible(false);
        v vVar = new v();
        this.f2364a = vVar;
        f2353o.addActor(vVar);
        a0 a0Var = new a0();
        f2363z = a0Var;
        f2353o.addActor(a0Var);
        t tVar6 = new t();
        A = tVar6;
        f2353o.addActor(tVar6);
        o0.r rVar = new o0.r();
        B = rVar;
        f2353o.addActor(rVar);
        p0.a aVar = new p0.a();
        C = aVar;
        f2353o.addActor(aVar);
        z zVar = new z("", Assets.g());
        Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        Label label2 = new Label(GameString.b("LOADING", false), Assets.g(), "menuLabelStrongStyle");
        zVar.setBackground(Assets.g().getDrawable("windowbg"));
        zVar.setMovable(false);
        zVar.setModal(true);
        zVar.setWidth(Gdx.graphics.getWidth() * 0.5f);
        zVar.setHeight(Gdx.graphics.getHeight() * 0.16f);
        zVar.setPosition((Gdx.graphics.getWidth() - zVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - zVar.getHeight()) / 2.0f);
        float height = Gdx.graphics.getHeight() / 720.0f;
        label2.setFontScale(1.5f * height);
        zVar.center();
        zVar.row().height(40.0f * height).space(height * 25.0f);
        zVar.add(label2).center();
        label2.setText(GameString.b("BACKUP_WAIT", false));
        zVar.setVisible(false);
        q0.a aVar2 = new q0.a(f2354p, zVar);
        this.f2373j = aVar2;
        q0.n nVar = new q0.n(zVar);
        f2353o.addActor(aVar2);
        f2353o.addActor(nVar);
        n0.g gVar = new n0.g(f2353o);
        this.f2374k = gVar;
        if (ExiledKingdoms.f3378h) {
            this.f2369f = new q0.b(vVar, gVar, nVar);
        } else {
            this.f2369f = new q0.b(vVar, gVar, aVar2);
        }
        f2353o.addActor(this.f2369f);
        x xVar = new x();
        this.f2376m = xVar;
        f2353o.addActor(xVar);
        q0.t tVar7 = new q0.t(f2353o);
        this.f2375l = tVar7;
        f2353o.addActor(tVar7);
        q0.h hVar = new q0.h();
        c1 c1Var = new c1();
        this.f2367d = c1Var;
        j1 j1Var = new j1(hVar);
        this.f2365b = j1Var;
        f2353o.addActor(j1Var);
        i1 i1Var = new i1(hVar, c1Var);
        this.f2366c = i1Var;
        f2353o.addActor(i1Var);
        f2353o.addActor(c1Var);
        c1Var.toFront();
        f2353o.addActor(hVar);
        q0.c cVar = new q0.c();
        this.f2368e = cVar;
        f2353o.addActor(cVar);
        q0.i iVar = new q0.i();
        this.f2370g = iVar;
        f2353o.addActor(iVar);
        a.f fVar = new a.f(f2353o);
        this.f2371h = fVar;
        f2353o.addActor(fVar);
        a.d dVar = new a.d();
        this.f2372i = dVar;
        f2353o.addActor(dVar);
        f2353o.addActor(gVar);
        o1 o1Var = new o1();
        D = o1Var;
        f2353o.addActor(o1Var);
        f2353o.addActor(zVar);
        f2356r.addListener(new r());
        f2355q.addListener(new s());
        f2357t.addListener(new a());
        s.addListener(new b());
        f2358u.addListener(new c());
        f2361x.addListener(new d());
        f2359v.addListener(new C0032e());
        f2360w.addListener(new f());
        TextureRegion textureRegion2 = new TextureRegion(GameAssets.x0, (int) Q, (int) R, S, T);
        X = textureRegion2;
        Y = new TextureRegionDrawable(textureRegion2);
    }

    public static void d() {
        if (!((MainActivity) ExiledKingdoms.f()).l() && Settings.d()) {
            Settings.z(false);
        }
        Gdx.app.exit();
        GameData.k();
    }

    public static void o() {
    }

    public static void p(int i2) {
        Assets.f3309a.x();
        o1 o1Var = D;
        o1Var.setVisible(true);
        o1Var.toFront();
        J = i2;
        K = 0.0f;
    }

    public static void q(PlayerCreation playerCreation, int i2) {
        GameConsole.e().initialized = Boolean.FALSE;
        if (playerCreation == null) {
            System.out.println("Game initialization error: null PlayerCreation");
            return;
        }
        GameData.v().b(playerCreation, i2);
        f2363z.e(GameData.v().player.sheet, f2353o);
        f2363z.d();
    }

    public static void r() {
        A.p(GameData.v().player.sheet, f2353o);
        A.o();
    }

    public static void s() {
        Assets.f3309a.x();
        f2354p.c(new l0.c(f2354p, new Transition("I10_tutorial", 1)));
    }

    public static void t() {
        try {
            if (((MainActivity) ExiledKingdoms.f()).l()) {
                ((MainActivity) ExiledKingdoms.f()).o();
                Settings.z(false);
            } else {
                Settings.z(true);
                ((MainActivity) ExiledKingdoms.f()).n();
            }
        } catch (NullPointerException unused) {
            com.badlogic.gdx.utils.l.d("WARNING: MainMenuScreen.toggleGPGSConnection() NullPointerException caught when trying to connect to GPGS");
        }
    }

    public static void u() {
        TextButton textButton = f2355q;
        if (textButton != null) {
            textButton.setText(GameString.b("CONTINUE_GAME", false));
        }
        f2356r.setText(GameString.b("START_NEW_GAME", false));
        s.setText(GameString.b("SETTINGS", false));
        f2357t.setText(GameString.b("LIBRARY", false));
        f2358u.setText(GameString.b("STORE", false));
        f2358u.setText(GameString.b("DONATE", false));
        f2359v.setText(GameString.b("CREDITS", false));
        f2360w.setText(GameString.b("EXIT", false));
        f2361x.setText(GameString.b("DONATE", false));
        if (Serializer.E()) {
            f2355q.setDisabled(false);
        } else {
            f2355q.setDisabled(true);
        }
    }

    @Override // com.badlogic.gdx.n
    public final void a() {
        int i2;
        com.badlogic.gdx.utils.l.d("MainMenuScreen.show() launched...");
        FDUtils.p();
        GameLevelData.J();
        GameData.k();
        ExiledKingdoms.f3382l = GameData.GameStatus.f3185b;
        if (ExiledKingdoms.f3378h) {
            Gdx.graphics.setCursor(Assets.f3312d);
        } else {
            Assets assets = Assets.f3309a;
        }
        GPGSUpdate.b();
        ExiledKingdoms.f().getClass();
        Serializer.G();
        float f2 = L;
        Q = f2;
        float f3 = M;
        R = f3;
        X.setRegion((int) f2, (int) f3, S, T);
        if (!ExiledKingdoms.f3378h && !ExiledKingdoms.f3375e) {
            int i3 = ExiledKingdoms.f3373c;
            if (i3 < 790 && i3 > 0 && Settings.e("GL_memory_warning") < 1) {
                Settings.A(1, "GL_memory_warning");
                Settings.A(ExiledKingdoms.f3373c, "GL_detected_ram");
                Settings.v();
                new h(GameString.b("WARNING_MEMORY", false), 0).show(f2353o);
            } else if (Settings.e("GL_rate_noask") >= 1 || !((i2 = ExiledKingdoms.f3374d) == 9 || i2 == 15 || i2 == 30 || i2 == 50)) {
                int i4 = ExiledKingdoms.f3374d;
                if ((i4 == 10 || i4 == 20 || i4 == 40) && Serializer.E()) {
                    new i(GameString.b("BACKUP_REMINDER", false), 0).show(f2353o);
                }
            } else {
                this.f2376m.setVisible(true);
            }
        }
        com.badlogic.gdx.utils.l.d("MainMenuScreen.show() messages verification...");
        if ((Settings.h() == 7 || Settings.h() == 10 || Settings.h() == 8) && Settings.e("GL_incompletelang_warning") < 1) {
            new j(GameString.b("WARNING_START_LANG_INCOMPLETE", false), 0).show(f2353o);
            Settings.A(1, "GL_incompletelang_warning");
            Settings.v();
        }
        com.badlogic.gdx.utils.l.d("MainMenuScreen.show() about to connect to external libraries...");
        try {
            if (!ExiledKingdoms.f3388r && !ExiledKingdoms.s && !((MainActivity) ExiledKingdoms.f()).l() && Settings.d()) {
                ((MainActivity) ExiledKingdoms.f()).n();
            }
            com.badlogic.gdx.utils.l.d("MainMenuScreen.show() about to connect to external libraries... (2)");
            if (!ExiledKingdoms.f3388r && ExiledKingdoms.s && ExiledKingdoms.f3378h && !((MainActivity) ExiledKingdoms.f()).l()) {
                try {
                    ((MainActivity) ExiledKingdoms.f()).n();
                } catch (Exception e2) {
                    com.badlogic.gdx.utils.l.d("WARNING: MainMenuScreen.show() General Exception caught when trying to connect to GPGS: " + e2.getMessage());
                }
            }
        } catch (NullPointerException unused) {
            com.badlogic.gdx.utils.l.d("WARNING: MainMenuScreen.show() NullPointerException caught when trying to connect to GPGS");
        }
        com.badlogic.gdx.utils.l.d("MainMenuScreen.show() completed");
        Gdx.input.setCatchBackKey(true);
        Assets.f3309a.t("hills");
        f2358u.setDisabled(false);
        f2361x.setVisible(false);
    }

    @Override // com.badlogic.gdx.n
    public final void b(int i2, int i3) {
        f2353o.getViewport().p(i2, i3);
    }

    @Override // com.badlogic.gdx.n
    public final void c(float f2) {
        if (ExiledKingdoms.f3380j && ExiledKingdoms.f3381k) {
            ExiledKingdoms.f3380j = false;
            ExiledKingdoms.f3381k = false;
            new g(GameString.b("DONATE_THANKS", false), 0).show(f2353o);
            GPGSUpdate.a();
        }
        boolean z2 = ExiledKingdoms.f3378h;
        if (z2) {
            if (z2) {
                Gdx.graphics.setCursor(Assets.f3312d);
            } else {
                Assets assets = Assets.f3309a;
            }
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        f2353o.act(Gdx.graphics.getDeltaTime());
        f2353o.draw();
        if (Z && Gdx.input.isKeyPressed(131)) {
            Z = false;
            this.f2377n = 0.0f;
            if (B.isVisible()) {
                B.setVisible(false);
            } else if (!D.isVisible() && !f2363z.isVisible() && !A.isVisible()) {
                v vVar = this.f2364a;
                if (vVar.isVisible()) {
                    vVar.setVisible(false);
                } else {
                    c1 c1Var = this.f2367d;
                    if (c1Var.isVisible()) {
                        c1Var.c();
                    } else {
                        x xVar = this.f2376m;
                        if (xVar.isVisible()) {
                            xVar.setVisible(false);
                        } else {
                            n0.g gVar = this.f2374k;
                            if (gVar.isVisible()) {
                                gVar.e();
                            } else {
                                q0.b bVar = this.f2369f;
                                if (bVar.isVisible()) {
                                    bVar.setVisible(false);
                                } else {
                                    q0.t tVar = this.f2375l;
                                    if (tVar.isVisible()) {
                                        tVar.setVisible(false);
                                    } else {
                                        j1 j1Var = this.f2365b;
                                        if (j1Var.isVisible()) {
                                            j1Var.n();
                                        } else {
                                            i1 i1Var = this.f2366c;
                                            if (i1Var.isVisible()) {
                                                i1Var.m();
                                            } else if (C.isVisible()) {
                                                C.setVisible(false);
                                            } else {
                                                q0.i iVar = this.f2370g;
                                                if (iVar.isVisible()) {
                                                    iVar.setVisible(false);
                                                } else {
                                                    a.f fVar = this.f2371h;
                                                    if (fVar.isVisible()) {
                                                        fVar.setVisible(false);
                                                    } else {
                                                        a.d dVar = this.f2372i;
                                                        if (dVar.isVisible()) {
                                                            dVar.setVisible(false);
                                                        } else {
                                                            q0.a aVar = this.f2373j;
                                                            if (aVar.isVisible()) {
                                                                aVar.setVisible(false);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!Z) {
            float f3 = this.f2377n;
            if (f3 > 1.0f) {
                Z = true;
            } else {
                this.f2377n = f3 + f2;
            }
        }
        float f4 = K + f2;
        K = f4;
        int i2 = J;
        if (i2 != -1 && f4 > 0.2f) {
            int iIntValue = Serializer.r(i2).intValue();
            int i3 = J;
            J = -1;
            Serializer.a(i3, iIntValue);
            D.setVisible(false);
        }
        if (1.0f / f2 >= 4.0f) {
            float f5 = Q;
            float f6 = f2 * U;
            float f7 = (O * f6) + f5;
            Q = f7;
            float f8 = (f6 * P) + R;
            R = f8;
            float f9 = S + f7;
            float f10 = V;
            float f11 = N;
            if (f9 > f10) {
                O = -f11;
            }
            if (T + f8 > W) {
                P = -f11;
            }
            if (f7 < L) {
                O = f11;
            }
            if (f8 < M) {
                P = f11;
            }
        }
        X.scroll(O / 5000.0f, P / 5000.0f);
        Y.setRegion(X);
        f2362y.setBackground(Y);
    }

    @Override // com.badlogic.gdx.n
    public final void resume() {
        try {
            Assets assets = Assets.f3309a;
            if (assets.s()) {
                assets.r(new r.d());
                Assets.v();
            }
            GameAssets.d();
        } catch (Exception e2) {
            com.badlogic.gdx.utils.l.d("ExiledKingdoms.initialize() EXCEPTION -:  " + e2.getMessage());
            Gdx.app.exit();
        }
        if (!ExiledKingdoms.f3371a && ExiledKingdoms.f3387q && ExiledKingdoms.f3386p.a() != null && ((e0.f) ExiledKingdoms.f3386p.a()).t()) {
            ExiledKingdoms.f3386p.c();
        }
        if (ExiledKingdoms.f3371a || !ExiledKingdoms.f3388r || ExiledKingdoms.f3374d <= 10 || ExiledKingdoms.f3386p.a() == null || !((e0.f) ExiledKingdoms.f3386p.a()).t()) {
            return;
        }
        ExiledKingdoms.f3386p.c();
    }
}
