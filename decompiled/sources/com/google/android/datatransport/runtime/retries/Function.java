package com.google.android.datatransport.runtime.retries;

import java.lang.Throwable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Function<TInput, TResult, TException extends Throwable> {
    TResult apply(TInput tinput);
}
