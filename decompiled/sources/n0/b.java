package n0;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: ADTVerticalProgressBar.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b extends Actor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private NinePatchDrawable f2533a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private NinePatchDrawable f2534b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2535c = 0.5f;

    public b(int i2) {
        if (i2 == 0) {
            this.f2534b = new NinePatchDrawable(GameAssets.D);
        }
        if (i2 == 1) {
            this.f2534b = new NinePatchDrawable(GameAssets.E);
        }
        if (i2 == 2) {
            this.f2534b = new NinePatchDrawable(GameAssets.F);
        }
        this.f2533a = new NinePatchDrawable(GameAssets.I);
    }

    public final void a(float f2) {
        this.f2535c = f2;
        if (f2 < 0.05f) {
            this.f2535c = 0.0f;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        this.f2533a.draw(batch, getX(), getY(), getScaleX() * getWidth(), getScaleY() * getHeight());
        if (this.f2535c > 0.0f) {
            this.f2534b.draw(batch, getX(), getY(), getScaleX() * getWidth(), getScaleY() * getHeight() * this.f2535c);
        }
    }
}
