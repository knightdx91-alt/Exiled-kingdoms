package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FPSLogger {
    int bound;
    long startTime;

    public FPSLogger() {
        this(Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public void log() {
        int framesPerSecond;
        long jNanoTime = System.nanoTime();
        if (jNanoTime - this.startTime <= 1000000000 || (framesPerSecond = Gdx.graphics.getFramesPerSecond()) >= this.bound) {
            return;
        }
        Gdx.app.log("FPSLogger", "fps: " + framesPerSecond);
        this.startTime = jNanoTime;
    }

    public FPSLogger(int i2) {
        this.bound = i2;
        this.startTime = System.nanoTime();
    }
}
