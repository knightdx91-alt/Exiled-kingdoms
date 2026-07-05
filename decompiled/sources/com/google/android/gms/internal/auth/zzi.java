package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzi extends BaseImplementation.ApiMethodImpl<Result, zzr> {
    private final /* synthetic */ boolean zzae;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzi(zzh zzhVar, Api api, GoogleApiClient googleApiClient, boolean z2) {
        super((Api<?>) api, googleApiClient);
        this.zzae = z2;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final Result createFailedResult(Status status) {
        return new zzp(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.auth.account.zzc) ((zzr) anyClient).getService()).zzb(this.zzae);
        setResult(new zzp(Status.RESULT_SUCCESS));
    }
}
