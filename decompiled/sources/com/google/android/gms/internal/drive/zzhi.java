package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhi extends zzhb<MetadataBuffer> {
    public zzhi(TaskCompletionSource<MetadataBuffer> taskCompletionSource) {
        super(taskCompletionSource);
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfp zzfpVar) {
        zzap().setResult(new MetadataBuffer(zzfpVar.zzam()));
    }
}
