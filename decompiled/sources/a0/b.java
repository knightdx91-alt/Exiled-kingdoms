package a0;

import java.io.Serializable;

/* JADX INFO: compiled from: Circle.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f31a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f32b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f33c;

    public b() {
    }

    public final boolean a(float f2, float f3) {
        float f4 = this.f31a - f2;
        float f5 = this.f32b - f3;
        float f6 = (f5 * f5) + (f4 * f4);
        float f7 = this.f33c;
        return f6 <= f7 * f7;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != b.class) {
            return false;
        }
        b bVar = (b) obj;
        return this.f31a == bVar.f31a && this.f32b == bVar.f32b && this.f33c == bVar.f33c;
    }

    public final int hashCode() {
        return Float.floatToRawIntBits(this.f32b) + ((Float.floatToRawIntBits(this.f31a) + ((Float.floatToRawIntBits(this.f33c) + 41) * 41)) * 41);
    }

    public final String toString() {
        return this.f31a + "," + this.f32b + "," + this.f33c;
    }

    public b(int i2) {
        this.f31a = 0.0f;
        this.f32b = 0.0f;
        this.f33c = 0.0f;
    }
}
