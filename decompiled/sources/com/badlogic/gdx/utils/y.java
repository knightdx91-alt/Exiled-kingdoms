package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObjectMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class y<K, V> implements Iterable<b<K, V>> {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    static final Object f2042n = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    K[] f2044b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    V[] f2045c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f2046d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2047e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f2048f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected int f2049g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    a f2050h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    a f2051i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    e f2052j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    e f2053k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    c f2054l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    c f2055m;

    /* JADX INFO: compiled from: ObjectMap.java */
    public static class a<K, V> extends d<K, V, b<K, V>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        b<K, V> f2056f;

        public a(y<K, V> yVar) {
            super(yVar);
            this.f2056f = new b<>();
        }

        @Override // com.badlogic.gdx.utils.y.d
        public void b() {
            this.f2062d = -1;
            this.f2061c = -1;
            a();
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public b<K, V> next() {
            if (!this.f2059a) {
                throw new NoSuchElementException();
            }
            if (!this.f2063e) {
                throw new m("#iterator() cannot be used nested.");
            }
            y<K, V> yVar = this.f2060b;
            K[] kArr = yVar.f2044b;
            int i2 = this.f2061c;
            K k2 = kArr[i2];
            b<K, V> bVar = this.f2056f;
            bVar.f2057a = k2;
            bVar.f2058b = yVar.f2045c[i2];
            this.f2062d = i2;
            a();
            return bVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f2063e) {
                return this.f2059a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this;
        }
    }

    /* JADX INFO: compiled from: ObjectMap.java */
    public static class b<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public K f2057a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public V f2058b;

        public final String toString() {
            return this.f2057a + "=" + this.f2058b;
        }
    }

    /* JADX INFO: compiled from: ObjectMap.java */
    public static class c<K> extends d<K, Object, K> {
        @Override // com.badlogic.gdx.utils.y.d
        public void b() {
            this.f2062d = -1;
            this.f2061c = -1;
            a();
        }

        public com.badlogic.gdx.utils.a<K> c() {
            return d(new com.badlogic.gdx.utils.a<>(true, this.f2060b.f2043a));
        }

        public com.badlogic.gdx.utils.a<K> d(com.badlogic.gdx.utils.a<K> aVar) {
            while (this.f2059a) {
                aVar.a(next());
            }
            return aVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f2063e) {
                return this.f2059a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this;
        }

        public K next() {
            if (!this.f2059a) {
                throw new NoSuchElementException();
            }
            if (!this.f2063e) {
                throw new m("#iterator() cannot be used nested.");
            }
            K[] kArr = this.f2060b.f2044b;
            int i2 = this.f2061c;
            K k2 = kArr[i2];
            this.f2062d = i2;
            a();
            return k2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ObjectMap.java */
    static abstract class d<K, V, I> implements Iterable<I>, Iterator<I> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final y<K, V> f2060b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2061c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2062d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2063e = true;

        public d(y<K, V> yVar) {
            this.f2060b = yVar;
            b();
        }

        final void a() {
            int i2;
            K[] kArr = this.f2060b.f2044b;
            int length = kArr.length;
            do {
                i2 = this.f2061c + 1;
                this.f2061c = i2;
                if (i2 >= length) {
                    this.f2059a = false;
                    return;
                }
            } while (kArr[i2] == null);
            this.f2059a = true;
        }

        public abstract void b();

        public void remove() {
            int i2 = this.f2062d;
            if (i2 < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            y<K, V> yVar = this.f2060b;
            K[] kArr = yVar.f2044b;
            V[] vArr = yVar.f2045c;
            int i3 = yVar.f2049g;
            int i4 = i2 + 1;
            while (true) {
                int i5 = i4 & i3;
                K k2 = kArr[i5];
                if (k2 == null) {
                    break;
                }
                int i6 = yVar.i(k2);
                if (((i5 - i6) & i3) > ((i2 - i6) & i3)) {
                    kArr[i2] = k2;
                    vArr[i2] = vArr[i5];
                    i2 = i5;
                }
                i4 = i5 + 1;
            }
            kArr[i2] = null;
            vArr[i2] = null;
            yVar.f2043a--;
            if (i2 != this.f2062d) {
                this.f2061c--;
            }
            this.f2062d = -1;
        }
    }

    /* JADX INFO: compiled from: ObjectMap.java */
    public static class e<V> extends d<Object, V, V> {
        public e(y<?, V> yVar) {
            super(yVar);
        }

        @Override // com.badlogic.gdx.utils.y.d
        public void b() {
            this.f2062d = -1;
            this.f2061c = -1;
            a();
        }

        public com.badlogic.gdx.utils.a<V> c() {
            return d(new com.badlogic.gdx.utils.a<>(true, this.f2060b.f2043a));
        }

        public com.badlogic.gdx.utils.a<V> d(com.badlogic.gdx.utils.a<V> aVar) {
            while (this.f2059a) {
                aVar.a(next());
            }
            return aVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f2063e) {
                return this.f2059a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this;
        }

        public V next() {
            if (!this.f2059a) {
                throw new NoSuchElementException();
            }
            if (!this.f2063e) {
                throw new m("#iterator() cannot be used nested.");
            }
            V[] vArr = this.f2060b.f2045c;
            int i2 = this.f2061c;
            V v2 = vArr[i2];
            this.f2062d = i2;
            a();
            return v2;
        }
    }

    public y() {
        this(51, 0);
    }

    public final boolean a(K k2) {
        return h(k2) >= 0;
    }

    public a<K, V> b() {
        if (this.f2050h == null) {
            this.f2050h = new a(this);
            this.f2051i = new a(this);
        }
        a aVar = this.f2050h;
        if (aVar.f2063e) {
            this.f2051i.b();
            a<K, V> aVar2 = this.f2051i;
            aVar2.f2063e = true;
            this.f2050h.f2063e = false;
            return aVar2;
        }
        aVar.b();
        a<K, V> aVar3 = this.f2050h;
        aVar3.f2063e = true;
        this.f2051i.f2063e = false;
        return aVar3;
    }

    public final Object c(Object obj) {
        V[] vArr = this.f2045c;
        if (obj != null) {
            for (int length = vArr.length - 1; length >= 0; length--) {
                if (vArr[length] == obj) {
                    return this.f2044b[length];
                }
            }
            return null;
        }
        K[] kArr = this.f2044b;
        for (int length2 = vArr.length - 1; length2 >= 0; length2--) {
            K k2 = kArr[length2];
            if (k2 != null && vArr[length2] == null) {
                return k2;
            }
        }
        return null;
    }

    public void clear() {
        if (this.f2043a == 0) {
            return;
        }
        this.f2043a = 0;
        Arrays.fill(this.f2044b, (Object) null);
        Arrays.fill(this.f2045c, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends K> V d(T t2) {
        int iH = h(t2);
        if (iH < 0) {
            return null;
        }
        return this.f2045c[iH];
    }

    public final V e(K k2, V v2) {
        int iH = h(k2);
        return iH < 0 ? v2 : this.f2045c[iH];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        if (yVar.f2043a != this.f2043a) {
            return false;
        }
        K[] kArr = this.f2044b;
        V[] vArr = this.f2045c;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k2 = kArr[i2];
            if (k2 != null) {
                V v2 = vArr[i2];
                if (v2 == null) {
                    if (yVar.e(k2, f2042n) != null) {
                        return false;
                    }
                } else if (!v2.equals(yVar.d(k2))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public a<K, V> iterator() {
        return b();
    }

    public c<K> g() {
        if (this.f2054l == null) {
            this.f2054l = new c(this);
            this.f2055m = new c(this);
        }
        c cVar = this.f2054l;
        if (cVar.f2063e) {
            this.f2055m.b();
            c<K> cVar2 = this.f2055m;
            cVar2.f2063e = true;
            this.f2054l.f2063e = false;
            return cVar2;
        }
        cVar.b();
        c<K> cVar3 = this.f2054l;
        cVar3.f2063e = true;
        this.f2055m.f2063e = false;
        return cVar3;
    }

    final int h(K k2) {
        if (k2 == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        K[] kArr = this.f2044b;
        int i2 = i(k2);
        while (true) {
            K k3 = kArr[i2];
            if (k3 == null) {
                return -(i2 + 1);
            }
            if (k3.equals(k2)) {
                return i2;
            }
            i2 = (i2 + 1) & this.f2049g;
        }
    }

    public final int hashCode() {
        int iHashCode = this.f2043a;
        K[] kArr = this.f2044b;
        V[] vArr = this.f2045c;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k2 = kArr[i2];
            if (k2 != null) {
                int iHashCode2 = k2.hashCode() + iHashCode;
                V v2 = vArr[i2];
                iHashCode = v2 != null ? v2.hashCode() + iHashCode2 : iHashCode2;
            }
        }
        return iHashCode;
    }

    protected final int i(K k2) {
        return (int) ((((long) k2.hashCode()) * (-7046029254386353131L)) >>> this.f2048f);
    }

    public V j(K k2, V v2) {
        int iH = h(k2);
        if (iH >= 0) {
            V[] vArr = this.f2045c;
            V v3 = vArr[iH];
            vArr[iH] = v2;
            return v3;
        }
        int i2 = -(iH + 1);
        K[] kArr = this.f2044b;
        kArr[i2] = k2;
        this.f2045c[i2] = v2;
        int i3 = this.f2043a + 1;
        this.f2043a = i3;
        if (i3 < this.f2047e) {
            return null;
        }
        l(kArr.length << 1);
        return null;
    }

    public V k(K k2) {
        int iH = h(k2);
        if (iH < 0) {
            return null;
        }
        K[] kArr = this.f2044b;
        V[] vArr = this.f2045c;
        V v2 = vArr[iH];
        int i2 = this.f2049g;
        int i3 = iH + 1;
        while (true) {
            int i4 = i3 & i2;
            K k3 = kArr[i4];
            if (k3 == null) {
                kArr[iH] = null;
                vArr[iH] = null;
                this.f2043a--;
                return v2;
            }
            int i5 = i(k3);
            if (((i4 - i5) & i2) > ((iH - i5) & i2)) {
                kArr[iH] = k3;
                vArr[iH] = vArr[i4];
                iH = i4;
            }
            i3 = i4 + 1;
        }
    }

    final void l(int i2) {
        int length = this.f2044b.length;
        this.f2047e = (int) (i2 * this.f2046d);
        int i3 = i2 - 1;
        this.f2049g = i3;
        this.f2048f = Long.numberOfLeadingZeros(i3);
        K[] kArr = this.f2044b;
        V[] vArr = this.f2045c;
        this.f2044b = (K[]) new Object[i2];
        this.f2045c = (V[]) new Object[i2];
        if (this.f2043a > 0) {
            for (int i4 = 0; i4 < length; i4++) {
                K k2 = kArr[i4];
                if (k2 != null) {
                    V v2 = vArr[i4];
                    K[] kArr2 = this.f2044b;
                    int i5 = i(k2);
                    while (kArr2[i5] != null) {
                        i5 = (i5 + 1) & this.f2049g;
                    }
                    kArr2[i5] = k2;
                    this.f2045c[i5] = v2;
                }
            }
        }
    }

    protected String m() {
        int i2;
        if (this.f2043a == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('{');
        Object[] objArr = this.f2044b;
        Object[] objArr2 = this.f2045c;
        int length = objArr.length;
        while (true) {
            i2 = length - 1;
            if (length <= 0) {
                break;
            }
            Object obj = objArr[i2];
            if (obj == null) {
                length = i2;
            } else {
                if (obj == this) {
                    obj = "(this)";
                }
                sb.append(obj);
                sb.append('=');
                Object obj2 = objArr2[i2];
                if (obj2 == this) {
                    obj2 = "(this)";
                }
                sb.append(obj2);
            }
        }
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                sb.append('}');
                return sb.toString();
            }
            Object obj3 = objArr[i3];
            if (obj3 != null) {
                sb.append(", ");
                if (obj3 == this) {
                    obj3 = "(this)";
                }
                sb.append(obj3);
                sb.append('=');
                Object obj4 = objArr2[i3];
                if (obj4 == this) {
                    obj4 = "(this)";
                }
                sb.append(obj4);
            }
            i2 = i3;
        }
    }

    public e<V> n() {
        if (this.f2052j == null) {
            this.f2052j = new e(this);
            this.f2053k = new e(this);
        }
        e eVar = this.f2052j;
        if (eVar.f2063e) {
            this.f2053k.b();
            e<V> eVar2 = this.f2053k;
            eVar2.f2063e = true;
            this.f2052j.f2063e = false;
            return eVar2;
        }
        eVar.b();
        e<V> eVar3 = this.f2052j;
        eVar3.f2063e = true;
        this.f2053k.f2063e = false;
        return eVar3;
    }

    public final String toString() {
        return m();
    }

    public y(int i2, int i3) {
        this.f2046d = 0.8f;
        int iH = z.h(i2, 0.8f);
        this.f2047e = (int) (iH * 0.8f);
        int i4 = iH - 1;
        this.f2049g = i4;
        this.f2048f = Long.numberOfLeadingZeros(i4);
        this.f2044b = (K[]) new Object[iH];
        this.f2045c = (V[]) new Object[iH];
    }
}
