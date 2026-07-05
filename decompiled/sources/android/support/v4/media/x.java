package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Field f530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f531b = 0;

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    static class a extends v {
        @Override // android.service.media.MediaBrowserService
        public final void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result, Bundle bundle) {
            c cVar = (c) this.f486b;
            b bVar = new b();
            bVar.f532a = result;
            cVar.a(str, bVar);
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        MediaBrowserService.Result f532a;
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    public interface c extends w {
        void a(String str, b bVar);
    }

    static {
        try {
            Field declaredField = MediaBrowserService.Result.class.getDeclaredField("mFlags");
            f530a = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            Log.w("MBSCompatApi26", e2);
        }
    }
}
