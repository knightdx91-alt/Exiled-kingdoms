package com.badlogic.gdx.utils;

import java.util.Iterator;

/* JADX INFO: compiled from: SortedIntList.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class m0<E> implements Iterable<b<E>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c<E> f1836a = new c<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private m0<E>.a f1837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b<E> f1838c;

    /* JADX INFO: compiled from: SortedIntList.java */
    public class a implements Iterator<b<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b<E> f1839a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private b<E> f1840b;

        public a() {
        }

        public final void a() {
            this.f1839a = m0.this.f1838c;
            this.f1840b = null;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f1839a != null;
        }

        @Override // java.util.Iterator
        public final Object next() {
            b<E> bVar = this.f1839a;
            this.f1840b = bVar;
            this.f1839a = bVar.f1843b;
            return bVar;
        }

        @Override // java.util.Iterator
        public final void remove() {
            b<E> bVar = this.f1840b;
            if (bVar != null) {
                m0 m0Var = m0.this;
                if (bVar == m0Var.f1838c) {
                    m0Var.f1838c = this.f1839a;
                    return;
                }
                b<E> bVar2 = bVar.f1842a;
                b<E> bVar3 = this.f1839a;
                bVar2.f1843b = bVar3;
                if (bVar3 != null) {
                    bVar3.f1842a = bVar2;
                }
            }
        }
    }

    /* JADX INFO: compiled from: SortedIntList.java */
    public static class b<E> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected b<E> f1842a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected b<E> f1843b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public com.badlogic.gdx.utils.a f1844c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1845d;
    }

    /* JADX INFO: compiled from: SortedIntList.java */
    static class c<E> extends c0<b<E>> {
        public final b a(b bVar, b bVar2, com.badlogic.gdx.utils.a aVar, int i2) {
            b bVar3 = (b) super.obtain();
            bVar3.f1842a = bVar;
            bVar3.f1843b = bVar2;
            bVar3.f1844c = aVar;
            bVar3.f1845d = i2;
            return bVar3;
        }

        @Override // com.badlogic.gdx.utils.c0
        protected final Object newObject() {
            return new b();
        }
    }

    public final void a(int i2, com.badlogic.gdx.utils.a aVar) {
        b<E> bVar;
        b<E> bVar2 = this.f1838c;
        c<E> cVar = this.f1836a;
        if (bVar2 == null) {
            this.f1838c = cVar.a(null, null, aVar, i2);
            return;
        }
        while (true) {
            bVar = bVar2.f1843b;
            if (bVar == null || bVar.f1845d > i2) {
                break;
            } else {
                bVar2 = bVar;
            }
        }
        int i3 = bVar2.f1845d;
        if (i2 > i3) {
            b<E> bVarA = cVar.a(bVar2, bVar, aVar, i2);
            bVar2.f1843b = bVarA;
            b<E> bVar3 = bVarA.f1843b;
            if (bVar3 != null) {
                bVar3.f1842a = bVarA;
                return;
            }
            return;
        }
        if (i2 >= i3) {
            bVar2.f1844c = aVar;
            return;
        }
        b<E> bVarA2 = cVar.a(null, this.f1838c, aVar, i2);
        this.f1838c.f1842a = bVarA2;
        this.f1838c = bVarA2;
    }

    public final void clear() {
        while (true) {
            b<E> bVar = this.f1838c;
            if (bVar == null) {
                return;
            }
            this.f1836a.free(bVar);
            this.f1838c = this.f1838c.f1843b;
        }
    }

    public final E get(int i2) {
        b<E> bVar = this.f1838c;
        if (bVar != null) {
            while (true) {
                b<E> bVar2 = bVar.f1843b;
                if (bVar2 == null || bVar.f1845d >= i2) {
                    break;
                }
                bVar = bVar2;
            }
            if (bVar.f1845d == i2) {
                return (E) bVar.f1844c;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public final Iterator<b<E>> iterator() {
        if (this.f1837b == null) {
            this.f1837b = new a();
        }
        m0<E>.a aVar = this.f1837b;
        aVar.a();
        return aVar;
    }
}
