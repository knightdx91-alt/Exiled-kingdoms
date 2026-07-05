package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzda extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ DriveContents zzfv;

    zzda(zzch zzchVar, DriveContents driveContents) {
        this.zzfv = driveContents;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzo(this.zzfv.zzh().getRequestId(), false), new zzhl(taskCompletionSource));
    }
}
