package com.google.android.gms.common;

import android.content.Intent;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zzaf;

    public GooglePlayServicesRepairableException(int i2, String str, Intent intent) {
        super(str, intent);
        this.zzaf = i2;
    }

    public int getConnectionStatusCode() {
        return this.zzaf;
    }
}
