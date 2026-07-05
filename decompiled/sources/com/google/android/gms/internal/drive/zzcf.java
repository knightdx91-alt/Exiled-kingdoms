package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcf implements DrivePreferencesApi.FileUploadPreferencesResult {
    private final Status zzdw;
    private final FileUploadPreferences zzfk;

    private zzcf(zzcb zzcbVar, Status status, FileUploadPreferences fileUploadPreferences) {
        this.zzdw = status;
        this.zzfk = fileUploadPreferences;
    }

    @Override // com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult
    public final FileUploadPreferences getFileUploadPreferences() {
        return this.zzfk;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzdw;
    }

    /* synthetic */ zzcf(zzcb zzcbVar, Status status, FileUploadPreferences fileUploadPreferences, zzcc zzccVar) {
        this(zzcbVar, status, fileUploadPreferences);
    }
}
