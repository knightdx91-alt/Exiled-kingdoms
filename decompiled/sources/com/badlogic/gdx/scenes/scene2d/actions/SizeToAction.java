package com.badlogic.gdx.scenes.scene2d.actions;

import a.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SizeToAction extends TemporalAction {
    private float endHeight;
    private float endWidth;
    private float startHeight;
    private float startWidth;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startWidth = this.target.getWidth();
        this.startHeight = this.target.getHeight();
    }

    public float getHeight() {
        return this.endHeight;
    }

    public float getWidth() {
        return this.endWidth;
    }

    public void setHeight(float f2) {
        this.endHeight = f2;
    }

    public void setSize(float f2, float f3) {
        this.endWidth = f2;
        this.endHeight = f3;
    }

    public void setWidth(float f2) {
        this.endWidth = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        float fC;
        float f3;
        if (f2 == 0.0f) {
            f3 = this.startWidth;
            fC = this.startHeight;
        } else if (f2 == 1.0f) {
            f3 = this.endWidth;
            fC = this.endHeight;
        } else {
            float f4 = this.startWidth;
            float fC2 = a.C(this.endWidth, f4, f2, f4);
            float f5 = this.startHeight;
            fC = a.C(this.endHeight, f5, f2, f5);
            f3 = fC2;
        }
        this.target.setSize(f3, fC);
    }
}
