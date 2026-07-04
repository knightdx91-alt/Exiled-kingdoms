package w;

import com.badlogic.gdx.utils.IntMap;
import java.util.Iterator;

/* JADX INFO: compiled from: TiledMapTileSet.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class f implements Iterable<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IntMap<d> f4084a = new IntMap <>();

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
