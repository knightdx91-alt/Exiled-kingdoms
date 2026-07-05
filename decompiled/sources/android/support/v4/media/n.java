package android.support.v4.media;

import android.os.IBinder;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class n implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f469b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f470c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ ResultReceiver f471d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f472e;

    n(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar, String str, ResultReceiver resultReceiver) {
        this.f472e = gVar;
        this.f469b = hVar;
        this.f470c = str;
        this.f471d = resultReceiver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder binder = this.f469b.f423a.getBinder();
        MediaBrowserServiceCompat.g gVar = this.f472e;
        MediaBrowserServiceCompat.b bVar = MediaBrowserServiceCompat.this.f405c.get(binder);
        String str = this.f470c;
        if (bVar == null) {
            Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + str);
        } else {
            MediaBrowserServiceCompat.this.getClass();
            f fVar = new f(str, this.f471d);
            fVar.g(2);
            fVar.f();
            if (!fVar.b()) {
                throw new IllegalStateException(a.a.A("onLoadItem must call detach() or sendResult() before returning for id=", str));
            }
        }
    }
}
