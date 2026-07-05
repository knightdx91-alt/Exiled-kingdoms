package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzbn extends zzdp implements DriveFile {
    public zzbn(DriveId driveId) {
        super(driveId);
    }

    @Override // com.google.android.gms.drive.DriveFile
    public final PendingResult<DriveApi.DriveContentsResult> open(GoogleApiClient googleApiClient, int i2, DriveFile.DownloadProgressListener downloadProgressListener) {
        if (i2 == 268435456 || i2 == 536870912 || i2 == 805306368) {
            return googleApiClient.enqueue(new zzbo(this, googleApiClient, i2, downloadProgressListener == null ? null : new zzbp(googleApiClient.registerListener(downloadProgressListener))));
        }
        throw new IllegalArgumentException("Invalid mode provided.");
    }
}
