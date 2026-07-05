package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class l implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f460b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f461c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ IBinder f462d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ Bundle f463e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f464f;

    l(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar, String str, IBinder iBinder, Bundle bundle) {
        this.f464f = gVar;
        this.f460b = hVar;
        this.f461c = str;
        this.f462d = iBinder;
        this.f463e = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder binder = this.f460b.f423a.getBinder();
        MediaBrowserServiceCompat.g gVar = this.f464f;
        MediaBrowserServiceCompat.b bVar = MediaBrowserServiceCompat.this.f405c.get(binder);
        if (bVar == null) {
            Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.f461c);
            return;
        }
        MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
        mediaBrowserServiceCompat.getClass();
        HashMap<String, List<android.support.v4.util.h<IBinder, Bundle>>> map = bVar.f408b;
        String str = this.f461c;
        List<android.support.v4.util.h<IBinder, Bundle>> arrayList = map.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        Iterator<android.support.v4.util.h<IBinder, Bundle>> it = arrayList.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            IBinder iBinder = this.f462d;
            Bundle bundle = this.f463e;
            if (!zHasNext) {
                arrayList.add(new android.support.v4.util.h<>(iBinder, bundle));
                map.put(str, arrayList);
                e eVar = new e(mediaBrowserServiceCompat, str, bVar, str, bundle);
                if (bundle == null) {
                    mediaBrowserServiceCompat.b();
                } else {
                    eVar.g(1);
                    mediaBrowserServiceCompat.b();
                }
                if (!eVar.b()) {
                    throw new IllegalStateException(a.a.A("onLoadChildren must call detach() or sendResult() before returning for package=null id=", str));
                }
                return;
            }
            android.support.v4.util.h<IBinder, Bundle> next = it.next();
            if (iBinder == next.f572a && com.badlogic.gdx.utils.l.e(bundle, next.f573b)) {
                return;
            }
        }
    }
}
