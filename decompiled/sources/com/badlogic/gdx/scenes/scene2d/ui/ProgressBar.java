package com.badlogic.gdx.scenes.scene2d.ui;

import a.a;
import a0.h;
import a0.j;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.d0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ProgressBar extends Widget implements Disableable {
    private float animateDuration;
    private float animateFromValue;
    private h animateInterpolation;
    private float animateTime;
    boolean disabled;
    float max;
    float min;
    float position;
    private boolean programmaticChangeEvents;
    private boolean round;
    float stepSize;
    private ProgressBarStyle style;
    private float value;
    final boolean vertical;
    private h visualInterpolation;

    public static class ProgressBarStyle {
        public Drawable background;
        public Drawable disabledBackground;
        public Drawable disabledKnob;
        public Drawable disabledKnobAfter;
        public Drawable disabledKnobBefore;
        public Drawable knob;
        public Drawable knobAfter;
        public Drawable knobBefore;

        public ProgressBarStyle() {
        }

        public ProgressBarStyle(Drawable drawable, Drawable drawable2) {
            this.background = drawable;
            this.knob = drawable2;
        }

        public ProgressBarStyle(ProgressBarStyle progressBarStyle) {
            this.background = progressBarStyle.background;
            this.disabledBackground = progressBarStyle.disabledBackground;
            this.knob = progressBarStyle.knob;
            this.disabledKnob = progressBarStyle.disabledKnob;
            this.knobBefore = progressBarStyle.knobBefore;
            this.disabledKnobBefore = progressBarStyle.disabledKnobBefore;
            this.knobAfter = progressBarStyle.knobAfter;
            this.disabledKnobAfter = progressBarStyle.disabledKnobAfter;
        }
    }

    public ProgressBar(float f2, float f3, float f4, boolean z2, Skin skin) {
        this(f2, f3, f4, z2, (ProgressBarStyle) skin.get("default-".concat(z2 ? "vertical" : "horizontal"), ProgressBarStyle.class));
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f2) {
        super.act(f2);
        float f3 = this.animateTime;
        if (f3 > 0.0f) {
            this.animateTime = f3 - f2;
            Stage stage = getStage();
            if (stage == null || !stage.getActionsRequestRendering()) {
                return;
            }
            Gdx.graphics.requestRendering();
        }
    }

    protected float clamp(float f2) {
        return j.a(f2, this.min, this.max);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        float f3;
        float leftWidth;
        float f4;
        float minWidth;
        float fRound;
        float f5;
        float fRound2;
        float fRound3;
        Drawable drawable;
        float f6;
        float f7;
        float topHeight;
        float f8;
        float fRound4;
        float fRound5;
        float f9;
        Drawable drawable2 = this.style.knob;
        Drawable knobDrawable = getKnobDrawable();
        Drawable backgroundDrawable = getBackgroundDrawable();
        Drawable knobBeforeDrawable = getKnobBeforeDrawable();
        Drawable knobAfterDrawable = getKnobAfterDrawable();
        Color color = getColor();
        float x2 = getX();
        float y2 = getY();
        float width = getWidth();
        float height = getHeight();
        float minHeight = drawable2 == null ? 0.0f : drawable2.getMinHeight();
        float minWidth2 = drawable2 == null ? 0.0f : drawable2.getMinWidth();
        float visualPercent = getVisualPercent();
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
        if (!this.vertical) {
            if (backgroundDrawable != null) {
                if (this.round) {
                    backgroundDrawable.draw(batch, x2, Math.round(((height - backgroundDrawable.getMinHeight()) * 0.5f) + y2), width, Math.round(backgroundDrawable.getMinHeight()));
                } else {
                    backgroundDrawable.draw(batch, x2, ((height - backgroundDrawable.getMinHeight()) * 0.5f) + y2, width, backgroundDrawable.getMinHeight());
                }
                leftWidth = backgroundDrawable.getLeftWidth();
                float rightWidth = backgroundDrawable.getRightWidth();
                f3 = width - (leftWidth + rightWidth);
                f4 = rightWidth;
            } else {
                f3 = width;
                leftWidth = 0.0f;
                f4 = 0.0f;
            }
            if (drawable2 == null) {
                minWidth = knobBeforeDrawable != null ? knobBeforeDrawable.getMinWidth() * 0.5f : 0.0f;
                float f10 = f3 - minWidth;
                float f11 = f10 * visualPercent;
                this.position = f11;
                this.position = Math.min(f10, f11);
            } else {
                minWidth = minWidth2 * 0.5f;
                float f12 = f3 - minWidth2;
                float f13 = f12 * visualPercent;
                this.position = f13;
                this.position = Math.min(f12, f13) + leftWidth;
            }
            this.position = Math.max(leftWidth, this.position);
            if (knobBeforeDrawable != null) {
                if (this.round) {
                    knobBeforeDrawable.draw(batch, Math.round(leftWidth + x2), Math.round(((height - knobBeforeDrawable.getMinHeight()) * 0.5f) + y2), Math.round(this.position + minWidth), Math.round(knobBeforeDrawable.getMinHeight()));
                } else {
                    knobBeforeDrawable.draw(batch, x2 + leftWidth, ((height - knobBeforeDrawable.getMinHeight()) * 0.5f) + y2, this.position + minWidth, knobBeforeDrawable.getMinHeight());
                }
            }
            if (knobAfterDrawable != null) {
                if (this.round) {
                    knobAfterDrawable.draw(batch, Math.round(this.position + x2 + minWidth), Math.round(((height - knobAfterDrawable.getMinHeight()) * 0.5f) + y2), Math.round(((width - this.position) - minWidth) - f4), Math.round(knobAfterDrawable.getMinHeight()));
                } else {
                    knobAfterDrawable.draw(batch, this.position + x2 + minWidth, ((height - knobAfterDrawable.getMinHeight()) * 0.5f) + y2, ((width - this.position) - minWidth) - f4, knobAfterDrawable.getMinHeight());
                }
            }
            if (knobDrawable != null) {
                float minWidth3 = knobDrawable.getMinWidth();
                float minHeight2 = knobDrawable.getMinHeight();
                float f14 = ((minWidth2 - minWidth3) * 0.5f) + this.position + x2;
                float fC = a.C(height, minHeight2, 0.5f, y2);
                if (this.round) {
                    fRound3 = Math.round(f14);
                    float fRound6 = Math.round(fC);
                    fRound = Math.round(minWidth3);
                    f5 = fRound6;
                    fRound2 = Math.round(minHeight2);
                } else {
                    fRound = minWidth3;
                    f5 = fC;
                    fRound2 = minHeight2;
                    fRound3 = f14;
                }
                knobDrawable.draw(batch, fRound3, f5, fRound, fRound2);
                return;
            }
            return;
        }
        if (backgroundDrawable != null) {
            if (this.round) {
                drawable = knobDrawable;
                f6 = 0.5f;
                backgroundDrawable.draw(batch, Math.round(((width - backgroundDrawable.getMinWidth()) * 0.5f) + x2), y2, Math.round(backgroundDrawable.getMinWidth()), height);
            } else {
                drawable = knobDrawable;
                f6 = 0.5f;
                backgroundDrawable.draw(batch, ((width - backgroundDrawable.getMinWidth()) * 0.5f) + x2, y2, backgroundDrawable.getMinWidth(), height);
            }
            topHeight = backgroundDrawable.getTopHeight();
            float bottomHeight = backgroundDrawable.getBottomHeight();
            f7 = height - (topHeight + bottomHeight);
            f8 = bottomHeight;
        } else {
            drawable = knobDrawable;
            f6 = 0.5f;
            f7 = height;
            topHeight = 0.0f;
            f8 = 0.0f;
        }
        if (drawable2 == null) {
            minWidth = knobBeforeDrawable != null ? knobBeforeDrawable.getMinHeight() * f6 : 0.0f;
            float f15 = f7 - minWidth;
            float f16 = f15 * visualPercent;
            this.position = f16;
            this.position = Math.min(f15, f16);
        } else {
            minWidth = minHeight * f6;
            float f17 = f7 - minHeight;
            float f18 = f17 * visualPercent;
            this.position = f18;
            this.position = Math.min(f17, f18) + f8;
        }
        this.position = Math.max(f8, this.position);
        if (knobBeforeDrawable != null) {
            if (this.round) {
                knobBeforeDrawable.draw(batch, Math.round(((width - knobBeforeDrawable.getMinWidth()) * f6) + x2), Math.round(topHeight + y2), Math.round(knobBeforeDrawable.getMinWidth()), Math.round(this.position + minWidth));
            } else {
                knobBeforeDrawable.draw(batch, ((width - knobBeforeDrawable.getMinWidth()) * f6) + x2, y2 + topHeight, knobBeforeDrawable.getMinWidth(), this.position + minWidth);
            }
        }
        if (knobAfterDrawable != null) {
            if (this.round) {
                knobAfterDrawable.draw(batch, Math.round(((width - knobAfterDrawable.getMinWidth()) * f6) + x2), Math.round(this.position + y2 + minWidth), Math.round(knobAfterDrawable.getMinWidth()), Math.round(((height - this.position) - minWidth) - f8));
            } else {
                knobAfterDrawable.draw(batch, ((width - knobAfterDrawable.getMinWidth()) * f6) + x2, this.position + y2 + minWidth, knobAfterDrawable.getMinWidth(), ((height - this.position) - minWidth) - f8);
            }
        }
        if (drawable != null) {
            float minWidth4 = drawable.getMinWidth();
            float minHeight3 = drawable.getMinHeight();
            float fC2 = a.C(width, minWidth4, f6, x2);
            float f19 = ((minHeight - minHeight3) * f6) + this.position + y2;
            if (this.round) {
                float fRound7 = Math.round(fC2);
                float fRound8 = Math.round(f19);
                fRound4 = Math.round(minWidth4);
                fRound5 = Math.round(minHeight3);
                f9 = fRound8;
                fC2 = fRound7;
            } else {
                fRound4 = minWidth4;
                fRound5 = minHeight3;
                f9 = f19;
            }
            drawable.draw(batch, fC2, f9, fRound4, fRound5);
        }
    }

    protected Drawable getBackgroundDrawable() {
        Drawable drawable;
        return (!this.disabled || (drawable = this.style.disabledBackground) == null) ? this.style.background : drawable;
    }

    protected Drawable getKnobAfterDrawable() {
        Drawable drawable;
        return (!this.disabled || (drawable = this.style.disabledKnobAfter) == null) ? this.style.knobAfter : drawable;
    }

    protected Drawable getKnobBeforeDrawable() {
        Drawable drawable;
        return (!this.disabled || (drawable = this.style.disabledKnobBefore) == null) ? this.style.knobBefore : drawable;
    }

    protected Drawable getKnobDrawable() {
        Drawable drawable;
        return (!this.disabled || (drawable = this.style.disabledKnob) == null) ? this.style.knob : drawable;
    }

    protected float getKnobPosition() {
        return this.position;
    }

    public float getMaxValue() {
        return this.max;
    }

    public float getMinValue() {
        return this.min;
    }

    public float getPercent() {
        float f2 = this.min;
        float f3 = this.max;
        if (f2 == f3) {
            return 0.0f;
        }
        return (this.value - f2) / (f3 - f2);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.vertical) {
            return 140.0f;
        }
        Drawable drawable = this.style.knob;
        Drawable backgroundDrawable = getBackgroundDrawable();
        return Math.max(drawable == null ? 0.0f : drawable.getMinHeight(), backgroundDrawable != null ? backgroundDrawable.getMinHeight() : 0.0f);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (!this.vertical) {
            return 140.0f;
        }
        Drawable drawable = this.style.knob;
        Drawable backgroundDrawable = getBackgroundDrawable();
        return Math.max(drawable == null ? 0.0f : drawable.getMinWidth(), backgroundDrawable != null ? backgroundDrawable.getMinWidth() : 0.0f);
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public ProgressBarStyle getStyle() {
        return this.style;
    }

    public float getValue() {
        return this.value;
    }

    public float getVisualPercent() {
        if (this.min == this.max) {
            return 0.0f;
        }
        h hVar = this.visualInterpolation;
        float visualValue = getVisualValue();
        float f2 = this.min;
        return hVar.a((visualValue - f2) / (this.max - f2));
    }

    public float getVisualValue() {
        float f2 = this.animateTime;
        if (f2 <= 0.0f) {
            return this.value;
        }
        h hVar = this.animateInterpolation;
        float f3 = this.animateFromValue;
        float f4 = this.value;
        return (hVar.a(1.0f - (f2 / this.animateDuration)) * (f4 - f3)) + f3;
    }

    public boolean isAnimating() {
        return this.animateTime > 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    public boolean isVertical() {
        return this.vertical;
    }

    protected float round(float f2) {
        return Math.round(f2 / this.stepSize) * this.stepSize;
    }

    public void setAnimateDuration(float f2) {
        this.animateDuration = f2;
    }

    public void setAnimateInterpolation(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("animateInterpolation cannot be null.");
        }
        this.animateInterpolation = hVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z2) {
        this.disabled = z2;
    }

    public void setProgrammaticChangeEvents(boolean z2) {
        this.programmaticChangeEvents = z2;
    }

    public void setRange(float f2, float f3) {
        if (f2 > f3) {
            throw new IllegalArgumentException("min must be <= max: " + f2 + " <= " + f3);
        }
        this.min = f2;
        this.max = f3;
        float f4 = this.value;
        if (f4 < f2) {
            setValue(f2);
        } else if (f4 > f3) {
            setValue(f3);
        }
    }

    public void setRound(boolean z2) {
        this.round = z2;
    }

    public void setStepSize(float f2) {
        if (f2 > 0.0f) {
            this.stepSize = f2;
        } else {
            throw new IllegalArgumentException("steps must be > 0: " + f2);
        }
    }

    public void setStyle(ProgressBarStyle progressBarStyle) {
        if (progressBarStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = progressBarStyle;
        invalidateHierarchy();
    }

    public boolean setValue(float f2) {
        float fClamp = clamp(round(f2));
        float f3 = this.value;
        if (fClamp == f3) {
            return false;
        }
        float visualValue = getVisualValue();
        this.value = fClamp;
        if (this.programmaticChangeEvents) {
            ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) d0.d(ChangeListener.ChangeEvent.class);
            boolean zFire = fire(changeEvent);
            d0.a(changeEvent);
            if (zFire) {
                this.value = f3;
                return false;
            }
        }
        float f4 = this.animateDuration;
        if (f4 <= 0.0f) {
            return true;
        }
        this.animateFromValue = visualValue;
        this.animateTime = f4;
        return true;
    }

    public void setVisualInterpolation(h hVar) {
        this.visualInterpolation = hVar;
    }

    public void updateVisualValue() {
        this.animateTime = 0.0f;
    }

    public ProgressBar(float f2, float f3, float f4, boolean z2, Skin skin, String str) {
        this(f2, f3, f4, z2, (ProgressBarStyle) skin.get(str, ProgressBarStyle.class));
    }

    public ProgressBar(float f2, float f3, float f4, boolean z2, ProgressBarStyle progressBarStyle) {
        h hVar = h.f52a;
        this.animateInterpolation = hVar;
        this.visualInterpolation = hVar;
        this.round = true;
        this.programmaticChangeEvents = true;
        if (f2 > f3) {
            throw new IllegalArgumentException("max must be > min. min,max: " + f2 + ", " + f3);
        }
        if (f4 > 0.0f) {
            setStyle(progressBarStyle);
            this.min = f2;
            this.max = f3;
            this.stepSize = f4;
            this.vertical = z2;
            this.value = f2;
            setSize(getPrefWidth(), getPrefHeight());
            return;
        }
        throw new IllegalArgumentException("stepSize must be > 0: " + f4);
    }
}
