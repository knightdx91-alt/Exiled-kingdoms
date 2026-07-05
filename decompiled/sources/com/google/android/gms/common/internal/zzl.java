package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzl extends com.google.android.gms.internal.common.zza implements IGmsCallbacks {
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void onPostInitComplete(int i2, IBinder iBinder, Bundle bundle) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i2);
        parcelZza.writeStrongBinder(iBinder);
        com.google.android.gms.internal.common.zzc.zza(parcelZza, bundle);
        zzb(1, parcelZza);
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zza(int i2, Bundle bundle) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i2);
        com.google.android.gms.internal.common.zzc.zza(parcelZza, bundle);
        zzb(2, parcelZza);
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zza(int i2, IBinder iBinder, zzb zzbVar) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i2);
        parcelZza.writeStrongBinder(iBinder);
        com.google.android.gms.internal.common.zzc.zza(parcelZza, zzbVar);
        zzb(3, parcelZza);
    }
}
