package a0;

/* JADX INFO: compiled from: FloatCounter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f44a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f45b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f46c;

    public final void a(float f2) {
        this.f46c = f2;
        float f3 = this.f46c;
        if (f3 < this.f44a) {
            this.f44a = f3;
        }
        if (f3 > this.f45b) {
            this.f45b = f3;
        }
    }

    public final void b() {
        this.f44a = Float.MAX_VALUE;
        this.f45b = -3.4028235E38f;
        this.f46c = 0.0f;
    }
}
