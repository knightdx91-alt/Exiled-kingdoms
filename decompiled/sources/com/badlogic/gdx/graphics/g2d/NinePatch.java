package com.badlogic.gdx.graphics.g2d;

import a.a;
import a0.j;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class NinePatch {
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_RIGHT = 8;
    public static final int MIDDLE_CENTER = 4;
    public static final int MIDDLE_LEFT = 3;
    public static final int MIDDLE_RIGHT = 5;
    public static final int TOP_CENTER = 1;
    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 2;
    private static final Color tmpDrawColor = new Color();
    private int bottomCenter;
    private float bottomHeight;
    private int bottomLeft;
    private int bottomRight;
    private final Color color;
    private int idx;
    private float leftWidth;
    private int middleCenter;
    private float middleHeight;
    private int middleLeft;
    private int middleRight;
    private float middleWidth;
    private float padBottom;
    private float padLeft;
    private float padRight;
    private float padTop;
    private float rightWidth;
    private Texture texture;
    private int topCenter;
    private float topHeight;
    private int topLeft;
    private int topRight;
    private float[] vertices;

    public NinePatch(Texture texture, int i2, int i3, int i4, int i5) {
        this(new TextureRegion(texture), i2, i3, i4, i5);
    }

    private int add(TextureRegion textureRegion, boolean z2, boolean z3) {
        Texture texture = this.texture;
        if (texture == null) {
            this.texture = textureRegion.getTexture();
        } else if (texture != textureRegion.getTexture()) {
            throw new IllegalArgumentException("All regions must be from the same texture.");
        }
        float f2 = textureRegion.f1698u;
        float f3 = textureRegion.v2;
        float f4 = textureRegion.u2;
        float f5 = textureRegion.f1699v;
        Texture.TextureFilter magFilter = this.texture.getMagFilter();
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
        if (magFilter == textureFilter || this.texture.getMinFilter() == textureFilter) {
            if (z2) {
                float width = 0.5f / this.texture.getWidth();
                f2 += width;
                f4 -= width;
            }
            if (z3) {
                float height = 0.5f / this.texture.getHeight();
                f3 -= height;
                f5 += height;
            }
        }
        float[] fArr = this.vertices;
        int i2 = this.idx;
        fArr[i2 + 3] = f2;
        fArr[i2 + 4] = f3;
        fArr[i2 + 8] = f2;
        fArr[i2 + 9] = f5;
        fArr[i2 + 13] = f4;
        fArr[i2 + 14] = f5;
        fArr[i2 + 18] = f4;
        fArr[i2 + 19] = f3;
        this.idx = i2 + 20;
        return i2;
    }

    private void load(TextureRegion[] textureRegionArr) {
        TextureRegion textureRegion = textureRegionArr[6];
        if (textureRegion != null) {
            this.bottomLeft = add(textureRegion, false, false);
            this.leftWidth = textureRegionArr[6].getRegionWidth();
            this.bottomHeight = textureRegionArr[6].getRegionHeight();
        } else {
            this.bottomLeft = -1;
        }
        TextureRegion textureRegion2 = textureRegionArr[7];
        if (textureRegion2 != null) {
            this.bottomCenter = add(textureRegion2, (textureRegionArr[6] == null && textureRegionArr[8] == null) ? false : true, false);
            this.middleWidth = Math.max(this.middleWidth, textureRegionArr[7].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, textureRegionArr[7].getRegionHeight());
        } else {
            this.bottomCenter = -1;
        }
        TextureRegion textureRegion3 = textureRegionArr[8];
        if (textureRegion3 != null) {
            this.bottomRight = add(textureRegion3, false, false);
            this.rightWidth = Math.max(this.rightWidth, textureRegionArr[8].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, textureRegionArr[8].getRegionHeight());
        } else {
            this.bottomRight = -1;
        }
        TextureRegion textureRegion4 = textureRegionArr[3];
        if (textureRegion4 != null) {
            this.middleLeft = add(textureRegion4, false, (textureRegionArr[0] == null && textureRegionArr[6] == null) ? false : true);
            this.leftWidth = Math.max(this.leftWidth, textureRegionArr[3].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, textureRegionArr[3].getRegionHeight());
        } else {
            this.middleLeft = -1;
        }
        TextureRegion textureRegion5 = textureRegionArr[4];
        if (textureRegion5 != null) {
            this.middleCenter = add(textureRegion5, (textureRegionArr[3] == null && textureRegionArr[5] == null) ? false : true, (textureRegionArr[1] == null && textureRegionArr[7] == null) ? false : true);
            this.middleWidth = Math.max(this.middleWidth, textureRegionArr[4].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, textureRegionArr[4].getRegionHeight());
        } else {
            this.middleCenter = -1;
        }
        TextureRegion textureRegion6 = textureRegionArr[5];
        if (textureRegion6 != null) {
            this.middleRight = add(textureRegion6, false, (textureRegionArr[2] == null && textureRegionArr[8] == null) ? false : true);
            this.rightWidth = Math.max(this.rightWidth, textureRegionArr[5].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, textureRegionArr[5].getRegionHeight());
        } else {
            this.middleRight = -1;
        }
        TextureRegion textureRegion7 = textureRegionArr[0];
        if (textureRegion7 != null) {
            this.topLeft = add(textureRegion7, false, false);
            this.leftWidth = Math.max(this.leftWidth, textureRegionArr[0].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, textureRegionArr[0].getRegionHeight());
        } else {
            this.topLeft = -1;
        }
        TextureRegion textureRegion8 = textureRegionArr[1];
        if (textureRegion8 != null) {
            this.topCenter = add(textureRegion8, (textureRegionArr[0] == null && textureRegionArr[2] == null) ? false : true, false);
            this.middleWidth = Math.max(this.middleWidth, textureRegionArr[1].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, textureRegionArr[1].getRegionHeight());
        } else {
            this.topCenter = -1;
        }
        TextureRegion textureRegion9 = textureRegionArr[2];
        if (textureRegion9 != null) {
            this.topRight = add(textureRegion9, false, false);
            this.rightWidth = Math.max(this.rightWidth, textureRegionArr[2].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, textureRegionArr[2].getRegionHeight());
        } else {
            this.topRight = -1;
        }
        int i2 = this.idx;
        float[] fArr = this.vertices;
        if (i2 < fArr.length) {
            float[] fArr2 = new float[i2];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            this.vertices = fArr2;
        }
    }

    private void prepareVertices(Batch batch, float f2, float f3, float f4, float f5) {
        float f6 = this.leftWidth;
        float f7 = f2 + f6;
        float f8 = this.bottomHeight;
        float f9 = f3 + f8;
        float f10 = this.rightWidth;
        float f11 = (f4 - f10) - f6;
        float f12 = this.topHeight;
        float f13 = (f5 - f12) - f8;
        float f14 = (f2 + f4) - f10;
        float f15 = (f3 + f5) - f12;
        float floatBits = tmpDrawColor.set(this.color).mul(batch.getColor()).toFloatBits();
        int i2 = this.bottomLeft;
        if (i2 != -1) {
            set(i2, f2, f3, this.leftWidth, this.bottomHeight, floatBits);
        }
        int i3 = this.bottomCenter;
        if (i3 != -1) {
            set(i3, f7, f3, f11, this.bottomHeight, floatBits);
        }
        int i4 = this.bottomRight;
        if (i4 != -1) {
            set(i4, f14, f3, this.rightWidth, this.bottomHeight, floatBits);
        }
        int i5 = this.middleLeft;
        if (i5 != -1) {
            set(i5, f2, f9, this.leftWidth, f13, floatBits);
        }
        int i6 = this.middleCenter;
        if (i6 != -1) {
            set(i6, f7, f9, f11, f13, floatBits);
        }
        int i7 = this.middleRight;
        if (i7 != -1) {
            set(i7, f14, f9, this.rightWidth, f13, floatBits);
        }
        int i8 = this.topLeft;
        if (i8 != -1) {
            set(i8, f2, f15, this.leftWidth, this.topHeight, floatBits);
        }
        int i9 = this.topCenter;
        if (i9 != -1) {
            set(i9, f7, f15, f11, this.topHeight, floatBits);
        }
        int i10 = this.topRight;
        if (i10 != -1) {
            set(i10, f14, f15, this.rightWidth, this.topHeight, floatBits);
        }
    }

    private void set(int i2, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 + f2;
        float f8 = f5 + f3;
        float[] fArr = this.vertices;
        fArr[i2] = f2;
        fArr[i2 + 1] = f3;
        fArr[i2 + 2] = f6;
        fArr[i2 + 5] = f2;
        fArr[i2 + 6] = f8;
        fArr[i2 + 7] = f6;
        fArr[i2 + 10] = f7;
        fArr[i2 + 11] = f8;
        fArr[i2 + 12] = f6;
        fArr[i2 + 15] = f7;
        fArr[i2 + 16] = f3;
        fArr[i2 + 17] = f6;
    }

    public void draw(Batch batch, float f2, float f3, float f4, float f5) {
        prepareVertices(batch, f2, f3, f4, f5);
        batch.draw(this.texture, this.vertices, 0, this.idx);
    }

    public float getBottomHeight() {
        return this.bottomHeight;
    }

    public Color getColor() {
        return this.color;
    }

    public float getLeftWidth() {
        return this.leftWidth;
    }

    public float getMiddleHeight() {
        return this.middleHeight;
    }

    public float getMiddleWidth() {
        return this.middleWidth;
    }

    public float getPadBottom() {
        float f2 = this.padBottom;
        return f2 == -1.0f ? getBottomHeight() : f2;
    }

    public float getPadLeft() {
        float f2 = this.padLeft;
        return f2 == -1.0f ? getLeftWidth() : f2;
    }

    public float getPadRight() {
        float f2 = this.padRight;
        return f2 == -1.0f ? getRightWidth() : f2;
    }

    public float getPadTop() {
        float f2 = this.padTop;
        return f2 == -1.0f ? getTopHeight() : f2;
    }

    public float getRightWidth() {
        return this.rightWidth;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public float getTopHeight() {
        return this.topHeight;
    }

    public float getTotalHeight() {
        return this.topHeight + this.middleHeight + this.bottomHeight;
    }

    public float getTotalWidth() {
        return this.leftWidth + this.middleWidth + this.rightWidth;
    }

    public void scale(float f2, float f3) {
        this.leftWidth *= f2;
        this.rightWidth *= f2;
        this.topHeight *= f3;
        this.bottomHeight *= f3;
        this.middleWidth *= f2;
        this.middleHeight *= f3;
        float f4 = this.padLeft;
        if (f4 != -1.0f) {
            this.padLeft = f4 * f2;
        }
        float f5 = this.padRight;
        if (f5 != -1.0f) {
            this.padRight = f5 * f2;
        }
        float f6 = this.padTop;
        if (f6 != -1.0f) {
            this.padTop = f6 * f3;
        }
        float f7 = this.padBottom;
        if (f7 != -1.0f) {
            this.padBottom = f7 * f3;
        }
    }

    public void setBottomHeight(float f2) {
        this.bottomHeight = f2;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setLeftWidth(float f2) {
        this.leftWidth = f2;
    }

    public void setMiddleHeight(float f2) {
        this.middleHeight = f2;
    }

    public void setMiddleWidth(float f2) {
        this.middleWidth = f2;
    }

    public void setPadBottom(float f2) {
        this.padBottom = f2;
    }

    public void setPadLeft(float f2) {
        this.padLeft = f2;
    }

    public void setPadRight(float f2) {
        this.padRight = f2;
    }

    public void setPadTop(float f2) {
        this.padTop = f2;
    }

    public void setPadding(float f2, float f3, float f4, float f5) {
        this.padLeft = f2;
        this.padRight = f3;
        this.padTop = f4;
        this.padBottom = f5;
    }

    public void setRightWidth(float f2) {
        this.rightWidth = f2;
    }

    public void setTopHeight(float f2) {
        this.topHeight = f2;
    }

    public NinePatch(TextureRegion textureRegion, int i2, int i3, int i4, int i5) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        if (textureRegion == null) {
            throw new IllegalArgumentException("region cannot be null.");
        }
        int regionWidth = (textureRegion.getRegionWidth() - i2) - i3;
        int regionHeight = (textureRegion.getRegionHeight() - i4) - i5;
        TextureRegion[] textureRegionArr = new TextureRegion[9];
        if (i4 > 0) {
            if (i2 > 0) {
                textureRegionArr[0] = new TextureRegion(textureRegion, 0, 0, i2, i4);
            }
            if (regionWidth > 0) {
                textureRegionArr[1] = new TextureRegion(textureRegion, i2, 0, regionWidth, i4);
            }
            if (i3 > 0) {
                textureRegionArr[2] = new TextureRegion(textureRegion, i2 + regionWidth, 0, i3, i4);
            }
        }
        if (regionHeight > 0) {
            if (i2 > 0) {
                textureRegionArr[3] = new TextureRegion(textureRegion, 0, i4, i2, regionHeight);
            }
            if (regionWidth > 0) {
                textureRegionArr[4] = new TextureRegion(textureRegion, i2, i4, regionWidth, regionHeight);
            }
            if (i3 > 0) {
                textureRegionArr[5] = new TextureRegion(textureRegion, i2 + regionWidth, i4, i3, regionHeight);
            }
        }
        if (i5 > 0) {
            if (i2 > 0) {
                textureRegionArr[6] = new TextureRegion(textureRegion, 0, i4 + regionHeight, i2, i5);
            }
            if (regionWidth > 0) {
                textureRegionArr[7] = new TextureRegion(textureRegion, i2, i4 + regionHeight, regionWidth, i5);
            }
            if (i3 > 0) {
                textureRegionArr[8] = new TextureRegion(textureRegion, i2 + regionWidth, i4 + regionHeight, i3, i5);
            }
        }
        if (i2 == 0 && regionWidth == 0) {
            textureRegionArr[1] = textureRegionArr[2];
            textureRegionArr[4] = textureRegionArr[5];
            textureRegionArr[7] = textureRegionArr[8];
            textureRegionArr[2] = null;
            textureRegionArr[5] = null;
            textureRegionArr[8] = null;
        }
        if (i4 == 0 && regionHeight == 0) {
            textureRegionArr[3] = textureRegionArr[6];
            textureRegionArr[4] = textureRegionArr[7];
            textureRegionArr[5] = textureRegionArr[8];
            textureRegionArr[6] = null;
            textureRegionArr[7] = null;
            textureRegionArr[8] = null;
        }
        load(textureRegionArr);
    }

    public void draw(Batch batch, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        prepareVertices(batch, f2, f3, f6, f7);
        float f11 = f2 + f4;
        float f12 = f3 + f5;
        int i2 = this.idx;
        float[] fArr = this.vertices;
        if (f10 != 0.0f) {
            for (int i3 = 0; i3 < i2; i3 += 5) {
                float f13 = (fArr[i3] - f11) * f8;
                int i4 = i3 + 1;
                float f14 = (fArr[i4] - f12) * f9;
                float fC = j.c(f10);
                float fK = j.k(f10);
                fArr[i3] = ((fC * f13) - (fK * f14)) + f11;
                fArr[i4] = a.B(fC, f14, fK * f13, f12);
            }
        } else if (f8 != 1.0f || f9 != 1.0f) {
            for (int i5 = 0; i5 < i2; i5 += 5) {
                fArr[i5] = a.C(fArr[i5], f11, f8, f11);
                int i6 = i5 + 1;
                fArr[i6] = a.C(fArr[i6], f12, f9, f12);
            }
        }
        batch.draw(this.texture, fArr, 0, i2);
    }

    public NinePatch(Texture texture, Color color) {
        this(texture);
        setColor(color);
    }

    public NinePatch(Texture texture) {
        this(new TextureRegion(texture));
    }

    public NinePatch(TextureRegion textureRegion, Color color) {
        this(textureRegion);
        setColor(color);
    }

    public NinePatch(TextureRegion textureRegion) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        load(new TextureRegion[]{null, null, null, null, textureRegion, null, null, null, null});
    }

    public NinePatch(TextureRegion... textureRegionArr) {
        TextureRegion textureRegion;
        TextureRegion textureRegion2;
        TextureRegion textureRegion3;
        TextureRegion textureRegion4;
        TextureRegion textureRegion5;
        TextureRegion textureRegion6;
        TextureRegion textureRegion7;
        TextureRegion textureRegion8;
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        if (textureRegionArr != null && textureRegionArr.length == 9) {
            load(textureRegionArr);
            if ((textureRegionArr[0] != null && r1.getRegionWidth() != this.leftWidth) || (((textureRegion = textureRegionArr[3]) != null && textureRegion.getRegionWidth() != this.leftWidth) || ((textureRegion2 = textureRegionArr[6]) != null && textureRegion2.getRegionWidth() != this.leftWidth))) {
                throw new m("Left side patches must have the same width");
            }
            if ((textureRegionArr[2] != null && r3.getRegionWidth() != this.rightWidth) || (((textureRegion3 = textureRegionArr[5]) != null && textureRegion3.getRegionWidth() != this.rightWidth) || ((textureRegion4 = textureRegionArr[8]) != null && textureRegion4.getRegionWidth() != this.rightWidth))) {
                throw new m("Right side patches must have the same width");
            }
            if ((textureRegionArr[6] != null && r1.getRegionHeight() != this.bottomHeight) || (((textureRegion5 = textureRegionArr[7]) != null && textureRegion5.getRegionHeight() != this.bottomHeight) || ((textureRegion6 = textureRegionArr[8]) != null && textureRegion6.getRegionHeight() != this.bottomHeight))) {
                throw new m("Bottom side patches must have the same height");
            }
            if ((textureRegionArr[0] != null && r0.getRegionHeight() != this.topHeight) || (((textureRegion7 = textureRegionArr[1]) != null && textureRegion7.getRegionHeight() != this.topHeight) || ((textureRegion8 = textureRegionArr[2]) != null && textureRegion8.getRegionHeight() != this.topHeight))) {
                throw new m("Top side patches must have the same height");
            }
            return;
        }
        throw new IllegalArgumentException("NinePatch needs nine TextureRegions");
    }

    public NinePatch(NinePatch ninePatch) {
        this(ninePatch, ninePatch.color);
    }

    public NinePatch(NinePatch ninePatch, Color color) {
        this.vertices = new float[180];
        Color color2 = new Color(Color.WHITE);
        this.color = color2;
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        this.texture = ninePatch.texture;
        this.bottomLeft = ninePatch.bottomLeft;
        this.bottomCenter = ninePatch.bottomCenter;
        this.bottomRight = ninePatch.bottomRight;
        this.middleLeft = ninePatch.middleLeft;
        this.middleCenter = ninePatch.middleCenter;
        this.middleRight = ninePatch.middleRight;
        this.topLeft = ninePatch.topLeft;
        this.topCenter = ninePatch.topCenter;
        this.topRight = ninePatch.topRight;
        this.leftWidth = ninePatch.leftWidth;
        this.rightWidth = ninePatch.rightWidth;
        this.middleWidth = ninePatch.middleWidth;
        this.middleHeight = ninePatch.middleHeight;
        this.topHeight = ninePatch.topHeight;
        this.bottomHeight = ninePatch.bottomHeight;
        this.padLeft = ninePatch.padLeft;
        this.padTop = ninePatch.padTop;
        this.padBottom = ninePatch.padBottom;
        this.padRight = ninePatch.padRight;
        float[] fArr = new float[ninePatch.vertices.length];
        this.vertices = fArr;
        float[] fArr2 = ninePatch.vertices;
        System.arraycopy(fArr2, 0, fArr, 0, fArr2.length);
        this.idx = ninePatch.idx;
        color2.set(color);
    }
}
