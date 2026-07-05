package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/* JADX INFO: compiled from: SkinLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class l extends b<Skin, a> {

    /* JADX INFO: compiled from: SkinLoader.java */
    public static class a extends r.b<Skin> {
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        com.badlogic.gdx.utils.a aVar2 = new com.badlogic.gdx.utils.a();
        aVar2.a(new r.a(aVar.pathWithoutExtension() + ".atlas", TextureAtlas.class, null));
        return aVar2;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final /* bridge */ /* synthetic */ void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final Skin loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        Skin skin = new Skin((TextureAtlas) dVar.e(aVar.pathWithoutExtension() + ".atlas", TextureAtlas.class));
        skin.load(aVar);
        return skin;
    }
}
