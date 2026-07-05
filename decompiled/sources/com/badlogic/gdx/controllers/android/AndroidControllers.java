package com.badlogic.gdx.controllers.android;

import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidInput;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.ControllerManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.k;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.q;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidControllers implements k, ControllerManager, View.OnKeyListener, View.OnGenericMotionListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final q<com.badlogic.gdx.controllers.android.a> f1625b = new q<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.badlogic.gdx.utils.a<Controller> f1626c = new com.badlogic.gdx.utils.a<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.badlogic.gdx.utils.a<ControllerListener> f1627d = new com.badlogic.gdx.utils.a<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.badlogic.gdx.utils.a<b> f1628e = new com.badlogic.gdx.utils.a<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c0<b> f1629f = new a();

    final class a extends c0<b> {
        @Override // com.badlogic.gdx.utils.c0
        protected final b newObject() {
            return new b();
        }
    }

    public AndroidControllers() {
        Gdx.app.addLifecycleListener(this);
        g(false);
        new c(this).run();
        ((AndroidInput) Gdx.input).addKeyListener(this);
        ((AndroidInput) Gdx.input).addGenericMotionListener(this);
        if (Gdx.app.getVersion() >= 16) {
            try {
                d.class.getConstructor(AndroidControllers.class).newInstance(this);
            } catch (Exception unused) {
                Gdx.app.log("AndroidControllers", "Couldn't register controller life-cycle listener");
            }
        }
    }

    private void g(boolean z2) {
        q qVar = new q();
        q<com.badlogic.gdx.controllers.android.a> qVar2 = this.f1625b;
        qVar.d(qVar2);
        for (int i2 : InputDevice.getDeviceIds()) {
            InputDevice.getDevice(i2);
            if (qVar2.get(i2) != null) {
                qVar.remove(i2);
            } else {
                f(i2, z2);
            }
        }
        q.a aVarA = qVar.a();
        while (aVarA.hasNext()) {
            h(((q.b) aVarA.next()).f1895a);
        }
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public final com.badlogic.gdx.utils.a<Controller> a() {
        return this.f1626c;
    }

    @Override // com.badlogic.gdx.k
    public final void dispose() {
    }

    protected final void f(int i2, boolean z2) {
        try {
            InputDevice device = InputDevice.getDevice(i2);
            if ((device.getSources() & 16) == 16) {
                if ((device.getSources() & 1025) == 1025 || device.getKeyboardType() != 2) {
                    String name = device.getName();
                    com.badlogic.gdx.controllers.android.a aVar = new com.badlogic.gdx.controllers.android.a(i2, name);
                    this.f1625b.c(i2, aVar);
                    if (z2) {
                        synchronized (this.f1628e) {
                            b bVarObtain = this.f1629f.obtain();
                            bVarObtain.f1638b = 4;
                            bVarObtain.f1637a = aVar;
                            this.f1628e.a(bVarObtain);
                        }
                    } else {
                        this.f1626c.a(aVar);
                    }
                    Gdx.app.log("AndroidControllers", "added controller '" + name + "'");
                }
            }
        } catch (RuntimeException e2) {
            Gdx.app.error("AndroidControllers", "Could not get information about " + i2 + ", ignoring the device.", e2);
        }
    }

    protected final void h(int i2) {
        com.badlogic.gdx.controllers.android.a aVarRemove = this.f1625b.remove(i2);
        if (aVarRemove != null) {
            synchronized (this.f1628e) {
                b bVarObtain = this.f1629f.obtain();
                bVarObtain.f1638b = 5;
                bVarObtain.f1637a = aVarRemove;
                this.f1628e.a(bVarObtain);
            }
            Gdx.app.log("AndroidControllers", "removed controller '" + aVarRemove.getName() + "'");
        }
    }

    @Override // android.view.View.OnGenericMotionListener
    public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
        com.badlogic.gdx.controllers.android.a aVar;
        int i2 = 16;
        if ((motionEvent.getSource() & 16) == 0 || (aVar = this.f1625b.get(motionEvent.getDeviceId())) == null) {
            return false;
        }
        synchronized (this.f1628e) {
            try {
                motionEvent.getHistorySize();
                if (aVar.e()) {
                    float axisValue = motionEvent.getAxisValue(15);
                    float axisValue2 = motionEvent.getAxisValue(16);
                    if (Float.compare(axisValue2, -1.0f) == 0) {
                        i2 = 1;
                    } else if (Float.compare(axisValue2, 1.0f) != 0) {
                        i2 = 0;
                    }
                    if (Float.compare(axisValue, 1.0f) == 0) {
                        i2 |= 256;
                    } else if (Float.compare(axisValue, -1.0f) == 0) {
                        i2 |= 4096;
                    }
                    if (i2 != aVar.f1634e) {
                        aVar.f1634e = i2;
                        b bVarObtain = this.f1629f.obtain();
                        bVarObtain.f1638b = 3;
                        bVarObtain.f1637a = aVar;
                        aVar.a(0);
                        this.f1628e.a(bVarObtain);
                    }
                }
                int i3 = 0;
                for (int i4 : aVar.f1633d) {
                    float axisValue3 = motionEvent.getAxisValue(i4);
                    if (aVar.c(i3) != axisValue3) {
                        b bVarObtain2 = this.f1629f.obtain();
                        bVarObtain2.f1638b = 2;
                        bVarObtain2.f1637a = aVar;
                        bVarObtain2.f1639c = i3;
                        bVarObtain2.f1640d = axisValue3;
                        this.f1628e.a(bVarObtain2);
                    }
                    i3++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
        com.badlogic.gdx.controllers.android.a aVar;
        if (!KeyEvent.isGamepadButton(i2) || (aVar = this.f1625b.get(keyEvent.getDeviceId())) == null) {
            return false;
        }
        if (aVar.f1631b.a(i2) && keyEvent.getAction() == 0) {
            return true;
        }
        synchronized (this.f1628e) {
            try {
                b bVarObtain = this.f1629f.obtain();
                bVarObtain.f1637a = aVar;
                if (keyEvent.getAction() == 0) {
                    if (i2 == 19) {
                        bVarObtain.f1638b = 3;
                        aVar.f1634e |= 1;
                        aVar.a(0);
                    } else if (i2 == 20) {
                        bVarObtain.f1638b = 3;
                        aVar.f1634e |= 16;
                        aVar.a(0);
                    } else if (i2 == 22) {
                        bVarObtain.f1638b = 3;
                        aVar.f1634e |= 256;
                        aVar.a(0);
                    } else if (i2 == 21) {
                        bVarObtain.f1638b = 3;
                        aVar.f1634e |= 4096;
                        aVar.a(0);
                    } else {
                        bVarObtain.f1638b = 0;
                        bVarObtain.f1639c = i2;
                    }
                } else if (i2 == 19) {
                    bVarObtain.f1638b = 3;
                    aVar.f1634e &= 4368;
                    aVar.a(0);
                } else if (i2 == 20) {
                    bVarObtain.f1638b = 3;
                    aVar.f1634e &= GL20.GL_FASTEST;
                    aVar.a(0);
                } else if (i2 == 22) {
                    bVarObtain.f1638b = 3;
                    aVar.f1634e &= 4113;
                    aVar.a(0);
                } else if (i2 == 21) {
                    bVarObtain.f1638b = 3;
                    aVar.f1634e &= 273;
                    aVar.a(0);
                } else {
                    bVarObtain.f1638b = 1;
                    bVarObtain.f1639c = i2;
                }
                this.f1628e.a(bVarObtain);
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2 != 4 || Gdx.input.isCatchBackKey();
    }

    @Override // com.badlogic.gdx.k
    public final void pause() {
        Gdx.app.log("AndroidControllers", "controllers paused");
    }

    @Override // com.badlogic.gdx.k
    public final void resume() {
        g(true);
        Gdx.app.log("AndroidControllers", "controllers resumed");
    }
}
