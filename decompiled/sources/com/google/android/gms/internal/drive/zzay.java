package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzay extends zzav {
    private final /* synthetic */ zzee zzej;
    private final /* synthetic */ zzgm zzek;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzay(zzaw zzawVar, GoogleApiClient googleApiClient, zzgm zzgmVar, zzee zzeeVar) {
        super(googleApiClient);
        this.zzek = zzgmVar;
        this.zzej = zzeeVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(this.zzek, this.zzej, (String) null, new zzgs(this));
    }
}
