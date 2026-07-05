package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbq extends zzbv {
    private final /* synthetic */ String zzjt;
    private final /* synthetic */ String zzju;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbq(zzbo zzboVar, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient, null);
        this.zzjt = str;
        this.zzju = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zzb(this, this.zzjt, this.zzju);
    }
}
