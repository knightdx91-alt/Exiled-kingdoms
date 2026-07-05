package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FileTextureArrayData;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface TextureArrayData {

    public static class Factory {
        public static TextureArrayData loadFromFiles(Pixmap.Format format, boolean z2, a... aVarArr) {
            return new FileTextureArrayData(format, z2, aVarArr);
        }
    }

    void consumeTextureArrayData();

    int getDepth();

    int getGLType();

    int getHeight();

    int getInternalFormat();

    int getWidth();

    boolean isManaged();

    boolean isPrepared();

    void prepare();
}
