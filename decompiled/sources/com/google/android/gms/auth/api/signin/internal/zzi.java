package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzi extends zzo<GoogleSignInResult> {
    final /* synthetic */ Context val$context;
    final /* synthetic */ GoogleSignInOptions zzbj;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzi(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        super(googleApiClient);
        this.val$context = context;
        this.zzbj = googleSignInOptions;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new GoogleSignInResult(null, status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzu) ((zzg) anyClient).getService()).zzc(new zzj(this), this.zzbj);
    }
}
