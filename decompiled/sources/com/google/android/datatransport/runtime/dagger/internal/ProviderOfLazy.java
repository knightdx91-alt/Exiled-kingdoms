package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import j0.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ProviderOfLazy<T> implements a<Lazy<T>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final a<T> provider;

    private ProviderOfLazy(a<T> aVar) {
        this.provider = aVar;
    }

    public static <T> a<Lazy<T>> create(a<T> aVar) {
        return new ProviderOfLazy((a) Preconditions.checkNotNull(aVar));
    }

    @Override // j0.a
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.provider);
    }
}
