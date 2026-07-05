package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdb extends TaskApiCall<zzaw, DriveFolder> {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ DriveFolder zzfx;

    zzdb(zzch zzchVar, MetadataChangeSet metadataChangeSet, DriveFolder driveFolder) {
        this.zzfb = metadataChangeSet;
        this.zzfx = driveFolder;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<DriveFolder> taskCompletionSource) {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzy(this.zzfx.getDriveId(), this.zzfb.zzp()), new zzhe(taskCompletionSource));
    }
}
