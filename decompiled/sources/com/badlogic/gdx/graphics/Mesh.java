package com.badlogic.gdx.graphics;

import a0.k;
import a0.q;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.IndexArray;
import com.badlogic.gdx.graphics.glutils.IndexBufferObject;
import com.badlogic.gdx.graphics.glutils.IndexBufferObjectSubData;
import com.badlogic.gdx.graphics.glutils.IndexData;
import com.badlogic.gdx.graphics.glutils.InstanceBufferObject;
import com.badlogic.gdx.graphics.glutils.InstanceData;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.badlogic.gdx.graphics.glutils.VertexBufferObject;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectSubData;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectWithVAO;
import com.badlogic.gdx.graphics.glutils.VertexData;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Mesh implements i {
    static final Map<Application, a<Mesh>> meshes = new HashMap();
    boolean autoBind;
    final IndexData indices;
    InstanceData instances;
    boolean isInstanced;
    final boolean isVertexArray;
    private final com.badlogic.gdx.math.a tmpV;
    final VertexData vertices;

    /* JADX INFO: renamed from: com.badlogic.gdx.graphics.Mesh$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType;

        static {
            int[] iArr = new int[VertexDataType.values().length];
            $SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType = iArr;
            try {
                iArr[VertexDataType.VertexBufferObject.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[VertexDataType.VertexBufferObjectSubData.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[VertexDataType.VertexBufferObjectWithVAO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[VertexDataType.VertexArray.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum VertexDataType {
        VertexArray,
        VertexBufferObject,
        VertexBufferObjectSubData,
        VertexBufferObjectWithVAO
    }

    protected Mesh(VertexData vertexData, IndexData indexData, boolean z2) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new com.badlogic.gdx.math.a();
        this.vertices = vertexData;
        this.indices = indexData;
        this.isVertexArray = z2;
        addManagedMesh(Gdx.app, this);
    }

    private static void addManagedMesh(Application application, Mesh mesh) {
        Map<Application, a<Mesh>> map = meshes;
        a<Mesh> aVar = map.get(application);
        if (aVar == null) {
            aVar = new a<>();
        }
        aVar.a(mesh);
        map.put(application, aVar);
    }

    public static void clearAllMeshes(Application application) {
        meshes.remove(application);
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder("Managed meshes/app: { ");
        Iterator<Application> it = meshes.keySet().iterator();
        while (it.hasNext()) {
            sb.append(meshes.get(it.next()).f1750b);
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void invalidateAllMeshes(Application application) {
        a<Mesh> aVar = meshes.get(application);
        if (aVar == null) {
            return;
        }
        for (int i2 = 0; i2 < aVar.f1750b; i2++) {
            aVar.get(i2).vertices.invalidate();
            aVar.get(i2).indices.invalidate();
        }
    }

    private VertexData makeVertexBuffer(boolean z2, int i2, VertexAttributes vertexAttributes) {
        return Gdx.gl30 != null ? new VertexBufferObjectWithVAO(z2, i2, vertexAttributes) : new VertexBufferObject(z2, i2, vertexAttributes);
    }

    public void bind(ShaderProgram shaderProgram) {
        bind(shaderProgram, null);
    }

    public b0.a calculateBoundingBox() {
        b0.a aVar = new b0.a();
        calculateBoundingBox(aVar);
        return aVar;
    }

    public float calculateRadius(float f2, float f3, float f4, int i2, int i3, Matrix4 matrix4) {
        return (float) Math.sqrt(calculateRadiusSquared(f2, f3, f4, i2, i3, matrix4));
    }

    public float calculateRadiusSquared(float f2, float f3, float f4, int i2, int i3, Matrix4 matrix4) {
        int i4;
        int numIndices = getNumIndices();
        if (i2 < 0 || i3 < 1 || (i4 = i2 + i3) > numIndices) {
            throw new m("Not enough indices");
        }
        FloatBuffer buffer = this.vertices.getBuffer();
        ShortBuffer buffer2 = this.indices.getBuffer();
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i5 = vertexAttribute.offset / 4;
        int i6 = this.vertices.getAttributes().vertexSize / 4;
        int i7 = vertexAttribute.numComponents;
        short s = 65535;
        float f5 = 0.0f;
        if (i7 == 1) {
            float f6 = 0.0f;
            for (int i8 = i2; i8 < i4; i8++) {
                this.tmpV.t(buffer.get(((buffer2.get(i8) & 65535) * i6) + i5), 0.0f, 0.0f);
                if (matrix4 != null) {
                    this.tmpV.m(matrix4);
                }
                com.badlogic.gdx.math.a aVar = this.tmpV;
                aVar.v(f2, f3, f4);
                float fJ = aVar.j();
                if (fJ > f6) {
                    f6 = fJ;
                }
            }
            return f6;
        }
        if (i7 == 2) {
            float f7 = 0.0f;
            for (int i9 = i2; i9 < i4; i9++) {
                int i10 = ((buffer2.get(i9) & 65535) * i6) + i5;
                this.tmpV.t(buffer.get(i10), buffer.get(i10 + 1), 0.0f);
                if (matrix4 != null) {
                    this.tmpV.m(matrix4);
                }
                com.badlogic.gdx.math.a aVar2 = this.tmpV;
                aVar2.v(f2, f3, f4);
                float fJ2 = aVar2.j();
                if (fJ2 > f7) {
                    f7 = fJ2;
                }
            }
            return f7;
        }
        if (i7 != 3) {
            return 0.0f;
        }
        int i11 = i2;
        while (i11 < i4) {
            int i12 = ((buffer2.get(i11) & s) * i6) + i5;
            int i13 = i5;
            this.tmpV.t(buffer.get(i12), buffer.get(i12 + 1), buffer.get(i12 + 2));
            if (matrix4 != null) {
                this.tmpV.m(matrix4);
            }
            com.badlogic.gdx.math.a aVar3 = this.tmpV;
            aVar3.v(f2, f3, f4);
            float fJ3 = aVar3.j();
            if (fJ3 > f5) {
                f5 = fJ3;
            }
            i11++;
            i5 = i13;
            s = 65535;
        }
        return f5;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Mesh copy(boolean z2, boolean z3, int[] iArr) {
        VertexAttribute[] vertexAttributeArr;
        short[] sArr;
        int i2;
        short[] sArr2;
        int i3;
        short s;
        int vertexSize = getVertexSize() / 4;
        int numVertices = getNumVertices();
        int i4 = numVertices * vertexSize;
        float[] fArr = new float[i4];
        int i5 = 0;
        getVertices(0, i4, fArr);
        if (iArr != null) {
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < iArr.length; i8++) {
                if (getVertexAttribute(iArr[i8]) != null) {
                    i6 += getVertexAttribute(iArr[i8]).numComponents;
                    i7++;
                }
            }
            if (i6 > 0) {
                vertexAttributeArr = new VertexAttribute[i7];
                sArr = new short[i6];
                int i9 = 0;
                i2 = 0;
                int i10 = -1;
                int i11 = -1;
                while (i9 < iArr.length) {
                    VertexAttribute vertexAttribute = getVertexAttribute(iArr[i9]);
                    if (vertexAttribute != null) {
                        for (int i12 = i5; i12 < vertexAttribute.numComponents; i12++) {
                            i10++;
                            sArr[i10] = (short) (vertexAttribute.offset + i12);
                        }
                        i11++;
                        vertexAttributeArr[i11] = vertexAttribute.copy();
                        i2 += vertexAttribute.numComponents;
                    }
                    i9++;
                    i5 = 0;
                }
            } else {
                vertexAttributeArr = null;
                sArr = null;
                i2 = 0;
            }
        }
        if (sArr == null) {
            sArr = new short[vertexSize];
            for (short s2 = 0; s2 < vertexSize; s2 = (short) (s2 + 1)) {
                sArr[s2] = s2;
            }
            i2 = vertexSize;
        }
        int numIndices = getNumIndices();
        if (numIndices > 0) {
            sArr2 = new short[numIndices];
            getIndices(sArr2);
            if (z3 || i2 != vertexSize) {
                float[] fArr2 = new float[i4];
                int i13 = 0;
                int i14 = 0;
                while (i14 < numIndices) {
                    int i15 = sArr2[i14] * vertexSize;
                    if (z3) {
                        short s3 = 0;
                        s = -1;
                        while (s3 < i13 && s < 0) {
                            int i16 = s3 * i2;
                            boolean z4 = true;
                            int i17 = numIndices;
                            for (int i18 = 0; i18 < sArr.length && z4; i18++) {
                                if (fArr2[i16 + i18] != fArr[i15 + sArr[i18]]) {
                                    z4 = false;
                                }
                            }
                            if (z4) {
                                s = s3;
                            }
                            s3 = (short) (s3 + 1);
                            numIndices = i17;
                        }
                        i3 = numIndices;
                    } else {
                        i3 = numIndices;
                        s = -1;
                    }
                    if (s > 0) {
                        sArr2[i14] = s;
                    } else {
                        int i19 = i13 * i2;
                        for (int i20 = 0; i20 < sArr.length; i20++) {
                            fArr2[i19 + i20] = fArr[sArr[i20] + i15];
                        }
                        sArr2[i14] = (short) i13;
                        i13++;
                    }
                    i14++;
                    numIndices = i3;
                }
                fArr = fArr2;
                numVertices = i13;
            }
        } else {
            sArr2 = null;
        }
        Mesh mesh = vertexAttributeArr == null ? new Mesh(z2, numVertices, sArr2 == null ? 0 : sArr2.length, getVertexAttributes()) : new Mesh(z2, numVertices, sArr2 == null ? 0 : sArr2.length, vertexAttributeArr);
        mesh.setVertices(fArr, 0, numVertices * i2);
        if (sArr2 != null) {
            mesh.setIndices(sArr2);
        }
        return mesh;
    }

    public Mesh disableInstancedRendering() {
        if (this.isInstanced) {
            this.isInstanced = false;
            this.instances.dispose();
            this.instances = null;
        }
        return this;
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        Map<Application, a<Mesh>> map = meshes;
        if (map.get(Gdx.app) != null) {
            map.get(Gdx.app).q(this, true);
        }
        this.vertices.dispose();
        InstanceData instanceData = this.instances;
        if (instanceData != null) {
            instanceData.dispose();
        }
        this.indices.dispose();
    }

    public Mesh enableInstancedRendering(boolean z2, int i2, VertexAttribute... vertexAttributeArr) {
        if (this.isInstanced) {
            throw new m("Trying to enable InstancedRendering on same Mesh instance twice. Use disableInstancedRendering to clean up old InstanceData first");
        }
        this.isInstanced = true;
        this.instances = new InstanceBufferObject(z2, i2, vertexAttributeArr);
        return this;
    }

    public b0.a extendBoundingBox(b0.a aVar, int i2, int i3) {
        return extendBoundingBox(aVar, i2, i3, null);
    }

    public void getIndices(short[] sArr) {
        getIndices(sArr, 0);
    }

    public ShortBuffer getIndicesBuffer() {
        return this.indices.getBuffer();
    }

    public int getMaxIndices() {
        return this.indices.getNumMaxIndices();
    }

    public int getMaxVertices() {
        return this.vertices.getNumMaxVertices();
    }

    public int getNumIndices() {
        return this.indices.getNumIndices();
    }

    public int getNumVertices() {
        return this.vertices.getNumVertices();
    }

    public VertexAttribute getVertexAttribute(int i2) {
        VertexAttributes attributes = this.vertices.getAttributes();
        int size = attributes.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (attributes.get(i3).usage == i2) {
                return attributes.get(i3);
            }
        }
        return null;
    }

    public VertexAttributes getVertexAttributes() {
        return this.vertices.getAttributes();
    }

    public int getVertexSize() {
        return this.vertices.getAttributes().vertexSize;
    }

    public float[] getVertices(float[] fArr) {
        return getVertices(0, -1, fArr);
    }

    public FloatBuffer getVerticesBuffer() {
        return this.vertices.getBuffer();
    }

    public boolean isInstanced() {
        return this.isInstanced;
    }

    public void render(ShaderProgram shaderProgram, int i2) {
        render(shaderProgram, i2, 0, this.indices.getNumMaxIndices() > 0 ? getNumIndices() : getNumVertices(), this.autoBind);
    }

    public void scale(float f2, float f3, float f4) {
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i2 = vertexAttribute.offset / 4;
        int i3 = vertexAttribute.numComponents;
        int numVertices = getNumVertices();
        int vertexSize = getVertexSize() / 4;
        float[] fArr = new float[numVertices * vertexSize];
        getVertices(fArr);
        int i4 = 0;
        if (i3 == 1) {
            while (i4 < numVertices) {
                fArr[i2] = fArr[i2] * f2;
                i2 += vertexSize;
                i4++;
            }
        } else if (i3 == 2) {
            while (i4 < numVertices) {
                fArr[i2] = fArr[i2] * f2;
                int i5 = i2 + 1;
                fArr[i5] = fArr[i5] * f3;
                i2 += vertexSize;
                i4++;
            }
        } else if (i3 == 3) {
            while (i4 < numVertices) {
                fArr[i2] = fArr[i2] * f2;
                int i6 = i2 + 1;
                fArr[i6] = fArr[i6] * f3;
                int i7 = i2 + 2;
                fArr[i7] = fArr[i7] * f4;
                i2 += vertexSize;
                i4++;
            }
        }
        setVertices(fArr);
    }

    public void setAutoBind(boolean z2) {
        this.autoBind = z2;
    }

    public Mesh setIndices(short[] sArr) {
        this.indices.setIndices(sArr, 0, sArr.length);
        return this;
    }

    public Mesh setInstanceData(float[] fArr, int i2, int i3) {
        InstanceData instanceData = this.instances;
        if (instanceData == null) {
            throw new m("An InstanceBufferObject must be set before setting instance data!");
        }
        instanceData.setInstanceData(fArr, i2, i3);
        return this;
    }

    public Mesh setVertices(float[] fArr) {
        this.vertices.setVertices(fArr, 0, fArr.length);
        return this;
    }

    public void transform(Matrix4 matrix4) {
        transform(matrix4, 0, getNumVertices());
    }

    public void transformUV(k kVar) {
        transformUV(kVar, 0, getNumVertices());
    }

    public void unbind(ShaderProgram shaderProgram) {
        unbind(shaderProgram, null);
    }

    public Mesh updateInstanceData(int i2, float[] fArr) {
        return updateInstanceData(i2, fArr, 0, fArr.length);
    }

    public Mesh updateVertices(int i2, float[] fArr) {
        return updateVertices(i2, fArr, 0, fArr.length);
    }

    public void bind(ShaderProgram shaderProgram, int[] iArr) {
        this.vertices.bind(shaderProgram, iArr);
        InstanceData instanceData = this.instances;
        if (instanceData != null && instanceData.getNumInstances() > 0) {
            this.instances.bind(shaderProgram, iArr);
        }
        if (this.indices.getNumIndices() > 0) {
            this.indices.bind();
        }
    }

    public float calculateRadius(com.badlogic.gdx.math.a aVar, int i2, int i3, Matrix4 matrix4) {
        return calculateRadius(aVar.f1729a, aVar.f1730b, aVar.f1731c, i2, i3, matrix4);
    }

    public b0.a extendBoundingBox(b0.a aVar, int i2, int i3, Matrix4 matrix4) {
        int i4;
        int numIndices = getNumIndices();
        int numVertices = getNumVertices();
        if (numIndices != 0) {
            numVertices = numIndices;
        }
        if (i2 < 0 || i3 < 1 || (i4 = i2 + i3) > numVertices) {
            StringBuilder sb = new StringBuilder("Invalid part specified ( offset=");
            sb.append(i2);
            sb.append(", count=");
            sb.append(i3);
            sb.append(", max=");
            throw new m(a.a.p(sb, numVertices, " )"));
        }
        FloatBuffer buffer = this.vertices.getBuffer();
        ShortBuffer buffer2 = this.indices.getBuffer();
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i5 = vertexAttribute.offset / 4;
        int i6 = this.vertices.getAttributes().vertexSize / 4;
        int i7 = vertexAttribute.numComponents;
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 == 3) {
                    if (numIndices > 0) {
                        while (i2 < i4) {
                            int i8 = ((buffer2.get(i2) & 65535) * i6) + i5;
                            this.tmpV.t(buffer.get(i8), buffer.get(i8 + 1), buffer.get(i8 + 2));
                            if (matrix4 != null) {
                                this.tmpV.m(matrix4);
                            }
                            aVar.c(this.tmpV);
                            i2++;
                        }
                    } else {
                        while (i2 < i4) {
                            int i9 = (i2 * i6) + i5;
                            this.tmpV.t(buffer.get(i9), buffer.get(i9 + 1), buffer.get(i9 + 2));
                            if (matrix4 != null) {
                                this.tmpV.m(matrix4);
                            }
                            aVar.c(this.tmpV);
                            i2++;
                        }
                    }
                }
            } else if (numIndices > 0) {
                while (i2 < i4) {
                    int i10 = ((buffer2.get(i2) & 65535) * i6) + i5;
                    this.tmpV.t(buffer.get(i10), buffer.get(i10 + 1), 0.0f);
                    if (matrix4 != null) {
                        this.tmpV.m(matrix4);
                    }
                    aVar.c(this.tmpV);
                    i2++;
                }
            } else {
                while (i2 < i4) {
                    int i11 = (i2 * i6) + i5;
                    this.tmpV.t(buffer.get(i11), buffer.get(i11 + 1), 0.0f);
                    if (matrix4 != null) {
                        this.tmpV.m(matrix4);
                    }
                    aVar.c(this.tmpV);
                    i2++;
                }
            }
        } else if (numIndices > 0) {
            while (i2 < i4) {
                this.tmpV.t(buffer.get(((buffer2.get(i2) & 65535) * i6) + i5), 0.0f, 0.0f);
                if (matrix4 != null) {
                    this.tmpV.m(matrix4);
                }
                aVar.c(this.tmpV);
                i2++;
            }
        } else {
            while (i2 < i4) {
                this.tmpV.t(buffer.get((i2 * i6) + i5), 0.0f, 0.0f);
                if (matrix4 != null) {
                    this.tmpV.m(matrix4);
                }
                aVar.c(this.tmpV);
                i2++;
            }
        }
        return aVar;
    }

    public void getIndices(short[] sArr, int i2) {
        getIndices(0, sArr, i2);
    }

    public float[] getVertices(int i2, float[] fArr) {
        return getVertices(i2, -1, fArr);
    }

    public void render(ShaderProgram shaderProgram, int i2, int i3, int i4) {
        render(shaderProgram, i2, i3, i4, this.autoBind);
    }

    public Mesh setIndices(short[] sArr, int i2, int i3) {
        this.indices.setIndices(sArr, i2, i3);
        return this;
    }

    public Mesh setVertices(float[] fArr, int i2, int i3) {
        this.vertices.setVertices(fArr, i2, i3);
        return this;
    }

    public void transform(Matrix4 matrix4, int i2, int i3) {
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i4 = vertexAttribute.offset / 4;
        int vertexSize = getVertexSize() / 4;
        int i5 = vertexAttribute.numComponents;
        getNumVertices();
        int i6 = i3 * vertexSize;
        float[] fArr = new float[i6];
        int i7 = i2 * vertexSize;
        getVertices(i7, i6, fArr);
        transform(matrix4, fArr, vertexSize, i4, i5, 0, i3);
        updateVertices(i7, fArr);
    }

    protected void transformUV(k kVar, int i2, int i3) {
        int i4 = getVertexAttribute(16).offset / 4;
        int vertexSize = getVertexSize() / 4;
        int numVertices = getNumVertices() * vertexSize;
        float[] fArr = new float[numVertices];
        getVertices(0, numVertices, fArr);
        transformUV(kVar, fArr, vertexSize, i4, i2, i3);
        setVertices(fArr, 0, numVertices);
    }

    public void unbind(ShaderProgram shaderProgram, int[] iArr) {
        this.vertices.unbind(shaderProgram, iArr);
        InstanceData instanceData = this.instances;
        if (instanceData != null && instanceData.getNumInstances() > 0) {
            this.instances.unbind(shaderProgram, iArr);
        }
        if (this.indices.getNumIndices() > 0) {
            this.indices.unbind();
        }
    }

    public Mesh updateInstanceData(int i2, float[] fArr, int i3, int i4) {
        this.instances.updateInstanceData(i2, fArr, i3, i4);
        return this;
    }

    public Mesh updateVertices(int i2, float[] fArr, int i3, int i4) {
        this.vertices.updateVertices(i2, fArr, i3, i4);
        return this;
    }

    public void calculateBoundingBox(b0.a aVar) {
        int numVertices = getNumVertices();
        if (numVertices != 0) {
            FloatBuffer buffer = this.vertices.getBuffer();
            aVar.f();
            VertexAttribute vertexAttribute = getVertexAttribute(1);
            int i2 = vertexAttribute.offset / 4;
            int i3 = this.vertices.getAttributes().vertexSize / 4;
            int i4 = vertexAttribute.numComponents;
            int i5 = 0;
            if (i4 == 1) {
                while (i5 < numVertices) {
                    aVar.a(buffer.get(i2), 0.0f, 0.0f);
                    i2 += i3;
                    i5++;
                }
                return;
            }
            if (i4 == 2) {
                while (i5 < numVertices) {
                    aVar.a(buffer.get(i2), buffer.get(i2 + 1), 0.0f);
                    i2 += i3;
                    i5++;
                }
                return;
            }
            if (i4 != 3) {
                return;
            }
            while (i5 < numVertices) {
                aVar.a(buffer.get(i2), buffer.get(i2 + 1), buffer.get(i2 + 2));
                i2 += i3;
                i5++;
            }
            return;
        }
        throw new m("No vertices defined");
    }

    public float calculateRadius(float f2, float f3, float f4, int i2, int i3) {
        return calculateRadius(f2, f3, f4, i2, i3, null);
    }

    public void getIndices(int i2, short[] sArr, int i3) {
        getIndices(i2, -1, sArr, i3);
    }

    public float[] getVertices(int i2, int i3, float[] fArr) {
        return getVertices(i2, i3, fArr, 0);
    }

    public void render(ShaderProgram shaderProgram, int i2, int i3, int i4, boolean z2) {
        if (i4 == 0) {
            return;
        }
        if (z2) {
            bind(shaderProgram);
        }
        if (this.isVertexArray) {
            if (this.indices.getNumIndices() > 0) {
                ShortBuffer buffer = this.indices.getBuffer();
                int iPosition = buffer.position();
                int iLimit = buffer.limit();
                buffer.position(i3);
                buffer.limit(i3 + i4);
                Gdx.gl20.glDrawElements(i2, i4, GL20.GL_UNSIGNED_SHORT, buffer);
                buffer.position(iPosition);
                buffer.limit(iLimit);
            } else {
                Gdx.gl20.glDrawArrays(i2, i3, i4);
            }
        } else {
            int numInstances = this.isInstanced ? this.instances.getNumInstances() : 0;
            if (this.indices.getNumIndices() > 0) {
                if (i4 + i3 <= this.indices.getNumMaxIndices()) {
                    if (this.isInstanced && numInstances > 0) {
                        Gdx.gl30.glDrawElementsInstanced(i2, i4, GL20.GL_UNSIGNED_SHORT, i3 * 2, numInstances);
                    } else {
                        Gdx.gl20.glDrawElements(i2, i4, GL20.GL_UNSIGNED_SHORT, i3 * 2);
                    }
                } else {
                    throw new m("Mesh attempting to access memory outside of the index buffer (count: " + i4 + ", offset: " + i3 + ", max: " + this.indices.getNumMaxIndices() + ")");
                }
            } else if (this.isInstanced && numInstances > 0) {
                Gdx.gl30.glDrawArraysInstanced(i2, i3, i4, numInstances);
            } else {
                Gdx.gl20.glDrawArrays(i2, i3, i4);
            }
        }
        if (z2) {
            unbind(shaderProgram);
        }
    }

    public Mesh updateInstanceData(int i2, FloatBuffer floatBuffer) {
        return updateInstanceData(i2, floatBuffer, 0, floatBuffer.limit());
    }

    public float calculateRadius(com.badlogic.gdx.math.a aVar, int i2, int i3) {
        return calculateRadius(aVar.f1729a, aVar.f1730b, aVar.f1731c, i2, i3, null);
    }

    public void getIndices(int i2, int i3, short[] sArr, int i4) {
        int numIndices = getNumIndices();
        if (i3 < 0) {
            i3 = numIndices - i2;
        }
        if (i2 >= 0 && i2 < numIndices && i2 + i3 <= numIndices) {
            if (sArr.length - i4 >= i3) {
                int iPosition = getIndicesBuffer().position();
                getIndicesBuffer().position(i2);
                getIndicesBuffer().get(sArr, i4, i3);
                getIndicesBuffer().position(iPosition);
                return;
            }
            throw new IllegalArgumentException("not enough room in indices array, has " + sArr.length + " shorts, needs " + i3);
        }
        throw new IllegalArgumentException("Invalid range specified, offset: " + i2 + ", count: " + i3 + ", max: " + numIndices);
    }

    public float[] getVertices(int i2, int i3, float[] fArr, int i4) {
        int vertexSize = (getVertexSize() * getNumVertices()) / 4;
        if (i3 == -1 && (i3 = vertexSize - i2) > fArr.length - i4) {
            i3 = fArr.length - i4;
        }
        if (i2 >= 0 && i3 > 0 && i2 + i3 <= vertexSize && i4 >= 0 && i4 < fArr.length) {
            if (fArr.length - i4 >= i3) {
                int iPosition = getVerticesBuffer().position();
                getVerticesBuffer().position(i2);
                getVerticesBuffer().get(fArr, i4, i3);
                getVerticesBuffer().position(iPosition);
                return fArr;
            }
            throw new IllegalArgumentException("not enough room in vertices array, has " + fArr.length + " floats, needs " + i3);
        }
        throw new IndexOutOfBoundsException();
    }

    public Mesh updateInstanceData(int i2, FloatBuffer floatBuffer, int i3, int i4) {
        this.instances.updateInstanceData(i2, floatBuffer, i3, i4);
        return this;
    }

    public float calculateRadius(float f2, float f3, float f4) {
        return calculateRadius(f2, f3, f4, 0, getNumIndices(), null);
    }

    public float calculateRadius(com.badlogic.gdx.math.a aVar) {
        return calculateRadius(aVar.f1729a, aVar.f1730b, aVar.f1731c, 0, getNumIndices(), null);
    }

    public Mesh setInstanceData(float[] fArr) {
        InstanceData instanceData = this.instances;
        if (instanceData != null) {
            instanceData.setInstanceData(fArr, 0, fArr.length);
            return this;
        }
        throw new m("An InstanceBufferObject must be set before setting instance data!");
    }

    public Mesh(boolean z2, int i2, int i3, VertexAttribute... vertexAttributeArr) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new com.badlogic.gdx.math.a();
        this.vertices = makeVertexBuffer(z2, i2, new VertexAttributes(vertexAttributeArr));
        this.indices = new IndexBufferObject(z2, i3);
        this.isVertexArray = false;
        addManagedMesh(Gdx.app, this);
    }

    public static void transformUV(k kVar, float[] fArr, int i2, int i3, int i4, int i5) {
        if (i4 >= 0 && i5 >= 1 && (i4 + i5) * i2 <= fArr.length) {
            q qVar = new q();
            int i6 = (i4 * i2) + i3;
            for (int i7 = 0; i7 < i5; i7++) {
                float f2 = fArr[i6];
                int i8 = i6 + 1;
                float f3 = fArr[i8];
                qVar.f91a = f2;
                qVar.f92b = f3;
                float[] fArr2 = kVar.f71a;
                float f4 = (fArr2[3] * f3) + (fArr2[0] * f2) + fArr2[6];
                float f5 = (f3 * fArr2[4]) + (f2 * fArr2[1]) + fArr2[7];
                qVar.f91a = f4;
                qVar.f92b = f5;
                fArr[i6] = f4;
                fArr[i8] = f5;
                i6 += i2;
            }
            return;
        }
        throw new IndexOutOfBoundsException("start = " + i4 + ", count = " + i5 + ", vertexSize = " + i2 + ", length = " + fArr.length);
    }

    public static void transform(Matrix4 matrix4, float[] fArr, int i2, int i3, int i4, int i5, int i6) {
        if (i3 >= 0 && i4 >= 1 && i3 + i4 <= i2) {
            if (i5 >= 0 && i6 >= 1 && (i5 + i6) * i2 <= fArr.length) {
                com.badlogic.gdx.math.a aVar = new com.badlogic.gdx.math.a();
                int i7 = (i5 * i2) + i3;
                int i8 = 0;
                if (i4 == 1) {
                    while (i8 < i6) {
                        aVar.t(fArr[i7], 0.0f, 0.0f);
                        aVar.m(matrix4);
                        fArr[i7] = aVar.f1729a;
                        i7 += i2;
                        i8++;
                    }
                    return;
                }
                if (i4 == 2) {
                    while (i8 < i6) {
                        int i9 = i7 + 1;
                        aVar.t(fArr[i7], fArr[i9], 0.0f);
                        aVar.m(matrix4);
                        fArr[i7] = aVar.f1729a;
                        fArr[i9] = aVar.f1730b;
                        i7 += i2;
                        i8++;
                    }
                    return;
                }
                if (i4 != 3) {
                    return;
                }
                while (i8 < i6) {
                    int i10 = i7 + 1;
                    int i11 = i7 + 2;
                    aVar.t(fArr[i7], fArr[i10], fArr[i11]);
                    aVar.m(matrix4);
                    fArr[i7] = aVar.f1729a;
                    fArr[i10] = aVar.f1730b;
                    fArr[i11] = aVar.f1731c;
                    i7 += i2;
                    i8++;
                }
                return;
            }
            throw new IndexOutOfBoundsException("start = " + i5 + ", count = " + i6 + ", vertexSize = " + i2 + ", length = " + fArr.length);
        }
        throw new IndexOutOfBoundsException();
    }

    public Mesh setInstanceData(FloatBuffer floatBuffer, int i2) {
        InstanceData instanceData = this.instances;
        if (instanceData != null) {
            instanceData.setInstanceData(floatBuffer, i2);
            return this;
        }
        throw new m("An InstanceBufferObject must be set before setting instance data!");
    }

    public b0.a calculateBoundingBox(b0.a aVar, int i2, int i3) {
        aVar.f();
        return extendBoundingBox(aVar, i2, i3);
    }

    public Mesh setInstanceData(FloatBuffer floatBuffer) {
        InstanceData instanceData = this.instances;
        if (instanceData != null) {
            instanceData.setInstanceData(floatBuffer, floatBuffer.limit());
            return this;
        }
        throw new m("An InstanceBufferObject must be set before setting instance data!");
    }

    public Mesh(boolean z2, int i2, int i3, VertexAttributes vertexAttributes) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new com.badlogic.gdx.math.a();
        this.vertices = makeVertexBuffer(z2, i2, vertexAttributes);
        this.indices = new IndexBufferObject(z2, i3);
        this.isVertexArray = false;
        addManagedMesh(Gdx.app, this);
    }

    public b0.a calculateBoundingBox(b0.a aVar, int i2, int i3, Matrix4 matrix4) {
        aVar.f();
        return extendBoundingBox(aVar, i2, i3, matrix4);
    }

    public Mesh(boolean z2, boolean z3, int i2, int i3, VertexAttributes vertexAttributes) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new com.badlogic.gdx.math.a();
        this.vertices = makeVertexBuffer(z2, i2, vertexAttributes);
        this.indices = new IndexBufferObject(z3, i3);
        this.isVertexArray = false;
        addManagedMesh(Gdx.app, this);
    }

    public Mesh(VertexDataType vertexDataType, boolean z2, int i2, int i3, VertexAttribute... vertexAttributeArr) {
        this(vertexDataType, z2, i2, i3, new VertexAttributes(vertexAttributeArr));
    }

    public Mesh copy(boolean z2) {
        return copy(z2, false, null);
    }

    public Mesh(VertexDataType vertexDataType, boolean z2, int i2, int i3, VertexAttributes vertexAttributes) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new com.badlogic.gdx.math.a();
        int i4 = AnonymousClass1.$SwitchMap$com$badlogic$gdx$graphics$Mesh$VertexDataType[vertexDataType.ordinal()];
        if (i4 == 1) {
            this.vertices = new VertexBufferObject(z2, i2, vertexAttributes);
            this.indices = new IndexBufferObject(z2, i3);
            this.isVertexArray = false;
        } else if (i4 == 2) {
            this.vertices = new VertexBufferObjectSubData(z2, i2, vertexAttributes);
            this.indices = new IndexBufferObjectSubData(z2, i3);
            this.isVertexArray = false;
        } else if (i4 != 3) {
            this.vertices = new VertexArray(i2, vertexAttributes);
            this.indices = new IndexArray(i3);
            this.isVertexArray = true;
        } else {
            this.vertices = new VertexBufferObjectWithVAO(z2, i2, vertexAttributes);
            this.indices = new IndexBufferObjectSubData(z2, i3);
            this.isVertexArray = false;
        }
        addManagedMesh(Gdx.app, this);
    }
}
