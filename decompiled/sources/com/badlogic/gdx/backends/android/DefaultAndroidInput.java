package com.badlogic.gdx.backends.android;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.f;
import com.badlogic.gdx.g;
import com.badlogic.gdx.j;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.r;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DefaultAndroidInput implements AndroidInput {
    public static final int NUM_TOUCHES = 20;
    public static final int SUPPORTED_KEYS = 260;
    final float[] R;
    public boolean accelerometerAvailable;
    private SensorEventListener accelerometerListener;
    protected final float[] accelerometerValues;
    final Application app;
    private float azimuth;
    int[] button;
    private boolean compassAvailable;
    private SensorEventListener compassListener;
    private final AndroidApplicationConfiguration config;
    final Context context;
    private long currentEventTimeStamp;
    int[] deltaX;
    int[] deltaY;
    private final ArrayList<View.OnGenericMotionListener> genericMotionListeners;
    public boolean gyroscopeAvailable;
    private SensorEventListener gyroscopeListener;
    protected final float[] gyroscopeValues;
    private Handler handle;
    final boolean hasMultitouch;
    private boolean[] justPressedButtons;
    private boolean[] justPressedKeys;
    private boolean justTouched;
    private int keyCount;
    ArrayList<KeyEvent> keyEvents;
    private boolean keyJustPressed;
    ArrayList<View.OnKeyListener> keyListeners;
    boolean keyboardAvailable;
    private boolean[] keys;
    private r keysToCatch;
    protected final float[] magneticFieldValues;
    private SensorManager manager;
    private final AndroidMouseHandler mouseHandler;
    protected final g.c nativeOrientation;
    final float[] orientation;
    private float pitch;
    float[] pressure;
    private j processor;
    int[] realId;
    boolean requestFocus;
    private float roll;
    private boolean rotationVectorAvailable;
    private SensorEventListener rotationVectorListener;
    protected final float[] rotationVectorValues;
    private int sleepTime;
    ArrayList<TouchEvent> touchEvents;
    protected final AndroidTouchHandler touchHandler;
    int[] touchX;
    int[] touchY;
    boolean[] touched;
    c0<KeyEvent> usedKeyEvents;
    c0<TouchEvent> usedTouchEvents;
    protected final Vibrator vibrator;

    /* JADX INFO: renamed from: com.badlogic.gdx.backends.android.DefaultAndroidInput$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType;

        static {
            int[] iArr = new int[g.b.values().length];
            $SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType = iArr;
            try {
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType[5] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static class KeyEvent {
        static final int KEY_DOWN = 0;
        static final int KEY_TYPED = 2;
        static final int KEY_UP = 1;
        char keyChar;
        int keyCode;
        long timeStamp;
        int type;

        KeyEvent() {
        }
    }

    private class SensorListener implements SensorEventListener {
        public SensorListener() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            int type = sensorEvent.sensor.getType();
            g.c cVar = g.c.f1665b;
            if (type == 1) {
                DefaultAndroidInput defaultAndroidInput = DefaultAndroidInput.this;
                if (defaultAndroidInput.nativeOrientation == cVar) {
                    float[] fArr = sensorEvent.values;
                    float[] fArr2 = defaultAndroidInput.accelerometerValues;
                    System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
                } else {
                    float[] fArr3 = defaultAndroidInput.accelerometerValues;
                    float[] fArr4 = sensorEvent.values;
                    fArr3[0] = fArr4[1];
                    fArr3[1] = -fArr4[0];
                    fArr3[2] = fArr4[2];
                }
            }
            if (sensorEvent.sensor.getType() == 2) {
                float[] fArr5 = sensorEvent.values;
                float[] fArr6 = DefaultAndroidInput.this.magneticFieldValues;
                System.arraycopy(fArr5, 0, fArr6, 0, fArr6.length);
            }
            if (sensorEvent.sensor.getType() == 4) {
                DefaultAndroidInput defaultAndroidInput2 = DefaultAndroidInput.this;
                if (defaultAndroidInput2.nativeOrientation == cVar) {
                    float[] fArr7 = sensorEvent.values;
                    float[] fArr8 = defaultAndroidInput2.gyroscopeValues;
                    System.arraycopy(fArr7, 0, fArr8, 0, fArr8.length);
                } else {
                    float[] fArr9 = defaultAndroidInput2.gyroscopeValues;
                    float[] fArr10 = sensorEvent.values;
                    fArr9[0] = fArr10[1];
                    fArr9[1] = -fArr10[0];
                    fArr9[2] = fArr10[2];
                }
            }
            if (sensorEvent.sensor.getType() == 11) {
                DefaultAndroidInput defaultAndroidInput3 = DefaultAndroidInput.this;
                if (defaultAndroidInput3.nativeOrientation == cVar) {
                    float[] fArr11 = sensorEvent.values;
                    float[] fArr12 = defaultAndroidInput3.rotationVectorValues;
                    System.arraycopy(fArr11, 0, fArr12, 0, fArr12.length);
                } else {
                    float[] fArr13 = defaultAndroidInput3.rotationVectorValues;
                    float[] fArr14 = sensorEvent.values;
                    fArr13[0] = fArr14[1];
                    fArr13[1] = -fArr14[0];
                    fArr13[2] = fArr14[2];
                }
            }
        }
    }

    static class TouchEvent {
        static final int TOUCH_DOWN = 0;
        static final int TOUCH_DRAGGED = 2;
        static final int TOUCH_MOVED = 4;
        static final int TOUCH_SCROLLED = 3;
        static final int TOUCH_UP = 1;
        int button;
        int pointer;
        int scrollAmountX;
        int scrollAmountY;
        long timeStamp;
        int type;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        int f1609x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        int f1610y;

        TouchEvent() {
        }
    }

    public DefaultAndroidInput(Application application, Context context, Object obj, AndroidApplicationConfiguration androidApplicationConfiguration) {
        int i2 = 16;
        int i3 = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
        this.usedKeyEvents = new c0<KeyEvent>(i2, i3) { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.c0
            public KeyEvent newObject() {
                return new KeyEvent();
            }
        };
        this.usedTouchEvents = new c0<TouchEvent>(i2, i3) { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.2
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.c0
            public TouchEvent newObject() {
                return new TouchEvent();
            }
        };
        this.keyListeners = new ArrayList<>();
        this.keyEvents = new ArrayList<>();
        this.touchEvents = new ArrayList<>();
        this.touchX = new int[20];
        this.touchY = new int[20];
        this.deltaX = new int[20];
        this.deltaY = new int[20];
        this.touched = new boolean[20];
        this.button = new int[20];
        this.realId = new int[20];
        this.pressure = new float[20];
        int i4 = 0;
        this.keyCount = 0;
        this.keys = new boolean[SUPPORTED_KEYS];
        this.keyJustPressed = false;
        this.justPressedKeys = new boolean[SUPPORTED_KEYS];
        this.justPressedButtons = new boolean[20];
        this.accelerometerAvailable = false;
        this.accelerometerValues = new float[3];
        this.gyroscopeAvailable = false;
        this.gyroscopeValues = new float[3];
        this.sleepTime = 0;
        this.keysToCatch = new r();
        this.compassAvailable = false;
        this.rotationVectorAvailable = false;
        this.magneticFieldValues = new float[3];
        this.rotationVectorValues = new float[3];
        this.azimuth = 0.0f;
        this.pitch = 0.0f;
        this.roll = 0.0f;
        this.justTouched = false;
        this.currentEventTimeStamp = 0L;
        this.genericMotionListeners = new ArrayList<>();
        this.requestFocus = true;
        this.R = new float[9];
        this.orientation = new float[3];
        if (obj instanceof View) {
            View view = (View) obj;
            view.setOnKeyListener(this);
            view.setOnTouchListener(this);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnGenericMotionListener(this);
        }
        this.config = androidApplicationConfiguration;
        this.mouseHandler = new AndroidMouseHandler();
        while (true) {
            int[] iArr = this.realId;
            if (i4 >= iArr.length) {
                break;
            }
            iArr[i4] = -1;
            i4++;
        }
        this.handle = new Handler();
        this.app = application;
        this.context = context;
        this.sleepTime = androidApplicationConfiguration.touchSleepTime;
        AndroidTouchHandler androidTouchHandler = new AndroidTouchHandler();
        this.touchHandler = androidTouchHandler;
        this.hasMultitouch = androidTouchHandler.supportsMultitouch(context);
        this.vibrator = (Vibrator) context.getSystemService("vibrator");
        int rotation = getRotation();
        f.b displayMode = application.getGraphics().getDisplayMode();
        if (((rotation == 0 || rotation == 180) && displayMode.width >= displayMode.height) || ((rotation == 90 || rotation == 270) && displayMode.width <= displayMode.height)) {
            this.nativeOrientation = g.c.f1664a;
        } else {
            this.nativeOrientation = g.c.f1665b;
        }
        this.keysToCatch.a(255);
    }

    public static int getAndroidInputType(g.b bVar) {
        int i2 = AnonymousClass5.$SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType[bVar.ordinal()];
        if (i2 == 1) {
            return 2;
        }
        if (i2 == 2) {
            return 3;
        }
        if (i2 == 3) {
            return 33;
        }
        if (i2 != 4) {
            return i2 != 5 ? 144 : 17;
        }
        return 129;
    }

    private int[] resize(int[] iArr) {
        int[] iArr2 = new int[iArr.length + 2];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    private void updateOrientation() {
        if (this.rotationVectorAvailable) {
            SensorManager.getRotationMatrixFromVector(this.R, this.rotationVectorValues);
        } else if (!SensorManager.getRotationMatrix(this.R, null, this.accelerometerValues, this.magneticFieldValues)) {
            return;
        }
        SensorManager.getOrientation(this.R, this.orientation);
        this.azimuth = (float) Math.toDegrees(this.orientation[0]);
        this.pitch = (float) Math.toDegrees(this.orientation[1]);
        this.roll = (float) Math.toDegrees(this.orientation[2]);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void addGenericMotionListener(View.OnGenericMotionListener onGenericMotionListener) {
        this.genericMotionListeners.add(onGenericMotionListener);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void addKeyListener(View.OnKeyListener onKeyListener) {
        this.keyListeners.add(onKeyListener);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void cancelVibrate() {
        this.vibrator.cancel();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getAccelerometerX() {
        return this.accelerometerValues[0];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getAccelerometerY() {
        return this.accelerometerValues[1];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getAccelerometerZ() {
        return this.accelerometerValues[2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getAzimuth() {
        if (!this.compassAvailable && !this.rotationVectorAvailable) {
            return 0.0f;
        }
        updateOrientation();
        return this.azimuth;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public long getCurrentEventTime() {
        return this.currentEventTimeStamp;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public int getDeltaX() {
        return this.deltaX[0];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public int getDeltaY() {
        return this.deltaY[0];
    }

    public int getFreePointerIndex() {
        int length = this.realId.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.realId[i2] == -1) {
                return i2;
            }
        }
        this.pressure = resize(this.pressure);
        this.realId = resize(this.realId);
        this.touchX = resize(this.touchX);
        this.touchY = resize(this.touchY);
        this.deltaX = resize(this.deltaX);
        this.deltaY = resize(this.deltaY);
        this.touched = resize(this.touched);
        this.button = resize(this.button);
        return length;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getGyroscopeX() {
        return this.gyroscopeValues[0];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getGyroscopeY() {
        return this.gyroscopeValues[1];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getGyroscopeZ() {
        return this.gyroscopeValues[2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public j getInputProcessor() {
        return this.processor;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public int getMaxPointers() {
        return 20;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public g.c getNativeOrientation() {
        return this.nativeOrientation;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getPitch() {
        if (!this.compassAvailable && !this.rotationVectorAvailable) {
            return 0.0f;
        }
        updateOrientation();
        return this.pitch;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getPressure() {
        return getPressure(0);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getRoll() {
        if (!this.compassAvailable && !this.rotationVectorAvailable) {
            return 0.0f;
        }
        updateOrientation();
        return this.roll;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public int getRotation() {
        Context context = this.context;
        int rotation = context instanceof Activity ? ((Activity) context).getWindowManager().getDefaultDisplay().getRotation() : ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void getRotationMatrix(float[] fArr) {
        if (this.rotationVectorAvailable) {
            SensorManager.getRotationMatrixFromVector(fArr, this.rotationVectorValues);
        } else {
            SensorManager.getRotationMatrix(fArr, null, this.accelerometerValues, this.magneticFieldValues);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void getTextInput(g.e eVar, String str, String str2, String str3) {
        getTextInput(eVar, str, str2, str3, g.b.f1661a);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public int getX() {
        int i2;
        synchronized (this) {
            i2 = this.touchX[0];
        }
        return i2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public int getY() {
        int i2;
        synchronized (this) {
            i2 = this.touchY[0];
        }
        return i2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public boolean isButtonJustPressed(int i2) {
        if (i2 < 0 || i2 > 20) {
            return false;
        }
        return this.justPressedButtons[i2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public boolean isButtonPressed(int i2) {
        synchronized (this) {
            try {
                boolean z2 = true;
                if (this.hasMultitouch) {
                    for (int i3 = 0; i3 < 20; i3++) {
                        if (this.touched[i3] && this.button[i3] == i2) {
                            return true;
                        }
                    }
                }
                if (!this.touched[0] || this.button[0] != i2) {
                    z2 = false;
                }
                return z2;
            } finally {
            }
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public boolean isCatchBackKey() {
        return this.keysToCatch.b(4);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public boolean isCatchKey(int i2) {
        return this.keysToCatch.b(i2);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public boolean isCatchMenuKey() {
        return this.keysToCatch.b(82);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public boolean isCursorCatched() {
        return false;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public synchronized boolean isKeyJustPressed(int i2) {
        if (i2 == -1) {
            return this.keyJustPressed;
        }
        if (i2 < 0 || i2 >= 260) {
            return false;
        }
        return this.justPressedKeys[i2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public synchronized boolean isKeyPressed(int i2) {
        if (i2 == -1) {
            return this.keyCount > 0;
        }
        if (i2 < 0 || i2 >= 260) {
            return false;
        }
        return this.keys[i2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public boolean isPeripheralAvailable(g.d dVar) {
        if (dVar == g.d.f1670d) {
            return this.accelerometerAvailable;
        }
        if (dVar == g.d.f1673g) {
            return this.gyroscopeAvailable;
        }
        if (dVar == g.d.f1671e) {
            return this.compassAvailable;
        }
        if (dVar == g.d.f1667a) {
            return this.keyboardAvailable;
        }
        if (dVar == g.d.f1668b) {
            return true;
        }
        if (dVar != g.d.f1672f) {
            return dVar == g.d.f1669c ? this.hasMultitouch : dVar == g.d.f1674h ? this.rotationVectorAvailable : dVar == g.d.f1675i;
        }
        Vibrator vibrator = this.vibrator;
        return vibrator != null && vibrator.hasVibrator();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public boolean isTouched(int i2) {
        boolean z2;
        synchronized (this) {
            z2 = this.touched[i2];
        }
        return z2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public boolean justTouched() {
        return this.justTouched;
    }

    public int lookUpPointerIndex(int i2) {
        int length = this.realId.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.realId[i3] == i2) {
                return i3;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < length; i4++) {
            sb.append(i4 + ":" + this.realId[i4] + " ");
        }
        Application application = Gdx.app;
        StringBuilder sbR = a.a.r(i2, "Pointer ID lookup failed: ", ", ");
        sbR.append(sb.toString());
        application.log("AndroidInput", sbR.toString());
        return -1;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void onDreamingStarted() {
        registerSensorListeners();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void onDreamingStopped() {
        unregisterSensorListeners();
        Arrays.fill(this.realId, -1);
        Arrays.fill(this.touched, false);
    }

    @Override // android.view.View.OnGenericMotionListener
    public boolean onGenericMotion(View view, MotionEvent motionEvent) {
        if (this.mouseHandler.onGenericMotion(motionEvent, this)) {
            return true;
        }
        int size = this.genericMotionListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.genericMotionListeners.get(i2).onGenericMotion(view, motionEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, android.view.KeyEvent keyEvent) {
        int size = this.keyListeners.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.keyListeners.get(i3).onKey(view, i2, keyEvent)) {
                return true;
            }
        }
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() > 0) {
            return this.keysToCatch.b(i2);
        }
        synchronized (this) {
            try {
                if (keyEvent.getKeyCode() == 0 && keyEvent.getAction() == 2) {
                    String characters = keyEvent.getCharacters();
                    for (int i4 = 0; i4 < characters.length(); i4++) {
                        KeyEvent keyEventObtain = this.usedKeyEvents.obtain();
                        keyEventObtain.timeStamp = System.nanoTime();
                        keyEventObtain.keyCode = 0;
                        keyEventObtain.keyChar = characters.charAt(i4);
                        keyEventObtain.type = 2;
                        this.keyEvents.add(keyEventObtain);
                    }
                    return false;
                }
                char unicodeChar = (char) keyEvent.getUnicodeChar();
                if (i2 == 67) {
                    unicodeChar = '\b';
                }
                if (keyEvent.getKeyCode() >= 0 && keyEvent.getKeyCode() < 260) {
                    int action = keyEvent.getAction();
                    if (action == 0) {
                        KeyEvent keyEventObtain2 = this.usedKeyEvents.obtain();
                        keyEventObtain2.timeStamp = System.nanoTime();
                        keyEventObtain2.keyChar = (char) 0;
                        keyEventObtain2.keyCode = keyEvent.getKeyCode();
                        keyEventObtain2.type = 0;
                        if (i2 == 4 && keyEvent.isAltPressed()) {
                            keyEventObtain2.keyCode = 255;
                            i2 = 255;
                        }
                        this.keyEvents.add(keyEventObtain2);
                        boolean[] zArr = this.keys;
                        int i5 = keyEventObtain2.keyCode;
                        if (!zArr[i5]) {
                            this.keyCount++;
                            zArr[i5] = true;
                        }
                    } else if (action == 1) {
                        long jNanoTime = System.nanoTime();
                        KeyEvent keyEventObtain3 = this.usedKeyEvents.obtain();
                        keyEventObtain3.timeStamp = jNanoTime;
                        keyEventObtain3.keyChar = (char) 0;
                        keyEventObtain3.keyCode = keyEvent.getKeyCode();
                        keyEventObtain3.type = 1;
                        if (i2 == 4 && keyEvent.isAltPressed()) {
                            keyEventObtain3.keyCode = 255;
                            i2 = 255;
                        }
                        this.keyEvents.add(keyEventObtain3);
                        KeyEvent keyEventObtain4 = this.usedKeyEvents.obtain();
                        keyEventObtain4.timeStamp = jNanoTime;
                        keyEventObtain4.keyChar = unicodeChar;
                        keyEventObtain4.keyCode = 0;
                        keyEventObtain4.type = 2;
                        this.keyEvents.add(keyEventObtain4);
                        if (i2 == 255) {
                            boolean[] zArr2 = this.keys;
                            if (zArr2[255]) {
                                this.keyCount--;
                                zArr2[255] = false;
                            }
                        } else if (this.keys[keyEvent.getKeyCode()]) {
                            this.keyCount--;
                            this.keys[keyEvent.getKeyCode()] = false;
                        }
                    }
                    this.app.getGraphics().requestRendering();
                    return this.keysToCatch.b(i2);
                }
                return false;
            } finally {
            }
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void onPause() {
        unregisterSensorListeners();
        Arrays.fill(this.realId, -1);
        Arrays.fill(this.touched, false);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void onResume() {
        registerSensorListeners();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.requestFocus && view != null) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            this.requestFocus = false;
        }
        this.touchHandler.onTouch(motionEvent, this);
        int i2 = this.sleepTime;
        if (i2 != 0) {
            try {
                Thread.sleep(i2);
            } catch (InterruptedException unused) {
            }
        }
        return true;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void processEvents() {
        synchronized (this) {
            try {
                if (this.justTouched) {
                    this.justTouched = false;
                    int i2 = 0;
                    while (true) {
                        boolean[] zArr = this.justPressedButtons;
                        if (i2 >= zArr.length) {
                            break;
                        }
                        zArr[i2] = false;
                        i2++;
                    }
                }
                if (this.keyJustPressed) {
                    this.keyJustPressed = false;
                    int i3 = 0;
                    while (true) {
                        boolean[] zArr2 = this.justPressedKeys;
                        if (i3 >= zArr2.length) {
                            break;
                        }
                        zArr2[i3] = false;
                        i3++;
                    }
                }
                j jVar = this.processor;
                if (jVar != null) {
                    int size = this.keyEvents.size();
                    for (int i4 = 0; i4 < size; i4++) {
                        KeyEvent keyEvent = this.keyEvents.get(i4);
                        this.currentEventTimeStamp = keyEvent.timeStamp;
                        int i5 = keyEvent.type;
                        if (i5 == 0) {
                            jVar.keyDown(keyEvent.keyCode);
                            this.keyJustPressed = true;
                            this.justPressedKeys[keyEvent.keyCode] = true;
                        } else if (i5 == 1) {
                            jVar.keyUp(keyEvent.keyCode);
                        } else if (i5 == 2) {
                            jVar.keyTyped(keyEvent.keyChar);
                        }
                        this.usedKeyEvents.free(keyEvent);
                    }
                    int size2 = this.touchEvents.size();
                    for (int i6 = 0; i6 < size2; i6++) {
                        TouchEvent touchEvent = this.touchEvents.get(i6);
                        this.currentEventTimeStamp = touchEvent.timeStamp;
                        int i7 = touchEvent.type;
                        if (i7 == 0) {
                            jVar.touchDown(touchEvent.f1609x, touchEvent.f1610y, touchEvent.pointer, touchEvent.button);
                            this.justTouched = true;
                            this.justPressedButtons[touchEvent.button] = true;
                        } else if (i7 == 1) {
                            jVar.touchUp(touchEvent.f1609x, touchEvent.f1610y, touchEvent.pointer, touchEvent.button);
                        } else if (i7 == 2) {
                            jVar.touchDragged(touchEvent.f1609x, touchEvent.f1610y, touchEvent.pointer);
                        } else if (i7 == 3) {
                            jVar.scrolled(touchEvent.scrollAmountX, touchEvent.scrollAmountY);
                        } else if (i7 == 4) {
                            jVar.mouseMoved(touchEvent.f1609x, touchEvent.f1610y);
                        }
                        this.usedTouchEvents.free(touchEvent);
                    }
                } else {
                    int size3 = this.touchEvents.size();
                    for (int i8 = 0; i8 < size3; i8++) {
                        TouchEvent touchEvent2 = this.touchEvents.get(i8);
                        if (touchEvent2.type == 0) {
                            this.justTouched = true;
                        }
                        this.usedTouchEvents.free(touchEvent2);
                    }
                    int size4 = this.keyEvents.size();
                    for (int i9 = 0; i9 < size4; i9++) {
                        this.usedKeyEvents.free(this.keyEvents.get(i9));
                    }
                }
                if (this.touchEvents.isEmpty()) {
                    int i10 = 0;
                    while (true) {
                        int[] iArr = this.deltaX;
                        if (i10 >= iArr.length) {
                            break;
                        }
                        iArr[0] = 0;
                        this.deltaY[0] = 0;
                        i10++;
                    }
                }
                this.keyEvents.clear();
                this.touchEvents.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void registerSensorListeners() {
        if (this.config.useAccelerometer) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            this.manager = sensorManager;
            if (sensorManager.getSensorList(1).isEmpty()) {
                this.accelerometerAvailable = false;
            } else {
                Sensor sensor = this.manager.getSensorList(1).get(0);
                SensorListener sensorListener = new SensorListener();
                this.accelerometerListener = sensorListener;
                this.accelerometerAvailable = this.manager.registerListener(sensorListener, sensor, this.config.sensorDelay);
            }
        } else {
            this.accelerometerAvailable = false;
        }
        if (this.config.useGyroscope) {
            SensorManager sensorManager2 = (SensorManager) this.context.getSystemService("sensor");
            this.manager = sensorManager2;
            if (sensorManager2.getSensorList(4).isEmpty()) {
                this.gyroscopeAvailable = false;
            } else {
                Sensor sensor2 = this.manager.getSensorList(4).get(0);
                SensorListener sensorListener2 = new SensorListener();
                this.gyroscopeListener = sensorListener2;
                this.gyroscopeAvailable = this.manager.registerListener(sensorListener2, sensor2, this.config.sensorDelay);
            }
        } else {
            this.gyroscopeAvailable = false;
        }
        this.rotationVectorAvailable = false;
        if (this.config.useRotationVectorSensor) {
            if (this.manager == null) {
                this.manager = (SensorManager) this.context.getSystemService("sensor");
            }
            List<Sensor> sensorList = this.manager.getSensorList(11);
            if (!sensorList.isEmpty()) {
                this.rotationVectorListener = new SensorListener();
                Iterator<Sensor> it = sensorList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Sensor next = it.next();
                    if (next.getVendor().equals("Google Inc.") && next.getVersion() == 3) {
                        this.rotationVectorAvailable = this.manager.registerListener(this.rotationVectorListener, next, this.config.sensorDelay);
                        break;
                    }
                }
                if (!this.rotationVectorAvailable) {
                    this.rotationVectorAvailable = this.manager.registerListener(this.rotationVectorListener, sensorList.get(0), this.config.sensorDelay);
                }
            }
        }
        if (!this.config.useCompass || this.rotationVectorAvailable) {
            this.compassAvailable = false;
        } else {
            if (this.manager == null) {
                this.manager = (SensorManager) this.context.getSystemService("sensor");
            }
            Sensor defaultSensor = this.manager.getDefaultSensor(2);
            if (defaultSensor != null) {
                boolean z2 = this.accelerometerAvailable;
                this.compassAvailable = z2;
                if (z2) {
                    SensorListener sensorListener3 = new SensorListener();
                    this.compassListener = sensorListener3;
                    this.compassAvailable = this.manager.registerListener(sensorListener3, defaultSensor, this.config.sensorDelay);
                }
            } else {
                this.compassAvailable = false;
            }
        }
        Gdx.app.log("AndroidInput", "sensor listener setup");
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public void setCatchBackKey(boolean z2) {
        setCatchKey(4, z2);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void setCatchKey(int i2, boolean z2) {
        if (!z2) {
            this.keysToCatch.e(i2);
        } else if (z2) {
            this.keysToCatch.a(i2);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void setCatchMenuKey(boolean z2) {
        setCatchKey(82, z2);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void setCursorCatched(boolean z2) {
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void setCursorPosition(int i2, int i3) {
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public void setInputProcessor(j jVar) {
        synchronized (this) {
            this.processor = jVar;
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void setKeyboardAvailable(boolean z2) {
        this.keyboardAvailable = z2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public void setOnscreenKeyboardVisible(boolean z2) {
        setOnscreenKeyboardVisible(z2, g.b.f1661a);
    }

    void unregisterSensorListeners() {
        SensorManager sensorManager = this.manager;
        if (sensorManager != null) {
            SensorEventListener sensorEventListener = this.accelerometerListener;
            if (sensorEventListener != null) {
                sensorManager.unregisterListener(sensorEventListener);
                this.accelerometerListener = null;
            }
            SensorEventListener sensorEventListener2 = this.gyroscopeListener;
            if (sensorEventListener2 != null) {
                this.manager.unregisterListener(sensorEventListener2);
                this.gyroscopeListener = null;
            }
            SensorEventListener sensorEventListener3 = this.rotationVectorListener;
            if (sensorEventListener3 != null) {
                this.manager.unregisterListener(sensorEventListener3);
                this.rotationVectorListener = null;
            }
            SensorEventListener sensorEventListener4 = this.compassListener;
            if (sensorEventListener4 != null) {
                this.manager.unregisterListener(sensorEventListener4);
                this.compassListener = null;
            }
            this.manager = null;
        }
        Gdx.app.log("AndroidInput", "sensor listener tear down");
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void vibrate(int i2) {
        this.vibrator.vibrate(VibrationEffect.createOneShot(i2, -1));
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public int getDeltaX(int i2) {
        return this.deltaX[i2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public int getDeltaY(int i2) {
        return this.deltaY[i2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public float getPressure(int i2) {
        return this.pressure[i2];
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void getTextInput(g.e eVar, String str, String str2, String str3, g.b bVar) {
        this.handle.post(new Runnable(str, bVar, str3, str2, eVar) { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.3
            final /* synthetic */ String val$hint;
            final /* synthetic */ g.b val$keyboardType;
            final /* synthetic */ g.e val$listener;
            final /* synthetic */ String val$text;
            final /* synthetic */ String val$title;

            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(DefaultAndroidInput.this.context);
                builder.setTitle(this.val$title);
                final EditText editText = new EditText(DefaultAndroidInput.this.context);
                g.b bVar2 = this.val$keyboardType;
                if (bVar2 != g.b.f1661a) {
                    editText.setInputType(DefaultAndroidInput.getAndroidInputType(bVar2));
                }
                editText.setHint(this.val$hint);
                editText.setText(this.val$text);
                editText.setSingleLine();
                if (this.val$keyboardType == g.b.f1662b) {
                    editText.setTransformationMethod(new PasswordTransformationMethod());
                }
                builder.setView(editText);
                builder.setPositiveButton(DefaultAndroidInput.this.context.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        Gdx.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.3.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                getClass();
                                editText.getText().toString();
                                throw null;
                            }
                        });
                    }
                });
                builder.setNegativeButton(DefaultAndroidInput.this.context.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.3.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        Gdx.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.3.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                getClass();
                                throw null;
                            }
                        });
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.3.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        Gdx.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.3.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                getClass();
                                throw null;
                            }
                        });
                    }
                });
                builder.show();
            }
        });
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void setOnscreenKeyboardVisible(final boolean z2, final g.b bVar) {
        this.handle.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.DefaultAndroidInput.4
            @Override // java.lang.Runnable
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) DefaultAndroidInput.this.context.getSystemService("input_method");
                if (!z2) {
                    inputMethodManager.hideSoftInputFromWindow(((AndroidGraphics) DefaultAndroidInput.this.app.getGraphics()).getView().getWindowToken(), 0);
                    return;
                }
                View view = ((AndroidGraphics) DefaultAndroidInput.this.app.getGraphics()).getView();
                g.b bVar2 = bVar;
                if (bVar2 == null) {
                    bVar2 = g.b.f1661a;
                }
                GLSurfaceView20 gLSurfaceView20 = (GLSurfaceView20) view;
                if (gLSurfaceView20.onscreenKeyboardType != bVar2) {
                    gLSurfaceView20.onscreenKeyboardType = bVar2;
                    inputMethodManager.restartInput(view);
                }
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                inputMethodManager.showSoftInput(((AndroidGraphics) DefaultAndroidInput.this.app.getGraphics()).getView(), 0);
            }
        });
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public void vibrate(long[] jArr, int i2) {
        this.vibrator.vibrate(VibrationEffect.createWaveform(jArr, i2));
    }

    private boolean[] resize(boolean[] zArr) {
        boolean[] zArr2 = new boolean[zArr.length + 2];
        System.arraycopy(zArr, 0, zArr2, 0, zArr.length);
        return zArr2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public int getX(int i2) {
        int i3;
        synchronized (this) {
            i3 = this.touchX[i2];
        }
        return i3;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput
    public int getY(int i2) {
        int i3;
        synchronized (this) {
            i3 = this.touchY[i2];
        }
        return i3;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidInput, com.badlogic.gdx.g
    public boolean isTouched() {
        synchronized (this) {
            try {
                if (this.hasMultitouch) {
                    for (int i2 = 0; i2 < 20; i2++) {
                        if (this.touched[i2]) {
                            return true;
                        }
                    }
                }
                return this.touched[0];
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private float[] resize(float[] fArr) {
        float[] fArr2 = new float[fArr.length + 2];
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        return fArr2;
    }
}
