package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzax extends zzav {
    private final /* synthetic */ zzj zzei;
    private final /* synthetic */ zzee zzej;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzax(zzaw zzawVar, GoogleApiClient googleApiClient, zzj zzjVar, zzee zzeeVar) {
        super(googleApiClient);
        this.zzei = zzjVar;
        this.zzej = zzeeVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(this.zzei, this.zzej, (String) null, new zzgs(this));
    }
}
