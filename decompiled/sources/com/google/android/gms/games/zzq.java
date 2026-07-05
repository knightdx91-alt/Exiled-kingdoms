package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzq extends com.google.android.gms.internal.games.zzah<String> {
    zzq(GamesClient gamesClient) {
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<String> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zzam());
    }
}
