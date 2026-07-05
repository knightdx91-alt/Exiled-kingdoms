package android.support.v4.media;

import android.support.v4.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class k implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f458b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f459c;

    k(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar) {
        this.f459c = gVar;
        this.f458b = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaBrowserServiceCompat.b bVarRemove = MediaBrowserServiceCompat.this.f405c.remove(this.f458b.f423a.getBinder());
        if (bVarRemove != null) {
            bVarRemove.f407a.f423a.getBinder().unlinkToDeath(bVarRemove, 0);
        }
    }
}
