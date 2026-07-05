package com.badlogic.gdx.scenes.scene2d.actions;

import a0.h;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class IntAction extends TemporalAction {
    private int end;
    private int start;
    private int value;

    public IntAction() {
        this.start = 0;
        this.end = 1;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.value = this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getStart() {
        return this.start;
    }

    public int getValue() {
        return this.value;
    }

    public void setEnd(int i2) {
        this.end = i2;
    }

    public void setStart(int i2) {
        this.start = i2;
    }

    public void setValue(int i2) {
        this.value = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        if (f2 == 0.0f) {
            this.value = this.start;
        } else {
            if (f2 == 1.0f) {
                this.value = this.end;
                return;
            }
            this.value = (int) (((this.end - r0) * f2) + this.start);
        }
    }

    public IntAction(int i2, int i3) {
        this.start = i2;
        this.end = i3;
    }

    public IntAction(int i2, int i3, float f2) {
        super(f2);
        this.start = i2;
        this.end = i3;
    }

    public IntAction(int i2, int i3, float f2, h hVar) {
        super(f2, hVar);
        this.start = i2;
        this.end = i3;
    }
}
