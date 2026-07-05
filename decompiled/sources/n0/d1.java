package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: LockedDialog.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class d1 extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f2623a;

    public d1() {
        super("", Assets.g());
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.f2623a = height;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(700.0f * height);
        setHeight(240.0f * height);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        TextButton textButton = new TextButton(a.a.o("YES", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        TextButton textButton2 = new TextButton(a.a.o("NO", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        Label label = new Label("", GameAssets.S);
        label.setFontScale(height);
        a.a.x("ONLY_PLACE_FULL_LICENSE", false, new StringBuilder("[BLACK]"), "[]", label);
        label.setWrap(true);
        padTop(30.0f).padBottom(30.0f);
        getContentTable().add(new Image(Assets.f("locked")));
        getContentTable().row();
        getContentTable().add(label).width(400.0f * height).center().align(1).row();
        getButtonTable().defaults().spaceLeft(40.0f * height).width(height * 90.0f);
        button(textButton2, Boolean.FALSE);
        button(textButton, Boolean.TRUE);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        return this.f2623a * 410.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return this.f2623a * 450.0f;
    }
}
