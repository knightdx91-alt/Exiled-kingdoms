package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievements;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzp extends Games.zza<Achievements.LoadAchievementsResult> {
    private zzp(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzq(this, status);
    }

    /* synthetic */ zzp(GoogleApiClient googleApiClient, zzg zzgVar) {
        this(googleApiClient);
    }
}
