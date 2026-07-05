package android.support.v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Messenger;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserServiceCompat;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class s extends MediaBrowserService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final u f486b;

    s(Context context, u uVar) {
        attachBaseContext(context);
        this.f486b = uVar;
    }

    @Override // android.service.media.MediaBrowserService
    public final MediaBrowserService.BrowserRoot onGetRoot(String str, int i2, Bundle bundle) {
        Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
        MediaBrowserServiceCompat.c cVar = (MediaBrowserServiceCompat.c) this.f486b;
        MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
        if (bundle2 != null && bundle2.getInt("extra_client_version", 0) != 0) {
            bundle2.remove("extra_client_version");
            cVar.f413c = new Messenger(mediaBrowserServiceCompat.f406d);
            Bundle bundle3 = new Bundle();
            bundle3.putInt("extra_service_version", 2);
            bundle3.putBinder("extra_messenger", cVar.f413c.getBinder());
            cVar.f411a.add(bundle3);
        }
        mediaBrowserServiceCompat.a();
        return null;
    }

    @Override // android.service.media.MediaBrowserService
    public final void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
        MediaBrowserServiceCompat.this.b();
    }
}
