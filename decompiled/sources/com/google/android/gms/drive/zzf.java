package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.internal.drive.zzaw;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzf extends Api.AbstractClientBuilder<zzaw, Drive.zzb> {
    zzf() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Drive.zzb zzbVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (zzbVar == null) {
            return new zzaw(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, new Bundle());
        }
        throw new NoSuchMethodError();
    }
}
