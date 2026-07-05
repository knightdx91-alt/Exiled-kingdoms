package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

/* JADX INFO: compiled from: TextureLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class p extends com.badlogic.gdx.assets.loaders.b<Texture, b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a f1590a;

    /* JADX INFO: compiled from: TextureLoader.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        TextureData f1591a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Texture f1592b;
    }

    /* JADX INFO: compiled from: TextureLoader.java */
    public static class b extends r.b<Texture> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Pixmap.Format f1593a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1594b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Texture f1595c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextureData f1596d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Texture.TextureFilter f1597e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Texture.TextureFilter f1598f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Texture.TextureWrap f1599g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Texture.TextureWrap f1600h;

        public b() {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
            this.f1597e = textureFilter;
            this.f1598f = textureFilter;
            Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
            this.f1599g = textureWrap;
            this.f1600h = textureWrap;
        }
    }

    public p(com.badlogic.gdx.utils.l lVar) {
        super(lVar);
        this.f1590a = new a();
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        boolean z2;
        TextureData textureData;
        b bVar2 = (b) bVar;
        a aVar2 = this.f1590a;
        aVar2.getClass();
        if (bVar2 == null || (textureData = bVar2.f1596d) == null) {
            Pixmap.Format format = null;
            aVar2.f1592b = null;
            if (bVar2 != null) {
                format = bVar2.f1593a;
                z2 = bVar2.f1594b;
                aVar2.f1592b = bVar2.f1595c;
            } else {
                z2 = false;
            }
            aVar2.f1591a = TextureData.Factory.loadFromFile(aVar, format, z2);
        } else {
            aVar2.f1591a = textureData;
            aVar2.f1592b = bVar2.f1595c;
        }
        if (aVar2.f1591a.isPrepared()) {
            return;
        }
        aVar2.f1591a.prepare();
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final Texture loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        b bVar2 = (b) bVar;
        a aVar2 = this.f1590a;
        if (aVar2 == null) {
            return null;
        }
        Texture texture = aVar2.f1592b;
        if (texture != null) {
            texture.load(aVar2.f1591a);
        } else {
            texture = new Texture(aVar2.f1591a);
        }
        Texture texture2 = texture;
        if (bVar2 == null) {
            return texture2;
        }
        texture2.setFilter(bVar2.f1597e, bVar2.f1598f);
        texture2.setWrap(bVar2.f1599g, bVar2.f1600h);
        return texture2;
    }
}
