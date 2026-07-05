package com.badlogic.gdx.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: IntMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class q<V> implements Iterable<b<V>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int[] f1883b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    V[] f1884c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    V f1885d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1886e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1887f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected int f1888g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected int f1889h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private a f1890i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private a f1891j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private d f1892k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private d f1893l;

    /* JADX INFO: compiled from: IntMap.java */
    public static class a<V> extends c<V> implements Iterable<b<V>>, Iterator<b<V>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final b<V> f1894f;

        public a(q qVar) {
            super(qVar);
            this.f1894f = new b<>();
        }

        @Override // com.badlogic.gdx.utils.q.c
        public final void b() {
            this.f1900d = -2;
            this.f1899c = -1;
            if (this.f1898b.f1886e) {
                this.f1897a = true;
            } else {
                a();
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f1901e) {
                return this.f1897a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator<b<V>> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!this.f1897a) {
                throw new NoSuchElementException();
            }
            if (!this.f1901e) {
                throw new m("#iterator() cannot be used nested.");
            }
            q<V> qVar = this.f1898b;
            int[] iArr = qVar.f1883b;
            int i2 = this.f1899c;
            b<V> bVar = this.f1894f;
            if (i2 == -1) {
                bVar.f1895a = 0;
                bVar.f1896b = qVar.f1885d;
            } else {
                bVar.f1895a = iArr[i2];
                bVar.f1896b = qVar.f1884c[i2];
            }
            this.f1900d = i2;
            a();
            return bVar;
        }
    }

    /* JADX INFO: compiled from: IntMap.java */
    public static class b<V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1895a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public V f1896b;

        public final String toString() {
            return this.f1895a + "=" + this.f1896b;
        }
    }

    /* JADX INFO: compiled from: IntMap.java */
    private static class c<V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1897a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final q<V> f1898b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1899c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1900d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f1901e = true;

        public c(q<V> qVar) {
            this.f1898b = qVar;
            b();
        }

        final void a() {
            int i2;
            int[] iArr = this.f1898b.f1883b;
            int length = iArr.length;
            do {
                i2 = this.f1899c + 1;
                this.f1899c = i2;
                if (i2 >= length) {
                    this.f1897a = false;
                    return;
                }
            } while (iArr[i2] == 0);
            this.f1897a = true;
        }

        public void b() {
            throw null;
        }

        public void remove() {
            int i2 = this.f1900d;
            q<V> qVar = this.f1898b;
            if (i2 == -1 && qVar.f1886e) {
                qVar.f1886e = false;
            } else {
                if (i2 < 0) {
                    throw new IllegalStateException("next must be called before remove.");
                }
                int[] iArr = qVar.f1883b;
                V[] vArr = qVar.f1884c;
                int i3 = qVar.f1889h;
                int i4 = i2 + 1;
                while (true) {
                    int i5 = i4 & i3;
                    int i6 = iArr[i5];
                    if (i6 == 0) {
                        break;
                    }
                    int i7 = (int) ((((long) i6) * (-7046029254386353131L)) >>> qVar.f1888g);
                    if (((i5 - i7) & i3) > ((i2 - i7) & i3)) {
                        iArr[i2] = i6;
                        vArr[i2] = vArr[i5];
                        i2 = i5;
                    }
                    i4 = i5 + 1;
                }
                iArr[i2] = 0;
                if (i2 != this.f1900d) {
                    this.f1899c--;
                }
            }
            this.f1900d = -2;
            qVar.f1882a--;
        }
    }

    /* JADX INFO: compiled from: IntMap.java */
    public static class d<V> extends c<V> implements Iterable<V>, Iterator<V> {
        @Override // com.badlogic.gdx.utils.q.c
        public final void b() {
            this.f1900d = -2;
            this.f1899c = -1;
            if (this.f1898b.f1886e) {
                this.f1897a = true;
            } else {
                a();
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f1901e) {
                return this.f1897a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator<V> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final V next() {
            if (!this.f1897a) {
                throw new NoSuchElementException();
            }
            if (!this.f1901e) {
                throw new m("#iterator() cannot be used nested.");
            }
            int i2 = this.f1899c;
            q<V> qVar = this.f1898b;
            V v2 = i2 == -1 ? qVar.f1885d : qVar.f1884c[i2];
            this.f1900d = i2;
            a();
            return v2;
        }
    }

    public q() {
        int iH = z.h(51, 0.8f);
        this.f1887f = (int) (iH * 0.8f);
        int i2 = iH - 1;
        this.f1889h = i2;
        this.f1888g = Long.numberOfLeadingZeros(i2);
        this.f1883b = new int[iH];
        this.f1884c = (V[]) new Object[iH];
    }

    private int b(int i2) {
        int[] iArr = this.f1883b;
        int i3 = (int) ((((long) i2) * (-7046029254386353131L)) >>> this.f1888g);
        while (true) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return -(i3 + 1);
            }
            if (i4 == i2) {
                return i3;
            }
            i3 = (i3 + 1) & this.f1889h;
        }
    }

    private void e(int i2) {
        int length = this.f1883b.length;
        this.f1887f = (int) (i2 * 0.8f);
        int i3 = i2 - 1;
        this.f1889h = i3;
        this.f1888g = Long.numberOfLeadingZeros(i3);
        int[] iArr = this.f1883b;
        V[] vArr = this.f1884c;
        this.f1883b = new int[i2];
        this.f1884c = (V[]) new Object[i2];
        if (this.f1882a > 0) {
            for (int i4 = 0; i4 < length; i4++) {
                int i5 = iArr[i4];
                if (i5 != 0) {
                    V v2 = vArr[i4];
                    int[] iArr2 = this.f1883b;
                    int i6 = (int) ((((long) i5) * (-7046029254386353131L)) >>> this.f1888g);
                    while (iArr2[i6] != 0) {
                        i6 = (i6 + 1) & this.f1889h;
                    }
                    iArr2[i6] = i5;
                    this.f1884c[i6] = v2;
                }
            }
        }
    }

    public final a<V> a() {
        if (this.f1890i == null) {
            this.f1890i = new a(this);
            this.f1891j = new a(this);
        }
        a aVar = this.f1890i;
        if (aVar.f1901e) {
            this.f1891j.b();
            a<V> aVar2 = this.f1891j;
            aVar2.f1901e = true;
            this.f1890i.f1901e = false;
            return aVar2;
        }
        aVar.b();
        a<V> aVar3 = this.f1890i;
        aVar3.f1901e = true;
        this.f1891j.f1901e = false;
        return aVar3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(int i2, Object obj) {
        if (i2 == 0) {
            this.f1885d = obj;
            if (this.f1886e) {
                return;
            }
            this.f1886e = true;
            this.f1882a++;
            return;
        }
        int iB = b(i2);
        if (iB >= 0) {
            V[] vArr = this.f1884c;
            Object[] objArr = vArr[iB];
            vArr[iB] = obj;
            return;
        }
        int i3 = -(iB + 1);
        int[] iArr = this.f1883b;
        iArr[i3] = i2;
        this.f1884c[i3] = obj;
        int i4 = this.f1882a + 1;
        this.f1882a = i4;
        if (i4 >= this.f1887f) {
            e(iArr.length << 1);
        }
    }

    public final void d(q<? extends V> qVar) {
        int iH = z.h(this.f1882a + qVar.f1882a, 0.8f);
        if (this.f1883b.length < iH) {
            e(iH);
        }
        if (qVar.f1886e) {
            c(0, qVar.f1885d);
        }
        int[] iArr = qVar.f1883b;
        Object[] objArr = qVar.f1884c;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                c(i3, objArr[i2]);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        if (qVar.f1882a != this.f1882a) {
            return false;
        }
        boolean z2 = qVar.f1886e;
        boolean z3 = this.f1886e;
        if (z2 != z3) {
            return false;
        }
        if (z3) {
            V v2 = qVar.f1885d;
            if (v2 == null) {
                if (this.f1885d != null) {
                    return false;
                }
            } else if (!v2.equals(this.f1885d)) {
                return false;
            }
        }
        int[] iArr = this.f1883b;
        V[] vArr = this.f1884c;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                V v3 = vArr[i2];
                if (v3 == null) {
                    V v4 = (V) y.f2042n;
                    if (i3 != 0) {
                        int iB = qVar.b(i3);
                        if (iB >= 0) {
                            v4 = (V) qVar.f1884c[iB];
                        }
                    } else if (qVar.f1886e) {
                        v4 = qVar.f1885d;
                    }
                    if (v4) {
                        return false;
                    }
                } else if (!v3.equals(qVar.get(i3))) {
                    return false;
                }
            }
        }
        return true;
    }

    public final d<V> f() {
        if (this.f1892k == null) {
            this.f1892k = new d(this);
            this.f1893l = new d(this);
        }
        d dVar = this.f1892k;
        if (dVar.f1901e) {
            this.f1893l.b();
            d<V> dVar2 = this.f1893l;
            dVar2.f1901e = true;
            this.f1892k.f1901e = false;
            return dVar2;
        }
        dVar.b();
        d<V> dVar3 = this.f1892k;
        dVar3.f1901e = true;
        this.f1893l.f1901e = false;
        return dVar3;
    }

    public final V get(int i2) {
        if (i2 == 0) {
            if (this.f1886e) {
                return this.f1885d;
            }
            return null;
        }
        int iB = b(i2);
        if (iB >= 0) {
            return this.f1884c[iB];
        }
        return null;
    }

    public final int hashCode() {
        V v2;
        int iHashCode = this.f1882a;
        if (this.f1886e && (v2 = this.f1885d) != null) {
            iHashCode += v2.hashCode();
        }
        int[] iArr = this.f1883b;
        V[] vArr = this.f1884c;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                int i4 = (i3 * 31) + iHashCode;
                V v3 = vArr[i2];
                iHashCode = v3 != null ? v3.hashCode() + i4 : i4;
            }
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public final Iterator<b<V>> iterator() {
        return a();
    }

    public final V remove(int i2) {
        if (i2 == 0) {
            if (!this.f1886e) {
                return null;
            }
            this.f1886e = false;
            V v2 = this.f1885d;
            this.f1885d = null;
            this.f1882a--;
            return v2;
        }
        int iB = b(i2);
        if (iB < 0) {
            return null;
        }
        int[] iArr = this.f1883b;
        V[] vArr = this.f1884c;
        V v3 = vArr[iB];
        int i3 = this.f1889h;
        int i4 = iB + 1;
        while (true) {
            int i5 = i4 & i3;
            int i6 = iArr[i5];
            if (i6 == 0) {
                iArr[iB] = 0;
                this.f1882a--;
                return v3;
            }
            int i7 = (int) ((((long) i6) * (-7046029254386353131L)) >>> this.f1888g);
            if (((i5 - i7) & i3) > ((iB - i7) & i3)) {
                iArr[iB] = i6;
                vArr[iB] = vArr[i5];
                iB = i5;
            }
            i4 = i5 + 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x003e -> B:16:0x003f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String toString() {
        int i2;
        if (this.f1882a == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        int[] iArr = this.f1883b;
        V[] vArr = this.f1884c;
        int length = iArr.length;
        if (this.f1886e) {
            sb.append("0=");
            sb.append(this.f1885d);
            i2 = length - 1;
            if (length <= 0) {
                sb.append(']');
                return sb.toString();
            }
            int i3 = iArr[i2];
            if (i3 != 0) {
                sb.append(", ");
                sb.append(i3);
                sb.append('=');
                sb.append(vArr[i2]);
            }
            length = i2;
            i2 = length - 1;
            if (length <= 0) {
            }
        } else {
            while (true) {
                i2 = length - 1;
                if (length > 0) {
                    int i4 = iArr[i2];
                    if (i4 != 0) {
                        sb.append(i4);
                        sb.append('=');
                        sb.append(vArr[i2]);
                        break;
                    }
                    length = i2;
                } else {
                    break;
                }
            }
            length = i2;
            i2 = length - 1;
            if (length <= 0) {
            }
        }
    }
}
