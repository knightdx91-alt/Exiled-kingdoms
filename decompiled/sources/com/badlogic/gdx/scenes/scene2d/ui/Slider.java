package com.badlogic.gdx.scenes.scene2d.ui;

import a0.h;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.d0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Slider extends ProgressBar {
    int button;
    int draggingPointer;
    boolean mouseOver;
    private float[] snapValues;
    private float threshold;
    private h visualInterpolationInverse;

    public static class SliderStyle extends ProgressBar.ProgressBarStyle {
        public Drawable backgroundDown;
        public Drawable backgroundOver;
        public Drawable knobAfterDown;
        public Drawable knobAfterOver;
        public Drawable knobBeforeDown;
        public Drawable knobBeforeOver;
        public Drawable knobDown;
        public Drawable knobOver;

        public SliderStyle() {
        }

        public SliderStyle(Drawable drawable, Drawable drawable2) {
            super(drawable, drawable2);
        }

        public SliderStyle(SliderStyle sliderStyle) {
            super(sliderStyle);
            this.backgroundOver = sliderStyle.backgroundOver;
            this.backgroundDown = sliderStyle.backgroundDown;
            this.knobOver = sliderStyle.knobOver;
            this.knobDown = sliderStyle.knobDown;
            this.knobBeforeOver = sliderStyle.knobBeforeOver;
            this.knobBeforeDown = sliderStyle.knobBeforeDown;
            this.knobAfterOver = sliderStyle.knobAfterOver;
            this.knobAfterDown = sliderStyle.knobAfterDown;
        }
    }

    public Slider(float f2, float f3, float f4, boolean z2, Skin skin) {
        this(f2, f3, f4, z2, (SliderStyle) skin.get("default-".concat(z2 ? "vertical" : "horizontal"), SliderStyle.class));
    }

    boolean calculatePositionAndValue(float f2, float f3) {
        float fA;
        Drawable drawable = getStyle().knob;
        Drawable backgroundDrawable = getBackgroundDrawable();
        float f4 = this.position;
        float minValue = getMinValue();
        float maxValue = getMaxValue();
        if (this.vertical) {
            float height = (getHeight() - backgroundDrawable.getTopHeight()) - backgroundDrawable.getBottomHeight();
            float minHeight = drawable == null ? 0.0f : drawable.getMinHeight();
            float bottomHeight = (f3 - backgroundDrawable.getBottomHeight()) - (0.5f * minHeight);
            this.position = bottomHeight;
            float f5 = height - minHeight;
            fA = (this.visualInterpolationInverse.a(bottomHeight / f5) * (maxValue - minValue)) + minValue;
            float fMax = Math.max(Math.min(0.0f, backgroundDrawable.getBottomHeight()), this.position);
            this.position = fMax;
            this.position = Math.min(f5, fMax);
        } else {
            float width = (getWidth() - backgroundDrawable.getLeftWidth()) - backgroundDrawable.getRightWidth();
            float minWidth = drawable == null ? 0.0f : drawable.getMinWidth();
            float leftWidth = (f2 - backgroundDrawable.getLeftWidth()) - (0.5f * minWidth);
            this.position = leftWidth;
            float f6 = width - minWidth;
            float fA2 = this.visualInterpolationInverse.a(leftWidth / f6);
            float fMax2 = Math.max(Math.min(0.0f, backgroundDrawable.getLeftWidth()), this.position);
            this.position = fMax2;
            this.position = Math.min(f6, fMax2);
            fA = (fA2 * (maxValue - minValue)) + minValue;
        }
        float fSnap = (Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60)) ? fA : snap(fA);
        boolean value = setValue(fSnap);
        if (fSnap == fA) {
            this.position = f4;
        }
        return value;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getBackgroundDrawable() {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || (drawable3 = sliderStyle.disabledBackground) == null) ? (!isDragging() || (drawable2 = sliderStyle.backgroundDown) == null) ? (!this.mouseOver || (drawable = sliderStyle.backgroundOver) == null) ? sliderStyle.background : drawable : drawable2 : drawable3;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getKnobAfterDrawable() {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || (drawable3 = sliderStyle.disabledKnobAfter) == null) ? (!isDragging() || (drawable2 = sliderStyle.knobAfterDown) == null) ? (!this.mouseOver || (drawable = sliderStyle.knobAfterOver) == null) ? sliderStyle.knobAfter : drawable : drawable2 : drawable3;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getKnobBeforeDrawable() {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || (drawable3 = sliderStyle.disabledKnobBefore) == null) ? (!isDragging() || (drawable2 = sliderStyle.knobBeforeDown) == null) ? (!this.mouseOver || (drawable = sliderStyle.knobBeforeOver) == null) ? sliderStyle.knobBefore : drawable : drawable2 : drawable3;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getKnobDrawable() {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || (drawable3 = sliderStyle.disabledKnob) == null) ? (!isDragging() || (drawable2 = sliderStyle.knobDown) == null) ? (!this.mouseOver || (drawable = sliderStyle.knobOver) == null) ? sliderStyle.knob : drawable : drawable2 : drawable3;
    }

    public boolean isDragging() {
        return this.draggingPointer != -1;
    }

    public boolean isOver() {
        return this.mouseOver;
    }

    public void setButton(int i2) {
        this.button = i2;
    }

    public void setSnapToValues(float[] fArr, float f2) {
        this.snapValues = fArr;
        this.threshold = f2;
    }

    public void setVisualInterpolationInverse(h hVar) {
        this.visualInterpolationInverse = hVar;
    }

    public void setVisualPercent(float f2) {
        float f3 = this.min;
        setValue((this.visualInterpolationInverse.a(f2) * (this.max - f3)) + f3);
    }

    protected float snap(float f2) {
        float[] fArr = this.snapValues;
        if (fArr == null || fArr.length == 0) {
            return f2;
        }
        float f3 = 0.0f;
        int i2 = 0;
        float f4 = -1.0f;
        while (true) {
            float[] fArr2 = this.snapValues;
            if (i2 >= fArr2.length) {
                break;
            }
            float f5 = fArr2[i2];
            float fAbs = Math.abs(f2 - f5);
            if (fAbs <= this.threshold && (f4 == -1.0f || fAbs < f4)) {
                f3 = f5;
                f4 = fAbs;
            }
            i2++;
        }
        return f4 == -1.0f ? f2 : f3;
    }

    public Slider(float f2, float f3, float f4, boolean z2, Skin skin, String str) {
        this(f2, f3, f4, z2, (SliderStyle) skin.get(str, SliderStyle.class));
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    public SliderStyle getStyle() {
        return (SliderStyle) super.getStyle();
    }

    public Slider(float f2, float f3, float f4, boolean z2, SliderStyle sliderStyle) {
        super(f2, f3, f4, z2, sliderStyle);
        this.button = -1;
        this.draggingPointer = -1;
        this.visualInterpolationInverse = h.f52a;
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Slider.1
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f5, float f6, int i2, Actor actor) {
                if (i2 == -1) {
                    Slider.this.mouseOver = true;
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f5, float f6, int i2, Actor actor) {
                if (i2 == -1) {
                    Slider.this.mouseOver = false;
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f5, float f6, int i2, int i3) {
                Slider slider = Slider.this;
                if (slider.disabled) {
                    return false;
                }
                int i4 = slider.button;
                if ((i4 != -1 && i4 != i3) || slider.draggingPointer != -1) {
                    return false;
                }
                slider.draggingPointer = i2;
                slider.calculatePositionAndValue(f5, f6);
                return true;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f5, float f6, int i2) {
                Slider.this.calculatePositionAndValue(f5, f6);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f5, float f6, int i2, int i3) {
                Slider slider = Slider.this;
                if (i2 != slider.draggingPointer) {
                    return;
                }
                slider.draggingPointer = -1;
                if (inputEvent.isTouchFocusCancel() || !Slider.this.calculatePositionAndValue(f5, f6)) {
                    ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) d0.d(ChangeListener.ChangeEvent.class);
                    Slider.this.fire(changeEvent);
                    d0.a(changeEvent);
                }
            }
        });
    }
}
