package a0;

/* JADX INFO: compiled from: Interpolation.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f52a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f53b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f54c;

    /* JADX INFO: compiled from: Interpolation.java */
    static class a extends h {
        @Override // a0.h
        public final float a(float f2) {
            return f2;
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    static class b extends h {
        @Override // a0.h
        public final float a(float f2) {
            return ((((6.0f * f2) - 15.0f) * f2) + 10.0f) * f2 * f2 * f2;
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class c extends e {
        @Override // a0.h.e, a0.h
        public final float a(float f2) {
            float[] fArr = this.f55d;
            if (f2 <= 0.5f) {
                float f3 = 1.0f - (f2 * 2.0f);
                float f4 = fArr[0];
                float f5 = f4 / 2.0f;
                float f6 = f5 + f3;
                return (1.0f - (f6 < f4 ? (f6 / f5) - 1.0f : super.a(f3))) / 2.0f;
            }
            float f7 = (f2 * 2.0f) - 1.0f;
            float f8 = fArr[0];
            float f9 = f8 / 2.0f;
            float f10 = f9 + f7;
            return ((f10 < f8 ? (f10 / f9) - 1.0f : super.a(f7)) / 2.0f) + 0.5f;
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class d extends e {
        @Override // a0.h.e, a0.h
        public final float a(float f2) {
            return 1.0f - super.a(1.0f - f2);
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class e extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final float[] f55d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final float[] f56e = {1.0f, 0.26f, 0.11f, 0.03f};

        public e() {
            float[] fArr = {0.34f, 0.34f, 0.2f, 0.15f};
            this.f55d = fArr;
            fArr[0] = 0.34f * 2.0f;
        }

        @Override // a0.h
        public float a(float f2) {
            if (f2 == 1.0f) {
                return 1.0f;
            }
            float[] fArr = this.f55d;
            int i2 = 0;
            float f3 = (fArr[0] / 2.0f) + f2;
            int length = fArr.length;
            float f4 = 0.0f;
            float f5 = 0.0f;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                f5 = fArr[i2];
                if (f3 <= f5) {
                    f4 = this.f56e[i2];
                    break;
                }
                f3 -= f5;
                i2++;
            }
            float f6 = f3 / f5;
            float f7 = (4.0f / f5) * f4 * f6;
            return 1.0f - ((f7 - (f6 * f7)) * f5);
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class f extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final float f57d;

        public f(int i2) {
            this.f57d = i2 * 3.1415927f * (i2 % 2 == 0 ? 1 : -1);
        }

        @Override // a0.h
        public float a(float f2) {
            float f3 = this.f57d;
            if (f2 <= 0.5f) {
                return ((a0.j.j((f2 * 2.0f) * f3) * ((float) Math.pow(2.0f, (r10 - 1.0f) * 10.0f))) * 1.0f) / 2.0f;
            }
            return 1.0f - (((a0.j.j(((1.0f - f2) * 2.0f) * f3) * ((float) Math.pow(2.0f, (r10 - 1.0f) * 10.0f))) * 1.0f) / 2.0f);
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class g extends f {
        @Override // a0.h.f, a0.h
        public final float a(float f2) {
            if (f2 >= 0.99d) {
                return 1.0f;
            }
            return a0.j.j(f2 * this.f57d) * ((float) Math.pow(2.0f, (f2 - 1.0f) * 10.0f)) * 1.0f;
        }
    }

    /* JADX INFO: renamed from: a0.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Interpolation.java */
    public static class C0001h extends f {
        @Override // a0.h.f, a0.h
        public final float a(float f2) {
            if (f2 == 0.0f) {
                return 0.0f;
            }
            return 1.0f - ((a0.j.j((1.0f - f2) * this.f57d) * ((float) Math.pow(2.0f, (r6 - 1.0f) * 10.0f))) * 1.0f);
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class i extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final float f58d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final float f59e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final float f60f;

        public i(float f2) {
            this.f58d = f2;
            float fPow = (float) Math.pow(2.0f, -f2);
            this.f59e = fPow;
            this.f60f = 1.0f / (1.0f - fPow);
        }

        @Override // a0.h
        public float a(float f2) {
            float fPow;
            float f3 = this.f60f;
            float f4 = this.f59e;
            float f5 = this.f58d;
            if (f2 <= 0.5f) {
                fPow = (((float) Math.pow(2.0f, ((f2 * 2.0f) - 1.0f) * f5)) - f4) * f3;
            } else {
                fPow = 2.0f - ((((float) Math.pow(2.0f, ((f2 * 2.0f) - 1.0f) * (-f5))) - f4) * f3);
            }
            return fPow / 2.0f;
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class j extends i {
        @Override // a0.h.i, a0.h
        public final float a(float f2) {
            return (((float) Math.pow(2.0f, (f2 - 1.0f) * this.f58d)) - this.f59e) * this.f60f;
        }
    }

    /* JADX INFO: compiled from: Interpolation.java */
    public static class k extends i {
        @Override // a0.h.i, a0.h
        public final float a(float f2) {
            return 1.0f - ((((float) Math.pow(2.0f, (-this.f58d) * f2)) - this.f59e) * this.f60f);
        }
    }

    static {
        new i(10.0f);
        new j(10.0f);
        new k(10.0f);
        new i(5.0f);
        f54c = new j(5.0f);
        new k(5.0f);
        new f(7);
        new g(6);
        new C0001h(7);
        new c();
        new d();
        new e();
    }

    public abstract float a(float f2);
}
