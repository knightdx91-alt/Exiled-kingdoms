package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import net.fdgames.GameWorld.GameData;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: RecoverButton.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class f1 extends ImageButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Color f2647b = new Color(0.19607843f, 0.8235294f, 0.27450982f, 1.0f);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final float f2648c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final float f2649d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final float f2650e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Color f2651f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static float f2652g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static float f2653h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BitmapFont f2654a;

    static {
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        f2648c = fMin;
        f2649d = fMin * 1.0f;
        f2650e = fMin * 1.0f;
    }

    public f1() {
        super(GameAssets.u0);
        this.f2654a = GameAssets.f3334j0;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ImageButton, com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        try {
            if (GameData.v().player.G1() < 1) {
                setDisabled(true);
            } else {
                setDisabled(false);
            }
            getImage().setSize(getWidth() * 0.5f, getHeight() * 0.5f);
            Image image = getImage();
            float f3 = f2648c;
            image.setPosition(f3 * 4.0f, (getHeight() * 0.5f) - (f3 * 4.0f));
            super.draw(batch, f2);
            BitmapFont bitmapFont = this.f2654a;
            f2651f = bitmapFont.getCache().getColor();
            f2652g = bitmapFont.getScaleX();
            f2653h = bitmapFont.getScaleY();
            if (!ExiledKingdoms.f3378h) {
                bitmapFont.getData().setScale(f2649d, f2650e);
            }
            if (GameData.v().player.G1() == 0) {
                getImage().setColor(Color.BLACK);
                bitmapFont.getCache().setColor(Color.GRAY);
            } else {
                getImage().setColor(Color.WHITE);
                bitmapFont.getCache().setColor(f2647b);
            }
            bitmapFont.draw(batch, "x" + GameData.v().player.G1(), ((getWidth() / 2.0f) + getX()) - (4.0f * f3), ((getHeight() / 2.0f) + getY()) - (f3 * 0.0f));
            bitmapFont.getCache().setColor(f2651f);
            bitmapFont.getData().setScale(f2652g, f2653h);
        } catch (NullPointerException unused) {
            setDisabled(true);
        }
    }
}
