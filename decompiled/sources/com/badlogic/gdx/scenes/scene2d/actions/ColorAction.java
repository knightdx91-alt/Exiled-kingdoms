package com.badlogic.gdx.scenes.scene2d.actions;

import a.a;
import com.badlogic.gdx.graphics.Color;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ColorAction extends TemporalAction {
    private Color color;
    private final Color end = new Color();
    private float startA;
    private float startB;
    private float startG;
    private float startR;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        if (this.color == null) {
            this.color = this.target.getColor();
        }
        Color color = this.color;
        this.startR = color.f1680r;
        this.startG = color.f1679g;
        this.startB = color.f1678b;
        this.startA = color.f1677a;
    }

    public Color getColor() {
        return this.color;
    }

    public Color getEndColor() {
        return this.end;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction, com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.color = null;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setEndColor(Color color) {
        this.end.set(color);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        if (f2 == 0.0f) {
            this.color.set(this.startR, this.startG, this.startB, this.startA);
            return;
        }
        if (f2 == 1.0f) {
            this.color.set(this.end);
            return;
        }
        float f3 = this.startR;
        Color color = this.end;
        float fC = a.C(color.f1680r, f3, f2, f3);
        float f4 = this.startG;
        float fC2 = a.C(color.f1679g, f4, f2, f4);
        float f5 = this.startB;
        float fC3 = a.C(color.f1678b, f5, f2, f5);
        float f6 = this.startA;
        this.color.set(fC, fC2, fC3, a.C(color.f1677a, f6, f2, f6));
    }
}
