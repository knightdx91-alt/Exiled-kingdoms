package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdd extends TaskApiCall<zzaw, Metadata> {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ DriveResource zzfo;

    zzdd(zzch zzchVar, MetadataChangeSet metadataChangeSet, DriveResource driveResource) {
        this.zzfb = metadataChangeSet;
        this.zzfo = driveResource;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Metadata> taskCompletionSource) {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzgz(this.zzfo.getDriveId(), this.zzfb.zzp()), new zzhj(taskCompletionSource));
    }
}
