package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaj extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ long zzbt;

    zzaj(LeaderboardsClient leaderboardsClient, String str, long j2) {
        this.zzbq = str;
        this.zzbt = j2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zza((BaseImplementation.ResultHolder<Leaderboards.SubmitScoreResult>) null, this.zzbq, this.zzbt, (String) null);
    }
}
