package com.google.android.gms.internal.drive;

import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzet extends zzb implements zzes {
    public zzet() {
        super("com.google.android.gms.drive.internal.IEventCallback");
    }

    @Override // com.google.android.gms.internal.drive.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 != 1) {
            return false;
        }
        zzc((zzfj) zzc.zza(parcel, zzfj.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
