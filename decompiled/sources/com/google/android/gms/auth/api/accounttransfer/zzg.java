package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.auth.zzz;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzg extends AccountTransferClient.zzb<DeviceMetaData> {
    private final /* synthetic */ com.google.android.gms.internal.auth.zzv zzar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzg(AccountTransferClient accountTransferClient, com.google.android.gms.internal.auth.zzv zzvVar) {
        super(null);
        this.zzar = zzvVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.AccountTransferClient.zzb
    protected final void zza(zzz zzzVar) {
        zzzVar.zza(new zzh(this, this), this.zzar);
    }
}
