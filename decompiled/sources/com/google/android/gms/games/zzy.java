package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzy extends com.google.android.gms.games.internal.zzs<OnInvitationReceivedListener> {
    private final /* synthetic */ ListenerHolder zzbi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzy(InvitationsClient invitationsClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        super(listenerHolder);
        this.zzbi = listenerHolder2;
    }

    @Override // com.google.android.gms.games.internal.zzs
    protected final void zzb(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zza(this.zzbi);
        taskCompletionSource.setResult(null);
    }
}
