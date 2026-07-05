package android.support.v4.util;

import java.io.PrintWriter;

/* JADX INFO: compiled from: TimeUtils.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class k {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f588c = new int[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final Object[] f589d = new Object[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f586a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static char[] f587b = new char[24];

    static int a(int i2, int[] iArr, int i3) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else {
                if (i7 <= i3) {
                    return i6;
                }
                i4 = i6 - 1;
            }
        }
        return ~i5;
    }

    static int b(long[] jArr, int i2, long j2) {
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j3 = jArr[i5];
            if (j3 < j2) {
                i4 = i5 + 1;
            } else {
                if (j3 <= j2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public static void c(Object obj, StringBuilder sb) {
        int iLastIndexOf;
        if (obj == null) {
            sb.append("null");
            return;
        }
        String simpleName = obj.getClass().getSimpleName();
        if (simpleName.length() <= 0 && (iLastIndexOf = (simpleName = obj.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(iLastIndexOf + 1);
        }
        sb.append(simpleName);
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(obj)));
    }

    public static void d(long j2, PrintWriter printWriter) {
        synchronized (f586a) {
            printWriter.print(new String(f587b, 0, e(j2)));
        }
    }

    private static int e(long j2) {
        char c2;
        int i2;
        int i3;
        int i4;
        int i5;
        if (f587b.length < 0) {
            f587b = new char[0];
        }
        char[] cArr = f587b;
        if (j2 == 0) {
            cArr[0] = '0';
            return 1;
        }
        if (j2 > 0) {
            c2 = '+';
        } else {
            j2 = -j2;
            c2 = '-';
        }
        int i6 = (int) (j2 % 1000);
        int iFloor = (int) Math.floor(j2 / 1000);
        if (iFloor > 86400) {
            i2 = iFloor / 86400;
            iFloor -= 86400 * i2;
        } else {
            i2 = 0;
        }
        if (iFloor > 3600) {
            i3 = iFloor / 3600;
            iFloor -= i3 * 3600;
        } else {
            i3 = 0;
        }
        if (iFloor > 60) {
            int i7 = iFloor / 60;
            i4 = iFloor - (i7 * 60);
            i5 = i7;
        } else {
            i4 = iFloor;
            i5 = 0;
        }
        cArr[0] = c2;
        int iF = f(cArr, i2, 'd', 1, false, 0);
        int iF2 = f(cArr, i3, 'h', iF, iF != 1, 0);
        int iF3 = f(cArr, i5, 'm', iF2, iF2 != 1, 0);
        int iF4 = f(cArr, i6, 'm', f(cArr, i4, 's', iF3, iF3 != 1, 0), true, 0);
        cArr[iF4] = 's';
        return iF4 + 1;
    }

    private static int f(char[] cArr, int i2, char c2, int i3, boolean z2, int i4) {
        int i5;
        if (!z2 && i2 <= 0) {
            return i3;
        }
        if ((!z2 || i4 < 3) && i2 <= 99) {
            i5 = i3;
        } else {
            int i6 = i2 / 100;
            cArr[i3] = (char) (i6 + 48);
            i5 = i3 + 1;
            i2 -= i6 * 100;
        }
        if ((z2 && i4 >= 2) || i2 > 9 || i3 != i5) {
            int i7 = i2 / 10;
            cArr[i5] = (char) (i7 + 48);
            i5++;
            i2 -= i7 * 10;
        }
        cArr[i5] = (char) (i2 + 48);
        cArr[i5 + 1] = c2;
        return i5 + 2;
    }
}
