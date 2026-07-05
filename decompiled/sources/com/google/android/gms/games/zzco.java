package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzco extends com.google.android.gms.games.internal.zzs<OnTurnBasedMatchUpdateReceivedListener> {
    private final /* synthetic */ ListenerHolder zzbi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzco(TurnBasedMultiplayerClient turnBasedMultiplayerClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        super(listenerHolder);
        this.zzbi = listenerHolder2;
    }

    @Override // com.google.android.gms.games.internal.zzs
    protected final void zzb(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zzc(this.zzbi);
        taskCompletionSource.setResult(null);
    }
}
