package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzee extends Games.zza<Videos.CaptureCapabilitiesResult> {
    private zzee(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzef(this, status);
    }

    /* synthetic */ zzee(GoogleApiClient googleApiClient, zzdz zzdzVar) {
        this(googleApiClient);
    }
}
