package com.badlogic.gdx.scenes.scene2d.ui;

import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Tooltip<T extends Actor> extends InputListener {
    static q tmp = new q();
    boolean always;
    final Container<T> container;
    boolean instant;
    private final TooltipManager manager;
    Actor targetActor;

    public Tooltip(T t2) {
        this(t2, TooltipManager.getInstance());
    }

    private void setContainerPosition(Actor actor, float f2, float f3) {
        this.targetActor = actor;
        Stage stage = actor.getStage();
        if (stage == null) {
            return;
        }
        this.container.pack();
        TooltipManager tooltipManager = this.manager;
        float f4 = tooltipManager.offsetX;
        float f5 = tooltipManager.offsetY;
        float f6 = tooltipManager.edgeDistance;
        q qVar = tmp;
        float f7 = f2 + f4;
        float height = (f3 - f5) - this.container.getHeight();
        qVar.f91a = f7;
        qVar.f92b = height;
        q qVarLocalToStageCoordinates = actor.localToStageCoordinates(qVar);
        if (qVarLocalToStageCoordinates.f92b < f6) {
            q qVar2 = tmp;
            qVar2.f91a = f7;
            qVar2.f92b = f3 + f5;
            qVarLocalToStageCoordinates = actor.localToStageCoordinates(qVar2);
        }
        if (qVarLocalToStageCoordinates.f91a < f6) {
            qVarLocalToStageCoordinates.f91a = f6;
        }
        if (this.container.getWidth() + qVarLocalToStageCoordinates.f91a > stage.getWidth() - f6) {
            qVarLocalToStageCoordinates.f91a = (stage.getWidth() - f6) - this.container.getWidth();
        }
        if (this.container.getHeight() + qVarLocalToStageCoordinates.f92b > stage.getHeight() - f6) {
            qVarLocalToStageCoordinates.f92b = (stage.getHeight() - f6) - this.container.getHeight();
        }
        this.container.setPosition(qVarLocalToStageCoordinates.f91a, qVarLocalToStageCoordinates.f92b);
        q qVar3 = tmp;
        float width = actor.getWidth() / 2.0f;
        float height2 = actor.getHeight() / 2.0f;
        qVar3.f91a = width;
        qVar3.f92b = height2;
        q qVarLocalToStageCoordinates2 = actor.localToStageCoordinates(qVar3);
        float x2 = this.container.getX();
        float y2 = this.container.getY();
        float f8 = qVarLocalToStageCoordinates2.f91a - x2;
        qVarLocalToStageCoordinates2.f91a = f8;
        float f9 = qVarLocalToStageCoordinates2.f92b - y2;
        qVarLocalToStageCoordinates2.f92b = f9;
        this.container.setOrigin(f8, f9);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        if (i2 == -1 && !Gdx.input.isTouched()) {
            Actor listenerActor = inputEvent.getListenerActor();
            if (actor == null || !actor.isDescendantOf(listenerActor)) {
                setContainerPosition(listenerActor, f2, f3);
                this.manager.enter(this);
            }
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        if (actor == null || !actor.isDescendantOf(inputEvent.getListenerActor())) {
            hide();
        }
    }

    public T getActor() {
        return (T) this.container.getActor();
    }

    public Container<T> getContainer() {
        return this.container;
    }

    public TooltipManager getManager() {
        return this.manager;
    }

    public void hide() {
        this.manager.hide(this);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
        if (this.container.hasParent()) {
            return false;
        }
        setContainerPosition(inputEvent.getListenerActor(), f2, f3);
        return true;
    }

    public void setActor(T t2) {
        this.container.setActor(t2);
    }

    public void setAlways(boolean z2) {
        this.always = z2;
    }

    public void setInstant(boolean z2) {
        this.instant = z2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        if (this.instant) {
            this.container.toFront();
            return false;
        }
        this.manager.touchDown(this);
        return false;
    }

    public Tooltip(T t2, TooltipManager tooltipManager) {
        this.manager = tooltipManager;
        Container<T> container = new Container(t2) { // from class: com.badlogic.gdx.scenes.scene2d.ui.Tooltip.1
            @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
            public void act(float f2) {
                super.act(f2);
                Actor actor = Tooltip.this.targetActor;
                if (actor == null || actor.getStage() != null) {
                    return;
                }
                remove();
            }
        };
        this.container = container;
        container.setTouchable(Touchable.disabled);
    }
}
