package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaz extends zzav {
    private final /* synthetic */ zzj zzei;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaz(zzaw zzawVar, GoogleApiClient googleApiClient, zzj zzjVar) {
        super(googleApiClient);
        this.zzei = zzjVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(this.zzei, (zzes) null, (String) null, new zzgs(this));
    }
}
