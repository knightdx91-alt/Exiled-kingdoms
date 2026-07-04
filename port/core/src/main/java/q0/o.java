package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: DifficultyImage.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class o extends Image {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static NinePatchDrawable f3929f = new NinePatchDrawable(GameAssets.P);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static NinePatchDrawable f3930g = new NinePatchDrawable(GameAssets.R);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final float f3931h = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextureRegionDrawable f3932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextureRegionDrawable f3933b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextureRegionDrawable f3934c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3935d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3936e;

    public o(int i2, boolean z2) {
        this.f3932a = null;
        this.f3933b = null;
        this.f3934c = null;
        this.f3935d = false;
        if (i2 == 0) {
            this.f3932a = new TextureRegionDrawable(Assets.a("normal"));
            this.f3933b = new TextureRegionDrawable(Assets.a("normal_disabled"));
        }
        if (i2 == 1) {
            this.f3932a = new TextureRegionDrawable(Assets.a("hard"));
            this.f3933b = new TextureRegionDrawable(Assets.a("hard_disabled"));
        }
        if (i2 == 2) {
            this.f3932a = new TextureRegionDrawable(Assets.a("ironman"));
            this.f3933b = new TextureRegionDrawable(Assets.a("ironman_disabled"));
        }
        if (i2 == 3) {
            this.f3932a = new TextureRegionDrawable(Assets.a("easy"));
            this.f3933b = new TextureRegionDrawable(Assets.a("easy_disabled"));
        }
        if (i2 == 4) {
            this.f3932a = new TextureRegionDrawable(Assets.a("story"));
            this.f3933b = new TextureRegionDrawable(Assets.a("story_disabled"));
        }
        this.f3934c = new TextureRegionDrawable(Assets.a("locked_gray"));
        this.f3935d = z2;
        this.f3936e = i2;
    }

    public final void a(int i2) {
        if (i2 == this.f3936e) {
            this.f3935d = true;
        } else {
            this.f3935d = false;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (this.f3935d) {
            f3930g.draw(batch, getX(), getY(), getWidth(), getHeight());
        } else {
            f3929f.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (this.f3935d) {
            setDrawable(this.f3932a);
        } else if (this.f3936e != 2 || ExiledKingdoms.f3371a) {
            setDrawable(this.f3933b);
        } else {
            setDrawable(this.f3934c);
        }
        if (getDrawable() != null) {
            Drawable drawable = getDrawable();
            float x2 = getX();
            float f3 = f3931h;
            float f4 = 4.0f * f3;
            float f5 = f3 * 8.0f;
            drawable.draw(batch, x2 + f4, getY() + f4, getWidth() - f5, getHeight() - f5);
        }
        validate();
    }
}
