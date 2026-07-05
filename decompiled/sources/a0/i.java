package a0;

/* JADX INFO: compiled from: Intersector.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.badlogic.gdx.utils.j f61a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final com.badlogic.gdx.utils.j f62b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final q f63c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final q f64d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final q f65e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final q f66f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final q f67g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ int f68h = 0;

    static {
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        f61a = new com.badlogic.gdx.utils.j();
        f62b = new com.badlogic.gdx.utils.j();
        f63c = new q();
        f64d = new q();
        f65e = new q();
        f66f = new q();
        f67g = new q();
        new q();
        new q();
        new q();
        new q();
        com.badlogic.gdx.math.a aVar = new com.badlogic.gdx.math.a();
        com.badlogic.gdx.math.a aVar2 = new com.badlogic.gdx.math.a();
        aVar2.u(aVar);
        aVar2.n();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
        new com.badlogic.gdx.math.a();
    }

    public static void a(q qVar, q qVar2, q qVar3, q qVar4, q qVar5) {
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        float f4 = qVar2.f91a;
        float f5 = qVar2.f92b;
        float f6 = qVar3.f91a;
        float f7 = qVar3.f92b;
        float f8 = qVar4.f91a;
        float f9 = qVar4.f92b - f7;
        float f10 = f4 - f2;
        float f11 = f8 - f6;
        float f12 = f5 - f3;
        float f13 = (f9 * f10) - (f11 * f12);
        if (f13 == 0.0f || qVar5 == null) {
            return;
        }
        float f14 = (((f3 - f7) * f11) - ((f2 - f6) * f9)) / f13;
        qVar5.f91a = (f10 * f14) + f2;
        qVar5.f92b = (f12 * f14) + f3;
    }

    public static void b(m mVar, m mVar2, m mVar3) {
        if (mVar.c().length == 0 || mVar2.c().length == 0) {
            return;
        }
        com.badlogic.gdx.utils.j jVar = f61a;
        jVar.f1824b = 0;
        com.badlogic.gdx.utils.j jVar2 = f62b;
        jVar2.f1824b = 0;
        float[] fArrB = mVar.b();
        jVar2.c(fArrB, 0, fArrB.length);
        float[] fArrB2 = mVar2.b();
        int length = fArrB2.length - 2;
        for (int i2 = 0; i2 <= length; i2 += 2) {
            float f2 = fArrB2[i2];
            float f3 = fArrB2[i2 + 1];
            q qVar = f64d;
            qVar.f91a = f2;
            qVar.f92b = f3;
            q qVar2 = f65e;
            if (i2 < length) {
                float f4 = fArrB2[i2 + 2];
                float f5 = fArrB2[i2 + 3];
                qVar2.f91a = f4;
                qVar2.f92b = f5;
            } else {
                float f6 = fArrB2[0];
                float f7 = fArrB2[1];
                qVar2.f91a = f6;
                qVar2.f92b = f7;
            }
            int i3 = jVar2.f1824b;
            if (i3 == 0) {
                return;
            }
            float fE = jVar2.e(i3 - 2);
            float fE2 = jVar2.e(jVar2.f1824b - 1);
            q qVar3 = f66f;
            qVar3.f91a = fE;
            qVar3.f92b = fE2;
            for (int i4 = 0; i4 < jVar2.f1824b; i4 += 2) {
                float fE3 = jVar2.e(i4);
                float fE4 = jVar2.e(i4 + 1);
                q qVar4 = f67g;
                qVar4.f91a = fE3;
                qVar4.f92b = fE4;
                boolean z2 = d(qVar2, qVar, qVar3) > 0;
                int iD = d(qVar2, qVar, qVar4);
                q qVar5 = f63c;
                if (iD > 0) {
                    if (!z2) {
                        a(qVar3, qVar4, qVar, qVar2, qVar5);
                        int i5 = jVar.f1824b;
                        if (i5 < 2 || jVar.e(i5 - 2) != qVar5.f91a || jVar.e(jVar.f1824b - 1) != qVar5.f92b) {
                            jVar.a(qVar5.f91a);
                            jVar.a(qVar5.f92b);
                        }
                    }
                    jVar.a(qVar4.f91a);
                    jVar.a(qVar4.f92b);
                } else if (z2) {
                    a(qVar3, qVar4, qVar, qVar2, qVar5);
                    jVar.a(qVar5.f91a);
                    jVar.a(qVar5.f92b);
                }
                float f8 = qVar4.f91a;
                float f9 = qVar4.f92b;
                qVar3.f91a = f8;
                qVar3.f92b = f9;
            }
            jVar2.f1824b = 0;
            jVar2.c(jVar.f1823a, 0, jVar.f1824b);
            jVar.f1824b = 0;
        }
        if (jVar2.f1824b != 0) {
            int length2 = mVar3.c().length;
            int i6 = jVar2.f1824b;
            if (length2 == i6) {
                System.arraycopy(jVar2.f1823a, 0, mVar3.c(), 0, jVar2.f1824b);
                return;
            }
            float[] fArr = new float[i6];
            System.arraycopy(jVar2.f1823a, 0, fArr, 0, i6);
            mVar3.e(fArr);
        }
    }

    public static boolean c(q qVar, q qVar2, m mVar) {
        float[] fArrB = mVar.b();
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        float f4 = qVar2.f91a;
        float f5 = qVar2.f92b;
        int length = fArrB.length;
        float f6 = fArrB[length - 2];
        float f7 = fArrB[length - 1];
        int i2 = 0;
        while (i2 < length) {
            float f8 = fArrB[i2];
            float f9 = fArrB[i2 + 1];
            float f10 = f9 - f7;
            float f11 = f4 - f2;
            float f12 = f8 - f6;
            float f13 = f5 - f3;
            float f14 = (f10 * f11) - (f12 * f13);
            if (f14 != 0.0f) {
                float f15 = f3 - f7;
                float f16 = f2 - f6;
                float f17 = ((f12 * f15) - (f10 * f16)) / f14;
                if (f17 >= 0.0f && f17 <= 1.0f) {
                    float f18 = ((f11 * f15) - (f13 * f16)) / f14;
                    if (f18 >= 0.0f && f18 <= 1.0f) {
                        return true;
                    }
                }
            }
            i2 += 2;
            f6 = f8;
            f7 = f9;
        }
        return false;
    }

    public static int d(q qVar, q qVar2, q qVar3) {
        float f2 = qVar2.f91a;
        float f3 = qVar.f91a;
        float f4 = qVar3.f92b;
        float f5 = qVar.f92b;
        return (int) Math.signum(((f4 - f5) * (f2 - f3)) - ((qVar3.f91a - f3) * (qVar2.f92b - f5)));
    }
}
