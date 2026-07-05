package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MipMapGenerator {
    private static boolean useHWMipMap = true;

    private MipMapGenerator() {
    }

    public static void generateMipMap(Pixmap pixmap, int i2, int i3) {
        generateMipMap(GL20.GL_TEXTURE_2D, pixmap, i2, i3);
    }

    private static void generateMipMapCPU(int i2, Pixmap pixmap, int i3, int i4) {
        Gdx.gl.glTexImage2D(i2, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
        if (Gdx.gl20 == null && i3 != i4) {
            throw new m("texture width and height must be square when using mipmapping.");
        }
        int width = pixmap.getWidth() / 2;
        int height = pixmap.getHeight() / 2;
        int i5 = 1;
        Pixmap pixmap2 = pixmap;
        while (width > 0 && height > 0) {
            Pixmap pixmap3 = new Pixmap(width, height, pixmap2.getFormat());
            pixmap3.setBlending(Pixmap.Blending.None);
            pixmap3.drawPixmap(pixmap2, 0, 0, pixmap2.getWidth(), pixmap2.getHeight(), 0, 0, width, height);
            if (i5 > 1) {
                pixmap2.dispose();
            }
            pixmap2 = pixmap3;
            Gdx.gl.glTexImage2D(i2, i5, pixmap3.getGLInternalFormat(), pixmap3.getWidth(), pixmap3.getHeight(), 0, pixmap3.getGLFormat(), pixmap3.getGLType(), pixmap3.getPixels());
            width = pixmap2.getWidth() / 2;
            height = pixmap2.getHeight() / 2;
            i5++;
        }
    }

    private static void generateMipMapDesktop(int i2, Pixmap pixmap, int i3, int i4) {
        if (!Gdx.graphics.supportsExtension("GL_ARB_framebuffer_object") && !Gdx.graphics.supportsExtension("GL_EXT_framebuffer_object") && Gdx.gl30 == null) {
            generateMipMapCPU(i2, pixmap, i3, i4);
        } else {
            Gdx.gl.glTexImage2D(i2, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
            Gdx.gl20.glGenerateMipmap(i2);
        }
    }

    private static void generateMipMapGLES20(int i2, Pixmap pixmap) {
        Gdx.gl.glTexImage2D(i2, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
        Gdx.gl20.glGenerateMipmap(i2);
    }

    public static void setUseHardwareMipMap(boolean z2) {
        useHWMipMap = z2;
    }

    public static void generateMipMap(int i2, Pixmap pixmap, int i3, int i4) {
        if (!useHWMipMap) {
            generateMipMapCPU(i2, pixmap, i3, i4);
        } else if (Gdx.app.getType() == Application.a.f1563a || Gdx.app.getType() == Application.a.f1566d || Gdx.app.getType() == Application.a.f1567e) {
            generateMipMapGLES20(i2, pixmap);
        } else {
            generateMipMapDesktop(i2, pixmap, i3, i4);
        }
    }
}
