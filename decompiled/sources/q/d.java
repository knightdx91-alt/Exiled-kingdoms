package q;

import a0.j;
import a0.q;
import com.badlogic.gdx.physics.box2d.World;

/* JADX INFO: compiled from: PointLight.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d extends e {
    @Override // q.b
    public final void d(float f2) {
        float f3 = f2 * f.f3764u;
        if (f3 < 0.01f) {
            f3 = 0.01f;
        }
        this.f3739i = f3;
        this.f3736f = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006a  */
    @Override // q.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void f() {
        boolean z2 = true;
        if (this.f3736f) {
            float f2 = 360.0f / (this.f3737g - 1);
            for (int i2 = 0; i2 < this.f3737g; i2++) {
                float f3 = i2 * f2;
                this.f3760x[i2] = j.k(f3);
                this.f3761y[i2] = j.c(f3);
                float[] fArr = this.f3762z;
                float f4 = this.f3739i;
                fArr[i2] = this.f3761y[i2] * f4;
                this.A[i2] = f4 * this.f3760x[i2];
            }
        }
        f fVar = this.f3732b;
        boolean z3 = fVar.f3775j;
        q qVar = this.f3759w;
        if (z3) {
            float f5 = qVar.f91a;
            float f6 = qVar.f92b;
            float f7 = this.f3739i + this.f3741k;
            if (fVar.f3780o < f5 + f7 && fVar.f3781p > f5 - f7 && fVar.f3782q < f6 + f7 && fVar.f3783r > f6 - f7) {
                z2 = false;
            }
        }
        this.f3735e = z2;
        if (z2) {
            return;
        }
        this.f3736f = false;
        for (int i3 = 0; i3 < this.f3737g; i3++) {
            this.f3748r = i3;
            this.f3747q[i3] = 1.0f;
            float f8 = this.f3762z[i3] + qVar.f91a;
            q qVar2 = this.f3758v;
            qVar2.f91a = f8;
            this.f3745o[i3] = f8;
            float f9 = this.A[i3] + qVar.f92b;
            qVar2.f92b = f9;
            this.f3746p[i3] = f9;
            World world = this.f3732b.s;
            if (world != null) {
                world.a(this.s, qVar, qVar2);
            }
        }
        g();
    }
}
