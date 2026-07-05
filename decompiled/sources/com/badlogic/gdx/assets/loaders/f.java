package com.badlogic.gdx.assets.loaders;

import java.util.Locale;

/* JADX INFO: compiled from: I18NBundleLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f extends b<com.badlogic.gdx.utils.n, a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    com.badlogic.gdx.utils.n f1582a;

    /* JADX INFO: compiled from: I18NBundleLoader.java */
    public static class a extends r.b<com.badlogic.gdx.utils.n> {
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        a aVar2 = (a) bVar;
        this.f1582a = null;
        this.f1582a = com.badlogic.gdx.utils.n.a(aVar, aVar2 == null ? Locale.getDefault() : Locale.getDefault());
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final com.badlogic.gdx.utils.n loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        com.badlogic.gdx.utils.n nVar = this.f1582a;
        this.f1582a = null;
        return nVar;
    }
}
