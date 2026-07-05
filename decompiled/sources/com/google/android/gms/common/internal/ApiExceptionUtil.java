package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class ApiExceptionUtil {
    @KeepForSdk
    public static ApiException fromStatus(Status status) {
        return status.hasResolution() ? new ResolvableApiException(status) : new ApiException(status);
    }
}
