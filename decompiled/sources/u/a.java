package u;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.y;

/* JADX INFO: compiled from: ImageResolver.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface a {

    /* JADX INFO: renamed from: u.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImageResolver.java */
    public static class C0060a implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final r.d f4053a;

        public C0060a(r.d dVar) {
            this.f4053a = dVar;
        }

        @Override // u.a
        public final TextureRegion a(String str) {
            return new TextureRegion((Texture) this.f4053a.e(str, Texture.class));
        }
    }

    /* JADX INFO: compiled from: ImageResolver.java */
    public static class b implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final y<String, Texture> f4054a;

        public b(y<String, Texture> yVar) {
            this.f4054a = yVar;
        }

        @Override // u.a
        public final TextureRegion a(String str) {
            return new TextureRegion(this.f4054a.d(str));
        }
    }

    TextureRegion a(String str);
}
