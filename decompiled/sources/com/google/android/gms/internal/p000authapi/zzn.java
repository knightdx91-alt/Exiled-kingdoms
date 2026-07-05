package com.google.android.gms.internal.p000authapi;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzn extends zzp<Status> {
    zzn(zzi zziVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.internal.p000authapi.zzp
    protected final void zzc(Context context, zzw zzwVar) {
        zzwVar.zzc(new zzo(this));
    }
}
