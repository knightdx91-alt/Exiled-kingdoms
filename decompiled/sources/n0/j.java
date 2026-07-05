package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ConfirmDialog.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class j extends Dialog {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static float f2723b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f2724a;

    public j(String str) {
        super("", Assets.g());
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.f2724a = height;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(700.0f * height);
        setHeight(240.0f * height);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f2723b = height;
        if (ExiledKingdoms.f3378h) {
            this.f2724a = 0.8f;
            f2723b = 1.0f;
            setWidth(560.0f);
            setHeight(192.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        t tVar = new t(a.a.o("YES", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        t tVar2 = new t(a.a.o("NO", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        Label label = new Label("", GameAssets.T);
        label.setFontScale(f2723b);
        label.setText("[BLACK]" + str + "[]");
        label.setWrap(true);
        padTop(30.0f).padBottom(30.0f);
        getContentTable().add(label).width(this.f2724a * 370.0f).center().align(1).row();
        getButtonTable().defaults().spaceLeft(this.f2724a * 40.0f).width(this.f2724a * 90.0f);
        button(tVar, Boolean.TRUE);
        button(tVar2, Boolean.FALSE);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        return this.f2724a * 300.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return this.f2724a * 430.0f;
    }
}
