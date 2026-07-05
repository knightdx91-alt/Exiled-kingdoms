package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.x;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final boolean f403e = Log.isLoggable("MBServiceCompat", 3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f404b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final android.support.v4.util.b<IBinder, b> f405c = new android.support.v4.util.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final i f406d = new i(this);

    public static final class a {
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        h f407a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        HashMap<String, List<android.support.v4.util.h<IBinder, Bundle>>> f408b = new HashMap<>();

        final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                b bVar = b.this;
                MediaBrowserServiceCompat.this.f405c.remove(bVar.f407a.f423a.getBinder());
            }
        }

        b() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            MediaBrowserServiceCompat.this.f406d.post(new a());
        }
    }

    class c implements u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ArrayList f411a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Object f412b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Messenger f413c;

        c() {
        }
    }

    class d extends c implements w {

        final class a extends f<MediaBrowserCompat.MediaItem> {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ t f416e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(String str, t tVar) {
                super(str);
                this.f416e = tVar;
            }

            @Override // android.support.v4.media.MediaBrowserServiceCompat.f
            final void d() {
                t tVar = this.f416e;
                tVar.getClass();
                tVar.f529a.sendResult(null);
            }
        }

        d() {
            super();
        }

        @Override // android.support.v4.media.w
        public final void b(String str, t<Parcel> tVar) {
            a aVar = new a(str, tVar);
            MediaBrowserServiceCompat.this.getClass();
            aVar.g(2);
            aVar.f();
        }
    }

    class e extends d implements x.c {
        e() {
            super();
        }

        @Override // android.support.v4.media.x.c
        public final void a(String str, x.b bVar) {
            android.support.v4.media.i iVar = new android.support.v4.media.i(str, bVar);
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.getClass();
            iVar.g(1);
            mediaBrowserServiceCompat.b();
        }
    }

    public static class f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f419b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f420c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f421d;

        f(Object obj) {
            this.f418a = obj;
        }

        final int a() {
            return this.f421d;
        }

        final boolean b() {
            return this.f419b || this.f420c;
        }

        void c() {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f418a);
        }

        void d() {
            throw null;
        }

        public final void e() {
            if (this.f419b || this.f420c) {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f418a);
            }
            this.f420c = true;
            c();
        }

        public final void f() {
            if (this.f419b || this.f420c) {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f418a);
            }
            this.f419b = true;
            d();
        }

        final void g(int i2) {
            this.f421d = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class g {
        g() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Messenger f423a;

        h(Messenger messenger) {
            this.f423a = messenger;
        }

        private void c(int i2, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.f423a.send(messageObtain);
        }

        public final void a() throws RemoteException {
            c(2, null);
        }

        public final void b(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putBundle("data_options", bundle);
            if (list != null) {
                bundle2.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            c(3, bundle2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class i extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final g f424a;

        i(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.f424a = mediaBrowserServiceCompat.new g();
        }

        public final void a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            int i2 = message.what;
            g gVar = this.f424a;
            switch (i2) {
                case 1:
                    String string = data.getString("data_package_name");
                    int i3 = data.getInt("data_calling_uid");
                    Bundle bundle = data.getBundle("data_root_hints");
                    h hVar = new h(message.replyTo);
                    MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                    if (string != null) {
                        for (String str : mediaBrowserServiceCompat.getPackageManager().getPackagesForUid(i3)) {
                            if (str.equals(string)) {
                                mediaBrowserServiceCompat.f406d.a(new j(gVar, hVar, string, bundle, i3));
                                return;
                            }
                        }
                    } else {
                        mediaBrowserServiceCompat.getClass();
                    }
                    throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + string);
                case 2:
                    MediaBrowserServiceCompat.this.f406d.a(new k(gVar, new h(message.replyTo)));
                    return;
                case 3:
                    MediaBrowserServiceCompat.this.f406d.a(new l(gVar, new h(message.replyTo), data.getString("data_media_item_id"), data.getBinder("data_callback_token"), data.getBundle("data_options")));
                    return;
                case 4:
                    MediaBrowserServiceCompat.this.f406d.a(new m(gVar, new h(message.replyTo), data.getString("data_media_item_id"), data.getBinder("data_callback_token")));
                    return;
                case 5:
                    String string2 = data.getString("data_media_item_id");
                    ResultReceiver resultReceiver = (ResultReceiver) data.getParcelable("data_result_receiver");
                    h hVar2 = new h(message.replyTo);
                    gVar.getClass();
                    if (TextUtils.isEmpty(string2) || resultReceiver == null) {
                        return;
                    }
                    MediaBrowserServiceCompat.this.f406d.a(new n(gVar, hVar2, string2, resultReceiver));
                    return;
                case 6:
                    MediaBrowserServiceCompat.this.f406d.a(new o(gVar, new h(message.replyTo), data.getBundle("data_root_hints")));
                    return;
                case 7:
                    MediaBrowserServiceCompat.this.f406d.a(new p(gVar, new h(message.replyTo)));
                    return;
                case 8:
                    String string3 = data.getString("data_search_query");
                    Bundle bundle2 = data.getBundle("data_search_extras");
                    ResultReceiver resultReceiver2 = (ResultReceiver) data.getParcelable("data_result_receiver");
                    h hVar3 = new h(message.replyTo);
                    gVar.getClass();
                    if (TextUtils.isEmpty(string3) || resultReceiver2 == null) {
                        return;
                    }
                    MediaBrowserServiceCompat.this.f406d.a(new q(gVar, hVar3, string3, bundle2, resultReceiver2));
                    return;
                case 9:
                    String string4 = data.getString("data_custom_action");
                    Bundle bundle3 = data.getBundle("data_custom_action_extras");
                    ResultReceiver resultReceiver3 = (ResultReceiver) data.getParcelable("data_result_receiver");
                    h hVar4 = new h(message.replyTo);
                    gVar.getClass();
                    if (TextUtils.isEmpty(string4) || resultReceiver3 == null) {
                        return;
                    }
                    MediaBrowserServiceCompat.this.f406d.a(new r(gVar, hVar4, string4, bundle3, resultReceiver3));
                    return;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    return;
            }
        }

        @Override // android.os.Handler
        public final boolean sendMessageAtTime(Message message, long j2) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            return super.sendMessageAtTime(message, j2);
        }
    }

    public abstract a a();

    public abstract void b();

    @Override // android.app.Service
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return ((MediaBrowserService) this.f404b.f412b).onBind(intent);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        e eVar = new e();
        this.f404b = eVar;
        int i2 = x.f531b;
        x.a aVar = new x.a(this, eVar);
        eVar.f412b = aVar;
        aVar.onCreate();
    }
}
