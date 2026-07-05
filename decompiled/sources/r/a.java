package r;

import com.badlogic.gdx.assets.loaders.p;
import com.badlogic.gdx.graphics.Texture;

/* JADX INFO: compiled from: AssetDescriptor.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4026a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<T> f4027b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f4028c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public com.badlogic.gdx.files.a f4029d;

    public a() {
        throw null;
    }

    public a(String str, Class<T> cls, b<T> bVar) {
        this.f4026a = str.replace('\\', '/');
        this.f4027b = cls;
        this.f4028c = bVar;
    }

    public final String toString() {
        return this.f4026a + ", " + this.f4027b.getName();
    }

    public a(com.badlogic.gdx.files.a aVar, p.b bVar) {
        this.f4026a = aVar.path().replace('\\', '/');
        this.f4029d = aVar;
        this.f4027b = Texture.class;
        this.f4028c = bVar;
    }
}
