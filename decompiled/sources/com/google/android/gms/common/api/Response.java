package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Response<T extends Result> {
    private T zzao;

    public Response() {
    }

    protected T getResult() {
        return this.zzao;
    }

    public void setResult(T t2) {
        this.zzao = t2;
    }

    protected Response(T t2) {
        this.zzao = t2;
    }
}
