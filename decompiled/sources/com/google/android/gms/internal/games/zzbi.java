package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.games.Players;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbi extends zzbm {
    private final /* synthetic */ int zzjs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbi(zzbe zzbeVar, GoogleApiClient googleApiClient, int i2) {
        super(googleApiClient);
        this.zzjs = i2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza((BaseImplementation.ResultHolder<Players.LoadPlayersResult>) this, this.zzjs, true, false);
    }
}
