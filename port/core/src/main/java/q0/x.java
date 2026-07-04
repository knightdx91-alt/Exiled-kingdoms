package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: RateWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class x extends Window {

    /* JADX INFO: compiled from: RateWindow.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            x.this.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: RateWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            x.this.setVisible(false);
            Settings.A(1, "GL_rate_noask");
            if (ExiledKingdoms.f3387q) {
                Gdx.f1569net.openURI("https://play.google.com/store/apps/details?id=net.fdgames.ek.android");
            } else {
                Gdx.f1569net.openURI("http://itunes.apple.com/app/1091313127");
            }
            Settings.v();
        }
    }

    /* JADX INFO: compiled from: RateWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            x.this.setVisible(false);
            Settings.A(1, "GL_rate_noask");
            Settings.v();
        }
    }

    public x() {
        super("", Assets.g());
        TextButton textButton = new TextButton(GameString.b("RATING_YES", false), Assets.g(), "menuButton");
        TextButton textButton2 = new TextButton(GameString.b("RATING_NOT_NOW", false), Assets.g(), "menuButton");
        TextButton textButton3 = new TextButton(GameString.b("RATING_NEVER", false), Assets.g(), "menuButton");
        Image image = new Image(Assets.f("EK"));
        Label label = new Label(a.a.o("RATING_TEXT", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.T);
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(700.0f * fMin);
        setHeight(360.0f * fMin);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        label.setFontScale(fMin);
        label.setWrap(true);
        textButton.getLabel().setFontScale(fMin);
        textButton2.getLabel().setFontScale(fMin);
        textButton3.getLabel().setFontScale(fMin);
        setVisible(false);
        row().colspan(3).center();
        Cell cellAdd = add(image);
        float f2 = 96.0f * fMin;
        cellAdd.width(f2).height(f2).space(8.0f * fMin);
        row().colspan(3).center();
        add(label).width(580.0f * fMin).center().align(1);
        float f3 = 50.0f * fMin;
        row().spaceRight(25.0f * fMin).spaceTop(f3);
        float f4 = 180.0f * fMin;
        add(textButton2).height(f3).width(f4);
        add(textButton3).height(f3).width(fMin * 250.0f);
        add(textButton).height(f3).width(f4);
        textButton2.addListener(new a());
        textButton.addListener(new b());
        textButton3.addListener(new c());
    }
}
