package android.support.v4.media;

import android.os.IBinder;
import android.support.v4.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class p implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f476c;

    p(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar) {
        this.f476c = gVar;
        this.f475b = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder binder = this.f475b.f423a.getBinder();
        MediaBrowserServiceCompat.b bVarRemove = MediaBrowserServiceCompat.this.f405c.remove(binder);
        if (bVarRemove != null) {
            binder.unlinkToDeath(bVarRemove, 0);
        }
    }
}
