package com.badlogic.gdx.scenes.scene2d.ui;

import a0.p;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Container<T extends Actor> extends WidgetGroup {
    private T actor;
    private int align;
    private Drawable background;
    private boolean clip;
    private float fillX;
    private float fillY;
    private Value maxHeight;
    private Value maxWidth;
    private Value minHeight;
    private Value minWidth;
    private Value padBottom;
    private Value padLeft;
    private Value padRight;
    private Value padTop;
    private Value prefHeight;
    private Value prefWidth;
    private boolean round;

    public Container() {
        this.minWidth = Value.minWidth;
        this.minHeight = Value.minHeight;
        this.prefWidth = Value.prefWidth;
        this.prefHeight = Value.prefHeight;
        Value.Fixed fixed = Value.zero;
        this.maxWidth = fixed;
        this.maxHeight = fixed;
        this.padTop = fixed;
        this.padLeft = fixed;
        this.padBottom = fixed;
        this.padRight = fixed;
        this.round = true;
        setTouchable(Touchable.childrenOnly);
        setTransform(false);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAfter(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAt(int i2, Actor actor) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    public Container<T> align(int i2) {
        this.align = i2;
        return this;
    }

    public Container<T> background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    public Container<T> bottom() {
        this.align = (this.align | 4) & (-3);
        return this;
    }

    public Container<T> center() {
        this.align = 1;
        return this;
    }

    public Container<T> clip() {
        setClip(true);
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        validate();
        if (!isTransform()) {
            drawBackground(batch, f2, getX(), getY());
            super.draw(batch, f2);
            return;
        }
        applyTransform(batch, computeTransform());
        drawBackground(batch, f2, 0.0f, 0.0f);
        if (this.clip) {
            batch.flush();
            float f3 = this.padLeft.get(this);
            float f4 = this.padBottom.get(this);
            if (clipBegin(f3, f4, (getWidth() - f3) - this.padRight.get(this), (getHeight() - f4) - this.padTop.get(this))) {
                drawChildren(batch, f2);
                batch.flush();
                clipEnd();
            }
        } else {
            drawChildren(batch, f2);
        }
        resetTransform(batch);
    }

    protected void drawBackground(Batch batch, float f2, float f3, float f4) {
        if (this.background == null) {
            return;
        }
        Color color = getColor();
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
        this.background.draw(batch, f3, f4, getWidth(), getHeight());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        validate();
        if (!isTransform()) {
            super.drawDebug(shapeRenderer);
            return;
        }
        applyTransform(shapeRenderer, computeTransform());
        if (this.clip) {
            shapeRenderer.flush();
            float f2 = this.padLeft.get(this);
            float f3 = this.padBottom.get(this);
            if (this.background == null ? clipBegin(0.0f, 0.0f, getWidth(), getHeight()) : clipBegin(f2, f3, (getWidth() - f2) - this.padRight.get(this), (getHeight() - f3) - this.padTop.get(this))) {
                drawDebugChildren(shapeRenderer);
                clipEnd();
            }
        } else {
            drawDebugChildren(shapeRenderer);
        }
        resetTransform(shapeRenderer);
    }

    public Container<T> fill() {
        this.fillX = 1.0f;
        this.fillY = 1.0f;
        return this;
    }

    public Container<T> fillX() {
        this.fillX = 1.0f;
        return this;
    }

    public Container<T> fillY() {
        this.fillY = 1.0f;
        return this;
    }

    public T getActor() {
        return this.actor;
    }

    public int getAlign() {
        return this.align;
    }

    public Drawable getBackground() {
        return this.background;
    }

    public boolean getClip() {
        return this.clip;
    }

    public float getFillX() {
        return this.fillX;
    }

    public float getFillY() {
        return this.fillY;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxHeight() {
        float f2 = this.maxHeight.get(this.actor);
        if (f2 <= 0.0f) {
            return f2;
        }
        return f2 + this.padBottom.get(this) + this.padTop.get(this);
    }

    public Value getMaxHeightValue() {
        return this.maxHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxWidth() {
        float f2 = this.maxWidth.get(this.actor);
        if (f2 <= 0.0f) {
            return f2;
        }
        return f2 + this.padRight.get(this) + this.padLeft.get(this);
    }

    public Value getMaxWidthValue() {
        return this.maxWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        return this.padBottom.get(this) + this.padTop.get(this) + this.minHeight.get(this.actor);
    }

    public Value getMinHeightValue() {
        return this.minHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        return this.padRight.get(this) + this.padLeft.get(this) + this.minWidth.get(this.actor);
    }

    public float getPadBottom() {
        return this.padBottom.get(this);
    }

    public Value getPadBottomValue() {
        return this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft.get(this);
    }

    public Value getPadLeftValue() {
        return this.padLeft;
    }

    public float getPadRight() {
        return this.padRight.get(this);
    }

    public Value getPadRightValue() {
        return this.padRight;
    }

    public float getPadTop() {
        return this.padTop.get(this);
    }

    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPadX() {
        return this.padRight.get(this) + this.padLeft.get(this);
    }

    public float getPadY() {
        return this.padBottom.get(this) + this.padTop.get(this);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        float fMax = this.prefHeight.get(this.actor);
        Drawable drawable = this.background;
        if (drawable != null) {
            fMax = Math.max(fMax, drawable.getMinHeight());
        }
        return Math.max(getMinHeight(), this.padBottom.get(this) + this.padTop.get(this) + fMax);
    }

    public Value getPrefHeightValue() {
        return this.prefHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        float fMax = this.prefWidth.get(this.actor);
        Drawable drawable = this.background;
        if (drawable != null) {
            fMax = Math.max(fMax, drawable.getMinWidth());
        }
        return Math.max(getMinWidth(), this.padRight.get(this) + this.padLeft.get(this) + fMax);
    }

    public Value getPrefWidthValue() {
        return this.prefWidth;
    }

    public Container<T> height(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.minHeight = value;
        this.prefHeight = value;
        this.maxHeight = value;
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public Actor hit(float f2, float f3, boolean z2) {
        if (!this.clip || (!(z2 && getTouchable() == Touchable.disabled) && f2 >= 0.0f && f2 < getWidth() && f3 >= 0.0f && f3 < getHeight())) {
            return super.hit(f2, f3, z2);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layout() {
        float f2;
        float f3;
        T t2;
        if (this.actor == null) {
            return;
        }
        float fRound = this.padLeft.get(this);
        float fRound2 = this.padBottom.get(this);
        float width = (getWidth() - fRound) - this.padRight.get(this);
        float height = (getHeight() - fRound2) - this.padTop.get(this);
        float f4 = this.minWidth.get(this.actor);
        float f5 = this.minHeight.get(this.actor);
        float f6 = this.prefWidth.get(this.actor);
        float f7 = this.prefHeight.get(this.actor);
        float fRound3 = this.maxWidth.get(this.actor);
        float fRound4 = this.maxHeight.get(this.actor);
        float f8 = this.fillX;
        float fMin = f8 > 0.0f ? f8 * width : Math.min(f6, width);
        if (fMin >= f4) {
            f4 = fMin;
        }
        if (fRound3 <= 0.0f || f4 <= fRound3) {
            fRound3 = f4;
        }
        float f9 = this.fillY;
        float fMin2 = f9 > 0.0f ? f9 * height : Math.min(f7, height);
        if (fMin2 >= f5) {
            f5 = fMin2;
        }
        if (fRound4 <= 0.0f || f5 <= fRound4) {
            fRound4 = f5;
        }
        int i2 = this.align;
        if ((i2 & 16) != 0) {
            f2 = width - fRound3;
        } else {
            if ((i2 & 8) == 0) {
                f2 = (width - fRound3) / 2.0f;
            }
            if ((i2 & 2) != 0) {
                if ((i2 & 4) == 0) {
                    f3 = (height - fRound4) / 2.0f;
                }
                if (this.round) {
                    fRound = Math.round(fRound);
                    fRound2 = Math.round(fRound2);
                    fRound3 = Math.round(fRound3);
                    fRound4 = Math.round(fRound4);
                }
                this.actor.setBounds(fRound, fRound2, fRound3, fRound4);
                t2 = this.actor;
                if (t2 instanceof Layout) {
                    ((Layout) t2).validate();
                    return;
                }
                return;
            }
            f3 = height - fRound4;
            fRound2 += f3;
            if (this.round) {
            }
            this.actor.setBounds(fRound, fRound2, fRound3, fRound4);
            t2 = this.actor;
            if (t2 instanceof Layout) {
            }
        }
        fRound += f2;
        if ((i2 & 2) != 0) {
        }
        fRound2 += f3;
        if (this.round) {
        }
        this.actor.setBounds(fRound, fRound2, fRound3, fRound4);
        t2 = this.actor;
        if (t2 instanceof Layout) {
        }
    }

    public Container<T> left() {
        this.align = (this.align | 8) & (-17);
        return this;
    }

    public Container<T> maxHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("maxHeight cannot be null.");
        }
        this.maxHeight = value;
        return this;
    }

    public Container<T> maxSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.maxWidth = value;
        this.maxHeight = value;
        return this;
    }

    public Container<T> maxWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("maxWidth cannot be null.");
        }
        this.maxWidth = value;
        return this;
    }

    public Container<T> minHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("minHeight cannot be null.");
        }
        this.minHeight = value;
        return this;
    }

    public Container<T> minSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.minWidth = value;
        this.minHeight = value;
        return this;
    }

    public Container<T> minWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("minWidth cannot be null.");
        }
        this.minWidth = value;
        return this;
    }

    public Container<T> pad(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.padTop = value;
        this.padLeft = value;
        this.padBottom = value;
        this.padRight = value;
        return this;
    }

    public Container<T> padBottom(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.padBottom = value;
        return this;
    }

    public Container<T> padLeft(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.padLeft = value;
        return this;
    }

    public Container<T> padRight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.padRight = value;
        return this;
    }

    public Container<T> padTop(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.padTop = value;
        return this;
    }

    public Container<T> prefHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("prefHeight cannot be null.");
        }
        this.prefHeight = value;
        return this;
    }

    public Container<T> prefSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.prefWidth = value;
        this.prefHeight = value;
        return this;
    }

    public Container<T> prefWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("prefWidth cannot be null.");
        }
        this.prefWidth = value;
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.actor) {
            return false;
        }
        setActor(null);
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int i2, boolean z2) {
        Actor actorRemoveActorAt = super.removeActorAt(i2, z2);
        if (actorRemoveActorAt == this.actor) {
            this.actor = null;
        }
        return actorRemoveActorAt;
    }

    public Container<T> right() {
        this.align = (this.align | 16) & (-9);
        return this;
    }

    public void setActor(T t2) {
        if (t2 == this) {
            throw new IllegalArgumentException("actor cannot be the Container.");
        }
        T t3 = this.actor;
        if (t2 == t3) {
            return;
        }
        if (t3 != null) {
            super.removeActor(t3);
        }
        this.actor = t2;
        if (t2 != null) {
            super.addActor(t2);
        }
    }

    public void setBackground(Drawable drawable) {
        setBackground(drawable, true);
    }

    public void setClip(boolean z2) {
        this.clip = z2;
        setTransform(z2);
        invalidate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.utils.Cullable
    public void setCullingArea(p pVar) {
        super.setCullingArea(pVar);
        if (this.fillX == 1.0f && this.fillY == 1.0f) {
            T t2 = this.actor;
            if (t2 instanceof Cullable) {
                ((Cullable) t2).setCullingArea(pVar);
            }
        }
    }

    public void setRound(boolean z2) {
        this.round = z2;
    }

    public Container<T> size(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.minWidth = value;
        this.minHeight = value;
        this.prefWidth = value;
        this.prefHeight = value;
        this.maxWidth = value;
        this.maxHeight = value;
        return this;
    }

    public Container<T> top() {
        this.align = (this.align | 2) & (-5);
        return this;
    }

    public Container<T> width(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        this.minWidth = value;
        this.prefWidth = value;
        this.maxWidth = value;
        return this;
    }

    public Container<T> clip(boolean z2) {
        setClip(z2);
        return this;
    }

    public void setBackground(Drawable drawable, boolean z2) {
        if (this.background == drawable) {
            return;
        }
        this.background = drawable;
        if (z2) {
            if (drawable == null) {
                pad(Value.zero);
            } else {
                pad(drawable.getTopHeight(), drawable.getLeftWidth(), drawable.getBottomHeight(), drawable.getRightWidth());
            }
            invalidate();
        }
    }

    public Container<T> fill(float f2, float f3) {
        this.fillX = f2;
        this.fillY = f3;
        return this;
    }

    public Container<T> maxHeight(float f2) {
        this.maxHeight = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> maxWidth(float f2) {
        this.maxWidth = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> minHeight(float f2) {
        this.minHeight = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> minWidth(float f2) {
        this.minWidth = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> padBottom(float f2) {
        this.padBottom = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> padLeft(float f2) {
        this.padLeft = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> padRight(float f2) {
        this.padRight = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> padTop(float f2) {
        this.padTop = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> prefHeight(float f2) {
        this.prefHeight = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> prefWidth(float f2) {
        this.prefWidth = Value.Fixed.valueOf(f2);
        return this;
    }

    public Container<T> maxSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 != null) {
            this.maxWidth = value;
            this.maxHeight = value2;
            return this;
        }
        throw new IllegalArgumentException("height cannot be null.");
    }

    public Container<T> minSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 != null) {
            this.minWidth = value;
            this.minHeight = value2;
            return this;
        }
        throw new IllegalArgumentException("height cannot be null.");
    }

    public Container<T> prefSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 != null) {
            this.prefWidth = value;
            this.prefHeight = value2;
            return this;
        }
        throw new IllegalArgumentException("height cannot be null.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor, boolean z2) {
        if (actor != null) {
            if (actor != this.actor) {
                return false;
            }
            this.actor = null;
            return super.removeActor(actor, z2);
        }
        throw new IllegalArgumentException("actor cannot be null.");
    }

    public Container<T> fill(boolean z2, boolean z3) {
        this.fillX = z2 ? 1.0f : 0.0f;
        this.fillY = z3 ? 1.0f : 0.0f;
        return this;
    }

    public Container<T> height(float f2) {
        height(Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> width(float f2) {
        width(Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> pad(Value value, Value value2, Value value3, Value value4) {
        if (value == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if (value3 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if (value4 != null) {
            this.padTop = value;
            this.padLeft = value2;
            this.padBottom = value3;
            this.padRight = value4;
            return this;
        }
        throw new IllegalArgumentException("right cannot be null.");
    }

    public Container<T> fill(boolean z2) {
        this.fillX = z2 ? 1.0f : 0.0f;
        this.fillY = z2 ? 1.0f : 0.0f;
        return this;
    }

    public Container<T> maxSize(float f2) {
        maxSize(Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> minSize(float f2) {
        minSize(Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> prefSize(float f2, float f3) {
        prefSize(Value.Fixed.valueOf(f2), Value.Fixed.valueOf(f3));
        return this;
    }

    public Container<T> size(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 != null) {
            this.minWidth = value;
            this.minHeight = value2;
            this.prefWidth = value;
            this.prefHeight = value2;
            this.maxWidth = value;
            this.maxHeight = value2;
            return this;
        }
        throw new IllegalArgumentException("height cannot be null.");
    }

    public Container(T t2) {
        this();
        setActor(t2);
    }

    public Container<T> maxSize(float f2, float f3) {
        maxSize(Value.Fixed.valueOf(f2), Value.Fixed.valueOf(f3));
        return this;
    }

    public Container<T> minSize(float f2, float f3) {
        minSize(Value.Fixed.valueOf(f2), Value.Fixed.valueOf(f3));
        return this;
    }

    public Container<T> prefSize(float f2) {
        prefSize(Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> pad(float f2) {
        Value.Fixed fixedValueOf = Value.Fixed.valueOf(f2);
        this.padTop = fixedValueOf;
        this.padLeft = fixedValueOf;
        this.padBottom = fixedValueOf;
        this.padRight = fixedValueOf;
        return this;
    }

    public Container<T> size(float f2) {
        size(Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> size(float f2, float f3) {
        size(Value.Fixed.valueOf(f2), Value.Fixed.valueOf(f3));
        return this;
    }

    public Container<T> pad(float f2, float f3, float f4, float f5) {
        this.padTop = Value.Fixed.valueOf(f2);
        this.padLeft = Value.Fixed.valueOf(f3);
        this.padBottom = Value.Fixed.valueOf(f4);
        this.padRight = Value.Fixed.valueOf(f5);
        return this;
    }
}
