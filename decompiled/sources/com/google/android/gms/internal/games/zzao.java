package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzao extends zzau {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ boolean zzjg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzao(zzam zzamVar, GoogleApiClient googleApiClient, String str, boolean z2) {
        super(googleApiClient, null);
        this.zzbq = str;
        this.zzjg = z2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zzb(this, this.zzbq, this.zzjg);
    }
}
