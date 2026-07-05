package com.badlogic.gdx.graphics.g3d.utils;

import a0.k;
import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ArrowShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CapsuleShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CylinderShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.EllipseShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.PatchShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.SphereShapeBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.j;
import com.badlogic.gdx.utils.j0;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.p;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MeshBuilder implements MeshPartBuilder {
    public static final int MAX_INDEX = 65535;
    public static final int MAX_VERTICES = 65536;
    private VertexAttributes attributes;
    private int biNorOffset;
    private int colOffset;
    private int colSize;
    private int cpOffset;
    private int istart;
    private int norOffset;
    private MeshPart part;
    private int posOffset;
    private int posSize;
    private int primitiveType;
    private int stride;
    private int tangentOffset;
    private int uvOffset;
    private float[] vertex;
    private int vindex;
    private static final j0 tmpIndices = new j0();
    private static final j tmpVertices = new j();
    private static final a vTmp = new a();
    private static p indicesMap = null;
    private final MeshPartBuilder.VertexInfo vertTmp1 = new MeshPartBuilder.VertexInfo();
    private final MeshPartBuilder.VertexInfo vertTmp2 = new MeshPartBuilder.VertexInfo();
    private final MeshPartBuilder.VertexInfo vertTmp3 = new MeshPartBuilder.VertexInfo();
    private final MeshPartBuilder.VertexInfo vertTmp4 = new MeshPartBuilder.VertexInfo();
    private final Color tempC1 = new Color();
    private j vertices = new j();
    private j0 indices = new j0();
    private com.badlogic.gdx.utils.a<MeshPart> parts = new com.badlogic.gdx.utils.a<>();
    private final Color color = new Color(Color.WHITE);
    private boolean hasColor = false;
    private float uOffset = 0.0f;
    private float uScale = 1.0f;
    private float vOffset = 0.0f;
    private float vScale = 1.0f;
    private boolean hasUVTransform = false;
    private boolean vertexTransformationEnabled = false;
    private final Matrix4 positionTransform = new Matrix4();
    private final k normalTransform = new k();
    private final b0.a bounds = new b0.a();
    private int lastIndex = -1;
    private final a tmpNormal = new a();

    private final void addVertex(float[] fArr, int i2) {
        int i3;
        j jVar = this.vertices;
        int i4 = jVar.f1824b;
        jVar.c(fArr, i2, this.stride);
        int i5 = this.vindex;
        this.vindex = i5 + 1;
        this.lastIndex = i5;
        if (this.vertexTransformationEnabled) {
            transformPosition(this.vertices.f1823a, this.posOffset + i4, this.posSize, this.positionTransform);
            int i6 = this.norOffset;
            if (i6 >= 0) {
                transformNormal(this.vertices.f1823a, i6 + i4, 3, this.normalTransform);
            }
            int i7 = this.biNorOffset;
            if (i7 >= 0) {
                transformNormal(this.vertices.f1823a, i7 + i4, 3, this.normalTransform);
            }
            int i8 = this.tangentOffset;
            if (i8 >= 0) {
                transformNormal(this.vertices.f1823a, i8 + i4, 3, this.normalTransform);
            }
        }
        float[] fArr2 = this.vertices.f1823a;
        int i9 = this.posOffset + i4;
        float f2 = fArr2[i9];
        int i10 = this.posSize;
        this.bounds.a(f2, i10 > 1 ? fArr2[i9 + 1] : 0.0f, i10 > 2 ? fArr2[i9 + 2] : 0.0f);
        if (this.hasColor) {
            int i11 = this.colOffset;
            if (i11 >= 0) {
                float[] fArr3 = this.vertices.f1823a;
                int i12 = i11 + i4;
                float f3 = fArr3[i12];
                Color color = this.color;
                fArr3[i12] = f3 * color.f1680r;
                int i13 = i12 + 1;
                fArr3[i13] = fArr3[i13] * color.f1679g;
                int i14 = i12 + 2;
                fArr3[i14] = fArr3[i14] * color.f1678b;
                if (this.colSize > 3) {
                    int i15 = i12 + 3;
                    fArr3[i15] = fArr3[i15] * color.f1677a;
                }
            } else {
                int i16 = this.cpOffset;
                if (i16 >= 0) {
                    Color.abgr8888ToColor(this.tempC1, this.vertices.f1823a[i16 + i4]);
                    this.vertices.f1823a[this.cpOffset + i4] = this.tempC1.mul(this.color).toFloatBits();
                }
            }
        }
        if (!this.hasUVTransform || (i3 = this.uvOffset) < 0) {
            return;
        }
        float[] fArr4 = this.vertices.f1823a;
        int i17 = i4 + i3;
        fArr4[i17] = (this.uScale * fArr4[i17]) + this.uOffset;
        int i18 = i17 + 1;
        fArr4[i18] = (this.vScale * fArr4[i18]) + this.vOffset;
    }

    public static VertexAttributes createAttributes(long j2) {
        com.badlogic.gdx.utils.a aVar = new com.badlogic.gdx.utils.a();
        if ((j2 & 1) == 1) {
            aVar.a(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE));
        }
        if ((j2 & 2) == 2) {
            aVar.a(new VertexAttribute(2, 4, ShaderProgram.COLOR_ATTRIBUTE));
        }
        if ((j2 & 4) == 4) {
            aVar.a(new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE));
        }
        if ((j2 & 8) == 8) {
            aVar.a(new VertexAttribute(8, 3, ShaderProgram.NORMAL_ATTRIBUTE));
        }
        if ((j2 & 16) == 16) {
            aVar.a(new VertexAttribute(16, 2, "a_texCoord0"));
        }
        int i2 = aVar.f1750b;
        VertexAttribute[] vertexAttributeArr = new VertexAttribute[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            vertexAttributeArr[i3] = (VertexAttribute) aVar.get(i3);
        }
        return new VertexAttributes(vertexAttributeArr);
    }

    private void endpart() {
        MeshPart meshPart = this.part;
        if (meshPart != null) {
            this.bounds.d(meshPart.center);
            b0.a aVar = this.bounds;
            a aVar2 = this.part.halfExtents;
            aVar.e(aVar2);
            aVar2.s(0.5f);
            MeshPart meshPart2 = this.part;
            meshPart2.radius = meshPart2.halfExtents.h();
            this.bounds.f();
            MeshPart meshPart3 = this.part;
            int i2 = this.istart;
            meshPart3.offset = i2;
            int i3 = this.indices.f1827b;
            meshPart3.size = i3 - i2;
            this.istart = i3;
            this.part = null;
        }
    }

    private static final void transformNormal(float[] fArr, int i2, int i3, k kVar) {
        if (i3 > 2) {
            a aVar = vTmp;
            int i4 = i2 + 1;
            int i5 = i2 + 2;
            aVar.t(fArr[i2], fArr[i4], fArr[i5]);
            aVar.l(kVar);
            aVar.n();
            fArr[i2] = aVar.f1729a;
            fArr[i4] = aVar.f1730b;
            fArr[i5] = aVar.f1731c;
            return;
        }
        if (i3 <= 1) {
            a aVar2 = vTmp;
            aVar2.t(fArr[i2], 0.0f, 0.0f);
            aVar2.l(kVar);
            aVar2.n();
            fArr[i2] = aVar2.f1729a;
            return;
        }
        a aVar3 = vTmp;
        int i6 = i2 + 1;
        aVar3.t(fArr[i2], fArr[i6], 0.0f);
        aVar3.l(kVar);
        aVar3.n();
        fArr[i2] = aVar3.f1729a;
        fArr[i6] = aVar3.f1730b;
    }

    private static final void transformPosition(float[] fArr, int i2, int i3, Matrix4 matrix4) {
        if (i3 > 2) {
            a aVar = vTmp;
            int i4 = i2 + 1;
            int i5 = i2 + 2;
            aVar.t(fArr[i2], fArr[i4], fArr[i5]);
            aVar.m(matrix4);
            fArr[i2] = aVar.f1729a;
            fArr[i4] = aVar.f1730b;
            fArr[i5] = aVar.f1731c;
            return;
        }
        if (i3 <= 1) {
            a aVar2 = vTmp;
            aVar2.t(fArr[i2], 0.0f, 0.0f);
            aVar2.m(matrix4);
            fArr[i2] = aVar2.f1729a;
            return;
        }
        a aVar3 = vTmp;
        int i6 = i2 + 1;
        aVar3.t(fArr[i2], fArr[i6], 0.0f);
        aVar3.m(matrix4);
        fArr[i2] = aVar3.f1729a;
        fArr[i6] = aVar3.f1730b;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(Mesh mesh) {
        addMesh(mesh, 0, mesh.getNumIndices());
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void arrow(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i2) {
        ArrowShapeBuilder.build(this, f2, f3, f4, f5, f6, f7, f8, f9, i2);
    }

    public void begin(long j2) {
        begin(createAttributes(j2), -1);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2, MeshPartBuilder.VertexInfo vertexInfo3, MeshPartBuilder.VertexInfo vertexInfo4, MeshPartBuilder.VertexInfo vertexInfo5, MeshPartBuilder.VertexInfo vertexInfo6, MeshPartBuilder.VertexInfo vertexInfo7, MeshPartBuilder.VertexInfo vertexInfo8) {
        BoxShapeBuilder.build(this, vertexInfo, vertexInfo2, vertexInfo3, vertexInfo4, vertexInfo5, vertexInfo6, vertexInfo7, vertexInfo8);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void capsule(float f2, float f3, int i2) {
        CapsuleShapeBuilder.build(this, f2, f3, i2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8) {
        EllipseShapeBuilder.build(this, f2, i2, f3, f4, f5, f6, f7, f8);
    }

    public void clear() {
        this.vertices.f1824b = 0;
        this.indices.f1827b = 0;
        this.parts.clear();
        this.vindex = 0;
        this.lastIndex = -1;
        this.istart = 0;
        this.part = null;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cone(float f2, float f3, float f4, int i2) {
        cone(f2, f3, f4, i2, 0.0f, 360.0f);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cylinder(float f2, float f3, float f4, int i2) {
        CylinderShapeBuilder.build(this, f2, f3, f4, i2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9) {
        EllipseShapeBuilder.build(this, f2, f3, i2, f4, f5, f6, f7, f8, f9);
    }

    public Mesh end(Mesh mesh) {
        endpart();
        VertexAttributes vertexAttributes = this.attributes;
        if (vertexAttributes == null) {
            throw new m("Call begin() first");
        }
        if (!vertexAttributes.equals(mesh.getVertexAttributes())) {
            throw new m("Mesh attributes don't match");
        }
        if (mesh.getMaxVertices() * this.stride < this.vertices.f1824b) {
            throw new m("Mesh can't hold enough vertices: " + mesh.getMaxVertices() + " * " + this.stride + " < " + this.vertices.f1824b);
        }
        if (mesh.getMaxIndices() < this.indices.f1827b) {
            throw new m("Mesh can't hold enough indices: " + mesh.getMaxIndices() + " < " + this.indices.f1827b);
        }
        j jVar = this.vertices;
        mesh.setVertices(jVar.f1823a, 0, jVar.f1824b);
        j0 j0Var = this.indices;
        mesh.setIndices(j0Var.f1826a, 0, j0Var.f1827b);
        a.b<MeshPart> it = this.parts.iterator();
        while (it.hasNext()) {
            it.next().mesh = mesh;
        }
        this.parts.clear();
        this.attributes = null;
        this.vertices.f1824b = 0;
        this.indices.f1827b = 0;
        return mesh;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureCapacity(int i2, int i3) {
        ensureVertices(i2);
        ensureIndices(i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureIndices(int i2) {
        this.indices.b(i2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureRectangleIndices(int i2) {
        int i3 = this.primitiveType;
        if (i3 == 0) {
            ensureIndices(i2 * 4);
        } else if (i3 == 1) {
            ensureIndices(i2 * 8);
        } else {
            ensureIndices(i2 * 6);
        }
    }

    @Deprecated
    public void ensureRectangles(int i2, int i3) {
        ensureVertices(i2);
        ensureRectangleIndices(i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureTriangleIndices(int i2) {
        int i3 = this.primitiveType;
        if (i3 == 1) {
            ensureIndices(i2 * 6);
        } else {
            if (i3 != 4 && i3 != 0) {
                throw new m("Incorrect primtive type");
            }
            ensureIndices(i2 * 3);
        }
    }

    @Deprecated
    public void ensureTriangles(int i2, int i3) {
        ensureVertices(i2);
        ensureTriangleIndices(i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void ensureVertices(int i2) {
        this.vertices.d(this.stride * i2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    public int getFloatsPerVertex() {
        return this.stride;
    }

    public void getIndices(short[] sArr, int i2) {
        if (this.attributes == null) {
            throw new m("Must be called in between #begin and #end");
        }
        if (i2 >= 0) {
            int length = sArr.length;
            j0 j0Var = this.indices;
            int i3 = j0Var.f1827b;
            if (i2 <= length - i3) {
                System.arraycopy(j0Var.f1826a, 0, sArr, i2, i3);
                return;
            }
        }
        throw new m("Array too small or offset out of range");
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public MeshPart getMeshPart() {
        return this.part;
    }

    public int getNumIndices() {
        return this.indices.f1827b;
    }

    public int getNumVertices() {
        return this.vertices.f1824b / this.stride;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public int getPrimitiveType() {
        return this.primitiveType;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public Matrix4 getVertexTransform(Matrix4 matrix4) {
        matrix4.o(this.positionTransform);
        return matrix4;
    }

    public void getVertices(float[] fArr, int i2) {
        if (this.attributes == null) {
            throw new m("Must be called in between #begin and #end");
        }
        if (i2 >= 0) {
            int length = fArr.length;
            j jVar = this.vertices;
            int i3 = jVar.f1824b;
            if (i2 <= length - i3) {
                System.arraycopy(jVar.f1823a, 0, fArr, i2, i3);
                return;
            }
        }
        throw new m("Array too small or offset out of range");
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short s) {
        this.indices.a(s);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public boolean isVertexTransformationEnabled() {
        return this.vertexTransformationEnabled;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short lastIndex() {
        return (short) this.lastIndex;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(short s, short s2) {
        if (this.primitiveType != 1) {
            throw new m("Incorrect primitive type");
        }
        index(s, s2);
    }

    public MeshPart part(String str, int i2) {
        return part(str, i2, new MeshPart());
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void patch(MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2, MeshPartBuilder.VertexInfo vertexInfo3, MeshPartBuilder.VertexInfo vertexInfo4, int i2, int i3) {
        PatchShapeBuilder.build(this, vertexInfo, vertexInfo2, vertexInfo3, vertexInfo4, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(short s, short s2, short s3, short s4) {
        int i2 = this.primitiveType;
        if (i2 == 4) {
            index(s, s2, s3, s3, s4, s);
        } else if (i2 == 1) {
            index(s, s2, s2, s3, s3, s4, s4, s);
        } else {
            if (i2 != 0) {
                throw new m("Incorrect primitive type");
            }
            index(s, s2, s3, s4);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        this.hasColor = !this.color.equals(Color.WHITE);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setUVRange(float f2, float f3, float f4, float f5) {
        this.uOffset = f2;
        this.vOffset = f3;
        this.uScale = f4 - f2;
        this.vScale = f5 - f3;
        this.hasUVTransform = (a0.j.f(f2) && a0.j.f(f3) && a0.j.d(f4, 1.0f) && a0.j.d(f5, 1.0f)) ? false : true;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setVertexTransform(Matrix4 matrix4) {
        boolean z2 = matrix4 != null;
        this.vertexTransformationEnabled = z2;
        if (!z2) {
            this.positionTransform.d();
            this.normalTransform.a();
            return;
        }
        this.positionTransform.o(matrix4);
        k kVar = this.normalTransform;
        kVar.c(matrix4);
        kVar.b();
        kVar.e();
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setVertexTransformationEnabled(boolean z2) {
        this.vertexTransformationEnabled = z2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(float f2, float f3, float f4, int i2, int i3) {
        SphereShapeBuilder.build(this, f2, f3, f4, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(short s, short s2, short s3) {
        int i2 = this.primitiveType;
        if (i2 == 4 || i2 == 0) {
            index(s, s2, s3);
        } else {
            if (i2 != 1) {
                throw new m("Incorrect primitive type");
            }
            index(s, s2, s2, s3, s3, s);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short vertex(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, Color color, q qVar) {
        int i2;
        if (this.vindex > 65535) {
            throw new m("Too many vertices used");
        }
        float[] fArr = this.vertex;
        int i3 = this.posOffset;
        fArr[i3] = aVar.f1729a;
        int i4 = this.posSize;
        if (i4 > 1) {
            fArr[i3 + 1] = aVar.f1730b;
        }
        if (i4 > 2) {
            fArr[i3 + 2] = aVar.f1731c;
        }
        if (this.norOffset >= 0) {
            if (aVar2 == null) {
                aVar2 = this.tmpNormal;
                aVar2.getClass();
                aVar2.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
                aVar2.n();
            }
            float[] fArr2 = this.vertex;
            int i5 = this.norOffset;
            fArr2[i5] = aVar2.f1729a;
            fArr2[i5 + 1] = aVar2.f1730b;
            fArr2[i5 + 2] = aVar2.f1731c;
        }
        int i6 = this.colOffset;
        if (i6 >= 0) {
            if (color == null) {
                color = Color.WHITE;
            }
            float[] fArr3 = this.vertex;
            fArr3[i6] = color.f1680r;
            fArr3[i6 + 1] = color.f1679g;
            fArr3[i6 + 2] = color.f1678b;
            if (this.colSize > 3) {
                fArr3[i6 + 3] = color.f1677a;
            }
        } else {
            int i7 = this.cpOffset;
            if (i7 > 0) {
                if (color == null) {
                    color = Color.WHITE;
                }
                this.vertex[i7] = color.toFloatBits();
            }
        }
        if (qVar != null && (i2 = this.uvOffset) >= 0) {
            float[] fArr4 = this.vertex;
            fArr4[i2] = qVar.f91a;
            fArr4[i2 + 1] = qVar.f92b;
        }
        addVertex(this.vertex, 0);
        return (short) this.lastIndex;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(MeshPart meshPart) {
        if (meshPart.primitiveType != this.primitiveType) {
            throw new m("Primitive type doesn't match");
        }
        addMesh(meshPart.mesh, meshPart.offset, meshPart.size);
    }

    public void begin(VertexAttributes vertexAttributes) {
        begin(vertexAttributes, -1);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4, com.badlogic.gdx.math.a aVar5, com.badlogic.gdx.math.a aVar6, com.badlogic.gdx.math.a aVar7, com.badlogic.gdx.math.a aVar8) {
        BoxShapeBuilder.build(this, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2) {
        EllipseShapeBuilder.build(this, f2, i2, aVar, aVar2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cone(float f2, float f3, float f4, int i2, float f5, float f6) {
        ConeShapeBuilder.build(this, f2, f3, f4, i2, f5, f6);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cylinder(float f2, float f3, float f4, int i2, float f5, float f6) {
        CylinderShapeBuilder.build(this, f2, f3, f4, i2, f5, f6);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2) {
        EllipseShapeBuilder.build(this, f2, f3, i2, aVar, aVar2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short s, short s2) {
        ensureIndices(2);
        this.indices.a(s);
        this.indices.a(s2);
    }

    public MeshPart part(String str, int i2, MeshPart meshPart) {
        if (this.attributes == null) {
            throw new RuntimeException("Call begin() first");
        }
        endpart();
        this.part = meshPart;
        meshPart.id = str;
        meshPart.primitiveType = i2;
        this.primitiveType = i2;
        this.parts.a(meshPart);
        setColor(null);
        setVertexTransform(null);
        setUVRange(null);
        return this.part;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void patch(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4, com.badlogic.gdx.math.a aVar5, int i2, int i3) {
        PatchShapeBuilder.build(this, aVar, aVar2, aVar3, aVar4, aVar5, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(Matrix4 matrix4, float f2, float f3, float f4, int i2, int i3) {
        SphereShapeBuilder.build(this, matrix4, f2, f3, f4, i2, i3);
    }

    public void begin(long j2, int i2) {
        begin(createAttributes(j2), i2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(Matrix4 matrix4) {
        BoxShapeBuilder.build(this, matrix4);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4) {
        EllipseShapeBuilder.build(this, f2, i2, aVar, aVar2, aVar3, aVar4);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void cylinder(float f2, float f3, float f4, int i2, float f5, float f6, boolean z2) {
        CylinderShapeBuilder.build(this, f2, f3, f4, i2, f5, f6, z2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4) {
        EllipseShapeBuilder.build(this, f2, f3, i2, aVar, aVar2, aVar3, aVar4);
    }

    @Deprecated
    public void ensureRectangles(int i2) {
        ensureVertices(i2 * 4);
        ensureRectangleIndices(i2);
    }

    @Deprecated
    public void ensureTriangles(int i2) {
        ensureVertices(i2 * 3);
        ensureTriangleIndices(i2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void patch(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int i2, int i3) {
        PatchShapeBuilder.build(this, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setColor(Color color) {
        Color color2 = this.color;
        boolean z2 = color != null;
        this.hasColor = z2;
        if (!z2) {
            color = Color.WHITE;
        }
        color2.set(color);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(float f2, float f3, float f4, int i2, int i3, float f5, float f6, float f7, float f8) {
        SphereShapeBuilder.build(this, f2, f3, f4, i2, i3, f5, f6, f7, f8);
    }

    public void begin(VertexAttributes vertexAttributes, int i2) {
        if (this.attributes == null) {
            this.attributes = vertexAttributes;
            this.vertices.f1824b = 0;
            this.indices.f1827b = 0;
            this.parts.clear();
            this.vindex = 0;
            this.lastIndex = -1;
            this.istart = 0;
            this.part = null;
            int i3 = vertexAttributes.vertexSize / 4;
            this.stride = i3;
            float[] fArr = this.vertex;
            if (fArr == null || fArr.length < i3) {
                this.vertex = new float[i3];
            }
            VertexAttribute vertexAttributeFindByUsage = vertexAttributes.findByUsage(1);
            if (vertexAttributeFindByUsage != null) {
                this.posOffset = vertexAttributeFindByUsage.offset / 4;
                this.posSize = vertexAttributeFindByUsage.numComponents;
                VertexAttribute vertexAttributeFindByUsage2 = vertexAttributes.findByUsage(8);
                this.norOffset = vertexAttributeFindByUsage2 == null ? -1 : vertexAttributeFindByUsage2.offset / 4;
                VertexAttribute vertexAttributeFindByUsage3 = vertexAttributes.findByUsage(256);
                this.biNorOffset = vertexAttributeFindByUsage3 == null ? -1 : vertexAttributeFindByUsage3.offset / 4;
                VertexAttribute vertexAttributeFindByUsage4 = vertexAttributes.findByUsage(VertexAttributes.Usage.Tangent);
                this.tangentOffset = vertexAttributeFindByUsage4 == null ? -1 : vertexAttributeFindByUsage4.offset / 4;
                VertexAttribute vertexAttributeFindByUsage5 = vertexAttributes.findByUsage(2);
                this.colOffset = vertexAttributeFindByUsage5 == null ? -1 : vertexAttributeFindByUsage5.offset / 4;
                this.colSize = vertexAttributeFindByUsage5 != null ? vertexAttributeFindByUsage5.numComponents : 0;
                VertexAttribute vertexAttributeFindByUsage6 = vertexAttributes.findByUsage(4);
                this.cpOffset = vertexAttributeFindByUsage6 == null ? -1 : vertexAttributeFindByUsage6.offset / 4;
                VertexAttribute vertexAttributeFindByUsage7 = vertexAttributes.findByUsage(16);
                this.uvOffset = vertexAttributeFindByUsage7 != null ? vertexAttributeFindByUsage7.offset / 4 : -1;
                setColor(null);
                setVertexTransform(null);
                setUVRange(null);
                this.primitiveType = i2;
                this.bounds.f();
                return;
            }
            throw new m("Cannot build mesh without position attribute");
        }
        throw new RuntimeException("Call end() first");
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(float f2, float f3, float f4) {
        BoxShapeBuilder.build(this, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        EllipseShapeBuilder.build(this, f2, i2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        EllipseShapeBuilder.build(this, f2, f3, i2, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void sphere(Matrix4 matrix4, float f2, float f3, float f4, int i2, int i3, float f5, float f6, float f7, float f8) {
        SphereShapeBuilder.build(this, matrix4, f2, f3, f4, i2, i3, f5, f6, f7, f8);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void box(float f2, float f3, float f4, float f5, float f6, float f7) {
        BoxShapeBuilder.build(this, f2, f3, f4, f5, f6, f7);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        EllipseShapeBuilder.build(this, f2, i2, f3, f4, f5, f6, f7, f8, f9, f10);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        EllipseShapeBuilder.build(this, f2, f3, i2, f4, f5, f6, f7, f8, f9, f10, f11);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short s, short s2, short s3) {
        ensureIndices(3);
        this.indices.a(s);
        this.indices.a(s2);
        this.indices.a(s3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, float f3, float f4) {
        EllipseShapeBuilder.build(this, f2, i2, aVar, aVar2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, float f4, float f5) {
        EllipseShapeBuilder.build(this, f2, f3, i2, aVar, aVar2, f4, f5);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2) {
        ensureVertices(2);
        line(vertex(vertexInfo), vertex(vertexInfo2));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void setUVRange(TextureRegion textureRegion) {
        if (textureRegion == null) {
            this.hasUVTransform = false;
            this.vOffset = 0.0f;
            this.uOffset = 0.0f;
            this.vScale = 1.0f;
            this.uScale = 1.0f;
            return;
        }
        this.hasUVTransform = true;
        setUVRange(textureRegion.getU(), textureRegion.getV(), textureRegion.getU2(), textureRegion.getV2());
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(Mesh mesh, int i2, int i3) {
        if (!this.attributes.equals(mesh.getVertexAttributes())) {
            throw new m("Vertex attributes do not match");
        }
        if (i3 <= 0) {
            return;
        }
        int numVertices = mesh.getNumVertices() * this.stride;
        j jVar = tmpVertices;
        jVar.f1824b = 0;
        jVar.d(numVertices);
        jVar.f1824b = numVertices;
        mesh.getVertices(jVar.f1823a);
        j0 j0Var = tmpIndices;
        j0Var.f1827b = 0;
        j0Var.b(i3);
        j0Var.f1827b = i3;
        mesh.getIndices(i2, i3, j0Var.f1826a, 0);
        addMesh(jVar.f1823a, j0Var.f1826a, 0, i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4, float f3, float f4) {
        circle(f2, i2, aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, aVar3.f1729a, aVar3.f1730b, aVar3.f1731c, aVar4.f1729a, aVar4.f1730b, aVar4.f1731c, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4, float f4, float f5) {
        EllipseShapeBuilder.build(this, f2, f3, i2, aVar, aVar2, aVar3, aVar4, f4, f5);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2, MeshPartBuilder.VertexInfo vertexInfo3) {
        ensureVertices(3);
        triangle(vertex(vertexInfo), vertex(vertexInfo2), vertex(vertexInfo3));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void circle(float f2, int i2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        EllipseShapeBuilder.build(this, f2, i2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, int i2, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        EllipseShapeBuilder.build(this, f2, f3, i2, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2) {
        line(this.vertTmp1.set(aVar, null, null, null), this.vertTmp2.set(aVar2, null, null, null));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2, MeshPartBuilder.VertexInfo vertexInfo3, MeshPartBuilder.VertexInfo vertexInfo4) {
        ensureVertices(4);
        rect(vertex(vertexInfo), vertex(vertexInfo2), vertex(vertexInfo3), vertex(vertexInfo4));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, float f4, float f5, int i2, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2) {
        EllipseShapeBuilder.build(this, f2, f3, f4, f5, i2, aVar, aVar2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short s, short s2, short s3, short s4) {
        ensureIndices(4);
        this.indices.a(s);
        this.indices.a(s2);
        this.indices.a(s3);
        this.indices.a(s4);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(float f2, float f3, float f4, float f5, float f6, float f7) {
        line(this.vertTmp1.set(null, null, null, null).setPos(f2, f3, f4), this.vertTmp2.set(null, null, null, null).setPos(f5, f6, f7));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3) {
        triangle(this.vertTmp1.set(aVar, null, null, null), this.vertTmp2.set(aVar2, null, null, null), this.vertTmp3.set(aVar3, null, null, null));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11) {
        EllipseShapeBuilder.build(this, f2, f3, f4, f5, i2, f6, f7, f8, f9, f10, f11);
    }

    protected short[] getIndices() {
        return this.indices.f1826a;
    }

    protected float[] getVertices() {
        return this.vertices.f1823a;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void line(com.badlogic.gdx.math.a aVar, Color color, com.badlogic.gdx.math.a aVar2, Color color2) {
        line(this.vertTmp1.set(aVar, null, color, null), this.vertTmp2.set(aVar2, null, color2, null));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3, com.badlogic.gdx.math.a aVar4, com.badlogic.gdx.math.a aVar5) {
        rect(this.vertTmp1.set(aVar, aVar5, null, null).setUV(0.0f, 1.0f), this.vertTmp2.set(aVar2, aVar5, null, null).setUV(1.0f, 1.0f), this.vertTmp3.set(aVar3, aVar5, null, null).setUV(1.0f, 0.0f), this.vertTmp4.set(aVar4, aVar5, null, null).setUV(0.0f, 0.0f));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void triangle(com.badlogic.gdx.math.a aVar, Color color, com.badlogic.gdx.math.a aVar2, Color color2, com.badlogic.gdx.math.a aVar3, Color color3) {
        triangle(this.vertTmp1.set(aVar, null, color, null), this.vertTmp2.set(aVar2, null, color2, null), this.vertTmp3.set(aVar3, null, color3, null));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        EllipseShapeBuilder.build(this, f2, f3, f4, f5, i2, f6, f7, f8, f9, f10, f11, f12, f13);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    @Deprecated
    public void ellipse(float f2, float f3, float f4, float f5, int i2, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19) {
        EllipseShapeBuilder.build(this, f2, f3, f4, f5, i2, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void rect(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        rect(this.vertTmp1.set(null, null, null, null).setPos(f2, f3, f4).setNor(f14, f15, f16).setUV(0.0f, 1.0f), this.vertTmp2.set(null, null, null, null).setPos(f5, f6, f7).setNor(f14, f15, f16).setUV(1.0f, 1.0f), this.vertTmp3.set(null, null, null, null).setPos(f8, f9, f10).setNor(f14, f15, f16).setUV(1.0f, 0.0f), this.vertTmp4.set(null, null, null, null).setPos(f11, f12, f13).setNor(f14, f15, f16).setUV(0.0f, 0.0f));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short s, short s2, short s3, short s4, short s5, short s6) {
        ensureIndices(6);
        this.indices.a(s);
        this.indices.a(s2);
        this.indices.a(s3);
        this.indices.a(s4);
        this.indices.a(s5);
        this.indices.a(s6);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void index(short s, short s2, short s3, short s4, short s5, short s6, short s7, short s8) {
        ensureIndices(8);
        this.indices.a(s);
        this.indices.a(s2);
        this.indices.a(s3);
        this.indices.a(s4);
        this.indices.a(s5);
        this.indices.a(s6);
        this.indices.a(s7);
        this.indices.a(s8);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(float[] fArr, short[] sArr, int i2, int i3) {
        p pVar = indicesMap;
        if (pVar == null) {
            indicesMap = new p(i3);
        } else {
            pVar.clear();
            indicesMap.b(i3);
        }
        ensureIndices(i3);
        int length = fArr.length / this.stride;
        if (length >= i3) {
            length = i3;
        }
        ensureVertices(length);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = sArr[i2 + i4] & 65535;
            int iC = indicesMap.c(i5, -1);
            if (iC < 0) {
                addVertex(fArr, this.stride * i5);
                p pVar2 = indicesMap;
                int i6 = this.lastIndex;
                pVar2.e(i5, i6);
                iC = i6;
            }
            index((short) iC);
        }
    }

    public Mesh end() {
        return end(new Mesh(true, this.vertices.f1824b / this.stride, this.indices.f1827b, this.attributes));
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short vertex(float... fArr) {
        int length = fArr.length - this.stride;
        int i2 = 0;
        while (i2 <= length) {
            addVertex(fArr, i2);
            i2 += this.stride;
        }
        return (short) this.lastIndex;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public short vertex(MeshPartBuilder.VertexInfo vertexInfo) {
        return vertex(vertexInfo.hasPosition ? vertexInfo.position : null, vertexInfo.hasNormal ? vertexInfo.normal : null, vertexInfo.hasColor ? vertexInfo.color : null, vertexInfo.hasUV ? vertexInfo.uv : null);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
    public void addMesh(float[] fArr, short[] sArr) {
        int i2 = this.lastIndex + 1;
        ensureVertices(fArr.length / this.stride);
        int i3 = 0;
        while (i3 < fArr.length) {
            addVertex(fArr, i3);
            i3 += this.stride;
        }
        ensureIndices(sArr.length);
        for (short s : sArr) {
            index((short) (s + i2));
        }
    }
}
