package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.support.v4.media.MediaBrowserCompat;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class b<T> extends MediaBrowser.SubscriptionCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final MediaBrowserCompat.i.b f446a;

    public b(MediaBrowserCompat.i.b bVar) {
        this.f446a = bVar;
    }

    @Override // android.media.browse.MediaBrowser.SubscriptionCallback
    public final void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list) {
        MediaBrowserCompat.i.this.getClass();
        MediaBrowserCompat.MediaItem.a(list);
    }

    @Override // android.media.browse.MediaBrowser.SubscriptionCallback
    public final void onError(String str) {
        MediaBrowserCompat.i.this.getClass();
    }
}
