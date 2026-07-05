package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FileTextureData implements TextureData {
    final a file;
    Pixmap.Format format;
    int height;
    boolean isPrepared = false;
    Pixmap pixmap;
    boolean useMipMaps;
    int width;

    public FileTextureData(a aVar, Pixmap pixmap, Pixmap.Format format, boolean z2) {
        this.width = 0;
        this.height = 0;
        this.file = aVar;
        this.pixmap = pixmap;
        this.format = format;
        this.useMipMaps = z2;
        if (pixmap != null) {
            this.width = pixmap.getWidth();
            this.height = this.pixmap.getHeight();
            if (format == null) {
                this.format = this.pixmap.getFormat();
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i2) {
        throw new m("This TextureData implementation does not upload data itself");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        if (!this.isPrepared) {
            throw new m("Call prepare() before calling getPixmap()");
        }
        this.isPrepared = false;
        Pixmap pixmap = this.pixmap;
        this.pixmap = null;
        return pixmap;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        return true;
    }

    public a getFileHandle() {
        return this.file;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap.Format getFormat() {
        return this.format;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.height;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public TextureData.TextureDataType getType() {
        return TextureData.TextureDataType.Pixmap;
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
        if (this.pixmap == null) {
            if (this.file.extension().equals("cim")) {
                this.pixmap = PixmapIO.readCIM(this.file);
            } else {
                this.pixmap = new Pixmap(this.file);
            }
            this.width = this.pixmap.getWidth();
            this.height = this.pixmap.getHeight();
            if (this.format == null) {
                this.format = this.pixmap.getFormat();
            }
        }
        this.isPrepared = true;
    }

    public String toString() {
        return this.file.toString();
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }
}
