package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.m;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class DefaultTextureBinder implements TextureBinder {
    public static final int LRU = 1;
    public static final int MAX_GLES_UNITS = 32;
    public static final int ROUNDROBIN = 0;
    private int bindCount;
    private final int count;
    private int currentTexture;
    private final int method;
    private final int offset;
    private int reuseCount;
    private boolean reused;
    private final TextureDescriptor tempDesc;
    private final GLTexture[] textures;
    private int[] unitsLRU;

    public DefaultTextureBinder(int i2) {
        this(i2, 0);
    }

    private final int bindTexture(TextureDescriptor textureDescriptor, boolean z2) {
        int i2;
        int iBindTextureRoundRobin;
        GLTexture gLTexture = textureDescriptor.texture;
        this.reused = false;
        int i3 = this.method;
        if (i3 == 0) {
            i2 = this.offset;
            iBindTextureRoundRobin = bindTextureRoundRobin(gLTexture);
        } else {
            if (i3 != 1) {
                return -1;
            }
            i2 = this.offset;
            iBindTextureRoundRobin = bindTextureLRU(gLTexture);
        }
        int i4 = i2 + iBindTextureRoundRobin;
        if (this.reused) {
            this.reuseCount++;
            if (z2) {
                gLTexture.bind(i4);
            } else {
                Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0 + i4);
            }
        } else {
            this.bindCount++;
        }
        gLTexture.unsafeSetWrap(textureDescriptor.uWrap, textureDescriptor.vWrap);
        gLTexture.unsafeSetFilter(textureDescriptor.minFilter, textureDescriptor.magFilter);
        return i4;
    }

    private final int bindTextureLRU(GLTexture gLTexture) {
        int i2;
        int i3 = 0;
        while (true) {
            i2 = this.count;
            if (i3 >= i2) {
                break;
            }
            GLTexture gLTexture2 = this.textures[this.unitsLRU[i3]];
            if (gLTexture2 == gLTexture) {
                this.reused = true;
                break;
            }
            if (gLTexture2 == null) {
                break;
            }
            i3++;
        }
        if (i3 >= i2) {
            i3 = i2 - 1;
        }
        int i4 = this.unitsLRU[i3];
        while (i3 > 0) {
            int[] iArr = this.unitsLRU;
            iArr[i3] = iArr[i3 - 1];
            i3--;
        }
        this.unitsLRU[0] = i4;
        if (!this.reused) {
            this.textures[i4] = gLTexture;
            gLTexture.bind(this.offset + i4);
        }
        return i4;
    }

    private final int bindTextureRoundRobin(GLTexture gLTexture) {
        int i2 = 0;
        while (true) {
            int i3 = this.count;
            if (i2 >= i3) {
                int i4 = (this.currentTexture + 1) % i3;
                this.currentTexture = i4;
                this.textures[i4] = gLTexture;
                gLTexture.bind(this.offset + i4);
                return this.currentTexture;
            }
            int i5 = (this.currentTexture + i2) % i3;
            if (this.textures[i5] == gLTexture) {
                this.reused = true;
                return i5;
            }
            i2++;
        }
    }

    private static int getMaxTextureUnits() {
        IntBuffer intBufferG = BufferUtils.g(16);
        Gdx.gl.glGetIntegerv(GL20.GL_MAX_TEXTURE_IMAGE_UNITS, intBufferG);
        return intBufferG.get(0);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public void begin() {
        for (int i2 = 0; i2 < this.count; i2++) {
            this.textures[i2] = null;
            int[] iArr = this.unitsLRU;
            if (iArr != null) {
                iArr[i2] = i2;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int bind(TextureDescriptor textureDescriptor) {
        return bindTexture(textureDescriptor, false);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public void end() {
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int getBindCount() {
        return this.bindCount;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int getReuseCount() {
        return this.reuseCount;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final void resetCounts() {
        this.reuseCount = 0;
        this.bindCount = 0;
    }

    public DefaultTextureBinder(int i2, int i3) {
        this(i2, i3, -1);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int bind(GLTexture gLTexture) {
        this.tempDesc.set(gLTexture, null, null, null, null);
        return bindTexture(this.tempDesc, false);
    }

    public DefaultTextureBinder(int i2, int i3, int i4) {
        this.reuseCount = 0;
        this.bindCount = 0;
        this.tempDesc = new TextureDescriptor();
        this.currentTexture = 0;
        int iMin = Math.min(getMaxTextureUnits(), 32);
        i4 = i4 < 0 ? iMin - i3 : i4;
        if (i3 >= 0 && i4 >= 0 && i3 + i4 <= iMin) {
            this.method = i2;
            this.offset = i3;
            this.count = i4;
            this.textures = new GLTexture[i4];
            this.unitsLRU = i2 == 1 ? new int[i4] : null;
            return;
        }
        throw new m("Illegal arguments");
    }
}
