package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzag extends com.google.android.gms.internal.games.zzah<Intent> {
    private final /* synthetic */ String zzbq;

    zzag(LeaderboardsClient leaderboardsClient, String str) {
        this.zzbq = str;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Intent> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zza(this.zzbq, -1, -1));
    }
}
