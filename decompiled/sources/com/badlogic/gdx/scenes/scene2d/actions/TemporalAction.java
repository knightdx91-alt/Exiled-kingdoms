package com.badlogic.gdx.scenes.scene2d.actions;

import a0.h;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class TemporalAction extends Action {
    private boolean began;
    private boolean complete;
    private float duration;
    private h interpolation;
    private boolean reverse;
    private float time;

    public TemporalAction() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        boolean z2 = true;
        if (this.complete) {
            return true;
        }
        c0 pool = getPool();
        setPool(null);
        try {
            if (!this.began) {
                begin();
                this.began = true;
            }
            float f3 = this.time + f2;
            this.time = f3;
            float f4 = this.duration;
            if (f3 < f4) {
                z2 = false;
            }
            this.complete = z2;
            float fA = z2 ? 1.0f : f3 / f4;
            h hVar = this.interpolation;
            if (hVar != null) {
                fA = hVar.a(fA);
            }
            if (this.reverse) {
                fA = 1.0f - fA;
            }
            update(fA);
            if (this.complete) {
                end();
            }
            boolean z3 = this.complete;
            setPool(pool);
            return z3;
        } catch (Throwable th) {
            setPool(pool);
            throw th;
        }
    }

    protected void begin() {
    }

    protected void end() {
    }

    public void finish() {
        this.time = this.duration;
    }

    public float getDuration() {
        return this.duration;
    }

    public h getInterpolation() {
        return this.interpolation;
    }

    public float getTime() {
        return this.time;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public boolean isReverse() {
        return this.reverse;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.reverse = false;
        this.interpolation = null;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.time = 0.0f;
        this.began = false;
        this.complete = false;
    }

    public void setDuration(float f2) {
        this.duration = f2;
    }

    public void setInterpolation(h hVar) {
        this.interpolation = hVar;
    }

    public void setReverse(boolean z2) {
        this.reverse = z2;
    }

    public void setTime(float f2) {
        this.time = f2;
    }

    protected abstract void update(float f2);

    public TemporalAction(float f2) {
        this.duration = f2;
    }

    public TemporalAction(float f2, h hVar) {
        this.duration = f2;
        this.interpolation = hVar;
    }
}
