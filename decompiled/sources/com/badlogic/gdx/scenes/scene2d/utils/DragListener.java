package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DragListener extends InputListener {
    private int button;
    private float dragLastX;
    private float dragLastY;
    private float dragStartX;
    private float dragStartY;
    private float dragX;
    private float dragY;
    private boolean dragging;
    private float tapSquareSize = 14.0f;
    private float touchDownX = -1.0f;
    private float touchDownY = -1.0f;
    private float stageTouchDownX = -1.0f;
    private float stageTouchDownY = -1.0f;
    private int pressedPointer = -1;

    public void cancel() {
        this.dragging = false;
        this.pressedPointer = -1;
    }

    public void drag(InputEvent inputEvent, float f2, float f3, int i2) {
    }

    public void dragStart(InputEvent inputEvent, float f2, float f3, int i2) {
    }

    public void dragStop(InputEvent inputEvent, float f2, float f3, int i2) {
    }

    public int getButton() {
        return this.button;
    }

    public float getDeltaX() {
        return this.dragX - this.dragLastX;
    }

    public float getDeltaY() {
        return this.dragY - this.dragLastY;
    }

    public float getDragDistance() {
        float f2 = this.dragX - this.dragStartX;
        float f3 = this.dragY - this.dragStartY;
        return (float) Math.sqrt((f3 * f3) + (f2 * f2));
    }

    public float getDragStartX() {
        return this.dragStartX;
    }

    public float getDragStartY() {
        return this.dragStartY;
    }

    public float getDragX() {
        return this.dragX;
    }

    public float getDragY() {
        return this.dragY;
    }

    public float getStageTouchDownX() {
        return this.stageTouchDownX;
    }

    public float getStageTouchDownY() {
        return this.stageTouchDownY;
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

    public boolean isDragging() {
        return this.dragging;
    }

    public void setButton(int i2) {
        this.button = i2;
    }

    public void setDragStartX(float f2) {
        this.dragStartX = f2;
    }

    public void setDragStartY(float f2) {
        this.dragStartY = f2;
    }

    public void setTapSquareSize(float f2) {
        this.tapSquareSize = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        int i4;
        if (this.pressedPointer != -1) {
            return false;
        }
        if (i2 == 0 && (i4 = this.button) != -1 && i3 != i4) {
            return false;
        }
        this.pressedPointer = i2;
        this.touchDownX = f2;
        this.touchDownY = f3;
        this.stageTouchDownX = inputEvent.getStageX();
        this.stageTouchDownY = inputEvent.getStageY();
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
        if (i2 != this.pressedPointer) {
            return;
        }
        if (!this.dragging && (Math.abs(this.touchDownX - f2) > this.tapSquareSize || Math.abs(this.touchDownY - f3) > this.tapSquareSize)) {
            this.dragging = true;
            this.dragStartX = f2;
            this.dragStartY = f3;
            dragStart(inputEvent, f2, f3, i2);
            this.dragX = f2;
            this.dragY = f3;
        }
        if (this.dragging) {
            this.dragLastX = this.dragX;
            this.dragLastY = this.dragY;
            this.dragX = f2;
            this.dragY = f3;
            drag(inputEvent, f2, f3, i2);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        if (i2 == this.pressedPointer) {
            if (this.dragging) {
                dragStop(inputEvent, f2, f3, i2);
            }
            cancel();
        }
    }
}
