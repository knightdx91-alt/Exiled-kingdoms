package com.badlogic.gdx.utils;

import java.util.Comparator;

/* JADX INFO: compiled from: SnapshotArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class k0<T> extends a<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private T[] f1828e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private T[] f1829f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1830g;

    public k0() {
        super(com.badlogic.gdx.k.class);
    }

    private void y() {
        T[] tArr;
        T[] tArr2 = this.f1828e;
        if (tArr2 == null || tArr2 != (tArr = this.f1749a)) {
            return;
        }
        T[] tArr3 = this.f1829f;
        if (tArr3 != null) {
            int length = tArr3.length;
            int i2 = this.f1750b;
            if (length >= i2) {
                System.arraycopy(tArr, 0, tArr3, 0, i2);
                this.f1749a = this.f1829f;
                this.f1829f = null;
                return;
            }
        }
        r(tArr.length);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void clear() {
        y();
        super.clear();
    }

    @Override // com.badlogic.gdx.utils.a
    public final void i(int i2, T t2) {
        y();
        super.i(i2, t2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final T l() {
        y();
        return (T) super.l();
    }

    @Override // com.badlogic.gdx.utils.a
    public final boolean n(a aVar) {
        y();
        return super.n(aVar);
    }

    @Override // com.badlogic.gdx.utils.a
    public final T o(int i2) {
        y();
        return (T) super.o(i2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void p(int i2) {
        y();
        super.p(i2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final boolean q(T t2, boolean z2) {
        y();
        return super.q(t2, z2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void s() {
        throw null;
    }

    @Override // com.badlogic.gdx.utils.a
    public final void sort(Comparator<? super T> comparator) {
        y();
        super.sort(comparator);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void t(int i2, T t2) {
        y();
        super.t(i2, t2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void v(int i2) {
        y();
        super.v(i2);
    }

    public final T[] w() {
        y();
        T[] tArr = this.f1749a;
        this.f1828e = tArr;
        this.f1830g++;
        return tArr;
    }

    public final void x() {
        int iMax = Math.max(0, this.f1830g - 1);
        this.f1830g = iMax;
        T[] tArr = this.f1828e;
        if (tArr == null) {
            return;
        }
        if (tArr != this.f1749a && iMax == 0) {
            this.f1829f = tArr;
            int length = tArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.f1829f[i2] = null;
            }
        }
        this.f1828e = null;
    }

    public final void z(int i2, int i3) {
        y();
        int i4 = this.f1750b;
        if (i2 >= i4) {
            StringBuilder sbR = a.a.r(i2, "first can't be >= size: ", " >= ");
            sbR.append(this.f1750b);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
        if (i3 >= i4) {
            StringBuilder sbR2 = a.a.r(i3, "second can't be >= size: ", " >= ");
            sbR2.append(this.f1750b);
            throw new IndexOutOfBoundsException(sbR2.toString());
        }
        T[] tArr = this.f1749a;
        T t2 = tArr[i2];
        tArr[i2] = tArr[i3];
        tArr[i3] = t2;
    }
}
