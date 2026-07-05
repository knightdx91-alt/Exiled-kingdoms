package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PatchShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2, MeshPartBuilder.VertexInfo vertexInfo3, MeshPartBuilder.VertexInfo vertexInfo4, int i2, int i3) {
        if (i2 < 1 || i3 < 1) {
            throw new m("divisionsU and divisionV must be > 0, u,v: " + i2 + ", " + i3);
        }
        meshPartBuilder.ensureVertices((i2 + 1) * (i3 + 1));
        meshPartBuilder.ensureRectangleIndices(i3 * i2);
        for (int i4 = 0; i4 <= i2; i4++) {
            float f2 = i4 / i2;
            BaseShapeBuilder.vertTmp5.set(vertexInfo).lerp(vertexInfo2, f2);
            BaseShapeBuilder.vertTmp6.set(vertexInfo4).lerp(vertexInfo3, f2);
            for (int i5 = 0; i5 <= i3; i5++) {
                short sVertex = meshPartBuilder.vertex(BaseShapeBuilder.vertTmp7.set(BaseShapeBuilder.vertTmp5).lerp(BaseShapeBuilder.vertTmp6, i5 / i3));
                if (i4 > 0 && i5 > 0) {
                    int i6 = sVertex - i3;
                    meshPartBuilder.rect((short) (i6 - 2), (short) (sVertex - 1), sVertex, (short) (i6 - 1));
                }
            }
        }
    }

    public static void build(MeshPartBuilder meshPartBuilder, a aVar, a aVar2, a aVar3, a aVar4, a aVar5, int i2, int i3) {
        build(meshPartBuilder, BaseShapeBuilder.vertTmp1.set(aVar, aVar5, null, null).setUV(0.0f, 1.0f), BaseShapeBuilder.vertTmp2.set(aVar2, aVar5, null, null).setUV(1.0f, 1.0f), BaseShapeBuilder.vertTmp3.set(aVar3, aVar5, null, null).setUV(1.0f, 0.0f), BaseShapeBuilder.vertTmp4.set(aVar4, aVar5, null, null).setUV(0.0f, 0.0f), i2, i3);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int i2, int i3) {
        build(meshPartBuilder, BaseShapeBuilder.vertTmp1.set(null).setPos(f2, f3, f4).setNor(f14, f15, f16).setUV(0.0f, 1.0f), BaseShapeBuilder.vertTmp2.set(null).setPos(f5, f6, f7).setNor(f14, f15, f16).setUV(1.0f, 1.0f), BaseShapeBuilder.vertTmp3.set(null).setPos(f8, f9, f10).setNor(f14, f15, f16).setUV(1.0f, 0.0f), BaseShapeBuilder.vertTmp4.set(null).setPos(f11, f12, f13).setNor(f14, f15, f16).setUV(0.0f, 0.0f), i2, i3);
    }
}
