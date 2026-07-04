package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import n0.k1;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: TraitsWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a0 extends Window {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    static float f3446m = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static float f3447n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n0.t f3448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public n0.t f3449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Label f3450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y f3451d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y f3452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public y f3453f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public y f3454g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public y f3455h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public y f3456i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharacterSheet f3457j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Stage f3458k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3459l;

    /* JADX INFO: compiled from: TraitsWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a0.this.c();
            return true;
        }
    }

    /* JADX INFO: compiled from: TraitsWindow.java */
    final class b extends InputListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a0 a0Var = a0.this;
            a0Var.f3457j.e0();
            a0Var.f3457j.d0();
            a0Var.f();
            return true;
        }
    }

    /* JADX INFO: compiled from: TraitsWindow.java */
    final class c extends InputListener {

        /* JADX INFO: compiled from: TraitsWindow.java */
        final class a extends k1 {
        }

        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a0 a0Var = a0.this;
            if (a0Var.f3457j.M() != 0) {
                new a(GameString.b("SPEND_ALL_TRAITS", false), 0).show(a0Var.f3458k);
                return true;
            }
            a0Var.c();
            l0.e.r();
            return true;
        }
    }

    public a0() {
        super("", Assets.g());
        n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
        this.f3448a = tVar;
        n0.t tVar2 = new n0.t("", Assets.g(), "menuButton");
        this.f3449b = tVar2;
        Label label = new Label(a.a.o("AVAILABLE_SKILL_POINTS", false, new StringBuilder(), ":"), Assets.g(), "menuLabelStrongStyle");
        Label label2 = new Label("", Assets.g(), "menuLabelStyle");
        this.f3450c = label2;
        y yVar = new y(0, this);
        this.f3451d = yVar;
        y yVar2 = new y(1, this);
        this.f3452e = yVar2;
        y yVar3 = new y(2, this);
        this.f3453f = yVar3;
        y yVar4 = new y(3, this);
        this.f3454g = yVar4;
        y yVar5 = new y(4, this);
        this.f3455h = yVar5;
        y yVar6 = new y(5, this);
        this.f3456i = yVar6;
        Image image = new Image();
        setVisible(false);
        this.f3459l = false;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(f3446m * 1140.0f);
        setHeight(f3446m * 710.0f);
        if (getHeight() < 320.0f) {
            setHeight(320.0f);
        }
        if (getWidth() < 476.0f) {
            setWidth(476.0f);
        }
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f3447n = f3446m;
        if (ExiledKingdoms.f3378h) {
            f3446m = 0.8f;
            f3447n = 1.0f;
            setWidth(912.0f);
            setHeight(568.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        if (!ExiledKingdoms.f3378h) {
            label.setFontScale(f3447n);
            label2.setFontScale(f3447n);
            tVar2.getLabel().setFontScale(f3447n);
            tVar.getLabel().setFontScale(f3447n);
        }
        label.setText(GameString.b("AVAILABLE_TRAIT_POINTS", false) + ":");
        image.setDrawable(new NinePatchDrawable(GameAssets.P));
        row().expandX().top().padTop(f3446m * 5.0f).align(8).padBottom(f3446m * 5.0f);
        add(new Label("", Assets.g(), "menuLabelStyle")).left().width(300.0f * f3446m).padLeft(f3446m * 8.0f).expandX();
        add(label).right().padLeft(f3446m * 8.0f);
        add(label2).left().align(8);
        row().colspan(3).left().pad(0.0f).height(2.0f).width(getWidth() - (f3446m * 8.0f));
        add(image);
        row().pad(f3446m * 5.0f);
        add(yVar, yVar2, yVar3);
        row().pad(f3446m * 5.0f);
        add(yVar4, yVar5, yVar6);
        row().align(4).center();
        add(tVar2).bottom().top().width(f3446m * 250.0f).height(f3446m * 50.0f);
        add(new Label("", Assets.g(), "menuLabelStyle"));
        add(tVar).bottom().top().width(f3446m * 200.0f).height(f3446m * 50.0f);
        tVar2.setVisible(false);
        tVar.addListener(new a());
        tVar2.addListener(new b());
    }

    public final void c() {
        setVisible(false);
        if (this.f3459l) {
            return;
        }
        this.f3457j.d0();
        n0.z.v().V.X();
    }

    public final void d() {
        this.f3459l = true;
        n0.t tVar = this.f3448a;
        tVar.setText(GameString.b("NEXT", false));
        n0.t tVar2 = this.f3449b;
        tVar2.setText(GameString.b("RESET", false));
        tVar2.setVisible(true);
        tVar.clearListeners();
        tVar.d(false);
        tVar.addListener(new c());
    }

    public final void e(CharacterSheet characterSheet, Stage stage) {
        this.f3457j = characterSheet;
        this.f3458k = stage;
        this.f3451d.f(stage);
        this.f3452e.f(this.f3458k);
        this.f3453f.f(this.f3458k);
        this.f3454g.f(this.f3458k);
        this.f3455h.f(this.f3458k);
        this.f3456i.f(this.f3458k);
        f();
        setVisible(true);
        this.f3459l = false;
    }

    public final void f() {
        this.f3450c.setText("" + this.f3457j.M());
        this.f3451d.g(this.f3457j);
        this.f3452e.g(this.f3457j);
        this.f3453f.g(this.f3457j);
        this.f3454g.g(this.f3457j);
        this.f3455h.g(this.f3457j);
        this.f3456i.g(this.f3457j);
    }
}
