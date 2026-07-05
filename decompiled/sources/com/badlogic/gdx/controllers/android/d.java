package com.badlogic.gdx.controllers.android;

import android.content.Context;
import android.hardware.input.InputManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.k;

/* JADX INFO: compiled from: ControllerLifeCycleListener.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class d implements k, InputManager.InputDeviceListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InputManager f1642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AndroidControllers f1643c;

    public d(AndroidControllers androidControllers) {
        this.f1643c = androidControllers;
        InputManager inputManager = (InputManager) ((Context) Gdx.app).getSystemService("input");
        this.f1642b = inputManager;
        Gdx.app.addLifecycleListener(this);
        inputManager.registerInputDeviceListener(this, ((AndroidApplication) Gdx.app).handler);
    }

    @Override // com.badlogic.gdx.k
    public final void dispose() {
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public final void onInputDeviceAdded(int i2) {
        this.f1643c.f(i2, true);
        Gdx.app.log("ControllerLifeCycleListener", "device " + i2 + " added");
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public final void onInputDeviceChanged(int i2) {
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public final void onInputDeviceRemoved(int i2) {
        this.f1643c.h(i2);
        Gdx.app.log("ControllerLifeCycleListener", "device " + i2 + " removed");
    }

    @Override // com.badlogic.gdx.k
    public final void pause() {
        this.f1642b.unregisterInputDeviceListener(this);
        Gdx.app.log("ControllerLifeCycleListener", "controller life cycle listener paused");
    }

    @Override // com.badlogic.gdx.k
    public final void resume() {
        this.f1642b.registerInputDeviceListener(this, ((AndroidApplication) Gdx.app).handler);
        Gdx.app.log("ControllerLifeCycleListener", "controller life cycle listener resumed");
    }
}
