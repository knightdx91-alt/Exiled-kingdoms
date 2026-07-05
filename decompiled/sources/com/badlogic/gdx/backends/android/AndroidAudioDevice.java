package com.badlogic.gdx.backends.android;

import android.media.AudioTrack;
import com.badlogic.gdx.graphics.GL20;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class AndroidAudioDevice implements t.a {
    private short[] buffer = new short[GL20.GL_STENCIL_BUFFER_BIT];
    private final boolean isMono;
    private final int latency;
    private final AudioTrack track;

    AndroidAudioDevice(int i2, boolean z2) {
        this.isMono = z2;
        int minBufferSize = AudioTrack.getMinBufferSize(i2, z2 ? 4 : 12, 2);
        AudioTrack audioTrack = new AudioTrack(3, i2, z2 ? 4 : 12, 2, minBufferSize, 1);
        this.track = audioTrack;
        audioTrack.play();
        this.latency = minBufferSize / (z2 ? 1 : 2);
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        this.track.stop();
        this.track.release();
    }

    public int getLatency() {
        return this.latency;
    }

    public boolean isMono() {
        return this.isMono;
    }

    public void setVolume(float f2) {
        this.track.setStereoVolume(f2, f2);
    }

    public void writeSamples(short[] sArr, int i2, int i3) {
        int iWrite = this.track.write(sArr, i2, i3);
        while (iWrite != i3) {
            iWrite += this.track.write(sArr, i2 + iWrite, i3 - iWrite);
        }
    }

    public void writeSamples(float[] fArr, int i2, int i3) {
        if (this.buffer.length < fArr.length) {
            this.buffer = new short[fArr.length];
        }
        int i4 = i2 + i3;
        int i5 = 0;
        while (i2 < i4) {
            float f2 = fArr[i2];
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            if (f2 < -1.0f) {
                f2 = -1.0f;
            }
            this.buffer[i5] = (short) (f2 * 32767.0f);
            i2++;
            i5++;
        }
        int iWrite = this.track.write(this.buffer, 0, i3);
        while (iWrite != i3) {
            iWrite += this.track.write(this.buffer, iWrite, i3 - iWrite);
        }
    }
}
