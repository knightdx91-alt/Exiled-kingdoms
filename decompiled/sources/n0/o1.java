package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: WaitMenuWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class o1 extends Window {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static float f2847b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f2848a;

    public o1() {
        super("", Assets.g());
        this.f2848a = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        Label label = new Label(GameString.b("LOADING", false), Assets.g(), "menuLabelStrongVeryLargeStyle");
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(Gdx.graphics.getWidth() * 0.5f);
        setHeight(Gdx.graphics.getHeight() * 0.16f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.f2848a = height;
        f2847b = height;
        if (ExiledKingdoms.f3378h) {
            this.f2848a = 0.8f;
            f2847b = 1.0f;
            setWidth(512.0f);
            setHeight(92.159996f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        label.setFontScale(f2847b);
        center();
        row().height(this.f2848a * 40.0f).space(this.f2848a * 25.0f);
        add(label).center();
        label.setText(GameString.b("LOADING", false));
        setVisible(false);
    }
}
