package com.badlogic.gdx.graphics;

import a.a;
import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Color {
    public static final Color BLACK;
    public static final Color BLUE;
    public static final Color BROWN;
    public static final Color CHARTREUSE;
    public static final Color CLEAR;
    public static final Color CORAL;
    public static final Color CYAN;
    public static final Color DARK_GRAY;
    public static final Color FIREBRICK;
    public static final Color FOREST;
    public static final Color GOLD;
    public static final Color GOLDENROD;
    public static final Color GRAY;
    public static final Color GREEN;
    public static final Color LIGHT_GRAY;
    public static final Color LIME;
    public static final Color MAGENTA;
    public static final Color MAROON;
    public static final Color NAVY;
    public static final Color OLIVE;
    public static final Color ORANGE;
    public static final Color PINK;
    public static final Color PURPLE;
    public static final Color RED;
    public static final Color ROYAL;
    public static final Color SALMON;
    public static final Color SCARLET;
    public static final Color SKY;
    public static final Color SLATE;
    public static final Color TAN;
    public static final Color TEAL;
    public static final Color VIOLET;
    public static final Color WHITE;
    public static final float WHITE_FLOAT_BITS;
    public static final Color YELLOW;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1677a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f1678b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f1679g;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f1680r;

    static {
        Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        WHITE = color;
        LIGHT_GRAY = new Color(-1077952513);
        GRAY = new Color(2139062271);
        DARK_GRAY = new Color(1061109759);
        BLACK = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        WHITE_FLOAT_BITS = color.toFloatBits();
        CLEAR = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        BLUE = new Color(0.0f, 0.0f, 1.0f, 1.0f);
        NAVY = new Color(0.0f, 0.0f, 0.5f, 1.0f);
        ROYAL = new Color(1097458175);
        SLATE = new Color(1887473919);
        SKY = new Color(-2016482305);
        CYAN = new Color(0.0f, 1.0f, 1.0f, 1.0f);
        TEAL = new Color(0.0f, 0.5f, 0.5f, 1.0f);
        GREEN = new Color(16711935);
        CHARTREUSE = new Color(2147418367);
        LIME = new Color(852308735);
        FOREST = new Color(579543807);
        OLIVE = new Color(1804477439);
        YELLOW = new Color(-65281);
        GOLD = new Color(-2686721);
        GOLDENROD = new Color(-626712321);
        ORANGE = new Color(-5963521);
        BROWN = new Color(-1958407169);
        TAN = new Color(-759919361);
        FIREBRICK = new Color(-1306385665);
        RED = new Color(-16776961);
        SCARLET = new Color(-13361921);
        CORAL = new Color(-8433409);
        SALMON = new Color(-92245249);
        PINK = new Color(-9849601);
        MAGENTA = new Color(1.0f, 0.0f, 1.0f, 1.0f);
        PURPLE = new Color(-1608453889);
        VIOLET = new Color(-293409025);
        MAROON = new Color(-1339006721);
    }

    public Color() {
    }

    public static void abgr8888ToColor(Color color, float f2) {
        int iG = l.g(f2);
        color.f1677a = (((-16777216) & iG) >>> 24) / 255.0f;
        color.f1678b = ((16711680 & iG) >>> 16) / 255.0f;
        color.f1679g = ((65280 & iG) >>> 8) / 255.0f;
        color.f1680r = (iG & 255) / 255.0f;
    }

    public static int alpha(float f2) {
        return (int) (f2 * 255.0f);
    }

    public static int argb8888(float f2, float f3, float f4, float f5) {
        return (((int) (f2 * 255.0f)) << 24) | (((int) (f3 * 255.0f)) << 16) | (((int) (f4 * 255.0f)) << 8) | ((int) (f5 * 255.0f));
    }

    public static void argb8888ToColor(Color color, int i2) {
        color.f1677a = (((-16777216) & i2) >>> 24) / 255.0f;
        color.f1680r = ((16711680 & i2) >>> 16) / 255.0f;
        color.f1679g = ((65280 & i2) >>> 8) / 255.0f;
        color.f1678b = (i2 & 255) / 255.0f;
    }

    public static int luminanceAlpha(float f2, float f3) {
        return (((int) (f2 * 255.0f)) << 8) | ((int) (f3 * 255.0f));
    }

    public static int rgb565(float f2, float f3, float f4) {
        return (((int) (f2 * 31.0f)) << 11) | (((int) (f3 * 63.0f)) << 5) | ((int) (f4 * 31.0f));
    }

    public static void rgb565ToColor(Color color, int i2) {
        color.f1680r = ((63488 & i2) >>> 11) / 31.0f;
        color.f1679g = ((i2 & 2016) >>> 5) / 63.0f;
        color.f1678b = (i2 & 31) / 31.0f;
    }

    public static int rgb888(float f2, float f3, float f4) {
        return (((int) (f2 * 255.0f)) << 16) | (((int) (f3 * 255.0f)) << 8) | ((int) (f4 * 255.0f));
    }

    public static void rgb888ToColor(Color color, int i2) {
        color.f1680r = ((16711680 & i2) >>> 16) / 255.0f;
        color.f1679g = ((65280 & i2) >>> 8) / 255.0f;
        color.f1678b = (i2 & 255) / 255.0f;
    }

    public static int rgba4444(float f2, float f3, float f4, float f5) {
        return (((int) (f2 * 15.0f)) << 12) | (((int) (f3 * 15.0f)) << 8) | (((int) (f4 * 15.0f)) << 4) | ((int) (f5 * 15.0f));
    }

    public static void rgba4444ToColor(Color color, int i2) {
        color.f1680r = ((61440 & i2) >>> 12) / 15.0f;
        color.f1679g = ((i2 & 3840) >>> 8) / 15.0f;
        color.f1678b = ((i2 & 240) >>> 4) / 15.0f;
        color.f1677a = (i2 & 15) / 15.0f;
    }

    public static int rgba8888(float f2, float f3, float f4, float f5) {
        return (((int) (f2 * 255.0f)) << 24) | (((int) (f3 * 255.0f)) << 16) | (((int) (f4 * 255.0f)) << 8) | ((int) (f5 * 255.0f));
    }

    public static void rgba8888ToColor(Color color, int i2) {
        color.f1680r = (((-16777216) & i2) >>> 24) / 255.0f;
        color.f1679g = ((16711680 & i2) >>> 16) / 255.0f;
        color.f1678b = ((65280 & i2) >>> 8) / 255.0f;
        color.f1677a = (i2 & 255) / 255.0f;
    }

    public static int toIntBits(int i2, int i3, int i4, int i5) {
        return i2 | (i3 << 8) | (i4 << 16) | (i5 << 24);
    }

    public static Color valueOf(String str) {
        return valueOf(str, new Color());
    }

    public Color add(Color color) {
        this.f1680r += color.f1680r;
        this.f1679g += color.f1679g;
        this.f1678b += color.f1678b;
        this.f1677a += color.f1677a;
        return clamp();
    }

    public Color clamp() {
        float f2 = this.f1680r;
        if (f2 < 0.0f) {
            this.f1680r = 0.0f;
        } else if (f2 > 1.0f) {
            this.f1680r = 1.0f;
        }
        float f3 = this.f1679g;
        if (f3 < 0.0f) {
            this.f1679g = 0.0f;
        } else if (f3 > 1.0f) {
            this.f1679g = 1.0f;
        }
        float f4 = this.f1678b;
        if (f4 < 0.0f) {
            this.f1678b = 0.0f;
        } else if (f4 > 1.0f) {
            this.f1678b = 1.0f;
        }
        float f5 = this.f1677a;
        if (f5 < 0.0f) {
            this.f1677a = 0.0f;
        } else if (f5 > 1.0f) {
            this.f1677a = 1.0f;
        }
        return this;
    }

    public Color cpy() {
        return new Color(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && toIntBits() == ((Color) obj).toIntBits();
    }

    public Color fromHsv(float f2, float f3, float f4) {
        float f5 = ((f2 / 60.0f) + 6.0f) % 6.0f;
        int i2 = (int) f5;
        float f6 = f5 - i2;
        float f7 = (1.0f - f3) * f4;
        float f8 = (1.0f - (f3 * f6)) * f4;
        float f9 = (1.0f - ((1.0f - f6) * f3)) * f4;
        if (i2 == 0) {
            this.f1680r = f4;
            this.f1679g = f9;
            this.f1678b = f7;
        } else if (i2 == 1) {
            this.f1680r = f8;
            this.f1679g = f4;
            this.f1678b = f7;
        } else if (i2 == 2) {
            this.f1680r = f7;
            this.f1679g = f4;
            this.f1678b = f9;
        } else if (i2 == 3) {
            this.f1680r = f7;
            this.f1679g = f8;
            this.f1678b = f4;
        } else if (i2 != 4) {
            this.f1680r = f4;
            this.f1679g = f7;
            this.f1678b = f8;
        } else {
            this.f1680r = f9;
            this.f1679g = f7;
            this.f1678b = f4;
        }
        return clamp();
    }

    public int hashCode() {
        float f2 = this.f1680r;
        int iFloatToIntBits = (f2 != 0.0f ? Float.floatToIntBits(f2) : 0) * 31;
        float f3 = this.f1679g;
        int iFloatToIntBits2 = (iFloatToIntBits + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
        float f4 = this.f1678b;
        int iFloatToIntBits3 = (iFloatToIntBits2 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0)) * 31;
        float f5 = this.f1677a;
        return iFloatToIntBits3 + (f5 != 0.0f ? Float.floatToIntBits(f5) : 0);
    }

    public Color lerp(Color color, float f2) {
        float f3 = this.f1680r;
        this.f1680r = a.C(color.f1680r, f3, f2, f3);
        float f4 = this.f1679g;
        this.f1679g = a.C(color.f1679g, f4, f2, f4);
        float f5 = this.f1678b;
        this.f1678b = a.C(color.f1678b, f5, f2, f5);
        float f6 = this.f1677a;
        this.f1677a = a.C(color.f1677a, f6, f2, f6);
        return clamp();
    }

    public Color mul(Color color) {
        this.f1680r *= color.f1680r;
        this.f1679g *= color.f1679g;
        this.f1678b *= color.f1678b;
        this.f1677a *= color.f1677a;
        return clamp();
    }

    public Color premultiplyAlpha() {
        float f2 = this.f1680r;
        float f3 = this.f1677a;
        this.f1680r = f2 * f3;
        this.f1679g *= f3;
        this.f1678b *= f3;
        return this;
    }

    public Color set(Color color) {
        this.f1680r = color.f1680r;
        this.f1679g = color.f1679g;
        this.f1678b = color.f1678b;
        this.f1677a = color.f1677a;
        return this;
    }

    public Color sub(Color color) {
        this.f1680r -= color.f1680r;
        this.f1679g -= color.f1679g;
        this.f1678b -= color.f1678b;
        this.f1677a -= color.f1677a;
        return clamp();
    }

    public float toFloatBits() {
        return l.h((((int) (this.f1677a * 255.0f)) << 24) | (((int) (this.f1678b * 255.0f)) << 16) | (((int) (this.f1679g * 255.0f)) << 8) | ((int) (this.f1680r * 255.0f)));
    }

    public float[] toHsv(float[] fArr) {
        float fMax = Math.max(Math.max(this.f1680r, this.f1679g), this.f1678b);
        float fMin = Math.min(Math.min(this.f1680r, this.f1679g), this.f1678b);
        float f2 = fMax - fMin;
        if (f2 == 0.0f) {
            fArr[0] = 0.0f;
        } else {
            float f3 = this.f1680r;
            if (fMax == f3) {
                fArr[0] = ((((this.f1679g - this.f1678b) * 60.0f) / f2) + 360.0f) % 360.0f;
            } else {
                float f4 = this.f1679g;
                if (fMax == f4) {
                    fArr[0] = (((this.f1678b - f3) * 60.0f) / f2) + 120.0f;
                } else {
                    fArr[0] = (((f3 - f4) * 60.0f) / f2) + 240.0f;
                }
            }
        }
        if (fMax > 0.0f) {
            fArr[1] = 1.0f - (fMin / fMax);
        } else {
            fArr[1] = 0.0f;
        }
        fArr[2] = fMax;
        return fArr;
    }

    public String toString() {
        String hexString = Integer.toHexString((((int) (this.f1680r * 255.0f)) << 24) | (((int) (this.f1679g * 255.0f)) << 16) | (((int) (this.f1678b * 255.0f)) << 8) | ((int) (this.f1677a * 255.0f)));
        while (hexString.length() < 8) {
            hexString = "0".concat(hexString);
        }
        return hexString;
    }

    public Color(int i2) {
        rgba8888ToColor(this, i2);
    }

    public static int argb8888(Color color) {
        return ((int) (color.f1678b * 255.0f)) | (((int) (color.f1677a * 255.0f)) << 24) | (((int) (color.f1680r * 255.0f)) << 16) | (((int) (color.f1679g * 255.0f)) << 8);
    }

    public static int rgb565(Color color) {
        return ((int) (color.f1678b * 31.0f)) | (((int) (color.f1680r * 31.0f)) << 11) | (((int) (color.f1679g * 63.0f)) << 5);
    }

    public static int rgb888(Color color) {
        return ((int) (color.f1678b * 255.0f)) | (((int) (color.f1680r * 255.0f)) << 16) | (((int) (color.f1679g * 255.0f)) << 8);
    }

    public static int rgba4444(Color color) {
        return ((int) (color.f1677a * 15.0f)) | (((int) (color.f1680r * 15.0f)) << 12) | (((int) (color.f1679g * 15.0f)) << 8) | (((int) (color.f1678b * 15.0f)) << 4);
    }

    public static int rgba8888(Color color) {
        return ((int) (color.f1677a * 255.0f)) | (((int) (color.f1680r * 255.0f)) << 24) | (((int) (color.f1679g * 255.0f)) << 16) | (((int) (color.f1678b * 255.0f)) << 8);
    }

    public static Color valueOf(String str, Color color) {
        if (str.charAt(0) == '#') {
            str = str.substring(1);
        }
        color.f1680r = Integer.parseInt(str.substring(0, 2), 16) / 255.0f;
        color.f1679g = Integer.parseInt(str.substring(2, 4), 16) / 255.0f;
        color.f1678b = Integer.parseInt(str.substring(4, 6), 16) / 255.0f;
        color.f1677a = str.length() != 8 ? 1.0f : Integer.parseInt(str.substring(6, 8), 16) / 255.0f;
        return color;
    }

    public int toIntBits() {
        return (((int) (this.f1677a * 255.0f)) << 24) | (((int) (this.f1678b * 255.0f)) << 16) | (((int) (this.f1679g * 255.0f)) << 8) | ((int) (this.f1680r * 255.0f));
    }

    public static float toFloatBits(int i2, int i3, int i4, int i5) {
        return l.h(i2 | (i3 << 8) | (i4 << 16) | (i5 << 24));
    }

    public Color(float f2, float f3, float f4, float f5) {
        this.f1680r = f2;
        this.f1679g = f3;
        this.f1678b = f4;
        this.f1677a = f5;
        clamp();
    }

    public static float toFloatBits(float f2, float f3, float f4, float f5) {
        return l.h(((int) (f2 * 255.0f)) | (((int) (f3 * 255.0f)) << 8) | (((int) (f4 * 255.0f)) << 16) | (((int) (f5 * 255.0f)) << 24));
    }

    public Color set(float f2, float f3, float f4, float f5) {
        this.f1680r = f2;
        this.f1679g = f3;
        this.f1678b = f4;
        this.f1677a = f5;
        return clamp();
    }

    public Color add(float f2, float f3, float f4, float f5) {
        this.f1680r += f2;
        this.f1679g += f3;
        this.f1678b += f4;
        this.f1677a += f5;
        return clamp();
    }

    public Color lerp(float f2, float f3, float f4, float f5, float f6) {
        float f7 = this.f1680r;
        this.f1680r = a.C(f2, f7, f6, f7);
        float f8 = this.f1679g;
        this.f1679g = a.C(f3, f8, f6, f8);
        float f9 = this.f1678b;
        this.f1678b = a.C(f4, f9, f6, f9);
        float f10 = this.f1677a;
        this.f1677a = a.C(f5, f10, f6, f10);
        return clamp();
    }

    public Color mul(float f2) {
        this.f1680r *= f2;
        this.f1679g *= f2;
        this.f1678b *= f2;
        this.f1677a *= f2;
        return clamp();
    }

    public Color sub(float f2, float f3, float f4, float f5) {
        this.f1680r -= f2;
        this.f1679g -= f3;
        this.f1678b -= f4;
        this.f1677a -= f5;
        return clamp();
    }

    public Color(Color color) {
        set(color);
    }

    public Color set(int i2) {
        rgba8888ToColor(this, i2);
        return this;
    }

    public Color mul(float f2, float f3, float f4, float f5) {
        this.f1680r *= f2;
        this.f1679g *= f3;
        this.f1678b *= f4;
        this.f1677a *= f5;
        return clamp();
    }

    public Color fromHsv(float[] fArr) {
        return fromHsv(fArr[0], fArr[1], fArr[2]);
    }
}
