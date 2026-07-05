package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbz implements DriveFolder.DriveFolderResult {
    private final Status zzdw;
    private final DriveFolder zzfh;

    public zzbz(Status status, DriveFolder driveFolder) {
        this.zzdw = status;
        this.zzfh = driveFolder;
    }

    @Override // com.google.android.gms.drive.DriveFolder.DriveFolderResult
    public final DriveFolder getDriveFolder() {
        return this.zzfh;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzdw;
    }
}
