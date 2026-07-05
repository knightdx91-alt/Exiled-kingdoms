package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.Requests;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcf implements Requests.LoadRequestsResult {
    private final /* synthetic */ Status zzbc;

    zzcf(zzce zzceVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.request.Requests.LoadRequestsResult
    public final GameRequestBuffer getRequests(int i2) {
        return new GameRequestBuffer(DataHolder.empty(this.zzbc.getStatusCode()));
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
