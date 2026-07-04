package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: CharacterMessageDialog.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class e extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f2624a;
    public float SCALE;

    public e(String str, String str2) {
        this(str, str2, Boolean.TRUE);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        return (this.SCALE * 220.0f) + getContentTable().getHeight();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return this.SCALE * 720.0f;
    }

    public e(String str, String str2, Boolean bool) {
        super("", Assets.g());
        this.SCALE = Gdx.graphics.getHeight() / 720.0f;
        GameLevel.n(true);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(this.SCALE * 1200.0f);
        setHeight(this.SCALE * 690.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f2624a = this.SCALE;
        if (ExiledKingdoms.f3378h) {
            this.SCALE = 0.8f;
            f2624a = 1.0f;
            setWidth(960.0f);
            setHeight(552.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        Image image = new Image(GameData.v().player.E());
        t tVar = new t(a.a.o("OK", false, new StringBuilder("[WHITE]"), "[]"), Assets.g(), "menuButton");
        Label label = new Label("", Assets.g(), "menuLabelStrongStyle");
        Label label2 = new Label("", GameAssets.T);
        label.setFontScale(f2624a);
        label.setText(str);
        label.setWrap(true);
        label.setAlignment(1);
        label2.setFontScale(f2624a);
        label2.setText("[BLACK]" + FDUtils.e(str2) + "[]");
        label2.setWrap(true);
        label2.setAlignment(1);
        padTop(this.SCALE * 10.0f).padBottom(this.SCALE * 20.0f).center();
        getContentTable().defaults().space(this.SCALE * 10.0f);
        if (bool.booleanValue()) {
            getContentTable().add(image).width(this.SCALE * 120.0f).height(this.SCALE * 120.0f).center().align(1).row();
        }
        getContentTable().add(label).width(this.SCALE * 600.0f).center().align(1).row();
        getContentTable().add(label2).width(this.SCALE * 680.0f).center().align(1).row();
        getButtonTable().defaults().spaceLeft(this.SCALE * 40.0f).width(this.SCALE * 120.0f).expandY();
        button(tVar, Boolean.TRUE);
    }
}
