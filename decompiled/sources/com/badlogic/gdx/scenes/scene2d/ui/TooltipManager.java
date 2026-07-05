package com.badlogic.gdx.scenes.scene2d.ui;

import a0.h;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.d;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.q0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TooltipManager {
    private static d files;
    private static TooltipManager instance;
    Tooltip showTooltip;
    public float initialTime = 2.0f;
    public float subsequentTime = 0.0f;
    public float resetTime = 1.5f;
    public boolean enabled = true;
    public boolean animations = true;
    public float maxWidth = 2.1474836E9f;
    public float offsetX = 15.0f;
    public float offsetY = 19.0f;
    public float edgeDistance = 7.0f;
    final a<Tooltip> shown = new a<>();
    float time = this.initialTime;
    final q0.a resetTask = new q0.a() { // from class: com.badlogic.gdx.scenes.scene2d.ui.TooltipManager.1
        @Override // com.badlogic.gdx.utils.q0.a, java.lang.Runnable
        public void run() {
            TooltipManager tooltipManager = TooltipManager.this;
            tooltipManager.time = tooltipManager.initialTime;
        }
    };
    final q0.a showTask = new q0.a() { // from class: com.badlogic.gdx.scenes.scene2d.ui.TooltipManager.2
        @Override // com.badlogic.gdx.utils.q0.a, java.lang.Runnable
        public void run() {
            Actor actor;
            Stage stage;
            Tooltip tooltip = TooltipManager.this.showTooltip;
            if (tooltip == null || (actor = tooltip.targetActor) == null || (stage = actor.getStage()) == null) {
                return;
            }
            stage.addActor(TooltipManager.this.showTooltip.container);
            TooltipManager.this.showTooltip.container.toFront();
            TooltipManager tooltipManager = TooltipManager.this;
            tooltipManager.shown.a(tooltipManager.showTooltip);
            TooltipManager.this.showTooltip.container.clearActions();
            TooltipManager tooltipManager2 = TooltipManager.this;
            tooltipManager2.showAction(tooltipManager2.showTooltip);
            TooltipManager tooltipManager3 = TooltipManager.this;
            if (tooltipManager3.showTooltip.instant) {
                return;
            }
            tooltipManager3.time = tooltipManager3.subsequentTime;
            tooltipManager3.resetTask.cancel();
        }
    };

    public static TooltipManager getInstance() {
        d dVar = files;
        if (dVar == null || dVar != Gdx.files) {
            files = Gdx.files;
            instance = new TooltipManager();
        }
        return instance;
    }

    public void enter(Tooltip tooltip) {
        this.showTooltip = tooltip;
        this.showTask.cancel();
        if (this.enabled || tooltip.always) {
            float f2 = this.time;
            if (f2 == 0.0f || tooltip.instant) {
                this.showTask.run();
            } else {
                q0.b().d(this.showTask, f2, 0.0f, 0);
            }
        }
    }

    public void hide(Tooltip tooltip) {
        this.showTooltip = null;
        this.showTask.cancel();
        if (tooltip.container.hasParent()) {
            this.shown.q(tooltip, true);
            hideAction(tooltip);
            this.resetTask.cancel();
            q0.b().d(this.resetTask, this.resetTime, 0.0f, 0);
        }
    }

    protected void hideAction(Tooltip tooltip) {
        Group group = tooltip.container;
        h hVar = h.f53b;
        group.addAction(Actions.sequence(Actions.parallel(Actions.alpha(0.2f, 0.2f, hVar), Actions.scaleTo(0.05f, 0.05f, 0.2f, hVar)), Actions.removeActor()));
    }

    public void hideAll() {
        this.resetTask.cancel();
        this.showTask.cancel();
        this.time = this.initialTime;
        this.showTooltip = null;
        a.b<Tooltip> it = this.shown.iterator();
        while (it.hasNext()) {
            it.next().hide();
        }
        this.shown.clear();
    }

    public void instant() {
        this.time = 0.0f;
        this.showTask.run();
        this.showTask.cancel();
    }

    protected void showAction(Tooltip tooltip) {
        float f2 = this.animations ? this.time > 0.0f ? 0.5f : 0.15f : 0.1f;
        tooltip.container.setTransform(true);
        tooltip.container.getColor().f1677a = 0.2f;
        tooltip.container.setScale(0.05f);
        Group group = tooltip.container;
        h hVar = h.f53b;
        group.addAction(Actions.parallel(Actions.fadeIn(f2, hVar), Actions.scaleTo(1.0f, 1.0f, f2, hVar)));
    }

    public void touchDown(Tooltip tooltip) {
        this.showTask.cancel();
        if (tooltip.container.remove()) {
            this.resetTask.cancel();
        }
        this.resetTask.run();
        if (this.enabled || tooltip.always) {
            this.showTooltip = tooltip;
            q0.b().d(this.showTask, this.time, 0.0f, 0);
        }
    }
}
