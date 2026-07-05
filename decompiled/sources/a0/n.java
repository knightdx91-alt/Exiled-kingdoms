package a0;

import java.io.Serializable;

/* JADX INFO: compiled from: Quaternion.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class n implements Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static n f81e = new n(0.0f, 0.0f, 0.0f, 0.0f);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static n f82f = new n(0.0f, 0.0f, 0.0f, 0.0f);
    private static final long serialVersionUID = -7661875440774897168L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f83a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f84b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f85c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f86d;

    public n(float f2, float f3, float f4, float f5) {
        e(f2, f3, f4, f5);
    }

    public final void a() {
        this.f83a = -this.f83a;
        this.f84b = -this.f84b;
        this.f85c = -this.f85c;
    }

    public final void b(n nVar) {
        float f2 = this.f86d;
        float f3 = nVar.f83a;
        float f4 = this.f83a;
        float f5 = nVar.f86d;
        float f6 = this.f84b;
        float f7 = nVar.f85c;
        float f8 = (f6 * f7) + (f4 * f5) + (f2 * f3);
        float f9 = this.f85c;
        float f10 = nVar.f84b;
        float f11 = ((f9 * f3) + ((f6 * f5) + (f2 * f10))) - (f4 * f7);
        this.f83a = f8 - (f9 * f10);
        this.f84b = f11;
        this.f85c = ((f4 * f10) + ((f9 * f5) + (f2 * f7))) - (f6 * f3);
        this.f86d = (((f2 * f5) - (f4 * f3)) - (f6 * f10)) - (f9 * f7);
    }

    public final void c(n nVar) {
        float f2 = nVar.f86d;
        float f3 = this.f83a;
        float f4 = nVar.f83a;
        float f5 = this.f86d;
        float f6 = nVar.f84b;
        float f7 = this.f85c;
        float f8 = (f6 * f7) + (f4 * f5) + (f2 * f3);
        float f9 = nVar.f85c;
        float f10 = this.f84b;
        float f11 = ((f9 * f3) + ((f6 * f5) + (f2 * f10))) - (f4 * f7);
        this.f83a = f8 - (f9 * f10);
        this.f84b = f11;
        this.f85c = ((f4 * f10) + ((f9 * f5) + (f2 * f7))) - (f6 * f3);
        this.f86d = (((f2 * f5) - (f4 * f3)) - (f6 * f10)) - (f9 * f7);
    }

    public final void d() {
        float f2 = this.f83a;
        float f3 = this.f84b;
        float f4 = (f3 * f3) + (f2 * f2);
        float f5 = this.f85c;
        float f6 = (f5 * f5) + f4;
        float f7 = this.f86d;
        float f8 = (f7 * f7) + f6;
        if (f8 == 0.0f || j.d(f8, 1.0f)) {
            return;
        }
        float fSqrt = (float) Math.sqrt(f8);
        this.f86d /= fSqrt;
        this.f83a /= fSqrt;
        this.f84b /= fSqrt;
        this.f85c /= fSqrt;
    }

    public final void e(float f2, float f3, float f4, float f5) {
        this.f83a = f2;
        this.f84b = f3;
        this.f85c = f4;
        this.f86d = f5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return Float.floatToRawIntBits(this.f86d) == Float.floatToRawIntBits(nVar.f86d) && Float.floatToRawIntBits(this.f83a) == Float.floatToRawIntBits(nVar.f83a) && Float.floatToRawIntBits(this.f84b) == Float.floatToRawIntBits(nVar.f84b) && Float.floatToRawIntBits(this.f85c) == Float.floatToRawIntBits(nVar.f85c);
    }

    public final void f(n nVar) {
        e(nVar.f83a, nVar.f84b, nVar.f85c, nVar.f86d);
    }

    public final void g(com.badlogic.gdx.math.a aVar, float f2) {
        i(aVar.f1729a, aVar.f1730b, aVar.f1731c, f2);
    }

    public final void h(boolean z2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        if (z2) {
            float fI = 1.0f / com.badlogic.gdx.math.a.i(f2, f3, f4);
            float fI2 = 1.0f / com.badlogic.gdx.math.a.i(f5, f6, f7);
            float fI3 = 1.0f / com.badlogic.gdx.math.a.i(f8, f9, f10);
            f2 *= fI;
            f3 *= fI;
            f4 *= fI;
            f5 *= fI2;
            f6 *= fI2;
            f7 *= fI2;
            f8 *= fI3;
            f9 *= fI3;
            f10 *= fI3;
        }
        if (f2 + f6 + f10 >= 0.0f) {
            float fSqrt = (float) Math.sqrt(r6 + 1.0f);
            this.f86d = fSqrt * 0.5f;
            float f11 = 0.5f / fSqrt;
            this.f83a = (f9 - f7) * f11;
            this.f84b = (f4 - f8) * f11;
            this.f85c = (f5 - f3) * f11;
            return;
        }
        if (f2 > f6 && f2 > f10) {
            float fSqrt2 = (float) Math.sqrt(((((double) f2) + 1.0d) - ((double) f6)) - ((double) f10));
            this.f83a = fSqrt2 * 0.5f;
            float f12 = 0.5f / fSqrt2;
            this.f84b = (f5 + f3) * f12;
            this.f85c = (f4 + f8) * f12;
            this.f86d = (f9 - f7) * f12;
            return;
        }
        if (f6 > f10) {
            float fSqrt3 = (float) Math.sqrt(((((double) f6) + 1.0d) - ((double) f2)) - ((double) f10));
            this.f84b = fSqrt3 * 0.5f;
            float f13 = 0.5f / fSqrt3;
            this.f83a = (f5 + f3) * f13;
            this.f85c = (f9 + f7) * f13;
            this.f86d = (f4 - f8) * f13;
            return;
        }
        float fSqrt4 = (float) Math.sqrt(((((double) f10) + 1.0d) - ((double) f2)) - ((double) f6));
        this.f85c = fSqrt4 * 0.5f;
        float f14 = 0.5f / fSqrt4;
        this.f83a = (f4 + f8) * f14;
        this.f84b = (f9 + f7) * f14;
        this.f86d = (f5 - f3) * f14;
    }

    public final int hashCode() {
        return Float.floatToRawIntBits(this.f85c) + ((Float.floatToRawIntBits(this.f84b) + ((Float.floatToRawIntBits(this.f83a) + ((Float.floatToRawIntBits(this.f86d) + 31) * 31)) * 31)) * 31);
    }

    public final void i(float f2, float f3, float f4, float f5) {
        float f6 = f5 * 0.017453292f;
        float fI = com.badlogic.gdx.math.a.i(f2, f3, f4);
        if (fI == 0.0f) {
            e(0.0f, 0.0f, 0.0f, 1.0f);
            return;
        }
        float f7 = 1.0f / fI;
        double d2 = (f6 < 0.0f ? 6.2831855f - ((-f6) % 6.2831855f) : f6 % 6.2831855f) / 2.0f;
        float fSin = (float) Math.sin(d2);
        e(f2 * f7 * fSin, f3 * f7 * fSin, f7 * f4 * fSin, (float) Math.cos(d2));
        d();
    }

    public final void j(n nVar, float f2) {
        float f3 = (this.f86d * nVar.f86d) + (this.f85c * nVar.f85c) + (this.f84b * nVar.f84b) + (this.f83a * nVar.f83a);
        if (f3 < 0.0f) {
            f3 = -f3;
        }
        float fSin = 1.0f - f2;
        if (1.0f - f3 > 0.1d) {
            float fSin2 = 1.0f / ((float) Math.sin((float) Math.acos(f3)));
            fSin = ((float) Math.sin(fSin * r1)) * fSin2;
            f2 = ((float) Math.sin(f2 * r1)) * fSin2;
        }
        if (f3 < 0.0f) {
            f2 = -f2;
        }
        this.f83a = (nVar.f83a * f2) + (this.f83a * fSin);
        this.f84b = (nVar.f84b * f2) + (this.f84b * fSin);
        this.f85c = (nVar.f85c * f2) + (this.f85c * fSin);
        this.f86d = (f2 * nVar.f86d) + (fSin * this.f86d);
    }

    public final void k(com.badlogic.gdx.math.a aVar) {
        n nVar = f82f;
        nVar.f(this);
        nVar.a();
        float f2 = aVar.f1729a;
        float f3 = aVar.f1730b;
        float f4 = aVar.f1731c;
        n nVar2 = f81e;
        nVar2.e(f2, f3, f4, 0.0f);
        nVar.c(nVar2);
        nVar.c(this);
        aVar.f1729a = nVar.f83a;
        aVar.f1730b = nVar.f84b;
        aVar.f1731c = nVar.f85c;
    }

    public final String toString() {
        return "[" + this.f83a + "|" + this.f84b + "|" + this.f85c + "|" + this.f86d + "]";
    }

    public n() {
        e(0.0f, 0.0f, 0.0f, 1.0f);
    }
}
