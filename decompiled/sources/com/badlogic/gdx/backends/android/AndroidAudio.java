package com.badlogic.gdx.backends.android;

import com.badlogic.gdx.c;
import com.badlogic.gdx.utils.i;
import t.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface AndroidAudio extends c, i {
    @Override // com.badlogic.gdx.utils.i
    /* synthetic */ void dispose();

    /* synthetic */ t.a newAudioDevice(int i2, boolean z2);

    /* synthetic */ t.b newAudioRecorder(int i2, boolean z2);

    @Override // com.badlogic.gdx.c
    /* synthetic */ t.c newMusic(com.badlogic.gdx.files.a aVar);

    @Override // com.badlogic.gdx.c
    /* synthetic */ d newSound(com.badlogic.gdx.files.a aVar);

    void notifyMusicDisposed(AndroidMusic androidMusic);

    void pause();

    void resume();
}
