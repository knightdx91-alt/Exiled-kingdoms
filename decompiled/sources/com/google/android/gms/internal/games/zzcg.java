package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.request.Requests;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzcg extends Games.zza<Requests.UpdateRequestsResult> {
    private zzcg(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzch(this, status);
    }

    /* synthetic */ zzcg(GoogleApiClient googleApiClient, zzcb zzcbVar) {
        this(googleApiClient);
    }
}
