package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbl extends zzbm {
    private final /* synthetic */ boolean zzjg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbl(zzbe zzbeVar, GoogleApiClient googleApiClient, boolean z2) {
        super(googleApiClient);
        this.zzjg = z2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzjg);
    }
}
