package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzao extends zza implements zzan {
    zzao(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.internal.IAuthService");
    }

    @Override // com.google.android.gms.internal.auth.zzan
    public final void zza(zzal zzalVar, ProxyRequest proxyRequest) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzalVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, proxyRequest);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzan
    public final void zza(zzal zzalVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzalVar);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }
}
