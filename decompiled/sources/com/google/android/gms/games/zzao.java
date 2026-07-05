package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzao extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ int zzbx;

    zzao(NotificationsClient notificationsClient, int i2) {
        this.zzbx = i2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zzk(this.zzbx);
        taskCompletionSource.setResult(null);
    }
}
