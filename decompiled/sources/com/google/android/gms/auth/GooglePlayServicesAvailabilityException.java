package com.google.android.gms.auth;

import android.content.Intent;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
    private final int zzu;

    GooglePlayServicesAvailabilityException(int i2, String str, Intent intent) {
        super(str, intent);
        this.zzu = i2;
    }

    public int getConnectionStatusCode() {
        return this.zzu;
    }
}
