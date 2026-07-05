package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GoogleSignInResult implements Result {
    private Status mStatus;
    private GoogleSignInAccount zzaz;

    public GoogleSignInResult(GoogleSignInAccount googleSignInAccount, Status status) {
        this.zzaz = googleSignInAccount;
        this.mStatus = status;
    }

    public GoogleSignInAccount getSignInAccount() {
        return this.zzaz;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.mStatus;
    }

    public boolean isSuccess() {
        return this.mStatus.isSuccess();
    }
}
