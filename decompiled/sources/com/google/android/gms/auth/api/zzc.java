package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.p000authapi.zzr;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzc extends Api.AbstractClientBuilder<zzr, Auth.AuthCredentialsOptions> {
    zzc() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Auth.AuthCredentialsOptions authCredentialsOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzr(context, looper, clientSettings, authCredentialsOptions, connectionCallbacks, onConnectionFailedListener);
    }
}
