package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: DesktopBackupWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class n extends Window implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Label f3922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private n0.t f3923b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private n0.t f3924c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private n0.t f3925d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Label f3926e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Stage f3927f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private z f3928g;

    public n(z zVar) {
        super("", Assets.g());
        Label label = new Label(GameString.b("BACKUP_TITLE", false), Assets.g(), "menuLabelStrongLargeStyle");
        this.f3922a = label;
        n0.t tVar = new n0.t(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f3923b = tVar;
        n0.t tVar2 = new n0.t("", Assets.g(), "menuButton");
        this.f3924c = tVar2;
        n0.t tVar3 = new n0.t("", Assets.g(), "menuButton");
        this.f3925d = tVar3;
        Label label2 = new Label(GameString.b("BACKUP_INFO_DESKTOP", false), GameAssets.S);
        this.f3926e = label2;
        Image image = new Image();
        Image image2 = new Image();
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(520.0f);
        setHeight(430.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.8f);
        label2.setFontScale(1.0f);
        label2.setWrap(true);
        this.f3928g = zVar;
        image.setDrawable(new NinePatchDrawable(GameAssets.P));
        image2.setDrawable(new NinePatchDrawable(GameAssets.P));
        label.setFontScale(1.0f);
        center();
        row().height(22.0f).spaceTop(4.0f).colspan(2);
        add(label).center();
        row().space(6.0f).colspan(2);
        add(label2).center().width(getWidth() * 0.9f).pad(8.0f);
        row().colspan(2).left().pad(0.0f).height(2.0f).width(getWidth() - 6.0f);
        add(image).space(15.0f);
        row().height(52.0f).spaceTop(15.0f).colspan(2).center();
        add(tVar2).width(480.0f);
        row().height(52.0f).spaceTop(15.0f).colspan(2).center();
        add(tVar3).width(480.0f);
        row().colspan(2).left().pad(0.0f).height(2.0f).width(getWidth() - 6.0f);
        add(image2).space(20.0f);
        tVar2.addListener(new k(this));
        tVar3.addListener(new l(this));
        row().colspan(2).height(40.0f).top();
        add(tVar).width(130.0f).height(40.0f).space(4.0f);
        tVar.clearListeners();
        tVar.addListener(new m(this));
        setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str) {
        return GameString.b(str, false).replace("##BACKUP##", Gdx.files.getExternalStoragePath() + "EK.bak");
    }

    @Override // q0.r
    public final void show(Stage stage) {
        this.f3927f = stage;
        ExiledKingdoms.i();
        this.f3922a.setText(GameString.b("BACKUP_TITLE", false));
        this.f3923b.setText(GameString.b("EXIT", false));
        n0.t tVar = this.f3924c;
        tVar.setText(d("BACKUP"));
        this.f3925d.setText(GameString.b("RESTORE_BACKUP", false));
        this.f3926e.setText("[BLACK]" + d("BACKUP_INFO_DESKTOP") + "[]");
        if (Serializer.E()) {
            tVar.setDisabled(false);
        } else {
            tVar.setDisabled(true);
        }
        setVisible(true);
    }
}
