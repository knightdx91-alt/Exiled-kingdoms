package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ETC1TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface TextureData {

    public static class Factory {
        public static TextureData loadFromFile(a aVar, boolean z2) {
            return loadFromFile(aVar, null, z2);
        }

        public static TextureData loadFromFile(a aVar, Pixmap.Format format, boolean z2) {
            if (aVar == null) {
                return null;
            }
            return aVar.name().endsWith(".cim") ? new FileTextureData(aVar, PixmapIO.readCIM(aVar), format, z2) : aVar.name().endsWith(".etc1") ? new ETC1TextureData(aVar, z2) : (aVar.name().endsWith(".ktx") || aVar.name().endsWith(".zktx")) ? new KTXTextureData(aVar, z2) : new FileTextureData(aVar, new Pixmap(aVar), format, z2);
        }
    }

    public enum TextureDataType {
        Pixmap,
        Custom
    }

    void consumeCustomData(int i2);

    Pixmap consumePixmap();

    boolean disposePixmap();

    Pixmap.Format getFormat();

    int getHeight();

    TextureDataType getType();

    int getWidth();

    boolean isManaged();

    boolean isPrepared();

    void prepare();

    boolean useMipMaps();
}
