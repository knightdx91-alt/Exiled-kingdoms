package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import a0.j;
import a0.q;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ConeShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, int i2) {
        build(meshPartBuilder, f2, f3, f4, i2, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, int i2, float f5, float f6) {
        build(meshPartBuilder, f2, f3, f4, i2, f5, f6, true);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, int i2, float f5, float f6, boolean z2) {
        meshPartBuilder.ensureVertices(i2 + 2);
        meshPartBuilder.ensureTriangleIndices(i2);
        float f7 = f2 * 0.5f;
        float f8 = f3 * 0.5f;
        float f9 = f4 * 0.5f;
        float f10 = f5 * 0.017453292f;
        float f11 = i2;
        float f12 = ((f6 - f5) * 0.017453292f) / f11;
        float f13 = 1.0f;
        float f14 = 1.0f / f11;
        MeshPartBuilder.VertexInfo vertexInfo = BaseShapeBuilder.vertTmp3.set(null, null, null, null);
        vertexInfo.hasNormal = true;
        vertexInfo.hasPosition = true;
        vertexInfo.hasUV = true;
        short sVertex = meshPartBuilder.vertex(BaseShapeBuilder.vertTmp4.set(null, null, null, null).setPos(0.0f, f8, 0.0f).setNor(0.0f, 1.0f, 0.0f).setUV(0.5f, 0.0f));
        int i3 = 0;
        short s = 0;
        while (i3 <= i2) {
            float f15 = i3;
            float f16 = (f12 * f15) + f10;
            float f17 = f13 - (f15 * f14);
            float f18 = f7;
            vertexInfo.position.t(j.b(f16) * f7, 0.0f, j.j(f16) * f9);
            a aVar = vertexInfo.normal;
            aVar.u(vertexInfo.position);
            aVar.n();
            vertexInfo.position.f1730b = -f8;
            q qVar = vertexInfo.uv;
            qVar.f91a = f17;
            qVar.f92b = 1.0f;
            short sVertex2 = meshPartBuilder.vertex(vertexInfo);
            if (i3 != 0) {
                meshPartBuilder.triangle(sVertex, sVertex2, s);
            }
            i3++;
            s = sVertex2;
            f13 = 1.0f;
            f7 = f18;
        }
        if (z2) {
            EllipseShapeBuilder.build(meshPartBuilder, f2, f4, 0.0f, 0.0f, i2, 0.0f, -f8, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 180.0f - f6, 180.0f - f5);
        }
    }
}
