package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zaq implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final Api<?> mApi;
    private final boolean zaeb;
    private zar zaec;

    public zaq(Api<?> api, boolean z2) {
        this.mApi = api;
        this.zaeb = z2;
    }

    private final void zav() {
        Preconditions.checkNotNull(this.zaec, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        zav();
        this.zaec.onConnected(bundle);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zav();
        this.zaec.zaa(connectionResult, this.mApi, this.zaeb);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
        zav();
        this.zaec.onConnectionSuspended(i2);
    }

    public final void zaa(zar zarVar) {
        this.zaec = zarVar;
    }
}
