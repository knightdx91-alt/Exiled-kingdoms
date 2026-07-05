package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class VisibleAction extends Action {
    private boolean visible;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        this.target.setVisible(this.visible);
        return true;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean z2) {
        this.visible = z2;
    }
}
