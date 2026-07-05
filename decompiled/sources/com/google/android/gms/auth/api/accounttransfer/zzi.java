package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.auth.zzah;
import com.google.android.gms.internal.auth.zzz;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzi extends AccountTransferClient.zzc {
    private final /* synthetic */ zzah zzat;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzi(AccountTransferClient accountTransferClient, zzah zzahVar) {
        super(null);
        this.zzat = zzahVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.AccountTransferClient.zzb
    protected final void zza(zzz zzzVar) {
        zzzVar.zza(this.zzax, this.zzat);
    }
}
