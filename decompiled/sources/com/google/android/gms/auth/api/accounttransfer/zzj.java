package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.auth.zzab;
import com.google.android.gms.internal.auth.zzz;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzj extends AccountTransferClient.zzc {
    private final /* synthetic */ zzab zzau;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzj(AccountTransferClient accountTransferClient, zzab zzabVar) {
        super(null);
        this.zzau = zzabVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.AccountTransferClient.zzb
    protected final void zza(zzz zzzVar) {
        zzzVar.zza(this.zzax, this.zzau);
    }
}
