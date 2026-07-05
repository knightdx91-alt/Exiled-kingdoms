package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzcs extends Games.zza<Snapshots.LoadSnapshotsResult> {
    private zzcs(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzct(this, status);
    }

    /* synthetic */ zzcs(GoogleApiClient googleApiClient, zzcj zzcjVar) {
        this(googleApiClient);
    }
}
