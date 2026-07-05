package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.quest.Quests;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzbx extends Games.zza<Quests.LoadQuestsResult> {
    private zzbx(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzby(this, status);
    }

    /* synthetic */ zzbx(GoogleApiClient googleApiClient, zzbp zzbpVar) {
        this(googleApiClient);
    }
}
