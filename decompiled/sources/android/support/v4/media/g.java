package android.support.v4.media;

import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.os.ResultReceiver;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class g extends MediaBrowserServiceCompat.f<List<MediaBrowserCompat.MediaItem>> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ ResultReceiver f452e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    g(Object obj, ResultReceiver resultReceiver) {
        super(obj);
        this.f452e = resultReceiver;
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat.f
    final void d() {
        this.f452e.b(-1, null);
    }
}
