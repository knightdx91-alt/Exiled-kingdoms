package u;

import java.util.Iterator;

/* JADX INFO: compiled from: MapLayers.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class e implements Iterable<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.badlogic.gdx.utils.a<d> f4063a = new com.badlogic.gdx.utils.a<>();

    public final void a(d dVar) {
        this.f4063a.a(dVar);
    }

    public final d b(int i2) {
        return this.f4063a.get(i2);
    }

    public final d c(String str) {
        com.badlogic.gdx.utils.a<d> aVar = this.f4063a;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            d dVar = aVar.get(i3);
            if (str.equals(dVar.a())) {
                return dVar;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public final Iterator<d> iterator() {
        return this.f4063a.iterator();
    }

    public final int size() {
        return this.f4063a.f1750b;
    }
}
