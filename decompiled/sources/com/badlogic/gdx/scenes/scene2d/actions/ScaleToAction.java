package com.badlogic.gdx.scenes.scene2d.actions;

import a.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ScaleToAction extends TemporalAction {
    private float endX;
    private float endY;
    private float startX;
    private float startY;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startX = this.target.getScaleX();
        this.startY = this.target.getScaleY();
    }

    public float getX() {
        return this.endX;
    }

    public float getY() {
        return this.endY;
    }

    public void setScale(float f2, float f3) {
        this.endX = f2;
        this.endY = f3;
    }

    public void setX(float f2) {
        this.endX = f2;
    }

    public void setY(float f2) {
        this.endY = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        float fC;
        float f3;
        if (f2 == 0.0f) {
            f3 = this.startX;
            fC = this.startY;
        } else if (f2 == 1.0f) {
            f3 = this.endX;
            fC = this.endY;
        } else {
            float f4 = this.startX;
            float fC2 = a.C(this.endX, f4, f2, f4);
            float f5 = this.startY;
            fC = a.C(this.endY, f5, f2, f5);
            f3 = fC2;
        }
        this.target.setScale(f3, fC);
    }

    public void setScale(float f2) {
        this.endX = f2;
        this.endY = f2;
    }
}
