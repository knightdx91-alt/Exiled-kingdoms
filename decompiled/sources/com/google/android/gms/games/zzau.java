package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzau extends com.google.android.gms.internal.games.zzah<Player> {
    zzau(PlayersClient playersClient) {
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Player> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zzr());
    }
}
