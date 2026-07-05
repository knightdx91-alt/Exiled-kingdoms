package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.utils.i;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface IndexData extends i {
    void bind();

    @Override // com.badlogic.gdx.utils.i
    void dispose();

    ShortBuffer getBuffer();

    int getNumIndices();

    int getNumMaxIndices();

    void invalidate();

    void setIndices(ShortBuffer shortBuffer);

    void setIndices(short[] sArr, int i2, int i3);

    void unbind();

    void updateIndices(int i2, short[] sArr, int i3, int i4);
}
