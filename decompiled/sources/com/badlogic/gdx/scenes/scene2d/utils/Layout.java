package com.badlogic.gdx.scenes.scene2d.utils;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Layout {
    float getMaxHeight();

    float getMaxWidth();

    float getMinHeight();

    float getMinWidth();

    float getPrefHeight();

    float getPrefWidth();

    void invalidate();

    void invalidateHierarchy();

    void layout();

    void pack();

    void setFillParent(boolean z2);

    void setLayoutEnabled(boolean z2);

    void validate();
}
