package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzt<L> extends UnregisterListenerMethod<zze, L> {
    protected zzt(ListenerHolder.ListenerKey<L> listenerKey) {
        super(listenerKey);
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    protected /* synthetic */ void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        try {
            zzc((zze) anyClient, taskCompletionSource);
        } catch (SecurityException e2) {
            taskCompletionSource.trySetException(e2);
        }
    }

    protected abstract void zzc(zze zzeVar, TaskCompletionSource<Boolean> taskCompletionSource);
}
