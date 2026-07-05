package android.support.v4.media.session;

import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.a;
import android.support.v4.media.session.b;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class MediaControllerCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f491a;

    static class MediaControllerImplApi21 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final MediaController f492a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private android.support.v4.media.session.b f494c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ArrayList f493b = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private HashMap<a, a> f495d = new HashMap<>();

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private WeakReference<MediaControllerImplApi21> f496a;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21, Handler handler) {
                super(handler);
                this.f496a = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            protected final void onReceiveResult(int i2, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f496a.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                mediaControllerImplApi21.f494c = b.a.i(bundle.getBinder("android.support.v4.media.session.EXTRA_BINDER"));
                MediaControllerImplApi21.b(mediaControllerImplApi21);
            }
        }

        private static class a extends a.b {
            @Override // android.support.v4.media.session.a
            public final void b() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public final void c() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public final void d() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public final void e() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public final void f() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public final void g() {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            MediaController mediaController = new MediaController(context, (MediaSession.Token) token.c());
            this.f492a = mediaController;
            android.support.v4.media.session.b bVarB = token.b();
            this.f494c = bVarB;
            if (bVarB == null) {
                mediaController.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this, new Handler()));
            }
        }

        static void b(MediaControllerImplApi21 mediaControllerImplApi21) {
            if (mediaControllerImplApi21.f494c == null) {
                return;
            }
            synchronized (mediaControllerImplApi21.f493b) {
                for (a aVar : mediaControllerImplApi21.f493b) {
                    a aVar2 = new a(aVar);
                    mediaControllerImplApi21.f495d.put(aVar, aVar2);
                    aVar.f497a = true;
                    try {
                        mediaControllerImplApi21.f494c.a(aVar2);
                    } catch (RemoteException e2) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                        mediaControllerImplApi21.f493b.clear();
                    }
                }
                mediaControllerImplApi21.f493b.clear();
            }
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f497a;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        static class C0006a implements android.support.v4.media.session.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f498a;

            C0006a(a aVar) {
                this.f498a = new WeakReference<>(aVar);
            }

            public final void a() {
                this.f498a.get();
            }

            public final void b() {
                this.f498a.get();
            }

            public final void c(MediaMetadata mediaMetadata) {
                if (this.f498a.get() != null) {
                    MediaMetadataCompat.a(mediaMetadata);
                }
            }

            public final void d(PlaybackState playbackState) {
                ArrayList arrayList;
                a aVar = this.f498a.get();
                if (aVar == null || aVar.f497a || playbackState == null) {
                    return;
                }
                List<PlaybackState.CustomAction> customActions = playbackState.getCustomActions();
                if (customActions != null) {
                    arrayList = new ArrayList(customActions.size());
                    Iterator<PlaybackState.CustomAction> it = customActions.iterator();
                    while (it.hasNext()) {
                        arrayList.add(PlaybackStateCompat.CustomAction.a(it.next()));
                    }
                } else {
                    arrayList = null;
                }
                ArrayList arrayList2 = arrayList;
                new PlaybackStateCompat(playbackState.getState(), playbackState.getPosition(), playbackState.getBufferedPosition(), playbackState.getPlaybackSpeed(), playbackState.getActions(), playbackState.getErrorMessage(), playbackState.getLastPositionUpdateTime(), arrayList2, playbackState.getActiveQueueItemId(), playbackState.getExtras());
            }

            public final void e(List<?> list) {
                if (this.f498a.get() != null) {
                    MediaSessionCompat.QueueItem.a(list);
                }
            }

            public final void f() {
                this.f498a.get();
            }

            public final void g() {
                this.f498a.get();
            }

            public final void h() {
                this.f498a.get();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static class b extends a.AbstractBinderC0007a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f499a;

            b(a aVar) {
                attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
                this.f499a = new WeakReference<>(aVar);
            }

            public final void i() {
                this.f499a.get();
            }

            public final void j() {
                this.f499a.get();
            }

            public final void k() {
                this.f499a.get();
            }

            public final void l() {
                this.f499a.get();
            }

            public final void m() {
                this.f499a.get();
            }

            public final void n() {
                this.f499a.get();
            }
        }

        public a() {
            new d(new C0006a(this));
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
        }
    }

    static class b extends MediaControllerImplApi21 {
    }

    static class c extends b {
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        new HashSet();
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f491a = new c(context, token);
    }

    public final void a(KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        this.f491a.f492a.dispatchMediaButtonEvent(keyEvent);
    }
}
