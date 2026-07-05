package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParallelAction extends Action {
    a<Action> actions = new a<>(true, 4);
    private boolean complete;

    public ParallelAction() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        if (this.complete) {
            return true;
        }
        this.complete = true;
        c0 pool = getPool();
        setPool(null);
        try {
            a<Action> aVar = this.actions;
            int i2 = aVar.f1750b;
            for (int i3 = 0; i3 < i2 && this.actor != null; i3++) {
                Action action = aVar.get(i3);
                if (action.getActor() != null && !action.act(f2)) {
                    this.complete = false;
                }
                if (this.actor == null) {
                    setPool(pool);
                    return true;
                }
            }
            boolean z2 = this.complete;
            setPool(pool);
            return z2;
        } catch (Throwable th) {
            setPool(pool);
            throw th;
        }
    }

    public void addAction(Action action) {
        this.actions.a(action);
        Actor actor = this.actor;
        if (actor != null) {
            action.setActor(actor);
        }
    }

    public a<Action> getActions() {
        return this.actions;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.actions.clear();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.complete = false;
        a<Action> aVar = this.actions;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            aVar.get(i3).restart();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void setActor(Actor actor) {
        a<Action> aVar = this.actions;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            aVar.get(i3).setActor(actor);
        }
        super.setActor(actor);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(super.toString());
        sb.append('(');
        a<Action> aVar = this.actions;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(aVar.get(i3));
        }
        sb.append(')');
        return sb.toString();
    }

    public ParallelAction(Action action) {
        addAction(action);
    }

    public ParallelAction(Action action, Action action2) {
        addAction(action);
        addAction(action2);
    }

    public ParallelAction(Action action, Action action2, Action action3) {
        addAction(action);
        addAction(action2);
        addAction(action3);
    }

    public ParallelAction(Action action, Action action2, Action action3, Action action4) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
    }

    public ParallelAction(Action action, Action action2, Action action3, Action action4, Action action5) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
        addAction(action5);
    }
}
