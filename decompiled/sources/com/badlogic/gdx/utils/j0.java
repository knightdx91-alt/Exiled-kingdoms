package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: ShortArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short[] f1826a = new short[16];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1827b;

    public final void a(short s) {
        short[] sArr = this.f1826a;
        int i2 = this.f1827b;
        if (i2 == sArr.length) {
            int iMax = Math.max(8, (int) (i2 * 1.75f));
            short[] sArr2 = new short[iMax];
            System.arraycopy(this.f1826a, 0, sArr2, 0, Math.min(this.f1827b, iMax));
            this.f1826a = sArr2;
            sArr = sArr2;
        }
        int i3 = this.f1827b;
        this.f1827b = i3 + 1;
        sArr[i3] = s;
    }

    public final void b(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(a.a.h(i2, "additionalCapacity must be >= 0: "));
        }
        int i3 = this.f1827b + i2;
        if (i3 > this.f1826a.length) {
            int iMax = Math.max(Math.max(8, i3), (int) (this.f1827b * 1.75f));
            short[] sArr = new short[iMax];
            System.arraycopy(this.f1826a, 0, sArr, 0, Math.min(this.f1827b, iMax));
            this.f1826a = sArr;
        }
    }

    public final short c(int i2) {
        if (i2 < this.f1827b) {
            return this.f1826a[i2];
        }
        StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
        sbR.append(this.f1827b);
        throw new IndexOutOfBoundsException(sbR.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof j0)) {
            return false;
        }
        j0 j0Var = (j0) obj;
        j0Var.getClass();
        int i2 = this.f1827b;
        if (i2 != j0Var.f1827b) {
            return false;
        }
        short[] sArr = this.f1826a;
        short[] sArr2 = j0Var.f1826a;
        for (int i3 = 0; i3 < i2; i3++) {
            if (sArr[i3] != sArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        short[] sArr = this.f1826a;
        int i2 = this.f1827b;
        int i3 = 1;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 31) + sArr[i4];
        }
        return i3;
    }

    public final String toString() {
        if (this.f1827b == 0) {
            return "[]";
        }
        short[] sArr = this.f1826a;
        o0 o0Var = new o0(32);
        o0Var.g('[');
        o0Var.a(sArr[0]);
        for (int i2 = 1; i2 < this.f1827b; i2++) {
            o0Var.h(", ");
            o0Var.a(sArr[i2]);
        }
        o0Var.g(']');
        return o0Var.toString();
    }
}
