package android.support.v4.media.session;

import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.c;
import java.util.List;

/* JADX INFO: compiled from: MediaControllerCompatApi21.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d<T extends c> extends MediaController.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final T f528a;

    public d(T t2) {
        this.f528a = t2;
    }

    @Override // android.media.session.MediaController.Callback
    public final void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        playbackInfo.getPlaybackType();
        AudioAttributes audioAttributes = playbackInfo.getAudioAttributes();
        if ((audioAttributes.getFlags() & 1) != 1 && (audioAttributes.getFlags() & 4) != 4) {
            audioAttributes.getUsage();
        }
        playbackInfo.getVolumeControl();
        playbackInfo.getMaxVolume();
        playbackInfo.getCurrentVolume();
        ((MediaControllerCompat.a.C0006a) this.f528a).a();
    }

    @Override // android.media.session.MediaController.Callback
    public final void onExtrasChanged(Bundle bundle) {
        ((MediaControllerCompat.a.C0006a) this.f528a).b();
    }

    @Override // android.media.session.MediaController.Callback
    public final void onMetadataChanged(MediaMetadata mediaMetadata) {
        ((MediaControllerCompat.a.C0006a) this.f528a).c(mediaMetadata);
    }

    @Override // android.media.session.MediaController.Callback
    public final void onPlaybackStateChanged(PlaybackState playbackState) {
        ((MediaControllerCompat.a.C0006a) this.f528a).d(playbackState);
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueChanged(List<MediaSession.QueueItem> list) {
        ((MediaControllerCompat.a.C0006a) this.f528a).e(list);
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueTitleChanged(CharSequence charSequence) {
        ((MediaControllerCompat.a.C0006a) this.f528a).f();
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionDestroyed() {
        ((MediaControllerCompat.a.C0006a) this.f528a).g();
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionEvent(String str, Bundle bundle) {
        ((MediaControllerCompat.a.C0006a) this.f528a).h();
    }
}
