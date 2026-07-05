package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzaa extends zza implements zzz {
    zzaa(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
    }

    @Override // com.google.android.gms.internal.auth.zzz
    public final void zza(zzx zzxVar, zzaf zzafVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzxVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzafVar);
        transactAndReadExceptionReturnVoid(5, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzz
    public final void zza(zzx zzxVar, zzad zzadVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzxVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzadVar);
        transactAndReadExceptionReturnVoid(6, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzz
    public final void zza(zzx zzxVar, zzv zzvVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzxVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzvVar);
        transactAndReadExceptionReturnVoid(7, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzz
    public final void zza(zzx zzxVar, zzah zzahVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzxVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzahVar);
        transactAndReadExceptionReturnVoid(8, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzz
    public final void zza(zzx zzxVar, zzab zzabVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzxVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzabVar);
        transactAndReadExceptionReturnVoid(9, parcelObtainAndWriteInterfaceToken);
    }
}
