package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzg extends zza implements zze {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.auth.IAuthManagerService");
    }

    @Override // com.google.android.gms.internal.auth.zze
    public final Bundle zza(String str, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        Parcel parcelTransactAndReadException = transactAndReadException(2, parcelObtainAndWriteInterfaceToken);
        Bundle bundle2 = (Bundle) zzc.zza(parcelTransactAndReadException, Bundle.CREATOR);
        parcelTransactAndReadException.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.auth.zze
    public final AccountChangeEventsResponse zza(AccountChangeEventsRequest accountChangeEventsRequest) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, accountChangeEventsRequest);
        Parcel parcelTransactAndReadException = transactAndReadException(3, parcelObtainAndWriteInterfaceToken);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) zzc.zza(parcelTransactAndReadException, AccountChangeEventsResponse.CREATOR);
        parcelTransactAndReadException.recycle();
        return accountChangeEventsResponse;
    }

    @Override // com.google.android.gms.internal.auth.zze
    public final Bundle zza(Account account, String str, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, account);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        Parcel parcelTransactAndReadException = transactAndReadException(5, parcelObtainAndWriteInterfaceToken);
        Bundle bundle2 = (Bundle) zzc.zza(parcelTransactAndReadException, Bundle.CREATOR);
        parcelTransactAndReadException.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.auth.zze
    public final Bundle zza(Account account) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, account);
        Parcel parcelTransactAndReadException = transactAndReadException(7, parcelObtainAndWriteInterfaceToken);
        Bundle bundle = (Bundle) zzc.zza(parcelTransactAndReadException, Bundle.CREATOR);
        parcelTransactAndReadException.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.auth.zze
    public final Bundle zza(String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        Parcel parcelTransactAndReadException = transactAndReadException(8, parcelObtainAndWriteInterfaceToken);
        Bundle bundle = (Bundle) zzc.zza(parcelTransactAndReadException, Bundle.CREATOR);
        parcelTransactAndReadException.recycle();
        return bundle;
    }
}
