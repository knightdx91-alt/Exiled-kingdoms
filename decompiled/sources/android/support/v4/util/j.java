package android.support.v4.util;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Object f581e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f582a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int[] f583b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object[] f584c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f585d;

    public j() {
        this(10);
    }

    private void c() {
        int i2 = this.f585d;
        int[] iArr = this.f583b;
        Object[] objArr = this.f584c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f581e) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f582a = false;
        this.f585d = i3;
    }

    public final void a(int i2, E e2) {
        int i3 = this.f585d;
        if (i3 != 0 && i2 <= this.f583b[i3 - 1]) {
            g(i2, e2);
            return;
        }
        if (this.f582a && i3 >= this.f583b.length) {
            c();
        }
        int i4 = this.f585d;
        if (i4 >= this.f583b.length) {
            int i5 = (i4 + 1) * 4;
            int i6 = 4;
            while (true) {
                if (i6 >= 32) {
                    break;
                }
                int i7 = (1 << i6) - 12;
                if (i5 <= i7) {
                    i5 = i7;
                    break;
                }
                i6++;
            }
            int i8 = i5 / 4;
            int[] iArr = new int[i8];
            Object[] objArr = new Object[i8];
            int[] iArr2 = this.f583b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f584c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f583b = iArr;
            this.f584c = objArr;
        }
        this.f583b[i4] = i2;
        this.f584c[i4] = e2;
        this.f585d = i4 + 1;
    }

    public final void b() {
        int i2 = this.f585d;
        Object[] objArr = this.f584c;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f585d = 0;
        this.f582a = false;
    }

    public final Object clone() {
        j jVar = null;
        try {
            j jVar2 = (j) super.clone();
            try {
                jVar2.f583b = (int[]) this.f583b.clone();
                jVar2.f584c = (Object[]) this.f584c.clone();
                return jVar2;
            } catch (CloneNotSupportedException unused) {
                jVar = jVar2;
                return jVar;
            }
        } catch (CloneNotSupportedException unused2) {
        }
    }

    public final E d(int i2) {
        E e2;
        int iA = k.a(this.f585d, this.f583b, i2);
        if (iA < 0 || (e2 = (E) this.f584c[iA]) == f581e) {
            return null;
        }
        return e2;
    }

    public final int e(int i2) {
        if (this.f582a) {
            c();
        }
        return k.a(this.f585d, this.f583b, i2);
    }

    public final int f(int i2) {
        if (this.f582a) {
            c();
        }
        return this.f583b[i2];
    }

    public final void g(int i2, E e2) {
        int iA = k.a(this.f585d, this.f583b, i2);
        if (iA >= 0) {
            this.f584c[iA] = e2;
            return;
        }
        int i3 = ~iA;
        int i4 = this.f585d;
        if (i3 < i4) {
            Object[] objArr = this.f584c;
            if (objArr[i3] == f581e) {
                this.f583b[i3] = i2;
                objArr[i3] = e2;
                return;
            }
        }
        if (this.f582a && i4 >= this.f583b.length) {
            c();
            i3 = ~k.a(this.f585d, this.f583b, i2);
        }
        int i5 = this.f585d;
        if (i5 >= this.f583b.length) {
            int i6 = (i5 + 1) * 4;
            int i7 = 4;
            while (true) {
                if (i7 >= 32) {
                    break;
                }
                int i8 = (1 << i7) - 12;
                if (i6 <= i8) {
                    i6 = i8;
                    break;
                }
                i7++;
            }
            int i9 = i6 / 4;
            int[] iArr = new int[i9];
            Object[] objArr2 = new Object[i9];
            int[] iArr2 = this.f583b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f584c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f583b = iArr;
            this.f584c = objArr2;
        }
        int i10 = this.f585d - i3;
        if (i10 != 0) {
            int[] iArr3 = this.f583b;
            int i11 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i11, i10);
            Object[] objArr4 = this.f584c;
            System.arraycopy(objArr4, i3, objArr4, i11, this.f585d - i3);
        }
        this.f583b[i3] = i2;
        this.f584c[i3] = e2;
        this.f585d++;
    }

    public final void h(int i2) {
        int iA = k.a(this.f585d, this.f583b, i2);
        if (iA >= 0) {
            Object[] objArr = this.f584c;
            Object obj = objArr[iA];
            Object obj2 = f581e;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f582a = true;
            }
        }
    }

    public final int i() {
        if (this.f582a) {
            c();
        }
        return this.f585d;
    }

    public final E j(int i2) {
        if (this.f582a) {
            c();
        }
        return (E) this.f584c[i2];
    }

    public final String toString() {
        if (i() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f585d * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f585d; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(f(i2));
            sb.append('=');
            E eJ = j(i2);
            if (eJ != this) {
                sb.append(eJ);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public j(int i2) {
        this.f582a = false;
        if (i2 == 0) {
            this.f583b = k.f588c;
            this.f584c = k.f589d;
        } else {
            int i3 = i2 * 4;
            int i4 = 4;
            while (true) {
                if (i4 >= 32) {
                    break;
                }
                int i5 = (1 << i4) - 12;
                if (i3 <= i5) {
                    i3 = i5;
                    break;
                }
                i4++;
            }
            int i6 = i3 / 4;
            this.f583b = new int[i6];
            this.f584c = new Object[i6];
        }
        this.f585d = 0;
    }
}
