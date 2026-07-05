package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzah extends zzam {
    private final /* synthetic */ int zzdt = DriveFile.MODE_WRITE_ONLY;

    zzah(zzaf zzafVar, GoogleApiClient googleApiClient, int i2) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzr(this.zzdt), new zzak(this));
    }
}
