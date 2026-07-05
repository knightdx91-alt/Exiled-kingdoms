package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzh extends AccountTransferClient.zza<DeviceMetaData> {
    private final /* synthetic */ zzg zzas;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzh(zzg zzgVar, AccountTransferClient.zzb zzbVar) {
        super(zzbVar);
        this.zzas = zzgVar;
    }

    @Override // com.google.android.gms.internal.auth.zzs, com.google.android.gms.internal.auth.zzx
    public final void zza(DeviceMetaData deviceMetaData) {
        this.zzas.setResult(deviceMetaData);
    }
}
