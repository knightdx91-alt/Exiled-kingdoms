package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;
import r.b;

/* JADX INFO: compiled from: AssetLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class a<T, P extends r.b<T>> {
    private e resolver;

    public a(e eVar) {
        this.resolver = eVar;
    }

    public abstract com.badlogic.gdx.utils.a<r.a> getDependencies(String str, com.badlogic.gdx.files.a aVar, P p2);

    public com.badlogic.gdx.files.a resolve(String str) {
        ((com.badlogic.gdx.utils.l) this.resolver).getClass();
        return Gdx.files.internal(str);
    }
}
