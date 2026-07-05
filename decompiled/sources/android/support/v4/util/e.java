package android.support.v4.util;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Object f552e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f553a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long[] f554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object[] f555c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f556d;

    public e() {
        int i2;
        int i3 = 4;
        while (true) {
            i2 = 80;
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (80 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        int i5 = i2 / 8;
        this.f554b = new long[i5];
        this.f555c = new Object[i5];
        this.f556d = 0;
    }

    private void c() {
        int i2 = this.f556d;
        long[] jArr = this.f554b;
        Object[] objArr = this.f555c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f552e) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f553a = false;
        this.f556d = i3;
    }

    public final void a() {
        int i2 = this.f556d;
        Object[] objArr = this.f555c;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f556d = 0;
        this.f553a = false;
    }

    public final void b(long j2) {
        int iB = k.b(this.f554b, this.f556d, j2);
        if (iB >= 0) {
            Object[] objArr = this.f555c;
            Object obj = objArr[iB];
            Object obj2 = f552e;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f553a = true;
            }
        }
    }

    public final Object clone() {
        e eVar = null;
        try {
            e eVar2 = (e) super.clone();
            try {
                eVar2.f554b = (long[]) this.f554b.clone();
                eVar2.f555c = (Object[]) this.f555c.clone();
                return eVar2;
            } catch (CloneNotSupportedException unused) {
                eVar = eVar2;
                return eVar;
            }
        } catch (CloneNotSupportedException unused2) {
        }
    }

    public final E d(long j2) {
        E e2;
        int iB = k.b(this.f554b, this.f556d, j2);
        if (iB < 0 || (e2 = (E) this.f555c[iB]) == f552e) {
            return null;
        }
        return e2;
    }

    public final void e(long j2, E e2) {
        int iB = k.b(this.f554b, this.f556d, j2);
        if (iB >= 0) {
            this.f555c[iB] = e2;
            return;
        }
        int i2 = ~iB;
        int i3 = this.f556d;
        if (i2 < i3) {
            Object[] objArr = this.f555c;
            if (objArr[i2] == f552e) {
                this.f554b[i2] = j2;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f553a && i3 >= this.f554b.length) {
            c();
            i2 = ~k.b(this.f554b, this.f556d, j2);
        }
        int i4 = this.f556d;
        if (i4 >= this.f554b.length) {
            int i5 = (i4 + 1) * 8;
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
            int i8 = i5 / 8;
            long[] jArr = new long[i8];
            Object[] objArr2 = new Object[i8];
            long[] jArr2 = this.f554b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f555c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f554b = jArr;
            this.f555c = objArr2;
        }
        int i9 = this.f556d - i2;
        if (i9 != 0) {
            long[] jArr3 = this.f554b;
            int i10 = i2 + 1;
            System.arraycopy(jArr3, i2, jArr3, i10, i9);
            Object[] objArr4 = this.f555c;
            System.arraycopy(objArr4, i2, objArr4, i10, this.f556d - i2);
        }
        this.f554b[i2] = j2;
        this.f555c[i2] = e2;
        this.f556d++;
    }

    public final String toString() {
        if (this.f553a) {
            c();
        }
        int i2 = this.f556d;
        if (i2 <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i2 * 28);
        sb.append('{');
        for (int i3 = 0; i3 < this.f556d; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            if (this.f553a) {
                c();
            }
            sb.append(this.f554b[i3]);
            sb.append('=');
            if (this.f553a) {
                c();
            }
            Object obj = this.f555c[i3];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
