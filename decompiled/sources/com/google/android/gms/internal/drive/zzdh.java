package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdh extends TaskApiCall<zzaw, DriveFile> {
    private final DriveFolder zzfh;
    private final MetadataChangeSet zzga;
    private ExecutionOptions zzgb;
    private String zzgc = null;
    private com.google.android.gms.drive.metadata.internal.zzk zzgd;
    private final DriveContents zzo;

    zzdh(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet, DriveContents driveContents, ExecutionOptions executionOptions, String str) {
        this.zzfh = driveFolder;
        this.zzga = metadataChangeSet;
        this.zzo = driveContents;
        this.zzgb = executionOptions;
        Preconditions.checkNotNull(driveFolder, "DriveFolder must not be null");
        Preconditions.checkNotNull(driveFolder.getDriveId(), "Folder's DriveId must not be null");
        Preconditions.checkNotNull(metadataChangeSet, "MetadataChangeSet must not be null");
        Preconditions.checkNotNull(executionOptions, "ExecutionOptions must not be null");
        com.google.android.gms.drive.metadata.internal.zzk zzkVarZze = com.google.android.gms.drive.metadata.internal.zzk.zze(metadataChangeSet.getMimeType());
        this.zzgd = zzkVarZze;
        if (zzkVarZze != null && zzkVarZze.isFolder()) {
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolderManagerClient#createFolder() instead of mime type application/vnd.google-apps.folder");
        }
        if (driveContents != null) {
            if (!(driveContents instanceof zzbi)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            }
            if (driveContents.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            }
            if (driveContents.zzj()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<DriveFile> taskCompletionSource) {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzgb.zza(zzawVar);
        MetadataChangeSet metadataChangeSet = this.zzga;
        metadataChangeSet.zzp().zza(zzawVar.getContext());
        int iZza = zzbs.zza(this.zzo, this.zzgd);
        com.google.android.gms.drive.metadata.internal.zzk zzkVar = this.zzgd;
        ((zzeo) zzawVar.getService()).zza(new zzw(this.zzfh.getDriveId(), metadataChangeSet.zzp(), iZza, (zzkVar == null || !zzkVar.zzaz()) ? 0 : 1, this.zzgb), new zzhd(taskCompletionSource));
    }
}
