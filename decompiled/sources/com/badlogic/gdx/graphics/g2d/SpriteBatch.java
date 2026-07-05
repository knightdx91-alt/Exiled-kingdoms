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
import com.google.android.gms.auth.api.credentials.CredentialsApi;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SpriteBatch implements Batch {

    @Deprecated
    public static Mesh.VertexDataType defaultVertexDataType = Mesh.VertexDataType.VertexArray;
    private int blendDstFunc;
    private int blendDstFuncAlpha;
    private int blendSrcFunc;
    private int blendSrcFuncAlpha;
    private boolean blendingDisabled;
    private final Color color;
    float colorPacked;
    private final Matrix4 combinedMatrix;
    private ShaderProgram customShader;
    boolean drawing;
    int idx;
    float invTexHeight;
    float invTexWidth;
    Texture lastTexture;
    public int maxSpritesInBatch;
    private Mesh mesh;
    private boolean ownsShader;
    private final Matrix4 projectionMatrix;
    public int renderCalls;
    private final ShaderProgram shader;
    public int totalRenderCalls;
    private final Matrix4 transformMatrix;
    final float[] vertices;

    public SpriteBatch() {
        this(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, null);
    }

    public static ShaderProgram createDefaultShader() {
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP \n#endif\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if (shaderProgram.isCompiled()) {
            return shaderProgram;
        }
        throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void begin() {
        if (this.drawing) {
            throw new IllegalStateException("SpriteBatch.end must be called before begin.");
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
        if (this.blendingDisabled) {
            return;
        }
        flush();
        this.blendingDisabled = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch, com.badlogic.gdx.utils.i
    public void dispose() {
        ShaderProgram shaderProgram;
        this.mesh.dispose();
        if (!this.ownsShader || (shaderProgram = this.shader) == null) {
            return;
        }
        shaderProgram.dispose();
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
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
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
        int i6 = this.idx;
        fArr[i6] = f31;
        fArr[i6 + 1] = f32;
        fArr[i6 + 2] = f45;
        fArr[i6 + 3] = f40;
        fArr[i6 + 4] = f42;
        fArr[i6 + 5] = f33;
        fArr[i6 + 6] = f34;
        fArr[i6 + 7] = f45;
        fArr[i6 + 8] = f40;
        fArr[i6 + 9] = f44;
        fArr[i6 + 10] = f35;
        fArr[i6 + 11] = f36;
        fArr[i6 + 12] = f45;
        fArr[i6 + 13] = f43;
        fArr[i6 + 14] = f44;
        fArr[i6 + 15] = f37;
        fArr[i6 + 16] = f38;
        fArr[i6 + 17] = f45;
        fArr[i6 + 18] = f43;
        fArr[i6 + 19] = f42;
        this.idx = i6 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void enableBlending() {
        if (this.blendingDisabled) {
            flush();
            this.blendingDisabled = false;
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void end() {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before end.");
        }
        if (this.idx > 0) {
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
        int i2 = this.idx;
        if (i2 == 0) {
            return;
        }
        this.renderCalls++;
        this.totalRenderCalls++;
        int i3 = i2 / 20;
        if (i3 > this.maxSpritesInBatch) {
            this.maxSpritesInBatch = i3;
        }
        int i4 = i3 * 6;
        this.lastTexture.bind();
        Mesh mesh = this.mesh;
        mesh.setVertices(this.vertices, 0, this.idx);
        mesh.getIndicesBuffer().position(0);
        mesh.getIndicesBuffer().limit(i4);
        if (this.blendingDisabled) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        } else {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            int i5 = this.blendSrcFunc;
            if (i5 != -1) {
                Gdx.gl.glBlendFuncSeparate(i5, this.blendDstFunc, this.blendSrcFuncAlpha, this.blendDstFuncAlpha);
            }
        }
        ShaderProgram shaderProgram = this.customShader;
        if (shaderProgram == null) {
            shaderProgram = this.shader;
        }
        mesh.render(shaderProgram, 4, 0, i4);
        this.idx = 0;
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

    protected void switchTexture(Texture texture) {
        flush();
        this.lastTexture = texture;
        this.invTexWidth = 1.0f / texture.getWidth();
        this.invTexHeight = 1.0f / texture.getHeight();
    }

    public SpriteBatch(int i2) {
        this(i2, null);
    }

    public SpriteBatch(int i2, ShaderProgram shaderProgram) {
        this.idx = 0;
        this.lastTexture = null;
        this.invTexWidth = 0.0f;
        this.invTexHeight = 0.0f;
        this.drawing = false;
        this.transformMatrix = new Matrix4();
        Matrix4 matrix4 = new Matrix4();
        this.projectionMatrix = matrix4;
        this.combinedMatrix = new Matrix4();
        this.blendingDisabled = false;
        this.blendSrcFunc = GL20.GL_SRC_ALPHA;
        this.blendDstFunc = GL20.GL_ONE_MINUS_SRC_ALPHA;
        this.blendSrcFuncAlpha = GL20.GL_SRC_ALPHA;
        this.blendDstFuncAlpha = GL20.GL_ONE_MINUS_SRC_ALPHA;
        this.customShader = null;
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.maxSpritesInBatch = 0;
        if (i2 <= 8191) {
            int i3 = i2 * 6;
            this.mesh = new Mesh(Gdx.gl30 != null ? Mesh.VertexDataType.VertexBufferObjectWithVAO : defaultVertexDataType, false, i2 * 4, i3, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
            matrix4.s(0.0f, 0.0f + Gdx.graphics.getWidth(), 0.0f, 0.0f + Gdx.graphics.getHeight(), 0.0f, 1.0f);
            this.vertices = new float[i2 * 20];
            short[] sArr = new short[i3];
            int i4 = 0;
            short s = 0;
            while (i4 < i3) {
                sArr[i4] = s;
                sArr[i4 + 1] = (short) (s + 1);
                short s2 = (short) (s + 2);
                sArr[i4 + 2] = s2;
                sArr[i4 + 3] = s2;
                sArr[i4 + 4] = (short) (s + 3);
                sArr[i4 + 5] = s;
                i4 += 6;
                s = (short) (s + 4);
            }
            this.mesh.setIndices(sArr);
            if (shaderProgram == null) {
                this.shader = createDefaultShader();
                this.ownsShader = true;
                return;
            } else {
                this.shader = shaderProgram;
                return;
            }
        }
        throw new IllegalArgumentException(a.h(i2, "Can't have more than 8191 sprites per batch: "));
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        this.colorPacked = this.color.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        if (this.drawing) {
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
            float f6 = this.invTexWidth;
            float f7 = i2 * f6;
            float f8 = this.invTexHeight;
            float f9 = (i5 + i3) * f8;
            float f10 = (i2 + i4) * f6;
            float f11 = i3 * f8;
            float f12 = f4 + f2;
            float f13 = f5 + f3;
            if (z2) {
                f10 = f7;
                f7 = f10;
            }
            if (z3) {
                f9 = f11;
                f11 = f9;
            }
            float f14 = this.colorPacked;
            int i6 = this.idx;
            fArr[i6] = f2;
            fArr[i6 + 1] = f3;
            fArr[i6 + 2] = f14;
            fArr[i6 + 3] = f7;
            fArr[i6 + 4] = f9;
            fArr[i6 + 5] = f2;
            fArr[i6 + 6] = f13;
            fArr[i6 + 7] = f14;
            fArr[i6 + 8] = f7;
            fArr[i6 + 9] = f11;
            fArr[i6 + 10] = f12;
            fArr[i6 + 11] = f13;
            fArr[i6 + 12] = f14;
            fArr[i6 + 13] = f10;
            fArr[i6 + 14] = f11;
            fArr[i6 + 15] = f12;
            fArr[i6 + 16] = f3;
            fArr[i6 + 17] = f14;
            fArr[i6 + 18] = f10;
            fArr[i6 + 19] = f9;
            this.idx = i6 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, int i2, int i3, int i4, int i5) {
        if (this.drawing) {
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
            float f4 = this.invTexWidth;
            float f5 = i2 * f4;
            float f6 = this.invTexHeight;
            float f7 = (i3 + i5) * f6;
            float f8 = (i2 + i4) * f4;
            float f9 = i3 * f6;
            float f10 = i4 + f2;
            float f11 = i5 + f3;
            float f12 = this.colorPacked;
            int i6 = this.idx;
            fArr[i6] = f2;
            fArr[i6 + 1] = f3;
            fArr[i6 + 2] = f12;
            fArr[i6 + 3] = f5;
            fArr[i6 + 4] = f7;
            fArr[i6 + 5] = f2;
            fArr[i6 + 6] = f11;
            fArr[i6 + 7] = f12;
            fArr[i6 + 8] = f5;
            fArr[i6 + 9] = f9;
            fArr[i6 + 10] = f10;
            fArr[i6 + 11] = f11;
            fArr[i6 + 12] = f12;
            fArr[i6 + 13] = f8;
            fArr[i6 + 14] = f9;
            fArr[i6 + 15] = f10;
            fArr[i6 + 16] = f3;
            fArr[i6 + 17] = f12;
            fArr[i6 + 18] = f8;
            fArr[i6 + 19] = f7;
            this.idx = i6 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (this.drawing) {
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
            float f10 = f4 + f2;
            float f11 = f5 + f3;
            float f12 = this.colorPacked;
            int i2 = this.idx;
            fArr[i2] = f2;
            fArr[i2 + 1] = f3;
            fArr[i2 + 2] = f12;
            fArr[i2 + 3] = f6;
            fArr[i2 + 4] = f7;
            fArr[i2 + 5] = f2;
            fArr[i2 + 6] = f11;
            fArr[i2 + 7] = f12;
            fArr[i2 + 8] = f6;
            fArr[i2 + 9] = f9;
            fArr[i2 + 10] = f10;
            fArr[i2 + 11] = f11;
            fArr[i2 + 12] = f12;
            fArr[i2 + 13] = f8;
            fArr[i2 + 14] = f9;
            fArr[i2 + 15] = f10;
            fArr[i2 + 16] = f3;
            fArr[i2 + 17] = f12;
            fArr[i2 + 18] = f8;
            fArr[i2 + 19] = f7;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3) {
        draw(texture, f2, f3, texture.getWidth(), texture.getHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5) {
        if (this.drawing) {
            float[] fArr = this.vertices;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
            float f6 = f4 + f2;
            float f7 = f5 + f3;
            float f8 = this.colorPacked;
            int i2 = this.idx;
            fArr[i2] = f2;
            fArr[i2 + 1] = f3;
            fArr[i2 + 2] = f8;
            fArr[i2 + 3] = 0.0f;
            fArr[i2 + 4] = 1.0f;
            fArr[i2 + 5] = f2;
            fArr[i2 + 6] = f7;
            fArr[i2 + 7] = f8;
            fArr[i2 + 8] = 0.0f;
            fArr[i2 + 9] = 0.0f;
            fArr[i2 + 10] = f6;
            fArr[i2 + 11] = f7;
            fArr[i2 + 12] = f8;
            fArr[i2 + 13] = 1.0f;
            fArr[i2 + 14] = 0.0f;
            fArr[i2 + 15] = f6;
            fArr[i2 + 16] = f3;
            fArr[i2 + 17] = f8;
            fArr[i2 + 18] = 1.0f;
            fArr[i2 + 19] = 1.0f;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c A[LOOP:0: B:12:0x0029->B:14:0x002c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040 A[SYNTHETIC] */
    @Override // com.badlogic.gdx.graphics.g2d.Batch
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void draw(Texture texture, float[] fArr, int i2, int i3) {
        int i4;
        int iMin;
        if (this.drawing) {
            int length = this.vertices.length;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else {
                i4 = length - this.idx;
                if (i4 == 0) {
                    flush();
                }
                iMin = Math.min(i4, i3);
                System.arraycopy(fArr, i2, this.vertices, this.idx, iMin);
                this.idx += iMin;
                while (true) {
                    i3 -= iMin;
                    if (i3 > 0) {
                        return;
                    }
                    i2 += iMin;
                    flush();
                    iMin = Math.min(length, i3);
                    System.arraycopy(fArr, i2, this.vertices, 0, iMin);
                    this.idx += iMin;
                }
            }
            i4 = length;
            iMin = Math.min(i4, i3);
            System.arraycopy(fArr, i2, this.vertices, this.idx, iMin);
            this.idx += iMin;
            while (true) {
                i3 -= iMin;
                if (i3 > 0) {
                }
                i2 += iMin;
                flush();
                iMin = Math.min(length, i3);
                System.arraycopy(fArr, i2, this.vertices, 0, iMin);
                this.idx += iMin;
            }
        } else {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3) {
        draw(textureRegion, f2, f3, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5) {
        if (this.drawing) {
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
            float f6 = f4 + f2;
            float f7 = f5 + f3;
            float f8 = textureRegion.f1698u;
            float f9 = textureRegion.v2;
            float f10 = textureRegion.u2;
            float f11 = textureRegion.f1699v;
            float f12 = this.colorPacked;
            int i2 = this.idx;
            fArr[i2] = f2;
            fArr[i2 + 1] = f3;
            fArr[i2 + 2] = f12;
            fArr[i2 + 3] = f8;
            fArr[i2 + 4] = f9;
            fArr[i2 + 5] = f2;
            fArr[i2 + 6] = f7;
            fArr[i2 + 7] = f12;
            fArr[i2 + 8] = f8;
            fArr[i2 + 9] = f11;
            fArr[i2 + 10] = f6;
            fArr[i2 + 11] = f7;
            fArr[i2 + 12] = f12;
            fArr[i2 + 13] = f10;
            fArr[i2 + 14] = f11;
            fArr[i2 + 15] = f6;
            fArr[i2 + 16] = f3;
            fArr[i2 + 17] = f12;
            fArr[i2 + 18] = f10;
            fArr[i2 + 19] = f9;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
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
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
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
            int i2 = this.idx;
            fArr[i2] = f31;
            fArr[i2 + 1] = f32;
            fArr[i2 + 2] = f43;
            fArr[i2 + 3] = f39;
            fArr[i2 + 4] = f40;
            fArr[i2 + 5] = f33;
            fArr[i2 + 6] = f34;
            fArr[i2 + 7] = f43;
            fArr[i2 + 8] = f39;
            fArr[i2 + 9] = f42;
            fArr[i2 + 10] = f35;
            fArr[i2 + 11] = f36;
            fArr[i2 + 12] = f43;
            fArr[i2 + 13] = f41;
            fArr[i2 + 14] = f42;
            fArr[i2 + 15] = f37;
            fArr[i2 + 16] = f38;
            fArr[i2 + 17] = f43;
            fArr[i2 + 18] = f41;
            fArr[i2 + 19] = f40;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
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
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
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
            int i2 = this.idx;
            fArr[i2] = f35;
            fArr[i2 + 1] = f36;
            fArr[i2 + 2] = f47;
            fArr[i2 + 3] = f46;
            fArr[i2 + 4] = f44;
            fArr[i2 + 5] = f37;
            fArr[i2 + 6] = f38;
            fArr[i2 + 7] = f47;
            fArr[i2 + 8] = f45;
            fArr[i2 + 9] = f44;
            fArr[i2 + 10] = f39;
            fArr[i2 + 11] = f40;
            fArr[i2 + 12] = f47;
            fArr[i2 + 13] = f45;
            fArr[i2 + 14] = f21;
            fArr[i2 + 15] = f41;
            fArr[i2 + 16] = f42;
            fArr[i2 + 17] = f47;
            fArr[i2 + 18] = f46;
            fArr[i2 + 19] = f43;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, a0.a aVar) {
        if (this.drawing) {
            float[] fArr = this.vertices;
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == fArr.length) {
                flush();
            }
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
            float f12 = (f10 * f2) + f4;
            float f13 = (f11 * f2) + f5;
            float f14 = textureRegion.f1698u;
            float f15 = textureRegion.v2;
            float f16 = textureRegion.u2;
            float f17 = textureRegion.f1699v;
            float f18 = this.colorPacked;
            int i2 = this.idx;
            fArr[i2] = f4;
            fArr[i2 + 1] = f5;
            fArr[i2 + 2] = f18;
            fArr[i2 + 3] = f14;
            fArr[i2 + 4] = f15;
            fArr[i2 + 5] = f7;
            fArr[i2 + 6] = f9;
            fArr[i2 + 7] = f18;
            fArr[i2 + 8] = f14;
            fArr[i2 + 9] = f17;
            fArr[i2 + 10] = fB;
            fArr[i2 + 11] = fB2;
            fArr[i2 + 12] = f18;
            fArr[i2 + 13] = f16;
            fArr[i2 + 14] = f17;
            fArr[i2 + 15] = f12;
            fArr[i2 + 16] = f13;
            fArr[i2 + 17] = f18;
            fArr[i2 + 18] = f16;
            fArr[i2 + 19] = f15;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
    }
}
