package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzcg extends zzau<DrivePreferencesApi.FileUploadPreferencesResult> {
    private final /* synthetic */ zzcb zzfi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcg(zzcb zzcbVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzfi = zzcbVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected /* synthetic */ Result createFailedResult(Status status) {
        return new zzcf(this.zzfi, status, null, 0 == true ? 1 : 0);
    }
}
