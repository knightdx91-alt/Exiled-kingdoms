package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcd extends zzce {
    private final /* synthetic */ int zzjl;
    private final /* synthetic */ int zzjy;
    private final /* synthetic */ int zzjz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcd(zzca zzcaVar, GoogleApiClient googleApiClient, int i2, int i3, int i4) {
        super(googleApiClient, null);
        this.zzjy = i2;
        this.zzjz = i3;
        this.zzjl = i4;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzjy, this.zzjz, this.zzjl);
    }
}
