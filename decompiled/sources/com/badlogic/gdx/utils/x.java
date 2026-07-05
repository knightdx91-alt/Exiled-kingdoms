package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObjectIntMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class x<K> implements Iterable<b<K>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2026a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    K[] f2027b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int[] f2028c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2029d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f2030e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f2031f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    a f2032g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    a f2033h;

    /* JADX INFO: compiled from: ObjectIntMap.java */
    public static class a<K> extends c<K> implements Iterable<b<K>>, Iterator<b<K>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        b<K> f2034f;

        public a(x<K> xVar) {
            super(xVar);
            this.f2034f = new b<>();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f2041e) {
                return this.f2037a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!this.f2037a) {
                throw new NoSuchElementException();
            }
            if (!this.f2041e) {
                throw new m("#iterator() cannot be used nested.");
            }
            x<K> xVar = this.f2038b;
            K[] kArr = xVar.f2027b;
            int i2 = this.f2039c;
            K k2 = kArr[i2];
            b<K> bVar = this.f2034f;
            bVar.f2035a = k2;
            bVar.f2036b = xVar.f2028c[i2];
            this.f2040d = i2;
            int length = kArr.length;
            while (true) {
                int i3 = this.f2039c + 1;
                this.f2039c = i3;
                if (i3 >= length) {
                    this.f2037a = false;
                    break;
                }
                if (kArr[i3] != null) {
                    this.f2037a = true;
                    break;
                }
            }
            return bVar;
        }
    }

    /* JADX INFO: compiled from: ObjectIntMap.java */
    public static class b<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public K f2035a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2036b;

        public final String toString() {
            return this.f2035a + "=" + this.f2036b;
        }
    }

    /* JADX INFO: compiled from: ObjectIntMap.java */
    private static class c<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2037a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final x<K> f2038b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2039c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2041e = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2040d = -1;

        public c(x<K> xVar) {
            int i2;
            this.f2038b = xVar;
            a aVar = (a) this;
            aVar.f2039c = -1;
            K[] kArr = aVar.f2038b.f2027b;
            int length = kArr.length;
            do {
                i2 = aVar.f2039c + 1;
                aVar.f2039c = i2;
                if (i2 >= length) {
                    aVar.f2037a = false;
                    return;
                }
            } while (kArr[i2] == null);
            aVar.f2037a = true;
        }

        public void remove() {
            int i2 = this.f2040d;
            if (i2 < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            x<K> xVar = this.f2038b;
            K[] kArr = xVar.f2027b;
            int[] iArr = xVar.f2028c;
            int i3 = xVar.f2031f;
            int i4 = i2 + 1;
            while (true) {
                int i5 = i4 & i3;
                K k2 = kArr[i5];
                if (k2 == null) {
                    break;
                }
                int iHashCode = (int) ((((long) k2.hashCode()) * (-7046029254386353131L)) >>> xVar.f2030e);
                if (((i5 - iHashCode) & i3) > ((i2 - iHashCode) & i3)) {
                    kArr[i2] = k2;
                    iArr[i2] = iArr[i5];
                    i2 = i5;
                }
                i4 = i5 + 1;
            }
            kArr[i2] = null;
            xVar.f2026a--;
            if (i2 != this.f2040d) {
                this.f2039c--;
            }
            this.f2040d = -1;
        }
    }

    public x() {
        int iH = z.h(51, 0.8f);
        this.f2029d = (int) (iH * 0.8f);
        int i2 = iH - 1;
        this.f2031f = i2;
        this.f2030e = Long.numberOfLeadingZeros(i2);
        this.f2027b = (K[]) new Object[iH];
        this.f2028c = new int[iH];
    }

    public final boolean a(K k2) {
        return d(k2) >= 0;
    }

    public final a<K> b() {
        if (this.f2032g == null) {
            this.f2032g = new a(this);
            this.f2033h = new a(this);
        }
        a aVar = this.f2032g;
        if (!aVar.f2041e) {
            aVar.f2040d = -1;
            aVar.f2039c = -1;
            K[] kArr = aVar.f2038b.f2027b;
            int length = kArr.length;
            while (true) {
                int i2 = aVar.f2039c + 1;
                aVar.f2039c = i2;
                if (i2 >= length) {
                    aVar.f2037a = false;
                    break;
                }
                if (kArr[i2] != null) {
                    aVar.f2037a = true;
                    break;
                }
            }
            a<K> aVar2 = this.f2032g;
            aVar2.f2041e = true;
            this.f2033h.f2041e = false;
            return aVar2;
        }
        a aVar3 = this.f2033h;
        aVar3.f2040d = -1;
        aVar3.f2039c = -1;
        K[] kArr2 = aVar3.f2038b.f2027b;
        int length2 = kArr2.length;
        while (true) {
            int i3 = aVar3.f2039c + 1;
            aVar3.f2039c = i3;
            if (i3 >= length2) {
                aVar3.f2037a = false;
                break;
            }
            if (kArr2[i3] != null) {
                aVar3.f2037a = true;
                break;
            }
        }
        a<K> aVar4 = this.f2033h;
        aVar4.f2041e = true;
        this.f2032g.f2041e = false;
        return aVar4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int c(int i2, Object obj) {
        int iD = d(obj);
        return iD < 0 ? i2 : this.f2028c[iD];
    }

    public final void clear() {
        if (this.f2026a == 0) {
            return;
        }
        this.f2026a = 0;
        Arrays.fill(this.f2027b, (Object) null);
    }

    final int d(K k2) {
        if (k2 == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        K[] kArr = this.f2027b;
        int iHashCode = (int) ((((long) k2.hashCode()) * (-7046029254386353131L)) >>> this.f2030e);
        while (true) {
            K k3 = kArr[iHashCode];
            if (k3 == null) {
                return -(iHashCode + 1);
            }
            if (k3.equals(k2)) {
                return iHashCode;
            }
            iHashCode = (iHashCode + 1) & this.f2031f;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e(int i2, String str) {
        int iD = d(str);
        if (iD >= 0) {
            this.f2028c[iD] = i2;
            return;
        }
        int i3 = -(iD + 1);
        K[] kArr = this.f2027b;
        kArr[i3] = str;
        this.f2028c[i3] = i2;
        int i4 = this.f2026a + 1;
        this.f2026a = i4;
        if (i4 >= this.f2029d) {
            int length = kArr.length << 1;
            int length2 = kArr.length;
            this.f2029d = (int) (length * 0.8f);
            int i5 = length - 1;
            this.f2031f = i5;
            this.f2030e = Long.numberOfLeadingZeros(i5);
            K[] kArr2 = this.f2027b;
            int[] iArr = this.f2028c;
            this.f2027b = (K[]) new Object[length];
            this.f2028c = new int[length];
            if (this.f2026a > 0) {
                for (int i6 = 0; i6 < length2; i6++) {
                    K k2 = kArr2[i6];
                    if (k2 != null) {
                        int i7 = iArr[i6];
                        K[] kArr3 = this.f2027b;
                        int iHashCode = (int) ((((long) k2.hashCode()) * (-7046029254386353131L)) >>> this.f2030e);
                        while (kArr3[iHashCode] != null) {
                            iHashCode = (iHashCode + 1) & this.f2031f;
                        }
                        kArr3[iHashCode] = k2;
                        this.f2028c[iHashCode] = i7;
                    }
                }
            }
        }
    }

    public final boolean equals(Object obj) {
        int iC;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        if (xVar.f2026a != this.f2026a) {
            return false;
        }
        K[] kArr = this.f2027b;
        int[] iArr = this.f2028c;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k2 = kArr[i2];
            if (k2 != null && (((iC = xVar.c(0, k2)) == 0 && !xVar.a(k2)) || iC != iArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int iHashCode = this.f2026a;
        K[] kArr = this.f2027b;
        int[] iArr = this.f2028c;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k2 = kArr[i2];
            if (k2 != null) {
                iHashCode = k2.hashCode() + iArr[i2] + iHashCode;
            }
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return b();
    }

    public final String toString() {
        int i2;
        if (this.f2026a == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('{');
        K[] kArr = this.f2027b;
        int[] iArr = this.f2028c;
        int length = kArr.length;
        while (true) {
            i2 = length - 1;
            if (length > 0) {
                K k2 = kArr[i2];
                if (k2 != null) {
                    sb.append(k2);
                    sb.append('=');
                    sb.append(iArr[i2]);
                    break;
                }
                length = i2;
            } else {
                break;
            }
        }
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                sb.append('}');
                return sb.toString();
            }
            K k3 = kArr[i3];
            if (k3 != null) {
                sb.append(", ");
                sb.append(k3);
                sb.append('=');
                sb.append(iArr[i3]);
            }
            i2 = i3;
        }
    }
}
