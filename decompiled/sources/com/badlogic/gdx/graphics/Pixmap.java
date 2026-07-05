package com.badlogic.gdx.graphics;

import a.a;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.l;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Pixmap implements i {
    private boolean disposed;
    final Gdx2DPixmap pixmap;
    private Blending blending = Blending.SourceOver;
    private Filter filter = Filter.BiLinear;
    int color = 0;

    /* JADX INFO: renamed from: com.badlogic.gdx.graphics.Pixmap$1, reason: invalid class name */
    static class AnonymousClass1 implements l.c {
        final /* synthetic */ DownloadPixmapResponseListener val$responseListener;

        AnonymousClass1(DownloadPixmapResponseListener downloadPixmapResponseListener) {
            this.val$responseListener = downloadPixmapResponseListener;
        }

        @Override // com.badlogic.gdx.l.c
        public void cancelled() {
        }

        @Override // com.badlogic.gdx.l.c
        public void failed(Throwable th) {
            this.val$responseListener.downloadFailed(th);
        }

        @Override // com.badlogic.gdx.l.c
        public void handleHttpResponse(l.b bVar) {
            final byte[] result = bVar.getResult();
            Gdx.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.graphics.Pixmap.1.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        byte[] bArr = result;
                        AnonymousClass1.this.val$responseListener.downloadComplete(new Pixmap(bArr, 0, bArr.length));
                    } catch (Throwable th) {
                        AnonymousClass1.this.failed(th);
                    }
                }
            });
        }
    }

    public enum Blending {
        None,
        SourceOver
    }

    public interface DownloadPixmapResponseListener {
        void downloadComplete(Pixmap pixmap);

        void downloadFailed(Throwable th);
    }

    public enum Filter {
        NearestNeighbour,
        BiLinear
    }

    public enum Format {
        Alpha,
        Intensity,
        LuminanceAlpha,
        RGB565,
        RGBA4444,
        RGB888,
        RGBA8888;

        public static Format fromGdx2DPixmapFormat(int i2) {
            if (i2 == 1) {
                return Alpha;
            }
            if (i2 == 2) {
                return LuminanceAlpha;
            }
            if (i2 == 5) {
                return RGB565;
            }
            if (i2 == 6) {
                return RGBA4444;
            }
            if (i2 == 3) {
                return RGB888;
            }
            if (i2 == 4) {
                return RGBA8888;
            }
            throw new m(a.h(i2, "Unknown Gdx2DPixmap Format: "));
        }

        public static int toGdx2DPixmapFormat(Format format) {
            if (format == Alpha || format == Intensity) {
                return 1;
            }
            if (format == LuminanceAlpha) {
                return 2;
            }
            if (format == RGB565) {
                return 5;
            }
            if (format == RGBA4444) {
                return 6;
            }
            if (format == RGB888) {
                return 3;
            }
            if (format == RGBA8888) {
                return 4;
            }
            throw new m("Unknown Format: " + format);
        }

        public static int toGlFormat(Format format) {
            return Gdx2DPixmap.toGlFormat(toGdx2DPixmapFormat(format));
        }

        public static int toGlType(Format format) {
            return Gdx2DPixmap.toGlType(toGdx2DPixmapFormat(format));
        }
    }

    public Pixmap(int i2, int i3, Format format) {
        this.pixmap = new Gdx2DPixmap(i2, i3, Format.toGdx2DPixmapFormat(format));
        setColor(0.0f, 0.0f, 0.0f, 0.0f);
        fill();
    }

    public static void downloadFromUrl(String str, DownloadPixmapResponseListener downloadPixmapResponseListener) {
        l.a aVar = new l.a("GET");
        aVar.f(str);
        Gdx.f1569net.sendHttpRequest(aVar, new AnonymousClass1(downloadPixmapResponseListener));
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        if (this.disposed) {
            throw new m("Pixmap already disposed!");
        }
        this.pixmap.dispose();
        this.disposed = true;
    }

    public void drawCircle(int i2, int i3, int i4) {
        this.pixmap.drawCircle(i2, i3, i4, this.color);
    }

    public void drawLine(int i2, int i3, int i4, int i5) {
        this.pixmap.drawLine(i2, i3, i4, i5, this.color);
    }

    public void drawPixel(int i2, int i3) {
        this.pixmap.setPixel(i2, i3, this.color);
    }

    public void drawPixmap(Pixmap pixmap, int i2, int i3) {
        drawPixmap(pixmap, i2, i3, 0, 0, pixmap.getWidth(), pixmap.getHeight());
    }

    public void drawRectangle(int i2, int i3, int i4, int i5) {
        this.pixmap.drawRect(i2, i3, i4, i5, this.color);
    }

    public void fill() {
        this.pixmap.clear(this.color);
    }

    public void fillCircle(int i2, int i3, int i4) {
        this.pixmap.fillCircle(i2, i3, i4, this.color);
    }

    public void fillRectangle(int i2, int i3, int i4, int i5) {
        this.pixmap.fillRect(i2, i3, i4, i5, this.color);
    }

    public void fillTriangle(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.pixmap.fillTriangle(i2, i3, i4, i5, i6, i7, this.color);
    }

    public Blending getBlending() {
        return this.blending;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public Format getFormat() {
        return Format.fromGdx2DPixmapFormat(this.pixmap.getFormat());
    }

    public int getGLFormat() {
        return this.pixmap.getGLFormat();
    }

    public int getGLInternalFormat() {
        return this.pixmap.getGLInternalFormat();
    }

    public int getGLType() {
        return this.pixmap.getGLType();
    }

    public int getHeight() {
        return this.pixmap.getHeight();
    }

    public int getPixel(int i2, int i3) {
        return this.pixmap.getPixel(i2, i3);
    }

    public ByteBuffer getPixels() {
        if (this.disposed) {
            throw new m("Pixmap already disposed");
        }
        return this.pixmap.getPixels();
    }

    public int getWidth() {
        return this.pixmap.getWidth();
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public void setBlending(Blending blending) {
        this.blending = blending;
        this.pixmap.setBlend(blending == Blending.None ? 0 : 1);
    }

    public void setColor(int i2) {
        this.color = i2;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
        this.pixmap.setScale(filter == Filter.NearestNeighbour ? 0 : 1);
    }

    public void drawPixel(int i2, int i3, int i4) {
        this.pixmap.setPixel(i2, i3, i4);
    }

    public void drawPixmap(Pixmap pixmap, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.pixmap.drawPixmap(pixmap.pixmap, i4, i5, i2, i3, i6, i7);
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color = Color.rgba8888(f2, f3, f4, f5);
    }

    public void drawPixmap(Pixmap pixmap, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.pixmap.drawPixmap(pixmap.pixmap, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public void setColor(Color color) {
        this.color = Color.rgba8888(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
    }

    public Pixmap(byte[] bArr, int i2, int i3) {
        try {
            this.pixmap = new Gdx2DPixmap(bArr, i2, i3, 0);
        } catch (IOException e2) {
            throw new m("Couldn't load pixmap from image data", (Throwable) e2);
        }
    }

    public Pixmap(com.badlogic.gdx.files.a aVar) {
        try {
            byte[] bytes = aVar.readBytes();
            this.pixmap = new Gdx2DPixmap(bytes, 0, bytes.length, 0);
        } catch (Exception e2) {
            throw new m(a.i(aVar, "Couldn't load file: "), (Throwable) e2);
        }
    }

    public Pixmap(Gdx2DPixmap gdx2DPixmap) {
        this.pixmap = gdx2DPixmap;
    }
}
