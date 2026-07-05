package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhe extends zzhb<DriveFolder> {
    public zzhe(TaskCompletionSource<DriveFolder> taskCompletionSource) {
        super(taskCompletionSource);
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfh zzfhVar) {
        zzap().setResult(zzfhVar.getDriveId().asDriveFolder());
    }
}
