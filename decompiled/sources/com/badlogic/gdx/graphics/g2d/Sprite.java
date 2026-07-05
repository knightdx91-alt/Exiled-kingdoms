package com.badlogic.gdx.graphics.g2d;

import a0.j;
import a0.p;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Sprite extends TextureRegion {
    static final int SPRITE_SIZE = 20;
    static final int VERTEX_SIZE = 5;
    private p bounds;
    private final Color color;
    private boolean dirty;
    float height;
    private float originX;
    private float originY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    final float[] vertices;
    float width;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1696x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private float f1697y;

    public Sprite() {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void draw(Batch batch) {
        batch.draw(this.texture, getVertices(), 0, 20);
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void flip(boolean z2, boolean z3) {
        super.flip(z2, z3);
        float[] fArr = this.vertices;
        if (z2) {
            float f2 = fArr[3];
            fArr[3] = fArr[13];
            fArr[13] = f2;
            float f3 = fArr[8];
            fArr[8] = fArr[18];
            fArr[18] = f3;
        }
        if (z3) {
            float f4 = fArr[4];
            fArr[4] = fArr[14];
            fArr[14] = f4;
            float f5 = fArr[9];
            fArr[9] = fArr[19];
            fArr[19] = f5;
        }
    }

    public p getBoundingRectangle() {
        float[] vertices = getVertices();
        float f2 = vertices[0];
        float f3 = vertices[1];
        float f4 = vertices[5];
        float f5 = f2 > f4 ? f4 : f2;
        float f6 = vertices[10];
        if (f5 > f6) {
            f5 = f6;
        }
        float f7 = vertices[15];
        if (f5 > f7) {
            f5 = f7;
        }
        if (f2 < f4) {
            f2 = f4;
        }
        if (f2 >= f6) {
            f6 = f2;
        }
        if (f6 >= f7) {
            f7 = f6;
        }
        float f8 = vertices[6];
        float f9 = f3 > f8 ? f8 : f3;
        float f10 = vertices[11];
        if (f9 > f10) {
            f9 = f10;
        }
        float f11 = vertices[16];
        if (f9 > f11) {
            f9 = f11;
        }
        if (f3 < f8) {
            f3 = f8;
        }
        if (f3 >= f10) {
            f10 = f3;
        }
        if (f10 >= f11) {
            f11 = f10;
        }
        if (this.bounds == null) {
            this.bounds = new p();
        }
        p pVar = this.bounds;
        pVar.f89x = f5;
        pVar.f90y = f9;
        pVar.width = f7 - f5;
        pVar.height = f11 - f9;
        return pVar;
    }

    public Color getColor() {
        int iG = l.g(this.vertices[2]);
        Color color = this.color;
        color.f1680r = (iG & 255) / 255.0f;
        color.f1679g = ((iG >>> 8) & 255) / 255.0f;
        color.f1678b = ((iG >>> 16) & 255) / 255.0f;
        color.f1677a = ((iG >>> 24) & 255) / 255.0f;
        return color;
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
        if (this.dirty) {
            this.dirty = false;
            float[] fArr = this.vertices;
            float f2 = -this.originX;
            float f3 = -this.originY;
            float f4 = this.width + f2;
            float f5 = this.height + f3;
            float f6 = this.f1696x - f2;
            float f7 = this.f1697y - f3;
            float f8 = this.scaleX;
            if (f8 != 1.0f || this.scaleY != 1.0f) {
                f2 *= f8;
                float f9 = this.scaleY;
                f3 *= f9;
                f4 *= f8;
                f5 *= f9;
            }
            float f10 = this.rotation;
            if (f10 != 0.0f) {
                float fC = j.c(f10);
                float fK = j.k(this.rotation);
                float f11 = f2 * fC;
                float f12 = f2 * fK;
                float f13 = f3 * fC;
                float f14 = f4 * fC;
                float f15 = fC * f5;
                float f16 = f5 * fK;
                float f17 = (f11 - (f3 * fK)) + f6;
                float f18 = f13 + f12 + f7;
                fArr[0] = f17;
                fArr[1] = f18;
                float f19 = (f11 - f16) + f6;
                float f20 = f12 + f15 + f7;
                fArr[5] = f19;
                fArr[6] = f20;
                float f21 = (f14 - f16) + f6;
                float f22 = f15 + (f4 * fK) + f7;
                fArr[10] = f21;
                fArr[11] = f22;
                fArr[15] = (f21 - f19) + f17;
                fArr[16] = f22 - (f20 - f18);
            } else {
                float f23 = f2 + f6;
                float f24 = f3 + f7;
                float f25 = f4 + f6;
                float f26 = f5 + f7;
                fArr[0] = f23;
                fArr[1] = f24;
                fArr[5] = f23;
                fArr[6] = f26;
                fArr[10] = f25;
                fArr[11] = f26;
                fArr[15] = f25;
                fArr[16] = f24;
            }
        }
        return this.vertices;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.f1696x;
    }

    public float getY() {
        return this.f1697y;
    }

    public void rotate(float f2) {
        if (f2 == 0.0f) {
            return;
        }
        this.rotation += f2;
        this.dirty = true;
    }

    public void rotate90(boolean z2) {
        float[] fArr = this.vertices;
        if (z2) {
            float f2 = fArr[4];
            fArr[4] = fArr[19];
            fArr[19] = fArr[14];
            fArr[14] = fArr[9];
            fArr[9] = f2;
            float f3 = fArr[3];
            fArr[3] = fArr[18];
            fArr[18] = fArr[13];
            fArr[13] = fArr[8];
            fArr[8] = f3;
            return;
        }
        float f4 = fArr[4];
        fArr[4] = fArr[9];
        fArr[9] = fArr[14];
        fArr[14] = fArr[19];
        fArr[19] = f4;
        float f5 = fArr[3];
        fArr[3] = fArr[8];
        fArr[8] = fArr[13];
        fArr[13] = fArr[18];
        fArr[18] = f5;
    }

    public void scale(float f2) {
        this.scaleX += f2;
        this.scaleY += f2;
        this.dirty = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void scroll(float f2, float f3) {
        float[] fArr = this.vertices;
        if (f2 != 0.0f) {
            float f4 = (fArr[3] + f2) % 1.0f;
            float width = (this.width / this.texture.getWidth()) + f4;
            this.f1698u = f4;
            this.u2 = width;
            fArr[3] = f4;
            fArr[8] = f4;
            fArr[13] = width;
            fArr[18] = width;
        }
        if (f3 != 0.0f) {
            float f5 = (fArr[9] + f3) % 1.0f;
            float height = (this.height / this.texture.getHeight()) + f5;
            this.f1699v = f5;
            this.v2 = height;
            fArr[4] = height;
            fArr[9] = f5;
            fArr[14] = f5;
            fArr[19] = height;
        }
    }

    public void set(Sprite sprite) {
        if (sprite == null) {
            throw new IllegalArgumentException("sprite cannot be null.");
        }
        System.arraycopy(sprite.vertices, 0, this.vertices, 0, 20);
        this.texture = sprite.texture;
        this.f1698u = sprite.f1698u;
        this.f1699v = sprite.f1699v;
        this.u2 = sprite.u2;
        this.v2 = sprite.v2;
        this.f1696x = sprite.f1696x;
        this.f1697y = sprite.f1697y;
        this.width = sprite.width;
        this.height = sprite.height;
        this.regionWidth = sprite.regionWidth;
        this.regionHeight = sprite.regionHeight;
        this.originX = sprite.originX;
        this.originY = sprite.originY;
        this.rotation = sprite.rotation;
        this.scaleX = sprite.scaleX;
        this.scaleY = sprite.scaleY;
        this.color.set(sprite.color);
        this.dirty = sprite.dirty;
    }

    public void setAlpha(float f2) {
        Color color = this.color;
        color.f1677a = f2;
        float floatBits = color.toFloatBits();
        float[] fArr = this.vertices;
        fArr[2] = floatBits;
        fArr[7] = floatBits;
        fArr[12] = floatBits;
        fArr[17] = floatBits;
    }

    public void setBounds(float f2, float f3, float f4, float f5) {
        this.f1696x = f2;
        this.f1697y = f3;
        this.width = f4;
        this.height = f5;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f6 = f4 + f2;
        float f7 = f5 + f3;
        float[] fArr = this.vertices;
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[5] = f2;
        fArr[6] = f7;
        fArr[10] = f6;
        fArr[11] = f7;
        fArr[15] = f6;
        fArr[16] = f3;
    }

    public void setCenter(float f2, float f3) {
        setPosition(f2 - (this.width / 2.0f), f3 - (this.height / 2.0f));
    }

    public void setCenterX(float f2) {
        setX(f2 - (this.width / 2.0f));
    }

    public void setCenterY(float f2) {
        setY(f2 - (this.height / 2.0f));
    }

    public void setColor(Color color) {
        this.color.set(color);
        float floatBits = color.toFloatBits();
        float[] fArr = this.vertices;
        fArr[2] = floatBits;
        fArr[7] = floatBits;
        fArr[12] = floatBits;
        fArr[17] = floatBits;
    }

    public void setFlip(boolean z2, boolean z3) {
        flip(isFlipX() != z2, isFlipY() != z3);
    }

    public void setOrigin(float f2, float f3) {
        this.originX = f2;
        this.originY = f3;
        this.dirty = true;
    }

    public void setOriginBasedPosition(float f2, float f3) {
        setPosition(f2 - this.originX, f3 - this.originY);
    }

    public void setOriginCenter() {
        this.originX = this.width / 2.0f;
        this.originY = this.height / 2.0f;
        this.dirty = true;
    }

    public void setPackedColor(float f2) {
        Color.abgr8888ToColor(this.color, f2);
        float[] fArr = this.vertices;
        fArr[2] = f2;
        fArr[7] = f2;
        fArr[12] = f2;
        fArr[17] = f2;
    }

    public void setPosition(float f2, float f3) {
        this.f1696x = f2;
        this.f1697y = f3;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f4 = this.width + f2;
        float f5 = this.height + f3;
        float[] fArr = this.vertices;
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[5] = f2;
        fArr[6] = f5;
        fArr[10] = f4;
        fArr[11] = f5;
        fArr[15] = f4;
        fArr[16] = f3;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setRegion(float f2, float f3, float f4, float f5) {
        super.setRegion(f2, f3, f4, f5);
        float[] fArr = this.vertices;
        fArr[3] = f2;
        fArr[4] = f5;
        fArr[8] = f2;
        fArr[9] = f3;
        fArr[13] = f4;
        fArr[14] = f3;
        fArr[18] = f4;
        fArr[19] = f5;
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
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f4 = this.f1696x;
        float f5 = f2 + f4;
        float f6 = this.f1697y;
        float f7 = f3 + f6;
        float[] fArr = this.vertices;
        fArr[0] = f4;
        fArr[1] = f6;
        fArr[5] = f4;
        fArr[6] = f7;
        fArr[10] = f5;
        fArr[11] = f7;
        fArr[15] = f5;
        fArr[16] = f6;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setU(float f2) {
        super.setU(f2);
        float[] fArr = this.vertices;
        fArr[3] = f2;
        fArr[8] = f2;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setU2(float f2) {
        super.setU2(f2);
        float[] fArr = this.vertices;
        fArr[13] = f2;
        fArr[18] = f2;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setV(float f2) {
        super.setV(f2);
        float[] fArr = this.vertices;
        fArr[9] = f2;
        fArr[14] = f2;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setV2(float f2) {
        super.setV2(f2);
        float[] fArr = this.vertices;
        fArr[4] = f2;
        fArr[19] = f2;
    }

    public void setX(float f2) {
        this.f1696x = f2;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f3 = this.width + f2;
        float[] fArr = this.vertices;
        fArr[0] = f2;
        fArr[5] = f2;
        fArr[10] = f3;
        fArr[15] = f3;
    }

    public void setY(float f2) {
        this.f1697y = f2;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f3 = this.height + f2;
        float[] fArr = this.vertices;
        fArr[1] = f2;
        fArr[6] = f3;
        fArr[11] = f3;
        fArr[16] = f2;
    }

    public void translate(float f2, float f3) {
        this.f1696x += f2;
        this.f1697y += f3;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float[] fArr = this.vertices;
        fArr[0] = fArr[0] + f2;
        fArr[1] = fArr[1] + f3;
        fArr[5] = fArr[5] + f2;
        fArr[6] = fArr[6] + f3;
        fArr[10] = fArr[10] + f2;
        fArr[11] = fArr[11] + f3;
        fArr[15] = fArr[15] + f2;
        fArr[16] = fArr[16] + f3;
    }

    public void translateX(float f2) {
        this.f1696x += f2;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float[] fArr = this.vertices;
        fArr[0] = fArr[0] + f2;
        fArr[5] = fArr[5] + f2;
        fArr[10] = fArr[10] + f2;
        fArr[15] = fArr[15] + f2;
    }

    public void translateY(float f2) {
        this.f1697y += f2;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float[] fArr = this.vertices;
        fArr[1] = fArr[1] + f2;
        fArr[6] = fArr[6] + f2;
        fArr[11] = fArr[11] + f2;
        fArr[16] = fArr[16] + f2;
    }

    public void draw(Batch batch, float f2) {
        float f3 = getColor().f1677a;
        setAlpha(f2 * f3);
        draw(batch);
        setAlpha(f3);
    }

    public void setScale(float f2, float f3) {
        this.scaleX = f2;
        this.scaleY = f3;
        this.dirty = true;
    }

    public Sprite(Texture texture) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    public Sprite(Texture texture, int i2, int i3) {
        this(texture, 0, 0, i2, i3);
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        float floatBits = this.color.toFloatBits();
        float[] fArr = this.vertices;
        fArr[2] = floatBits;
        fArr[7] = floatBits;
        fArr[12] = floatBits;
        fArr[17] = floatBits;
    }

    public Sprite(Texture texture, int i2, int i3, int i4, int i5) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        if (texture != null) {
            this.texture = texture;
            setRegion(i2, i3, i4, i5);
            setColor(1.0f, 1.0f, 1.0f, 1.0f);
            setSize(Math.abs(i4), Math.abs(i5));
            setOrigin(this.width / 2.0f, this.height / 2.0f);
            return;
        }
        throw new IllegalArgumentException("texture cannot be null.");
    }

    public Sprite(TextureRegion textureRegion) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        setRegion(textureRegion);
        setColor(1.0f, 1.0f, 1.0f, 1.0f);
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public Sprite(TextureRegion textureRegion, int i2, int i3, int i4, int i5) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        setRegion(textureRegion, i2, i3, i4, i5);
        setColor(1.0f, 1.0f, 1.0f, 1.0f);
        setSize(Math.abs(i4), Math.abs(i5));
        setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public Sprite(Sprite sprite) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        set(sprite);
    }
}
