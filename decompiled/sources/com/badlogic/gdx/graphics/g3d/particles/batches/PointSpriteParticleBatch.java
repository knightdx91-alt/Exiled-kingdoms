package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.PointSpriteControllerRenderData;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PointSpriteParticleBatch extends BufferedParticleBatch<PointSpriteControllerRenderData> {
    protected static final VertexAttributes CPU_ATTRIBUTES;
    protected static final int CPU_COLOR_OFFSET;
    protected static final int CPU_POSITION_OFFSET;
    protected static final int CPU_REGION_OFFSET;
    protected static final int CPU_SIZE_AND_ROTATION_OFFSET;
    protected static final int CPU_VERTEX_SIZE;
    protected static final a TMP_V1 = new a();
    private static boolean pointSpritesEnabled = false;
    protected static final int sizeAndRotationUsage = 512;
    Renderable renderable;
    private float[] vertices;

    static {
        VertexAttributes vertexAttributes = new VertexAttributes(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(2, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 4, "a_region"), new VertexAttribute(512, 3, "a_sizeAndRotation"));
        CPU_ATTRIBUTES = vertexAttributes;
        CPU_VERTEX_SIZE = (short) (vertexAttributes.vertexSize / 4);
        CPU_POSITION_OFFSET = (short) (vertexAttributes.findByUsage(1).offset / 4);
        CPU_COLOR_OFFSET = (short) (vertexAttributes.findByUsage(2).offset / 4);
        CPU_REGION_OFFSET = (short) (vertexAttributes.findByUsage(16).offset / 4);
        CPU_SIZE_AND_ROTATION_OFFSET = (short) (vertexAttributes.findByUsage(512).offset / 4);
    }

    public PointSpriteParticleBatch() {
        this(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
    }

    private static void enablePointSprites() {
        Gdx.gl.glEnable(GL20.GL_VERTEX_PROGRAM_POINT_SIZE);
        if (Gdx.app.getType() == Application.a.f1564b) {
            Gdx.gl.glEnable(34913);
        }
        pointSpritesEnabled = true;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void allocParticlesData(int i2) {
        this.vertices = new float[CPU_VERTEX_SIZE * i2];
        Mesh mesh = this.renderable.meshPart.mesh;
        if (mesh != null) {
            mesh.dispose();
        }
        this.renderable.meshPart.mesh = new Mesh(false, i2, 0, CPU_ATTRIBUTES);
    }

    protected void allocRenderable() {
        Renderable renderable = new Renderable();
        this.renderable = renderable;
        MeshPart meshPart = renderable.meshPart;
        meshPart.primitiveType = 0;
        meshPart.offset = 0;
        renderable.material = new Material(new BlendingAttribute(1, GL20.GL_ONE_MINUS_SRC_ALPHA, 1.0f), new DepthTestAttribute(GL20.GL_LEQUAL, false), TextureAttribute.createDiffuse((Texture) null));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void flush(int[] iArr) {
        a.b it = this.renderData.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            PointSpriteControllerRenderData pointSpriteControllerRenderData = (PointSpriteControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = pointSpriteControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = pointSpriteControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = pointSpriteControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = pointSpriteControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = pointSpriteControllerRenderData.rotationChannel;
            int i3 = 0;
            while (i3 < pointSpriteControllerRenderData.controller.particles.size) {
                int i4 = iArr[i2] * CPU_VERTEX_SIZE;
                int i5 = floatChannel2.strideSize * i3;
                int i6 = floatChannel3.strideSize * i3;
                int i7 = floatChannel4.strideSize * i3;
                int i8 = floatChannel5.strideSize * i3;
                float[] fArr = this.vertices;
                int i9 = i4 + CPU_POSITION_OFFSET;
                a.b bVar = it;
                float[] fArr2 = floatChannel3.data;
                fArr[i9] = fArr2[i6];
                fArr[i9 + 1] = fArr2[i6 + 1];
                fArr[i9 + 2] = fArr2[i6 + 2];
                int i10 = CPU_COLOR_OFFSET + i4;
                float[] fArr3 = floatChannel4.data;
                fArr[i10] = fArr3[i7];
                fArr[i10 + 1] = fArr3[i7 + 1];
                fArr[i10 + 2] = fArr3[i7 + 2];
                fArr[i10 + 3] = fArr3[i7 + 3];
                int i11 = CPU_SIZE_AND_ROTATION_OFFSET + i4;
                fArr[i11] = floatChannel.data[floatChannel.strideSize * i3];
                float[] fArr4 = floatChannel5.data;
                fArr[i11 + 1] = fArr4[i8];
                fArr[i11 + 2] = fArr4[i8 + 1];
                int i12 = i4 + CPU_REGION_OFFSET;
                float[] fArr5 = floatChannel2.data;
                fArr[i12] = fArr5[i5];
                fArr[i12 + 1] = fArr5[i5 + 1];
                fArr[i12 + 2] = fArr5[i5 + 2];
                fArr[i12 + 3] = fArr5[i5 + 3];
                i3++;
                i2++;
                it = bVar;
            }
        }
        MeshPart meshPart = this.renderable.meshPart;
        int i13 = this.bufferedParticlesCount;
        meshPart.size = i13;
        meshPart.mesh.setVertices(this.vertices, 0, i13 * CPU_VERTEX_SIZE);
        this.renderable.meshPart.update();
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(com.badlogic.gdx.utils.a<Renderable> aVar, c0<Renderable> c0Var) {
        if (this.bufferedParticlesCount > 0) {
            aVar.a(c0Var.obtain().set(this.renderable));
        }
    }

    public Texture getTexture() {
        return (Texture) ((TextureAttribute) this.renderable.material.get(TextureAttribute.Diffuse)).textureDescription.texture;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData("pointSpriteBatch");
        if (saveData != null) {
            setTexture((Texture) dVar.f(saveData.loadAsset()));
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        resourceData.createSaveData("pointSpriteBatch").saveAsset(dVar.h(getTexture()), Texture.class);
    }

    public void setTexture(Texture texture) {
        ((TextureAttribute) this.renderable.material.get(TextureAttribute.Diffuse)).textureDescription.texture = texture;
    }

    public PointSpriteParticleBatch(int i2) {
        this(i2, new ParticleShader.Config(ParticleShader.ParticleType.Point));
    }

    public PointSpriteParticleBatch(int i2, ParticleShader.Config config) {
        super(PointSpriteControllerRenderData.class);
        if (!pointSpritesEnabled) {
            enablePointSprites();
        }
        allocRenderable();
        ensureCapacity(i2);
        this.renderable.shader = new ParticleShader(this.renderable, config);
        this.renderable.shader.init();
    }
}
