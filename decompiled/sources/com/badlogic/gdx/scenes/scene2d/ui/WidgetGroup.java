package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.k0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class WidgetGroup extends Group implements Layout {
    private boolean fillParent;
    private boolean needsLayout = true;
    private boolean layoutEnabled = true;

    public WidgetGroup() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    protected void childrenChanged() {
        invalidateHierarchy();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        validate();
        super.draw(batch, f2);
    }

    public float getMaxHeight() {
        return 0.0f;
    }

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
        invalidate();
        Object parent = getParent();
        if (parent instanceof Layout) {
            ((Layout) parent).invalidateHierarchy();
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
        setLayoutEnabled(this, z2);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    protected void sizeChanged() {
        invalidate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void validate() {
        float width;
        float height;
        if (this.layoutEnabled) {
            Group parent = getParent();
            if (this.fillParent && parent != null) {
                Stage stage = getStage();
                if (stage == null || parent != stage.getRoot()) {
                    width = parent.getWidth();
                    height = parent.getHeight();
                } else {
                    width = stage.getWidth();
                    height = stage.getHeight();
                }
                if (getWidth() != width || getHeight() != height) {
                    setWidth(width);
                    setHeight(height);
                    invalidate();
                }
            }
            if (this.needsLayout) {
                this.needsLayout = false;
                layout();
                if (!this.needsLayout || (parent instanceof WidgetGroup)) {
                    return;
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    this.needsLayout = false;
                    layout();
                    if (!this.needsLayout) {
                        return;
                    }
                }
            }
        }
    }

    private void setLayoutEnabled(Group group, boolean z2) {
        k0<Actor> children = group.getChildren();
        int i2 = children.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Object obj = (Actor) children.get(i3);
            if (obj instanceof Layout) {
                ((Layout) obj).setLayoutEnabled(z2);
            } else if (obj instanceof Group) {
                setLayoutEnabled((Group) obj, z2);
            }
        }
    }

    public WidgetGroup(Actor... actorArr) {
        for (Actor actor : actorArr) {
            addActor(actor);
        }
    }
}
