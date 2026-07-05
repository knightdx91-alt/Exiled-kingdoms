package a0;

import com.badlogic.gdx.utils.j0;

/* JADX INFO: compiled from: EarClippingTriangulator.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private short[] f39b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float[] f40c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f41d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j0 f38a = new j0();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.badlogic.gdx.utils.o f42e = new com.badlogic.gdx.utils.o();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final j0 f43f = new j0();

    private int a(int i2) {
        short[] sArr = this.f39b;
        int i3 = sArr[(i2 == 0 ? this.f41d : i2) - 1] * 2;
        int i4 = sArr[i2] * 2;
        int i5 = sArr[(i2 + 1) % this.f41d] * 2;
        float[] fArr = this.f40c;
        return b(fArr[i3], fArr[i3 + 1], fArr[i4], fArr[i4 + 1], fArr[i5], fArr[i5 + 1]);
    }

    private static int b(float f2, float f3, float f4, float f5, float f6, float f7) {
        return (int) Math.signum(((f5 - f3) * f6) + a.a.C(f3, f7, f4, (f7 - f5) * f2));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final j0 c(float[] fArr) {
        int length = fArr.length;
        this.f40c = fArr;
        int i2 = length / 2;
        this.f41d = i2;
        j0 j0Var = this.f38a;
        int i3 = 0;
        j0Var.f1827b = 0;
        j0Var.b(i2);
        j0Var.f1827b = i2;
        short[] sArr = j0Var.f1826a;
        this.f39b = sArr;
        int i4 = g.f51a;
        if (length <= 2) {
            int i5 = i2 - 1;
            for (int i6 = 0; i6 < i2; i6++) {
                sArr[i6] = (short) (i5 - i6);
            }
        } else {
            int i7 = length - 2;
            float f2 = fArr[i7];
            float f3 = fArr[length - 1];
            int i8 = 0;
            float f4 = 0.0f;
            while (i8 <= i7) {
                float f5 = fArr[i8];
                float f6 = fArr[i8 + 1];
                f4 += (f2 * f6) - (f3 * f5);
                i8 += 2;
                f2 = f5;
                f3 = f6;
            }
            if (f4 < 0.0f) {
                for (short s = 0; s < i2; s = (short) (s + 1)) {
                    sArr[s] = s;
                }
            }
        }
        com.badlogic.gdx.utils.o oVar = this.f42e;
        oVar.f1850b = 0;
        oVar.c(i2);
        for (int i9 = 0; i9 < i2; i9++) {
            oVar.a(a(i9));
        }
        j0 j0Var2 = this.f43f;
        j0Var2.f1827b = 0;
        j0Var2.b(Math.max(0, i2 - 2) * 3);
        int[] iArr = oVar.f1849a;
        while (true) {
            int i10 = this.f41d;
            if (i10 <= 3) {
                if (i10 == 3) {
                    short[] sArr2 = this.f39b;
                    j0Var2.a(sArr2[0]);
                    j0Var2.a(sArr2[1]);
                    j0Var2.a(sArr2[2]);
                }
                return j0Var2;
            }
            int i11 = i3;
            while (true) {
                if (i11 >= i10) {
                    int[] iArr2 = oVar.f1849a;
                    i11 = 0;
                    while (true) {
                        if (i11 >= i10) {
                            i11 = 0;
                            break;
                        }
                        if (iArr2[i11] != -1) {
                            break;
                        }
                        i11++;
                    }
                } else {
                    int[] iArr3 = oVar.f1849a;
                    if (iArr3[i11] != -1) {
                        int i12 = (i11 == 0 ? this.f41d : i11) - 1;
                        int i13 = this.f41d;
                        int i14 = (i11 + 1) % i13;
                        short[] sArr3 = this.f39b;
                        int i15 = sArr3[i12] * 2;
                        int i16 = sArr3[i11] * 2;
                        int i17 = sArr3[i14] * 2;
                        float[] fArr2 = this.f40c;
                        float f7 = fArr2[i15];
                        float f8 = fArr2[i15 + 1];
                        float f9 = fArr2[i16];
                        float f10 = fArr2[i16 + 1];
                        float f11 = fArr2[i17];
                        float f12 = fArr2[i17 + 1];
                        int i18 = i14 + 1;
                        while (true) {
                            int i19 = i18 % i13;
                            if (i19 == i12) {
                                break;
                            }
                            if (iArr3[i19] != 1) {
                                int i20 = sArr3[i19] * 2;
                                float f13 = fArr2[i20];
                                float f14 = fArr2[i20 + 1];
                                if (b(f11, f12, f7, f8, f13, f14) < 0 || b(f7, f8, f9, f10, f13, f14) < 0 || b(f9, f10, f11, f12, f13, f14) < 0) {
                                }
                            }
                            i18 = i19 + 1;
                            i13 = this.f41d;
                        }
                    }
                    i11++;
                }
            }
            short[] sArr4 = this.f39b;
            j0Var2.a(sArr4[(i11 == 0 ? this.f41d : i11) - 1]);
            j0Var2.a(sArr4[i11]);
            int i21 = i11 + 1;
            j0Var2.a(sArr4[i21 % this.f41d]);
            int i22 = j0Var.f1827b;
            if (i11 >= i22) {
                StringBuilder sbR = a.a.r(i11, "index can't be >= size: ", " >= ");
                sbR.append(j0Var.f1827b);
                throw new IndexOutOfBoundsException(sbR.toString());
            }
            short[] sArr5 = j0Var.f1826a;
            short s2 = sArr5[i11];
            int i23 = i22 - 1;
            j0Var.f1827b = i23;
            System.arraycopy(sArr5, i21, sArr5, i11, i23 - i11);
            int i24 = oVar.f1850b;
            if (i11 >= i24) {
                StringBuilder sbR2 = a.a.r(i11, "index can't be >= size: ", " >= ");
                sbR2.append(oVar.f1850b);
                throw new IndexOutOfBoundsException(sbR2.toString());
            }
            int[] iArr4 = oVar.f1849a;
            int i25 = iArr4[i11];
            int i26 = i24 - 1;
            oVar.f1850b = i26;
            if (oVar.f1851c) {
                System.arraycopy(iArr4, i21, iArr4, i11, i26 - i11);
            } else {
                iArr4[i11] = iArr4[i26];
            }
            int i27 = this.f41d - 1;
            this.f41d = i27;
            int i28 = (i11 == 0 ? i27 : i11) - 1;
            if (i11 == i27) {
                i11 = 0;
            }
            iArr[i28] = a(i28);
            iArr[i11] = a(i11);
            i3 = 0;
        }
    }
}
