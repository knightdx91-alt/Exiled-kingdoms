package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbr extends zzbx {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjl;
    private final /* synthetic */ int[] zzjv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbr(zzbo zzboVar, GoogleApiClient googleApiClient, int[] iArr, int i2, boolean z2) {
        super(googleApiClient, null);
        this.zzjv = iArr;
        this.zzjl = i2;
        this.zzjg = z2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzjv, this.zzjl, this.zzjg);
    }
}
