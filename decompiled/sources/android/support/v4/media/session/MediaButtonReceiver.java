package android.support.v4.media.session;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MediaButtonReceiver extends BroadcastReceiver {

    private static class a extends MediaBrowserCompat.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f487c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Intent f488d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final BroadcastReceiver.PendingResult f489e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private MediaBrowserCompat f490f;

        a(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            this.f487c = context;
            this.f488d = intent;
            this.f489e = pendingResult;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public final void a() {
            try {
                new MediaControllerCompat(this.f487c, this.f490f.c()).a((KeyEvent) this.f488d.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            } catch (RemoteException e2) {
                Log.e("MediaButtonReceiver", "Failed to create a media controller", e2);
            }
            this.f490f.b();
            this.f489e.finish();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public final void b() {
            this.f490f.b();
            this.f489e.finish();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public final void c() {
            this.f490f.b();
            this.f489e.finish();
        }

        final void d(MediaBrowserCompat mediaBrowserCompat) {
            this.f490f = mediaBrowserCompat;
        }
    }

    private static ComponentName a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices.size() == 1) {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            return new ComponentName(serviceInfo.packageName, serviceInfo.name);
        }
        if (listQueryIntentServices.isEmpty()) {
            return null;
        }
        StringBuilder sbU = a.a.u("Expected 1 service that handles ", str, ", found ");
        sbU.append(listQueryIntentServices.size());
        throw new IllegalStateException(sbU.toString());
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Log.d("MediaButtonReceiver", "Ignore unsupported intent: " + intent);
            return;
        }
        ComponentName componentNameA = a(context, "android.intent.action.MEDIA_BUTTON");
        if (componentNameA != null) {
            intent.setComponent(componentNameA);
            context.startForegroundService(intent);
            return;
        }
        ComponentName componentNameA2 = a(context, "android.media.browse.MediaBrowserService");
        if (componentNameA2 == null) {
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        }
        BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        Context applicationContext = context.getApplicationContext();
        a aVar = new a(applicationContext, intent, pendingResultGoAsync);
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(applicationContext, componentNameA2, aVar);
        aVar.d(mediaBrowserCompat);
        mediaBrowserCompat.a();
    }
}
