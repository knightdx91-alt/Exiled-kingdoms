package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcq extends com.google.android.gms.internal.games.zzah<Intent> {
    private final /* synthetic */ int zzdl;
    private final /* synthetic */ int zzdm;
    private final /* synthetic */ boolean zzdn;

    zzcq(TurnBasedMultiplayerClient turnBasedMultiplayerClient, int i2, int i3, boolean z2) {
        this.zzdl = i2;
        this.zzdm = i3;
        this.zzdn = z2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Intent> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zza(this.zzdl, this.zzdm, this.zzdn));
    }
}
