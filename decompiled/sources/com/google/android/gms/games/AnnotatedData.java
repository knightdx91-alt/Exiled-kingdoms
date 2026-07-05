package com.google.android.gms.games;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AnnotatedData<T> {
    private final T data;
    private final boolean zzi;

    public AnnotatedData(T t2, boolean z2) {
        this.data = t2;
        this.zzi = z2;
    }

    public T get() {
        return this.data;
    }

    public boolean isStale() {
        return this.zzi;
    }
}
