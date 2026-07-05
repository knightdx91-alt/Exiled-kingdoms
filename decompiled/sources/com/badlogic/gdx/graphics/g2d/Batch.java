package com.badlogic.gdx.graphics.g2d;

import a0.a;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.i;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Batch extends i {
    public static final int C1 = 2;
    public static final int C2 = 7;
    public static final int C3 = 12;
    public static final int C4 = 17;
    public static final int U1 = 3;
    public static final int U2 = 8;
    public static final int U3 = 13;
    public static final int U4 = 18;
    public static final int V1 = 4;
    public static final int V2 = 9;
    public static final int V3 = 14;
    public static final int V4 = 19;
    public static final int X1 = 0;
    public static final int X2 = 5;
    public static final int X3 = 10;
    public static final int X4 = 15;
    public static final int Y1 = 1;
    public static final int Y2 = 6;
    public static final int Y3 = 11;
    public static final int Y4 = 16;

    void begin();

    void disableBlending();

    @Override // com.badlogic.gdx.utils.i
    /* synthetic */ void dispose();

    void draw(Texture texture, float f2, float f3);

    void draw(Texture texture, float f2, float f3, float f4, float f5);

    void draw(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9);

    void draw(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int i2, int i3, int i4, int i5, boolean z2, boolean z3);

    void draw(Texture texture, float f2, float f3, float f4, float f5, int i2, int i3, int i4, int i5, boolean z2, boolean z3);

    void draw(Texture texture, float f2, float f3, int i2, int i3, int i4, int i5);

    void draw(Texture texture, float[] fArr, int i2, int i3);

    void draw(TextureRegion textureRegion, float f2, float f3);

    void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5);

    void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10);

    void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, boolean z2);

    void draw(TextureRegion textureRegion, float f2, float f3, a aVar);

    void enableBlending();

    void end();

    void flush();

    int getBlendDstFunc();

    int getBlendDstFuncAlpha();

    int getBlendSrcFunc();

    int getBlendSrcFuncAlpha();

    Color getColor();

    float getPackedColor();

    Matrix4 getProjectionMatrix();

    ShaderProgram getShader();

    Matrix4 getTransformMatrix();

    boolean isBlendingEnabled();

    boolean isDrawing();

    void setBlendFunction(int i2, int i3);

    void setBlendFunctionSeparate(int i2, int i3, int i4, int i5);

    void setColor(float f2, float f3, float f4, float f5);

    void setColor(Color color);

    void setPackedColor(float f2);

    void setProjectionMatrix(Matrix4 matrix4);

    void setShader(ShaderProgram shaderProgram);

    void setTransformMatrix(Matrix4 matrix4);
}
