package com.google.android.datatransport.runtime.dagger.internal;

import j0.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SingleCheck<T> implements a<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile a<T> provider;

    private SingleCheck(a<T> aVar) {
        this.provider = aVar;
    }

    public static <P extends a<T>, T> a<T> provider(P p2) {
        return ((p2 instanceof SingleCheck) || (p2 instanceof DoubleCheck)) ? p2 : new SingleCheck((a) Preconditions.checkNotNull(p2));
    }

    @Override // j0.a
    public T get() {
        T t2 = (T) this.instance;
        if (t2 != UNINITIALIZED) {
            return t2;
        }
        a<T> aVar = this.provider;
        if (aVar == null) {
            return (T) this.instance;
        }
        T t3 = aVar.get();
        this.instance = t3;
        this.provider = null;
        return t3;
    }
}
