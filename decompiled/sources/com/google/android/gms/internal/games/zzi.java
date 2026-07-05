package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzi extends zzr {
    private final /* synthetic */ String val$id;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzi(zzf zzfVar, String str, GoogleApiClient googleApiClient, String str2) {
        super(str, googleApiClient);
        this.val$id = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.val$id);
    }
}
