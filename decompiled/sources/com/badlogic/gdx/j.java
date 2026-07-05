package com.badlogic.gdx;

/* JADX INFO: compiled from: InputProcessor.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface j {
    boolean keyDown(int i2);

    boolean keyTyped(char c2);

    boolean keyUp(int i2);

    boolean mouseMoved(int i2, int i3);

    boolean scrolled(float f2, float f3);

    boolean touchDown(int i2, int i3, int i4, int i5);

    boolean touchDragged(int i2, int i3, int i4);

    boolean touchUp(int i2, int i3, int i4, int i5);
}
