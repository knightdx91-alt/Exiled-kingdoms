package com.badlogic.gdx.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: Queue.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e0<T> implements Iterable<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f1789e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int f1786b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f1787c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1788d = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected T[] f1785a = (T[]) new Object[16];

    /* JADX INFO: compiled from: Queue.java */
    public static class a<T> implements Iterable<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e0<T> f1790a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f1791b = true;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private b f1792c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private b f1793d;

        public a(e0<T> e0Var) {
            this.f1790a = e0Var;
        }

        @Override // java.lang.Iterable
        public final Iterator<T> iterator() {
            if (this.f1792c == null) {
                e0<T> e0Var = this.f1790a;
                boolean z2 = this.f1791b;
                this.f1792c = new b(e0Var, z2);
                this.f1793d = new b(e0Var, z2);
            }
            b bVar = this.f1792c;
            if (!bVar.f1797d) {
                bVar.f1796c = 0;
                bVar.f1797d = true;
                this.f1793d.f1797d = false;
                return bVar;
            }
            b bVar2 = this.f1793d;
            bVar2.f1796c = 0;
            bVar2.f1797d = true;
            bVar.f1797d = false;
            return bVar2;
        }
    }

    /* JADX INFO: compiled from: Queue.java */
    public static class b<T> implements Iterator<T>, Iterable<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e0<T> f1794a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f1795b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1796c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1797d = true;

        public b(e0<T> e0Var, boolean z2) {
            this.f1794a = e0Var;
            this.f1795b = z2;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f1797d) {
                return this.f1796c < this.f1794a.f1788d;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator<T> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final T next() {
            int i2 = this.f1796c;
            e0<T> e0Var = this.f1794a;
            if (i2 >= e0Var.f1788d) {
                throw new NoSuchElementException(String.valueOf(this.f1796c));
            }
            if (!this.f1797d) {
                throw new m("#iterator() cannot be used nested.");
            }
            this.f1796c = i2 + 1;
            return e0Var.get(i2);
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.f1795b) {
                throw new m("Remove not allowed.");
            }
            int i2 = this.f1796c - 1;
            this.f1796c = i2;
            e0<T> e0Var = this.f1794a;
            if (i2 < 0) {
                e0Var.getClass();
                throw new IndexOutOfBoundsException(a.a.h(i2, "index can't be < 0: "));
            }
            if (i2 >= e0Var.f1788d) {
                StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
                sbR.append(e0Var.f1788d);
                throw new IndexOutOfBoundsException(sbR.toString());
            }
            T[] tArr = e0Var.f1785a;
            int i3 = e0Var.f1786b;
            int i4 = e0Var.f1787c;
            int i5 = i2 + i3;
            if (i3 < i4) {
                T t2 = tArr[i5];
                System.arraycopy(tArr, i5 + 1, tArr, i5, i4 - i5);
                tArr[i4] = null;
                e0Var.f1787c--;
            } else if (i5 >= tArr.length) {
                int length = i5 - tArr.length;
                T t3 = tArr[length];
                System.arraycopy(tArr, length + 1, tArr, length, i4 - length);
                e0Var.f1787c--;
            } else {
                T t4 = tArr[i5];
                System.arraycopy(tArr, i3, tArr, i3 + 1, i5 - i3);
                tArr[i3] = null;
                int i6 = e0Var.f1786b + 1;
                e0Var.f1786b = i6;
                if (i6 == tArr.length) {
                    e0Var.f1786b = 0;
                }
            }
            e0Var.f1788d--;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x003c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        int i2 = this.f1788d;
        if (e0Var.f1788d != i2) {
            return false;
        }
        T[] tArr = this.f1785a;
        int length = tArr.length;
        T[] tArr2 = e0Var.f1785a;
        int length2 = tArr2.length;
        int i3 = this.f1786b;
        int i4 = e0Var.f1786b;
        for (int i5 = 0; i5 < i2; i5++) {
            T t2 = tArr[i3];
            T t3 = tArr2[i4];
            if (t2 == null) {
                if (t3 != null) {
                    return false;
                }
                i3++;
                i4++;
                if (i3 == length) {
                    i3 = 0;
                }
                if (i4 != length2) {
                    i4 = 0;
                }
            } else {
                if (!t2.equals(t3)) {
                    return false;
                }
                i3++;
                i4++;
                if (i3 == length) {
                }
                if (i4 != length2) {
                }
            }
        }
        return true;
    }

    public final T get(int i2) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException(a.a.h(i2, "index can't be < 0: "));
        }
        if (i2 >= this.f1788d) {
            StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
            sbR.append(this.f1788d);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
        T[] tArr = this.f1785a;
        int length = this.f1786b + i2;
        if (length >= tArr.length) {
            length -= tArr.length;
        }
        return tArr[length];
    }

    public final int hashCode() {
        int i2 = this.f1788d;
        T[] tArr = this.f1785a;
        int length = tArr.length;
        int i3 = this.f1786b;
        int iHashCode = i2 + 1;
        for (int i4 = 0; i4 < i2; i4++) {
            T t2 = tArr[i3];
            iHashCode *= 31;
            if (t2 != null) {
                iHashCode = t2.hashCode() + iHashCode;
            }
            i3++;
            if (i3 == length) {
                i3 = 0;
            }
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        if (this.f1789e == null) {
            this.f1789e = new a(this);
        }
        return this.f1789e.iterator();
    }

    public final String toString() {
        if (this.f1788d == 0) {
            return "[]";
        }
        T[] tArr = this.f1785a;
        int length = this.f1786b;
        int i2 = this.f1787c;
        o0 o0Var = new o0(64);
        o0Var.g('[');
        o0Var.e(tArr[length]);
        while (true) {
            length = (length + 1) % tArr.length;
            if (length == i2) {
                o0Var.g(']');
                return o0Var.toString();
            }
            o0Var.h(", ");
            o0Var.e(tArr[length]);
        }
    }
}
