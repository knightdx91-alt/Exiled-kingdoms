package com.google.android.gms.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzl extends Games.zzd {
    zzl(GoogleApiClient googleApiClient) {
        super(googleApiClient, null);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zzb(this);
    }
}
