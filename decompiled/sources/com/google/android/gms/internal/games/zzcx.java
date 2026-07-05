package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.stats.Stats;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzcx implements Stats {
    @Override // com.google.android.gms.games.stats.Stats
    public final PendingResult<Stats.LoadPlayerStatsResult> loadPlayerStats(GoogleApiClient googleApiClient, boolean z2) {
        return googleApiClient.enqueue(new zzcy(this, googleApiClient, z2));
    }
}
