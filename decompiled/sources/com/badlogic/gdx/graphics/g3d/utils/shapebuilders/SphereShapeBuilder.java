package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import a0.j;
import a0.k;
import a0.q;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.j0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SphereShapeBuilder extends BaseShapeBuilder {
    private static final j0 tmpIndices = new j0();
    private static final k normalTransform = new k();

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, int i2, int i3) {
        build(meshPartBuilder, f2, f3, f4, i2, i3, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    @Deprecated
    public static void build(MeshPartBuilder meshPartBuilder, Matrix4 matrix4, float f2, float f3, float f4, int i2, int i3) {
        build(meshPartBuilder, matrix4, f2, f3, f4, i2, i3, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, int i2, int i3, float f5, float f6, float f7, float f8) {
        Matrix4 matrix4 = BaseShapeBuilder.matTmp1;
        matrix4.d();
        build(meshPartBuilder, matrix4, f2, f3, f4, i2, i3, f5, f6, f7, f8);
    }

    @Deprecated
    public static void build(MeshPartBuilder meshPartBuilder, Matrix4 matrix4, float f2, float f3, float f4, int i2, int i3, float f5, float f6, float f7, float f8) {
        float f9;
        float f10;
        float f11;
        int i4 = i2;
        int i5 = i3;
        boolean zD = j.d(f7, 0.0f);
        boolean zD2 = j.d(f8, 180.0f);
        float f12 = f2 * 0.5f;
        float f13 = f3 * 0.5f;
        float f14 = f4 * 0.5f;
        float f15 = f5 * 0.017453292f;
        float f16 = i4;
        float f17 = ((f6 - f5) * 0.017453292f) / f16;
        float f18 = f7 * 0.017453292f;
        float f19 = i5;
        float f20 = ((f8 - f7) * 0.017453292f) / f19;
        float f21 = 1.0f / f16;
        float f22 = 1.0f / f19;
        MeshPartBuilder.VertexInfo vertexInfo = BaseShapeBuilder.vertTmp3.set(null, null, null, null);
        vertexInfo.hasNormal = true;
        vertexInfo.hasPosition = true;
        vertexInfo.hasUV = true;
        normalTransform.c(matrix4);
        int i6 = i4 + 3;
        j0 j0Var = tmpIndices;
        j0Var.f1827b = 0;
        j0Var.b(i4 * 2);
        j0Var.f1827b = i6;
        int i7 = i4 + 1;
        meshPartBuilder.ensureVertices((i5 + 1) * i7);
        meshPartBuilder.ensureRectangleIndices(i4);
        int i8 = 0;
        int i9 = 0;
        while (i8 <= i5) {
            int i10 = i7;
            float f23 = i8;
            float f24 = (f20 * f23) + f18;
            float f25 = f23 * f22;
            float fJ = j.j(f24);
            float f26 = f20;
            float fB = j.b(f24) * f13;
            float f27 = f22;
            int i11 = i9;
            float f28 = f13;
            int i12 = 0;
            while (i12 <= i4) {
                float f29 = i12;
                float f30 = (f17 * f29) + f15;
                if ((i8 == 0 && zD) || (i8 == i5 && zD2)) {
                    f10 = (f29 - 0.5f) * f21;
                    f9 = 1.0f;
                } else {
                    f9 = 1.0f;
                    f10 = f29 * f21;
                }
                float f31 = f9 - f10;
                float f32 = f15;
                float f33 = f12;
                float f34 = f17;
                vertexInfo.position.t(j.b(f30) * f12 * fJ, fB, j.j(f30) * f14 * fJ);
                a aVar = vertexInfo.normal;
                aVar.u(vertexInfo.position);
                aVar.l(normalTransform);
                aVar.n();
                vertexInfo.position.m(matrix4);
                q qVar = vertexInfo.uv;
                qVar.f91a = f31;
                qVar.f92b = f25;
                j0 j0Var2 = tmpIndices;
                short sVertex = meshPartBuilder.vertex(vertexInfo);
                if (i11 < j0Var2.f1827b) {
                    j0Var2.f1826a[i11] = sVertex;
                    int i13 = i11 + i6;
                    if (i8 <= 0 || i12 <= 0) {
                        f11 = fB;
                    } else if (i8 == 1 && zD) {
                        f11 = fB;
                        meshPartBuilder.triangle(j0Var2.c(i11), j0Var2.c((i13 - 1) % i6), j0Var2.c((i13 - i10) % i6));
                    } else {
                        f11 = fB;
                        if (i8 == i5 && zD2) {
                            meshPartBuilder.triangle(j0Var2.c(i11), j0Var2.c((i13 - (i2 + 2)) % i6), j0Var2.c((i13 - i10) % i6));
                        } else {
                            meshPartBuilder.rect(j0Var2.c(i11), j0Var2.c((i13 - 1) % i6), j0Var2.c((i13 - (i2 + 2)) % i6), j0Var2.c((i13 - i10) % i6));
                        }
                    }
                    i11 = (i11 + 1) % j0Var2.f1827b;
                    i12++;
                    f15 = f32;
                    i4 = i2;
                    i5 = i3;
                    f17 = f34;
                    f12 = f33;
                    fB = f11;
                } else {
                    StringBuilder sbR = a.a.r(i11, "index can't be >= size: ", " >= ");
                    sbR.append(j0Var2.f1827b);
                    throw new IndexOutOfBoundsException(sbR.toString());
                }
            }
            i8++;
            f15 = f15;
            i4 = i2;
            i5 = i3;
            i7 = i10;
            f20 = f26;
            f13 = f28;
            i9 = i11;
            f22 = f27;
        }
    }
}
