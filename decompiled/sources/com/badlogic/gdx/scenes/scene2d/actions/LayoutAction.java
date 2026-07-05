package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class LayoutAction extends Action {
    private boolean enabled;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        ((Layout) this.target).setLayoutEnabled(this.enabled);
        return true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setLayoutEnabled(boolean z2) {
        this.enabled = z2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void setTarget(Actor actor) {
        if (actor == null || (actor instanceof Layout)) {
            super.setTarget(actor);
        } else {
            throw new m("Actor must implement layout: " + actor);
        }
    }
}
