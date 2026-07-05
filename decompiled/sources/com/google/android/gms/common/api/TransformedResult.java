package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class TransformedResult<R extends Result> {
    public abstract void andFinally(ResultCallbacks<? super R> resultCallbacks);

    public abstract <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform);
}
