package com.google.android.gms.internal.drive;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SuppressLint({"MissingRemoteException"})
final class zzat extends zzav {
    zzat(GoogleApiClient googleApiClient, Status status) {
        super(googleApiClient);
        setResult(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) {
    }
}
