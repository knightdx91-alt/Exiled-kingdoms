package com.badlogic.gdx;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.GLVersion;

/* JADX INFO: compiled from: Graphics.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface f {

    /* JADX INFO: compiled from: Graphics.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1650a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1651b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1652c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f1653d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1654e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f1655f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f1656g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f1657h;

        public a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z2) {
            this.f1650a = i2;
            this.f1651b = i3;
            this.f1652c = i4;
            this.f1653d = i5;
            this.f1654e = i6;
            this.f1655f = i7;
            this.f1656g = i8;
            this.f1657h = z2;
        }

        public final String toString() {
            return "r: " + this.f1650a + ", g: " + this.f1651b + ", b: " + this.f1652c + ", a: " + this.f1653d + ", depth: " + this.f1654e + ", stencil: " + this.f1655f + ", num samples: " + this.f1656g + ", coverage sampling: " + this.f1657h;
        }
    }

    /* JADX INFO: compiled from: Graphics.java */
    public static class b {
        public final int bitsPerPixel;
        public final int height;
        public final int refreshRate;
        public final int width;

        protected b(int i2, int i3, int i4, int i5) {
            this.width = i2;
            this.height = i3;
            this.refreshRate = i4;
            this.bitsPerPixel = i5;
        }

        public String toString() {
            return this.width + "x" + this.height + ", bpp: " + this.bitsPerPixel + ", hz: " + this.refreshRate;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Graphics.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f1658a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f1659b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final /* synthetic */ c[] f1660c;

        static {
            c cVar = new c("AndroidGL", 0);
            f1658a = cVar;
            c cVar2 = new c("LWJGL", 1);
            c cVar3 = new c("WebGL", 2);
            c cVar4 = new c("iOSGL", 3);
            c cVar5 = new c("JGLFW", 4);
            c cVar6 = new c("Mock", 5);
            c cVar7 = new c("LWJGL3", 6);
            f1659b = cVar7;
            f1660c = new c[]{cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7};
        }

        private c() {
            throw null;
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        public static c[] values() {
            return (c[]) f1660c.clone();
        }
    }

    /* JADX INFO: compiled from: Graphics.java */
    public static class d {
        public final String name;
        public final int virtualX;
        public final int virtualY;

        protected d(int i2, int i3, String str) {
            this.virtualX = i2;
            this.virtualY = i3;
            this.name = str;
        }
    }

    int getBackBufferHeight();

    int getBackBufferWidth();

    float getDeltaTime();

    b getDisplayMode();

    int getFramesPerSecond();

    GL20 getGL20();

    GL30 getGL30();

    GLVersion getGLVersion();

    int getHeight();

    float getPpiX();

    float getPpiY();

    c getType();

    int getWidth();

    boolean isContinuousRendering();

    boolean isFullscreen();

    boolean isGL30Available();

    Cursor newCursor(Pixmap pixmap, int i2, int i3);

    void requestRendering();

    void setCursor(Cursor cursor);

    void setGL20(GL20 gl20);

    void setGL30(GL30 gl30);

    boolean supportsExtension(String str);
}
