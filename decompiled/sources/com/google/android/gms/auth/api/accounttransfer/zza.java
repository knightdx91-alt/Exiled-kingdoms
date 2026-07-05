package com.google.android.gms.auth.api.accounttransfer;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zza extends Api.AbstractClientBuilder<com.google.android.gms.internal.auth.zzu, zzn> {
    zza() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, zzn zznVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new com.google.android.gms.internal.auth.zzu(context, looper, clientSettings, zznVar, connectionCallbacks, onConnectionFailedListener);
    }
}
