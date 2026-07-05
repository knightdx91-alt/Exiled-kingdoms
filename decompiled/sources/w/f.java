package w;

import com.badlogic.gdx.utils.q;
import java.util.Iterator;

/* JADX INFO: compiled from: TiledMapTileSet.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f implements Iterable<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private q<d> f4084a = new q<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private u.h f4085b = new u.h();

    public final u.h a() {
        return this.f4085b;
    }

    public final d b(int i2) {
        return this.f4084a.get(i2);
    }

    public final void c(int i2, d dVar) {
        this.f4084a.c(i2, dVar);
    }

    @Override // java.lang.Iterable
    public final Iterator<d> iterator() {
        return this.f4084a.f();
    }
}
