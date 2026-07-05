package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: ComparableTimSort.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object[] f1799a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1802d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1800b = 7;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1803e = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Object[] f1801c = new Object[256];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int[] f1804f = new int[40];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int[] f1805g = new int[40];

    g() {
    }

    private static void a(Object[] objArr, int i2, int i3, int i4) {
        if (i4 == i2) {
            i4++;
        }
        while (i4 < i3) {
            Comparable comparable = (Comparable) objArr[i4];
            int i5 = i2;
            int i6 = i4;
            while (i5 < i6) {
                int i7 = (i5 + i6) >>> 1;
                if (comparable.compareTo(objArr[i7]) < 0) {
                    i6 = i7;
                } else {
                    i5 = i7 + 1;
                }
            }
            int i8 = i4 - i5;
            if (i8 == 1) {
                objArr[i5 + 1] = objArr[i5];
            } else if (i8 != 2) {
                System.arraycopy(objArr, i5, objArr, i5 + 1, i8);
            } else {
                objArr[i5 + 2] = objArr[i5 + 1];
                objArr[i5 + 1] = objArr[i5];
            }
            objArr[i5] = comparable;
            i4++;
        }
    }

    private static int b(Object[] objArr, int i2, int i3) {
        int i4 = i2 + 1;
        if (i4 == i3) {
            return 1;
        }
        int i5 = i2 + 2;
        if (((Comparable) objArr[i4]).compareTo(objArr[i2]) < 0) {
            while (i5 < i3 && ((Comparable) objArr[i5]).compareTo(objArr[i5 - 1]) < 0) {
                i5++;
            }
            int i6 = i5 - 1;
            for (int i7 = i2; i7 < i6; i7++) {
                Object obj = objArr[i7];
                objArr[i7] = objArr[i6];
                objArr[i6] = obj;
                i6--;
            }
        } else {
            while (i5 < i3 && ((Comparable) objArr[i5]).compareTo(objArr[i5 - 1]) >= 0) {
                i5++;
            }
        }
        return i5 - i2;
    }

    private Object[] d(int i2) {
        this.f1802d = Math.max(this.f1802d, i2);
        if (this.f1801c.length < i2) {
            int i3 = (i2 >> 1) | i2;
            int i4 = i3 | (i3 >> 2);
            int i5 = i4 | (i4 >> 4);
            int i6 = i5 | (i5 >> 8);
            int i7 = (i6 | (i6 >> 16)) + 1;
            if (i7 >= 0) {
                i2 = Math.min(i7, this.f1799a.length >>> 1);
            }
            this.f1801c = new Object[i2];
        }
        return this.f1801c;
    }

    private static int e(Comparable<Object> comparable, Object[] objArr, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i2 + i4;
        if (comparable.compareTo(objArr[i7]) > 0) {
            int i8 = i3 - i4;
            int i9 = 0;
            int i10 = 1;
            while (i10 < i8 && comparable.compareTo(objArr[i7 + i10]) > 0) {
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
            while (i15 < i13 && comparable.compareTo(objArr[i7 - i15]) <= 0) {
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
            if (comparable.compareTo(objArr[i2 + i21]) > 0) {
                i20 = i21 + 1;
            } else {
                i6 = i21;
            }
        }
        return i6;
    }

    private static int f(Comparable<Object> comparable, Object[] objArr, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i2 + i4;
        if (comparable.compareTo(objArr[i7]) < 0) {
            int i8 = i4 + 1;
            int i9 = 0;
            int i10 = 1;
            while (i10 < i8 && comparable.compareTo(objArr[i7 - i10]) < 0) {
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
            while (i15 < i13 && comparable.compareTo(objArr[i7 + i15]) >= 0) {
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
            if (comparable.compareTo(objArr[i2 + i20]) < 0) {
                i5 = i20;
            } else {
                i19 = i20 + 1;
            }
        }
        return i5;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01db, code lost:
    
        r12 = r13 - r2;
        r13 = r14 - r2;
        r7 = r7 - r2;
        java.lang.System.arraycopy(r11, r13 + 1, r8, r12 + 1, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01e7, code lost:
    
        if (r7 > 1) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01e9, code lost:
    
        r2 = r7;
        r6 = r12;
        r12 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01ed, code lost:
    
        r13 = r12;
        r12 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01f3, code lost:
    
        r12 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01f4, code lost:
    
        r14 = r13 - 1;
        r15 = r4 - 1;
        r8[r13] = r8[r4];
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01fe, code lost:
    
        if (r5 != 0) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0200, code lost:
    
        r2 = r7;
        r6 = r14;
        r4 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0227, code lost:
    
        r9 = r9 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x022b, code lost:
    
        if (r6 < 7) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x022d, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x022f, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0230, code lost:
    
        if (r2 < 7) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0232, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0234, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0236, code lost:
    
        if ((r2 | r6) != false) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0238, code lost:
    
        if (r9 >= 0) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x023a, code lost:
    
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0243, code lost:
    
        r2 = r7;
        r7 = r14;
        r4 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b4, code lost:
    
        r7 = f((java.lang.Comparable) r11[r9], r12, r3, r5, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00bc, code lost:
    
        if (r7 == 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00be, code lost:
    
        java.lang.System.arraycopy(r12, r3, r11, r4, r7);
        r4 = r4 + r7;
        r3 = r3 + r7;
        r5 = r5 - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c4, code lost:
    
        if (r5 > 1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c6, code lost:
    
        r7 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c8, code lost:
    
        r8 = r4 + 1;
        r14 = r9 + 1;
        r11[r4] = r11[r9];
        r2 = r2 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00d2, code lost:
    
        if (r2 != 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d4, code lost:
    
        r7 = r8;
        r9 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d7, code lost:
    
        r4 = e((java.lang.Comparable) r12[r3], r11, r14, r2, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00df, code lost:
    
        if (r4 == 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e1, code lost:
    
        java.lang.System.arraycopy(r11, r14, r11, r8, r4);
        r8 = r8 + r4;
        r9 = r14 + r4;
        r2 = r2 - r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e8, code lost:
    
        if (r2 != 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ea, code lost:
    
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ec, code lost:
    
        r9 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ed, code lost:
    
        r14 = r8 + 1;
        r15 = r3 + 1;
        r11[r8] = r12[r3];
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f7, code lost:
    
        if (r5 != 1) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00f9, code lost:
    
        r7 = r14;
        r3 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0119, code lost:
    
        r13 = r13 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x011c, code lost:
    
        if (r7 < 7) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x011e, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0120, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0121, code lost:
    
        if (r4 < 7) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0123, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0125, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0127, code lost:
    
        if ((r3 | r7) != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0129, code lost:
    
        if (r13 >= 0) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012b, code lost:
    
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0132, code lost:
    
        r4 = r14;
        r3 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0197, code lost:
    
        r12 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01a1, code lost:
    
        r6 = r5 - f((java.lang.Comparable) r11[r12], r8, r3, r5, r5 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01ad, code lost:
    
        if (r6 == 0) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01af, code lost:
    
        r7 = r7 - r6;
        r4 = r4 - r6;
        r5 = r5 - r6;
        java.lang.System.arraycopy(r8, r4 + 1, r8, r7 + 1, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01b9, code lost:
    
        if (r5 != 0) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01bb, code lost:
    
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01bd, code lost:
    
        r13 = r7 - 1;
        r14 = r12 - 1;
        r8[r7] = r11[r12];
        r7 = r2 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01c7, code lost:
    
        if (r7 != 1) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01c9, code lost:
    
        r2 = r7;
        r6 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01cc, code lost:
    
        r2 = r7 - e((java.lang.Comparable) r8[r4], r11, 0, r7, r2 - 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01d9, code lost:
    
        if (r2 == 0) goto L104;
     */
    /* JADX WARN: Removed duplicated region for block: B:140:0x00b4 A[EDGE_INSN: B:140:0x00b4->B:32:0x00b4 BREAK  A[LOOP:1: B:20:0x007d->B:141:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:141:? A[LOOP:1: B:20:0x007d->B:141:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void g(int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int[] iArr = this.f1804f;
        int i10 = iArr[i2];
        int[] iArr2 = this.f1805g;
        int i11 = iArr2[i2];
        int i12 = i2 + 1;
        int i13 = iArr[i12];
        int i14 = iArr2[i12];
        iArr2[i2] = i11 + i14;
        int i15 = this.f1803e;
        if (i2 == i15 - 3) {
            int i16 = i2 + 2;
            iArr[i12] = iArr[i16];
            iArr2[i12] = iArr2[i16];
        }
        this.f1803e = i15 - 1;
        Object[] objArr = this.f1799a;
        int i17 = 0;
        int iF = f((Comparable) objArr[i13], objArr, i10, i11, 0);
        int i18 = i10 + iF;
        int i19 = i11 - iF;
        if (i19 == 0) {
            return;
        }
        Object[] objArr2 = this.f1799a;
        int i20 = i18 + i19;
        int i21 = i20 - 1;
        int iE = e((Comparable) objArr2[i21], objArr2, i13, i14, i14 - 1);
        if (iE == 0) {
            return;
        }
        if (i19 <= iE) {
            Object[] objArr3 = this.f1799a;
            Object[] objArrD = d(i19);
            System.arraycopy(objArr3, i18, objArrD, 0, i19);
            int i22 = i18 + 1;
            int i23 = i13 + 1;
            objArr3[i18] = objArr3[i13];
            int i24 = iE - 1;
            if (i24 == 0) {
                System.arraycopy(objArrD, 0, objArr3, i22, i19);
                return;
            }
            if (i19 == 1) {
                System.arraycopy(objArr3, i23, objArr3, i22, i24);
                objArr3[i22 + i24] = objArrD[0];
                return;
            }
            int i25 = this.f1800b;
            int i26 = 0;
            loop0: while (true) {
                int i27 = 0;
                int i28 = 0;
                while (true) {
                    if (((Comparable) objArr3[i23]).compareTo(objArrD[i26]) < 0) {
                        i7 = i22 + 1;
                        int i29 = i23 + 1;
                        objArr3[i22] = objArr3[i23];
                        i28++;
                        i24--;
                        if (i24 == 0) {
                            i23 = i29;
                            break loop0;
                        }
                        i22 = i7;
                        i23 = i29;
                        i27 = 0;
                        if ((i27 | i28) < i25) {
                            break;
                        }
                    } else {
                        int i30 = i22 + 1;
                        int i31 = i26 + 1;
                        objArr3[i22] = objArrD[i26];
                        i27++;
                        i19--;
                        if (i19 == 1) {
                            i7 = i30;
                            i26 = i31;
                            break loop0;
                        } else {
                            i22 = i30;
                            i26 = i31;
                            i28 = 0;
                            if ((i27 | i28) < i25) {
                            }
                        }
                    }
                }
                i25 += 2;
                i22 = i8;
                i26 = i9;
            }
            if (i25 < 1) {
                i25 = 1;
            }
            this.f1800b = i25;
            if (i19 == 1) {
                System.arraycopy(objArr3, i23, objArr3, i7, i24);
                objArr3[i7 + i24] = objArrD[i26];
                return;
            } else {
                if (i19 == 0) {
                    throw new IllegalArgumentException("Comparison method violates its general contract!");
                }
                System.arraycopy(objArrD, i26, objArr3, i7, i19);
                return;
            }
        }
        Object[] objArr4 = this.f1799a;
        Object[] objArrD2 = d(iE);
        System.arraycopy(objArr4, i13, objArrD2, 0, iE);
        int i32 = iE - 1;
        int i33 = i13 + iE;
        int i34 = i33 - 1;
        int i35 = i33 - 2;
        int i36 = i20 - 2;
        objArr4[i34] = objArr4[i21];
        int i37 = i19 - 1;
        if (i37 == 0) {
            System.arraycopy(objArrD2, 0, objArr4, i35 - i32, iE);
            return;
        }
        if (iE == 1) {
            int i38 = i35 - i37;
            System.arraycopy(objArr4, (i36 - i37) + 1, objArr4, i38 + 1, i37);
            objArr4[i38] = objArrD2[i32];
            return;
        }
        int i39 = this.f1800b;
        loop3: while (true) {
            int i40 = i17;
            int i41 = i40;
            while (true) {
                if (((Comparable) objArrD2[i32]).compareTo(objArr4[i36]) < 0) {
                    i3 = i35 - 1;
                    int i42 = i36 - 1;
                    objArr4[i35] = objArr4[i36];
                    i41++;
                    i37--;
                    if (i37 == 0) {
                        i36 = i42;
                        break loop3;
                    } else {
                        i35 = i3;
                        i36 = i42;
                        i40 = 0;
                    }
                } else {
                    i3 = i35 - 1;
                    int i43 = i32 - 1;
                    objArr4[i35] = objArrD2[i32];
                    i40++;
                    iE--;
                    if (iE == 1) {
                        break loop3;
                    }
                    i35 = i3;
                    i32 = i43;
                    i41 = 0;
                }
                if ((i41 | i40) >= i39) {
                    break;
                }
            }
            i39 += 2;
            i17 = 0;
            iE = i4;
            i35 = i5;
            i36 = i6;
        }
        if (i39 < 1) {
            i39 = 1;
        }
        this.f1800b = i39;
        if (iE == 1) {
            int i44 = i3 - i37;
            System.arraycopy(objArr4, (i36 - i37) + 1, objArr4, i44 + 1, i37);
            objArr4[i44] = objArrD2[i32];
        } else {
            if (iE == 0) {
                throw new IllegalArgumentException("Comparison method violates its general contract!");
            }
            System.arraycopy(objArrD2, 0, objArr4, i3 - (iE - 1), iE);
        }
    }

    public final void c(Object[] objArr, int i2) {
        int[] iArr;
        this.f1803e = 0;
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
            a(objArr, 0, i2, b(objArr, 0, i2));
            return;
        }
        this.f1799a = objArr;
        this.f1802d = 0;
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
            int iB = b(objArr, i7, i2);
            if (iB < i5) {
                int i8 = i6 <= i5 ? i6 : i5;
                a(objArr, i7, i7 + i8, iB + i7);
                iB = i8;
            }
            int i9 = this.f1803e;
            this.f1804f[i9] = i7;
            iArr = this.f1805g;
            iArr[i9] = iB;
            this.f1803e = i9 + 1;
            while (true) {
                int i10 = this.f1803e;
                if (i10 <= 1) {
                    break;
                }
                int i11 = i10 - 2;
                if (i11 > 0) {
                    int i12 = i10 - 3;
                    int i13 = iArr[i12];
                    int i14 = iArr[i11];
                    int i15 = iArr[i10 - 1];
                    if (i13 <= i14 + i15) {
                        if (i13 < i15) {
                            i11 = i12;
                        }
                        g(i11);
                    }
                }
                if (iArr[i11] > iArr[i10 - 1]) {
                    break;
                } else {
                    g(i11);
                }
            }
            i7 += iB;
            i6 -= iB;
        } while (i6 != 0);
        while (true) {
            int i16 = this.f1803e;
            if (i16 <= 1) {
                break;
            }
            int i17 = i16 - 2;
            if (i17 > 0) {
                int i18 = i16 - 3;
                if (iArr[i18] < iArr[i16 - 1]) {
                    i17 = i18;
                }
            }
            g(i17);
        }
        this.f1799a = null;
        Object[] objArr2 = this.f1801c;
        int i19 = this.f1802d;
        for (int i20 = 0; i20 < i19; i20++) {
            objArr2[i20] = null;
        }
    }
}
