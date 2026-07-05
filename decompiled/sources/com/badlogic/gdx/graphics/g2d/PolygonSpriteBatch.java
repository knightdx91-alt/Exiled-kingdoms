package com.badlogic.gdx.graphics.g2d;

import a.a;
import a0.j;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PolygonSpriteBatch implements PolygonBatch {
    private int blendDstFunc;
    private int blendDstFuncAlpha;
    private int blendSrcFunc;
    private int blendSrcFuncAlpha;
    private boolean blendingDisabled;
    private final Color color;
    float colorPacked;
    private final Matrix4 combinedMatrix;
    private ShaderProgram customShader;
    private boolean drawing;
    private float invTexHeight;
    private float invTexWidth;
    private Texture lastTexture;
    public int maxTrianglesInBatch;
    private Mesh mesh;
    private boolean ownsShader;
    private final Matrix4 projectionMatrix;
    public int renderCalls;
    private final ShaderProgram shader;
    public int totalRenderCalls;
    private final Matrix4 transformMatrix;
    private int triangleIndex;
    private final short[] triangles;
    private int vertexIndex;
    private final float[] vertices;

    public PolygonSpriteBatch() {
        this(2000, null);
    }

    private void switchTexture(Texture texture) {
        flush();
        this.lastTexture = texture;
        this.invTexWidth = 1.0f / texture.getWidth();
        this.invTexHeight = 1.0f / texture.getHeight();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void begin() {
        if (this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.end must be called before begin.");
        }
        this.renderCalls = 0;
        Gdx.gl.glDepthMask(false);
        ShaderProgram shaderProgram = this.customShader;
        if (shaderProgram != null) {
            shaderProgram.bind();
        } else {
            this.shader.bind();
        }
        setupMatrices();
        this.drawing = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void disableBlending() {
        flush();
        this.blendingDisabled = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch, com.badlogic.gdx.graphics.g2d.Batch, com.badlogic.gdx.utils.i
    public void dispose() {
        ShaderProgram shaderProgram;
        this.mesh.dispose();
        if (!this.ownsShader || (shaderProgram = this.shader) == null) {
            return;
        }
        shaderProgram.dispose();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void draw(PolygonRegion polygonRegion, float f2, float f3) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        short[] sArr2 = polygonRegion.triangles;
        int length = sArr2.length;
        float[] fArr = polygonRegion.vertices;
        int length2 = fArr.length;
        Texture texture = polygonRegion.region.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + length <= sArr.length) {
            if (((length2 * 5) / 2) + this.vertexIndex > this.vertices.length) {
                flush();
            }
        }
        int i2 = this.triangleIndex;
        int i3 = this.vertexIndex;
        int i4 = i3 / 5;
        int i5 = 0;
        while (i5 < length) {
            sArr[i2] = (short) (sArr2[i5] + i4);
            i5++;
            i2++;
        }
        this.triangleIndex = i2;
        float[] fArr2 = this.vertices;
        float f4 = this.colorPacked;
        float[] fArr3 = polygonRegion.textureCoords;
        for (int i6 = 0; i6 < length2; i6 += 2) {
            fArr2[i3] = fArr[i6] + f2;
            int i7 = i6 + 1;
            fArr2[i3 + 1] = fArr[i7] + f3;
            fArr2[i3 + 2] = f4;
            int i8 = i3 + 4;
            fArr2[i3 + 3] = fArr3[i6];
            i3 += 5;
            fArr2[i8] = fArr3[i7];
        }
        this.vertexIndex = i3;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void enableBlending() {
        flush();
        this.blendingDisabled = false;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void end() {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before end.");
        }
        if (this.vertexIndex > 0) {
            flush();
        }
        this.lastTexture = null;
        this.drawing = false;
        GL20 gl20 = Gdx.gl;
        gl20.glDepthMask(true);
        if (isBlendingEnabled()) {
            gl20.glDisable(GL20.GL_BLEND);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void flush() {
        if (this.vertexIndex == 0) {
            return;
        }
        this.renderCalls++;
        this.totalRenderCalls++;
        int i2 = this.triangleIndex;
        if (i2 > this.maxTrianglesInBatch) {
            this.maxTrianglesInBatch = i2;
        }
        this.lastTexture.bind();
        Mesh mesh = this.mesh;
        mesh.setVertices(this.vertices, 0, this.vertexIndex);
        mesh.setIndices(this.triangles, 0, i2);
        if (this.blendingDisabled) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        } else {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            int i3 = this.blendSrcFunc;
            if (i3 != -1) {
                Gdx.gl.glBlendFuncSeparate(i3, this.blendDstFunc, this.blendSrcFuncAlpha, this.blendDstFuncAlpha);
            }
        }
        ShaderProgram shaderProgram = this.customShader;
        if (shaderProgram == null) {
            shaderProgram = this.shader;
        }
        mesh.render(shaderProgram, 4, 0, i2);
        this.vertexIndex = 0;
        this.triangleIndex = 0;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendDstFunc() {
        return this.blendDstFunc;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendDstFuncAlpha() {
        return this.blendDstFuncAlpha;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendSrcFunc() {
        return this.blendSrcFunc;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendSrcFuncAlpha() {
        return this.blendSrcFuncAlpha;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public Color getColor() {
        return this.color;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public float getPackedColor() {
        return this.colorPacked;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public ShaderProgram getShader() {
        ShaderProgram shaderProgram = this.customShader;
        return shaderProgram == null ? this.shader : shaderProgram;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public boolean isBlendingEnabled() {
        return !this.blendingDisabled;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public boolean isDrawing() {
        return this.drawing;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setBlendFunction(int i2, int i3) {
        setBlendFunctionSeparate(i2, i3, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setBlendFunctionSeparate(int i2, int i3, int i4, int i5) {
        if (this.blendSrcFunc == i2 && this.blendDstFunc == i3 && this.blendSrcFuncAlpha == i4 && this.blendDstFuncAlpha == i5) {
            return;
        }
        flush();
        this.blendSrcFunc = i2;
        this.blendDstFunc = i3;
        this.blendSrcFuncAlpha = i4;
        this.blendDstFuncAlpha = i5;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(Color color) {
        this.color.set(color);
        this.colorPacked = color.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setPackedColor(float f2) {
        Color.abgr8888ToColor(this.color, f2);
        this.colorPacked = f2;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setProjectionMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            flush();
        }
        this.projectionMatrix.o(matrix4);
        if (this.drawing) {
            setupMatrices();
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setShader(ShaderProgram shaderProgram) {
        if (this.drawing) {
            flush();
        }
        this.customShader = shaderProgram;
        if (this.drawing) {
            if (shaderProgram != null) {
                shaderProgram.bind();
            } else {
                this.shader.bind();
            }
            setupMatrices();
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setTransformMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            flush();
        }
        this.transformMatrix.o(matrix4);
        if (this.drawing) {
            setupMatrices();
        }
    }

    protected void setupMatrices() {
        Matrix4 matrix4 = this.combinedMatrix;
        matrix4.o(this.projectionMatrix);
        matrix4.g(this.transformMatrix);
        ShaderProgram shaderProgram = this.customShader;
        if (shaderProgram != null) {
            shaderProgram.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.customShader.setUniformi("u_texture", 0);
        } else {
            this.shader.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.shader.setUniformi("u_texture", 0);
        }
    }

    public PolygonSpriteBatch(int i2) {
        this(i2, i2 * 2, null);
    }

    public PolygonSpriteBatch(int i2, ShaderProgram shaderProgram) {
        this(i2, i2 * 2, shaderProgram);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        this.colorPacked = this.color.toFloatBits();
    }

    public PolygonSpriteBatch(int i2, int i3, ShaderProgram shaderProgram) {
        this.invTexWidth = 0.0f;
        this.invTexHeight = 0.0f;
        this.transformMatrix = new Matrix4();
        Matrix4 matrix4 = new Matrix4();
        this.projectionMatrix = matrix4;
        this.combinedMatrix = new Matrix4();
        this.blendSrcFunc = GL20.GL_SRC_ALPHA;
        this.blendDstFunc = GL20.GL_ONE_MINUS_SRC_ALPHA;
        this.blendSrcFuncAlpha = GL20.GL_SRC_ALPHA;
        this.blendDstFuncAlpha = GL20.GL_ONE_MINUS_SRC_ALPHA;
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.maxTrianglesInBatch = 0;
        if (i2 <= 32767) {
            int i4 = i3 * 3;
            this.mesh = new Mesh(Gdx.gl30 != null ? Mesh.VertexDataType.VertexBufferObjectWithVAO : Mesh.VertexDataType.VertexArray, false, i2, i4, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
            this.vertices = new float[i2 * 5];
            this.triangles = new short[i4];
            if (shaderProgram == null) {
                this.shader = SpriteBatch.createDefaultShader();
                this.ownsShader = true;
            } else {
                this.shader = shaderProgram;
            }
            matrix4.s(0.0f, 0.0f + Gdx.graphics.getWidth(), 0.0f, 0.0f + Gdx.graphics.getHeight(), 0.0f, 1.0f);
            return;
        }
        throw new IllegalArgumentException(a.h(i2, "Can't have more than 32767 vertices per batch: "));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void draw(PolygonRegion polygonRegion, float f2, float f3, float f4, float f5) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            short[] sArr2 = polygonRegion.triangles;
            int length = sArr2.length;
            float[] fArr = polygonRegion.vertices;
            int length2 = fArr.length;
            Texture texture = polygonRegion.region.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + length <= sArr.length) {
                if (((length2 * 5) / 2) + this.vertexIndex > this.vertices.length) {
                    flush();
                }
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex;
            int i4 = i3 / 5;
            int length3 = sArr2.length;
            int i5 = 0;
            while (i5 < length3) {
                sArr[i2] = (short) (sArr2[i5] + i4);
                i5++;
                i2++;
            }
            this.triangleIndex = i2;
            float[] fArr2 = this.vertices;
            float f6 = this.colorPacked;
            float[] fArr3 = polygonRegion.textureCoords;
            float f7 = f4 / r7.regionWidth;
            float f8 = f5 / r7.regionHeight;
            for (int i6 = 0; i6 < length2; i6 += 2) {
                fArr2[i3] = (fArr[i6] * f7) + f2;
                int i7 = i6 + 1;
                fArr2[i3 + 1] = (fArr[i7] * f8) + f3;
                fArr2[i3 + 2] = f6;
                int i8 = i3 + 4;
                fArr2[i3 + 3] = fArr3[i6];
                i3 += 5;
                fArr2[i8] = fArr3[i7];
            }
            this.vertexIndex = i3;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void draw(PolygonRegion polygonRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            short[] sArr2 = polygonRegion.triangles;
            int length = sArr2.length;
            float[] fArr = polygonRegion.vertices;
            int length2 = fArr.length;
            Texture texture = polygonRegion.region.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + length <= sArr.length) {
                if (((length2 * 5) / 2) + this.vertexIndex > this.vertices.length) {
                    flush();
                }
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex;
            int i4 = i3 / 5;
            int i5 = 0;
            int i6 = 0;
            while (i6 < length) {
                sArr[i2] = (short) (sArr2[i6] + i4);
                i6++;
                i2++;
            }
            this.triangleIndex = i2;
            float[] fArr2 = this.vertices;
            float f11 = this.colorPacked;
            float[] fArr3 = polygonRegion.textureCoords;
            float f12 = f2 + f4;
            float f13 = f3 + f5;
            float f14 = f6 / r7.regionWidth;
            float f15 = f7 / r7.regionHeight;
            float fC = j.c(f10);
            float fK = j.k(f10);
            while (i5 < length2) {
                float f16 = ((fArr[i5] * f14) - f4) * f8;
                int i7 = i5 + 1;
                float[] fArr4 = fArr;
                float f17 = ((fArr[i7] * f15) - f5) * f9;
                fArr2[i3] = ((fC * f16) - (fK * f17)) + f12;
                fArr2[i3 + 1] = a.B(fC, f17, f16 * fK, f13);
                fArr2[i3 + 2] = f11;
                int i8 = i3 + 4;
                fArr2[i3 + 3] = fArr3[i5];
                i3 += 5;
                fArr2[i8] = fArr3[i7];
                i5 += 2;
                fArr = fArr4;
            }
            this.vertexIndex = i3;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(Texture texture, float[] fArr, int i2, int i3, short[] sArr, int i4, int i5) {
        if (this.drawing) {
            short[] sArr2 = this.triangles;
            float[] fArr2 = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + i5 > sArr2.length || this.vertexIndex + i3 > fArr2.length) {
                flush();
            }
            int i6 = this.triangleIndex;
            int i7 = this.vertexIndex;
            int i8 = i7 / 5;
            int i9 = i5 + i4;
            while (i4 < i9) {
                sArr2[i6] = (short) (sArr[i4] + i8);
                i4++;
                i6++;
            }
            this.triangleIndex = i6;
            System.arraycopy(fArr, i2, fArr2, i7, i3);
            this.vertexIndex += i3;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i6 = this.triangleIndex;
            int i7 = this.vertexIndex / 5;
            short s = (short) i7;
            sArr[i6] = s;
            sArr[i6 + 1] = (short) (i7 + 1);
            short s2 = (short) (i7 + 2);
            sArr[i6 + 2] = s2;
            sArr[i6 + 3] = s2;
            sArr[i6 + 4] = (short) (i7 + 3);
            sArr[i6 + 5] = s;
            this.triangleIndex = i6 + 6;
            float f18 = f2 + f4;
            float f19 = f3 + f5;
            float f20 = -f4;
            float f21 = -f5;
            float f22 = f6 - f4;
            float f23 = f7 - f5;
            if (f8 != 1.0f || f9 != 1.0f) {
                f20 *= f8;
                f21 *= f9;
                f22 *= f8;
                f23 *= f9;
            }
            if (f10 != 0.0f) {
                float fC = j.c(f10);
                float fK = j.k(f10);
                float f24 = fC * f20;
                f12 = f24 - (fK * f21);
                float f25 = f20 * fK;
                float f26 = (f21 * fC) + f25;
                float f27 = fK * f23;
                f11 = f24 - f27;
                float f28 = f23 * fC;
                f15 = f25 + f28;
                float f29 = (fC * f22) - f27;
                float f30 = f28 + (fK * f22);
                f14 = f30 - (f15 - f26);
                f17 = (f29 - f11) + f12;
                f22 = f29;
                f13 = f26;
                f16 = f30;
            } else {
                f11 = f20;
                f12 = f11;
                f13 = f21;
                f14 = f13;
                f15 = f23;
                f16 = f15;
                f17 = f22;
            }
            float f31 = f12 + f18;
            float f32 = f13 + f19;
            float f33 = f11 + f18;
            float f34 = f15 + f19;
            float f35 = f22 + f18;
            float f36 = f16 + f19;
            float f37 = f17 + f18;
            float f38 = f14 + f19;
            float f39 = this.invTexWidth;
            float f40 = i2 * f39;
            float f41 = this.invTexHeight;
            float f42 = (i3 + i5) * f41;
            float f43 = (i2 + i4) * f39;
            float f44 = i3 * f41;
            if (z2) {
                f40 = f43;
                f43 = f40;
            }
            if (z3) {
                f42 = f44;
                f44 = f42;
            }
            float f45 = this.colorPacked;
            int i8 = this.vertexIndex;
            fArr[i8] = f31;
            fArr[i8 + 1] = f32;
            fArr[i8 + 2] = f45;
            fArr[i8 + 3] = f40;
            fArr[i8 + 4] = f42;
            fArr[i8 + 5] = f33;
            fArr[i8 + 6] = f34;
            fArr[i8 + 7] = f45;
            fArr[i8 + 8] = f40;
            fArr[i8 + 9] = f44;
            fArr[i8 + 10] = f35;
            fArr[i8 + 11] = f36;
            fArr[i8 + 12] = f45;
            fArr[i8 + 13] = f43;
            fArr[i8 + 14] = f44;
            fArr[i8 + 15] = f37;
            fArr[i8 + 16] = f38;
            fArr[i8 + 17] = f45;
            fArr[i8 + 18] = f43;
            fArr[i8 + 19] = f42;
            this.vertexIndex = i8 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i6 = this.triangleIndex;
            int i7 = this.vertexIndex;
            int i8 = i7 / 5;
            short s = (short) i8;
            sArr[i6] = s;
            sArr[i6 + 1] = (short) (i8 + 1);
            short s2 = (short) (i8 + 2);
            sArr[i6 + 2] = s2;
            sArr[i6 + 3] = s2;
            sArr[i6 + 4] = (short) (i8 + 3);
            sArr[i6 + 5] = s;
            this.triangleIndex = i6 + 6;
            float f6 = this.invTexWidth;
            float f7 = i2 * f6;
            float f8 = this.invTexHeight;
            float f9 = (i3 + i5) * f8;
            float f10 = (i2 + i4) * f6;
            float f11 = i3 * f8;
            float f12 = f2 + f4;
            float f13 = f3 + f5;
            if (z2) {
                f7 = f10;
                f10 = f7;
            }
            if (z3) {
                f9 = f11;
                f11 = f9;
            }
            float f14 = this.colorPacked;
            fArr[i7] = f2;
            fArr[i7 + 1] = f3;
            fArr[i7 + 2] = f14;
            fArr[i7 + 3] = f7;
            fArr[i7 + 4] = f9;
            fArr[i7 + 5] = f2;
            fArr[i7 + 6] = f13;
            fArr[i7 + 7] = f14;
            fArr[i7 + 8] = f7;
            fArr[i7 + 9] = f11;
            fArr[i7 + 10] = f12;
            fArr[i7 + 11] = f13;
            fArr[i7 + 12] = f14;
            fArr[i7 + 13] = f10;
            fArr[i7 + 14] = f11;
            fArr[i7 + 15] = f12;
            fArr[i7 + 16] = f3;
            fArr[i7 + 17] = f14;
            fArr[i7 + 18] = f10;
            fArr[i7 + 19] = f9;
            this.vertexIndex = i7 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, int i2, int i3, int i4, int i5) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i6 = this.triangleIndex;
            int i7 = this.vertexIndex;
            int i8 = i7 / 5;
            short s = (short) i8;
            sArr[i6] = s;
            sArr[i6 + 1] = (short) (i8 + 1);
            short s2 = (short) (i8 + 2);
            sArr[i6 + 2] = s2;
            sArr[i6 + 3] = s2;
            sArr[i6 + 4] = (short) (i8 + 3);
            sArr[i6 + 5] = s;
            this.triangleIndex = i6 + 6;
            float f4 = this.invTexWidth;
            float f5 = i2 * f4;
            float f6 = this.invTexHeight;
            float f7 = (i3 + i5) * f6;
            float f8 = (i2 + i4) * f4;
            float f9 = i3 * f6;
            float f10 = i4 + f2;
            float f11 = i5 + f3;
            float f12 = this.colorPacked;
            fArr[i7] = f2;
            fArr[i7 + 1] = f3;
            fArr[i7 + 2] = f12;
            fArr[i7 + 3] = f5;
            fArr[i7 + 4] = f7;
            fArr[i7 + 5] = f2;
            fArr[i7 + 6] = f11;
            fArr[i7 + 7] = f12;
            fArr[i7 + 8] = f5;
            fArr[i7 + 9] = f9;
            fArr[i7 + 10] = f10;
            fArr[i7 + 11] = f11;
            fArr[i7 + 12] = f12;
            fArr[i7 + 13] = f8;
            fArr[i7 + 14] = f9;
            fArr[i7 + 15] = f10;
            fArr[i7 + 16] = f3;
            fArr[i7 + 17] = f12;
            fArr[i7 + 18] = f8;
            fArr[i7 + 19] = f7;
            this.vertexIndex = i7 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex;
            int i4 = i3 / 5;
            short s = (short) i4;
            sArr[i2] = s;
            sArr[i2 + 1] = (short) (i4 + 1);
            short s2 = (short) (i4 + 2);
            sArr[i2 + 2] = s2;
            sArr[i2 + 3] = s2;
            sArr[i2 + 4] = (short) (i4 + 3);
            sArr[i2 + 5] = s;
            this.triangleIndex = i2 + 6;
            float f10 = f2 + f4;
            float f11 = f3 + f5;
            float f12 = this.colorPacked;
            fArr[i3] = f2;
            fArr[i3 + 1] = f3;
            fArr[i3 + 2] = f12;
            fArr[i3 + 3] = f6;
            fArr[i3 + 4] = f7;
            fArr[i3 + 5] = f2;
            fArr[i3 + 6] = f11;
            fArr[i3 + 7] = f12;
            fArr[i3 + 8] = f6;
            fArr[i3 + 9] = f9;
            fArr[i3 + 10] = f10;
            fArr[i3 + 11] = f11;
            fArr[i3 + 12] = f12;
            fArr[i3 + 13] = f8;
            fArr[i3 + 14] = f9;
            fArr[i3 + 15] = f10;
            fArr[i3 + 16] = f3;
            fArr[i3 + 17] = f12;
            fArr[i3 + 18] = f8;
            fArr[i3 + 19] = f7;
            this.vertexIndex = i3 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3) {
        draw(texture, f2, f3, texture.getWidth(), texture.getHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex;
            int i4 = i3 / 5;
            short s = (short) i4;
            sArr[i2] = s;
            sArr[i2 + 1] = (short) (i4 + 1);
            short s2 = (short) (i4 + 2);
            sArr[i2 + 2] = s2;
            sArr[i2 + 3] = s2;
            sArr[i2 + 4] = (short) (i4 + 3);
            sArr[i2 + 5] = s;
            this.triangleIndex = i2 + 6;
            float f6 = f4 + f2;
            float f7 = f5 + f3;
            float f8 = this.colorPacked;
            fArr[i3] = f2;
            fArr[i3 + 1] = f3;
            fArr[i3 + 2] = f8;
            fArr[i3 + 3] = 0.0f;
            fArr[i3 + 4] = 1.0f;
            fArr[i3 + 5] = f2;
            fArr[i3 + 6] = f7;
            fArr[i3 + 7] = f8;
            fArr[i3 + 8] = 0.0f;
            fArr[i3 + 9] = 0.0f;
            fArr[i3 + 10] = f6;
            fArr[i3 + 11] = f7;
            fArr[i3 + 12] = f8;
            fArr[i3 + 13] = 1.0f;
            fArr[i3 + 14] = 0.0f;
            fArr[i3 + 15] = f6;
            fArr[i3 + 16] = f3;
            fArr[i3 + 17] = f8;
            fArr[i3 + 18] = 1.0f;
            fArr[i3 + 19] = 1.0f;
            this.vertexIndex = i3 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005b A[LOOP:0: B:16:0x0059->B:17:0x005b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008b A[SYNTHETIC] */
    @Override // com.badlogic.gdx.graphics.g2d.Batch
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void draw(Texture texture, float[] fArr, int i2, int i3) {
        int iMin;
        int i4;
        int i5;
        int i6;
        int i7;
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr2 = this.vertices;
            int i8 = (i3 / 20) * 6;
            if (texture != this.lastTexture) {
                switchTexture(texture);
                iMin = Math.min(Math.min(i3, fArr2.length - (fArr2.length % 20)), (sArr.length / 6) * 20);
                i4 = iMin / 20;
            } else if (this.triangleIndex + i8 > sArr.length || this.vertexIndex + i3 > fArr2.length) {
                flush();
                iMin = Math.min(Math.min(i3, fArr2.length - (fArr2.length % 20)), (sArr.length / 6) * 20);
                i4 = iMin / 20;
            } else {
                iMin = i3;
                i5 = this.vertexIndex;
                short s = (short) (i5 / 5);
                i6 = this.triangleIndex;
                i7 = i8 + i6;
                while (i6 < i7) {
                    sArr[i6] = s;
                    sArr[i6 + 1] = (short) (s + 1);
                    short s2 = (short) (s + 2);
                    sArr[i6 + 2] = s2;
                    sArr[i6 + 3] = s2;
                    sArr[i6 + 4] = (short) (s + 3);
                    sArr[i6 + 5] = s;
                    i6 += 6;
                    s = (short) (s + 4);
                }
                while (true) {
                    System.arraycopy(fArr, i2, fArr2, i5, iMin);
                    this.vertexIndex = i5 + iMin;
                    this.triangleIndex = i6;
                    i3 -= iMin;
                    if (i3 != 0) {
                        return;
                    }
                    i2 += iMin;
                    flush();
                    i5 = 0;
                    if (iMin > i3) {
                        iMin = Math.min(i3, (sArr.length / 6) * 20);
                        i6 = (iMin / 20) * 6;
                    }
                }
            }
            i8 = i4 * 6;
            i5 = this.vertexIndex;
            short s3 = (short) (i5 / 5);
            i6 = this.triangleIndex;
            i7 = i8 + i6;
            while (i6 < i7) {
            }
            while (true) {
                System.arraycopy(fArr, i2, fArr2, i5, iMin);
                this.vertexIndex = i5 + iMin;
                this.triangleIndex = i6;
                i3 -= iMin;
                if (i3 != 0) {
                }
            }
        } else {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3) {
        draw(textureRegion, f2, f3, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex;
            int i4 = i3 / 5;
            short s = (short) i4;
            sArr[i2] = s;
            sArr[i2 + 1] = (short) (i4 + 1);
            short s2 = (short) (i4 + 2);
            sArr[i2 + 2] = s2;
            sArr[i2 + 3] = s2;
            sArr[i2 + 4] = (short) (i4 + 3);
            sArr[i2 + 5] = s;
            this.triangleIndex = i2 + 6;
            float f6 = f4 + f2;
            float f7 = f5 + f3;
            float f8 = textureRegion.f1698u;
            float f9 = textureRegion.v2;
            float f10 = textureRegion.u2;
            float f11 = textureRegion.f1699v;
            float f12 = this.colorPacked;
            fArr[i3] = f2;
            fArr[i3 + 1] = f3;
            fArr[i3 + 2] = f12;
            fArr[i3 + 3] = f8;
            fArr[i3 + 4] = f9;
            fArr[i3 + 5] = f2;
            fArr[i3 + 6] = f7;
            fArr[i3 + 7] = f12;
            fArr[i3 + 8] = f8;
            fArr[i3 + 9] = f11;
            fArr[i3 + 10] = f6;
            fArr[i3 + 11] = f7;
            fArr[i3 + 12] = f12;
            fArr[i3 + 13] = f10;
            fArr[i3 + 14] = f11;
            fArr[i3 + 15] = f6;
            fArr[i3 + 16] = f3;
            fArr[i3 + 17] = f12;
            fArr[i3 + 18] = f10;
            fArr[i3 + 19] = f9;
            this.vertexIndex = i3 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex / 5;
            short s = (short) i3;
            sArr[i2] = s;
            sArr[i2 + 1] = (short) (i3 + 1);
            short s2 = (short) (i3 + 2);
            sArr[i2 + 2] = s2;
            sArr[i2 + 3] = s2;
            sArr[i2 + 4] = (short) (i3 + 3);
            sArr[i2 + 5] = s;
            this.triangleIndex = i2 + 6;
            float f18 = f2 + f4;
            float f19 = f3 + f5;
            float f20 = -f4;
            float f21 = -f5;
            float f22 = f6 - f4;
            float f23 = f7 - f5;
            if (f8 != 1.0f || f9 != 1.0f) {
                f20 *= f8;
                f21 *= f9;
                f22 *= f8;
                f23 *= f9;
            }
            if (f10 != 0.0f) {
                float fC = j.c(f10);
                float fK = j.k(f10);
                float f24 = fC * f20;
                f12 = f24 - (fK * f21);
                float f25 = f20 * fK;
                float f26 = (f21 * fC) + f25;
                float f27 = fK * f23;
                f11 = f24 - f27;
                float f28 = f23 * fC;
                f15 = f25 + f28;
                float f29 = (fC * f22) - f27;
                float f30 = f28 + (fK * f22);
                f14 = f30 - (f15 - f26);
                f17 = (f29 - f11) + f12;
                f22 = f29;
                f13 = f26;
                f16 = f30;
            } else {
                f11 = f20;
                f12 = f11;
                f13 = f21;
                f14 = f13;
                f15 = f23;
                f16 = f15;
                f17 = f22;
            }
            float f31 = f12 + f18;
            float f32 = f13 + f19;
            float f33 = f11 + f18;
            float f34 = f15 + f19;
            float f35 = f22 + f18;
            float f36 = f16 + f19;
            float f37 = f17 + f18;
            float f38 = f14 + f19;
            float f39 = textureRegion.f1698u;
            float f40 = textureRegion.v2;
            float f41 = textureRegion.u2;
            float f42 = textureRegion.f1699v;
            float f43 = this.colorPacked;
            int i4 = this.vertexIndex;
            fArr[i4] = f31;
            fArr[i4 + 1] = f32;
            fArr[i4 + 2] = f43;
            fArr[i4 + 3] = f39;
            fArr[i4 + 4] = f40;
            fArr[i4 + 5] = f33;
            fArr[i4 + 6] = f34;
            fArr[i4 + 7] = f43;
            fArr[i4 + 8] = f39;
            fArr[i4 + 9] = f42;
            fArr[i4 + 10] = f35;
            fArr[i4 + 11] = f36;
            fArr[i4 + 12] = f43;
            fArr[i4 + 13] = f41;
            fArr[i4 + 14] = f42;
            fArr[i4 + 15] = f37;
            fArr[i4 + 16] = f38;
            fArr[i4 + 17] = f43;
            fArr[i4 + 18] = f41;
            fArr[i4 + 19] = f40;
            this.vertexIndex = i4 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, boolean z2) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex / 5;
            short s = (short) i3;
            sArr[i2] = s;
            sArr[i2 + 1] = (short) (i3 + 1);
            short s2 = (short) (i3 + 2);
            sArr[i2 + 2] = s2;
            sArr[i2 + 3] = s2;
            sArr[i2 + 4] = (short) (i3 + 3);
            sArr[i2 + 5] = s;
            this.triangleIndex = i2 + 6;
            float f22 = f2 + f4;
            float f23 = f3 + f5;
            float f24 = -f4;
            float f25 = -f5;
            float f26 = f6 - f4;
            float f27 = f7 - f5;
            if (f8 != 1.0f || f9 != 1.0f) {
                f24 *= f8;
                f25 *= f9;
                f26 *= f8;
                f27 *= f9;
            }
            if (f10 != 0.0f) {
                float fC = j.c(f10);
                float fK = j.k(f10);
                float f28 = fC * f24;
                f12 = f28 - (fK * f25);
                float f29 = f24 * fK;
                float f30 = (f25 * fC) + f29;
                float f31 = fK * f27;
                f11 = f28 - f31;
                float f32 = f27 * fC;
                f15 = f29 + f32;
                float f33 = (fC * f26) - f31;
                float f34 = f32 + (fK * f26);
                f14 = f34 - (f15 - f30);
                f17 = (f33 - f11) + f12;
                f26 = f33;
                f13 = f30;
                f16 = f34;
            } else {
                f11 = f24;
                f12 = f11;
                f13 = f25;
                f14 = f13;
                f15 = f27;
                f16 = f15;
                f17 = f26;
            }
            float f35 = f12 + f22;
            float f36 = f13 + f23;
            float f37 = f11 + f22;
            float f38 = f15 + f23;
            float f39 = f26 + f22;
            float f40 = f16 + f23;
            float f41 = f17 + f22;
            float f42 = f14 + f23;
            if (z2) {
                f18 = textureRegion.u2;
                f19 = textureRegion.v2;
                f20 = textureRegion.f1698u;
                f21 = textureRegion.f1699v;
            } else {
                f18 = textureRegion.f1698u;
                f19 = textureRegion.f1699v;
                f20 = textureRegion.u2;
                f21 = textureRegion.v2;
            }
            float f43 = f21;
            float f44 = f19;
            float f45 = f20;
            float f46 = f18;
            float f47 = this.colorPacked;
            int i4 = this.vertexIndex;
            fArr[i4] = f35;
            fArr[i4 + 1] = f36;
            fArr[i4 + 2] = f47;
            fArr[i4 + 3] = f46;
            fArr[i4 + 4] = f44;
            fArr[i4 + 5] = f37;
            fArr[i4 + 6] = f38;
            fArr[i4 + 7] = f47;
            fArr[i4 + 8] = f45;
            fArr[i4 + 9] = f44;
            fArr[i4 + 10] = f39;
            fArr[i4 + 11] = f40;
            fArr[i4 + 12] = f47;
            fArr[i4 + 13] = f45;
            fArr[i4 + 14] = f21;
            fArr[i4 + 15] = f41;
            fArr[i4 + 16] = f42;
            fArr[i4 + 17] = f47;
            fArr[i4 + 18] = f46;
            fArr[i4 + 19] = f43;
            this.vertexIndex = i4 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, a0.a aVar) {
        if (this.drawing) {
            short[] sArr = this.triangles;
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
                flush();
            }
            int i2 = this.triangleIndex;
            int i3 = this.vertexIndex;
            int i4 = i3 / 5;
            short s = (short) i4;
            sArr[i2] = s;
            sArr[i2 + 1] = (short) (i4 + 1);
            short s2 = (short) (i4 + 2);
            sArr[i2 + 2] = s2;
            sArr[i2 + 3] = s2;
            sArr[i2 + 4] = (short) (i4 + 3);
            sArr[i2 + 5] = s;
            this.triangleIndex = i2 + 6;
            float f4 = aVar.f27c;
            float f5 = aVar.f30f;
            float f6 = aVar.f26b;
            float f7 = (f6 * f3) + f4;
            float f8 = aVar.f29e;
            float f9 = (f8 * f3) + f5;
            float f10 = aVar.f25a;
            float fB = a.B(f6, f3, f10 * f2, f4);
            float f11 = aVar.f28d;
            float fB2 = a.B(f8, f3, f11 * f2, f5);
            float f12 = textureRegion.f1698u;
            float f13 = textureRegion.v2;
            float f14 = textureRegion.u2;
            float f15 = textureRegion.f1699v;
            float f16 = this.colorPacked;
            fArr[i3] = f4;
            fArr[i3 + 1] = f5;
            fArr[i3 + 2] = f16;
            fArr[i3 + 3] = f12;
            fArr[i3 + 4] = f13;
            fArr[i3 + 5] = f7;
            fArr[i3 + 6] = f9;
            fArr[i3 + 7] = f16;
            fArr[i3 + 8] = f12;
            fArr[i3 + 9] = f15;
            fArr[i3 + 10] = fB;
            fArr[i3 + 11] = fB2;
            fArr[i3 + 12] = f16;
            fArr[i3 + 13] = f14;
            fArr[i3 + 14] = f15;
            fArr[i3 + 15] = (f10 * f2) + f4;
            fArr[i3 + 16] = (f11 * f2) + f5;
            fArr[i3 + 17] = f16;
            fArr[i3 + 18] = f14;
            fArr[i3 + 19] = f13;
            this.vertexIndex = i3 + 20;
            return;
        }
        throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
    }
}
