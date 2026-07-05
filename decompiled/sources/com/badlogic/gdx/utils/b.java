package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.y;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b<K, V> implements Iterable<y.b<K, V>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public K[] f1765a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public V[] f1766b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1767c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1768d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f1769e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f1770f;

    /* JADX INFO: compiled from: ArrayMap.java */
    public static class a<K, V> implements Iterable<y.b<K, V>>, Iterator<y.b<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b<K, V> f1771a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1773c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        y.b<K, V> f1772b = new y.b<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1774d = true;

        public a(b<K, V> bVar) {
            this.f1771a = bVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f1774d) {
                return this.f1773c < this.f1771a.f1767c;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator<y.b<K, V>> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final Object next() {
            int i2 = this.f1773c;
            b<K, V> bVar = this.f1771a;
            if (i2 >= bVar.f1767c) {
                throw new NoSuchElementException(String.valueOf(this.f1773c));
            }
            if (!this.f1774d) {
                throw new m("#iterator() cannot be used nested.");
            }
            K k2 = bVar.f1765a[i2];
            y.b<K, V> bVar2 = this.f1772b;
            bVar2.f2057a = k2;
            V[] vArr = bVar.f1766b;
            this.f1773c = i2 + 1;
            bVar2.f2058b = vArr[i2];
            return bVar2;
        }

        @Override // java.util.Iterator
        public final void remove() {
            int i2 = this.f1773c;
            int i3 = i2 - 1;
            this.f1773c = i3;
            b<K, V> bVar = this.f1771a;
            int i4 = bVar.f1767c;
            if (i3 >= i4) {
                throw new IndexOutOfBoundsException(String.valueOf(i3));
            }
            K[] kArr = bVar.f1765a;
            int i5 = i4 - 1;
            bVar.f1767c = i5;
            if (bVar.f1768d) {
                System.arraycopy(kArr, i2, kArr, i3, i5 - i3);
                V[] vArr = bVar.f1766b;
                System.arraycopy(vArr, i2, vArr, i3, bVar.f1767c - i3);
            } else {
                kArr[i3] = kArr[i5];
                V[] vArr2 = bVar.f1766b;
                vArr2[i3] = vArr2[i5];
            }
            int i6 = bVar.f1767c;
            kArr[i6] = null;
            bVar.f1766b[i6] = null;
        }
    }

    public b() {
        this.f1768d = true;
        this.f1765a = (K[]) new Object[16];
        this.f1766b = (V[]) new Object[16];
    }

    public final a<K, V> a() {
        if (this.f1769e == null) {
            this.f1769e = new a(this);
            this.f1770f = new a(this);
        }
        a<K, V> aVar = this.f1769e;
        if (!aVar.f1774d) {
            aVar.f1773c = 0;
            aVar.f1774d = true;
            this.f1770f.f1774d = false;
            return aVar;
        }
        a<K, V> aVar2 = this.f1770f;
        aVar2.f1773c = 0;
        aVar2.f1774d = true;
        aVar.f1774d = false;
        return aVar2;
    }

    public final V b(K k2, V v2) {
        K[] kArr = this.f1765a;
        int i2 = this.f1767c - 1;
        if (k2 == null) {
            while (i2 >= 0) {
                if (kArr[i2] == k2) {
                    return this.f1766b[i2];
                }
                i2--;
            }
        } else {
            while (i2 >= 0) {
                if (k2.equals(kArr[i2])) {
                    return this.f1766b[i2];
                }
                i2--;
            }
        }
        return v2;
    }

    public final void c(Object obj, Object obj2) {
        Object[] objArr = this.f1765a;
        int i2 = 0;
        if (obj == null) {
            int i3 = this.f1767c;
            while (i2 < i3) {
                if (objArr[i2] == obj) {
                    break;
                } else {
                    i2++;
                }
            }
            i2 = -1;
        } else {
            int i4 = this.f1767c;
            while (i2 < i4) {
                if (obj.equals(objArr[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
            i2 = -1;
        }
        if (i2 == -1) {
            int i5 = this.f1767c;
            if (i5 == this.f1765a.length) {
                e(Math.max(8, (int) (i5 * 1.75f)));
            }
            i2 = this.f1767c;
            this.f1767c = i2 + 1;
        }
        ((K[]) this.f1765a)[i2] = obj;
        ((V[]) this.f1766b)[i2] = obj2;
    }

    public final void d(b<? extends K, ? extends V> bVar) {
        int i2 = bVar.f1767c;
        int i3 = this.f1767c + i2;
        if (i3 >= this.f1765a.length) {
            e(Math.max(8, (int) (i3 * 1.75f)));
        }
        System.arraycopy(bVar.f1765a, 0, this.f1765a, this.f1767c, i2);
        System.arraycopy(bVar.f1766b, 0, this.f1766b, this.f1767c, i2);
        this.f1767c += i2;
    }

    protected final void e(int i2) {
        K[] kArr = (K[]) ((Object[]) Array.newInstance(this.f1765a.getClass().getComponentType(), i2));
        System.arraycopy(this.f1765a, 0, kArr, 0, Math.min(this.f1767c, kArr.length));
        this.f1765a = kArr;
        V[] vArr = (V[]) ((Object[]) Array.newInstance(this.f1766b.getClass().getComponentType(), i2));
        System.arraycopy(this.f1766b, 0, vArr, 0, Math.min(this.f1767c, vArr.length));
        this.f1766b = vArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        int i2 = bVar.f1767c;
        int i3 = this.f1767c;
        if (i2 != i3) {
            return false;
        }
        K[] kArr = this.f1765a;
        V[] vArr = this.f1766b;
        for (int i4 = 0; i4 < i3; i4++) {
            K k2 = kArr[i4];
            V v2 = vArr[i4];
            if (v2 == null) {
                if (bVar.b(k2, y.f2042n) != null) {
                    return false;
                }
            } else if (!v2.equals(bVar.b(k2, null))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        K[] kArr = this.f1765a;
        V[] vArr = this.f1766b;
        int i2 = this.f1767c;
        int iHashCode = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            K k2 = kArr[i3];
            V v2 = vArr[i3];
            if (k2 != null) {
                iHashCode += k2.hashCode() * 31;
            }
            if (v2 != null) {
                iHashCode = v2.hashCode() + iHashCode;
            }
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public final Iterator<y.b<K, V>> iterator() {
        return a();
    }

    public final String toString() {
        if (this.f1767c == 0) {
            return "{}";
        }
        K[] kArr = this.f1765a;
        V[] vArr = this.f1766b;
        o0 o0Var = new o0(32);
        o0Var.g('{');
        o0Var.e(kArr[0]);
        o0Var.g('=');
        o0Var.e(vArr[0]);
        for (int i2 = 1; i2 < this.f1767c; i2++) {
            o0Var.h(", ");
            o0Var.e(kArr[i2]);
            o0Var.g('=');
            o0Var.e(vArr[i2]);
        }
        o0Var.g('}');
        return o0Var.toString();
    }

    public b(boolean z2, int i2, Class cls) {
        this.f1768d = z2;
        this.f1765a = (K[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        this.f1766b = (V[]) ((Object[]) Array.newInstance((Class<?>) Matrix4.class, i2));
    }
}
