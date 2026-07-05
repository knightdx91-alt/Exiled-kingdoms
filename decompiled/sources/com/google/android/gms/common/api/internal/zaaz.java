package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaaz implements GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ StatusPendingResult zahi;

    zaaz(zaaw zaawVar, StatusPendingResult statusPendingResult) {
        this.zahi = statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zahi.setResult(new Status(8));
    }
}
