package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Widget extends Actor implements Layout {
    private boolean fillParent;
    private boolean needsLayout = true;
    private boolean layoutEnabled = true;

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        validate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxHeight() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxWidth() {
        return 0.0f;
    }

    public float getMinHeight() {
        return getPrefHeight();
    }

    public float getMinWidth() {
        return getPrefWidth();
    }

    public float getPrefHeight() {
        return 0.0f;
    }

    public float getPrefWidth() {
        return 0.0f;
    }

    public void invalidate() {
        this.needsLayout = true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidateHierarchy() {
        if (this.layoutEnabled) {
            invalidate();
            Object parent = getParent();
            if (parent instanceof Layout) {
                ((Layout) parent).invalidateHierarchy();
            }
        }
    }

    public void layout() {
    }

    public boolean needsLayout() {
        return this.needsLayout;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void pack() {
        setSize(getPrefWidth(), getPrefHeight());
        validate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void setFillParent(boolean z2) {
        this.fillParent = z2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void setLayoutEnabled(boolean z2) {
        this.layoutEnabled = z2;
        if (z2) {
            invalidateHierarchy();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    protected void sizeChanged() {
        invalidate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void validate() {
        float height;
        float width;
        if (this.layoutEnabled) {
            Group parent = getParent();
            if (this.fillParent && parent != null) {
                Stage stage = getStage();
                if (stage == null || parent != stage.getRoot()) {
                    float width2 = parent.getWidth();
                    height = parent.getHeight();
                    width = width2;
                } else {
                    width = stage.getWidth();
                    height = stage.getHeight();
                }
                setSize(width, height);
            }
            if (this.needsLayout) {
                this.needsLayout = false;
                layout();
            }
        }
    }
}
