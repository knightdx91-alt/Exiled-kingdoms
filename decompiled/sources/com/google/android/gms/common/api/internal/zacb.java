package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zacb extends UnregisterListenerMethod {
    private final /* synthetic */ RegistrationMethods.Builder zakg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zacb(RegistrationMethods.Builder builder, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zakg = builder;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    protected final void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zakg.zakb.accept(anyClient, taskCompletionSource);
    }
}
