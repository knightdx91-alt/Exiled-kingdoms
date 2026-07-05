package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.OpenFileActivityOptions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbf extends TaskApiCall<zzaw, IntentSender> {
    private final /* synthetic */ OpenFileActivityOptions zzeo;

    zzbf(zzbb zzbbVar, OpenFileActivityOptions openFileActivityOptions) {
        this.zzeo = openFileActivityOptions;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<IntentSender> taskCompletionSource) {
        zzeo zzeoVar = (zzeo) ((zzaw) anyClient).getService();
        OpenFileActivityOptions openFileActivityOptions = this.zzeo;
        taskCompletionSource.setResult(zzeoVar.zza(new zzgg(openFileActivityOptions.zzay, openFileActivityOptions.zzaz, openFileActivityOptions.zzbb, openFileActivityOptions.zzbc)));
    }
}
