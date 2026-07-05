package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zam {
    private final int zadg;
    private final ConnectionResult zadh;

    zam(ConnectionResult connectionResult, int i2) {
        Preconditions.checkNotNull(connectionResult);
        this.zadh = connectionResult;
        this.zadg = i2;
    }

    final ConnectionResult getConnectionResult() {
        return this.zadh;
    }

    final int zar() {
        return this.zadg;
    }
}
