package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.GPGSUpdate;

/* JADX INFO: compiled from: GameOptionsWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class d0 extends Window {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static d0 f2607h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static float f2608i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private t f2609a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t f2610b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t f2611c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private t f2612d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private t f2613e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private t f2614f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Label f2615g;

    /* JADX INFO: compiled from: GameOptionsWindow.java */
    final class a extends ChangeListener {

        /* JADX INFO: renamed from: n0.d0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GameOptionsWindow.java */
        final class C0034a extends j {

            /* JADX INFO: renamed from: n0.d0$a$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: GameOptionsWindow.java */
            final class C0035a extends k1 {
                @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
                protected final void result(Object obj) {
                    GameLevel.n(false);
                }
            }

            C0034a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    GameData.v().c();
                    d0.this.setVisible(false);
                    new C0035a(GameString.b("DIF_LOWERED", false), 0).show(z.v().a());
                }
            }
        }

        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            new C0034a(GameString.b("RED_DIFFICULTY_CONFIRM", false)).show(z.v().a());
        }
    }

    /* JADX INFO: compiled from: GameOptionsWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            d0.this.setVisible(false);
            z.v().O();
        }
    }

    /* JADX INFO: compiled from: GameOptionsWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (GameData.v().G()) {
                return;
            }
            z.v().U.i(1, GameData.v().slot, false);
            d0.this.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: GameOptionsWindow.java */
    final class d extends ChangeListener {

        /* JADX INFO: compiled from: GameOptionsWindow.java */
        final class a extends k1 {
        }

        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (GameData.v().G()) {
                return;
            }
            if (!m0.b.P().f2423h && !m0.b.P().f2426k) {
                new a(GameString.b("CANT_SAVE_INDOOR", false), 0).show(z.v().a());
            } else {
                z.v().U.i(0, GameData.v().slot, false);
                d0.this.setVisible(false);
            }
        }
    }

    /* JADX INFO: compiled from: GameOptionsWindow.java */
    final class e extends ChangeListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            d0.this.setVisible(false);
            GameData.v().getClass();
            if (GameData.I()) {
                Serializer.z(GameData.v().slot, 0);
            }
            System.out.println("exit game");
            GPGSUpdate.c(false);
            com.badlogic.gdx.e eVar = (com.badlogic.gdx.e) Gdx.app.getApplicationListener();
            GameData.v().getClass();
            GameData.X();
            eVar.c(new l0.e(eVar));
            GameLevel.n(false);
        }
    }

    /* JADX INFO: compiled from: GameOptionsWindow.java */
    final class f extends ChangeListener {
        f() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            d0.this.setVisible(false);
            GameLevel.n(false);
        }
    }

    public static void a() {
        if (f2607h != null) {
            f2607h = null;
        }
    }

    public static d0 b() {
        if (f2607h == null) {
            d0 d0Var = new d0("", Assets.g());
            t tVar = new t(GameString.b("SETTINGS", false), Assets.g(), "menuButton");
            d0Var.f2609a = tVar;
            t tVar2 = new t(GameString.b("SAVE_GAME", false), Assets.g(), "menuButton");
            d0Var.f2610b = tVar2;
            t tVar3 = new t(GameString.b("LOAD_GAME", false), Assets.g(), "menuButton");
            d0Var.f2611c = tVar3;
            t tVar4 = new t(GameString.b("SAVE_EXIT", false), Assets.g(), "menuButton");
            d0Var.f2612d = tVar4;
            t tVar5 = new t(GameString.b("BACK_TO_GAME", false), Assets.g(), "menuButton");
            d0Var.f2613e = tVar5;
            t tVar6 = new t(GameString.b("LOWER_DIFFICULTY", false), Assets.g(), "menuButton");
            d0Var.f2614f = tVar6;
            Label label = new Label("", Assets.g(), "menuLabelStrongStyle");
            d0Var.f2615g = label;
            float height = Gdx.graphics.getHeight() / 720.0f;
            f2608i = height;
            if (ExiledKingdoms.f3378h) {
                f2608i = 1.0f;
                height = 0.8f;
            }
            d0Var.setBackground(Assets.g().getDrawable("windowbg"));
            d0Var.setMovable(false);
            d0Var.setModal(true);
            d0Var.setWidth(Gdx.graphics.getWidth() / 3);
            d0Var.setHeight(Gdx.graphics.getHeight() / 1.15f);
            if (ExiledKingdoms.f3378h) {
                d0Var.setWidth(341.33334f);
                d0Var.setHeight(500.86957f);
            }
            d0Var.setPosition((Gdx.graphics.getWidth() - d0Var.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - d0Var.getHeight()) / 2.0f);
            d0Var.setVisible(false);
            label.setFontScale(f2608i);
            d0Var.row();
            float f2 = 8.0f * height;
            d0Var.add(tVar2).width(d0Var.getWidth() * 0.9f).height(d0Var.getHeight() / 7.4f).space(f2);
            d0Var.row();
            d0Var.add(tVar3).width(d0Var.getWidth() * 0.9f).height(d0Var.getHeight() / 7.4f).space(f2);
            d0Var.row();
            d0Var.add(tVar).width(d0Var.getWidth() * 0.9f).height(d0Var.getHeight() / 7.4f).space(f2);
            d0Var.row();
            d0Var.add(tVar5).width(d0Var.getWidth() * 0.9f).height(d0Var.getHeight() / 7.4f).space(f2);
            d0Var.row();
            d0Var.add(tVar4).width(d0Var.getWidth() * 0.9f).height(d0Var.getHeight() / 7.4f).space(f2);
            d0Var.row();
            d0Var.add(label).space(f2).padTop(height * 10.0f);
            d0Var.row();
            d0Var.add(tVar6).space(f2);
            tVar6.addListener(d0Var.new a());
            tVar.addListener(d0Var.new b());
            tVar3.addListener(d0Var.new c());
            tVar2.addListener(d0Var.new d());
            tVar4.addListener(d0Var.new e());
            tVar5.addListener(d0Var.new f());
            f2607h = d0Var;
        }
        return f2607h;
    }

    public final void c() {
        setVisible(true);
        this.f2609a.setText(GameString.b("SETTINGS", false));
        t tVar = this.f2610b;
        tVar.setText(GameString.b("SAVE_GAME", false));
        t tVar2 = this.f2611c;
        tVar2.setText(GameString.b("LOAD_GAME", false));
        this.f2612d.setText(GameString.b("SAVE_EXIT", false));
        this.f2613e.setText(GameString.b("BACK_TO_GAME", false));
        this.f2615g.setText(GameData.v().p());
        t tVar3 = this.f2614f;
        tVar3.setText(GameString.b("LOWER_DIFFICULTY", false));
        tVar3.getLabel().setFontScale(0.9f);
        if (GameData.v().F()) {
            tVar3.setDisabled(true);
        } else {
            tVar3.setDisabled(false);
        }
        tVar.setDisabled(false);
        tVar2.setDisabled(false);
        if (!Gdx.files.local(Serializer.C(GameData.v().slot, 1)).exists()) {
            tVar2.setDisabled(true);
        }
        if (GameData.v().G()) {
            tVar.setDisabled(true);
            tVar2.setDisabled(true);
        }
    }
}
