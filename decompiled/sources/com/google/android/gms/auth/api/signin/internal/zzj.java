package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzj extends zzc {
    private final /* synthetic */ zzi zzbk;

    zzj(zzi zziVar) {
        this.zzbk = zziVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzc, com.google.android.gms.auth.api.signin.internal.zzs
    public final void zzc(GoogleSignInAccount googleSignInAccount, Status status) {
        if (googleSignInAccount != null) {
            zzp.zzd(this.zzbk.val$context).zzc(this.zzbk.zzbj, googleSignInAccount);
        }
        this.zzbk.setResult(new GoogleSignInResult(googleSignInAccount, status));
    }
}
