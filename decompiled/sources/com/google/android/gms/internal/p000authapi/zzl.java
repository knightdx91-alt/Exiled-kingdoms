package com.google.android.gms.internal.p000authapi;

import android.content.Context;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzl extends zzp<Status> {
    private final /* synthetic */ Credential zzao;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzl(zzi zziVar, GoogleApiClient googleApiClient, Credential credential) {
        super(googleApiClient);
        this.zzao = credential;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.internal.p000authapi.zzp
    protected final void zzc(Context context, zzw zzwVar) {
        zzwVar.zzc(new zzo(this), new zzy(this.zzao));
    }
}
