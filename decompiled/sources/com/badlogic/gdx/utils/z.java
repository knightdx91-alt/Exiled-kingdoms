package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObjectSet.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class z<T> implements Iterable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    T[] f2065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f2066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f2068e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f2069f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f2070g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a f2071h;

    /* JADX INFO: compiled from: ObjectSet.java */
    public static class a<K> implements Iterable<K>, Iterator<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2072a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final z<K> f2073b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2074c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2075d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2076e = true;

        public a(z<K> zVar) {
            this.f2073b = zVar;
            a();
        }

        public void a() {
            int i2;
            this.f2075d = -1;
            this.f2074c = -1;
            K[] kArr = this.f2073b.f2065b;
            int length = kArr.length;
            do {
                i2 = this.f2074c + 1;
                this.f2074c = i2;
                if (i2 >= length) {
                    this.f2072a = false;
                    return;
                }
            } while (kArr[i2] == null);
            this.f2072a = true;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f2076e) {
                return this.f2072a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this;
        }

        public K next() {
            if (!this.f2072a) {
                throw new NoSuchElementException();
            }
            if (!this.f2076e) {
                throw new m("#iterator() cannot be used nested.");
            }
            K[] kArr = this.f2073b.f2065b;
            int i2 = this.f2074c;
            K k2 = kArr[i2];
            this.f2075d = i2;
            int length = kArr.length;
            while (true) {
                int i3 = this.f2074c + 1;
                this.f2074c = i3;
                if (i3 >= length) {
                    this.f2072a = false;
                    break;
                }
                if (kArr[i3] != null) {
                    this.f2072a = true;
                    break;
                }
            }
            return k2;
        }

        public void remove() {
            int i2 = this.f2075d;
            if (i2 < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            z<K> zVar = this.f2073b;
            K[] kArr = zVar.f2065b;
            int i3 = zVar.f2069f;
            int i4 = i2 + 1;
            while (true) {
                int i5 = i4 & i3;
                K k2 = kArr[i5];
                if (k2 == null) {
                    break;
                }
                int iF = zVar.f(k2);
                if (((i5 - iF) & i3) > ((i2 - iF) & i3)) {
                    kArr[i2] = k2;
                    i2 = i5;
                }
                i4 = i5 + 1;
            }
            kArr[i2] = null;
            zVar.f2064a--;
            if (i2 != this.f2075d) {
                this.f2074c--;
            }
            this.f2075d = -1;
        }
    }

    public z() {
        this(51, 0);
    }

    private void g(int i2) {
        int length = this.f2065b.length;
        this.f2067d = (int) (i2 * this.f2066c);
        int i3 = i2 - 1;
        this.f2069f = i3;
        this.f2068e = Long.numberOfLeadingZeros(i3);
        T[] tArr = this.f2065b;
        this.f2065b = (T[]) new Object[i2];
        if (this.f2064a > 0) {
            for (int i4 = 0; i4 < length; i4++) {
                T t2 = tArr[i4];
                if (t2 != null) {
                    T[] tArr2 = this.f2065b;
                    int iF = f(t2);
                    while (tArr2[iF] != null) {
                        iF = (iF + 1) & this.f2069f;
                    }
                    tArr2[iF] = t2;
                }
            }
        }
    }

    static int h(int i2, float f2) {
        int i3 = 1;
        if (i2 < 0) {
            throw new IllegalArgumentException(a.a.h(i2, "capacity must be >= 0: "));
        }
        int iMax = Math.max(2, (int) Math.ceil(i2 / f2));
        a0.o oVar = a0.j.f69a;
        if (iMax != 0) {
            int i4 = iMax - 1;
            int i5 = i4 | (i4 >> 1);
            int i6 = i5 | (i5 >> 2);
            int i7 = i6 | (i6 >> 4);
            int i8 = i7 | (i7 >> 8);
            i3 = 1 + (i8 | (i8 >> 16));
        }
        if (i3 <= 1073741824) {
            return i3;
        }
        throw new IllegalArgumentException(a.a.h(i2, "The required capacity is too large: "));
    }

    public void a(int i2) {
        int iH = h(i2, this.f2066c);
        if (this.f2065b.length <= iH) {
            clear();
        } else {
            this.f2064a = 0;
            g(iH);
        }
    }

    public boolean add(T t2) {
        int iE = e(t2);
        if (iE >= 0) {
            return false;
        }
        T[] tArr = this.f2065b;
        tArr[-(iE + 1)] = t2;
        int i2 = this.f2064a + 1;
        this.f2064a = i2;
        if (i2 >= this.f2067d) {
            g(tArr.length << 1);
        }
        return true;
    }

    public final void b(int i2) {
        int iH = h(this.f2064a + i2, this.f2066c);
        if (this.f2065b.length < iH) {
            g(iH);
        }
    }

    public final T c() {
        for (T t2 : this.f2065b) {
            if (t2 != null) {
                return t2;
            }
        }
        throw new IllegalStateException("ObjectSet is empty.");
    }

    public void clear() {
        if (this.f2064a == 0) {
            return;
        }
        this.f2064a = 0;
        Arrays.fill(this.f2065b, (Object) null);
    }

    public final boolean contains(T t2) {
        return e(t2) >= 0;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public a<T> iterator() {
        if (this.f2070g == null) {
            this.f2070g = new a(this);
            this.f2071h = new a(this);
        }
        a aVar = this.f2070g;
        if (aVar.f2076e) {
            this.f2071h.a();
            a<T> aVar2 = this.f2071h;
            aVar2.f2076e = true;
            this.f2070g.f2076e = false;
            return aVar2;
        }
        aVar.a();
        a<T> aVar3 = this.f2070g;
        aVar3.f2076e = true;
        this.f2071h.f2076e = false;
        return aVar3;
    }

    final int e(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        T[] tArr = this.f2065b;
        int iF = f(t2);
        while (true) {
            T t3 = tArr[iF];
            if (t3 == null) {
                return -(iF + 1);
            }
            if (t3.equals(t2)) {
                return iF;
            }
            iF = (iF + 1) & this.f2069f;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        if (zVar.f2064a != this.f2064a) {
            return false;
        }
        for (T t2 : this.f2065b) {
            if (t2 != null && !zVar.contains(t2)) {
                return false;
            }
        }
        return true;
    }

    protected final int f(T t2) {
        return (int) ((((long) t2.hashCode()) * (-7046029254386353131L)) >>> this.f2068e);
    }

    public final int hashCode() {
        int iHashCode = this.f2064a;
        for (T t2 : this.f2065b) {
            if (t2 != null) {
                iHashCode = t2.hashCode() + iHashCode;
            }
        }
        return iHashCode;
    }

    public String i() {
        int i2;
        if (this.f2064a == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(32);
        Object[] objArr = this.f2065b;
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
            }
        }
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return sb.toString();
            }
            Object obj2 = objArr[i3];
            if (obj2 != null) {
                sb.append(", ");
                if (obj2 == this) {
                    obj2 = "(this)";
                }
                sb.append(obj2);
            }
            i2 = i3;
        }
    }

    public boolean remove(T t2) {
        int iE = e(t2);
        if (iE < 0) {
            return false;
        }
        T[] tArr = this.f2065b;
        int i2 = this.f2069f;
        int i3 = iE + 1;
        while (true) {
            int i4 = i3 & i2;
            T t3 = tArr[i4];
            if (t3 == null) {
                tArr[iE] = null;
                this.f2064a--;
                return true;
            }
            int iF = f(t3);
            if (((i4 - iF) & i2) > ((iE - iF) & i2)) {
                tArr[iE] = t3;
                iE = i4;
            }
            i3 = i4 + 1;
        }
    }

    public String toString() {
        return "{" + i() + '}';
    }

    public z(int i2) {
        this(4, 0);
    }

    public z(int i2, int i3) {
        this.f2066c = 0.8f;
        int iH = h(i2, 0.8f);
        this.f2067d = (int) (iH * 0.8f);
        int i4 = iH - 1;
        this.f2069f = i4;
        this.f2068e = Long.numberOfLeadingZeros(i4);
        this.f2065b = (T[]) new Object[iH];
    }
}
