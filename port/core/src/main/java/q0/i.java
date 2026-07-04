package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: CreditsWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class i extends Window {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static float f3913b = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static float f3914c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Table f3915a;

    /* JADX INFO: compiled from: CreditsWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            i.this.setVisible(false);
            return true;
        }
    }

    public i() {
        super("", Assets.g());
        Image image = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/ui/logo.png"))));
        n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
        Table table = new Table();
        this.f3915a = table;
        ScrollPane scrollPane = new ScrollPane(table);
        Image image2 = new Image();
        setVisible(false);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(f3913b * 800.0f);
        setHeight(f3913b * 500.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 1.2f);
        f3914c = f3913b;
        if (ExiledKingdoms.f3378h) {
            f3913b = 0.8f;
            f3914c = 1.0f;
            setWidth(640.0f);
            setHeight(400.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        scrollPane.setForceScroll(false, true);
        scrollPane.setScrollbarsOnTop(true);
        image2.setDrawable(new NinePatchDrawable(GameAssets.P));
        row();
        add(image).center().width(f3913b * 384.0f).height(f3913b * 135.0f);
        row();
        add(scrollPane).fill().expand().top().center();
        row().align(4).center().padBottom(f3913b * 10.0f);
        add(tVar).bottom().width(f3913b * 200.0f);
        tVar.addListener(new a());
    }

    public final void a() {
        setVisible(true);
        Table table = this.f3915a;
        table.clearChildren();
        table.align(8).align(2).center();
        table.row().top().pad(f3913b * 5.0f).center();
        table.setWidth(f3913b * 800.0f);
        table.row().top().pad(f3913b * 5.0f).center();
        Label label = new Label(GameString.b("CREATED_BY", false), Assets.g(), "menuLabelStyle");
        label.setFontScale(f3914c);
        table.add(label);
        table.row().top().pad(0.0f).center();
        Label label2 = new Label("David Ballestrino", Assets.g(), "menuLabelStrongVeryLargeStyle");
        label2.setFontScale(f3914c);
        table.add(label2);
        table.row().spaceTop(f3913b * 20.0f);
        Label label3 = new Label(a.a.o("OPEN_ASSETS", false, new StringBuilder("[BLACK]"), ":[]"), GameAssets.S);
        label3.setWrap(true);
        label3.setFontScale(f3914c);
        table.add(label3).center().width(f3913b * 600.0f);
        table.row().spaceTop(f3913b * 10.0f);
        Label label4 = new Label("[BLUE]http://www.exiledkingdoms.com/ek_credits.html[]", GameAssets.S);
        label4.setWrap(true);
        label4.setFontScale(f3914c);
        table.add(label4).center().center().width(f3913b * 600.0f);
        label4.addListener(new j());
    }
}
