package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzal implements Releasable, DriveApi.DriveContentsResult {
    private final Status zzdw;
    private final DriveContents zzo;

    public zzal(Status status, DriveContents driveContents) {
        this.zzdw = status;
        this.zzo = driveContents;
    }

    @Override // com.google.android.gms.drive.DriveApi.DriveContentsResult
    public final DriveContents getDriveContents() {
        return this.zzo;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzdw;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
        DriveContents driveContents = this.zzo;
        if (driveContents != null) {
            driveContents.zzi();
        }
    }
}
