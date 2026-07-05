package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.GPGSUpdate;

/* JADX INFO: compiled from: EndingWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class p extends Window {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final float f2849f = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2850a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextButton f2851b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2852c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f2853d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Label f2854e;

    /* JADX INFO: compiled from: EndingWindow.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            p.this.a();
        }
    }

    public p() {
        super("", Assets.g());
        TextButton textButton = new TextButton(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f2851b = textButton;
        setMovable(false);
        setModal(true);
        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        setVisible(false);
        Label label = new Label("", GameAssets.Y);
        this.f2854e = label;
        float f2 = f2849f;
        label.setFontScale(f2);
        label.setPosition(10.0f * f2, 130.0f * f2);
        label.setWrap(true);
        label.setWidth(getWidth() * 0.96f);
        label.setAlignment(1);
        label.setText(GameString.b("ENDING_1", false).replace("\"", ""));
        label.setColor(Color.CYAN);
        addActor(label);
        textButton.setWidth(160.0f * f2);
        textButton.getLabel().setFontScale(f2);
        textButton.setPosition((getWidth() / 2.0f) - (textButton.getWidth() / 2.0f), f2 * 5.0f);
        addActor(textButton);
        textButton.addListener(new a());
    }

    public final void a() {
        setVisible(false);
        z.v().f2945e0 = 0;
        if (this.f2850a == 3) {
            GameLevel.n(false);
            GameData.v().player.Y1(new Transition("I3", 2));
            GameData.v().player.sheet.S();
            return;
        }
        com.badlogic.gdx.e eVar = (com.badlogic.gdx.e) Gdx.app.getApplicationListener();
        GameData.v().getClass();
        GameData.X();
        GameConsole.d();
        eVar.c(new l0.e(eVar));
    }

    public final void b(int i2) {
        setBackground(new Image(i2 == 2 ? GameAssets.e("data/ui/ending2.png") : i2 == 3 ? GameAssets.e("data/ui/ending3.png") : GameAssets.e("data/ui/ending1.png")).getDrawable());
        this.f2850a = i2;
        GameLevel.n(true);
        this.f2852c = (float) GameData.v().realTime;
        TextButton textButton = this.f2851b;
        textButton.setText(GameString.b("EXIT", false));
        Label label = this.f2854e;
        label.setVisible(false);
        textButton.setVisible(false);
        GPGSUpdate.c(false);
        if (this.f2850a == 1) {
            label.setText(GameString.b("ENDING_1", false).replace("\"", ""));
            Assets.f3309a.t("something_near");
        }
        if (this.f2850a == 2) {
            label.setText(GameString.b("ENDING_2", false).replace("\"", ""));
            Assets.f3309a.t("magical_forest");
        }
        if (this.f2850a == 3) {
            label.setText(GameString.b("ENDING_3", false).replace("\"", ""));
            Assets.f3309a.t("intro_dragons");
        }
        setVisible(true);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float f3 = (float) (GameData.v().realTime - ((double) this.f2852c));
        this.f2853d = f3;
        float f4 = f3 < 2.4f ? f3 / 2.4f : 1.0f;
        setColor(f4, f4, f4, 1.0f);
        if (this.f2853d > 2.5f) {
            Label label = this.f2854e;
            label.setVisible(true);
            label.setColor(label.getColor().f1680r, label.getColor().f1679g, label.getColor().f1678b, this.f2853d - 2.5f);
        }
        if (this.f2853d > 5.0f) {
            this.f2851b.setVisible(true);
        }
        super.draw(batch, f2);
    }
}
