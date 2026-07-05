package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: IntArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f1849a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1850b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1851c;

    public o() {
        this(16);
    }

    public final void a(int i2) {
        int[] iArrG = this.f1849a;
        int i3 = this.f1850b;
        if (i3 == iArrG.length) {
            iArrG = g(Math.max(8, (int) (i3 * 1.75f)));
        }
        int i4 = this.f1850b;
        this.f1850b = i4 + 1;
        iArrG[i4] = i2;
    }

    public final void b(int... iArr) {
        int length = iArr.length;
        int[] iArrG = this.f1849a;
        int i2 = this.f1850b + length;
        if (i2 > iArrG.length) {
            iArrG = g(Math.max(Math.max(8, i2), (int) (this.f1850b * 1.75f)));
        }
        System.arraycopy(iArr, 0, iArrG, this.f1850b, length);
        this.f1850b += length;
    }

    public final void c(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(a.a.h(i2, "additionalCapacity must be >= 0: "));
        }
        int i3 = this.f1850b + i2;
        if (i3 > this.f1849a.length) {
            g(Math.max(Math.max(8, i3), (int) (this.f1850b * 1.75f)));
        }
    }

    public final int d(int i2) {
        if (i2 < this.f1850b) {
            return this.f1849a[i2];
        }
        StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
        sbR.append(this.f1850b);
        throw new IndexOutOfBoundsException(sbR.toString());
    }

    public final void e(int i2, int i3) {
        int i4 = this.f1850b;
        if (i2 > i4) {
            StringBuilder sbR = a.a.r(i2, "index can't be > size: ", " > ");
            sbR.append(this.f1850b);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
        int[] iArrG = this.f1849a;
        if (i4 == iArrG.length) {
            iArrG = g(Math.max(8, (int) (i4 * 1.75f)));
        }
        if (this.f1851c) {
            System.arraycopy(iArrG, i2, iArrG, i2 + 1, this.f1850b - i2);
        } else {
            iArrG[this.f1850b] = iArrG[i2];
        }
        this.f1850b++;
        iArrG[i2] = i3;
    }

    public final boolean equals(Object obj) {
        int i2;
        if (obj == this) {
            return true;
        }
        if (!this.f1851c || !(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        if (!oVar.f1851c || (i2 = this.f1850b) != oVar.f1850b) {
            return false;
        }
        int[] iArr = this.f1849a;
        int[] iArr2 = oVar.f1849a;
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3] != iArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    public final int f() {
        int[] iArr = this.f1849a;
        int i2 = this.f1850b - 1;
        this.f1850b = i2;
        return iArr[i2];
    }

    protected final int[] g(int i2) {
        int[] iArr = new int[i2];
        System.arraycopy(this.f1849a, 0, iArr, 0, Math.min(this.f1850b, i2));
        this.f1849a = iArr;
        return iArr;
    }

    public final void h() {
        int length = this.f1849a.length;
        int i2 = this.f1850b;
        if (length != i2) {
            g(i2);
        }
    }

    public final int hashCode() {
        if (!this.f1851c) {
            return super.hashCode();
        }
        int[] iArr = this.f1849a;
        int i2 = this.f1850b;
        int i3 = 1;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        return i3;
    }

    public final String toString() {
        if (this.f1850b == 0) {
            return "[]";
        }
        int[] iArr = this.f1849a;
        o0 o0Var = new o0(32);
        o0Var.g('[');
        o0Var.a(iArr[0]);
        for (int i2 = 1; i2 < this.f1850b; i2++) {
            o0Var.h(", ");
            o0Var.a(iArr[i2]);
        }
        o0Var.g(']');
        return o0Var.toString();
    }

    public o(int i2) {
        this.f1851c = true;
        this.f1849a = new int[i2];
    }
}
