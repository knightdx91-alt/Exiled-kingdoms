package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DelayAction extends DelegateAction {
    private float duration;
    private float time;

    public DelayAction() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f2) {
        float f3 = this.time;
        float f4 = this.duration;
        if (f3 < f4) {
            float f5 = f3 + f2;
            this.time = f5;
            if (f5 < f4) {
                return false;
            }
            f2 = f5 - f4;
        }
        Action action = this.action;
        if (action == null) {
            return true;
        }
        return action.act(f2);
    }

    public void finish() {
        this.time = this.duration;
    }

    public float getDuration() {
        return this.duration;
    }

    public float getTime() {
        return this.time;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction, com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        super.restart();
        this.time = 0.0f;
    }

    public void setDuration(float f2) {
        this.duration = f2;
    }

    public void setTime(float f2) {
        this.time = f2;
    }

    public DelayAction(float f2) {
        this.duration = f2;
    }
}
