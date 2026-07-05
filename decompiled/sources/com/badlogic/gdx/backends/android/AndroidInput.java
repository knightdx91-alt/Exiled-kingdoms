package com.badlogic.gdx.backends.android;

import android.view.View;
import com.badlogic.gdx.g;
import com.badlogic.gdx.j;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface AndroidInput extends g, View.OnTouchListener, View.OnKeyListener, View.OnGenericMotionListener {
    void addGenericMotionListener(View.OnGenericMotionListener onGenericMotionListener);

    void addKeyListener(View.OnKeyListener onKeyListener);

    /* synthetic */ void cancelVibrate();

    /* synthetic */ float getAccelerometerX();

    /* synthetic */ float getAccelerometerY();

    /* synthetic */ float getAccelerometerZ();

    /* synthetic */ float getAzimuth();

    @Override // com.badlogic.gdx.g
    /* synthetic */ long getCurrentEventTime();

    @Override // com.badlogic.gdx.g
    /* synthetic */ int getDeltaX();

    /* synthetic */ int getDeltaX(int i2);

    @Override // com.badlogic.gdx.g
    /* synthetic */ int getDeltaY();

    /* synthetic */ int getDeltaY(int i2);

    /* synthetic */ float getGyroscopeX();

    /* synthetic */ float getGyroscopeY();

    /* synthetic */ float getGyroscopeZ();

    /* synthetic */ j getInputProcessor();

    /* synthetic */ int getMaxPointers();

    /* synthetic */ g.c getNativeOrientation();

    /* synthetic */ float getPitch();

    /* synthetic */ float getPressure();

    /* synthetic */ float getPressure(int i2);

    /* synthetic */ float getRoll();

    /* synthetic */ int getRotation();

    /* synthetic */ void getRotationMatrix(float[] fArr);

    /* synthetic */ void getTextInput(g.e eVar, String str, String str2, String str3);

    /* synthetic */ void getTextInput(g.e eVar, String str, String str2, String str3, g.b bVar);

    @Override // com.badlogic.gdx.g
    /* synthetic */ int getX();

    /* synthetic */ int getX(int i2);

    @Override // com.badlogic.gdx.g
    /* synthetic */ int getY();

    /* synthetic */ int getY(int i2);

    /* synthetic */ boolean isButtonJustPressed(int i2);

    @Override // com.badlogic.gdx.g
    /* synthetic */ boolean isButtonPressed(int i2);

    @Override // com.badlogic.gdx.g
    @Deprecated
    /* synthetic */ boolean isCatchBackKey();

    /* synthetic */ boolean isCatchKey(int i2);

    @Deprecated
    /* synthetic */ boolean isCatchMenuKey();

    /* synthetic */ boolean isCursorCatched();

    @Override // com.badlogic.gdx.g
    /* synthetic */ boolean isKeyJustPressed(int i2);

    @Override // com.badlogic.gdx.g
    /* synthetic */ boolean isKeyPressed(int i2);

    /* synthetic */ boolean isPeripheralAvailable(g.d dVar);

    @Override // com.badlogic.gdx.g
    /* synthetic */ boolean isTouched();

    @Override // com.badlogic.gdx.g
    /* synthetic */ boolean isTouched(int i2);

    /* synthetic */ boolean justTouched();

    void onDreamingStarted();

    void onDreamingStopped();

    void onPause();

    void onResume();

    void processEvents();

    @Override // com.badlogic.gdx.g
    @Deprecated
    /* synthetic */ void setCatchBackKey(boolean z2);

    /* synthetic */ void setCatchKey(int i2, boolean z2);

    @Deprecated
    /* synthetic */ void setCatchMenuKey(boolean z2);

    /* synthetic */ void setCursorCatched(boolean z2);

    /* synthetic */ void setCursorPosition(int i2, int i3);

    @Override // com.badlogic.gdx.g
    /* synthetic */ void setInputProcessor(j jVar);

    void setKeyboardAvailable(boolean z2);

    @Override // com.badlogic.gdx.g
    /* synthetic */ void setOnscreenKeyboardVisible(boolean z2);

    /* synthetic */ void setOnscreenKeyboardVisible(boolean z2, g.b bVar);

    /* synthetic */ void vibrate(int i2);

    /* synthetic */ void vibrate(long[] jArr, int i2);
}
