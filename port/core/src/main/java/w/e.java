package w;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: TiledMapTileLayer.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class e extends u.d {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4078f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4079g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4080h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4081i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public a[][] f4082j;

    /* JADX INFO: compiled from: TiledMapTileLayer.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f4083a;

        public final d a() {
            return this.f4083a;
        }

        public final void b(d dVar) {
            this.f4083a = dVar;
        }
    }

    public e(int i2, int i3, int i4, int i5) {
        this.f4078f = i2;
        this.f4079g = i3;
        this.f4080h = i4;
        this.f4081i = i5;
        this.f4082j = (a[][]) Array.newInstance((Class<?>) a.class, i2, i3);
    }

    public final a j(int i2, int i3) {
        if (i2 < 0 || i2 >= this.f4078f || i3 < 0 || i3 >= this.f4079g) {
            return null;
        }
        return this.f4082j[i2][i3];
    }

    public final int k() {
        return this.f4081i;
    }

    public final int l() {
        return this.f4080h;
    }

    public final void m(int i2, int i3, a aVar) {
        if (i2 < 0 || i2 >= this.f4078f || i3 < 0 || i3 >= this.f4079g) {
            return;
        }
        this.f4082j[i2][i3] = aVar;
    }
}
