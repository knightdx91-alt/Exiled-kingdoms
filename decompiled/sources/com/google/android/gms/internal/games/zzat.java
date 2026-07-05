package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzat extends zzba {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ long zzbt;
    private final /* synthetic */ String zzbu;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzat(zzam zzamVar, GoogleApiClient googleApiClient, String str, long j2, String str2) {
        super(googleApiClient);
        this.zzbq = str;
        this.zzbt = j2;
        this.zzbu = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzbq, this.zzbt, this.zzbu);
    }
}
