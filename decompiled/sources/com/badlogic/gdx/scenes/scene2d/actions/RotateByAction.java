package com.badlogic.gdx.scenes.scene2d.actions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RotateByAction extends RelativeTemporalAction {
    private float amount;

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f2) {
        this.amount = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction
    protected void updateRelative(float f2) {
        this.target.rotateBy(this.amount * f2);
    }
}
