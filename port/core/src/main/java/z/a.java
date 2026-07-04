package z;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import u.g;
import com.badlogic.gdx.maps.MapProperties;
import w.d;

/* JADX INFO: compiled from: AnimatedTiledMapTile.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static long f4095g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f4096h = System.currentTimeMillis();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4097a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public MapProperties f4098b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f4099c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b[] f4100d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4101e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4102f;

    public a(o oVar, com.badlogic.gdx.utils.Array<b> aVar) {
        this.f4100d = new b[aVar.f1750b];
        int i2 = oVar.f1850b;
        int[] iArr = new int[i2];
        System.arraycopy(oVar.f1849a, 0, iArr, 0, i2);
        this.f4101e = iArr;
        this.f4102f = 0;
        for (int i3 = 0; i3 < oVar.f1850b; i3++) {
            this.f4100d[i3] = aVar.get(i3);
            this.f4102f = oVar.d(i3) + this.f4102f;
        }
    }

    public static void e() {
        f4095g = System.currentTimeMillis() - f4096h;
    }

    @Override // w.d
    public final g a() {
        if (this.f4099c == null) {
            this.f4099c = new g();
        }
        return this.f4099c;
    }

    @Override // w.d
    public final TextureRegion b() {
        int i2 = (int) (f4095g % ((long) this.f4102f));
        int i3 = 0;
        while (true) {
            int[] iArr = this.f4101e;
            if (i3 >= iArr.length) {
                throw new m("Could not determine current animation frame in AnimatedTiledMapTile.  This should never happen.");
            }
            int i4 = iArr[i3];
            if (i2 <= i4) {
                return this.f4100d[i3].b();
            }
            i2 -= i4;
            i3++;
        }
    }

    @Override // w.d
    public final MapProperties c() {
        if (this.f4098b == null) {
            this.f4098b = new MapProperties ();
        }
        return this.f4098b;
    }

    public final void d(int i2) {
        this.f4097a = i2;
    }

    @Override // w.d
    public final int getId() {
        return this.f4097a;
    }
}
