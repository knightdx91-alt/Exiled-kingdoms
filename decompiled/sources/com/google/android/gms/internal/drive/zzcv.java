package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcv extends UnregisterListenerMethod<zzaw, OpenFileCallback> {
    private final /* synthetic */ zzg zzfs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcv(zzch zzchVar, ListenerHolder.ListenerKey listenerKey, zzg zzgVar) {
        super(listenerKey);
        this.zzfs = zzgVar;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    protected final /* synthetic */ void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        taskCompletionSource.setResult(Boolean.valueOf(this.zzfs.cancel()));
    }
}
