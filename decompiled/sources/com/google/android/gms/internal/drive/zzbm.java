package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbm extends zzav {
    private final /* synthetic */ zzbi zzet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbm(zzbi zzbiVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzet = zzbiVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzo(this.zzet.zzeq.getRequestId(), false), new zzgs(this));
    }
}
