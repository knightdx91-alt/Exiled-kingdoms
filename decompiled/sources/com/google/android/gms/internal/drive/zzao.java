package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzao implements DriveApi.DriveIdResult {
    private final Status zzdw;
    private final DriveId zzk;

    public zzao(Status status, DriveId driveId) {
        this.zzdw = status;
        this.zzk = driveId;
    }

    @Override // com.google.android.gms.drive.DriveApi.DriveIdResult
    public final DriveId getDriveId() {
        return this.zzk;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzdw;
    }
}
