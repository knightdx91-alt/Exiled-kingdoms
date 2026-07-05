package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GLOnlyTextureData implements TextureData {
    int format;
    int height;
    int internalFormat;
    boolean isPrepared = false;
    int mipLevel;
    int type;
    int width;

    public GLOnlyTextureData(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.width = i2;
        this.height = i3;
        this.mipLevel = i4;
        this.internalFormat = i5;
        this.format = i6;
        this.type = i7;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i2) {
        Gdx.gl.glTexImage2D(i2, this.mipLevel, this.internalFormat, this.width, this.height, 0, this.format, this.type, null);
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new m("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new m("This TextureData implementation does not return a Pixmap");
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
        return false;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        if (this.isPrepared) {
            throw new m("Already prepared");
        }
        this.isPrepared = true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return false;
    }
}
