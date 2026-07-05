package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ADTProgressBar.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a extends Actor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static GlyphLayout f2516i = new GlyphLayout();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final float f2517j = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private NinePatchDrawable f2518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private NinePatchDrawable f2519b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2520c = 0.5f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2521d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Color f2522e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f2523f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f2524g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f2525h;

    public a(int i2, float f2) {
        if (i2 == 0) {
            this.f2519b = new NinePatchDrawable(GameAssets.D);
            this.f2522e = new Color(Color.WHITE);
        }
        if (i2 == 1) {
            this.f2519b = new NinePatchDrawable(GameAssets.E);
            this.f2522e = new Color(Color.BLACK);
        }
        if (i2 == 2) {
            this.f2519b = new NinePatchDrawable(GameAssets.F);
            this.f2522e = new Color(Color.WHITE);
        }
        this.f2523f = f2;
        float f3 = f2 * f2517j;
        this.f2524g = f3;
        this.f2525h = f3;
        this.f2518a = new NinePatchDrawable(GameAssets.I);
    }

    public final void a(float f2) {
        this.f2520c = f2;
        if (f2 < 0.02f) {
            this.f2520c = 0.0f;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        this.f2518a.draw(batch, getX(), getY(), getScaleX() * getWidth(), getScaleY() * getHeight());
        if (this.f2520c > 0.0f) {
            this.f2519b.draw(batch, getX(), getY(), getScaleX() * getWidth() * this.f2520c, getScaleY() * getHeight());
        }
        if (this.f2523f <= 0.0f || this.f2521d.equals("")) {
            return;
        }
        boolean z2 = ExiledKingdoms.f3378h;
        GlyphLayout glyphLayout = f2516i;
        Color color = this.f2522e;
        if (!z2) {
            Color color2 = GameAssets.f3328g0.getCache().getColor();
            float scaleX = GameAssets.f3328g0.getScaleX();
            float scaleY = GameAssets.f3328g0.getScaleY();
            GameAssets.f3328g0.getData().setScale(this.f2524g, this.f2525h);
            GameAssets.f3328g0.getCache().setColor(color);
            glyphLayout.setText(GameAssets.f3328g0, this.f2521d);
            GameAssets.f3328g0.draw(batch, this.f2521d, ((getWidth() / 2.0f) + getX()) - (glyphLayout.width / 2.0f), (getHeight() + getY()) - (glyphLayout.height / 2.0f));
            GameAssets.f3328g0.getCache().setColor(color2);
            GameAssets.f3328g0.getData().setScale(scaleX, scaleY);
            return;
        }
        BitmapFont bitmapFont = GameAssets.f3322d0;
        Color color3 = bitmapFont.getCache().getColor();
        float scaleX2 = bitmapFont.getScaleX();
        float scaleY2 = bitmapFont.getScaleY();
        bitmapFont.getData().setScale(0.5f, 0.5f);
        bitmapFont.getCache().setColor(color);
        glyphLayout.setText(bitmapFont, this.f2521d);
        bitmapFont.draw(batch, this.f2521d, ((getWidth() / 2.0f) + getX()) - (glyphLayout.width / 2.0f), (glyphLayout.height / 2.0f) + (getHeight() / 2.0f) + getY());
        bitmapFont.getCache().setColor(color3);
        bitmapFont.getData().setScale(scaleX2, scaleY2);
    }

    public final void setText(String str) {
        this.f2521d = str;
    }
}
