package android.support.v4.media;

import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.x;
import android.util.Log;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class i extends MediaBrowserServiceCompat.f<List<MediaBrowserCompat.MediaItem>> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ x.b f454e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    i(String str, x.b bVar) {
        super(str);
        this.f454e = bVar;
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat.f
    final void d() {
        int iA = a();
        MediaBrowserService.Result result = this.f454e.f532a;
        try {
            x.f530a.setInt(result, iA);
        } catch (IllegalAccessException e2) {
            Log.w("MBSCompatApi26", e2);
        }
        result.sendResult(null);
    }
}
