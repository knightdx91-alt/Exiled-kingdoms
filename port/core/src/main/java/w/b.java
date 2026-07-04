package w;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/* JADX INFO: compiled from: TiledMap.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b extends u.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f4076c = new g();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public com.badlogic.gdx.utils.Array<? extends i> f4077d;

    public final g c() {
        return this.f4076c;
    }

    public final void d(com.badlogic.gdx.utils.Array<? extends i> aVar) {
        this.f4077d = aVar;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        com.badlogic.gdx.utils.Array<? extends i> aVar = this.f4077d;
        if (aVar != null) {
            a.b<? extends i> it = aVar.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
        }
    }
}
