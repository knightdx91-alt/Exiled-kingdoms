package com.google.android.gms.internal.p001authapiphone;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzm extends TaskApiCall<zzi, Void> {
    private TaskCompletionSource<Void> zzf;

    private zzm() {
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) {
        this.zzf = taskCompletionSource;
        zza((zze) ((zzi) anyClient).getService());
    }

    protected abstract void zza(zze zzeVar);

    protected final void zzb(Status status) {
        TaskUtil.setResultOrApiException(status, this.zzf);
    }

    /* synthetic */ zzm(zzk zzkVar) {
        this();
    }
}
