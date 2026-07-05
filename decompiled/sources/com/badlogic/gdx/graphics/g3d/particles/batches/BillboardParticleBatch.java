package com.badlogic.gdx.graphics.g3d.particles.batches;

import a0.j;
import a0.k;
import a0.o;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.BillboardControllerRenderData;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BillboardParticleBatch extends BufferedParticleBatch<BillboardControllerRenderData> {
    private static final VertexAttributes CPU_ATTRIBUTES;
    private static final int CPU_COLOR_OFFSET;
    private static final int CPU_POSITION_OFFSET;
    private static final int CPU_UV_OFFSET;
    private static final int CPU_VERTEX_SIZE;
    private static final VertexAttributes GPU_ATTRIBUTES;
    private static final int GPU_COLOR_OFFSET;
    private static final int GPU_POSITION_OFFSET;
    private static final int GPU_SIZE_ROTATION_OFFSET;
    private static final int GPU_UV_OFFSET;
    private static final int GPU_VERTEX_SIZE;
    private static final int MAX_PARTICLES_PER_MESH = 8191;
    private static final int MAX_VERTICES_PER_MESH = 32764;
    protected static final int directionUsage = 1024;
    protected static final int sizeAndRotationUsage = 512;
    protected BlendingAttribute blendingAttribute;
    private VertexAttributes currentAttributes;
    private int currentVertexSize;
    protected DepthTestAttribute depthTestAttribute;
    private short[] indices;
    protected ParticleShader.AlignMode mode;
    private RenderablePool renderablePool;
    private a<Renderable> renderables;
    Shader shader;
    protected Texture texture;
    protected boolean useGPU;
    private float[] vertices;
    protected static final com.badlogic.gdx.math.a TMP_V1 = new com.badlogic.gdx.math.a();
    protected static final com.badlogic.gdx.math.a TMP_V2 = new com.badlogic.gdx.math.a();
    protected static final com.badlogic.gdx.math.a TMP_V3 = new com.badlogic.gdx.math.a();
    protected static final com.badlogic.gdx.math.a TMP_V4 = new com.badlogic.gdx.math.a();
    protected static final com.badlogic.gdx.math.a TMP_V5 = new com.badlogic.gdx.math.a();
    protected static final com.badlogic.gdx.math.a TMP_V6 = new com.badlogic.gdx.math.a();
    protected static final k TMP_M3 = new k();

    public static class Config {
        ParticleShader.AlignMode mode;
        boolean useGPU;

        public Config() {
        }

        public Config(boolean z2, ParticleShader.AlignMode alignMode) {
            this.useGPU = z2;
            this.mode = alignMode;
        }
    }

    private class RenderablePool extends c0<Renderable> {
        public RenderablePool() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.c0
        public Renderable newObject() {
            return BillboardParticleBatch.this.allocRenderable();
        }
    }

    static {
        VertexAttributes vertexAttributes = new VertexAttributes(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"), new VertexAttribute(2, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(512, 4, "a_sizeAndRotation"));
        GPU_ATTRIBUTES = vertexAttributes;
        VertexAttributes vertexAttributes2 = new VertexAttributes(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"), new VertexAttribute(2, 4, ShaderProgram.COLOR_ATTRIBUTE));
        CPU_ATTRIBUTES = vertexAttributes2;
        GPU_POSITION_OFFSET = (short) (vertexAttributes.findByUsage(1).offset / 4);
        GPU_UV_OFFSET = (short) (vertexAttributes.findByUsage(16).offset / 4);
        GPU_SIZE_ROTATION_OFFSET = (short) (vertexAttributes.findByUsage(512).offset / 4);
        GPU_COLOR_OFFSET = (short) (vertexAttributes.findByUsage(2).offset / 4);
        GPU_VERTEX_SIZE = vertexAttributes.vertexSize / 4;
        CPU_POSITION_OFFSET = (short) (vertexAttributes2.findByUsage(1).offset / 4);
        CPU_UV_OFFSET = (short) (vertexAttributes2.findByUsage(16).offset / 4);
        CPU_COLOR_OFFSET = (short) (vertexAttributes2.findByUsage(2).offset / 4);
        CPU_VERTEX_SIZE = vertexAttributes2.vertexSize / 4;
    }

    public BillboardParticleBatch(ParticleShader.AlignMode alignMode, boolean z2, int i2, BlendingAttribute blendingAttribute, DepthTestAttribute depthTestAttribute) {
        super(BillboardControllerRenderData.class);
        this.currentVertexSize = 0;
        this.useGPU = false;
        this.mode = ParticleShader.AlignMode.Screen;
        this.renderables = new a<>();
        this.renderablePool = new RenderablePool();
        this.blendingAttribute = blendingAttribute;
        this.depthTestAttribute = depthTestAttribute;
        if (blendingAttribute == null) {
            this.blendingAttribute = new BlendingAttribute(1, GL20.GL_ONE_MINUS_SRC_ALPHA, 1.0f);
        }
        if (this.depthTestAttribute == null) {
            this.depthTestAttribute = new DepthTestAttribute(GL20.GL_LEQUAL, false);
        }
        allocIndices();
        initRenderData();
        ensureCapacity(i2);
        setUseGpu(z2);
        setAlignMode(alignMode);
    }

    private void allocIndices() {
        this.indices = new short[49146];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 49146) {
            short[] sArr = this.indices;
            short s = (short) i3;
            sArr[i2] = s;
            sArr[i2 + 1] = (short) (i3 + 1);
            short s2 = (short) (i3 + 2);
            sArr[i2 + 2] = s2;
            sArr[i2 + 3] = s2;
            sArr[i2 + 4] = (short) (i3 + 3);
            sArr[i2 + 5] = s;
            i2 += 6;
            i3 += 4;
        }
    }

    private void allocRenderables(int i2) {
        float f2 = i2 / MAX_PARTICLES_PER_MESH;
        o oVar = j.f69a;
        int i3 = 16384 - ((int) (16384.0d - ((double) f2)));
        int free = this.renderablePool.getFree();
        if (free < i3) {
            int i4 = i3 - free;
            for (int i5 = 0; i5 < i4; i5++) {
                RenderablePool renderablePool = this.renderablePool;
                renderablePool.free(renderablePool.newObject());
            }
        }
    }

    private void allocShader() {
        Renderable renderableAllocRenderable = allocRenderable();
        Shader shader = getShader(renderableAllocRenderable);
        renderableAllocRenderable.shader = shader;
        this.shader = shader;
        this.renderablePool.free(renderableAllocRenderable);
    }

    private void clearRenderablesPool() {
        this.renderablePool.freeAll(this.renderables);
        int free = this.renderablePool.getFree();
        for (int i2 = 0; i2 < free; i2++) {
            this.renderablePool.obtain().meshPart.mesh.dispose();
        }
        this.renderables.clear();
    }

    private void fillVerticesGPU(int[] iArr) {
        a.b it = this.renderData.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            BillboardControllerRenderData billboardControllerRenderData = (BillboardControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = billboardControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = billboardControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = billboardControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = billboardControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = billboardControllerRenderData.rotationChannel;
            int i3 = billboardControllerRenderData.controller.particles.size;
            int i4 = 0;
            while (i4 < i3) {
                int i5 = iArr[i2] * this.currentVertexSize * 4;
                float f2 = floatChannel.data[floatChannel.strideSize * i4];
                int i6 = floatChannel2.strideSize * i4;
                int i7 = floatChannel3.strideSize * i4;
                int i8 = floatChannel4.strideSize * i4;
                int i9 = floatChannel5.strideSize * i4;
                a.b bVar = it;
                float[] fArr = floatChannel3.data;
                float f3 = fArr[i7];
                float f4 = fArr[i7 + 1];
                float f5 = fArr[i7 + 2];
                float[] fArr2 = floatChannel2.data;
                float f6 = fArr2[i6];
                float f7 = fArr2[i6 + 1];
                float f8 = fArr2[i6 + 2];
                float f9 = fArr2[i6 + 3];
                int i10 = i3;
                float f10 = fArr2[i6 + 4] * f2;
                float f11 = fArr2[i6 + 5] * f2;
                float[] fArr3 = floatChannel4.data;
                float f12 = fArr3[i8];
                float f13 = fArr3[i8 + 1];
                float f14 = fArr3[i8 + 2];
                float f15 = fArr3[i8 + 3];
                float[] fArr4 = floatChannel5.data;
                float f16 = fArr4[i9];
                float f17 = fArr4[i9 + 1];
                float f18 = -f10;
                float f19 = -f11;
                putVertex(this.vertices, i5, f3, f4, f5, f6, f9, f18, f19, f16, f17, f12, f13, f14, f15);
                int i11 = i5 + this.currentVertexSize;
                putVertex(this.vertices, i11, f3, f4, f5, f8, f9, f10, f19, f16, f17, f12, f13, f14, f15);
                int i12 = i11 + this.currentVertexSize;
                putVertex(this.vertices, i12, f3, f4, f5, f8, f7, f10, f11, f16, f17, f12, f13, f14, f15);
                putVertex(this.vertices, i12 + this.currentVertexSize, f3, f4, f5, f6, f7, f18, f11, f16, f17, f12, f13, f14, f15);
                i4++;
                i2++;
                it = bVar;
                i3 = i10;
            }
        }
    }

    private void fillVerticesToScreenCPU(int[] iArr) {
        com.badlogic.gdx.math.a aVar;
        com.badlogic.gdx.math.a aVar2 = TMP_V3;
        aVar2.u(this.camera.direction);
        aVar2.s(-1.0f);
        com.badlogic.gdx.math.a aVar3 = TMP_V4;
        aVar3.u(this.camera.up);
        aVar3.d(aVar2);
        aVar3.n();
        com.badlogic.gdx.math.a aVar4 = this.camera.up;
        a.b it = this.renderData.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            BillboardControllerRenderData billboardControllerRenderData = (BillboardControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = billboardControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = billboardControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = billboardControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = billboardControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = billboardControllerRenderData.rotationChannel;
            int i3 = billboardControllerRenderData.controller.particles.size;
            int i4 = 0;
            while (i4 < i3) {
                int i5 = iArr[i2] * this.currentVertexSize * 4;
                float f2 = floatChannel.data[floatChannel.strideSize * i4];
                int i6 = floatChannel2.strideSize * i4;
                a.b bVar = it;
                int i7 = floatChannel3.strideSize * i4;
                int i8 = i3;
                int i9 = floatChannel4.strideSize * i4;
                ParallelArray.FloatChannel floatChannel6 = floatChannel;
                int i10 = floatChannel5.strideSize * i4;
                int i11 = i2;
                float[] fArr = floatChannel3.data;
                ParallelArray.FloatChannel floatChannel7 = floatChannel3;
                float f3 = fArr[i7];
                int i12 = i4;
                float f4 = fArr[i7 + 1];
                float f5 = fArr[i7 + 2];
                float[] fArr2 = floatChannel2.data;
                float f6 = fArr2[i6];
                float f7 = fArr2[i6 + 1];
                float f8 = fArr2[i6 + 2];
                float f9 = fArr2[i6 + 3];
                ParallelArray.FloatChannel floatChannel8 = floatChannel2;
                float f10 = fArr2[i6 + 4] * f2;
                float f11 = fArr2[i6 + 5] * f2;
                float[] fArr3 = floatChannel4.data;
                float f12 = fArr3[i9];
                float f13 = fArr3[i9 + 1];
                float f14 = fArr3[i9 + 2];
                float f15 = fArr3[i9 + 3];
                float[] fArr4 = floatChannel5.data;
                float f16 = fArr4[i10];
                float f17 = fArr4[i10 + 1];
                com.badlogic.gdx.math.a aVar5 = TMP_V1;
                aVar5.getClass();
                ParallelArray.FloatChannel floatChannel9 = floatChannel4;
                ParallelArray.FloatChannel floatChannel10 = floatChannel5;
                aVar5.t(aVar3.f1729a, aVar3.f1730b, aVar3.f1731c);
                aVar5.s(f10);
                com.badlogic.gdx.math.a aVar6 = TMP_V2;
                aVar6.u(aVar4);
                aVar6.s(f11);
                if (f16 != 1.0f) {
                    k kVar = TMP_M3;
                    kVar.d(aVar2, f16, f17);
                    float[] fArr5 = this.vertices;
                    com.badlogic.gdx.math.a aVar7 = TMP_V6;
                    aVar = aVar2;
                    aVar7.t((-aVar5.f1729a) - aVar6.f1729a, (-aVar5.f1730b) - aVar6.f1730b, (-aVar5.f1731c) - aVar6.f1731c);
                    aVar7.l(kVar);
                    aVar7.a(f3, f4, f5);
                    putVertex(fArr5, i5, aVar7, f6, f9, f12, f13, f14, f15);
                    int i13 = i5 + this.currentVertexSize;
                    float[] fArr6 = this.vertices;
                    aVar7.t(aVar5.f1729a - aVar6.f1729a, aVar5.f1730b - aVar6.f1730b, aVar5.f1731c - aVar6.f1731c);
                    aVar7.l(kVar);
                    aVar7.a(f3, f4, f5);
                    putVertex(fArr6, i13, aVar7, f8, f9, f12, f13, f14, f15);
                    int i14 = i13 + this.currentVertexSize;
                    float[] fArr7 = this.vertices;
                    aVar7.t(aVar5.f1729a + aVar6.f1729a, aVar5.f1730b + aVar6.f1730b, aVar5.f1731c + aVar6.f1731c);
                    aVar7.l(kVar);
                    aVar7.a(f3, f4, f5);
                    putVertex(fArr7, i14, aVar7, f8, f7, f12, f13, f14, f15);
                    int i15 = i14 + this.currentVertexSize;
                    float[] fArr8 = this.vertices;
                    aVar7.t((-aVar5.f1729a) + aVar6.f1729a, (-aVar5.f1730b) + aVar6.f1730b, (-aVar5.f1731c) + aVar6.f1731c);
                    aVar7.l(kVar);
                    aVar7.a(f3, f4, f5);
                    putVertex(fArr8, i15, aVar7, f6, f7, f12, f13, f14, f15);
                } else {
                    aVar = aVar2;
                    float[] fArr9 = this.vertices;
                    com.badlogic.gdx.math.a aVar8 = TMP_V6;
                    aVar8.t(((-aVar5.f1729a) - aVar6.f1729a) + f3, ((-aVar5.f1730b) - aVar6.f1730b) + f4, ((-aVar5.f1731c) - aVar6.f1731c) + f5);
                    putVertex(fArr9, i5, aVar8, f6, f9, f12, f13, f14, f15);
                    int i16 = i5 + this.currentVertexSize;
                    float[] fArr10 = this.vertices;
                    aVar8.t((aVar5.f1729a - aVar6.f1729a) + f3, (aVar5.f1730b - aVar6.f1730b) + f4, (aVar5.f1731c - aVar6.f1731c) + f5);
                    putVertex(fArr10, i16, aVar8, f8, f9, f12, f13, f14, f15);
                    int i17 = i16 + this.currentVertexSize;
                    float[] fArr11 = this.vertices;
                    aVar8.t(aVar5.f1729a + aVar6.f1729a + f3, aVar5.f1730b + aVar6.f1730b + f4, aVar5.f1731c + aVar6.f1731c + f5);
                    putVertex(fArr11, i17, aVar8, f8, f7, f12, f13, f14, f15);
                    int i18 = i17 + this.currentVertexSize;
                    float[] fArr12 = this.vertices;
                    aVar8.t((-aVar5.f1729a) + aVar6.f1729a + f3, (-aVar5.f1730b) + aVar6.f1730b + f4, (-aVar5.f1731c) + aVar6.f1731c + f5);
                    putVertex(fArr12, i18, aVar8, f6, f7, f12, f13, f14, f15);
                }
                i4 = i12 + 1;
                i2 = i11 + 1;
                it = bVar;
                i3 = i8;
                floatChannel = floatChannel6;
                floatChannel3 = floatChannel7;
                floatChannel2 = floatChannel8;
                floatChannel4 = floatChannel9;
                floatChannel5 = floatChannel10;
                aVar2 = aVar;
            }
        }
    }

    private void fillVerticesToViewPointCPU(int[] iArr) {
        a.b it = this.renderData.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            BillboardControllerRenderData billboardControllerRenderData = (BillboardControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = billboardControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = billboardControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = billboardControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = billboardControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = billboardControllerRenderData.rotationChannel;
            int i3 = billboardControllerRenderData.controller.particles.size;
            int i4 = 0;
            while (i4 < i3) {
                int i5 = iArr[i2] * this.currentVertexSize * 4;
                float f2 = floatChannel.data[floatChannel.strideSize * i4];
                int i6 = floatChannel2.strideSize * i4;
                int i7 = floatChannel3.strideSize * i4;
                int i8 = floatChannel4.strideSize * i4;
                int i9 = floatChannel5.strideSize * i4;
                a.b bVar = it;
                float[] fArr = floatChannel3.data;
                int i10 = i3;
                float f3 = fArr[i7];
                ParallelArray.FloatChannel floatChannel6 = floatChannel;
                float f4 = fArr[i7 + 1];
                float f5 = fArr[i7 + 2];
                float[] fArr2 = floatChannel2.data;
                float f6 = fArr2[i6];
                float f7 = fArr2[i6 + 1];
                float f8 = fArr2[i6 + 2];
                float f9 = fArr2[i6 + 3];
                ParallelArray.FloatChannel floatChannel7 = floatChannel2;
                float f10 = fArr2[i6 + 4] * f2;
                float f11 = fArr2[i6 + 5] * f2;
                float[] fArr3 = floatChannel4.data;
                float f12 = fArr3[i8];
                float f13 = fArr3[i8 + 1];
                float f14 = fArr3[i8 + 2];
                float f15 = fArr3[i8 + 3];
                float[] fArr4 = floatChannel5.data;
                float f16 = fArr4[i9];
                float f17 = fArr4[i9 + 1];
                com.badlogic.gdx.math.a aVar = TMP_V3;
                aVar.u(this.camera.position);
                aVar.v(f3, f4, f5);
                aVar.n();
                com.badlogic.gdx.math.a aVar2 = TMP_V1;
                ParallelArray.FloatChannel floatChannel8 = floatChannel3;
                aVar2.u(this.camera.up);
                aVar2.d(aVar);
                aVar2.n();
                com.badlogic.gdx.math.a aVar3 = TMP_V2;
                aVar3.getClass();
                ParallelArray.FloatChannel floatChannel9 = floatChannel4;
                ParallelArray.FloatChannel floatChannel10 = floatChannel5;
                int i11 = i2;
                aVar3.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
                aVar3.d(aVar2);
                aVar2.s(f10);
                aVar3.s(f11);
                if (f16 != 1.0f) {
                    k kVar = TMP_M3;
                    kVar.d(aVar, f16, f17);
                    float[] fArr5 = this.vertices;
                    com.badlogic.gdx.math.a aVar4 = TMP_V6;
                    aVar4.t((-aVar2.f1729a) - aVar3.f1729a, (-aVar2.f1730b) - aVar3.f1730b, (-aVar2.f1731c) - aVar3.f1731c);
                    aVar4.l(kVar);
                    aVar4.a(f3, f4, f5);
                    putVertex(fArr5, i5, aVar4, f6, f9, f12, f13, f14, f15);
                    int i12 = i5 + this.currentVertexSize;
                    float[] fArr6 = this.vertices;
                    aVar4.t(aVar2.f1729a - aVar3.f1729a, aVar2.f1730b - aVar3.f1730b, aVar2.f1731c - aVar3.f1731c);
                    aVar4.l(kVar);
                    aVar4.a(f3, f4, f5);
                    putVertex(fArr6, i12, aVar4, f8, f9, f12, f13, f14, f15);
                    int i13 = i12 + this.currentVertexSize;
                    float[] fArr7 = this.vertices;
                    aVar4.t(aVar2.f1729a + aVar3.f1729a, aVar2.f1730b + aVar3.f1730b, aVar2.f1731c + aVar3.f1731c);
                    aVar4.l(kVar);
                    aVar4.a(f3, f4, f5);
                    putVertex(fArr7, i13, aVar4, f8, f7, f12, f13, f14, f15);
                    int i14 = i13 + this.currentVertexSize;
                    float[] fArr8 = this.vertices;
                    aVar4.t((-aVar2.f1729a) + aVar3.f1729a, (-aVar2.f1730b) + aVar3.f1730b, (-aVar2.f1731c) + aVar3.f1731c);
                    aVar4.l(kVar);
                    aVar4.a(f3, f4, f5);
                    putVertex(fArr8, i14, aVar4, f6, f7, f12, f13, f14, f15);
                } else {
                    float[] fArr9 = this.vertices;
                    com.badlogic.gdx.math.a aVar5 = TMP_V6;
                    aVar5.t(((-aVar2.f1729a) - aVar3.f1729a) + f3, ((-aVar2.f1730b) - aVar3.f1730b) + f4, ((-aVar2.f1731c) - aVar3.f1731c) + f5);
                    putVertex(fArr9, i5, aVar5, f6, f9, f12, f13, f14, f15);
                    int i15 = i5 + this.currentVertexSize;
                    float[] fArr10 = this.vertices;
                    aVar5.t((aVar2.f1729a - aVar3.f1729a) + f3, (aVar2.f1730b - aVar3.f1730b) + f4, (aVar2.f1731c - aVar3.f1731c) + f5);
                    putVertex(fArr10, i15, aVar5, f8, f9, f12, f13, f14, f15);
                    int i16 = i15 + this.currentVertexSize;
                    float[] fArr11 = this.vertices;
                    aVar5.t(aVar2.f1729a + aVar3.f1729a + f3, aVar2.f1730b + aVar3.f1730b + f4, aVar2.f1731c + aVar3.f1731c + f5);
                    putVertex(fArr11, i16, aVar5, f8, f7, f12, f13, f14, f15);
                    int i17 = i16 + this.currentVertexSize;
                    float[] fArr12 = this.vertices;
                    aVar5.t((-aVar2.f1729a) + aVar3.f1729a + f3, (-aVar2.f1730b) + aVar3.f1730b + f4, (-aVar2.f1731c) + aVar3.f1731c + f5);
                    putVertex(fArr12, i17, aVar5, f6, f7, f12, f13, f14, f15);
                }
                i4++;
                i2 = i11 + 1;
                it = bVar;
                i3 = i10;
                floatChannel = floatChannel6;
                floatChannel2 = floatChannel7;
                floatChannel3 = floatChannel8;
                floatChannel4 = floatChannel9;
                floatChannel5 = floatChannel10;
            }
        }
    }

    private void initRenderData() {
        setVertexData();
        clearRenderablesPool();
        allocShader();
        resetCapacity();
    }

    private static void putVertex(float[] fArr, int i2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        int i3 = GPU_POSITION_OFFSET;
        fArr[i2 + i3] = f2;
        fArr[i2 + i3 + 1] = f3;
        fArr[i3 + i2 + 2] = f4;
        int i4 = GPU_UV_OFFSET;
        fArr[i2 + i4] = f5;
        fArr[i4 + i2 + 1] = f6;
        int i5 = GPU_SIZE_ROTATION_OFFSET;
        fArr[i2 + i5] = f7;
        fArr[i2 + i5 + 1] = f8;
        fArr[i2 + i5 + 2] = f9;
        fArr[i5 + i2 + 3] = f10;
        int i6 = GPU_COLOR_OFFSET;
        fArr[i2 + i6] = f11;
        fArr[i2 + i6 + 1] = f12;
        fArr[i2 + i6 + 2] = f13;
        fArr[i6 + i2 + 3] = f14;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    public void allocParticlesData(int i2) {
        this.vertices = new float[this.currentVertexSize * 4 * i2];
        allocRenderables(i2);
    }

    protected Renderable allocRenderable() {
        Renderable renderable = new Renderable();
        MeshPart meshPart = renderable.meshPart;
        meshPart.primitiveType = 4;
        meshPart.offset = 0;
        renderable.material = new Material(this.blendingAttribute, this.depthTestAttribute, TextureAttribute.createDiffuse(this.texture));
        renderable.meshPart.mesh = new Mesh(false, MAX_VERTICES_PER_MESH, 49146, this.currentAttributes);
        renderable.meshPart.mesh.setIndices(this.indices);
        renderable.shader = this.shader;
        return renderable;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch, com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void begin() {
        super.begin();
        this.renderablePool.freeAll(this.renderables);
        this.renderables.clear();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void flush(int[] iArr) {
        if (this.useGPU) {
            fillVerticesGPU(iArr);
        } else {
            ParticleShader.AlignMode alignMode = this.mode;
            if (alignMode == ParticleShader.AlignMode.Screen) {
                fillVerticesToScreenCPU(iArr);
            } else if (alignMode == ParticleShader.AlignMode.ViewPoint) {
                fillVerticesToViewPointCPU(iArr);
            }
        }
        int i2 = this.bufferedParticlesCount * 4;
        int i3 = 0;
        while (i3 < i2) {
            int iMin = Math.min(i2 - i3, MAX_VERTICES_PER_MESH);
            Renderable renderableObtain = this.renderablePool.obtain();
            MeshPart meshPart = renderableObtain.meshPart;
            meshPart.size = (iMin / 4) * 6;
            Mesh mesh = meshPart.mesh;
            float[] fArr = this.vertices;
            int i4 = this.currentVertexSize;
            mesh.setVertices(fArr, i4 * i3, i4 * iMin);
            renderableObtain.meshPart.update();
            this.renderables.a(renderableObtain);
            i3 += iMin;
        }
    }

    public ParticleShader.AlignMode getAlignMode() {
        return this.mode;
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(a<Renderable> aVar, c0<Renderable> c0Var) {
        a.b<Renderable> it = this.renderables.iterator();
        while (it.hasNext()) {
            aVar.a(c0Var.obtain().set(it.next()));
        }
    }

    protected Shader getShader(Renderable renderable) {
        Shader particleShader = this.useGPU ? new ParticleShader(renderable, new ParticleShader.Config(this.mode)) : new DefaultShader(renderable);
        particleShader.init();
        return particleShader;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public boolean isUseGPU() {
        return this.useGPU;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData("billboardBatch");
        if (saveData != null) {
            setTexture((Texture) dVar.f(saveData.loadAsset()));
            Config config = (Config) saveData.load("cfg");
            setUseGpu(config.useGPU);
            setAlignMode(config.mode);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveDataCreateSaveData = resourceData.createSaveData("billboardBatch");
        saveDataCreateSaveData.save("cfg", new Config(this.useGPU, this.mode));
        saveDataCreateSaveData.saveAsset(dVar.h(this.texture), Texture.class);
    }

    public void setAlignMode(ParticleShader.AlignMode alignMode) {
        if (alignMode != this.mode) {
            this.mode = alignMode;
            if (this.useGPU) {
                initRenderData();
                allocRenderables(this.bufferedParticlesCount);
            }
        }
    }

    public void setTexture(Texture texture) {
        this.renderablePool.freeAll(this.renderables);
        this.renderables.clear();
        int free = this.renderablePool.getFree();
        for (int i2 = 0; i2 < free; i2++) {
            ((TextureAttribute) this.renderablePool.obtain().material.get(TextureAttribute.Diffuse)).textureDescription.texture = texture;
        }
        this.texture = texture;
    }

    public void setUseGpu(boolean z2) {
        if (this.useGPU != z2) {
            this.useGPU = z2;
            initRenderData();
            allocRenderables(this.bufferedParticlesCount);
        }
    }

    public void setVertexData() {
        if (this.useGPU) {
            this.currentAttributes = GPU_ATTRIBUTES;
            this.currentVertexSize = GPU_VERTEX_SIZE;
        } else {
            this.currentAttributes = CPU_ATTRIBUTES;
            this.currentVertexSize = CPU_VERTEX_SIZE;
        }
    }

    private static void putVertex(float[] fArr, int i2, com.badlogic.gdx.math.a aVar, float f2, float f3, float f4, float f5, float f6, float f7) {
        int i3 = CPU_POSITION_OFFSET;
        fArr[i2 + i3] = aVar.f1729a;
        fArr[i2 + i3 + 1] = aVar.f1730b;
        fArr[i3 + i2 + 2] = aVar.f1731c;
        int i4 = CPU_UV_OFFSET;
        fArr[i2 + i4] = f2;
        fArr[i4 + i2 + 1] = f3;
        int i5 = CPU_COLOR_OFFSET;
        fArr[i2 + i5] = f4;
        fArr[i2 + i5 + 1] = f5;
        fArr[i2 + i5 + 2] = f6;
        fArr[i2 + i5 + 3] = f7;
    }

    public BillboardParticleBatch(ParticleShader.AlignMode alignMode, boolean z2, int i2) {
        this(alignMode, z2, i2, null, null);
    }

    public BillboardParticleBatch() {
        this(ParticleShader.AlignMode.Screen, false, 100);
    }

    public BillboardParticleBatch(int i2) {
        this(ParticleShader.AlignMode.Screen, false, i2);
    }
}
