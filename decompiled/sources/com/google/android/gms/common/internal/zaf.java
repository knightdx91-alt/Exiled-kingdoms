package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaf implements BaseGmsClient.BaseConnectionCallbacks {
    private final /* synthetic */ GoogleApiClient.ConnectionCallbacks zaoi;

    zaf(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zaoi = connectionCallbacks;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zaoi.onConnected(bundle);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
        this.zaoi.onConnectionSuspended(i2);
    }
}
