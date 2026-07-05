package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface TextureBinder {
    void begin();

    int bind(GLTexture gLTexture);

    int bind(TextureDescriptor textureDescriptor);

    void end();

    int getBindCount();

    int getReuseCount();

    void resetCounts();
}
