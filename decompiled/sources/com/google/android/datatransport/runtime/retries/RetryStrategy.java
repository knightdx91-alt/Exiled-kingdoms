package com.google.android.datatransport.runtime.retries;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface RetryStrategy<TInput, TResult> {
    TInput shouldRetry(TInput tinput, TResult tresult);
}
