package z;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import u.g;
import com.badlogic.gdx.maps.MapProperties;
import w.d;

/* JADX INFO: compiled from: StaticTiledMapTile.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public MapProperties f4104b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f4105c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextureRegion f4106d;

    public b(TextureRegion textureRegion) {
        this.f4106d = textureRegion;
    }

    @Override // w.d
    public final g a() {
        if (this.f4105c == null) {
            this.f4105c = new g();
        }
        return this.f4105c;
    }

    @Override // w.d
    public final TextureRegion b() {
        return this.f4106d;
    }

    @Override // w.d
    public final MapProperties c() {
        if (this.f4104b == null) {
            this.f4104b = new MapProperties ();
        }
        return this.f4104b;
    }

    public final void d(int i2) {
        this.f4103a = i2;
    }

    @Override // w.d
    public final int getId() {
        return this.f4103a;
    }
}
