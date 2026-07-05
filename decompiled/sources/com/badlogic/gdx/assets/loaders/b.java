package com.badlogic.gdx.assets.loaders;

import r.b;

/* JADX INFO: compiled from: AsynchronousAssetLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class b<T, P extends r.b<T>> extends a<T, P> {
    public b(e eVar) {
        super(eVar);
    }

    public abstract void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, P p2);

    public abstract T loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, P p2);

    public void unloadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, P p2) {
    }
}
