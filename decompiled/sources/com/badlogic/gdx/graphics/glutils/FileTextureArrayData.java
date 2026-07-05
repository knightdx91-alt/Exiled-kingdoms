package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureArrayData;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FileTextureArrayData implements TextureArrayData {
    private int depth;
    private Pixmap.Format format;
    private boolean prepared;
    private TextureData[] textureDatas;
    boolean useMipMaps;

    public FileTextureArrayData(Pixmap.Format format, boolean z2, a[] aVarArr) {
        this.format = format;
        this.useMipMaps = z2;
        this.depth = aVarArr.length;
        this.textureDatas = new TextureData[aVarArr.length];
        for (int i2 = 0; i2 < aVarArr.length; i2++) {
            this.textureDatas[i2] = TextureData.Factory.loadFromFile(aVarArr[i2], format, z2);
        }
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public void consumeTextureArrayData() {
        boolean z2;
        Pixmap pixmap;
        int i2 = 0;
        while (true) {
            TextureData[] textureDataArr = this.textureDatas;
            if (i2 >= textureDataArr.length) {
                return;
            }
            if (textureDataArr[i2].getType() == TextureData.TextureDataType.Custom) {
                this.textureDatas[i2].consumeCustomData(GL30.GL_TEXTURE_2D_ARRAY);
            } else {
                TextureData textureData = this.textureDatas[i2];
                Pixmap pixmapConsumePixmap = textureData.consumePixmap();
                boolean zDisposePixmap = textureData.disposePixmap();
                if (textureData.getFormat() != pixmapConsumePixmap.getFormat()) {
                    Pixmap pixmap2 = new Pixmap(pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight(), textureData.getFormat());
                    pixmap2.setBlending(Pixmap.Blending.None);
                    pixmap2.drawPixmap(pixmapConsumePixmap, 0, 0, 0, 0, pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight());
                    if (textureData.disposePixmap()) {
                        pixmapConsumePixmap.dispose();
                    }
                    z2 = true;
                    pixmap = pixmap2;
                } else {
                    z2 = zDisposePixmap;
                    pixmap = pixmapConsumePixmap;
                }
                Gdx.gl30.glTexSubImage3D(GL30.GL_TEXTURE_2D_ARRAY, 0, 0, 0, i2, pixmap.getWidth(), pixmap.getHeight(), 1, pixmap.getGLInternalFormat(), pixmap.getGLType(), pixmap.getPixels());
                if (z2) {
                    pixmap.dispose();
                }
            }
            i2++;
        }
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getDepth() {
        return this.depth;
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getGLType() {
        return Pixmap.Format.toGlType(this.format);
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getHeight() {
        return this.textureDatas[0].getHeight();
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getInternalFormat() {
        return Pixmap.Format.toGlFormat(this.format);
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getWidth() {
        return this.textureDatas[0].getWidth();
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public boolean isManaged() {
        for (TextureData textureData : this.textureDatas) {
            if (!textureData.isManaged()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public boolean isPrepared() {
        return this.prepared;
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public void prepare() {
        int width = -1;
        int height = -1;
        for (TextureData textureData : this.textureDatas) {
            textureData.prepare();
            if (width == -1) {
                width = textureData.getWidth();
                height = textureData.getHeight();
            } else if (width != textureData.getWidth() || height != textureData.getHeight()) {
                throw new m("Error whilst preparing TextureArray: TextureArray Textures must have equal dimensions.");
            }
        }
        this.prepared = true;
    }
}
