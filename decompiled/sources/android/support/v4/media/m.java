package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class m implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ IBinder f467d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f468e;

    m(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar, String str, IBinder iBinder) {
        this.f468e = gVar;
        this.f465b = hVar;
        this.f466c = str;
        this.f467d = iBinder;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder binder = this.f465b.f423a.getBinder();
        MediaBrowserServiceCompat.g gVar = this.f468e;
        MediaBrowserServiceCompat.b bVar = MediaBrowserServiceCompat.this.f405c.get(binder);
        String str = this.f466c;
        if (bVar == null) {
            Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + str);
            return;
        }
        MediaBrowserServiceCompat.this.getClass();
        HashMap<String, List<android.support.v4.util.h<IBinder, Bundle>>> map = bVar.f408b;
        IBinder iBinder = this.f467d;
        boolean z2 = false;
        if (iBinder != null) {
            List<android.support.v4.util.h<IBinder, Bundle>> list = map.get(str);
            if (list != null) {
                Iterator<android.support.v4.util.h<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().f572a) {
                        it.remove();
                        z2 = true;
                    }
                }
                if (list.size() == 0) {
                    map.remove(str);
                }
            }
        } else if (map.remove(str) != null) {
            z2 = true;
        }
        if (z2) {
            return;
        }
        Log.w("MBServiceCompat", "removeSubscription called for " + str + " which is not subscribed");
    }
}
