package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzah<TResult> extends TaskApiCall<com.google.android.gms.games.internal.zze, TResult> {
    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        try {
            zza((com.google.android.gms.games.internal.zze) anyClient, taskCompletionSource);
        } catch (RemoteException | SecurityException e2) {
            taskCompletionSource.trySetException(e2);
        }
    }

    protected abstract void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<TResult> taskCompletionSource);
}
