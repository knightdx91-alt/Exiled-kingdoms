package com.badlogic.gdx.utils;

import java.util.Comparator;

/* JADX INFO: compiled from: Sort.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class l0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static l0 f1833c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private p0 f1834a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g f1835b;

    public static l0 a() {
        if (f1833c == null) {
            f1833c = new l0();
        }
        return f1833c;
    }

    public final <T> void b(a<T> aVar, Comparator<? super T> comparator) {
        if (this.f1834a == null) {
            this.f1834a = new p0();
        }
        this.f1834a.c(aVar.f1749a, comparator, aVar.f1750b);
    }

    public final void c(Object[] objArr, int i2) {
        if (this.f1835b == null) {
            this.f1835b = new g();
        }
        this.f1835b.c(objArr, i2);
    }

    public final void d(Object[] objArr, Comparator comparator, int i2) {
        if (this.f1834a == null) {
            this.f1834a = new p0();
        }
        this.f1834a.c(objArr, comparator, i2);
    }
}
