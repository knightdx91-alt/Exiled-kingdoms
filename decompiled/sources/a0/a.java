package a0;

import com.badlogic.gdx.math.Matrix4;
import java.io.Serializable;

/* JADX INFO: compiled from: Affine2.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a implements Serializable {
    private static final long serialVersionUID = 1524569123485049187L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f25a = 1.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f26b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f27c = 0.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f28d = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f29e = 1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f30f = 0.0f;

    public final void a() {
        float f2 = this.f25a;
        float f3 = this.f29e;
        float f4 = this.f26b;
        float f5 = this.f28d;
        float f6 = (f2 * f3) - (f4 * f5);
        if (f6 == 0.0f) {
            throw new com.badlogic.gdx.utils.m("Can't invert a singular affine matrix");
        }
        float f7 = 1.0f / f6;
        float f8 = -f4;
        float f9 = this.f30f;
        float f10 = this.f27c;
        float f11 = -f5;
        this.f25a = f3 * f7;
        this.f26b = f8 * f7;
        this.f27c = ((f4 * f9) - (f3 * f10)) * f7;
        this.f28d = f11 * f7;
        this.f29e = f2 * f7;
        this.f30f = f7 * ((f5 * f10) - (f9 * f2));
    }

    public final void b(a aVar) {
        float f2 = this.f25a;
        float f3 = aVar.f25a;
        float f4 = this.f26b;
        float f5 = aVar.f28d;
        float f6 = (f4 * f5) + (f2 * f3);
        float f7 = aVar.f26b;
        float f8 = aVar.f29e;
        float f9 = (f4 * f8) + (f2 * f7);
        float f10 = aVar.f27c;
        float f11 = aVar.f30f;
        float f12 = (f4 * f11) + (f2 * f10) + this.f27c;
        float f13 = this.f28d;
        float f14 = this.f29e;
        float f15 = (f5 * f14) + (f3 * f13);
        float f16 = (f8 * f14) + (f7 * f13);
        float f17 = (f14 * f11) + (f13 * f10) + this.f30f;
        this.f25a = f6;
        this.f26b = f9;
        this.f27c = f12;
        this.f28d = f15;
        this.f29e = f16;
        this.f30f = f17;
    }

    public final void c(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        this.f25a = fArr[0];
        this.f26b = fArr[4];
        this.f27c = fArr[12];
        this.f28d = fArr[1];
        this.f29e = fArr[5];
        this.f30f = fArr[13];
    }

    public final String toString() {
        return "[" + this.f25a + "|" + this.f26b + "|" + this.f27c + "]\n[" + this.f28d + "|" + this.f29e + "|" + this.f30f + "]\n[0.0|0.0|0.1]";
    }
}
