package com.badlogic.gdx.graphics.g2d;

import a.a;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Gdx2DPixmap implements i {
    public static final int GDX2D_BLEND_NONE = 0;
    public static final int GDX2D_BLEND_SRC_OVER = 1;
    public static final int GDX2D_FORMAT_ALPHA = 1;
    public static final int GDX2D_FORMAT_LUMINANCE_ALPHA = 2;
    public static final int GDX2D_FORMAT_RGB565 = 5;
    public static final int GDX2D_FORMAT_RGB888 = 3;
    public static final int GDX2D_FORMAT_RGBA4444 = 6;
    public static final int GDX2D_FORMAT_RGBA8888 = 4;
    public static final int GDX2D_SCALE_LINEAR = 1;
    public static final int GDX2D_SCALE_NEAREST = 0;
    long basePtr;
    int format;
    int height;
    long[] nativeData;
    ByteBuffer pixelPtr;
    int width;

    public Gdx2DPixmap(byte[] bArr, int i2, int i3, int i4) throws IOException {
        long[] jArr = new long[4];
        this.nativeData = jArr;
        ByteBuffer byteBufferLoad = load(jArr, bArr, i2, i3);
        this.pixelPtr = byteBufferLoad;
        if (byteBufferLoad == null) {
            throw new IOException("Error loading pixmap: " + getFailureReason());
        }
        long[] jArr2 = this.nativeData;
        this.basePtr = jArr2[0];
        this.width = (int) jArr2[1];
        this.height = (int) jArr2[2];
        int i5 = (int) jArr2[3];
        this.format = i5;
        if (i4 == 0 || i4 == i5) {
            return;
        }
        convert(i4);
    }

    private static native void clear(long j2, int i2);

    private void convert(int i2) {
        Gdx2DPixmap gdx2DPixmap = new Gdx2DPixmap(this.width, this.height, i2);
        gdx2DPixmap.setBlend(0);
        gdx2DPixmap.drawPixmap(this, 0, 0, 0, 0, this.width, this.height);
        dispose();
        this.basePtr = gdx2DPixmap.basePtr;
        this.format = gdx2DPixmap.format;
        this.height = gdx2DPixmap.height;
        this.nativeData = gdx2DPixmap.nativeData;
        this.pixelPtr = gdx2DPixmap.pixelPtr;
        this.width = gdx2DPixmap.width;
    }

    private static native void drawCircle(long j2, int i2, int i3, int i4, int i5);

    private static native void drawLine(long j2, int i2, int i3, int i4, int i5, int i6);

    private static native void drawPixmap(long j2, long j3, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    private static native void drawRect(long j2, int i2, int i3, int i4, int i5, int i6);

    private static native void fillCircle(long j2, int i2, int i3, int i4, int i5);

    private static native void fillRect(long j2, int i2, int i3, int i4, int i5, int i6);

    private static native void fillTriangle(long j2, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    private static native void free(long j2);

    public static native String getFailureReason();

    private static native int getPixel(long j2, int i2, int i3);

    private static native ByteBuffer load(long[] jArr, byte[] bArr, int i2, int i3);

    public static Gdx2DPixmap newPixmap(InputStream inputStream, int i2) {
        try {
            return new Gdx2DPixmap(inputStream, i2);
        } catch (IOException unused) {
            return null;
        }
    }

    private static native ByteBuffer newPixmap(long[] jArr, int i2, int i3, int i4);

    private static native void setBlend(long j2, int i2);

    private static native void setPixel(long j2, int i2, int i3, int i4);

    private static native void setScale(long j2, int i2);

    public static int toGlFormat(int i2) {
        switch (i2) {
            case 1:
                return GL20.GL_ALPHA;
            case 2:
                return GL20.GL_LUMINANCE_ALPHA;
            case 3:
            case 5:
                return GL20.GL_RGB;
            case 4:
            case 6:
                return GL20.GL_RGBA;
            default:
                throw new m(a.h(i2, "unknown format: "));
        }
    }

    public static int toGlType(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
                return GL20.GL_UNSIGNED_BYTE;
            case 5:
                return GL20.GL_UNSIGNED_SHORT_5_6_5;
            case 6:
                return GL20.GL_UNSIGNED_SHORT_4_4_4_4;
            default:
                throw new m(a.h(i2, "unknown format: "));
        }
    }

    public void clear(int i2) {
        clear(this.basePtr, i2);
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        free(this.basePtr);
    }

    public void drawCircle(int i2, int i3, int i4, int i5) {
        drawCircle(this.basePtr, i2, i3, i4, i5);
    }

    public void drawLine(int i2, int i3, int i4, int i5, int i6) {
        drawLine(this.basePtr, i2, i3, i4, i5, i6);
    }

    public void drawPixmap(Gdx2DPixmap gdx2DPixmap, int i2, int i3, int i4, int i5, int i6, int i7) {
        drawPixmap(gdx2DPixmap.basePtr, this.basePtr, i2, i3, i6, i7, i4, i5, i6, i7);
    }

    public void drawRect(int i2, int i3, int i4, int i5, int i6) {
        drawRect(this.basePtr, i2, i3, i4, i5, i6);
    }

    public void fillCircle(int i2, int i3, int i4, int i5) {
        fillCircle(this.basePtr, i2, i3, i4, i5);
    }

    public void fillRect(int i2, int i3, int i4, int i5, int i6) {
        fillRect(this.basePtr, i2, i3, i4, i5, i6);
    }

    public void fillTriangle(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        fillTriangle(this.basePtr, i2, i3, i4, i5, i6, i7, i8);
    }

    public int getFormat() {
        return this.format;
    }

    public String getFormatString() {
        return getFormatString(this.format);
    }

    public int getGLFormat() {
        return getGLInternalFormat();
    }

    public int getGLInternalFormat() {
        return toGlFormat(this.format);
    }

    public int getGLType() {
        return toGlType(this.format);
    }

    public int getHeight() {
        return this.height;
    }

    public int getPixel(int i2, int i3) {
        return getPixel(this.basePtr, i2, i3);
    }

    public ByteBuffer getPixels() {
        return this.pixelPtr;
    }

    public int getWidth() {
        return this.width;
    }

    public void setBlend(int i2) {
        setBlend(this.basePtr, i2);
    }

    public void setPixel(int i2, int i3, int i4) {
        setPixel(this.basePtr, i2, i3, i4);
    }

    public void setScale(int i2) {
        setScale(this.basePtr, i2);
    }

    private static String getFormatString(int i2) {
        switch (i2) {
            case 1:
                return "alpha";
            case 2:
                return "luminance alpha";
            case 3:
                return "rgb888";
            case 4:
                return "rgba8888";
            case 5:
                return "rgb565";
            case 6:
                return "rgba4444";
            default:
                return "unknown";
        }
    }

    public static Gdx2DPixmap newPixmap(int i2, int i3, int i4) {
        try {
            return new Gdx2DPixmap(i2, i3, i4);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public void drawPixmap(Gdx2DPixmap gdx2DPixmap, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        drawPixmap(gdx2DPixmap.basePtr, this.basePtr, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public Gdx2DPixmap(InputStream inputStream, int i2) throws IOException {
        this.nativeData = new long[4];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(GL20.GL_STENCIL_BUFFER_BIT);
        byte[] bArr = new byte[GL20.GL_STENCIL_BUFFER_BIT];
        while (true) {
            int i3 = inputStream.read(bArr);
            if (i3 == -1) {
                break;
            } else {
                byteArrayOutputStream.write(bArr, 0, i3);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ByteBuffer byteBufferLoad = load(this.nativeData, byteArray, 0, byteArray.length);
        this.pixelPtr = byteBufferLoad;
        if (byteBufferLoad != null) {
            long[] jArr = this.nativeData;
            this.basePtr = jArr[0];
            this.width = (int) jArr[1];
            this.height = (int) jArr[2];
            int i4 = (int) jArr[3];
            this.format = i4;
            if (i2 == 0 || i2 == i4) {
                return;
            }
            convert(i2);
            return;
        }
        throw new IOException("Error loading pixmap: " + getFailureReason());
    }

    public Gdx2DPixmap(int i2, int i3, int i4) {
        long[] jArr = new long[4];
        this.nativeData = jArr;
        ByteBuffer byteBufferNewPixmap = newPixmap(jArr, i2, i3, i4);
        this.pixelPtr = byteBufferNewPixmap;
        if (byteBufferNewPixmap != null) {
            long[] jArr2 = this.nativeData;
            this.basePtr = jArr2[0];
            this.width = (int) jArr2[1];
            this.height = (int) jArr2[2];
            this.format = (int) jArr2[3];
            return;
        }
        throw new m("Unable to allocate memory for pixmap: " + i2 + "x" + i3 + ", " + getFormatString(i4));
    }

    public Gdx2DPixmap(ByteBuffer byteBuffer, long[] jArr) {
        this.nativeData = new long[4];
        this.pixelPtr = byteBuffer;
        this.basePtr = jArr[0];
        this.width = (int) jArr[1];
        this.height = (int) jArr[2];
        this.format = (int) jArr[3];
    }
}
