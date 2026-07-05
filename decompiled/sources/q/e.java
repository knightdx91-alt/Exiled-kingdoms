package q;

import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;

/* JADX INFO: compiled from: PositionalLight.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class e extends b {
    protected float[] A;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    protected final q f3758v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    protected final q f3759w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected float[] f3760x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    protected float[] f3761y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    protected float[] f3762z;

    public e(f fVar, Color color, float f2) {
        super(fVar, color, f2);
        this.f3758v = new q();
        q qVar = new q();
        this.f3759w = qVar;
        qVar.f91a = 0.0f;
        qVar.f92b = 0.0f;
        Mesh.VertexDataType vertexDataType = Mesh.VertexDataType.VertexArray;
        this.f3742l = new Mesh(vertexDataType, false, this.f3738h, 0, new VertexAttribute(1, 2, "vertex_positions"), new VertexAttribute(4, 4, "quad_colors"), new VertexAttribute(32, 1, "s"));
        this.f3743m = new Mesh(vertexDataType, false, this.f3738h * 2, 0, new VertexAttribute(1, 2, "vertex_positions"), new VertexAttribute(4, 4, "quad_colors"), new VertexAttribute(32, 1, "s"));
        g();
    }

    @Override // q.b
    final void c() {
        f fVar = this.f3732b;
        if (fVar.f3775j && this.f3735e) {
            return;
        }
        fVar.f3779n++;
        this.f3742l.render(fVar.f3774i, 6, 0, this.f3738h);
        if (this.f3734d) {
            this.f3743m.render(this.f3732b.f3774i, 5, 0, (this.f3738h - 1) * 2);
        }
    }

    @Override // q.b
    protected final void e() {
        super.e();
        this.f3760x = new float[8];
        this.f3761y = new float[8];
        this.f3762z = new float[8];
        this.A = new float[8];
    }

    protected final void g() {
        float[] fArr = this.f3744n;
        q qVar = this.f3759w;
        fArr[0] = qVar.f91a;
        fArr[1] = qVar.f92b;
        fArr[2] = this.f3740j;
        fArr[3] = 1.0f;
        int i2 = 4;
        for (int i3 = 0; i3 < this.f3737g; i3++) {
            float[] fArr2 = this.f3744n;
            fArr2[i2] = this.f3745o[i3];
            fArr2[i2 + 1] = this.f3746p[i3];
            int i4 = i2 + 3;
            fArr2[i2 + 2] = this.f3740j;
            i2 += 4;
            fArr2[i4] = 1.0f - this.f3747q[i3];
        }
        this.f3742l.setVertices(this.f3744n, 0, i2);
        if (this.f3734d) {
            int i5 = 0;
            for (int i6 = 0; i6 < this.f3737g; i6++) {
                float[] fArr3 = this.f3744n;
                float[] fArr4 = this.f3745o;
                fArr3[i5] = fArr4[i6];
                float[] fArr5 = this.f3746p;
                fArr3[i5 + 1] = fArr5[i6];
                fArr3[i5 + 2] = this.f3740j;
                float f2 = 1.0f - this.f3747q[i6];
                fArr3[i5 + 3] = f2;
                float f3 = fArr4[i6];
                float f4 = f2 * this.f3741k;
                fArr3[i5 + 4] = (this.f3761y[i6] * f4) + f3;
                fArr3[i5 + 5] = (f4 * this.f3760x[i6]) + fArr5[i6];
                int i7 = i5 + 7;
                fArr3[i5 + 6] = b.f3730u;
                i5 += 8;
                fArr3[i7] = 0.0f;
            }
            this.f3743m.setVertices(this.f3744n, 0, i5);
        }
    }

    public final void h(float f2, float f3) {
        q qVar = this.f3759w;
        qVar.f91a = f2;
        qVar.f92b = f3;
    }
}
