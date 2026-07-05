package com.google.android.gms.internal.auth;

import android.os.Parcel;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzy extends zzb implements zzx {
    public zzy() {
        super("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        switch (i2) {
            case 1:
                zzb((Status) zzc.zza(parcel, Status.CREATOR));
                return true;
            case 2:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (com.google.android.gms.auth.api.accounttransfer.zzt) zzc.zza(parcel, com.google.android.gms.auth.api.accounttransfer.zzt.CREATOR));
                return true;
            case 3:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (com.google.android.gms.auth.api.accounttransfer.zzl) zzc.zza(parcel, com.google.android.gms.auth.api.accounttransfer.zzl.CREATOR));
                return true;
            case 4:
                zzd();
                return true;
            case 5:
                onFailure((Status) zzc.zza(parcel, Status.CREATOR));
                return true;
            case 6:
                zza(parcel.createByteArray());
                return true;
            case 7:
                zza((DeviceMetaData) zzc.zza(parcel, DeviceMetaData.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
