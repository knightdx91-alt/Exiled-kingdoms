package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static final int f645r = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final C0013a f646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AccelerateInterpolator f647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final ListView f648d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Runnable f649e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float[] f650f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float[] f651g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f652h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f653i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float[] f654j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float[] f655k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private float[] f656l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f657m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    boolean f658n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    boolean f659o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    boolean f660p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f661q;

    /* JADX INFO: renamed from: android.support.v4.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private static class C0013a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f662a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f663b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f664c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f665d;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private float f670i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f671j;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f666e = Long.MIN_VALUE;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private long f669h = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f667f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f668g = 0;

        C0013a() {
        }

        private float d(long j2) {
            if (j2 < this.f666e) {
                return 0.0f;
            }
            long j3 = this.f669h;
            if (j3 < 0 || j2 < j3) {
                return a.c((j2 - r0) / this.f662a, 0.0f, 1.0f) * 0.5f;
            }
            float f2 = this.f670i;
            return (f2 * a.c((j2 - j3) / this.f671j, 0.0f, 1.0f)) + (1.0f - f2);
        }

        public final void a() {
            if (this.f667f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fD = d(jCurrentAnimationTimeMillis);
            long j2 = jCurrentAnimationTimeMillis - this.f667f;
            this.f667f = jCurrentAnimationTimeMillis;
            this.f668g = (int) (j2 * ((fD * 4.0f) + ((-4.0f) * fD * fD)) * this.f665d);
        }

        public final int b() {
            return this.f668g;
        }

        public final void c() {
            Math.abs(this.f664c);
        }

        public final int e() {
            float f2 = this.f665d;
            return (int) (f2 / Math.abs(f2));
        }

        public final boolean f() {
            return this.f669h > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f669h + ((long) this.f671j);
        }

        public final void g() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            int i2 = (int) (jCurrentAnimationTimeMillis - this.f666e);
            int i3 = this.f663b;
            if (i2 > i3) {
                i2 = i3;
            } else if (i2 < 0) {
                i2 = 0;
            }
            this.f671j = i2;
            this.f670i = d(jCurrentAnimationTimeMillis);
            this.f669h = jCurrentAnimationTimeMillis;
        }

        public final void h() {
            this.f663b = 500;
        }

        public final void i() {
            this.f662a = 500;
        }

        public final void j(float f2, float f3) {
            this.f664c = f2;
            this.f665d = f3;
        }

        public final void k() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f666e = jCurrentAnimationTimeMillis;
            this.f669h = -1L;
            this.f667f = jCurrentAnimationTimeMillis;
            this.f670i = 0.5f;
            this.f668g = 0;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            a aVar = a.this;
            if (aVar.f660p) {
                boolean z2 = aVar.f658n;
                C0013a c0013a = aVar.f646b;
                if (z2) {
                    aVar.f658n = false;
                    c0013a.k();
                }
                if (!c0013a.f()) {
                    int iE = c0013a.e();
                    c0013a.c();
                    if (iE != 0 && aVar.a(iE)) {
                        boolean z3 = aVar.f659o;
                        ListView listView = aVar.f648d;
                        if (z3) {
                            aVar.f659o = false;
                            long jUptimeMillis = SystemClock.uptimeMillis();
                            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                            listView.onTouchEvent(motionEventObtain);
                            motionEventObtain.recycle();
                        }
                        c0013a.a();
                        aVar.e(c0013a.b());
                        listView.postOnAnimation(this);
                        return;
                    }
                }
                aVar.f660p = false;
            }
        }
    }

    public a(ListView listView) {
        C0013a c0013a = new C0013a();
        this.f646b = c0013a;
        this.f647c = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.f650f = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f651g = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.f654j = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.f655k = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f656l = fArr5;
        this.f648d = listView;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = ((int) ((1575.0f * f2) + 0.5f)) / 1000.0f;
        fArr5[0] = f3;
        fArr5[1] = f3;
        float f4 = ((int) ((f2 * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f4;
        fArr4[1] = f4;
        this.f652h = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.f653i = f645r;
        c0013a.i();
        c0013a.h();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private float b(int i2, float f2, float f3, float f4) {
        float fC;
        float interpolation;
        float fC2 = c(this.f650f[i2] * f3, 0.0f, this.f651g[i2]);
        float fD = d(f3 - f2, fC2) - d(f2, fC2);
        AccelerateInterpolator accelerateInterpolator = this.f647c;
        if (fD < 0.0f) {
            interpolation = -accelerateInterpolator.getInterpolation(-fD);
        } else {
            if (fD <= 0.0f) {
                fC = 0.0f;
                if (fC != 0.0f) {
                    return 0.0f;
                }
                float f5 = this.f654j[i2];
                float f6 = this.f655k[i2];
                float f7 = this.f656l[i2];
                float f8 = f5 * f4;
                return fC > 0.0f ? c(fC * f8, f6, f7) : -c((-fC) * f8, f6, f7);
            }
            interpolation = accelerateInterpolator.getInterpolation(fD);
        }
        fC = c(interpolation, -1.0f, 1.0f);
        if (fC != 0.0f) {
        }
    }

    static float c(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    private float d(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.f652h;
        if (i2 == 0 || i2 == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.f660p && i2 == 1) {
                    return 1.0f;
                }
            }
        } else if (i2 == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    public abstract boolean a(int i2);

    public abstract void e(int i2);

    public final void f(boolean z2) {
        if (this.f661q && !z2) {
            if (this.f658n) {
                this.f660p = false;
            } else {
                this.f646b.g();
            }
        }
        this.f661q = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i2;
        if (!this.f661q) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        C0013a c0013a = this.f646b;
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                if (this.f658n) {
                    this.f660p = false;
                } else {
                    c0013a.g();
                }
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                }
            }
            return false;
        }
        this.f659o = true;
        this.f657m = false;
        float x2 = motionEvent.getX();
        float width = view.getWidth();
        ListView listView = this.f648d;
        c0013a.j(b(0, x2, width, listView.getWidth()), b(1, motionEvent.getY(), view.getHeight(), listView.getHeight()));
        if (!this.f660p) {
            int iE = c0013a.e();
            c0013a.c();
            if (iE != 0 && a(iE)) {
                if (this.f649e == null) {
                    this.f649e = new b();
                }
                this.f660p = true;
                this.f658n = true;
                if (this.f657m || (i2 = this.f653i) <= 0) {
                    ((b) this.f649e).run();
                } else {
                    listView.postOnAnimationDelayed(this.f649e, i2);
                }
                this.f657m = true;
            }
        }
        return false;
    }
}
