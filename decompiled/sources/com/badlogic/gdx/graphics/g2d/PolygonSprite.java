package com.badlogic.gdx.graphics.g2d;

import a.a;
import a0.j;
import a0.p;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PolygonSprite {
    private boolean dirty;
    private float height;
    private float originX;
    private float originY;
    PolygonRegion region;
    private float rotation;
    private float[] vertices;
    private float width;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1692x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private float f1693y;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private p bounds = new p();
    private final Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);

    public PolygonSprite(PolygonRegion polygonRegion) {
        setRegion(polygonRegion);
        TextureRegion textureRegion = polygonRegion.region;
        setSize(textureRegion.regionWidth, textureRegion.regionHeight);
        setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch) {
        PolygonRegion polygonRegion = this.region;
        Texture texture = polygonRegion.region.texture;
        float[] vertices = getVertices();
        int length = this.vertices.length;
        short[] sArr = polygonRegion.triangles;
        polygonSpriteBatch.draw(texture, vertices, 0, length, sArr, 0, sArr.length);
    }

    public p getBoundingRectangle() {
        float[] vertices = getVertices();
        float f2 = vertices[0];
        float f3 = vertices[1];
        float f4 = f2;
        float f5 = f3;
        for (int i2 = 5; i2 < vertices.length; i2 += 5) {
            float f6 = vertices[i2];
            float f7 = vertices[i2 + 1];
            if (f2 > f6) {
                f2 = f6;
            }
            if (f4 < f6) {
                f4 = f6;
            }
            if (f5 > f7) {
                f5 = f7;
            }
            if (f3 < f7) {
                f3 = f7;
            }
        }
        p pVar = this.bounds;
        pVar.f89x = f2;
        pVar.f90y = f5;
        pVar.width = f4 - f2;
        pVar.height = f3 - f5;
        return pVar;
    }

    public Color getColor() {
        return this.color;
    }

    public float getHeight() {
        return this.height;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public Color getPackedColor() {
        Color.abgr8888ToColor(this.color, this.vertices[2]);
        return this.color;
    }

    public PolygonRegion getRegion() {
        return this.region;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public float[] getVertices() {
        if (!this.dirty) {
            return this.vertices;
        }
        int i2 = 0;
        this.dirty = false;
        float f2 = this.originX;
        float f3 = this.originY;
        float f4 = this.scaleX;
        float f5 = this.scaleY;
        PolygonRegion polygonRegion = this.region;
        float[] fArr = this.vertices;
        float[] fArr2 = polygonRegion.vertices;
        float f6 = this.f1692x + f2;
        float f7 = this.f1693y + f3;
        float regionWidth = this.width / polygonRegion.region.getRegionWidth();
        float regionHeight = this.height / polygonRegion.region.getRegionHeight();
        float fC = j.c(this.rotation);
        float fK = j.k(this.rotation);
        int length = fArr2.length;
        int i3 = 0;
        while (i2 < length) {
            float f8 = ((fArr2[i2] * regionWidth) - f2) * f4;
            float f9 = ((fArr2[i2 + 1] * regionHeight) - f3) * f5;
            fArr[i3] = ((fC * f8) - (fK * f9)) + f6;
            fArr[i3 + 1] = a.B(fC, f9, fK * f8, f7);
            i2 += 2;
            i3 += 5;
            f2 = f2;
        }
        return fArr;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.f1692x;
    }

    public float getY() {
        return this.f1693y;
    }

    public void rotate(float f2) {
        this.rotation += f2;
        this.dirty = true;
    }

    public void scale(float f2) {
        this.scaleX += f2;
        this.scaleY += f2;
        this.dirty = true;
    }

    public void set(PolygonSprite polygonSprite) {
        if (polygonSprite == null) {
            throw new IllegalArgumentException("sprite cannot be null.");
        }
        setRegion(polygonSprite.region);
        this.f1692x = polygonSprite.f1692x;
        this.f1693y = polygonSprite.f1693y;
        this.width = polygonSprite.width;
        this.height = polygonSprite.height;
        this.originX = polygonSprite.originX;
        this.originY = polygonSprite.originY;
        this.rotation = polygonSprite.rotation;
        this.scaleX = polygonSprite.scaleX;
        this.scaleY = polygonSprite.scaleY;
        this.color.set(polygonSprite.color);
    }

    public void setBounds(float f2, float f3, float f4, float f5) {
        this.f1692x = f2;
        this.f1693y = f3;
        this.width = f4;
        this.height = f5;
        this.dirty = true;
    }

    public void setColor(Color color) {
        this.color.set(color);
        float floatBits = color.toFloatBits();
        float[] fArr = this.vertices;
        for (int i2 = 2; i2 < fArr.length; i2 += 5) {
            fArr[i2] = floatBits;
        }
    }

    public void setOrigin(float f2, float f3) {
        this.originX = f2;
        this.originY = f3;
        this.dirty = true;
    }

    public void setPosition(float f2, float f3) {
        translate(f2 - this.f1692x, f3 - this.f1693y);
    }

    public void setRegion(PolygonRegion polygonRegion) {
        this.region = polygonRegion;
        float[] fArr = polygonRegion.vertices;
        float[] fArr2 = polygonRegion.textureCoords;
        int length = (fArr.length / 2) * 5;
        float[] fArr3 = this.vertices;
        if (fArr3 == null || fArr3.length != length) {
            this.vertices = new float[length];
        }
        float floatBits = this.color.toFloatBits();
        float[] fArr4 = this.vertices;
        int i2 = 0;
        for (int i3 = 2; i3 < length; i3 += 5) {
            fArr4[i3] = floatBits;
            fArr4[i3 + 1] = fArr2[i2];
            fArr4[i3 + 2] = fArr2[i2 + 1];
            i2 += 2;
        }
        this.dirty = true;
    }

    public void setRotation(float f2) {
        this.rotation = f2;
        this.dirty = true;
    }

    public void setScale(float f2) {
        this.scaleX = f2;
        this.scaleY = f2;
        this.dirty = true;
    }

    public void setSize(float f2, float f3) {
        this.width = f2;
        this.height = f3;
        this.dirty = true;
    }

    public void setX(float f2) {
        translateX(f2 - this.f1692x);
    }

    public void setY(float f2) {
        translateY(f2 - this.f1693y);
    }

    public void translate(float f2, float f3) {
        this.f1692x += f2;
        this.f1693y += f3;
        if (this.dirty) {
            return;
        }
        float[] fArr = this.vertices;
        for (int i2 = 0; i2 < fArr.length; i2 += 5) {
            fArr[i2] = fArr[i2] + f2;
            int i3 = i2 + 1;
            fArr[i3] = fArr[i3] + f3;
        }
    }

    public void translateX(float f2) {
        this.f1692x += f2;
        if (this.dirty) {
            return;
        }
        float[] fArr = this.vertices;
        for (int i2 = 0; i2 < fArr.length; i2 += 5) {
            fArr[i2] = fArr[i2] + f2;
        }
    }

    public void translateY(float f2) {
        this.f1693y += f2;
        if (this.dirty) {
            return;
        }
        float[] fArr = this.vertices;
        for (int i2 = 1; i2 < fArr.length; i2 += 5) {
            fArr[i2] = fArr[i2] + f2;
        }
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch, float f2) {
        Color color = getColor();
        float f3 = color.f1677a;
        color.f1677a = f2 * f3;
        setColor(color);
        draw(polygonSpriteBatch);
        color.f1677a = f3;
        setColor(color);
    }

    public void setScale(float f2, float f3) {
        this.scaleX = f2;
        this.scaleY = f3;
        this.dirty = true;
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        float floatBits = this.color.toFloatBits();
        float[] fArr = this.vertices;
        for (int i2 = 2; i2 < fArr.length; i2 += 5) {
            fArr[i2] = floatBits;
        }
    }

    public PolygonSprite(PolygonSprite polygonSprite) {
        set(polygonSprite);
    }
}
