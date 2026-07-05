package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zack extends TaskApiCall {
    private final /* synthetic */ TaskApiCall.Builder zakm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zack(TaskApiCall.Builder builder, Feature[] featureArr, boolean z2) {
        super(featureArr, z2);
        this.zakm = builder;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zakm.zakl.accept(anyClient, taskCompletionSource);
    }
}
