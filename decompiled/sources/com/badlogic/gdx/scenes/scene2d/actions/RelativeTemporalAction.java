package com.badlogic.gdx.scenes.scene2d.actions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class RelativeTemporalAction extends TemporalAction {
    private float lastPercent;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.lastPercent = 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f2) {
        updateRelative(f2 - this.lastPercent);
        this.lastPercent = f2;
    }

    protected abstract void updateRelative(float f2);
}
