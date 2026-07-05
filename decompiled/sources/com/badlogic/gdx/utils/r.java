package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: IntSet.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int[] f1911b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1912c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1913d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f1914e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f1915f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f1916g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a f1917h;

    /* JADX INFO: compiled from: IntSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1918a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final r f1919b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1920c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1921d = true;

        public a(r rVar) {
            this.f1919b = rVar;
            a();
        }

        public final void a() {
            int i2;
            this.f1920c = -1;
            r rVar = this.f1919b;
            if (rVar.f1912c) {
                this.f1918a = true;
                return;
            }
            int[] iArr = rVar.f1911b;
            int length = iArr.length;
            do {
                i2 = this.f1920c + 1;
                this.f1920c = i2;
                if (i2 >= length) {
                    this.f1918a = false;
                    return;
                }
            } while (iArr[i2] == 0);
            this.f1918a = true;
        }
    }

    public r() {
        int iH = z.h(51, 0.8f);
        this.f1913d = (int) (iH * 0.8f);
        int i2 = iH - 1;
        this.f1915f = i2;
        this.f1914e = Long.numberOfLeadingZeros(i2);
        this.f1911b = new int[iH];
    }

    private int d(int i2) {
        int[] iArr = this.f1911b;
        int i3 = (int) ((((long) i2) * (-7046029254386353131L)) >>> this.f1914e);
        while (true) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return -(i3 + 1);
            }
            if (i4 == i2) {
                return i3;
            }
            i3 = (i3 + 1) & this.f1915f;
        }
    }

    public final void a(int i2) {
        if (i2 == 0) {
            if (this.f1912c) {
                return;
            }
            this.f1912c = true;
            this.f1910a++;
            return;
        }
        int iD = d(i2);
        if (iD >= 0) {
            return;
        }
        int[] iArr = this.f1911b;
        iArr[-(iD + 1)] = i2;
        int i3 = this.f1910a + 1;
        this.f1910a = i3;
        if (i3 >= this.f1913d) {
            int length = iArr.length << 1;
            int length2 = iArr.length;
            this.f1913d = (int) (length * 0.8f);
            int i4 = length - 1;
            this.f1915f = i4;
            this.f1914e = Long.numberOfLeadingZeros(i4);
            int[] iArr2 = this.f1911b;
            this.f1911b = new int[length];
            if (this.f1910a > 0) {
                for (int i5 = 0; i5 < length2; i5++) {
                    int i6 = iArr2[i5];
                    if (i6 != 0) {
                        int[] iArr3 = this.f1911b;
                        int i7 = (int) ((((long) i6) * (-7046029254386353131L)) >>> this.f1914e);
                        while (iArr3[i7] != 0) {
                            i7 = (i7 + 1) & this.f1915f;
                        }
                        iArr3[i7] = i6;
                    }
                }
            }
        }
    }

    public final boolean b(int i2) {
        return i2 == 0 ? this.f1912c : d(i2) >= 0;
    }

    public final a c() {
        if (this.f1916g == null) {
            this.f1916g = new a(this);
            this.f1917h = new a(this);
        }
        a aVar = this.f1916g;
        if (aVar.f1921d) {
            this.f1917h.a();
            a aVar2 = this.f1917h;
            aVar2.f1921d = true;
            this.f1916g.f1921d = false;
            return aVar2;
        }
        aVar.a();
        a aVar3 = this.f1916g;
        aVar3.f1921d = true;
        this.f1917h.f1921d = false;
        return aVar3;
    }

    public final void e(int i2) {
        if (i2 == 0) {
            if (this.f1912c) {
                this.f1912c = false;
                this.f1910a--;
                return;
            }
            return;
        }
        int iD = d(i2);
        if (iD < 0) {
            return;
        }
        int[] iArr = this.f1911b;
        int i3 = this.f1915f;
        int i4 = iD + 1;
        while (true) {
            int i5 = i4 & i3;
            int i6 = iArr[i5];
            if (i6 == 0) {
                iArr[iD] = 0;
                this.f1910a--;
                return;
            } else {
                int i7 = (int) ((((long) i6) * (-7046029254386353131L)) >>> this.f1914e);
                if (((i5 - i7) & i3) > ((iD - i7) & i3)) {
                    iArr[iD] = i6;
                    iD = i5;
                }
                i4 = i5 + 1;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (rVar.f1910a != this.f1910a || rVar.f1912c != this.f1912c) {
            return false;
        }
        for (int i2 : this.f1911b) {
            if (i2 != 0 && !rVar.b(i2)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i2 = this.f1910a;
        for (int i3 : this.f1911b) {
            if (i3 != 0) {
                i2 += i3;
            }
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0040  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002d -> B:16:0x002e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String toString() {
        int i2;
        if (this.f1910a == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        int[] iArr = this.f1911b;
        int length = iArr.length;
        if (this.f1912c) {
            sb.append("0");
            i2 = length - 1;
            if (length <= 0) {
                sb.append(']');
                return sb.toString();
            }
            int i3 = iArr[i2];
            if (i3 != 0) {
                sb.append(", ");
                sb.append(i3);
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
