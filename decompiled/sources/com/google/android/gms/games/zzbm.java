package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbm extends com.google.android.gms.games.internal.zzt<com.google.android.gms.games.multiplayer.realtime.zzh> {
    zzbm(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
    }

    @Override // com.google.android.gms.games.internal.zzt
    protected final void zzc(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Boolean> taskCompletionSource) {
        taskCompletionSource.setResult(Boolean.TRUE);
    }
}
