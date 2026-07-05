package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import b0.a;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Matrix4;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BoxShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, a aVar) {
        com.badlogic.gdx.math.a aVarObtainV3 = BaseShapeBuilder.obtainV3();
        com.badlogic.gdx.math.a aVar2 = aVar.f1412a;
        aVarObtainV3.t(aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
        com.badlogic.gdx.math.a aVarObtainV32 = BaseShapeBuilder.obtainV3();
        com.badlogic.gdx.math.a aVar3 = aVar.f1412a;
        float f2 = aVar3.f1729a;
        com.badlogic.gdx.math.a aVar4 = aVar.f1413b;
        aVarObtainV32.t(f2, aVar4.f1730b, aVar3.f1731c);
        com.badlogic.gdx.math.a aVarObtainV33 = BaseShapeBuilder.obtainV3();
        aVarObtainV33.t(aVar4.f1729a, aVar3.f1730b, aVar3.f1731c);
        com.badlogic.gdx.math.a aVarObtainV34 = BaseShapeBuilder.obtainV3();
        aVarObtainV34.t(aVar4.f1729a, aVar4.f1730b, aVar3.f1731c);
        com.badlogic.gdx.math.a aVarObtainV35 = BaseShapeBuilder.obtainV3();
        aVarObtainV35.t(aVar3.f1729a, aVar3.f1730b, aVar4.f1731c);
        com.badlogic.gdx.math.a aVarObtainV36 = BaseShapeBuilder.obtainV3();
        aVarObtainV36.t(aVar3.f1729a, aVar4.f1730b, aVar4.f1731c);
        com.badlogic.gdx.math.a aVarObtainV37 = BaseShapeBuilder.obtainV3();
        aVarObtainV37.t(aVar4.f1729a, aVar3.f1730b, aVar4.f1731c);
        com.badlogic.gdx.math.a aVarObtainV38 = BaseShapeBuilder.obtainV3();
        aVarObtainV38.t(aVar4.f1729a, aVar4.f1730b, aVar4.f1731c);
        meshPartBuilder.box(aVarObtainV3, aVarObtainV32, aVarObtainV33, aVarObtainV34, aVarObtainV35, aVarObtainV36, aVarObtainV37, aVarObtainV38);
        BaseShapeBuilder.freeAll();
    }

    public static void build(MeshPartBuilder meshPartBuilder, MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2, MeshPartBuilder.VertexInfo vertexInfo3, MeshPartBuilder.VertexInfo vertexInfo4, MeshPartBuilder.VertexInfo vertexInfo5, MeshPartBuilder.VertexInfo vertexInfo6, MeshPartBuilder.VertexInfo vertexInfo7, MeshPartBuilder.VertexInfo vertexInfo8) {
        meshPartBuilder.ensureVertices(8);
        short sVertex = meshPartBuilder.vertex(vertexInfo);
        short sVertex2 = meshPartBuilder.vertex(vertexInfo3);
        short sVertex3 = meshPartBuilder.vertex(vertexInfo4);
        short sVertex4 = meshPartBuilder.vertex(vertexInfo2);
        short sVertex5 = meshPartBuilder.vertex(vertexInfo5);
        short sVertex6 = meshPartBuilder.vertex(vertexInfo7);
        short sVertex7 = meshPartBuilder.vertex(vertexInfo8);
        short sVertex8 = meshPartBuilder.vertex(vertexInfo6);
        int primitiveType = meshPartBuilder.getPrimitiveType();
        if (primitiveType == 1) {
            meshPartBuilder.ensureIndices(24);
            meshPartBuilder.rect(sVertex, sVertex2, sVertex3, sVertex4);
            meshPartBuilder.rect(sVertex6, sVertex5, sVertex8, sVertex7);
            meshPartBuilder.index(sVertex, sVertex5, sVertex4, sVertex8, sVertex3, sVertex7, sVertex2, sVertex6);
            return;
        }
        if (primitiveType == 0) {
            meshPartBuilder.ensureRectangleIndices(2);
            meshPartBuilder.rect(sVertex, sVertex2, sVertex3, sVertex4);
            meshPartBuilder.rect(sVertex6, sVertex5, sVertex8, sVertex7);
            return;
        }
        meshPartBuilder.ensureRectangleIndices(6);
        meshPartBuilder.rect(sVertex, sVertex2, sVertex3, sVertex4);
        meshPartBuilder.rect(sVertex6, sVertex5, sVertex8, sVertex7);
        meshPartBuilder.rect(sVertex, sVertex4, sVertex8, sVertex5);
        meshPartBuilder.rect(sVertex6, sVertex7, sVertex3, sVertex2);
        meshPartBuilder.rect(sVertex6, sVertex2, sVertex, sVertex5);
        meshPartBuilder.rect(sVertex3, sVertex7, sVertex8, sVertex4);
    }

    public static void build(MeshPartBuilder meshPartBuilder, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4, com.badlogic.gdx.math.a aVar5, com.badlogic.gdx.math.a aVar6, com.badlogic.gdx.math.a aVar7, com.badlogic.gdx.math.a aVar8) {
        if ((meshPartBuilder.getAttributes().getMask() & 408) == 0) {
            build(meshPartBuilder, BaseShapeBuilder.vertTmp1.set(aVar, null, null, null), BaseShapeBuilder.vertTmp2.set(aVar2, null, null, null), BaseShapeBuilder.vertTmp3.set(aVar3, null, null, null), BaseShapeBuilder.vertTmp4.set(aVar4, null, null, null), BaseShapeBuilder.vertTmp5.set(aVar5, null, null, null), BaseShapeBuilder.vertTmp6.set(aVar6, null, null, null), BaseShapeBuilder.vertTmp7.set(aVar7, null, null, null), BaseShapeBuilder.vertTmp8.set(aVar8, null, null, null));
            return;
        }
        meshPartBuilder.ensureVertices(24);
        meshPartBuilder.ensureRectangleIndices(6);
        com.badlogic.gdx.math.a aVar9 = BaseShapeBuilder.tmpV1;
        aVar9.u(aVar);
        aVar9.k(aVar4, 0.5f);
        com.badlogic.gdx.math.a aVar10 = BaseShapeBuilder.tmpV2;
        aVar10.u(aVar5);
        aVar10.k(aVar8, 0.5f);
        aVar9.v(aVar10.f1729a, aVar10.f1730b, aVar10.f1731c);
        aVar9.n();
        meshPartBuilder.rect(aVar, aVar2, aVar4, aVar3, aVar9);
        aVar9.s(-1.0f);
        meshPartBuilder.rect(aVar6, aVar5, aVar7, aVar8, aVar9);
        aVar9.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
        aVar9.k(aVar7, 0.5f);
        aVar10.u(aVar2);
        aVar10.k(aVar8, 0.5f);
        aVar9.v(aVar10.f1729a, aVar10.f1730b, aVar10.f1731c);
        aVar9.n();
        meshPartBuilder.rect(aVar5, aVar, aVar3, aVar7, aVar9);
        aVar9.s(-1.0f);
        meshPartBuilder.rect(aVar2, aVar6, aVar8, aVar4, aVar9);
        aVar9.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
        aVar9.k(aVar6, 0.5f);
        aVar10.u(aVar3);
        aVar10.k(aVar8, 0.5f);
        aVar9.v(aVar10.f1729a, aVar10.f1730b, aVar10.f1731c);
        aVar9.n();
        meshPartBuilder.rect(aVar5, aVar6, aVar2, aVar, aVar9);
        aVar9.s(-1.0f);
        meshPartBuilder.rect(aVar3, aVar4, aVar8, aVar7, aVar9);
    }

    public static void build(MeshPartBuilder meshPartBuilder, Matrix4 matrix4) {
        com.badlogic.gdx.math.a aVarObtainV3 = BaseShapeBuilder.obtainV3();
        aVarObtainV3.t(-0.5f, -0.5f, -0.5f);
        aVarObtainV3.m(matrix4);
        com.badlogic.gdx.math.a aVarObtainV32 = BaseShapeBuilder.obtainV3();
        aVarObtainV32.t(-0.5f, 0.5f, -0.5f);
        aVarObtainV32.m(matrix4);
        com.badlogic.gdx.math.a aVarObtainV33 = BaseShapeBuilder.obtainV3();
        aVarObtainV33.t(0.5f, -0.5f, -0.5f);
        aVarObtainV33.m(matrix4);
        com.badlogic.gdx.math.a aVarObtainV34 = BaseShapeBuilder.obtainV3();
        aVarObtainV34.t(0.5f, 0.5f, -0.5f);
        aVarObtainV34.m(matrix4);
        com.badlogic.gdx.math.a aVarObtainV35 = BaseShapeBuilder.obtainV3();
        aVarObtainV35.t(-0.5f, -0.5f, 0.5f);
        aVarObtainV35.m(matrix4);
        com.badlogic.gdx.math.a aVarObtainV36 = BaseShapeBuilder.obtainV3();
        aVarObtainV36.t(-0.5f, 0.5f, 0.5f);
        aVarObtainV36.m(matrix4);
        com.badlogic.gdx.math.a aVarObtainV37 = BaseShapeBuilder.obtainV3();
        aVarObtainV37.t(0.5f, -0.5f, 0.5f);
        aVarObtainV37.m(matrix4);
        com.badlogic.gdx.math.a aVarObtainV38 = BaseShapeBuilder.obtainV3();
        aVarObtainV38.t(0.5f, 0.5f, 0.5f);
        aVarObtainV38.m(matrix4);
        build(meshPartBuilder, aVarObtainV3, aVarObtainV32, aVarObtainV33, aVarObtainV34, aVarObtainV35, aVarObtainV36, aVarObtainV37, aVarObtainV38);
        BaseShapeBuilder.freeAll();
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4) {
        build(meshPartBuilder, 0.0f, 0.0f, 0.0f, f2, f3, f4);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f5 * 0.5f;
        float f9 = f6 * 0.5f;
        float f10 = 0.5f * f7;
        float f11 = f2 - f8;
        float f12 = f3 - f9;
        float f13 = f4 - f10;
        float f14 = f2 + f8;
        float f15 = f3 + f9;
        float f16 = f4 + f10;
        com.badlogic.gdx.math.a aVarObtainV3 = BaseShapeBuilder.obtainV3();
        aVarObtainV3.t(f11, f12, f13);
        com.badlogic.gdx.math.a aVarObtainV32 = BaseShapeBuilder.obtainV3();
        aVarObtainV32.t(f11, f15, f13);
        com.badlogic.gdx.math.a aVarObtainV33 = BaseShapeBuilder.obtainV3();
        aVarObtainV33.t(f14, f12, f13);
        com.badlogic.gdx.math.a aVarObtainV34 = BaseShapeBuilder.obtainV3();
        aVarObtainV34.t(f14, f15, f13);
        com.badlogic.gdx.math.a aVarObtainV35 = BaseShapeBuilder.obtainV3();
        aVarObtainV35.t(f11, f12, f16);
        com.badlogic.gdx.math.a aVarObtainV36 = BaseShapeBuilder.obtainV3();
        aVarObtainV36.t(f11, f15, f16);
        com.badlogic.gdx.math.a aVarObtainV37 = BaseShapeBuilder.obtainV3();
        aVarObtainV37.t(f14, f12, f16);
        com.badlogic.gdx.math.a aVarObtainV38 = BaseShapeBuilder.obtainV3();
        aVarObtainV38.t(f14, f15, f16);
        build(meshPartBuilder, aVarObtainV3, aVarObtainV32, aVarObtainV33, aVarObtainV34, aVarObtainV35, aVarObtainV36, aVarObtainV37, aVarObtainV38);
        BaseShapeBuilder.freeAll();
    }
}
