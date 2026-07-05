package com.badlogic.gdx.controllers.android;

import android.view.InputDevice;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.utils.p;

/* JADX INFO: compiled from: AndroidController.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a implements Controller {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1630a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final float[] f1632c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final int[] f1633d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1635f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final p f1631b = new p();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f1634e = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final com.badlogic.gdx.utils.a<ControllerListener> f1636g = new com.badlogic.gdx.utils.a<>();

    public a(int i2, String str) {
        int i3 = 0;
        this.f1630a = str;
        InputDevice device = InputDevice.getDevice(i2);
        int i4 = 0;
        for (InputDevice.MotionRange motionRange : device.getMotionRanges()) {
            if ((motionRange.getSource() & 16) != 0) {
                if (motionRange.getAxis() == 15 || motionRange.getAxis() == 16) {
                    this.f1635f = true;
                } else {
                    i4++;
                }
            }
        }
        this.f1633d = new int[i4];
        this.f1632c = new float[i4];
        for (InputDevice.MotionRange motionRange2 : device.getMotionRanges()) {
            if ((motionRange2.getSource() & 16) != 0 && motionRange2.getAxis() != 15 && motionRange2.getAxis() != 16) {
                this.f1633d[i3] = motionRange2.getAxis();
                i3++;
            }
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public final PovDirection a(int i2) {
        int i3;
        PovDirection povDirection = PovDirection.f1615a;
        if (i2 != 0 || (i3 = this.f1634e) == 0) {
            return povDirection;
        }
        if (i3 == 1) {
            return PovDirection.f1616b;
        }
        if (i3 == 16) {
            return PovDirection.f1617c;
        }
        if (i3 == 272) {
            return PovDirection.f1621g;
        }
        if (i3 == 4112) {
            return PovDirection.f1623i;
        }
        if (i3 == 256) {
            return PovDirection.f1618d;
        }
        if (i3 == 257) {
            return PovDirection.f1620f;
        }
        if (i3 == 4096) {
            return PovDirection.f1619e;
        }
        if (i3 == 4097) {
            return PovDirection.f1622h;
        }
        throw new RuntimeException("Unexpected POV value : " + this.f1634e);
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public final boolean b(int i2) {
        return this.f1631b.a(i2);
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public final float c(int i2) {
        if (i2 < 0) {
            return 0.0f;
        }
        float[] fArr = this.f1632c;
        if (i2 >= fArr.length) {
            return 0.0f;
        }
        return fArr[i2];
    }

    public final com.badlogic.gdx.utils.a<ControllerListener> d() {
        return this.f1636g;
    }

    public final boolean e() {
        return this.f1635f;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public final String getName() {
        return this.f1630a;
    }
}
