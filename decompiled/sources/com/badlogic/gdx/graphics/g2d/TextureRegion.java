package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextureRegion {
    int regionHeight;
    int regionWidth;
    Texture texture;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    float f1698u;
    float u2;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    float f1699v;
    float v2;

    public TextureRegion() {
    }

    public void flip(boolean z2, boolean z3) {
        if (z2) {
            float f2 = this.f1698u;
            this.f1698u = this.u2;
            this.u2 = f2;
        }
        if (z3) {
            float f3 = this.f1699v;
            this.f1699v = this.v2;
            this.v2 = f3;
        }
    }

    public int getRegionHeight() {
        return this.regionHeight;
    }

    public int getRegionWidth() {
        return this.regionWidth;
    }

    public int getRegionX() {
        return Math.round(this.f1698u * this.texture.getWidth());
    }

    public int getRegionY() {
        return Math.round(this.f1699v * this.texture.getHeight());
    }

    public Texture getTexture() {
        return this.texture;
    }

    public float getU() {
        return this.f1698u;
    }

    public float getU2() {
        return this.u2;
    }

    public float getV() {
        return this.f1699v;
    }

    public float getV2() {
        return this.v2;
    }

    public boolean isFlipX() {
        return this.f1698u > this.u2;
    }

    public boolean isFlipY() {
        return this.f1699v > this.v2;
    }

    public void scroll(float f2, float f3) {
        if (f2 != 0.0f) {
            float width = (this.u2 - this.f1698u) * this.texture.getWidth();
            float f4 = (this.f1698u + f2) % 1.0f;
            this.f1698u = f4;
            this.u2 = (width / this.texture.getWidth()) + f4;
        }
        if (f3 != 0.0f) {
            float height = (this.v2 - this.f1699v) * this.texture.getHeight();
            float f5 = (this.f1699v + f3) % 1.0f;
            this.f1699v = f5;
            this.v2 = (height / this.texture.getHeight()) + f5;
        }
    }

    public void setRegion(Texture texture) {
        this.texture = texture;
        setRegion(0, 0, texture.getWidth(), texture.getHeight());
    }

    public void setRegionHeight(int i2) {
        if (isFlipY()) {
            setV((i2 / this.texture.getHeight()) + this.v2);
        } else {
            setV2((i2 / this.texture.getHeight()) + this.f1699v);
        }
    }

    public void setRegionWidth(int i2) {
        if (isFlipX()) {
            setU((i2 / this.texture.getWidth()) + this.u2);
        } else {
            setU2((i2 / this.texture.getWidth()) + this.f1698u);
        }
    }

    public void setRegionX(int i2) {
        setU(i2 / this.texture.getWidth());
    }

    public void setRegionY(int i2) {
        setV(i2 / this.texture.getHeight());
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setU(float f2) {
        this.f1698u = f2;
        this.regionWidth = Math.round(Math.abs(this.u2 - f2) * this.texture.getWidth());
    }

    public void setU2(float f2) {
        this.u2 = f2;
        this.regionWidth = Math.round(Math.abs(f2 - this.f1698u) * this.texture.getWidth());
    }

    public void setV(float f2) {
        this.f1699v = f2;
        this.regionHeight = Math.round(Math.abs(this.v2 - f2) * this.texture.getHeight());
    }

    public void setV2(float f2) {
        this.v2 = f2;
        this.regionHeight = Math.round(Math.abs(f2 - this.f1699v) * this.texture.getHeight());
    }

    public TextureRegion[][] split(int i2, int i3) {
        int regionX = getRegionX();
        int regionY = getRegionY();
        int i4 = this.regionWidth;
        int i5 = this.regionHeight / i3;
        int i6 = i4 / i2;
        TextureRegion[][] textureRegionArr = (TextureRegion[][]) Array.newInstance((Class<?>) TextureRegion.class, i5, i6);
        int i7 = regionY;
        int i8 = 0;
        while (i8 < i5) {
            int i9 = regionX;
            int i10 = 0;
            while (i10 < i6) {
                textureRegionArr[i8][i10] = new TextureRegion(this.texture, i9, i7, i2, i3);
                i10++;
                i9 += i2;
            }
            i8++;
            i7 += i3;
        }
        return textureRegionArr;
    }

    public TextureRegion(Texture texture) {
        if (texture == null) {
            throw new IllegalArgumentException("texture cannot be null.");
        }
        this.texture = texture;
        setRegion(0, 0, texture.getWidth(), texture.getHeight());
    }

    public void setRegion(int i2, int i3, int i4, int i5) {
        float width = 1.0f / this.texture.getWidth();
        float height = 1.0f / this.texture.getHeight();
        setRegion(i2 * width, i3 * height, (i2 + i4) * width, (i3 + i5) * height);
        this.regionWidth = Math.abs(i4);
        this.regionHeight = Math.abs(i5);
    }

    public TextureRegion(Texture texture, int i2, int i3) {
        this.texture = texture;
        setRegion(0, 0, i2, i3);
    }

    public void setRegion(float f2, float f3, float f4, float f5) {
        int width = this.texture.getWidth();
        int height = this.texture.getHeight();
        float f6 = width;
        this.regionWidth = Math.round(Math.abs(f4 - f2) * f6);
        float f7 = height;
        int iRound = Math.round(Math.abs(f5 - f3) * f7);
        this.regionHeight = iRound;
        if (this.regionWidth == 1 && iRound == 1) {
            float f8 = 0.25f / f6;
            f2 += f8;
            f4 -= f8;
            float f9 = 0.25f / f7;
            f3 += f9;
            f5 -= f9;
        }
        this.f1698u = f2;
        this.f1699v = f3;
        this.u2 = f4;
        this.v2 = f5;
    }

    public TextureRegion(Texture texture, int i2, int i3, int i4, int i5) {
        this.texture = texture;
        setRegion(i2, i3, i4, i5);
    }

    public static TextureRegion[][] split(Texture texture, int i2, int i3) {
        return new TextureRegion(texture).split(i2, i3);
    }

    public TextureRegion(Texture texture, float f2, float f3, float f4, float f5) {
        this.texture = texture;
        setRegion(f2, f3, f4, f5);
    }

    public TextureRegion(TextureRegion textureRegion) {
        setRegion(textureRegion);
    }

    public void setRegion(TextureRegion textureRegion) {
        this.texture = textureRegion.texture;
        setRegion(textureRegion.f1698u, textureRegion.f1699v, textureRegion.u2, textureRegion.v2);
    }

    public TextureRegion(TextureRegion textureRegion, int i2, int i3, int i4, int i5) {
        setRegion(textureRegion, i2, i3, i4, i5);
    }

    public void setRegion(TextureRegion textureRegion, int i2, int i3, int i4, int i5) {
        this.texture = textureRegion.texture;
        setRegion(textureRegion.getRegionX() + i2, textureRegion.getRegionY() + i3, i4, i5);
    }
}
