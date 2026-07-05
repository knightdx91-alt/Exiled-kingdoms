package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TimeScaleAction extends DelegateAction {
    private float scale;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f2) {
        Action action = this.action;
        if (action == null) {
            return true;
        }
        return action.act(f2 * this.scale);
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float f2) {
        this.scale = f2;
    }
}
