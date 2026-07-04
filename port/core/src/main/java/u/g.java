package u;

import java.util.Iterator;

/* JADX INFO: compiled from: MapObjects.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class g implements Iterable<f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.badlogic.gdx.utils.Array<f> f4065a = new com.badlogic.gdx.utils.Array<>();

    public final void a(f fVar) {
        this.f4065a.add(fVar);
    }

    @Override // java.lang.Iterable
    public final Iterator<f> iterator() {
        return this.f4065a.iterator();
    }
}
