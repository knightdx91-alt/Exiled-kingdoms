package com.google.android.gms.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.drive.zzbi;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public class CreateFileActivityBuilder {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private final com.google.android.gms.internal.drive.zzt zzn = new com.google.android.gms.internal.drive.zzt(0);
    private DriveContents zzo;
    private boolean zzp;

    public IntentSender build(GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), "Client must be connected");
        zzf();
        return this.zzn.build(googleApiClient);
    }

    final int getRequestId() {
        return this.zzn.getRequestId();
    }

    public CreateFileActivityBuilder setActivityStartFolder(DriveId driveId) {
        this.zzn.zza(driveId);
        return this;
    }

    public CreateFileActivityBuilder setActivityTitle(String str) {
        this.zzn.zzc(str);
        return this;
    }

    public CreateFileActivityBuilder setInitialDriveContents(DriveContents driveContents) {
        if (driveContents == null) {
            this.zzn.zzd(1);
        } else {
            if (!(driveContents instanceof zzbi)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            }
            if (driveContents.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            }
            if (driveContents.zzj()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
            this.zzn.zzd(driveContents.zzh().zzj);
            this.zzo = driveContents;
        }
        this.zzp = true;
        return this;
    }

    public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet metadataChangeSet) {
        this.zzn.zza(metadataChangeSet);
        return this;
    }

    final MetadataChangeSet zzb() {
        return this.zzn.zzb();
    }

    final DriveId zzc() {
        return this.zzn.zzc();
    }

    final String zzd() {
        return this.zzn.zzd();
    }

    final int zze() {
        return 0;
    }

    final void zzf() {
        Preconditions.checkState(this.zzp, "Must call setInitialDriveContents.");
        DriveContents driveContents = this.zzo;
        if (driveContents != null) {
            driveContents.zzi();
        }
        this.zzn.zzf();
    }
}
