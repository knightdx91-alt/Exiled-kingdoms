package com.badlogic.gdx.utils;

import java.util.Comparator;

/* JADX INFO: compiled from: DelayedRemovalArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h<T> extends a<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1811e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private o f1812f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1813g;

    public h() {
        super(true, 0);
        this.f1812f = new o(0);
    }

    private void y(int i2) {
        if (i2 < this.f1813g) {
            return;
        }
        o oVar = this.f1812f;
        int i3 = oVar.f1850b;
        for (int i4 = 0; i4 < i3; i4++) {
            int iD = oVar.d(i4);
            if (i2 == iD) {
                return;
            }
            if (i2 < iD) {
                oVar.e(i4, i2);
                return;
            }
        }
        oVar.a(i2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void clear() {
        if (this.f1811e > 0) {
            this.f1813g = this.f1750b;
        } else {
            super.clear();
        }
    }

    @Override // com.badlogic.gdx.utils.a
    public final void i(int i2, T t2) {
        if (this.f1811e > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.i(i2, t2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final T l() {
        if (this.f1811e <= 0) {
            return (T) super.l();
        }
        throw new IllegalStateException("Invalid between begin/end.");
    }

    @Override // com.badlogic.gdx.utils.a
    public final T o(int i2) {
        if (this.f1811e <= 0) {
            return (T) super.o(i2);
        }
        y(i2);
        return get(i2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void p(int i2) {
        if (this.f1811e <= 0) {
            super.p(i2);
            return;
        }
        while (i2 >= 0) {
            y(i2);
            i2--;
        }
    }

    @Override // com.badlogic.gdx.utils.a
    public final boolean q(T t2, boolean z2) {
        if (this.f1811e <= 0) {
            return super.q(t2, z2);
        }
        int iH = h(t2, z2);
        if (iH == -1) {
            return false;
        }
        y(iH);
        return true;
    }

    @Override // com.badlogic.gdx.utils.a
    public final void s() {
        throw null;
    }

    @Override // com.badlogic.gdx.utils.a
    public final void sort(Comparator<? super T> comparator) {
        if (this.f1811e > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.sort(comparator);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void t(int i2, T t2) {
        if (this.f1811e > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.t(i2, t2);
    }

    @Override // com.badlogic.gdx.utils.a
    public final void v(int i2) {
        if (this.f1811e > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.v(i2);
    }

    public final void w() {
        this.f1811e++;
    }

    public final void x() {
        int i2 = this.f1811e;
        if (i2 == 0) {
            throw new IllegalStateException("begin must be called before end.");
        }
        int i3 = i2 - 1;
        this.f1811e = i3;
        if (i3 == 0) {
            int i4 = this.f1813g;
            o oVar = this.f1812f;
            if (i4 <= 0 || i4 != this.f1750b) {
                int i5 = oVar.f1850b;
                for (int i6 = 0; i6 < i5; i6++) {
                    int iF = oVar.f();
                    if (iF >= this.f1813g) {
                        o(iF);
                    }
                }
                for (int i7 = this.f1813g - 1; i7 >= 0; i7--) {
                    o(i7);
                }
            } else {
                oVar.f1850b = 0;
                clear();
            }
            this.f1813g = 0;
        }
    }
}
