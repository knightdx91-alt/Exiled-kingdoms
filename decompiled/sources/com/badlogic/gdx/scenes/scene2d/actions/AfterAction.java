package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AfterAction extends DelegateAction {
    private a<Action> waitForActions = new a<>(false, 4);

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f2) {
        a<Action> actions = this.target.getActions();
        if (actions.f1750b == 1) {
            this.waitForActions.clear();
        }
        for (int i2 = this.waitForActions.f1750b - 1; i2 >= 0; i2--) {
            if (actions.h(this.waitForActions.get(i2), true) == -1) {
                this.waitForActions.o(i2);
            }
        }
        if (this.waitForActions.f1750b > 0) {
            return false;
        }
        return this.action.act(f2);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction, com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        super.restart();
        this.waitForActions.clear();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction, com.badlogic.gdx.scenes.scene2d.Action
    public void setTarget(Actor actor) {
        if (actor != null) {
            this.waitForActions.b(actor.getActions());
        }
        super.setTarget(actor);
    }
}
