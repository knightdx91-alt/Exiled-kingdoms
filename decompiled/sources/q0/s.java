package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: LanguageImage.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class s extends Image {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static NinePatchDrawable f3950d = new NinePatchDrawable(GameAssets.R);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final float f3951e = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextureRegionDrawable f3952a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3953b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3954c;

    public s(int i2, int i3) {
        this(i2);
        this.f3954c = true;
    }

    public final void a(int i2) {
        this.f3953b = i2;
        if (i2 == 1) {
            this.f3952a = new TextureRegionDrawable(Assets.a("english"));
        }
        if (this.f3953b == 2) {
            this.f3952a = new TextureRegionDrawable(Assets.a("spanish"));
        }
        if (this.f3953b == 3) {
            this.f3952a = new TextureRegionDrawable(Assets.a("russian"));
        }
        if (this.f3953b == 4) {
            this.f3952a = new TextureRegionDrawable(Assets.a("portuguese"));
        }
        if (this.f3953b == 5) {
            this.f3952a = new TextureRegionDrawable(Assets.a("german"));
        }
        if (this.f3953b == 6) {
            this.f3952a = new TextureRegionDrawable(Assets.a("polish"));
        }
        if (this.f3953b == 8) {
            this.f3952a = new TextureRegionDrawable(Assets.a("turkish"));
        }
        if (this.f3953b == 7) {
            this.f3952a = new TextureRegionDrawable(Assets.a("czech"));
        }
        if (this.f3953b == 10) {
            this.f3952a = new TextureRegionDrawable(Assets.a("french"));
        }
        if (this.f3953b == 9) {
            this.f3952a = new TextureRegionDrawable(Assets.a("italian"));
        }
        setDrawable(this.f3952a);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (!this.f3954c && this.f3953b == Settings.h()) {
            f3950d.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (getDrawable() != null) {
            Drawable drawable = getDrawable();
            float x2 = getX();
            float f3 = f3951e;
            float f4 = 4.0f * f3;
            float f5 = f3 * 8.0f;
            drawable.draw(batch, x2 + f4, getY() + f4, getWidth() - f5, getHeight() - f5);
        }
        validate();
    }

    public s(int i2) {
        this.f3952a = null;
        this.f3954c = false;
        a(i2);
    }
}
