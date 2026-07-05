package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzah extends com.google.android.gms.internal.games.zzah<Intent> {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ int zzbr;

    zzah(LeaderboardsClient leaderboardsClient, String str, int i2) {
        this.zzbq = str;
        this.zzbr = i2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Intent> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zza(this.zzbq, this.zzbr, -1));
    }
}
