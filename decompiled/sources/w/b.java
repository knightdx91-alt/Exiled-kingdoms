package w;

import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;

/* JADX INFO: compiled from: TiledMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b extends u.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f4076c = new g();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.badlogic.gdx.utils.a<? extends i> f4077d;

    public final g c() {
        return this.f4076c;
    }

    public final void d(com.badlogic.gdx.utils.a<? extends i> aVar) {
        this.f4077d = aVar;
    }

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        com.badlogic.gdx.utils.a<? extends i> aVar = this.f4077d;
        if (aVar != null) {
            a.b<? extends i> it = aVar.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
        }
    }
}
