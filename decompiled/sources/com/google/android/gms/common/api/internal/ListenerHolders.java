package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class ListenerHolders {
    private final Set<ListenerHolder<?>> zajn = Collections.newSetFromMap(new WeakHashMap());

    @KeepForSdk
    public static <L> ListenerHolder<L> createListenerHolder(L l2, Looper looper, String str) {
        Preconditions.checkNotNull(l2, "Listener must not be null");
        Preconditions.checkNotNull(looper, "Looper must not be null");
        Preconditions.checkNotNull(str, "Listener type must not be null");
        return new ListenerHolder<>(looper, l2, str);
    }

    @KeepForSdk
    public static <L> ListenerHolder.ListenerKey<L> createListenerKey(L l2, String str) {
        Preconditions.checkNotNull(l2, "Listener must not be null");
        Preconditions.checkNotNull(str, "Listener type must not be null");
        Preconditions.checkNotEmpty(str, "Listener type must not be empty");
        return new ListenerHolder.ListenerKey<>(l2, str);
    }

    public final void release() {
        Iterator<ListenerHolder<?>> it = this.zajn.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.zajn.clear();
    }

    public final <L> ListenerHolder<L> zaa(L l2, Looper looper, String str) {
        ListenerHolder<L> listenerHolderCreateListenerHolder = createListenerHolder(l2, looper, str);
        this.zajn.add(listenerHolderCreateListenerHolder);
        return listenerHolderCreateListenerHolder;
    }
}
