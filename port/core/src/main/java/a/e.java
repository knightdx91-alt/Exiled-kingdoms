package a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: LicenseTable.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class e extends Table {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final float f7d = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Image f8a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Table f9b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f10c;

    public e(int i2) {
        Image image = new Image();
        this.f8a = image;
        Table table = new Table();
        this.f9b = table;
        setBackground(new NinePatchDrawable(GameAssets.P));
        float f2 = f7d;
        float f3 = 5.0f * f2;
        this.f10c = f3;
        setWidth(880.0f * f2);
        pad(f3);
        if (Settings.h() == 2) {
            image.setDrawable(new TextureRegionDrawable(Assets.f("available_ES")));
        } else {
            image.setDrawable(new TextureRegionDrawable(Assets.f("available")));
        }
        if (i2 == 0) {
            ExiledKingdoms.f3371a = true;
            image.setVisible(false);
        } else {
            image.setVisible(true);
        }
        table.clear();
        String str = i2 == 1 ? "FULL_FEATURE_" : "FREE_FEATURE_";
        int i3 = (int) (10.0f * f2);
        for (int i4 = 0; i4 < 3; i4++) {
            Image image2 = new Image();
            image2.setDrawable(Assets.g().getDrawable("default-rect-down"));
            Label label = new Label(a.l("[BLACK]", GameString.b(str + i4, false).replace("{p}", "[BLUE]" + f.f12k + "[]"), "[]"), GameAssets.S);
            label.setFontScale(f2);
            label.setWrap(true);
            table.row().spaceBottom((float) i3);
            float f4 = 12.0f * f2;
            table.add(image2).width(f4).height(f4).top().padTop(f3);
            table.add(label).width(525.0f * f2).padLeft(f3).top();
        }
        Image image3 = new Image();
        if (i2 == 0) {
            image3.setDrawable(new TextureRegionDrawable(Assets.f("free_license")));
        }
        if (i2 == 1) {
            image3.setDrawable(new TextureRegionDrawable(Assets.f("full_license")));
        }
        Cell cellAdd = add(image3);
        float f5 = 150.0f * f2;
        cellAdd.width(f5).height(f5);
        add(this.f8a).width(f5).height(f5).pad(this.f10c * 2.0f).spaceRight(15.0f * f2);
        add(this.f9b).width(f2 * 540.0f);
        center();
    }
}
