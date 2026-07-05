package com.badlogic.gdx.scenes.scene2d.actions;

import a.a;
import a0.j;
import a0.o;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RotateToAction extends TemporalAction {
    private float end;
    private float start;
    private boolean useShortestDirection;

    public RotateToAction() {
        this.useShortestDirection = false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.start = this.target.getRotation();
    }

    public float getRotation() {
        return this.end;
    }

    public boolean isUseShortestDirection() {
        return this.useShortestDirection;
    }

    public void setRotation(float f2) {
        this.end = f2;
    }

    public void setUseShortestDirection(boolean z2) {
        this.useShortestDirection = z2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        float fC;
        if (f2 == 0.0f) {
            fC = this.start;
        } else if (f2 == 1.0f) {
            fC = this.end;
        } else if (this.useShortestDirection) {
            float f3 = this.start;
            float f4 = this.end;
            o oVar = j.f69a;
            fC = ((((((((f4 - f3) + 360.0f) + 180.0f) % 360.0f) - 180.0f) * f2) + f3) + 360.0f) % 360.0f;
        } else {
            float f5 = this.start;
            fC = a.C(this.end, f5, f2, f5);
        }
        this.target.setRotation(fC);
    }

    public RotateToAction(boolean z2) {
        this.useShortestDirection = z2;
    }
}
