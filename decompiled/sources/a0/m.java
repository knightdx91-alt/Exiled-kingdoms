package a0;

/* JADX INFO: compiled from: Polygon.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float[] f75a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float[] f76b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f77c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f78d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f79e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private p f80f;

    public m() {
        this.f79e = true;
        this.f75a = new float[0];
    }

    public final p a() {
        float[] fArrB = b();
        float f2 = fArrB[0];
        float f3 = fArrB[1];
        int length = fArrB.length;
        float f4 = f3;
        float f5 = f2;
        float f6 = f4;
        for (int i2 = 2; i2 < length; i2 += 2) {
            float f7 = fArrB[i2];
            if (f2 > f7) {
                f2 = f7;
            }
            float f8 = fArrB[i2 + 1];
            if (f6 > f8) {
                f6 = f8;
            }
            if (f5 < f7) {
                f5 = f7;
            }
            if (f4 < f8) {
                f4 = f8;
            }
        }
        if (this.f80f == null) {
            this.f80f = new p();
        }
        p pVar = this.f80f;
        pVar.f89x = f2;
        pVar.f90y = f6;
        pVar.width = f5 - f2;
        pVar.height = f4 - f6;
        return pVar;
    }

    public final float[] b() {
        if (!this.f79e) {
            return this.f76b;
        }
        this.f79e = false;
        float[] fArr = this.f75a;
        float[] fArr2 = this.f76b;
        if (fArr2 == null || fArr2.length != fArr.length) {
            this.f76b = new float[fArr.length];
        }
        float[] fArr3 = this.f76b;
        float f2 = this.f77c;
        float f3 = this.f78d;
        j.c(0.0f);
        j.k(0.0f);
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2 += 2) {
            float f4 = fArr[i2] - 0.0f;
            int i3 = i2 + 1;
            float f5 = fArr[i3] - 0.0f;
            fArr3[i2] = f4 + f2 + 0.0f;
            fArr3[i3] = f5 + f3 + 0.0f;
        }
        return fArr3;
    }

    public final float[] c() {
        return this.f75a;
    }

    public final void d(float f2, float f3) {
        this.f77c = f2;
        this.f78d = f3;
        this.f79e = true;
    }

    public final void e(float[] fArr) {
        if (fArr.length < 6) {
            throw new IllegalArgumentException("polygons must contain at least 3 points.");
        }
        this.f75a = fArr;
        this.f79e = true;
    }

    public m(float[] fArr) {
        this.f79e = true;
        if (fArr.length >= 6) {
            this.f75a = fArr;
            return;
        }
        throw new IllegalArgumentException("polygons must contain at least 3 points.");
    }
}
