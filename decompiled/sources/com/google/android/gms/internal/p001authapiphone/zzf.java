package com.google.android.gms.internal.p001authapiphone;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf extends zza implements zze {
    zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
    }

    @Override // com.google.android.gms.internal.p001authapiphone.zze
    public final void zza(zzg zzgVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgVar);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }
}
