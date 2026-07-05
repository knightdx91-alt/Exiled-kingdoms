package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdu extends zzav {
    private final /* synthetic */ zzdp zzgo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdu(zzdp zzdpVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzgo = zzdpVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzab(this.zzgo.zzk), new zzgs(this));
    }
}
