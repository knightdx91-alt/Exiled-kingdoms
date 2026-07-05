package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzs<L> extends RegisterListenerMethod<zze, L> {
    protected zzs(ListenerHolder<L> listenerHolder) {
        super(listenerHolder);
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    protected /* synthetic */ void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        try {
            zzb((zze) anyClient, taskCompletionSource);
        } catch (SecurityException e2) {
            taskCompletionSource.trySetException(e2);
        }
    }

    protected abstract void zzb(zze zzeVar, TaskCompletionSource<Void> taskCompletionSource);
}
