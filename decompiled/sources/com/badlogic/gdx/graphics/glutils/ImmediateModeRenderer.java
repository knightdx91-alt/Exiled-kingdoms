package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface ImmediateModeRenderer {
    void begin(Matrix4 matrix4, int i2);

    void color(float f2);

    void color(float f2, float f3, float f4, float f5);

    void color(Color color);

    void dispose();

    void end();

    void flush();

    int getMaxVertices();

    int getNumVertices();

    void normal(float f2, float f3, float f4);

    void texCoord(float f2, float f3);

    void vertex(float f2, float f3, float f4);
}
