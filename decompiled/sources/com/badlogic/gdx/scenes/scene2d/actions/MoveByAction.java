package com.badlogic.gdx.scenes.scene2d.actions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MoveByAction extends RelativeTemporalAction {
    private float amountX;
    private float amountY;

    public float getAmountX() {
        return this.amountX;
    }

    public float getAmountY() {
        return this.amountY;
    }

    public void setAmount(float f2, float f3) {
        this.amountX = f2;
        this.amountY = f3;
    }

    public void setAmountX(float f2) {
        this.amountX = f2;
    }

    public void setAmountY(float f2) {
        this.amountY = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction
    protected void updateRelative(float f2) {
        this.target.moveBy(this.amountX * f2, this.amountY * f2);
    }
}
