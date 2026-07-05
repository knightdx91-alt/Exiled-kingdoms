package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.i;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface InstanceData extends i {
    void bind(ShaderProgram shaderProgram);

    void bind(ShaderProgram shaderProgram, int[] iArr);

    @Override // com.badlogic.gdx.utils.i
    void dispose();

    VertexAttributes getAttributes();

    FloatBuffer getBuffer();

    int getNumInstances();

    int getNumMaxInstances();

    void invalidate();

    void setInstanceData(FloatBuffer floatBuffer, int i2);

    void setInstanceData(float[] fArr, int i2, int i3);

    void unbind(ShaderProgram shaderProgram);

    void unbind(ShaderProgram shaderProgram, int[] iArr);

    void updateInstanceData(int i2, FloatBuffer floatBuffer, int i3, int i4);

    void updateInstanceData(int i2, float[] fArr, int i3, int i4);
}
