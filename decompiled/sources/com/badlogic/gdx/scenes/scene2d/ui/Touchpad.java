package com.badlogic.gdx.scenes.scene2d.ui;

import a0.b;
import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.d0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Touchpad extends Widget {
    private final b deadzoneBounds;
    private float deadzoneRadius;
    private final b knobBounds;
    private final q knobPercent;
    private final q knobPosition;
    boolean resetOnTouchUp;
    private TouchpadStyle style;
    private final b touchBounds;
    boolean touched;

    public static class TouchpadStyle {
        public Drawable background;
        public Drawable knob;

        public TouchpadStyle() {
        }

        public TouchpadStyle(Drawable drawable, Drawable drawable2) {
            this.background = drawable;
            this.knob = drawable2;
        }

        public TouchpadStyle(TouchpadStyle touchpadStyle) {
            this.background = touchpadStyle.background;
            this.knob = touchpadStyle.knob;
        }
    }

    public Touchpad(float f2, Skin skin) {
        this(f2, (TouchpadStyle) skin.get(TouchpadStyle.class));
    }

    void calculatePositionAndValue(float f2, float f3, boolean z2) {
        q qVar = this.knobPosition;
        float f4 = qVar.f91a;
        float f5 = qVar.f92b;
        q qVar2 = this.knobPercent;
        float f6 = qVar2.f91a;
        float f7 = qVar2.f92b;
        b bVar = this.knobBounds;
        float f8 = bVar.f31a;
        float f9 = bVar.f32b;
        qVar.f91a = f8;
        qVar.f92b = f9;
        qVar2.f91a = 0.0f;
        qVar2.f92b = 0.0f;
        if (!z2 && !this.deadzoneBounds.a(f2, f3)) {
            q qVar3 = this.knobPercent;
            float f10 = this.knobBounds.f33c;
            qVar3.f91a = (f2 - f8) / f10;
            qVar3.f92b = (f3 - f9) / f10;
            float fSqrt = (float) Math.sqrt((r6 * r6) + (r0 * r0));
            if (fSqrt > 1.0f) {
                q qVar4 = this.knobPercent;
                float f11 = 1.0f / fSqrt;
                qVar4.f91a *= f11;
                qVar4.f92b *= f11;
            }
            if (this.knobBounds.a(f2, f3)) {
                q qVar5 = this.knobPosition;
                qVar5.f91a = f2;
                qVar5.f92b = f3;
            } else {
                q qVar6 = this.knobPosition;
                qVar6.b(this.knobPercent);
                qVar6.a();
                b bVar2 = this.knobBounds;
                float f12 = bVar2.f33c;
                float f13 = qVar6.f91a * f12;
                qVar6.f91a = f13;
                float f14 = qVar6.f92b * f12;
                qVar6.f92b = f14;
                float f15 = bVar2.f31a;
                float f16 = bVar2.f32b;
                qVar6.f91a = f13 + f15;
                qVar6.f92b = f14 + f16;
            }
        }
        q qVar7 = this.knobPercent;
        if (f6 == qVar7.f91a && f7 == qVar7.f92b) {
            return;
        }
        ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) d0.d(ChangeListener.ChangeEvent.class);
        if (fire(changeEvent)) {
            q qVar8 = this.knobPercent;
            qVar8.f91a = f6;
            qVar8.f92b = f7;
            q qVar9 = this.knobPosition;
            qVar9.f91a = f4;
            qVar9.f92b = f5;
        }
        d0.a(changeEvent);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        validate();
        Color color = getColor();
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
        float x2 = getX();
        float y2 = getY();
        float width = getWidth();
        float height = getHeight();
        Drawable drawable = this.style.background;
        if (drawable != null) {
            drawable.draw(batch, x2, y2, width, height);
        }
        Drawable drawable2 = this.style.knob;
        if (drawable2 != null) {
            drawable2.draw(batch, x2 + (this.knobPosition.f91a - (drawable2.getMinWidth() / 2.0f)), (this.knobPosition.f92b - (drawable2.getMinHeight() / 2.0f)) + y2, drawable2.getMinWidth(), drawable2.getMinHeight());
        }
    }

    public float getKnobPercentX() {
        return this.knobPercent.f91a;
    }

    public float getKnobPercentY() {
        return this.knobPercent.f92b;
    }

    public float getKnobX() {
        return this.knobPosition.f91a;
    }

    public float getKnobY() {
        return this.knobPosition.f92b;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        Drawable drawable = this.style.background;
        if (drawable != null) {
            return drawable.getMinHeight();
        }
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        Drawable drawable = this.style.background;
        if (drawable != null) {
            return drawable.getMinWidth();
        }
        return 0.0f;
    }

    public boolean getResetOnTouchUp() {
        return this.resetOnTouchUp;
    }

    public TouchpadStyle getStyle() {
        return this.style;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public Actor hit(float f2, float f3, boolean z2) {
        if ((!z2 || getTouchable() == Touchable.enabled) && isVisible() && this.touchBounds.a(f2, f3)) {
            return this;
        }
        return null;
    }

    public boolean isTouched() {
        return this.touched;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        float fMin = Math.min(width, height);
        b bVar = this.touchBounds;
        bVar.f31a = width;
        bVar.f32b = height;
        bVar.f33c = fMin;
        Drawable drawable = this.style.knob;
        if (drawable != null) {
            fMin -= Math.max(drawable.getMinWidth(), this.style.knob.getMinHeight()) / 2.0f;
        }
        b bVar2 = this.knobBounds;
        bVar2.f31a = width;
        bVar2.f32b = height;
        bVar2.f33c = fMin;
        b bVar3 = this.deadzoneBounds;
        float f2 = this.deadzoneRadius;
        bVar3.f31a = width;
        bVar3.f32b = height;
        bVar3.f33c = f2;
        q qVar = this.knobPosition;
        qVar.f91a = width;
        qVar.f92b = height;
        q qVar2 = this.knobPercent;
        qVar2.f91a = 0.0f;
        qVar2.f92b = 0.0f;
    }

    public void setDeadzone(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("deadzoneRadius must be > 0");
        }
        this.deadzoneRadius = f2;
        invalidate();
    }

    public void setResetOnTouchUp(boolean z2) {
        this.resetOnTouchUp = z2;
    }

    public void setStyle(TouchpadStyle touchpadStyle) {
        if (touchpadStyle == null) {
            throw new IllegalArgumentException("style cannot be null");
        }
        this.style = touchpadStyle;
        invalidateHierarchy();
    }

    public Touchpad(float f2, Skin skin, String str) {
        this(f2, (TouchpadStyle) skin.get(str, TouchpadStyle.class));
    }

    public Touchpad(float f2, TouchpadStyle touchpadStyle) {
        this.resetOnTouchUp = true;
        this.knobBounds = new b(0);
        this.touchBounds = new b(0);
        this.deadzoneBounds = new b(0);
        q qVar = new q();
        this.knobPosition = qVar;
        this.knobPercent = new q();
        if (f2 >= 0.0f) {
            this.deadzoneRadius = f2;
            float width = getWidth() / 2.0f;
            float height = getHeight() / 2.0f;
            qVar.f91a = width;
            qVar.f92b = height;
            setStyle(touchpadStyle);
            setSize(getPrefWidth(), getPrefHeight());
            addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Touchpad.1
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f3, float f4, int i2, int i3) {
                    Touchpad touchpad = Touchpad.this;
                    if (touchpad.touched) {
                        return false;
                    }
                    touchpad.touched = true;
                    touchpad.calculatePositionAndValue(f3, f4, false);
                    return true;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void touchDragged(InputEvent inputEvent, float f3, float f4, int i2) {
                    Touchpad.this.calculatePositionAndValue(f3, f4, false);
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void touchUp(InputEvent inputEvent, float f3, float f4, int i2, int i3) {
                    Touchpad touchpad = Touchpad.this;
                    touchpad.touched = false;
                    touchpad.calculatePositionAndValue(f3, f4, touchpad.resetOnTouchUp);
                }
            });
            return;
        }
        throw new IllegalArgumentException("deadzoneRadius must be > 0");
    }
}
