package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AddAction extends Action {
    private Action action;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        this.target.addAction(this.action);
        return true;
    }

    public Action getAction() {
        return this.action;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.action = null;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        Action action = this.action;
        if (action != null) {
            action.restart();
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
