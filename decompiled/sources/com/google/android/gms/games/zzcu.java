package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcu extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ String zzew;

    zzcu(TurnBasedMultiplayerClient turnBasedMultiplayerClient, String str) {
        this.zzew = str;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zzb(this.zzew);
        taskCompletionSource.setResult(null);
    }
}
