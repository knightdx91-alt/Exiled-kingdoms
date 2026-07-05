package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbf extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ String zzdj;

    zzbf(RealTimeMultiplayerClient realTimeMultiplayerClient, String str) {
        this.zzdj = str;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zzb(this.zzdj, 0);
        taskCompletionSource.setResult(null);
    }
}
