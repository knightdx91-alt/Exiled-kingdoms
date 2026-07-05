package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.GLVersion;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.m;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FloatTextureData implements TextureData {
    FloatBuffer buffer;
    int format;
    int height;
    int internalFormat;
    boolean isGpuOnly;
    boolean isPrepared = false;
    int type;
    int width;

    public FloatTextureData(int i2, int i3, int i4, int i5, int i6, boolean z2) {
        this.width = i2;
        this.height = i3;
        this.internalFormat = i4;
        this.format = i5;
        this.type = i6;
        this.isGpuOnly = z2;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i2) {
        if (Gdx.app.getType() == Application.a.f1563a || Gdx.app.getType() == Application.a.f1567e || Gdx.app.getType() == Application.a.f1566d) {
            if (!Gdx.graphics.supportsExtension("OES_texture_float")) {
                throw new m("Extension OES_texture_float not supported!");
            }
            Gdx.gl.glTexImage2D(i2, 0, GL20.GL_RGBA, this.width, this.height, 0, GL20.GL_RGBA, GL20.GL_FLOAT, this.buffer);
        } else {
            if (!Gdx.graphics.isGL30Available() && !Gdx.graphics.supportsExtension("GL_ARB_texture_float")) {
                throw new m("Extension GL_ARB_texture_float not supported!");
            }
            Gdx.gl.glTexImage2D(i2, 0, this.internalFormat, this.width, this.height, 0, this.format, GL20.GL_FLOAT, this.buffer);
        }
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new m("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new m("This TextureData implementation does not return a Pixmap");
    }

    public FloatBuffer getBuffer() {
        return this.buffer;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap.Format getFormat() {
        return Pixmap.Format.RGBA8888;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.height;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public TextureData.TextureDataType getType() {
        return TextureData.TextureDataType.Custom;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.width;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        int i2;
        if (this.isPrepared) {
            throw new m("Already prepared");
        }
        if (!this.isGpuOnly) {
            if (Gdx.graphics.getGLVersion().getType().equals(GLVersion.Type.OpenGL)) {
                int i3 = this.internalFormat;
                if (i3 != 34842) {
                }
                i2 = (i3 == 34843 || i3 == 34837) ? 3 : 4;
                if (i3 == 33327 || i3 == 33328) {
                    i2 = 2;
                }
                if (i3 == 33325 || i3 == 33326) {
                    i2 = 1;
                }
            } else {
                i2 = 4;
            }
            int i4 = this.width * this.height * i2;
            int i5 = BufferUtils.f1745b;
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i4 * 4);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            this.buffer = byteBufferAllocateDirect.asFloatBuffer();
        }
        this.isPrepared = true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return false;
    }
}
