package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class j implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.h f455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f456c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ MediaBrowserServiceCompat.g f457d;

    j(MediaBrowserServiceCompat.g gVar, MediaBrowserServiceCompat.h hVar, String str, Bundle bundle, int i2) {
        this.f457d = gVar;
        this.f455b = hVar;
        this.f456c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaBrowserServiceCompat.h hVar = this.f455b;
        IBinder binder = hVar.f423a.getBinder();
        MediaBrowserServiceCompat.g gVar = this.f457d;
        MediaBrowserServiceCompat.this.f405c.remove(binder);
        new HashMap();
        String str = this.f456c;
        MediaBrowserServiceCompat.this.a();
        Log.i("MBServiceCompat", "No root for client " + str + " from service " + j.class.getName());
        try {
            hVar.a();
        } catch (RemoteException unused) {
            Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=".concat(str));
        }
    }
}
