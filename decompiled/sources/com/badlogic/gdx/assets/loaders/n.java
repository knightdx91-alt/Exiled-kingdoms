package com.badlogic.gdx.assets.loaders;

import r.b;

/* JADX INFO: compiled from: SynchronousAssetLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class n<T, P extends r.b<T>> extends a<T, P> {
    public n(e eVar) {
        super(eVar);
    }

    public abstract T load(r.d dVar, String str, com.badlogic.gdx.files.a aVar, P p2);
}
