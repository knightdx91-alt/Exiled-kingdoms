package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzco extends Games.zza<Snapshots.CommitSnapshotResult> {
    private zzco(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzcp(this, status);
    }

    /* synthetic */ zzco(GoogleApiClient googleApiClient, zzcj zzcjVar) {
        this(googleApiClient);
    }
}
