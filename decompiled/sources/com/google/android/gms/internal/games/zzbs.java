package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbs extends zzbx {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String[] zzjw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbs(zzbo zzboVar, GoogleApiClient googleApiClient, boolean z2, String[] strArr) {
        super(googleApiClient, null);
        this.zzjg = z2;
        this.zzjw = strArr;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zzb(this, this.zzjg, this.zzjw);
    }
}
