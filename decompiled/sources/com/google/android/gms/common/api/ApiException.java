package com.google.android.gms.common.api;

import a.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ApiException extends Exception {
    protected final Status mStatus;

    public ApiException(Status status) {
        int statusCode = status.getStatusCode();
        String statusMessage = status.getStatusMessage() != null ? status.getStatusMessage() : "";
        StringBuilder sb = new StringBuilder(a.e(13, statusMessage));
        sb.append(statusCode);
        sb.append(": ");
        sb.append(statusMessage);
        super(sb.toString());
        this.mStatus = status;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    @Deprecated
    public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}
