package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class r implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f482c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ Bundle f483d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ ResultReceiver f484e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f485f;

    r(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
        this.f485f = gVar;
        this.f481b = hVar;
        this.f482c = str;
        this.f483d = bundle;
        this.f484e = resultReceiver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder binder = this.f481b.f423a.getBinder();
        MediaBrowserServiceCompat.g gVar = this.f485f;
        MediaBrowserServiceCompat.b bVar = MediaBrowserServiceCompat.this.f405c.get(binder);
        Bundle bundle = this.f483d;
        String str = this.f482c;
        if (bVar == null) {
            Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + str + ", extras=" + bundle);
            return;
        }
        MediaBrowserServiceCompat.this.getClass();
        h hVar = new h(str, this.f484e);
        hVar.e();
        if (hVar.b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }
}
