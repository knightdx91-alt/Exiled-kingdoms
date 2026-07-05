package com.badlogic.gdx.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObjectFloatMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class w<K> implements Iterable<b<K>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2010a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    K[] f2011b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float[] f2012c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2013d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f2014e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f2015f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    a f2016g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    a f2017h;

    /* JADX INFO: compiled from: ObjectFloatMap.java */
    public static class a<K> extends c<K> implements Iterable<b<K>>, Iterator<b<K>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        b<K> f2018f;

        public a(w<K> wVar) {
            super(wVar);
            this.f2018f = new b<>();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f2025e) {
                return this.f2021a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!this.f2021a) {
                throw new NoSuchElementException();
            }
            if (!this.f2025e) {
                throw new m("#iterator() cannot be used nested.");
            }
            w<K> wVar = this.f2022b;
            K[] kArr = wVar.f2011b;
            int i2 = this.f2023c;
            K k2 = kArr[i2];
            b<K> bVar = this.f2018f;
            bVar.f2019a = k2;
            bVar.f2020b = wVar.f2012c[i2];
            this.f2024d = i2;
            int length = kArr.length;
            while (true) {
                int i3 = this.f2023c + 1;
                this.f2023c = i3;
                if (i3 >= length) {
                    this.f2021a = false;
                    break;
                }
                if (kArr[i3] != null) {
                    this.f2021a = true;
                    break;
                }
            }
            return bVar;
        }
    }

    /* JADX INFO: compiled from: ObjectFloatMap.java */
    public static class b<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public K f2019a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2020b;

        public final String toString() {
            return this.f2019a + "=" + this.f2020b;
        }
    }

    /* JADX INFO: compiled from: ObjectFloatMap.java */
    private static class c<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2021a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final w<K> f2022b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2023c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2025e = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2024d = -1;

        public c(w<K> wVar) {
            int i2;
            this.f2022b = wVar;
            a aVar = (a) this;
            aVar.f2023c = -1;
            K[] kArr = aVar.f2022b.f2011b;
            int length = kArr.length;
            do {
                i2 = aVar.f2023c + 1;
                aVar.f2023c = i2;
                if (i2 >= length) {
                    aVar.f2021a = false;
                    return;
                }
            } while (kArr[i2] == null);
            aVar.f2021a = true;
        }

        public void remove() {
            int i2 = this.f2024d;
            if (i2 < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            w<K> wVar = this.f2022b;
            K[] kArr = wVar.f2011b;
            float[] fArr = wVar.f2012c;
            int i3 = wVar.f2015f;
            int i4 = i2 + 1;
            while (true) {
                int i5 = i4 & i3;
                K k2 = kArr[i5];
                if (k2 == null) {
                    break;
                }
                int iHashCode = (int) ((((long) k2.hashCode()) * (-7046029254386353131L)) >>> wVar.f2014e);
                if (((i5 - iHashCode) & i3) > ((i2 - iHashCode) & i3)) {
                    kArr[i2] = k2;
                    fArr[i2] = fArr[i5];
                    i2 = i5;
                }
                i4 = i5 + 1;
            }
            kArr[i2] = null;
            wVar.f2010a--;
            if (i2 != this.f2024d) {
                this.f2023c--;
            }
            this.f2024d = -1;
        }
    }

    public w() {
        int iH = z.h(51, 0.8f);
        this.f2013d = (int) (iH * 0.8f);
        int i2 = iH - 1;
        this.f2015f = i2;
        this.f2014e = Long.numberOfLeadingZeros(i2);
        this.f2011b = (K[]) new Object[iH];
        this.f2012c = new float[iH];
    }

    public final a<K> a() {
        if (this.f2016g == null) {
            this.f2016g = new a(this);
            this.f2017h = new a(this);
        }
        a aVar = this.f2016g;
        if (!aVar.f2025e) {
            aVar.f2024d = -1;
            aVar.f2023c = -1;
            K[] kArr = aVar.f2022b.f2011b;
            int length = kArr.length;
            while (true) {
                int i2 = aVar.f2023c + 1;
                aVar.f2023c = i2;
                if (i2 >= length) {
                    aVar.f2021a = false;
                    break;
                }
                if (kArr[i2] != null) {
                    aVar.f2021a = true;
                    break;
                }
            }
            a<K> aVar2 = this.f2016g;
            aVar2.f2025e = true;
            this.f2017h.f2025e = false;
            return aVar2;
        }
        a aVar3 = this.f2017h;
        aVar3.f2024d = -1;
        aVar3.f2023c = -1;
        K[] kArr2 = aVar3.f2022b.f2011b;
        int length2 = kArr2.length;
        while (true) {
            int i3 = aVar3.f2023c + 1;
            aVar3.f2023c = i3;
            if (i3 >= length2) {
                aVar3.f2021a = false;
                break;
            }
            if (kArr2[i3] != null) {
                aVar3.f2021a = true;
                break;
            }
        }
        a<K> aVar4 = this.f2017h;
        aVar4.f2025e = true;
        this.f2016g.f2025e = false;
        return aVar4;
    }

    final int b(K k2) {
        if (k2 == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        K[] kArr = this.f2011b;
        int iHashCode = (int) ((((long) k2.hashCode()) * (-7046029254386353131L)) >>> this.f2014e);
        while (true) {
            K k3 = kArr[iHashCode];
            if (k3 == null) {
                return -(iHashCode + 1);
            }
            if (k3.equals(k2)) {
                return iHashCode;
            }
            iHashCode = (iHashCode + 1) & this.f2015f;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        if (wVar.f2010a != this.f2010a) {
            return false;
        }
        K[] kArr = this.f2011b;
        float[] fArr = this.f2012c;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k2 = kArr[i2];
            if (k2 != null) {
                int iB = wVar.b(k2);
                float f2 = iB < 0 ? 0.0f : wVar.f2012c[iB];
                if ((f2 == 0.0f && wVar.b(k2) < 0) || f2 != fArr[i2]) {
                    return false;
                }
            }
        }
        return true;
    }

    public final int hashCode() {
        int iFloatToRawIntBits = this.f2010a;
        K[] kArr = this.f2011b;
        float[] fArr = this.f2012c;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k2 = kArr[i2];
            if (k2 != null) {
                iFloatToRawIntBits = Float.floatToRawIntBits(fArr[i2]) + k2.hashCode() + iFloatToRawIntBits;
            }
        }
        return iFloatToRawIntBits;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return a();
    }

    public final String toString() {
        int i2;
        if (this.f2010a == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('{');
        K[] kArr = this.f2011b;
        float[] fArr = this.f2012c;
        int length = kArr.length;
        while (true) {
            i2 = length - 1;
            if (length > 0) {
                K k2 = kArr[i2];
                if (k2 != null) {
                    sb.append(k2);
                    sb.append('=');
                    sb.append(fArr[i2]);
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
                sb.append(fArr[i3]);
            }
            i2 = i3;
        }
    }
}
