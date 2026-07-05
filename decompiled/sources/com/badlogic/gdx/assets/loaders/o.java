package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.loaders.p;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.a;

/* JADX INFO: compiled from: TextureAtlasLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class o extends n<TextureAtlas, a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    TextureAtlas.TextureAtlasData f1589a;

    /* JADX INFO: compiled from: TextureAtlasLoader.java */
    public static class a extends r.b<TextureAtlas> {
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        a aVar2 = (a) bVar;
        com.badlogic.gdx.files.a aVarParent = aVar.parent();
        if (aVar2 != null) {
            this.f1589a = new TextureAtlas.TextureAtlasData(aVar, aVarParent, false);
        } else {
            this.f1589a = new TextureAtlas.TextureAtlasData(aVar, aVarParent, false);
        }
        com.badlogic.gdx.utils.a aVar3 = new com.badlogic.gdx.utils.a();
        a.b<TextureAtlas.TextureAtlasData.Page> it = this.f1589a.getPages().iterator();
        while (it.hasNext()) {
            TextureAtlas.TextureAtlasData.Page next = it.next();
            p.b bVar2 = new p.b();
            bVar2.f1593a = next.format;
            bVar2.f1594b = next.useMipMaps;
            bVar2.f1597e = next.minFilter;
            bVar2.f1598f = next.magFilter;
            aVar3.a(new r.a(next.textureFile, bVar2));
        }
        return aVar3;
    }

    @Override // com.badlogic.gdx.assets.loaders.n
    public final TextureAtlas load(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        a.b<TextureAtlas.TextureAtlasData.Page> it = this.f1589a.getPages().iterator();
        while (it.hasNext()) {
            TextureAtlas.TextureAtlasData.Page next = it.next();
            next.texture = (Texture) dVar.e(next.textureFile.path().replaceAll("\\\\", "/"), Texture.class);
        }
        TextureAtlas textureAtlas = new TextureAtlas(this.f1589a);
        this.f1589a = null;
        return textureAtlas;
    }
}
