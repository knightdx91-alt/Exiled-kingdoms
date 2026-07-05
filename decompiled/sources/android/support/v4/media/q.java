package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class q implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f478c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ ResultReceiver f479d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f480e;

    q(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
        this.f480e = gVar;
        this.f477b = hVar;
        this.f478c = str;
        this.f479d = resultReceiver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder binder = this.f477b.f423a.getBinder();
        MediaBrowserServiceCompat.g gVar = this.f480e;
        MediaBrowserServiceCompat.b bVar = MediaBrowserServiceCompat.this.f405c.get(binder);
        String str = this.f478c;
        if (bVar == null) {
            Log.w("MBServiceCompat", "search for callback that isn't registered query=" + str);
        } else {
            MediaBrowserServiceCompat.this.getClass();
            g gVar2 = new g(str, this.f479d);
            gVar2.g(4);
            gVar2.f();
            if (!gVar2.b()) {
                throw new IllegalStateException(a.a.A("onSearch must call detach() or sendResult() before returning for query=", str));
            }
        }
    }
}
