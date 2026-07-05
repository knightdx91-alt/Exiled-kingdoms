package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder<L> zajt;
    private final Feature[] zaju;
    private final boolean zajv;

    @KeepForSdk
    protected RegisterListenerMethod(ListenerHolder<L> listenerHolder) {
        this.zajt = listenerHolder;
        this.zaju = null;
        this.zajv = false;
    }

    @KeepForSdk
    public void clearListener() {
        this.zajt.clear();
    }

    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zajt.getListenerKey();
    }

    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return this.zaju;
    }

    @KeepForSdk
    protected abstract void registerListener(A a2, TaskCompletionSource<Void> taskCompletionSource);

    public final boolean shouldAutoResolveMissingFeatures() {
        return this.zajv;
    }

    @KeepForSdk
    protected RegisterListenerMethod(ListenerHolder<L> listenerHolder, Feature[] featureArr, boolean z2) {
        this.zajt = listenerHolder;
        this.zaju = featureArr;
        this.zajv = z2;
    }
}
