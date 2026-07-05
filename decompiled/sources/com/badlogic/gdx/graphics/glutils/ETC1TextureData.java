package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.ETC1;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ETC1TextureData implements TextureData {
    ETC1.ETC1Data data;
    a file;
    int height;
    boolean isPrepared;
    boolean useMipMaps;
    int width;

    public ETC1TextureData(a aVar) {
        this(aVar, false);
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i2) {
        if (!this.isPrepared) {
            throw new m("Call prepare() before calling consumeCompressedData()");
        }
        if (Gdx.graphics.supportsExtension("GL_OES_compressed_ETC1_RGB8_texture")) {
            GL20 gl20 = Gdx.gl;
            int i3 = ETC1.ETC1_RGB8_OES;
            int i4 = this.width;
            int i5 = this.height;
            int iCapacity = this.data.compressedData.capacity();
            ETC1.ETC1Data eTC1Data = this.data;
            gl20.glCompressedTexImage2D(i2, 0, i3, i4, i5, 0, iCapacity - eTC1Data.dataOffset, eTC1Data.compressedData);
            if (useMipMaps()) {
                Gdx.gl20.glGenerateMipmap(GL20.GL_TEXTURE_2D);
            }
        } else {
            Pixmap pixmapDecodeImage = ETC1.decodeImage(this.data, Pixmap.Format.RGB565);
            Gdx.gl.glTexImage2D(i2, 0, pixmapDecodeImage.getGLInternalFormat(), pixmapDecodeImage.getWidth(), pixmapDecodeImage.getHeight(), 0, pixmapDecodeImage.getGLFormat(), pixmapDecodeImage.getGLType(), pixmapDecodeImage.getPixels());
            if (this.useMipMaps) {
                MipMapGenerator.generateMipMap(i2, pixmapDecodeImage, pixmapDecodeImage.getWidth(), pixmapDecodeImage.getHeight());
            }
            pixmapDecodeImage.dispose();
            this.useMipMaps = false;
        }
        this.data.dispose();
        this.data = null;
        this.isPrepared = false;
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
        return Pixmap.Format.RGB565;
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
        if (this.isPrepared) {
            throw new m("Already prepared");
        }
        a aVar = this.file;
        if (aVar == null && this.data == null) {
            throw new m("Can only load once from ETC1Data");
        }
        if (aVar != null) {
            this.data = new ETC1.ETC1Data(aVar);
        }
        ETC1.ETC1Data eTC1Data = this.data;
        this.width = eTC1Data.width;
        this.height = eTC1Data.height;
        this.isPrepared = true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }

    public ETC1TextureData(a aVar, boolean z2) {
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.file = aVar;
        this.useMipMaps = z2;
    }

    public ETC1TextureData(ETC1.ETC1Data eTC1Data, boolean z2) {
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.data = eTC1Data;
        this.useMipMaps = z2;
    }
}
