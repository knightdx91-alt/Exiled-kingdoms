package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcr extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ DriveResource zzfo;

    zzcr(zzch zzchVar, DriveResource driveResource) {
        this.zzfo = driveResource;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) {
        zzaw zzawVar = (zzaw) anyClient;
        if (!zzawVar.zzea) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
        }
        ((zzeo) zzawVar.getService()).zza(new zzj(1, this.zzfo.getDriveId()), (zzes) null, (String) null, new zzhl(taskCompletionSource));
    }
}
