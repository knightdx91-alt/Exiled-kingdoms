package com.badlogic.gdx.backends.android;

import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidApplicationConfiguration {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1605r = 5;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1604g = 6;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1603b = 5;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1602a = 0;
    public int depth = 16;
    public int stencil = 0;
    public int numSamples = 0;
    public boolean useAccelerometer = true;
    public boolean useGyroscope = false;
    public boolean useCompass = true;
    public boolean useRotationVectorSensor = false;
    public int sensorDelay = 1;
    public int touchSleepTime = 0;
    public boolean useWakelock = false;
    public boolean hideStatusBar = false;
    public boolean disableAudio = false;
    public int maxSimultaneousSounds = 16;
    public ResolutionStrategy resolutionStrategy = new FillResolutionStrategy();
    public boolean getTouchEventsForLiveWallpaper = false;
    public boolean useImmersiveMode = false;

    @Deprecated
    public boolean useGL30 = false;
    public int maxNetThreads = Api.BaseClientBuilder.API_PRIORITY_OTHER;
}
