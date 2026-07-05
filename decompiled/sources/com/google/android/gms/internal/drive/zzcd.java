package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcd extends zzav {
    private final /* synthetic */ zzei zzfj;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcd(zzcb zzcbVar, GoogleApiClient googleApiClient, zzei zzeiVar) {
        super(googleApiClient);
        this.zzfj = zzeiVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgo(this.zzfj), new zzgs(this));
    }
}
