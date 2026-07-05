package com.google.android.gms.common.api.internal;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaba implements ResultCallback<Status> {
    private final /* synthetic */ zaaw zahg;
    private final /* synthetic */ StatusPendingResult zahi;
    private final /* synthetic */ boolean zahj;
    private final /* synthetic */ GoogleApiClient zahk;

    zaba(zaaw zaawVar, StatusPendingResult statusPendingResult, boolean z2, GoogleApiClient googleApiClient) {
        this.zahg = zaawVar;
        this.zahi = statusPendingResult;
        this.zahj = z2;
        this.zahk = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        Storage.getInstance(this.zahg.mContext).zaf();
        if (status.isSuccess() && this.zahg.isConnected()) {
            this.zahg.reconnect();
        }
        this.zahi.setResult(status);
        if (this.zahj) {
            this.zahk.disconnect();
        }
    }
}
