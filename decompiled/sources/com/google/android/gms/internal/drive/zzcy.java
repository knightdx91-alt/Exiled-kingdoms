package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcy extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ MetadataChangeSet zzeu;
    private final /* synthetic */ DriveContents zzfv;
    private final /* synthetic */ com.google.android.gms.drive.zzn zzfw;

    zzcy(zzch zzchVar, com.google.android.gms.drive.zzn zznVar, DriveContents driveContents, MetadataChangeSet metadataChangeSet) {
        this.zzfw = zznVar;
        this.zzfv = driveContents;
        this.zzeu = metadataChangeSet;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) {
        zzaw zzawVar = (zzaw) anyClient;
        try {
            this.zzfw.zza(zzawVar);
        } catch (IllegalStateException e2) {
            taskCompletionSource.setException(e2);
        }
        this.zzfv.zzi();
        this.zzeu.zzp().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzm(this.zzfv.getDriveId(), this.zzeu.zzp(), this.zzfv.zzh().getRequestId(), this.zzfv.zzh().zza(), this.zzfw), new zzhl(taskCompletionSource));
    }
}
