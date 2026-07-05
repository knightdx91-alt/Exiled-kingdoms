package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RunnableAction extends Action {
    private boolean ran;
    private Runnable runnable;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        if (!this.ran) {
            this.ran = true;
            run();
        }
        return true;
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.runnable = null;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.ran = false;
    }

    public void run() {
        c0 pool = getPool();
        setPool(null);
        try {
            this.runnable.run();
        } finally {
            setPool(pool);
        }
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
