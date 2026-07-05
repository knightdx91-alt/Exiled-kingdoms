package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdj extends zzdq {
    private final /* synthetic */ String zzew;
    private final /* synthetic */ String zzkk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdj(zzdb zzdbVar, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient, null);
        this.zzew = str;
        this.zzkk = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzew, this.zzkk);
    }
}
