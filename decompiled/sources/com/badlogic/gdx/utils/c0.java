package com.badlogic.gdx.utils;

import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: Pool.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class c0<T> {
    private final com.badlogic.gdx.utils.a<T> freeObjects;
    public final int max;
    public int peak;

    /* JADX INFO: compiled from: Pool.java */
    public interface a {
        void reset();
    }

    public c0() {
        this(16, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public void clear() {
        this.freeObjects.clear();
    }

    public void fill(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            com.badlogic.gdx.utils.a<T> aVar = this.freeObjects;
            if (aVar.f1750b < this.max) {
                aVar.a(newObject());
            }
        }
        this.peak = Math.max(this.peak, this.freeObjects.f1750b);
    }

    public void free(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        com.badlogic.gdx.utils.a<T> aVar = this.freeObjects;
        if (aVar.f1750b < this.max) {
            aVar.a(t2);
            this.peak = Math.max(this.peak, this.freeObjects.f1750b);
        }
        reset(t2);
    }

    public void freeAll(com.badlogic.gdx.utils.a<T> aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("objects cannot be null.");
        }
        com.badlogic.gdx.utils.a<T> aVar2 = this.freeObjects;
        int i2 = this.max;
        int i3 = aVar.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            T t2 = aVar.get(i4);
            if (t2 != null) {
                if (aVar2.f1750b < i2) {
                    aVar2.a(t2);
                }
                reset(t2);
            }
        }
        this.peak = Math.max(this.peak, aVar2.f1750b);
    }

    public int getFree() {
        return this.freeObjects.f1750b;
    }

    protected abstract T newObject();

    public T obtain() {
        com.badlogic.gdx.utils.a<T> aVar = this.freeObjects;
        return aVar.f1750b == 0 ? newObject() : aVar.l();
    }

    protected void reset(T t2) {
        if (t2 instanceof a) {
            ((a) t2).reset();
        }
    }

    public c0(int i2) {
        this(i2, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public c0(int i2, int i3) {
        this.freeObjects = new com.badlogic.gdx.utils.a<>(false, i2);
        this.max = i3;
    }
}
