package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.v4.media.c;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d<T extends c> extends b<T> {
    @Override // android.media.browse.MediaBrowser.SubscriptionCallback
    public final void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
        this.f446a.b(list);
    }

    @Override // android.media.browse.MediaBrowser.SubscriptionCallback
    public final void onError(String str, Bundle bundle) {
        this.f446a.a();
    }
}
