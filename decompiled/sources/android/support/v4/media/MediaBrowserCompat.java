package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.b;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final boolean f380b = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e f381a;

    private static class CustomActionResultReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        protected final void a(int i2, Bundle bundle) {
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        protected final void a(int i2, Bundle bundle) {
            if (bundle != null) {
                bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (i2 != 0 || bundle == null || !bundle.containsKey("media_item")) {
                throw null;
            }
            Parcelable parcelable = bundle.getParcelable("media_item");
            if (parcelable != null && !(parcelable instanceof MediaItem)) {
                throw null;
            }
            throw null;
        }
    }

    private static class SearchResultReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        protected final void a(int i2, Bundle bundle) {
            Parcelable[] parcelableArray;
            if (bundle != null) {
                bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (i2 != 0 || bundle == null || !bundle.containsKey("search_results") || (parcelableArray = bundle.getParcelableArray("search_results")) == null) {
                throw null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaItem) parcelable);
            }
            throw null;
        }
    }

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<f> f384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WeakReference<Messenger> f385b;

        a(c cVar) {
            this.f384a = new WeakReference<>(cVar);
        }

        final void a(Messenger messenger) {
            this.f385b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.f385b;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            WeakReference<f> weakReference2 = this.f384a;
            if (weakReference2.get() == null) {
                return;
            }
            Bundle data = message.getData();
            data.setClassLoader(MediaSessionCompat.class.getClassLoader());
            f fVar = weakReference2.get();
            Messenger messenger = this.f385b.get();
            try {
                int i2 = message.what;
                if (i2 == 1) {
                    data.getString("data_media_item_id");
                    data.getBundle("data_root_hints");
                    fVar.b();
                } else if (i2 == 2) {
                    fVar.a();
                } else if (i2 != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    String string = data.getString("data_media_item_id");
                    data.getParcelableArrayList("data_media_item_list");
                    fVar.c(messenger, string, data.getBundle("data_options"));
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    fVar.a();
                }
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f386a = new android.support.v4.media.a(new a());

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c f387b;

        /* JADX INFO: Access modifiers changed from: private */
        class a {
            a() {
            }
        }

        public void a() {
            throw null;
        }

        public void b() {
            throw null;
        }

        public void c() {
            throw null;
        }
    }

    static class c implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f389a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final MediaBrowser f390b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected final Bundle f391c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected final a f392d = new a(this);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final android.support.v4.util.b<String, h> f393e = new android.support.v4.util.b<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        protected g f394f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        protected Messenger f395g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private MediaSessionCompat.Token f396h;

        c(Context context, ComponentName componentName, b bVar) {
            this.f389a = context;
            Bundle bundle = new Bundle();
            bundle.putInt("extra_client_version", 1);
            Bundle bundle2 = new Bundle(bundle);
            this.f391c = bundle2;
            bVar.f387b = this;
            this.f390b = new MediaBrowser(context, componentName, (MediaBrowser.ConnectionCallback) bVar.f386a, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.f
        public final void a() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.f
        public final void b() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.f
        public final void c(Messenger messenger, String str, Bundle bundle) {
            if (this.f395g != messenger) {
                return;
            }
            h hVar = this.f393e.get(str);
            if (hVar != null) {
                hVar.a(this.f389a, bundle);
            } else if (MediaBrowserCompat.f380b) {
                Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
            }
        }

        public final MediaSessionCompat.Token d() {
            if (this.f396h == null) {
                this.f396h = MediaSessionCompat.Token.a(this.f390b.getSessionToken(), null);
            }
            return this.f396h;
        }

        public final void e() {
            MediaBrowser mediaBrowser = this.f390b;
            Bundle extras = mediaBrowser.getExtras();
            if (extras == null) {
                return;
            }
            extras.getInt("extra_service_version", 0);
            IBinder binder = extras.getBinder("extra_messenger");
            if (binder != null) {
                this.f394f = new g(binder, this.f391c);
                a aVar = this.f392d;
                Messenger messenger = new Messenger(aVar);
                this.f395g = messenger;
                aVar.a(messenger);
                try {
                    this.f394f.a(this.f395g);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }
            android.support.v4.media.session.b bVarI = b.a.i(extras.getBinder("extra_session_binder"));
            if (bVarI != null) {
                this.f396h = MediaSessionCompat.Token.a(mediaBrowser.getSessionToken(), bVarI);
            }
        }

        public final void f() {
            this.f394f = null;
            this.f395g = null;
            this.f396h = null;
            this.f392d.a(null);
        }
    }

    static class d extends c {
    }

    static class e extends d {
    }

    interface f {
        void a();

        void b();

        void c(Messenger messenger, String str, Bundle bundle);
    }

    private static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Messenger f397a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Bundle f398b;

        public g(IBinder iBinder, Bundle bundle) {
            this.f397a = new Messenger(iBinder);
            this.f398b = bundle;
        }

        private void b(int i2, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f397a.send(messageObtain);
        }

        final void a(Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putBundle("data_root_hints", this.f398b);
            b(6, bundle, messenger);
        }

        final void c(Messenger messenger) throws RemoteException {
            b(7, null, messenger);
        }
    }

    private static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ArrayList f399a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ArrayList f400b = new ArrayList();

        public final void a(Context context, Bundle bundle) {
            if (bundle != null) {
                bundle.setClassLoader(context.getClassLoader());
            }
            int i2 = 0;
            while (true) {
                ArrayList arrayList = this.f400b;
                if (i2 >= arrayList.size()) {
                    return;
                }
                if (com.badlogic.gdx.utils.l.e((Bundle) arrayList.get(i2), bundle)) {
                    return;
                }
                i2++;
            }
        }
    }

    public static abstract class i {

        /* JADX INFO: Access modifiers changed from: private */
        class a {
            a() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class b extends a implements android.support.v4.media.c {
            b() {
                super();
            }

            @Override // android.support.v4.media.c
            public final void a() {
                i.this.getClass();
            }

            @Override // android.support.v4.media.c
            public final void b(List list) {
                MediaItem.a(list);
                i.this.getClass();
            }
        }

        public i() {
            new Binder();
            new android.support.v4.media.d(new b());
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, b bVar) {
        this.f381a = new e(context, componentName, bVar);
    }

    public final void a() {
        this.f381a.f390b.connect();
    }

    public final void b() {
        Messenger messenger;
        e eVar = this.f381a;
        g gVar = eVar.f394f;
        if (gVar != null && (messenger = eVar.f395g) != null) {
            try {
                gVar.c(messenger);
            } catch (RemoteException unused) {
                Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
            }
        }
        eVar.f390b.disconnect();
    }

    public final MediaSessionCompat.Token c() {
        return this.f381a.d();
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f382a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final MediaDescriptionCompat f383b;

        static class a implements Parcelable.Creator<MediaItem> {
            @Override // android.os.Parcelable.Creator
            public final MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final MediaItem[] newArray(int i2) {
                return new MediaItem[i2];
            }
        }

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i2) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.b())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.f382a = i2;
            this.f383b = mediaDescriptionCompat;
        }

        public static void a(List list) {
            MediaItem mediaItem;
            if (list != null) {
                ArrayList arrayList = new ArrayList(list.size());
                for (Object obj : list) {
                    if (obj != null) {
                        MediaBrowser.MediaItem mediaItem2 = (MediaBrowser.MediaItem) obj;
                        mediaItem = new MediaItem(MediaDescriptionCompat.a(mediaItem2.getDescription()), mediaItem2.getFlags());
                    } else {
                        mediaItem = null;
                    }
                    arrayList.add(mediaItem);
                }
            }
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "MediaItem{mFlags=" + this.f382a + ", mDescription=" + this.f383b + '}';
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f382a);
            this.f383b.writeToParcel(parcel, i2);
        }

        MediaItem(Parcel parcel) {
            this.f382a = parcel.readInt();
            this.f383b = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
