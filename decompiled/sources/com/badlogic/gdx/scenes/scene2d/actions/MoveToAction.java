package com.badlogic.gdx.scenes.scene2d.actions;

import a.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MoveToAction extends TemporalAction {
    private int alignment = 12;
    private float endX;
    private float endY;
    private float startX;
    private float startY;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startX = this.target.getX(this.alignment);
        this.startY = this.target.getY(this.alignment);
    }

    public int getAlignment() {
        return this.alignment;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public float getX() {
        return this.endX;
    }

    public float getY() {
        return this.endY;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction, com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.alignment = 12;
    }

    public void setAlignment(int i2) {
        this.alignment = i2;
    }

    public void setPosition(float f2, float f3) {
        this.endX = f2;
        this.endY = f3;
    }

    public void setStartPosition(float f2, float f3) {
        this.startX = f2;
        this.startY = f3;
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
        this.target.setPosition(f3, fC, this.alignment);
    }

    public void setPosition(float f2, float f3, int i2) {
        this.endX = f2;
        this.endY = f3;
        this.alignment = i2;
    }
}
