package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.LogLine;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: GameLogWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c0 extends Window {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static c0 f2581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static float f2582e = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextButton f2583a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Table f2584b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ScrollPane f2585c;

    /* JADX INFO: compiled from: GameLogWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            c0.this.setVisible(false);
            GameLevel.n(false);
            return true;
        }
    }

    public static void a() {
        if (f2581d != null) {
            f2581d = null;
        }
    }

    public static c0 b() {
        if (f2581d == null) {
            c0 c0Var = new c0("", Assets.g());
            TextButton textButton = new TextButton(GameString.b("BACK", false), Assets.g(), "menuButton");
            c0Var.f2583a = textButton;
            Table table = new Table();
            c0Var.f2584b = table;
            ScrollPane scrollPane = new ScrollPane(table);
            c0Var.f2585c = scrollPane;
            c0Var.setBackground(Assets.g().getDrawable("windowbg"));
            c0Var.setMovable(false);
            c0Var.setModal(true);
            float f2 = f2582e;
            c0Var.setWidth(930.0f * f2);
            c0Var.setHeight(620.0f * f2);
            c0Var.setPosition((Gdx.graphics.getWidth() - c0Var.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - c0Var.getHeight()) / 2.0f);
            c0Var.left();
            table.top();
            scrollPane.setForceScroll(false, true);
            scrollPane.setScrollbarsOnTop(true);
            c0Var.add(scrollPane).fill().expand().top().left().width(f2 * 910.0f);
            c0Var.row().expandY().bottom().center();
            c0Var.add(textButton).bottom();
            textButton.clearListeners();
            textButton.addListener(c0Var.new a());
            f2581d = c0Var;
        }
        return f2581d;
    }

    public final void c() {
        this.f2583a.setText(GameString.b("BACK", false));
        Table table = this.f2584b;
        table.clearChildren();
        for (LogLine logLine : GameData.v().log.f()) {
            Label label = new Label("", GameAssets.f3318b0);
            label.setText(logLine.a().replace("[GRAY]", "[BLACK]"));
            float f2 = f2582e;
            label.setFontScale(f2);
            table.add(label).left().top().width(f2 * 910.0f);
            table.row().left().top();
        }
        ScrollPane scrollPane = this.f2585c;
        scrollPane.layout();
        scrollPane.setScrollPercentY(100.0f);
        GameLevel.n(true);
        GameData.v().log.k();
        setVisible(true);
    }
}
