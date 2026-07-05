package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzw extends zzz {
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String[] zzjk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzw(zzv zzvVar, GoogleApiClient googleApiClient, boolean z2, String[] strArr) {
        super(googleApiClient, null);
        this.zzjg = z2;
        this.zzjk = strArr;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzjg, this.zzjk);
    }
}
