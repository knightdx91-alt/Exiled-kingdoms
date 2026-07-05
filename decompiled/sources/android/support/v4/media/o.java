package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaBrowserServiceCompat.b;
import android.util.Log;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class o implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f473b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f474c;

    o(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar, Bundle bundle) {
        this.f474c = gVar;
        this.f473b = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaBrowserServiceCompat.h hVar = this.f473b;
        IBinder binder = hVar.f423a.getBinder();
        MediaBrowserServiceCompat.g gVar = this.f474c;
        MediaBrowserServiceCompat.this.f405c.remove(binder);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
        MediaBrowserServiceCompat.b bVar = mediaBrowserServiceCompat.new b();
        bVar.f407a = hVar;
        mediaBrowserServiceCompat.f405c.put(binder, bVar);
        try {
            binder.linkToDeath(bVar, 0);
        } catch (RemoteException unused) {
            Log.w("MBServiceCompat", "IBinder is already dead.");
        }
    }
}
