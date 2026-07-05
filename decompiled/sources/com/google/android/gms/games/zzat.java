package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzat extends com.google.android.gms.internal.games.zzah<String> {
    zzat(PlayersClient playersClient) {
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<String> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zza(true));
    }
}
