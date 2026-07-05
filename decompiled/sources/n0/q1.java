package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import net.fdgames.GameWorld.Areas;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: WorldMapImage.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class q1 extends Image {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final float f2861i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static final float f2862j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static final float f2863k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    static final float f2864l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    static final float f2865m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static Color f2866n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2867a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    TextureRegion f2868b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f2869c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f2870d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f2871e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f2872f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f2873g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f2874h;

    static {
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        f2861i = fMin;
        f2862j = 4.0f * fMin;
        f2863k = 2.0f * fMin;
        f2864l = (-500.0f) * fMin;
        f2865m = fMin * (-1370.0f);
        f2866n = new Color(1.0f, 1.0f, 1.0f, 0.4f);
    }

    public q1() {
        super(GameAssets.x0);
        this.f2867a = "";
        this.f2868b = GameAssets.f3357y;
        float f2 = f2861i;
        this.f2869c = (int) (150.0f * f2);
        setScale(f2 * 1.2f);
    }

    public final void a(String str) {
        this.f2867a = str;
        boolean zJ = Areas.j(str);
        int i2 = this.f2869c;
        if (zJ) {
            this.f2872f = i2 / 3;
            float f2 = (str.startsWith("NG") ? new Coords(21, 22) : str.startsWith("FT") ? new Coords(9, 25) : str.startsWith("NI") ? new Coords(17, 32) : str.startsWith("IM") ? new Coords(26, 37) : new Coords(0, 0)).f3287x;
            float f3 = this.f2872f;
            this.f2870d = f2 * f3;
            this.f2871e = r0.f3288y * f3;
            if (str.equals("IM")) {
                float f4 = this.f2870d;
                float f5 = this.f2872f;
                this.f2870d = f4 - (f5 / 2.0f);
                this.f2871e -= f5 / 2.5f;
            }
        } else {
            this.f2872f = i2;
            GameWorld.f3192f.getClass();
            float f6 = Areas.h(str).f3287x;
            float f7 = this.f2872f;
            float f8 = f6 * f7;
            this.f2870d = f8;
            float f9 = r5.f3288y * f7;
            this.f2871e = f9;
            this.f2873g = f8;
            this.f2874h = f9;
        }
        setPosition((Gdx.graphics.getWidth() / 2) - this.f2870d, (Gdx.graphics.getHeight() / 2) - this.f2871e);
        r1 r1VarB = r1.b();
        String strE = GameWorld.f3192f.e(this.f2867a);
        String strF = GameWorld.f3192f.f(this.f2867a);
        Areas areas = GameWorld.f3192f;
        String str2 = this.f2867a;
        areas.getClass();
        r1VarB.c(strE, strF, Areas.d(str2));
    }

    public final void b(int i2, int i3) {
        int i4 = (int) ((-getX()) + i2);
        int height = (int) (((-getY()) - i3) + Gdx.graphics.getHeight());
        Areas areas = GameWorld.f3192f;
        int i5 = this.f2869c;
        String strC = areas.c(i4 / i5, height / i5);
        if (strC.equals("")) {
            return;
        }
        GameWorld.f3192f.getClass();
        Coords coordsH = Areas.h(strC);
        this.f2873g = coordsH.f3287x * i5;
        this.f2874h = coordsH.f3288y * i5;
        this.f2867a = strC;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        float x2 = getX();
        float f3 = f2864l;
        if (x2 < f3) {
            setX(f3);
        }
        if (getX() > 0.0f) {
            setX(0.0f);
        }
        float y2 = getY();
        float f4 = f2865m;
        if (y2 < f4) {
            setY(f4);
        }
        if (getY() > 0.0f) {
            setY(0.0f);
        }
        float f5 = this.f2872f;
        super.draw(batch, f2);
        batch.setColor(Color.RED);
        TextureRegion textureRegion = this.f2868b;
        float x3 = getX() + this.f2870d;
        float y3 = getY() + this.f2871e;
        float f6 = f2862j;
        batch.draw(textureRegion, x3, y3, f5, f6);
        batch.draw(textureRegion, getX() + this.f2870d, getY() + this.f2871e, f6, f5);
        batch.draw(textureRegion, getX() + this.f2870d, ((getY() + this.f2871e) + f5) - f6, f5, f6);
        batch.draw(textureRegion, ((getX() + this.f2870d) + f5) - f6, getY() + this.f2871e, f6, f5);
        if (!Areas.j(this.f2867a)) {
            batch.setColor(f2866n);
            float x4 = getX() + this.f2873g;
            float y4 = getY() + this.f2874h;
            float f7 = this.f2869c;
            batch.draw(textureRegion, x4, y4, f7, f7);
            batch.setColor(Color.MAGENTA);
            float x5 = getX() + this.f2873g;
            float y5 = getY() + this.f2874h;
            float f8 = f2863k;
            batch.draw(textureRegion, x5, y5, f7, f8);
            batch.draw(textureRegion, getX() + this.f2873g, getY() + this.f2874h, f8, f7);
            batch.draw(textureRegion, getX() + this.f2873g, ((getY() + this.f2874h) + f7) - f8, f7, f8);
            batch.draw(textureRegion, ((getX() + this.f2873g) + f7) - f8, getY() + this.f2874h, f8, f7);
            batch.setColor(Color.WHITE);
        }
        r1 r1VarB = r1.b();
        String strE = GameWorld.f3192f.e(this.f2867a);
        String strF = GameWorld.f3192f.f(this.f2867a);
        Areas areas = GameWorld.f3192f;
        String str = this.f2867a;
        areas.getClass();
        r1VarB.c(strE, strF, Areas.d(str));
    }
}
