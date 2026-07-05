package android.support.v7.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1319a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1320b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1321c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1322d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1323e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1324f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f1325g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1326h = false;

    p0() {
    }

    public final int a() {
        return this.f1325g ? this.f1319a : this.f1320b;
    }

    public final int b() {
        return this.f1319a;
    }

    public final int c() {
        return this.f1320b;
    }

    public final int d() {
        return this.f1325g ? this.f1320b : this.f1319a;
    }

    public final void e(int i2, int i3) {
        this.f1326h = false;
        if (i2 != Integer.MIN_VALUE) {
            this.f1323e = i2;
            this.f1319a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1324f = i3;
            this.f1320b = i3;
        }
    }

    public final void f(boolean z2) {
        if (z2 == this.f1325g) {
            return;
        }
        this.f1325g = z2;
        if (!this.f1326h) {
            this.f1319a = this.f1323e;
            this.f1320b = this.f1324f;
            return;
        }
        if (z2) {
            int i2 = this.f1322d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f1323e;
            }
            this.f1319a = i2;
            int i3 = this.f1321c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = this.f1324f;
            }
            this.f1320b = i3;
            return;
        }
        int i4 = this.f1321c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f1323e;
        }
        this.f1319a = i4;
        int i5 = this.f1322d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = this.f1324f;
        }
        this.f1320b = i5;
    }

    public final void g(int i2, int i3) {
        this.f1321c = i2;
        this.f1322d = i3;
        this.f1326h = true;
        if (this.f1325g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f1319a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f1320b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1319a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1320b = i3;
        }
    }
}
