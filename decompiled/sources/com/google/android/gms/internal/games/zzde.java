package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzde extends zzdo {
    private final /* synthetic */ String zzew;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzde(zzdb zzdbVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, null);
        this.zzew = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zzc(this, this.zzew);
    }
}
