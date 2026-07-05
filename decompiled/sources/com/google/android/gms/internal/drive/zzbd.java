package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbd extends TaskApiCall<zzaw, TransferPreferences> {
    zzbd(zzbb zzbbVar) {
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<TransferPreferences> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zzb(new zzhg(taskCompletionSource));
    }
}
