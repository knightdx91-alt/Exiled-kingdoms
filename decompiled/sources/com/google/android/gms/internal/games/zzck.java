package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzck extends zzcu {
    private final /* synthetic */ String zzka;
    private final /* synthetic */ boolean zzkb;
    private final /* synthetic */ int zzkc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzck(zzci zzciVar, GoogleApiClient googleApiClient, String str, boolean z2, int i2) {
        super(googleApiClient, null);
        this.zzka = str;
        this.zzkb = z2;
        this.zzkc = i2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzka, this.zzkb, this.zzkc);
    }
}
