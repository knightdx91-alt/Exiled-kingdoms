package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zag implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ GoogleApiClient.OnConnectionFailedListener zaoj;

    zag(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zaoj = onConnectionFailedListener;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zaoj.onConnectionFailed(connectionResult);
    }
}
