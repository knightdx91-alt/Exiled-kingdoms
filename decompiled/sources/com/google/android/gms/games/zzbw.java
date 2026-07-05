package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbw extends com.google.android.gms.internal.games.zzah<Intent> {
    private final /* synthetic */ String zzeb;
    private final /* synthetic */ boolean zzec;
    private final /* synthetic */ boolean zzed;
    private final /* synthetic */ int zzee;

    zzbw(SnapshotsClient snapshotsClient, String str, boolean z2, boolean z3, int i2) {
        this.zzeb = str;
        this.zzec = z2;
        this.zzed = z3;
        this.zzee = i2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Intent> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zza(this.zzeb, this.zzec, this.zzed, this.zzee));
    }
}
