package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zze extends com.google.android.gms.internal.auth.zza implements zzc {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.IWorkAccountService");
    }

    @Override // com.google.android.gms.auth.account.zzc
    public final void zza(zza zzaVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.auth.zzc.zza(parcelObtainAndWriteInterfaceToken, zzaVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.auth.account.zzc
    public final void zzb(boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.auth.zzc.writeBoolean(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.auth.account.zzc
    public final void zza(zza zzaVar, Account account) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.auth.zzc.zza(parcelObtainAndWriteInterfaceToken, zzaVar);
        com.google.android.gms.internal.auth.zzc.zza(parcelObtainAndWriteInterfaceToken, account);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }
}
