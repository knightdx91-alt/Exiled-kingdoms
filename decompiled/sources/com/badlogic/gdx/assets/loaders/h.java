package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;

/* JADX INFO: compiled from: MusicLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h extends b<t.c, a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private t.c f1583a;

    /* JADX INFO: compiled from: MusicLoader.java */
    public static class a extends r.b<t.c> {
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        this.f1583a = Gdx.audio.newMusic(aVar);
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final t.c loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        t.c cVar = this.f1583a;
        this.f1583a = null;
        return cVar;
    }
}
