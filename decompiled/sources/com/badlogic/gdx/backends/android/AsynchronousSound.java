package com.badlogic.gdx.backends.android;

import android.os.Handler;
import t.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AsynchronousSound implements d {
    private final Handler handler;
    private final d sound;

    public AsynchronousSound(d dVar, Handler handler) {
        this.sound = dVar;
        this.handler = handler;
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        this.sound.dispose();
    }

    @Override // t.d
    public long loop() {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AsynchronousSound.4
            @Override // java.lang.Runnable
            public void run() {
                AsynchronousSound.this.sound.loop();
            }
        });
        return 0L;
    }

    @Override // t.d
    public void pause() {
        this.sound.pause();
    }

    @Override // t.d
    public long play() {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AsynchronousSound.1
            @Override // java.lang.Runnable
            public void run() {
                AsynchronousSound.this.sound.play();
            }
        });
        return 0L;
    }

    @Override // t.d
    public void resume() {
        this.sound.resume();
    }

    public void setLooping(long j2, boolean z2) {
        throw new UnsupportedOperationException("Asynchronous audio doesn't support sound id based operations.");
    }

    public void setPan(long j2, float f2, float f3) {
        throw new UnsupportedOperationException("Asynchronous audio doesn't support sound id based operations.");
    }

    public void setPitch(long j2, float f2) {
        throw new UnsupportedOperationException("Asynchronous audio doesn't support sound id based operations.");
    }

    public void setVolume(long j2, float f2) {
        throw new UnsupportedOperationException("Asynchronous audio doesn't support sound id based operations.");
    }

    @Override // t.d
    public void stop() {
        this.sound.stop();
    }

    @Override // t.d
    public long loop(final float f2) {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AsynchronousSound.5
            @Override // java.lang.Runnable
            public void run() {
                AsynchronousSound.this.sound.loop(f2);
            }
        });
        return 0L;
    }

    public void pause(long j2) {
        throw new UnsupportedOperationException("Asynchronous audio doesn't support sound id based operations.");
    }

    @Override // t.d
    public long play(final float f2) {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AsynchronousSound.2
            @Override // java.lang.Runnable
            public void run() {
                AsynchronousSound.this.sound.play(f2);
            }
        });
        return 0L;
    }

    public void resume(long j2) {
        throw new UnsupportedOperationException("Asynchronous audio doesn't support sound id based operations.");
    }

    public void stop(long j2) {
        throw new UnsupportedOperationException("Asynchronous audio doesn't support sound id based operations.");
    }

    @Override // t.d
    public long loop(final float f2, final float f3, final float f4) {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AsynchronousSound.6
            @Override // java.lang.Runnable
            public void run() {
                AsynchronousSound.this.sound.loop(f2, f3, f4);
            }
        });
        return 0L;
    }

    @Override // t.d
    public long play(final float f2, final float f3, final float f4) {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AsynchronousSound.3
            @Override // java.lang.Runnable
            public void run() {
                AsynchronousSound.this.sound.play(f2, f3, f4);
            }
        });
        return 0L;
    }
}
