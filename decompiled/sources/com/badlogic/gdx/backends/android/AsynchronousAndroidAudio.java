package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import t.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AsynchronousAndroidAudio extends DefaultAndroidAudio {
    private final Handler handler;
    private final HandlerThread handlerThread;

    public AsynchronousAndroidAudio(Context context, AndroidApplicationConfiguration androidApplicationConfiguration) {
        super(context, androidApplicationConfiguration);
        if (androidApplicationConfiguration.disableAudio) {
            this.handler = null;
            this.handlerThread = null;
        } else {
            HandlerThread handlerThread = new HandlerThread("libGDX Sound Management");
            this.handlerThread = handlerThread;
            handlerThread.start();
            this.handler = new Handler(handlerThread.getLooper());
        }
    }

    @Override // com.badlogic.gdx.backends.android.DefaultAndroidAudio, com.badlogic.gdx.backends.android.AndroidAudio, com.badlogic.gdx.utils.i
    public void dispose() {
        super.dispose();
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }

    @Override // com.badlogic.gdx.backends.android.DefaultAndroidAudio, com.badlogic.gdx.backends.android.AndroidAudio, com.badlogic.gdx.c
    public d newSound(com.badlogic.gdx.files.a aVar) {
        return new AsynchronousSound(super.newSound(aVar), this.handler);
    }
}
