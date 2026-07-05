package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbq implements Continuation<String, Task<Boolean>> {
    private final /* synthetic */ ListenerHolder zzdp;
    private final /* synthetic */ RealTimeMultiplayerClient zzdq;

    zzbq(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder listenerHolder) {
        this.zzdq = realTimeMultiplayerClient;
        this.zzdp = listenerHolder;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<Boolean> then(Task<String> task) {
        return this.zzdq.doUnregisterEventListener(this.zzdp.getListenerKey());
    }
}
