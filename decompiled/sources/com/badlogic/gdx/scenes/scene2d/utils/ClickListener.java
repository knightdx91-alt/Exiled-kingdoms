package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ClickListener extends InputListener {
    public static float visualPressedDuration = 0.1f;
    private int button;
    private boolean cancelled;
    private long lastTapTime;
    private boolean over;
    private boolean pressed;
    private int tapCount;
    private long visualPressedTime;
    private float tapSquareSize = 14.0f;
    private float touchDownX = -1.0f;
    private float touchDownY = -1.0f;
    private int pressedPointer = -1;
    private int pressedButton = -1;
    private long tapCountInterval = 400000000;

    public ClickListener() {
    }

    public void cancel() {
        if (this.pressedPointer == -1) {
            return;
        }
        this.cancelled = true;
        this.pressed = false;
    }

    public void clicked(InputEvent inputEvent, float f2, float f3) {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        if (i2 != -1 || this.cancelled) {
            return;
        }
        this.over = true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        if (i2 != -1 || this.cancelled) {
            return;
        }
        this.over = false;
    }

    public int getButton() {
        return this.button;
    }

    public int getPressedButton() {
        return this.pressedButton;
    }

    public int getPressedPointer() {
        return this.pressedPointer;
    }

    public int getTapCount() {
        return this.tapCount;
    }

    public float getTapSquareSize() {
        return this.tapSquareSize;
    }

    public float getTouchDownX() {
        return this.touchDownX;
    }

    public float getTouchDownY() {
        return this.touchDownY;
    }

    public boolean inTapSquare(float f2, float f3) {
        float f4 = this.touchDownX;
        return !(f4 == -1.0f && this.touchDownY == -1.0f) && Math.abs(f2 - f4) < this.tapSquareSize && Math.abs(f3 - this.touchDownY) < this.tapSquareSize;
    }

    public void invalidateTapSquare() {
        this.touchDownX = -1.0f;
        this.touchDownY = -1.0f;
    }

    public boolean isOver(Actor actor, float f2, float f3) {
        Actor actorHit = actor.hit(f2, f3, true);
        if (actorHit == null || !actorHit.isDescendantOf(actor)) {
            return inTapSquare(f2, f3);
        }
        return true;
    }

    public boolean isPressed() {
        return this.pressed;
    }

    public boolean isVisualPressed() {
        if (this.pressed) {
            return true;
        }
        long j2 = this.visualPressedTime;
        if (j2 <= 0) {
            return false;
        }
        if (j2 > System.currentTimeMillis()) {
            return true;
        }
        this.visualPressedTime = 0L;
        return false;
    }

    public void setButton(int i2) {
        this.button = i2;
    }

    public void setTapCount(int i2) {
        this.tapCount = i2;
    }

    public void setTapCountInterval(float f2) {
        this.tapCountInterval = (long) (f2 * 1.0E9f);
    }

    public void setTapSquareSize(float f2) {
        this.tapSquareSize = f2;
    }

    public void setVisualPressed(boolean z2) {
        if (z2) {
            this.visualPressedTime = System.currentTimeMillis() + ((long) (visualPressedDuration * 1000.0f));
        } else {
            this.visualPressedTime = 0L;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        int i4;
        if (this.pressed) {
            return false;
        }
        if (i2 == 0 && (i4 = this.button) != -1 && i3 != i4) {
            return false;
        }
        this.pressed = true;
        this.pressedPointer = i2;
        this.pressedButton = i3;
        this.touchDownX = f2;
        this.touchDownY = f3;
        setVisualPressed(true);
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
        if (i2 != this.pressedPointer || this.cancelled) {
            return;
        }
        boolean zIsOver = isOver(inputEvent.getListenerActor(), f2, f3);
        this.pressed = zIsOver;
        if (zIsOver) {
            return;
        }
        invalidateTapSquare();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        int i4;
        if (i2 == this.pressedPointer) {
            if (!this.cancelled) {
                boolean zIsOver = isOver(inputEvent.getListenerActor(), f2, f3);
                if (zIsOver && i2 == 0 && (i4 = this.button) != -1 && i3 != i4) {
                    zIsOver = false;
                }
                if (zIsOver) {
                    long jNanoTime = System.nanoTime();
                    if (jNanoTime - this.lastTapTime > this.tapCountInterval) {
                        this.tapCount = 0;
                    }
                    this.tapCount++;
                    this.lastTapTime = jNanoTime;
                    clicked(inputEvent, f2, f3);
                }
            }
            this.pressed = false;
            this.pressedPointer = -1;
            this.pressedButton = -1;
            this.cancelled = false;
        }
    }

    public boolean inTapSquare() {
        return this.touchDownX != -1.0f;
    }

    public boolean isOver() {
        return this.over || this.pressed;
    }

    public ClickListener(int i2) {
        this.button = i2;
    }
}
