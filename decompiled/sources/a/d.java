package a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: DonateWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d extends Window {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static float f3c = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ int f4d = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextButton f5a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f6b;

    public d() {
        super("", Assets.g());
        TextButton textButton = new TextButton(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f5a = textButton;
        Image image = new Image(Assets.f("donate1"));
        Image image2 = new Image(Assets.f("donate2"));
        Image image3 = new Image(Assets.f("donate3"));
        Label label = new Label("USD 1", Assets.g(), "menuLabelStrongStyle");
        Label label2 = new Label("USD 2.5", Assets.g(), "menuLabelStrongStyle");
        Label label3 = new Label("USD 5", Assets.g(), "menuLabelStrongStyle");
        Label label4 = new Label("Not licensed", GameAssets.S);
        this.f6b = label4;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(Gdx.graphics.getWidth() * 0.65f);
        setHeight(Gdx.graphics.getHeight() * 0.61f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.8f);
        label4.setWrap(true);
        float f2 = f3c;
        label4.setFontScale(f2);
        Image image4 = new Image(Assets.f("EK"));
        center().top();
        float f3 = f2 * 12.0f;
        row().colspan(3).padTop(f3);
        float f4 = 96.0f * f2;
        add(image4).center().width(f4).height(f4);
        row().space(10.0f * f2).height(70.0f * f2).colspan(3);
        label4.setAlignment(1);
        add(label4).width(getWidth() * 0.94f).center().padTop(f3);
        row().space(2.0f * f2).padTop(22.0f * f2);
        float f5 = 72.0f * f2;
        float f6 = 120.0f * f2;
        add(image).width(f5).height(f5).padLeft(f6);
        add(image2).width(f5).height(f5);
        add(image3).width(f5).height(f5).padRight(f6);
        row();
        add(label).padLeft(f6);
        add(label2);
        add(label3).padRight(f6);
        float f7 = 1.33f * f2;
        label.setFontScale(f7);
        label2.setFontScale(f7);
        label3.setFontScale(f7);
        row().height(50.0f * f2).spaceTop(f3).colspan(3);
        textButton.getLabel().setFontScale(0.9f * f2);
        add(textButton).width(f2 * 240.0f);
        textButton.clearListeners();
        textButton.addListener(new b(this));
        image.addListener(new c(this, 0));
        image2.addListener(new c(this, 1));
        image3.addListener(new c(this, 2));
        setVisible(false);
    }

    public final void a() {
        a.x("DONATE_TEXT", false, new StringBuilder("[BLACK]"), "[]", this.f6b);
        Settings.e("don1");
        Settings.e("don2");
        Settings.e("don3");
        this.f5a.setText(GameString.b("EXIT", false));
        setVisible(true);
    }
}
