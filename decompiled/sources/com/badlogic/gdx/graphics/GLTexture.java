package com.badlogic.gdx.graphics;

import a0.j;
import a0.o;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.MipMapGenerator;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.i;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class GLTexture implements i {
    private static float maxAnisotropicFilterLevel;
    protected float anisotropicFilterLevel;
    protected int glHandle;
    public final int glTarget;
    protected Texture.TextureFilter magFilter;
    protected Texture.TextureFilter minFilter;
    protected Texture.TextureWrap uWrap;
    protected Texture.TextureWrap vWrap;

    public GLTexture(int i2) {
        this(i2, Gdx.gl.glGenTexture());
    }

    public static float getMaxAnisotropicFilterLevel() {
        float f2 = maxAnisotropicFilterLevel;
        if (f2 > 0.0f) {
            return f2;
        }
        if (!Gdx.graphics.supportsExtension("GL_EXT_texture_filter_anisotropic")) {
            maxAnisotropicFilterLevel = 1.0f;
            return 1.0f;
        }
        int i2 = BufferUtils.f1745b;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(64);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
        floatBufferAsFloatBuffer.position(0);
        floatBufferAsFloatBuffer.limit(floatBufferAsFloatBuffer.capacity());
        Gdx.gl20.glGetFloatv(GL20.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, floatBufferAsFloatBuffer);
        float f3 = floatBufferAsFloatBuffer.get(0);
        maxAnisotropicFilterLevel = f3;
        return f3;
    }

    protected static void uploadImageData(int i2, TextureData textureData) {
        uploadImageData(i2, textureData, 0);
    }

    public void bind() {
        Gdx.gl.glBindTexture(this.glTarget, this.glHandle);
    }

    protected void delete() {
        int i2 = this.glHandle;
        if (i2 != 0) {
            Gdx.gl.glDeleteTexture(i2);
            this.glHandle = 0;
        }
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        delete();
    }

    public float getAnisotropicFilter() {
        return this.anisotropicFilterLevel;
    }

    public abstract int getDepth();

    public abstract int getHeight();

    public Texture.TextureFilter getMagFilter() {
        return this.magFilter;
    }

    public Texture.TextureFilter getMinFilter() {
        return this.minFilter;
    }

    public int getTextureObjectHandle() {
        return this.glHandle;
    }

    public Texture.TextureWrap getUWrap() {
        return this.uWrap;
    }

    public Texture.TextureWrap getVWrap() {
        return this.vWrap;
    }

    public abstract int getWidth();

    public abstract boolean isManaged();

    protected abstract void reload();

    public float setAnisotropicFilter(float f2) {
        float maxAnisotropicFilterLevel2 = getMaxAnisotropicFilterLevel();
        if (maxAnisotropicFilterLevel2 == 1.0f) {
            return 1.0f;
        }
        float fMin = Math.min(f2, maxAnisotropicFilterLevel2);
        float f3 = this.anisotropicFilterLevel;
        o oVar = j.f69a;
        if (Math.abs(fMin - f3) <= 0.1f) {
            return fMin;
        }
        bind();
        Gdx.gl20.glTexParameterf(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAX_ANISOTROPY_EXT, fMin);
        this.anisotropicFilterLevel = fMin;
        return fMin;
    }

    public void setFilter(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2) {
        this.minFilter = textureFilter;
        this.magFilter = textureFilter2;
        bind();
        Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_MIN_FILTER, textureFilter.getGLEnum());
        Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_MAG_FILTER, textureFilter2.getGLEnum());
    }

    public void setWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        this.uWrap = textureWrap;
        this.vWrap = textureWrap2;
        bind();
        Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_WRAP_S, textureWrap.getGLEnum());
        Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_WRAP_T, textureWrap2.getGLEnum());
    }

    public float unsafeSetAnisotropicFilter(float f2) {
        return unsafeSetAnisotropicFilter(f2, false);
    }

    public void unsafeSetFilter(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2) {
        unsafeSetFilter(textureFilter, textureFilter2, false);
    }

    public void unsafeSetWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        unsafeSetWrap(textureWrap, textureWrap2, false);
    }

    public GLTexture(int i2, int i3) {
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
        this.minFilter = textureFilter;
        this.magFilter = textureFilter;
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        this.uWrap = textureWrap;
        this.vWrap = textureWrap;
        this.anisotropicFilterLevel = 1.0f;
        this.glTarget = i2;
        this.glHandle = i3;
    }

    public static void uploadImageData(int i2, TextureData textureData, int i3) {
        if (textureData == null) {
            return;
        }
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        if (textureData.getType() == TextureData.TextureDataType.Custom) {
            textureData.consumeCustomData(i2);
            return;
        }
        Pixmap pixmapConsumePixmap = textureData.consumePixmap();
        boolean zDisposePixmap = textureData.disposePixmap();
        if (textureData.getFormat() != pixmapConsumePixmap.getFormat()) {
            Pixmap pixmap = new Pixmap(pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight(), textureData.getFormat());
            pixmap.setBlending(Pixmap.Blending.None);
            pixmap.drawPixmap(pixmapConsumePixmap, 0, 0, 0, 0, pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight());
            if (textureData.disposePixmap()) {
                pixmapConsumePixmap.dispose();
            }
            pixmapConsumePixmap = pixmap;
            zDisposePixmap = true;
        }
        Gdx.gl.glPixelStorei(GL20.GL_UNPACK_ALIGNMENT, 1);
        if (textureData.useMipMaps()) {
            MipMapGenerator.generateMipMap(i2, pixmapConsumePixmap, pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight());
        } else {
            Gdx.gl.glTexImage2D(i2, i3, pixmapConsumePixmap.getGLInternalFormat(), pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight(), 0, pixmapConsumePixmap.getGLFormat(), pixmapConsumePixmap.getGLType(), pixmapConsumePixmap.getPixels());
        }
        if (zDisposePixmap) {
            pixmapConsumePixmap.dispose();
        }
    }

    public void bind(int i2) {
        Gdx.gl.glActiveTexture(i2 + GL20.GL_TEXTURE0);
        Gdx.gl.glBindTexture(this.glTarget, this.glHandle);
    }

    public float unsafeSetAnisotropicFilter(float f2, boolean z2) {
        float maxAnisotropicFilterLevel2 = getMaxAnisotropicFilterLevel();
        if (maxAnisotropicFilterLevel2 == 1.0f) {
            return 1.0f;
        }
        float fMin = Math.min(f2, maxAnisotropicFilterLevel2);
        if (!z2) {
            float f3 = this.anisotropicFilterLevel;
            o oVar = j.f69a;
            if (Math.abs(fMin - f3) <= 0.1f) {
                return this.anisotropicFilterLevel;
            }
        }
        Gdx.gl20.glTexParameterf(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAX_ANISOTROPY_EXT, fMin);
        this.anisotropicFilterLevel = fMin;
        return fMin;
    }

    public void unsafeSetFilter(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z2) {
        if (textureFilter != null && (z2 || this.minFilter != textureFilter)) {
            Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_MIN_FILTER, textureFilter.getGLEnum());
            this.minFilter = textureFilter;
        }
        if (textureFilter2 != null) {
            if (z2 || this.magFilter != textureFilter2) {
                Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_MAG_FILTER, textureFilter2.getGLEnum());
                this.magFilter = textureFilter2;
            }
        }
    }

    public void unsafeSetWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2, boolean z2) {
        if (textureWrap != null && (z2 || this.uWrap != textureWrap)) {
            Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_WRAP_S, textureWrap.getGLEnum());
            this.uWrap = textureWrap;
        }
        if (textureWrap2 != null) {
            if (z2 || this.vWrap != textureWrap2) {
                Gdx.gl.glTexParameteri(this.glTarget, GL20.GL_TEXTURE_WRAP_T, textureWrap2.getGLEnum());
                this.vWrap = textureWrap2;
            }
        }
    }
}
