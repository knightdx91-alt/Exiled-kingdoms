package com.badlogic.gdx.utils;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.y;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: compiled from: XmlReader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class s0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final byte[] f1936e = {0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 2, 0, 6, 2, 1, 4, 2, 2, 4};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final byte[] f1937f = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95, 99, 103, 104, 108, 109, 110, 111, 112, 115};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final char[] f1938g = {' ', '<', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '=', '\t', '\r', ' ', '\"', '\'', '\t', '\r', '\"', '\"', ' ', '/', '>', '\t', '\r', ' ', '>', '\t', '\r', ' ', '>', '\t', '\r', '\'', '\'', ' ', '<', '\t', '\r', '<', ' ', '/', '>', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '=', '\t', '\r', ' ', '\"', '\'', '\t', '\r', '\"', '\"', ' ', '/', '>', '\t', '\r', ' ', '>', '\t', '\r', ' ', '>', '\t', '\r', '<', ' ', '/', '\t', '\r', '>', '>', '\'', '\'', ' ', '\t', '\r', 0};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final byte[] f1939h = {0, 2, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 1, 2, 1, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 2, 1, 1, 1, 1, 1, 0};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final byte[] f1940i = {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final short[] f1941j = {0, 0, 4, 9, 14, 20, 26, 30, 35, 37, 39, 44, 48, 52, 54, 56, 60, 62, 67, 72, 78, 84, 88, 93, 95, 97, 102, 106, 110, 112, 116, 118, 120, 122, 124, 127};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final byte[] f1942k = {0, 2, 0, 1, 2, 1, 1, 2, 3, 5, 6, 7, 5, 4, 9, 10, 1, 11, 9, 8, 13, 1, 14, 1, 13, 12, 15, 16, 15, 1, 16, 17, 18, 16, 1, 20, 19, 22, 21, 9, 10, 11, 9, 1, 23, 24, 23, 1, 25, 11, 25, 1, 20, 26, 22, 27, 29, 30, 29, 28, 32, 31, 30, 34, 1, 30, 33, 36, 37, 38, 36, 35, 40, 41, 1, 42, 40, 39, 44, 1, 45, 1, 44, 43, 46, 47, 46, 1, 47, 48, 49, 47, 1, 51, 50, 53, 52, 40, 41, 42, 40, 1, 54, 55, 54, 1, 56, 42, 56, 1, 57, 1, 57, 34, 57, 1, 1, 58, 59, 58, 51, 60, 53, 61, 62, 62, 1, 1, 0};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final byte[] f1943l = {1, 0, 2, 3, 3, 4, 11, 34, 5, 4, 11, 34, 5, 6, 7, 6, 7, 8, 13, 9, 10, 9, 10, 12, 34, 12, 14, 14, 16, 15, 17, 16, 17, 18, 30, 18, 19, 26, 28, 20, 19, 26, 28, 20, 21, 22, 21, 22, 23, 32, 24, 25, 24, 25, 27, 28, 27, 29, 31, 35, 33, 33, 34};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final byte[] f1944m = {0, 0, 0, 1, 0, 3, 3, 20, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 17, 0, 13, 5, 23, 0, 1, 0, 1, 0, 0, 0, 15, 1, 0, 0, 3, 3, 20, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 17, 0, 13, 5, 23, 0, 0, 0, 7, 1, 0, 0};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f1946b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f1947c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.badlogic.gdx.utils.a<a> f1945a = new com.badlogic.gdx.utils.a<>(true, 8);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final o0 f1948d = new o0(64);

    /* JADX INFO: compiled from: XmlReader.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1949a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private y<String, String> f1950b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private com.badlogic.gdx.utils.a<a> f1951c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f1952d;

        public a(String str) {
            this.f1949a = str;
        }

        public final void a(a aVar) {
            if (this.f1951c == null) {
                this.f1951c = new com.badlogic.gdx.utils.a<>(true, 8);
            }
            this.f1951c.a(aVar);
        }

        public final void b() {
            y<String, String> yVar = this.f1950b;
            if (yVar == null || yVar.d("name") == null) {
                f("name");
            }
        }

        public final String c(String str) {
            y<String, String> yVar = this.f1950b;
            String str2 = this.f1949a;
            if (yVar == null) {
                throw new m("Element " + str2 + " doesn't have attribute: " + str);
            }
            String strD = yVar.d(str);
            if (strD != null) {
                return strD;
            }
            throw new m("Element " + str2 + " doesn't have attribute: " + str);
        }

        public final String d(String str, String str2) {
            String strD;
            y<String, String> yVar = this.f1950b;
            return (yVar == null || (strD = yVar.d(str)) == null) ? str2 : strD;
        }

        public final a e(int i2) {
            com.badlogic.gdx.utils.a<a> aVar = this.f1951c;
            if (aVar != null) {
                return aVar.get(i2);
            }
            throw new m("Element has no children: " + this.f1949a);
        }

        public final a f(String str) {
            if (this.f1951c == null) {
                return null;
            }
            int i2 = 0;
            while (true) {
                com.badlogic.gdx.utils.a<a> aVar = this.f1951c;
                if (i2 >= aVar.f1750b) {
                    return null;
                }
                a aVar2 = aVar.get(i2);
                if (aVar2.f1949a.equals(str)) {
                    return aVar2;
                }
                i2++;
            }
        }

        public final int g() {
            com.badlogic.gdx.utils.a<a> aVar = this.f1951c;
            if (aVar == null) {
                return 0;
            }
            return aVar.f1750b;
        }

        public final com.badlogic.gdx.utils.a<a> h(String str) {
            com.badlogic.gdx.utils.a<a> aVar = new com.badlogic.gdx.utils.a<>();
            if (this.f1951c == null) {
                return aVar;
            }
            int i2 = 0;
            while (true) {
                com.badlogic.gdx.utils.a<a> aVar2 = this.f1951c;
                if (i2 >= aVar2.f1750b) {
                    return aVar;
                }
                a aVar3 = aVar2.get(i2);
                if (aVar3.f1949a.equals(str)) {
                    aVar.a(aVar3);
                }
                i2++;
            }
        }

        public final float i(String str, float f2) {
            String strD = d(str, null);
            return strD == null ? f2 : Float.parseFloat(strD);
        }

        public final int j(int i2, String str) {
            String strD = d(str, null);
            return strD == null ? i2 : Integer.parseInt(strD);
        }

        public final String k() {
            return this.f1949a;
        }

        public final String l() {
            return this.f1952d;
        }

        public final boolean m(String str) {
            y<String, String> yVar = this.f1950b;
            if (yVar == null) {
                return false;
            }
            return yVar.a(str);
        }

        public final void n(a aVar) {
            com.badlogic.gdx.utils.a<a> aVar2 = this.f1951c;
            if (aVar2 != null) {
                aVar2.q(aVar, true);
            }
        }

        public final void o(String str, String str2) {
            if (this.f1950b == null) {
                this.f1950b = new y<>(8, 0);
            }
            this.f1950b.j(str, str2);
        }

        public final void p(String str) {
            this.f1952d = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final String q(String str) {
            String str2;
            o0 o0Var = new o0(VertexAttributes.Usage.Tangent);
            o0Var.h(str);
            o0Var.g('<');
            String str3 = this.f1949a;
            o0Var.h(str3);
            y<String, String> yVar = this.f1950b;
            if (yVar != null) {
                y.a<String, String> aVarB = yVar.b();
                aVarB.getClass();
                while (aVarB.hasNext()) {
                    y.b next = aVarB.next();
                    o0Var.g(' ');
                    o0Var.h((String) next.f2057a);
                    o0Var.h("=\"");
                    o0Var.h((String) next.f2058b);
                    o0Var.g('\"');
                }
            }
            if (this.f1951c == null && ((str2 = this.f1952d) == null || str2.length() == 0)) {
                o0Var.h("/>");
            } else {
                o0Var.h(">\n");
                String str4 = str + '\t';
                String str5 = this.f1952d;
                if (str5 != null && str5.length() > 0) {
                    o0Var.h(str4);
                    o0Var.h(this.f1952d);
                    o0Var.g('\n');
                }
                com.badlogic.gdx.utils.a<a> aVar = this.f1951c;
                if (aVar != null) {
                    a.b<a> it = aVar.iterator();
                    while (it.hasNext()) {
                        o0Var.h(it.next().q(str4));
                        o0Var.g('\n');
                    }
                }
                o0Var.h(str);
                o0Var.h("</");
                o0Var.h(str3);
                o0Var.g('>');
            }
            return o0Var.toString();
        }

        public final String toString() {
            return q("");
        }
    }

    public final a a(com.badlogic.gdx.files.a aVar) {
        char[] cArr;
        int i2;
        try {
            Reader erVar = aVar.reader("UTF-8");
            try {
                try {
                    cArr = new char[GL20.GL_STENCIL_BUFFER_BIT];
                    i2 = 0;
                } catch (IOException e2) {
                    throw new h0(e2);
                }
            } finally {
                n0.a(erVar);
            }
            while (true) {
                int i3 = erVar.read(cArr, i2, cArr.length - i2);
                if (i3 == -1) {
                    return b(cArr, i2);
                }
                if (i3 == 0) {
                    char[] cArr2 = new char[cArr.length * 2];
                    System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                    cArr = cArr2;
                } else {
                    i2 += i3;
                }
                n0.a(erVar);
            }
        } catch (Exception e3) {
            throw new h0(a.a.i(aVar, "Error parsing file: "), e3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x01c2, code lost:
    
        r5 = r6;
        r8 = 15;
        r6 = 2;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:43:0x00a4. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02ca A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x016a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final a b(char[] cArr, int i2) {
        com.badlogic.gdx.utils.a<a> aVar;
        int i3;
        int i4;
        byte b2;
        int i5;
        int i6;
        byte b3;
        byte[] bArr;
        char c2;
        byte[] bArr2;
        int i7 = 1;
        byte b4 = 1;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        String str = null;
        int i11 = 0;
        while (true) {
            aVar = this.f1945a;
            if (i8 != 0) {
                if (i8 != i7) {
                    if (i8 != 2) {
                    }
                }
                if (b4 != 0) {
                    i7 = 1;
                    i8 = 5;
                } else {
                    i9++;
                    if (i9 != i2) {
                        i7 = 1;
                        i8 = 1;
                    }
                }
            } else if (i9 == i2) {
                i8 = 4;
            } else if (b4 == 0) {
                i8 = 5;
            }
            int i12 = f1937f[b4];
            short s = f1941j[b4];
            byte b5 = f1939h[b4];
            char[] cArr2 = f1938g;
            int i13 = s;
            if (b5 > 0) {
                int i14 = i12 + b5;
                int i15 = i12;
                int i16 = i14 - 1;
                while (i16 >= i15) {
                    int i17 = i15 + ((i16 - i15) >> 1);
                    char c3 = cArr[i9];
                    char c4 = cArr2[i17];
                    if (c3 < c4) {
                        i16 = i17 - 1;
                    } else if (c3 > c4) {
                        i15 = i17 + 1;
                    } else {
                        i4 = (i17 - i12) + s;
                        byte b6 = f1942k[i4];
                        byte b7 = f1943l[b6];
                        b2 = f1944m[b6];
                        if (b2 != 0) {
                            byte[] bArr3 = f1936e;
                            int i18 = b2 + 1;
                            int i19 = bArr3[b2];
                            while (true) {
                                int i20 = i19 - 1;
                                if (i19 > 0) {
                                    int i21 = i18 + 1;
                                    switch (bArr3[i18]) {
                                        case 0:
                                            i6 = i21;
                                            b3 = b7;
                                            bArr = bArr3;
                                            i10 = i9;
                                            i19 = i20;
                                            i18 = i6;
                                            b7 = b3;
                                            bArr3 = bArr;
                                            i7 = 1;
                                            break;
                                        case 1:
                                            i6 = i21;
                                            b3 = b7;
                                            bArr = bArr3;
                                            int i22 = i7;
                                            c2 = cArr[i10];
                                            if (c2 != '?' && c2 != '!') {
                                                String str2 = new String(cArr, i10, i9 - i10);
                                                a aVar2 = this.f1947c;
                                                a aVar3 = new a(str2);
                                                if (aVar2 != null) {
                                                    aVar2.a(aVar3);
                                                }
                                                aVar.a(aVar3);
                                                this.f1947c = aVar3;
                                                i11 = i22;
                                                i19 = i20;
                                                i18 = i6;
                                                b7 = b3;
                                                bArr3 = bArr;
                                                i7 = 1;
                                            }
                                            break;
                                        case 2:
                                            i5 = i7;
                                            this.f1946b = aVar.l();
                                            this.f1947c = aVar.f1750b > 0 ? aVar.k() : null;
                                            i11 = 0;
                                            break;
                                        case 3:
                                            i5 = i7;
                                            this.f1946b = aVar.l();
                                            this.f1947c = aVar.f1750b > 0 ? aVar.k() : null;
                                            break;
                                        case 4:
                                            i6 = i21;
                                            b3 = b7;
                                            bArr = bArr3;
                                            i5 = i7;
                                            if (i11 != 0) {
                                            }
                                            i19 = i20;
                                            i18 = i6;
                                            b7 = b3;
                                            bArr3 = bArr;
                                            i7 = 1;
                                            break;
                                        case 5:
                                            i6 = i21;
                                            b3 = b7;
                                            bArr = bArr3;
                                            str = new String(cArr, i10, i9 - i10);
                                            i19 = i20;
                                            i18 = i6;
                                            b7 = b3;
                                            bArr3 = bArr;
                                            i7 = 1;
                                            break;
                                        case 6:
                                            i6 = i21;
                                            b3 = b7;
                                            bArr = bArr3;
                                            this.f1947c.o(str, new String(cArr, i10, i9 - i10));
                                            i19 = i20;
                                            i18 = i6;
                                            b7 = b3;
                                            bArr3 = bArr;
                                            i7 = 1;
                                            break;
                                        case 7:
                                            for (int i23 = i9; i23 != i10; i23--) {
                                                char c5 = cArr[i23 - 1];
                                                if (c5 != '\t' && c5 != '\n' && c5 != '\r' && c5 != ' ') {
                                                    int i24 = i10;
                                                    boolean z2 = false;
                                                    while (true) {
                                                        o0 o0Var = this.f1948d;
                                                        if (i10 != i23) {
                                                            i6 = i21;
                                                            b3 = b7;
                                                            bArr = bArr3;
                                                            if (z2) {
                                                                if (i24 < i23) {
                                                                    o0Var.i(cArr, i24, i23 - i24);
                                                                }
                                                                c(o0Var.toString());
                                                                o0Var.n(0);
                                                            } else {
                                                                c(new String(cArr, i24, i23 - i24));
                                                            }
                                                            i10 = i24;
                                                            i19 = i20;
                                                            i18 = i6;
                                                            b7 = b3;
                                                            bArr3 = bArr;
                                                            i7 = 1;
                                                            break;
                                                        } else {
                                                            int i25 = i21;
                                                            int i26 = i10 + 1;
                                                            byte b8 = b7;
                                                            if (cArr[i10] != '&') {
                                                                i10 = i26;
                                                                i21 = i25;
                                                                b7 = b8;
                                                            } else {
                                                                int i27 = i26;
                                                                while (true) {
                                                                    if (i27 != i23) {
                                                                        i10 = i27 + 1;
                                                                        bArr2 = bArr3;
                                                                        if (cArr[i27] != ';') {
                                                                            i27 = i10;
                                                                            bArr3 = bArr2;
                                                                        } else {
                                                                            o0Var.i(cArr, i24, (i26 - i24) - 1);
                                                                            String str3 = new String(cArr, i26, (i10 - i26) - 1);
                                                                            String string = str3.equals("lt") ? "<" : str3.equals("gt") ? ">" : str3.equals("amp") ? "&" : str3.equals("apos") ? "'" : str3.equals("quot") ? "\"" : str3.startsWith("#x") ? Character.toString((char) Integer.parseInt(str3.substring(2), 16)) : null;
                                                                            if (string != null) {
                                                                                str3 = string;
                                                                            }
                                                                            o0Var.h(str3);
                                                                            z2 = true;
                                                                            i24 = i10;
                                                                        }
                                                                    } else {
                                                                        bArr2 = bArr3;
                                                                        i10 = i27;
                                                                    }
                                                                }
                                                                i21 = i25;
                                                                b7 = b8;
                                                                bArr3 = bArr2;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            int i242 = i10;
                                            boolean z22 = false;
                                            while (true) {
                                                o0 o0Var2 = this.f1948d;
                                                if (i10 != i23) {
                                                }
                                            }
                                            break;
                                        default:
                                            i6 = i21;
                                            b3 = b7;
                                            bArr = bArr3;
                                            i19 = i20;
                                            i18 = i6;
                                            b7 = b3;
                                            bArr3 = bArr;
                                            i7 = 1;
                                            break;
                                    }
                                }
                            }
                            char c6 = cArr[i10 + 1];
                            if (c6 == '[' && cArr[i10 + 2] == 'C' && cArr[i10 + 3] == 'D' && cArr[i10 + 4] == 'A' && cArr[i10 + 5] == 'T' && cArr[i10 + 6] == 'A' && cArr[i10 + 7] == '[') {
                                int i28 = i10 + 8;
                                int i29 = i10 + 10;
                                while (true) {
                                    if (cArr[i29 - 2] == ']' && cArr[i29 - 1] == ']' && cArr[i29] == '>') {
                                        i8 = 2;
                                        c(new String(cArr, i28, (i29 - i28) - 2));
                                        i9 = i29;
                                        i10 = i28;
                                    } else {
                                        i29++;
                                    }
                                }
                            } else {
                                i8 = 2;
                                if (c2 == '!' && c6 == '-' && cArr[i10 + 2] == '-') {
                                    int i30 = i10 + 3;
                                    while (true) {
                                        if (cArr[i30] == '-' && cArr[i30 + 1] == '-') {
                                            int i31 = i30 + 2;
                                            if (cArr[i31] == '>') {
                                                i9 = i31;
                                            }
                                        }
                                        i30++;
                                    }
                                } else {
                                    while (cArr[i9] != '>') {
                                        i9++;
                                    }
                                }
                            }
                            b4 = 15;
                            i7 = 1;
                        }
                        b4 = b7;
                        if (b4 != 0) {
                        }
                    }
                }
                i12 = i14;
                i13 = s + b5;
                i3 = f1940i[b4];
                if (i3 <= 0) {
                    int i32 = ((i3 << 1) + i12) - 2;
                    int i33 = i12;
                    while (true) {
                        if (i32 >= i33) {
                            int i34 = (((i32 - i33) >> i7) & (-2)) + i33;
                            char c7 = cArr[i9];
                            if (c7 < cArr2[i34]) {
                                i32 = i34 - 2;
                            } else if (c7 > cArr2[i34 + 1]) {
                                i33 = i34 + 2;
                            } else {
                                i3 = (i34 - i12) >> 1;
                            }
                        }
                    }
                    i4 = i13 + i3;
                } else {
                    i4 = i13;
                }
                byte b62 = f1942k[i4];
                byte b72 = f1943l[b62];
                b2 = f1944m[b62];
                if (b2 != 0) {
                }
                b4 = b72;
                if (b4 != 0) {
                }
            } else {
                i3 = f1940i[b4];
                if (i3 <= 0) {
                }
                byte b622 = f1942k[i4];
                byte b722 = f1943l[b622];
                b2 = f1944m[b622];
                if (b2 != 0) {
                }
                b4 = b722;
                if (b4 != 0) {
                }
            }
        }
        if (i9 < i2) {
            int i35 = 1;
            for (int i36 = 0; i36 < i9; i36++) {
                if (cArr[i36] == '\n') {
                    i35++;
                }
            }
            StringBuilder sbR = a.a.r(i35, "Error parsing XML on line ", " near: ");
            sbR.append(new String(cArr, i9, Math.min(32, i2 - i9)));
            throw new h0(sbR.toString());
        }
        if (aVar.f1750b == 0) {
            a aVar4 = this.f1946b;
            this.f1946b = null;
            return aVar4;
        }
        a aVarK = aVar.k();
        aVar.clear();
        throw new h0("Error parsing XML, unclosed element: " + aVarK.k());
    }

    protected final void c(String str) {
        String strL = this.f1947c.l();
        a aVar = this.f1947c;
        if (strL != null) {
            str = strL.concat(str);
        }
        aVar.p(str);
    }
}
