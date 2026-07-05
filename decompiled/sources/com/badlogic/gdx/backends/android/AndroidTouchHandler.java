package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.view.MotionEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.DefaultAndroidInput;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidTouchHandler {
    private void logAction(int i2, int i3) {
        String str;
        if (i2 == 0) {
            str = "DOWN";
        } else if (i2 == 5) {
            str = "POINTER DOWN";
        } else if (i2 == 1) {
            str = "UP";
        } else if (i2 == 6) {
            str = "POINTER UP";
        } else if (i2 == 4) {
            str = "OUTSIDE";
        } else if (i2 == 3) {
            str = "CANCEL";
        } else if (i2 == 2) {
            str = "MOVE";
        } else {
            str = "UNKNOWN (" + i2 + ")";
        }
        Gdx.app.log("AndroidMultiTouchHandler", "action " + str + ", Android pointer id: " + i3);
    }

    private void postTouchEvent(DefaultAndroidInput defaultAndroidInput, int i2, int i3, int i4, int i5, int i6, long j2) {
        DefaultAndroidInput.TouchEvent touchEventObtain = defaultAndroidInput.usedTouchEvents.obtain();
        touchEventObtain.timeStamp = j2;
        touchEventObtain.pointer = i5;
        touchEventObtain.f1609x = i3;
        touchEventObtain.f1610y = i4;
        touchEventObtain.type = i2;
        touchEventObtain.button = i6;
        defaultAndroidInput.touchEvents.add(touchEventObtain);
    }

    private int toGdxButton(int i2) {
        if (i2 == 0 || i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 8) {
            return 3;
        }
        return i2 == 16 ? 4 : -1;
    }

    public void onTouch(MotionEvent motionEvent, DefaultAndroidInput defaultAndroidInput) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int action = motionEvent.getAction() & 255;
        int action2 = (motionEvent.getAction() & 65280) >> 8;
        int pointerId = motionEvent.getPointerId(action2);
        long jNanoTime = System.nanoTime();
        synchronized (defaultAndroidInput) {
            int i10 = 20;
            try {
                switch (action) {
                    case 0:
                    case 5:
                        int freePointerIndex = defaultAndroidInput.getFreePointerIndex();
                        if (freePointerIndex < 20) {
                            defaultAndroidInput.realId[freePointerIndex] = pointerId;
                            int x2 = (int) motionEvent.getX(action2);
                            int y2 = (int) motionEvent.getY(action2);
                            int gdxButton = toGdxButton(motionEvent.getButtonState());
                            if (gdxButton != -1) {
                                i2 = gdxButton;
                                i3 = x2;
                                i4 = y2;
                                postTouchEvent(defaultAndroidInput, 0, x2, y2, freePointerIndex, i2, jNanoTime);
                            } else {
                                i2 = gdxButton;
                                i3 = x2;
                                i4 = y2;
                            }
                            defaultAndroidInput.touchX[freePointerIndex] = i3;
                            defaultAndroidInput.touchY[freePointerIndex] = i4;
                            defaultAndroidInput.deltaX[freePointerIndex] = 0;
                            defaultAndroidInput.deltaY[freePointerIndex] = 0;
                            int i11 = i2;
                            defaultAndroidInput.touched[freePointerIndex] = i11 != -1;
                            defaultAndroidInput.button[freePointerIndex] = i11;
                            defaultAndroidInput.pressure[freePointerIndex] = motionEvent.getPressure(action2);
                        }
                        break;
                    case 1:
                    case 4:
                    case 6:
                        int iLookUpPointerIndex = defaultAndroidInput.lookUpPointerIndex(pointerId);
                        if (iLookUpPointerIndex != -1 && iLookUpPointerIndex < 20) {
                            defaultAndroidInput.realId[iLookUpPointerIndex] = -1;
                            int x3 = (int) motionEvent.getX(action2);
                            int y3 = (int) motionEvent.getY(action2);
                            int i12 = defaultAndroidInput.button[iLookUpPointerIndex];
                            if (i12 != -1) {
                                i5 = x3;
                                postTouchEvent(defaultAndroidInput, 1, x3, y3, iLookUpPointerIndex, i12, jNanoTime);
                            } else {
                                i5 = x3;
                            }
                            defaultAndroidInput.touchX[iLookUpPointerIndex] = i5;
                            defaultAndroidInput.touchY[iLookUpPointerIndex] = y3;
                            defaultAndroidInput.deltaX[iLookUpPointerIndex] = 0;
                            defaultAndroidInput.deltaY[iLookUpPointerIndex] = 0;
                            defaultAndroidInput.touched[iLookUpPointerIndex] = false;
                            defaultAndroidInput.button[iLookUpPointerIndex] = 0;
                            defaultAndroidInput.pressure[iLookUpPointerIndex] = 0.0f;
                        }
                        break;
                    case 2:
                        int pointerCount = motionEvent.getPointerCount();
                        int i13 = 0;
                        while (i13 < pointerCount) {
                            int pointerId2 = motionEvent.getPointerId(i13);
                            int x4 = (int) motionEvent.getX(i13);
                            int y4 = (int) motionEvent.getY(i13);
                            int iLookUpPointerIndex2 = defaultAndroidInput.lookUpPointerIndex(pointerId2);
                            if (iLookUpPointerIndex2 == -1) {
                                i8 = i13;
                            } else if (iLookUpPointerIndex2 < i10) {
                                int i14 = defaultAndroidInput.button[iLookUpPointerIndex2];
                                if (i14 != -1) {
                                    i6 = iLookUpPointerIndex2;
                                    i7 = y4;
                                    i8 = i13;
                                    i9 = x4;
                                    postTouchEvent(defaultAndroidInput, 2, x4, y4, iLookUpPointerIndex2, i14, jNanoTime);
                                } else {
                                    i6 = iLookUpPointerIndex2;
                                    i7 = y4;
                                    i8 = i13;
                                    i9 = x4;
                                    postTouchEvent(defaultAndroidInput, 4, i9, i7, iLookUpPointerIndex2, 0, jNanoTime);
                                }
                                int[] iArr = defaultAndroidInput.deltaX;
                                int[] iArr2 = defaultAndroidInput.touchX;
                                iArr[i6] = i9 - iArr2[i6];
                                int[] iArr3 = defaultAndroidInput.deltaY;
                                int[] iArr4 = defaultAndroidInput.touchY;
                                iArr3[i6] = i7 - iArr4[i6];
                                iArr2[i6] = i9;
                                iArr4[i6] = i7;
                                defaultAndroidInput.pressure[i6] = motionEvent.getPressure(i8);
                            }
                            i13 = i8 + 1;
                            i10 = 20;
                            break;
                        }
                        break;
                    case 3:
                        int i15 = 0;
                        while (true) {
                            int[] iArr5 = defaultAndroidInput.realId;
                            if (i15 < iArr5.length) {
                                iArr5[i15] = -1;
                                defaultAndroidInput.touchX[i15] = 0;
                                defaultAndroidInput.touchY[i15] = 0;
                                defaultAndroidInput.deltaX[i15] = 0;
                                defaultAndroidInput.deltaY[i15] = 0;
                                defaultAndroidInput.touched[i15] = false;
                                defaultAndroidInput.button[i15] = 0;
                                defaultAndroidInput.pressure[i15] = 0.0f;
                                i15++;
                            }
                            break;
                        }
                        break;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Gdx.app.getGraphics().requestRendering();
    }

    public boolean supportsMultitouch(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
    }
}
