package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class HdpiUtils {
    private static HdpiMode mode = HdpiMode.Logical;

    public static void glScissor(int i2, int i3, int i4, int i5) {
        if (mode != HdpiMode.Logical || (Gdx.graphics.getWidth() == Gdx.graphics.getBackBufferWidth() && Gdx.graphics.getHeight() == Gdx.graphics.getBackBufferHeight())) {
            Gdx.gl.glScissor(i2, i3, i4, i5);
        } else {
            Gdx.gl.glScissor(toBackBufferX(i2), toBackBufferY(i3), toBackBufferX(i4), toBackBufferY(i5));
        }
    }

    public static void glViewport(int i2, int i3, int i4, int i5) {
        if (mode != HdpiMode.Logical || (Gdx.graphics.getWidth() == Gdx.graphics.getBackBufferWidth() && Gdx.graphics.getHeight() == Gdx.graphics.getBackBufferHeight())) {
            Gdx.gl.glViewport(i2, i3, i4, i5);
        } else {
            Gdx.gl.glViewport(toBackBufferX(i2), toBackBufferY(i3), toBackBufferX(i4), toBackBufferY(i5));
        }
    }

    public static void setMode(HdpiMode hdpiMode) {
        mode = hdpiMode;
    }

    public static int toBackBufferX(int i2) {
        return (int) ((Gdx.graphics.getBackBufferWidth() * i2) / Gdx.graphics.getWidth());
    }

    public static int toBackBufferY(int i2) {
        return (int) ((Gdx.graphics.getBackBufferHeight() * i2) / Gdx.graphics.getHeight());
    }

    public static int toLogicalX(int i2) {
        return (int) ((Gdx.graphics.getWidth() * i2) / Gdx.graphics.getBackBufferWidth());
    }

    public static int toLogicalY(int i2) {
        return (int) ((Gdx.graphics.getHeight() * i2) / Gdx.graphics.getBackBufferHeight());
    }
}
