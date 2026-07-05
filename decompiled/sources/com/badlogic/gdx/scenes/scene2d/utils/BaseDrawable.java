package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.g2d.Batch;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BaseDrawable implements Drawable {
    private float bottomHeight;
    private float leftWidth;
    private float minHeight;
    private float minWidth;
    private String name;
    private float rightWidth;
    private float topHeight;

    public BaseDrawable() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch, float f2, float f3, float f4, float f5) {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getBottomHeight() {
        return this.bottomHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getLeftWidth() {
        return this.leftWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getMinHeight() {
        return this.minHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getMinWidth() {
        return this.minWidth;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getRightWidth() {
        return this.rightWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getTopHeight() {
        return this.topHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setBottomHeight(float f2) {
        this.bottomHeight = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setLeftWidth(float f2) {
        this.leftWidth = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setMinHeight(float f2) {
        this.minHeight = f2;
    }

    public void setMinSize(float f2, float f3) {
        setMinWidth(f2);
        setMinHeight(f3);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setMinWidth(float f2) {
        this.minWidth = f2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPadding(float f2, float f3, float f4, float f5) {
        setTopHeight(f2);
        setLeftWidth(f3);
        setBottomHeight(f4);
        setRightWidth(f5);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setRightWidth(float f2) {
        this.rightWidth = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setTopHeight(float f2) {
        this.topHeight = f2;
    }

    public String toString() {
        String str = this.name;
        return str == null ? getClass().getSimpleName() : str;
    }

    public BaseDrawable(Drawable drawable) {
        if (drawable instanceof BaseDrawable) {
            this.name = ((BaseDrawable) drawable).getName();
        }
        this.leftWidth = drawable.getLeftWidth();
        this.rightWidth = drawable.getRightWidth();
        this.topHeight = drawable.getTopHeight();
        this.bottomHeight = drawable.getBottomHeight();
        this.minWidth = drawable.getMinWidth();
        this.minHeight = drawable.getMinHeight();
    }
}
