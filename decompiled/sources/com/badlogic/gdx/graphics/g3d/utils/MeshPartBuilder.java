package com.badlogic.gdx.graphics.g3d.utils;

import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface MeshPartBuilder {
    void addMesh(Mesh mesh);

    void addMesh(Mesh mesh, int i2, int i3);

    void addMesh(MeshPart meshPart);

    void addMesh(float[] fArr, short[] sArr);

    void addMesh(float[] fArr, short[] sArr, int i2, int i3);

    @Deprecated
    void arrow(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i2);

    @Deprecated
    void box(float f2, float f3, float f4);

    @Deprecated
    void box(float f2, float f3, float f4, float f5, float f6, float f7);

    @Deprecated
    void box(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3, VertexInfo vertexInfo4, VertexInfo vertexInfo5, VertexInfo vertexInfo6, VertexInfo vertexInfo7, VertexInfo vertexInfo8);

    @Deprecated
    void box(Matrix4 matrix4);

    @Deprecated
    void box(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8);

    @Deprecated
    void capsule(float f2, float f3, int i2);

    @Deprecated
    void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8);

    @Deprecated
    void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10);

    @Deprecated
    void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14);

    @Deprecated
    void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16);

    @Deprecated
    void circle(float f2, int i2, a aVar, a aVar2);

    @Deprecated
    void circle(float f2, int i2, a aVar, a aVar2, float f3, float f4);

    @Deprecated
    void circle(float f2, int i2, a aVar, a aVar2, a aVar3, a aVar4);

    @Deprecated
    void circle(float f2, int i2, a aVar, a aVar2, a aVar3, a aVar4, float f3, float f4);

    @Deprecated
    void cone(float f2, float f3, float f4, int i2);

    @Deprecated
    void cone(float f2, float f3, float f4, int i2, float f5, float f6);

    @Deprecated
    void cylinder(float f2, float f3, float f4, int i2);

    @Deprecated
    void cylinder(float f2, float f3, float f4, int i2, float f5, float f6);

    @Deprecated
    void cylinder(float f2, float f3, float f4, int i2, float f5, float f6, boolean z2);

    @Deprecated
    void ellipse(float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11);

    @Deprecated
    void ellipse(float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13);

    @Deprecated
    void ellipse(float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19);

    @Deprecated
    void ellipse(float f2, float f3, float f4, float f5, int i2, a aVar, a aVar2);

    @Deprecated
    void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9);

    @Deprecated
    void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11);

    @Deprecated
    void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15);

    @Deprecated
    void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17);

    @Deprecated
    void ellipse(float f2, float f3, int i2, a aVar, a aVar2);

    @Deprecated
    void ellipse(float f2, float f3, int i2, a aVar, a aVar2, float f4, float f5);

    @Deprecated
    void ellipse(float f2, float f3, int i2, a aVar, a aVar2, a aVar3, a aVar4);

    @Deprecated
    void ellipse(float f2, float f3, int i2, a aVar, a aVar2, a aVar3, a aVar4, float f4, float f5);

    void ensureCapacity(int i2, int i3);

    void ensureIndices(int i2);

    void ensureRectangleIndices(int i2);

    void ensureTriangleIndices(int i2);

    void ensureVertices(int i2);

    VertexAttributes getAttributes();

    MeshPart getMeshPart();

    int getPrimitiveType();

    Matrix4 getVertexTransform(Matrix4 matrix4);

    void index(short s);

    void index(short s, short s2);

    void index(short s, short s2, short s3);

    void index(short s, short s2, short s3, short s4);

    void index(short s, short s2, short s3, short s4, short s5, short s6);

    void index(short s, short s2, short s3, short s4, short s5, short s6, short s7, short s8);

    boolean isVertexTransformationEnabled();

    short lastIndex();

    void line(float f2, float f3, float f4, float f5, float f6, float f7);

    void line(VertexInfo vertexInfo, VertexInfo vertexInfo2);

    void line(a aVar, Color color, a aVar2, Color color2);

    void line(a aVar, a aVar2);

    void line(short s, short s2);

    @Deprecated
    void patch(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int i2, int i3);

    @Deprecated
    void patch(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3, VertexInfo vertexInfo4, int i2, int i3);

    @Deprecated
    void patch(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, int i2, int i3);

    void rect(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16);

    void rect(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3, VertexInfo vertexInfo4);

    void rect(a aVar, a aVar2, a aVar3, a aVar4, a aVar5);

    void rect(short s, short s2, short s3, short s4);

    void setColor(float f2, float f3, float f4, float f5);

    void setColor(Color color);

    void setUVRange(float f2, float f3, float f4, float f5);

    void setUVRange(TextureRegion textureRegion);

    void setVertexTransform(Matrix4 matrix4);

    void setVertexTransformationEnabled(boolean z2);

    @Deprecated
    void sphere(float f2, float f3, float f4, int i2, int i3);

    @Deprecated
    void sphere(float f2, float f3, float f4, int i2, int i3, float f5, float f6, float f7, float f8);

    @Deprecated
    void sphere(Matrix4 matrix4, float f2, float f3, float f4, int i2, int i3);

    @Deprecated
    void sphere(Matrix4 matrix4, float f2, float f3, float f4, int i2, int i3, float f5, float f6, float f7, float f8);

    void triangle(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3);

    void triangle(a aVar, Color color, a aVar2, Color color2, a aVar3, Color color3);

    void triangle(a aVar, a aVar2, a aVar3);

    void triangle(short s, short s2, short s3);

    short vertex(VertexInfo vertexInfo);

    short vertex(a aVar, a aVar2, Color color, q qVar);

    short vertex(float... fArr);

    public static class VertexInfo implements c0.a {
        public boolean hasColor;
        public boolean hasNormal;
        public boolean hasPosition;
        public boolean hasUV;
        public final a position = new a();
        public final a normal = new a(0.0f, 1.0f, 0.0f);
        public final Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        public final q uv = new q();

        public VertexInfo lerp(VertexInfo vertexInfo, float f2) {
            if (this.hasPosition && vertexInfo.hasPosition) {
                this.position.k(vertexInfo.position, f2);
            }
            if (this.hasNormal && vertexInfo.hasNormal) {
                this.normal.k(vertexInfo.normal, f2);
            }
            if (this.hasColor && vertexInfo.hasColor) {
                this.color.lerp(vertexInfo.color, f2);
            }
            if (this.hasUV && vertexInfo.hasUV) {
                q qVar = this.uv;
                q qVar2 = vertexInfo.uv;
                float f3 = 1.0f - f2;
                qVar.f91a = (qVar2.f91a * f2) + (qVar.f91a * f3);
                qVar.f92b = (qVar2.f92b * f2) + (qVar.f92b * f3);
            }
            return this;
        }

        @Override // com.badlogic.gdx.utils.c0.a
        public void reset() {
            this.position.t(0.0f, 0.0f, 0.0f);
            this.normal.t(0.0f, 1.0f, 0.0f);
            this.color.set(1.0f, 1.0f, 1.0f, 1.0f);
            q qVar = this.uv;
            qVar.f91a = 0.0f;
            qVar.f92b = 0.0f;
        }

        public VertexInfo set(a aVar, a aVar2, Color color, q qVar) {
            reset();
            boolean z2 = aVar != null;
            this.hasPosition = z2;
            if (z2) {
                this.position.u(aVar);
            }
            boolean z3 = aVar2 != null;
            this.hasNormal = z3;
            if (z3) {
                this.normal.u(aVar2);
            }
            boolean z4 = color != null;
            this.hasColor = z4;
            if (z4) {
                this.color.set(color);
            }
            boolean z5 = qVar != null;
            this.hasUV = z5;
            if (z5) {
                this.uv.b(qVar);
            }
            return this;
        }

        public VertexInfo setCol(float f2, float f3, float f4, float f5) {
            this.color.set(f2, f3, f4, f5);
            this.hasColor = true;
            return this;
        }

        public VertexInfo setNor(float f2, float f3, float f4) {
            this.normal.t(f2, f3, f4);
            this.hasNormal = true;
            return this;
        }

        public VertexInfo setPos(float f2, float f3, float f4) {
            this.position.t(f2, f3, f4);
            this.hasPosition = true;
            return this;
        }

        public VertexInfo setUV(float f2, float f3) {
            q qVar = this.uv;
            qVar.f91a = f2;
            qVar.f92b = f3;
            this.hasUV = true;
            return this;
        }

        public VertexInfo setCol(Color color) {
            boolean z2 = color != null;
            this.hasColor = z2;
            if (z2) {
                this.color.set(color);
            }
            return this;
        }

        public VertexInfo setNor(a aVar) {
            boolean z2 = aVar != null;
            this.hasNormal = z2;
            if (z2) {
                this.normal.u(aVar);
            }
            return this;
        }

        public VertexInfo setPos(a aVar) {
            boolean z2 = aVar != null;
            this.hasPosition = z2;
            if (z2) {
                this.position.u(aVar);
            }
            return this;
        }

        public VertexInfo setUV(q qVar) {
            boolean z2 = qVar != null;
            this.hasUV = z2;
            if (z2) {
                this.uv.b(qVar);
            }
            return this;
        }

        public VertexInfo set(VertexInfo vertexInfo) {
            if (vertexInfo == null) {
                return set(null, null, null, null);
            }
            this.hasPosition = vertexInfo.hasPosition;
            this.position.u(vertexInfo.position);
            this.hasNormal = vertexInfo.hasNormal;
            this.normal.u(vertexInfo.normal);
            this.hasColor = vertexInfo.hasColor;
            this.color.set(vertexInfo.color);
            this.hasUV = vertexInfo.hasUV;
            this.uv.b(vertexInfo.uv);
            return this;
        }
    }
}
