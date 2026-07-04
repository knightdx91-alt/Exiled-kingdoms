package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import net.fdgames.GameWorld.GameData;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: PlayerButton.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class e1 extends v {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static float f2625l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static float f2626m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static float f2627n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static Color f2628o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static float f2629p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static float f2630q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static GlyphLayout f2631r;
    public static int s;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final TextureRegion f2632k;

    static {
        new Color(0.0f, 0.0f, 0.0f, 1.0f);
        float height = (Gdx.graphics.getHeight() * 1.0f) / 720.0f;
        f2625l = height;
        f2626m = height;
        f2627n = Gdx.graphics.getHeight() / 720.0f;
        f2631r = new GlyphLayout();
        s = 0;
    }

    public e1(ImageButton.ImageButtonStyle imageButtonStyle, Color color) {
        super(imageButtonStyle, color, false);
        this.f2632k = GameAssets.f3357y;
        if (Settings.M() && ExiledKingdoms.f3378h) {
            s = 7;
        }
        if (ExiledKingdoms.f3378h) {
            f2627n = 1.0f;
            f2626m = 1.0f;
            f2625l = 1.0f;
        }
    }

    @Override // n0.v, com.badlogic.gdx.scenes.scene2d.ui.ImageButton, com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        super.draw(batch, f2);
        if (GameData.v() == null || GameData.v().player == null || GameData.v().player.sheet == null) {
            return;
        }
        f2628o = GameAssets.f3326f0.getCache().getColor();
        f2629p = GameAssets.f3326f0.getScaleX();
        f2630q = GameAssets.f3326f0.getScaleY();
        GameAssets.f3326f0.getData().setScale(f2625l, f2626m);
        GameAssets.f3326f0.getCache().setColor(Color.BLACK);
        int iF = GameData.v().player.sheet.stats.f();
        GlyphLayout glyphLayout = f2631r;
        glyphLayout.setText(GameAssets.f3326f0, "" + iF);
        boolean z2 = ExiledKingdoms.f3378h;
        TextureRegion textureRegion = this.f2632k;
        if (z2) {
            batch.setColor(Color.YELLOW);
            batch.draw(textureRegion, getX() + 1.0f, (getHeight() - 25.0f) + getY() + 3.0f, 22.0f, 18.0f);
            batch.setColor(Color.WHITE);
            GameAssets.f3326f0.draw(batch, a.a.h(iF, ""), (getX() + 12.0f) - (glyphLayout.width / 2.0f), (getHeight() + getY()) - (12 - s));
        } else {
            batch.setColor(Color.YELLOW);
            float x2 = (f2627n * 3.0f) + getX();
            float y2 = getY() - (f2627n * 4.0f);
            float height = getHeight();
            float f3 = f2627n;
            batch.draw(textureRegion, x2, (height - (25.0f * f3)) + y2, f3 * 28.0f, f3 * 27.0f);
            batch.setColor(Color.WHITE);
            GameAssets.f3326f0.draw(batch, a.a.h(iF, ""), ((f2627n * 17.0f) + getX()) - (glyphLayout.width / 2.0f), (getHeight() + getY()) - (f2627n * 8.0f));
        }
        GameAssets.f3326f0.getCache().setColor(f2628o);
        GameAssets.f3326f0.getData().setScale(f2629p, f2630q);
    }
}
