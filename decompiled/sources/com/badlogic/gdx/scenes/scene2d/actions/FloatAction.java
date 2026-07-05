package com.badlogic.gdx.scenes.scene2d.actions;

import a.a;
import a0.h;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FloatAction extends TemporalAction {
    private float end;
    private float start;
    private float value;

    public FloatAction() {
        this.start = 0.0f;
        this.end = 1.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.value = this.start;
    }

    public float getEnd() {
        return this.end;
    }

    public float getStart() {
        return this.start;
    }

    public float getValue() {
        return this.value;
    }

    public void setEnd(float f2) {
        this.end = f2;
    }

    public void setStart(float f2) {
        this.start = f2;
    }

    public void setValue(float f2) {
        this.value = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        if (f2 == 0.0f) {
            this.value = this.start;
        } else if (f2 == 1.0f) {
            this.value = this.end;
        } else {
            float f3 = this.start;
            this.value = a.C(this.end, f3, f2, f3);
        }
    }

    public FloatAction(float f2, float f3) {
        this.start = f2;
        this.end = f3;
    }

    public FloatAction(float f2, float f3, float f4) {
        super(f4);
        this.start = f2;
        this.end = f3;
    }

    public FloatAction(float f2, float f3, float f4, h hVar) {
        super(f4, hVar);
        this.start = f2;
        this.end = f3;
    }
}
