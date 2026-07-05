package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzct extends TaskApiCall<zzaw, DriveContents> {
    private final /* synthetic */ DriveFile zzfq;
    private final /* synthetic */ int zzfr;

    zzct(zzch zzchVar, DriveFile driveFile, int i2) {
        this.zzfq = driveFile;
        this.zzfr = i2;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<DriveContents> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzfq.getDriveId(), this.zzfr, 0), new zzhc(taskCompletionSource));
    }
}
