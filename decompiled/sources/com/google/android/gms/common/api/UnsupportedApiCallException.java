package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzar;

    @KeepForSdk
    public UnsupportedApiCallException(Feature feature) {
        this.zzar = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String strValueOf = String.valueOf(this.zzar);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 8);
        sb.append("Missing ");
        sb.append(strValueOf);
        return sb.toString();
    }
}
