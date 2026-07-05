package a0;

import com.badlogic.gdx.math.Matrix4;
import java.io.Serializable;

/* JADX INFO: compiled from: Matrix3.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class k implements Serializable {
    private static final long serialVersionUID = 7907569533774959788L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f71a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float[] f72b = new float[9];

    public k() {
        a();
    }

    public final void a() {
        float[] fArr = this.f71a;
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 1.0f;
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    public final void b() {
        float[] fArr = this.f71a;
        float f2 = fArr[0];
        float f3 = fArr[4];
        float f4 = fArr[8];
        float f5 = fArr[3];
        float f6 = fArr[7];
        float f7 = fArr[2];
        float f8 = fArr[6];
        float f9 = fArr[1];
        float f10 = fArr[5];
        float fZ = a.a.z(f8, f3, f7, a.a.z(f5, f9, f4, a.a.z(f2, f6, f10, (f8 * f9 * f10) + (f5 * f6 * f7) + (f2 * f3 * f4))));
        if (fZ == 0.0f) {
            throw new com.badlogic.gdx.utils.m("Can't invert a singular matrix");
        }
        float f11 = 1.0f / fZ;
        float f12 = (f3 * f4) - (f10 * f6);
        float[] fArr2 = this.f72b;
        fArr2[0] = f12;
        float f13 = (f7 * f6) - (f9 * f4);
        fArr2[1] = f13;
        float f14 = fArr[1];
        float f15 = (f14 * f10) - (f7 * f3);
        fArr2[2] = f15;
        float f16 = (f10 * f8) - (f5 * f4);
        fArr2[3] = f16;
        float f17 = fArr[0];
        float f18 = fArr[2];
        float f19 = (f4 * f17) - (f18 * f8);
        fArr2[4] = f19;
        float f20 = fArr[3];
        float f21 = (f18 * f20) - (f10 * f17);
        fArr2[5] = f21;
        float f22 = fArr[4];
        float f23 = (f20 * f6) - (f8 * f22);
        fArr2[6] = f23;
        float f24 = (fArr[6] * f14) - (f6 * f17);
        fArr2[7] = f24;
        float f25 = (f17 * f22) - (f14 * f20);
        fArr2[8] = f25;
        fArr[0] = f12 * f11;
        fArr[1] = f13 * f11;
        fArr[2] = f15 * f11;
        fArr[3] = f16 * f11;
        fArr[4] = f19 * f11;
        fArr[5] = f21 * f11;
        fArr[6] = f23 * f11;
        fArr[7] = f24 * f11;
        fArr[8] = f11 * f25;
    }

    public final void c(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        float f2 = fArr[0];
        float[] fArr2 = this.f71a;
        fArr2[0] = f2;
        fArr2[1] = fArr[1];
        fArr2[2] = fArr[2];
        fArr2[3] = fArr[4];
        fArr2[4] = fArr[5];
        fArr2[5] = fArr[6];
        fArr2[6] = fArr[8];
        fArr2[7] = fArr[9];
        fArr2[8] = fArr[10];
    }

    public final void d(com.badlogic.gdx.math.a aVar, float f2, float f3) {
        float f4 = 1.0f - f2;
        float f5 = aVar.f1729a;
        float f6 = f4 * f5;
        float[] fArr = this.f71a;
        fArr[0] = (f6 * f5) + f2;
        float f7 = aVar.f1730b;
        float f8 = f6 * f7;
        float f9 = aVar.f1731c;
        float f10 = f9 * f3;
        fArr[3] = f8 - f10;
        float f11 = f4 * f9;
        float f12 = f11 * f5;
        float f13 = f7 * f3;
        fArr[6] = f13 + f12;
        fArr[1] = f10 + f8;
        float f14 = f4 * f7;
        fArr[4] = (f7 * f14) + f2;
        float f15 = f14 * f9;
        float f16 = f5 * f3;
        fArr[7] = f15 - f16;
        fArr[2] = f12 - f13;
        fArr[5] = f16 + f15;
        fArr[8] = (f11 * f9) + f2;
    }

    public final void e() {
        float[] fArr = this.f71a;
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = fArr[5];
        float f6 = fArr[6];
        float f7 = fArr[7];
        fArr[3] = f2;
        fArr[6] = f3;
        fArr[1] = f4;
        fArr[7] = f5;
        fArr[2] = f6;
        fArr[5] = f7;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        float[] fArr = this.f71a;
        sb.append(fArr[0]);
        sb.append("|");
        sb.append(fArr[3]);
        sb.append("|");
        sb.append(fArr[6]);
        sb.append("]\n[");
        sb.append(fArr[1]);
        sb.append("|");
        sb.append(fArr[4]);
        sb.append("|");
        sb.append(fArr[7]);
        sb.append("]\n[");
        sb.append(fArr[2]);
        sb.append("|");
        sb.append(fArr[5]);
        sb.append("|");
        sb.append(fArr[8]);
        sb.append("]");
        return sb.toString();
    }
}
