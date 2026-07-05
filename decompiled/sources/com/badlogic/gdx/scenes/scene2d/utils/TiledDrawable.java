package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TiledDrawable extends TextureRegionDrawable {
    private final Color color;
    private float scale;

    public TiledDrawable() {
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scale = 1.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable, com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch, float f2, float f3, float f4, float f5) {
        int i2;
        float packedColor = batch.getPackedColor();
        batch.getColor().mul(this.color);
        TextureRegion region = getRegion();
        float regionWidth = region.getRegionWidth() * this.scale;
        float regionHeight = region.getRegionHeight() * this.scale;
        int i3 = (int) (f4 / regionWidth);
        int i4 = (int) (f5 / regionHeight);
        float f6 = f4 - (i3 * regionWidth);
        float f7 = f5 - (i4 * regionHeight);
        float f8 = f2;
        float f9 = f3;
        int i5 = 0;
        while (i5 < i3) {
            float f10 = f3;
            for (int i6 = 0; i6 < i4; i6++) {
                batch.draw(region, f8, f10, regionWidth, regionHeight);
                f10 += regionHeight;
            }
            f8 += regionWidth;
            i5++;
            f9 = f10;
        }
        Texture texture = region.getTexture();
        float u2 = region.getU();
        float v2 = region.getV2();
        if (f6 > 0.0f) {
            float width = (f6 / (texture.getWidth() * this.scale)) + u2;
            float v3 = region.getV();
            float f11 = f3;
            int i7 = 0;
            while (i7 < i4) {
                batch.draw(texture, f8, f11, f6, regionHeight, u2, v2, width, v3);
                f11 += regionHeight;
                i7++;
                i4 = i4;
                i3 = i3;
            }
            i2 = i3;
            if (f7 > 0.0f) {
                batch.draw(texture, f8, f11, f6, f7, u2, v2, width, v2 - (f7 / (texture.getHeight() * this.scale)));
            }
            f9 = f11;
        } else {
            i2 = i3;
        }
        if (f7 > 0.0f) {
            float u22 = region.getU2();
            float height = v2 - (f7 / (texture.getHeight() * this.scale));
            float f12 = f2;
            int i8 = 0;
            for (int i9 = i2; i8 < i9; i9 = i9) {
                batch.draw(texture, f12, f9, regionWidth, f7, u2, v2, u22, height);
                f12 += regionWidth;
                i8++;
            }
        }
        batch.setPackedColor(packedColor);
    }

    public Color getColor() {
        return this.color;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float f2) {
        this.scale = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
    public TiledDrawable tint(Color color) {
        TiledDrawable tiledDrawable = new TiledDrawable(this);
        tiledDrawable.color.set(color);
        tiledDrawable.setLeftWidth(getLeftWidth());
        tiledDrawable.setRightWidth(getRightWidth());
        tiledDrawable.setTopHeight(getTopHeight());
        tiledDrawable.setBottomHeight(getBottomHeight());
        return tiledDrawable;
    }

    public TiledDrawable(TextureRegion textureRegion) {
        super(textureRegion);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scale = 1.0f;
    }

    public TiledDrawable(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scale = 1.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable, com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        throw new UnsupportedOperationException();
    }
}
