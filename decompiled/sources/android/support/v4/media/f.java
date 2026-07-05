package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.os.ResultReceiver;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class f extends MediaBrowserServiceCompat.f<MediaBrowserCompat.MediaItem> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ ResultReceiver f451e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    f(Object obj, ResultReceiver resultReceiver) {
        super(obj);
        this.f451e = resultReceiver;
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat.f
    final void d() {
        int iA = a() & 2;
        ResultReceiver resultReceiver = this.f451e;
        if (iA != 0) {
            resultReceiver.b(-1, null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", null);
        resultReceiver.b(0, bundle);
    }
}
