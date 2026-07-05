package p0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import n0.t;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: TipDialog.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class b extends Dialog {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static float f3722b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f3723a;

    public b(String str, String str2, TextureRegion textureRegion) {
        this(str, str2, "", textureRegion);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        return this.f3723a * 555.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return this.f3723a * 760.0f;
    }

    public b(String str, String str2, String str3, TextureRegion textureRegion) {
        super("", Assets.g());
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.f3723a = height;
        GameLevel.n(true);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(1200.0f * height);
        setHeight(680.0f * height);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f3722b = height;
        if (ExiledKingdoms.f3378h) {
            this.f3723a = 0.8f;
            f3722b = 1.0f;
            setWidth(960.0f);
            setHeight(544.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        Image image = new Image(textureRegion);
        t tVar = new t(a.a.o("OK", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        Label label = new Label("", Assets.g(), "menuLabelStrongVeryLargeStyle");
        Label label2 = new Label("", GameAssets.T);
        Label label3 = new Label("", GameAssets.S);
        label.setFontScale(f3722b);
        label.setText(str);
        label.setWrap(true);
        label.setAlignment(1);
        label2.setFontScale(f3722b);
        label2.setText("[BLACK]" + str2 + "[]");
        label2.setWrap(true);
        label2.setAlignment(8);
        if (!str3.equals("")) {
            label3.setFontScale(f3722b);
            label3.setText("[BLACK]" + str3 + "[]");
            label3.setWrap(true);
            label3.setAlignment(8);
        }
        padTop(this.f3723a * 10.0f).padBottom(this.f3723a * 20.0f).center();
        getContentTable().defaults().space(this.f3723a * 10.0f);
        float height2 = (this.f3723a * 80.0f) / image.getHeight();
        getContentTable().row().center();
        getContentTable().add(new Image(Assets.a("help"))).width(this.f3723a * 50.0f).height(this.f3723a * 50.0f).center().spaceTop(40.0f).align(1).right();
        getContentTable().add(image).width(image.getWidth() * height2).height(this.f3723a * 80.0f).center().spaceTop(40.0f).align(1).left();
        getContentTable().row().colspan(2);
        getContentTable().add(label).width(this.f3723a * 600.0f).center().align(1);
        getContentTable().row().colspan(2);
        getContentTable().add(label2).width(this.f3723a * 700.0f).center().align(1);
        if (!str3.equals("")) {
            getContentTable().row().colspan(2).spaceTop(this.f3723a * 12.0f);
            getContentTable().add(label3).width(this.f3723a * 655.0f).center().align(1);
        }
        getContentTable().row().colspan(2);
        getButtonTable().defaults().spaceLeft(this.f3723a * 40.0f).width(this.f3723a * 120.0f).expandY();
        button(tVar, Boolean.TRUE);
    }
}
