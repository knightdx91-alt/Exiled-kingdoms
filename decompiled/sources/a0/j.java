package a0;

import com.badlogic.gdx.graphics.GL20;
import java.util.Random;

/* JADX INFO: compiled from: MathUtils.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static o f69a;

    /* JADX INFO: compiled from: MathUtils.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final float[] f70a = new float[GL20.GL_COLOR_BUFFER_BIT];

        static {
            for (int i2 = 0; i2 < 16384; i2++) {
                f70a[i2] = (float) Math.sin(((i2 + 0.5f) / 16384.0f) * 6.2831855f);
            }
            for (int i3 = 0; i3 < 360; i3 += 90) {
                f70a[((int) (45.511112f * i3)) & 16383] = (float) Math.sin(r2 * 0.017453292f);
            }
        }
    }

    static {
        o oVar = new o();
        oVar.setSeed(new Random().nextLong());
        f69a = oVar;
    }

    public static float a(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public static float b(float f2) {
        return a.f70a[((int) ((f2 + 1.5707964f) * 2607.5945f)) & 16383];
    }

    public static float c(float f2) {
        return a.f70a[((int) ((f2 + 90.0f) * 45.511112f)) & 16383];
    }

    public static boolean d(float f2, float f3) {
        return Math.abs(f2 - f3) <= 1.0E-6f;
    }

    public static boolean e(int i2) {
        return i2 != 0 && (i2 & (i2 + (-1))) == 0;
    }

    public static boolean f(float f2) {
        return Math.abs(f2) <= 1.0E-6f;
    }

    public static float g(float f2) {
        return f69a.nextFloat() * f2;
    }

    public static float h(float f2, float f3) {
        return a.a.C(f3, f2, f69a.nextFloat(), f2);
    }

    public static int i(int i2) {
        return (int) f69a.nextLong(i2 + 1);
    }

    public static float j(float f2) {
        return a.f70a[((int) (f2 * 2607.5945f)) & 16383];
    }

    public static float k(float f2) {
        return a.f70a[((int) (f2 * 45.511112f)) & 16383];
    }
}
