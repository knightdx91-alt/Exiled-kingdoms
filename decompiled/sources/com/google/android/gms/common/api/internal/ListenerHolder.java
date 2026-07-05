package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class ListenerHolder<L> {
    private final zaa zaji;
    private volatile L zajj;
    private final ListenerKey<L> zajk;

    @KeepForSdk
    public static final class ListenerKey<L> {
        private final L zajj;
        private final String zajm;

        @KeepForSdk
        ListenerKey(L l2, String str) {
            this.zajj = l2;
            this.zajm = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zajj == listenerKey.zajj && this.zajm.equals(listenerKey.zajm);
        }

        public final int hashCode() {
            return this.zajm.hashCode() + (System.identityHashCode(this.zajj) * 31);
        }
    }

    @KeepForSdk
    public interface Notifier<L> {
        @KeepForSdk
        void notifyListener(L l2);

        @KeepForSdk
        void onNotifyListenerFailed();
    }

    private final class zaa extends com.google.android.gms.internal.base.zal {
        public zaa(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Preconditions.checkArgument(message.what == 1);
            ListenerHolder.this.notifyListenerInternal((Notifier) message.obj);
        }
    }

    @KeepForSdk
    ListenerHolder(Looper looper, L l2, String str) {
        this.zaji = new zaa(looper);
        this.zajj = (L) Preconditions.checkNotNull(l2, "Listener must not be null");
        this.zajk = new ListenerKey<>(l2, Preconditions.checkNotEmpty(str));
    }

    @KeepForSdk
    public final void clear() {
        this.zajj = null;
    }

    @KeepForSdk
    public final ListenerKey<L> getListenerKey() {
        return this.zajk;
    }

    @KeepForSdk
    public final boolean hasListener() {
        return this.zajj != null;
    }

    @KeepForSdk
    public final void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zaji.sendMessage(this.zaji.obtainMessage(1, notifier));
    }

    @KeepForSdk
    final void notifyListenerInternal(Notifier<? super L> notifier) {
        L l2 = this.zajj;
        if (l2 == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(l2);
        } catch (RuntimeException e2) {
            notifier.onNotifyListenerFailed();
            throw e2;
        }
    }
}
