package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzda implements Stats.LoadPlayerStatsResult {
    private final /* synthetic */ Status zzbc;

    zzda(zzcz zzczVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult
    public final PlayerStats getPlayerStats() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
