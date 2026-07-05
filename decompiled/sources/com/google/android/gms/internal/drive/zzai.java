package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzai extends zzap {
    private final /* synthetic */ String zzdu;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzai(zzaf zzafVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzdu = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzek(DriveId.zza(this.zzdu), false), new zzan(this));
    }
}
