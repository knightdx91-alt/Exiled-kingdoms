package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;

/* JADX INFO: compiled from: SaveWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class h1 extends Window {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static h1 f2685a;

    public static h1 a() {
        if (f2685a == null) {
            h1 h1Var = new h1("", Assets.g());
            Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
            Label label = new Label(GameString.b("SAVING", false), Assets.g(), "menuLabelStrongStyle");
            h1Var.setBackground(Assets.g().getDrawable("windowbg"));
            h1Var.setMovable(false);
            h1Var.setModal(true);
            h1Var.setWidth(Gdx.graphics.getWidth() * 0.5f);
            h1Var.setHeight(Gdx.graphics.getHeight() * 0.16f);
            h1Var.setPosition((Gdx.graphics.getWidth() - h1Var.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - h1Var.getHeight()) / 2.0f);
            float height = Gdx.graphics.getHeight() / 720.0f;
            label.setFontScale(1.5f * height);
            h1Var.center();
            h1Var.row().height(40.0f * height).space(height * 25.0f);
            h1Var.add(label).center();
            label.setText(GameString.b("SAVING", false));
            h1Var.setVisible(false);
            f2685a = h1Var;
        }
        return f2685a;
    }
}
