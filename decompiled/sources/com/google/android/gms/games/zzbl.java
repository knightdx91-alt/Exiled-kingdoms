package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbl extends com.google.android.gms.games.internal.zzs<com.google.android.gms.games.multiplayer.realtime.zzh> {
    private final /* synthetic */ ListenerHolder zzbi;
    private final /* synthetic */ RoomConfig zzdo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbl(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2, RoomConfig roomConfig) {
        super(listenerHolder);
        this.zzbi = listenerHolder2;
        this.zzdo = roomConfig;
    }

    @Override // com.google.android.gms.games.internal.zzs
    protected final void zzb(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        ListenerHolder<? extends RoomUpdateListener> listenerHolder = this.zzbi;
        zzeVar.zzc(listenerHolder, listenerHolder, listenerHolder, this.zzdo);
        taskCompletionSource.setResult(null);
    }
}
