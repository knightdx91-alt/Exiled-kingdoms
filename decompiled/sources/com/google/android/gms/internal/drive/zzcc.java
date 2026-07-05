package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcc extends zzcg {
    private final /* synthetic */ zzcb zzfi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcc(zzcb zzcbVar, GoogleApiClient googleApiClient) {
        super(zzcbVar, googleApiClient);
        this.zzfi = zzcbVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zzb(new zzce(this.zzfi, this, null));
    }
}
