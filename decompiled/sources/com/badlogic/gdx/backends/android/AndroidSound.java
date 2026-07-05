package com.badlogic.gdx.backends.android;

import android.media.AudioManager;
import android.media.SoundPool;
import com.badlogic.gdx.utils.o;
import t.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class AndroidSound implements d {
    final AudioManager manager;
    final int soundId;
    final SoundPool soundPool;
    final o streamIds = new o(8);

    AndroidSound(SoundPool soundPool, AudioManager audioManager, int i2) {
        this.soundPool = soundPool;
        this.manager = audioManager;
        this.soundId = i2;
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        this.soundPool.unload(this.soundId);
    }

    @Override // t.d
    public long loop() {
        return loop(1.0f);
    }

    @Override // t.d
    public void pause() {
        this.soundPool.autoPause();
    }

    @Override // t.d
    public long play() {
        return play(1.0f);
    }

    @Override // t.d
    public void resume() {
        this.soundPool.autoResume();
    }

    public void setLooping(long j2, boolean z2) {
        int i2 = (int) j2;
        this.soundPool.pause(i2);
        this.soundPool.setLoop(i2, z2 ? -1 : 0);
        this.soundPool.resume(i2);
    }

    public void setPan(long j2, float f2, float f3) {
        float fAbs;
        if (f2 < 0.0f) {
            fAbs = (1.0f - Math.abs(f2)) * f3;
        } else if (f2 > 0.0f) {
            fAbs = f3;
            f3 = (1.0f - Math.abs(f2)) * f3;
        } else {
            fAbs = f3;
        }
        this.soundPool.setVolume((int) j2, f3, fAbs);
    }

    public void setPitch(long j2, float f2) {
        this.soundPool.setRate((int) j2, f2);
    }

    public void setVolume(long j2, float f2) {
        this.soundPool.setVolume((int) j2, f2, f2);
    }

    @Override // t.d
    public void stop() {
        int i2 = this.streamIds.f1850b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.soundPool.stop(this.streamIds.d(i3));
        }
    }

    @Override // t.d
    public long loop(float f2) {
        o oVar = this.streamIds;
        if (oVar.f1850b == 8) {
            oVar.f();
        }
        int iPlay = this.soundPool.play(this.soundId, f2, f2, 1, -1, 1.0f);
        if (iPlay == 0) {
            return -1L;
        }
        this.streamIds.e(0, iPlay);
        return iPlay;
    }

    public void pause(long j2) {
        this.soundPool.pause((int) j2);
    }

    @Override // t.d
    public long play(float f2) {
        o oVar = this.streamIds;
        if (oVar.f1850b == 8) {
            oVar.f();
        }
        int iPlay = this.soundPool.play(this.soundId, f2, f2, 1, 0, 1.0f);
        if (iPlay == 0) {
            return -1L;
        }
        this.streamIds.e(0, iPlay);
        return iPlay;
    }

    public void resume(long j2) {
        this.soundPool.resume((int) j2);
    }

    public void stop(long j2) {
        this.soundPool.stop((int) j2);
    }

    @Override // t.d
    public long loop(float f2, float f3, float f4) {
        float fAbs;
        float fAbs2;
        o oVar = this.streamIds;
        if (oVar.f1850b == 8) {
            oVar.f();
        }
        if (f4 < 0.0f) {
            fAbs = f2;
            fAbs2 = (1.0f - Math.abs(f4)) * f2;
        } else if (f4 > 0.0f) {
            fAbs2 = f2;
            fAbs = (1.0f - Math.abs(f4)) * f2;
        } else {
            fAbs = f2;
            fAbs2 = fAbs;
        }
        int iPlay = this.soundPool.play(this.soundId, fAbs, fAbs2, 1, -1, f3);
        if (iPlay == 0) {
            return -1L;
        }
        this.streamIds.e(0, iPlay);
        return iPlay;
    }

    @Override // t.d
    public long play(float f2, float f3, float f4) {
        float fAbs;
        float fAbs2;
        o oVar = this.streamIds;
        if (oVar.f1850b == 8) {
            oVar.f();
        }
        if (f4 < 0.0f) {
            fAbs = f2;
            fAbs2 = (1.0f - Math.abs(f4)) * f2;
        } else if (f4 > 0.0f) {
            fAbs2 = f2;
            fAbs = (1.0f - Math.abs(f4)) * f2;
        } else {
            fAbs = f2;
            fAbs2 = fAbs;
        }
        int iPlay = this.soundPool.play(this.soundId, fAbs, fAbs2, 1, 0, f3);
        if (iPlay == 0) {
            return -1L;
        }
        this.streamIds.e(0, iPlay);
        return iPlay;
    }
}
