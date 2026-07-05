package com.badlogic.gdx.math;

import a0.k;
import a0.n;
import java.io.Serializable;

/* JADX INFO: compiled from: Vector3.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f1725d = new a(1.0f, 0.0f, 0.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f1726e = new a(0.0f, 1.0f, 0.0f);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f1727f = new a(0.0f, 0.0f, 1.0f);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Matrix4 f1728g;
    private static final long serialVersionUID = 3840054589595372522L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1729a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f1730b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1731c;

    static {
        new a(0.0f, 0.0f, 0.0f);
        f1728g = new Matrix4();
    }

    public a() {
    }

    public static float i(float f2, float f3, float f4) {
        return (float) Math.sqrt((f4 * f4) + (f3 * f3) + (f2 * f2));
    }

    public final void a(float f2, float f3, float f4) {
        t(this.f1729a + f2, this.f1730b + f3, this.f1731c + f4);
    }

    public final void b(a aVar) {
        a(aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public final void c(float f2, float f3, float f4) {
        float f5 = this.f1730b;
        float f6 = this.f1731c;
        float f7 = this.f1729a;
        t((f5 * f4) - (f6 * f3), (f6 * f2) - (f4 * f7), (f7 * f3) - (f5 * f2));
    }

    public final void d(a aVar) {
        float f2 = this.f1730b;
        float f3 = aVar.f1731c;
        float f4 = this.f1731c;
        float f5 = aVar.f1730b;
        float f6 = aVar.f1729a;
        float f7 = this.f1729a;
        t((f2 * f3) - (f4 * f5), (f4 * f6) - (f3 * f7), (f7 * f5) - (f2 * f6));
    }

    public final float e(a aVar) {
        float f2 = aVar.f1729a - this.f1729a;
        float f3 = aVar.f1730b - this.f1730b;
        float f4 = aVar.f1731c - this.f1731c;
        return (float) Math.sqrt((f4 * f4) + (f3 * f3) + (f2 * f2));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return Float.floatToIntBits(this.f1729a) == Float.floatToIntBits(aVar.f1729a) && Float.floatToIntBits(this.f1730b) == Float.floatToIntBits(aVar.f1730b) && Float.floatToIntBits(this.f1731c) == Float.floatToIntBits(aVar.f1731c);
    }

    public final float f(a aVar) {
        float f2 = aVar.f1729a - this.f1729a;
        float f3 = aVar.f1730b - this.f1730b;
        float f4 = aVar.f1731c - this.f1731c;
        return (f4 * f4) + (f3 * f3) + (f2 * f2);
    }

    public final boolean g() {
        return this.f1729a == 0.0f && this.f1730b == 0.0f && this.f1731c == 0.0f;
    }

    public final float h() {
        float f2 = this.f1729a;
        float f3 = this.f1730b;
        float f4 = (f3 * f3) + (f2 * f2);
        float f5 = this.f1731c;
        return (float) Math.sqrt((f5 * f5) + f4);
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.f1731c) + ((Float.floatToIntBits(this.f1730b) + ((Float.floatToIntBits(this.f1729a) + 31) * 31)) * 31);
    }

    public final float j() {
        float f2 = this.f1729a;
        float f3 = this.f1730b;
        float f4 = (f3 * f3) + (f2 * f2);
        float f5 = this.f1731c;
        return (f5 * f5) + f4;
    }

    public final void k(a aVar, float f2) {
        float f3 = this.f1729a;
        this.f1729a = a.a.C(aVar.f1729a, f3, f2, f3);
        float f4 = this.f1730b;
        this.f1730b = a.a.C(aVar.f1730b, f4, f2, f4);
        float f5 = this.f1731c;
        this.f1731c = a.a.C(aVar.f1731c, f5, f2, f5);
    }

    public final void l(k kVar) {
        float[] fArr = kVar.f71a;
        float f2 = this.f1729a;
        float f3 = fArr[0] * f2;
        float f4 = this.f1730b;
        float f5 = (fArr[3] * f4) + f3;
        float f6 = this.f1731c;
        t((fArr[6] * f6) + f5, (fArr[7] * f6) + (fArr[4] * f4) + (fArr[1] * f2), (f6 * fArr[8]) + (f4 * fArr[5]) + (f2 * fArr[2]));
    }

    public final void m(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        float f2 = this.f1729a;
        float f3 = fArr[0] * f2;
        float f4 = this.f1730b;
        float f5 = (fArr[4] * f4) + f3;
        float f6 = this.f1731c;
        t((fArr[8] * f6) + f5 + fArr[12], (fArr[9] * f6) + (fArr[5] * f4) + (fArr[1] * f2) + fArr[13], (f6 * fArr[10]) + (f4 * fArr[6]) + (f2 * fArr[2]) + fArr[14]);
    }

    public final void n() {
        float fJ = j();
        if (fJ == 0.0f || fJ == 1.0f) {
            return;
        }
        s(1.0f / ((float) Math.sqrt(fJ)));
    }

    public final void o(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        float f2 = this.f1729a;
        float f3 = fArr[3] * f2;
        float f4 = this.f1730b;
        float f5 = (fArr[7] * f4) + f3;
        float f6 = this.f1731c;
        float f7 = 1.0f / (((fArr[11] * f6) + f5) + fArr[15]);
        t(((fArr[8] * f6) + (fArr[4] * f4) + (fArr[0] * f2) + fArr[12]) * f7, ((fArr[9] * f6) + (fArr[5] * f4) + (fArr[1] * f2) + fArr[13]) * f7, ((f6 * fArr[10]) + (f4 * fArr[6]) + (f2 * fArr[2]) + fArr[14]) * f7);
    }

    public final void p(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        float f2 = this.f1729a;
        float f3 = fArr[0] * f2;
        float f4 = this.f1730b;
        float f5 = (fArr[4] * f4) + f3;
        float f6 = this.f1731c;
        t((fArr[8] * f6) + f5, (fArr[9] * f6) + (fArr[5] * f4) + (fArr[1] * f2), (f6 * fArr[10]) + (f4 * fArr[6]) + (f2 * fArr[2]));
    }

    public final void q(float f2, float f3, float f4, float f5) {
        Matrix4 matrix4 = f1728g;
        if (f2 == 0.0f) {
            matrix4.d();
        } else {
            matrix4.getClass();
            n nVar = Matrix4.f1718b;
            nVar.i(f3, f4, f5, f2);
            matrix4.n(nVar);
        }
        m(matrix4);
    }

    public final void r(a aVar, float f2) {
        Matrix4 matrix4 = f1728g;
        if (f2 == 0.0f) {
            matrix4.d();
        } else {
            matrix4.getClass();
            n nVar = Matrix4.f1718b;
            nVar.g(aVar, f2);
            matrix4.n(nVar);
        }
        m(matrix4);
    }

    public final void s(float f2) {
        t(this.f1729a * f2, this.f1730b * f2, this.f1731c * f2);
    }

    public final void t(float f2, float f3, float f4) {
        this.f1729a = f2;
        this.f1730b = f3;
        this.f1731c = f4;
    }

    public final String toString() {
        return "(" + this.f1729a + "," + this.f1730b + "," + this.f1731c + ")";
    }

    public final void u(a aVar) {
        t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public final void v(float f2, float f3, float f4) {
        t(this.f1729a - f2, this.f1730b - f3, this.f1731c - f4);
    }

    public final void w(a aVar) {
        v(aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public a(float f2, float f3, float f4) {
        t(f2, f3, f4);
    }

    public a(a aVar) {
        u(aVar);
    }
}
