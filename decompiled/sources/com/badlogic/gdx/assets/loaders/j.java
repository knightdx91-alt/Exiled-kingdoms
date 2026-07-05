package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.graphics.Pixmap;

/* JADX INFO: compiled from: PixmapLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j extends b<Pixmap, a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Pixmap f1584a;

    /* JADX INFO: compiled from: PixmapLoader.java */
    public static class a extends r.b<Pixmap> {
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        this.f1584a = null;
        this.f1584a = new Pixmap(aVar);
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final Pixmap loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        Pixmap pixmap = this.f1584a;
        this.f1584a = null;
        return pixmap;
    }
}
