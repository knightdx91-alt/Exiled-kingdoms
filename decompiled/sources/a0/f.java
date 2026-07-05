package a0;

import com.badlogic.gdx.math.Matrix4;

/* JADX INFO: compiled from: Frustum.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected static final float[] f47d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l[] f48a = new l[6];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.badlogic.gdx.math.a[] f49b = {new com.badlogic.gdx.math.a(), new com.badlogic.gdx.math.a(), new com.badlogic.gdx.math.a(), new com.badlogic.gdx.math.a(), new com.badlogic.gdx.math.a(), new com.badlogic.gdx.math.a(), new com.badlogic.gdx.math.a(), new com.badlogic.gdx.math.a()};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final float[] f50c = new float[24];

    static {
        com.badlogic.gdx.math.a[] aVarArr = {new com.badlogic.gdx.math.a(-1.0f, -1.0f, -1.0f), new com.badlogic.gdx.math.a(1.0f, -1.0f, -1.0f), new com.badlogic.gdx.math.a(1.0f, 1.0f, -1.0f), new com.badlogic.gdx.math.a(-1.0f, 1.0f, -1.0f), new com.badlogic.gdx.math.a(-1.0f, -1.0f, 1.0f), new com.badlogic.gdx.math.a(1.0f, -1.0f, 1.0f), new com.badlogic.gdx.math.a(1.0f, 1.0f, 1.0f), new com.badlogic.gdx.math.a(-1.0f, 1.0f, 1.0f)};
        f47d = new float[24];
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            com.badlogic.gdx.math.a aVar = aVarArr[i3];
            float[] fArr = f47d;
            fArr[i2] = aVar.f1729a;
            int i4 = i2 + 2;
            fArr[i2 + 1] = aVar.f1730b;
            i2 += 3;
            fArr[i4] = aVar.f1731c;
        }
        new com.badlogic.gdx.math.a();
    }

    public f() {
        for (int i2 = 0; i2 < 6; i2++) {
            this.f48a[i2] = new l(new com.badlogic.gdx.math.a());
        }
    }

    public final void a(Matrix4 matrix4) {
        float[] fArr = f47d;
        int length = fArr.length;
        float[] fArr2 = this.f50c;
        System.arraycopy(fArr, 0, fArr2, 0, length);
        Matrix4.prj(matrix4.f1724a, fArr2, 0, 8, 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            com.badlogic.gdx.math.a[] aVarArr = this.f49b;
            if (i2 >= 8) {
                l[] lVarArr = this.f48a;
                lVarArr[0].a(aVarArr[1], aVarArr[0], aVarArr[2]);
                lVarArr[1].a(aVarArr[4], aVarArr[5], aVarArr[7]);
                lVarArr[2].a(aVarArr[0], aVarArr[4], aVarArr[3]);
                lVarArr[3].a(aVarArr[5], aVarArr[1], aVarArr[6]);
                lVarArr[4].a(aVarArr[2], aVarArr[3], aVarArr[6]);
                lVarArr[5].a(aVarArr[4], aVarArr[0], aVarArr[1]);
                return;
            }
            com.badlogic.gdx.math.a aVar = aVarArr[i2];
            aVar.f1729a = fArr2[i3];
            int i4 = i3 + 2;
            aVar.f1730b = fArr2[i3 + 1];
            i3 += 3;
            aVar.f1731c = fArr2[i4];
            i2++;
        }
    }
}
