package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzx extends zzc implements zzw {
    zzx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    @Override // com.google.android.gms.internal.p000authapi.zzw
    public final void zzc(zzu zzuVar, CredentialRequest credentialRequest) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zze.zzc(parcelObtainAndWriteInterfaceToken, zzuVar);
        zze.zzc(parcelObtainAndWriteInterfaceToken, credentialRequest);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.p000authapi.zzw
    public final void zzc(zzu zzuVar, zzy zzyVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zze.zzc(parcelObtainAndWriteInterfaceToken, zzuVar);
        zze.zzc(parcelObtainAndWriteInterfaceToken, zzyVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.p000authapi.zzw
    public final void zzc(zzu zzuVar, zzs zzsVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zze.zzc(parcelObtainAndWriteInterfaceToken, zzuVar);
        zze.zzc(parcelObtainAndWriteInterfaceToken, zzsVar);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.p000authapi.zzw
    public final void zzc(zzu zzuVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zze.zzc(parcelObtainAndWriteInterfaceToken, zzuVar);
        transactAndReadExceptionReturnVoid(4, parcelObtainAndWriteInterfaceToken);
    }
}
