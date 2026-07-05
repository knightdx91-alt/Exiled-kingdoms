package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import a0.j;
import a0.q;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class EllipseShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8) {
        build(meshPartBuilder, f2, i2, f3, f4, f5, f6, f7, f8, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, a aVar, a aVar2) {
        build(meshPartBuilder, f2, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, a aVar, a aVar2, a aVar3, a aVar4) {
        build(meshPartBuilder, f2, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, aVar3.f1729a, aVar3.f1730b, aVar3.f1731c, aVar4.f1729a, aVar4.f1730b, aVar4.f1731c);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        build(meshPartBuilder, f2, i2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        float f11 = f2 * 2.0f;
        build(meshPartBuilder, f11, f11, i2, f3, f4, f5, f6, f7, f8, f9, f10);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, a aVar, a aVar2, float f3, float f4) {
        build(meshPartBuilder, f2, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, f3, f4);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, a aVar, a aVar2, a aVar3, a aVar4, float f3, float f4) {
        build(meshPartBuilder, f2, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, aVar3.f1729a, aVar3.f1730b, aVar3.f1731c, aVar4.f1729a, aVar4.f1730b, aVar4.f1731c, f3, f4);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        float f17 = f2 * 2.0f;
        build(meshPartBuilder, f17, f17, 0.0f, 0.0f, i2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9) {
        build(meshPartBuilder, f2, f3, i2, f4, f5, f6, f7, f8, f9, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, a aVar, a aVar2) {
        build(meshPartBuilder, f2, f3, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, a aVar, a aVar2, a aVar3, a aVar4) {
        build(meshPartBuilder, f2, f3, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, aVar3.f1729a, aVar3.f1730b, aVar3.f1731c, aVar4.f1729a, aVar4.f1730b, aVar4.f1731c);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        build(meshPartBuilder, f2, f3, i2, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        build(meshPartBuilder, f2, f3, 0.0f, 0.0f, i2, f4, f5, f6, f7, f8, f9, f10, f11);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, a aVar, a aVar2, float f4, float f5) {
        build(meshPartBuilder, f2, f3, 0.0f, 0.0f, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, f4, f5);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, a aVar, a aVar2, a aVar3, a aVar4, float f4, float f5) {
        build(meshPartBuilder, f2, f3, 0.0f, 0.0f, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, aVar3.f1729a, aVar3.f1730b, aVar3.f1731c, aVar4.f1729a, aVar4.f1730b, aVar4.f1731c, f4, f5);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        build(meshPartBuilder, f2, f3, 0.0f, 0.0f, i2, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        a aVar = BaseShapeBuilder.tmpV1;
        aVar.t(f9, f10, f11);
        aVar.c(0.0f, 0.0f, 1.0f);
        a aVar2 = BaseShapeBuilder.tmpV2;
        aVar2.t(f9, f10, f11);
        aVar2.c(0.0f, 1.0f, 0.0f);
        if (aVar2.j() > aVar.j()) {
            aVar.u(aVar2);
        }
        aVar.n();
        aVar2.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
        aVar2.c(f9, f10, f11);
        aVar2.n();
        build(meshPartBuilder, f2, f3, f4, f5, i2, f6, f7, f8, f9, f10, f11, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, f12, f13);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11) {
        build(meshPartBuilder, f2, f3, f4, f5, i2, f6, f7, f8, f9, f10, f11, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, float f5, int i2, a aVar, a aVar2) {
        build(meshPartBuilder, f2, f3, f4, f5, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19) {
        a aVar;
        short s;
        short s2;
        char c2;
        short s3;
        int i3 = i2;
        float f20 = f6;
        if (f4 <= 0.0f || f5 <= 0.0f) {
            meshPartBuilder.ensureVertices(i3 + 2);
            meshPartBuilder.ensureTriangleIndices(i3);
        } else if (f4 == f2 && f5 == f3) {
            int i4 = i3 + 1;
            meshPartBuilder.ensureVertices(i4);
            meshPartBuilder.ensureIndices(i4);
            if (meshPartBuilder.getPrimitiveType() != 1) {
                throw new m("Incorrect primitive type : expect GL_LINES because innerWidth == width && innerHeight == height");
            }
        } else {
            int i5 = i3 + 1;
            meshPartBuilder.ensureVertices(i5 * 2);
            meshPartBuilder.ensureRectangleIndices(i5);
        }
        float f21 = f18 * 0.017453292f;
        float f22 = ((f19 - f18) * 0.017453292f) / i3;
        a aVar2 = BaseShapeBuilder.tmpV1;
        aVar2.t(f12, f13, f14);
        aVar2.s(f2 * 0.5f);
        a aVar3 = BaseShapeBuilder.tmpV2;
        aVar3.t(f15, f16, f17);
        aVar3.s(f3 * 0.5f);
        a aVar4 = BaseShapeBuilder.tmpV3;
        aVar4.t(f12, f13, f14);
        aVar4.s(f4 * 0.5f);
        a aVar5 = BaseShapeBuilder.tmpV4;
        aVar5.t(f15, f16, f17);
        aVar5.s(f5 * 0.5f);
        MeshPartBuilder.VertexInfo vertexInfo = BaseShapeBuilder.vertTmp3.set(null, null, null, null);
        vertexInfo.hasNormal = true;
        vertexInfo.hasPosition = true;
        vertexInfo.hasUV = true;
        q qVar = vertexInfo.uv;
        qVar.f91a = 0.5f;
        qVar.f92b = 0.5f;
        vertexInfo.position.t(f20, f7, f8);
        vertexInfo.normal.t(f9, f10, f11);
        MeshPartBuilder.VertexInfo vertexInfo2 = BaseShapeBuilder.vertTmp4.set(null, null, null, null);
        vertexInfo2.hasNormal = true;
        vertexInfo2.hasPosition = true;
        vertexInfo2.hasUV = true;
        q qVar2 = vertexInfo2.uv;
        qVar2.f91a = 0.5f;
        qVar2.f92b = 0.5f;
        vertexInfo2.position.t(f20, f7, f8);
        vertexInfo2.normal.t(f9, f10, f11);
        short sVertex = meshPartBuilder.vertex(vertexInfo2);
        float f23 = (f4 / f2) * 0.5f;
        float f24 = (f5 / f3) * 0.5f;
        int i6 = 0;
        short s4 = 0;
        short s5 = 0;
        short s6 = 0;
        while (i6 <= i3) {
            float f25 = (i6 * f22) + f21;
            float fB = j.b(f25);
            float fJ = j.j(f25);
            a aVar6 = vertexInfo2.position;
            aVar6.t(f20, f7, f8);
            short s7 = sVertex;
            float f26 = f24;
            float f27 = f23;
            a aVar7 = aVar5;
            aVar6.a((aVar3.f1729a * fJ) + (aVar2.f1729a * fB), (aVar3.f1730b * fJ) + (aVar2.f1730b * fB), (aVar3.f1731c * fJ) + (aVar2.f1731c * fB));
            q qVar3 = vertexInfo2.uv;
            qVar3.f91a = (fB * 0.5f) + 0.5f;
            qVar3.f92b = (fJ * 0.5f) + 0.5f;
            short sVertex2 = meshPartBuilder.vertex(vertexInfo2);
            if (f4 <= 0.0f || f5 <= 0.0f) {
                aVar = aVar7;
                s = s5;
                s2 = s6;
                c2 = 0;
                s3 = s7;
                if (i6 != 0) {
                    meshPartBuilder.triangle(sVertex2, s4, s3);
                }
            } else if (f4 == f2 && f5 == f3) {
                if (i6 != 0) {
                    meshPartBuilder.line(sVertex2, s4);
                }
                s3 = s7;
                aVar = aVar7;
                s = s5;
                s2 = s6;
                c2 = 0;
            } else {
                a aVar8 = vertexInfo.position;
                aVar8.t(f20, f7, f8);
                aVar = aVar7;
                aVar8.a((aVar.f1729a * fJ) + (aVar4.f1729a * fB), (aVar.f1730b * fJ) + (aVar4.f1730b * fB), (aVar.f1731c * fJ) + (aVar4.f1731c * fB));
                q qVar4 = vertexInfo.uv;
                c2 = 0;
                qVar4.f91a = (f27 * fB) + 0.5f;
                qVar4.f92b = (f26 * fJ) + 0.5f;
                short sVertex3 = meshPartBuilder.vertex(vertexInfo);
                if (i6 != 0) {
                    meshPartBuilder.rect(sVertex3, sVertex2, s6, s5);
                }
                s6 = sVertex2;
                s5 = sVertex3;
                s3 = s7;
                i6++;
                f23 = f27;
                s4 = sVertex2;
                aVar5 = aVar;
                i3 = i2;
                f24 = f26;
                sVertex = s3;
                f20 = f6;
            }
            s5 = s;
            s6 = s2;
            i6++;
            f23 = f27;
            s4 = sVertex2;
            aVar5 = aVar;
            i3 = i2;
            f24 = f26;
            sVertex = s3;
            f20 = f6;
        }
    }
}
