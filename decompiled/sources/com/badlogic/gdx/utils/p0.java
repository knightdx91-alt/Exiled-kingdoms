package com.badlogic.gdx.utils;

import java.util.Comparator;

/* JADX INFO: compiled from: TimSort.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class p0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private T[] f1874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Comparator<? super T> f1875b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1878e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1876c = 7;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1879f = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private T[] f1877d = (T[]) new Object[256];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int[] f1880g = new int[40];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int[] f1881h = new int[40];

    p0() {
    }

    private static <T> void a(T[] tArr, int i2, int i3, int i4, Comparator<? super T> comparator) {
        if (i4 == i2) {
            i4++;
        }
        while (i4 < i3) {
            T t2 = tArr[i4];
            int i5 = i2;
            int i6 = i4;
            while (i5 < i6) {
                int i7 = (i5 + i6) >>> 1;
                if (comparator.compare(t2, tArr[i7]) < 0) {
                    i6 = i7;
                } else {
                    i5 = i7 + 1;
                }
            }
            int i8 = i4 - i5;
            if (i8 == 1) {
                tArr[i5 + 1] = tArr[i5];
            } else if (i8 != 2) {
                System.arraycopy(tArr, i5, tArr, i5 + 1, i8);
            } else {
                tArr[i5 + 2] = tArr[i5 + 1];
                tArr[i5 + 1] = tArr[i5];
            }
            tArr[i5] = t2;
            i4++;
        }
    }

    private static <T> int b(T[] tArr, int i2, int i3, Comparator<? super T> comparator) {
        int i4 = i2 + 1;
        if (i4 == i3) {
            return 1;
        }
        int i5 = i2 + 2;
        if (comparator.compare(tArr[i4], tArr[i2]) < 0) {
            while (i5 < i3 && comparator.compare(tArr[i5], tArr[i5 - 1]) < 0) {
                i5++;
            }
            int i6 = i5 - 1;
            for (int i7 = i2; i7 < i6; i7++) {
                T t2 = tArr[i7];
                tArr[i7] = tArr[i6];
                tArr[i6] = t2;
                i6--;
            }
        } else {
            while (i5 < i3 && comparator.compare(tArr[i5], tArr[i5 - 1]) >= 0) {
                i5++;
            }
        }
        return i5 - i2;
    }

    private T[] d(int i2) {
        this.f1878e = Math.max(this.f1878e, i2);
        if (this.f1877d.length < i2) {
            int i3 = (i2 >> 1) | i2;
            int i4 = i3 | (i3 >> 2);
            int i5 = i4 | (i4 >> 4);
            int i6 = i5 | (i5 >> 8);
            int i7 = (i6 | (i6 >> 16)) + 1;
            if (i7 >= 0) {
                i2 = Math.min(i7, this.f1874a.length >>> 1);
            }
            this.f1877d = (T[]) new Object[i2];
        }
        return this.f1877d;
    }

    private static <T> int e(T t2, T[] tArr, int i2, int i3, int i4, Comparator<? super T> comparator) {
        int i5;
        int i6;
        int i7 = i2 + i4;
        if (comparator.compare(t2, tArr[i7]) > 0) {
            int i8 = i3 - i4;
            int i9 = 0;
            int i10 = 1;
            while (i10 < i8 && comparator.compare(t2, tArr[i7 + i10]) > 0) {
                int i11 = (i10 << 1) + 1;
                if (i11 <= 0) {
                    i9 = i10;
                    i10 = i8;
                } else {
                    int i12 = i10;
                    i10 = i11;
                    i9 = i12;
                }
            }
            if (i10 <= i8) {
                i8 = i10;
            }
            i5 = i9 + i4;
            i6 = i8 + i4;
        } else {
            int i13 = i4 + 1;
            int i14 = 0;
            int i15 = 1;
            while (i15 < i13 && comparator.compare(t2, tArr[i7 - i15]) <= 0) {
                int i16 = (i15 << 1) + 1;
                if (i16 <= 0) {
                    i14 = i15;
                    i15 = i13;
                } else {
                    int i17 = i15;
                    i15 = i16;
                    i14 = i17;
                }
            }
            if (i15 <= i13) {
                i13 = i15;
            }
            int i18 = i4 - i13;
            int i19 = i4 - i14;
            i5 = i18;
            i6 = i19;
        }
        int i20 = i5 + 1;
        while (i20 < i6) {
            int i21 = ((i6 - i20) >>> 1) + i20;
            if (comparator.compare(t2, tArr[i2 + i21]) > 0) {
                i20 = i21 + 1;
            } else {
                i6 = i21;
            }
        }
        return i6;
    }

    private static <T> int f(T t2, T[] tArr, int i2, int i3, int i4, Comparator<? super T> comparator) {
        int i5;
        int i6;
        int i7 = i2 + i4;
        if (comparator.compare(t2, tArr[i7]) < 0) {
            int i8 = i4 + 1;
            int i9 = 0;
            int i10 = 1;
            while (i10 < i8 && comparator.compare(t2, tArr[i7 - i10]) < 0) {
                int i11 = (i10 << 1) + 1;
                if (i11 <= 0) {
                    i9 = i10;
                    i10 = i8;
                } else {
                    int i12 = i10;
                    i10 = i11;
                    i9 = i12;
                }
            }
            if (i10 <= i8) {
                i8 = i10;
            }
            i6 = i4 - i8;
            i5 = i4 - i9;
        } else {
            int i13 = i3 - i4;
            int i14 = 0;
            int i15 = 1;
            while (i15 < i13 && comparator.compare(t2, tArr[i7 + i15]) >= 0) {
                int i16 = (i15 << 1) + 1;
                if (i16 <= 0) {
                    i14 = i15;
                    i15 = i13;
                } else {
                    int i17 = i15;
                    i15 = i16;
                    i14 = i17;
                }
            }
            if (i15 <= i13) {
                i13 = i15;
            }
            int i18 = i14 + i4;
            i5 = i4 + i13;
            i6 = i18;
        }
        int i19 = i6 + 1;
        while (i19 < i5) {
            int i20 = ((i5 - i19) >>> 1) + i19;
            if (comparator.compare(t2, tArr[i2 + i20]) < 0) {
                i5 = i20;
            } else {
                i19 = i20 + 1;
            }
        }
        return i5;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0201, code lost:
    
        r5 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0203, code lost:
    
        r4 = r16 - 1;
        r6 = r13 - 1;
        r11[r16] = r15[r13];
        r7 = r10 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x020e, code lost:
    
        if (r7 != 1) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0210, code lost:
    
        r3 = r1;
        r1 = r4;
        r4 = r7;
        r14 = 1;
        r10 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0215, code lost:
    
        r21 = r6;
        r6 = r5;
        r5 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x021b, code lost:
    
        r17 = r10 - 2;
        r20 = r14;
        r10 = r15;
        r8 = r7 - e(r11[r2], r15, 0, r7, r17, r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x022f, code lost:
    
        if (r8 == 0) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0231, code lost:
    
        r4 = r4 - r8;
        r6 = r6 - r8;
        r7 = r7 - r8;
        java.lang.System.arraycopy(r10, r6 + 1, r11, r4 + 1, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x023c, code lost:
    
        if (r7 > 1) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x023e, code lost:
    
        r3 = r1;
        r1 = r4;
        r4 = r7;
        r14 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0243, code lost:
    
        r13 = r6;
        r16 = r4 - 1;
        r6 = r2 - 1;
        r11[r4] = r11[r2];
        r17 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x024e, code lost:
    
        if (r17 != 0) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0250, code lost:
    
        r3 = r1;
        r2 = r6;
        r4 = r7;
        r5 = r13;
        r1 = r16;
        r6 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x027e, code lost:
    
        r1 = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0283, code lost:
    
        if (r3 < 7) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0285, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0287, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0288, code lost:
    
        if (r8 < 7) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x028a, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x028c, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x028e, code lost:
    
        if ((r3 | r5) != false) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0290, code lost:
    
        if (r1 >= 0) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0292, code lost:
    
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0294, code lost:
    
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02a4, code lost:
    
        r2 = r6;
        r15 = r10;
        r14 = r20;
        r10 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00bd, code lost:
    
        r6 = r14;
        r9 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00bf, code lost:
    
        r11 = f(r5[r9], r7, r3, r10, 0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ce, code lost:
    
        if (r11 == 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d0, code lost:
    
        java.lang.System.arraycopy(r7, r3, r5, r2, r11);
        r2 = r2 + r11;
        r3 = r3 + r11;
        r10 = r10 - r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00d6, code lost:
    
        if (r10 > 1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d8, code lost:
    
        r14 = r6;
        r15 = r9;
        r6 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00dc, code lost:
    
        r13 = r2 + 1;
        r15 = r9 + 1;
        r5[r2] = r5[r9];
        r4 = r4 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e6, code lost:
    
        if (r4 != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e8, code lost:
    
        r14 = r6;
        r6 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00eb, code lost:
    
        r9 = r15;
        r2 = e(r7[r3], r5, r9, r4, 0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00fb, code lost:
    
        if (r2 == 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00fd, code lost:
    
        java.lang.System.arraycopy(r5, r9, r5, r13, r2);
        r13 = r13 + r2;
        r15 = r9 + r2;
        r4 = r4 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0104, code lost:
    
        if (r4 != 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0107, code lost:
    
        r9 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0108, code lost:
    
        r14 = r13 + 1;
        r15 = r3 + 1;
        r5[r13] = r7[r3];
        r10 = r10 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0112, code lost:
    
        if (r10 != 1) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0114, code lost:
    
        r3 = r15;
        r15 = r9;
        r14 = r6;
        r6 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0139, code lost:
    
        r6 = r6 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x013c, code lost:
    
        if (r11 < 7) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x013e, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0140, code lost:
    
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0141, code lost:
    
        if (r2 < 7) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0143, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0145, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0147, code lost:
    
        if ((r2 | r11) != false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0149, code lost:
    
        if (r6 >= 0) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x014b, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0158, code lost:
    
        r2 = r14;
        r3 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01af, code lost:
    
        r10 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01d2, code lost:
    
        r1 = r3;
        r13 = r5;
        r16 = r8;
        r17 = r10;
        r10 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01d9, code lost:
    
        r3 = r17 - f(r15[r13], r11, r9, r17, r17 - 1, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01e8, code lost:
    
        if (r3 == 0) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01ea, code lost:
    
        r4 = r16 - r3;
        r2 = r2 - r3;
        r5 = r17 - r3;
        java.lang.System.arraycopy(r11, r2 + 1, r11, r4 + 1, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01f6, code lost:
    
        if (r5 != 0) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01f8, code lost:
    
        r3 = r1;
        r1 = r4;
        r6 = r5;
        r4 = r10;
        r5 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01fe, code lost:
    
        r16 = r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:146:0x00bd A[EDGE_INSN: B:146:0x00bd->B:32:0x00bd BREAK  A[LOOP:1: B:20:0x0087->B:147:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:? A[LOOP:1: B:20:0x0087->B:147:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void g(int i2) {
        int i3;
        int i4;
        int i5;
        T[] tArr;
        int i6;
        int i7;
        int i8;
        int i9;
        Comparator<? super T> comparator;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int[] iArr = this.f1880g;
        int i17 = iArr[i2];
        int[] iArr2 = this.f1881h;
        int i18 = iArr2[i2];
        int i19 = i2 + 1;
        int i20 = iArr[i19];
        int i21 = iArr2[i19];
        iArr2[i2] = i18 + i21;
        int i22 = this.f1879f;
        if (i2 == i22 - 3) {
            int i23 = i2 + 2;
            iArr[i19] = iArr[i23];
            iArr2[i19] = iArr2[i23];
        }
        this.f1879f = i22 - 1;
        T[] tArr2 = this.f1874a;
        int iF = f(tArr2[i20], tArr2, i17, i18, 0, this.f1875b);
        int i24 = i17 + iF;
        int i25 = i18 - iF;
        if (i25 == 0) {
            return;
        }
        T[] tArr3 = this.f1874a;
        int i26 = i24 + i25;
        int i27 = i26 - 1;
        int iE = e(tArr3[i27], tArr3, i20, i21, i21 - 1, this.f1875b);
        if (iE == 0) {
            return;
        }
        int i28 = 0;
        if (i25 <= iE) {
            T[] tArr4 = this.f1874a;
            T[] tArrD = d(i25);
            System.arraycopy(tArr4, i24, tArrD, 0, i25);
            int i29 = i24 + 1;
            int i30 = i20 + 1;
            tArr4[i24] = tArr4[i20];
            int i31 = iE - 1;
            if (i31 == 0) {
                System.arraycopy(tArrD, 0, tArr4, i29, i25);
                return;
            }
            if (i25 == 1) {
                System.arraycopy(tArr4, i30, tArr4, i29, i31);
                tArr4[i29 + i31] = tArrD[0];
                return;
            }
            Comparator<? super T> comparator2 = this.f1875b;
            int i32 = this.f1876c;
            int i33 = 0;
            loop0: while (true) {
                int i34 = i28;
                int i35 = i34;
                while (true) {
                    if (comparator2.compare(tArr4[i30], tArrD[i33]) < 0) {
                        i12 = i29 + 1;
                        int i36 = i30 + 1;
                        tArr4[i29] = tArr4[i30];
                        i35++;
                        i31--;
                        if (i31 == 0) {
                            i30 = i36;
                            break loop0;
                        }
                        i29 = i12;
                        i30 = i36;
                        i34 = 0;
                        if ((i34 | i35) < i32) {
                            break;
                        }
                    } else {
                        int i37 = i29 + 1;
                        int i38 = i33 + 1;
                        tArr4[i29] = tArrD[i33];
                        i34++;
                        i25--;
                        if (i25 == 1) {
                            i12 = i37;
                            i33 = i38;
                            break loop0;
                        } else {
                            i29 = i37;
                            i33 = i38;
                            i35 = 0;
                            if ((i34 | i35) < i32) {
                            }
                        }
                    }
                }
                i33 = i16;
                i28 = 0;
                i30 = i14;
                i32 = i13 + 2;
                i29 = i15;
            }
            if (i32 < 1) {
                i32 = 1;
            }
            this.f1876c = i32;
            if (i25 == 1) {
                System.arraycopy(tArr4, i30, tArr4, i12, i31);
                tArr4[i12 + i31] = tArrD[i33];
                return;
            } else {
                if (i25 == 0) {
                    throw new IllegalArgumentException("Comparison method violates its general contract!");
                }
                System.arraycopy(tArrD, i33, tArr4, i12, i25);
                return;
            }
        }
        T[] tArr5 = this.f1874a;
        T[] tArrD2 = d(iE);
        System.arraycopy(tArr5, i20, tArrD2, 0, iE);
        int i39 = iE - 1;
        int i40 = i20 + iE;
        int i41 = i40 - 1;
        int i42 = i40 - 2;
        int i43 = i26 - 2;
        tArr5[i41] = tArr5[i27];
        int i44 = i25 - 1;
        if (i44 == 0) {
            System.arraycopy(tArrD2, 0, tArr5, i42 - i39, iE);
            return;
        }
        if (iE == 1) {
            int i45 = i42 - i44;
            System.arraycopy(tArr5, (i43 - i44) + 1, tArr5, i45 + 1, i44);
            tArr5[i45] = tArrD2[i39];
            return;
        }
        Comparator<? super T> comparator3 = this.f1875b;
        int i46 = this.f1876c;
        loop3: while (true) {
            int i47 = i42;
            int i48 = 0;
            int i49 = 0;
            while (true) {
                if (comparator3.compare(tArrD2[i39], tArr5[i43]) < 0) {
                    i3 = i47 - 1;
                    int i50 = i43 - 1;
                    tArr5[i47] = tArr5[i43];
                    i49++;
                    i44--;
                    if (i44 == 0) {
                        i43 = i50;
                        i5 = i44;
                        break loop3;
                    } else {
                        i47 = i3;
                        i43 = i50;
                        i48 = 0;
                    }
                } else {
                    i3 = i47 - 1;
                    int i51 = i39 - 1;
                    tArr5[i47] = tArrD2[i39];
                    i48++;
                    iE--;
                    if (iE == 1) {
                        i4 = 1;
                        i39 = i51;
                        i5 = i44;
                        tArr = tArrD2;
                        break loop3;
                    }
                    i47 = i3;
                    i39 = i51;
                    i49 = 0;
                }
                if ((i49 | i48) >= i46) {
                    break;
                }
            }
            i46 = i11 + 2;
            i43 = i10;
            iE = i9;
            tArrD2 = tArr;
            i39 = i6;
            i42 = i7;
            i44 = i8;
            comparator3 = comparator;
        }
        i4 = 1;
        if (i46 < i4) {
            i46 = i4;
        }
        this.f1876c = i46;
        if (iE == i4) {
            int i52 = i3 - i5;
            System.arraycopy(tArr5, (i43 - i5) + i4, tArr5, i52 + 1, i5);
            tArr5[i52] = tArr[i39];
        } else {
            if (iE == 0) {
                throw new IllegalArgumentException("Comparison method violates its general contract!");
            }
            System.arraycopy(tArr, 0, tArr5, i3 - (iE - 1), iE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(Object[] objArr, Comparator comparator, int i2) {
        int[] iArr;
        this.f1879f = 0;
        int length = objArr.length;
        if (i2 < 0) {
            throw new IllegalArgumentException("fromIndex(0) > toIndex(" + i2 + ")");
        }
        if (i2 > length) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        if (i2 < 2) {
            return;
        }
        if (i2 < 32) {
            a(objArr, 0, i2, b(objArr, 0, i2, comparator), comparator);
            return;
        }
        this.f1874a = objArr;
        this.f1875b = comparator;
        this.f1878e = 0;
        int i3 = i2;
        int i4 = 0;
        while (i3 >= 32) {
            i4 |= i3 & 1;
            i3 >>= 1;
        }
        int i5 = i3 + i4;
        int i6 = i2;
        int i7 = 0;
        do {
            int iB = b(objArr, i7, i2, comparator);
            if (iB < i5) {
                int i8 = i6 <= i5 ? i6 : i5;
                a(objArr, i7, i7 + i8, iB + i7, comparator);
                iB = i8;
            }
            int i9 = this.f1879f;
            this.f1880g[i9] = i7;
            iArr = this.f1881h;
            iArr[i9] = iB;
            this.f1879f = i9 + 1;
            while (true) {
                int i10 = this.f1879f;
                if (i10 <= 1) {
                    break;
                }
                int i11 = i10 - 2;
                if ((i11 < 1 || iArr[i10 - 3] > iArr[i11] + iArr[i10 - 1]) && (i11 < 2 || iArr[i10 - 4] > iArr[i11] + iArr[i10 - 3])) {
                    if (iArr[i11] > iArr[i10 - 1]) {
                        break;
                    }
                } else {
                    int i12 = i10 - 3;
                    if (iArr[i12] < iArr[i10 - 1]) {
                        i11 = i12;
                    }
                }
                g(i11);
            }
            i7 += iB;
            i6 -= iB;
        } while (i6 != 0);
        while (true) {
            int i13 = this.f1879f;
            if (i13 <= 1) {
                break;
            }
            int i14 = i13 - 2;
            if (i14 > 0) {
                int i15 = i13 - 3;
                if (iArr[i15] < iArr[i13 - 1]) {
                    i14 = i15;
                }
            }
            g(i14);
        }
        this.f1874a = null;
        this.f1875b = null;
        T[] tArr = this.f1877d;
        int i16 = this.f1878e;
        for (int i17 = 0; i17 < i16; i17++) {
            tArr[i17] = null;
        }
    }
}
