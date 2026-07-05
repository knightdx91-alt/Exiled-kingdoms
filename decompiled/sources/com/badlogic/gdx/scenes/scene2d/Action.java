package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class Action implements c0.a {
    protected Actor actor;
    private c0 pool;
    protected Actor target;

    public abstract boolean act(float f2);

    public Actor getActor() {
        return this.actor;
    }

    public c0 getPool() {
        return this.pool;
    }

    public Actor getTarget() {
        return this.target;
    }

    @Override // com.badlogic.gdx.utils.c0.a
    public void reset() {
        this.actor = null;
        this.target = null;
        this.pool = null;
        restart();
    }

    public void restart() {
    }

    public void setActor(Actor actor) {
        c0 c0Var;
        this.actor = actor;
        if (this.target == null) {
            setTarget(actor);
        }
        if (actor != null || (c0Var = this.pool) == null) {
            return;
        }
        c0Var.free(this);
        this.pool = null;
    }

    public void setPool(c0 c0Var) {
        this.pool = c0Var;
    }

    public void setTarget(Actor actor) {
        this.target = actor;
    }

    public String toString() {
        String name = getClass().getName();
        int iLastIndexOf = name.lastIndexOf(46);
        if (iLastIndexOf != -1) {
            name = name.substring(iLastIndexOf + 1);
        }
        return name.endsWith("Action") ? name.substring(0, name.length() - 6) : name;
    }
}
