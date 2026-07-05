package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FacedCubemapData implements CubemapData {
    protected final TextureData[] data;

    public FacedCubemapData() {
        this((TextureData) null, (TextureData) null, (TextureData) null, (TextureData) null, (TextureData) null, (TextureData) null);
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public void consumeCubemapData() {
        int i2 = 0;
        while (true) {
            TextureData[] textureDataArr = this.data;
            if (i2 >= textureDataArr.length) {
                return;
            }
            if (textureDataArr[i2].getType() == TextureData.TextureDataType.Custom) {
                this.data[i2].consumeCustomData(GL20.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i2);
            } else {
                Pixmap pixmapConsumePixmap = this.data[i2].consumePixmap();
                boolean zDisposePixmap = this.data[i2].disposePixmap();
                if (this.data[i2].getFormat() != pixmapConsumePixmap.getFormat()) {
                    Pixmap pixmap = new Pixmap(pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight(), this.data[i2].getFormat());
                    pixmap.setBlending(Pixmap.Blending.None);
                    pixmap.drawPixmap(pixmapConsumePixmap, 0, 0, 0, 0, pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight());
                    if (this.data[i2].disposePixmap()) {
                        pixmapConsumePixmap.dispose();
                    }
                    pixmapConsumePixmap = pixmap;
                    zDisposePixmap = true;
                }
                Gdx.gl.glPixelStorei(GL20.GL_UNPACK_ALIGNMENT, 1);
                Gdx.gl.glTexImage2D(i2 + GL20.GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, pixmapConsumePixmap.getGLInternalFormat(), pixmapConsumePixmap.getWidth(), pixmapConsumePixmap.getHeight(), 0, pixmapConsumePixmap.getGLFormat(), pixmapConsumePixmap.getGLType(), pixmapConsumePixmap.getPixels());
                if (zDisposePixmap) {
                    pixmapConsumePixmap.dispose();
                }
            }
            i2++;
        }
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public int getHeight() {
        int height;
        int height2;
        int height3;
        int height4;
        TextureData textureData = this.data[Cubemap.CubemapSide.PositiveZ.index];
        if (textureData == null || (height = textureData.getHeight()) <= 0) {
            height = 0;
        }
        TextureData textureData2 = this.data[Cubemap.CubemapSide.NegativeZ.index];
        if (textureData2 != null && (height4 = textureData2.getHeight()) > height) {
            height = height4;
        }
        TextureData textureData3 = this.data[Cubemap.CubemapSide.PositiveX.index];
        if (textureData3 != null && (height3 = textureData3.getHeight()) > height) {
            height = height3;
        }
        TextureData textureData4 = this.data[Cubemap.CubemapSide.NegativeX.index];
        return (textureData4 == null || (height2 = textureData4.getHeight()) <= height) ? height : height2;
    }

    public TextureData getTextureData(Cubemap.CubemapSide cubemapSide) {
        return this.data[cubemapSide.index];
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public int getWidth() {
        int width;
        int width2;
        int width3;
        int width4;
        TextureData textureData = this.data[Cubemap.CubemapSide.PositiveZ.index];
        if (textureData == null || (width = textureData.getWidth()) <= 0) {
            width = 0;
        }
        TextureData textureData2 = this.data[Cubemap.CubemapSide.NegativeZ.index];
        if (textureData2 != null && (width4 = textureData2.getWidth()) > width) {
            width = width4;
        }
        TextureData textureData3 = this.data[Cubemap.CubemapSide.PositiveY.index];
        if (textureData3 != null && (width3 = textureData3.getWidth()) > width) {
            width = width3;
        }
        TextureData textureData4 = this.data[Cubemap.CubemapSide.NegativeY.index];
        return (textureData4 == null || (width2 = textureData4.getWidth()) <= width) ? width : width2;
    }

    public boolean isComplete() {
        int i2 = 0;
        while (true) {
            TextureData[] textureDataArr = this.data;
            if (i2 >= textureDataArr.length) {
                return true;
            }
            if (textureDataArr[i2] == null) {
                return false;
            }
            i2++;
        }
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public boolean isManaged() {
        for (TextureData textureData : this.data) {
            if (!textureData.isManaged()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public boolean isPrepared() {
        return false;
    }

    public void load(Cubemap.CubemapSide cubemapSide, a aVar) {
        this.data[cubemapSide.index] = TextureData.Factory.loadFromFile(aVar, false);
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public void prepare() {
        if (!isComplete()) {
            throw new m("You need to complete your cubemap data before using it");
        }
        int i2 = 0;
        while (true) {
            TextureData[] textureDataArr = this.data;
            if (i2 >= textureDataArr.length) {
                return;
            }
            if (!textureDataArr[i2].isPrepared()) {
                this.data[i2].prepare();
            }
            i2++;
        }
    }

    public FacedCubemapData(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6) {
        this(TextureData.Factory.loadFromFile(aVar, false), TextureData.Factory.loadFromFile(aVar2, false), TextureData.Factory.loadFromFile(aVar3, false), TextureData.Factory.loadFromFile(aVar4, false), TextureData.Factory.loadFromFile(aVar5, false), TextureData.Factory.loadFromFile(aVar6, false));
    }

    public void load(Cubemap.CubemapSide cubemapSide, Pixmap pixmap) {
        this.data[cubemapSide.index] = pixmap != null ? new PixmapTextureData(pixmap, null, false, false) : null;
    }

    public FacedCubemapData(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, boolean z2) {
        this(TextureData.Factory.loadFromFile(aVar, z2), TextureData.Factory.loadFromFile(aVar2, z2), TextureData.Factory.loadFromFile(aVar3, z2), TextureData.Factory.loadFromFile(aVar4, z2), TextureData.Factory.loadFromFile(aVar5, z2), TextureData.Factory.loadFromFile(aVar6, z2));
    }

    public FacedCubemapData(Pixmap pixmap, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, Pixmap pixmap6) {
        this(pixmap, pixmap2, pixmap3, pixmap4, pixmap5, pixmap6, false);
    }

    public FacedCubemapData(Pixmap pixmap, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, Pixmap pixmap6, boolean z2) {
        this(pixmap == null ? null : new PixmapTextureData(pixmap, null, z2, false), pixmap2 == null ? null : new PixmapTextureData(pixmap2, null, z2, false), pixmap3 == null ? null : new PixmapTextureData(pixmap3, null, z2, false), pixmap4 == null ? null : new PixmapTextureData(pixmap4, null, z2, false), pixmap5 == null ? null : new PixmapTextureData(pixmap5, null, z2, false), pixmap6 == null ? null : new PixmapTextureData(pixmap6, null, z2, false));
    }

    public FacedCubemapData(int i2, int i3, int i4, Pixmap.Format format) {
        this(new PixmapTextureData(new Pixmap(i4, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i4, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i4, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i4, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i3, format), null, false, true));
    }

    public FacedCubemapData(TextureData textureData, TextureData textureData2, TextureData textureData3, TextureData textureData4, TextureData textureData5, TextureData textureData6) {
        this.data = new TextureData[]{textureData, textureData2, textureData3, textureData4, textureData5, textureData6};
    }
}
