package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhf extends zzhb<DriveId> {
    public zzhf(TaskCompletionSource<DriveId> taskCompletionSource) {
        super(taskCompletionSource);
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfh zzfhVar) {
        zzap().setResult(zzfhVar.getDriveId());
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfs zzfsVar) {
        zzap().setResult(new zzaa(zzfsVar.zzan()).getDriveId());
    }
}
