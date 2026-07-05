package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: IntIntMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class p implements Iterable<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1855a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int[] f1856b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int[] f1857c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1858d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1859e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final float f1860f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1861g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected int f1862h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected int f1863i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private a f1864j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private a f1865k;

    /* JADX INFO: compiled from: IntIntMap.java */
    public static class a extends c implements Iterable<b>, Iterator<b> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final b f1866f;

        public a(p pVar) {
            super(pVar);
            this.f1866f = new b();
        }

        @Override // com.badlogic.gdx.utils.p.c
        public final void a() {
            int i2;
            this.f1872d = -2;
            this.f1871c = -1;
            p pVar = this.f1870b;
            if (pVar.f1859e) {
                this.f1869a = true;
                return;
            }
            int[] iArr = pVar.f1856b;
            int length = iArr.length;
            do {
                i2 = this.f1871c + 1;
                this.f1871c = i2;
                if (i2 >= length) {
                    this.f1869a = false;
                    return;
                }
            } while (iArr[i2] == 0);
            this.f1869a = true;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f1873e) {
                return this.f1869a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator<b> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final b next() {
            if (!this.f1869a) {
                throw new NoSuchElementException();
            }
            if (!this.f1873e) {
                throw new m("#iterator() cannot be used nested.");
            }
            p pVar = this.f1870b;
            int[] iArr = pVar.f1856b;
            int i2 = this.f1871c;
            b bVar = this.f1866f;
            if (i2 == -1) {
                bVar.f1867a = 0;
                bVar.f1868b = pVar.f1858d;
            } else {
                bVar.f1867a = iArr[i2];
                bVar.f1868b = pVar.f1857c[i2];
            }
            this.f1872d = i2;
            int length = iArr.length;
            while (true) {
                int i3 = this.f1871c + 1;
                this.f1871c = i3;
                if (i3 >= length) {
                    this.f1869a = false;
                    break;
                }
                if (iArr[i3] != 0) {
                    this.f1869a = true;
                    break;
                }
            }
            return bVar;
        }
    }

    /* JADX INFO: compiled from: IntIntMap.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1867a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1868b;

        public final String toString() {
            return this.f1867a + "=" + this.f1868b;
        }
    }

    /* JADX INFO: compiled from: IntIntMap.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1869a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final p f1870b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1871c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1872d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f1873e = true;

        public c(p pVar) {
            this.f1870b = pVar;
            a();
        }

        public void a() {
            throw null;
        }

        public void remove() {
            int i2 = this.f1872d;
            p pVar = this.f1870b;
            if (i2 == -1 && pVar.f1859e) {
                pVar.f1859e = false;
            } else {
                if (i2 < 0) {
                    throw new IllegalStateException("next must be called before remove.");
                }
                int[] iArr = pVar.f1856b;
                int[] iArr2 = pVar.f1857c;
                int i3 = pVar.f1863i;
                int i4 = i2 + 1;
                while (true) {
                    int i5 = i4 & i3;
                    int i6 = iArr[i5];
                    if (i6 == 0) {
                        break;
                    }
                    int i7 = (int) ((((long) i6) * (-7046029254386353131L)) >>> pVar.f1862h);
                    if (((i5 - i7) & i3) > ((i2 - i7) & i3)) {
                        iArr[i2] = i6;
                        iArr2[i2] = iArr2[i5];
                        i2 = i5;
                    }
                    i4 = i5 + 1;
                }
                iArr[i2] = 0;
                if (i2 != this.f1872d) {
                    this.f1871c--;
                }
            }
            this.f1872d = -2;
            pVar.f1855a--;
        }
    }

    public p() {
        this(51);
    }

    private int d(int i2) {
        int[] iArr = this.f1856b;
        int i3 = (int) ((((long) i2) * (-7046029254386353131L)) >>> this.f1862h);
        while (true) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return -(i3 + 1);
            }
            if (i4 == i2) {
                return i3;
            }
            i3 = (i3 + 1) & this.f1863i;
        }
    }

    private void g(int i2) {
        int length = this.f1856b.length;
        this.f1861g = (int) (i2 * this.f1860f);
        int i3 = i2 - 1;
        this.f1863i = i3;
        this.f1862h = Long.numberOfLeadingZeros(i3);
        int[] iArr = this.f1856b;
        int[] iArr2 = this.f1857c;
        this.f1856b = new int[i2];
        this.f1857c = new int[i2];
        if (this.f1855a > 0) {
            for (int i4 = 0; i4 < length; i4++) {
                int i5 = iArr[i4];
                if (i5 != 0) {
                    int i6 = iArr2[i4];
                    int[] iArr3 = this.f1856b;
                    int i7 = (int) ((((long) i5) * (-7046029254386353131L)) >>> this.f1862h);
                    while (iArr3[i7] != 0) {
                        i7 = (i7 + 1) & this.f1863i;
                    }
                    iArr3[i7] = i5;
                    this.f1857c[i7] = i6;
                }
            }
        }
    }

    public final boolean a(int i2) {
        return i2 == 0 ? this.f1859e : d(i2) >= 0;
    }

    public final void b(int i2) {
        int iH = z.h(this.f1855a + i2, this.f1860f);
        if (this.f1856b.length < iH) {
            g(iH);
        }
    }

    public final int c(int i2, int i3) {
        if (i2 == 0) {
            return this.f1859e ? this.f1858d : i3;
        }
        int iD = d(i2);
        return iD >= 0 ? this.f1857c[iD] : i3;
    }

    public final void clear() {
        if (this.f1855a == 0) {
            return;
        }
        Arrays.fill(this.f1856b, 0);
        this.f1855a = 0;
        this.f1859e = false;
    }

    public final void e(int i2, int i3) {
        if (i2 == 0) {
            this.f1858d = i3;
            if (this.f1859e) {
                return;
            }
            this.f1859e = true;
            this.f1855a++;
            return;
        }
        int iD = d(i2);
        if (iD >= 0) {
            this.f1857c[iD] = i3;
            return;
        }
        int i4 = -(iD + 1);
        int[] iArr = this.f1856b;
        iArr[i4] = i2;
        this.f1857c[i4] = i3;
        int i5 = this.f1855a + 1;
        this.f1855a = i5;
        if (i5 >= this.f1861g) {
            g(iArr.length << 1);
        }
    }

    public final boolean equals(Object obj) {
        int iC;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        if (pVar.f1855a != this.f1855a) {
            return false;
        }
        boolean z2 = pVar.f1859e;
        boolean z3 = this.f1859e;
        if (z2 != z3) {
            return false;
        }
        if (z3 && pVar.f1858d != this.f1858d) {
            return false;
        }
        int[] iArr = this.f1856b;
        int[] iArr2 = this.f1857c;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0 && (((iC = pVar.c(i3, 0)) == 0 && !pVar.a(i3)) || iC != iArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    public final void f(int i2) {
        if (i2 == 0) {
            if (this.f1859e) {
                this.f1859e = false;
                this.f1855a--;
                return;
            }
            return;
        }
        int iD = d(i2);
        if (iD < 0) {
            return;
        }
        int[] iArr = this.f1856b;
        int[] iArr2 = this.f1857c;
        int i3 = iArr2[iD];
        int i4 = this.f1863i;
        int i5 = iD + 1;
        while (true) {
            int i6 = i5 & i4;
            int i7 = iArr[i6];
            if (i7 == 0) {
                iArr[iD] = 0;
                this.f1855a--;
                return;
            }
            int i8 = (int) ((((long) i7) * (-7046029254386353131L)) >>> this.f1862h);
            if (((i6 - i8) & i4) > ((iD - i8) & i4)) {
                iArr[iD] = i7;
                iArr2[iD] = iArr2[i6];
                iD = i6;
            }
            i5 = i6 + 1;
        }
    }

    public final int hashCode() {
        int i2 = this.f1855a;
        if (this.f1859e) {
            i2 += this.f1858d;
        }
        int[] iArr = this.f1856b;
        int[] iArr2 = this.f1857c;
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = iArr[i3];
            if (i4 != 0) {
                i2 = (i4 * 31) + iArr2[i3] + i2;
            }
        }
        return i2;
    }

    @Override // java.lang.Iterable
    public final Iterator<b> iterator() {
        if (this.f1864j == null) {
            this.f1864j = new a(this);
            this.f1865k = new a(this);
        }
        a aVar = this.f1864j;
        if (aVar.f1873e) {
            this.f1865k.a();
            a aVar2 = this.f1865k;
            aVar2.f1873e = true;
            this.f1864j.f1873e = false;
            return aVar2;
        }
        aVar.a();
        a aVar3 = this.f1864j;
        aVar3.f1873e = true;
        this.f1865k.f1873e = false;
        return aVar3;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x003e -> B:16:0x003f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String toString() {
        int i2;
        if (this.f1855a == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        int[] iArr = this.f1856b;
        int[] iArr2 = this.f1857c;
        int length = iArr.length;
        if (this.f1859e) {
            sb.append("0=");
            sb.append(this.f1858d);
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
                sb.append(iArr2[i2]);
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
                        sb.append(iArr2[i2]);
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

    public p(int i2) {
        this.f1860f = 0.8f;
        int iH = z.h(i2, 0.8f);
        this.f1861g = (int) (iH * 0.8f);
        int i3 = iH - 1;
        this.f1863i = i3;
        this.f1862h = Long.numberOfLeadingZeros(i3);
        this.f1856b = new int[iH];
        this.f1857c = new int[iH];
    }
}
