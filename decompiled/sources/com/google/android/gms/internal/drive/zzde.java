package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzde extends TaskApiCall<zzaw, MetadataBuffer> {
    private final /* synthetic */ DriveResource zzfo;

    zzde(zzch zzchVar, DriveResource driveResource) {
        this.zzfo = driveResource;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<MetadataBuffer> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzex(this.zzfo.getDriveId()), new zzhi(taskCompletionSource));
    }
}
