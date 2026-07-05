package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbn implements Continuation<Boolean, Task<Void>> {
    final /* synthetic */ String zzdg;
    final /* synthetic */ RoomConfig zzdo;
    final /* synthetic */ ListenerHolder zzdp;
    private final /* synthetic */ RealTimeMultiplayerClient zzdq;

    zzbn(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder listenerHolder, String str, RoomConfig roomConfig) {
        this.zzdq = realTimeMultiplayerClient;
        this.zzdp = listenerHolder;
        this.zzdg = str;
        this.zzdo = roomConfig;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<Void> then(Task<Boolean> task) {
        return this.zzdq.doRead(new zzbo(this));
    }
}
