package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.TransformedResult;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {
    private final BasePendingResult<R> zajp;

    public OptionalPendingResultImpl(PendingResult<R> pendingResult) {
        this.zajp = (BasePendingResult) pendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(PendingResult.StatusListener statusListener) {
        this.zajp.addStatusListener(statusListener);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final R await() {
        return (R) this.zajp.await();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        this.zajp.cancel();
    }

    @Override // com.google.android.gms.common.api.OptionalPendingResult
    public final R get() {
        if (isDone()) {
            return (R) await(0L, TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final boolean isCanceled() {
        return this.zajp.isCanceled();
    }

    @Override // com.google.android.gms.common.api.OptionalPendingResult
    public final boolean isDone() {
        return this.zajp.isReady();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        this.zajp.setResultCallback(resultCallback);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        return this.zajp.then(resultTransform);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final Integer zam() {
        return this.zajp.zam();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final R await(long j2, TimeUnit timeUnit) {
        return (R) this.zajp.await(j2, timeUnit);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback<? super R> resultCallback, long j2, TimeUnit timeUnit) {
        this.zajp.setResultCallback(resultCallback, j2, timeUnit);
    }
}
