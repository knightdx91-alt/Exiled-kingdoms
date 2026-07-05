package com.badlogic.gdx.scenes.scene2d;

import a0.q;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class InputEvent extends Event {
    private int button;
    private char character;
    private int keyCode;
    private int pointer;
    private Actor relatedActor;
    private float scrollAmountX;
    private float scrollAmountY;
    private float stageX;
    private float stageY;
    private boolean touchFocus = true;
    private Type type;

    public enum Type {
        touchDown,
        touchUp,
        touchDragged,
        mouseMoved,
        enter,
        exit,
        scrolled,
        keyDown,
        keyUp,
        keyTyped
    }

    public int getButton() {
        return this.button;
    }

    public char getCharacter() {
        return this.character;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public int getPointer() {
        return this.pointer;
    }

    public Actor getRelatedActor() {
        return this.relatedActor;
    }

    public float getScrollAmountX() {
        return this.scrollAmountX;
    }

    public float getScrollAmountY() {
        return this.scrollAmountY;
    }

    public float getStageX() {
        return this.stageX;
    }

    public float getStageY() {
        return this.stageY;
    }

    public boolean getTouchFocus() {
        return this.touchFocus;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isTouchFocusCancel() {
        return this.stageX == -2.1474836E9f || this.stageY == -2.1474836E9f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Event, com.badlogic.gdx.utils.c0.a
    public void reset() {
        super.reset();
        this.relatedActor = null;
        this.button = -1;
    }

    public void setButton(int i2) {
        this.button = i2;
    }

    public void setCharacter(char c2) {
        this.character = c2;
    }

    public void setKeyCode(int i2) {
        this.keyCode = i2;
    }

    public void setPointer(int i2) {
        this.pointer = i2;
    }

    public void setRelatedActor(Actor actor) {
        this.relatedActor = actor;
    }

    public void setScrollAmountX(float f2) {
        this.scrollAmountX = f2;
    }

    public void setScrollAmountY(float f2) {
        this.scrollAmountY = f2;
    }

    public void setStageX(float f2) {
        this.stageX = f2;
    }

    public void setStageY(float f2) {
        this.stageY = f2;
    }

    public void setTouchFocus(boolean z2) {
        this.touchFocus = z2;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public q toCoordinates(Actor actor, q qVar) {
        float f2 = this.stageX;
        float f3 = this.stageY;
        qVar.f91a = f2;
        qVar.f92b = f3;
        actor.stageToLocalCoordinates(qVar);
        return qVar;
    }

    public String toString() {
        return this.type.toString();
    }
}
