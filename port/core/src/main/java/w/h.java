package w;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.ObjectMap;
import u.a;
import w.a;

/* JADX INFO: compiled from: TmxMapLoader.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class h extends w.a<a> {

    /* JADX INFO: compiled from: TmxMapLoader.java */
    public static class a extends a.C0061a {
    }

    protected final com.badlogic.gdx.utils.Array<com.badlogic.gdx.files.FileHandle> g(com.badlogic.gdx.files.FileHandle aVar) {
        com.badlogic.gdx.utils.Array<com.badlogic.gdx.files.FileHandle> aVar2 = new com.badlogic.gdx.utils.Array<>();
        a.b<s0.a> it = this.f4069b.h("tileset").iterator();
        while (it.hasNext()) {
            s0.a next = it.next();
            String strD = next.d("source", null);
            if (strD != null) {
                com.badlogic.gdx.files.FileHandle aVarA = w.a.a(aVar, strD);
                s0.a aVarA2 = this.f4068a.a(aVarA);
                if (aVarA2.f("image") != null) {
                    aVar2.add(w.a.a(aVarA, aVarA2.f("image").c("source")));
                } else {
                    a.b<s0.a> it2 = aVarA2.h("tile").iterator();
                    while (it2.hasNext()) {
                        aVar2.add(w.a.a(aVarA, it2.next().f("image").c("source")));
                    }
                }
            } else if (next.f("image") != null) {
                aVar2.add(w.a.a(aVar, next.f("image").c("source")));
            } else {
                a.b<s0.a> it3 = next.h("tile").iterator();
                while (it3.hasNext()) {
                    aVar2.add(w.a.a(aVar, it3.next().f("image").c("source")));
                }
            }
        }
        a.b<s0.a> it4 = this.f4069b.h("imagelayer").iterator();
        while (it4.hasNext()) {
            String strD2 = it4.next().f("image").d("source", null);
            if (strD2 != null) {
                aVar2.add(w.a.a(aVar, strD2));
            }
        }
        return aVar2;
    }

    public final b h(String str, a aVar) {
        com.badlogic.gdx.files.FileHandle aVarResolve = resolve(str);
        this.f4069b = this.f4068a.a(aVarResolve);
        y yVar = new y();
        a.b<com.badlogic.gdx.files.FileHandle> it = g(aVarResolve).iterator();
        while (it.hasNext()) {
            com.badlogic.gdx.files.FileHandle next = it.next();
            Texture texture = new Texture(next, false);
            texture.setFilter(aVar.f4073a, aVar.f4074b);
            yVar.j(next.path(), texture);
        }
        b bVarF = f(aVarResolve, aVar, new a.b(yVar));
        bVarF.d(yVar.n().c());
        return bVarF;
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public final void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.FileHandle aVar, r.b bVar) {
        this.f4072e = f(aVar, (a) bVar, new a.C0060a(dVar));
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public final b loadSync(r.d dVar, String str, com.badlogic.gdx.files.FileHandle aVar, r.b bVar) {
        return this.f4072e;
    }
}
