package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PixmapTextureData implements TextureData {
    final boolean disposePixmap;
    final Pixmap.Format format;
    final boolean managed;
    final Pixmap pixmap;
    final boolean useMipMaps;

    public PixmapTextureData(Pixmap pixmap, Pixmap.Format format, boolean z2, boolean z3) {
        this(pixmap, format, z2, z3, false);
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i2) {
        throw new m("This TextureData implementation does not upload data itself");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        return this.pixmap;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        return this.disposePixmap;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap.Format getFormat() {
        return this.format;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.pixmap.getHeight();
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public TextureData.TextureDataType getType() {
        return TextureData.TextureDataType.Pixmap;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.pixmap.getWidth();
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return this.managed;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        throw new m("prepare() must not be called on a PixmapTextureData instance as it is already prepared.");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }

    public PixmapTextureData(Pixmap pixmap, Pixmap.Format format, boolean z2, boolean z3, boolean z4) {
        this.pixmap = pixmap;
        this.format = format == null ? pixmap.getFormat() : format;
        this.useMipMaps = z2;
        this.disposePixmap = z3;
        this.managed = z4;
    }
}
