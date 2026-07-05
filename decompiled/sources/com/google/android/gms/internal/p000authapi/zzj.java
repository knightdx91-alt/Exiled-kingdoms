package com.google.android.gms.internal.p000authapi;

import android.content.Context;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzj extends zzp<CredentialRequestResult> {
    private final /* synthetic */ CredentialRequest zzam;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzj(zzi zziVar, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        super(googleApiClient);
        this.zzam = credentialRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return zzh.zzd(status);
    }

    @Override // com.google.android.gms.internal.p000authapi.zzp
    protected final void zzc(Context context, zzw zzwVar) {
        zzwVar.zzc(new zzk(this), this.zzam);
    }
}
