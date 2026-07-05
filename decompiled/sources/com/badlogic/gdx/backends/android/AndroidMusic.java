package com.badlogic.gdx.backends.android;

import android.media.MediaPlayer;
import com.badlogic.gdx.Gdx;
import java.io.IOException;
import t.c;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidMusic implements c, MediaPlayer.OnCompletionListener {
    private final AndroidAudio audio;
    protected c.a onCompletionListener;
    private MediaPlayer player;
    private boolean isPrepared = true;
    protected boolean wasPlaying = false;
    private float volume = 1.0f;

    /* JADX INFO: renamed from: com.badlogic.gdx.backends.android.AndroidMusic$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AndroidMusic.this.getClass();
            throw null;
        }
    }

    AndroidMusic(AndroidAudio androidAudio, MediaPlayer mediaPlayer) {
        this.audio = androidAudio;
        this.player = mediaPlayer;
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return;
        }
        try {
            try {
                mediaPlayer.release();
            } catch (Throwable unused) {
                Gdx.app.log("AndroidMusic", "error while disposing AndroidMusic instance, non-fatal");
            }
        } finally {
            this.player = null;
            this.audio.notifyMusicDisposed(this);
        }
    }

    public float getDuration() {
        if (this.player == null) {
            return 0.0f;
        }
        return r0.getDuration() / 1000.0f;
    }

    public float getPosition() {
        if (this.player == null) {
            return 0.0f;
        }
        return r0.getCurrentPosition() / 1000.0f;
    }

    public float getVolume() {
        return this.volume;
    }

    public boolean isLooping() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return false;
        }
        try {
            return mediaPlayer.isLooping();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean isPlaying() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return false;
        }
        try {
            return mediaPlayer.isPlaying();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
    }

    public void pause() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return;
        }
        try {
            if (mediaPlayer.isPlaying()) {
                this.player.pause();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.wasPlaying = false;
    }

    @Override // t.c
    public void play() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return;
        }
        try {
            if (mediaPlayer.isPlaying()) {
                return;
            }
            try {
                if (!this.isPrepared) {
                    this.player.prepare();
                    this.isPrepared = true;
                }
                this.player.start();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    @Override // t.c
    public void setLooping(boolean z2) {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.setLooping(z2);
    }

    public void setOnCompletionListener(c.a aVar) {
    }

    public void setPan(float f2, float f3) {
        float fAbs;
        float fAbs2;
        if (this.player == null) {
            return;
        }
        if (f2 < 0.0f) {
            fAbs = (1.0f - Math.abs(f2)) * f3;
            fAbs2 = f3;
        } else if (f2 > 0.0f) {
            fAbs2 = (1.0f - Math.abs(f2)) * f3;
            fAbs = f3;
        } else {
            fAbs = f3;
            fAbs2 = fAbs;
        }
        this.player.setVolume(fAbs2, fAbs);
        this.volume = f3;
    }

    public void setPosition(float f2) {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return;
        }
        try {
            if (!this.isPrepared) {
                mediaPlayer.prepare();
                this.isPrepared = true;
            }
            this.player.seekTo((int) (f2 * 1000.0f));
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        }
    }

    @Override // t.c
    public void setVolume(float f2) {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.setVolume(f2, f2);
        this.volume = f2;
    }

    @Override // t.c
    public void stop() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer == null) {
            return;
        }
        if (this.isPrepared) {
            mediaPlayer.seekTo(0);
        }
        this.player.stop();
        this.isPrepared = false;
    }
}
