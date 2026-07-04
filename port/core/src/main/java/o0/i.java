package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: InventorySlotImage.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class i extends Image {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Color f3524m = new Color(0.98039216f, 0.54901963f, 0.23529412f, 1.0f);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Color f3525n = new Color(Color.YELLOW);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Color f3526o = new Color(Color.CYAN);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final float f3527p = (Gdx.graphics.getHeight() * 0.75f) / 720.0f;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final float f3528q = (Gdx.graphics.getWidth() * 0.75f) / 1280.0f;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static float f3529r = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
    public static Color s = new Color();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static boolean f3530t = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public NinePatchDrawable f3531a = new NinePatchDrawable(GameAssets.P);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextureRegionDrawable f3532b = new TextureRegionDrawable(Assets.a("empty_slot_bg"));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextureRegionDrawable f3533c = new TextureRegionDrawable(Assets.a("redcross"));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextureRegionDrawable f3534d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BitmapFont f3535e = GameAssets.f3336k0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3536f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3537g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3538h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3539i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3540j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f3541k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3542l = false;

    public final void a(int i2) {
        if (ExiledKingdoms.f3378h) {
            f3529r = 1.0f;
        }
        int i3 = 0;
        this.f3542l = false;
        this.f3536f = i2;
        if (i2 != 0) {
            TextureRegion textureRegionA = null;
            this.f3534d = null;
            while (true) {
                Item[] itemArr = Rules.f3247a;
                if (i3 >= itemArr.length) {
                    break;
                }
                Item item = itemArr[i3];
                if (item.item_ID == i2) {
                    textureRegionA = Assets.a(item.icon);
                    break;
                }
                i3++;
            }
            this.f3534d = new TextureRegionDrawable(textureRegionA);
            if (Rules.f(this.f3536f) != null) {
                Item itemF = Rules.f(this.f3536f);
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                this.f3542l = itemF.g(GameData.v().player.sheet);
            } else {
                System.out.println("Can't find item id " + this.f3536f);
            }
        }
    }

    public final void b(int i2) {
        this.f3537g = i2;
    }

    public final void c(int i2) {
        this.f3538h = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        TextureRegionDrawable textureRegionDrawable;
        Color color = s;
        color.set(batch.getColor());
        if (this.f3540j) {
            batch.setColor(f3524m);
        }
        if (this.f3542l) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (!GameData.v().player.F0()) {
                batch.setColor(color.f1680r, color.f1679g, color.f1678b, 0.3f);
            }
        }
        this.f3531a.draw(batch, getX(), getY(), getWidth(), getHeight());
        this.f3532b.draw(batch, (f3529r * 4.0f) + getX(), (f3529r * 4.0f) + getY(), getWidth() - (f3529r * 8.0f), getHeight() - (f3529r * 8.0f));
        batch.setColor(color);
        if (this.f3536f != 0 && (textureRegionDrawable = this.f3534d) != null) {
            setDrawable(textureRegionDrawable);
            if (this.f3542l && !n0.z.v().V.O().F0()) {
                batch.setColor(color.f1680r, color.f1679g, color.f1678b, 0.3f);
            }
            if (getDrawable() != null) {
                getDrawable().draw(batch, (f3529r * 4.0f) + getX(), (f3529r * 4.0f) + getY(), getWidth() - (f3529r * 8.0f), getHeight() - (f3529r * 8.0f));
            }
            batch.setColor(color);
        }
        if (this.f3541k && this.f3536f != 0) {
            this.f3533c.draw(batch, (f3529r * 4.0f) + getX(), (f3529r * 4.0f) + getY(), getWidth() - (f3529r * 8.0f), getHeight() - (f3529r * 8.0f));
        }
        int i2 = this.f3537g;
        BitmapFont bitmapFont = this.f3535e;
        float f3 = f3528q;
        float f4 = f3527p;
        if (i2 != 0) {
            Color color2 = bitmapFont.getCache().getColor();
            float scaleX = bitmapFont.getScaleX();
            float scaleY = bitmapFont.getScaleY();
            boolean z2 = ExiledKingdoms.f3378h;
            Color color3 = f3525n;
            if (z2) {
                bitmapFont.getData().setScale(0.5f);
                bitmapFont.getCache().setColor(color3);
                bitmapFont.draw(batch, Integer.toString(this.f3537g), (f3529r * 2.0f) + getX(), (f3529r * 18.0f) + getY());
            } else {
                bitmapFont.getData().setScale(f4, f3);
                bitmapFont.getCache().setColor(color3);
                bitmapFont.draw(batch, Integer.toString(this.f3537g), (f3529r * 2.0f) + getX(), (f3529r * 30.0f) + getY());
            }
            bitmapFont.getCache().setColor(color2);
            bitmapFont.getData().setScale(scaleX, scaleY);
        }
        if (this.f3538h > 1) {
            Color color4 = bitmapFont.getCache().getColor();
            float scaleX2 = bitmapFont.getScaleX();
            float scaleY2 = bitmapFont.getScaleY();
            boolean z3 = ExiledKingdoms.f3378h;
            Color color5 = f3526o;
            if (z3) {
                bitmapFont.getData().setScale(0.5f);
                bitmapFont.getCache().setColor(color5);
                bitmapFont.draw(batch, Integer.toString(this.f3538h), (f3529r * 2.0f) + getX(), (getHeight() * f3529r) + getY());
            } else {
                bitmapFont.getData().setScale(f4, f3);
                bitmapFont.getCache().setColor(color5);
                bitmapFont.draw(batch, Integer.toString(this.f3538h), (f3529r * 2.0f) + getX(), (f3529r * 80.0f) + getY());
            }
            bitmapFont.getCache().setColor(color4);
            bitmapFont.getData().setScale(scaleX2, scaleY2);
        }
        if (ExiledKingdoms.f3378h && f3530t && !GameLevel.l() && n0.z.v().w() == 0 && this.f3536f != 0) {
            Color color6 = bitmapFont.getCache().getColor();
            float scaleX3 = bitmapFont.getScaleX();
            float scaleY3 = bitmapFont.getScaleY();
            if (ExiledKingdoms.f3378h) {
                bitmapFont.getData().setScale(0.5f);
                bitmapFont.getCache().setColor(n0.z.f2925i0);
                try {
                    bitmapFont.draw(batch, Settings.g(this.f3539i), (f3529r * 26.0f) + getX(), (f3529r * 2.0f) + getY());
                } catch (Exception e2) {
                    com.badlogic.gdx.utils.GdxNativesLoader.d("inventoryslot draw() EXCEPTION -:  " + e2.getMessage());
                    System.out.println(" inventoryslot draw() EXCEPTION -:  " + e2.getMessage());
                }
            }
            bitmapFont.getCache().setColor(color6);
            bitmapFont.getData().setScale(scaleX3, scaleY3);
        }
        validate();
    }
}
