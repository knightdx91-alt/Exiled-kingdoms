package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: FloatingText.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class w {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final float f2904m = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2905a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2906b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2907c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2908d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Color f2909e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f2910f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2911g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2912h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Color f2913i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f2914j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Coords f2915k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public MapActor f2916l;

    public w(int i2, String str, float f2, Color color, float f3, float f4) {
        float fU = GameData.v().u();
        this.f2907c = fU;
        this.f2906b = fU + f2;
        this.f2905a = str;
        this.f2908d = i2;
        this.f2909e = color;
        this.f2910f = f3;
        this.f2912h = f4 * 100.0f;
        this.f2916l = GameLevel.g(i2);
        this.f2915k = new Coords();
    }

    public final int a() {
        int i2 = this.f2911g + 48;
        double d2 = this.f2907c;
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        return i2 - ((int) ((d2 - GameData.v().s()) * ((double) this.f2912h)));
    }

    public final void b(SpriteBatch spriteBatch) {
        if (c().booleanValue()) {
            return;
        }
        MapActor mapActor = this.f2916l;
        int i2 = mapActor.f3092x;
        int i3 = mapActor.f3093y;
        int i4 = i2 + i3;
        int i5 = ((i3 + 64) - i2) / 2;
        Coords coords = this.f2915k;
        coords.f3287x = i4;
        coords.f3288y = i5;
        coords.f3288y = a() + i5;
        int i6 = coords.f3287x;
        String str = this.f2905a;
        float length = str.length();
        float f2 = f2904m;
        coords.f3287x = i6 - ((int) ((length * f2) * 4.0f));
        this.f2913i = GameAssets.f3322d0.getCache().getColor();
        this.f2914j = GameAssets.f3322d0.getScaleX();
        GameAssets.f3322d0.getCache().setColor(this.f2909e);
        GameAssets.f3322d0.getData().setScale(f2 * 0.8f * this.f2910f * 0.6f);
        GameAssets.f3322d0.draw(spriteBatch, str, coords.f3287x, coords.f3288y);
        GameAssets.f3322d0.getCache().setColor(this.f2913i);
        GameAssets.f3322d0.getData().setScale(this.f2914j);
    }

    public final Boolean c() {
        if (GameLevel.b() > this.f2906b) {
            return Boolean.TRUE;
        }
        MapActor mapActor = this.f2916l;
        return (mapActor == null || mapActor.destroy) ? Boolean.TRUE : Boolean.FALSE;
    }

    public final void d(int i2) {
        this.f2911g = -i2;
    }
}
