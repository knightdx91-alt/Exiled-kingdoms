package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzab extends Games.zza<Result> {
    private zzab(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public Result createFailedResult(Status status) {
        return new zzac(this, status);
    }

    /* synthetic */ zzab(GoogleApiClient googleApiClient, zzw zzwVar) {
        this(googleApiClient);
    }
}
