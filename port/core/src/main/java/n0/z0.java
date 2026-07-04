package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: HowManyDialog.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class z0 extends Dialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static float f2966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f2967e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f2968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Slider f2969b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f2970c;

    /* JADX INFO: compiled from: HowManyDialog.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            z0.this.f2970c = 0;
        }
    }

    /* JADX INFO: compiled from: HowManyDialog.java */
    final class b extends ChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Label f2972a;

        b(Label label) {
            this.f2972a = label;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            z0 z0Var = z0.this;
            z0Var.f2970c = (int) z0Var.f2969b.getValue();
            this.f2972a.setText(a.a.p(new StringBuilder("[BLACK]"), z0Var.f2970c, "[]"));
        }
    }

    public z0(int i2, boolean z2) {
        super("", Assets.g());
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.f2968a = height;
        Slider slider = new Slider(1.0f, 99.0f, 1.0f, false, GameAssets.k());
        this.f2969b = slider;
        this.f2970c = 1;
        f2967e = true;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(600.0f * height);
        setHeight(240.0f * height);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f2966d = height;
        if (ExiledKingdoms.f3378h) {
            this.f2968a = 0.8f;
            f2966d = 1.0f;
            setWidth(560.0f);
            setHeight(192.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        t tVar = new t(a.a.o("OK", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        t tVar2 = new t(a.a.o("CANCEL", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        Label label = new Label(a.a.o("HOW_MANY_UNITS", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.T);
        label.setFontScale(f2966d);
        label.setWrap(true);
        padTop(30.0f).padBottom(30.0f);
        getContentTable().row().center().align(1);
        getContentTable().add(label).width(this.f2968a * 400.0f).center();
        getContentTable().row();
        float f2 = i2;
        slider.setRange(1.0f, f2);
        slider.setValue(1.0f);
        if (z2) {
            slider.setValue(f2);
            this.f2970c = i2;
        }
        getContentTable().add(slider).width(this.f2968a * 400.0f);
        getContentTable().row();
        Label label2 = new Label(a.a.p(new StringBuilder("[BLACK]"), this.f2970c, "[]"), GameAssets.f3316a0);
        label2.setFontScale(f2966d);
        label2.setWrap(true);
        getContentTable().add(label2);
        getButtonTable().defaults().spaceLeft(this.f2968a * 40.0f).width(this.f2968a * 110.0f);
        row().width(this.f2968a * 450.0f);
        button(tVar, Integer.valueOf(this.f2970c));
        button((Button) tVar2, (Object) 0);
        tVar2.addListener(new a());
        slider.addListener(new b(label2));
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        return this.f2968a * 300.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return this.f2968a * 430.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
    public final void hide() {
        f2967e = false;
        super.hide();
    }
}
