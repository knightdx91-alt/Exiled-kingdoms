package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhc extends zzhb<DriveContents> {
    public zzhc(TaskCompletionSource<DriveContents> taskCompletionSource) {
        super(taskCompletionSource);
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfb zzfbVar) {
        zzap().setResult(new zzbi(zzfbVar.zzai()));
    }
}
