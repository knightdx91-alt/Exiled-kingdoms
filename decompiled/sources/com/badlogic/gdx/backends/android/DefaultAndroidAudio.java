package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import com.badlogic.gdx.d;
import com.badlogic.gdx.utils.m;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import t.c;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DefaultAndroidAudio implements AndroidAudio {
    private final AudioManager manager;
    private final List<AndroidMusic> musics = new ArrayList();
    private final SoundPool soundPool;

    public DefaultAndroidAudio(Context context, AndroidApplicationConfiguration androidApplicationConfiguration) {
        if (androidApplicationConfiguration.disableAudio) {
            this.soundPool = null;
            this.manager = null;
            return;
        }
        this.soundPool = new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(14).setContentType(4).build()).setMaxStreams(androidApplicationConfiguration.maxSimultaneousSounds).build();
        this.manager = (AudioManager) context.getSystemService("audio");
        if (context instanceof Activity) {
            ((Activity) context).setVolumeControlStream(3);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio, com.badlogic.gdx.utils.i
    public void dispose() {
        if (this.soundPool == null) {
            return;
        }
        synchronized (this.musics) {
            try {
                Iterator it = new ArrayList(this.musics).iterator();
                while (it.hasNext()) {
                    ((AndroidMusic) it.next()).dispose();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.soundPool.release();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio
    public t.a newAudioDevice(int i2, boolean z2) {
        if (this.soundPool != null) {
            return new AndroidAudioDevice(i2, z2);
        }
        throw new m("Android audio is not enabled by the application config.");
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio
    public t.b newAudioRecorder(int i2, boolean z2) {
        if (this.soundPool != null) {
            return new AndroidAudioRecorder(i2, z2);
        }
        throw new m("Android audio is not enabled by the application config.");
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio, com.badlogic.gdx.c
    public c newMusic(com.badlogic.gdx.files.a aVar) {
        if (this.soundPool == null) {
            throw new m("Android audio is not enabled by the application config.");
        }
        AndroidFileHandle androidFileHandle = (AndroidFileHandle) aVar;
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (androidFileHandle.type() != d.a.f1645b) {
            try {
                mediaPlayer.setDataSource(androidFileHandle.file().getPath());
                mediaPlayer.prepare();
                AndroidMusic androidMusic = new AndroidMusic(this, mediaPlayer);
                synchronized (this.musics) {
                    this.musics.add(androidMusic);
                }
                return androidMusic;
            } catch (Exception e2) {
                throw new m(a.a.i(aVar, "Error loading audio file: "), (Throwable) e2);
            }
        }
        try {
            AssetFileDescriptor assetFileDescriptor = androidFileHandle.getAssetFileDescriptor();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            assetFileDescriptor.close();
            mediaPlayer.prepare();
            AndroidMusic androidMusic2 = new AndroidMusic(this, mediaPlayer);
            synchronized (this.musics) {
                this.musics.add(androidMusic2);
            }
            return androidMusic2;
        } catch (Exception e3) {
            throw new m("Error loading audio file: " + aVar + "\nNote: Internal audio files must be placed in the assets directory.", (Throwable) e3);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio, com.badlogic.gdx.c
    public t.d newSound(com.badlogic.gdx.files.a aVar) {
        if (this.soundPool == null) {
            throw new m("Android audio is not enabled by the application config.");
        }
        AndroidFileHandle androidFileHandle = (AndroidFileHandle) aVar;
        if (androidFileHandle.type() != d.a.f1645b) {
            try {
                SoundPool soundPool = this.soundPool;
                return new AndroidSound(soundPool, this.manager, soundPool.load(androidFileHandle.file().getPath(), 1));
            } catch (Exception e2) {
                throw new m(a.a.i(aVar, "Error loading audio file: "), (Throwable) e2);
            }
        }
        try {
            AssetFileDescriptor assetFileDescriptor = androidFileHandle.getAssetFileDescriptor();
            SoundPool soundPool2 = this.soundPool;
            AndroidSound androidSound = new AndroidSound(soundPool2, this.manager, soundPool2.load(assetFileDescriptor, 1));
            assetFileDescriptor.close();
            return androidSound;
        } catch (IOException e3) {
            throw new m("Error loading audio file: " + aVar + "\nNote: Internal audio files must be placed in the assets directory.", (Throwable) e3);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio
    public void notifyMusicDisposed(AndroidMusic androidMusic) {
        synchronized (this.musics) {
            this.musics.remove(this);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio
    public void pause() {
        if (this.soundPool == null) {
            return;
        }
        synchronized (this.musics) {
            try {
                for (AndroidMusic androidMusic : this.musics) {
                    if (androidMusic.isPlaying()) {
                        androidMusic.pause();
                        androidMusic.wasPlaying = true;
                    } else {
                        androidMusic.wasPlaying = false;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.soundPool.autoPause();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidAudio
    public void resume() {
        if (this.soundPool == null) {
            return;
        }
        synchronized (this.musics) {
            for (int i2 = 0; i2 < this.musics.size(); i2++) {
                try {
                    if (this.musics.get(i2).wasPlaying) {
                        this.musics.get(i2).play();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.soundPool.autoResume();
    }

    public c newMusic(FileDescriptor fileDescriptor) {
        if (this.soundPool != null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(fileDescriptor);
                mediaPlayer.prepare();
                AndroidMusic androidMusic = new AndroidMusic(this, mediaPlayer);
                synchronized (this.musics) {
                    this.musics.add(androidMusic);
                }
                return androidMusic;
            } catch (Exception e2) {
                throw new m("Error loading audio from FileDescriptor", (Throwable) e2);
            }
        }
        throw new m("Android audio is not enabled by the application config.");
    }
}
