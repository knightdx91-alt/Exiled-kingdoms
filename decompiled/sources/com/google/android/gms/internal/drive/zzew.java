package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzew extends zza implements zzeu {
    zzew(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.drive.internal.IEventReleaseCallback");
    }

    @Override // com.google.android.gms.internal.drive.zzeu
    public final void zza(boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}
