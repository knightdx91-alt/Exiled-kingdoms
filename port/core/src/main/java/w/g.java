package w;

import java.util.Iterator;

/* JADX INFO: compiled from: TiledMapTileSets.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class g implements Iterable<f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.badlogic.gdx.utils.a<f> f4086a = new com.badlogic.gdx.utils.a<>();

    public final void a(f fVar) {
        this.f4086a.a(fVar);
    }

    public final d b(int i2) {
        com.badlogic.gdx.utils.a<f> aVar = this.f4086a;
        for (int i3 = aVar.f1750b - 1; i3 >= 0; i3--) {
            d dVarB = aVar.get(i3).b(i2);
            if (dVarB != null) {
                return dVarB;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public final Iterator<f> iterator() {
        return this.f4086a.iterator();
    }
}
