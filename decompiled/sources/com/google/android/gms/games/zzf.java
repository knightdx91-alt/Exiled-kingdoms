package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzf extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ String zzk;
    private final /* synthetic */ int zzl;

    zzf(EventsClient eventsClient, String str, int i2) {
        this.zzk = str;
        this.zzl = i2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zza(this.zzk, this.zzl);
    }
}
