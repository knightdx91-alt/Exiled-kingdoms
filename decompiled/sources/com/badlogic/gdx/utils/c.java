package com.badlogic.gdx.utils;

import com.badlogic.gdx.graphics.VertexAttributes;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: Base64Coder.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f1779a = new a('+', '/');

    /* JADX INFO: compiled from: Base64Coder.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final char[] f1780a = new char[64];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final byte[] f1781b = new byte[VertexAttributes.Usage.Tangent];

        public a(char c2, char c3) {
            char c4 = 'A';
            int i2 = 0;
            while (c4 <= 'Z') {
                this.f1780a[i2] = c4;
                c4 = (char) (c4 + 1);
                i2++;
            }
            char c5 = 'a';
            while (c5 <= 'z') {
                this.f1780a[i2] = c5;
                c5 = (char) (c5 + 1);
                i2++;
            }
            char c6 = '0';
            while (c6 <= '9') {
                this.f1780a[i2] = c6;
                c6 = (char) (c6 + 1);
                i2++;
            }
            char[] cArr = this.f1780a;
            cArr[i2] = c2;
            cArr[i2 + 1] = c3;
            int i3 = 0;
            while (true) {
                byte[] bArr = this.f1781b;
                if (i3 >= bArr.length) {
                    break;
                }
                bArr[i3] = -1;
                i3++;
            }
            for (int i4 = 0; i4 < 64; i4++) {
                this.f1781b[this.f1780a[i4]] = (byte) i4;
            }
        }
    }

    static {
        new a('-', '_');
    }

    public static byte[] a(String str) {
        char[] charArray = str.toCharArray();
        return b(charArray, charArray.length, f1779a.f1781b);
    }

    public static byte[] b(char[] cArr, int i2, byte[] bArr) {
        char c2;
        if (i2 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (i2 > 0 && cArr[i2 - 1] == '=') {
            i2--;
        }
        int i3 = (i2 * 3) / 4;
        byte[] bArr2 = new byte[i3];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            char c3 = cArr[i4];
            int i6 = i4 + 2;
            char c4 = cArr[i4 + 1];
            char c5 = 'A';
            if (i6 < i2) {
                i4 += 3;
                c2 = cArr[i6];
            } else {
                i4 = i6;
                c2 = 'A';
            }
            if (i4 < i2) {
                c5 = cArr[i4];
                i4++;
            }
            if (c3 > 127 || c4 > 127 || c2 > 127 || c5 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            byte b2 = bArr[c3];
            byte b3 = bArr[c4];
            byte b4 = bArr[c2];
            byte b5 = bArr[c5];
            if (b2 < 0 || b3 < 0 || b4 < 0 || b5 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int i7 = (b2 << 2) | (b3 >>> 4);
            int i8 = ((b3 & 15) << 4) | (b4 >>> 2);
            int i9 = ((b4 & 3) << 6) | b5;
            int i10 = i5 + 1;
            bArr2[i5] = (byte) i7;
            if (i10 < i3) {
                bArr2[i10] = (byte) i8;
                i10 = i5 + 2;
            }
            if (i10 < i3) {
                i5 = i10 + 1;
                bArr2[i10] = (byte) i9;
            } else {
                i5 = i10;
            }
        }
        return bArr2;
    }

    public static String c(String str) {
        char[] charArray = str.toCharArray();
        return new String(b(charArray, charArray.length, f1779a.f1781b));
    }

    public static char[] d(byte[] bArr) {
        return e(bArr, f1779a.f1780a);
    }

    public static char[] e(byte[] bArr, char[] cArr) {
        int i2;
        int i3;
        int i4;
        int i5;
        int length = bArr.length;
        int i6 = ((length * 4) + 2) / 3;
        char[] cArr2 = new char[((length + 2) / 3) * 4];
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int i9 = i7 + 1;
            byte b2 = bArr[i7];
            int i10 = b2 & 255;
            if (i9 < length) {
                i2 = i7 + 2;
                i3 = bArr[i9] & 255;
            } else {
                i2 = i9;
                i3 = 0;
            }
            if (i2 < length) {
                i4 = i2 + 1;
                i5 = bArr[i2] & 255;
            } else {
                i4 = i2;
                i5 = 0;
            }
            int i11 = ((b2 & 3) << 4) | (i3 >>> 4);
            int i12 = ((i3 & 15) << 2) | (i5 >>> 6);
            int i13 = i5 & 63;
            cArr2[i8] = cArr[i10 >>> 2];
            int i14 = i8 + 2;
            cArr2[i8 + 1] = cArr[i11];
            char c2 = '=';
            cArr2[i14] = i14 < i6 ? cArr[i12] : '=';
            int i15 = i8 + 3;
            if (i15 < i6) {
                c2 = cArr[i13];
            }
            cArr2[i15] = c2;
            i8 += 4;
            i7 = i4;
        }
        return cArr2;
    }

    public static String f(String str) {
        try {
            return new String(e(str.getBytes("UTF-8"), f1779a.f1780a));
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }
}
