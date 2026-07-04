package p0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: UIHelpWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c extends Window {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f3724a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static float f3725b = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: compiled from: UIHelpWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            c.this.setVisible(false);
            GameLevel.n(false);
            return true;
        }
    }

    public static void a() {
        if (f3724a != null) {
            f3724a = null;
        }
    }

    public static c b() {
        if (f3724a == null) {
            c cVar = new c("", Assets.g());
            TextButton textButton = new TextButton(GameString.b("BACK", false), Assets.g(), "menuButton");
            ScrollPane scrollPane = new ScrollPane(new Table());
            Image image = new Image();
            cVar.setBackground(Assets.g().getDrawable("windowbg"));
            cVar.setMovable(false);
            cVar.setModal(true);
            float f2 = f3725b;
            cVar.setWidth(820.0f * f2);
            cVar.setHeight(650.0f * f2);
            cVar.setPosition((Gdx.graphics.getWidth() - cVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - cVar.getHeight()) / 2.0f);
            scrollPane.setForceScroll(false, true);
            scrollPane.setScrollbarsOnTop(true);
            image.setDrawable(new NinePatchDrawable(GameAssets.P));
            cVar.add(scrollPane).fill().expand().top().left();
            cVar.row().align(4).center();
            cVar.add(textButton).bottom().width(f2 * 200.0f);
            textButton.clearListeners();
            textButton.addListener(cVar.new a());
            f3724a = cVar;
        }
        return f3724a;
    }
}
