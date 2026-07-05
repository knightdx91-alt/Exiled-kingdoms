package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaj extends zzak {
    private final /* synthetic */ int zzjl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaj(zzai zzaiVar, GoogleApiClient googleApiClient, int i2) {
        super(googleApiClient, null);
        this.zzjl = i2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzjl);
    }
}
