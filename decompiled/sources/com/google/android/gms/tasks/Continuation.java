package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Continuation<TResult, TContinuationResult> {
    TContinuationResult then(Task<TResult> task);
}
