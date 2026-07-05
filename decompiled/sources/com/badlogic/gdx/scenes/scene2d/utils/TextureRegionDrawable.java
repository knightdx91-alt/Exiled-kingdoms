package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextureRegionDrawable extends BaseDrawable implements TransformDrawable {
    private TextureRegion region;

    public TextureRegionDrawable() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch, float f2, float f3, float f4, float f5) {
        batch.draw(this.region, f2, f3, f4, f5);
    }

    public TextureRegion getRegion() {
        return this.region;
    }

    public void setRegion(TextureRegion textureRegion) {
        this.region = textureRegion;
        if (textureRegion != null) {
            setMinWidth(textureRegion.getRegionWidth());
            setMinHeight(textureRegion.getRegionHeight());
        }
    }

    public Drawable tint(Color color) {
        TextureRegion textureRegion = this.region;
        Sprite atlasSprite = textureRegion instanceof TextureAtlas.AtlasRegion ? new TextureAtlas.AtlasSprite((TextureAtlas.AtlasRegion) textureRegion) : new Sprite(textureRegion);
        atlasSprite.setColor(color);
        atlasSprite.setSize(getMinWidth(), getMinHeight());
        SpriteDrawable spriteDrawable = new SpriteDrawable(atlasSprite);
        spriteDrawable.setLeftWidth(getLeftWidth());
        spriteDrawable.setRightWidth(getRightWidth());
        spriteDrawable.setTopHeight(getTopHeight());
        spriteDrawable.setBottomHeight(getBottomHeight());
        return spriteDrawable;
    }

    public TextureRegionDrawable(Texture texture) {
        setRegion(new TextureRegion(texture));
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        batch.draw(this.region, f2, f3, f4, f5, f6, f7, f8, f9, f10);
    }

    public TextureRegionDrawable(TextureRegion textureRegion) {
        setRegion(textureRegion);
    }

    public TextureRegionDrawable(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        setRegion(textureRegionDrawable.region);
    }
}
