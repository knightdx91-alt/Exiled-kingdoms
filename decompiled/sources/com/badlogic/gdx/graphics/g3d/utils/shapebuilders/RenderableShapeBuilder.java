package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.k;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RenderableShapeBuilder extends BaseShapeBuilder {
    private static final int FLOAT_BYTES = 4;
    private static short[] indices;
    private static float[] vertices;
    private static final RenderablePool renderablesPool = new RenderablePool();
    private static final a<Renderable> renderables = new a<>();

    private static class RenderablePool extends k<Renderable> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.utils.c0
        public Renderable newObject() {
            return new Renderable();
        }

        @Override // com.badlogic.gdx.utils.k, com.badlogic.gdx.utils.c0
        public Renderable obtain() {
            Renderable renderable = (Renderable) super.obtain();
            renderable.environment = null;
            renderable.material = null;
            renderable.meshPart.set("", null, 0, 0, 0);
            renderable.shader = null;
            renderable.userData = null;
            return renderable;
        }
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder, RenderableProvider renderableProvider, float f2) {
        buildNormals(meshPartBuilder, renderableProvider, f2, BaseShapeBuilder.tmpColor0.set(0.0f, 0.0f, 1.0f, 1.0f), BaseShapeBuilder.tmpColor1.set(1.0f, 0.0f, 0.0f, 1.0f), BaseShapeBuilder.tmpColor2.set(0.0f, 1.0f, 0.0f, 1.0f));
    }

    private static void ensureIndicesCapacity(int i2) {
        short[] sArr = indices;
        if (sArr == null || sArr.length < i2) {
            indices = new short[i2];
        }
    }

    private static void ensureVerticesCapacity(int i2) {
        float[] fArr = vertices;
        if (fArr == null || fArr.length < i2) {
            vertices = new float[i2];
        }
    }

    private static short maxVerticeInIndices() {
        short s = Short.MIN_VALUE;
        int i2 = 0;
        while (true) {
            short[] sArr = indices;
            if (i2 >= sArr.length) {
                return s;
            }
            short s2 = sArr[i2];
            if (s2 > s) {
                s = s2;
            }
            i2++;
        }
    }

    private static short minVerticeInIndices() {
        short s = Short.MAX_VALUE;
        int i2 = 0;
        while (true) {
            short[] sArr = indices;
            if (i2 >= sArr.length) {
                return s;
            }
            short s2 = sArr[i2];
            if (s2 < s) {
                s = s2;
            }
            i2++;
        }
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder, RenderableProvider renderableProvider, float f2, Color color, Color color2, Color color3) {
        a<Renderable> aVar = renderables;
        renderableProvider.getRenderables(aVar, renderablesPool);
        a.b<Renderable> it = aVar.iterator();
        while (it.hasNext()) {
            buildNormals(meshPartBuilder, it.next(), f2, color, color2, color3);
        }
        renderablesPool.flush();
        renderables.clear();
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder, Renderable renderable, float f2, Color color, Color color2, Color color3) {
        int iMaxVerticeInIndices;
        int iMinVerticeInIndices;
        int i2;
        int i3;
        Mesh mesh = renderable.meshPart.mesh;
        int i4 = mesh.getVertexAttribute(1) != null ? mesh.getVertexAttribute(1).offset / 4 : -1;
        int i5 = mesh.getVertexAttribute(8) != null ? mesh.getVertexAttribute(8).offset / 4 : -1;
        int i6 = mesh.getVertexAttribute(VertexAttributes.Usage.Tangent) != null ? mesh.getVertexAttribute(VertexAttributes.Usage.Tangent).offset / 4 : -1;
        int i7 = mesh.getVertexAttribute(256) != null ? mesh.getVertexAttribute(256).offset / 4 : -1;
        int vertexSize = mesh.getVertexSize() / 4;
        if (mesh.getNumIndices() > 0) {
            ensureIndicesCapacity(mesh.getNumIndices());
            MeshPart meshPart = renderable.meshPart;
            mesh.getIndices(meshPart.offset, meshPart.size, indices, 0);
            iMinVerticeInIndices = minVerticeInIndices();
            iMaxVerticeInIndices = maxVerticeInIndices() - iMinVerticeInIndices;
        } else {
            MeshPart meshPart2 = renderable.meshPart;
            int i8 = meshPart2.offset;
            iMaxVerticeInIndices = meshPart2.size;
            iMinVerticeInIndices = i8;
        }
        int i9 = iMaxVerticeInIndices * vertexSize;
        ensureVerticesCapacity(i9);
        mesh.getVertices(iMinVerticeInIndices * vertexSize, i9, vertices, 0);
        while (iMinVerticeInIndices < iMaxVerticeInIndices) {
            int i10 = iMinVerticeInIndices * vertexSize;
            com.badlogic.gdx.math.a aVar = BaseShapeBuilder.tmpV0;
            float[] fArr = vertices;
            int i11 = i10 + i4;
            aVar.t(fArr[i11], fArr[i11 + 1], fArr[i11 + 2]);
            if (i5 != -1) {
                com.badlogic.gdx.math.a aVar2 = BaseShapeBuilder.tmpV1;
                float[] fArr2 = vertices;
                int i12 = i10 + i5;
                i2 = i4;
                aVar2.t(fArr2[i12], fArr2[i12 + 1], fArr2[i12 + 2]);
                com.badlogic.gdx.math.a aVar3 = BaseShapeBuilder.tmpV2;
                aVar3.u(aVar);
                aVar2.s(f2);
                aVar3.b(aVar2);
                i3 = -1;
            } else {
                i2 = i4;
                i3 = -1;
            }
            if (i6 != i3) {
                com.badlogic.gdx.math.a aVar4 = BaseShapeBuilder.tmpV3;
                float[] fArr3 = vertices;
                int i13 = i10 + i6;
                aVar4.t(fArr3[i13], fArr3[i13 + 1], fArr3[i13 + 2]);
                com.badlogic.gdx.math.a aVar5 = BaseShapeBuilder.tmpV4;
                aVar5.u(aVar);
                aVar4.s(f2);
                aVar5.b(aVar4);
                i3 = -1;
            }
            if (i7 != i3) {
                com.badlogic.gdx.math.a aVar6 = BaseShapeBuilder.tmpV5;
                float[] fArr4 = vertices;
                int i14 = i10 + i7;
                aVar6.t(fArr4[i14], fArr4[i14 + 1], fArr4[i14 + 2]);
                com.badlogic.gdx.math.a aVar7 = BaseShapeBuilder.tmpV6;
                aVar7.u(aVar);
                aVar6.s(f2);
                aVar7.b(aVar6);
            }
            aVar.m(renderable.worldTransform);
            com.badlogic.gdx.math.a aVar8 = BaseShapeBuilder.tmpV2;
            aVar8.m(renderable.worldTransform);
            com.badlogic.gdx.math.a aVar9 = BaseShapeBuilder.tmpV4;
            aVar9.m(renderable.worldTransform);
            com.badlogic.gdx.math.a aVar10 = BaseShapeBuilder.tmpV6;
            aVar10.m(renderable.worldTransform);
            if (i5 != -1) {
                meshPartBuilder.setColor(color);
                meshPartBuilder.line(aVar, aVar8);
            }
            if (i6 != -1) {
                meshPartBuilder.setColor(color2);
                meshPartBuilder.line(aVar, aVar9);
            }
            if (i7 != -1) {
                meshPartBuilder.setColor(color3);
                meshPartBuilder.line(aVar, aVar10);
            }
            iMinVerticeInIndices++;
            i4 = i2;
        }
    }
}
