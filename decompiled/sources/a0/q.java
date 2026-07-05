package a0;

import java.io.Serializable;

/* JADX INFO: compiled from: Vector2.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class q implements Serializable {
    private static final long serialVersionUID = 913902788239530931L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f91a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f92b;

    public q() {
    }

    public final void a() {
        float f2 = this.f91a;
        float f3 = this.f92b;
        float fSqrt = (float) Math.sqrt((f3 * f3) + (f2 * f2));
        if (fSqrt != 0.0f) {
            this.f91a /= fSqrt;
            this.f92b /= fSqrt;
        }
    }

    public final void b(q qVar) {
        this.f91a = qVar.f91a;
        this.f92b = qVar.f92b;
    }

    public final void c(q qVar) {
        this.f91a -= qVar.f91a;
        this.f92b -= qVar.f92b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || q.class != obj.getClass()) {
            return false;
        }
        q qVar = (q) obj;
        return Float.floatToIntBits(this.f91a) == Float.floatToIntBits(qVar.f91a) && Float.floatToIntBits(this.f92b) == Float.floatToIntBits(qVar.f92b);
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.f92b) + ((Float.floatToIntBits(this.f91a) + 31) * 31);
    }

    public final String toString() {
        return "(" + this.f91a + "," + this.f92b + ")";
    }

    public q(float f2, float f3) {
        this.f91a = f2;
        this.f92b = f3;
    }
}
