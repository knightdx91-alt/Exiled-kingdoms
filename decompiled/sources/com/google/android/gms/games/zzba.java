package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzba extends com.google.android.gms.internal.games.zzah<Intent> {
    private final /* synthetic */ Room zzdd;
    private final /* synthetic */ int zzde;

    zzba(RealTimeMultiplayerClient realTimeMultiplayerClient, Room room, int i2) {
        this.zzdd = room;
        this.zzde = i2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Intent> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zza(this.zzdd, this.zzde));
    }
}
