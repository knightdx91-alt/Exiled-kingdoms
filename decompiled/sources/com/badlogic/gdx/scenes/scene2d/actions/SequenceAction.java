package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SequenceAction extends ParallelAction {
    private int index;

    public SequenceAction() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.ParallelAction, com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        if (this.index >= this.actions.f1750b) {
            return true;
        }
        c0 pool = getPool();
        setPool(null);
        try {
            if (this.actions.get(this.index).act(f2)) {
                if (this.actor == null) {
                    return true;
                }
                int i2 = this.index + 1;
                this.index = i2;
                if (i2 >= this.actions.f1750b) {
                    return true;
                }
            }
            setPool(pool);
            return false;
        } finally {
            setPool(pool);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.ParallelAction, com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        super.restart();
        this.index = 0;
    }

    public SequenceAction(Action action) {
        addAction(action);
    }

    public SequenceAction(Action action, Action action2) {
        addAction(action);
        addAction(action2);
    }

    public SequenceAction(Action action, Action action2, Action action3) {
        addAction(action);
        addAction(action2);
        addAction(action3);
    }

    public SequenceAction(Action action, Action action2, Action action3, Action action4) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
    }

    public SequenceAction(Action action, Action action2, Action action3, Action action4, Action action5) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
        addAction(action5);
    }
}
