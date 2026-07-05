package com.badlogic.gdx.scenes.scene2d.actions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SizeByAction extends RelativeTemporalAction {
    private float amountHeight;
    private float amountWidth;

    public float getAmountHeight() {
        return this.amountHeight;
    }

    public float getAmountWidth() {
        return this.amountWidth;
    }

    public void setAmount(float f2, float f3) {
        this.amountWidth = f2;
        this.amountHeight = f3;
    }

    public void setAmountHeight(float f2) {
        this.amountHeight = f2;
    }

    public void setAmountWidth(float f2) {
        this.amountWidth = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction
    protected void updateRelative(float f2) {
        this.target.sizeBy(this.amountWidth * f2, this.amountHeight * f2);
    }
}
