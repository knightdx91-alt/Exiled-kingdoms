package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;

/* JADX INFO: compiled from: CubemapLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d extends com.badlogic.gdx.assets.loaders.b<Cubemap, b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a f1573a;

    /* JADX INFO: compiled from: CubemapLoader.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        CubemapData f1574a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Cubemap f1575b;
    }

    /* JADX INFO: compiled from: CubemapLoader.java */
    public static class b extends r.b<Cubemap> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Cubemap f1576a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CubemapData f1577b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Texture.TextureFilter f1578c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Texture.TextureFilter f1579d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Texture.TextureWrap f1580e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Texture.TextureWrap f1581f;

        public b() {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
            this.f1578c = textureFilter;
            this.f1579d = textureFilter;
            Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
            this.f1580e = textureWrap;
            this.f1581f = textureWrap;
        }
    }

    public d(com.badlogic.gdx.utils.l lVar) {
        super(lVar);
        this.f1573a = new a();
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        CubemapData cubemapData;
        b bVar2 = (b) bVar;
        a aVar2 = this.f1573a;
        aVar2.getClass();
        if (bVar2 == null || (cubemapData = bVar2.f1577b) == null) {
            aVar2.f1575b = null;
            if (bVar2 != null) {
                aVar2.f1575b = bVar2.f1576a;
            }
            if (str.contains(".ktx") || str.contains(".zktx")) {
                aVar2.f1574a = new KTXTextureData(aVar, false);
            }
        } else {
            aVar2.f1574a = cubemapData;
            aVar2.f1575b = bVar2.f1576a;
        }
        if (aVar2.f1574a.isPrepared()) {
            return;
        }
        aVar2.f1574a.prepare();
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final Cubemap loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        b bVar2 = (b) bVar;
        a aVar2 = this.f1573a;
        if (aVar2 == null) {
            return null;
        }
        Cubemap cubemap = aVar2.f1575b;
        if (cubemap != null) {
            cubemap.load(aVar2.f1574a);
        } else {
            cubemap = new Cubemap(aVar2.f1574a);
        }
        Cubemap cubemap2 = cubemap;
        if (bVar2 == null) {
            return cubemap2;
        }
        cubemap2.setFilter(bVar2.f1578c, bVar2.f1579d);
        cubemap2.setWrap(bVar2.f1580e, bVar2.f1581f);
        return cubemap2;
    }
}
