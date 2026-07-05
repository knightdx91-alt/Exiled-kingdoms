package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbe extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ String zzdj;

    zzbe(RealTimeMultiplayerClient realTimeMultiplayerClient, String str) {
        this.zzdj = str;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zzd(this.zzdj, 0);
        taskCompletionSource.setResult(null);
    }
}
