package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.os.ResultReceiver;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class h extends MediaBrowserServiceCompat.f<Bundle> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ ResultReceiver f453e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    h(Object obj, ResultReceiver resultReceiver) {
        super(obj);
        this.f453e = resultReceiver;
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat.f
    final void c() {
        this.f453e.b(-1, null);
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat.f
    final void d() {
        this.f453e.b(0, null);
    }
}
