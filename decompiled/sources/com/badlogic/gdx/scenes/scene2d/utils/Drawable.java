package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.g2d.Batch;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Drawable {
    void draw(Batch batch, float f2, float f3, float f4, float f5);

    float getBottomHeight();

    float getLeftWidth();

    float getMinHeight();

    float getMinWidth();

    float getRightWidth();

    float getTopHeight();

    void setBottomHeight(float f2);

    void setLeftWidth(float f2);

    void setMinHeight(float f2);

    void setMinWidth(float f2);

    void setRightWidth(float f2);

    void setTopHeight(float f2);
}
