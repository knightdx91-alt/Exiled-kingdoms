package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzz extends com.google.android.gms.games.internal.zzt<OnInvitationReceivedListener> {
    zzz(InvitationsClient invitationsClient, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
    }

    @Override // com.google.android.gms.games.internal.zzt
    protected final void zzc(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Boolean> taskCompletionSource) {
        zzeVar.zzaa();
        taskCompletionSource.setResult(Boolean.TRUE);
    }
}
