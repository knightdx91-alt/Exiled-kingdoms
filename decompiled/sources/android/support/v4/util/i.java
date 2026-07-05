package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: compiled from: SimpleArrayMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class i<K, V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static Object[] f574d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static int f575e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static Object[] f576f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static int f577g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int[] f578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object[] f579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f580c;

    public i() {
        this.f578a = k.f588c;
        this.f579b = k.f589d;
        this.f580c = 0;
    }

    private void a(int i2) {
        if (i2 == 8) {
            synchronized (b.class) {
                try {
                    Object[] objArr = f576f;
                    if (objArr != null) {
                        this.f579b = objArr;
                        f576f = (Object[]) objArr[0];
                        this.f578a = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        f577g--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i2 == 4) {
            synchronized (b.class) {
                try {
                    Object[] objArr2 = f574d;
                    if (objArr2 != null) {
                        this.f579b = objArr2;
                        f574d = (Object[]) objArr2[0];
                        this.f578a = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        f575e--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.f578a = new int[i2];
        this.f579b = new Object[i2 << 1];
    }

    private static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                try {
                    if (f577g < 10) {
                        objArr[0] = f576f;
                        objArr[1] = iArr;
                        for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                            objArr[i3] = null;
                        }
                        f576f = objArr;
                        f577g++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (b.class) {
                try {
                    if (f575e < 10) {
                        objArr[0] = f574d;
                        objArr[1] = iArr;
                        for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                            objArr[i4] = null;
                        }
                        f574d = objArr;
                        f575e++;
                    }
                } finally {
                }
            }
        }
    }

    public final void b(int i2) {
        int i3 = this.f580c;
        int[] iArr = this.f578a;
        if (iArr.length < i2) {
            Object[] objArr = this.f579b;
            a(i2);
            if (this.f580c > 0) {
                System.arraycopy(iArr, 0, this.f578a, 0, i3);
                System.arraycopy(objArr, 0, this.f579b, 0, i3 << 1);
            }
            c(iArr, objArr, i3);
        }
        if (this.f580c != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public final void clear() {
        int i2 = this.f580c;
        if (i2 > 0) {
            int[] iArr = this.f578a;
            Object[] objArr = this.f579b;
            this.f578a = k.f588c;
            this.f579b = k.f589d;
            this.f580c = 0;
            c(iArr, objArr, i2);
        }
        if (this.f580c > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public final boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    final int d(int i2, Object obj) {
        int i3 = this.f580c;
        if (i3 == 0) {
            return -1;
        }
        try {
            int iA = k.a(i3, this.f578a, i2);
            if (iA < 0 || obj.equals(this.f579b[iA << 1])) {
                return iA;
            }
            int i4 = iA + 1;
            while (i4 < i3 && this.f578a[i4] == i2) {
                if (obj.equals(this.f579b[i4 << 1])) {
                    return i4;
                }
                i4++;
            }
            for (int i5 = iA - 1; i5 >= 0 && this.f578a[i5] == i2; i5--) {
                if (obj.equals(this.f579b[i5 << 1])) {
                    return i5;
                }
            }
            return ~i4;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int e(Object obj) {
        return obj == null ? f() : d(obj.hashCode(), obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (this.f580c != iVar.f580c) {
                return false;
            }
            for (int i2 = 0; i2 < this.f580c; i2++) {
                try {
                    K kH = h(i2);
                    V vJ = j(i2);
                    Object obj2 = iVar.get(kH);
                    if (vJ == null) {
                        if (obj2 != null || !iVar.containsKey(kH)) {
                            return false;
                        }
                    } else if (!vJ.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.f580c != map.size()) {
                return false;
            }
            for (int i3 = 0; i3 < this.f580c; i3++) {
                try {
                    K kH2 = h(i3);
                    V vJ2 = j(i3);
                    Object obj3 = map.get(kH2);
                    if (vJ2 == null) {
                        if (obj3 != null || !map.containsKey(kH2)) {
                            return false;
                        }
                    } else if (!vJ2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    final int f() {
        int i2 = this.f580c;
        if (i2 == 0) {
            return -1;
        }
        try {
            int iA = k.a(i2, this.f578a, 0);
            if (iA < 0 || this.f579b[iA << 1] == null) {
                return iA;
            }
            int i3 = iA + 1;
            while (i3 < i2 && this.f578a[i3] == 0) {
                if (this.f579b[i3 << 1] == null) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = iA - 1; i4 >= 0 && this.f578a[i4] == 0; i4--) {
                if (this.f579b[i4 << 1] == null) {
                    return i4;
                }
            }
            return ~i3;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    final int g(Object obj) {
        int i2 = this.f580c * 2;
        Object[] objArr = this.f579b;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public final V get(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return (V) this.f579b[(iE << 1) + 1];
        }
        return null;
    }

    public final K h(int i2) {
        return (K) this.f579b[i2 << 1];
    }

    public final int hashCode() {
        int[] iArr = this.f578a;
        Object[] objArr = this.f579b;
        int i2 = this.f580c;
        int i3 = 1;
        int i4 = 0;
        int iHashCode = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return iHashCode;
    }

    public final V i(int i2) {
        Object[] objArr = this.f579b;
        int i3 = i2 << 1;
        V v2 = (V) objArr[i3 + 1];
        int i4 = this.f580c;
        int i5 = 0;
        if (i4 <= 1) {
            c(this.f578a, objArr, i4);
            this.f578a = k.f588c;
            this.f579b = k.f589d;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.f578a;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.f579b;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.f579b;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.f580c) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.f578a, 0, i2);
                    System.arraycopy(objArr, 0, this.f579b, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.f578a, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.f579b, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 != this.f580c) {
            throw new ConcurrentModificationException();
        }
        this.f580c = i5;
        return v2;
    }

    public final boolean isEmpty() {
        return this.f580c <= 0;
    }

    public final V j(int i2) {
        return (V) this.f579b[(i2 << 1) + 1];
    }

    public final V put(K k2, V v2) {
        int i2;
        int iD;
        int i3 = this.f580c;
        if (k2 == null) {
            iD = f();
            i2 = 0;
        } else {
            int iHashCode = k2.hashCode();
            i2 = iHashCode;
            iD = d(iHashCode, k2);
        }
        if (iD >= 0) {
            int i4 = (iD << 1) + 1;
            Object[] objArr = this.f579b;
            V v3 = (V) objArr[i4];
            objArr[i4] = v2;
            return v3;
        }
        int i5 = ~iD;
        int[] iArr = this.f578a;
        if (i3 >= iArr.length) {
            int i6 = 8;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i6 = 4;
            }
            Object[] objArr2 = this.f579b;
            a(i6);
            if (i3 != this.f580c) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f578a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f579b, 0, objArr2.length);
            }
            c(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.f578a;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f579b;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.f580c - i5) << 1);
        }
        int i8 = this.f580c;
        if (i3 == i8) {
            int[] iArr4 = this.f578a;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.f579b;
                int i9 = i5 << 1;
                objArr4[i9] = k2;
                objArr4[i9 + 1] = v2;
                this.f580c = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final V remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return i(iE);
        }
        return null;
    }

    public final int size() {
        return this.f580c;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f580c * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f580c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            K kH = h(i2);
            if (kH != this) {
                sb.append(kH);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vJ = j(i2);
            if (vJ != this) {
                sb.append(vJ);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public i(int i2) {
        if (i2 == 0) {
            this.f578a = k.f588c;
            this.f579b = k.f589d;
        } else {
            a(i2);
        }
        this.f580c = 0;
    }
}
