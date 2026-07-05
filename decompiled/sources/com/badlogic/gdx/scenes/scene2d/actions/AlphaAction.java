package com.badlogic.gdx.scenes.scene2d.actions;

import a.a;
import com.badlogic.gdx.graphics.Color;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AlphaAction extends TemporalAction {
    private Color color;
    private float end;
    private float start;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        if (this.color == null) {
            this.color = this.target.getColor();
        }
        this.start = this.color.f1677a;
    }

    public float getAlpha() {
        return this.end;
    }

    public Color getColor() {
        return this.color;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction, com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.color = null;
    }

    public void setAlpha(float f2) {
        this.end = f2;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        if (f2 == 0.0f) {
            this.color.f1677a = this.start;
        } else if (f2 == 1.0f) {
            this.color.f1677a = this.end;
        } else {
            Color color = this.color;
            float f3 = this.start;
            color.f1677a = a.C(this.end, f3, f2, f3);
        }
    }
}
