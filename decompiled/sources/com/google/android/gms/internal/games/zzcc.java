package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcc extends zzcg {
    private final /* synthetic */ String[] zzjx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcc(zzca zzcaVar, GoogleApiClient googleApiClient, String[] strArr) {
        super(googleApiClient, null);
        this.zzjx = strArr;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zzb(this, this.zzjx);
    }
}
