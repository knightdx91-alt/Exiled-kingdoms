package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: FloatArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f1823a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1824b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1825c;

    public j() {
        this(16);
    }

    public final void a(float f2) {
        float[] fArr = this.f1823a;
        int i2 = this.f1824b;
        if (i2 == fArr.length) {
            int iMax = Math.max(8, (int) (i2 * 1.75f));
            float[] fArr2 = new float[iMax];
            System.arraycopy(this.f1823a, 0, fArr2, 0, Math.min(this.f1824b, iMax));
            this.f1823a = fArr2;
            fArr = fArr2;
        }
        int i3 = this.f1824b;
        this.f1824b = i3 + 1;
        fArr[i3] = f2;
    }

    public final void b(j jVar, int i2, int i3) {
        if (i2 + i3 <= jVar.f1824b) {
            c(jVar.f1823a, i2, i3);
            return;
        }
        throw new IllegalArgumentException("offset + length must be <= size: " + i2 + " + " + i3 + " <= " + jVar.f1824b);
    }

    public final void c(float[] fArr, int i2, int i3) {
        float[] fArr2 = this.f1823a;
        int i4 = this.f1824b + i3;
        if (i4 > fArr2.length) {
            int iMax = Math.max(Math.max(8, i4), (int) (this.f1824b * 1.75f));
            float[] fArr3 = new float[iMax];
            System.arraycopy(this.f1823a, 0, fArr3, 0, Math.min(this.f1824b, iMax));
            this.f1823a = fArr3;
            fArr2 = fArr3;
        }
        System.arraycopy(fArr, i2, fArr2, this.f1824b, i3);
        this.f1824b += i3;
    }

    public final void d(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(a.a.h(i2, "additionalCapacity must be >= 0: "));
        }
        int i3 = this.f1824b + i2;
        if (i3 > this.f1823a.length) {
            int iMax = Math.max(Math.max(8, i3), (int) (this.f1824b * 1.75f));
            float[] fArr = new float[iMax];
            System.arraycopy(this.f1823a, 0, fArr, 0, Math.min(this.f1824b, iMax));
            this.f1823a = fArr;
        }
    }

    public final float e(int i2) {
        if (i2 < this.f1824b) {
            return this.f1823a[i2];
        }
        StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
        sbR.append(this.f1824b);
        throw new IndexOutOfBoundsException(sbR.toString());
    }

    public final boolean equals(Object obj) {
        int i2;
        if (obj == this) {
            return true;
        }
        if (!this.f1825c || !(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (!jVar.f1825c || (i2 = this.f1824b) != jVar.f1824b) {
            return false;
        }
        float[] fArr = this.f1823a;
        float[] fArr2 = jVar.f1823a;
        for (int i3 = 0; i3 < i2; i3++) {
            if (fArr[i3] != fArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    public final void f(int i2) {
        int i3 = this.f1824b;
        if (i2 >= i3) {
            StringBuilder sbR = a.a.r(i2, "end can't be >= size: ", " >= ");
            sbR.append(this.f1824b);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
        if (1 > i2) {
            throw new IndexOutOfBoundsException(a.a.h(i2, "start can't be > end: 1 > "));
        }
        int i4 = i3 - i2;
        if (this.f1825c) {
            float[] fArr = this.f1823a;
            int i5 = i2 + 1;
            System.arraycopy(fArr, i5, fArr, 1, i3 - i5);
        } else {
            int iMax = Math.max(i4, i2 + 1);
            float[] fArr2 = this.f1823a;
            System.arraycopy(fArr2, iMax, fArr2, 1, i3 - iMax);
        }
        this.f1824b = i4;
    }

    public final int hashCode() {
        if (!this.f1825c) {
            return super.hashCode();
        }
        float[] fArr = this.f1823a;
        int i2 = this.f1824b;
        int iFloatToRawIntBits = 1;
        for (int i3 = 0; i3 < i2; i3++) {
            iFloatToRawIntBits = (iFloatToRawIntBits * 31) + Float.floatToRawIntBits(fArr[i3]);
        }
        return iFloatToRawIntBits;
    }

    public final String toString() {
        if (this.f1824b == 0) {
            return "[]";
        }
        float[] fArr = this.f1823a;
        o0 o0Var = new o0(32);
        o0Var.g('[');
        o0Var.h(Float.toString(fArr[0]));
        for (int i2 = 1; i2 < this.f1824b; i2++) {
            o0Var.h(", ");
            o0Var.h(Float.toString(fArr[i2]));
        }
        o0Var.g(']');
        return o0Var.toString();
    }

    public j(int i2) {
        this.f1825c = true;
        this.f1823a = new float[i2];
    }
}
