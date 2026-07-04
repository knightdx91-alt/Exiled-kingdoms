package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: WaitWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class p1 extends Window {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static float f2856b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static p1 f2857c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f2858a;

    public static void a() {
        if (f2857c != null) {
            f2857c = null;
        }
    }

    public static p1 b() {
        if (f2857c == null) {
            p1 p1Var = new p1("", Assets.g());
            p1Var.f2858a = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
            Label label = new Label(GameString.b("LOADING", false), Assets.g(), "menuLabelStrongVeryLargeStyle");
            p1Var.setBackground(Assets.g().getDrawable("windowbg"));
            p1Var.setMovable(false);
            p1Var.setModal(true);
            p1Var.setWidth(Gdx.graphics.getWidth() * 0.5f);
            p1Var.setHeight(Gdx.graphics.getHeight() * 0.16f);
            p1Var.setPosition((Gdx.graphics.getWidth() - p1Var.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - p1Var.getHeight()) / 2.0f);
            float height = Gdx.graphics.getHeight() / 720.0f;
            p1Var.f2858a = height;
            f2856b = height;
            if (ExiledKingdoms.f3378h) {
                p1Var.f2858a = 0.8f;
                f2856b = 1.0f;
                p1Var.setWidth(512.0f);
                p1Var.setHeight(92.159996f);
                p1Var.setPosition((Gdx.graphics.getWidth() - p1Var.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - p1Var.getHeight()) / 2.0f);
            }
            label.setFontScale(f2856b);
            p1Var.center();
            p1Var.row().height(p1Var.f2858a * 40.0f).space(p1Var.f2858a * 25.0f);
            p1Var.add(label).center();
            label.setText(GameString.b("LOADING", false));
            p1Var.setVisible(false);
            f2857c = p1Var;
        }
        return f2857c;
    }
}
