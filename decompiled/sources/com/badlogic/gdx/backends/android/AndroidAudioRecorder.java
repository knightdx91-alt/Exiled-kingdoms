package com.badlogic.gdx.backends.android;

import android.media.AudioRecord;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidAudioRecorder implements t.b {
    private AudioRecord recorder;

    public AndroidAudioRecorder(int i2, boolean z2) {
        int i3 = z2 ? 16 : 12;
        AudioRecord audioRecord = new AudioRecord(1, i2, i3, 2, AudioRecord.getMinBufferSize(i2, i3, 2));
        this.recorder = audioRecord;
        if (audioRecord.getState() != 1) {
            throw new m("Unable to initialize AudioRecorder.\nDo you have the RECORD_AUDIO permission?");
        }
        this.recorder.startRecording();
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        this.recorder.stop();
        this.recorder.release();
    }

    public void read(short[] sArr, int i2, int i3) {
        int i4 = 0;
        while (i4 != i3) {
            i4 += this.recorder.read(sArr, i2 + i4, i3 - i4);
        }
    }
}
