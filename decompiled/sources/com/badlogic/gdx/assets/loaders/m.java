package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;

/* JADX INFO: compiled from: SoundLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class m extends b<t.d, a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private t.d f1588a;

    /* JADX INFO: compiled from: SoundLoader.java */
    public static class a extends r.b<t.d> {
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        this.f1588a = Gdx.audio.newSound(aVar);
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final t.d loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        t.d dVar2 = this.f1588a;
        this.f1588a = null;
        return dVar2;
    }
}
