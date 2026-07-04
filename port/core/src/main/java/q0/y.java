package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.GameWorld.BasicGameData;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: SlotDescriptionTable.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class y extends n0.t {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static float f4019k = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static float f4020l;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Label f4021f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Label f4022g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Image f4023h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f4024i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4025j;

    public y(int i2) {
        super("", Assets.g(), "menuButton");
        Label label = new Label("", GameAssets.T);
        this.f4021f = label;
        Label label2 = new Label("", GameAssets.T);
        this.f4022g = label2;
        this.f4023h = new Image();
        f4020l = f4019k;
        if (ExiledKingdoms.f3378h) {
            f4019k = 0.8f;
            f4020l = 1.0f;
        }
        float f2 = f4019k;
        this.f4024i = 5.0f * f2;
        setHeight(f2 * 82.0f);
        label.setFontScale(f4020l);
        label2.setFontScale(f4020l);
        label.setWrap(true);
        label2.setWrap(true);
        this.f4025j = i2 + 1;
    }

    public final void f(BasicGameData basicGameData) {
        clearChildren();
        int i2 = this.f4025j;
        Image image = this.f4023h;
        Label label = this.f4022g;
        Label label2 = this.f4021f;
        if (basicGameData == null) {
            a.a.x("EMPTY", false, a.a.r(i2, "", ".[WHITE] < "), " >[]", label2);
            label.setText("");
            image.setDrawable(new TextureRegionDrawable(GameAssets.f3358z));
            setDisabled(true);
            row().center().width(f4019k * 190.0f);
            add(label2).align(1).center().expand();
        } else if (basicGameData.corrupted) {
            a.a.x("INCOMPATIBLE_FILE", false, a.a.r(i2, "", ".[WHITE] < "), " >[]", label2);
            label.setText("");
            image.setDrawable(new TextureRegionDrawable(GameAssets.f3358z));
            setDisabled(true);
            row().center().width(f4019k * 300.0f);
            add(label2).align(1).center().expand();
        } else {
            StringBuilder sbR = a.a.r(i2, "", ". [BLUE]");
            sbR.append(basicGameData.name);
            sbR.append("[]");
            label2.setText(sbR.toString());
            label.setText("[WHITE]" + basicGameData.description + "[]");
            image.setDrawable(new TextureRegionDrawable(Assets.n(basicGameData.gender, basicGameData.portraitIndex)));
            setBackground(new NinePatchDrawable(GameAssets.Q));
            setDisabled(false);
            left();
            add(image).height(f4019k * 72.0f).width(f4019k * 72.0f).left().top().left();
            Table table = new Table();
            table.add(label2);
            table.row();
            table.add(label);
            add(table).spaceLeft(this.f4024i);
        }
        pack();
    }
}
