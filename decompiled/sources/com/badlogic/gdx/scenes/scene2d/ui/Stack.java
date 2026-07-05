package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.k0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Stack extends WidgetGroup {
    private float maxHeight;
    private float maxWidth;
    private float minHeight;
    private float minWidth;
    private float prefHeight;
    private float prefWidth;
    private boolean sizeInvalid;

    public Stack() {
        this.sizeInvalid = true;
        setTransform(false);
        setWidth(150.0f);
        setHeight(150.0f);
        setTouchable(Touchable.childrenOnly);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void computeSize() {
        float fMin;
        float fMin2;
        this.sizeInvalid = false;
        this.prefWidth = 0.0f;
        this.prefHeight = 0.0f;
        this.minWidth = 0.0f;
        this.minHeight = 0.0f;
        this.maxWidth = 0.0f;
        this.maxHeight = 0.0f;
        k0<Actor> children = getChildren();
        int i2 = children.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Actor actor = children.get(i3);
            if (actor instanceof Layout) {
                Layout layout = (Layout) actor;
                this.prefWidth = Math.max(this.prefWidth, layout.getPrefWidth());
                this.prefHeight = Math.max(this.prefHeight, layout.getPrefHeight());
                this.minWidth = Math.max(this.minWidth, layout.getMinWidth());
                this.minHeight = Math.max(this.minHeight, layout.getMinHeight());
                fMin2 = layout.getMaxWidth();
                fMin = layout.getMaxHeight();
            } else {
                this.prefWidth = Math.max(this.prefWidth, actor.getWidth());
                this.prefHeight = Math.max(this.prefHeight, actor.getHeight());
                this.minWidth = Math.max(this.minWidth, actor.getWidth());
                this.minHeight = Math.max(this.minHeight, actor.getHeight());
                fMin = 0.0f;
                fMin2 = 0.0f;
            }
            if (fMin2 > 0.0f) {
                float f2 = this.maxWidth;
                if (f2 != 0.0f) {
                    fMin2 = Math.min(f2, fMin2);
                }
                this.maxWidth = fMin2;
            }
            if (fMin > 0.0f) {
                float f3 = this.maxHeight;
                if (f3 != 0.0f) {
                    fMin = Math.min(f3, fMin);
                }
                this.maxHeight = fMin;
            }
        }
    }

    public void add(Actor actor) {
        addActor(actor);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.maxHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.maxWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.minHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.minWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        if (this.sizeInvalid) {
            computeSize();
        }
        float width = getWidth();
        float height = getHeight();
        k0<Actor> children = getChildren();
        int i2 = children.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Actor actor = children.get(i3);
            actor.setBounds(0.0f, 0.0f, width, height);
            if (actor instanceof Layout) {
                ((Layout) actor).validate();
            }
        }
    }

    public Stack(Actor... actorArr) {
        this();
        for (Actor actor : actorArr) {
            addActor(actor);
        }
    }
}
