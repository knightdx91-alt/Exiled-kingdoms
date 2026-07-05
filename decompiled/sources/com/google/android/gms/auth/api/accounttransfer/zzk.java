package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzk extends com.google.android.gms.internal.auth.zzs {
    private final /* synthetic */ AccountTransferClient.zzc zzay;

    zzk(AccountTransferClient.zzc zzcVar) {
        this.zzay = zzcVar;
    }

    @Override // com.google.android.gms.internal.auth.zzs, com.google.android.gms.internal.auth.zzx
    public final void onFailure(Status status) {
        this.zzay.zza(status);
    }

    @Override // com.google.android.gms.internal.auth.zzs, com.google.android.gms.internal.auth.zzx
    public final void zzd() {
        this.zzay.setResult(null);
    }
}
