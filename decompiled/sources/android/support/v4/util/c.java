package android.support.v4.util;

import android.support.v4.util.g;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: ArraySet.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c<E> implements Collection<E>, Set<E> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int[] f540e = new int[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Object[] f541f = new Object[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static Object[] f542g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static int f543h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static Object[] f544i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static int f545j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int[] f546a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object[] f547b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f548c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    a f549d;

    public c() {
        this(0);
    }

    private void a(int i2) {
        if (i2 == 8) {
            synchronized (c.class) {
                try {
                    Object[] objArr = f544i;
                    if (objArr != null) {
                        this.f547b = objArr;
                        f544i = (Object[]) objArr[0];
                        this.f546a = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        f545j--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i2 == 4) {
            synchronized (c.class) {
                try {
                    Object[] objArr2 = f542g;
                    if (objArr2 != null) {
                        this.f547b = objArr2;
                        f542g = (Object[]) objArr2[0];
                        this.f546a = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        f543h--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.f546a = new int[i2];
        this.f547b = new Object[i2];
    }

    private static void b(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (c.class) {
                try {
                    if (f545j < 10) {
                        objArr[0] = f544i;
                        objArr[1] = iArr;
                        for (int i3 = i2 - 1; i3 >= 2; i3--) {
                            objArr[i3] = null;
                        }
                        f544i = objArr;
                        f545j++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (c.class) {
                try {
                    if (f543h < 10) {
                        objArr[0] = f542g;
                        objArr[1] = iArr;
                        for (int i4 = i2 - 1; i4 >= 2; i4--) {
                            objArr[i4] = null;
                        }
                        f542g = objArr;
                        f543h++;
                    }
                } finally {
                }
            }
        }
    }

    private int c(int i2, Object obj) {
        int i3 = this.f548c;
        if (i3 == 0) {
            return -1;
        }
        int iA = k.a(i3, this.f546a, i2);
        if (iA < 0 || obj.equals(this.f547b[iA])) {
            return iA;
        }
        int i4 = iA + 1;
        while (i4 < i3 && this.f546a[i4] == i2) {
            if (obj.equals(this.f547b[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iA - 1; i5 >= 0 && this.f546a[i5] == i2; i5--) {
            if (obj.equals(this.f547b[i5])) {
                return i5;
            }
        }
        return ~i4;
    }

    private int d() {
        int i2 = this.f548c;
        if (i2 == 0) {
            return -1;
        }
        int iA = k.a(i2, this.f546a, 0);
        if (iA < 0 || this.f547b[iA] == null) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.f546a[i3] == 0) {
            if (this.f547b[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.f546a[i4] == 0; i4--) {
            if (this.f547b[i4] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(E e2) {
        int i2;
        int iC;
        if (e2 == null) {
            iC = d();
            i2 = 0;
        } else {
            int iHashCode = e2.hashCode();
            i2 = iHashCode;
            iC = c(iHashCode, e2);
        }
        if (iC >= 0) {
            return false;
        }
        int i3 = ~iC;
        int i4 = this.f548c;
        int[] iArr = this.f546a;
        if (i4 >= iArr.length) {
            int i5 = 8;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 < 4) {
                i5 = 4;
            }
            Object[] objArr = this.f547b;
            a(i5);
            int[] iArr2 = this.f546a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f547b, 0, objArr.length);
            }
            b(iArr, objArr, this.f548c);
        }
        int i6 = this.f548c;
        if (i3 < i6) {
            int[] iArr3 = this.f546a;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.f547b;
            System.arraycopy(objArr2, i3, objArr2, i7, this.f548c - i3);
        }
        this.f546a[i3] = i2;
        this.f547b[i3] = e2;
        this.f548c++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection<? extends E> collection) {
        int size = collection.size() + this.f548c;
        int[] iArr = this.f546a;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.f547b;
            a(size);
            int i2 = this.f548c;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.f546a, 0, i2);
                System.arraycopy(objArr, 0, this.f547b, 0, this.f548c);
            }
            b(iArr, objArr, this.f548c);
        }
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        int i2 = this.f548c;
        if (i2 != 0) {
            b(this.f546a, this.f547b, i2);
            this.f546a = f540e;
            this.f547b = f541f;
            this.f548c = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final void e(int i2) {
        Object[] objArr = this.f547b;
        Object obj = objArr[i2];
        int i3 = this.f548c;
        if (i3 <= 1) {
            b(this.f546a, objArr, i3);
            this.f546a = f540e;
            this.f547b = f541f;
            this.f548c = 0;
            return;
        }
        int[] iArr = this.f546a;
        if (iArr.length <= 8 || i3 >= iArr.length / 3) {
            int i4 = i3 - 1;
            this.f548c = i4;
            if (i2 < i4) {
                int i5 = i2 + 1;
                System.arraycopy(iArr, i5, iArr, i2, i4 - i2);
                Object[] objArr2 = this.f547b;
                System.arraycopy(objArr2, i5, objArr2, i2, this.f548c - i2);
            }
            this.f547b[this.f548c] = null;
            return;
        }
        a(i3 > 8 ? i3 + (i3 >> 1) : 8);
        this.f548c--;
        if (i2 > 0) {
            System.arraycopy(iArr, 0, this.f546a, 0, i2);
            System.arraycopy(objArr, 0, this.f547b, 0, i2);
        }
        int i6 = this.f548c;
        if (i2 < i6) {
            int i7 = i2 + 1;
            System.arraycopy(iArr, i7, this.f546a, i2, i6 - i2);
            System.arraycopy(objArr, i7, this.f547b, i2, this.f548c - i2);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.f548c != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f548c; i2++) {
                try {
                    if (!set.contains(this.f547b[i2])) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public final E f(int i2) {
        return (E) this.f547b[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.f546a;
        int i2 = this.f548c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public final int indexOf(Object obj) {
        return obj == null ? d() : c(obj.hashCode(), obj);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f548c <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<E> iterator() {
        if (this.f549d == null) {
            this.f549d = new a(this, 1);
        }
        a aVar = this.f549d;
        if (aVar.f558b == null) {
            aVar.f558b = new g.c();
        }
        return (Iterator<E>) aVar.f558b.iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        e(iIndexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        boolean z2 = false;
        for (int i2 = this.f548c - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.f547b[i2])) {
                e(i2);
                z2 = true;
            }
        }
        return z2;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.f548c;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        int i2 = this.f548c;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.f547b, 0, objArr, 0, i2);
        return objArr;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f548c * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.f548c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object obj = this.f547b[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public c(int i2) {
        if (i2 == 0) {
            this.f546a = f540e;
            this.f547b = f541f;
        } else {
            a(i2);
        }
        this.f548c = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f548c) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f548c));
        }
        System.arraycopy(this.f547b, 0, tArr, 0, this.f548c);
        int length = tArr.length;
        int i2 = this.f548c;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }
}
