package com.badlogic.gdx.utils;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.t;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class s implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final byte[] f1922e = {0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 2, 0, 7, 2, 0, 8, 2, 1, 3, 2, 1, 5};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final short[] f1923f = {0, 0, 11, 13, 14, 16, 25, 31, 37, 39, 50, 57, 64, 73, 74, 83, 85, 87, 96, 98, 100, 101, 103, 105, 116, 123, 130, 141, 142, 153, 155, 157, 168, 170, 172, 174, 179, 184, 184};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final char[] f1924g = {'\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '\"', '*', '/', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '\r', ' ', '/', ':', '\t', '\n', '\r', ' ', '/', ':', '\t', '\n', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\t', '\n', '\r', ' ', ',', '/', '}', '\t', '\n', '\r', ' ', ',', '/', '}', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '\"', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '*', '/', '*', '/', '\"', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\t', '\n', '\r', ' ', ',', '/', ']', '\t', '\n', '\r', ' ', ',', '/', ']', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\"', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '*', '/', '*', '/', '\r', ' ', '/', '\t', '\n', '\r', ' ', '/', '\t', '\n', 0};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final byte[] f1925h = {0, 9, 2, 1, 2, 7, 4, 4, 2, 9, 7, 7, 7, 1, 7, 2, 2, 7, 2, 2, 1, 2, 2, 9, 7, 7, 9, 1, 9, 2, 2, 9, 2, 2, 2, 3, 3, 0, 0};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final byte[] f1926i = {0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final short[] f1927j = {0, 0, 11, 14, 16, 19, 28, 34, 40, 43, 54, 62, 70, 79, 81, 90, 93, 96, 105, 108, 111, 113, 116, 119, 130, 138, 146, 157, 159, 170, 173, 176, 187, 190, 193, 196, 201, 206, 207};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final byte[] f1928k = {1, 1, 2, 3, 4, 3, 5, 3, 6, 1, 0, 7, 7, 3, 8, 3, 9, 9, 3, 11, 11, 12, 13, 14, 3, 15, 11, 10, 16, 16, 17, 18, 16, 3, 19, 19, 20, 21, 19, 3, 22, 22, 3, 21, 21, 24, 3, 25, 3, 26, 3, 27, 21, 23, 28, 29, 29, 28, 30, 31, 32, 3, 33, 34, 34, 33, 13, 35, 15, 3, 34, 34, 12, 36, 37, 3, 15, 34, 10, 16, 3, 36, 36, 12, 3, 38, 3, 3, 36, 10, 39, 39, 3, 40, 40, 3, 13, 13, 12, 3, 41, 3, 15, 13, 10, 42, 42, 3, 43, 43, 3, 28, 3, 44, 44, 3, 45, 45, 3, 47, 47, 48, 49, 50, 3, 51, 52, 53, 47, 46, 54, 55, 55, 54, 56, 57, 58, 3, 59, 60, 60, 59, 49, 61, 52, 3, 60, 60, 48, 62, 63, 3, 51, 52, 53, 60, 46, 54, 3, 62, 62, 48, 3, 64, 3, 51, 3, 53, 62, 46, 65, 65, 3, 66, 66, 3, 49, 49, 48, 3, 67, 3, 51, 52, 53, 49, 46, 68, 68, 3, 69, 69, 3, 70, 70, 3, 8, 8, 71, 8, 3, 72, 72, 73, 72, 3, 3, 3, 0};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final byte[] f1929l = {35, 1, 3, 0, 4, 36, 36, 36, 36, 1, 6, 5, 13, 17, 22, 37, 7, 8, 9, 7, 8, 9, 7, 10, 20, 21, 11, 11, 11, 12, 17, 19, 37, 11, 12, 19, 14, 16, 15, 14, 12, 18, 17, 11, 9, 5, 24, 23, 27, 31, 34, 25, 38, 25, 25, 26, 31, 33, 38, 25, 26, 33, 28, 30, 29, 28, 26, 32, 31, 25, 23, 2, 36, 2};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final byte[] f1930m = {13, 0, 15, 0, 0, 7, 3, 11, 1, 11, 17, 0, 20, 0, 0, 5, 1, 1, 1, 0, 0, 0, 11, 13, 15, 0, 7, 3, 1, 1, 1, 1, 23, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 11, 13, 0, 15, 0, 0, 7, 9, 3, 1, 1, 1, 1, 26, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 1, 0, 0};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final byte[] f1931n = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a<t> f1932a = new a<>(true, 8);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a<t> f1933b = new a<>(true, 8);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t f1934c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private t f1935d;

    private void b(String str, t tVar) {
        tVar.f1957e = str;
        t tVar2 = this.f1935d;
        if (tVar2 == null) {
            this.f1935d = tVar;
            this.f1934c = tVar;
            return;
        }
        if (!tVar2.q() && !this.f1935d.t()) {
            this.f1934c = this.f1935d;
            return;
        }
        t tVar3 = this.f1935d;
        tVar.f1959g = tVar3;
        int i2 = tVar3.f1962j;
        a<t> aVar = this.f1933b;
        if (i2 == 0) {
            tVar3.f1958f = tVar;
        } else {
            t tVarL = aVar.l();
            tVarL.f1960h = tVar;
            tVar.f1961i = tVarL;
        }
        aVar.a(tVar);
        this.f1935d.f1962j++;
    }

    private static String h(String str) {
        int length = str.length();
        o0 o0Var = new o0(length + 16);
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            char cCharAt = str.charAt(i2);
            if (cCharAt != '\\') {
                o0Var.g(cCharAt);
                i2 = i3;
            } else {
                if (i3 == length) {
                    break;
                }
                int i4 = i2 + 2;
                char cCharAt2 = str.charAt(i3);
                if (cCharAt2 == 'u') {
                    i2 += 6;
                    o0Var.f(Character.toChars(Integer.parseInt(str.substring(i4, i2), 16)));
                } else {
                    if (cCharAt2 != '\"' && cCharAt2 != '/' && cCharAt2 != '\\') {
                        if (cCharAt2 == 'b') {
                            cCharAt2 = '\b';
                        } else if (cCharAt2 == 'f') {
                            cCharAt2 = '\f';
                        } else if (cCharAt2 == 'n') {
                            cCharAt2 = '\n';
                        } else if (cCharAt2 == 'r') {
                            cCharAt2 = '\r';
                        } else {
                            if (cCharAt2 != 't') {
                                throw new h0("Illegal escaped character: \\" + cCharAt2);
                            }
                            cCharAt2 = '\t';
                        }
                    }
                    o0Var.g(cCharAt2);
                    i2 = i4;
                }
            }
        }
        return o0Var.toString();
    }

    @Override // com.badlogic.gdx.utils.d
    public final t a(com.badlogic.gdx.files.a aVar) {
        try {
            return d(aVar.reader("UTF-8"));
        } catch (Exception e2) {
            throw new h0(a.a.i(aVar, "Error parsing file: "), e2);
        }
    }

    public final t c(InputStream inputStream) {
        try {
            try {
                return d(new InputStreamReader(inputStream, "UTF-8"));
            } catch (IOException e2) {
                throw new h0(e2);
            }
        } finally {
            n0.a(inputStream);
        }
    }

    public final t d(Reader reader) {
        char[] cArr;
        int i2;
        try {
            try {
                cArr = new char[GL20.GL_STENCIL_BUFFER_BIT];
                i2 = 0;
            } catch (IOException e2) {
                throw new h0(e2);
            }
        } finally {
            n0.a(reader);
        }
        while (true) {
            int i3 = reader.read(cArr, i2, cArr.length - i2);
            if (i3 == -1) {
                return f(cArr, 0, i2);
            }
            if (i3 == 0) {
                char[] cArr2 = new char[cArr.length * 2];
                System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                cArr = cArr2;
            } else {
                i2 += i3;
            }
            n0.a(reader);
        }
    }

    public final t e(String str) {
        char[] charArray = str.toCharArray();
        return f(charArray, 0, charArray.length);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0218, code lost:
    
        r4 = r10;
        r11 = r13;
        r12 = r21;
        r13 = r25;
        r9 = 2;
        r10 = r0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:68:0x010d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01b7 A[LOOP:7: B:113:0x017d->B:139:0x01b7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:388:0x03ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:392:0x03a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0396 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0174 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:425:? A[LOOP:5: B:360:0x0145->B:425:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:429:0x01b5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final t f(char[] cArr, int i2, int i3) {
        byte[] bArr;
        String str;
        int i4;
        RuntimeException runtimeException;
        String str2;
        int i5;
        a aVar;
        int i6;
        int[] iArr;
        short s;
        short s2;
        byte b2;
        char[] cArr2;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        byte b3;
        int i12;
        int i13;
        int i14;
        char c2;
        int i15;
        int[] iArr2;
        byte b4;
        int i16;
        int i17;
        char c3;
        int i18;
        int i19;
        int i20;
        s sVar = this;
        a aVar2 = new a(true, 8);
        int[] iArr3 = new int[4];
        int i21 = 1;
        char c4 = 0;
        int i22 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int i23 = 0;
        int i24 = i2;
        while (true) {
            bArr = f1922e;
            a<t> aVar3 = sVar.f1932a;
            str = "null";
            i4 = i22;
            boolean z6 = z3;
            try {
                if (c4 == 0) {
                    if (i24 == i3) {
                        c4 = 4;
                        i22 = i4;
                    } else if (i21 == 0) {
                        i22 = i4;
                        c4 = 5;
                    }
                    z3 = z6;
                } else if (c4 != 1) {
                    if (c4 == 2) {
                        iArr = iArr3;
                        z3 = z6;
                        if (i21 == 0) {
                            i22 = i4;
                            c4 = 5;
                            iArr3 = iArr;
                        } else {
                            i24++;
                            if (i24 != i3) {
                                i22 = i4;
                                iArr3 = iArr;
                                c4 = 1;
                            }
                        }
                    } else if (c4 == 4) {
                        z3 = z6;
                    }
                }
                s = f1923f[i21];
                s2 = f1927j[i21];
                b2 = f1925h[i21];
                cArr2 = f1924g;
            } catch (RuntimeException e2) {
                e = e2;
            }
            if (b2 > 0) {
                int i25 = s + b2;
                int i26 = i25 - 1;
                z2 = z4;
                int i27 = s;
                while (i26 >= i27) {
                    int i28 = i27 + ((i26 - i27) >> 1);
                    int i29 = i26;
                    char c5 = cArr[i24];
                    int i30 = i27;
                    char c6 = cArr2[i28];
                    if (c5 < c6) {
                        i26 = i28 - 1;
                        i27 = i30 == true ? 1 : 0;
                    } else if (c5 > c6) {
                        i27 = i28 + 1;
                        i26 = i29;
                    } else {
                        i9 = (i28 - s) + s2;
                        i20 = i9;
                        i10 = i24;
                        i11 = i20;
                        byte b5 = f1928k[i11];
                        byte b6 = f1929l[b5];
                        b3 = f1930m[b5];
                        if (b3 == 0) {
                            try {
                                int i31 = b3 + 1;
                                i12 = bArr[b3];
                                i13 = i31;
                                i14 = i4;
                                i5 = i10;
                            } catch (RuntimeException e3) {
                                e = e3;
                                i24 = i10;
                            }
                            while (true) {
                                int i32 = i12 - 1;
                                if (i12 > 0) {
                                    int i33 = i13 + 1;
                                    try {
                                        switch (bArr[i13]) {
                                            case 0:
                                                iArr2 = iArr3;
                                                b4 = b6;
                                                z2 = true;
                                                i12 = i32;
                                                i13 = i33;
                                                iArr3 = iArr2;
                                                b6 = b4;
                                                break;
                                            case 1:
                                                String str3 = new String(cArr, i14, i5 - i14);
                                                if (z6) {
                                                    str3 = h(str3);
                                                }
                                                if (z2) {
                                                    aVar2.a(str3);
                                                    iArr2 = iArr3;
                                                    b4 = b6;
                                                    z2 = false;
                                                } else {
                                                    String str4 = aVar2.f1750b > 0 ? (String) aVar2.l() : null;
                                                    if (z5) {
                                                        if (str3.equals("true")) {
                                                            sVar.b(str4, new t(true));
                                                        } else if (str3.equals("false")) {
                                                            sVar.b(str4, new t(false));
                                                        } else if (!str3.equals("null")) {
                                                            boolean z7 = false;
                                                            boolean z8 = true;
                                                            while (true) {
                                                                if (i14 < i5) {
                                                                    iArr2 = iArr3;
                                                                    char c7 = cArr[i14];
                                                                    b4 = b6;
                                                                    if (c7 != '+') {
                                                                        if (c7 == 'E' || c7 == 'e') {
                                                                            z7 = true;
                                                                            z8 = false;
                                                                        } else if (c7 == '-') {
                                                                            continue;
                                                                        } else if (c7 != '.') {
                                                                            switch (c7) {
                                                                                case '0':
                                                                                case '1':
                                                                                case '2':
                                                                                case '3':
                                                                                case '4':
                                                                                case '5':
                                                                                case '6':
                                                                                case '7':
                                                                                case '8':
                                                                                case '9':
                                                                                    break;
                                                                                default:
                                                                                    z7 = false;
                                                                                    z8 = false;
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }
                                                                    i14++;
                                                                    iArr3 = iArr2;
                                                                    b6 = b4;
                                                                } else {
                                                                    iArr2 = iArr3;
                                                                    b4 = b6;
                                                                }
                                                            }
                                                            if (z7) {
                                                                try {
                                                                    double d2 = Double.parseDouble(str3);
                                                                    t tVar = new t();
                                                                    tVar.z(str3, d2);
                                                                    sVar.b(str4, tVar);
                                                                } catch (NumberFormatException unused) {
                                                                    sVar.b(str4, new t(str3));
                                                                }
                                                            } else if (z8) {
                                                                long j2 = Long.parseLong(str3);
                                                                t tVar2 = new t();
                                                                tVar2.A(str3, j2);
                                                                sVar.b(str4, tVar2);
                                                            }
                                                            i12 = i32;
                                                            i13 = i33;
                                                            iArr3 = iArr2;
                                                            b6 = b4;
                                                        } else {
                                                            sVar.b(str4, new t((String) null));
                                                        }
                                                        iArr2 = iArr3;
                                                        b4 = b6;
                                                    } else {
                                                        iArr2 = iArr3;
                                                        b4 = b6;
                                                    }
                                                    sVar.b(str4, new t(str3));
                                                }
                                                i14 = i5;
                                                z5 = false;
                                                i12 = i32;
                                                i13 = i33;
                                                iArr3 = iArr2;
                                                b6 = b4;
                                                break;
                                            case 2:
                                                String str5 = aVar2.f1750b > 0 ? (String) aVar2.l() : null;
                                                t tVar3 = new t(t.c.f1968a);
                                                if (sVar.f1935d != null) {
                                                    sVar.b(str5, tVar3);
                                                }
                                                aVar3.a(tVar3);
                                                sVar.f1935d = tVar3;
                                                if (i23 == iArr3.length) {
                                                    c2 = 2;
                                                    int[] iArr4 = new int[iArr3.length * 2];
                                                    System.arraycopy(iArr3, 0, iArr4, 0, iArr3.length);
                                                    iArr3 = iArr4;
                                                } else {
                                                    c2 = 2;
                                                }
                                                iArr3[i23] = b6;
                                                i23++;
                                                c4 = c2;
                                                i24 = i5;
                                                i22 = i14;
                                                i21 = 5;
                                                z3 = z6;
                                                z4 = z2;
                                                break;
                                            case 3:
                                                g();
                                                i23--;
                                                i15 = iArr3[i23];
                                                break;
                                            case 4:
                                                String str6 = aVar2.f1750b > 0 ? (String) aVar2.l() : null;
                                                t tVar4 = new t(t.c.f1969b);
                                                if (sVar.f1935d != null) {
                                                    sVar.b(str6, tVar4);
                                                }
                                                aVar3.a(tVar4);
                                                sVar.f1935d = tVar4;
                                                if (i23 == iArr3.length) {
                                                    int[] iArr5 = new int[iArr3.length * 2];
                                                    System.arraycopy(iArr3, 0, iArr5, 0, iArr3.length);
                                                    iArr3 = iArr5;
                                                }
                                                iArr3[i23] = b6;
                                                i23++;
                                                i22 = i14;
                                                z3 = z6;
                                                z4 = z2;
                                                c4 = 2;
                                                int i34 = i5;
                                                i21 = 23;
                                                i24 = i34;
                                                break;
                                            case 5:
                                                g();
                                                i23--;
                                                i15 = iArr3[i23];
                                                break;
                                            case 6:
                                                i24 = i5 + 1;
                                                if (cArr[i5] == '/') {
                                                    while (i24 != i3 && cArr[i24] != '\n') {
                                                        i24++;
                                                    }
                                                    i5 = i24 - 1;
                                                } else {
                                                    while (true) {
                                                        i5 = i24 + 1;
                                                        try {
                                                            if (i5 < i3) {
                                                                try {
                                                                    i16 = i24;
                                                                    if (cArr[i24] == '*') {
                                                                    }
                                                                    i24 = i5;
                                                                } catch (RuntimeException e4) {
                                                                    e = e4;
                                                                    runtimeException = e;
                                                                    t tVar5 = sVar.f1934c;
                                                                    sVar.f1934c = null;
                                                                    sVar.f1935d = null;
                                                                    sVar.f1933b.clear();
                                                                    if (i24 < i3) {
                                                                    }
                                                                }
                                                            } else {
                                                                i16 = i24;
                                                            }
                                                            if (cArr[i5] != '/') {
                                                                i24 = i5;
                                                            }
                                                        } catch (RuntimeException e5) {
                                                            e = e5;
                                                            i24 = i16;
                                                            runtimeException = e;
                                                            t tVar52 = sVar.f1934c;
                                                            sVar.f1934c = null;
                                                            sVar.f1935d = null;
                                                            sVar.f1933b.clear();
                                                            if (i24 < i3) {
                                                            }
                                                        }
                                                    }
                                                }
                                                iArr2 = iArr3;
                                                b4 = b6;
                                                i12 = i32;
                                                i13 = i33;
                                                iArr3 = iArr2;
                                                b6 = b4;
                                                break;
                                            case 7:
                                                char c8 = '\r';
                                                if (z2) {
                                                    i17 = i5;
                                                    boolean z9 = false;
                                                    while (true) {
                                                        try {
                                                            char c9 = cArr[i17];
                                                            z6 = z9;
                                                            if (c9 != '\n' && c9 != '\r') {
                                                                if (c9 == '/') {
                                                                    int i35 = i17 + 1;
                                                                    if (i35 != i3) {
                                                                        char c10 = cArr[i35];
                                                                        if (c10 == '/' || c10 == '*') {
                                                                        }
                                                                    }
                                                                    z9 = z6;
                                                                    i17++;
                                                                    if (i17 == i3) {
                                                                    }
                                                                } else if (c9 != ':') {
                                                                    if (c9 != '\\') {
                                                                        z9 = z6;
                                                                        i17++;
                                                                        if (i17 == i3) {
                                                                            z6 = z9;
                                                                        }
                                                                    } else {
                                                                        z9 = true;
                                                                        i17++;
                                                                        if (i17 == i3) {
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } catch (RuntimeException e6) {
                                                            e = e6;
                                                            i24 = i17;
                                                            runtimeException = e;
                                                            t tVar522 = sVar.f1934c;
                                                            sVar.f1934c = null;
                                                            sVar.f1935d = null;
                                                            sVar.f1933b.clear();
                                                            if (i24 < i3) {
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    int i36 = i5;
                                                    boolean z10 = false;
                                                    while (true) {
                                                        char c11 = cArr[i36];
                                                        z6 = z10;
                                                        if (c11 != '\n' && c11 != c8 && c11 != ',') {
                                                            if (c11 == '/') {
                                                                int i37 = i36 + 1;
                                                                if (i37 == i3 || ((c3 = cArr[i37]) != '/' && c3 != '*')) {
                                                                    i36++;
                                                                    if (i36 == i3) {
                                                                    }
                                                                }
                                                            } else if (c11 != '}') {
                                                                if (c11 == '\\') {
                                                                    z6 = true;
                                                                } else if (c11 != ']') {
                                                                }
                                                                i36++;
                                                                if (i36 == i3) {
                                                                    z10 = z6;
                                                                    c8 = '\r';
                                                                }
                                                            }
                                                            i12 = i32;
                                                            i13 = i33;
                                                            iArr3 = iArr2;
                                                            b6 = b4;
                                                            break;
                                                        }
                                                    }
                                                    i17 = i36;
                                                }
                                                int i38 = i17 - 1;
                                                while (Character.isSpace(cArr[i38])) {
                                                    i38--;
                                                }
                                                iArr2 = iArr3;
                                                b4 = b6;
                                                i14 = i5;
                                                z5 = true;
                                                i5 = i38;
                                                i12 = i32;
                                                i13 = i33;
                                                iArr3 = iArr2;
                                                b6 = b4;
                                                break;
                                            case '\b':
                                                int i39 = i5 + 1;
                                                i24 = i39;
                                                boolean z11 = false;
                                                while (true) {
                                                    try {
                                                        char c12 = cArr[i24];
                                                        i18 = i39;
                                                        if (c12 != '\"') {
                                                            if (c12 != '\\') {
                                                                i19 = 1;
                                                            } else {
                                                                i24++;
                                                                i19 = 1;
                                                                z11 = true;
                                                            }
                                                            i24 += i19;
                                                            if (i24 != i3) {
                                                                i39 = i18;
                                                            }
                                                        }
                                                    } catch (RuntimeException e7) {
                                                        e = e7;
                                                        runtimeException = e;
                                                        t tVar5222 = sVar.f1934c;
                                                        sVar.f1934c = null;
                                                        sVar.f1935d = null;
                                                        sVar.f1933b.clear();
                                                        if (i24 < i3) {
                                                        }
                                                    }
                                                }
                                                i5 = i24 - 1;
                                                iArr2 = iArr3;
                                                b4 = b6;
                                                i14 = i18;
                                                z6 = z11;
                                                i12 = i32;
                                                i13 = i33;
                                                iArr3 = iArr2;
                                                b6 = b4;
                                                break;
                                            default:
                                                iArr2 = iArr3;
                                                b4 = b6;
                                                i12 = i32;
                                                i13 = i33;
                                                iArr3 = iArr2;
                                                b6 = b4;
                                                break;
                                        }
                                    } catch (RuntimeException e8) {
                                        e = e8;
                                        i24 = i5;
                                    }
                                    t tVar52222 = sVar.f1934c;
                                    sVar.f1934c = null;
                                    sVar.f1935d = null;
                                    sVar.f1933b.clear();
                                    if (i24 < i3) {
                                        if (aVar3.f1750b == 0) {
                                            if (runtimeException == null) {
                                                return tVar52222;
                                            }
                                            throw new h0("Error parsing JSON: ".concat(new String(cArr)), runtimeException);
                                        }
                                        t tVarK = aVar3.k();
                                        aVar3.clear();
                                        if (tVarK == null || !tVarK.t()) {
                                            throw new h0("Error parsing JSON, unmatched bracket.");
                                        }
                                        throw new h0("Error parsing JSON, unmatched brace.");
                                    }
                                    int i40 = 1;
                                    for (int i41 = 0; i41 < i24; i41++) {
                                        if (cArr[i41] == '\n') {
                                            i40++;
                                        }
                                    }
                                    int iMax = Math.max(0, i24 - 32);
                                    StringBuilder sbR = a.a.r(i40, "Error parsing JSON on line ", " near: ");
                                    sbR.append(new String(cArr, iMax, i24 - iMax));
                                    sbR.append("*ERROR*");
                                    sbR.append(new String(cArr, i24, Math.min(64, i3 - i24)));
                                    throw new h0(sbR.toString(), runtimeException);
                                }
                                iArr = iArr3;
                                i24 = i5;
                                i4 = i14;
                                z3 = z6;
                                i21 = b6;
                                z4 = z2;
                            }
                        } else {
                            iArr = iArr3;
                            z3 = z6;
                            i21 = b6;
                            z4 = z2;
                            i24 = i10;
                        }
                        if (i21 == 0) {
                        }
                    }
                }
                i8 = i25;
                i7 = s2 + b2;
            } else {
                z2 = z4;
                i8 = s;
                i7 = s2;
            }
            byte b7 = f1926i[i21];
            i20 = i7;
            if (b7 > 0) {
                int i42 = ((b7 << 1) + i8) - 2;
                int i43 = i8;
                while (i42 >= i43) {
                    int i44 = i43 + (((i42 - i43) >> 1) & (-2));
                    byte b8 = b7;
                    char c13 = cArr[i24];
                    i10 = i24;
                    if (c13 < cArr2[i44]) {
                        i42 = i44 - 2;
                    } else if (c13 > cArr2[i44 + 1]) {
                        i43 = i44 + 2;
                    } else {
                        i11 = (i7 == true ? 1 : 0) + ((i44 - i8) >> 1);
                    }
                    b7 = b8;
                    i24 = i10;
                    i43 = i43;
                }
                i9 = (i7 == true ? 1 : 0) + b7;
                i20 = i9;
                i10 = i24;
                i11 = i20;
            } else {
                i10 = i24;
                i11 = i20;
            }
            byte b52 = f1928k[i11];
            byte b62 = f1929l[b52];
            b3 = f1930m[b52];
            if (b3 == 0) {
            }
            if (i21 == 0) {
            }
        }
        if (i24 == i3) {
            try {
                byte b9 = f1931n[i21];
                int i45 = b9 + 1;
                int i46 = bArr[b9];
                int i47 = i4;
                while (true) {
                    int i48 = i46 - 1;
                    if (i46 > 0) {
                        int i49 = i45 + 1;
                        if (bArr[i45] != 1) {
                            str2 = str;
                            aVar = aVar2;
                            int i50 = i47;
                            i5 = i24;
                            i6 = i50;
                        } else {
                            String str7 = new String(cArr, i47, i24 - i47);
                            if (z3) {
                                str7 = h(str7);
                            }
                            if (z4) {
                                aVar2.a(str7);
                                str2 = str;
                                i5 = i24;
                                aVar = aVar2;
                                z4 = false;
                            } else {
                                String str8 = aVar2.f1750b > 0 ? (String) aVar2.l() : null;
                                if (!z5) {
                                    str2 = str;
                                } else if (str7.equals("true")) {
                                    try {
                                        sVar.b(str8, new t(true));
                                        str2 = str;
                                        i5 = i24;
                                        aVar = aVar2;
                                    } catch (RuntimeException e9) {
                                        e = e9;
                                        runtimeException = e;
                                        t tVar522222 = sVar.f1934c;
                                        sVar.f1934c = null;
                                        sVar.f1935d = null;
                                        sVar.f1933b.clear();
                                        if (i24 < i3) {
                                        }
                                    }
                                } else {
                                    if (str7.equals("false")) {
                                        sVar.b(str8, new t(false));
                                    } else if (str7.equals(str)) {
                                        sVar.b(str8, new t((String) null));
                                    } else {
                                        boolean z12 = true;
                                        boolean z13 = false;
                                        while (true) {
                                            if (i47 < i24) {
                                                char c14 = cArr[i47];
                                                str2 = str;
                                                if (c14 != '+') {
                                                    if (c14 != 'E' && c14 != 'e') {
                                                        if (c14 != '-') {
                                                            if (c14 != '.') {
                                                                switch (c14) {
                                                                    case '0':
                                                                    case '1':
                                                                    case '2':
                                                                    case '3':
                                                                    case '4':
                                                                    case '5':
                                                                    case '6':
                                                                    case '7':
                                                                    case '8':
                                                                    case '9':
                                                                        break;
                                                                    default:
                                                                        z13 = false;
                                                                        z12 = false;
                                                                        break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    z13 = true;
                                                    z12 = false;
                                                }
                                                i47++;
                                                str = str2;
                                            } else {
                                                str2 = str;
                                            }
                                        }
                                        if (z13) {
                                            try {
                                                double d3 = Double.parseDouble(str7);
                                                t tVar6 = new t();
                                                tVar6.z(str7, d3);
                                                sVar = this;
                                                try {
                                                    sVar.b(str8, tVar6);
                                                    i5 = i24;
                                                    aVar = aVar2;
                                                } catch (NumberFormatException unused2) {
                                                    i5 = i24;
                                                    aVar = aVar2;
                                                    sVar.b(str8, new t(str7));
                                                }
                                            } catch (NumberFormatException unused3) {
                                                sVar = this;
                                            } catch (RuntimeException e10) {
                                                e = e10;
                                                sVar = this;
                                                runtimeException = e;
                                                t tVar5222222 = sVar.f1934c;
                                                sVar.f1934c = null;
                                                sVar.f1935d = null;
                                                sVar.f1933b.clear();
                                                if (i24 < i3) {
                                                }
                                            }
                                        } else {
                                            if (z12) {
                                                i5 = i24;
                                                aVar = aVar2;
                                                try {
                                                    long j3 = Long.parseLong(str7);
                                                    t tVar7 = new t();
                                                    tVar7.A(str7, j3);
                                                    sVar.b(str8, tVar7);
                                                } catch (NumberFormatException unused4) {
                                                    sVar.b(str8, new t(str7));
                                                }
                                            }
                                            sVar.b(str8, new t(str7));
                                        }
                                    }
                                    str2 = str;
                                    i5 = i24;
                                    aVar = aVar2;
                                }
                                i5 = i24;
                                aVar = aVar2;
                                sVar.b(str8, new t(str7));
                            }
                            i6 = i5;
                            z5 = false;
                        }
                        i45 = i49;
                        aVar2 = aVar;
                        i46 = i48;
                        str = str2;
                        int i51 = i5;
                        i47 = i6;
                        i24 = i51;
                    }
                }
            } catch (RuntimeException e11) {
                e = e11;
            }
        }
        i24 = i24;
        runtimeException = null;
        t tVar52222222 = sVar.f1934c;
        sVar.f1934c = null;
        sVar.f1935d = null;
        sVar.f1933b.clear();
        if (i24 < i3) {
        }
    }

    protected final void g() {
        a<t> aVar = this.f1932a;
        this.f1934c = aVar.l();
        if (this.f1935d.f1962j > 0) {
            this.f1933b.l();
        }
        this.f1935d = aVar.f1750b > 0 ? aVar.k() : null;
    }
}
