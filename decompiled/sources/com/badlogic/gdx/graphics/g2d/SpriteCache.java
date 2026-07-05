package com.badlogic.gdx.graphics.g2d;

import a0.j;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.o;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SpriteCache implements i {
    private static final float[] tempVertices = new float[30];
    private a<Cache> caches;
    private final Color color;
    private float colorPacked;
    private final Matrix4 combinedMatrix;
    private final o counts;
    private Cache currentCache;
    private ShaderProgram customShader;
    private boolean drawing;
    private final Mesh mesh;
    private final Matrix4 projectionMatrix;
    public int renderCalls;
    private final ShaderProgram shader;
    private final a<Texture> textures;
    public int totalRenderCalls;
    private final Matrix4 transformMatrix;

    private static class Cache {
        int[] counts;
        final int id;
        int maxCount;
        final int offset;
        int textureCount;
        Texture[] textures;

        public Cache(int i2, int i3) {
            this.id = i2;
            this.offset = i3;
        }
    }

    public SpriteCache() {
        this(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, false);
    }

    static ShaderProgram createDefaultShader() {
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projectionViewMatrix;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projectionViewMatrix * a_position;\n}\n", "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if (shaderProgram.isCompiled()) {
            return shaderProgram;
        }
        throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());
    }

    public void add(Texture texture, float[] fArr, int i2, int i3) {
        if (this.currentCache == null) {
            throw new IllegalStateException("beginCache must be called before add.");
        }
        int i4 = (i3 / ((this.mesh.getNumIndices() > 0 ? 4 : 6) * 5)) * 6;
        a<Texture> aVar = this.textures;
        int i5 = aVar.f1750b - 1;
        if (i5 < 0 || aVar.get(i5) != texture) {
            this.textures.a(texture);
            this.counts.a(i4);
        } else {
            o oVar = this.counts;
            if (i5 >= oVar.f1850b) {
                StringBuilder sbR = a.a.r(i5, "index can't be >= size: ", " >= ");
                sbR.append(oVar.f1850b);
                throw new IndexOutOfBoundsException(sbR.toString());
            }
            int[] iArr = oVar.f1849a;
            iArr[i5] = iArr[i5] + i4;
        }
        this.mesh.getVerticesBuffer().put(fArr, i2, i3);
    }

    public void begin() {
        if (this.drawing) {
            throw new IllegalStateException("end must be called before begin.");
        }
        if (this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin");
        }
        this.renderCalls = 0;
        Matrix4 matrix4 = this.combinedMatrix;
        matrix4.o(this.projectionMatrix);
        matrix4.g(this.transformMatrix);
        Gdx.gl20.glDepthMask(false);
        ShaderProgram shaderProgram = this.customShader;
        if (shaderProgram != null) {
            shaderProgram.bind();
            this.customShader.setUniformMatrix("u_proj", this.projectionMatrix);
            this.customShader.setUniformMatrix("u_trans", this.transformMatrix);
            this.customShader.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.customShader.setUniformi("u_texture", 0);
            this.mesh.bind(this.customShader);
        } else {
            this.shader.bind();
            this.shader.setUniformMatrix("u_projectionViewMatrix", this.combinedMatrix);
            this.shader.setUniformi("u_texture", 0);
            this.mesh.bind(this.shader);
        }
        this.drawing = true;
    }

    public void beginCache() {
        if (this.drawing) {
            throw new IllegalStateException("end must be called before beginCache");
        }
        if (this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin.");
        }
        this.mesh.getNumIndices();
        Cache cache = new Cache(this.caches.f1750b, this.mesh.getVerticesBuffer().limit());
        this.currentCache = cache;
        this.caches.a(cache);
        this.mesh.getVerticesBuffer().compact();
    }

    public void clear() {
        this.caches.clear();
        this.mesh.getVerticesBuffer().clear().flip();
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        this.mesh.dispose();
        ShaderProgram shaderProgram = this.shader;
        if (shaderProgram != null) {
            shaderProgram.dispose();
        }
    }

    public void draw(int i2) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteCache.begin must be called before draw.");
        }
        Cache cache = this.caches.get(i2);
        int i3 = (cache.offset / ((this.mesh.getNumIndices() > 0 ? 4 : 6) * 5)) * 6;
        Texture[] textureArr = cache.textures;
        int[] iArr = cache.counts;
        int i4 = cache.textureCount;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = iArr[i5];
            textureArr[i5].bind();
            ShaderProgram shaderProgram = this.customShader;
            if (shaderProgram != null) {
                this.mesh.render(shaderProgram, 4, i3, i6);
            } else {
                this.mesh.render(this.shader, 4, i3, i6);
            }
            i3 += i6;
        }
        this.renderCalls += i4;
        this.totalRenderCalls += i4;
    }

    public void end() {
        if (!this.drawing) {
            throw new IllegalStateException("begin must be called before end.");
        }
        this.drawing = false;
        Gdx.gl20.glDepthMask(true);
        ShaderProgram shaderProgram = this.customShader;
        if (shaderProgram != null) {
            this.mesh.unbind(shaderProgram);
        } else {
            this.mesh.unbind(this.shader);
        }
    }

    public int endCache() {
        Cache cache = this.currentCache;
        if (cache == null) {
            throw new IllegalStateException("beginCache must be called before endCache.");
        }
        int iPosition = this.mesh.getVerticesBuffer().position() - cache.offset;
        Texture[] textureArr = cache.textures;
        if (textureArr == null) {
            cache.maxCount = iPosition;
            a<Texture> aVar = this.textures;
            cache.textureCount = aVar.f1750b;
            cache.textures = (Texture[]) aVar.u(Texture.class);
            cache.counts = new int[cache.textureCount];
            int i2 = this.counts.f1850b;
            for (int i3 = 0; i3 < i2; i3++) {
                cache.counts[i3] = this.counts.d(i3);
            }
            this.mesh.getVerticesBuffer().flip();
        } else {
            if (iPosition > cache.maxCount) {
                throw new m(a.a.p(a.a.r(iPosition, "If a cache is not the last created, it cannot be redefined with more entries than when it was first created: ", " ("), cache.maxCount, " max)"));
            }
            int i4 = this.textures.f1750b;
            cache.textureCount = i4;
            if (textureArr.length < i4) {
                cache.textures = new Texture[i4];
            }
            for (int i5 = 0; i5 < i4; i5++) {
                cache.textures[i5] = this.textures.get(i5);
            }
            int length = cache.counts.length;
            int i6 = cache.textureCount;
            if (length < i6) {
                cache.counts = new int[i6];
            }
            for (int i7 = 0; i7 < i6; i7++) {
                cache.counts[i7] = this.counts.d(i7);
            }
            FloatBuffer verticesBuffer = this.mesh.getVerticesBuffer();
            verticesBuffer.position(0);
            Cache cache2 = this.caches.get(r2.f1750b - 1);
            verticesBuffer.limit(cache2.offset + cache2.maxCount);
        }
        this.currentCache = null;
        this.textures.clear();
        this.counts.f1850b = 0;
        return cache.id;
    }

    public Color getColor() {
        return this.color;
    }

    public ShaderProgram getCustomShader() {
        return this.customShader;
    }

    public float getPackedColor() {
        return this.colorPacked;
    }

    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    public boolean isDrawing() {
        return this.drawing;
    }

    public void setColor(Color color) {
        this.color.set(color);
        this.colorPacked = color.toFloatBits();
    }

    public void setPackedColor(float f2) {
        Color.abgr8888ToColor(this.color, f2);
        this.colorPacked = f2;
    }

    public void setProjectionMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            throw new IllegalStateException("Can't set the matrix within begin/end.");
        }
        this.projectionMatrix.o(matrix4);
    }

    public void setShader(ShaderProgram shaderProgram) {
        this.customShader = shaderProgram;
    }

    public void setTransformMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            throw new IllegalStateException("Can't set the matrix within begin/end.");
        }
        this.transformMatrix.o(matrix4);
    }

    public SpriteCache(int i2, boolean z2) {
        this(i2, createDefaultShader(), z2);
    }

    public SpriteCache(int i2, ShaderProgram shaderProgram, boolean z2) {
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.caches = new a<>();
        this.combinedMatrix = new Matrix4();
        this.textures = new a<>(true, 8);
        this.counts = new o(8);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.customShader = null;
        int i3 = 0;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.shader = shaderProgram;
        if (z2 && i2 > 8191) {
            throw new IllegalArgumentException(a.a.h(i2, "Can't have more than 8191 sprites per batch: "));
        }
        Mesh mesh = new Mesh(true, (z2 ? 4 : 6) * i2, z2 ? i2 * 6 : 0, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
        this.mesh = mesh;
        mesh.setAutoBind(false);
        if (z2) {
            int i4 = i2 * 6;
            short[] sArr = new short[i4];
            short s = 0;
            while (i3 < i4) {
                sArr[i3] = s;
                sArr[i3 + 1] = (short) (s + 1);
                short s2 = (short) (s + 2);
                sArr[i3 + 2] = s2;
                sArr[i3 + 3] = s2;
                sArr[i3 + 4] = (short) (s + 3);
                sArr[i3 + 5] = s;
                i3 += 6;
                s = (short) (s + 4);
            }
            this.mesh.setIndices(sArr);
        }
        this.projectionMatrix.s(0.0f, 0.0f + Gdx.graphics.getWidth(), 0.0f, 0.0f + Gdx.graphics.getHeight(), 0.0f, 1.0f);
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        this.colorPacked = this.color.toFloatBits();
    }

    public void beginCache(int i2) {
        if (!this.drawing) {
            if (this.currentCache == null) {
                a<Cache> aVar = this.caches;
                if (i2 == aVar.f1750b - 1) {
                    this.mesh.getVerticesBuffer().limit(aVar.o(i2).offset);
                    beginCache();
                    return;
                } else {
                    this.currentCache = aVar.get(i2);
                    this.mesh.getVerticesBuffer().position(this.currentCache.offset);
                    return;
                }
            }
            throw new IllegalStateException("endCache must be called before begin.");
        }
        throw new IllegalStateException("end must be called before beginCache");
    }

    public void draw(int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.drawing) {
            Cache cache = this.caches.get(i2);
            int i7 = (i3 * 6) + cache.offset;
            int i8 = i4 * 6;
            Texture[] textureArr = cache.textures;
            int[] iArr = cache.counts;
            int i9 = cache.textureCount;
            int i10 = 0;
            while (i10 < i9) {
                textureArr[i10].bind();
                int i11 = iArr[i10];
                if (i11 > i8) {
                    i5 = i8;
                    i6 = i9;
                } else {
                    int i12 = i10;
                    i5 = i8 - i11;
                    i8 = i11;
                    i6 = i12;
                }
                ShaderProgram shaderProgram = this.customShader;
                if (shaderProgram != null) {
                    this.mesh.render(shaderProgram, 4, i7, i8);
                } else {
                    this.mesh.render(this.shader, 4, i7, i8);
                }
                i7 += i8;
                int i13 = i5;
                i10 = i6 + 1;
                i8 = i13;
            }
            this.renderCalls += cache.textureCount;
            this.totalRenderCalls += i9;
            return;
        }
        throw new IllegalStateException("SpriteCache.begin must be called before draw.");
    }

    public void add(Texture texture, float f2, float f3) {
        float width = f2 + texture.getWidth();
        float height = f3 + texture.getHeight();
        float[] fArr = tempVertices;
        fArr[0] = f2;
        fArr[1] = f3;
        float f4 = this.colorPacked;
        fArr[2] = f4;
        fArr[3] = 0.0f;
        fArr[4] = 1.0f;
        fArr[5] = f2;
        fArr[6] = height;
        fArr[7] = f4;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = width;
        fArr[11] = height;
        fArr[12] = f4;
        fArr[13] = 1.0f;
        fArr[14] = 0.0f;
        if (this.mesh.getNumIndices() > 0) {
            fArr[15] = width;
            fArr[16] = f3;
            fArr[17] = this.colorPacked;
            fArr[18] = 1.0f;
            fArr[19] = 1.0f;
            add(texture, fArr, 0, 20);
            return;
        }
        fArr[15] = width;
        fArr[16] = height;
        float f5 = this.colorPacked;
        fArr[17] = f5;
        fArr[18] = 1.0f;
        fArr[19] = 0.0f;
        fArr[20] = width;
        fArr[21] = f3;
        fArr[22] = f5;
        fArr[23] = 1.0f;
        fArr[24] = 1.0f;
        fArr[25] = f2;
        fArr[26] = f3;
        fArr[27] = f5;
        fArr[28] = 0.0f;
        fArr[29] = 1.0f;
        add(texture, fArr, 0, 30);
    }

    public void add(Texture texture, float f2, float f3, int i2, int i3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = i2 + f2;
        float f10 = f3 + i3;
        float[] fArr = tempVertices;
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[2] = f8;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f2;
        fArr[6] = f10;
        fArr[7] = f8;
        fArr[8] = f4;
        fArr[9] = f7;
        fArr[10] = f9;
        fArr[11] = f10;
        fArr[12] = f8;
        fArr[13] = f6;
        fArr[14] = f7;
        if (this.mesh.getNumIndices() > 0) {
            fArr[15] = f9;
            fArr[16] = f3;
            fArr[17] = f8;
            fArr[18] = f6;
            fArr[19] = f5;
            add(texture, fArr, 0, 20);
            return;
        }
        fArr[15] = f9;
        fArr[16] = f10;
        fArr[17] = f8;
        fArr[18] = f6;
        fArr[19] = f7;
        fArr[20] = f9;
        fArr[21] = f3;
        fArr[22] = f8;
        fArr[23] = f6;
        fArr[24] = f5;
        fArr[25] = f2;
        fArr[26] = f3;
        fArr[27] = f8;
        fArr[28] = f4;
        fArr[29] = f5;
        add(texture, fArr, 0, 30);
    }

    public void add(Texture texture, float f2, float f3, int i2, int i3, int i4, int i5) {
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        float f4 = i2 * width;
        float f5 = (i3 + i5) * height;
        float f6 = (i2 + i4) * width;
        float f7 = i3 * height;
        float f8 = f2 + i4;
        float f9 = f3 + i5;
        float[] fArr = tempVertices;
        fArr[0] = f2;
        fArr[1] = f3;
        float f10 = this.colorPacked;
        fArr[2] = f10;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f2;
        fArr[6] = f9;
        fArr[7] = f10;
        fArr[8] = f4;
        fArr[9] = f7;
        fArr[10] = f8;
        fArr[11] = f9;
        fArr[12] = f10;
        fArr[13] = f6;
        fArr[14] = f7;
        if (this.mesh.getNumIndices() > 0) {
            fArr[15] = f8;
            fArr[16] = f3;
            fArr[17] = this.colorPacked;
            fArr[18] = f6;
            fArr[19] = f5;
            add(texture, fArr, 0, 20);
            return;
        }
        fArr[15] = f8;
        fArr[16] = f9;
        float f11 = this.colorPacked;
        fArr[17] = f11;
        fArr[18] = f6;
        fArr[19] = f7;
        fArr[20] = f8;
        fArr[21] = f3;
        fArr[22] = f11;
        fArr[23] = f6;
        fArr[24] = f5;
        fArr[25] = f2;
        fArr[26] = f3;
        fArr[27] = f11;
        fArr[28] = f4;
        fArr[29] = f5;
        add(texture, fArr, 0, 30);
    }

    public void add(Texture texture, float f2, float f3, float f4, float f5, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        float f6 = i2 * width;
        float f7 = (i3 + i5) * height;
        float f8 = (i2 + i4) * width;
        float f9 = i3 * height;
        float f10 = f2 + f4;
        float f11 = f3 + f5;
        if (!z2) {
            f6 = f8;
            f8 = f6;
        }
        if (z3) {
            f7 = f9;
            f9 = f7;
        }
        float[] fArr = tempVertices;
        fArr[0] = f2;
        fArr[1] = f3;
        float f12 = this.colorPacked;
        fArr[2] = f12;
        fArr[3] = f8;
        fArr[4] = f7;
        fArr[5] = f2;
        fArr[6] = f11;
        fArr[7] = f12;
        fArr[8] = f8;
        fArr[9] = f9;
        fArr[10] = f10;
        fArr[11] = f11;
        fArr[12] = f12;
        fArr[13] = f6;
        fArr[14] = f9;
        if (this.mesh.getNumIndices() > 0) {
            fArr[15] = f10;
            fArr[16] = f3;
            fArr[17] = this.colorPacked;
            fArr[18] = f6;
            fArr[19] = f7;
            add(texture, fArr, 0, 20);
            return;
        }
        fArr[15] = f10;
        fArr[16] = f11;
        float f13 = this.colorPacked;
        fArr[17] = f13;
        fArr[18] = f6;
        fArr[19] = f9;
        fArr[20] = f10;
        fArr[21] = f3;
        fArr[22] = f13;
        fArr[23] = f6;
        fArr[24] = f7;
        fArr[25] = f2;
        fArr[26] = f3;
        fArr[27] = f13;
        fArr[28] = f8;
        fArr[29] = f7;
        add(texture, fArr, 0, 30);
    }

    public void add(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
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
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        float f39 = i2 * width;
        float f40 = (i3 + i5) * height;
        float f41 = (i2 + i4) * width;
        float f42 = i3 * height;
        if (z2) {
            f39 = f41;
            f41 = f39;
        }
        if (z3) {
            f40 = f42;
            f42 = f40;
        }
        float[] fArr = tempVertices;
        fArr[0] = f31;
        fArr[1] = f32;
        float f43 = this.colorPacked;
        fArr[2] = f43;
        fArr[3] = f39;
        fArr[4] = f40;
        fArr[5] = f33;
        fArr[6] = f34;
        fArr[7] = f43;
        fArr[8] = f39;
        fArr[9] = f42;
        fArr[10] = f35;
        fArr[11] = f36;
        fArr[12] = f43;
        fArr[13] = f41;
        fArr[14] = f42;
        if (this.mesh.getNumIndices() > 0) {
            fArr[15] = f37;
            fArr[16] = f38;
            fArr[17] = this.colorPacked;
            fArr[18] = f41;
            fArr[19] = f40;
            add(texture, fArr, 0, 20);
            return;
        }
        fArr[15] = f35;
        fArr[16] = f36;
        float f44 = this.colorPacked;
        fArr[17] = f44;
        fArr[18] = f41;
        fArr[19] = f42;
        fArr[20] = f37;
        fArr[21] = f38;
        fArr[22] = f44;
        fArr[23] = f41;
        fArr[24] = f40;
        fArr[25] = f31;
        fArr[26] = f32;
        fArr[27] = f44;
        fArr[28] = f39;
        fArr[29] = f40;
        add(texture, fArr, 0, 30);
    }

    public void add(TextureRegion textureRegion, float f2, float f3) {
        add(textureRegion, f2, f3, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public void add(TextureRegion textureRegion, float f2, float f3, float f4, float f5) {
        float f6 = f2 + f4;
        float f7 = f3 + f5;
        float f8 = textureRegion.f1698u;
        float f9 = textureRegion.v2;
        float f10 = textureRegion.u2;
        float f11 = textureRegion.f1699v;
        float[] fArr = tempVertices;
        fArr[0] = f2;
        fArr[1] = f3;
        float f12 = this.colorPacked;
        fArr[2] = f12;
        fArr[3] = f8;
        fArr[4] = f9;
        fArr[5] = f2;
        fArr[6] = f7;
        fArr[7] = f12;
        fArr[8] = f8;
        fArr[9] = f11;
        fArr[10] = f6;
        fArr[11] = f7;
        fArr[12] = f12;
        fArr[13] = f10;
        fArr[14] = f11;
        if (this.mesh.getNumIndices() > 0) {
            fArr[15] = f6;
            fArr[16] = f3;
            fArr[17] = this.colorPacked;
            fArr[18] = f10;
            fArr[19] = f9;
            add(textureRegion.texture, fArr, 0, 20);
            return;
        }
        fArr[15] = f6;
        fArr[16] = f7;
        float f13 = this.colorPacked;
        fArr[17] = f13;
        fArr[18] = f10;
        fArr[19] = f11;
        fArr[20] = f6;
        fArr[21] = f3;
        fArr[22] = f13;
        fArr[23] = f10;
        fArr[24] = f9;
        fArr[25] = f2;
        fArr[26] = f3;
        fArr[27] = f13;
        fArr[28] = f8;
        fArr[29] = f9;
        add(textureRegion.texture, fArr, 0, 30);
    }

    public void add(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
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
        float[] fArr = tempVertices;
        fArr[0] = f31;
        fArr[1] = f32;
        float f43 = this.colorPacked;
        fArr[2] = f43;
        fArr[3] = f39;
        fArr[4] = f40;
        fArr[5] = f33;
        fArr[6] = f34;
        fArr[7] = f43;
        fArr[8] = f39;
        fArr[9] = f42;
        fArr[10] = f35;
        fArr[11] = f36;
        fArr[12] = f43;
        fArr[13] = f41;
        fArr[14] = f42;
        if (this.mesh.getNumIndices() > 0) {
            fArr[15] = f37;
            fArr[16] = f38;
            fArr[17] = this.colorPacked;
            fArr[18] = f41;
            fArr[19] = f40;
            add(textureRegion.texture, fArr, 0, 20);
            return;
        }
        fArr[15] = f35;
        fArr[16] = f36;
        float f44 = this.colorPacked;
        fArr[17] = f44;
        fArr[18] = f41;
        fArr[19] = f42;
        fArr[20] = f37;
        fArr[21] = f38;
        fArr[22] = f44;
        fArr[23] = f41;
        fArr[24] = f40;
        fArr[25] = f31;
        fArr[26] = f32;
        fArr[27] = f44;
        fArr[28] = f39;
        fArr[29] = f40;
        add(textureRegion.texture, fArr, 0, 30);
    }

    public void add(Sprite sprite) {
        if (this.mesh.getNumIndices() > 0) {
            add(sprite.getTexture(), sprite.getVertices(), 0, 20);
            return;
        }
        float[] vertices = sprite.getVertices();
        float[] fArr = tempVertices;
        System.arraycopy(vertices, 0, fArr, 0, 15);
        System.arraycopy(vertices, 10, fArr, 15, 5);
        System.arraycopy(vertices, 15, fArr, 20, 5);
        System.arraycopy(vertices, 0, fArr, 25, 5);
        add(sprite.getTexture(), fArr, 0, 30);
    }
}
