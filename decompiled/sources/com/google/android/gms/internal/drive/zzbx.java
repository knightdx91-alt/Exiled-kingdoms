package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbx implements DriveFolder.DriveFileResult {
    private final Status zzdw;
    private final DriveFile zzfg;

    public zzbx(Status status, DriveFile driveFile) {
        this.zzdw = status;
        this.zzfg = driveFile;
    }

    @Override // com.google.android.gms.drive.DriveFolder.DriveFileResult
    public final DriveFile getDriveFile() {
        return this.zzfg;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzdw;
    }
}
