package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: ByteArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f1783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1784b;

    public e() {
        this(16);
    }

    public final byte[] a(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(a.a.h(i2, "additionalCapacity must be >= 0: "));
        }
        if (i2 > this.f1783a.length) {
            int iMax = Math.max(Math.max(8, i2), (int) (0 * 1.75f));
            byte[] bArr = new byte[iMax];
            System.arraycopy(this.f1783a, 0, bArr, 0, Math.min(0, iMax));
            this.f1783a = bArr;
        }
        return this.f1783a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return this.f1784b && (obj instanceof e) && ((e) obj).f1784b;
    }

    public final int hashCode() {
        if (this.f1784b) {
            return 1;
        }
        return super.hashCode();
    }

    public final String toString() {
        return "[]";
    }

    public e(int i2) {
        this.f1784b = true;
        this.f1783a = new byte[i2];
    }
}
