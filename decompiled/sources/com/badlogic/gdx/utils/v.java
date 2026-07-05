package com.badlogic.gdx.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: LongMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class v<V> implements Iterable<b<V>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long[] f1992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    V[] f1993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    V f1994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1995e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final float f1996f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1997g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected int f1998h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected int f1999i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private a f2000j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private a f2001k;

    /* JADX INFO: compiled from: LongMap.java */
    public static class a<V> extends c<V> implements Iterable<b<V>>, Iterator<b<V>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final b<V> f2002f;

        public a(v vVar) {
            super(vVar);
            this.f2002f = new b<>();
        }

        @Override // com.badlogic.gdx.utils.v.c
        public final void b() {
            this.f2008d = -2;
            this.f2007c = -1;
            if (this.f2006b.f1995e) {
                this.f2005a = true;
            } else {
                a();
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f2009e) {
                return this.f2005a;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public final Iterator<b<V>> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!this.f2005a) {
                throw new NoSuchElementException();
            }
            if (!this.f2009e) {
                throw new m("#iterator() cannot be used nested.");
            }
            v<V> vVar = this.f2006b;
            long[] jArr = vVar.f1992b;
            int i2 = this.f2007c;
            b<V> bVar = this.f2002f;
            if (i2 == -1) {
                bVar.f2003a = 0L;
                bVar.f2004b = vVar.f1994d;
            } else {
                bVar.f2003a = jArr[i2];
                bVar.f2004b = vVar.f1993c[i2];
            }
            this.f2008d = i2;
            a();
            return bVar;
        }
    }

    /* JADX INFO: compiled from: LongMap.java */
    public static class b<V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f2003a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public V f2004b;

        public final String toString() {
            return this.f2003a + "=" + this.f2004b;
        }
    }

    /* JADX INFO: compiled from: LongMap.java */
    private static class c<V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2005a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final v<V> f2006b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2007c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2008d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2009e = true;

        public c(v<V> vVar) {
            this.f2006b = vVar;
            b();
        }

        final void a() {
            int i2;
            long[] jArr = this.f2006b.f1992b;
            int length = jArr.length;
            do {
                i2 = this.f2007c + 1;
                this.f2007c = i2;
                if (i2 >= length) {
                    this.f2005a = false;
                    return;
                }
            } while (jArr[i2] == 0);
            this.f2005a = true;
        }

        public void b() {
            throw null;
        }

        public void remove() {
            int i2 = this.f2008d;
            v<V> vVar = this.f2006b;
            if (i2 == -1 && vVar.f1995e) {
                vVar.f1995e = false;
            } else {
                if (i2 < 0) {
                    throw new IllegalStateException("next must be called before remove.");
                }
                long[] jArr = vVar.f1992b;
                V[] vArr = vVar.f1993c;
                int i3 = vVar.f1999i;
                int i4 = i2 + 1;
                while (true) {
                    int i5 = i4 & i3;
                    long j2 = jArr[i5];
                    if (j2 == 0) {
                        break;
                    }
                    int i6 = (int) ((((j2 >>> 32) ^ j2) * (-7046029254386353131L)) >>> vVar.f1998h);
                    if (((i5 - i6) & i3) > ((i2 - i6) & i3)) {
                        jArr[i2] = j2;
                        vArr[i2] = vArr[i5];
                        i2 = i5;
                    }
                    i4 = i5 + 1;
                }
                jArr[i2] = 0;
                if (i2 != this.f2008d) {
                    this.f2007c--;
                }
            }
            this.f2008d = -2;
            vVar.f1991a--;
        }
    }

    public v() {
        this(51, 0);
    }

    private int c(long j2) {
        long[] jArr = this.f1992b;
        int i2 = (int) ((((j2 >>> 32) ^ j2) * (-7046029254386353131L)) >>> this.f1998h);
        while (true) {
            long j3 = jArr[i2];
            if (j3 == 0) {
                return -(i2 + 1);
            }
            if (j3 == j2) {
                return i2;
            }
            i2 = (i2 + 1) & this.f1999i;
        }
    }

    public final a<V> a() {
        if (this.f2000j == null) {
            this.f2000j = new a(this);
            this.f2001k = new a(this);
        }
        a aVar = this.f2000j;
        if (aVar.f2009e) {
            this.f2001k.b();
            a<V> aVar2 = this.f2001k;
            aVar2.f2009e = true;
            this.f2000j.f2009e = false;
            return aVar2;
        }
        aVar.b();
        a<V> aVar3 = this.f2000j;
        aVar3.f2009e = true;
        this.f2001k.f2009e = false;
        return aVar3;
    }

    public final V b(long j2) {
        if (j2 == 0) {
            if (this.f1995e) {
                return this.f1994d;
            }
            return null;
        }
        int iC = c(j2);
        if (iC >= 0) {
            return this.f1993c[iC];
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d(long j2, Object obj) {
        if (j2 == 0) {
            this.f1994d = obj;
            if (this.f1995e) {
                return;
            }
            this.f1995e = true;
            this.f1991a++;
            return;
        }
        int iC = c(j2);
        if (iC >= 0) {
            V[] vArr = this.f1993c;
            Object[] objArr = vArr[iC];
            vArr[iC] = obj;
            return;
        }
        int i2 = -(iC + 1);
        long[] jArr = this.f1992b;
        jArr[i2] = j2;
        this.f1993c[i2] = obj;
        int i3 = this.f1991a + 1;
        this.f1991a = i3;
        if (i3 >= this.f1997g) {
            int length = jArr.length << 1;
            int length2 = jArr.length;
            this.f1997g = (int) (length * this.f1996f);
            int i4 = length - 1;
            this.f1999i = i4;
            this.f1998h = Long.numberOfLeadingZeros(i4);
            long[] jArr2 = this.f1992b;
            V[] vArr2 = this.f1993c;
            this.f1992b = new long[length];
            this.f1993c = (V[]) new Object[length];
            if (this.f1991a > 0) {
                for (int i5 = 0; i5 < length2; i5++) {
                    long j3 = jArr2[i5];
                    if (j3 != 0) {
                        V v2 = vArr2[i5];
                        long[] jArr3 = this.f1992b;
                        int i6 = (int) ((((j3 >>> 32) ^ j3) * (-7046029254386353131L)) >>> this.f1998h);
                        while (jArr3[i6] != 0) {
                            i6 = (i6 + 1) & this.f1999i;
                        }
                        jArr3[i6] = j3;
                        this.f1993c[i6] = v2;
                    }
                }
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        if (vVar.f1991a != this.f1991a) {
            return false;
        }
        boolean z2 = vVar.f1995e;
        boolean z3 = this.f1995e;
        if (z2 != z3) {
            return false;
        }
        if (z3) {
            V v2 = vVar.f1994d;
            if (v2 == null) {
                if (this.f1994d != null) {
                    return false;
                }
            } else if (!v2.equals(this.f1994d)) {
                return false;
            }
        }
        long[] jArr = this.f1992b;
        V[] vArr = this.f1993c;
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                V v3 = vArr[i2];
                if (v3 == null) {
                    V v4 = (V) y.f2042n;
                    if (j2 != 0) {
                        int iC = vVar.c(j2);
                        if (iC >= 0) {
                            v4 = (V) vVar.f1993c[iC];
                        }
                    } else if (vVar.f1995e) {
                        v4 = vVar.f1994d;
                    }
                    if (v4) {
                        return false;
                    }
                } else if (!v3.equals(vVar.b(j2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public final int hashCode() {
        V v2;
        int iHashCode = this.f1991a;
        if (this.f1995e && (v2 = this.f1994d) != null) {
            iHashCode += v2.hashCode();
        }
        long[] jArr = this.f1992b;
        V[] vArr = this.f1993c;
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                iHashCode = (int) ((j2 * 31) + ((long) iHashCode));
                V v3 = vArr[i2];
                if (v3 != null) {
                    iHashCode = v3.hashCode() + iHashCode;
                }
            }
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public final Iterator<b<V>> iterator() {
        return a();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0042 -> B:16:0x0043). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String toString() {
        int i2;
        if (this.f1991a == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        long[] jArr = this.f1992b;
        V[] vArr = this.f1993c;
        int length = jArr.length;
        if (this.f1995e) {
            sb.append("0=");
            sb.append(this.f1994d);
            i2 = length - 1;
            if (length > 0) {
                long j2 = jArr[i2];
                if (j2 != 0) {
                    sb.append(", ");
                    sb.append(j2);
                    sb.append('=');
                    sb.append(vArr[i2]);
                }
                length = i2;
                i2 = length - 1;
                if (length > 0) {
                    sb.append(']');
                    return sb.toString();
                }
            }
        } else {
            while (true) {
                i2 = length - 1;
                if (length <= 0) {
                    break;
                }
                long j3 = jArr[i2];
                if (j3 != 0) {
                    sb.append(j3);
                    sb.append('=');
                    sb.append(vArr[i2]);
                    break;
                }
                length = i2;
            }
            length = i2;
            i2 = length - 1;
            if (length > 0) {
            }
        }
    }

    public v(int i2) {
        this(100, 0);
    }

    public v(int i2, int i3) {
        this.f1996f = 0.8f;
        int iH = z.h(i2, 0.8f);
        this.f1997g = (int) (iH * 0.8f);
        int i4 = iH - 1;
        this.f1999i = i4;
        this.f1998h = Long.numberOfLeadingZeros(i4);
        this.f1992b = new long[iH];
        this.f1993c = (V[]) new Object[iH];
    }
}
