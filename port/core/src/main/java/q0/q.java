package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.GameEntities.Character;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: GenderImage.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class q extends Image {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static NinePatchDrawable f3943e = new NinePatchDrawable(GameAssets.P);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static NinePatchDrawable f3944f = new NinePatchDrawable(GameAssets.R);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final float f3945g = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextureRegionDrawable f3946a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextureRegionDrawable f3947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3948c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Character.Gender f3949d;

    public q(Character.Gender gender, boolean z2) {
        this.f3946a = null;
        this.f3947b = null;
        this.f3948c = z2;
        this.f3949d = gender;
        if (gender == Character.Gender.Male) {
            this.f3946a = new TextureRegionDrawable(Assets.a("male"));
            this.f3947b = new TextureRegionDrawable(Assets.a("male_disabled"));
        }
        if (gender == Character.Gender.Female) {
            this.f3946a = new TextureRegionDrawable(Assets.a("female"));
            this.f3947b = new TextureRegionDrawable(Assets.a("female_disabled"));
        }
    }

    public final void a(Character.Gender gender) {
        if (gender == this.f3949d) {
            this.f3948c = true;
        } else {
            this.f3948c = false;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (this.f3948c) {
            f3944f.draw(batch, getX(), getY(), getWidth(), getHeight());
        } else {
            f3943e.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (this.f3948c) {
            setDrawable(this.f3946a);
        } else {
            setDrawable(this.f3947b);
        }
        if (getDrawable() != null) {
            Drawable drawable = getDrawable();
            float x2 = getX();
            float f3 = f3945g;
            float f4 = 4.0f * f3;
            float f5 = f3 * 8.0f;
            drawable.draw(batch, x2 + f4, getY() + f4, getWidth() - f5, getHeight() - f5);
        }
        validate();
    }
}
