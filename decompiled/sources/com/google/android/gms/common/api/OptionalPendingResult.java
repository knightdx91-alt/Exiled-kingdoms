package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class OptionalPendingResult<R extends Result> extends PendingResult<R> {
    public abstract R get();

    public abstract boolean isDone();
}
