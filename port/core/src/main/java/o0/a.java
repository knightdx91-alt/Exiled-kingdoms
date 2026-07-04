package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ArmorImage.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a extends Image {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Color f3439c = new Color(Color.DARK_GRAY);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final float f3440d = (Gdx.graphics.getHeight() * 1.0f) / 720.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final float f3441e = (Gdx.graphics.getWidth() * 1.0f) / 1280.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static float f3442f = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f3443g = 50;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3444a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private GlyphLayout f3445b = new GlyphLayout();

    public a() {
        setDrawable(new TextureRegionDrawable(Assets.a("shield")));
        if (ExiledKingdoms.f3378h) {
            f3442f = 0.8f;
            f3439c = new Color(Color.GOLDENROD);
            f3443g = 46;
        }
    }

    public final void a(int i2) {
        this.f3444a = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        super.draw(batch, f2);
        Color color = GameAssets.f3334j0.getCache().getColor();
        float scaleX = GameAssets.f3334j0.getScaleX();
        float scaleY = GameAssets.f3334j0.getScaleY();
        if (ExiledKingdoms.f3378h) {
            GameAssets.f3334j0.getData().setScale(0.99f);
        } else {
            GameAssets.f3334j0.getData().setScale(f3440d, f3441e);
        }
        GameAssets.f3334j0.getCache().setColor(f3439c);
        BitmapFont bitmapFont = GameAssets.f3334j0;
        String str = "" + this.f3444a + "";
        this.f3445b.setText(bitmapFont, str);
        GameAssets.f3334j0.draw(batch, Integer.toString(this.f3444a), (int) (((getWidth() / 2.0f) + getX()) - (r4.width / 2.0f)), (f3443g * f3442f) + getY());
        GameAssets.f3334j0.getCache().setColor(color);
        GameAssets.f3334j0.getData().setScale(scaleX, scaleY);
        validate();
    }
}
