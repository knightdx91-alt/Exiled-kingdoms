package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.i;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface VertexData extends i {
    void bind(ShaderProgram shaderProgram);

    void bind(ShaderProgram shaderProgram, int[] iArr);

    @Override // com.badlogic.gdx.utils.i
    void dispose();

    VertexAttributes getAttributes();

    FloatBuffer getBuffer();

    int getNumMaxVertices();

    int getNumVertices();

    void invalidate();

    void setVertices(float[] fArr, int i2, int i3);

    void unbind(ShaderProgram shaderProgram);

    void unbind(ShaderProgram shaderProgram, int[] iArr);

    void updateVertices(int i2, float[] fArr, int i3, int i4);
}
