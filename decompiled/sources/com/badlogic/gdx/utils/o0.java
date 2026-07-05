package com.badlogic.gdx.utils;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Arrays;

/* JADX INFO: compiled from: StringBuilder.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class o0 implements Appendable, CharSequence {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final char[] f1852c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public char[] f1853a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1854b;

    public o0() {
        this.f1853a = new char[16];
    }

    private void k(int i2) {
        char[] cArr = this.f1853a;
        int length = (cArr.length >> 1) + cArr.length + 2;
        if (i2 <= length) {
            i2 = length;
        }
        char[] cArr2 = new char[i2];
        System.arraycopy(cArr, 0, cArr2, 0, this.f1854b);
        this.f1853a = cArr2;
    }

    private void l(int i2, int i3) {
        char[] cArr = this.f1853a;
        int length = cArr.length;
        int i4 = this.f1854b;
        if (length - i4 >= i2) {
            System.arraycopy(cArr, i3, cArr, i2 + i3, i4 - i3);
            return;
        }
        int i5 = i4 + i2;
        int length2 = (cArr.length << 1) + 2;
        if (i5 <= length2) {
            i5 = length2;
        }
        char[] cArr2 = new char[i5];
        System.arraycopy(cArr, 0, cArr2, 0, i3);
        System.arraycopy(this.f1853a, i3, cArr2, i2 + i3, this.f1854b - i3);
        this.f1853a = cArr2;
    }

    public final void a(int i2) {
        if (i2 == Integer.MIN_VALUE) {
            h("-2147483648");
            return;
        }
        if (i2 < 0) {
            g('-');
            i2 = -i2;
        }
        char[] cArr = f1852c;
        if (i2 >= 10000) {
            if (i2 >= 1000000000) {
                g(cArr[(int) ((((long) i2) % 10000000000L) / 1000000000)]);
            }
            if (i2 >= 100000000) {
                g(cArr[(i2 % 1000000000) / 100000000]);
            }
            if (i2 >= 10000000) {
                g(cArr[(i2 % 100000000) / 10000000]);
            }
            if (i2 >= 1000000) {
                g(cArr[(i2 % 10000000) / 1000000]);
            }
            if (i2 >= 100000) {
                g(cArr[(i2 % 1000000) / 100000]);
            }
            g(cArr[(i2 % 100000) / 10000]);
        }
        if (i2 >= 1000) {
            g(cArr[(i2 % 10000) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT]);
        }
        if (i2 >= 100) {
            g(cArr[(i2 % CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) / 100]);
        }
        if (i2 >= 10) {
            g(cArr[(i2 % 100) / 10]);
        }
        g(cArr[i2 % 10]);
    }

    @Override // java.lang.Appendable
    public final /* bridge */ /* synthetic */ Appendable append(CharSequence charSequence) {
        d(charSequence);
        return this;
    }

    public final void b(long j2) {
        if (j2 == Long.MIN_VALUE) {
            h("-9223372036854775808");
            return;
        }
        if (j2 < 0) {
            g('-');
            j2 = -j2;
        }
        char[] cArr = f1852c;
        if (j2 >= 10000) {
            if (j2 >= 1000000000000000000L) {
                g(cArr[(int) ((j2 % 1.0E19d) / 1.0E18d)]);
            }
            if (j2 >= 100000000000000000L) {
                g(cArr[(int) ((j2 % 1000000000000000000L) / 100000000000000000L)]);
            }
            if (j2 >= 10000000000000000L) {
                g(cArr[(int) ((j2 % 100000000000000000L) / 10000000000000000L)]);
            }
            if (j2 >= 1000000000000000L) {
                g(cArr[(int) ((j2 % 10000000000000000L) / 1000000000000000L)]);
            }
            if (j2 >= 100000000000000L) {
                g(cArr[(int) ((j2 % 1000000000000000L) / 100000000000000L)]);
            }
            if (j2 >= 10000000000000L) {
                g(cArr[(int) ((j2 % 100000000000000L) / 10000000000000L)]);
            }
            if (j2 >= 1000000000000L) {
                g(cArr[(int) ((j2 % 10000000000000L) / 1000000000000L)]);
            }
            if (j2 >= 100000000000L) {
                g(cArr[(int) ((j2 % 1000000000000L) / 100000000000L)]);
            }
            if (j2 >= 10000000000L) {
                g(cArr[(int) ((j2 % 100000000000L) / 10000000000L)]);
            }
            if (j2 >= 1000000000) {
                g(cArr[(int) ((j2 % 10000000000L) / 1000000000)]);
            }
            if (j2 >= 100000000) {
                g(cArr[(int) ((j2 % 1000000000) / 100000000)]);
            }
            if (j2 >= 10000000) {
                g(cArr[(int) ((j2 % 100000000) / 10000000)]);
            }
            if (j2 >= 1000000) {
                g(cArr[(int) ((j2 % 10000000) / 1000000)]);
            }
            if (j2 >= 100000) {
                g(cArr[(int) ((j2 % 1000000) / 100000)]);
            }
            g(cArr[(int) ((j2 % 100000) / 10000)]);
        }
        if (j2 >= 1000) {
            g(cArr[(int) ((j2 % 10000) / 1000)]);
        }
        if (j2 >= 100) {
            g(cArr[(int) ((j2 % 1000) / 100)]);
        }
        if (j2 >= 10) {
            g(cArr[(int) ((j2 % 100) / 10)]);
        }
        g(cArr[(int) (j2 % 10)]);
    }

    public final void c(o0 o0Var) {
        if (o0Var == null) {
            j();
        } else {
            i(o0Var.f1853a, 0, o0Var.f1854b);
        }
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i2) {
        if (i2 < 0 || i2 >= this.f1854b) {
            throw new StringIndexOutOfBoundsException(i2);
        }
        return this.f1853a[i2];
    }

    public final void d(CharSequence charSequence) {
        if (charSequence == null) {
            j();
        } else if (!(charSequence instanceof o0)) {
            h(charSequence.toString());
        } else {
            o0 o0Var = (o0) charSequence;
            i(o0Var.f1853a, 0, o0Var.f1854b);
        }
    }

    public final void e(Object obj) {
        if (obj == null) {
            j();
        } else {
            h(obj.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o0.class != obj.getClass()) {
            return false;
        }
        o0 o0Var = (o0) obj;
        int i2 = this.f1854b;
        if (i2 != o0Var.f1854b) {
            return false;
        }
        char[] cArr = this.f1853a;
        char[] cArr2 = o0Var.f1853a;
        for (int i3 = 0; i3 < i2; i3++) {
            if (cArr[i3] != cArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    public final void f(char[] cArr) {
        int length = this.f1854b + cArr.length;
        if (length > this.f1853a.length) {
            k(length);
        }
        System.arraycopy(cArr, 0, this.f1853a, this.f1854b, cArr.length);
        this.f1854b = length;
    }

    final void g(char c2) {
        int i2 = this.f1854b;
        if (i2 == this.f1853a.length) {
            k(i2 + 1);
        }
        char[] cArr = this.f1853a;
        int i3 = this.f1854b;
        this.f1854b = i3 + 1;
        cArr[i3] = c2;
    }

    final void h(String str) {
        if (str == null) {
            j();
            return;
        }
        int length = str.length();
        int i2 = this.f1854b + length;
        if (i2 > this.f1853a.length) {
            k(i2);
        }
        str.getChars(0, length, this.f1853a, this.f1854b);
        this.f1854b = i2;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f1853a) + ((this.f1854b + 31) * 31);
    }

    final void i(char[] cArr, int i2, int i3) {
        if (i2 > cArr.length || i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(a.a.h(i2, "Offset out of bounds: "));
        }
        if (i3 < 0 || cArr.length - i2 < i3) {
            throw new ArrayIndexOutOfBoundsException(a.a.h(i3, "Length out of bounds: "));
        }
        int i4 = this.f1854b + i3;
        if (i4 > this.f1853a.length) {
            k(i4);
        }
        System.arraycopy(cArr, i2, this.f1853a, this.f1854b, i3);
        this.f1854b = i4;
    }

    public final boolean isEmpty() {
        return this.f1854b == 0;
    }

    final void j() {
        int i2 = this.f1854b + 4;
        if (i2 > this.f1853a.length) {
            k(i2);
        }
        char[] cArr = this.f1853a;
        int i3 = this.f1854b;
        int i4 = i3 + 1;
        this.f1854b = i4;
        cArr[i3] = 'n';
        int i5 = i3 + 2;
        this.f1854b = i5;
        cArr[i4] = 'u';
        int i6 = i3 + 3;
        this.f1854b = i6;
        cArr[i5] = 'l';
        this.f1854b = i3 + 4;
        cArr[i6] = 'l';
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f1854b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
    
        throw new java.lang.StringIndexOutOfBoundsException();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m(char c2, String str) {
        int length = str.length();
        int i2 = 0;
        while (true) {
            int i3 = this.f1854b;
            if (i2 == i3) {
                return;
            }
            if (this.f1853a[i2] == c2) {
                int i4 = i2 + 1;
                if (i2 < 0) {
                    break;
                }
                if (i4 > i3) {
                    i4 = i3;
                }
                if (i4 <= i2) {
                    if (i2 != i4) {
                        break;
                    }
                    if (i2 < 0 || i2 > i3) {
                        break;
                    }
                    int length2 = str.length();
                    if (length2 != 0) {
                        l(length2, i2);
                        str.getChars(0, length2, this.f1853a, i2);
                        this.f1854b += length2;
                    }
                } else {
                    int length3 = str.length();
                    int i5 = (i4 - i2) - length3;
                    if (i5 > 0) {
                        char[] cArr = this.f1853a;
                        System.arraycopy(cArr, i4, cArr, i2 + length3, this.f1854b - i4);
                    } else if (i5 < 0) {
                        l(-i5, i4);
                    }
                    str.getChars(0, length3, this.f1853a, i2);
                    this.f1854b -= i5;
                }
                i2 += length;
            } else {
                i2++;
            }
        }
        throw new StringIndexOutOfBoundsException(i2);
    }

    public final void n(int i2) {
        if (i2 < 0) {
            throw new StringIndexOutOfBoundsException(i2);
        }
        char[] cArr = this.f1853a;
        if (i2 > cArr.length) {
            k(i2);
        } else {
            int i3 = this.f1854b;
            if (i3 < i2) {
                Arrays.fill(cArr, i3, i2, (char) 0);
            }
        }
        this.f1854b = i2;
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i2, int i3) {
        if (i2 < 0 || i2 > i3 || i3 > this.f1854b) {
            throw new StringIndexOutOfBoundsException();
        }
        return i2 == i3 ? "" : new String(this.f1853a, i2, i3 - i2);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        int i2 = this.f1854b;
        return i2 == 0 ? "" : new String(this.f1853a, 0, i2);
    }

    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i2, int i3) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (i2 < 0 || i3 < 0 || i2 > i3 || i3 > charSequence.length()) {
            throw new IndexOutOfBoundsException();
        }
        h(charSequence.subSequence(i2, i3).toString());
        return this;
    }

    public o0(int i2) {
        if (i2 >= 0) {
            this.f1853a = new char[i2];
            return;
        }
        throw new NegativeArraySizeException();
    }

    public o0(String str) {
        int length = str.length();
        this.f1854b = length;
        char[] cArr = new char[length + 16];
        this.f1853a = cArr;
        str.getChars(0, length, cArr, 0);
    }

    @Override // java.lang.Appendable
    public final Appendable append(char c2) {
        g(c2);
        return this;
    }
}
