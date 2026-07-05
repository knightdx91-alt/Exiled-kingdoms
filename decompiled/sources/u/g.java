package u;

import java.util.Iterator;

/* JADX INFO: compiled from: MapObjects.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g implements Iterable<f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.badlogic.gdx.utils.a<f> f4065a = new com.badlogic.gdx.utils.a<>();

    public final void a(f fVar) {
        this.f4065a.a(fVar);
    }

    @Override // java.lang.Iterable
    public final Iterator<f> iterator() {
        return this.f4065a.iterator();
    }
}
