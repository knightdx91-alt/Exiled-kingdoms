package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.games.Players;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbh extends zzbm {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbh(zzbe zzbeVar, GoogleApiClient googleApiClient, int i2, boolean z2) {
        super(googleApiClient);
        this.zzjs = i2;
        this.zzjg = z2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza((BaseImplementation.ResultHolder<Players.LoadPlayersResult>) this, this.zzjs, false, this.zzjg);
    }
}
