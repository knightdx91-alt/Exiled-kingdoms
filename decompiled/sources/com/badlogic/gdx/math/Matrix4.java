package com.badlogic.gdx.math;

import a0.j;
import a0.n;
import java.io.Serializable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Matrix4 implements Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final n f1718b = new n();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final a f1719c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final a f1720d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final a f1721e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final a f1722f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final Matrix4 f1723g;
    private static final long serialVersionUID = -2717655254359579617L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f1724a;

    static {
        new n();
        f1719c = new a();
        f1720d = new a();
        f1721e = new a();
        f1722f = new a();
        f1723g = new Matrix4();
        new a();
        new a();
        new a();
    }

    public Matrix4() {
        float[] fArr = new float[16];
        this.f1724a = fArr;
        fArr[0] = 1.0f;
        fArr[5] = 1.0f;
        fArr[10] = 1.0f;
        fArr[15] = 1.0f;
    }

    public static void f(float[] fArr) {
        float f2 = fArr[3];
        float f3 = fArr[6];
        float f4 = f2 * f3;
        float f5 = fArr[9];
        float f6 = fArr[12];
        float f7 = fArr[2];
        float f8 = fArr[7];
        float f9 = f7 * f8;
        float fZ = a.a.z(f9, f5, f6, f4 * f5 * f6);
        float f10 = fArr[5];
        float f11 = f2 * f10;
        float f12 = fArr[10];
        float fZ2 = a.a.z(f11, f12, f6, fZ);
        float f13 = fArr[1];
        float f14 = f13 * f8;
        float fA = a.a.a(f14, f12, f6, fZ2);
        float f15 = f7 * f10;
        float f16 = fArr[11];
        float f17 = f15 * f16;
        float f18 = (f17 * f6) + fA;
        float f19 = f13 * f3;
        float f20 = f19 * f16;
        float f21 = fArr[8];
        float f22 = fArr[13];
        float fA2 = a.a.a(f9, f21, f22, (f18 - (f20 * f6)) - ((f4 * f21) * f22));
        float f23 = fArr[4];
        float f24 = f2 * f23;
        float fA3 = a.a.a(f24, f12, f22, fA2);
        float f25 = fArr[0];
        float f26 = f25 * f8;
        float f27 = f7 * f23;
        float f28 = f27 * f16;
        float f29 = f25 * f3;
        float f30 = f29 * f16;
        float fZ3 = (f30 * f22) + (a.a.z(f26, f12, f22, fA3) - (f28 * f22));
        float f31 = fArr[14];
        float fA4 = a.a.a(f26, f5, f31, a.a.z(f24, f5, f31, a.a.z(f14, f21, f31, (f11 * f21 * f31) + fZ3)));
        float f32 = f13 * f23;
        float f33 = f32 * f16;
        float f34 = (f33 * f31) + fA4;
        float f35 = f25 * f10;
        float f36 = f35 * f16;
        float f37 = fArr[15];
        float f38 = f32 * f12;
        float f39 = f35 * f12;
        float fZ4 = (f39 * f37) + (a.a.z(f29, f5, f37, a.a.a(f27, f5, f37, a.a.a(f19, f21, f37, (f34 - (f36 * f31)) - ((f15 * f21) * f37)))) - (f38 * f37));
        if (fZ4 == 0.0f) {
            return;
        }
        float f40 = f5 * f31;
        float f41 = f22 * f12;
        float f42 = f22 * f3;
        float f43 = (f42 * f16) + ((f40 * f8) - (f41 * f8));
        float f44 = f10 * f31;
        float f45 = f5 * f3;
        float f46 = f10 * f12;
        float f47 = (f46 * f37) + ((f43 - (f44 * f16)) - (f45 * f37));
        float f48 = f6 * f12;
        float f49 = f21 * f31;
        float f50 = f6 * f3;
        float f51 = f23 * f31;
        float f52 = (f51 * f16) + (((f48 * f8) - (f49 * f8)) - (f50 * f16));
        float f53 = f21 * f3;
        float f54 = (f53 * f37) + f52;
        float f55 = f23 * f12;
        float f56 = f54 - (f55 * f37);
        float f57 = f21 * f22;
        float f58 = f6 * f5;
        float f59 = f6 * f10;
        float f60 = (f59 * f16) + ((f57 * f8) - (f58 * f8));
        float f61 = f23 * f22;
        float f62 = f10 * f21;
        float f63 = f23 * f5;
        float f64 = (f63 * f37) + ((f60 - (f61 * f16)) - (f62 * f37));
        float f65 = ((f62 * f31) + ((f61 * f12) + (((f58 * f3) - (f57 * f3)) - (f59 * f12)))) - (f63 * f31);
        float f66 = (f41 * f2) - (f40 * f2);
        float f67 = f22 * f7;
        float f68 = f13 * f31;
        float f69 = (f68 * f16) + (f66 - (f67 * f16));
        float f70 = f5 * f7;
        float f71 = (f70 * f37) + f69;
        float f72 = f13 * f12;
        float f73 = f71 - (f72 * f37);
        float f74 = (f49 * f2) - (f48 * f2);
        float f75 = f6 * f7;
        float f76 = (f75 * f16) + f74;
        float f77 = f25 * f31;
        float f78 = f21 * f7;
        float f79 = f25 * f12;
        float f80 = (f79 * f37) + ((f76 - (f77 * f16)) - (f78 * f37));
        float f81 = f6 * f13;
        float f82 = f22 * f25;
        float f83 = f21 * f13;
        float f84 = f83 * f37;
        float f85 = f25 * f5;
        float f86 = (f84 + ((f16 * f82) + (((f58 * f2) - (f57 * f2)) - (f81 * f16)))) - (f85 * f37);
        float f87 = (f85 * f31) + ((((f81 * f12) + ((f57 * f7) - (f58 * f7))) - (f12 * f82)) - (f83 * f31));
        float f88 = f19 * f37;
        float f89 = f88 + ((((f67 * f8) + ((f44 * f2) - (f42 * f2))) - (f68 * f8)) - (f15 * f37));
        float f90 = f27 * f37;
        float f91 = (f90 + ((f77 * f8) + (((f50 * f2) - (f51 * f2)) - (f75 * f8)))) - (f29 * f37);
        float f92 = (f37 * f35) + ((((f81 * f8) + ((f61 * f2) - (f59 * f2))) - (f82 * f8)) - (f32 * f37));
        float f93 = f32 * f31;
        float f94 = (f93 + ((f82 * f3) + (((f59 * f7) - (f61 * f7)) - (f81 * f3)))) - (f35 * f31);
        float f95 = (((f72 * f8) + (((f45 * f2) - (f46 * f2)) - (f70 * f8))) + f17) - f20;
        float f96 = ((((f78 * f8) + ((f55 * f2) - (f53 * f2))) - (f79 * f8)) - f28) + f30;
        float f97 = (((f8 * f85) + (((f62 * f2) - (f63 * f2)) - (f83 * f8))) + f33) - f36;
        float f98 = 1.0f / fZ4;
        fArr[0] = f47 * f98;
        fArr[1] = f73 * f98;
        fArr[2] = f89 * f98;
        fArr[3] = f95 * f98;
        fArr[4] = f56 * f98;
        fArr[5] = f80 * f98;
        fArr[6] = f91 * f98;
        fArr[7] = f96 * f98;
        fArr[8] = f64 * f98;
        fArr[9] = f86 * f98;
        fArr[10] = f92 * f98;
        fArr[11] = f97 * f98;
        fArr[12] = f65 * f98;
        fArr[13] = f87 * f98;
        fArr[14] = f94 * f98;
        fArr[15] = (((((f83 * f3) + ((f63 * f7) - (f62 * f7))) - (f85 * f3)) - f38) + f39) * f98;
    }

    public static void h(float[] fArr, float[] fArr2) {
        float f2 = fArr[0];
        float f3 = fArr2[0];
        float f4 = fArr[4];
        float f5 = fArr2[1];
        float f6 = fArr[8];
        float f7 = fArr2[2];
        float f8 = f6 * f7;
        float f9 = fArr[12];
        float f10 = fArr2[3];
        float f11 = f9 * f10;
        float f12 = f11 + f8 + (f4 * f5) + (f2 * f3);
        float f13 = fArr2[4];
        float f14 = fArr2[5];
        float f15 = fArr2[6];
        float f16 = f6 * f15;
        float f17 = fArr2[7];
        float f18 = f9 * f17;
        float f19 = f18 + f16 + (f4 * f14) + (f2 * f13);
        float f20 = fArr2[8];
        float f21 = fArr2[9];
        float f22 = fArr2[10];
        float f23 = f6 * f22;
        float f24 = fArr2[11];
        float f25 = f9 * f24;
        float f26 = f25 + f23 + (f4 * f21) + (f2 * f20);
        float f27 = fArr2[12];
        float f28 = fArr2[13];
        float f29 = fArr2[14];
        float f30 = f6 * f29;
        float f31 = fArr2[15];
        float f32 = f9 * f31;
        float f33 = f32 + f30 + (f4 * f28) + (f2 * f27);
        float f34 = fArr[1];
        float f35 = fArr[5];
        float f36 = fArr[9];
        float f37 = (f36 * f7) + (f35 * f5) + (f34 * f3);
        float f38 = fArr[13];
        float f39 = (f38 * f10) + f37;
        float f40 = (f38 * f17) + (f36 * f15) + (f35 * f14) + (f34 * f13);
        float f41 = (f38 * f24) + (f36 * f22) + (f35 * f21) + (f34 * f20);
        float f42 = f36 * f29;
        float f43 = f38 * f31;
        float f44 = f43 + f42 + (f35 * f28) + (f34 * f27);
        float f45 = fArr[2];
        float f46 = fArr[6];
        float f47 = fArr[10];
        float f48 = (f47 * f7) + (f46 * f5) + (f45 * f3);
        float f49 = fArr[14];
        float f50 = (f49 * f10) + f48;
        float f51 = (f49 * f17) + (f47 * f15) + (f46 * f14) + (f45 * f13);
        float f52 = (f49 * f24) + (f47 * f22) + (f46 * f21) + (f45 * f20);
        float f53 = f47 * f29;
        float f54 = f49 * f31;
        float f55 = f54 + f53 + (f46 * f28) + (f45 * f27);
        float f56 = fArr[3];
        float f57 = fArr[7];
        float f58 = (f5 * f57) + (f3 * f56);
        float f59 = fArr[11];
        float f60 = (f7 * f59) + f58;
        float f61 = fArr[15];
        float f62 = (f10 * f61) + f60;
        float f63 = f15 * f59;
        float f64 = f17 * f61;
        float f65 = f64 + f63 + (f14 * f57) + (f13 * f56);
        float f66 = f22 * f59;
        float f67 = f24 * f61;
        float f68 = f67 + f66 + (f21 * f57) + (f20 * f56);
        float f69 = f59 * f29;
        float f70 = f61 * f31;
        fArr[0] = f12;
        fArr[1] = f39;
        fArr[2] = f50;
        fArr[3] = f62;
        fArr[4] = f19;
        fArr[5] = f40;
        fArr[6] = f51;
        fArr[7] = f65;
        fArr[8] = f26;
        fArr[9] = f41;
        fArr[10] = f52;
        fArr[11] = f68;
        fArr[12] = f33;
        fArr[13] = f44;
        fArr[14] = f55;
        fArr[15] = f70 + f69 + (f57 * f28) + (f56 * f27);
    }

    public static native void prj(float[] fArr, float[] fArr2, int i2, int i3, int i4);

    public final void a(n nVar) {
        nVar.getClass();
        float[] fArr = this.f1724a;
        nVar.h(true, fArr[0], fArr[4], fArr[8], fArr[1], fArr[5], fArr[9], fArr[2], fArr[6], fArr[10]);
    }

    public final void b(a aVar) {
        float fSqrt;
        float fSqrt2;
        float fSqrt3;
        float[] fArr = this.f1724a;
        if (j.f(fArr[4]) && j.f(fArr[8])) {
            fSqrt = Math.abs(fArr[0]);
        } else {
            float f2 = fArr[0];
            float f3 = fArr[4];
            float f4 = (f3 * f3) + (f2 * f2);
            float f5 = fArr[8];
            fSqrt = (float) Math.sqrt((f5 * f5) + f4);
        }
        if (j.f(fArr[1]) && j.f(fArr[9])) {
            fSqrt2 = Math.abs(fArr[5]);
        } else {
            float f6 = fArr[1];
            float f7 = fArr[5];
            float f8 = (f7 * f7) + (f6 * f6);
            float f9 = fArr[9];
            fSqrt2 = (float) Math.sqrt((f9 * f9) + f8);
        }
        if (j.f(fArr[2]) && j.f(fArr[6])) {
            fSqrt3 = Math.abs(fArr[10]);
        } else {
            float f10 = fArr[2];
            float f11 = fArr[6];
            float f12 = fArr[10];
            float f13 = f12 * f12;
            fSqrt3 = (float) Math.sqrt(f13 + (f11 * f11) + (f10 * f10));
        }
        aVar.t(fSqrt, fSqrt2, fSqrt3);
    }

    public final void c(a aVar) {
        float[] fArr = this.f1724a;
        aVar.f1729a = fArr[12];
        aVar.f1730b = fArr[13];
        aVar.f1731c = fArr[14];
    }

    public final void d() {
        float[] fArr = this.f1724a;
        fArr[0] = 1.0f;
        fArr[4] = 0.0f;
        fArr[8] = 0.0f;
        fArr[12] = 0.0f;
        fArr[1] = 0.0f;
        fArr[5] = 1.0f;
        fArr[9] = 0.0f;
        fArr[13] = 0.0f;
        fArr[2] = 0.0f;
        fArr[6] = 0.0f;
        fArr[10] = 1.0f;
        fArr[14] = 0.0f;
        fArr[3] = 0.0f;
        fArr[7] = 0.0f;
        fArr[11] = 0.0f;
        fArr[15] = 1.0f;
    }

    public final void e() {
        float[] fArr = this.f1724a;
        float f2 = fArr[3];
        float f3 = fArr[6];
        float f4 = f2 * f3;
        float f5 = fArr[9];
        float f6 = fArr[12];
        float f7 = fArr[2];
        float f8 = fArr[7];
        float f9 = f7 * f8;
        float fZ = a.a.z(f9, f5, f6, f4 * f5 * f6);
        float f10 = fArr[5];
        float f11 = f2 * f10;
        float f12 = fArr[10];
        float fZ2 = a.a.z(f11, f12, f6, fZ);
        float f13 = fArr[1];
        float f14 = f13 * f8;
        float fA = a.a.a(f14, f12, f6, fZ2);
        float f15 = f7 * f10;
        float f16 = fArr[11];
        float f17 = f15 * f16;
        float f18 = (f17 * f6) + fA;
        float f19 = f13 * f3;
        float f20 = f19 * f16;
        float f21 = fArr[8];
        float f22 = fArr[13];
        float fA2 = a.a.a(f9, f21, f22, (f18 - (f20 * f6)) - ((f4 * f21) * f22));
        float f23 = fArr[4];
        float f24 = f2 * f23;
        float fA3 = a.a.a(f24, f12, f22, fA2);
        float f25 = fArr[0];
        float f26 = f25 * f8;
        float f27 = f7 * f23;
        float f28 = f27 * f16;
        float f29 = f25 * f3;
        float f30 = f29 * f16;
        float fZ3 = (f30 * f22) + (a.a.z(f26, f12, f22, fA3) - (f28 * f22));
        float f31 = fArr[14];
        float fA4 = a.a.a(f26, f5, f31, a.a.z(f24, f5, f31, a.a.z(f14, f21, f31, (f11 * f21 * f31) + fZ3)));
        float f32 = f13 * f23;
        float f33 = f32 * f16;
        float f34 = (f33 * f31) + fA4;
        float f35 = f25 * f10;
        float f36 = f35 * f16;
        float f37 = fArr[15];
        float f38 = f32 * f12;
        float f39 = f35 * f12;
        float fZ4 = (f39 * f37) + (a.a.z(f29, f5, f37, a.a.a(f27, f5, f37, a.a.a(f19, f21, f37, (f34 - (f36 * f31)) - ((f15 * f21) * f37)))) - (f38 * f37));
        if (fZ4 == 0.0f) {
            throw new RuntimeException("non-invertible matrix");
        }
        float f40 = f5 * f31;
        float f41 = f22 * f12;
        float f42 = f22 * f3;
        float f43 = (f42 * f16) + ((f40 * f8) - (f41 * f8));
        float f44 = f10 * f31;
        float f45 = f5 * f3;
        float f46 = f10 * f12;
        float f47 = (f46 * f37) + ((f43 - (f44 * f16)) - (f45 * f37));
        float f48 = f6 * f12;
        float f49 = f21 * f31;
        float f50 = f6 * f3;
        float f51 = f23 * f31;
        float f52 = (f51 * f16) + (((f48 * f8) - (f49 * f8)) - (f50 * f16));
        float f53 = f21 * f3;
        float f54 = (f53 * f37) + f52;
        float f55 = f23 * f12;
        float f56 = f54 - (f55 * f37);
        float f57 = f21 * f22;
        float f58 = f6 * f5;
        float f59 = f6 * f10;
        float f60 = (f59 * f16) + ((f57 * f8) - (f58 * f8));
        float f61 = f23 * f22;
        float f62 = f10 * f21;
        float f63 = f23 * f5;
        float f64 = (f63 * f37) + ((f60 - (f61 * f16)) - (f62 * f37));
        float f65 = ((f62 * f31) + ((f61 * f12) + (((f58 * f3) - (f57 * f3)) - (f59 * f12)))) - (f63 * f31);
        float f66 = (f41 * f2) - (f40 * f2);
        float f67 = f22 * f7;
        float f68 = f13 * f31;
        float f69 = (f68 * f16) + (f66 - (f67 * f16));
        float f70 = f5 * f7;
        float f71 = (f70 * f37) + f69;
        float f72 = f13 * f12;
        float f73 = f71 - (f72 * f37);
        float f74 = (f49 * f2) - (f48 * f2);
        float f75 = f6 * f7;
        float f76 = (f75 * f16) + f74;
        float f77 = f25 * f31;
        float f78 = f21 * f7;
        float f79 = f25 * f12;
        float f80 = (f79 * f37) + ((f76 - (f77 * f16)) - (f78 * f37));
        float f81 = f6 * f13;
        float f82 = f22 * f25;
        float f83 = f21 * f13;
        float f84 = f83 * f37;
        float f85 = f25 * f5;
        float f86 = (f84 + ((f16 * f82) + (((f58 * f2) - (f57 * f2)) - (f81 * f16)))) - (f85 * f37);
        float f87 = (f85 * f31) + ((((f81 * f12) + ((f57 * f7) - (f58 * f7))) - (f12 * f82)) - (f83 * f31));
        float f88 = f19 * f37;
        float f89 = f88 + ((((f67 * f8) + ((f44 * f2) - (f42 * f2))) - (f68 * f8)) - (f15 * f37));
        float f90 = ((f27 * f37) + ((f77 * f8) + (((f50 * f2) - (f51 * f2)) - (f75 * f8)))) - (f29 * f37);
        float f91 = (f37 * f35) + ((((f81 * f8) + ((f61 * f2) - (f59 * f2))) - (f82 * f8)) - (f32 * f37));
        float f92 = f32 * f31;
        float f93 = (f92 + ((f82 * f3) + (((f59 * f7) - (f61 * f7)) - (f81 * f3)))) - (f35 * f31);
        float f94 = (((f72 * f8) + (((f45 * f2) - (f46 * f2)) - (f70 * f8))) + f17) - f20;
        float f95 = ((((f78 * f8) + ((f55 * f2) - (f53 * f2))) - (f79 * f8)) - f28) + f30;
        float f96 = (((f8 * f85) + (((f62 * f2) - (f63 * f2)) - (f83 * f8))) + f33) - f36;
        float f97 = 1.0f / fZ4;
        fArr[0] = f47 * f97;
        fArr[1] = f73 * f97;
        fArr[2] = f89 * f97;
        fArr[3] = f94 * f97;
        fArr[4] = f56 * f97;
        fArr[5] = f80 * f97;
        fArr[6] = f90 * f97;
        fArr[7] = f95 * f97;
        fArr[8] = f64 * f97;
        fArr[9] = f86 * f97;
        fArr[10] = f91 * f97;
        fArr[11] = f96 * f97;
        fArr[12] = f65 * f97;
        fArr[13] = f87 * f97;
        fArr[14] = f93 * f97;
        fArr[15] = (((((f83 * f3) + ((f63 * f7) - (f62 * f7))) - (f85 * f3)) - f38) + f39) * f97;
    }

    public final void g(Matrix4 matrix4) {
        h(this.f1724a, matrix4.f1724a);
    }

    public final void i(float f2, float f3, float f4, float f5) {
        if (f5 == 0.0f) {
            return;
        }
        n nVar = f1718b;
        nVar.i(f2, f3, f4, f5);
        j(nVar);
    }

    public final void j(n nVar) {
        float f2 = nVar.f83a;
        float f3 = nVar.f84b;
        float f4 = nVar.f85c;
        float f5 = nVar.f86d;
        float f6 = f2 * f2;
        float f7 = f2 * f3;
        float f8 = f2 * f4;
        float f9 = f2 * f5;
        float f10 = f3 * f3;
        float f11 = f3 * f4;
        float f12 = f3 * f5;
        float f13 = f4 * f4;
        float f14 = f4 * f5;
        float f15 = 1.0f - ((f10 + f13) * 2.0f);
        float f16 = (f7 - f14) * 2.0f;
        float f17 = (f8 + f12) * 2.0f;
        float f18 = (f7 + f14) * 2.0f;
        float f19 = 1.0f - ((f13 + f6) * 2.0f);
        float f20 = (f11 - f9) * 2.0f;
        float f21 = (f8 - f12) * 2.0f;
        float f22 = (f11 + f9) * 2.0f;
        float f23 = 1.0f - ((f6 + f10) * 2.0f);
        float[] fArr = this.f1724a;
        float f24 = fArr[0];
        float f25 = fArr[4];
        float f26 = fArr[8];
        float f27 = f26 * f21;
        float f28 = f27 + (f25 * f18) + (f24 * f15);
        float f29 = (f26 * f22) + (f25 * f19) + (f24 * f16);
        float f30 = f26 * f23;
        float f31 = f30 + (f25 * f20) + (f24 * f17);
        float f32 = fArr[1];
        float f33 = fArr[5];
        float f34 = fArr[9];
        float f35 = f34 * f21;
        float f36 = f35 + (f33 * f18) + (f32 * f15);
        float f37 = (f34 * f22) + (f33 * f19) + (f32 * f16);
        float f38 = f34 * f23;
        float f39 = f38 + (f33 * f20) + (f32 * f17);
        float f40 = fArr[2];
        float f41 = fArr[6];
        float f42 = fArr[10];
        float f43 = f42 * f21;
        float f44 = f43 + (f41 * f18) + (f40 * f15);
        float f45 = (f42 * f22) + (f41 * f19) + (f40 * f16);
        float f46 = f42 * f23;
        float f47 = f46 + (f41 * f20) + (f40 * f17);
        float f48 = fArr[3];
        float f49 = fArr[7];
        float f50 = fArr[11];
        float f51 = f21 * f50;
        float f52 = f51 + (f18 * f49) + (f15 * f48);
        float f53 = f22 * f50;
        float f54 = f53 + (f19 * f49) + (f16 * f48);
        float f55 = f50 * f23;
        fArr[0] = f28;
        fArr[1] = f36;
        fArr[2] = f44;
        fArr[3] = f52;
        fArr[4] = f29;
        fArr[5] = f37;
        fArr[6] = f45;
        fArr[7] = f54;
        fArr[8] = f31;
        fArr[9] = f39;
        fArr[10] = f47;
        fArr[11] = f55 + (f49 * f20) + (f48 * f17);
    }

    public final void k(a aVar, float f2) {
        if (f2 == 0.0f) {
            return;
        }
        n nVar = f1718b;
        nVar.g(aVar, f2);
        j(nVar);
    }

    public final void l(float f2, float f3, float f4) {
        float[] fArr = this.f1724a;
        fArr[0] = fArr[0] * f2;
        fArr[4] = fArr[4] * f3;
        fArr[8] = fArr[8] * f4;
        fArr[1] = fArr[1] * f2;
        fArr[5] = fArr[5] * f3;
        fArr[9] = fArr[9] * f4;
        fArr[2] = fArr[2] * f2;
        fArr[6] = fArr[6] * f3;
        fArr[10] = fArr[10] * f4;
        fArr[3] = fArr[3] * f2;
        fArr[7] = fArr[7] * f3;
        fArr[11] = fArr[11] * f4;
    }

    public final void m(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        float f12 = f5 * 2.0f;
        float f13 = f6 * 2.0f;
        float f14 = 2.0f * f7;
        float f15 = f8 * f12;
        float f16 = f8 * f13;
        float f17 = f8 * f14;
        float f18 = f12 * f5;
        float f19 = f5 * f13;
        float f20 = f5 * f14;
        float f21 = f13 * f6;
        float f22 = f6 * f14;
        float f23 = f14 * f7;
        float[] fArr = this.f1724a;
        fArr[0] = (1.0f - (f21 + f23)) * f9;
        fArr[4] = (f19 - f17) * f10;
        fArr[8] = (f20 + f16) * f11;
        fArr[12] = f2;
        fArr[1] = (f19 + f17) * f9;
        fArr[5] = (1.0f - (f23 + f18)) * f10;
        fArr[9] = (f22 - f15) * f11;
        fArr[13] = f3;
        fArr[2] = (f20 - f16) * f9;
        fArr[6] = (f22 + f15) * f10;
        fArr[10] = (1.0f - (f18 + f21)) * f11;
        fArr[14] = f4;
        fArr[3] = 0.0f;
        fArr[7] = 0.0f;
        fArr[11] = 0.0f;
        fArr[15] = 1.0f;
    }

    public final void n(n nVar) {
        float f2 = nVar.f83a;
        float f3 = nVar.f84b;
        float f4 = nVar.f85c;
        float f5 = nVar.f86d;
        float f6 = f2 * 2.0f;
        float f7 = f3 * 2.0f;
        float f8 = 2.0f * f4;
        float f9 = f5 * f6;
        float f10 = f5 * f7;
        float f11 = f5 * f8;
        float f12 = f6 * f2;
        float f13 = f2 * f7;
        float f14 = f2 * f8;
        float f15 = f7 * f3;
        float f16 = f3 * f8;
        float f17 = f4 * f8;
        float[] fArr = this.f1724a;
        fArr[0] = 1.0f - (f15 + f17);
        fArr[4] = f13 - f11;
        fArr[8] = f14 + f10;
        fArr[12] = 0.0f;
        fArr[1] = f13 + f11;
        fArr[5] = 1.0f - (f17 + f12);
        fArr[9] = f16 - f9;
        fArr[13] = 0.0f;
        fArr[2] = f14 - f10;
        fArr[6] = f16 + f9;
        fArr[10] = 1.0f - (f12 + f15);
        fArr[14] = 0.0f;
        fArr[3] = 0.0f;
        fArr[7] = 0.0f;
        fArr[11] = 0.0f;
        fArr[15] = 1.0f;
    }

    public final void o(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        float[] fArr2 = this.f1724a;
        System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
    }

    public final void p(a0.a aVar) {
        float f2 = aVar.f25a;
        float[] fArr = this.f1724a;
        fArr[0] = f2;
        fArr[1] = aVar.f28d;
        fArr[4] = aVar.f26b;
        fArr[5] = aVar.f29e;
        fArr[12] = aVar.f27c;
        fArr[13] = aVar.f30f;
    }

    public final void q(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        float f2 = fArr[0];
        float[] fArr2 = this.f1724a;
        fArr2[0] = f2;
        fArr2[1] = fArr[1];
        fArr2[4] = fArr[4];
        fArr2[5] = fArr[5];
        fArr2[12] = fArr[12];
        fArr2[13] = fArr[13];
    }

    public final void r(a aVar, a aVar2, a aVar3) {
        a aVar4 = f1722f;
        aVar4.u(aVar2);
        aVar4.w(aVar);
        a aVar5 = f1719c;
        aVar5.u(aVar4);
        aVar5.n();
        a aVar6 = f1720d;
        aVar6.getClass();
        aVar6.t(aVar4.f1729a, aVar4.f1730b, aVar4.f1731c);
        aVar6.d(aVar3);
        aVar6.n();
        a aVar7 = f1721e;
        aVar7.u(aVar6);
        aVar7.d(aVar5);
        aVar7.n();
        d();
        float f2 = aVar6.f1729a;
        float[] fArr = this.f1724a;
        fArr[0] = f2;
        fArr[4] = aVar6.f1730b;
        fArr[8] = aVar6.f1731c;
        fArr[1] = aVar7.f1729a;
        fArr[5] = aVar7.f1730b;
        fArr[9] = aVar7.f1731c;
        fArr[2] = -aVar5.f1729a;
        fArr[6] = -aVar5.f1730b;
        fArr[10] = -aVar5.f1731c;
        Matrix4 matrix4 = f1723g;
        matrix4.t(-aVar.f1729a, -aVar.f1730b, -aVar.f1731c);
        h(fArr, matrix4.f1724a);
    }

    public final void s(float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f3 - f2;
        float f9 = f5 - f4;
        float f10 = f7 - f6;
        float f11 = (-(f3 + f2)) / f8;
        float f12 = (-(f5 + f4)) / f9;
        float[] fArr = this.f1724a;
        fArr[0] = 2.0f / f8;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 0.0f;
        fArr[5] = 2.0f / f9;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = (-2.0f) / f10;
        fArr[11] = 0.0f;
        fArr[12] = f11;
        fArr[13] = f12;
        fArr[14] = (-(f7 + f6)) / f10;
        fArr[15] = 1.0f;
    }

    public final void t(float f2, float f3, float f4) {
        d();
        float[] fArr = this.f1724a;
        fArr[12] = f2;
        fArr[13] = f3;
        fArr[14] = f4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        float[] fArr = this.f1724a;
        sb.append(fArr[0]);
        sb.append("|");
        sb.append(fArr[4]);
        sb.append("|");
        sb.append(fArr[8]);
        sb.append("|");
        sb.append(fArr[12]);
        sb.append("]\n[");
        sb.append(fArr[1]);
        sb.append("|");
        sb.append(fArr[5]);
        sb.append("|");
        sb.append(fArr[9]);
        sb.append("|");
        sb.append(fArr[13]);
        sb.append("]\n[");
        sb.append(fArr[2]);
        sb.append("|");
        sb.append(fArr[6]);
        sb.append("|");
        sb.append(fArr[10]);
        sb.append("|");
        sb.append(fArr[14]);
        sb.append("]\n[");
        sb.append(fArr[3]);
        sb.append("|");
        sb.append(fArr[7]);
        sb.append("|");
        sb.append(fArr[11]);
        sb.append("|");
        sb.append(fArr[15]);
        sb.append("]\n");
        return sb.toString();
    }

    public final void u(a aVar) {
        float f2 = aVar.f1729a;
        float[] fArr = this.f1724a;
        fArr[12] = f2;
        fArr[13] = aVar.f1730b;
        fArr[14] = aVar.f1731c;
    }

    public final void v(float f2, float f3, float f4) {
        float[] fArr = this.f1724a;
        float f5 = fArr[12];
        fArr[12] = a.a.B(fArr[8], f4, (fArr[4] * f3) + (fArr[0] * f2), f5);
        float f6 = fArr[13];
        fArr[13] = a.a.B(fArr[9], f4, (fArr[5] * f3) + (fArr[1] * f2), f6);
        float f7 = fArr[14];
        fArr[14] = a.a.B(fArr[10], f4, (fArr[6] * f3) + (fArr[2] * f2), f7);
        float f8 = fArr[15];
        fArr[15] = a.a.B(fArr[11], f4, (fArr[7] * f3) + (fArr[3] * f2), f8);
    }

    public Matrix4(Matrix4 matrix4) {
        this.f1724a = new float[16];
        o(matrix4);
    }
}
