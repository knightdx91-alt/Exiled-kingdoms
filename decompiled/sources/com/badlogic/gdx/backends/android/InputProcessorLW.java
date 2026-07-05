package com.badlogic.gdx.backends.android;

import com.badlogic.gdx.j;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface InputProcessorLW extends j {
    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean keyDown(int i2);

    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean keyTyped(char c2);

    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean keyUp(int i2);

    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean mouseMoved(int i2, int i3);

    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean scrolled(float f2, float f3);

    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean touchDown(int i2, int i3, int i4, int i5);

    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean touchDragged(int i2, int i3, int i4);

    void touchDrop(int i2, int i3);

    @Override // com.badlogic.gdx.j
    /* synthetic */ boolean touchUp(int i2, int i3, int i4, int i5);
}
