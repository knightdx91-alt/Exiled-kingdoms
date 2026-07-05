package com.badlogic.gdx.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: Array.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class a<T> implements Iterable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T[] f1749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private C0027a f1752d;

    /* JADX INFO: renamed from: com.badlogic.gdx.utils.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Array.java */
    public static class C0027a<T> implements Iterable<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a<T> f1753a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f1754b = true;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private b f1755c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private b f1756d;

        public C0027a(a<T> aVar) {
            this.f1753a = aVar;
        }

        @Override // java.lang.Iterable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b<T> iterator() {
            if (this.f1755c == null) {
                a<T> aVar = this.f1753a;
                boolean z2 = this.f1754b;
                this.f1755c = new b(aVar, z2);
                this.f1756d = new b(aVar, z2);
            }
            b<T> bVar = this.f1755c;
            if (!bVar.f1760d) {
                bVar.f1759c = 0;
                bVar.f1760d = true;
                this.f1756d.f1760d = false;
                return bVar;
            }
            b<T> bVar2 = this.f1756d;
            bVar2.f1759c = 0;
            bVar2.f1760d = true;
            bVar.f1760d = false;
            return bVar2;
        }
    }

    /* JADX INFO: compiled from: Array.java */
    public static class b<T> implements Iterator<T>, Iterable<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a<T> f1757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f1758b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1759c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1760d = true;

        public b(a<T> aVar, boolean z2) {
            this.f1757a = aVar;
            this.f1758b = z2;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f1760d) {
                return this.f1759c < this.f1757a.f1750b;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final T next() {
            int i2 = this.f1759c;
            a<T> aVar = this.f1757a;
            if (i2 >= aVar.f1750b) {
                throw new NoSuchElementException(String.valueOf(this.f1759c));
            }
            if (!this.f1760d) {
                throw new m("#iterator() cannot be used nested.");
            }
            T[] tArr = aVar.f1749a;
            this.f1759c = i2 + 1;
            return tArr[i2];
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.f1758b) {
                throw new m("Remove not allowed.");
            }
            int i2 = this.f1759c - 1;
            this.f1759c = i2;
            this.f1757a.o(i2);
        }
    }

    public a() {
        this(true, 16);
    }

    public final void a(T t2) {
        T[] tArrR = this.f1749a;
        int i2 = this.f1750b;
        if (i2 == tArrR.length) {
            tArrR = r(Math.max(8, (int) (i2 * 1.75f)));
        }
        int i3 = this.f1750b;
        this.f1750b = i3 + 1;
        tArrR[i3] = t2;
    }

    public final void b(a<? extends T> aVar) {
        d(aVar.f1749a, 0, aVar.f1750b);
    }

    public final void c(a<? extends T> aVar, int i2, int i3) {
        if (i2 + i3 <= aVar.f1750b) {
            d(aVar.f1749a, i2, i3);
            return;
        }
        throw new IllegalArgumentException("start + count must be <= size: " + i2 + " + " + i3 + " <= " + aVar.f1750b);
    }

    public void clear() {
        Arrays.fill(this.f1749a, 0, this.f1750b, (Object) null);
        this.f1750b = 0;
    }

    public final void d(T[] tArr, int i2, int i3) {
        T[] tArrR = this.f1749a;
        int i4 = this.f1750b + i3;
        if (i4 > tArrR.length) {
            tArrR = r(Math.max(Math.max(8, i4), (int) (this.f1750b * 1.75f)));
        }
        System.arraycopy(tArr, i2, tArrR, this.f1750b, i3);
        this.f1750b = i4;
    }

    public final boolean e(T t2, boolean z2) {
        T[] tArr = this.f1749a;
        int i2 = this.f1750b - 1;
        if (z2 || t2 == null) {
            while (i2 >= 0) {
                int i3 = i2 - 1;
                if (tArr[i2] == t2) {
                    return true;
                }
                i2 = i3;
            }
            return false;
        }
        while (i2 >= 0) {
            int i4 = i2 - 1;
            if (t2.equals(tArr[i2])) {
                return true;
            }
            i2 = i4;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        int i2;
        if (obj == this) {
            return true;
        }
        if (!this.f1751c || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (!aVar.f1751c || (i2 = this.f1750b) != aVar.f1750b) {
            return false;
        }
        T[] tArr = this.f1749a;
        T[] tArr2 = aVar.f1749a;
        for (int i3 = 0; i3 < i2; i3++) {
            T t2 = tArr[i3];
            T t3 = tArr2[i3];
            if (t2 == null) {
                if (t3 != null) {
                    return false;
                }
            } else {
                if (!t2.equals(t3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void f(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(a.a.h(i2, "additionalCapacity must be >= 0: "));
        }
        int i3 = this.f1750b + i2;
        if (i3 > this.f1749a.length) {
            r(Math.max(Math.max(8, i3), (int) (this.f1750b * 1.75f)));
        }
    }

    public final T g() {
        if (this.f1750b != 0) {
            return this.f1749a[0];
        }
        throw new IllegalStateException("Array is empty.");
    }

    public final T get(int i2) {
        if (i2 < this.f1750b) {
            return this.f1749a[i2];
        }
        StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
        sbR.append(this.f1750b);
        throw new IndexOutOfBoundsException(sbR.toString());
    }

    public final int h(T t2, boolean z2) {
        T[] tArr = this.f1749a;
        int i2 = 0;
        if (z2 || t2 == null) {
            int i3 = this.f1750b;
            while (i2 < i3) {
                if (tArr[i2] == t2) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        int i4 = this.f1750b;
        while (i2 < i4) {
            if (t2.equals(tArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public final int hashCode() {
        if (!this.f1751c) {
            return super.hashCode();
        }
        T[] tArr = this.f1749a;
        int i2 = this.f1750b;
        int iHashCode = 1;
        for (int i3 = 0; i3 < i2; i3++) {
            iHashCode *= 31;
            T t2 = tArr[i3];
            if (t2 != null) {
                iHashCode = t2.hashCode() + iHashCode;
            }
        }
        return iHashCode;
    }

    public void i(int i2, T t2) {
        int i3 = this.f1750b;
        if (i2 > i3) {
            StringBuilder sbR = a.a.r(i2, "index can't be > size: ", " > ");
            sbR.append(this.f1750b);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
        T[] tArrR = this.f1749a;
        if (i3 == tArrR.length) {
            tArrR = r(Math.max(8, (int) (i3 * 1.75f)));
        }
        if (this.f1751c) {
            System.arraycopy(tArrR, i2, tArrR, i2 + 1, this.f1750b - i2);
        } else {
            tArrR[this.f1750b] = tArrR[i2];
        }
        this.f1750b++;
        tArrR[i2] = t2;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final b<T> iterator() {
        if (this.f1752d == null) {
            this.f1752d = new C0027a(this);
        }
        return this.f1752d.iterator();
    }

    public final T k() {
        int i2 = this.f1750b;
        if (i2 != 0) {
            return this.f1749a[i2 - 1];
        }
        throw new IllegalStateException("Array is empty.");
    }

    public T l() {
        int i2 = this.f1750b;
        if (i2 == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        int i3 = i2 - 1;
        this.f1750b = i3;
        T[] tArr = this.f1749a;
        T t2 = tArr[i3];
        tArr[i3] = null;
        return t2;
    }

    public final T m() {
        int i2 = this.f1750b;
        if (i2 == 0) {
            return null;
        }
        return this.f1749a[(int) a0.j.f69a.nextLong(i2)];
    }

    public boolean n(a aVar) {
        int i2 = this.f1750b;
        T[] tArr = this.f1749a;
        int i3 = aVar.f1750b;
        int i4 = i2;
        for (int i5 = 0; i5 < i3; i5++) {
            Object obj = aVar.get(i5);
            int i6 = 0;
            while (true) {
                if (i6 >= i4) {
                    break;
                }
                if (obj == tArr[i6]) {
                    o(i6);
                    i4--;
                    break;
                }
                i6++;
            }
        }
        return i4 != i2;
    }

    public T o(int i2) {
        int i3 = this.f1750b;
        if (i2 >= i3) {
            StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
            sbR.append(this.f1750b);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
        T[] tArr = this.f1749a;
        T t2 = tArr[i2];
        int i4 = i3 - 1;
        this.f1750b = i4;
        if (this.f1751c) {
            System.arraycopy(tArr, i2 + 1, tArr, i2, i4 - i2);
        } else {
            tArr[i2] = tArr[i4];
        }
        tArr[this.f1750b] = null;
        return t2;
    }

    public void p(int i2) {
        int i3 = this.f1750b;
        if (i2 >= i3) {
            StringBuilder sbR = a.a.r(i2, "end can't be >= size: ", " >= ");
            sbR.append(this.f1750b);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException(a.a.h(i2, "start can't be > end: 0 > "));
        }
        T[] tArr = this.f1749a;
        int i4 = i2 + 1;
        int i5 = i3 - i4;
        if (this.f1751c) {
            System.arraycopy(tArr, i4, tArr, 0, i3 - i4);
        } else {
            int iMax = Math.max(i5, i2 + 1);
            System.arraycopy(tArr, iMax, tArr, 0, i3 - iMax);
        }
        for (int i6 = i5; i6 < i3; i6++) {
            tArr[i6] = null;
        }
        this.f1750b = i5;
    }

    public boolean q(T t2, boolean z2) {
        T[] tArr = this.f1749a;
        if (z2 || t2 == null) {
            int i2 = this.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                if (tArr[i3] == t2) {
                    o(i3);
                    return true;
                }
            }
        } else {
            int i4 = this.f1750b;
            for (int i5 = 0; i5 < i4; i5++) {
                if (t2.equals(tArr[i5])) {
                    o(i5);
                    return true;
                }
            }
        }
        return false;
    }

    protected final T[] r(int i2) {
        T[] tArr = this.f1749a;
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i2));
        System.arraycopy(tArr, 0, tArr2, 0, Math.min(this.f1750b, tArr2.length));
        this.f1749a = tArr2;
        return tArr2;
    }

    public void s() {
        T[] tArr = this.f1749a;
        int i2 = this.f1750b;
        int i3 = i2 - 1;
        int i4 = i2 / 2;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i3 - i5;
            T t2 = tArr[i5];
            tArr[i5] = tArr[i6];
            tArr[i6] = t2;
        }
    }

    public void sort(Comparator<? super T> comparator) {
        l0.a().d(this.f1749a, comparator, this.f1750b);
    }

    public void t(int i2, T t2) {
        if (i2 < this.f1750b) {
            this.f1749a[i2] = t2;
        } else {
            StringBuilder sbR = a.a.r(i2, "index can't be >= size: ", " >= ");
            sbR.append(this.f1750b);
            throw new IndexOutOfBoundsException(sbR.toString());
        }
    }

    public final String toString() {
        if (this.f1750b == 0) {
            return "[]";
        }
        T[] tArr = this.f1749a;
        o0 o0Var = new o0(32);
        o0Var.g('[');
        o0Var.e(tArr[0]);
        for (int i2 = 1; i2 < this.f1750b; i2++) {
            o0Var.h(", ");
            o0Var.e(tArr[i2]);
        }
        o0Var.g(']');
        return o0Var.toString();
    }

    public final <V> V[] u(Class<V> cls) {
        V[] vArr = (V[]) ((Object[]) Array.newInstance((Class<?>) cls, this.f1750b));
        System.arraycopy(this.f1749a, 0, vArr, 0, this.f1750b);
        return vArr;
    }

    public void v(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(a.a.h(i2, "newSize must be >= 0: "));
        }
        if (this.f1750b <= i2) {
            return;
        }
        for (int i3 = i2; i3 < this.f1750b; i3++) {
            this.f1749a[i3] = null;
        }
        this.f1750b = i2;
    }

    public a(boolean z2, int i2) {
        this.f1751c = z2;
        this.f1749a = (T[]) new Object[i2];
    }

    public a(boolean z2, int i2, Class cls) {
        this.f1751c = z2;
        this.f1749a = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
    }

    public a(Class cls) {
        this(true, 16, cls);
    }

    public a(a<? extends T> aVar) {
        this(aVar.f1751c, aVar.f1750b, aVar.f1749a.getClass().getComponentType());
        int i2 = aVar.f1750b;
        this.f1750b = i2;
        System.arraycopy(aVar.f1749a, 0, this.f1749a, 0, i2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(T[] tArr) {
        int length = tArr.length;
        this(true, length, tArr.getClass().getComponentType());
        this.f1750b = length;
        System.arraycopy(tArr, 0, this.f1749a, 0, length);
    }
}
