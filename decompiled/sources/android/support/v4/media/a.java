package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.support.v4.media.MediaBrowserCompat;

/* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class a<T> extends MediaBrowser.ConnectionCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final MediaBrowserCompat.b.a f445a;

    public a(MediaBrowserCompat.b.a aVar) {
        this.f445a = aVar;
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnected() {
        MediaBrowserCompat.b bVar = MediaBrowserCompat.b.this;
        MediaBrowserCompat.c cVar = bVar.f387b;
        if (cVar != null) {
            cVar.e();
        }
        bVar.a();
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionFailed() {
        MediaBrowserCompat.b bVar = MediaBrowserCompat.b.this;
        MediaBrowserCompat.c cVar = bVar.f387b;
        bVar.b();
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionSuspended() {
        MediaBrowserCompat.b bVar = MediaBrowserCompat.b.this;
        MediaBrowserCompat.c cVar = bVar.f387b;
        if (cVar != null) {
            cVar.f();
        }
        bVar.c();
    }
}
