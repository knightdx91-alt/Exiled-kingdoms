package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Event implements c0.a {
    private boolean bubbles = true;
    private boolean cancelled;
    private boolean capture;
    private boolean handled;
    private Actor listenerActor;
    private Stage stage;
    private boolean stopped;
    private Actor targetActor;

    public void cancel() {
        this.cancelled = true;
        this.stopped = true;
        this.handled = true;
    }

    public boolean getBubbles() {
        return this.bubbles;
    }

    public Actor getListenerActor() {
        return this.listenerActor;
    }

    public Stage getStage() {
        return this.stage;
    }

    public Actor getTarget() {
        return this.targetActor;
    }

    public void handle() {
        this.handled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public boolean isCapture() {
        return this.capture;
    }

    public boolean isHandled() {
        return this.handled;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    @Override // com.badlogic.gdx.utils.c0.a
    public void reset() {
        this.stage = null;
        this.targetActor = null;
        this.listenerActor = null;
        this.capture = false;
        this.bubbles = true;
        this.handled = false;
        this.stopped = false;
        this.cancelled = false;
    }

    public void setBubbles(boolean z2) {
        this.bubbles = z2;
    }

    public void setCapture(boolean z2) {
        this.capture = z2;
    }

    public void setListenerActor(Actor actor) {
        this.listenerActor = actor;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTarget(Actor actor) {
        this.targetActor = actor;
    }

    public void stop() {
        this.stopped = true;
    }
}
