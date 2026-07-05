package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhj extends zzhb<Metadata> {
    public zzhj(TaskCompletionSource<Metadata> taskCompletionSource) {
        super(taskCompletionSource);
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfs zzfsVar) {
        zzap().setResult(new zzaa(zzfsVar.zzan()));
    }
}
