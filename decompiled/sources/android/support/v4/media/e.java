package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.util.Log;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class e extends MediaBrowserServiceCompat.f<List<MediaBrowserCompat.MediaItem>> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.b f447e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ String f448f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final /* synthetic */ Bundle f449g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat f450h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    e(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, MediaBrowserServiceCompat.b bVar, String str, Bundle bundle) {
        super(obj);
        this.f450h = mediaBrowserServiceCompat;
        this.f447e = bVar;
        this.f448f = str;
        this.f449g = bundle;
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat.f
    final void d() {
        android.support.v4.util.b<IBinder, MediaBrowserServiceCompat.b> bVar = this.f450h.f405c;
        MediaBrowserServiceCompat.b bVar2 = this.f447e;
        MediaBrowserServiceCompat.b bVar3 = bVar.get(bVar2.f407a.f423a.getBinder());
        String str = this.f448f;
        if (bVar3 != bVar2) {
            if (MediaBrowserServiceCompat.f403e) {
                Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=null id=" + str);
                return;
            }
            return;
        }
        try {
            bVar2.f407a.b(str, null, this.f449g);
        } catch (RemoteException unused) {
            Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + str + " package=null");
        }
    }
}
