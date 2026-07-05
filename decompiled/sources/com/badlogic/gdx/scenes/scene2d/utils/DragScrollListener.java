package com.badlogic.gdx.scenes.scene2d.utils;

import a0.h;
import a0.q;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.q0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DragScrollListener extends DragListener {
    static final q tmpCoords = new q();
    float padBottom;
    float padTop;
    private ScrollPane scroll;
    private q0.a scrollDown;
    private q0.a scrollUp;
    long startTime;
    h interpolation = h.f54c;
    float minSpeed = 15.0f;
    float maxSpeed = 75.0f;
    float tickSecs = 0.05f;
    long rampTime = 1750;

    public DragScrollListener(final ScrollPane scrollPane) {
        this.scroll = scrollPane;
        this.scrollUp = new q0.a() { // from class: com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener.1
            @Override // com.badlogic.gdx.utils.q0.a, java.lang.Runnable
            public void run() {
                DragScrollListener.this.scroll(scrollPane.getScrollY() - DragScrollListener.this.getScrollPixels());
            }
        };
        this.scrollDown = new q0.a() { // from class: com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener.2
            @Override // com.badlogic.gdx.utils.q0.a, java.lang.Runnable
            public void run() {
                DragScrollListener.this.scroll(scrollPane.getScrollY() + DragScrollListener.this.getScrollPixels());
            }
        };
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void drag(InputEvent inputEvent, float f2, float f3, int i2) {
        Actor listenerActor = inputEvent.getListenerActor();
        ScrollPane scrollPane = this.scroll;
        q qVar = tmpCoords;
        qVar.f91a = f2;
        qVar.f92b = f3;
        listenerActor.localToActorCoordinates(scrollPane, qVar);
        if (isAbove(qVar.f92b)) {
            this.scrollDown.cancel();
            if (this.scrollUp.isScheduled()) {
                return;
            }
            this.startTime = System.currentTimeMillis();
            q0.a aVar = this.scrollUp;
            float f4 = this.tickSecs;
            q0.c(aVar, f4, f4);
            return;
        }
        if (!isBelow(qVar.f92b)) {
            this.scrollUp.cancel();
            this.scrollDown.cancel();
            return;
        }
        this.scrollUp.cancel();
        if (this.scrollDown.isScheduled()) {
            return;
        }
        this.startTime = System.currentTimeMillis();
        q0.a aVar2 = this.scrollDown;
        float f5 = this.tickSecs;
        q0.c(aVar2, f5, f5);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void dragStop(InputEvent inputEvent, float f2, float f3, int i2) {
        this.scrollUp.cancel();
        this.scrollDown.cancel();
    }

    float getScrollPixels() {
        h hVar = this.interpolation;
        float f2 = this.minSpeed;
        return (hVar.a(Math.min(1.0f, (System.currentTimeMillis() - this.startTime) / this.rampTime)) * (this.maxSpeed - f2)) + f2;
    }

    protected boolean isAbove(float f2) {
        return f2 >= this.scroll.getHeight() - this.padTop;
    }

    protected boolean isBelow(float f2) {
        return f2 < this.padBottom;
    }

    protected void scroll(float f2) {
        this.scroll.setScrollY(f2);
    }

    public void setPadding(float f2, float f3) {
        this.padTop = f2;
        this.padBottom = f3;
    }

    public void setup(float f2, float f3, float f4, float f5) {
        this.minSpeed = f2;
        this.maxSpeed = f3;
        this.tickSecs = f4;
        this.rampTime = (long) (f5 * 1000.0f);
    }
}
