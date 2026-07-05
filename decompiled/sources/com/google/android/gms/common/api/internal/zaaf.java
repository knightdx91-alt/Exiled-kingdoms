package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaaf {
    private final zai<?> zafp;
    private final TaskCompletionSource<Boolean> zafq = new TaskCompletionSource<>();

    public zaaf(zai<?> zaiVar) {
        this.zafp = zaiVar;
    }

    public final TaskCompletionSource<Boolean> zaal() {
        return this.zafq;
    }

    public final zai<?> zak() {
        return this.zafp;
    }
}
