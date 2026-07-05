package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcw extends TaskApiCall<zzaw, DriveContents> {
    private final /* synthetic */ int zzdt = DriveFile.MODE_WRITE_ONLY;

    zzcw(zzch zzchVar, int i2) {
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<DriveContents> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzr(this.zzdt), new zzhc(taskCompletionSource));
    }
}
