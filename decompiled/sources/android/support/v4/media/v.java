package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.service.media.MediaBrowserService;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi23.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class v extends s {
    @Override // android.service.media.MediaBrowserService
    public final void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
        w wVar = (w) this.f486b;
        t<Parcel> tVar = new t<>();
        tVar.f529a = result;
        wVar.b(str, tVar);
    }
}
