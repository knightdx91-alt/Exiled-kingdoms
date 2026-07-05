package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SpriteDrawable extends BaseDrawable implements TransformDrawable {
    private Sprite sprite;

    public SpriteDrawable() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch, float f2, float f3, float f4, float f5) {
        Color color = this.sprite.getColor();
        float floatBits = color.toFloatBits();
        this.sprite.setColor(color.mul(batch.getColor()));
        this.sprite.setRotation(0.0f);
        this.sprite.setScale(1.0f, 1.0f);
        this.sprite.setBounds(f2, f3, f4, f5);
        this.sprite.draw(batch);
        this.sprite.setPackedColor(floatBits);
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        setMinWidth(sprite.getWidth());
        setMinHeight(sprite.getHeight());
    }

    public SpriteDrawable tint(Color color) {
        Sprite sprite = this.sprite;
        Sprite atlasSprite = sprite instanceof TextureAtlas.AtlasSprite ? new TextureAtlas.AtlasSprite((TextureAtlas.AtlasSprite) sprite) : new Sprite(sprite);
        atlasSprite.setColor(color);
        atlasSprite.setSize(getMinWidth(), getMinHeight());
        SpriteDrawable spriteDrawable = new SpriteDrawable(atlasSprite);
        spriteDrawable.setLeftWidth(getLeftWidth());
        spriteDrawable.setRightWidth(getRightWidth());
        spriteDrawable.setTopHeight(getTopHeight());
        spriteDrawable.setBottomHeight(getBottomHeight());
        return spriteDrawable;
    }

    public SpriteDrawable(Sprite sprite) {
        setSprite(sprite);
    }

    public SpriteDrawable(SpriteDrawable spriteDrawable) {
        super(spriteDrawable);
        setSprite(spriteDrawable.sprite);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        Color color = this.sprite.getColor();
        float floatBits = color.toFloatBits();
        this.sprite.setColor(color.mul(batch.getColor()));
        this.sprite.setOrigin(f4, f5);
        this.sprite.setRotation(f10);
        this.sprite.setScale(f8, f9);
        this.sprite.setBounds(f2, f3, f6, f7);
        this.sprite.draw(batch);
        this.sprite.setPackedColor(floatBits);
    }
}
