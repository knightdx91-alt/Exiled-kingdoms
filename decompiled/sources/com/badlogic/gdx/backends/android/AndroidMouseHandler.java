package com.badlogic.gdx.backends.android;

import android.view.MotionEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.DefaultAndroidInput;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidMouseHandler {
    private int deltaX = 0;
    private int deltaY = 0;

    private void logAction(int i2) {
        String str;
        if (i2 == 9) {
            str = "HOVER_ENTER";
        } else if (i2 == 7) {
            str = "HOVER_MOVE";
        } else if (i2 == 10) {
            str = "HOVER_EXIT";
        } else if (i2 == 8) {
            str = "SCROLL";
        } else {
            str = "UNKNOWN (" + i2 + ")";
        }
        Gdx.app.log("AndroidMouseHandler", "action " + str);
    }

    private void postTouchEvent(DefaultAndroidInput defaultAndroidInput, int i2, int i3, int i4, int i5, int i6, long j2) {
        DefaultAndroidInput.TouchEvent touchEventObtain = defaultAndroidInput.usedTouchEvents.obtain();
        touchEventObtain.timeStamp = j2;
        touchEventObtain.f1609x = i3;
        touchEventObtain.f1610y = i4;
        touchEventObtain.type = i2;
        touchEventObtain.scrollAmountX = i5;
        touchEventObtain.scrollAmountY = i6;
        defaultAndroidInput.touchEvents.add(touchEventObtain);
    }

    public boolean onGenericMotion(MotionEvent motionEvent, DefaultAndroidInput defaultAndroidInput) {
        if ((motionEvent.getSource() & 2) == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        long jNanoTime = System.nanoTime();
        synchronized (defaultAndroidInput) {
            try {
                if (action == 7) {
                    int x2 = (int) motionEvent.getX();
                    int y2 = (int) motionEvent.getY();
                    if (x2 != this.deltaX || y2 != this.deltaY) {
                        postTouchEvent(defaultAndroidInput, 4, x2, y2, 0, 0, jNanoTime);
                        this.deltaX = x2;
                        this.deltaY = y2;
                    }
                } else if (action == 8) {
                    postTouchEvent(defaultAndroidInput, 3, 0, 0, (int) (-Math.signum(motionEvent.getAxisValue(10))), (int) (-Math.signum(motionEvent.getAxisValue(9))), jNanoTime);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Gdx.app.getGraphics().requestRendering();
        return true;
    }
}
