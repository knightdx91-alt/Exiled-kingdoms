package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: SimpleDialog.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class k1 extends Dialog {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static float f2761b = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static float f2762c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2763a;

    public k1(String str, int i2) {
        super("", Assets.g());
        this.f2763a = 0;
        int length = str.length() / 36;
        this.f2763a = length;
        int iIndexOf = 0;
        int i3 = 0;
        while (iIndexOf != -1) {
            iIndexOf = str.indexOf("\n", iIndexOf);
            if (iIndexOf != -1) {
                i3++;
                iIndexOf++;
            }
        }
        this.f2763a = Math.max(i3 - 1, 0) + length;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(f2761b * 700.0f);
        setHeight(f2761b * 240.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f2762c = f2761b;
        if (ExiledKingdoms.f3378h) {
            f2761b = 0.8f;
            f2762c = 1.0f;
            setWidth(560.0f);
            setHeight(192.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        t tVar = new t("[WHITE]OK[]", Assets.g(), "menuButton");
        Label label = new Label("", GameAssets.S);
        label.setFontScale(f2762c * 1.0f);
        label.setText("[BLACK]" + str + "[]");
        label.setWrap(true);
        padTop(30.0f).padBottom(20.0f);
        getContentTable().add(label).width(f2761b * 380.0f).center().align(1).row();
        getButtonTable().defaults().spaceLeft(f2761b * 40.0f).width(f2761b * 90.0f);
        button(tVar, Boolean.TRUE);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        float f2 = f2761b;
        return Math.max(315.0f * f2, this.f2763a * 58.0f * f2);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return f2761b * 430.0f;
    }
}
