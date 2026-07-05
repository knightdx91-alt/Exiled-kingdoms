package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.stats.Stats;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzcz extends Games.zza<Stats.LoadPlayerStatsResult> {
    private zzcz(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzda(this, status);
    }

    /* synthetic */ zzcz(GoogleApiClient googleApiClient, zzcy zzcyVar) {
        this(googleApiClient);
    }
}
